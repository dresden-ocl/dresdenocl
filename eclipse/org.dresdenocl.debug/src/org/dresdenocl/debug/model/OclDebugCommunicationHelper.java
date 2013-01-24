package org.dresdenocl.debug.model;

import java.io.BufferedReader;
import java.io.IOException;

import org.dresdenocl.debug.events.OclDebugMessage;


public class OclDebugCommunicationHelper {
	
	public OclDebugMessage receive(BufferedReader reader) {
		try {
			String response = reader.readLine();
			if(response == null) {
				return null;
			}
			OclDebugMessage message = null;
			return message;
		} catch(IOException e) {
			return null;
		}
	}
}
