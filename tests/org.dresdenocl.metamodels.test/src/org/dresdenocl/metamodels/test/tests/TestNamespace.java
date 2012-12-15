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
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.metamodels.test.MetaModelTestPlugin;
import org.dresdenocl.metamodels.test.MetaModelTestServices;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * This class provides test cases to test the {@link Namespace}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestNamespace {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = MetaModelTestPlugin
			.getLogger(TestNamespace.class);

	/** One {@link Namespace} under test. */
	private static Namespace package1;

	/** One {@link Namespace} under test. */
	private static Namespace package2;

	/** One {@link Namespace} under test. */
	private static Namespace rootPackage;

	/** One {@link Type} located in one of the {@link Namespace} s. */
	private static Type class1;

	/**
	 * <p>
	 * Loads some elements from the current {@link IModel} under test required
	 * for the tests contained in this Class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		rootPackage = MetaModelTestServices.getInstance()
				.getNamespaceUnderTest(ModelConstants.ROOT_PACKAGE_NAME);

		package1 = MetaModelTestServices.getInstance().getNamespaceUnderTest(
				MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1);

		package2 = MetaModelTestServices.getInstance().getNamespaceUnderTest(
				MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE2);

		if (package2 == null)
			LOGGER.warn("Namespace 'package2' was not found. Probably the adaptation works not correctly.");
		// no else.

		class1 = MetaModelTestServices.getInstance().getTypeUnderTest(
				MetaModelTestServices.TYPE_QUALIFIED_NAME_TESTTYPECLASS1);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getName()}.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of Namespace.getName() seems to be wrong.";

		/* The name space must have the name 'metamodel'. */
		assertEquals(msg, MetaModelTestServices.NAMESPACE_NAME_PACKAGE1,
				MetaModelTestServices.probablyToLowerCase(package1.getName()));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getNestedNamespace()}.
	 * </p>
	 */
	@Test
	public void testGetNestedNamespace1() {

		if (package2 != null) {
			String msg;

			msg = "The adaptation of Namespace.getNestedNamespace() seems to be wrong.";

			/* The test name space must be one of the nested name spaces. */
			assertTrue(msg, package1.getNestedNamespace().contains(package2));
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getNestedNamespace()}.
	 * </p>
	 */
	@Test
	public void testGetNestedNamespace2() {

		String msg = "The adaptation of Namespace.getNestedNamespace() seems to be wrong. The return type should be an EList.";

		assertTrue(msg, package1.getNestedNamespace() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getNestingNamespace()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetNestingNamespace1() {

		String msg;

		msg = "The adaptation of Namespace.getNestingNamespace() seems to be wrong.";

		/* The name space must be contained in the root name space. */
		assertEquals(msg, rootPackage, package1.getNestingNamespace());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getNestingNamespace()}
	 * .
	 * </p>
	 */
	@Test
	public void testGetNestingNamespace2() {

		if (package2 != null) {
			String msg;

			msg = "The adaptation of Namespace.getNestingNamespace() seems to be wrong.";

			/* The name space must be contained in the metamodel name space. */
			assertEquals(msg, package2.getNestingNamespace(), package1);
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getOwnedType()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedType1() {

		if (package2 != null) {
			List<Type> ownedTypes;

			String msg;

			msg = "The adaptation of Namespace.getOwnedType() seems to be wrong.";

			ownedTypes = package2.getOwnedType();

			/* The test name space must not contain any types. */
			assertEquals(msg, 0, ownedTypes.size());
		}
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getOwnedType()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedType2() {

		List<Type> ownedTypes;

		String msg;

		msg = "The adaptation of Namespace.getOwnedType() seems to be wrong.";

		ownedTypes = package1.getOwnedType();

		/* The test name space must contain at least 3 types. */
		assertTrue(msg, 3 <= ownedTypes.size());

		/* The test name space must contain the following type. */
		assertTrue(msg, ownedTypes.contains(class1));
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getOwnedType()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedType3() {

		String msg = "The adaptation of Namespace.getOwnedType() seems to be wrong. The return type should be an ESet.";

		assertTrue(msg, package1.getOwnedType() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Namespace#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		String msg;

		msg = "The adaptation of Namespace.getOwnedTypeParameter() seems to be wrong. ";
		msg += "The Namesapce " + package1.getQualifiedName();
		msg += " should not have Type Parameters.";

		/* The name space must not have type parameters. */
		assertEquals(msg, 0, package1.getOwnedTypeParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link Namespace#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter2() {

		String msg = "The adaptation of Namespace.getOwnedTypeParameter() seems to be wrong. ";
		msg += "The return type should be an EList.";

		/* The name space must not have type parameters. */
		assertTrue(msg, package1.getOwnedTypeParameter() instanceof EList);
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getOwner()} .
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of Namespace.getOwner() seems to be wrong.";
		msg += "A Namespace must be owned by its nesting Namespace.";

		/* The name space must be owned by the root name space. */
		assertEquals(msg, rootPackage, package1.getOwner());
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getOwner()} .
	 * </p>
	 */
	@Test
	public void testGetOwner2() {

		if (package2 != null) {
			String msg;

			msg = "The adaptation of Namespace.getOwner() seems to be wrong.";
			msg += "A Namespace must be owned by its nesting Namespace.";

			/* The name space must be owned by the root metamodel space. */
			assertEquals(msg, package1, package2.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * A test case testing the operation {@link Namespace#getQualifiedName()}.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of Namespace.getQualifiedName() seems to be wrong.";

		/* The test name space must have the name 'test'. */
		assertEquals(msg,
				MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1,
				MetaModelTestServices.probablyToLowerCase(package1
						.getQualifiedName()));
	}
}