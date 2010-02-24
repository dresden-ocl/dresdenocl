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
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.msg.ModelInstanceTypeTestSuiteMessages;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceEnumerationLiteral {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypeTestPlugin
					.getLogger(TestModelInstanceEnumerationLiteral.class);

	/** A {@link String} used to display and log messages and warnings. */
	private static String msg;

	/** A {@link Type} used in this test class. */
	private static Type type_EnumerationLiteralProviderClass;

	/** A {@link Type} used in this test class. */
	private static Type type_Enumeration1;

	/** All instances of the <code>PrimitiveTypeProviderClass</code>. */
	private static Set<IModelInstanceObject> instances_EnumerationLiteralProviderClass;

	/** All instances of the {@link EnmerationLiteral}s. */
	private static Set<IModelInstanceEnumerationLiteral> instances_EnumerationLiteral;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Get the Enumeration1 Test Type from the model. */
		type_Enumeration1 =
				ModelInstanceTypeTestServices.getInstance().getModelType(
						TestModelTypesNames.TYPE_NAME_ENUMERATION1);

		/* Get the EnumerationLiteral's provider class from the model. */
		type_EnumerationLiteralProviderClass =
				ModelInstanceTypeTestServices.getInstance().getModelType(
						TestModelTypesNames.TYPE_NAME_ENUMERATION_LITERAL_PROVIDER_CLASS);

		/* Load all instances of the type from the model instance. */
		instances_EnumerationLiteralProviderClass =
				ModelInstanceTypeTestServices
						.getInstance()
						.getModelInstanceObjectsOfType(type_EnumerationLiteralProviderClass);

		/* Check if any provider class instance has been found. */
		if (instances_EnumerationLiteralProviderClass.size() != 0) {

			List<Property> literalProperties;
			literalProperties = new ArrayList<Property>();

			/* Get the literal properties from the provider class. */
			for (Property aProperty : type_EnumerationLiteralProviderClass
					.getOwnedProperty()) {

				if (aProperty.getName().startsWith("enumerationLiteral")) {
					literalProperties.add(aProperty);
				}
				// no else.
			}

			instances_EnumerationLiteral =
					new HashSet<IModelInstanceEnumerationLiteral>();

			/* Get the property values from all provider class instances. */
			for (IModelInstanceObject aProviderInstance : instances_EnumerationLiteralProviderClass) {

				for (Property aLiteralProperty : literalProperties) {
					IModelInstanceElement aLiteralResult;

					try {
						aLiteralResult = aProviderInstance.getProperty(aLiteralProperty);

						if (aLiteralResult != null
								&& aLiteralResult instanceof IModelInstanceEnumerationLiteral) {
							instances_EnumerationLiteral
									.add((IModelInstanceEnumerationLiteral) aLiteralResult);
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

			if (instances_EnumerationLiteral.size() == 0 && LOGGER.isDebugEnabled()) {
				msg =
						ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_NoEnumerationLiteralFound;

				LOGGER.warn(msg);
			}
			// no else.
		}

		/* Else print a warning. */
		else {
			msg =
					ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_NoProviderClassInstanceFound;

			LOGGER.warn(msg);
		}
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#asType(Type)}.
	 * </p>
	 */
	@Test
	public void testAsType01() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_AsTypeIsWrong;

		/* Check as type with all types possible to cast. */
		for (IModelInstanceEnumerationLiteral literal1 : instances_EnumerationLiteral) {

			IModelInstanceElement literal2;

			/* Test as type with string type. */
			try {
				literal2 = literal1.asType(type_Enumeration1);

				/* The casted element should be a EnumerationLiteral. */
				assertTrue(msg, literal2 instanceof IModelInstanceEnumerationLiteral);

				/* The value should remain the same. */
				assertEquals(msg, literal1.getLiteral(),
						((IModelInstanceEnumerationLiteral) literal2).getLiteral());
			}

			catch (AsTypeCastException e) {
				fail(msg);
			}

		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#asType(Type)} with
	 * illegal arguments.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAsType02() throws AsTypeCastException {

		for (IModelInstanceEnumerationLiteral literal1 : instances_EnumerationLiteral) {

			literal1.asType(null);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#copyForAtPre()}.
	 * </p>
	 */
	@Test
	public void testCopyForAtPre() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_CopyForAtPreIsWrong;

		/* An EnumerationLiteral should be copy-able. */
		for (IModelInstanceEnumerationLiteral aLiteral : instances_EnumerationLiteral) {

			try {
				assertNotNull(msg, aLiteral.copyForAtPre());
			}

			catch (CopyForAtPreException e) {
				fail(msg);
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_EqualsIsWrong;

		/* An EnumerationLiteral should be copy-able. */
		for (IModelInstanceEnumerationLiteral aLiteral : instances_EnumerationLiteral) {

			for (IModelInstanceEnumerationLiteral anotherLiteral : instances_EnumerationLiteral) {

				if (aLiteral.getLiteral().equals(anotherLiteral.getLiteral())) {
					assertTrue(msg, aLiteral.equals(anotherLiteral));
				}

				else {
					assertFalse(msg, aLiteral.equals(anotherLiteral));
				}
				// end else.

				/* No literal should be equal to null. */
				assertFalse(msg, aLiteral.equals(null));
			}
			// end for.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#getLiteral()}.
	 * </p>
	 */
	@Test
	public void testGetLiteral() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_GetLiteralIsWrong;

		/* The method should return a literal or should be undefined. */
		for (IModelInstanceEnumerationLiteral aLiteral : instances_EnumerationLiteral) {

			if (aLiteral.isUndefined()) {
				assertNull(msg, aLiteral.getLiteral());
			}

			else {
				assertNotNull(msg, aLiteral.getLiteral());
			}
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_GetTypesIsWrong;

		for (IModelInstanceEnumerationLiteral aLiteral : instances_EnumerationLiteral) {

			assertNotNull(msg, aLiteral.getType());

			/* An EnumerationLiteral should have its Enumeration as Type. */
			assertTrue(msg, aLiteral.getType() instanceof Enumeration);
			assertEquals(msg, type_Enumeration1, aLiteral.getType());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined() {

		msg =
				ModelInstanceTypeTestSuiteMessages.TestModelInstanceEnumerationLiteral_IsUndefinedIsWrong;

		/* The method should return null if the enumeration literal is undefined. */
		for (IModelInstanceEnumerationLiteral aLiteral : instances_EnumerationLiteral) {

			if (aLiteral.isUndefined()) {
				assertNull(msg, aLiteral.getLiteral());
			}

			else {
				assertNotNull(msg, aLiteral.getLiteral());
			}
		}
		// end for.
	}
}