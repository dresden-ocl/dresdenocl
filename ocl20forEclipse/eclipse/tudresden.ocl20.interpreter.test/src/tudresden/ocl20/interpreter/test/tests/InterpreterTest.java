package tudresden.ocl20.interpreter.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram;
import tudresden.ocl20.pivot.examples.royalsandloyals.Membership;
import tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner;
import tudresden.ocl20.pivot.examples.royalsandloyals.Service;
import tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel;
import tudresden.ocl20.pivot.examples.royalsandloyals.Transaction;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclVoid;

/**
 * <p>
 * This class provides some test cases which tests the OCL2 Interpreter plug-in.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InterpreterTest {

	/**
	 * The {@link TestPerformer} used for all test cases of this class.
	 */
	protected static TestPerformer testPerformer;

	/**
	 * The {@link IModelObject}s which shall be interpreted during a test case.
	 */
	List<IModelObject> objectList;

	/**
	 * The interpreted results of a test case.
	 */
	List<OclRoot> results;

	/**
	 * The global {@link IEnvironment} of the used {@link OclInterpreter}.
	 */
	IEnvironment globalEnvironment;

	/**
	 * <p>
	 * Prepares the interpreter tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		testPerformer = new TestPerformer();
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in body02.ocl.
	 * </p>
	 */
	@Test
	public void testBody02() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/body02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(10));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::getCustomerName()";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/body02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in body03.ocl.
	 * </p>
	 */
	@Test
	public void testBody03() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/body03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Date.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "Date::nowAsString()";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/body03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define01.ocl.
	 * </p>
	 */
	@Test
	public void testDef01() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(11));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::turnover";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define02.ocl.
	 * </p>
	 */
	@Test
	public void testDef02() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(13));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyProgram::getServicesByLevel(String)";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define03.ocl.
	 * </p>
	 */
	@Test
	public void testDef03() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(10));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "Membership::getCurrentLevelName()";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define04.ocl.
	 * </p>
	 */
	@Test
	public void testDef04() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(12));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::usedServices";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define04.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define05.ocl.
	 * </p>
	 */
	@Test
	public void testDef05() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(22));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "Customer::initial";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define05.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define06.ocl.
	 * </p>
	 */
	@Test
	public void testDef06() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(9));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "CustomerCard::getTotalPoints(root::tudresden::ocl20::pivot::examples::royalsandloyals::Date)";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define06.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define07.ocl.
	 * </p>
	 */
	@Test
	public void testDef07() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(10));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "CustomerCard::getAllInstances()";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define07.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in define08.ocl.
	 * </p>
	 */
	@Test
	public void testDef08() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/define08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(3));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "ProgramPartner::getBurningTransactions()";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/define08.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in derive01.ocl.
	 * </p>
	 */
	@Test
	public void testDerive01() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/derive01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(11));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "CustomerCard::printedName-derive";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/derive01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in derive02.ocl.
	 * </p>
	 */
	@Test
	public void testDerive02() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/derive02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(13));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::totalPointsEarned-derive";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/derive02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in init01.ocl.
	 * </p>
	 */
	@Test
	public void testInit01() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/init01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(14));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::points-initial";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/init01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in init02.ocl.
	 * </p>
	 */
	@Test
	public void testInit02() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/init02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(12));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "CustomerCard::valid-initial";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/init02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in init03.ocl.
	 * </p>
	 */
	@Test
	public void testInit03() {

		List<IModelObject> tempList;

		String constraintPath;
		Constraint result1;
		JavaOclBoolean result2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/init03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(15));

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer
				.prepareRemainingConstraints(objectList);

		constraintPath = "root::tudresden::ocl20::pivot::examples::royalsandloyals::";
		constraintPath += "LoyaltyAccount::transactions-initial";

		/* The global environment should now contain the defined method. */
		result1 = globalEnvironment.getConstraint(constraintPath);
		assertNotNull(result1);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("expressions/testInterpreter/init03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result2 = (JavaOclBoolean) results.get(0);
		assertTrue(result2.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant01.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(18));
		objectList.add(tempList.get(19));
		objectList.add(tempList.get(20));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant02.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant03.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant04.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(11));
		objectList.add(tempList.get(12));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant05.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(13));
		objectList.add(tempList.get(14));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant06.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(16));
		objectList.add(tempList.get(17));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant07.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant07() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/*
		 * Reset the model to undo some preparations which cross-cut with the
		 * tested constraint.
		 */
		testPerformer.resetUML2Model();

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(26));
		objectList.add(tempList.get(27));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant08.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant08() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(20));
		objectList.add(tempList.get(21));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant09.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant09() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant09.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(8));
		objectList.add(tempList.get(9));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant10.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant10() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant10.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(24));
		objectList.add(tempList.get(25));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant11.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant11() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant11.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(10));
		objectList.add(tempList.get(11));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant12.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant12() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant12.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(12));
		objectList.add(tempList.get(13));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant13.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant13() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant13.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(22));
		objectList.add(tempList.get(23));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant14.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant14() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant14.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(17));
		objectList.add(tempList.get(18));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant15.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant15() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant15.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(19));
		objectList.add(tempList.get(20));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant16.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant16() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant16.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant17.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant17() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/invariant17.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * FIXME Test again when the bug in the parser is fixed.
	 * 
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant18.ocl.
	 * </p>
	 */
