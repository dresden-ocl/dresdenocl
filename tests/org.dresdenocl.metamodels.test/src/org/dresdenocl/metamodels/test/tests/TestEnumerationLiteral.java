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

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;

/**
 * <p>
 * This class provides test cases to test the {@link PrimitiveType}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestEnumerationLiteral {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestEnumerationLiteral.class);

	/** The {@link Enumeration} under test. */
	private static Enumeration enumeration1;

	/** An {@link EnumerationLiteral}s of the {@link Enumeration} under test. */
	private static EnumerationLiteral enumerationLiteral1;

	/**
	 * <p>
	 * Loads some elements from the current {@link IModel} under test required
	 * for the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		enumerationLiteral1 = null;

		enumeration1 = (Enumeration) MetaModelTestServices
				.getInstance()
				.getTypeUnderTest(
						MetaModelTestServices.ENUMERATION_QUALIFIED_NAME_ENUMERATION1);

		if (enumeration1 == null) {

			/* Eventually send a warning to the logger. */
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
			enumerationLiteral1 = enumeration1
					.lookupLiteral(MetaModelTestServices.ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL1);
		}
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link EnumerationLiteral#getEnumeration()}.
	 * </p>
	 */
	@Test
	public void testGetEnumeration1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of EnumerationLiteral.getEnumeration() seems to be wrong.";

			/* The enumeration literal must be a literal of the enumeration. */
			assertEquals(msg, enumeration1,
					enumerationLiteral1.getEnumeration());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link EnumerationLiteral#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of EnumerationLiteral.getName() seems to be wrong.";

			/* The enumeration literal must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.ENUMERATIONLITERAL_NAME_ENUMERATIONLITERAL1,
					enumerationLiteral1.getName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link EnumerationLiteral#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of EnumerationLiteral.getOwner() seems to be wrong.";
			msg += "An EnumerationLiteral must be owned by its Enumeration.";

			/* The enumeration literal must be owned by the enumeration. */
			assertEquals(msg, enumeration1, enumerationLiteral1.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link EnumerationLiteral#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		/* Only execute this test case for meta models with enumeration support. */
		if (enumeration1 != null) {

			String msg;

			msg = "The adaptation of EnumerationLiteral.getQualifiedName() seems to be wrong.";

			/* The enumeration literal must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.ENUMERATIONLITERAL_QUALIFIED_NAME_ENUMERATIONLITERAL1,
					enumerationLiteral1.getQualifiedName());
		}
		// no else.
	}
}