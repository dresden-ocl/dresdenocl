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
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceInteger {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin.getLogger(TestModelInstanceInteger.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** A {@link Type} used in this test class. */
	private static Type type_PrimitiveTypeProviderClass;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_integer;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_real;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_string;

	/** All instances of the <code>PrimitiveTypeProviderClass</code>. */
	private static Set<IModelInstanceObject> instances_PrimitiveTypeProviderClass;

	/** All instances of the {@link PrimitiveTypeKind#INTEGER}. */
	private static Set<IModelInstanceInteger> instances_integer;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

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

			List<Property> integerProperties;
			integerProperties = new ArrayList<Property>();

			/* Get the integer properties from the provider class. */
			for (Property aProperty : type_PrimitiveTypeProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("integer")) {
					integerProperties.add(aProperty);
				}
				// no else.
			}

			instances_integer = new HashSet<IModelInstanceInteger>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_PrimitiveTypeProviderClass) {

				for (Property anIntegerProperty : integerProperties) {
					IModelInstanceElement anIntegerResult;

					try {
						anIntegerResult = aProviderInstance.getProperty(anIntegerProperty);

						if (anIntegerResult != null
								&& anIntegerResult instanceof IModelInstanceInteger) {
							instances_integer.add((IModelInstanceInteger) anIntegerResult);
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

			if (instances_integer.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_NoIntegerInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceInteger anInteger : instances_integer) {

			IModelInstanceElement anotherInteger;
			IModelInstanceElement aReal;
			IModelInstanceElement aString;

			/* Test as type with integer type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_AsTypeIsWrong;
				msg = NLS.bind(msg, type_integer);

				anotherInteger = anInteger.asType(type_integer);

				/* The casted element should be an Integer. */
				assertTrue(msg, anInteger instanceof IModelInstanceInteger);

				/* The value should depend on the Integer value. */
				assertEquals(msg, anInteger.getLong(),
						((IModelInstanceInteger) anotherInteger).getLong());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with string type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_AsTypeIsWrong;
				msg = NLS.bind(msg, type_string);

				aString = anInteger.asType(type_string);

				/* The casted element should be a String. */
				assertTrue(msg, aString instanceof IModelInstanceString);

				/* The value should depend on the Integer value. */
				assertEquals(msg, anInteger.getLong().toString(),
						((IModelInstanceString) aString).getString());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with real type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_AsTypeIsWrong;
				msg = NLS.bind(msg, type_real);

				aReal = anInteger.asType(type_real);

				/* The casted element should be a Real. */
				assertTrue(msg, aReal instanceof IModelInstanceReal);

				/* The value should depend on the Integer value. */
				assertEquals(msg, new Double(anInteger.getLong()),
						((IModelInstanceReal) aReal).getDouble());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		for (IModelInstanceInteger anInteger : instances_integer) {

			anInteger.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceInteger anInteger : instances_integer) {

			IModelInstanceElement anotherInteger;

			/* Test recast from string. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_AsTypeIsWrong;
			msg = NLS.bind(msg, type_string);

			anotherInteger = anInteger.asType(type_string).asType(type_integer);

			/* The casted element should be an integer. */
			assertTrue(msg, anotherInteger instanceof IModelInstanceInteger);

			/* The value should be equal. */
			assertEquals(msg, anInteger, anotherInteger);

			/* Test recast from real. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_AsTypeIsWrong;
			msg = NLS.bind(msg, type_real);

			anotherInteger = anInteger.asType(type_real).asType(type_integer);

			/* The casted element should be an integer. */
			assertTrue(msg, anotherInteger instanceof IModelInstanceInteger);

			/* The value should be equal. */
			assertEquals(msg, anInteger, anotherInteger);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_CopyForAtPreIsWrong;

		/* An Integer should be copy-able. */
		for (IModelInstanceInteger anInteger : instances_integer) {

			try {
				assertNotNull(msg, anInteger.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_EqualsIsWrong;

		for (IModelInstanceInteger anInteger : instances_integer) {

			for (IModelInstanceInteger anotherInteger : instances_integer) {

				if (anInteger.getLong().equals(anotherInteger.getLong())) {
					assertTrue(msg, anInteger.equals(anotherInteger));
				}

				else {
					assertFalse(msg, anInteger.equals(anotherInteger));
				}
				// end else.

				/* No integer should be equal to null. */
				assertFalse(msg, anInteger.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#getInteger()}.
	 * </p>
	 */
	@Test
	public void testGetInteger() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_GetIntegerIsWrong;

		/* The method should return an Integer or should be undefined. */
		for (IModelInstanceInteger anInteger : instances_integer) {

			if (anInteger.isUndefined()) {
				assertNull(msg, anInteger.getLong());
			}

			else {
				assertNotNull(msg, anInteger.getLong());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceInteger#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_GetTypesIsWrong;

		for (IModelInstanceInteger anInteger : instances_integer) {

			assertNotNull(msg, anInteger.getType());

			/* An Integer should have the PrimitiveType of the kind Integer. */
			assertTrue(msg, anInteger.getType() instanceof PrimitiveType);
			assertEquals(msg, PrimitiveTypeKind.INTEGER, ((PrimitiveType) anInteger
					.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceInteger_IsUndefinedIsWrong;

		/* The method should return null if the Integer is undefined. */
		for (IModelInstanceInteger anInteger : instances_integer) {

			if (anInteger.isUndefined()) {
				assertNull(msg, anInteger.getLong());
			}

			else {
				assertNotNull(msg, anInteger.getLong());
			}
		}
		// end for.
	}
}