/*
 * Created on 17.01.2006
 *
 */
package org.dresdenocl.tools.transformation;

import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;

/**
 * ITransformation represents the interface that each metamodelbased
 * transformation, which should be managed and executed in the
 * TransformationEngine, must implement.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 */
public interface ITransformation<IN, SETTINGS, OUT> {

	/**
	 * Returns the result of the transformation.
	 * 
	 * @return Returns the result of the transformation.
	 */
	public OUT getResult();

	/**
	 * Invokes the metamodelbased transformation.
	 * 
	 * @throws TransformationException
	 * @throws InvalidModelException
	 */
	public void invoke() throws TransformationException, InvalidModelException;

	public void setParameterIN(IN in);

	public void setParameterOUT(OUT out);

	public String getDisplayName();

	public void setSettings(SETTINGS settings);

}