//	@Test
//	public void testInvariant18() {
//
//		List<IModelObject> tempList;
//		JavaOclBoolean result;
//
//		/* Load OCL file. */
//		testPerformer.loadOCLFile("expressions/invariant18.ocl");
//
//		/* Select the model objects which shall be interpreted. */
//		tempList = testPerformer.getObjectsOfKind(TestPerformer
//				.getClassNameAsPathList(Burning.class));
//
//		objectList = new ArrayList<IModelObject>();
//		objectList.add(tempList.get(4));
//
//		/* Interpret the selected combinations. */
//		results = testPerformer.interpretRemainingConstraints(objectList);
//
//		/* Compare with expected results. */
//		result = (JavaOclBoolean) results.get(0);
//		assertTrue(result.isTrue());
//	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post01.ocl.
	 * </p>
	 */
	@Test
	public void testPost01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		IModelObject aCustomer;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(29));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		aCustomer = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(Customer.class)).get(32);
		testPerformer.setVar("c", aCustomer.getOclObject().getAdaptee());

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			objectList.get(0).getOclObject().invokeOperation("enroll",
					aCustomer.getOclObject());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post02.ocl.
	 * </p>
	 */
	@Test
	public void testPost02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		IModelObject aPartner;
		IModelObject aLevel;
		IModelObject aService;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(30));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		aPartner = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(15);
		aLevel = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				11);
		aService = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(Service.class)).get(19);

		testPerformer.setVar("aPartner", aPartner.getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", aLevel.getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService.getOclObject().getAdaptee());

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[3];

			parameters[0] = aPartner.getOclObject();
			parameters[1] = aLevel.getOclObject();
			parameters[2] = aService.getOclObject();

			objectList.get(0).getOclObject().invokeOperation("addService",
					parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post03.ocl.
	 * </p>
	 */
	@Test
	public void testPost03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		IModelObject aPartner;
		IModelObject aLevel;
		IModelObject aService;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(30));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		aPartner = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(15);
		aLevel = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				11);
		aService = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(Service.class)).get(19);

		testPerformer.setVar("aPartner", aPartner.getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", aLevel.getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService.getOclObject().getAdaptee());

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[3];

			parameters[0] = aPartner.getOclObject();
			parameters[1] = aLevel.getOclObject();
			parameters[2] = aService.getOclObject();

			objectList.get(0).getOclObject().invokeOperation("addService",
					parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post04.ocl.
	 * </p>
	 */
	@Test
	public void testPost04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		IModelObject aPartner;
		IModelObject aLevel;
		IModelObject aService;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(30));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		aPartner = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(15);
		aLevel = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				11);
		aService = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(Service.class)).get(19);

		testPerformer.setVar("aPartner", aPartner.getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", aLevel.getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService.getOclObject().getAdaptee());

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[3];

			parameters[0] = aPartner.getOclObject();
			parameters[1] = aLevel.getOclObject();
			parameters[2] = aService.getOclObject();

			objectList.get(0).getOclObject().invokeOperation("addService",
					parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post05.ocl.
	 * </p>
	 */
	@Test
	public void testPost05_1() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(21));

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclRoot parameters[];
			OclRoot opResult;

			parameters = new OclRoot[0];

			opResult = objectList.get(0).getOclObject().invokeOperation(
					"isEmpty", parameters);

			testPerformer.setVar("result", opResult.getAdaptee());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post05.ocl.
	 * </p>
	 */
	@Test
	public void testPost05_2() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(22));

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclRoot parameters[];
			OclRoot opResult;

			parameters = new OclRoot[0];

			opResult = objectList.get(0).getOclObject().invokeOperation(
					"isEmpty", parameters);

			testPerformer.setVar("result", opResult.getAdaptee());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post06.ocl.
	 * </p>
	 */
	@Test
	public void testPost06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(33));

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[0];

			objectList.get(0).getOclObject().invokeOperation("birthdayHappens",
					parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post07.ocl.
	 * </p>
	 */
	@Test
	public void testPost07() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		Integer anAmount;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(20));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		anAmount = new Integer(100);

		testPerformer.setVar("amount", anAmount);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[1];

			parameters[0] = new JavaOclInteger(anAmount);

			objectList.get(0).getOclObject().invokeOperation(
					"upgradePointsEarned", parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("amount");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post08.ocl.
	 * </p>
	 */
	@Test
	public void testPost08() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(20));

		/* Prepare the selected model objects. */
		testPerformer.setVar("result", null);
		testPerformer.prepareRemainingConstraints(objectList, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclRoot parameters[];
			OclRoot opResult;

			parameters = new OclRoot[0];

			opResult = objectList.get(0).getOclObject().invokeOperation(
					"getProgram", parameters);

			testPerformer.setVar("result", opResult.getAdaptee());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post10.ocl.
	 * </p>
	 */
	@Test
	public void testPost10() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post10.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(20));

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclRoot parameters[];

			parameters = new OclRoot[0];

			objectList.get(0).getOclObject().invokeOperation("getProgram",
					parameters);
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post11.ocl.
	 * </p>
	 */
	@Test
	public void testPost11() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		IModelObject aCustomer;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post11.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(32));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this postcondition to the environment.
		 */
		aCustomer = testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(Customer.class)).get(34);
		testPerformer.setVar("c", aCustomer.getOclObject().getAdaptee());

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/* Try to invoke the tested operation. */
		try {
			objectList.get(0).getOclObject().invokeOperation("enroll",
					aCustomer.getOclObject());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post12.ocl.
	 * </p>
	 */
	@Test
	public void testPost12() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("expressions/post12.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Date.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(1));

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(objectList, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclRoot parameters[];
			OclRoot opResult;

			parameters = new OclRoot[0];

			opResult = objectList.get(0).getOclObject().invokeOperation("now",
					parameters);

			testPerformer.setVar("result", opResult.getAdaptee());
		}

		catch (NoSuchMethodException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre01.ocl.
	 * </p>
	 */
	@Test
	public void testPre01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		Customer aCustomer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this precondition to the environment.
		 */
		aCustomer = new Customer(23);
		aCustomer.setName("Testman");
		testPerformer.setVar("c", aCustomer);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Do the same test with different values. */

		/* Reload the OCL file for another interpretation. */
		testPerformer.loadOCLFile("expressions/pre01.ocl");

		/* Set new parameters as preparation. */
		aCustomer.setName("");
		testPerformer.setVar("c", aCustomer);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre02.ocl.
	 * </p>
	 */
	@Test
	public void testPre02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		Service aService;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(26));
		objectList.add(tempList.get(28));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this precondition to the environment.
		 */
		aService = new Service();

		testPerformer.setVar("aPartner", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(14).getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				10).getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aServices");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre03.ocl.
	 * </p>
	 */
	@Test
	public void testPre03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		Service aService;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(26));
		objectList.add(tempList.get(27));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this precondition to the environment.
		 */
		aService = new Service();

		testPerformer.setVar("aPartner", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(14).getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				10).getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aServices");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre04.ocl.
	 * </p>
	 */
	@Test
	public void testPre04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		Service aService;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(26));
		objectList.add(tempList.get(27));
		objectList.add(tempList.get(28));

		/*
		 * Add the values needed as parameters of the operation call constrained
		 * by this precondition to the environment.
		 */
		aService = new Service();

		testPerformer.setVar("aPartner", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ProgramPartner.class))
				.get(14).getOclObject().getAdaptee());
		testPerformer.setVar("aLevel", testPerformer.getObjectsOfKind(
				TestPerformer.getClassNameAsPathList(ServiceLevel.class)).get(
				10).getOclObject().getAdaptee());
		testPerformer.setVar("aService", aService);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetVar("aPartner");
		testPerformer.resetVar("aLevel");
		testPerformer.resetVar("aServices");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre05.ocl.
	 * </p>
	 */
	@Test
	public void testPre05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre06.ocl.
	 * </p>
	 */
	@Test
	public void testPre06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/pre06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Date.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any01.ocl.
	 * </p>
	 */
	@Test
	public void testAny01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/any01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any02.ocl.
	 * </p>
	 */
	@Test
	public void testAny02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/any02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any03.ocl.
	 * </p>
	 */
	@Test
	public void testAny03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/any03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any04.ocl.
	 * </p>
	 */
	@Test
	public void testAny04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/any04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any05.ocl.
	 * </p>
	 */
	@Test
	public void testAny05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/any05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean01.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(2));
		objectList.add(tempList.get(3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean02.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean03.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));
		objectList.add(tempList.get(7));
		objectList.add(tempList.get(8));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean04.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(9));
		objectList.add(tempList.get(10));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean05.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(11));
		objectList.add(tempList.get(12));
		objectList.add(tempList.get(13));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean06.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/boolean06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(14));
		objectList.add(tempList.get(15));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection01.ocl.
	 * </p>
	 */
	@Test
	public void testCollection01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection02.ocl.
	 * </p>
	 */
	@Test
	public void testCollection02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection03.ocl.
	 * </p>
	 */
	@Test
	public void testCollection03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection04.ocl.
	 * </p>
	 */
	@Test
	public void testCollection04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));
		objectList.add(tempList.get(2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection05.ocl.
	 * </p>
	 */
	@Test
	public void testCollection05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection06.ocl.
	 * </p>
	 */
	@Test
	public void testCollection06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection07.ocl.
	 * </p>
	 */
	@Test
	public void testCollection07() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection08.ocl.
	 * </p>
	 */
	@Test
	public void testCollection08() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection09.ocl.
	 * </p>
	 */
	@Test
	public void testCollection09() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection09.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection10.ocl.
	 * </p>
	 */
	@Test
	public void testCollection10() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection10.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection11.ocl.
	 * </p>
	 */
	@Test
	public void testCollection11() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection11.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection12.ocl.
	 * </p>
	 */
	@Test
	public void testCollection12() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection12.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection13.ocl.
	 * </p>
	 */
	@Test
	public void testCollection13() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection13.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection14.ocl.
	 * </p>
	 */
	@Test
	public void testCollection14() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection14.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection15.ocl.
	 * </p>
	 */
	@Test
	public void testCollection15() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection15.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection16.ocl.
	 * </p>
	 */
	@Test
	public void testCollection16() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection16.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection17.ocl.
	 * </p>
	 */
	@Test
	public void testCollection17() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection17.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection18.ocl.
	 * </p>
	 */
	@Test
	public void testCollection18() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection18.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection19.ocl.
	 * </p>
	 */
	@Test
	public void testCollection19() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection19.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection20.ocl.
	 * </p>
	 */
	@Test
	public void testCollection20() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection20.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ServiceLevel.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));
		objectList.add(tempList.get(2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection21.ocl.
	 * </p>
	 */
	@Test
	public void testCollection21() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection21.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ServiceLevel.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));
		objectList.add(tempList.get(2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection22.ocl.
	 * </p>
	 */
	@Test
	public void testCollection22() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection22.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection25.ocl.
	 * </p>
	 */
	@Test
	public void testCollection25() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection25.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection26.ocl.
	 * </p>
	 */
	@Test
	public void testCollection26() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection26.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection27.ocl.
	 * </p>
	 */
	@Test
	public void testCollection27() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection27.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection28.ocl.
	 * </p>
	 */
	@Test
	public void testCollection28() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection28.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection29.ocl.
	 * </p>
	 */
	@Test
	public void testCollection29() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection29.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection30.ocl.
	 * </p>
	 */
	@Test
	public void testCollection30() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection30.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection31.ocl.
	 * </p>
	 */
	@Test
	public void testCollection31() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection31.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection32.ocl.
	 * </p>
	 */
	@Test
	public void testCollection32() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection32.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection33.ocl.
	 * </p>
	 */
	@Test
	public void testCollection33() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection33.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection34.ocl.
	 * </p>
	 */
	@Test
	public void testCollection34() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/collection34.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Membership.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterate01.ocl.
	 * </p>
	 */
	@Test
	public void testIterate01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterate01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator01.ocl.
	 * </p>
	 */
	@Test
	public void testIterator01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator02.ocl.
	 * </p>
	 */
	@Test
	public void testIterator02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator03.ocl.
	 * </p>
	 */
	@Test
	public void testIterator03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator04.ocl.
	 * </p>
	 */
	@Test
	public void testIterator04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator05.ocl.
	 * </p>
	 */
	@Test
	public void testIterator05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));
		objectList.add(tempList.get(1));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator06.ocl.
	 * </p>
	 */
	@Test
	public void testIterator06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(ProgramPartner.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator07.ocl.
	 * </p>
	 */
	@Test
	public void testIterator07() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(CustomerCard.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));
		objectList.add(tempList.get(7));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator08.ocl.
	 * </p>
	 */
	@Test
	public void testIterator08() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(16));
		objectList.add(tempList.get(17));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator09.ocl.
	 * </p>
	 */
	@Test
	public void testIterator09() {

		List<IModelObject> tempList;
		JavaOclBoolean result;
		JavaOclRoot voidResult;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator09.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(11));
		objectList.add(tempList.get(12));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		voidResult = (JavaOclRoot) results.get(1);
		assertTrue(voidResult instanceof JavaOclVoid);
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator10.ocl.
	 * </p>
	 */
	@Test
	public void testIterator10() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator10.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(2));
		objectList.add(tempList.get(3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator11.ocl.
	 * </p>
	 */
	@Test
	public void testIterator11() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator11.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(1));
		objectList.add(tempList.get(2));
		objectList.add(tempList.get(3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator12.ocl.
	 * </p>
	 */
	@Test
	public void testIterator12() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator12.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(1));
		objectList.add(tempList.get(2));
		objectList.add(tempList.get(3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator13.ocl.
	 * </p>
	 */
	@Test
	public void testIterator13() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator13.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyProgram.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(4));
		objectList.add(tempList.get(5));
		objectList.add(tempList.get(6));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(2);
		assertFalse(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator14.ocl.
	 * </p>
	 */
	@Test
	public void testIterator14() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator14.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(LoyaltyAccount.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(4));
		objectList.add(tempList.get(5));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator15.ocl.
	 * </p>
	 */
	@Test
	public void testIterator15() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator15.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(4));
		objectList.add(tempList.get(5));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator16.ocl.
	 * </p>
	 */
	@Test
	public void testIterator16() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/iterator16.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Customer.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(4));
		objectList.add(tempList.get(5));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertFalse(result.isTrue());

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(1);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric01.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric02.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric03.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric04.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric05.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric06.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric07.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric07() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric07.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric08.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric08() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric08.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric09.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric09() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric09.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric10.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric10() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric10.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric11.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric11() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/numeric11.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static01.ocl.
	 * </p>
	 */
	@Test
	public void testStatic01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/static01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static02.ocl.
	 * </p>
	 */
	@Test
	public void testStatic02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/static02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static03.ocl.
	 * </p>
	 */
	@Test
	public void testStatic03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/static03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static04.ocl.
	 * </p>
	 */
	@Test
	public void testStatic04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/static04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(6));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string01.ocl.
	 * </p>
	 */
	@Test
	public void testString01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string02.ocl.
	 * </p>
	 */
	@Test
	public void testString02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string03.ocl.
	 * </p>
	 */
	@Test
	public void testString03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string04.ocl.
	 * </p>
	 */
	@Test
	public void testString04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string05.ocl.
	 * </p>
	 */
	@Test
	public void testString05() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string05.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in string06.ocl.
	 * </p>
	 */
	@Test
	public void testString06() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/string06.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Service.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in tuple01.ocl.
	 * </p>
	 */
	@Test
	public void testTuple01() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/tuple01.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in tuple02.ocl.
	 * </p>
	 */
	@Test
	public void testTuple02() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/tuple02.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in tuple03.ocl.
	 * </p>
	 */
	@Test
	public void testTuple03() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/tuple03.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * in tuple04.ocl.
	 * </p>
	 */
	@Test
	public void testTuple04() {

		List<IModelObject> tempList;
		JavaOclBoolean result;

		/* Load OCL file. */
		testPerformer.loadOCLFile("expressions/test/tuple04.ocl");

		/* Select the model objects which shall be interpreted. */
		tempList = testPerformer.getObjectsOfKind(TestPerformer
				.getClassNameAsPathList(Transaction.class));

		objectList = new ArrayList<IModelObject>();
		objectList.add(tempList.get(0));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result = (JavaOclBoolean) results.get(0);
		assertTrue(result.isTrue());
	}
}
