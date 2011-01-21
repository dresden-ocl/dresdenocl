package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.test.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.Ocl2SQLFactory;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class SingleOcl2Sql_notoptimizeTest extends SingleOcl2SqlTest {

	@BeforeClass
	public static void setUpClass() {

		expected = parseFile(filePath + "solution/view_nop.sql");

		URL stream = SingleOcl2Sql_notoptimizeTest.class
				.getResource("/template/standard.stg");
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
							new File(filePath + "model/university_complex.uml"));
		} catch (IllegalArgumentException e) {
			fail("Wrong parameter");
		} catch (ModelAccessException e) {
			fail("The model can't generate");
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

	protected List<String> runCodeGenerator(IOcl2DeclSettings settings, int index)
			throws Ocl2CodeException {

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
