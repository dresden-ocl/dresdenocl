/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.transformation.exception;

/**
 * The TemplateException is thrown, when an error occurs while working with code templates.
 * @author Christian Wende
 *
 */
public class TemplateException extends Exception {

	/**
	 * The standard constructor for a TemplateException.
	 * @param message A helpful explanation why the Exception was thrown.
	 */
	public TemplateException(String message) {
		super(message);
	}

}
