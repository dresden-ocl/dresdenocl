/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;


/**
 * ITransformation represents the interface that each metamodelbased
 * transformation, which should be managed and executed in the 
 * TransformationEngine, must implement.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 */
public interface ITransformation<IN, OUT> {
	
	/**
	 * Returns the result of the transformation.
	 * @return Returns the result of the transformation.
	 */
	public OUT getResult();


	/**
	 * Invokes the metamodelbased transformation.
	 * @throws TransformationException
	 * @throws InvalidModelException
	 */
	public void invoke() throws TransformationException, InvalidModelException;
	

	public void setParameterIN(IN in);
	
	public void setParameterOUT(OUT out);
	
	public String getDisplayName();
	
	public void setSettings(IOcl2CodeSettings ocl2CodeSettings);
	
		
}
