package tudresden.ocl20.pivot.tools.transformation.test.util;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.M2XTransformation;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;

public class TestTrans extends
		M2XTransformation<EObject, IOcl2CodeSettings, String> implements
		ITransformation<EObject, IOcl2CodeSettings, String> {

	public TestTrans(String modelIn, String modelOut) {

		super(modelIn, modelOut);
	}

	public void invoke() throws TransformationException, InvalidModelException {

		// Do nothing.

	}

}
