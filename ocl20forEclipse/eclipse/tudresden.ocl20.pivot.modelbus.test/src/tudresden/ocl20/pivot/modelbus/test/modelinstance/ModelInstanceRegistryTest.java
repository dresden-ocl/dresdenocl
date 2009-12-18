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
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

		/* The model should now be added to the registry. */
		assertEquals(1, modelInstanceRegistry.getModelInstances(this.model).length);

		/* The model instance should be set as active model instance. */
		assertNotNull(modelInstanceRegistry.getActiveModelInstance(this.model));
		assertEquals(modelInstance, modelInstanceRegistry
				.getActiveModelInstance(this.model));

		/* Add the same model instance again. */
		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

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
	 * adding a <code>null</code> value.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddModelInstance04() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		/* Should cause an exception. */
		modelInstanceRegistry.addModelInstance(model, null);
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
	public void testAddModelInstance05() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		assertEquals(0, modelInstanceRegistry.getModelInstances(this.model).length);

		IModelInstance modelInstance1;
		modelInstance1 =
				ModelBusTestUtility.getJavaModelInstance(
						"bin/package1/ModelInstance01ProviderClass.class", this.model);

		modelInstanceRegistry.addModelInstance(this.model, modelInstance1);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance2);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance);

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
	@Test(expected = IllegalArgumentException.class)
	public void testSetActiveModelInstance03() throws ModelAccessException {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		/* Should cause an exception. */
		modelInstanceRegistry.setActiveModelInstance(this.model, null);
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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance1);

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

		modelInstanceRegistry.addModelInstance(this.model, modelInstance2);

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