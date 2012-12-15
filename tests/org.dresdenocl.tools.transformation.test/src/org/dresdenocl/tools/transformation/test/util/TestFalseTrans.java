package org.dresdenocl.tools.transformation.test.util;

import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.tools.codegen.IOcl2CodeSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.M2MTransformation;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;

public class TestFalseTrans extends
		M2MTransformation<EObject, IOcl2CodeSettings, EObject> implements
		ITransformation<EObject, IOcl2CodeSettings, EObject> {

	public TestFalseTrans(String modelInName, String modelOutName) {

		super(modelInName, modelOutName);
	}

	public void invoke() throws InvalidModelException, TransformationException {

		// Do nothing
	}

}
