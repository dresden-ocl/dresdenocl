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

import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceString {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin.getLogger(TestModelInstanceString.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** A {@link Type} used in this test class. */
	private static Type type_PrimitiveTypeProviderClass;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_boolean;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_integer;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_real;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_string;

	/** All instances of the <code>PrimitiveTypeProviderClass</code>. */
	private static Set<IModelInstanceObject> instances_PrimitiveTypeProviderClass;

	/** All instances of the {@link PrimitiveTypeKind#STRING}. */
	private static Set<IModelInstanceString> instances_string;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Get a primitive type from the model. */
		type_boolean =
				(PrimitiveType) ModelInstanceTypeTestServices.getInstance()
						.getModelType(PrimitiveTypeKind.BOOLEAN.toString());

		/* Get a primitive type from the model. */
		type_integer =
				(PrimitiveType) ModelInstanceTypeTestServices.getInstance()
						.getModelType(PrimitiveTypeKind.INTEGER.toString());

		/* Get a primitive type from the model. */
		type_real =
				(PrimitiveType) ModelInstanceTypeTestServices.getInstance()
						.getModelType(PrimitiveTypeKind.REAL.toString());

		/* Get a primitive type from the model. */
		type_string =
				(PrimitiveType) ModelInstanceTypeTestServices.getInstance()
						.getModelType(PrimitiveTypeKind.STRING.toString());

		/* Get the PrimitiveType's provider class from the model. */
		type_PrimitiveTypeProviderClass =
				ModelInstanceTypeTestServices.getInstance().getModelType(
						TestModelTypesNames.TYPE_NAME_PRIMITIVE_TYPE_PROVIDER_CLASS);

		/* Load all instances of the type from the model instance. */
		instances_PrimitiveTypeProviderClass =
				ModelInstanceTypeTestServices.getInstance()
						.getModelInstanceObjectsOfType(type_PrimitiveTypeProviderClass);

		/* Check if any provider class instance has been found. */
		if (instances_PrimitiveTypeProviderClass.size() != 0) {

			List<Property> stringProperties;
			stringProperties = new ArrayList<Property>();

			/* Get the string properties from the provider class. */
			for (Property aProperty : type_PrimitiveTypeProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("string")) {
					stringProperties.add(aProperty);
				}
				// no else.
			}

			instances_string = new HashSet<IModelInstanceString>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_PrimitiveTypeProviderClass) {

				for (Property aStringProperty : stringProperties) {
					IModelInstanceElement aStringResult;

					try {
						aStringResult = aProviderInstance.getProperty(aStringProperty);

						if (aStringResult != null
								&& aStringResult instanceof IModelInstanceString) {
							instances_string.add((IModelInstanceString) aStringResult);
						}
						// no else.
					}

					catch (PropertyAccessException e) {
						/* Do nothing. */
					}

					catch (PropertyNotFoundException e) {
						/* Do nothing. */
					}
				}
				// end for.
			}
			// end for.

			if (instances_string.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_NoStringInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceString aString : instances_string) {

			IModelInstanceElement anotherString;
			IModelInstanceElement aBoolean;
			IModelInstanceElement anInteger;
			IModelInstanceElement aReal;

			/* Test as type with string type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
				msg = NLS.bind(msg, type_string);

				anotherString = aString.asType(type_string);

				/* The casted element should be a String. */
				assertTrue(msg, anotherString instanceof IModelInstanceString);

				/* The value should depend on the String value. */
				assertEquals(msg, aString.getString(),
						((IModelInstanceString) anotherString).getString());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with boolean type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
				msg = NLS.bind(msg, type_boolean);

				aBoolean = aString.asType(type_boolean);

				/* The casted element should be a Boolean. */
				assertTrue(msg, aBoolean instanceof IModelInstanceBoolean);

				/* The value should depend on the String value. */
				if (aString.getString().toLowerCase().equals("true")) {
					assertTrue(msg, ((IModelInstanceBoolean) aBoolean).getBoolean());
				}

				else if (aString.getString().toLowerCase().equals("false")) {
					assertFalse(msg, ((IModelInstanceBoolean) aBoolean).getBoolean());
				}

				else {
					assertNull(msg, ((IModelInstanceBoolean) aBoolean).getBoolean());
				}
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with integer type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
				msg = NLS.bind(msg, type_integer);

				anInteger = aString.asType(type_integer);

				/* The casted element should be an Integer. */
				assertTrue(msg, anInteger instanceof IModelInstanceInteger);

				/* The value should depend on the String value. */
				try {
					assertEquals(msg, (Long) Long.parseLong(aString.getString()),
							((IModelInstanceInteger) anInteger).getLong());
				}

				catch (NumberFormatException e) {
					assertNull(msg, ((IModelInstanceInteger) anInteger).getLong());
				}
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with real type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
				msg = NLS.bind(msg, type_real);

				aReal = aString.asType(type_real);

				/* The casted element should be an Real. */
				assertTrue(msg, aReal instanceof IModelInstanceReal);

				/* The value should depend on the String value. */
				try {
					assertEquals(msg, (Double) Double.parseDouble(aString.getString()),
							((IModelInstanceReal) aReal).getDouble());
				}

				catch (NumberFormatException e) {
					assertNull(msg, ((IModelInstanceReal) aReal).getDouble());
				}
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		for (IModelInstanceString aString : instances_string) {

			aString.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceString aString : instances_string) {

			IModelInstanceElement anotherString;
			IModelInstanceElement aBoolean;
			IModelInstanceElement anInteger;
			IModelInstanceElement aReal;

			/* Test recast with boolean type. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
			msg = NLS.bind(msg, type_boolean);

			aBoolean = aString.asType(type_boolean);
			anotherString = aBoolean.asType(type_string);

			/* The casted element should be a String. */
			assertTrue(msg, anotherString instanceof IModelInstanceString);

			/* The value should depend on the boolean value. */
			if (aBoolean.isUndefined()) {
				assertTrue(msg, anotherString.isUndefined());
			}

			else {
				assertEquals(msg, ((IModelInstanceBoolean) aBoolean).getBoolean()
						.toString(), ((IModelInstanceString) anotherString).getString());
			}

			/* Test recast with integer type. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
			msg = NLS.bind(msg, type_integer);

			anInteger = aString.asType(type_integer);
			anotherString = anInteger.asType(type_string);

			/* The casted element should be a String. */
			assertTrue(msg, anotherString instanceof IModelInstanceString);

			/* The value should depend on the integer value. */
			if (anInteger.isUndefined()) {
				assertTrue(msg, anotherString.isUndefined());
			}

			else {
				assertEquals(msg, ((IModelInstanceInteger) anInteger).getLong()
						.toString(), ((IModelInstanceString) anotherString).getString());
			}

			/* Test recast with real type. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_AsTypeIsWrong;
			msg = NLS.bind(msg, type_real);

			aReal = aString.asType(type_real);
			anotherString = aReal.asType(type_string);

			/* The casted element should be a String. */
			assertTrue(msg, anotherString instanceof IModelInstanceString);

			/* The value should depend on the real value. */
			if (aReal.isUndefined()) {
				assertTrue(msg, anotherString.isUndefined());
			}

			else {
				assertEquals(msg, ((IModelInstanceReal) aReal).getDouble().toString(),
						((IModelInstanceString) anotherString).getString());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_CopyForAtPreIsWrong;

		/* A String should be copy-able. */
		for (IModelInstanceString aString : instances_string) {

			try {
				assertNotNull(msg, aString.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_EqualsIsWrong;

		for (IModelInstanceString aString : instances_string) {

			for (IModelInstanceString anotherString : instances_string) {

				if (aString.getString().equals(anotherString.getString())) {
					assertTrue(msg, aString.equals(anotherString));
				}

				else {
					assertFalse(msg, aString.equals(anotherString));
				}
				// end else.

				/* No string should be equal to null. */
				assertFalse(msg, aString.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_GetTypesIsWrong;

		for (IModelInstanceString aString : instances_string) {

			/* A String should have exactly one type. */
			assertEquals(msg, 1, aString.getTypes().size());

			/* A String should have the PrimitiveType of the kind String. */
			for (Type type : aString.getTypes()) {

				assertTrue(msg, type instanceof PrimitiveType);
				assertEquals(msg, PrimitiveTypeKind.STRING, ((PrimitiveType) type)
						.getKind());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#getString()}.
	 * </p>
	 */
	@Test
	public void testGetString() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_GetStringIsWrong;

		/* The method should return a String or should be undefined. */
		for (IModelInstanceString aString : instances_string) {

			if (aString.isUndefined()) {
				assertNull(msg, aString.getString());
			}

			else {
				assertNotNull(msg, aString.getString());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceString#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceString_IsUndefinedIsWrong;

		/* The method should return null if the String is undefined. */
		for (IModelInstanceString aString : instances_string) {

			if (aString.isUndefined()) {
				assertNull(msg, aString.getString());
			}

			else {
				assertNotNull(msg, aString.getString());
			}
		}
		// end for.
	}
}