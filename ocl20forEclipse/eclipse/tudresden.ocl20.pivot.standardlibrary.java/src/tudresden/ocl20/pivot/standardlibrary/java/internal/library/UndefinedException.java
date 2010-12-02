package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

public class UndefinedException extends RuntimeException {

	private static final long serialVersionUID = -1687367024366924472L;

	public UndefinedException(String cause) {

		super("Tried to invoke isTrue() on undefined. Reason: " + cause);
	}

}
