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
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceTypeRegistry;

/**
 * <p>
 * Test cases to test the {@link IModelInstanceTypeRegistry} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceTypeRegistryTest {

	/** The id of the {@link IExtensionPoint} to registers {@link IMetamodel}s. */
	private static final String MODEL_INSTANCE_TYPE_EXTENSION_POINT_ID = ModelBusPlugin.ID
			+ '.' + IModelBusConstants.EXT_MODELINSTANCETYPES;

	/**
	 * <p>
	 * Test case to test the {@link IModelInstanceTypeRegistry} with the dynamic
	 * update of {@link IModelInstanceType}s via {@link IExtension}s.
	 * </p>
	 */
	@Test
	public void testDynamicUpdate01() {

		/* Run test only if Platform is running. */
		if (Platform.isRunning()) {
			IModelInstanceTypeRegistry modelInstanceTypeRegistry;
			modelInstanceTypeRegistry = ModelBusPlugin
					.getModelInstanceTypeRegistry();

			IRegistryEventListener eventListener;
			eventListener = (IRegistryEventListener) modelInstanceTypeRegistry;

			IExtensionPoint modelInstanceTypeExtensionPoint;
			modelInstanceTypeExtensionPoint = Platform.getExtensionRegistry()
					.getExtensionPoint(MODEL_INSTANCE_TYPE_EXTENSION_POINT_ID);

			assertNotNull(modelInstanceTypeExtensionPoint);

			IExtension[] modelInstanceTypeExtensions;
			modelInstanceTypeExtensions = modelInstanceTypeExtensionPoint
					.getExtensions();

			assertTrue(modelInstanceTypeExtensions.length > 0);

			int modelInstanceTypeSize;
			modelInstanceTypeSize = modelInstanceTypeRegistry
					.getModelInstanceTypes().length;

			IExtension[] aModelInstanceTypeExtension;
			aModelInstanceTypeExtension = new IExtension[1];
			aModelInstanceTypeExtension[0] = modelInstanceTypeExtensions[0];

			eventListener.removed(aModelInstanceTypeExtension);

			/* One model instance type should have been removed. */
			assertEquals(modelInstanceTypeSize - 1,
					modelInstanceTypeRegistry.getModelInstanceTypes().length);

			eventListener.added(aModelInstanceTypeExtension);

			/* One model instance type should have been added again. */
			assertEquals(modelInstanceTypeSize,
					modelInstanceTypeRegistry.getModelInstanceTypes().length);
		}
		// no else.
	}

	/**
	 * <p>
	 * Test case to test the method
	 * {@link IModelInstanceTypeRegistry#getModelInstanceTypes()}.
	 * </p>
	 */
	@Test
	public void testGetModelInstanceTypes01() {

		/* Run test only if Platform is running. */
		if (Platform.isRunning()) {
			IModelInstanceTypeRegistry modelInstanceTypeRegistry;
			modelInstanceTypeRegistry = ModelBusPlugin
					.getModelInstanceTypeRegistry();

			IExtensionPoint modelInstanceTypeExtensionPoint;
			modelInstanceTypeExtensionPoint = Platform.getExtensionRegistry()
					.getExtensionPoint(MODEL_INSTANCE_TYPE_EXTENSION_POINT_ID);

			assertNotNull(modelInstanceTypeExtensionPoint);

			IExtension[] modelInstanceTypeExtensions;
			modelInstanceTypeExtensions = modelInstanceTypeExtensionPoint
					.getExtensions();

			assertNotNull(modelInstanceTypeExtensions);

			assertTrue(
					"The ModelInstanceTypeRegistry should have at least as much "
							+ "IModelInstanceTypes as registered via the IExtensionPoint.",
					modelInstanceTypeRegistry.getModelInstanceTypes().length >= modelInstanceTypeExtensions.length);
		}
		// no else.
	}
}