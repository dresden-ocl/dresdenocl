package org.dresdenocl.tools.transformation.pivot2sql.test.tests.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.TransformationFactory;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.exception.TransformationException;
import org.dresdenocl.tools.transformation.impl.Tuple;
import org.junit.Before;
import orgomg.cwm.resource.relational.Catalog;

public abstract class CWMTest extends TransformationTest {

	protected static final int MODUS_TYPED = IOcl2DeclSettings.MODUS_TYPED;

	protected static final int MODUS_VERTICAL = IOcl2DeclSettings.MODUS_VERTICAL;

	protected static int modus;
	
	protected static boolean schema = false;

	protected List<String> tables = new ArrayList<String>();
	protected List<String> views = new ArrayList<String>();
	protected Map<String, List<Tuple<String, String>>> table2properties =
			new HashMap<String, List<Tuple<String, String>>>();
	protected Map<String, String> view2queryexpression =
			new HashMap<String, String>();
	protected Map<String, List<String>> table2ForeignKey =
			new HashMap<String, List<String>>();
	protected Map<String, String> table2PrimaryKey =
			new HashMap<String, String>();

	protected Catalog catalog;

	@Before
	public void setUp() {

		tables.clear();
		views.clear();
		table2properties.clear();
		view2queryexpression.clear();
		table2ForeignKey.clear();
		table2PrimaryKey.clear();
		catalog = null;
		schema = false;
	}

	protected Catalog generateCWMModel(File file, int modus)
			throws ModelAccessException, InvalidModelException,
			TransformationException {

		IModel model = TestPerformer.addUMLModel(file);
		ITransformation<Namespace, IOcl2DeclSettings, Catalog> p2cwm =
				TransformationFactory.getInstance().getTransformation("Pivot2CwmImpl",
						Namespace.class, Catalog.class, IOcl2DeclSettings.class, "pivot",
						"cwm");
		p2cwm.setParameterIN(model.getRootNamespace());
		IOcl2DeclSettings oclSettings = TestPerformer.getSettings();
		oclSettings.setModus(modus);
		oclSettings.setSchemaUsing(schema);
		p2cwm.setSettings(oclSettings);
		p2cwm.invoke();
		TestPerformer.removeUMLModel(model);
		return p2cwm.getResult();
	}

	protected void checkCWM() {

		ModelChecker.checkCWM(catalog, tables, views, table2properties,
				table2PrimaryKey, table2ForeignKey, view2queryexpression);
	}

	private void exceptionCWMModel(File file) {

		try {
			catalog = generateCWMModel(file, modus);
		} catch (Exception e) {
			e.printStackTrace();
			fail("The cwm model can't generate");
		}
	}

	/**
	 * <p>
	 * Checks if a class mapped correctly.
	 * </p>
	 */
	public void testClass() {

		exceptionCWMModel(TEST_CLASS);
	}

	/**
	 * <p>
	 * Checks if a property mapped correctly.
	 * </p>
	 */
	public void testProperty() {

		exceptionCWMModel(TEST_PROPERTY);
	}

	/**
	 * <p>
	 * Checks if a inheritance mapped correctly.
	 * </p>
	 */
	public void testInheritance() {

		exceptionCWMModel(TEST_INHERITANCE);
	}

	/*
	 * <p>Checks if a 1to1 relation mapped correctly.</p>
	 */
	public void testRelation1to1() {

		exceptionCWMModel(TEST_RELATION_1TO1);
	}

	/**
	 * <p>
	 * Checks if a 1toN relation mapped correctly.
	 * </p>
	 */
	public void testRelation1toN() {

		exceptionCWMModel(TEST_RELATION_1TON);
	}

	/**
	 * <p>
	 * Checks if a Nto1 relation mapped correctly.
	 * </p>
	 */
	public void testRelationNto1() {

		exceptionCWMModel(TEST_RELATION_NTO1);
	}

	/**
	 * <p>
	 * Checks if a MtoN relation mapped correctly.
	 * </p>
	 */
	public void testRelationMtoN() {

		exceptionCWMModel(TEST_RELATION_MTON);
	}

	
	/**
	 * <p>
	 * Checks if schema generation correctly.
	 * </p>
	 */
	public void testMultipleSchema() {
		exceptionCWMModel(TEST_SCHEMA);
	}
	
	/**
	 * <p>
	 * Checks if a complex university example mapped correctly.
	 * </p>
	 */
	public void testComplexUniversity() {

		exceptionCWMModel(TEST_COMPLEX);
	}

}
