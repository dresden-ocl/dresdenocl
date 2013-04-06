package org.dresdenocl.tools.transformation.test.util;

import org.dresdenocl.tools.codegen.IOcl2CodeSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.Transformation;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.eclipse.emf.ecore.EObject;

public class TestFalseTrans extends
		Transformation<EObject, IOcl2CodeSettings, EObject> implements
		ITransformation<EObject, IOcl2CodeSettings, EObject> {

	public TestFalseTrans(String modelInName, String modelOutName) {

		super(modelInName, modelOutName);
	}

	public void invoke() throws InvalidModelException, TransformationException {

		// Do nothing
	}

}
