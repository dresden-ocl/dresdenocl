package tudresden.ocl20.pivot.tools.transformation.test.util;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.ParallelTransformation;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

public class TestParallelTrans extends
		ParallelTransformation<EObject, IOcl2CodeSettings, String, EObject>
		implements
		ITransformation<EObject, IOcl2CodeSettings, Tuple<String, EObject>> {

	public TestParallelTrans(String modelInName, String outName) {

		super(modelInName, outName, TestTrans.class.getSimpleName(),
				TestFalseTrans.class.getSimpleName(), EObject.class, String.class,
				EObject.class, IOcl2CodeSettings.class);
	}

}
