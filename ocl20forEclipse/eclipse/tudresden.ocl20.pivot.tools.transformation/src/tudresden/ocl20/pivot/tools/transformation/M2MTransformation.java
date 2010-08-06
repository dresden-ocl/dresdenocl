/*
 * Created on 13.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;

/**
 * M2MTransformation is an abstract implementation for all model-to-model
 * transformations.
 * 
 * It contains the implementation logic for the instanciation of the target
 * model and the return of the transformation result.
 * 
 * @author Christian Wende
 * 
 * @param <M_IN>
 * @param <M_OUT>
 */
public abstract class M2MTransformation<M_IN extends EObject, M_OUT extends EObject>
		extends M2XTransformation<M_IN, M_OUT> {

	protected String transformationId;

	/**
	 * The constructor for a M2MTransformation.
	 * 
	 * @param modelInName
	 *          The name of the in model.
	 * @param modelOutName
	 *          The name of the out model.
	 * @param modelOutType
	 *          The type of the out model.
	 * @throws ModelAccessException
	 */
	public M2MTransformation(String modelInName, String modelOutName,
			String modelOutType) throws ModelAccessException {

		super(modelInName, modelOutName);
	}

	public M2MTransformation() {

		super();
	}

	public M_OUT getResult() {

		return out;
	}

	public abstract void invoke() throws InvalidModelException,
			TransformationException;

	public String getInModelType() {

		return in_type;
	}

	public String getOutType() {

		return out_type;
	}

}
