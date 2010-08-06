/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * ParallelTransformation is an abstract implementation of
 * a parallel transformation composition.
 * 
 * To implement a composition you must a class which implements 
 * ParallelTransformation and define the involved tranformations using for example
 * super(modelInName, outName, Pivot2CwmImpl.class.getSimpleName(), Pivot2MappedModelImpl.class.getSimpleName());
 * in the constructor.
* @author Christian Wende
 *
 */
public abstract class ParallelTransformations<IN extends EObject, A ,B> extends M2XTransformation<IN, Tuple<A,B>> {

	
	private ITransformation<IN,A> trans1;
	private ITransformation<IN,B> trans2;

	
	/**
	 * The constructor for ParallelTransformations.
	 * @param modelInName The name of the in model.
	 * @param outName The name ouf the out entity.
	 * @param tid1 The id of the first transformation.
	 * @param tid2 The id of the second transformation.
	 * @throws ModelAccessException
	 */
	@SuppressWarnings("unchecked")
	protected ParallelTransformations(String modelInName, String outName, String tid1, String tid2) throws ModelAccessException {
		super(modelInName, outName);
		
		trans1 = (ITransformation<IN, A>) TransformationPlugin.getTransformationRegistry().getTransformation(tid1, modelInName, outName);
		trans2 = (ITransformation<IN, B>) TransformationPlugin.getTransformationRegistry().getTransformation(tid2, modelInName, outName);
			
		
	}

	public ParallelTransformations() {
		super();
	}

	public void invoke() throws TransformationException, InvalidModelException {
		trans1.invoke();
		A a = trans1.getResult();
		
		trans2.invoke();
		B b = trans2.getResult();

		this.out = new Tuple<A,B>(a,b);
	
	
	}

	public void setSettings(IOcl2CodeSettings ocl2CodeSettings) {
		trans1.setSettings(ocl2CodeSettings);
		trans2.setSettings(ocl2CodeSettings);
	}	
	
	public void setParameterIN(IN in) {
		super.setParameterIN(in);
		trans1.setParameterIN(in);
		trans2.setParameterIN(in);

	}	
	

}
