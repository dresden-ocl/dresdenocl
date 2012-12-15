/*
 * Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de) This file is part of
 * the Meta Model Architecture of Dresden OCL. Dresden OCL
 * is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.test.tests;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
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
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestParameter.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type testTypeClass1;

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
	 * Loads some elements from the current {@link IModel} under test required
	 * for the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		if (!MetaModelTestServices.supportsNoOperations) {
			testTypeClass1 = MetaModelTestServices
					.getInstance()
					.getTypeUnderTest(
							MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS1);

			class4 = MetaModelTestServices
					.getInstance()
					.getTypeUnderTest(
							MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTOPERATIONANDPARAMETERCLASS);

			/* Find some operations in the class. */
			for (Operation anOperation : class4.getOwnedOperation()) {

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_OPERATIONWITHOUTPARAMETERS)) {
					operation1 = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_VOIDOPERATIONWITHPARAMETER)) {
					operation2 = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_INPUTOUTPUTPARAMETEROPERATION)) {
					inputOutputParameterOperation = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION)) {
					outputParameterOperation = anOperation;
				}
				// no else.

				if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_MULTIPLEOPERATION)) {
					multipleOperation = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_ORDEREDMULTIPLEOPERATION)) {
					orderedMultipleOperation = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_UNORDEREDMULTIPLEOPERATION)) {
					unorderedMultipleOperation = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_UNIQUEMULTIPLEOPERATION)) {
					uniqueMultipleOperation = anOperation;
				}
				// no else.

				if (anOperation
						.getName()
						.equals(MetaModelTestServices.OPERATION_NAME_NONUNIQUEMULTIPLEOPERATION)) {
					nonuniqueMultipleOperation = anOperation;
				}
				// no else.
			}
			// end for.

			if (inputOutputParameterOperation == null) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg = NLS
							.bind(MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
									MetaModelTestServices.OPERATION_NAME_INPUTOUTPUTPARAMETEROPERATION,
									"Parameter.getKind()");
					msg += " "
							+ NLS.bind(
									MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
									MetaModelTestServices.getInstance()
											.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}

				inputOutputParameter1 = null;
			}

			else {
				inputOutputParameter1 = inputOutputParameterOperation
						.getInputParameter().get(0);
			}

			if (outputParameterOperation == null) {

				/* Eventually send a warning to the logger. */
				if (LOGGER.isInfoEnabled()) {
					String msg;

					msg = NLS
							.bind(MetaModelTestSuiteMessages.MetaModelTestSuite_OperationNotFoundInModel,
									MetaModelTestServices.OPERATION_NAME_OUTPUTPARAMETEROPERATION,
									"Parameter.getKind()");
					msg += " "
							+ NLS.bind(
									MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
									MetaModelTestServices.getInstance()
											.getMetaModelUnderTestID());

					LOGGER.warn(msg);
				}

				outputParameter1 = null;
			}

			else {
				outputParameter1 = outputParameterOperation
						.getOutputParameter().get(0);
			}

			inputParameter1 = operation2.getInputParameter().get(0);

			returnParameter1 = operation1.getReturnParameter();

			if (multipleOperation != null) {
				multipleParameter = multipleOperation.getReturnParameter();
			}
			// no else.

			orderedMultipleParameter = orderedMultipleOperation
					.getReturnParameter();

			if (unorderedMultipleOperation != null) {
				unorderedMultipleParameter = unorderedMultipleOperation
						.getReturnParameter();
			}
			// no else.

			uniqueMultipleParameter = uniqueMultipleOperation
					.getReturnParameter();

			if (nonuniqueMultipleParameter != null) {
				nonuniqueMultipleParameter = nonuniqueMultipleOperation
						.getReturnParameter();
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getKind()}.
	 * </p>
	 */
	@Test
	public void testGetKind1() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getKind() seems to be wrong.";

			/* The parameter must have the same kind as in the model. */
			assertEquals(msg, ParameterDirectionKind.IN,
					inputParameter1.getKind());
		}
		// no else.
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
			assertEquals(msg, ParameterDirectionKind.OUT,
					outputParameter1.getKind());
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

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getKind() seems to be wrong.";

			/* The parameter must have the same kind as in the model. */
			assertEquals(msg, ParameterDirectionKind.RETURN,
					returnParameter1.getKind());
		}
		// no else.
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
			assertEquals(msg, ParameterDirectionKind.INOUT,
					inputOutputParameter1.getKind());
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

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getName() seems to be wrong.";

			/* The parameter must have the same name as in the model. */
			assertEquals(msg,
					MetaModelTestServices.PARAMETER_NAME_INPUTPARAMETER1,
					inputParameter1.getName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOperation1() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getOperation() seems to be wrong.";

			/* The parameter must be owned by its Operation. */
			assertEquals(msg, operation2, inputParameter1.getOperation());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getOwner() seems to be wrong.";

			/* The parameter must be owned by its Operation. */
			assertEquals(msg, operation2, inputParameter1.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getQualifiedName() seems to be wrong.";

			/* The parameter must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.PARAMETER_QUALIFIED_NAME_INPUTPARAMETER1,
					inputParameter1.getQualifiedName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType01() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			/* The parameter must have the same type as in the model. */
			assertEquals(msg, testTypeClass1, returnParameter1.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType02() {

		if (multipleParameter != null) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			/* The parameter must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getCollectionType(testTypeClass1),
					multipleParameter.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType03() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getSequenceType(testTypeClass1),
					orderedMultipleParameter.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType04() {

		if (unorderedMultipleOperation != null) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getSetType(testTypeClass1),
					unorderedMultipleParameter.getType());
		}

		else {
			LOGGER.warn("Operation 'unorderedMultipleOperation' was not found. Probably, type adaptation of Parameters has not been tested completely.");
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType05() {

		if (!MetaModelTestServices.supportsNoOperations) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getSetType(testTypeClass1),
					uniqueMultipleParameter.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Parameter#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType06() {

		if (nonuniqueMultipleParameter != null) {
			String msg;

			msg = "The adaptation of Parameter.getType() seems to be wrong.";

			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(testTypeClass1),
					nonuniqueMultipleParameter.getType());
		}

		else {
			LOGGER.warn("Operation 'nonuniqueMultipleParameter' was not found. Probably, type adaptation of Parameters has not been tested completely.");
		}
	}
}