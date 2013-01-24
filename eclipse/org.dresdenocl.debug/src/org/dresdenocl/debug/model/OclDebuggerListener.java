/**
 * 
 */
package org.dresdenocl.debug.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.dresdenocl.interpreter.internal.OclInterpreter;

/**
 * @author Lars Schuetze
 * 
 */
public class OclDebuggerListener implements Runnable {

	private OclDebugCommunicationHelper m_comHelper;
	private int m_requestPort;
	private OclInterpreter m_debuggable;
	private boolean stop;

	public OclDebuggerListener(int requestPort) {
		m_requestPort = requestPort;
		stop = false;
	}

	public OclInterpreter getDebuggable() {
		return m_debuggable;
	}

	public void setDebuggable(OclInterpreter debuggable) {
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
		
		while(!stop) {
		}
		server.close();
	}

}
