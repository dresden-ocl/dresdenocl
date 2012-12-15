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
package tudresden.ocl20.pivot.standardlibrary.java;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * <p>
 * This plug-in provides the OCL standard library which implements the OCL types
 * in Java.
 * </p>
 * 
 * @author Ronny Brandt.
 */
public class JavaStandardlibraryPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID =
			"tudresden.ocl20.pivot.standardlibrary.java";

	/** The shared instance. */
	private static JavaStandardlibraryPlugin plugin;

	/**
	 * <p>
	 * Returns the shared instance.
	 * </p>
	 * 
	 * @return the shared instance
	 */
	public static JavaStandardlibraryPlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Returns the {@link IStandardLibraryFactory} of the
	 * {@link JavaStandardlibraryPlugin}.
	 * </p>
	 * 
	 * @return The {@link IStandardLibraryFactory} of the
	 *         {@link JavaStandardlibraryPlugin}.
	 */
	public static IStandardLibraryFactory getStandardLibraryFactory() {

		return JavaStandardLibraryFactory.INSTANCE;
	}

	/**
	 * <p>
	 * The constructor.
	 * </p>
	 */
	public JavaStandardlibraryPlugin() {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);
		plugin = this;

		/* Starts the logger for this plug-in. */
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
