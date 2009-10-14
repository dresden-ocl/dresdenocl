/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.interpreter.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.examples.royalsandloyals.Burning;
import tudresden.ocl20.pivot.examples.royalsandloyals.Color;
import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import tudresden.ocl20.pivot.examples.royalsandloyals.Earning;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram;
import tudresden.ocl20.pivot.examples.royalsandloyals.Membership;
import tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner;
import tudresden.ocl20.pivot.examples.royalsandloyals.Service;
import tudresden.ocl20.pivot.examples.royalsandloyals.ServiceLevel;
import tudresden.ocl20.pivot.examples.royalsandloyals.Transaction;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;

/**
 * <p>
 * This class tests the OCL interpreter using the UML2 meta model and the Java
 * model instance type.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInterpretation {

	/**
	 * The {@link TestPerformer} used for all test cases of this class.
	 */
	protected static TestPerformer testPerformer;

	/**
	 * The {@link IModelInstanceElement}s which shall be interpreted during a test
	 * case.
	 */
	private List<IModelInstanceElement> objectList;

	/**
	 * The interpreted results of a test case.
	 */
	private List<OclAny> results;

	/**
	 * The global {@link IInterpretationEnvironment} of the used
	 * {@link IOclInterpreter}.
	 */
	private IInterpretationEnvironment globalEnvironment;

	/**
	 * The current {@link IModelInstanceElement} for that a {@link Constraint}
	 * shall be interpreted.
	 */
	private IModelInstanceElement modelObject;

	/**
	 * A field that can be used to store the name of the result of a preparation
	 * (a {@link Constraint}).
	 */
	private String result_constrainedElement;

	/**
	 * A field that can be used to store the result of a preparation (a
	 * {@link Constraint}).
	 */
	private Constraint result_Constraint;

	/**
	 * A field that can be used to store an {@link OclBoolean} interpretation
	 * result.
	 */
	private OclBoolean result_Boolean;

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
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * body02.ocl.
	 * </p>
	 */
	@Test
	public void testBody02() {

		LoyaltyAccount loyaltyAccount;
		Membership membership;
		CustomerCard card;
		Customer customer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/body02.ocl");

		/* Create the model objects that shall be interpreted. */
		customer = new Customer(25);
		customer.setName("Testman");

		card = new CustomerCard();
		card.setOwner(customer);

		membership = new Membership();
		membership.setCard(card);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::getCustomerName()";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/body02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * body03.ocl.
	 * </p>
	 */
	@Test
	public void testBody03() {

		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/body03.ocl");

		/* Create the model objects which shall be interpreted. */
		date = Date.now();

		modelObject = testPerformer.addModelObject(date);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_DATE;
		result_constrainedElement += "::nowAsString()";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/body03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define01.ocl.
	 * </p>
	 */
	@Test
	public void testDef01() {

		LoyaltyAccount loyaltyAccount;
		Transaction transaction1;
		Transaction transaction2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define01.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Transaction();
		transaction1.setAmount(new Float(25.5));

		transaction2 = new Transaction();
		transaction2.setAmount(new Float(74.5));

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.addTransaction(transaction1);
		loyaltyAccount.addTransaction(transaction2);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::turnover";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define02.ocl.
	 * </p>
	 */
	@Test
	public void testDef02() {

		LoyaltyProgram loyaltyProgram;
		ServiceLevel level1;
		ServiceLevel level2;
		Service service1;
		Service service2;
		Service service3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define02.ocl");

		/* Create the model objects which shall be interpreted. */
		service1 = new Service();
		service2 = new Service();
		service3 = new Service();

		level1 = new ServiceLevel();
		level1.setName("level1");
		level1.addAvailableService(service1);
		level1.addAvailableService(service2);

		level2 = new ServiceLevel();
		level2.setName("level2");
		level2.addAvailableService(service3);

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addLevel(level1);
		loyaltyProgram.addLevel(level2);

		modelObject = testPerformer.addModelObject(loyaltyProgram);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_PROGRAM;
		result_constrainedElement += "::getServicesByLevel(String)";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define03.ocl.
	 * </p>
	 */
	@Test
	public void testDef03() {

		Membership membership;
		ServiceLevel level;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define03.ocl");

		/* Create the model objects which shall be interpreted. */
		level = new ServiceLevel();
		level.setName("level1");

		membership = new Membership();
		membership.setCurrentLevel(level);

		modelObject = testPerformer.addModelObject(membership);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_MEMBERSHIP;
		result_constrainedElement += "::getCurrentLevelName()";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define04.ocl.
	 * </p>
	 */
	@Test
	public void testDef04() {

		LoyaltyAccount loyaltyAccount;
		Transaction transaction1;
		Transaction transaction2;
		Service service1;
		Service service2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define04.ocl");

		/* Create the model objects which shall be interpreted. */
		service1 = new Service();
		service2 = new Service();

		transaction1 = new Transaction();
		transaction1.setService(service1);

		transaction2 = new Transaction();
		transaction2.setService(service2);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.addTransaction(transaction1);
		loyaltyAccount.addTransaction(transaction2);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::usedServices";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define04.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define05.ocl.
	 * </p>
	 */
	@Test
	public void testDef05() {

		Customer customer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define05.ocl");

		/* Create the model objects which shall be interpreted. */
		customer = new Customer(25);
		customer.setName("Tesman");

		modelObject = testPerformer.addModelObject(customer);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_CUSTOMER;
		result_constrainedElement += "::initial";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define05.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define06.ocl.
	 * </p>
	 */
	@Test
	public void testDef06() {

		CustomerCard customerCard;
		Transaction transaction1;
		Transaction transaction2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define06.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Transaction();
		transaction1.setDate(Date.now());
		transaction1.setPoints(100);

		transaction2 = new Transaction();
		transaction2.setDate(Date.now());
		transaction2.setPoints(50);

		customerCard = new CustomerCard();
		customerCard.addTransaction(transaction1);
		customerCard.addTransaction(transaction2);

		modelObject = testPerformer.addModelObject(customerCard);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_CUSTOMER_CARD;
		result_constrainedElement +=
				"::getTotalPoints(root::tudresden::ocl20::pivot::examples::royalsandloyals::Date)";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define06.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define07.ocl.
	 * </p>
	 */
	@Test
	public void testDef07() {

		CustomerCard customerCard;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define07.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard = new CustomerCard();

		modelObject = testPerformer.addModelObject(customerCard);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_CUSTOMER_CARD;
		result_constrainedElement += "::getAllInstances()";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define07.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.oclIsInvalid().isTrue());
		// assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * define08.ocl.
	 * </p>
	 */
	@Test
	public void testDef08() {

		ProgramPartner programPartner;

		Service service1;
		Service service2;
		Service service3;

		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/define08.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Burning();
		transaction2 = new Earning();
		transaction3 = new Burning();

		service1 = new Service();
		service1.setTransaction(transaction1);

		service2 = new Service();
		service2.setTransaction(transaction2);

		service3 = new Service();
		service3.setTransaction(transaction3);

		programPartner = new ProgramPartner();
		programPartner.addDeliveredService(service1);
		programPartner.addDeliveredService(service2);
		programPartner.addDeliveredService(service3);

		testPerformer.addModelObject(transaction1);
		testPerformer.addModelObject(transaction2);
		testPerformer.addModelObject(transaction3);

		testPerformer.addModelObject(service1);
		testPerformer.addModelObject(service2);
		testPerformer.addModelObject(service3);

		modelObject = testPerformer.addModelObject(programPartner);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_PROGRAM_PARTNER;
		result_constrainedElement += "::getBurningTransactions()";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_constrainedElement);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/define08.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * derive01.ocl.
	 * </p>
	 */
	@Test
	public void testDerive01() {

		CustomerCard customerCard;

		Customer customer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/derive01.ocl");

		/* Create the model objects which shall be interpreted. */
		customer = new Customer(25);
		customer.setName("Testman");
		customer.setTitle("Mr.");

		customerCard = new CustomerCard();
		customerCard.setOwner(customer);

		modelObject = testPerformer.addModelObject(customerCard);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_CUSTOMER_CARD;
		result_constrainedElement += "::printedName-derive";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/derive01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * derive02.ocl.
	 * </p>
	 */
	@Test
	public void testDerive02() {

		LoyaltyAccount loyaltyAccount;

		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/derive02.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Earning();
		transaction1.setPoints(25);

		transaction2 = new Burning();
		transaction2.setPoints(50);

		transaction3 = new Earning();
		transaction3.setPoints(25);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.addTransaction(transaction1);
		loyaltyAccount.addTransaction(transaction2);
		loyaltyAccount.addTransaction(transaction3);

		testPerformer.addModelObject(transaction1);
		testPerformer.addModelObject(transaction2);
		testPerformer.addModelObject(transaction3);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::totalPointsEarned-derive";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/derive02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * init01.ocl.
	 * </p>
	 */
	@Test
	public void testInit01() {

		LoyaltyAccount loyaltyAccount;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/init01.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::points-initial";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/init01.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * init02.ocl.
	 * </p>
	 */
	@Test
	public void testInit02() {

		CustomerCard customerCard;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/init02.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard = new CustomerCard();

		modelObject = testPerformer.addModelObject(customerCard);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_CUSTOMER_CARD;
		result_constrainedElement += "::valid-initial";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/init02.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * init03.ocl.
	 * </p>
	 */
	@Test
	public void testInit03() {

		LoyaltyAccount loyaltyAccount;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/init03.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		/* Prepare the selected combinations. */
		globalEnvironment = testPerformer.prepareRemainingConstraints(modelObject);

		result_constrainedElement = TestPerformer.QUALIFIED_NAME_LOYALTY_ACCOUNT;
		result_constrainedElement += "::transactions-initial";

		/* The global environment should now contain the defined method. */
		result_Constraint =
				globalEnvironment.getConstraint(result_constrainedElement);
		assertNotNull(result_Constraint);

		/* Load another OCL file to verify the body preparation. */
		testPerformer.loadOCLFile("constraints/testInterpreter/init03.ocl");

		/* Interpret the selected objectList. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant01.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant01() {

		Customer customer1;
		Customer customer2;
		Customer customer3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant01.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(17);
		customer2 = new Customer(18);
		customer3 = new Customer(19);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));
		objectList.add(testPerformer.addModelObject(customer3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant02.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant02() {

		CustomerCard customerCard1;
		CustomerCard customerCard2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant02.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard1.setValidFrom(new Date(2008, 1, 1));
		customerCard1.setValidThru(new Date(2009, 1, 1));

		customerCard2 = new CustomerCard();
		customerCard2.setValidFrom(new Date(2009, 1, 1));
		customerCard2.setValidThru(new Date(2008, 1, 1));

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customerCard1));
		objectList.add(testPerformer.addModelObject(customerCard2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant03.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant03() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;
		Membership membership1;
		Membership membership2;
		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant03.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);

		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant04.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant04() {

		Membership membership1;
		Membership membership2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Customer customer1;
		Customer customer2;

		CustomerCard customerCard1;
		CustomerCard customerCard2;
		CustomerCard customerCard3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant04.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();
		customerCard3 = new CustomerCard();

		customer1 = new Customer(25);
		customer1.addCard(customerCard1);

		customer2 = new Customer(25);
		customer2.addCard(customerCard2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram1.enroll(customer1);

		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram2.enroll(customer2);

		membership1 = new Membership();
		membership1.setProgram(loyaltyProgram1);
		membership1.setCard(customerCard1);

		membership2 = new Membership();
		membership2.setProgram(loyaltyProgram2);
		membership2.setCard(customerCard3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant05.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant05() {

		Membership membership1;
		Membership membership2;
		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		CustomerCard customerCard1;
		CustomerCard customerCard2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant05.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();

		customerCard1.setColor(Color.silver);
		customerCard2.setColor(Color.silver);

		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		serviceLevel1.setName("Silver");
		serviceLevel2.setName("Gold");

		membership1 = new Membership();
		membership2 = new Membership();

		membership1.setCurrentLevel(serviceLevel1);
		membership2.setCurrentLevel(serviceLevel2);

		membership1.setCard(customerCard1);
		membership2.setCard(customerCard2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/*
		 * Otherwhise the Enumeration 'Color' will not be found during
		 * interpretation.
		 */
		testPerformer.addModelObject(Color.silver);
		testPerformer.addModelObject(Color.gold);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant06.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant06() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant06.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		programPartner1.addDeliveredService(service);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		loyaltyProgram1.addPartner(programPartner1);
		loyaltyProgram2.addPartner(programPartner1);
		loyaltyProgram2.addPartner(programPartner2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant07.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant07() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		CustomerCard customerCard1;
		CustomerCard customerCard2;
		CustomerCard customerCard3;
		CustomerCard customerCard4;

		/*
		 * Reset the model to undo some preparations which cross-cut with the tested
		 * constraint.
		 */
		testPerformer.resetModel();

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant07.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();
		customerCard3 = new CustomerCard();
		customerCard4 = new CustomerCard();

		customerCard1.setValid(true);
		customerCard2.setValid(true);
		customerCard3.setValid(true);
		customerCard4.setValid(false);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1 = new Customer(25);
		customer2 = new Customer(25);

		customer1.addProgram(loyaltyProgram1);
		customer1.addProgram(loyaltyProgram2);
		customer2.addProgram(loyaltyProgram1);
		customer2.addProgram(loyaltyProgram2);

		customer1.addCard(customerCard1);
		customer1.addCard(customerCard2);
		customer2.addCard(customerCard3);
		customer2.addCard(customerCard4);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant08.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant08() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Service service1;
		Service service2;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Membership membership1;
		Membership membership2;

		LoyaltyAccount loyaltyAccount;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant08.ocl");

		/* Select the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();

		membership1 = new Membership();
		membership2 = new Membership();

		membership2.addAccount(loyaltyAccount);

		service1 = new Service();
		service2 = new Service();

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		programPartner1.addDeliveredService(service1);
		programPartner2.addDeliveredService(service2);

		service1.setPointsBurned(0);
		service2.setPointsBurned(0);

		service1.setPointsEarned(0);
		service2.setPointsEarned(0);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		loyaltyProgram1.addPartner(programPartner1);
		loyaltyProgram2.addPartner(programPartner2);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram2.setMembership(membership2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant09.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant09() {

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Customer customer1;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant09.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(25);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		loyaltyProgram1.enroll(customer1);

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		programPartner1.setNumberOfCustomers(1);
		programPartner2.setNumberOfCustomers(1);

		programPartner1.addProgram(loyaltyProgram1);
		programPartner2.addProgram(loyaltyProgram2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(programPartner1));
		objectList.add(testPerformer.addModelObject(programPartner2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant10.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant10() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant10.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		serviceLevel1.setName("Silver");
		serviceLevel2.setName("Gold");

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram2.addLevel(serviceLevel2);
		loyaltyProgram2.addLevel(serviceLevel1);

		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant11.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant11() {

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Service service1;
		Service service2;

		Transaction transaction1;
		Transaction transaction2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant11.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Transaction();
		transaction2 = new Transaction();

		transaction1.setPoints(2000);
		transaction2.setPoints(10000);

		service1 = new Service();
		service2 = new Service();

		service1.setTransaction(transaction1);
		service2.setTransaction(transaction2);

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		programPartner1.addDeliveredService(service1);
		programPartner2.addDeliveredService(service2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(programPartner1));
		objectList.add(testPerformer.addModelObject(programPartner2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant12.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant12() {

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Service service1;
		Service service2;

		Transaction transaction1;
		Transaction transaction2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant12.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction1 = new Burning();
		transaction2 = new Earning();

		transaction1.setPoints(10000);
		transaction2.setPoints(10000);

		service1 = new Service();
		service2 = new Service();

		service1.setTransaction(transaction1);
		service2.setTransaction(transaction2);

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		programPartner1.addDeliveredService(service1);
		programPartner2.addDeliveredService(service2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(programPartner1));
		objectList.add(testPerformer.addModelObject(programPartner2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant13.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant13() {

		CustomerCard customerCard1;
		CustomerCard customerCard2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant13.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();

		customerCard1.setValidFrom(new Date(2008, 1, 1));
		customerCard2.setValidFrom(new Date(2008, 1, 1));

		customerCard1.setValidThru(new Date(3008, 1, 1));
		customerCard2.setValidThru(new Date(2009, 1, 1));

		customerCard1.setValid(true);
		customerCard2.setValid(true);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customerCard1));
		objectList.add(testPerformer.addModelObject(customerCard2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant14.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant14() {

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;

		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;
		Transaction transaction4;

		CustomerCard customerCard1;
		CustomerCard customerCard2;
		CustomerCard customerCard3;
		CustomerCard customerCard4;

		Customer customer1;
		Customer customer2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant14.ocl");

		/* Select the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer2 = new Customer(25);

		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();
		customerCard3 = new CustomerCard();
		customerCard4 = new CustomerCard();

		customerCard1.setOwner(customer1);
		customerCard2.setOwner(customer1);
		customerCard3.setOwner(customer1);
		customerCard4.setOwner(customer2);

		transaction1 = new Transaction();
		transaction2 = new Transaction();
		transaction3 = new Transaction();
		transaction4 = new Transaction();

		transaction1.setCard(customerCard1);
		transaction2.setCard(customerCard2);
		transaction3.setCard(customerCard3);
		transaction4.setCard(customerCard4);

		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount2 = new LoyaltyAccount();

		loyaltyAccount1.addTransaction(transaction1);
		loyaltyAccount1.addTransaction(transaction2);
		loyaltyAccount2.addTransaction(transaction3);
		loyaltyAccount2.addTransaction(transaction4);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyAccount1));
		objectList.add(testPerformer.addModelObject(loyaltyAccount2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant15.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant15() {

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;

		Transaction transaction1;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant15.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction1 = new Transaction();

		transaction1.setPoints(100);

		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount2 = new LoyaltyAccount();

		loyaltyAccount1.setPoints(100);
		loyaltyAccount2.setPoints(100);

		loyaltyAccount1.addTransaction(transaction1);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyAccount1));
		objectList.add(testPerformer.addModelObject(loyaltyAccount2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant16.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant16() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant16.ocl");

		/* Select the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant17.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant17() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant17.ocl");

		/* Select the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * invariant18.ocl.
	 * </p>
	 */
	@Test
	public void testInvariant18() {

		Transaction transaction;
		Burning burning;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/invariant18.ocl");

		/* Create the model objects which shall be interpreted. */
		burning = new Burning();
		burning.setPoints(100);

		transaction = new Transaction();

		testPerformer.addModelObject(transaction);
		modelObject = testPerformer.addModelObject(burning);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post01.ocl.
	 * </p>
	 */
	@Test
	public void testPost01() {

		LoyaltyProgram loyaltyProgram;
		OclAny loyaltyProgramInOcl;

		Customer customer;
		OclAny customerInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post01.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		modelObject = testPerformer.addModelObject(loyaltyProgram);
		loyaltyProgramInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		customer = new Customer(25);

		IModelInstanceElement imiCustomer = testPerformer.addModelObject(customer);
		customerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiCustomer);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		try {
			testPerformer.setEnvironmentVariable("c", customer);
		} catch (TypeNotFoundInModelException e1) {
			fail(e1.getMessage());
		}

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			Operation operation = testPerformer.findOperation(modelObject, "enroll");
			loyaltyProgramInOcl.invokeOperation(operation, customerInOcl);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post02.ocl.
	 * </p>
	 */
	@Test
	public void testPost02() {

		LoyaltyProgram loyaltyProgram;
		OclAny loyaltyProgramInOcl;

		ProgramPartner programPartner;
		OclAny programPartnerInOcl;

		ServiceLevel serviceLevel;
		OclAny serviceLevelInOcl;

		Service service;
		OclAny serviceInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post02.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		serviceLevel = new ServiceLevel();

		programPartner = new ProgramPartner();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addPartner(programPartner);
		loyaltyProgram.addLevel(serviceLevel);

		modelObject = testPerformer.addModelObject(loyaltyProgram);
		IModelInstanceElement imiProgramPartner =
				testPerformer.addModelObject(programPartner);
		IModelInstanceElement imiServiceLevel =
				testPerformer.addModelObject(serviceLevel);
		IModelInstanceElement imiService = testPerformer.addModelObject(service);

		loyaltyProgramInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		programPartnerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiProgramPartner);

		serviceLevelInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiServiceLevel);

		serviceInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiService);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		testPerformer.setEnvironmentVariable("aPartner", programPartnerInOcl);
		testPerformer.setEnvironmentVariable("aLevel", serviceLevelInOcl);
		testPerformer.setEnvironmentVariable("aService", serviceInOcl);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			OclAny parameters[];

			parameters = new OclAny[3];

			parameters[0] = programPartnerInOcl;
			parameters[1] = serviceLevelInOcl;
			parameters[2] = serviceInOcl;

			Operation operation =
					testPerformer.findOperation(modelObject, "addService");
			loyaltyProgramInOcl.invokeOperation(operation, parameters);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post03.ocl.
	 * </p>
	 */
	@Test
	public void testPost03() {

		LoyaltyProgram loyaltyProgram;
		OclAny loyaltyProgramInOcl;

		ProgramPartner programPartner;
		OclAny programPartnerInOcl;

		ServiceLevel serviceLevel;
		OclAny serviceLevelInOcl;

		Service service;
		OclAny serviceInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post03.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		serviceLevel = new ServiceLevel();

		programPartner = new ProgramPartner();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addPartner(programPartner);
		loyaltyProgram.addLevel(serviceLevel);

		modelObject = testPerformer.addModelObject(loyaltyProgram);
		IModelInstanceElement imiProgramPartner =
				testPerformer.addModelObject(programPartner);
		IModelInstanceElement imiServiceLevel =
				testPerformer.addModelObject(serviceLevel);
		IModelInstanceElement imiService = testPerformer.addModelObject(service);

		loyaltyProgramInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		programPartnerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiProgramPartner);

		serviceLevelInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiServiceLevel);

		serviceInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiService);
		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		testPerformer.setEnvironmentVariable("aPartner", programPartnerInOcl);
		testPerformer.setEnvironmentVariable("aLevel", serviceLevelInOcl);
		testPerformer.setEnvironmentVariable("aService", serviceInOcl);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			OclAny parameters[];

			parameters = new OclAny[3];

			parameters[0] = programPartnerInOcl;
			parameters[1] = serviceLevelInOcl;
			parameters[2] = serviceInOcl;

			Operation operation =
					testPerformer.findOperation(modelObject, "addService");
			loyaltyProgramInOcl.invokeOperation(operation, parameters);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post04.ocl.
	 * </p>
	 */
	@Test
	public void testPost04() {

		LoyaltyProgram loyaltyProgram;
		OclAny loyaltyProgramInOcl;

		ProgramPartner programPartner;
		OclAny programPartnerInOcl;

		ServiceLevel serviceLevel;
		OclAny serviceLevelInOcl;

		Service service;
		OclAny serviceInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post04.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		serviceLevel = new ServiceLevel();

		programPartner = new ProgramPartner();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addPartner(programPartner);
		loyaltyProgram.addLevel(serviceLevel);

		modelObject = testPerformer.addModelObject(loyaltyProgram);
		IModelInstanceElement imiProgramPartner =
				testPerformer.addModelObject(programPartner);
		IModelInstanceElement imiServiceLevel =
				testPerformer.addModelObject(serviceLevel);
		IModelInstanceElement imiService = testPerformer.addModelObject(service);

		loyaltyProgramInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		programPartnerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiProgramPartner);

		serviceLevelInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiServiceLevel);

		serviceInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiService);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		testPerformer.setEnvironmentVariable("aPartner", programPartnerInOcl);
		testPerformer.setEnvironmentVariable("aLevel", serviceLevelInOcl);
		testPerformer.setEnvironmentVariable("aService", serviceInOcl);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			OclAny parameters[];

			parameters = new OclAny[3];

			parameters[0] = programPartnerInOcl;
			parameters[1] = serviceLevelInOcl;
			parameters[2] = serviceInOcl;

			Operation operation =
					testPerformer.findOperation(modelObject, "addService");
			loyaltyProgramInOcl.invokeOperation(operation, parameters);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aService");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post05.ocl.
	 * </p>
	 */
	@Test
	public void testPost05a() {

		LoyaltyAccount loyaltyAccount;
		OclAny loyaltyAccountInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post05.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setPoints(0);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		loyaltyAccountInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclAny parameters[];
			OclAny opResult;

			parameters = new OclAny[0];

			Operation operation = testPerformer.findOperation(modelObject, "isEmpty");
			opResult = loyaltyAccountInOcl.invokeOperation(operation, parameters);

			testPerformer.setEnvironmentVariable("result", opResult);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post05.ocl.
	 * </p>
	 */
	@Test
	public void testPost05b() {

		LoyaltyAccount loyaltyAccount;
		OclAny loyaltyAccountInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post05.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setPoints(100);

		modelObject = testPerformer.addModelObject(loyaltyAccount);

		loyaltyAccountInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclAny parameters[];
			OclAny opResult;

			parameters = new OclAny[0];

			Operation operation = testPerformer.findOperation(modelObject, "isEmpty");
			opResult = loyaltyAccountInOcl.invokeOperation(operation, parameters);

			testPerformer.setEnvironmentVariable("result", opResult);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post06.ocl.
	 * </p>
	 */
	@Test
	public void testPost06() {

		Customer customer;
		OclAny customerInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post06.ocl");

		/* Create the model objects which shall be interpreted. */
		customer = new Customer(25);

		modelObject = testPerformer.addModelObject(customer);

		customerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			OclAny parameters[];

			parameters = new OclAny[0];

			Operation operation =
					testPerformer.findOperation(modelObject, "birthdayHappens");
			customerInOcl.invokeOperation(operation, parameters);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post07.ocl.
	 * </p>
	 */
	@Test
	public void testPost07() {

		Service service;
		Long integer;

		OclAny serviceInOcl;
		OclAny integerInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post07.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		service.setPointsEarned(100);
		service.setPointsBurned(0);

		modelObject = testPerformer.addModelObject(service);

		serviceInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		integer = new Long(100);

		integerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclInteger(
						integer);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		try {
			testPerformer.setEnvironmentVariable("amount", integer);
		} catch (TypeNotFoundInModelException e1) {
			fail(e1.getMessage());
		}

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			OclAny parameters[];

			parameters = new OclAny[1];
			parameters[0] = integerInOcl;

			Operation operation =
					testPerformer.findOperation(modelObject, "upgradePointsEarned");
			serviceInOcl.invokeOperation(operation, parameters);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("amount");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post08.ocl.
	 * </p>
	 */
	@Test
	public void testPost08() {

		Transaction transaction;
		OclAny transactionInOcl;

		Service service;
		ServiceLevel serviceLevel;
		LoyaltyProgram loyaltyProgram;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post08.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		serviceLevel = new ServiceLevel();
		serviceLevel.setProgram(loyaltyProgram);

		service = new Service();
		service.setLevel(serviceLevel);

		transaction = new Transaction();
		transaction.setService(service);

		testPerformer.addModelObject(service);
		testPerformer.addModelObject(serviceLevel);
		testPerformer.addModelObject(loyaltyProgram);

		modelObject = testPerformer.addModelObject(transaction);
		transactionInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/* Prepare the selected model objects. */
		testPerformer.resetEnvironmentVariable("result");
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclAny parameters[];
			OclAny opResult;

			parameters = new OclAny[0];
			Operation operation =
					testPerformer.findOperation(modelObject, "getProgram");
			opResult = transactionInOcl.invokeOperation(operation, parameters);

			testPerformer.setEnvironmentVariable("result", opResult);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post09.ocl.
	 * </p>
	 */
	@Test
	public void testPost10() {

		Transaction transaction;
		OclAny transactionInOcl;

		Service service;
		ServiceLevel serviceLevel;
		LoyaltyProgram loyaltyProgram;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post10.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		serviceLevel = new ServiceLevel();
		serviceLevel.setProgram(loyaltyProgram);

		service = new Service();
		service.setLevel(serviceLevel);

		transaction = new Transaction();
		transaction.setService(service);

		testPerformer.addModelObject(service);
		testPerformer.addModelObject(serviceLevel);
		testPerformer.addModelObject(loyaltyProgram);

		modelObject = testPerformer.addModelObject(transaction);
		transactionInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/* Prepare the selected model objects. */
		testPerformer.resetEnvironmentVariable("result");
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclAny parameters[];
			OclAny opResult;

			parameters = new OclAny[0];
			Operation operation =
					testPerformer.findOperation(modelObject, "getProgram");
			opResult = transactionInOcl.invokeOperation(operation, parameters);

			testPerformer.setEnvironmentVariable("result", opResult);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post11.ocl.
	 * </p>
	 */
	@Test
	public void testPost11() {

		LoyaltyProgram loyaltyProgram;
		OclAny loyaltyProgramInOcl;

		Membership membership;

		Customer customer;
		OclAny customerInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post11.ocl");

		/* Create the model objects which shall be interpreted. */
		customer = new Customer(25);

		membership = new Membership();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.setMembership(membership);

		modelObject = testPerformer.addModelObject(loyaltyProgram);
		loyaltyProgramInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		IModelInstanceElement imiCustomer = testPerformer.addModelObject(customer);

		customerInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiCustomer);

		testPerformer.addModelObject(membership);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this postcondition to the environment.
		 */
		testPerformer.setEnvironmentVariable("c", customerInOcl);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/* Try to invoke the tested operation. */
		try {
			Operation operation = testPerformer.findOperation(modelObject, "enroll");
			loyaltyProgramInOcl.invokeOperation(operation, customerInOcl);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * post12.ocl.
	 * </p>
	 */
	@Test
	public void testPost12() {

		Date date;
		OclAny dateInOcl;

		/* Load the OCL file. */
		testPerformer.loadOCLFile("constraints/post12.ocl");

		/* Create the model objects which shall be interpreted. */
		date = Date.now();

		modelObject = testPerformer.addModelObject(date);

		dateInOcl =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						modelObject);

		/* Prepare the selected model objects. */
		testPerformer.prepareRemainingConstraints(modelObject, false);

		/*
		 * Try to invoke the tested operation and add the result variable to the
		 * environment.
		 */
		try {
			OclAny parameters[];
			OclAny opResult;

			parameters = new OclAny[0];
			Operation operation = testPerformer.findOperation(modelObject, "now");
			opResult = dateInOcl.invokeOperation(operation, parameters);

			testPerformer.setEnvironmentVariable("result", opResult);
		}

		catch (OperationNotFoundException e) {
			fail("The tested operation could not be invoked. Test failed.");
		}

		/* Interpret the selected model objects. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("result");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre01.ocl.
	 * </p>
	 */
	@Test
	public void testPre01() {

		LoyaltyProgram loyaltyProgram;
		Customer customer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre01.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		modelObject = testPerformer.addModelObject(loyaltyProgram);

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this precondition to the environment.
		 */
		customer = new Customer(23);
		customer.setName("Testman");
		try {
			testPerformer.setEnvironmentVariable("c", customer);
		} catch (TypeNotFoundInModelException e1) {
			fail(e1.getMessage());
		}

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Do the same test with different values. */

		/* Reload the OCL file for another interpretation. */
		testPerformer.loadOCLFile("constraints/pre01.ocl");

		/* Set new parameters as preparation. */
		customer.setName("");
		try {
			testPerformer.setEnvironmentVariable("c", customer);
		} catch (TypeNotFoundInModelException e) {
			fail(e.getMessage());
		}

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("c");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre02.ocl.
	 * </p>
	 */
	@Test
	public void testPre02() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;
		LoyaltyProgram loyaltyProgram3;

		ProgramPartner programPartner;
		ServiceLevel serviceLevel;
		Object service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre02.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel = new ServiceLevel();
		programPartner = new ProgramPartner();

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram3 = new LoyaltyProgram();

		loyaltyProgram1.addPartner(programPartner);
		loyaltyProgram2.addPartner(programPartner);

		loyaltyProgram1.addLevel(serviceLevel);
		loyaltyProgram3.addLevel(serviceLevel);

		testPerformer.addModelObject(programPartner);
		testPerformer.addModelObject(serviceLevel);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));
		objectList.add(testPerformer.addModelObject(loyaltyProgram3));

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this precondition to the environment.
		 */
		service = new Service();

		try {
			testPerformer.setEnvironmentVariable("aPartner", programPartner);
			testPerformer.setEnvironmentVariable("aLevel", serviceLevel);
			testPerformer.setEnvironmentVariable("aService", service);

		} catch (TypeNotFoundInModelException e) {
			fail(e.getMessage());
		}

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aServices");

	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre03.ocl.
	 * </p>
	 */
	@Test
	public void testPre03() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;
		LoyaltyProgram loyaltyProgram3;

		ProgramPartner programPartner;
		ServiceLevel serviceLevel;
		Object service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre03.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel = new ServiceLevel();
		programPartner = new ProgramPartner();

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram3 = new LoyaltyProgram();

		loyaltyProgram1.addPartner(programPartner);
		loyaltyProgram2.addPartner(programPartner);

		loyaltyProgram1.addLevel(serviceLevel);
		loyaltyProgram3.addLevel(serviceLevel);

		testPerformer.addModelObject(programPartner);
		testPerformer.addModelObject(serviceLevel);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));
		objectList.add(testPerformer.addModelObject(loyaltyProgram3));

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this precondition to the environment.
		 */
		service = new Service();

		try {
			testPerformer.setEnvironmentVariable("aPartner", programPartner);
			testPerformer.setEnvironmentVariable("aLevel", serviceLevel);
			testPerformer.setEnvironmentVariable("aService", service);
		} catch (TypeNotFoundInModelException e) {
			fail(e.getMessage());
		}

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aServices");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre04.ocl.
	 * </p>
	 */
	@Test
	public void testPre04() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;
		LoyaltyProgram loyaltyProgram3;

		ProgramPartner programPartner;
		ServiceLevel serviceLevel;
		Object service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre04.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel = new ServiceLevel();
		programPartner = new ProgramPartner();

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram3 = new LoyaltyProgram();

		loyaltyProgram1.addPartner(programPartner);
		loyaltyProgram2.addPartner(programPartner);

		loyaltyProgram1.addLevel(serviceLevel);
		loyaltyProgram3.addLevel(serviceLevel);

		testPerformer.addModelObject(programPartner);
		testPerformer.addModelObject(serviceLevel);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));
		objectList.add(testPerformer.addModelObject(loyaltyProgram3));

		/*
		 * Add the values needed as parameters of the operation call constrained by
		 * this precondition to the environment.
		 */
		service = new Service();

		try {
			testPerformer.setEnvironmentVariable("aPartner", programPartner);
			testPerformer.setEnvironmentVariable("aLevel", serviceLevel);
			testPerformer.setEnvironmentVariable("aService", service);
		} catch (TypeNotFoundInModelException e) {
			fail(e.getMessage());
		}

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Remove the added values from the environment. */
		testPerformer.resetEnvironmentVariable("aPartner");
		testPerformer.resetEnvironmentVariable("aLevel");
		testPerformer.resetEnvironmentVariable("aServices");
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre05.ocl.
	 * </p>
	 */
	@Test
	public void testPre05() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre05.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * pre06.ocl.
	 * </p>
	 */
	@Test
	public void testPre06() {

		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/pre06.ocl");

		/* Select the model objects which shall be interpreted. */
		date = Date.now();
		modelObject = testPerformer.addModelObject(date);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any01.ocl.
	 * </p>
	 */
	@Test
	public void testAny01() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/any01.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any02.ocl.
	 * </p>
	 */
	@Test
	public void testAny02() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/any02.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any03.ocl.
	 * </p>
	 */
	@Test
	public void testAny03() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/any03.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.oclIsInvalid().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any04.ocl.
	 * </p>
	 */
	@Test
	public void testAny04() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/any04.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * any05.ocl.
	 * </p>
	 */
	@Test
	public void testAny05() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/any05.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean01.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean01() {

		Service service1;
		Service service2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean01.ocl");

		/* Create the model objects which shall be interpreted. */
		service1 = new Service();
		service2 = new Service();

		service1.setPointsEarned(100);
		service1.setPointsBurned(0);

		service2.setPointsEarned(100);
		service2.setPointsBurned(50);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(service1));
		objectList.add(testPerformer.addModelObject(service2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean02.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean02() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean02.ocl");

		/* Select the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean03.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean03() {

		Customer customer1;
		Customer customer2;
		Customer customer3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean03.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(15);
		customer2 = new Customer(55);
		customer3 = new Customer(99);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));
		objectList.add(testPerformer.addModelObject(customer3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean04.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean04() {

		Customer customer1;
		Customer customer2;

		CustomerCard card1;
		CustomerCard card2;
		CustomerCard card3;
		CustomerCard card4;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean04.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(11);
		customer2 = new Customer(11);

		card1 = new CustomerCard();
		card2 = new CustomerCard();
		card3 = new CustomerCard();
		card4 = new CustomerCard();

		customer1.addCard(card1);
		customer1.addCard(card2);
		customer1.addCard(card3);
		customer1.addCard(card4);

		testPerformer.addModelObject(card1);
		testPerformer.addModelObject(card2);
		testPerformer.addModelObject(card3);
		testPerformer.addModelObject(card4);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean05.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean05() {

		Customer customer1;
		Customer customer2;
		Customer customer3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean05.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer1.setTitle("Mr.");
		customer2 = new Customer(25);
		customer2.setTitle("Ms.");
		customer3 = new Customer(25);
		customer3.setTitle("Dr.");

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));
		objectList.add(testPerformer.addModelObject(customer3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * boolean06.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean06() {

		Customer customer1;
		Customer customer2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/boolean06.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer1.setName("Foobar");
		customer2 = new Customer(25);
		customer2.setName("Nobar");

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection01.ocl.
	 * </p>
	 */
	@Test
	public void testCollection01() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection01.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection02.ocl.
	 * </p>
	 */
	@Test
	public void testCollection02() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection02.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection03.ocl.
	 * </p>
	 */
	@Test
	public void testCollection03() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection03.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection04.ocl.
	 * </p>
	 */
	@Test
	public void testCollection04() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection04.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection05.ocl.
	 * </p>
	 */
	@Test
	public void testCollection05() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection05.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection06.ocl.
	 * </p>
	 */
	@Test
	public void testCollection06() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection06.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection07.ocl.
	 * </p>
	 */
	@Test
	public void testCollection07() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection07.ocl");

		/* Select the model objects which shall be interpreted. */
		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection08.ocl.
	 * </p>
	 */
	@Test
	public void testCollection08() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection08.ocl");

		/* Select the model objects which shall be interpreted. */
		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection09.ocl.
	 * </p>
	 */
	@Test
	public void testCollection09() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection09.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();
		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection10.ocl.
	 * </p>
	 */
	@Test
	public void testCollection10() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Customer customer;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection10.ocl");

		/* Select the model objects which shall be interpreted. */
		customer = new Customer(19);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram1.enroll(customer);

		loyaltyProgram2 = new LoyaltyProgram();

		testPerformer.addModelObject(customer);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection11.ocl.
	 * </p>
	 */
	@Test
	public void testCollection11() {

		Membership membership1;
		Membership membership2;

		LoyaltyAccount loyaltyAccount;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection11.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount = new LoyaltyAccount();

		membership1 = new Membership();
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();

		testPerformer.addModelObject(loyaltyAccount);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection12.ocl.
	 * </p>
	 */
	@Test
	public void testCollection12() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection12.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();
		modelObject = testPerformer.addModelObject(membership);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection13.ocl.
	 * </p>
	 */
	@Test
	public void testCollection13() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection13.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();
		modelObject = testPerformer.addModelObject(membership);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection14.ocl.
	 * </p>
	 */
	@Test
	public void testCollection14() {

		Membership membership;
		LoyaltyProgram loyaltyProgram;
		ServiceLevel serviceLevel;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection14.ocl");

		/* Select the model objects which shall be interpreted. */
		serviceLevel = new ServiceLevel();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addLevel(serviceLevel);

		membership = new Membership();
		membership.setCurrentLevel(serviceLevel);
		membership.setProgram(loyaltyProgram);

		testPerformer.addModelObject(serviceLevel);
		testPerformer.addModelObject(loyaltyProgram);

		modelObject = testPerformer.addModelObject(membership);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection15.ocl.
	 * </p>
	 */
	@Test
	public void testCollection15() {

		Membership membership1;
		Membership membership2;

		LoyaltyProgram loyaltyProgram;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection15.ocl");

		/* Select the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addLevel(serviceLevel1);

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);
		membership1.setProgram(loyaltyProgram);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);
		membership2.setProgram(loyaltyProgram);

		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(loyaltyProgram);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection16.ocl.
	 * </p>
	 */
	@Test
	public void testCollection16() {

		Membership membership1;
		Membership membership2;

		LoyaltyProgram loyaltyProgram;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection16.ocl");

		/* Select the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.addLevel(serviceLevel1);

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);
		membership1.setProgram(loyaltyProgram);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);
		membership2.setProgram(loyaltyProgram);

		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(loyaltyProgram);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection17.ocl.
	 * </p>
	 */
	@Test
	public void testCollection17() {

		Membership membership1;
		Membership membership2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection17.ocl");

		/* Select the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram1.addLevel(serviceLevel1);

		loyaltyProgram2 = new LoyaltyProgram();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);
		membership1.setProgram(loyaltyProgram1);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);
		membership2.setProgram(loyaltyProgram2);

		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(loyaltyProgram1);
		testPerformer.addModelObject(loyaltyProgram2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership1));
		objectList.add(testPerformer.addModelObject(membership2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection18.ocl.
	 * </p>
	 */
	@Test
	public void testCollection18() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection18.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection19.ocl.
	 * </p>
	 */
	@Test
	public void testCollection19() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection19.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection20.ocl.
	 * </p>
	 */
	@Test
	public void testCollection20() {

		LoyaltyProgram loyaltyProgram;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;
		ProgramPartner programPartner3;

		Service service1;
		Service service2;
		Service service3;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection20.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		programPartner1 = new ProgramPartner();
		loyaltyProgram.addPartner(programPartner1);

		programPartner2 = new ProgramPartner();
		loyaltyProgram.addPartner(programPartner2);

		programPartner3 = new ProgramPartner();

		service1 = new Service();
		service1.setPartner(programPartner1);

		service2 = new Service();
		service2.setPartner(programPartner2);

		service3 = new Service();
		service3.setPartner(programPartner3);

		serviceLevel1 = new ServiceLevel();
		serviceLevel1.setProgram(loyaltyProgram);
		serviceLevel1.addAvailableService(service1);
		serviceLevel1.addAvailableService(service2);

		serviceLevel2 = new ServiceLevel();
		serviceLevel2.setProgram(loyaltyProgram);
		serviceLevel2.addAvailableService(service1);
		serviceLevel2.addAvailableService(service2);
		serviceLevel2.addAvailableService(service3);

		serviceLevel3 = new ServiceLevel();
		serviceLevel3.setProgram(loyaltyProgram);
		serviceLevel3.addAvailableService(service3);

		testPerformer.addModelObject(loyaltyProgram);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(service1);
		testPerformer.addModelObject(service2);
		testPerformer.addModelObject(service3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(serviceLevel1));
		objectList.add(testPerformer.addModelObject(serviceLevel2));
		objectList.add(testPerformer.addModelObject(serviceLevel3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection21.ocl.
	 * </p>
	 */
	@Test
	public void testCollection21() {

		LoyaltyProgram loyaltyProgram;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;
		ProgramPartner programPartner3;

		Service service1;
		Service service2;
		Service service3;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection21.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram = new LoyaltyProgram();

		programPartner1 = new ProgramPartner();
		loyaltyProgram.addPartner(programPartner1);

		programPartner2 = new ProgramPartner();
		loyaltyProgram.addPartner(programPartner2);

		programPartner3 = new ProgramPartner();

		service1 = new Service();
		service1.setPartner(programPartner1);

		service2 = new Service();
		service2.setPartner(programPartner2);

		service3 = new Service();
		service3.setPartner(programPartner3);

		serviceLevel1 = new ServiceLevel();
		serviceLevel1.setProgram(loyaltyProgram);
		serviceLevel1.addAvailableService(service1);
		serviceLevel1.addAvailableService(service2);

		serviceLevel2 = new ServiceLevel();
		serviceLevel2.setProgram(loyaltyProgram);
		serviceLevel2.addAvailableService(service1);
		serviceLevel2.addAvailableService(service2);
		serviceLevel2.addAvailableService(service3);

		serviceLevel3 = new ServiceLevel();
		serviceLevel3.setProgram(loyaltyProgram);
		serviceLevel3.addAvailableService(service3);

		testPerformer.addModelObject(loyaltyProgram);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(service1);
		testPerformer.addModelObject(service2);
		testPerformer.addModelObject(service3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(serviceLevel1));
		objectList.add(testPerformer.addModelObject(serviceLevel2));
		objectList.add(testPerformer.addModelObject(serviceLevel3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection22.ocl.
	 * </p>
	 */
	@Test
	public void testCollection22() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection22.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection25.ocl.
	 * </p>
	 */
	@Test
	public void testCollection25() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection25.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection26.ocl.
	 * </p>
	 */
	@Test
	public void testCollection26() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection26.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection27.ocl.
	 * </p>
	 */
	@Test
	public void testCollection27() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection27.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection28.ocl.
	 * </p>
	 */
	@Test
	public void testCollection28() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection28.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection29.ocl.
	 * </p>
	 */
	@Test
	public void testCollection29() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection29.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection30.ocl.
	 * </p>
	 */
	@Test
	public void testCollection30() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection30.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection31.ocl.
	 * </p>
	 */
	@Test
	public void testCollection31() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection31.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection32.ocl.
	 * </p>
	 */
	@Test
	public void testCollection32() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection32.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * collection33.ocl.
	 * </p>
	 */
	@Test
	public void testCollection33() {

		Membership membership;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/collection33.ocl");

		/* Create the model objects which shall be interpreted. */
		membership = new Membership();

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(membership));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator01.ocl.
	 * </p>
	 */
	@Test
	public void testIterator01() {

		LoyaltyAccount loyaltyAccount;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator01.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership1);
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		service = new Service();
		programPartner = new ProgramPartner();

		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addProgram(loyaltyProgram1);
		programPartner.addDeliveredService(service);

		serviceLevel1.setProgram(loyaltyProgram1);
		serviceLevel1.addAvailableService(service);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram1.addPartner(programPartner);

		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(serviceLevel3);
		testPerformer.addModelObject(service);
		testPerformer.addModelObject(programPartner);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator02.ocl.
	 * </p>
	 */
	@Test
	public void testIterator02() {

		LoyaltyAccount loyaltyAccount;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator02.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership1);
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		service = new Service();
		programPartner = new ProgramPartner();

		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addProgram(loyaltyProgram1);
		programPartner.addDeliveredService(service);

		serviceLevel1.setProgram(loyaltyProgram1);
		serviceLevel1.addAvailableService(service);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram1.addPartner(programPartner);

		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(serviceLevel3);
		testPerformer.addModelObject(service);
		testPerformer.addModelObject(programPartner);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator03.ocl.
	 * </p>
	 */
	@Test
	public void testIterator03() {

		LoyaltyAccount loyaltyAccount;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator03.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership1);
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		service = new Service();
		programPartner = new ProgramPartner();

		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addProgram(loyaltyProgram1);
		programPartner.addDeliveredService(service);

		serviceLevel1.setProgram(loyaltyProgram1);
		serviceLevel1.addAvailableService(service);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram1.addPartner(programPartner);

		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(serviceLevel3);
		testPerformer.addModelObject(service);
		testPerformer.addModelObject(programPartner);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterate01.ocl.
	 * </p>
	 */
	@Test
	public void testIterate01() {

		LoyaltyAccount loyaltyAccount;
		LoyaltyProgram loyaltyProgram;

		Membership membership;
		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterate01.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		membership = new Membership();
		membership.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership);
		membership.addAccount(loyaltyAccount);

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.setMembership(membership);
		loyaltyProgram.addLevel(serviceLevel1);
		loyaltyProgram.addLevel(serviceLevel2);

		service = new Service();
		programPartner = new ProgramPartner();

		serviceLevel1.setProgram(loyaltyProgram);
		loyaltyProgram.addPartner(programPartner);
		programPartner.addProgram(loyaltyProgram);

		serviceLevel1.addAvailableService(service);
		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addDeliveredService(service);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership);
		testPerformer.addModelObject(loyaltyProgram);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(service);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(programPartner));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator04.ocl.
	 * </p>
	 */
	@Test
	public void testIterator04() {

		LoyaltyAccount loyaltyAccount;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator04.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership1);
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		service = new Service();
		programPartner = new ProgramPartner();

		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addProgram(loyaltyProgram1);
		programPartner.addDeliveredService(service);

		serviceLevel1.setProgram(loyaltyProgram1);
		serviceLevel1.addAvailableService(service);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram1.addPartner(programPartner);

		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(serviceLevel3);
		testPerformer.addModelObject(service);
		testPerformer.addModelObject(programPartner);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator05.ocl.
	 * </p>
	 */
	@Test
	public void testIterator05() {

		LoyaltyAccount loyaltyAccount;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;
		ServiceLevel serviceLevel3;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator05.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();
		serviceLevel3 = new ServiceLevel();

		membership1 = new Membership();
		membership1.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership1);
		membership1.addAccount(loyaltyAccount);

		membership2 = new Membership();
		membership2.setCurrentLevel(serviceLevel2);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		service = new Service();
		programPartner = new ProgramPartner();

		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addProgram(loyaltyProgram1);
		programPartner.addDeliveredService(service);

		serviceLevel1.setProgram(loyaltyProgram1);
		serviceLevel1.addAvailableService(service);

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram1.addLevel(serviceLevel1);
		loyaltyProgram1.addLevel(serviceLevel2);
		loyaltyProgram1.addPartner(programPartner);

		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram2.addLevel(serviceLevel1);
		loyaltyProgram2.addLevel(serviceLevel3);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(serviceLevel3);
		testPerformer.addModelObject(service);
		testPerformer.addModelObject(programPartner);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator06.ocl.
	 * </p>
	 */
	@Test
	public void testIterator06() {

		LoyaltyAccount loyaltyAccount;
		LoyaltyProgram loyaltyProgram;

		Membership membership;
		Service service;

		ServiceLevel serviceLevel1;
		ServiceLevel serviceLevel2;

		ProgramPartner programPartner;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator06.ocl");

		/* Create the model objects which shall be interpreted. */
		serviceLevel1 = new ServiceLevel();
		serviceLevel2 = new ServiceLevel();

		membership = new Membership();
		membership.setCurrentLevel(serviceLevel1);

		loyaltyAccount = new LoyaltyAccount();
		loyaltyAccount.setMembership(membership);
		membership.addAccount(loyaltyAccount);

		loyaltyProgram = new LoyaltyProgram();
		loyaltyProgram.setMembership(membership);
		loyaltyProgram.addLevel(serviceLevel1);
		loyaltyProgram.addLevel(serviceLevel2);

		service = new Service();
		programPartner = new ProgramPartner();

		serviceLevel1.setProgram(loyaltyProgram);
		loyaltyProgram.addPartner(programPartner);
		programPartner.addProgram(loyaltyProgram);

		serviceLevel1.addAvailableService(service);
		service.setLevel(serviceLevel1);
		service.setPartner(programPartner);

		programPartner.addDeliveredService(service);

		testPerformer.addModelObject(loyaltyAccount);
		testPerformer.addModelObject(membership);
		testPerformer.addModelObject(loyaltyProgram);
		testPerformer.addModelObject(serviceLevel1);
		testPerformer.addModelObject(serviceLevel2);
		testPerformer.addModelObject(service);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(programPartner));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator07.ocl.
	 * </p>
	 */
	@Test
	public void testIterator07() {

		CustomerCard customerCard1;
		CustomerCard customerCard2;

		Transaction transaction1;
		Transaction transaction2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator07.ocl");

		/* Create the model objects which shall be interpreted. */
		customerCard1 = new CustomerCard();
		customerCard2 = new CustomerCard();

		transaction1 = new Transaction();
		transaction2 = new Transaction();

		transaction1.setPoints(100);
		transaction2.setPoints(200);

		customerCard1.addTransaction(transaction1);
		customerCard2.addTransaction(transaction1);
		customerCard2.addTransaction(transaction2);

		testPerformer.addModelObject(customerCard1);
		testPerformer.addModelObject(customerCard2);
		testPerformer.addModelObject(transaction1);
		testPerformer.addModelObject(transaction2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customerCard1));
		objectList.add(testPerformer.addModelObject(customerCard2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator08.ocl.
	 * </p>
	 */
	@Test
	public void testIterator08() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator08.ocl");

		/* Create the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer2 = new Customer(25);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1.addProgram(loyaltyProgram1);
		customer2.addProgram(loyaltyProgram2);

		membership1 = new Membership();
		membership2 = new Membership();

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram2.setMembership(membership2);

		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount2 = new LoyaltyAccount();

		membership1.addAccount(loyaltyAccount1);
		membership2.addAccount(loyaltyAccount1);
		membership2.addAccount(loyaltyAccount2);

		loyaltyAccount1.setPoints(0);
		loyaltyAccount2.setPoints(100);

		testPerformer.addModelObject(loyaltyProgram1);
		testPerformer.addModelObject(loyaltyProgram2);
		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(loyaltyAccount1);
		testPerformer.addModelObject(loyaltyAccount2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator09.ocl.
	 * </p>
	 */
	@Test
	public void testIterator09() {

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		Membership membership1;
		Membership membership2;

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator09.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		membership1 = new Membership();
		membership2 = new Membership();

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram2.setMembership(membership2);

		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount2 = new LoyaltyAccount();

		membership1.addAccount(loyaltyAccount1);
		membership1.addAccount(loyaltyAccount2);
		membership2.addAccount(loyaltyAccount2);

		loyaltyAccount1.setNumber(9999);
		loyaltyAccount2.setNumber(10000);

		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(loyaltyAccount1);
		testPerformer.addModelObject(loyaltyAccount2);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertTrue(result_Boolean.oclIsUndefined().isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator10.ocl.
	 * </p>
	 */
	@Test
	public void testIterator10() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator10.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1 = new Customer(27);
		customer1.setName("Testman");

		customer2 = new Customer(95);
		customer2.setName("Testman");

		loyaltyProgram1.enroll(customer1);
		loyaltyProgram2.enroll(customer1);
		loyaltyProgram2.enroll(customer2);

		testPerformer.addModelObject(customer1);
		testPerformer.addModelObject(customer2);
		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator11.ocl.
	 * </p>
	 */
	@Test
	public void testIterator11() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator11.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1 = new Customer(27);
		customer1.setName("Testman");

		customer2 = new Customer(95);
		customer2.setName("Testman");

		loyaltyProgram1.enroll(customer1);
		loyaltyProgram2.enroll(customer1);
		loyaltyProgram2.enroll(customer2);

		testPerformer.addModelObject(customer1);
		testPerformer.addModelObject(customer2);
		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator12.ocl.
	 * </p>
	 */
	@Test
	public void testIterator12() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator12.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1 = new Customer(27);
		customer1.setName("Testman");

		customer2 = new Customer(95);
		customer2.setName("Testman");

		loyaltyProgram1.enroll(customer1);
		loyaltyProgram2.enroll(customer1);
		loyaltyProgram2.enroll(customer2);

		testPerformer.addModelObject(customer1);
		testPerformer.addModelObject(customer2);
		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator13.ocl.
	 * </p>
	 */
	@Test
	public void testIterator13() {

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;
		LoyaltyAccount loyaltyAccount3;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;
		LoyaltyProgram loyaltyProgram3;

		Membership membership1;
		Membership membership2;
		Membership membership3;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator13.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();
		loyaltyProgram3 = new LoyaltyProgram();

		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount1.setNumber(9998);
		loyaltyAccount2 = new LoyaltyAccount();
		loyaltyAccount2.setNumber(9999);
		loyaltyAccount3 = new LoyaltyAccount();
		loyaltyAccount3.setNumber(10000);

		membership1 = new Membership();
		membership2 = new Membership();
		membership3 = new Membership();

		loyaltyProgram1.setMembership(membership1);
		loyaltyProgram2.setMembership(membership2);
		loyaltyProgram3.setMembership(membership3);

		membership2.addAccount(loyaltyAccount1);
		membership3.addAccount(loyaltyAccount1);
		membership3.addAccount(loyaltyAccount2);
		membership3.addAccount(loyaltyAccount3);

		testPerformer.addModelObject(loyaltyAccount1);
		testPerformer.addModelObject(loyaltyAccount2);
		testPerformer.addModelObject(loyaltyAccount3);

		testPerformer.addModelObject(membership1);
		testPerformer.addModelObject(membership2);
		testPerformer.addModelObject(membership3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyProgram1));
		objectList.add(testPerformer.addModelObject(loyaltyProgram2));
		objectList.add(testPerformer.addModelObject(loyaltyProgram3));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(2);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator14.ocl.
	 * </p>
	 */
	@Test
	public void testIterator14() {

		Transaction transaction1;
		Transaction transaction2;
		Transaction transaction3;

		LoyaltyAccount loyaltyAccount1;
		LoyaltyAccount loyaltyAccount2;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator14.ocl");

		/* Create the model objects which shall be interpreted. */
		loyaltyAccount1 = new LoyaltyAccount();
		loyaltyAccount2 = new LoyaltyAccount();

		transaction1 = new Transaction();
		transaction2 = new Transaction();
		transaction3 = new Transaction();

		transaction1.setPoints(400);
		transaction2.setPoints(500);
		transaction3.setPoints(600);

		loyaltyAccount1.addTransaction(transaction1);
		loyaltyAccount1.addTransaction(transaction3);
		loyaltyAccount2.addTransaction(transaction1);
		loyaltyAccount2.addTransaction(transaction2);
		loyaltyAccount2.addTransaction(transaction3);

		testPerformer.addModelObject(loyaltyAccount1);
		testPerformer.addModelObject(loyaltyAccount2);
		testPerformer.addModelObject(transaction1);
		testPerformer.addModelObject(transaction2);
		testPerformer.addModelObject(transaction3);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(loyaltyAccount1));
		objectList.add(testPerformer.addModelObject(loyaltyAccount2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator15.ocl.
	 * </p>
	 */
	@Test
	public void testIterator15() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator15.ocl");

		/* Select the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer2 = new Customer(25);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1.addProgram(loyaltyProgram1);
		customer2.addProgram(loyaltyProgram2);

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		loyaltyProgram2.addPartner(programPartner1);
		loyaltyProgram2.addPartner(programPartner2);

		service = new Service();

		programPartner2.addDeliveredService(service);

		testPerformer.addModelObject(loyaltyProgram1);
		testPerformer.addModelObject(loyaltyProgram2);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner2);
		testPerformer.addModelObject(service);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * iterator16.ocl.
	 * </p>
	 */
	@Test
	public void testIterator16() {

		Customer customer1;
		Customer customer2;

		LoyaltyProgram loyaltyProgram1;
		LoyaltyProgram loyaltyProgram2;

		ProgramPartner programPartner1;
		ProgramPartner programPartner2;

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/iterator16.ocl");

		/* Select the model objects which shall be interpreted. */
		customer1 = new Customer(25);
		customer2 = new Customer(25);

		loyaltyProgram1 = new LoyaltyProgram();
		loyaltyProgram2 = new LoyaltyProgram();

		customer1.addProgram(loyaltyProgram1);
		customer2.addProgram(loyaltyProgram2);

		programPartner1 = new ProgramPartner();
		programPartner2 = new ProgramPartner();

		loyaltyProgram2.addPartner(programPartner1);
		loyaltyProgram2.addPartner(programPartner2);

		service = new Service();

		programPartner2.addDeliveredService(service);

		testPerformer.addModelObject(loyaltyProgram1);
		testPerformer.addModelObject(loyaltyProgram2);
		testPerformer.addModelObject(programPartner1);
		testPerformer.addModelObject(programPartner2);
		testPerformer.addModelObject(service);

		objectList = new ArrayList<IModelInstanceElement>();
		objectList.add(testPerformer.addModelObject(customer1));
		objectList.add(testPerformer.addModelObject(customer2));

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(objectList);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertFalse(result_Boolean.isTrue());

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(1);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric01.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric01() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric01.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric02.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric02() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric02.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric03.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric03() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric03.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric04.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric04() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric04.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric05.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric05() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric05.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric06.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric06() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric06.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric07.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric07() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric07.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric08.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric08() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric08.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric09.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric09() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric09.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric10.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric10() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric10.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * numeric11.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric11() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/numeric11.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static01.ocl.
	 * </p>
	 */
	@Test
	public void testStatic01() {

		Transaction transaction;
		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/static01.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();
		date = new Date(2008, 1, 1);

		transaction.setDate(date);

		testPerformer.addModelObject(date);
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static02.ocl.
	 * </p>
	 */
	@Test
	public void testStatic02() {

		Transaction transaction;
		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/static02.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();
		date = new Date(2008, 1, 1);

		transaction.setDate(date);

		testPerformer.addModelObject(date);
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static03.ocl.
	 * </p>
	 */
	@Test
	public void testStatic03() {

		Transaction transaction;
		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/static03.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();
		date = new Date(2008, 1, 1);

		transaction.setDate(date);

		testPerformer.addModelObject(date);
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained
	 * static04.ocl.
	 * </p>
	 */
	@Test
	public void testStatic04() {

		Transaction transaction;
		Date date;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/static04.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();
		date = new Date(2008, 1, 1);

		transaction.setDate(date);

		testPerformer.addModelObject(date);
		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string01.ocl.
	 * </p>
	 */
	@Test
	public void testString01() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string01.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string02.ocl.
	 * </p>
	 */
	@Test
	public void testString02() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string02.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string03.ocl.
	 * </p>
	 */
	@Test
	public void testString03() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string03.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string04.ocl.
	 * </p>
	 */
	@Test
	public void testString04() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string04.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string05.ocl.
	 * </p>
	 */
	@Test
	public void testString05() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string05.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * string06.ocl.
	 * </p>
	 */
	@Test
	public void testString06() {

		Service service;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/string06.ocl");

		/* Create the model objects which shall be interpreted. */
		service = new Service();

		modelObject = testPerformer.addModelObject(service);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * tuple01.ocl.
	 * </p>
	 */
	@Test
	public void testTuple01() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/tuple01.ocl");

		/* Select the model objects which shall be interpreted. */
		transaction = new Transaction();

		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * tuple02.ocl.
	 * </p>
	 */
	@Test
	public void testTuple02() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/tuple02.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();

		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * tuple03.ocl.
	 * </p>
	 */
	@Test
	public void testTuple03() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/tuple03.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();

		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}

	/**
	 * <p>
	 * A test case testing the interpretation of a {@link Constraint} contained in
	 * tuple04.ocl.
	 * </p>
	 */
	@Test
	public void testTuple04() {

		Transaction transaction;

		/* Load OCL file. */
		testPerformer.loadOCLFile("constraints/test/tuple04.ocl");

		/* Create the model objects which shall be interpreted. */
		transaction = new Transaction();

		modelObject = testPerformer.addModelObject(transaction);

		/* Interpret the selected combinations. */
		results = testPerformer.interpretRemainingConstraints(modelObject);

		/* Compare with expected results. */
		result_Boolean = (OclBoolean) results.get(0);
		assertFalse(result_Boolean.oclIsUndefined().isTrue());
		assertTrue(result_Boolean.isTrue());
	}
}
