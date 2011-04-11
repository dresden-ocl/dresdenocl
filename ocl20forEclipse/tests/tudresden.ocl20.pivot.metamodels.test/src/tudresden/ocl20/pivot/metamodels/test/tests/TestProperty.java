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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
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
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestProperty.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type testPropertyClass;

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
	 * Loads some elements from the current {@link IModel} under test required
	 * for the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		class1 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS1);

		testPropertyClass = MetaModelTestServices
				.getInstance()
				.getTypeUnderTest(
						MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS);

		nonmultipleProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEPROPERTY);
		staticProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_STATICPROPERTY);
		orderedMultipleProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_ORDEREDMULTIPLEPROPERTY);
		unorderedMultipleProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_UNORDEREDMULTIPLEPROPERTY);
		uniqueMultipleProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_UNIQUEMULTIPLEPROPERTY);
		nonuniqueMultipleProperty = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_NONUNIQUEMULTIPLEPROPERTY);
		associationEnd1 = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEASSOCIATONEND);
		staticAssociationEnd = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_STATICASSOCIATONEND);
		orderedMultipleAssociationEnd = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_ORDEREDMULTIPLEASSOCIATONEND);
		unorderedMultipleAssociationEnd = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_UNORDEREDMULTIPLEASSOCIATONEND);
		uniqueMultipleAssociationEnd = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_UNIQUEMULTIPLEASSOCIATONEND);
		nonuniqueMultipleAssociationEnd = testPropertyClass
				.lookupProperty(MetaModelTestServices.PROPERTY_NAME_NONUNIQUEMULTIPLEASSOCIATONEND);

		if (associationEnd1 == null || orderedMultipleAssociationEnd == null
				|| unorderedMultipleAssociationEnd == null
				|| uniqueMultipleAssociationEnd == null
				|| nonuniqueMultipleAssociationEnd == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg = MetaModelTestSuiteMessages.MetaModelTestSuite_AssociationEndNotFound;
				msg += " "
						+ NLS.bind(
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

				msg = NLS
						.bind(MetaModelTestSuiteMessages.MetaModelTestSuite_PropertyNotFoundInModel,
								MetaModelTestServices.PROPERTY_NAME_STATICPROPERTY,
								"Property.isStatic()");
				msg += " "
						+ NLS.bind(
								MetaModelTestSuiteMessages.MetaModelTestSuite_CurrentlyTestedMetaModel,
								MetaModelTestServices.getInstance()
										.getMetaModelUnderTestID());

				LOGGER.warn(msg);
			}
			// no else.
		}
		// no else.
	}

	@AfterClass
	public static void tearDown() {
		class1 = null;
		testPropertyClass = null;
		associationEnd1 = null;
		staticAssociationEnd = null;
		orderedMultipleAssociationEnd = null;
		uniqueMultipleAssociationEnd = null;
		unorderedMultipleAssociationEnd = null;
		uniqueMultipleAssociationEnd = null;
		nonuniqueMultipleAssociationEnd = null;
		nonmultipleProperty = null;
		staticProperty = null;
		orderedMultipleProperty = null;
		uniqueMultipleProperty = null;
		unorderedMultipleProperty = null;
		nonuniqueMultipleProperty = null;
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
		assertEquals(msg,
				MetaModelTestServices.PROPERTY_NAME_NONMULTIPLEPROPERTY,
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

			msg = "The adaptation of Property.getName() seems to be wrong for Associations.";

			/* The property must have been found by the given name. */
			assertEquals(
					msg,
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
		assertEquals(msg, testPropertyClass, nonmultipleProperty.getOwner());
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

			msg = "The adaptation of Property.getOwner() seems to be wrong for Associations. ";
			msg += "A Property must be owned by the Type to which it belongs.";

			/* The property must be owned by the same type as in the model. */
			assertEquals(msg, testPropertyClass, associationEnd1.getOwner());
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
		assertEquals(msg, testPropertyClass,
				nonmultipleProperty.getOwningType());
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

			msg = "The adaptation of Property.getOwningType() seems to be wrong for Associations.";

			/* The property must be owned by the same type as in the model. */
			assertEquals(msg, testPropertyClass,
					associationEnd1.getOwningType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType01() {

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
	public void testGetType02() {

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
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType03() {

		String msg;

		msg = "The adaptation of Property.getType() seems to be wrong.";

		/* The property must be multiple. */
		assertTrue(
				msg,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(class1)
						.equals(orderedMultipleProperty.getType())
						|| EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOrderedSetType(class1)
								.equals(orderedMultipleProperty.getType()));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType04() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (orderedMultipleAssociationEnd != null) {

			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getSequenceType(class1),
					orderedMultipleAssociationEnd.getType());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType05() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (unorderedMultipleProperty != null) {
			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(class1),
					unorderedMultipleProperty.getType());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType06() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (unorderedMultipleAssociationEnd != null) {

			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(class1),
					unorderedMultipleAssociationEnd.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType07() {

		String msg;

		msg = "The adaptation of Property.getType() seems to be wrong.";

		/* The property must be multiple. */
		assertTrue(
				msg,
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSetType(class1)
						.equals(uniqueMultipleProperty.getType())
						|| EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getOrderedSetType(class1)
								.equals(uniqueMultipleProperty.getType()));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType08() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (uniqueMultipleAssociationEnd != null) {
			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getSetType(class1),
					uniqueMultipleAssociationEnd.getType());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType09() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations.
		 */
		if (nonuniqueMultipleProperty != null) {
			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(class1),
					nonuniqueMultipleProperty.getType());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Property#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType10() {

		/*
		 * Only execute this test if the current model under test supports
		 * associations and static properties.
		 */
		if (nonuniqueMultipleAssociationEnd != null) {
			String msg;

			msg = "The adaptation of Property.getType() seems to be wrong.";

			/* The property must be multiple. */
			assertEquals(msg, EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(class1),
					nonuniqueMultipleAssociationEnd.getType());
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
		assertEquals(
				msg,
				MetaModelTestServices
						.probablyToLowerCase(MetaModelTestServices.PROPERTY_QUALIFIED_NAME_NONMULTIPLEPROPERTY),
				MetaModelTestServices.probablyToLowerCase(nonmultipleProperty
						.getQualifiedName()));
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

			msg = "The adaptation of Property.getQualifiedName() seems to be wrong for Associations.";

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

			msg = "The adaptation of Property.isStatic() seems to be wrong for Associations.";

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
		 * Only execute this test if the current IModel under test contains
		 * static properties.
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

			msg = "The adaptation of Property.isStatic() seems to be wrong for Associations.";

			/* The property must not be static. */
			assertTrue(msg, staticAssociationEnd.isStatic());
		}
		// no else.
	}
}