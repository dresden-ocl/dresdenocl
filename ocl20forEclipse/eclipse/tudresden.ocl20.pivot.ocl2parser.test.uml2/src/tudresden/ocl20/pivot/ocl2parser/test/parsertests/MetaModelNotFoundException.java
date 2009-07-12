/**
 * 
 */
package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

/**
 * @author Christoph Dähne
 *
 */
public class MetaModelNotFoundException extends Exception {

	/**
	 * 
	 */
	public MetaModelNotFoundException() {
	}

	/**
	 * @param message
	 */
	public MetaModelNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MetaModelNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MetaModelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
