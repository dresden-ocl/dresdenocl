package org.dresdenocl.debug.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class OclDebugUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.dresdenocl.debug.ui"; //$NON-NLS-1$

	public static final String LAUNCH_CONFIGURATION_TYPE =
			"org.dresdenocl.debug.launch.OclLaunchConfigurationDelegate"; //$NON-NLS-1$
	
	public static final String OCL_URI = "uri";

	// The shared instance
	private static OclDebugUIPlugin plugin;

	/**
	 * The constructor
	 */
	public OclDebugUIPlugin() {

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
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static OclDebugUIPlugin getDefault() {

		return plugin;
	}

}
