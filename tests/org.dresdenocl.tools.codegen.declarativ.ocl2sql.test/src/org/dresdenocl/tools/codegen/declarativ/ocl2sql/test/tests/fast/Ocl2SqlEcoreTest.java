package org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.fast;

import java.io.IOException;

import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;

public class Ocl2SqlEcoreTest extends Ocl2SqlTest {

	@Override
	protected IModel getModel() throws ModelAccessException, IOException {
		return getModel("model/university_complex.ecore", EcoreMetamodelPlugin.ID);
	}
}
