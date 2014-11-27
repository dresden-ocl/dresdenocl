/*
 * Created on 26.01.2006
 *
 */
package org.dresdenocl.tools.transformation;

import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;

/**
 * Transformation is an abstract implementation for all 
 * transformations. This abstract class implements the logic to load the in
 * model, to set configuration parameters and to return the result entity of a
 * transformation.
 * 
 * @author Christian Wende
 * 
 **/
public abstract class Transformation<IN, SETTINGS, OUT>
		implements ITransformation<IN, SETTINGS, OUT> {

	protected IN in;
	protected OUT out;

	protected static String in_name;
	protected static String out_name;

	protected SETTINGS settings;

	public Transformation(String modelIn, String modelOut) {

		in_name = modelIn;
		out_name = modelOut;

	}

	public OUT getResult() {

		return out;
	}

	public abstract void invoke() throws TransformationException,
			InvalidModelException;

	public void setParameterIN(IN in) {

		this.in = in;
	}

	public void setParameterOUT(OUT out) {

		this.out = out;
	}

	public String getDisplayName() {

		return in_name + " To " + out_name;
	}

	public void setSettings(SETTINGS settings) {

		this.settings = settings;

	}

}
