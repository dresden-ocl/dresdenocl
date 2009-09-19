/*
Copyright (C) 2008-2009 by Matthias Bräuer

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.ui;

import java.awt.Image;

import org.apache.log4j.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelsView;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Matthias Bräuer
 */
public class ModelBusUIPlugin extends AbstractUIPlugin {

	/** The plug-in ID. */
	public static final String ID = "tudresden.ocl20.pivot.modelbus.ui"; //$NON-NLS-1$

	/** The plug-in ID. */
	public static final String MODELS_VIEW_ID = ModelsView.ID;

	/** The shared instance. */
	private static ModelBusUIPlugin plugin;

	/**
	 * <p>
	 * Constructs the {@link ModelBusUIPlugin}.
	 * </p>
	 */
	public ModelBusUIPlugin() {

		/* No implementation necessary. */
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
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
	@Override
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
	public static ModelBusUIPlugin getDefault() {

		return plugin;
	}

	/**
	 * <p>
	 * Returns an {@link ImageDescriptor} for the {@link Image} file at the given
	 * plug-in relative path.
	 * </p>
	 * 
	 * @param aPath
	 *          The path whose {@link ImageDescriptor} shall be returned.
	 * @return The {@link ImageDescriptor} for the given path.
	 */
	public static ImageDescriptor getImageDescriptor(String aPath) {

		return imageDescriptorFromPlugin(ID, aPath);
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
	 * 
	 * @param aClass
	 *          The class to return the {@link Logger} for.
	 * 
	 * @return A log4j {@link Logger} instance.
	 */
	public static Logger getLogger(Class<?> aClass) {

		return LoggingPlugin.getLogManager(plugin).getLogger(aClass);
	}
}