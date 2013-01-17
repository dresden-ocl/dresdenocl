package org.dresdenocl.debug;

import org.apache.log4j.Logger;
import org.dresdenocl.logging.LoggingPlugin;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class OclDebugPlugin extends Plugin {
	
	/** The shared instance. */
	private static OclDebugPlugin plugin;
	
	public static final String PLUGIN_ID = "org.dresdenocl.debug.OclDebugPlugin";
	public static final String DEBUG_MODEL_ID = "org.dresdenocl.debug.ocl";
	
	public OclDebugPlugin() {

		plugin = this;
	}
	
	/**
	 * @return The shared instance.
	 */
	public static OclDebugPlugin getDefault() {

		return plugin;
	}
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		super.start(context);

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
