package org.dresdenocl.tools.transformation.test.util;

import org.dresdenocl.tools.codegen.IOcl2CodeSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.ParallelTransformation;
import org.dresdenocl.tools.transformation.impl.Tuple;
import org.eclipse.emf.ecore.EObject;

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
