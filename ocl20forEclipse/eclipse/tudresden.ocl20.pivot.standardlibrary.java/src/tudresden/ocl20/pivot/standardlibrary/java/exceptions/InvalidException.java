package tudresden.ocl20.pivot.standardlibrary.java.exceptions;


public class InvalidException extends UndefinedOrInvalidException {

	private static final long serialVersionUID = 336264215373528449L;

	private Throwable invalidReason;

	public InvalidException(Throwable cause) {
		super(cause);
		this.invalidReason = cause;
	}
	
	public Throwable getInvalidReason() {
		return invalidReason;
	}
	
}
