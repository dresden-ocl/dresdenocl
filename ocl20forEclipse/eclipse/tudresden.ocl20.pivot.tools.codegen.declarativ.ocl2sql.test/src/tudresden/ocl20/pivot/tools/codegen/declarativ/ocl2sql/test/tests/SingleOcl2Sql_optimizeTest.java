package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.test.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.Ocl2DeclCodeFactory;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.Ocl2SQLFactory;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.test.Ocl2SqlTestPlugin;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class SingleOcl2Sql_optimizeTest {

	private String sourcePath = System.getProperty("java.io.tmpdir")
			+ "/ocl2sqltest";

	private static List<String> expected;

	private IModel model;
	private static String filePath = Platform
			.getBundle(Ocl2SqlTestPlugin.PLUGIN_ID).getLocation()
			.replace("reference:file:", "");

	public List<Constraint> constraints = null;

	@BeforeClass
	public static void setUpClass() {

		expected = parseFile(filePath + "solution/view.sql");
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
			model =
					ModelBusPlugin.getMetamodelRegistry()
							.getMetamodel(UML2MetamodelPlugin.ID).getModelProvider()
							.getModel(new File(filePath + "model/university_complex.uml"));
		} catch (IllegalArgumentException e) {
			fail("Wrong parameter");
		} catch (ModelAccessException e) {
			fail("The model can't generate");
		}

		boolean exists = (new File(sourcePath)).exists();
		if (!exists) {
			new File(sourcePath).mkdir();
		}
		else {
			fail("Path " + sourcePath + " already exits");
		}
	}

	@After
	public void tear_down() {

		if (model != null) {
			ModelBusPlugin.getModelRegistry().removeModel(model);
		}

		boolean exists = (new File(sourcePath)).exists();
		if (exists) {
			deleteDir(new File(sourcePath));
		}
		else {
			fail("Path " + sourcePath + " already exits");
		}
	}

	private void testString(String actual, String expected) {

		assertTrue("actual:" + actual + "\nexptected:" + expected,
				expected.contains(actual));
	}

	private List<String> runCodeGenerator(IOcl2DeclSettings settings, int index)
			throws Ocl2CodeException {

		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		settings.setSourceDirectory(sourcePath);
		try {
			model.removeAllConstraints();
		} catch (IllegalArgumentException e2) {
			fail("Can't model reset.");
		} catch (ModelAccessException e2) {
			fail("Can't model reset.");
		}
		try {
			settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup("Standard(SQL)"));
		} catch (TemplateException e1) {
			fail("Can't load Standard SQL template.");
		}
		try {
			constraints = new LinkedList<Constraint>();
			constraints.add(Ocl22Parser.INSTANCE.doParse(model,
					URI.createFileURI(filePath + "constraints/university_complex.ocl"),
					true).get(index));
		} catch (ParseException e) {
			fail("Can't parse the constraints");
		}
		IOcl2Sql ocl2Sql =
				Ocl2SQLFactory.getInstance().createSQLCodeGenerator(settings);
		ocl2Sql.setInputModel(model);
		return ocl2Sql.transformFragmentCode(constraints);
	}

	/**
	 * Test if no schema created and check the created views.
	 */
	@Test
	public void runConstraint1() {

		this.runConstraint(0);
	}

	@Test
	public void runConstraint2() {

		this.runConstraint(1);
	}

	@Test
	public void runConstraint3() {

		this.runConstraint(2);
	}

	@Test
	public void runConstraint4() {

		this.runConstraint(3);
	}

	@Test
	public void runConstraint5() {

		this.runConstraint(4);
	}

	@Test
	public void runConstraint6() {

		this.runConstraint(5);
	}

	@Test
	public void runConstraint7() {

		this.runConstraint(6);
	}

	@Test
	public void runConstraint8() {

		this.runConstraint(7);
	}

	@Test
	public void runConstraint9_1() {

		this.runConstraint(8);
	}

	@Test
	public void runConstraint9_2() {

		this.runConstraint(9);
	}

	@Test
	public void runConstraint10_1() {

		this.runConstraint(10);
	}

	@Test
	public void runConstraint10_2() {

		this.runConstraint(11);
	}

	@Test
	public void runConstraint10_3() {

		this.runConstraint(12);
	}

	@Test
	public void runConstraint10_4() {

		this.runConstraint(13);
	}

	@Test
	public void runConstraint10_5() {

		this.runConstraint(14);
	}

	@Test
	public void runConstraint10_6() {

		this.runConstraint(15);
	}

	@Test
	public void runConstraint10_7() {

		this.runConstraint(16);
	}

	@Test
	public void runConstraint10_8() {

		this.runConstraint(17);
	}

	@Test
	public void runConstraint10_9() {

		this.runConstraint(18);
	}

	@Test
	public void runConstraint11_1() {

		this.runConstraint(19);
	}

	@Test
	public void runConstraint11_2() {

		this.runConstraint(20);
	}

	@Test
	public void runConstraint11_3() {

		this.runConstraint(21);
	}

	@Test
	public void runConstraint11_4() {

		this.runConstraint(22);
	}

	@Test
	public void runConstraint12_1() {

		this.runConstraint(23);
	}

	@Test
	public void runConstraint12_2() {

		this.runConstraint(24);
	}

	@Test
	public void runConstraint12_3() {

		this.runConstraint(25);
	}

	@Test
	public void runConstraint13_1() {

		this.runConstraint(26);
	}

	@Test
	public void runConstraint13_2() {

		this.runConstraint(27);
	}

	@Test
	public void runConstraint13_3() {

		this.runConstraint(28);
	}

	@Test
	public void runConstraint13_4() {

		this.runConstraint(29);
	}

	@Test
	public void runConstraint13_5() {

		this.runConstraint(30);
	}

	@Test
	public void runConstraint14_1() {

		this.runConstraint(31);
	}

	@Test
	public void runConstraint14_2() {

		this.runConstraint(32);
	}

	@Test
	public void runConstraint14_3() {

		this.runConstraint(33);
	}

	@Test
	public void runConstraint14_4() {

		this.runConstraint(34);
	}

	@Test
	public void runConstraint14_5() {

		this.runConstraint(35);
	}

	@Test
	public void runConstraint14_6() {

		this.runConstraint(36);
	}

	@Test
	public void runConstraint14_7() {

		this.runConstraint(37);
	}

	@Test
	public void runConstraint15_1() {

		this.runConstraint(38);
	}

	@Test
	public void runConstraint15_2() {

		this.runConstraint(39);
	}

	@Test
	public void runConstraint15_3() {

		this.runConstraint(40);
	}

	@Test
	public void runConstraint15_4() {

		this.runConstraint(41);
	}

	@Test
	public void runConstraint15_5() {

		this.runConstraint(42);
	}

	@Test
	public void runConstraint15_6() {

		this.runConstraint(43);
	}

	@Test
	public void runConstraint15_7() {

		this.runConstraint(44);
	}

	@Test
	public void runConstraint15_8() {

		this.runConstraint(45);
	}

	@Test
	public void runConstraint15_9() {

		this.runConstraint(46);
	}

	@Test
	public void runConstraint15_10() {

		this.runConstraint(47);
	}

	@Test
	public void runConstraint15_11() {

		this.runConstraint(48);
	}

	@Test
	public void runConstraint15_12() {

		this.runConstraint(49);
	}

	private void runConstraint(int index) {

		IOcl2DeclSettings settings =
				Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
		settings.setSaveCode(false);
		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		List<String> result = null;
		try {
			result = runCodeGenerator(settings, index);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		assertNotNull("No result", result);
		testString(removeComment(result.get(0)), expected.get(index));
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
				}
				else if (zeile.equals("")) {
					retValue.add(temp);
					temp = null;
				}
				else {
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

}
