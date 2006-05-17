package tudresden.ocl20.eclipse.plugins.visual.util;

import java.util.Set;

import javax.jmi.reflect.RefPackage;

/**
 * Class für using multiple start objects
 * @author Kai-Uwe Gärtner
 *
 */
public class TopNode {
	private RefPackage model;
	private Class elementType;
	
	/**
	 * Constructor.
	 * @param model the model 
	 * @param elementType the type of start elements
	 */
	public TopNode(RefPackage model,Class elementType){
		this.model=model;
		this.elementType=elementType;
	}
	
	/**
	 * Returns all elements with elementType of the specified model
	 * @return elements
	 */
	public Set getElements(){
		return ModelAnalyser.getInstancesOfType(model, elementType);
	}
}
