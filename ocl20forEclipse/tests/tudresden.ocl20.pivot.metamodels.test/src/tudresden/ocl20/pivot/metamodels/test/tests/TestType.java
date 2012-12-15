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
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.msg.MetaModelTestSuiteMessages;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides test cases to test the {@link Type}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestEnumeration.class);

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class2;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type class3;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type interface1;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type interface2;

	/** A {@link Type} of the current {@link IModel} under test. */
	private static Type interface3;

	/** A {@link Namespace} of the current {@link IModel} under test. */
	private static Namespace package1;
	
	/** A {@link Property} of the current {@link IModel} under test. */
	private static Property identifierProperty;

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

		class2 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS2);

		class3 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTPROPERTYCLASS);

		interface1 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPEINTERFACE1);

		interface2 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPEINTERFACE2);
		
		identifierProperty = class3.lookupProperty(MetaModelTestServices.PROPERTY_NAME_IDENTIFIER);

		if (interface1 == null || interface2 == null) {

			/* Eventually send a warning to the logger. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg = MetaModelTestSuiteMessages.MetaModelTestSuite_InterfaceNotFoundInModel;
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

		package1 = MetaModelTestServices.getInstance().getNamespaceUnderTest(
				MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getGenericSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType1() {

		String msg;

		msg = "The adaptation of Type.getGenericSuperType() seems to be wrong.";
		msg += " The Type " + class1.getQualifiedName();
		msg += " should not have Generic Super Types.";

		/* The type must not have generic super types. */
		assertEquals(msg, 0, class1.getGenericSuperType().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getGenericSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType2() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			String msg;

			msg = "The adaptation of Type.getGenericSuperType() seems to be wrong for Interfaces.";
			msg += " The Type " + interface1.getQualifiedName();
			msg += " should not have Generic Super Types.";

			/* The type must not have generic super types. */
			assertEquals(msg, 0, interface1.getGenericSuperType().size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getGenericSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType3() {

		String msg = "The adaptation of Type.getGenericSuperType() seems to be wrong for Interfaces.";
		msg += " Result shoul be an EList.";

		assertTrue(msg, class1.getGenericSuperType() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of Type.getName() seems to be wrong.";

		/* The type must have the given name in the model. */
		assertEquals(msg, MetaModelTestServices.TYPE_NAME_TESTTYPECLASS1,
				class1.getName());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName2() {

		String msg;

		msg = "The adaptation of Type.getName() seems to be wrong for Interfaces.";

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			/* The type must have the given name in the model. */
			assertEquals(msg,
					MetaModelTestServices.TYPE_NAME_TESTTYPEINTERFACE1,
					interface1.getName());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getNamespace()}.
	 * </p>
	 */
	@Test
	public void testGetNamespace1() {

		String msg;

		msg = "The adaptation of Type.getNamespace() seems to be wrong.";

		/* The type must be located in the name space 'test'. */
		assertEquals(msg, package1, class1.getNamespace());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getNamespace()}.
	 * </p>
	 */
	@Test
	public void testGetNamespace2() {

		String msg;

		msg = "The adaptation of Type.getNamespace() seems to be wrong for Interfaces.";

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			/* The type must be located in the name space 'test'. */
			assertEquals(msg, package1, interface1.getNamespace());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation1() {

		if (!MetaModelTestServices.supportsNoOperations) {
			List<Operation> operations;

			boolean foundOperation1;
			boolean foundOperation2;

			String msg;

			msg = "The adaptation of Type.getOwnedOperation() seems to be wrong.";

			operations = class1.getOwnedOperation();

			/* The type must have exactly one operation. */
			assertEquals(1, operations.size());

			foundOperation1 = false;
			foundOperation2 = false;

			/* Search for some operations. */
			for (Operation anOperation : operations) {

				if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION1)) {
					foundOperation1 = true;
				}

				else if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION2)) {
					foundOperation2 = true;
				}
			}

			/* Operation1 should be found. */
			assertTrue(msg, foundOperation1);

			/* Operation2 should not be found. */
			assertFalse(msg, foundOperation2);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation2() {

		if (!MetaModelTestServices.supportsNoOperations) {
			List<Operation> operations;

			boolean foundOperation1;
			boolean foundOperation2;

			String msg;

			msg = "The adaptation of Type.getOwnedOperation() seems to be wrong.";

			operations = class2.getOwnedOperation();

			/* The type must have exactly one operation. */
			assertEquals(1, operations.size());

			foundOperation1 = false;
			foundOperation2 = false;

			/* Search for some operations. */
			for (Operation anOperation : operations) {

				if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION1)) {
					foundOperation1 = true;
				}

				else if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION2)) {
					foundOperation2 = true;
				}
			}

			/* Operation1 should not be found. */
			assertFalse(msg, foundOperation1);

			/* Operation2 should be found. */
			assertTrue(msg, foundOperation2);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation3() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			List<Operation> operations;

			boolean foundOperation1;
			boolean foundOperation2;

			String msg;

			msg = "The adaptation of Type.getOwnedOperation() seems to be wrong for Interfaces.";

			operations = interface1.getOwnedOperation();

			/* The type must have exactly one operation. */
			assertEquals(1, operations.size());

			foundOperation1 = false;
			foundOperation2 = false;

			/* Search for some operations. */
			for (Operation anOperation : operations) {

				if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION1)) {
					foundOperation1 = true;
				}

				else if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION2)) {
					foundOperation2 = true;
				}
			}

			/* Operation1 should be found. */
			assertTrue(msg, foundOperation1);

			/* Operation2 should not be found. */
			assertFalse(msg, foundOperation2);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation4() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface2 != null) {

			List<Operation> operations;

			boolean foundOperation1;
			boolean foundOperation2;

			String msg;

			msg = "The adaptation of Type.getOwnedOperation() seems to be wrong for Interfaces.";

			operations = interface2.getOwnedOperation();

			/* The type must have one operation. */
			assertEquals(1, operations.size());

			foundOperation1 = false;
			foundOperation2 = false;

			/* Search for some operations. */
			for (Operation anOperation : operations) {

				if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION1)) {
					foundOperation1 = true;
				}

				else if (anOperation.getName().equals(
						MetaModelTestServices.OPERATION_NAME_OPERATION2)) {
					foundOperation2 = true;
				}
			}

			/* Operation1 should not be found. */
			assertFalse(msg, foundOperation1);

			/* Operation2 should be found. */
			assertTrue(msg, foundOperation2);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation5() {

		String msg = "The adaptation of Type.getOwnedOperation() seems to be wrong. Result should be an EList.";

		assertTrue(msg, class1.getOwnedOperation() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty1() {

		List<Property> properties;

		boolean foundProperty1;
		boolean foundProperty2;

		String msg;

		msg = "The adaptation of Type.getOwnedProperty() seems to be wrong.";

		properties = class1.getOwnedProperty();

		foundProperty1 = false;
		foundProperty2 = false;

		/* Search for some operations. */
		for (Property aProperty : properties) {

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_PROPERTY1)) {
				foundProperty1 = true;
			}

			else if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_PROPERTY2)) {
				foundProperty2 = true;
			}
		}

		/* Property1 should be found. */
		assertTrue(msg, foundProperty1);

		/* Property2 should not be found. */
		assertFalse(msg, foundProperty2);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty2() {

		List<Property> properties;

		boolean foundProperty1;
		boolean foundProperty2;

		String msg;

		msg = "The adaptation of Type.getOwnedProperty() seems to be wrong.";

		properties = class2.getOwnedProperty();

		foundProperty1 = false;
		foundProperty2 = false;

		/* Search for some operations. */
		for (Property aProperty : properties) {

			if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_PROPERTY1)) {
				foundProperty1 = true;
			}

			else if (aProperty.getName().equals(
					MetaModelTestServices.PROPERTY_NAME_PROPERTY2)) {
				foundProperty2 = true;
			}
		}

		/* Property1 should not be found. */
		assertFalse(msg, foundProperty1);

		/* Property2 should be found. */
		assertTrue(msg, foundProperty2);
	}
	

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty3() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			String msg;

			msg = "The adaptation of Type.getOwnedProperty() seems to be wrong for Interfaces.";

			/* The interface must not have properties. */
			assertEquals(msg, 0, interface1.getOwnedProperty().size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty4() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface2 != null) {

			String msg;

			msg = "The adaptation of Type.getOwnedProperty() seems to be wrong for Interfaces.";

			/* The interface must not have properties. */
			assertEquals(msg, 0, interface2.getOwnedProperty().size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty5() {

		String msg = "The adaptation of Type.getOwnedProperty() seems to be wrong. Result should be an EList.";

		assertTrue(msg, class1.getOwnedProperty() instanceof EList);
	}
	
	/**
	 * <p>
	 * A test case testing the operation {@link Type#getIDProperties()}.
	 * </p>
	 */
	@Test
	public void testGetIDProperties() {

		String msg = "The adaptation of Type.getIDProperties() seems to be wrong. Result should be contains one element";

		if (identifierProperty != null) {
			assertEquals(msg,1,class3.getIDProperties().size());
			assertTrue(msg,class3.getIDProperties().contains(identifierProperty));
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		String msg;

		msg = "The adaptation of Type.getOwnedTypeParameter() seems to be wrong.";
		msg += " The Type " + class1.getQualifiedName();
		msg += " should not have Type Parameters.";

		/* The type must not have type parameters. */
		assertEquals(msg, 0, class1.getOwnedTypeParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter2() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			String msg;

			msg = "The adaptation of Type.getOwnedTypeParameter() seems to be wrong for Interfaces.";
			msg += " The Type " + interface1.getQualifiedName();
			msg += " should not have Type Parameters.";

			/* The type must not have type parameters. */
			assertEquals(msg, 0, interface1.getOwnedTypeParameter().size());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter3() {

		String msg;

		msg = "The adaptation of Type.getOwnedTypeParameter() seems to be wrong.";
		msg += " Result should be an EList.";

		assertTrue(msg, class1.getOwnedTypeParameter() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of Type.getOwner() seems to be wrong.";

		/* The type must be owned by the name space 'test'. */
		assertEquals(msg, package1, class1.getOwner());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner2() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			String msg;

			msg = "The adaptation of Type.getOwner() seems to be wrong for Interfaces.";

			/* The type must be owned by the name space 'test'. */
			assertEquals(msg, package1, interface1.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType1() {

		String msg;

		msg = "The adaptation of Type.getSuperType() seems to be wrong.";

		/*
		 * The type must not have super types (but general super types like
		 * java.lang.Object are possible.
		 */
		assertTrue(msg, 0 <= class1.getSuperType().size());

		/* The type must not have this type as super type. */
		assertFalse(msg, class1.getSuperType().contains(class2));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType2() {

		String msg;

		msg = "The adaptation of Type.getSuperType() seems to be wrong.";

		/* The type must not have at least one super type. */
		assertTrue(msg, 1 <= class2.getSuperType().size());

		/* The type must have the this type as super type. */
		assertTrue(msg, class2.getSuperType().contains(class1));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType3() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null && interface2 != null) {

			String msg;

			msg = "The adaptation of Type.getSuperType() seems to be wrong for Interfaces.";

			/*
			 * The type must not have super types (but general super types like
			 * java.lang.Object are possible.
			 */
			assertTrue(msg, 0 <= interface1.getSuperType().size());

			/* The type must not have this type as super type. */
			assertFalse(msg, interface1.getSuperType().contains(interface2));
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType4() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null && interface2 != null) {

			String msg;

			msg = "The adaptation of Type.getSuperType() seems to be wrong for Interfaces.";

			/* The type must not have at least one super type. */
			assertTrue(msg, 1 <= interface2.getSuperType().size());

			/* The type must have the this type as super type. */
			assertTrue(msg, interface2.getSuperType().contains(interface1));
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType5() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface3 != null) {

			String msg;

			msg = "The adaptation of Type.getSuperType() seems to be wrong for Interfaces.";

			/* The type must not have at least two super types. */
			assertTrue(msg, 2 <= class3.getSuperType().size());

			/* The type must have the this type as super type. */
			assertTrue(msg, class3.getSuperType().contains(class1));

			/* The type must have the this type as super type. */
			assertTrue(msg, class3.getSuperType().contains(interface3));
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType6() {

		String msg = "The adaptation of Type.getSuperType() seems to be wrong. Result should be an EList.";

		assertTrue(msg, class1.getSuperType() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of Type.getQualifiedName() seems to be wrong.";

		/* The type must have the same name as in the model. */
		assertEquals(
				msg,
				MetaModelTestServices
						.probablyToLowerCase(MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS1),
				MetaModelTestServices.probablyToLowerCase(class1
						.getQualifiedName()));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Type#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName2() {

		/*
		 * Only execute this test case, if interfaces are adapted by the current
		 * meta model.
		 */
		if (interface1 != null) {

			String msg;

			msg = "The adaptation of Type.getQualifiedName() seems to be wrong for Interfaces.";

			/* The type must have the same name as in the model. */
			assertEquals(
					msg,
					MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPEINTERFACE1,
					interface1.getQualifiedName());
		}
		// no else.
	}
}