/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.exception;

import tudresden.ocl20.pivot.tools.transformation.ITransformation;

/**
 * The TransformationExcpetion is thrown, when an error occurs while running a
 * metamodelbased transformation.
 * 
 * @author Christian Wende
 * 
 */
public class TransformationException extends Exception {

	private static final long serialVersionUID = -8882468425456595874L;

	private ITransformation<?, ?> transformation;

	/**
	 * The standard constructor for a TransformationException.
	 * 
	 * @param messageA
	 *          helpful explanation why the exception was thrown
	 * @param impl
	 *          The transformation in which the error occured.
	 */
	public TransformationException(String message, ITransformation<?, ?> impl) {

		super(message);
		this.transformation = impl;
	}

	/**
	 * Returns the transformation in which the error occured.
	 * 
	 * @return The transformation in which the error occured.
	 * 
	 */
	public ITransformation<?, ?> getTransformation() {

		return transformation;
	}
}
