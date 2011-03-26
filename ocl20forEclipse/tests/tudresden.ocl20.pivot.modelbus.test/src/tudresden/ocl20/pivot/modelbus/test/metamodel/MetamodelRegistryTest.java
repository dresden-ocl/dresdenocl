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

package tudresden.ocl20.pivot.modelbus.test.metamodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * Test cases to test the {@link IMetamodelRegistry} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class MetamodelRegistryTest {

	/** The id of the {@link IExtensionPoint} to registers {@link IMetamodel}s. */
	private static final String META_MODEL_EXTENSION_POINT_ID = ModelBusPlugin.ID
			+ '.' + IModelBusConstants.EXT_METAMODELS;

	/**
	 * <p>
	 * Test case to test the method {@link IMetamodelRegistry#getMetamodels()}.
	 * </p>
	 */
	@Test
	public void testGetMetaModels01() {

		/* Test is only executable for running platform. */
		if (Platform.isRunning()) {
			IMetamodelRegistry metamodelRegistry;
			metamodelRegistry = ModelBusPlugin.getMetamodelRegistry();

			IExtensionPoint metaModelExtensionPoint;
			metaModelExtensionPoint = Platform.getExtensionRegistry()
					.getExtensionPoint(META_MODEL_EXTENSION_POINT_ID);

			assertNotNull(metaModelExtensionPoint);

			IExtension[] metaModelExtensions;
			metaModelExtensions = metaModelExtensionPoint.getExtensions();

			assertNotNull(metaModelExtensions);

			assertTrue(
					"The MetaModelRegistry should have at least as much "
							+ "IMetamodels as registered via the IExtensionPoint.",
					metamodelRegistry.getMetamodels().length >= metaModelExtensions.length);
		}
		// no else.
	}

	/**
	 * <p>
	 * Test case to test the {@link IMetamodelRegistry} with the dynamic update
	 * of {@link IMetamodel}s via {@link IExtension}s.
	 * </p>
	 */
	@Test
	public void testDynamicUpdate01() {

		/* Test is only executable for running platform. */
		if (Platform.isRunning()) {
			IMetamodelRegistry metamodelRegistry;
			metamodelRegistry = ModelBusPlugin.getMetamodelRegistry();

			IRegistryEventListener eventListener;
			eventListener = (IRegistryEventListener) metamodelRegistry;

			IExtensionPoint metaModelExtensionPoint;
			metaModelExtensionPoint = Platform.getExtensionRegistry()
					.getExtensionPoint(META_MODEL_EXTENSION_POINT_ID);

			assertNotNull(metaModelExtensionPoint);

			IExtension[] metaModelExtensions;
			metaModelExtensions = metaModelExtensionPoint.getExtensions();

			assertTrue(metaModelExtensions.length > 0);

			int metaModelSize;
			metaModelSize = metamodelRegistry.getMetamodels().length;

			IExtension[] aMetaModelExtension;
			aMetaModelExtension = new IExtension[1];
			aMetaModelExtension[0] = metaModelExtensions[0];

			eventListener.removed(aMetaModelExtension);

			/* One meta-model should have been removed. */
			assertEquals(metaModelSize - 1,
					metamodelRegistry.getMetamodels().length);

			eventListener.added(aMetaModelExtension);

			/* One meta-model should have been added again. */
			assertEquals(metaModelSize,
					metamodelRegistry.getMetamodels().length);
		}
		// no else.
	}
}