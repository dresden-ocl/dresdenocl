package org.dresdenocl.standardlibrary.java.internal.library;

public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = -7940668036364712604L;

	public InvalidException(Throwable cause) {

		super("Tried to invoke isTrue() on invalid boolean value.", cause);
	}

}
