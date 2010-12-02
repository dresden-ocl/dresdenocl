package tudresden.ocl20.testautomation.exceptions;

public class TestFileParseException extends Exception {

	private static final long serialVersionUID = -6056023760725504796L;

	public TestFileParseException(String msg) {

		super(msg);
	}

	public TestFileParseException(String msg, Throwable cause) {

		super(msg, cause);
	}

	public TestFileParseException(Throwable cause) {

		super(cause);
	}
}
