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
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
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
public class TestProperty {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			MetaModelTestPlugin.getLogger(TestProperty.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class3;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property associationEnd1;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property staticAssociationEnd;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property orderedMultipleAssociationEnd;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property unorderedMultipleAssociationEnd;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property uniqueMultipleAssociationEnd;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property nonuniqueMultipleAssociationEnd;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property nonmultipleProperty;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property staticProperty;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property orderedMultipleProperty;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property unorderedMultipleProperty;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property uniqueMultipleProperty;

	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property nonuniqueMultipleProperty;

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

		class3 =
				MetaModelTestServices.getInstance().getTypeUnderTest(
						MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS);

		/* Find some properties in the class. */
		for (Property aProperty : class3.getOwnedProperty()) {

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEPROPERTY)) {
				nonmultipleProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_STATICPROPERTY)) {
				staticProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_ORDEREDMULTIPLEPROPERTY)) {
				orderedMultipleProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_UNORDEREDMULTIPLEPROPERTY)) {
				unorderedMultipleProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_UNIQUEMULTIPLEPROPERTY)) {
				uniqueMultipleProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_NONUNIQUEMULTIPLEPROPERTY)) {
				nonuniqueMultipleProperty = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEASSOCIATONEND)) {
				associationEnd1 = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_STATICASSOCIATONEND)) {
				staticAssociationEnd = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_ORDEREDMULTIPLEASSOCIATONEND)) {
				orderedMultipleAssociationEnd = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_UNORDEREDMULTIPLEASSOCIATONEND)) {
				unorderedMultipleAssociationEnd = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_UNIQUEMULTIPLEASSOCIATONEND)) {
				uniqueMultipleAssociationEnd = aProperty;
			}
			// no else.

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_NONUNIQUEMULTIPLEASSOCIATONEND)) {
				nonuniqueMultipleAssociationEnd = aProperty;
			}
			// no else.
		}

		if (associationEnd1 == null || orderedMultipleAssociationEnd == null
				|| unorderedMultipleAssociationEnd == null
				|| uniqueMultipleAssociationEnd == null
				|| nonuniqueMultipleAssociationEnd == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						MetaModelTestSuiteMessages.MetaModelTestSuite_AssociationEndNotFound;
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}
		// no else.

		if (staticProperty == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg =
						NLS
								.bind(
										MetaModelTestSuiteMessages.MetaModelTestSuite_PropertyNotFoundInModel,
										MetaModelTestServices.PROPERTY_NAME_STATICPROPERTY,
										"Property.isStatic()");
				msg +=
						" "
								+ NLS
										.bind(
												MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
												MetaModelTestServices.getInstance()
														.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of Property.getName() seems to be wrong.";

		/* The property must have been found by the given name. */
		assertEquals(msg, MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEPROPERTY,
				nonmultipleProperty.getName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.getName() seems to be wrong for Associations.";

			/* The property must have been found by the given name. */
			assertEquals(msg,
					MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEASSOCIATONEND,
					associationEnd1.getName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of Property.getOwner() seems to be wrong. ";
		msg += "A Property must be owned by the Type to which it belongs.";

		/* The property must be owned by the same type as in the model. */
		assertEquals(msg, class3, nonmultipleProperty.getOwner());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.getOwner() seems to be wrong for Associations. ";
			msg += "A Property must be owned by the Type to which it belongs.";

			/* The property must be owned by the same type as in the model. */
			assertEquals(msg, class3, associationEnd1.getOwner());
			// no else.
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getOwningType()}.
	 * </p>
	 */
	@Test
	public void testGetOwningType1() {

		String msg;

		msg = "The adaptation of Property.getOwningType() seems to be wrong.";

		/* The property must be owned by the same type as in the model. */
		assertEquals(msg, class3, nonmultipleProperty.getOwningType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getOwningType()}.
	 * </p>
	 */
	@Test
	public void testGetOwningType2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.getOwningType() seems to be wrong for Associations.";

			/* The property must be owned by the same type as in the model. */
			assertEquals(msg, class3, associationEnd1.getOwningType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType1() {

		String msg;

		msg = "The adaptation of Property.getType() seems to be wrong ";
		msg += " or the Type of Class3.property1 is not set in the model.";

		/* The property must have a type. */
		assertNotNull(msg, nonmultipleProperty.getType());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong ";
			msg += " or the Type of Class3.associationEnd1 is not set in the model.";

			/* The property must have the same type as in the model. */
			assertEquals(class1, associationEnd1.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of Property.getQualifiedName() seems to be wrong.";

		/* The property must have the same name as in the model. */
		assertEquals(msg,
				MetaModelTestServices.PROPERTY_QUALIFIED_NAME_NONMULTIPLEPROPERTY,
				nonmultipleProperty.getQualifiedName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.getQualifiedName() seems to be wrong for Associations.";

			/* The property must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.PROPERTY_QUALIFIED_NAME_NONMULTIPLEASSOCIATIONEND,
					associationEnd1.getQualifiedName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple1() {

		String msg;

		msg = "The adaptation of Property.isMultiple() seems to be wrong.";

		/* The property must not be multiple. */
		assertFalse(msg, nonmultipleProperty.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.isMultiple() seems to be wrong for Associations.";

			/* The property must not be multiple. */
			assertFalse(msg, associationEnd1.isMultiple());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple3() {

		String msg;

		msg = "The adaptation of Property.isMultiple() seems to be wrong.";

		/* The property must be multiple. */
		assertTrue(msg, orderedMultipleProperty.isMultiple());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isMultiple()}.
	 * </p>
	 */
	@Test
	public void testIsMultiple4() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (orderedMultipleAssociationEnd != null) {

			String msg;

			msg =
					"The adaptation of Property.isMultiple() seems to be wrong for Associations.";

			/* The property must not be multiple. */
			assertTrue(msg, orderedMultipleAssociationEnd.isMultiple());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered1() {

		String msg;

		msg = "The adaptation of Property.isOrdered() seems to be wrong.";
		msg += "Non-multiple Properties must be ordered.";

		/* The property must be ordered. */
		assertTrue(msg, nonmultipleProperty.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.isOrdered() seems to be wrong for Associations.";
			msg += "Non-multiple Properties must be ordered.";

			/* The property must be ordered. */
			assertTrue(msg, associationEnd1.isOrdered());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered3() {

		String msg;

		msg = "The adaptation of Property.isOrdered() seems to be wrong.";

		/* The property must be ordered. */
		assertTrue(msg, orderedMultipleProperty.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered4() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (orderedMultipleAssociationEnd != null) {

			String msg;

			msg =
					"The adaptation of Property.isOrdered() seems to be wrong for Associations.";

			/* The property must be ordered. */
			assertTrue(msg, orderedMultipleAssociationEnd.isOrdered());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered5() {

		String msg;

		msg = "The adaptation of Property.isOrdered() seems to be wrong.";

		/* The property must not be ordered. */
		assertFalse(msg, unorderedMultipleProperty.isOrdered());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isOrdered()}.
	 * </p>
	 */
	@Test
	public void testIsOrdered6() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (unorderedMultipleAssociationEnd != null) {

			String msg;

			msg =
					"The adaptation of Property.isOrdered() seems to be wrong for Associations.";

			/* The property must not be ordered. */
			assertFalse(msg, unorderedMultipleAssociationEnd.isOrdered());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic1() {

		String msg;

		msg = "The adaptation of Property.isStatic() seems to be wrong.";

		/* The property must not be static. */
		assertFalse(msg, nonmultipleProperty.isStatic());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (associationEnd1 != null) {

			String msg;

			msg =
					"The adaptation of Property.isStatic() seems to be wrong for Associations.";

			/* The property must not be static. */
			assertFalse(msg, associationEnd1.isStatic());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic3() {

		/*
		 * Only execute this test if the current IModel under test contains static
		 * properties.
		 */
		if (staticProperty != null) {
			String msg;

			msg = "The adaptation of Property.isStatic() seems to be wrong.";

			/* The property must not be static. */
			assertTrue(msg, staticProperty.isStatic());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isStatic()}.
	 * </p>
	 */
	@Test
	public void testIsStatic4() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (staticAssociationEnd != null) {

			String msg;

			msg =
					"The adaptation of Property.isStatic() seems to be wrong for Associations.";

			/* The property must not be static. */
			assertTrue(msg, staticAssociationEnd.isStatic());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique1() {

		String msg;

		msg = "The adaptation of Property.isUnique() seems to be wrong. ";
		msg += "Non-multiple Properties must be unique.";

		/* The property must be unique. */
		assertTrue(msg, nonmultipleProperty.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique2() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (associationEnd1 != null) {
			String msg;

			msg =
					"The adaptation of Property.isUnique() seems to be wrong for Associations. ";
			msg += "Non-multiple Properties must be unique.";

			/* The property must be unique. */
			assertTrue(msg, associationEnd1.isUnique());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique3() {

		String msg;

		msg = "The adaptation of Property.isUnique() seems to be wrong.";

		/* The property must be unique. */
		assertTrue(msg, uniqueMultipleProperty.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique4() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (uniqueMultipleAssociationEnd != null) {
			String msg;

			msg =
					"The adaptation of Property.isUnique() seems to be wrong for Associations. ";

			/* The property must be unique. */
			assertTrue(msg, uniqueMultipleAssociationEnd.isUnique());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique5() {

		String msg;

		msg = "The adaptation of Property.isUnique() seems to be wrong.";

		/* The property must not be unique. */
		assertFalse(msg, nonuniqueMultipleProperty.isUnique());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#isUnique()}.
	 * </p>
	 */
	@Test
	public void testIsUnique6() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (nonuniqueMultipleAssociationEnd != null) {
			String msg;

			msg =
					"The adaptation of Property.isUnique() seems to be wrong for Associations. ";

			/* The property must not be unique. */
			assertFalse(msg, nonuniqueMultipleAssociationEnd.isUnique());
		}
		// no else.
	}
}