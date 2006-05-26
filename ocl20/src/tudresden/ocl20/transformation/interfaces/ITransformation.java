/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.transformation.interfaces;

import java.util.Collection;

import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;


/**
 * ITransformation represents the interface that each metamodelbased
 * transformation, which should be managed and executed in the 
 * TransformationEngine, must implement.
 * 
 * @author Christian Wende
 */
public interface ITransformation<IN, OUT> {
	
	/**
	 * * This method sets the given value fot the given parameter
	 
	 * @param key The name of the parameter to set.
	 * @param value The value that should be set for the parameter
	 */
	public void setConfigurationParameter(String key, String value);
	/**
	 * Returns the result of the transformation.
	 * @return Returns the result of the transformation.
	 */
	public OUT getResult();
	/**
	 * Returns the trace of the transformation process.
	 * @return Returns the trace of the transformation process.
	 */
	public Trace getTrace();
	/**
	 * Invokes the metamodelbased transformation.
	 * @throws TransformationException
	 * @throws InvalidModelException
	 */
	public void invoke() throws TransformationException, InvalidModelException;
	/**
	 * Returns a collection of all Parameters, that can be set for the transformation.
	 * @return Returns a collection of all Parameters, that can be set for the transformation.
	 */
	public Collection<TParameter> getRequiredParameters();
	
		
}
