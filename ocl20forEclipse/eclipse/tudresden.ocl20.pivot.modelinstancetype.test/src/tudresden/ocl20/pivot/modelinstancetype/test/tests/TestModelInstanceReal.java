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
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal}
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
	public void testAsType() {

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
				aReal.copyForAtPre();
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

			/* A Real should have exactly one type. */
			assertEquals(msg, 1, aReal.getTypes().size());

			/* A Real should have the PrimitiveType of the kind Real. */
			for (Type type : aReal.getTypes()) {

				assertTrue(msg, type instanceof PrimitiveType);
				assertEquals(msg, PrimitiveTypeKind.REAL, ((PrimitiveType) type)
						.getKind());
			}
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