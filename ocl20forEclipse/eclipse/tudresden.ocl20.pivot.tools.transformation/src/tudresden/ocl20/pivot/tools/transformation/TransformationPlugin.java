/*
Copyright (C) 2010 by Bjoern Freitag (freitag.bjoern@gmx.de)

This file is part of the Transformation Engine of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.tools.transformation;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.tools.transformation.internal.TransformationRegistry;

/**
 * <p>
 * Activates the Transfomation plug-in.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class TransformationPlugin extends Plugin {

	/** The plug-in ID. */
	public static final String ID = "tudresden.ocl20.pivot.tools.transformation";

	/** The shared instance. */
	private static TransformationPlugin plugin;

	private ITransformationRegistry transformationRegistry;

	/**
	 * <p>
	 * Creates a new {@link TransformationPlugin}.
	 * </p>
	 */
	public TransformationPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);

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

	/**
	 * @return the shared instance
	 */
	public static TransformationPlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
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
	 * Returns the {@link ITransformationRegistry} managed by the
	 * {@link TransformationPlugin}.
	 * </p>
	 * <p>
	 * If no {@link ITransformationRegistry} has been given to the
	 * {@link TemplatePlugin}, the standard {@link TransformationRegistry} will be
	 * used. This is important when using DresdenOCL stand-alone, as the
	 * {@link StandaloneTransformationRegistry} should be set first, using
	 * {@link #setTransformationRegistry(ITransformationRegistry)}.
	 * </p>
	 * 
	 * @see #setTransformationRegistry(ITransformationRegistry)
	 * 
	 * @return An {@link ITransformationRegistry} instance.
	 */
	public synchronized static ITransformationRegistry getTransformationRegistry() {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		/* Lazily create the registry. */
		if (plugin.transformationRegistry == null) {
			plugin.transformationRegistry = new TransformationRegistry();
		}

		return plugin.transformationRegistry;
	}

	/**
	 * Sets the {@link ITransformationRegistry} of the
	 * {@link TransformationPlugin}. This method has to be called when using
	 * DresdenOCL stand-alone. The standard argument should be
	 * {@link StandaloneTransformationRegistry}.
	 * 
	 * @param metamodelRegistry
	 *          the {@link ITransformationRegistry} to set
	 */
	public synchronized static void setTransformationRegistry(
			ITransformationRegistry transformationRegistry) {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		plugin.transformationRegistry = transformationRegistry;
	}
}
