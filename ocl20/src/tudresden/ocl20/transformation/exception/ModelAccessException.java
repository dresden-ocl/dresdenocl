/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.transformation.exception;

/**
 * The ModelAccessException is thrown, when an illegal modelaccess occured.
 * @author Christian Wende
 *
 */
public class ModelAccessException extends Exception {

	/**
	 * A standard constructor for a ModelAccessException
	 * @param message A helpful explanation why the Exception was thrown.
	 * @param modeltype The type of the model on which the illegal access occured.
	 * @param modelname The name of the model on which the illegal access occured.
	 */
	public ModelAccessException(String message, String modeltype, String modelname) {
		super(message + "[Model: " + modelname + " : " + modeltype +"]");
	}
	
	/**
	 * A standard constructor for a ModelAccessException
	 * @param message A helpful explanation why the Exception was thrown.
	 * @param modelname The name of the model on which the illegal access occured.
	 */
	public ModelAccessException(String message, String modelname) {
		super(message + "[Model: " + modelname + "]");
	}

}
