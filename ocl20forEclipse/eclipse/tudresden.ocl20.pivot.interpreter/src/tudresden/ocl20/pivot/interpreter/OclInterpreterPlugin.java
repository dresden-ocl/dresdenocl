/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.interpreter;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterRegistryListener;
import tudresden.ocl20.pivot.interpreter.internal.InterpreterRegistry;
import tudresden.ocl20.pivot.interpreter.internal.OclInterpreter;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class OclInterpreterPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.interpreter";

	/** The shared instance. */
	private static OclInterpreterPlugin plugin;

	/**
	 * The {@link IInterpreterRegistry} for {@link IInterpreterRegistryListener}
	 * s of the {@link IOclInterpreter}.
	 */
	private IInterpreterRegistry interpreterRegistry;

	/**
	 * <p>
	 * Creates a new {@link OclInterpreterPlugin}.
	 * </p>
	 */
	public OclInterpreterPlugin() {

		plugin = this;
	}

	/**
	 * <p>
	 * Creates a new {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @param aModelInstance
	 *            The {@link IModelInstance} used during interpretation.
	 */
	public static IOclInterpreter createInterpreter(
			IModelInstance aModelInstance) {

		return new OclInterpreter(aModelInstance);
	}

	/**
	 * @return The shared instance.
	 */
	public static OclInterpreterPlugin getDefault() {

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
	 * @return The {@link IInterpreterRegistry} of this
	 *         {@link OclInterpreterPlugin}.
	 */
	public static IInterpreterRegistry getInterpreterRegistry() {

		/* Check if the plug-in has been activated. */
		if (plugin == null) {
			System.out
					.println("The Interpreter plugin has not been activated. Initialized it manually.");
			plugin = new OclInterpreterPlugin();
		}
		// no else.

		/* Lazily create the registry. */
		if (plugin.interpreterRegistry == null) {
			plugin.interpreterRegistry = new InterpreterRegistry();
		}
		// no else.

		return plugin.interpreterRegistry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);

		LoggingPlugin.configureDefaultLogging(plugin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {

		plugin = null;

		super.stop(context);
	}
}