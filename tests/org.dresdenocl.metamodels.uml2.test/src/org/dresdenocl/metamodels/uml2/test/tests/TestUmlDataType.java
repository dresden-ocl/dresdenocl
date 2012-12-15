/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
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
package org.dresdenocl.metamodels.uml2.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.ecore.EDataType;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.metamodels.test.MetaModelTestServices;
import org.dresdenocl.metamodels.test.MetaModelTestSuite;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * This class extends the {@link MetaModelTestSuite} for the
 * {@link UML2MetamodelPlugin} with additional tests for the adaptation of
 * UML2DataType.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestUmlDataType {

	/** The name of a non-primitive data type in the model under test. */
	private static String DATATYPE_NAME_DATATYPE = "DataType";

	/** The name of a non-primitive data type in the model under test. */
	private static String DATATYPE_QUALIFIED_NAME_DATATYPE = MetaModelTestServices.NAMESPACE_QUALIFIED_NAME_PACKAGE1
			+ "::" + DATATYPE_NAME_DATATYPE;

	/** The {@link Type} used for the tests in this class. */
	private static Type dataType;

	/** The {@link Namespace} used for the tests in this class. */
	private static Namespace package1;

	/**
	 * <p>
	 * Initializes some types tested in this {@link Class}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		dataType = MetaModelTestServices.getInstance().getTypeUnderTest(
				DATATYPE_QUALIFIED_NAME_DATATYPE);

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

		msg = "The adaptation of UmlDataType seems to be wrong. ";
		msg += "All DataTypes shall be mapped to Type.";

		assertNotNull(dataType);

		/* The type should not be adapted as a primitive type. */
		assertFalse(msg, dataType instanceof PrimitiveType);
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getGenericSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetGenericSuperType1() {

		String msg;

		msg = "The adaptation of UML2DataType.getGenericSuperType() seems to be wrong.";
		msg += " The Type " + dataType.getQualifiedName();
		msg += " should not have Generic Super Types.";

		/* The type must not have generic super types. */
		assertEquals(msg, 0, dataType.getGenericSuperType().size());
	}

	/**
	 * <p>
	 * Tests the method <code>UML2TypePrimitiveType.getName()</code>.
	 * </p>
	 */
	@Test
	public void testGetName1() {

		String msg;

		msg = "The adaptation of UML2DataType.getName() seems to be wrong.";

		/* The type should have the right name. */
		assertEquals(msg, DATATYPE_NAME_DATATYPE, dataType.getName());
	}

	/**
	 * <p>
	 * Tests the method <code>UML2TypePrimitiveType.getNamespace()</code>.
	 * </p>
	 */
	@Test
	public void testGetNamespace1() {

		String msg;

		msg = "The adaptation of UML2DataType.getNamespace() seems to be wrong.";

		/* The type should belong to the right name space. */
		assertEquals(msg, package1, dataType.getNamespace());
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getOwnedOperation()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedOperation1() {

		List<Operation> operations;

		boolean foundOperation1;
		boolean foundOperation2;

		String msg;

		msg = "The adaptation of UML2DataType.getOwnedOperation() seems to be wrong.";

		operations = dataType.getOwnedOperation();

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

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getOwnedProperty()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedProperty1() {

		List<Property> properties;

		boolean foundProperty1;
		boolean foundProperty2;

		String msg;

		msg = "The adaptation of UML2DataType.getOwnedProperty() seems to be wrong.";

		properties = dataType.getOwnedProperty();

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
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getOwnedTypeParameter()}.
	 * </p>
	 */
	@Test
	public void testGetOwnedTypeParameter1() {

		String msg;

		msg = "The adaptation of UML2DataType.getOwnedTypeParameter() seems to be wrong.";
		msg += " The Type " + dataType.getQualifiedName();
		msg += " should not have Type Parameters.";

		/* The type must not have type parameters. */
		assertEquals(msg, 0, dataType.getOwnedTypeParameter().size());
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getOwner()}.
	 * </p>
	 */
	@Test
	public void testGetOwner1() {

		String msg;

		msg = "The adaptation of UML2DataType.getOwner() seems to be wrong.";

		/* The type must be owned by the name space 'test'. */
		assertEquals(msg, package1, dataType.getOwner());
	}

	/**
	 * <p>
	 * Tests the method <code>UML2TypePrimitiveType.getQualifiedName()</code>.
	 * </p>
	 */
	@Test
	public void testGetQualifiedName1() {

		String msg;

		msg = "The adaptation of UML2DataType.getQualifiedName() seems to be wrong.";

		/* The type should have the right qualified name. */
		assertEquals(msg, DATATYPE_QUALIFIED_NAME_DATATYPE,
				dataType.getQualifiedName());
	}

	/**
	 * <p>
	 * A test case testing the operation
	 * {@link UML2TypePrimitiveType#getSuperType()}.
	 * </p>
	 */
	@Test
	public void testGetSuperType1() {

		String msg;

		msg = "The adaptation of UML2DataType.getSuperType() seems to be wrong.";
		msg += " UML2TypePrimitiveTypes cannot have super types.";

		/* The type must not have super types. */
		assertEquals(msg, 0, dataType.getSuperType().size());
	}
}