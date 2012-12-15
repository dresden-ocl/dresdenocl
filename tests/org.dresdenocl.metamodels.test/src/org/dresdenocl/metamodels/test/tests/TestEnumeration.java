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
package org.dresdenocl.metamodels.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.metamodels.test.MetaModelTestPlugin;
import org.dresdenocl.metamodels.test.MetaModelTestServices;
import org.dresdenocl.metamodels.test.msg.MetaModelTestSuiteMessages;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.model.IModel;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.GenericType;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeParameter;

/**
 * <p>
 * This class provides test cases to test the {@link PrimitiveType}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestEnumeration {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestEnumeration.class);

	/** The {@link Enumeration} under test. */
	private static Enumeration enumeration1;

	/** The {@link EnumerationLiteral}s of the {@link Enumeration} under test. */
	private static List<EnumerationLiteral> enumerationLiterals;

	/** The {@link Namespace} in which the {@link Enumeration} must be located. */
	private static Namespace package1;

	/**
	 * <p>
	 * Loads some elements from the current {@link IModel} under test required
	 * for the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		enumerationLiterals = null;
		package1 = null;

		enumeration1 = (Enumeration) MetaModelTestServices
				.getInstance()
				.getTypeUnderTest(
						MetaModelTestServices.ENUMERATION_QUALIFIED_NAME_ENUMERATION1);

		if (enumeration1 == null) {

			/* Probably send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg = MetaModelTestSuiteMessages.MetaModelTestSuite_EnumerationNotFoundInModel;
				msg += " "
						+ NLS.bind(
								MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
								MetaModelTestServices.getInstance()
										.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}

		else {
			enumerationLiterals = enumeration1.getOwnedLiteral();

			package1 = MetaModelTestServices
					.getInstance()
					.getNamespaceUnderTest(
							MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1);
		}
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Enumeration#getGenericSuperType()} .
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			List<GenericType> genericSuperTypes;

			String msg;

			msg = "The adaptation of Enumeration.getGenericSuperType() seems to be wrong.";
			msg += "An Enumeration should not have any generic super types.";

			genericSuperTypes = enumeration1.getGenericSuperType();

			/* Enumerations should not have generic super types. */
			assertEquals(msg, 0, genericSuperTypes.size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Enumeration#getGenericSuperType()} .
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			String msg = "The adaptation of Enumeration.getGenericSuperType() seems to be wrong.";
			msg += "Result should be an EList.";

			assertTrue(msg, enumeration1.getGenericSuperType() instanceof EList);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of Enumeration.getName() seems to be wrong.";

			/* The enumeration must have the same name as in the model. */
			assertEquals(msg,
					MetaModelTestServices.ENUMERATION_NAME_ENUMERATION1,
					enumeration1.getName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getNamespace()}.
	 * </p>
	 */
	@Test
	public void testGetNamespace1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of Enumeration.getNamespace() seems to be wrong.";

			/* The enumeration must be in the name space. */
			assertEquals(msg, package1, enumeration1.getNamespace());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedLiteral()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedLiteral1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			boolean isLiteralInEnum;

			String msg;

			msg = "The adaptation of Enumeration.getOwnedLiteral() seems to be wrong.";

			/* The enumeration must have two literals. */
			assertEquals(msg, 2, enumerationLiterals.size());

			/* The enumeration must have the literal 'EnumerationLiteral1'. */
			isLiteralInEnum = false;

			for (EnumerationLiteral aLiteral : enumerationLiterals) {
				if (aLiteral
						.getName()
						.equals(MetaModelTestServices.ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL1)) {
					isLiteralInEnum = true;
					break;
				}
			}

			assertTrue(msg, isLiteralInEnum);

			/* The enumeration must have the literal 'EnumerationLiteral2'. */
			isLiteralInEnum = false;

			for (EnumerationLiteral aLiteral : enumerationLiterals) {
				if (aLiteral
						.getName()
						.equals(MetaModelTestServices.ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL2)) {
					isLiteralInEnum = true;
					break;
				}
			}

			assertTrue(msg, isLiteralInEnum);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedLiteral()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedLiteral2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg = "The adaptation of Enumeration.getOwnedLiteral() seems to be wrong. Return type should be an EList.";
			assertTrue(msg, enumeration1.getOwnedLiteral() instanceof EList);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedOperation()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			List<Operation> operations;

			String msg;

			msg = "The adaptation of Enumeration.getOwnedOperation() seems to be wrong.";
			msg += "An Enumeration should not have any Operations.";

			operations = enumeration1.getOwnedOperation();

			/* Enumerations should not have operations. */
			assertEquals(msg, 0, operations.size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedOperation()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			String msg = "The adaptation of Enumeration.getOwnedOperation() seems to be wrong.";
			msg += "Return type should be an EList.";

			assertTrue(msg, enumeration1.getOwnedOperation() instanceof EList);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			List<Property> properties;

			String msg;

			msg = "The adaptation of Enumeration.getOwnedProperty() seems to be wrong.";
			msg += "An Enumeration should not have any Properties.";

			properties = enumeration1.getOwnedProperty();

			/* Enumerations should not have properties. */
			assertEquals(msg, 0, properties.size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			String msg = "The adaptation of Enumeration.getOwnedProperty() seems to be wrong.";
			msg += "Return type should be an EList.";

			assertTrue(msg, enumeration1.getOwnedProperty() instanceof EList);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Enumeration#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			List<TypeParameter> typeParameters;

			String msg;

			msg = "The adaptation of Enumeration.getOwnedTypeParameter() seems to be wrong.";
			msg += "An Enumeration should not have any Parameters.";

			typeParameters = enumeration1.getOwnedTypeParameter();

			/* Enumerations should not have type parameters. */
			assertEquals(msg, 0, typeParameters.size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Enumeration#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			String msg = "The adaptation of Enumeration.getOwnedTypeParameter() seems to be wrong.";
			msg += "Return type should be an EList.";

			assertTrue(msg,
					enumeration1.getOwnedTypeParameter() instanceof EList);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of Enumeration.getOwner() seems to be wrong.";

			/* The enumeration must be owned by the name space. */
			assertEquals(msg, package1, enumeration1.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of Enumeration.getQualifiedName() seems to be wrong.";

			/* The enumeration must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.ENUMERATION_QUALIFIED_NAME_ENUMERATION1,
					enumeration1.getQualifiedName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			List<Type> superTypes;

			String msg;

			msg = "The adaptation of Enumeration.getSuperType() seems to be wrong. ";
			msg += "An Enumeration should not have any super types.";

			superTypes = enumeration1.getSuperType();

			/* Enumerations should not have super types. */
			assertEquals(msg, 0, superTypes.size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Enumeration#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType2() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {
			String msg = "The adaptation of Enumeration.getSuperType() seems to be wrong. ";
			msg += "Result should be an EList.";

			assertTrue(msg, enumeration1.getSuperType() instanceof EList);
		}
		// no else.
	}
}