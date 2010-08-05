/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;

/**
 * M2X Transformation is an abstract implementation for all
 * model-to-x transformations. This abstract class implements
 * the logic to load the in model, to set configuration parameters
 * and to return the result entity of a transformation.
 * 
 * @author Christian Wende
 *
 **/
public abstract class M2XTransformation<M_IN extends EObject, OUT> implements ITransformation<M_IN,OUT> {
	
		
	protected M_IN model_in;
	protected OUT out;
	
	protected static String in_type;
	protected static String out_type;
	
	protected static String out_name;
	
		
	public M2XTransformation(String modelIn, String modelOut) throws ModelAccessException {
		in_type = modelIn;
		out_name = modelOut;
				
	}
	
	public M2XTransformation() {
		super();
	}

	public OUT getResult() {
		return out;
	}

	public abstract void invoke() throws TransformationException, InvalidModelException;
	
	public String getInModelType() {
		return in_type;
	}
	
	public void setParameterIN(M_IN in) {
		model_in = in;	
	}

	public void setParameterOUT(OUT out) {
		this.out = out;
	}

	public String getDisplayName() {
		return in_type + " to " + out_type;
	}
	
}
