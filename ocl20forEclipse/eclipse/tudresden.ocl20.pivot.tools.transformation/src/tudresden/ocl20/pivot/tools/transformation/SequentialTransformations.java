package tudresden.ocl20.pivot.tools.transformation;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.ModelAccessException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
/**
 * SequentialTransformations is a abstract implementation of a sequential transformation composition.
 * @author Christian Wende
 * @author Bjoern Freitag
 *
 * @param <IN>
 * @param <OUT>
 */
public abstract class SequentialTransformations<IN extends EObject,BET,OUT> extends M2XTransformation<IN, OUT> {

	private ITransformation<IN,BET> trans1;
	private ITransformation<BET,OUT> trans2;
		
	@SuppressWarnings("unchecked")
	public SequentialTransformations(String modelInName, String outName, String tid1, String tid2) throws ModelAccessException  {
		super(modelInName, outName);
		
		trans1 = (ITransformation<IN, BET>) TransformationPlugin.getTransformationRegistry().getTransformation(tid1, modelInName, "intermediateResult");
		trans2 = (ITransformation<BET, OUT>) TransformationPlugin.getTransformationRegistry().getTransformation(tid2, "intermediateResult", outName);
			
	}

	public SequentialTransformations() {
		super();
	}

	public void invoke() throws TransformationException, InvalidModelException {
				
		trans1.invoke();
	//	A a = (A) te.getResult(id1);
		trans2.setParameterIN(trans1.getResult());
		trans2.invoke();
		OUT b = trans2.getResult();

		this.out = b;
	}
	
	public void setSettings(IOcl2CodeSettings ocl2CodeSettings) {
		trans1.setSettings(ocl2CodeSettings);
		trans2.setSettings(ocl2CodeSettings);
	}	
	
	public void setParameterIN(IN in) {
		super.setParameterIN(in);
		trans1.setParameterIN(in);
	}
	
}
