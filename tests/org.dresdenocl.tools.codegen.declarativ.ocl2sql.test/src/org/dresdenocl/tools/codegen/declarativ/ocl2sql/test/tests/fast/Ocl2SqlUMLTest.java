package org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.fast;

import java.io.IOException;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;

public class Ocl2SqlUMLTest extends Ocl2SqlTest {

	@Override
	protected IModel getModel() throws ModelAccessException, IOException {
		return getModel("model/university_complex.uml", UML2MetamodelPlugin.ID);
	}
}
