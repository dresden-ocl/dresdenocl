package org.dresdenocl.interpreter.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;


public class OclDebugCommunicationHelper {

	public void sendEvent(OclDebugMessage message, PrintStream stream) {
		synchronized (stream) {
			stream.println(message.serialize());
		}
	}

	public OclDebugMessage sendAndReceive(OclDebugMessage message,
			PrintStream stream, BufferedReader reader) {
		synchronized (stream) {
			sendEvent(message, stream);
			OclDebugMessage response = receive(reader);
			return response;
		}
	}

	public OclDebugMessage receive(BufferedReader reader) {
		try {
			String response = reader.readLine();
			if (response == null) {
				return null;
			}
			OclDebugMessage message = OclDebugMessage.deserialize(response);
			return message;
		} catch (IOException e) {
			return null;
		}
	}
}
