/*
 * Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL2Java Code Generator of Dresden OCL.
 *
 * Dresden OCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Dresden OCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.dresdenocl.logging.LoggingPlugin;

/**
 * <p>
 * Activates the Ocl2Java plug-in.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl2JavaPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID =
			"org.dresdenocl.tools.codegen.ocl2java";

	/** The shared instance. */
	private static Ocl2JavaPlugin plugin;

	/**
	 * @return the shared instance
	 */
	public static Ocl2JavaPlugin getDefault() {
	
		return plugin;
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>org.dresdenocl.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *          the class to return the logger for
	 * 
	 * @return a log4j <code>Logger</code> instance
	 */
	public static Logger getLogger(Class<?> clazz) {
	
		return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
	}

	/**
	 * <p>
	 * Creates a new {@link Ocl2JavaPlugin}.
	 * </p>
	 */
	public Ocl2JavaPlugin() {
	
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);
		plugin = this;

		/* Configure custom logging properties. */
		LoggingPlugin.configureDefaultLogging(plugin);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {

		plugin = null;
		super.stop(context);
	}
}
