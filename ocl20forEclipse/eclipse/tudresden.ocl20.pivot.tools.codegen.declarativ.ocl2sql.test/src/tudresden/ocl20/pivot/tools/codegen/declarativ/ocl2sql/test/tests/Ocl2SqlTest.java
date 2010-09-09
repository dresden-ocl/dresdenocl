package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

public class Ocl2SqlTest {

	private String sourcePath = System.getProperty("java.io.tmpdir")
			+ "ocl2sqltest";

	private static List<String> expected;
	
	private IModel model;
	private static String filePath =
		Platform.getBundle(Ocl2SqlTestPlugin.PLUGIN_ID).getLocation()
				.replace("reference:file:", "");

	@BeforeClass
	public static void setUpClass() {

		expected = parseFile(filePath +"solution/view.sql");
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
			model =	ModelBusPlugin.getMetamodelRegistry().getMetamodel(UML2MetamodelPlugin.ID).getModelProvider().getModel(new File(filePath
						+ "model/university_complex.uml"));
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

	private void testStringList(List<String> actual, List<String> expected) {
		for(String s : actual) {
			if (s.equals("")) continue;
			assertTrue("A extra view is generated\n"+s,expected.contains(s));
		}
		for(String s : expected) {
			if (s.equals("")) continue;
			assertTrue("A view isn't generate\n"+s,actual.contains(s));
		}
	}
	
	/**
	 * Test if no schema created and check the created views.
	 */
	@Test
	public void runTestNotSave() {

		List<Constraint> constraints = null;
		try {
			constraints = Ocl22Parser.INSTANCE.doParse(model, URI.createFileURI(filePath
							+ "constraints/university_complex.ocl"), true);
		} catch (ParseException e) {
			fail("Can't parse the constraints");
		}
		IOcl2DeclSettings settings =
				Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
		settings.setSaveCode(false);
		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		settings.setSourceDirectory(sourcePath);
		settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
				.getTemplateGroup("Standard(SQL)"));
		List<String> result = null;
		IOcl2Sql ocl2Sql = Ocl2SQLFactory.getInstance().createSQLCodeGenerator(settings);
		ocl2Sql.setInputModel(model);
		try {
			result = ocl2Sql.transformFragmentCode(constraints);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		testStringList(result,expected);
		String[] files = (new File(sourcePath)).list();
		assertEquals(1, files.length);
		testStringList(parseFile(sourcePath+"\\"+files[0]),parseFile(filePath+"solution/view.sql"));
	}
	
	/**
	 * Test if check the created views and schema.
	 */
	@Test
	public void runTestWithSave() {

		String filePath =
				Platform.getBundle(Ocl2SqlTestPlugin.PLUGIN_ID).getLocation()
						.replace("reference:file:", "");
		List<Constraint> constraints = null;
		try {
			constraints = Ocl22Parser.INSTANCE.doParse(model, URI.createFileURI(filePath
					+ "constraints/university_complex.ocl"), true);
		} catch (ParseException e) {
			fail("Can't parse the constraints");
		}
		IOcl2DeclSettings settings =
			Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		settings.setSaveCode(true);
		settings.setSourceDirectory(sourcePath);
		settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
				.getTemplateGroup("Standard(SQL)"));
		List<String> result = null;
		IOcl2Sql ocl2Sql = Ocl2SQLFactory.getInstance().createSQLCodeGenerator(settings);
		ocl2Sql.setInputModel(model);
		try {
			result = ocl2Sql.transformFragmentCode(constraints);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		testStringList(result,expected);
		String[] files = (new File(sourcePath)).list();
		assertEquals(2, files.length);
		testStringList(parseFile(sourcePath+"\\"+files[0]),parseFile(filePath+"solution/schema.sql"));
		testStringList(parseFile(sourcePath+"\\"+files[1]),parseFile(filePath+"solution/view.sql"));
	}
	
	@Test
	public void runTestWithSaveAndOtherParameter() {

		String filePath =
				Platform.getBundle(Ocl2SqlTestPlugin.PLUGIN_ID).getLocation()
						.replace("reference:file:", "");
		List<Constraint> constraints = null;
		try {
			constraints = Ocl22Parser.INSTANCE.doParse(model, URI.createFileURI(filePath
					+ "constraints/university_complex.ocl"), true);
		} catch (ParseException e) {
			fail("Can't parse the constraints");
		}
		IOcl2DeclSettings settings =
			Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
		settings.setSaveCode(true);
		settings.setSourceDirectory(sourcePath);
		settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
				.getTemplateGroup("Standard(SQL)"));
		settings.setTablePrefix("TB_");
		settings.setObjectViewPrefix("O_");
		settings.setAssociationTablePrefix("AS_");
		settings.setPrimaryKeyPrefix("P_");
		settings.setForeignKeyPrefix("F_");
		IOcl2Sql ocl2Sql = Ocl2SQLFactory.getInstance().createSQLCodeGenerator(settings);
		ocl2Sql.setInputModel(model);
		try {
			ocl2Sql.transformFragmentCode(constraints);
		} catch (Ocl2CodeException e) {
			fail("Can't generate sql code");
		}
		String[] files = (new File(sourcePath)).list();
		assertEquals(2, files.length);
		testStringList(parseFile(sourcePath+"\\"+files[0]),parseFile(filePath+"solution/schema_para.sql"));
		testStringList(parseFile(sourcePath+"\\"+files[1]),parseFile(filePath+"solution/view_para.sql"));
	}
	
	private static List<String> parseFile(String file) {
		List<String> retValue = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String zeile = null;
			String temp = null;
			while ((zeile = in.readLine()) != null) {
				if (zeile.startsWith("--")) continue;
				if (temp == null) {
					temp = zeile;
				} else {
					temp += "\n"+zeile;
				}
				if (zeile.equals("")) {
					retValue.add(temp);
					temp = null;
				}
			}
			retValue.add(temp+"\n");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retValue;
	}
}
