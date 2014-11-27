/*
 * Created on 10.02.2006
 *
 */
package org.dresdenocl.tools.transformation.exception;

/**
 * The ModelAccessException is thrown, when an illegal modelaccess occured.
 * 
 * @author Christian Wende
 * 
 */
public class ModelAccessException extends Exception {

	private static final long serialVersionUID = -7842399918104473120L;

	/**
	 * A standard constructor for a ModelAccessException
	 * 
	 * @param message
	 *          A helpful explanation why the Exception was thrown.
	 * @param modeltype
	 *          The type of the model on which the illegal access occured.
	 * @param modelname
	 *          The name of the model on which the illegal access occured.
	 */
	public ModelAccessException(String message, String modeltype, String modelname) {

		super(message + "[Model: " + modelname + " : " + modeltype + "]");
	}

	/**
	 * A standard constructor for a ModelAccessException
	 * 
	 * @param message
	 *          A helpful explanation why the Exception was thrown.
	 * @param modelname
	 *          The name of the model on which the illegal access occured.
	 */
	public ModelAccessException(String message, String modelname) {

		super(message + "[Model: " + modelname + "]");
	}

}
