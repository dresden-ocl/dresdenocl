package org.dresdenocl.debug.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.dresdenocl.interpreter.debug.EOclDebugMessageType;
import org.dresdenocl.interpreter.debug.OclDebugCommunicationHelper;
import org.dresdenocl.interpreter.debug.OclDebugMessage;
import org.dresdenocl.interpreter.debug.util.OclStringUtil;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class OclDebugProxy {

	private final int STARTUP_DELAY = 1000; // ms

	private OclDebugTarget m_debugTarget;
	private OclDebugCommunicationHelper m_communicationHelper;

	private PrintStream m_output;
	private BufferedReader m_reader;

	public OclDebugProxy(OclDebugTarget debugTarget, final int requestPort)
			throws UnknownHostException, IOException {
		m_debugTarget = debugTarget;
		m_communicationHelper = new OclDebugCommunicationHelper();
		try {
			Thread.sleep(STARTUP_DELAY);
		} catch (InterruptedException e) {

		}
		startSocket(requestPort);
	}

	private void startSocket(final int requestPort)
			throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", requestPort);
		try {
			BufferedInputStream in = new BufferedInputStream(
					client.getInputStream());
			m_reader = new BufferedReader(new InputStreamReader(in));
			m_output = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void addLineBreakpoint(final String location, final int line) {
		OclDebugMessage message = new OclDebugMessage(
				EOclDebugMessageType.ADD_LINE_BREAKPOINT, new String[] {
						location, Integer.toString(line) });
		m_communicationHelper.sendEvent(message, m_output);
	}

	public void removeLineBreakpoint(final String location, final int line) {
		OclDebugMessage message = new OclDebugMessage(
				EOclDebugMessageType.REMOVE_LINE_BREAKPOINT, new String[] {
						location, Integer.toString(line) });
		m_communicationHelper.sendEvent(message, m_output);
	}

	public void resume() {
		sendCommand(EOclDebugMessageType.RESUME);
	}

	public void stepOver() {
		sendCommand(EOclDebugMessageType.STEP_OVER);
	}

	public void stepInto() {
		sendCommand(EOclDebugMessageType.STEP_INTO);
	}

	public void stepReturn() {
		sendCommand(EOclDebugMessageType.STEP_RETURN);
	}

	public void terminate() {
		sendCommand(EOclDebugMessageType.EXIT);
	}

	public OclDebugMessage getStack() {
		return sendCommandAndRead(EOclDebugMessageType.GET_STACK);
	}

	private void sendCommand(EOclDebugMessageType command, String... parameters) {
		OclDebugMessage message = new OclDebugMessage(command, parameters);
		m_communicationHelper.sendEvent(message, m_output);
	}

	private OclDebugMessage sendCommandAndRead(EOclDebugMessageType command,
			String... parameters) {
		OclDebugMessage message = new OclDebugMessage(command, parameters);
		return m_communicationHelper
				.sendAndReceive(message, m_output, m_reader);
	}

	public IVariable[] getStackVariables(String stackFrame) {
		OclDebugMessage response = sendCommandAndRead(
				EOclDebugMessageType.GET_FRAME_VARIABLES,
				new String[] { stackFrame });
		String[] ids = response.getArguments();
		// fetch all variables
		IVariable[] variables = getVariables(ids);
		return variables;
	}

	// TODO unterstand
	public IVariable[] getVariables(String... requestedIDs) {
		OclDebugMessage response = sendCommandAndRead(
				EOclDebugMessageType.GET_VARIABLES, requestedIDs);
		String[] varStrings = response.getArguments();
		OclDebugVariable[] variables = new OclDebugVariable[varStrings.length];
		int i = 0;
		for (String varString : varStrings) {
			java.util.Map<String, String> properties = OclStringUtil
					.convertFromString(varString);

			// convert varString to variables and values
			String valueString = properties.get("!valueString");
			String valueRefType = "valueRefType";
			Map<String, Long> childVariables = new TreeMap<String, Long>(
					new Comparator<String>() {
						public int compare(String s1, String s2) {
							return s1.compareToIgnoreCase(s2);
						}
					});
			for (String property : properties.keySet()) {
				// ignore special properties - they are not children
				if (property.startsWith("!")) {
					continue;
				}
				childVariables.put(property,
						Long.parseLong(properties.get(property)));
			}
			String id = properties.get("!id");
			IValue value = new OclDebugValue(m_debugTarget, id, valueString,
					valueRefType, childVariables);

			String variableName = properties.get("!name");
			String variableRefType = properties.get("!type");

			OclDebugVariable variable = new OclDebugVariable(m_debugTarget,
					variableName, value, variableRefType);
			variables[i++] = variable;
		}
		return variables;
	}
}
