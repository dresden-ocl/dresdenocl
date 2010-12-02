/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import java.util.Collection;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.ModelAccessException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.util.ModelFactory;

/**
 * M2X Transformation is an abstract implementation for all
 * model-to-x transformations. This abstract class implements
 * the logic to load the in model, to set configuration parameters
 * and to return the result entity of a transformation.
 * 
 * @author Christian Wende
 *
 **/
public abstract class M2XTransformation<M_IN extends RefPackage, OUT> implements ITransformation {
	
		
	protected M_IN model_in;
	protected TConfiguration conf;
	protected Trace trace;
	protected OUT out;
	
	protected static String in_type;
	protected static String out_type;
	protected static String out_name;
	
		
	public M2XTransformation(String modelInName, String outname) throws ModelAccessException {
		conf = new TConfiguration();
		initRequiredParameters();
		trace = new Trace();
		model_in = new ModelFactory<M_IN>().accessExtent(modelInName);
		out_name = outname;
				
	}
	
	public void setConfigurationParameter(String key, String value) {
		conf.put(key,value);
	}
	
	public Trace getTrace() {
		return trace;
	}
 	
	protected void trace(TraceType t, String msg) {
		trace.addTrace(t, msg);
	}
	

	public OUT getResult() {
		return out;
	}

	
	public abstract void invoke() throws TransformationException, InvalidModelException;
	
	protected abstract void initRequiredParameters();
	
	public String getInModelType() {
		return in_type;
	}
	
	public Collection<TParameter> getRequiredParameters() {
		return conf.getRequiredParameters();
	}
	
	
}
