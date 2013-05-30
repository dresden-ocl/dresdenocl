/**
 * 
 */
package org.dresdenocl.debug.model;

import static org.dresdenocl.debug.model.EOclDebugMessageType.ADD_LINE_BREAKPOINT;
import static org.dresdenocl.debug.model.EOclDebugMessageType.EXIT;
import static org.dresdenocl.debug.model.EOclDebugMessageType.GET_STACK;
import static org.dresdenocl.debug.model.EOclDebugMessageType.REMOVE_LINE_BREAKPOINT;
import static org.dresdenocl.debug.model.EOclDebugMessageType.RESUME;
import static org.dresdenocl.debug.model.EOclDebugMessageType.STEP_INTO;
import static org.dresdenocl.debug.model.EOclDebugMessageType.STEP_OVER;
import static org.dresdenocl.debug.model.EOclDebugMessageType.STEP_RETURN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dresdenocl.debug.IOclDebuggable;
import org.dresdenocl.debug.util.OclPair;
import org.dresdenocl.debug.util.OclStringUtil;

/**
 * @author Lars Schuetze
 * 
 */
public class OclDebuggerListener implements Runnable {

	private OclDebugCommunicationHelper m_communicationHelper;
	private Map<Long, OclPair<String, Object>> m_objectMap;
	private int m_requestPort;
	private IOclDebuggable m_debuggable;
	private boolean m_stop;
	private long m_id;

	public OclDebuggerListener(int requestPort) {

		m_requestPort = requestPort;
		m_communicationHelper = new OclDebugCommunicationHelper();
		m_objectMap = new LinkedHashMap<Long, OclPair<String, Object>>();
		m_stop = false;
		m_id = 0;
	}

	public IOclDebuggable getDebuggable() {

		return m_debuggable;
	}

	public void setDebuggable(IOclDebuggable debuggable) {

		m_debuggable = debuggable;
	}

	@Override
	public void run() {

		try {
			runDebugger();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void runDebugger() throws IOException {

		ServerSocket server = new ServerSocket(m_requestPort);
		Socket accept = server.accept();
		InputStream inputStream = accept.getInputStream();
		BufferedReader reader =
				new BufferedReader(new InputStreamReader(inputStream));
		PrintStream output = new PrintStream(accept.getOutputStream());

		OclDebugMessage command;
		while (!m_stop) {
			System.out.println("OclDebuggerListener m_stop = " + m_stop);
			command = m_communicationHelper.receive(reader);
			System.out.println("OclDebuggerListener command = " + command);
			if (command == null) {
				break;
			}
			if (command.hasType(EXIT)) {
				m_debuggable.terminate();
				m_stop = true;
			}
			else if (command.hasType(RESUME)) {
				System.out.println("OclDebuggerListener RESUME");
				m_debuggable.resume();
			}
			else if (command.hasType(STEP_OVER)) {
				m_debuggable.stepOver();
			}
			else if (command.hasType(STEP_INTO)) {
				m_debuggable.stepInto();
			}
			else if (command.hasType(STEP_RETURN)) {
				m_debuggable.stepReturn();
			}
			else if (command.hasType(ADD_LINE_BREAKPOINT)) {
				String location = command.getArgument(0);
				int line = Integer.parseInt(command.getArgument(1));
				m_debuggable.addLineBreakPoint(location, line);
			}
			else if (command.hasType(REMOVE_LINE_BREAKPOINT)) {
				String location = command.getArgument(0);
				int line = Integer.parseInt(command.getArgument(1));
				m_debuggable.removeLineBreakPoint(location, line);
			}
			else if (command.hasType(GET_STACK)) {
				final String[] stack = m_debuggable.getStack();
				String controlStack = OclStringUtil.encode('#', stack);
				OclDebugMessage message =
						new OclDebugMessage(EOclDebugMessageType.GET_STACK_RESPONSE,
								new String[] { controlStack });
				m_communicationHelper.sendEvent(message, output);
			}
			else if (command.hasType(EOclDebugMessageType.GET_FRAME_VARIABLES)) {
				String stackFrame = command.getArgument(0);
				Map<String, Object> frameVariables =
						m_debuggable.getFrameVariables(stackFrame);

				List<String> variableIds = new LinkedList<String>();
				for (String name : frameVariables.keySet()) {
					Object value = frameVariables.get(name);
					long id = getObjectId(name, value);
					variableIds.add(Long.toString(id));
				}
				OclDebugMessage message =
						new OclDebugMessage(
								EOclDebugMessageType.GET_FRAME_VARIABLES_RESPONSE,
								variableIds.toArray(new String[0]));
				m_communicationHelper.sendEvent(message, output);
			}
			else if (command.hasType(EOclDebugMessageType.GET_VARIABLES)) {
				String[] arguments = command.getArguments();
				List<String> varStrings = new LinkedList<String>();
				for (String argument : arguments) {
					Long id = Long.parseLong(argument);
					Object next = m_objectMap.get(id).getRight();
					String varString = convertToString(id, next);
					varStrings.add(varString);
				}
				OclDebugMessage message =
						new OclDebugMessage(EOclDebugMessageType.GET_VARIABLES_RESPONSE,
								varStrings.toArray(new String[0]));
				m_communicationHelper.sendEvent(message, output);
			}
			else if (command.hasType(EOclDebugMessageType.FINISHED_CONSTRAINT)) {
				// ignore this event
			}
			else {
				System.out.println("ERROR: Unrecognized command (" + command + ")!");
				output.append("Unrecognized command!");
			}
		}
		System.out.println("OclDebuggerListener END WHILE");
		//m_objectMap.clear();
		server.close();
	}

	private String convertToString(long id, Object object) {

		String name = m_objectMap.get(id).getLeft();
		Map<String, Object> properties = new LinkedHashMap<String, Object>();
		properties.put("!name", name);
		properties.put("!id", Long.toString(id));
		properties.put("!type", object.getClass().toString());
		properties.put("!valueString", object.toString());
		return OclStringUtil.convertToString(properties);
	}

	private long getObjectId(String name, Object value) {

		OclPair<String, Object> pair = new OclPair<String, Object>(name, value);
		if (m_objectMap.containsValue(pair)) {
			for (Long nextId : m_objectMap.keySet()) {
				OclPair<String, Object> p = m_objectMap.get(nextId);
				if (pair.equals(p)) {
					return nextId;
				}
			}
		}
		else {
			long id = m_id++;
			m_objectMap.put(id, pair);
			return id;
		}
		return -1;
	}
}
