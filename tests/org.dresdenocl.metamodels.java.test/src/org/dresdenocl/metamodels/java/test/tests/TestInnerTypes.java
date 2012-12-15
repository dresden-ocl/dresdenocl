/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.java.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EDataType;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.metamodels.test.MetaModelTestServices;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that test the adaptation of the internal defined Classes,
 * Interfaces and Enumerations in a Java {@link Class}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInnerTypes {

	/** The name of a {@link Type} in the model under test. */
	private static String NAMESPACE_NAME_TESTINNERCLASSESCLASS =
			"TestInnerClassesClass";

	/** The name of a {@link Type} in the model under test. */
	private static String NAMESPACE_QUALIFIEDNAME_TESTINNERCLASSESCLASS =
			MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1 + "::"
					+ NAMESPACE_NAME_TESTINNERCLASSESCLASS;

	/** The name of a {@link Type} in the model under test. */
	private static String TYPE_NAME_INNERCLASS = "InnerClass";

	/** The qualified name of a {@link Type} in the model under test. */
	private static String TYPE_QUALIFIED_NAME_INNERCLASS =
			NAMESPACE_QUALIFIEDNAME_TESTINNERCLASSESCLASS + "::"
					+ TYPE_NAME_INNERCLASS;

	/** The name of a {@link Type} in the model under test. */
	private static String TYPE_NAME_INNERENUMERATION = "InnerEnumeration";

	/** The qualified name of a {@link Type} in the model under test. */
	private static String TYPE_QUALIFIED_NAME_INNERENUMERATION =
			NAMESPACE_QUALIFIEDNAME_TESTINNERCLASSESCLASS + "::"
					+ TYPE_NAME_INNERENUMERATION;

	/** The name of a {@link Type} in the model under test. */
	private static String TYPE_NAME_INNERINTERFACE = "InnerInterface";

	/** The qualified name of a {@link Type} in the model under test. */
	private static String TYPE_QUALIFIED_NAME_INNERINTERFACE =
			NAMESPACE_QUALIFIEDNAME_TESTINNERCLASSESCLASS + "::"
					+ TYPE_NAME_INNERINTERFACE;

	/** The name of a {@link Type} in the model under test. */
	private static String TYPE_NAME_TESTINNERCLASSESCLASS =
			"TestInnerClassesClass";

	/** The name of a {@link Type} in the model under test. */
	private static String TYPE_QUALIFIED_NAME_TESTINNERCLASSESCLASS =
			MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1 + "::"
					+ TYPE_NAME_TESTINNERCLASSESCLASS;

	/** A {@link Type} used for the tests in this class. */
	private static Type innerClass;

	/** A {@link Type} used for the tests in this class. */
	private static Type innerEnumeration;

	/** A {@link Type} used for the tests in this class. */
	private static Type innerInterface;

	/** A {@link Type} used for the tests in this class. */
	private static Type testInnerClassesClass;

	/** The {@link Namespace} used for the tests in this class. */
	private static Namespace namespace;

	/**
	 * <p>
	 * Initializes some types tested in this {@link Class}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		testInnerClassesClass =
				MetaModelTestServices.getInstance().getTypeUnderTest(
						TYPE_QUALIFIED_NAME_TESTINNERCLASSESCLASS);

		for (Property aProperty : testInnerClassesClass.getOwnedProperty()) {

			if (aProperty.getName().equalsIgnoreCase(TYPE_NAME_INNERCLASS)) {
				innerClass = aProperty.getType();
			}

			else if (aProperty.getName().equalsIgnoreCase(TYPE_NAME_INNERENUMERATION)) {
				innerEnumeration = aProperty.getType();
			}

			else if (aProperty.getName().equalsIgnoreCase(TYPE_NAME_INNERINTERFACE)) {
				innerInterface = aProperty.getType();
			}
			// no else.
		}
		// end if.

		namespace =
				MetaModelTestServices.getInstance().getNamespaceUnderTest(
						NAMESPACE_QUALIFIEDNAME_TESTINNERCLASSESCLASS);
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation1() {

		String msg;

		msg = "The adaptation of Inner Java Classes seems to be wrong. ";

		assertNotNull(msg, innerClass);
		assertTrue(msg, innerClass instanceof Type);

		assertEquals(namespace, innerClass.getNamespace());
		assertEquals(TYPE_QUALIFIED_NAME_INNERCLASS, innerClass.getQualifiedName());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation2() {

		String msg;

		msg = "The adaptation of Inner Java Enumerations seems to be wrong. ";

		assertNotNull(msg, innerEnumeration);
		assertTrue(msg, innerEnumeration instanceof Enumeration);

		assertEquals(namespace, innerEnumeration.getNamespace());
		assertEquals(TYPE_QUALIFIED_NAME_INNERENUMERATION, innerEnumeration
				.getQualifiedName());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation3() {

		String msg;

		msg = "The adaptation of Inner Java Interfaces seems to be wrong. ";

		assertNotNull(msg, innerInterface);
		assertTrue(msg, innerInterface instanceof Type);

		assertEquals(namespace, innerInterface.getNamespace());
		assertEquals(TYPE_QUALIFIED_NAME_INNERINTERFACE, innerInterface
				.getQualifiedName());
	}
}