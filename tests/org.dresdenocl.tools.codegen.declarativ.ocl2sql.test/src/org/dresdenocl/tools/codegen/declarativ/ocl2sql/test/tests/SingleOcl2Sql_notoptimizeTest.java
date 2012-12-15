package org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.Ocl2SQLFactory;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.Ocl2SqlTestPlugin;
import org.dresdenocl.tools.codegen.exception.Ocl2CodeException;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;

public class SingleOcl2Sql_notoptimizeTest extends SingleOcl2SqlTest {

	@BeforeClass
	public static void setUpClass() throws IOException {

		expected = parseFile(AbstractDresdenOclTest.getFile(
				"solution/view_nop.sql", Ocl2SqlTestPlugin.PLUGIN_ID)
				.getAbsolutePath());

		String stream = AbstractDresdenOclTest
				.getFile("/template/standard.stg", Ocl2SqlTestPlugin.PLUGIN_ID)
				.getAbsolutePath();
		ITemplateGroup standardGroup = null;
		try {
			standardGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup(
							"StandardTest(SQL)",
							TemplatePlugin.getTemplateGroupRegistry()
									.getTemplateGroup("Standard(SQL)"));
			standardGroup.addFile(stream);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void tear_downClass() {

		TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(
				"StandardTest(SQL)");
	}

	@Before
	public void setUp() {

		try {
			model = ModelBusPlugin
					.getMetamodelRegistry()
					.getMetamodel(UML2MetamodelPlugin.ID)
					.getModelProvider()
					.getModel(
							AbstractDresdenOclTest.getFile(
									"model/university_complex.uml",
									Ocl2SqlTestPlugin.PLUGIN_ID));
		} catch (IllegalArgumentException e) {
			fail("Wrong parameter");
		} catch (ModelAccessException e) {
			fail("The model can't generate");
		} catch (IOException e) {
			fail("Canniot find the model file: " + e.getMessage());
		}

		boolean exists = (new File(sourcePath)).exists();
		if (!exists) {
			new File(sourcePath).mkdir();
		}
		// else {
		// fail("Path " + sourcePath + " already exits");
		// }
	}

	@After
	public void tear_down() {

		if (model != null) {
			ModelBusPlugin.getModelRegistry().removeModel(model);
		}

		boolean exists = (new File(sourcePath)).exists();
		if (exists) {
			deleteDir(new File(sourcePath));
		} else {
			fail("Path " + sourcePath + " already exits");
		}
	}

	protected List<String> runCodeGenerator(IOcl2DeclSettings settings,
			int index) throws Ocl2CodeException {

		try {
			settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup("StandardTest(SQL)"));
		} catch (TemplateException e1) {
			fail("Can't load Standard SQL template.");
		}
		IOcl2Sql ocl2Sql = Ocl2SQLFactory.getInstance().createSQLCodeGenerator(
				settings);
		ocl2Sql.setInputModel(model);
		return ocl2Sql.transformFragmentCode(constraints);
	}

}
