/*
 * Created on 18.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;

/**
 * The TranformationEngine is the central point of the transformation framework. With the transformation engine
 * all metamodelbased transformations are managed, configured and executed.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 *
 */
public class TransformationEngine {

	/**
	 * Instanciates a transformation for the given transformationId with the given out and in modelnames.
	 * @param transformationID The transformationId for which a new instance should be created for.
	 * @return Returns a unique runtime identifier for the instanciated transformation
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ITransformation<?, ?> loadTransformation(String transformationId, String model_inName, String model_outName) {
		//TODO: correct link to class:
		Class<? extends ITransformation> tClass = TransformationPlugin.getTransformationRegistry().getTransformation(transformationId).getClass();
		Constructor<? extends ITransformation<?,?>> cons;
		try {
			cons = (Constructor<? extends ITransformation<?, ?>>) tClass.getConstructor(new Class[] {String.class, String.class});
			 return cons.newInstance(new Object[] {model_inName, model_outName});
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
