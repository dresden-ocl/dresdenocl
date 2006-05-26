/*
 * Created on 13.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.ModelAccessException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.util.ModelFactory;

/**
 * M2MTransformation is an abstract implementation for 
 * all model-to-model transformations.
 * 
 * It contains the implementation logic for the 
 * instanciation of the target model and the return of
 * the transformation result.
 * @author Christian Wende
 *
 * @param <M_IN>
 * @param <M_OUT>
 */
public abstract class M2MTransformation<M_IN extends RefPackage, M_OUT extends RefPackage> extends M2XTransformation {
	
	
	protected M_OUT model_out;
	protected M_IN model_in;
	protected String transformationId;
	
	
	/**
	 * The constructor for a M2MTransformation.
	 * @param modelInName The name of the in model.
	 * @param modelOutName The name of the out model.
	 * @param modelOutType The type of the out model.
	 * @throws ModelAccessException
	 */
	public M2MTransformation(String modelInName, String modelOutName, String modelOutType) throws ModelAccessException {
		super(modelInName, modelOutName);
		
		this.model_in = (M_IN) super.model_in;
		
		model_out = new ModelFactory<M_OUT>().createExtent(modelOutType, modelOutName, true);
		
	}
	

	public M_OUT getResult() {
		return model_out;
	}
	
	public Trace getTrace() {
		return trace;
	}

	protected void trace(TraceType t, String msg) {
		trace.addTrace(t, msg);
	}
	
	public abstract void invoke() throws InvalidModelException, TransformationException;
	
	protected abstract void initRequiredParameters();
	
	public String getInModelType() {
		return in_type;
	}
	public String getOutType() {
		return out_type;
	}		

		
}
