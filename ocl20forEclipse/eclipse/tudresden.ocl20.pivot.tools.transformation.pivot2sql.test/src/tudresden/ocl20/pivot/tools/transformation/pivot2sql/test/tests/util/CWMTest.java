package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import orgomg.cwm.resource.relational.Schema;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.exception.TransformationException;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

public abstract class CWMTest extends TransformationTest {

	protected static int MODUS_TYPED = IOcl2DeclSettings.MODUS_TYPED;

	protected static int MODUS_VERTICAL = IOcl2DeclSettings.MODUS_VERTICAL;
	
	protected static int modus;
	
	protected List<String> tables = new ArrayList<String>();
	protected List<String> views = new ArrayList<String>();
	protected Map<String,List<Tuple<String,String>>> table2properties = new HashMap<String,List<Tuple<String,String>>>();
	protected Map<String,String> view2queryexpression = new HashMap<String,String>();
	protected Map<String,List<String>> table2ForeignKey = new HashMap<String,List<String>>();
	protected Map<String,String> table2PrimaryKey = new HashMap<String,String>();

	protected Schema schema;
	
	@Before
	public void setUp(){
		tables.clear();
		views.clear();
		table2properties.clear();
		view2queryexpression.clear();
		table2ForeignKey.clear();
		table2PrimaryKey.clear();
		schema = null;
	}
	
	protected Schema generateCWMModel(File file, int modus) throws ModelAccessException, InvalidModelException, TransformationException {
		IModel model = TestPerformer.addUMLModel(file);
		ITransformation<Namespace,IOcl2DeclSettings,Schema> p2cwm = TransformationFactory.getInstance().getTransformation("Pivot2CwmImpl",Namespace.class, Schema.class, IOcl2DeclSettings.class,"pivot","cwm");		
		p2cwm.setParameterIN(model.getRootNamespace());
		IOcl2DeclSettings oclSettings = TestPerformer.getSettings();
		oclSettings.setModus(modus);
		p2cwm.setSettings(oclSettings);
		p2cwm.invoke();
		TestPerformer.removeUMLModel(model);
		return p2cwm.getResult();
	}
	
	protected void checkCWM() {
		ModelChecker.checkCWM(schema,tables,views,table2properties,table2PrimaryKey,table2ForeignKey,view2queryexpression);
	}
	
	private void exceptionCWMModel(File file) {
		try {
			schema = generateCWMModel(file,modus);
		} catch (Exception e) {
			e.printStackTrace();
			fail("The cwm model can't generate");
		}
	}
	
	/** 
	 * <p>Checks if a class mapped correctly.</p>
	 */
	public void testClass() {
		exceptionCWMModel(TEST_CLASS);
	}

	/**
	 * <p>Checks if a property mapped correctly.</p>
	 */
	public void testProperty() {
		exceptionCWMModel(TEST_PROPERTY);
	}

	/**
	 * <p>Checks if a inheritance mapped correctly.</p>
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
	 * <p>Checks if a 1toN relation mapped correctly.</p>
	 */
	public void testRelation1toN() {
		exceptionCWMModel(TEST_RELATION_1TON);
	}

	/**
	 * <p>Checks if a Nto1 relation mapped correctly.</p>
	 */
	public void testRelationNto1() {
		exceptionCWMModel(TEST_RELATION_NTO1);
	}

	/**
	 * <p>Checks if a MtoN relation mapped correctly.</p>
	 */
	public void testRelationMtoN() {
		exceptionCWMModel(TEST_RELATION_MTON);
	}

	/**
	 * <p>Checks if a complex university example mapped correctly.</p>
	 */
	public void testComplexUniversity() {
		exceptionCWMModel(TEST_COMPLEX);
	}
		
}
