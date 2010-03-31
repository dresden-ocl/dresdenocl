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

import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceBoolean {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin.getLogger(TestModelInstanceBoolean.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** A {@link Type} used in this test class. */
	private static Type type_PrimitiveTypeProviderClass;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_boolean;

	/** A {@link Type} used in this test class. */
	private static PrimitiveType type_string;

	/** All instances of the <code>PrimitiveTypeProviderClass</code>. */
	private static Set<IModelInstanceObject> instances_PrimitiveTypeProviderClass;

	/** All instances of the {@link PrimitiveTypeKind#BOOLEAN}. */
	private static Set<IModelInstanceBoolean> instances_boolean;

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

			List<Property> booleanProperties;
			booleanProperties = new ArrayList<Property>();

			/* Get the boolean properties from the provider class. */
			for (Property aProperty : type_PrimitiveTypeProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("boolean")) {
					booleanProperties.add(aProperty);
				}
				// no else.
			}

			instances_boolean = new HashSet<IModelInstanceBoolean>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_PrimitiveTypeProviderClass) {

				for (Property aBooleanProperty : booleanProperties) {
					IModelInstanceElement aBooleanResult;

					try {
						aBooleanResult = aProviderInstance.getProperty(aBooleanProperty);

						if (aBooleanResult != null
								&& aBooleanResult instanceof IModelInstanceBoolean) {
							instances_boolean.add((IModelInstanceBoolean) aBooleanResult);
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

			if (instances_boolean.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_NoBooleanInstanceFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_AsTypeIsWrong;
		msg = NLS.bind(msg, type_string);

		/* Check as type with all types possible to cast. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			IModelInstanceElement anotherBoolean;

			/* Test as type with boolean type. */
			try {
				anotherBoolean = aBoolean.asType(type_boolean);

				/* The casted element should be a String. */
				assertTrue(msg, anotherBoolean instanceof IModelInstanceBoolean);

				/* The value should depend on the boolean value. */
				assertEquals(msg, aBoolean.getBoolean(),
						((IModelInstanceBoolean) anotherBoolean).getBoolean());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

			/* Test as type with string type. */
			try {
				anotherBoolean = aBoolean.asType(type_string);

				/* The casted element should be a String. */
				assertTrue(msg, anotherBoolean instanceof IModelInstanceString);

				/* The value should depend on the boolean value. */
				if (aBoolean.getBoolean()) {
					assertEquals(msg, "true", ((IModelInstanceString) anotherBoolean)
							.getString());
				}

				else {
					assertEquals(msg, "false", ((IModelInstanceString) anotherBoolean)
							.getString());
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
	 * Tests the method {@link IModelInstanceBoolean#asType(Type)} with illegal
	 * arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		/* Check as type with all types possible to cast. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			/* Test as type with null value. */
			aBoolean.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_AsTypeIsWrong;
		msg = NLS.bind(msg, type_string);

		/* Check as type with all types possible to cast. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			IModelInstanceElement anotherBoolean;

			/* Test as type with string type and back to boolean. */
			anotherBoolean = aBoolean.asType(type_string).asType(type_boolean);

			/* The casted element should be a Boolean. */
			assertTrue(msg, anotherBoolean instanceof IModelInstanceBoolean);

			/* The boolean should be equal. */
			assertEquals(msg, aBoolean, anotherBoolean);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_CopyForAtPreIsWrong;

		/* A boolean should be copy-able. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			try {
				assertNotNull(msg, aBoolean.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_EqualsIsWrong;

		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			for (IModelInstanceBoolean anotherBoolean : instances_boolean) {

				if (aBoolean.getBoolean().equals(anotherBoolean.getBoolean())) {
					assertTrue(msg, aBoolean.equals(anotherBoolean));
				}

				else {
					assertFalse(msg, aBoolean.equals(anotherBoolean));
				}
				// end else.

				/* No boolean should be equal to null. */
				assertFalse(msg, aBoolean.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#getBoolean()}.
	 * </p>
	 */
	@Test
	public void testGetBoolean() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_GetBooleanIsWrong;

		/* The method should return a boolean or should be undefined. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			if (aBoolean.isUndefined()) {
				assertNull(msg, aBoolean.getBoolean());
			}

			else {
				assertNotNull(msg, aBoolean.getBoolean());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#getType()}.
	 * </p>
	 */
	@Test
	public void testGetType() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_GetTypesIsWrong;

		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			assertNotNull(msg, aBoolean.getType());

			/* A boolean should have the PrimitiveType of the kind Boolean. */
			assertTrue(msg, aBoolean.getType() instanceof PrimitiveType);
			assertEquals(msg, PrimitiveTypeKind.BOOLEAN, ((PrimitiveType) aBoolean
					.getType()).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceBoolean#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceBoolean_IsUndefinedIsWrong;

		/* The method should return null if the boolean is undefined. */
		for (IModelInstanceBoolean aBoolean : instances_boolean) {

			if (aBoolean.isUndefined()) {
				assertNull(msg, aBoolean.getBoolean());
			}

			else {
				assertNotNull(msg, aBoolean.getBoolean());
			}
		}
		// end for.
	}
}