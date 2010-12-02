/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Test Suite of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.modelbus.test.modelinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.metamodel.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceRegistry;

/**
 * <p>
 * Test cases to test the default {@link IMetamodelRegistry} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceRegistryTest {

	/** The {@link IModel} used by all test cases. */
	private IModel model;

	/**
	 * <p>
	 * Disposes the {@link IModelInstanceRegistry} before the execution of every
	 * test case to avoid side effects.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Before
	public void setUp() throws ModelAccessException {

		if (this.model == null) {
			this.model =
					ModelBusTestUtility.getUML2Model("resources/models/model03.uml");
		}
		// no else.

		ModelBusPlugin.getModelInstanceRegistry().dispose();
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#addModelInstance(IModel, IModelInstance)} by
	 * simply adding a not yet added {@link IModelInstance}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModelInstance01() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#addModelInstance(IModel, IModelInstance)} by
	 * simply adding the same {@link IModelInstance} twice.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModelInstance02() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Add the same model instance again. */
		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#addModelInstance(IModel, IModelInstance)} by
	 * simply reloading and readding the same {@link IModelInstance} twice.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModelInstance03() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Load and add the same model instance again. */
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#addModelInstance(IModel, IModelInstance)} by
	 * simply adding two different {@link IModelInstance}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModelInstance04() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance1;
		modelInstance1 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance1);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance1, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Load and add another model instance. */
		IModelInstance modelInstance2;
		modelInstance2 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance02ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance2);

		/* The model should now be added to the registry. */
		assertEquals(2, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The first model instance should still be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance1, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#removeModelInstance(IModelInstance)} by
	 * simply removing an {@link IModelInstance} that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance01() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance01);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		assertTrue(modelInstanceRegistry.removeModelInstance(modelInstance01));

		/* The model instance should now be removed from the registry. */
		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Active model instance should be null. */
		assertNull(modelInstanceRegistry.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#removeModelInstance(IModelInstance)} by
	 * simply removing an {@link IModelInstance} that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance02() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		IModelInstance modelInstance02;
		modelInstance02 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance02ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance01);
		modelInstanceRegistry.addModelInstance(modelInstance02);

		/* The model instances should now be added to the registry. */
		assertEquals(2, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The first model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		assertTrue(modelInstanceRegistry.removeModelInstance(modelInstance01));

		/* The model instance should now be removed from the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Active model instance should be set to model instance 2. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance02, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#removeModelInstance(IModelInstance)} by
	 * simply removing an {@link IModelInstance} that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance03() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		IModelInstance modelInstance02;
		modelInstance02 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance02ProviderClass.class", this.model);

		IModelInstance modelInstance03;
		modelInstance03 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance03ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance01);
		modelInstanceRegistry.addModelInstance(modelInstance02);
		modelInstanceRegistry.addModelInstance(modelInstance03);

		/* The model instances should now be added to the registry. */
		assertEquals(3, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The first model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		assertTrue(modelInstanceRegistry.removeModelInstance(modelInstance01));

		/* The model instance should now be removed from the registry. */
		assertEquals(2, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Active model instance should be set to null. */
		assertNull(modelInstanceRegistry.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#removeModelInstance(IModelInstance)} by
	 * simply removing a <code>null</code> value.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveModelInstance04() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 = null;

		/* Should raise an exception. */
		modelInstanceRegistry.removeModelInstance(modelInstance01);
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#removeModelInstance(IModelInstance)} by
	 * simply removing an {@link IModelInstance} that has not been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance05() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		assertFalse(modelInstanceRegistry.removeModelInstance(modelInstance01));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceRegistry#removeModelInstance(String)}
	 * by simply removing an {@link IModelInstance} that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance06() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance01);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		IModelInstance removedIModelInstance;
		removedIModelInstance =
				modelInstanceRegistry.removeModelInstance(modelInstance01
						.getDisplayName());

		assertEquals(modelInstance01, removedIModelInstance);

		/* The model instance should now be removed from the registry. */
		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Active model instance should be null. */
		assertNull(modelInstanceRegistry.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceRegistry#removeModelInstance(String)}
	 * by simply removing an {@link IModelInstance}'s display name that does not
	 * exist.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRemoveModelInstance07() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance01;
		modelInstance01 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance01);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		IModelInstance removedIModelInstance;
		removedIModelInstance =
				modelInstanceRegistry.removeModelInstance("unknown display name");

		assertNull(removedIModelInstance);

		/* The model instance should not be removed from the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Active model instance should stil be set. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance01, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceRegistry#removeModelInstance(String)}
	 * by simply removing a <code>null</code> value.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveModelInstance08() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		String displayName;
		displayName = null;

		/* Should raise an exception. */
		modelInstanceRegistry.removeModelInstance(displayName);
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#setActiveModelInstance(IModel, IModelInstance)}
	 * by setting an {@link IModelInstance} as active that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testSetActiveModelInstance01() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model instance should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Set the model instance as active. */
		modelInstanceRegistry.setActiveModelInstance(this.model, modelInstance);

		/* The model Instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#setActiveModelInstance(IModel, IModelInstance)}
	 * by setting an {@link IModelInstance} as active that has not been added
	 * before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetActiveModelInstance02() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		/* Should cause an exception. */
		modelInstanceRegistry.setActiveModelInstance(this.model, modelInstance);
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#setActiveModelInstance(IModel, IModelInstance)}
	 * by setting <code>null</code> as active
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testSetActiveModelInstance03() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance;
		modelInstance =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance);

		/* The model instance should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Should not cause an exception. */
		modelInstanceRegistry.setActiveModelInstance(this.model, null);

		/* The model Instance should be set to null. */
		assertNull(modelInstanceRegistry.getActiveModelInstance(this.model));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceRegistry#setActiveModelInstance(IModel, IModelInstance)}
	 * by setting an two {@link IModelInstance}s as active that have been added
	 * before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testSetActiveModelInstance04() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance1;
		modelInstance1 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance1);

		/* Set the model instance as active. */
		modelInstanceRegistry.setActiveModelInstance(this.model, modelInstance1);

		/* The model Instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance1, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Load another model instance. */
		IModelInstance modelInstance2;
		modelInstance2 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance02ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(modelInstance2);

		/* Set the model instance as active. */
		modelInstanceRegistry.setActiveModelInstance(this.model, modelInstance2);

		/* The model Instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance2, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Set the first model instance as active again. */
		modelInstanceRegistry.setActiveModelInstance(this.model, modelInstance1);

		/* The model Instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance1, modelInstanceRegistry
				.getActiveModelInstance(this.model));
	}
}