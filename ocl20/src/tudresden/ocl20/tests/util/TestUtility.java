package tudresden.ocl20.tests.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Constraint;
import tudresden.ocl20.core.jmi.ocl.expressions.AttributeCallExp;
import tudresden.ocl20.core.jmi.ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.jmi.ocl.expressions.OclExpression;
import tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.util.Utility;

/**
 * Test Class for <code>tudresden.ocl20.util.Utility</code>.
 * 
 * @author Jordi Cabot Sagrera
 * 
 */
/*
 * Refactored and changed into a jUnit Test Case and documented by Claas Wilke
 * in March 2008.
 */
public class TestUtility {

	protected static Utility myUtility;
	protected static OclModel myModel;

	/**
	 * Loads the OCL model and creates the Utility used in the test cases of
	 * this Class.
	 * 
	 * All test cases use the same Model and Utility, thus this method is just
	 * executed once.
	 */
	@BeforeClass
	public static void setUp() {
		// Create Utility instance.
		TestUtility.myUtility = new Utility();

		// Converting relative path of the UML model file to an absolute URL.
		File modelFile = new File(
				".\\resources\\PoseidonProjects\\CarOwner.xmi");
		String urlModel = "file:" + modelFile.getAbsolutePath();

		// Converting relative path of the OCL model file to an absolute URL.
		File OCLurl = new File(
				".\\resources\\constraints\\AttrEvalTest-CarWorld-2.ocl");
		String urlOCL = OCLurl.getAbsolutePath();

		// Load OCL model.
		TestUtility.myModel = myUtility.loadUMLModel(urlModel, urlOCL);
	}

	/**
	 * Test the method <code>getConstraintsClassifier(OclModel, String)</code>
	 * of the <code>Utility</code>.
	 */
	// Some collection operations in this test does not use generics. This
	// annotation prevents warnings in Eclipse.
	@SuppressWarnings("unchecked")
	@Test
	public void testGetConstraintsClassifier() {
		// Retrieving all constraints of a given class ('Person' in this
		// example)
		Collection cons = myUtility.getConstraintsClassifier(
				TestUtility.myModel, "Person");
		Vector vcons = new Vector(cons);

		// The Class 'Person' should have exactly one constraint.
		assertTrue(vcons.size() == 1);

		// Iterate through the constraints.
		Iterator it = cons.iterator();

		// There should be another constraint.
		assertTrue(it.hasNext());

		// Get the first constraint and check its name.
		Constraint c = (Constraint) it.next();

		// The name is not given in the model, thus it should be null
		assertEquals(c.getNameA(), null);

		// There should be no more constraints.
		assertFalse(it.hasNext());
	}

	/**
	 * Test the method <code>getAllConstraintsModel(OclModel)</code> of the
	 * <code>Utility</code>.
	 */
	// Some collection operations in this test does not use generics. This
	// annotation prevents warnings in Eclipse.
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllConstraintsModel() {
		// Retrieving all constraints defined in the model
		Collection cons2 = myUtility
				.getAllConstraintsModel(TestUtility.myModel);
		Vector vcons2 = new Vector(cons2);

		// The model should have exactly two constraint.
		assertTrue(vcons2.size() == 2);

		// Traverse through the tree of the given constraints.
		Iterator it2 = cons2.iterator();

		// There should be another constraint.
		assertTrue(it2.hasNext());

		// Get the first constraint and check its name.
		Constraint c2 = (Constraint) it2.next();
		// The name is not given in the model, thus it should be null
		assertEquals(c2.getNameA(), null);

		// Get the constrained element of the first constraint.
		// It should be the class 'Person'.
		assertEquals(c2.getConstrainedElementA().getNameA(), "Person");

		// Get the Expression of the constraint.
		ExpressionInOcl exp2 = (ExpressionInOcl) c2.getBodyA();
		OclExpression o = (OclExpression) exp2.getBodyExpression();
		// the object o points to the root of the tree representing the
		// constraint
		// In this example we know how is the constraint so we do not
		// check the type of each "node" when traversing the tree
		OperationCallExp op = (OperationCallExp) o;

		// Check the name of the operation (should be '>=').
		assertEquals(op.getReferredOperation().getNameA(), ">=");

		// Get the AtttributeCallExpression of the operation and check the
		// attributes name (should be 'age').
		AttributeCallExp at = (AttributeCallExp) op.getSource();
		assertEquals(at.getReferredAttribute().getNameA(), "age");

		// Get the arguments of the operation and iterate through them.
		List l = op.getArguments();
		
		// The list should have exactly one element.
		assertTrue(l.size() == 1);

		// The element should be an IntegerLiteralExpression with the value '16'.
		IntegerLiteralExp i = (IntegerLiteralExp) l.iterator().next();
		assertTrue(i.getIntegerSymbol() == 16);

		// There should be another constraint.
		assertTrue(it2.hasNext());

		// Get the second constraint and check its name.
		c2 = (Constraint) it2.next();
		// The name is not given in the model, thus it should be null
		assertEquals(c2.getNameA(), null);

		// There should be no more constraints.
		assertFalse(it2.hasNext());
	}

	/**
	 * Test the method <code>getAllClassesModel(OclModel)</code> of the
	 * <code>Utility</code>.
	 */
	// Some collection operations in this test does not use generics. This
	// annotation prevents warnings in Eclipse.
	@SuppressWarnings("unchecked")
	@Test
	public void testUtility() {

		// Test 4: Retrieving all classes from the model
		Collection classes = myUtility.getAllClassesModel(TestUtility.myModel);
		Vector vcons3 = new Vector(classes);
		
		// The model should have 7 classes.
		assertTrue(vcons3.size() == 7);

		// Iterate through the classes.
		Iterator it3 = classes.iterator();
		Classifier cl;
		
		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Collection').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Collection");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'String').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "String");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Integer').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Integer");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Classifier1').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Classifier1");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Producer').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Producer");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Person').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Person");

		// There should be another class in the vector.
		assertTrue(it3.hasNext());
		
		// Get the next Class and check its name (should be 'Car').
		cl = (Classifier) it3.next();
		assertEquals(cl.getNameA(), "Car");

		// There should be no more classes in the vector.
		assertFalse(it3.hasNext());
	}

}
