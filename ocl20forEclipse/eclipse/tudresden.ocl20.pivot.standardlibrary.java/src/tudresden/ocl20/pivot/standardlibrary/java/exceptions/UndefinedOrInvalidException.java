package tudresden.ocl20.pivot.standardlibrary.java.exceptions;



public abstract class UndefinedOrInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 2860657452868584307L;

	protected UndefinedOrInvalidException() {
		super();
	}
	
	protected UndefinedOrInvalidException(Throwable cause) {
		super(cause);
	}
	
}
