/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = ModelInstanceTypeTestPlugin
			.getLogger(TestModelInstanceObject.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** The type {@link Class1} used in this test class. */
	private static Type type_Class1;

	/** The type {@link Class2} used in this test class. */
	private static Type type_Class2;

	/** The type {@link Interface1} used in this test class. */
	private static Type type_Interface1;

	/** The type {@link Interface2} used in this test class. */
	private static Type type_Interface2;

	/** The type {@link Interface3} used in this test class. */
	private static Type type_Interface3;

	/** The type {@link CopyableClass} used in this test class. */
	private static Type type_CopyableClass;

	/** The type {@link NonCopyableClass} used in this test class. */
	private static Type type_NonCopyableClass;

	/** All instances of the {@link Class1}. */
	private static Set<IModelInstanceObject> instances_Class1;

	/** All instances of the {@link Class2}. */
	private static Set<IModelInstanceObject> instances_Class2;

	/** All instances of the {@link Interface1}. */
	private static Set<IModelInstanceObject> instances_Interface1;

	/** All instances of the {@link Interface2}. */
	private static Set<IModelInstanceObject> instances_Interface2;

	/** All instances of the {@link Interface3}. */
	private static Set<IModelInstanceObject> instances_Interface3;

	/** All instances of the {@link CopyableClass}. */
	private static Set<IModelInstanceObject> instances_CopyableClass;

	/** All instances of the {@link NonCopyableClass}. */
	private static Set<IModelInstanceObject> instances_NonCopyableClass;

	/** All instances of implemented classes. */
	private static Set<IModelInstanceObject> instances_AllClasses;

	/** All instances of implemented interfaces. */
	private static Set<IModelInstanceObject> instances_AllInterfaces;

	/** All instances of implemented types. */
	private static Set<IModelInstanceObject> instances_AllTypes;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Find the type in the Model. */
		type_Class1 = ModelInstanceTypeTestServices.getInstance().getModelType(
				TestModelTypesNames.TYPE_NAME_CLASS1);

		/* Load all instances of the type from the model instance. */
		instances_Class1 = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_Class1);

		if (instances_Class1.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoClass1InstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_Class2 = ModelInstanceTypeTestServices.getInstance().getModelType(
				TestModelTypesNames.TYPE_NAME_CLASS2);

		/* Load all instances of the type from the model instance. */
		instances_Class2 = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_Class2);

		if (instances_Class2.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoClass2InstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_Interface1 = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_INTERFACE1);

		/* Load all instances of the type from the model instance. */
		instances_Interface1 = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_Interface1);

		if (instances_Interface1.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoInterface1InstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_Interface2 = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_INTERFACE2);

		/* Load all instances of the type from the model instance. */
		instances_Interface2 = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_Interface2);

		if (instances_Interface2.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoInterface2InstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_Interface3 = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_INTERFACE3);

		/* Load all instances of the type from the model instance. */
		instances_Interface3 = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_Interface3);

		if (instances_Interface3.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoInterface3InstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_CopyableClass = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_COPYABLE_CLASS);

		/* Load all instances of the type from the model instance. */
		instances_CopyableClass = ModelInstanceTypeTestServices.getInstance()
				.getModelInstanceObjectsOfType(type_CopyableClass);

		if (instances_CopyableClass.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoCopyableClassInstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		/* Find the type in the Model. */
		type_NonCopyableClass = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_NON_COPYABLE_CLASS);

		/* Load all instances of the type from the model instance. */
		instances_NonCopyableClass = ModelInstanceTypeTestServices
				.getInstance().getModelInstanceObjectsOfType(
						type_NonCopyableClass);

		if (instances_NonCopyableClass.size() == 0 && LOGGER.isDebugEnabled()) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoNonCopyableClassInstanceFound;

			LOGGER.warn(msg);
		}
		// no else.

		instances_AllClasses = new HashSet<IModelInstanceObject>();
		instances_AllClasses.addAll(instances_Class1);
		instances_AllClasses.addAll(instances_CopyableClass);
		instances_AllClasses.addAll(instances_NonCopyableClass);

		instances_AllInterfaces = new HashSet<IModelInstanceObject>();
		instances_AllInterfaces.addAll(instances_Interface1);
		instances_AllInterfaces.addAll(instances_Interface2);
		instances_AllInterfaces.addAll(instances_Interface3);

		instances_AllTypes = new HashSet<IModelInstanceObject>();
		instances_AllTypes.addAll(instances_AllClasses);
		instances_AllTypes.addAll(instances_AllInterfaces);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} for instances
	 * of {@link Class1}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceElement castedElement;

			/* Class1 instances should be cast-able to itself. */
			try {
				castedElement = anElement.asType(type_Class1);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Class1. */
				assertEquals(msg, type_Class1, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} for instances
	 * of {@link Class2}.
	 * </p>
	 */
	@Test
	public void testAsType02() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Class2) {

			IModelInstanceElement castedElement;

			/* Class2 instances should be cast-able to itself. */
			try {
				castedElement = anElement.asType(type_Class2);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Class1. */
				assertEquals(msg, type_Class2, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Class2 instances should be cast-able to Class1. */
			try {
				castedElement = anElement.asType(type_Class1);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Class1. */
				assertEquals(msg, type_Class1, castedElement.getType());

				/* The casted Class1 should be re-cast-able to Class2. */
				castedElement = castedElement.asType(type_Class2);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Class2. */
				assertEquals(msg, type_Class2, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} for instances
	 * of {@link Interface1}.
	 * </p>
	 */
	@Test
	public void testAsType03() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Interface1) {

			IModelInstanceElement castedElement;

			/* Interface1 instances should be cast-able to itself. */
			try {
				castedElement = anElement.asType(type_Interface1);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Class1. */
				assertEquals(msg, type_Interface1, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} for instances
	 * of {@link Interface2}.
	 * </p>
	 */
	@Test
	public void testAsType04() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Interface2) {

			IModelInstanceElement castedElement;

			/* Interface2 instances should be cast-able to itself. */
			try {
				castedElement = anElement.asType(type_Interface2);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Interface2. */
				assertEquals(msg, type_Interface2, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} for instances
	 * of {@link Interface3}.
	 * </p>
	 */
	@Test
	public void testAsType05() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Interface3) {

			IModelInstanceElement castedElement;

			/* Interface3 instances should be cast-able to itself. */
			try {
				castedElement = anElement.asType(type_Interface3);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Interface3. */
				assertEquals(msg, type_Interface3, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Interface3 instances should be cast-able to Interface2. */
			try {
				castedElement = anElement.asType(type_Interface2);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Interface2. */
				assertEquals(msg, type_Interface2, castedElement.getType());

				/* The casted Interface2 should be re-cast-able to Interface3. */
				castedElement = castedElement.asType(type_Interface3);

				/* The casted element should be an IModelInstanceObject. */
				assertTrue(msg, castedElement instanceof IModelInstanceObject);

				/* The value should remain the same. */
				assertEquals(msg, ((IModelInstanceObject) anElement)
						.getObject(), ((IModelInstanceObject) castedElement)
						.getObject());

				/* The casted element should have exactly one type. */
				assertNotNull(msg, castedElement.getType());

				/* The casted element should have the type Interface3. */
				assertEquals(msg, type_Interface3, castedElement.getType());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType06() throws AsTypeCastException {

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_AllClasses) {
			anElement.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#asType(Type)} in combination
	 * with the method {@link IModelInstanceObject#getProperty(Property)}.
	 * </p>
	 */
	@Test
	public void testAsTypeAndGetProperty() {

		Property class1property;
		Property class2property;

		IModelInstanceElement castedElement;
		IModelInstanceElement class1PropertyValue;
		IModelInstanceElement class2PropertyValue;

		/* Find the properties in the model. */
		class1property = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("nonMultipleProperty")) {
				class1property = property;
				break;
			}
			// no else.
		}

		class2property = null;

		for (Property property : type_Class2.allProperties()) {

			if (property.getName().equals("nonMultipleProperty")) {
				class2property = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_AsTypeGetPropertyIsWrong;

		/* An IModelInstanceObject should cast-able to all implemented types. */
		for (IModelInstanceElement anElement : instances_Class2) {

			/* Cast Class2 instances to Class1. */
			try {
				/* Get the property value of class2. */
				class2PropertyValue = ((IModelInstanceObject) anElement)
						.getProperty(class2property);

				/* Get the property value of class1 (after cast). */
				castedElement = anElement.asType(type_Class1);
				class1PropertyValue = ((IModelInstanceObject) castedElement)
						.getProperty(class1property);

				/* Both values should have the right type. */
				assertNotNull(msg, class2PropertyValue.getType());
				assertEquals(msg, TypeConstants.STRING, class2PropertyValue
						.getType());

				assertNotNull(msg, class1PropertyValue.getType());
				assertEquals(msg, TypeConstants.STRING, class1PropertyValue
						.getType());

				/* Both values should not be equal. */
				if (((IModelInstanceString) class1PropertyValue).getString()
						.equals(
								((IModelInstanceString) class2PropertyValue)
										.getString())
						&& LOGGER.isDebugEnabled()) {

					/*
					 * Only warn and not fail here. Some model instance types
					 * cannot support this mechanism. E.g., EMF Ecore.
					 */
					LOGGER.warn(msg);
				}
				// no else.
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			catch (PropertyAccessException e) {
				fail(msg);
			}

			catch (PropertyNotFoundException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_CopyForAtPreIsWrong;

		/* An CopyableClass instance should be copy-able. */
		for (IModelInstanceElement anElement : instances_CopyableClass) {

			try {
				assertNotNull(msg, anElement.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.

		/* An NonCopyableClass instance should not be copy-able. */
		for (IModelInstanceElement anElement : instances_NonCopyableClass) {

			try {
				anElement.copyForAtPre();
				fail(msg);
			}

			catch (CopyForAtPreException e) {
				/* Expected exception. */
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_EqualsIsWrong;

		/* An CopyableClass instance should be copy-able. */
		for (IModelInstanceObject anElement : instances_Class1) {

			/*
			 * An instance of class1 should equal to another instance of class1
			 * if they have the same type(s) and the same adapted object.
			 */
			for (IModelInstanceObject anotherElement : instances_Class1) {

				if (anElement.getType() != null
						&& anotherElement.getType() != null
						&& anElement.getType().equals(anotherElement.getType())
						&& ((anElement.getObject() == null && anotherElement
								.getObject() == null) || (anElement.getObject() != null && anElement
								.getObject().equals(anotherElement.getObject())))) {
					assertTrue(msg, anElement.equals(anotherElement));
				}

				else {
					assertFalse(msg, anElement.equals(anotherElement));
				}
				// end else.

				/* No object should be equal to null. */
				assertFalse(msg, anElement.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} without arguments and without result (void).
	 * </p>
	 */
	@Test
	public void testInvokeOperation01() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "voidOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have the same type as the operation. */
				assertEquals(msg, operation.getType(), operationResult
						.getType());

				/* The result should be void. */
				assertTrue(msg, operationResult instanceof IModelInstanceVoid);
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with one model instance argument and with single
	 * result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation02() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "nonMultipleOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have the same type as the operation. */
				assertEquals(msg, operation.getType(), operationResult
						.getType());

				/* The result should be an IModelInstanceElement. */
				assertTrue(msg,
						operationResult instanceof IModelInstanceElement);
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with no arguments and with a multiple, unique,
	 * ordered result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation03() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "multipleUniqueOrderedOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have an ordered set type. */
				assertEquals(msg, TypeConstants.ORDERED_SET, operationResult
						.getType());

				/* The result should be an IModelInstanceCollecttion. */
				assertTrue(msg,
						operationResult instanceof IModelInstanceCollection<?>);

				/* The result should be unique. */
				assertTrue(msg, ((IModelInstanceCollection<?>) operationResult)
						.isUnique());

				/* The result should be ordered. */
				assertTrue(msg, ((IModelInstanceCollection<?>) operationResult)
						.isOrdered());
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with no arguments and with a multiple, unique,
	 * unordered result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation04() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "multipleUniqueUnorderedOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have an set type. */
				assertEquals(msg, TypeConstants.SET, operationResult.getType());

				/* The result should be an IModelInstanceCollecttion. */
				assertTrue(msg,
						operationResult instanceof IModelInstanceCollection<?>);

				/* The result should be unique. */
				assertTrue(msg, ((IModelInstanceCollection<?>) operationResult)
						.isUnique());

				/* The result should not be ordered. */
				assertFalse(msg,
						((IModelInstanceCollection<?>) operationResult)
								.isOrdered());
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with no arguments and with a multiple,
	 * nonunique, ordered result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation05() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "multipleNonuniqueOrderedOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have an sequence type. */
				assertEquals(msg, TypeConstants.SEQUENCE, operationResult
						.getType());

				/* The result should be an IModelInstanceCollecttion. */
				assertTrue(msg,
						operationResult instanceof IModelInstanceCollection<?>);

				/* The result should not be unique. */
				assertFalse(msg,
						((IModelInstanceCollection<?>) operationResult)
								.isUnique());

				/* The result should be ordered. */
				assertTrue(msg, ((IModelInstanceCollection<?>) operationResult)
						.isOrdered());
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with no arguments and with a multiple,
	 * nonunique, unordered result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation06() {

		Operation operation;
		String operationName;

		List<IModelInstanceElement> args;
		IModelInstanceElement operationResult;

		/* Try to find the operation. */
		operationName = "multipleNonuniqueUnorderedOperation";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		/* Initialize the arguments. */
		args = new ArrayList<IModelInstanceElement>();

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			try {
				operationResult = anObject.invokeOperation(operation, args);

				/* The result should not be null. */
				assertNotNull(msg, operationResult);

				/* The result should have exactly one type. */
				assertNotNull(msg, operationResult.getType());

				/* The result should have an bag type. */
				assertEquals(msg, TypeConstants.BAG, operationResult.getType());

				/* The result should be an IModelInstanceCollecttion. */
				assertTrue(msg,
						operationResult instanceof IModelInstanceCollection<?>);

				/* The result should not be unique. */
				assertFalse(msg,
						((IModelInstanceCollection<?>) operationResult)
								.isUnique());

				/* The result should not be ordered. */
				assertFalse(msg,
						((IModelInstanceCollection<?>) operationResult)
								.isOrdered());
			}

			catch (OperationAccessException e) {
				fail(msg + " " + e.getMessage());
			}

			catch (OperationNotFoundException e) {
				msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
				msg = NLS.bind(msg, operationName);

				LOGGER.warn(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link PrimitiveType} and
	 * without result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation07() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyBooleanNonMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoPrimitiveTypeInstanceFound;
			msg = NLS.bind(msg, PrimitiveTypeKind.BOOLEAN.getName());

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithBooleanArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link PrimitiveType} and
	 * without result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation08() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyStringNonMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoPrimitiveTypeInstanceFound;
			msg = NLS.bind(msg, PrimitiveTypeKind.STRING.getName());

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithStringArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link CollectionType} and
	 * without result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation09() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyBooleanMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoPrimitiveTypeInstanceFound;
			msg = NLS.bind(msg, PrimitiveTypeKind.BOOLEAN.getName());

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithBooleanMultipleArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link CollectionType} and
	 * without result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation10() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyStringMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoPrimitiveTypeInstanceFound;
			msg = NLS.bind(msg, PrimitiveTypeKind.STRING.getName());

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithStringMultipleArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link Type} and without
	 * result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation11() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyObjectNonMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoClass1InstanceFound2;

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithObjectArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link Type} and without
	 * result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation12() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals("argumentPropertyObjectMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoClass1InstanceFound2;

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithObjectMultipleArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link Type} and without
	 * result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation13() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a enumeration literal argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals(
					"argumentPropertyEnumerationLiteralNonMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoEnumeration1InstanceFound2;

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithEnumerationLiteralArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with an argument of a {@link Type} and without
	 * result.
	 * </p>
	 */
	@Test
	public void testInvokeOperation14() {

		Property argumentProperty;
		IModelInstanceElement argument1;

		argument1 = null;

		/* Try to load a boolean argument. Probably fail. */
		for (Property property : type_Class1.getOwnedProperty()) {
			if (property.getName().equals(
					"argumentPropertyEnumerationLiteralMultiple")) {
				argumentProperty = property;
				try {
					argument1 = instances_Class1.iterator().next().getProperty(
							argumentProperty);
					break;
				}

				catch (PropertyAccessException e) {
					/* Do nothing, fail later on. */
				}

				catch (PropertyNotFoundException e) {
					/* Do nothing, fail later on. */
				}
			}
			// no else.
		}
		// end for.

		if (argument1 == null) {
			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_NoEnumeration1InstanceFound2;

			fail(msg);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(msg);
			}
			// no else.
		}

		else {
			Operation operation;
			String operationName;

			List<IModelInstanceElement> args;
			IModelInstanceElement operationResult;

			/* Try to find the operation. */
			operationName = "voidOperationWithEnumerationLiteralMultipleArgument";
			operation = null;

			for (Operation anOperation : type_Class1.allOperations()) {

				if (anOperation.getName().equals(operationName)) {
					operation = anOperation;
					break;
				}
				// no else.
			}

			/* Initialize the arguments. */
			args = new ArrayList<IModelInstanceElement>();

			/* Add a boolean argument. */
			args.add(argument1);

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_InvokeOperationIsWrong;

			for (IModelInstanceElement anElement : instances_Class1) {

				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					operationResult = anObject.invokeOperation(operation, args);

					/* The result should not be null. */
					assertNotNull(msg, operationResult);

					/* The result should have exactly one type. */
					assertNotNull(msg, operationResult.getType());

					/* The result should be void. */
					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(msg,
							operationResult instanceof IModelInstanceVoid);
				}

				catch (OperationAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (OperationNotFoundException e) {
					msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_OperationNotFound;
					msg = NLS.bind(msg, operationName);

					LOGGER.warn(msg);
				}
			}
			// end for.
		}
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with illegal arguments.
	 * </p>
	 * 
	 * @throws OperationAccessException
	 * @throws OperationNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvokeOperation15() throws OperationNotFoundException,
			OperationAccessException {

		Operation operation;
		String operationName;

		/* Try to find the operation. */
		operationName = "voidOperationWithEnumerationLiteralMultipleArgument";
		operation = null;

		for (Operation anOperation : type_Class1.allOperations()) {

			if (anOperation.getName().equals(operationName)) {
				operation = anOperation;
				break;
			}
			// no else.
		}

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			anObject.invokeOperation(operation, null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * for an {@link Operation} with illegal arguments.
	 * </p>
	 * 
	 * @throws OperationAccessException
	 * @throws OperationNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testInvokeOperation16() throws OperationNotFoundException,
			OperationAccessException {

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			anObject.invokeOperation(null,
					new ArrayList<IModelInstanceElement>());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * for a non multiple property.
	 * </p>
	 */
	@Test
	public void testGetProperty01() {

		Property aProperty;
		IModelInstanceElement aPropertyValue;

		aProperty = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("nonMultipleProperty")) {
				aProperty = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetPropertyIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			if (!anElement.isUndefined()) {
				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					aPropertyValue = anObject.getProperty(aProperty);

					/* The property should not be null. */
					assertNotNull(msg, aPropertyValue);

					/* The result should have at least one type. */
					assertNotNull(msg, aPropertyValue.getType());

					/* The result should have the same type as the property. */
					assertEquals(msg, aProperty.getType(), aPropertyValue
							.getType());

					/* The result should be and IModelInstanceElement. */
					assertTrue(msg,
							aPropertyValue instanceof IModelInstanceElement);
				}

				catch (PropertyAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (PropertyNotFoundException e) {
					fail(msg + " " + e.getMessage());
				}
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * for a multiple, ordered, unique property.
	 * </p>
	 */
	@Test
	public void testGetProperty02() {

		Property aProperty;
		IModelInstanceElement aPropertyValue;

		aProperty = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("multipleUniqueOrderedProperty")) {
				aProperty = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetPropertyIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			if (!anElement.isUndefined()) {
				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					aPropertyValue = anObject.getProperty(aProperty);

					/* The property should not be null. */
					assertNotNull(msg, aPropertyValue);

					/* The result should have exactly one type. */
					assertNotNull(msg, aPropertyValue.getType());

					/* The result should have an ordered set type. */
					assertEquals(msg, TypeConstants.ORDERED_SET, aPropertyValue
							.getType());

					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(
							msg,
							aPropertyValue instanceof IModelInstanceCollection<?>);

					/* The result should be unique. */
					assertTrue(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isUnique());

					/* The result should be ordered. */
					assertTrue(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isOrdered());
				}

				catch (PropertyAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (PropertyNotFoundException e) {
					fail(msg + " " + e.getMessage());
				}
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * for a multiple, unordered, unique property.
	 * </p>
	 */
	@Test
	public void testGetProperty03() {

		Property aProperty;
		IModelInstanceElement aPropertyValue;

		aProperty = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("multipleUniqueUnorderedProperty")) {
				aProperty = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetPropertyIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			if (!anElement.isUndefined()) {
				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					aPropertyValue = anObject.getProperty(aProperty);

					/* The property should not be null. */
					assertNotNull(msg, aPropertyValue);

					/* The result should have exactly one type. */
					assertNotNull(msg, aPropertyValue.getType());

					/* The result should have an unordered set type. */
					assertEquals(msg, TypeConstants.SET, aPropertyValue
							.getType());

					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(
							msg,
							aPropertyValue instanceof IModelInstanceCollection<?>);

					/* The result should be unique. */
					assertTrue(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isUnique());

					/* The result should be unordered. */
					assertFalse(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isOrdered());
				}

				catch (PropertyAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (PropertyNotFoundException e) {
					fail(msg + " " + e.getMessage());
				}
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * for a multiple, ordered, nonunique property.
	 * </p>
	 */
	@Test
	public void testGetProperty04() {

		Property aProperty;
		IModelInstanceElement aPropertyValue;

		aProperty = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("multipleNonuniqueOrderedProperty")) {
				aProperty = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetPropertyIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			if (!anElement.isUndefined()) {
				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					aPropertyValue = anObject.getProperty(aProperty);

					/* The property should not be null. */
					assertNotNull(msg, aPropertyValue);

					/* The result should have exactly one type. */
					assertNotNull(msg, aPropertyValue.getType());

					/* The result should have an ordered list type. */
					assertEquals(msg, TypeConstants.SEQUENCE, aPropertyValue
							.getType());

					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(
							msg,
							aPropertyValue instanceof IModelInstanceCollection<?>);

					/* The result should be unique. */
					assertFalse(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isUnique());

					/* The result should be ordered. */
					assertTrue(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isOrdered());
				}

				catch (PropertyAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (PropertyNotFoundException e) {
					fail(msg + " " + e.getMessage());
				}
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * for a multiple, unordered, nonunique property.
	 * </p>
	 */
	@Test
	public void testGetProperty05() {

		Property aProperty;
		IModelInstanceElement aPropertyValue;

		aProperty = null;

		for (Property property : type_Class1.allProperties()) {

			if (property.getName().equals("multipleNonuniqueUnorderedProperty")) {
				aProperty = property;
				break;
			}
			// no else.
		}

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetPropertyIsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			if (!anElement.isUndefined()) {
				IModelInstanceObject anObject;
				anObject = (IModelInstanceObject) anElement;

				try {
					aPropertyValue = anObject.getProperty(aProperty);

					/* The property should not be null. */
					assertNotNull(msg, aPropertyValue);

					/* The result should have exactly one type. */
					assertNotNull(msg, aPropertyValue.getType());

					/* The result should have an unordered list type. */
					assertEquals(msg, TypeConstants.BAG, aPropertyValue
							.getType());

					/* The result should be an IModelInstanceCollecttion. */
					assertTrue(
							msg,
							aPropertyValue instanceof IModelInstanceCollection<?>);

					/* The result should be non-unique. */
					assertFalse(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isUnique());

					/* The result should be unordered. */
					assertFalse(msg,
							((IModelInstanceCollection<?>) aPropertyValue)
									.isOrdered());
				}

				catch (PropertyAccessException e) {
					fail(msg + " " + e.getMessage());
				}

				catch (PropertyNotFoundException e) {
					fail(msg + " " + e.getMessage());
				}
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * with illegal arguments.
	 * </p>
	 * 
	 * @throws PropertyNotFoundException
	 * @throws PropertyAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetProperty06() throws PropertyAccessException,
			PropertyNotFoundException {

		for (IModelInstanceElement anElement : instances_Class1) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			anObject.getProperty(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getTypes()} for instances of
	 * {@link Class1}.
	 * </p>
	 */
	@Test
	public void testGetTypes1() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesIsWrong;
		msg += " "
				+ ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesOfClass1IsWrong;

		for (IModelInstanceElement anElement : instances_Class1) {

			/* An Class1 instance should have at least one type. */
			assertNotNull(msg, anElement.getType());

			/*
			 * A Class1 instance should have Class1 as Type or should be an
			 * instance of Class2.
			 */
			if (!anElement.getType().conformsTo(type_Class1)
					&& !anElement.getType().conformsTo(type_Class2)) {

				fail(msg);
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getTypes()} for instances of
	 * {@link Class2}.
	 * </p>
	 */
	@Test
	public void testGetTypes2() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesIsWrong;
		msg += " "
				+ ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesOfClass2IsWrong;

		for (IModelInstanceElement anElement : instances_Class2) {

			/* An Class2 instance should have at least one type. */
			assertNotNull(msg, anElement.getType());

			/*
			 * A Class2 should have Class2 as Type and not should have Class1 as
			 * Type
			 */
			if (!anElement.getType().conformsTo(type_Class2)
					|| anElement.getType().equals(type_Class1)) {

				fail(msg);
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getTypes()} for instances of
	 * {@link Interface1}.
	 * </p>
	 */
	@Test
	public void testGetTypes3() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesIsWrong;
		msg += " "
				+ ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesOfInterface1IsWrong;

		for (IModelInstanceElement anElement : instances_Interface1) {

			/* An Interface1 instance should have at least one type. */
			assertNotNull(msg, anElement.getType());

			/* An Interface1 should have Interface1 as Type. */
			if (!anElement.getType().conformsTo(type_Interface1)) {

				fail(msg);
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getTypes()} for instances of
	 * {@link Interface2}.
	 * </p>
	 */
	@Test
	public void testGetTypes4() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesIsWrong;
		msg += " "
				+ ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesOfInterface2IsWrong;

		for (IModelInstanceElement anElement : instances_Interface2) {

			/* An Interface2 instance should have at least one type. */
			assertNotNull(msg, anElement.getType());

			/*
			 * An Interface2 instance should have Interface2 as Type XOR the
			 * Interface3 as type.
			 */
			if (anElement.getType().conformsTo(type_Interface2)) {

				if (anElement.getType().conformsTo(type_Interface3)) {
					fail(msg);
				}
				// no else.
			}

			else {
				if (!anElement.getType().conformsTo(type_Interface3)) {
					fail(msg);
				}
				// no else.
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getTypes()} for instances of
	 * {@link Interface3}.
	 * </p>
	 */
	@Test
	public void testGetTypes5() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesIsWrong;
		msg += " "
				+ ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetTypesOfInterface3IsWrong;

		for (IModelInstanceElement anElement : instances_Interface3) {

			/* An Interface3 instance should have at least one type. */
			assertNotNull(msg, anElement.getType());

			/*
			 * An Interface2 instance should have Interface3 as Type and not
			 * should have the Interface2 as type.
			 */
			if (!anElement.getType().conformsTo(type_Interface3)
					|| anElement.getType().equals(type_Interface2)) {
				fail(msg);
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#getLiteral()}.
	 * </p>
	 */
	@Test
	public void testGetObject() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_GetObjectIsWrong;

		/* The method should return an object or should be undefined. */
		for (IModelInstanceElement anElement : instances_AllTypes) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			if (anObject.isUndefined()) {
				assertNull(msg, anObject.getObject());
			}

			else {
				assertNotNull(msg, anObject.getObject());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceObject#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_IsUndefinedIsWrong;

		/* The method should return null if the object is undefined. */
		for (IModelInstanceElement anElement : instances_AllTypes) {

			IModelInstanceObject anObject;
			anObject = (IModelInstanceObject) anElement;

			if (anObject.isUndefined()) {
				assertNull(msg, anObject.getObject());
			}

			else {
				assertNotNull(msg, anObject.getObject());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * All {@link Class} instances should be adapted to
	 * {@link IModelInstanceObject}.
	 * </p>
	 */
	@Test
	public void testRightAdaptation() {

		/* The model instance objects should be adapted to the right interface. */
		for (IModelInstanceElement anElement : instances_AllTypes) {

			msg = ModelInstanceTypeTestSuiteMessages.TestModelInstanceObject_WrongAdaptation;

			assertTrue(msg, anElement instanceof IModelInstanceObject);
		}
		// end for.
	}
}