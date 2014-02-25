package org.dresdenocl.debug.model;

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
			return response == null ? null : OclDebugMessage.deserialize(response);
		} catch (IOException e) {
			System.out.println("IO Error occured");
			return null;
		}
	}
}
