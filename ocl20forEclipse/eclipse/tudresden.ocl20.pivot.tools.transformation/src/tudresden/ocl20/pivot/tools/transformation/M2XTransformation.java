/*
 * Created on 26.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;

/**
 * M2X Transformation is an abstract implementation for all model-to-x
 * transformations. This abstract class implements the logic to load the in
 * model, to set configuration parameters and to return the result entity of a
 * transformation.
 * 
 * @author Christian Wende
 * 
 **/
public abstract class M2XTransformation<M_IN extends EObject, SETTINGS, OUT>
		implements ITransformation<M_IN, SETTINGS, OUT> {

	protected M_IN model_in;
	protected OUT out;

	protected static String in_name;
	protected static String out_name;

	protected SETTINGS settings;

	public M2XTransformation(String modelIn, String modelOut) {

		in_name = modelIn;
		out_name = modelOut;

	}

	public OUT getResult() {

		return out;
	}

	public abstract void invoke() throws TransformationException,
			InvalidModelException;

	public void setParameterIN(M_IN in) {

		model_in = in;
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

	public Class<M_IN> getInType() {

		// TODO Auto-generated method stub
		return null;
	}

	public Class<SETTINGS> getSettingsType() {

		// TODO Auto-generated method stub
		return null;
	}

	public Class<OUT> getOutType() {

		// TODO Auto-generated method stub
		return null;
	}

}
