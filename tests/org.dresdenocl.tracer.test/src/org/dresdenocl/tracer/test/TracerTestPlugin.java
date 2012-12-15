package tudresden.ocl20.pivot.tracer.test;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TracerTestPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.tracer.test"; //$NON-NLS-1$

	// The shared instance
	private static TracerTestPlugin plugin;

	/**
	 * The constructor
	 */
	public TracerTestPlugin() {

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
	public static TracerTestPlugin getDefault() {

		return plugin;
	}

}
