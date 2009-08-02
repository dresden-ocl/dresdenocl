/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.java;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstance;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Class Wilke
 */
public class JavaModelInstanceTypePlugin extends Plugin {

	/** The plug-in ID. */
	public static final String PLUGIN_ID =
			"tudresden.ocl20.pivot.modelinstancetype.java";

	/** The shared instance. */
	private static JavaModelInstanceTypePlugin plugin;

	/**
	 * <p>
	 * Adds a given Java {@link Object} to a given {@link IModelInstance}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be added.
	 * @param modelInstance
	 *          The {@link IModelInstance} to which the {@link Object} shall be
	 *          added.
	 * @return The adapted {@link Object} as {@link IModelInstance}.
	 * @throws ModelAccessException
	 *           Thrown, if the given {@link Object} cannot be adapted to the
	 *           given {@link IModelInstance}.
	 */
	public static IModelObject addModelObjectToInstance(Object object,
			IModelInstance modelInstance) throws ModelAccessException {

		IModelObject result;

		/* Check if the given IModelInstance is a JavaModelInstance. */
		if (modelInstance instanceof JavaModelInstance) {
			JavaModelInstance javaInstance;
			javaInstance = (JavaModelInstance) modelInstance;

			result = javaInstance.addModelObject(object);
		}

		else {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstance} containing no {@link IModelObject}
	 * at all. Such a {@link JavaModelInstance} can be used to be filled later by
	 * using the method
	 * {@link JavaModelInstanceTypePlugin#addModelObjectToInstance(Object, IModelInstance)}
	 * .
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for which a new {@link JavaModelInstance} shall
	 *          be created.
	 * @return The newly created {@link JavaModelInstance}.
	 * @throws ModelAccessException
	 *           Thrown if no {@link JavaModelInstance} of the given
	 *           {@link IModel} can be created.
	 * 
	 * @see JavaModelInstanceTypePlugin#addModelObjectToInstance(Object,
	 *      IModelInstance)
	 */
	public static IModelInstance createEmptyModelInstance(IModel model)
			throws ModelAccessException {

		IModelInstance result;

		result = new JavaModelInstance(model);

		return result;
	}

	/**
	 * @return The shared instance.
	 */
	public static JavaModelInstanceTypePlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
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
	 * The constructor of the {@link JavaModelInstanceTypePlugin}.
	 * </p>
	 */
	public JavaModelInstanceTypePlugin() {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);

		plugin = this;
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