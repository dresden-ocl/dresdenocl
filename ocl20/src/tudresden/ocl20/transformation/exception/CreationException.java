/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.transformation.exception;

/**
 * The CreationException is thrown, when an error occurs while reflectivly creating model elements.
 * @author Christian Wende
 *
 */
public class CreationException extends RuntimeException {

	/**
	 * The standard constructor for the CreationException.
	 * @param message A helpful explanation why the Exception was thrown.
	 */
	public CreationException(String message) {
		super(message);
	}
}
