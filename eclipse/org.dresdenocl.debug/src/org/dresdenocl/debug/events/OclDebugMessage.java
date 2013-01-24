package org.dresdenocl.debug.events;

import java.util.List;

public class OclDebugMessage {

	private static final char DELIMITER = ':';
	private EOclDebugMessageType m_type;
	private String[] m_arguments;

	public OclDebugMessage(EOclDebugMessageType type, String[] arguments) {
		m_type = type;
		m_arguments = arguments;
	}

	public boolean hasType(EOclDebugMessageType type) {
		return m_type.equals(type);
	}
	
	public static OclDebugMessage deserialize(String response) {
		List<String> parts = null; //org.dresdenocl.language.ocl.resource.ocl.util.OclStringUtil.decode(response, DELIMITER);
		String messageType = parts.get(0);
		String[] arguments = new String[parts.size() - 1];
		for (int i = 1; i < parts.size(); i++) {
			arguments[i - 1] = parts.get(i);
		}
		EOclDebugMessageType type = EOclDebugMessageType.valueOf(messageType);
		OclDebugMessage message = new OclDebugMessage(type, arguments);
		return message;
	}
}
