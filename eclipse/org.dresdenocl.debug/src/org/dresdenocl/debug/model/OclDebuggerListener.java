/**
 * 
 */
package org.dresdenocl.debug.model;

import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.ADD_LINE_BREAKPOINT;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.EXIT;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.GET_STACK;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.REMOVE_LINE_BREAKPOINT;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.RESUME;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.STEP_INTO;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.STEP_OVER;
import static org.dresdenocl.interpreter.debug.EOclDebugMessageType.STEP_RETURN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.dresdenocl.interpreter.IOclDebuggable;
import org.dresdenocl.interpreter.debug.EOclDebugMessageType;
import org.dresdenocl.interpreter.debug.OclDebugCommunicationHelper;
import org.dresdenocl.interpreter.debug.OclDebugMessage;

/**
 * @author Lars Schuetze
 * 
 */
public class OclDebuggerListener implements Runnable {

	private OclDebugCommunicationHelper m_communicationHelper;
	private int m_requestPort;
	private IOclDebuggable m_debuggable;
	private boolean m_stop;

	public OclDebuggerListener(int requestPort) {
		m_requestPort = requestPort;
		m_communicationHelper = new OclDebugCommunicationHelper();
		m_stop = false;
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		PrintStream output = new PrintStream(accept.getOutputStream());
		
		OclDebugMessage command;
		while(!m_stop) {
			System.out.println("OclDebuggerListener m_stop = " + m_stop);
			command = m_communicationHelper.receive(reader);
			System.out.println("OclDebuggerListener command = " + command);
			if(command == null) {
				break;
			}
			if(command.hasType(EXIT)) {
				m_debuggable.terminate();
				m_stop = true;
			} else if(command.hasType(RESUME)) {
				System.out.println("OclDebuggerListener RESUME");
				m_debuggable.resume();
			} else if(command.hasType(STEP_OVER)) {
				m_debuggable.stepOver();
			} else if(command.hasType(STEP_INTO)) {
				m_debuggable.stepInto();
			} else if(command.hasType(STEP_RETURN)) {
				m_debuggable.stepReturn();
			} else if(command.hasType(ADD_LINE_BREAKPOINT)) {
				String location = command.getArgument(0);
				int line = Integer.parseInt(command.getArgument(1));
				m_debuggable.addLineBreakPoint(location, line);
			} else if(command.hasType(REMOVE_LINE_BREAKPOINT)) {
				String location = command.getArgument(0);
				int line = Integer.parseInt(command.getArgument(1));
				m_debuggable.removeLineBreakPoint(location, line);
			} else if(command.hasType(GET_STACK)) {
				//TODO get stack
			} else if(command.hasType(EOclDebugMessageType.GET_FRAME_VARIABLES)) {
				//TODO get frame variables
			} else if(command.hasType(EOclDebugMessageType.GET_VARIABLES)) {
				//TODO get variables
			} else {
				System.out.println("ERROR: Unrecognized command (" + command + ")!");
				output.append("Unrecognized command!");
			}
		}
		System.out.println("OclDebuggerListener END WHILE");
		server.close();
	}

}
