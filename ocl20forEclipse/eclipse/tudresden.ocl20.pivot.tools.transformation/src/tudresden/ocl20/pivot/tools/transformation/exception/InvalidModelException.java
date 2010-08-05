/*
 * Created on 10.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.exception;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.transformation.ITransformation;

/**
 * The InvalidModelException is thrown, when a given model has features that are not allowed.
 * @author Christian Wende
 *
 */
public class InvalidModelException extends Exception {

	private static final long serialVersionUID = -7842393918104473120L;
	
	private EObject errorModel;
	private ITransformation<?, ?> errorTransformation;
	
	/**
	 * The standard constructor for the InvalidModelException.
	 * @param message A helpful explanation why the Exception was thrown.
	 * @param errorModel The Modeltype of the invalid model.
	 * @param transformation The transformation, in which the Exception occured.
	 */
	public InvalidModelException(final String message, final EObject errorModel, final ITransformation<?, ?> transformation) {
		super(message);
		this.errorModel = errorModel;
		this.errorTransformation = transformation;
	}

	public InvalidModelException(final String message, final EObject errorModel) {
		super(message);
		this.errorModel = errorModel;
		
	}

	/**
	 * @return Returns the errorModel.
	 */
	public EObject getErrorModel() {
		return errorModel;
	}

	/**
	 * @return Returns the errorTransformation.
	 */
	public ITransformation<?, ?> getErrorTransformation() {
		return errorTransformation;
	}

}
