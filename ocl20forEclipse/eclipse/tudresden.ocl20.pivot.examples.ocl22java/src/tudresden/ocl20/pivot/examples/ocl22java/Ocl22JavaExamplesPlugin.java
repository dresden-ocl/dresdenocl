package tudresden.ocl20.pivot.examples.ocl22java;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Ocl22JavaExamplesPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.examples.ocl22java"; //$NON-NLS-1$

	// The shared instance
	private static Ocl22JavaExamplesPlugin plugin;
	
	/**
	 * The constructor
	 */
	public Ocl22JavaExamplesPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
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
	public static Ocl22JavaExamplesPlugin getDefault() {
		return plugin;
	}

}
