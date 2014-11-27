package org.dresdenocl.debug.model;

import java.util.List;

import org.dresdenocl.debug.util.OclStringUtil;

public class OclDebugMessage {

	private static final char DELIMITER = ':';
	private EOclDebugMessageType m_type;
	private String[] m_arguments;

	public OclDebugMessage(EOclDebugMessageType type, String[] arguments) {

		m_type = type;
		m_arguments = arguments;
	}

	public OclDebugMessage(EOclDebugMessageType messageType,
			List<String> arguments) {

		super();
		m_type = messageType;
		m_arguments = arguments.toArray(m_arguments);
	}

	public EOclDebugMessageType getMessageType() {

		return m_type;
	}

	public String[] getArguments() {

		return m_arguments;
	}

	public String serialize() {

		java.util.List<String> parts = new java.util.ArrayList<String>();
		parts.add(m_type.name());
		for (String argument : m_arguments) {
			parts.add(argument);
		}
		return OclStringUtil.encode(DELIMITER, parts);
	}

	public boolean hasType(EOclDebugMessageType type) {

		return m_type.equals(type);
	}

	public static OclDebugMessage deserialize(String response) {

		List<String> parts = OclStringUtil.decode(response, DELIMITER);
		String messageType = parts.get(0);
		String[] arguments = new String[parts.size() - 1];
		for (int i = 1; i < parts.size(); i++) {
			arguments[i - 1] = parts.get(i);
		}
		EOclDebugMessageType type = EOclDebugMessageType.valueOf(messageType);
		OclDebugMessage message = new OclDebugMessage(type, arguments);
		return message;
	}

	public String getArgument(int index) {

		return m_arguments[index];
	}

	public String toString() {

		return this.getClass().getSimpleName() + "[" + m_type.name() + ": "
				+ OclStringUtil.explode(m_arguments, ", ") + "]";
	}
}
