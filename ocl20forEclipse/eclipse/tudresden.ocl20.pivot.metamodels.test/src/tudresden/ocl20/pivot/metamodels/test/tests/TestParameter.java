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
import static org.junit.Assert.assertTrue;

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
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
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
public class TestParameter {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			MetaModelTestPlugin.getLogger(TestParameter.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class4;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation operation1;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation operation2;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation inputOutputParameterOperation;

	/** An {@link Operation} of the current {@link IModel} under test. */
	private static Operation outputParameterOperation;

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

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter inputParameter1;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter inputOutputParameter1;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter outputParameter1;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter returnParameter1;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter multipleParameter;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter orderedMultipleParameter;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter unorderedMultipleParameter;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter uniqueMultipleParameter;

	/** An {@link Parameter} of the current {@link IModel} under test. */
	private static Parameter nonuniqueMultipleParameter;

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
					MetaModelTestServices.OPERATION_NAME_INPUTOUTPUTPARAMETEROPERATION)) {
				inputOutputParameterOperation = anOperation;
			}
			// no else.

			if (anOperation.getName().equals(
					MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION)) {
				outputParameterOperation = anOperation;
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

		if (inputOutputParameterOperation == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
										MetaModelTestServices.OPERATION_NAME_INPUTOUTPUTPARAMETEROPERATION,
										"Parameter.getKind()");
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}

			inputOutputParameter1 = null;
		}

		else {
			inputOutputParameter1 =
					inputOutputParameterOperation.getInputParameter().get(0);
		}

		if (outputParameterOperation == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
										MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION,
										"Parameter.getKind()");
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}

			outputParameter1 = null;
		}

		else {
			outputParameter1 = outputParameterOperation.getOutputParameter().get(0);
		}

		inputParameter1 = operation2.getInputParameter().get(0);

		returnParameter1 = operation1.getReturnParameter();

		multipleParameter = multipleOperation.getReturnParameter();

		orderedMultipleParameter = orderedMultipleOperation.getReturnParameter();

		unorderedMultipleParameter =
				unorderedMultipleOperation.getReturnParameter();

		uniqueMultipleParameter = uniqueMultipleOperation.getReturnParameter();

		nonuniqueMultipleParameter =
				nonuniqueMultipleOperation.getReturnParameter();
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind1() {

		String msg;

		msg = "The adaptation of Parameter.getKind() seems to be wrong.";

		/* The parameter must have the same kind as in the model. */
		assertEquals(msg, ParameterDirectionKind.IN, inputParameter1.getKind());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind2() {

		/*
		 * Only execute this test case if the current IModel under test contains
		 * output parameters.
		 */
		if (outputParameter1 != null) {
			String msg;

			msg = "The adaptation of Parameter.getKind() seems to be wrong.";

			/* The parameter must have the same kind as in the model. */
			assertEquals(msg, ParameterDirectionKind.OUT, outputParameter1.getKind());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind3() {

		String msg;

		msg = "The adaptation of Parameter.getKind() seems to be wrong.";

		/* The parameter must have the same kind as in the model. */
		assertEquals(msg, ParameterDirectionKind.RETURN, returnParameter1.getKind());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind4() {

		/*
		 * Only execute this test case if the current IModel under test contains
		 * input/output parameters.
		 */
		if (inputOutputParameter1 != null) {
			String msg;

			msg = "The adaptation of Parameter.getKind() seems to be wrong.";

			/* The parameter must have the same kind as in the model. */
			assertEquals(msg, ParameterDirectionKind.INOUT, inputOutputParameter1
					.getKind());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of Parameter.getName() seems to be wrong.";

		/* The parameter must have the same name as in the model. */
		assertEquals(msg, MetaModelTestServices.PARAMETER_NAME_INPUTPARAMETER1,
				inputParameter1.getName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOperation1() {

		String msg;

		msg = "The adaptation of Parameter.getOperation() seems to be wrong.";

		/* The parameter must be owned by its Operation. */
		assertEquals(msg, operation2, inputParameter1.getOperation());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of Parameter.getOwner() seems to be wrong.";

		/* The parameter must be owned by its Operation. */
		assertEquals(msg, operation2, inputParameter1.getOwner());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of Parameter.getQualifiedName() seems to be wrong.";

		/* The parameter must have the same name as in the model. */
		assertEquals(msg,
				MetaModelTestServices.PARAMETER_QUALIFIED_NAME_INPUTPARAMETER1,
				inputParameter1.getQualifiedName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType1() {

		String msg;

		msg = "The adaptation of Parameter.getType() seems to be wrong.";

		/* The parameter must have the same type as in the model. */
		assertEquals(msg, class1, returnParameter1.getType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getName()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple1() {

		String msg;

		msg = "The adaptation of Parameter.isMultiple() seems to be wrong.";

		/* The parameter must not be multiple. */
		assertFalse(msg, returnParameter1.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getName()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple2() {

		String msg;

		msg = "The adaptation of Parameter.isMultiple() seems to be wrong.";

		/* The parameter must be multiple. */
		assertTrue(msg, multipleParameter.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered1() {

		String msg;

		msg = "The adaptation of Parameter.isOrdered() seems to be wrong. ";
		msg += "Non-multiple parameters should be ordered.";

		/* The parameter must be ordered. */
		assertTrue(msg, returnParameter1.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered2() {

		String msg;

		msg = "The adaptation of Parameter.isOrdered() seems to be wrong. ";

		/* The parameter must be ordered. */
		assertTrue(msg, orderedMultipleParameter.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered3() {

		String msg;

		msg = "The adaptation of Parameter.isOrdered() seems to be wrong. ";

		/* The parameter must not be ordered. */
		assertFalse(msg, unorderedMultipleParameter.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique1() {

		String msg;

		msg = "The adaptation of Parameter.isUnique() seems to be wrong. ";
		msg += "Non-multiple Parameters should be unique.";

		/* The parameter must be unique. */
		assertTrue(msg, returnParameter1.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique2() {

		String msg;

		msg = "The adaptation of Parameter.isUnique() seems to be wrong. ";

		/* The parameter must be unique. */
		assertTrue(msg, uniqueMultipleParameter.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique3() {

		String msg;

		msg = "The adaptation of Parameter.isUnique() seems to be wrong. ";

		/* The parameter must not be unique. */
		assertFalse(msg, nonuniqueMultipleParameter.isUnique());
	}
}