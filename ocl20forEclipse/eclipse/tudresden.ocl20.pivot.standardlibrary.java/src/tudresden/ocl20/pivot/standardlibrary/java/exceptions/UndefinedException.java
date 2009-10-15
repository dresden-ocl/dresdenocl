package tudresden.ocl20.pivot.standardlibrary.java.exceptions;

public class UndefinedException extends UndefinedOrInvalidException {

	private static final long serialVersionUID = 1311891077162504304L;

	private String undefinedReason;

	public UndefinedException(String undefinedReason) {

		this.undefinedReason = undefinedReason;
	}

	public String getUndefinedReason() {

		return undefinedReason;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {

		return this.undefinedReason;
	}
}