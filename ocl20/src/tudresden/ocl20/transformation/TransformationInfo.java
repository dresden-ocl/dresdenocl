/*
 * Created on 31.01.2006
 *
 */
package tudresden.ocl20.transformation;

import tudresden.ocl20.transformation.interfaces.ITransformation;

/**
 * This class encapsulates the information for a transformation. 
 * This is needed to manage different transformations
 * @author Christian Wende
 *
 */
public class TransformationInfo {
	
	private Class<? extends ITransformation> transformation;
	private String in_model;
	private String out_model;
	
	/**
	 * The constructor for a TransformationInfo
	 * @param in_model The type of the in model.
	 * @param out_model The type of the out model.
	 * @param transformation The Class that implements the transformation.
	 */
	public TransformationInfo(String in_model, String out_model, Class<? extends ITransformation> transformation) {
		this.in_model = in_model;
		this.out_model = out_model;
		this.transformation = transformation;
	}

	/**
	 * Returns the type of the in model.
	 * @return Returns the type of the in model.
	 */
	public String getIn_model() {
		return in_model;
	}


	/**
	 * Returns the type of the out model.
	 * @return Returns the type of the out model.
	 */
	public String getOut_model() {
		return out_model;
	}


	/**
	 * Returns the class that implements the transformation.
	 * @return Returns the class that implements the transformation.
	 */
	public Class<? extends ITransformation> getTransformation() {
		return transformation;
	}

	/**
	 * Returns the string representation of the transformation info.
	 */
	public String toString() {
		return getTransformationID() + " : " + in_model + " -> " + out_model; 
	}

	/**
	 * @return Returns the transformationID.
	 */
	public String getTransformationID() {
		return transformation.getSimpleName();
	}

	}
