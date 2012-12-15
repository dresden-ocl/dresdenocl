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
package tudresden.ocl20.pivot.modelinstancetype.test;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceType;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceTypeTestPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.modelinstancetype.test";

	/** The shared instance. */
	private static ModelInstanceTypeTestPlugin plugin;

	/**
	 * <p>
	 * Returns the shared instance.
	 * </p>
	 * 
	 * @return The shared instance.
	 */
	public static ModelInstanceTypeTestPlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *            The {@link Class} to return the {@link Logger} for.
	 * 
	 * @return A log4j {@link Logger}> instance.
	 * 
	 * @generated NOT
	 */
	public static Logger getLogger(Class<?> clazz) {

		return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
	}

	/**
	 * <p>
	 * Prepares the tests of the {@link ModelInstanceTypeTestPlugin}.
	 * </p>
	 * 
	 * @param testModelInstanceBundleID
	 *            The ID of {@link Bundle} which provides the
	 *            {@link IModelInstance} the {@link IModelInstanceType} should
	 *            be tested with.
	 * @param testModelInstancePath
	 *            The path of the {@link IModelInstance} the
	 *            {@link IModelInstanceType} should be tested with.
	 * @param modelInstanceID
	 *            The ID of the {@link IModelInstanceType} in which the
	 *            <code>testModelInstance</code> is modeled and which shall be
	 *            tested.
	 */
	public static void prepareTest(String testModelInstanceBundleID,
			String testModelInstancePath, String modelInstanceID) {

		/*
		 * Reset the model. Has probably been used by another instance of this
		 * test suite.
		 */
		ModelInstanceTypeTestServices.getInstance().myModel = null;

		ModelInstanceTypeTestServices.getInstance()
				.setTestModelInstanceBundleID(testModelInstanceBundleID);

		ModelInstanceTypeTestServices.getInstance().setTestModelInstancePath(
				testModelInstancePath);

		ModelInstanceTypeTestServices.getInstance().setModelInstanceTypeID(
				modelInstanceID);

		/* Set all counters to default values. */
		ModelInstanceTypeTestServices.getInstance().setBagPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance()
				.setBooleanPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance()
				.setIntegerPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance()
				.setOrderedSetPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setRealPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setSequencePropertyCounter(
				1);
		ModelInstanceTypeTestServices.getInstance().setSetPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setStringPropertyCounter(1);
	}

	/**
	 * <p>
	 * The constructor.
	 * </p>
	 */
	public ModelInstanceTypeTestPlugin() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {

		plugin = null;
		super.stop(context);
	}
}