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
package org.dresdenocl.modelinstancetype.test.tests;

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

import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.modelinstancetype.exception.PropertyAccessException;
import org.dresdenocl.modelinstancetype.exception.PropertyNotFoundException;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestServices;
import org.dresdenocl.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import org.dresdenocl.modelinstancetype.test.testmodel.TestModelTypesNames;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.modelinstancetype.types.IModelInstanceReal;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link org.dresdenocl.modelbus.modelinstance.types.IModelInstanceReal}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceReal {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin.getLogger(TestModelInstanceReal.class);

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

	/** All instances of the {@link PrimitiveTypeKind#REAL}. */
	private static Set<IModelInstanceReal> instances_real;

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

			List<Property> realProperties;
			realProperties = new ArrayList<Property>();

			/* Get the real properties from the provider class. */
			for (Property aProperty : type_PrimitiveTypeProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("real")) {
					realProperties.add(aProperty);
				}
				// no else.
			}

			instances_real = new HashSet<IModelInstanceReal>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_PrimitiveTypeProviderClass) {

				for (Property aRealProperty : realProperties) {
					IModelInstanceElement aRealResult;

					try {
						aRealResult = aProviderInstance.getProperty(aRealProperty);

						if (aRealResult != null
								&& aRealResult instanceof IModelInstanceReal) {
							instances_real.add((IModelInstanceReal) aRealResult);
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

			if (instances_real.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_NoRealInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceReal aReal : instances_real) {

			IModelInstanceElement anotherReal;
			IModelInstanceElement anInteger;
			IModelInstanceElement aString;

			/* Test as type with real type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_AsTypeIsWrong;
				msg = NLS.bind(msg, type_real);

				anotherReal = aReal.asType(type_real);

				/* The casted element should be a Real. */
				assertTrue(msg, anotherReal instanceof IModelInstanceReal);

				/* The value should depend on the Real value. */
				assertEquals(msg, aReal.getDouble(), ((IModelInstanceReal) anotherReal)
						.getDouble());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with string type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_AsTypeIsWrong;
				msg = NLS.bind(msg, type_string);

				aString = aReal.asType(type_string);

				/* The casted element should be a String. */
				assertTrue(msg, aString instanceof IModelInstanceString);

				/* The value should depend on the Real value. */
				/*
				 * Cannot compare the values because getDouble uses toDouble(), a method
				 * that can cause rounding errors.
				 */
				// assertEquals(msg, aReal.getDouble().toString(),
				// ((IModelInstanceString) aString).getString());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with integer type. */
			try {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_AsTypeIsWrong;
				msg = NLS.bind(msg, type_integer);

				anInteger = aReal.asType(type_integer);

				/* The casted element should be an Integer. */
				assertTrue(msg, anInteger instanceof IModelInstanceInteger);

				/* The value should depend on the Real value. */
				assertEquals(msg, (Long) aReal.getDouble().longValue(),
						((IModelInstanceInteger) anInteger).getLong());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		for (IModelInstanceReal aReal : instances_real) {

			aReal.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceReal aReal : instances_real) {

			IModelInstanceElement anotherReal;
			IModelInstanceElement anInteger;
			IModelInstanceElement aString;

			/* Test recast with integer type. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_AsTypeIsWrong;
			msg = NLS.bind(msg, type_integer);

			anInteger = aReal.asType(type_integer);
			anotherReal = anInteger.asType(type_real);

			/* The casted element should be a Real. */
			assertTrue(msg, anotherReal instanceof IModelInstanceReal);

			/* The value should depend on the real value. */
			if (anInteger.isUndefined()) {
				assertTrue(msg, anotherReal.isUndefined());
			}

			else {
				assertEquals(msg, ((IModelInstanceInteger) anInteger).getDouble(),
						((IModelInstanceReal) anotherReal).getDouble());
			}

			/* Test recast with string type. */
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_AsTypeIsWrong;
			msg = NLS.bind(msg, type_string);

			aString = aReal.asType(type_string);
			anotherReal = aString.asType(type_real);

			/* The casted element should be a Real. */
			assertTrue(msg, anotherReal instanceof IModelInstanceReal);

			/* The value should depend on the real value. */
			if (aString.isUndefined()) {
				assertTrue(msg, anotherReal.isUndefined());
			}

			else {
				assertEquals(msg, new Double(Double
						.parseDouble(((IModelInstanceString) aString).getString())),
						((IModelInstanceReal) anotherReal).getDouble());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_CopyForAtPreIsWrong;

		/* A Real should be copy-able. */
		for (IModelInstanceReal aReal : instances_real) {

			try {
				assertNotNull(msg, aReal.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_EqualsIsWrong;

		for (IModelInstanceReal aReal : instances_real) {

			for (IModelInstanceReal anotherReal : instances_real) {

				if (aReal.getDouble().equals(anotherReal.getDouble())) {
					assertTrue(msg, aReal.equals(anotherReal));
				}

				else {
					assertFalse(msg, aReal.equals(anotherReal));
				}
				// end else.

				/* No real should be equal to null. */
				assertFalse(msg, aReal.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#getDouble()}.
	 * </p>
	 */
	@Test
	public void testGetDouble() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_GetDoubleIsWrong;

		/* The method should return a Real or should be undefined. */
		for (IModelInstanceReal aReal : instances_real) {

			if (aReal.isUndefined()) {
				assertNull(msg, aReal.getDouble());
			}

			else {
				assertNotNull(msg, aReal.getDouble());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceReal#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_GetTypesIsWrong;

		for (IModelInstanceReal aReal : instances_real) {

			assertNotNull(msg, aReal.getType());

			/* A Real should have the PrimitiveType of the kind Real. */
			assertTrue(msg, aReal.getType() instanceof PrimitiveType);
			assertEquals(msg, PrimitiveTypeKind.REAL, ((PrimitiveType) aReal
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
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceReal_IsUndefinedIsWrong;

		/* The method should return null if the Real is undefined. */
		for (IModelInstanceReal aReal : instances_real) {

			if (aReal.isUndefined()) {
				assertNull(msg, aReal.getDouble());
			}

			else {
				assertNotNull(msg, aReal.getDouble());
			}
		}
		// end for.
	}
}