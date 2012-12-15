/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.test;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import org.dresdenocl.logging.LoggingPlugin;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.model.IModel;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 */
public class MetaModelTestPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID =
			"org.dresdenocl.metamodels.test";

	/** The shared instance. */
	private static MetaModelTestPlugin plugin;

	/**
	 * <p>
	 * The constructor.
	 * </p>
	 */
	public MetaModelTestPlugin() {

	}

	/*
	 * (non-Javadoc)
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
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {

		plugin = null;
		super.stop(context);
	}

	/**
	 * <p>
	 * Returns the shared instance.
	 * </p>
	 * 
	 * @return The shared instance.
	 */
	public static MetaModelTestPlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>org.dresdenocl.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *          The {@link Class} to return the {@link Logger} for.
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
	 * Prepares the tests of the {@link MetaModelTestPlugin}.
	 * </p>
	 * 
	 * @param testModelBundleID
	 *          The ID of {@link Bundle} which provides the {@link IModel} the
	 *          {@link IMetamodel} should be tested with.
	 * @param testModelPath
	 *          The path of the {@link IModel} the {@link IMetamodel} should be
	 *          tested with.
	 * @param metaModelID
	 *          The ID of the {@link IMetamodel} in which the
	 *          <code>testModelBundle</code> is modeled and which shall be tested.
	 */
	public static void prepareTest(String testModelBundleID,
			String testModelPath, String metaModelID) {

		MetaModelTestServices.getInstance().setTestModelBundleID(testModelBundleID);

		MetaModelTestServices.getInstance().setTestModelPath(testModelPath);

		MetaModelTestServices.getInstance().setMetaModelID(metaModelID);
	}
}