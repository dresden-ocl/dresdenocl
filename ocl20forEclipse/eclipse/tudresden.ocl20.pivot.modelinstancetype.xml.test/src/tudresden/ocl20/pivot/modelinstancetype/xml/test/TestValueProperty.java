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
package tudresden.ocl20.pivot.modelinstancetype.xml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.types.TypeConstants;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that test the adaptation of Classes contained in a
 * reference Jar archive.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestValueProperty {

	/** The {@link IModel} used for testing. */
	private static IModel testModel;

	/** The {@link IModelInstance} used for testing. */
	private static IModelInstance testModelInstance;

	/**
	 * <p>
	 * Initializes the test cases of this test suite.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@BeforeClass
	public static void setUp() throws IllegalArgumentException,
			ModelAccessException {

		File modelFile;
		modelFile = TestValueProperty.getFile("model/test01.ecore");

		testModel = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL);
		assertNotNull(testModel);

		File modelInstanceFile;
		modelInstanceFile = TestValueProperty
				.getFile("modelinstance/test01.xml");

		testModelInstance = Ocl2ForEclipseFacade.getModelInstance(
				modelInstanceFile, testModel,
				XmlModelInstanceTypePlugin.PLUGIN_ID);

		assertNotNull(testModelInstance);
	}

	/**
	 * <p>
	 * Cleans the data used by the test cases of this test suite.
	 * </p>
	 */
	@AfterClass
	public static void tearDown() {

		Ocl2ForEclipseFacade.removeModelInstance(testModelInstance);
		Ocl2ForEclipseFacade.removeModel(testModel);
	}

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @return The found {@link File} object.
	 */
	private static File getFile(String path) {

		String filePath;
		filePath = XmlModelInstanceTypeTestPlugin.getDefault().getBundle()
				.getLocation();
		/* Remove 'reference:file:/' */
		filePath = filePath.substring(16);

		filePath += XmlModelInstanceTypeTestPlugin.getDefault().getBundle()
				.getResource(path).getPath().substring(1);

		File constraintFile;
		constraintFile = new File(filePath);

		assertTrue(constraintFile.exists());

		return constraintFile;
	}

	/**
	 * <p>
	 * Tests the adaptation of boolean values defined between XML Tags.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test
	public void testBooleanValue01() throws IllegalArgumentException,
			ModelAccessException, PropertyAccessException,
			PropertyNotFoundException {

		String msg;
		msg = "The adaptation of values contained between XML tags seems to be wrong.";

		Type booleanValueType;
		booleanValueType = testModel.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "package1",
				"BooleanValue" }));
		assertNotNull(msg, booleanValueType);

		Set<IModelInstanceObject> booleanValues;
		booleanValues = testModelInstance.getAllInstances(booleanValueType);

		assertNotNull(booleanValues);
		assertTrue(booleanValues.size() > 0);

		IModelInstanceObject aBooleanValue;
		aBooleanValue = booleanValues.iterator().next();

		Property valueProperty;
		valueProperty = booleanValueType.lookupProperty("value");

		IModelInstanceElement adaptedValue;
		adaptedValue = aBooleanValue.getProperty(valueProperty);

		assertNotNull(msg, adaptedValue);
		assertEquals(msg, TypeConstants.BOOLEAN, adaptedValue.getType());
		assertFalse(msg, adaptedValue.isUndefined());
	}

	/**
	 * <p>
	 * Tests the adaptation of boolean values defined between XML Tags.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test
	public void testEnumerationValue01() throws IllegalArgumentException,
			ModelAccessException, PropertyAccessException,
			PropertyNotFoundException {
	
		String msg;
		msg = "The adaptation of values contained between XML tags seems to be wrong.";
	
		Type enumerationValueType;
		enumerationValueType = testModel.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "package1",
				"EnumerationValue" }));
		assertNotNull(msg, enumerationValueType);
	
		Set<IModelInstanceObject> enumerationValues;
		enumerationValues = testModelInstance
				.getAllInstances(enumerationValueType);
	
		assertNotNull(enumerationValues);
		assertTrue(enumerationValues.size() > 0);
	
		IModelInstanceObject anEnumerationValue;
		anEnumerationValue = enumerationValues.iterator().next();
	
		Property valueProperty;
		valueProperty = enumerationValueType.lookupProperty("value");
	
		IModelInstanceElement adaptedValue;
		adaptedValue = anEnumerationValue.getProperty(valueProperty);
	
		assertNotNull(msg, adaptedValue);
		assertEquals(msg, testModel.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "package1",
				"Enumeration1" })), adaptedValue.getType());
		assertFalse(msg, adaptedValue.isUndefined());
	}

	/**
	 * <p>
	 * Tests the adaptation of boolean values defined between XML Tags.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test
	public void testIntegerValue01() throws IllegalArgumentException,
			ModelAccessException, PropertyAccessException,
			PropertyNotFoundException {

		String msg;
		msg = "The adaptation of values contained between XML tags seems to be wrong.";

		Type integerValueType;
		integerValueType = testModel.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "package1",
				"IntegerValue" }));
		assertNotNull(msg, integerValueType);

		Set<IModelInstanceObject> integerValues;
		integerValues = testModelInstance.getAllInstances(integerValueType);

		assertNotNull(integerValues);
		assertTrue(integerValues.size() > 0);

		IModelInstanceObject anIntegerValue;
		anIntegerValue = integerValues.iterator().next();

		Property valueProperty;
		valueProperty = integerValueType.lookupProperty("value");

		IModelInstanceElement adaptedValue;
		adaptedValue = anIntegerValue.getProperty(valueProperty);

		assertNotNull(msg, adaptedValue);
		assertEquals(msg, TypeConstants.INTEGER, adaptedValue.getType());
		assertFalse(msg, adaptedValue.isUndefined());
	}

	/**
	 * <p>
	 * Tests the adaptation of boolean values defined between XML Tags.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test
	public void testRealValue01() throws IllegalArgumentException,
			ModelAccessException, PropertyAccessException,
			PropertyNotFoundException {

		String msg;
		msg = "The adaptation of values contained between XML tags seems to be wrong.";

		Type realValueType;
		realValueType = testModel.findType(Arrays
				.asList(new String[] { ModelConstants.ROOT_PACKAGE_NAME,
						"package1", "RealValue" }));
		assertNotNull(msg, realValueType);

		Set<IModelInstanceObject> realValues;
		realValues = testModelInstance.getAllInstances(realValueType);

		assertNotNull(realValues);
		assertTrue(realValues.size() > 0);

		IModelInstanceObject aRealValue;
		aRealValue = realValues.iterator().next();

		Property valueProperty;
		valueProperty = realValueType.lookupProperty("value");

		IModelInstanceElement adaptedValue;
		adaptedValue = aRealValue.getProperty(valueProperty);

		assertNotNull(msg, adaptedValue);
		assertEquals(msg, TypeConstants.REAL, adaptedValue.getType());
		assertFalse(msg, adaptedValue.isUndefined());
	}

	/**
	 * <p>
	 * Tests the adaptation of boolean values defined between XML Tags.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test
	public void testStringValue01() throws IllegalArgumentException,
			ModelAccessException, PropertyAccessException,
			PropertyNotFoundException {

		String msg;
		msg = "The adaptation of values contained between XML tags seems to be wrong.";

		Type stringValueType;
		stringValueType = testModel.findType(Arrays
				.asList(new String[] { ModelConstants.ROOT_PACKAGE_NAME,
						"package1", "StringValue" }));
		assertNotNull(msg, stringValueType);

		Set<IModelInstanceObject> stringValues;
		stringValues = testModelInstance.getAllInstances(stringValueType);

		assertNotNull(stringValues);
		assertTrue(stringValues.size() > 0);

		IModelInstanceObject aStringValue;
		aStringValue = stringValues.iterator().next();

		Property valueProperty;
		valueProperty = stringValueType.lookupProperty("value");

		IModelInstanceElement adaptedValue;
		adaptedValue = aStringValue.getProperty(valueProperty);

		assertNotNull(msg, adaptedValue);
		assertEquals(msg, TypeConstants.STRING, adaptedValue.getType());
		assertFalse(msg, adaptedValue.isUndefined());
	}
}