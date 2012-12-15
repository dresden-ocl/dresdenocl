package org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.fast;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.language.ocl.resource.ocl.Ocl22Parser;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.Ocl2DeclCodeFactory;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.Ocl2SQLFactory;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.Ocl2SqlTestPlugin;
import org.dresdenocl.tools.codegen.exception.Ocl2CodeException;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;

public class CarOcl2SqlTest {

	private String sourcePath = System.getProperty("java.io.tmpdir")
			+ "/ocl2sqltest";

	private IModel model;
	public List<Constraint> constraints = null;
	
	
	private static ITemplateGroup standardGroup = null;
	
	@BeforeClass
	public static void setUpClass() throws IOException {

		String stream = AbstractDresdenOclTest
				.getFile("/template/standard.stg", Ocl2SqlTestPlugin.PLUGIN_ID).getAbsolutePath();
		try {
			standardGroup = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup(
							"StandardTest(SQL)",
							TemplatePlugin.getTemplateGroupRegistry()
									.getTemplateGroup("MySQL(SQL)"));
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

	private boolean deleteDir(File dir) {

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
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
									"model/car.uml",
									Ocl2SqlTestPlugin.PLUGIN_ID));
		} catch (IllegalArgumentException e) {
			fail("Wrong parameter");
		} catch (ModelAccessException e) {
			fail("The model can't generate");
		} catch (IOException e) {
			fail(e.getMessage());
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

	private void testStringList(List<String> actual, List<String> expected) {

		/* Required for OS-independent regression tests. */
		List<String> expectedCorrected = new ArrayList<String>(expected.size());
		List<String> actualCorrected = new ArrayList<String>(expected.size());

		for (String anExpected : expected) {
			expectedCorrected.add(anExpected.replaceAll("\r\n", "\n")
					.replaceAll("\r", "\n"));
		}

		for (String anActual : actual) {
			actualCorrected.add(anActual.replaceAll("\r\n", "\n").replaceAll(
					"\r", "\n"));
		}

		for (String s : actualCorrected) {
			if (s.equals(""))
				continue;
			assertTrue("An extra view is generated\n" + s,
					expectedCorrected.contains(s));
		}
		for (String s : expectedCorrected) {
			if (s.equals(""))
				continue;
			assertTrue("A view isn't generate\n" + s,
					actualCorrected.contains(s));
		}
	}

	private List<String> runCodeGenerator(IOcl2DeclSettings settings)
			throws Ocl2CodeException {

		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		settings.setSourceDirectory(sourcePath);
		settings.setSaveCode(false);
		try {
			model.removeAllConstraints();
		} catch (IllegalArgumentException e2) {
			fail("Can't model reset.");
		} catch (ModelAccessException e2) {
			fail("Can't model reset.");
		}
		try {
			constraints = Ocl22Parser.INSTANCE.doParse(
					model,
					URI.createFileURI(AbstractDresdenOclTest.getFile(
							"constraints/car.ocl",
							Ocl2SqlTestPlugin.PLUGIN_ID).getAbsolutePath()));
		} catch (ParseException e) {
			fail("Can't parse the constraints");
		} catch (IOException e) {
			fail(e.getMessage());
		}
		IOcl2Sql ocl2Sql = Ocl2SQLFactory.getInstance().createSQLCodeGenerator(
				settings);
		ocl2Sql.setInputModel(model);
		return ocl2Sql.transformFragmentCode(constraints);
	}

	/**
	 * Test if no schema created and check the created views.
	 * 
	 * @throws IOException
	 */
	@Test
	public void runTestCar() throws IOException {

		IOcl2DeclSettings settings = Ocl2DeclCodeFactory.getInstance()
				.createOcl2DeclCodeSettings();
		try {
			settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup("StandardTest(SQL)"));
		} catch (TemplateException e1) {
			fail("Can't load StandardTest template.");
		}
		List<String> result = null;
		try {
			result = runCodeGenerator(settings);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		assertNotNull("No result", result);
		testStringList(removeComments(result), parseFile(AbstractDresdenOclTest.getFile("solution/car-nop.sql",
				Ocl2SqlTestPlugin.PLUGIN_ID).getAbsolutePath()));
	}
	
	@Test
	public void runTestCarOptimize() throws IOException {

		IOcl2DeclSettings settings = Ocl2DeclCodeFactory.getInstance()
				.createOcl2DeclCodeSettings();
		try {
			settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup("MySQL(SQL)"));
		} catch (TemplateException e1) {
			fail("Can't load MySQL template.");
		}
		List<String> result = null;
		try {
			result = runCodeGenerator(settings);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		assertNotNull("No result", result);
		testStringList(removeComments(result), parseFile(AbstractDresdenOclTest.getFile("solution/car.sql",
				Ocl2SqlTestPlugin.PLUGIN_ID).getAbsolutePath()));
	}

	private static List<String> parseFile(String file) {

		List<String> retValue = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String zeile = null;
			String temp = null;
			while ((zeile = in.readLine()) != null) {
				if (zeile.startsWith("--"))
					continue;
				if (temp == null) {
					temp = zeile;
				} else if (zeile.equals("")) {
					retValue.add(temp);
					temp = null;
				} else {
					temp += "\n" + zeile;
				}

			}
			retValue.add(temp);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retValue;
	}

	private String removeComment(String view) {

		String result = "";
		for (String s : Arrays.asList(view.split("\n"))) {
			if (s.startsWith("--"))
				continue;
			result += s + "\n";
		}
		return result.trim();
	}

	private List<String> removeComments(List<String> views) {

		List<String> result = new ArrayList<String>();
		for (String s : views) {
			result.add(removeComment(s));
		}
		return result;
	}
}
