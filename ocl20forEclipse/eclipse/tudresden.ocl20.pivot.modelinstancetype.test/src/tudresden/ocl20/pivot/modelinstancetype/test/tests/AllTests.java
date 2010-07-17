/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Generic Model Instance Type Test Suite of Dresden 
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

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelinstancetype.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelinstancetype.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestModelTypesNames;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains all test cases of the package
 * <code>tudresden.ocl20.pivot.modelinstancetype.test.tests</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestModelInstanceBoolean.class,
		TestModelInstanceInteger.class, TestModelInstanceReal.class,
		TestModelInstanceString.class, TestModelInstanceCollection.class,
		TestModelInstanceEnumerationLiteral.class,
		TestModelInstanceProvider.class, TestModelInstanceObject.class,
		TestModelInstance.class })
public class AllTests {

	/**
	 * <p>
	 * Initializes the test suite by loading all class instances from the
	 * container class.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		Type containerClassType;
		Set<IModelInstanceObject> allContainerClassInstances;

		IModelInstanceObject containerClassInstance;

		containerClassType = ModelInstanceTypeTestServices.getInstance()
				.getModelType(TestModelTypesNames.TYPE_NAME_CONTAINER_CLASS);

		/* Try to get an instance of the container class. */
		if (containerClassType != null) {

			allContainerClassInstances = ModelInstanceTypeTestServices
					.getInstance().getModelInstanceObjectsOfType(
							containerClassType);

			if (allContainerClassInstances.size() > 0) {
				Property primitiveTypeProviderClassProperty = null;
				Property collectionTypeProviderClassProperty = null;
				Property staticPropertyAndOperationClassProperty = null;
				Property enumerationLiteralProviderClassProperty = null;
				Property class1Property = null;
				Property class2Property = null;
				Property interface1Property = null;
				Property interface2Property = null;
				Property interface3Property = null;
				Property copyableClassProperty = null;
				Property nonCopyableClassProperty = null;

				containerClassInstance = allContainerClassInstances.iterator()
						.next();

				/* Find the instances properties. */
				for (Property property : containerClassType.allProperties()) {

					if (property.getName().equals(
							"primitiveTypeProviderClassInstances")) {
						primitiveTypeProviderClassProperty = property;
					}

					else if (property.getName().equals(
							"collectionTypeProviderClassInstances")) {
						collectionTypeProviderClassProperty = property;
					}

					else if (property.getName().equals(
							"enumerationLiteralProviderClassInstances")) {
						enumerationLiteralProviderClassProperty = property;
					}

					else if (property.getName().equals("class1Instances")) {
						class1Property = property;
					}

					else if (property.getName().equals("class2Instances")) {
						class2Property = property;
					}

					else if (property.getName().equals(
							"staticPropertyAndOperationClassInstances")) {
						staticPropertyAndOperationClassProperty = property;
					}

					else if (property.getName().equals("interface1Instances")) {
						interface1Property = property;
					}

					else if (property.getName().equals("interface2Instances")) {
						interface2Property = property;
					}

					else if (property.getName().equals("interface3Instances")) {
						interface3Property = property;
					}

					else if (property.getName()
							.equals("copyableClassInstances")) {
						copyableClassProperty = property;
					}

					else if (property.getName().equals(
							"nonCopyableClassInstances")) {
						nonCopyableClassProperty = property;
					}
					// no else.
				}
				// end for.

				addInstancesOfProperty(containerClassInstance,
						primitiveTypeProviderClassProperty);
				addInstancesOfProperty(containerClassInstance,
						collectionTypeProviderClassProperty);
				addInstancesOfProperty(containerClassInstance,
						enumerationLiteralProviderClassProperty);
				addInstancesOfProperty(containerClassInstance, class1Property);
				addInstancesOfProperty(containerClassInstance, class2Property);
				addInstancesOfProperty(containerClassInstance,
						staticPropertyAndOperationClassProperty);
				addInstancesOfProperty(containerClassInstance,
						interface1Property);
				addInstancesOfProperty(containerClassInstance,
						interface2Property);
				addInstancesOfProperty(containerClassInstance,
						interface3Property);
				addInstancesOfProperty(containerClassInstance,
						copyableClassProperty);
				addInstancesOfProperty(containerClassInstance,
						nonCopyableClassProperty);
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that tries to get a given {@link Property} of a given
	 * <code>ContainerClass</code> and tries to add all result values of the
	 * {@link Property} as {@link IModelInstanceObject}s of the
	 * {@link IModelInstance} under test.
	 * </p>
	 * 
	 * @param containerClassInstance
	 *            The <code>ContainerClass</code> which {@link Property} shall
	 *            be invoked.
	 * @param primitiveTypeProviderClassProperty
	 *            The {@link Property} whose value shall be added as
	 *            {@link IModelInstanceObject}s.
	 */
	private static void addInstancesOfProperty(
			IModelInstanceObject containerClassInstance,
			Property primitiveTypeProviderClassProperty) {

		/* Try to get the instances. */
		try {
			IModelInstanceElement propertyValue;

			/* Try to get the property's value. */
			propertyValue = containerClassInstance
					.getProperty(primitiveTypeProviderClassProperty);

			/* If the value is a collection add all its instances. */
			if (propertyValue instanceof IModelInstanceCollection<?>) {

				IModelInstanceCollection<?> propertyValueCollection;
				propertyValueCollection = (IModelInstanceCollection<?>) propertyValue;

				/* Iterate through the result value and add all elements. */
				for (Object anElement : propertyValueCollection.getCollection()) {

					if (anElement instanceof IModelInstanceObject) {
						IModelInstanceObject modelInstanceObject;
						modelInstanceObject = (IModelInstanceObject) anElement;

						try {
							ModelInstanceTypeTestServices.getInstance()
									.getModelInstance()
									.addModelInstanceElement(
											modelInstanceObject.getObject());
						} catch (TypeNotFoundInModelException e) {
							/* Do nothing. */
						}
						// end catch.
					}
					// no else (cannot add the element).
				}
				// end for.
			}

			/* Else if the value is a IModelInstanceObject, add this object. */
			if (propertyValue instanceof IModelInstanceObject) {
				IModelInstanceObject modelInstanceObject;
				modelInstanceObject = (IModelInstanceObject) propertyValue;

				if (!modelInstanceObject.isUndefined()) {
					try {
						ModelInstanceTypeTestServices.getInstance()
								.getModelInstance().addModelInstanceElement(
										modelInstanceObject.getObject());
					} catch (TypeNotFoundInModelException e) {
						/* Do nothing. */
					}
					// end catch.
				}
				// no else.
			}
			// no else (cannot add the value).
		}
		// end try.

		catch (PropertyAccessException e) {
			/* Do nothing. */
		}

		catch (PropertyNotFoundException e) {
			/* Do nothing. */
		}
		// end catch.
	}
}