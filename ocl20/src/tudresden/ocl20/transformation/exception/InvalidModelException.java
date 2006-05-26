/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.transformation.exception;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.transformation.interfaces.ITransformation;

/**
 * The InvalidModelException is thrown, when a given model has features that are not allowed.
 * @author Christian Wende
 *
 */
public class InvalidModelException extends Exception {

	private RefPackage errorModel;
	private ITransformation errorTransformation;
	
	/**
	 * The standard constructor for the InvalidModelException.
	 * @param message A helpful explanation why the Exception was thrown.
	 * @param errorModel The Modeltype of the invalid model.
	 * @param transformation The transformation, in which the Exception occured.
	 */
	public InvalidModelException(String message, RefPackage errorModel, ITransformation transformation) {
		super(message);
		this.errorModel = errorModel;
		this.errorTransformation = transformation;
	}

	/**
	 * @return Returns the errorModel.
	 */
	public RefPackage getErrorModel() {
		return errorModel;
	}

	/**
	 * @return Returns the errorTransformation.
	 */
	public ITransformation getErrorTransformation() {
		return errorTransformation;
	}

}
