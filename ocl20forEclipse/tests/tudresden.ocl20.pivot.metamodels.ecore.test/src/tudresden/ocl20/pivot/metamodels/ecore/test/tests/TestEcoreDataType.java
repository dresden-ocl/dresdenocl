/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.ecore.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.eclipse.emf.ecore.EDataType;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestSuite;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class extends the {@link MetaModelTestSuite} for the
 * {@link EcoreMetamodelPlugin} with additional tests for the adaptation of
 * EDataType.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestEcoreDataType {

	/** The name of a non-primitive data type in the model under test. */
	private static String PRIMITIVETYPE_NAME_NONPRIMITIVEDATATYPE = "NonPrimitiveDataType";

	/** The name of a non-primitive data type in the model under test. */
	private static String PRIMITIVETYPE_QUALIFIED_NAME_NONPRIMITIVEDATATYPE = MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1
			+ "::" + PRIMITIVETYPE_NAME_NONPRIMITIVEDATATYPE;

	/** The {@link Type} used for the tests in this class. */
	private static Type nonPrimitiveDataType;

	/** The {@link Namespace} used for the tests in this class. */
	private static Namespace package1;

	/**
	 * <p>
	 * Initializes some types tested in this {@link Class}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {
		nonPrimitiveDataType = MetaModelTestServices.getInstance()
				.getTypeUnderTest(
						PRIMITIVETYPE_QUALIFIED_NAME_NONPRIMITIVEDATATYPE);

		package1 = MetaModelTestServices.getInstance().getNamespaceUnderTest(
				MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1);
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation1() {

		String msg;

		msg = "The adaptation of EDataType seems to be wrong. ";
		msg += "Not all EDataTypes shall be primitive.";

		assertNotNull(nonPrimitiveDataType);

		/* The type should not be adapted as a primitive type. */
		assertFalse(msg, nonPrimitiveDataType instanceof PrimitiveType);
	}

	/**
	 * <p>
	 * Tests the method <code>EcoreDataType.getName()</code>.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of EDataType.getName() seems to be wrong.";

		/* The type should have the right name. */
		assertEquals(msg, PRIMITIVETYPE_NAME_NONPRIMITIVEDATATYPE,
				nonPrimitiveDataType.getName());
	}

	/**
	 * <p>
	 * Tests the method <code>EcoreDataType.getNamespace()</code>.
	 * </p>
	 */
	@Test
	public void testGetNamespace1() {

		String msg;

		msg = "The adaptation of EDataType.getNamespace() seems to be wrong.";

		/* The type should belong to the right name space. */
		assertEquals(msg, package1, nonPrimitiveDataType.getNamespace());
	}

	/**
	 * <p>
	 * Tests the method <code>EcoreDataType.getQualifiedName()</code>.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of EDataType.getQualifiedName() seems to be wrong.";

		/* The type should have the right qualified name. */
		assertEquals(msg, PRIMITIVETYPE_QUALIFIED_NAME_NONPRIMITIVEDATATYPE,
				nonPrimitiveDataType.getQualifiedName());
	}
}