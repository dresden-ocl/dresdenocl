/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides test cases to test the {@link PrimitiveType}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestOperation {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			MetaModelTestPlugin.getLogger(TestOperation.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class4;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation operation1;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation operation2;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation outputParameterOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation staticOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation multipleOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation orderedMultipleOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation unorderedMultipleOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation uniqueMultipleOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation nonuniqueMultipleOperation;

	/**
	 * <p>
	 * Loads some elements from the current {@link IModel} under test required for
	 * the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		class1 =
				MetaModelTestServices.getInstance().getTypeUnderTest(
						MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS1);

		class4 =
				MetaModelTestServices
						.getInstance()
						.getTypeUnderTest(
								MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTOPERATIONANDPARAMETERCLASS);

		/* Find some operations in the class. */
		for (Operation anOperation : class4.getOwnedOperation()) {

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_OPERATIONWITHOUTPARAMETERS)) {
				operation1 = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_VOIDOPERATIONWITHPARAMETER)) {
				operation2 = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION)) {
				outputParameterOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_STATICOPERATION)) {
				staticOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_MULTIPLEOPERATION)) {
				multipleOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_ORDEREDMULTIPLEOPERATION)) {
				orderedMultipleOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_UNORDEREDMULTIPLEOPERATION)) {
				unorderedMultipleOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_UNIQUEMULTIPLEOPERATION)) {
				uniqueMultipleOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_NONUNIQUEMULTIPLEOPERATION)) {
				nonuniqueMultipleOperation = anOperation;
			}
			// no else.
		}
		// end for.

		if (outputParameterOperation == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
										MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION,
										"Operation.getOutputParameter()");

				LOGGER.warn(msg);
			}
		}
		// no else.

		if (staticOperation == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
										MetaModelTestServices.OPERATION_NAME_STATICOPERATION,
										"Operation.isStatic()");

				LOGGER.warn(msg);
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getGenericType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericType1() {

		String msg;

		msg = "The adaptation of Operation.getGenericType() seems to be wrong. ";
		msg = "The Operation " + operation1.getQualifiedName();
		msg += " should not return a generic Type.";

		/* The operation must not have a generic type. */
		assertNull(msg, operation1.getGenericType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getInputParameter()}.
	 * </p>
	 */
	@Test
	public void testGetInputParameter1() {

		String msg;

		msg = "The adaptation of Operation.getInputParameter() seems to be wrong.";

		/* The operation must have no input parameters. */
		assertEquals(msg, 0, operation1.getInputParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getInputParameter()}.
	 * </p>
	 */
	@Test
	public void testGetInputParameter2() {

		String msg;

		msg = "The adaptation of Operation.getInputParameter() seems to be wrong.";

		/* The operation must have no input parameters. */
		assertEquals(msg, 1, operation2.getInputParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of Operation.getName() seems to be wrong.";

		/* The operation must have been found by the given name. */
		assertEquals(msg,
				MetaModelTestServices.OPERATION_NAME_OPERATIONWITHOUTPARAMETERS,
				operation1.getName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOutputParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOutputParameter1() {

		String msg;

		msg = "The adaptation of Operation.getOutputParameter() seems to be wrong.";

		/* The operation must have no output parameters. */
		assertEquals(msg, 0, operation1.getOutputParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOutputParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOutputParameter2() {

		String msg;

		msg = "The adaptation of Operation.getOutputParameter() seems to be wrong.";

		/* The operation must have no output parameters. */
		assertEquals(msg, 0, operation2.getOutputParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOutputParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOutputParameter3() {

		/*
		 * Only execute this test, if the current IModel under test contains
		 * Operations with output parameters.
		 */
		if (outputParameterOperation != null) {
			String msg;

			msg =
					"The adaptation of Operation.getOutputParameter() seems to be wrong.";

			/* The operation must have one output parameters. */
			assertEquals(msg, 1, outputParameterOperation.getOutputParameter().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwnedParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedParameter1() {

		String msg;

		msg = "The adaptation of Operation.getOwnedParameter() seems to be wrong.";

		/* The operation must have one owned parameters. */
		assertEquals(msg, 1, operation1.getOwnedParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwnedParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedParameter2() {

		String msg;

		msg = "The adaptation of Operation.getOwnedParameter() seems to be wrong.";

		/*
		 * The operation must have one or two owned parameters depending on the
		 * decision, whether or not the return parameter is null or void.
		 */
		if (operation2.getReturnParameter() == null) {
			assertEquals(msg, 1, operation2.getOwnedParameter().size());
		}

		else {
			assertEquals(msg, 2, operation2.getOwnedParameter().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwnedParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedParameter3() {

		/*
		 * Only execute this test, if the current IModel under test contains
		 * Operations with output parameters.
		 */
		if (outputParameterOperation != null) {
			String msg;

			msg =
					"The adaptation of Operation.getOwnedParameter() seems to be wrong.";

			/* The operation must have one owned parameters. */
			assertEquals(msg, 1, outputParameterOperation.getOwnedParameter().size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwnedTypeParameter()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		String msg;

		msg =
				"The adaptation of Operation.getOwnedTypeParameter() seems to be wrong. ";
		msg += "The Operation " + operation1.getQualifiedName();
		msg += " should not have Type Parameters.";

		/* The operation must have no type parameters. */
		assertEquals(msg, 0, operation1.getOwnedTypeParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getReturnParameter()}.
	 * </p>
	 */
	@Test
	public void testGetReturnParameter1() {

		String msg;

		msg = "The adaptation of Operation.getReturnParameter() seems to be wrong.";

		assertNotNull(msg, operation1.getReturnParameter());

		/* The operation must have class1 as return parameter type. */
		assertEquals(msg, class1, operation1.getReturnParameter().getType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getReturnParameter()}.
	 * </p>
	 */
	@Test
	public void testGetReturnParameter2() {

		String msg;

		msg =
				"The adaptation of Operation.getReturnParameter() seems to be wrong. ";
		msg += "The return type must be either null or void.";

		/*
		 * The operation must not have a return parameter or a return parameter of
		 * void type.
		 */
		if (operation2.getReturnParameter() != null) {
			Type returnType;

			returnType = operation2.getReturnParameter().getType();

			/* If the type is set it must be void. */
			if (!returnType.getName().toLowerCase().equals("void")) {
				fail(msg);
			}
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of Operation.getOwner() seems to be wrong.";

		/* The operation must be owned by the type its belongs to. */
		assertEquals(msg, class4, operation1.getOwner());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getOwningType()}.
	 * </p>
	 */
	@Test
	public void testGetOwningType1() {

		String msg;

		msg = "The adaptation of Operation.getOwningType() seems to be wrong.";

		/* The operation must be owned by the type its belongs to. */
		assertEquals(msg, class4, operation1.getOwningType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of Operation.getQualifiedName() seems to be wrong.";

		/* The operation must have been found by the given name. */
		assertEquals(
				msg,
				MetaModelTestServices.OPERATION_QUALIFIED_NAME_OPERATIONWITHOUTPARAMETERS,
				operation1.getQualifiedName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName2() {

		String msg;

		msg = "The adaptation of Operation.getQualifiedName() seems to be wrong ";
		msg += "for Operations with input Parameters.";

		/* The operation must have been found by the given name. */
		assertEquals(
				msg,
				MetaModelTestServices.OPERATION_QUALIFIED_NAME_VOIDOPERATIONWITHPARAMETER,
				operation2.getQualifiedName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getSignatureParameter()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetSignatureParameter1() {

		String msg;

		msg =
				"The adaptation of Operation.getSignatureParameter() seems to be wrong.";

		/* The operation must not have any signature parameters. */
		assertEquals(msg, 0, operation1.getSignatureParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getSignatureParameter()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetSignatureParameter2() {

		String msg;

		msg =
				"The adaptation of Operation.getSignatureParameter() seems to be wrong.";

		/* The operation must have one signature parameters. */
		assertEquals(msg, 1, operation2.getSignatureParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getSignatureParameter()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetSignatureParameter3() {

		/*
		 * Only execute this test, if the current IModel under test contains
		 * Operations with output parameters.
		 */
		if (outputParameterOperation != null) {
			String msg;

			msg =
					"The adaptation of Operation.getSignatureParameter() seems to be wrong.";

			/* The operation must have one signature parameters. */
			assertEquals(msg, 1, outputParameterOperation.getSignatureParameter()
					.size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType1() {

		String msg;

		msg = "The adaptation of Operation.getType() seems to be wrong.";

		/* The operation must have the same type as in the model. */
		assertEquals(msg, class1, operation1.getType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType2() {

		String msg;

		msg = "The adaptation of Operation.getType() seems to be wrong.";

		/* The type of the operation must be null or void. */
		if (operation2.getType() != null) {
			assertEquals(msg, "void", operation2.getType().getName().toLowerCase());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple1() {

		String msg;

		msg = "The adaptation of Operation.isMultiple() seems to be wrong. ";
		msg += "Class3.operation1() should not be multiple.";

		/* The operation must not be multiple. */
		assertFalse(msg, operation1.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple2() {

		String msg;

		msg = "The adaptation of Operation.isMultiple() seems to be wrong.";

		/* The operation must be multiple. */
		assertTrue(msg, multipleOperation.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered1() {

		String msg;

		msg = "The adaptation of Operation.isMultiple() seems to be wrong. ";
		msg += "Operations with non-multiple results should have ordered results.";

		/* The operation must be ordered. */
		assertTrue(msg, operation1.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered2() {

		String msg;

		msg = "The adaptation of Operation.isMultiple() seems to be wrong. ";

		/* The operation must be ordered. */
		assertTrue(msg, orderedMultipleOperation.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered3() {

		String msg;

		msg = "The adaptation of Operation.isMultiple() seems to be wrong. ";

		/* The operation must not be ordered. */
		assertFalse(msg, unorderedMultipleOperation.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic1() {

		String msg;

		msg = "The adaptation of Operation.isStatic() seems to be wrong.";

		/* The operation must not be static. */
		assertFalse(msg, operation1.isStatic());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic2() {

		/*
		 * Only execute this test, if static Operations are in the current IModel
		 * under test.
		 */
		if (staticOperation != null) {
			String msg;

			msg = "The adaptation of Operation.isStatic() seems to be wrong.";

			/* The operation must not be static. */
			assertTrue(msg, staticOperation.isStatic());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique1() {

		String msg;

		msg = "The adaptation of Operation.isUnique() seems to be wrong. ";
		msg += "Operations with non-multiple results should have unique results.";

		/* The operation must be unique. */
		assertTrue(msg, operation1.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique2() {

		String msg;

		msg = "The adaptation of Operation.isUnique() seems to be wrong. ";

		/* The operation must be unique. */
		assertTrue(msg, uniqueMultipleOperation.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Operation#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique3() {

		String msg;

		msg = "The adaptation of Operation.isUnique() seems to be wrong. ";

		/* The operation must not be unique. */
		assertFalse(msg, nonuniqueMultipleOperation.isUnique());
	}
}