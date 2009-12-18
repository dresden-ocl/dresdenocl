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

package tudresden.ocl20.pivot.modelbus.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;

/**
 * <p>
 * Test cases to test the default {@link IMetamodelRegistry} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelRegistryTest {

	/**
	 * <p>
	 * Disposes the {@link IMetamodelRegistry} before the execution of every test
	 * case to avoid side effects.
	 * </p>
	 */
	@Before
	public void setUp() {

		ModelBusPlugin.getModelRegistry().dispose();
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by simply adding a
	 * not yet added {@link IModel}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModel01() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by simply adding
	 * the same {@link IModel} twice.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModel02() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());

		/* Add the model again. */
		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by simply adding
	 * the same model (but different {@link IModel} objects) twice.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModel03() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());

		/* Load and add the model again. */
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");
		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by adding a
	 * <code>null</code> value.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddModel04() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		/* Should fail. */
		modelRegistry.addModel(null);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by adding two
	 * different {@link IModel}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testAddModel05() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model1;
		model1 = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model1);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model1, modelRegistry.getActiveModel());

		/* Load and add another model again. */
		IModel model2;
		model2 = ModelBusTestUtility.getUML2Model("resources/models/model02.uml");
		modelRegistry.addModel(model2);

		/* The model should now be added to the registry as well. */
		assertEquals(2, modelRegistry.getModels().length);

		/* Model1 should still be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model1, modelRegistry.getActiveModel());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by setting an
	 * {@link IModel} as active that has been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testSetActiveModel01() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model);

		/* The model should now be added to the registry. */
		assertEquals(1, modelRegistry.getModels().length);

		/* Set the model as active. */
		modelRegistry.setActiveModel(model);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model, modelRegistry.getActiveModel());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by setting an
	 * {@link IModel} as active that has not been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetActiveModel02() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		/* Set the model as active (should fail). */
		modelRegistry.setActiveModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by setting
	 * <code>null</code> as active.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetActiveModel03() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		/* Set the null as active (should fail). */
		modelRegistry.setActiveModel(null);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelRegistry#addModel(IModel)} by setting two
	 * {@link IModel}s as active that have been added before.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testSetActiveModel04() throws ModelAccessException {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		assertEquals(0, modelRegistry.getModels().length);

		IModel model1;
		model1 = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		modelRegistry.addModel(model1);

		IModel model2;
		model2 = ModelBusTestUtility.getUML2Model("resources/models/model02.uml");

		modelRegistry.addModel(model2);

		/* Set the model1 as active. */
		modelRegistry.setActiveModel(model1);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model1, modelRegistry.getActiveModel());

		/* Set the model2 as active. */
		modelRegistry.setActiveModel(model2);

		/* The model should be set as active model. */
		assertNotNull(modelRegistry.getActiveModel());
		assertEquals(model2, modelRegistry.getActiveModel());
	}
}