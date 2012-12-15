/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.exception;

/**
 * The CreationException is thrown, when an error occurs while reflectivly
 * creating model elements.
 * 
 * @author Christian Wende
 * 
 */
public class CreationException extends RuntimeException {

	private static final long serialVersionUID = -2215575186034871743L;

	/**
	 * The standard constructor for the CreationException.
	 * 
	 * @param message
	 *          A helpful explanation why the Exception was thrown.
	 */
	public CreationException(String message) {

		super(message);
	}
}
