package tudresden.ocl20.pivot.tools.transformation.test.util;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.M2MTransformation;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;

public class TestFalseTrans extends M2MTransformation<EObject, IOcl2CodeSettings, EObject>
		implements ITransformation<EObject, IOcl2CodeSettings, EObject> {

	public TestFalseTrans(String modelInName, String modelOutName) {
		super(modelInName, modelOutName);
	}

	public void invoke() throws InvalidModelException, TransformationException {
		// Do nothing
	}

}
