/*
 * Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net) This file is part of
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.ecore.EDataType;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.model.IModel;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that test the adaptation of java collections to
 * OclLibrary types (CollectionTypes).
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestCollectionAdaptation extends AbstractJavaModelTest {

	/** The {@link IModel} used to test the adaptation. */
	private static IModel modelUnderTest;

	/** The {@link Type} used to test the adaptation of operations. */
	private static Type testType;

	/**
	 * <p>
	 * Initializes some types tested in this {@link Class}.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		File modelFile = getFile("bin/package1/package2/collections/CollectionTestModel.class");

		modelUnderTest = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.JAVA_META_MODEL);

		testType = modelUnderTest
				.findType(Arrays.asList(new String[] { "package1", "package2",
						"collections", "CollectionTestModel" }));

		assertNotNull(testType);
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation01() {

		String msg;
		msg = "The operation 'getCollection' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getCollection",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getCollectionType(testType);

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation02() {

		String msg;
		msg = "The operation 'getList' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getList",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSequenceType(testType);

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation03() {

		String msg;
		msg = "The operation 'getSet' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getSet",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSetType(testType);

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation04() {

		String msg;
		msg = "The operation 'getArray' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getArray",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSequenceType(testType);

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation05() {

		String msg;
		msg = "The operation 'getCollectionCollection' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation(
				"getCollectionCollection", new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getCollectionType(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getCollectionType(testType));

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation06() {

		String msg;
		msg = "The operation 'getCollectionListSet' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getCollectionListSet",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getCollectionType(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getSequenceType(
										EssentialOclPlugin
												.getOclLibraryProvider()
												.getOclLibrary().getSetType(
														testType)));

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation07() {

		String msg;
		msg = "The operation 'getCollectionArray' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getCollectionArray",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getCollectionType(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getSequenceType(testType));

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation08() {

		String msg;
		msg = "The operation 'getArrayCollection' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getArrayCollection",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSequenceType(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getCollectionType(testType));

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}

	/**
	 * <p>
	 * Tests the adaptation of a {@link EDataType} which is not primitive.
	 * </p>
	 */
	@Test
	public void testAdaptation09() {

		String msg;
		msg = "The operation 'getArrayArray' has a wrong return type. ";

		Operation operationUnderTest;
		operationUnderTest = testType.lookupOperation("getArrayArray",
				new ArrayList<Type>());

		assertNotNull(operationUnderTest);

		Type expectedType;
		expectedType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getSequenceType(
						EssentialOclPlugin.getOclLibraryProvider()
								.getOclLibrary().getSequenceType(testType));

		assertEquals(msg, expectedType, operationUnderTest.getType());
	}
}