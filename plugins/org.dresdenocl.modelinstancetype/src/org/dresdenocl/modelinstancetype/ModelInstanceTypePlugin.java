package org.dresdenocl.modelinstancetype;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.dresdenocl.logging.LoggingPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class ModelInstanceTypePlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.dresdenocl.modelinstancetype";

	// The shared instance
	private static ModelInstanceTypePlugin plugin;
	
	/**
	 * The constructor
	 */
	public ModelInstanceTypePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
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

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ModelInstanceTypePlugin getDefault() {
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

}
