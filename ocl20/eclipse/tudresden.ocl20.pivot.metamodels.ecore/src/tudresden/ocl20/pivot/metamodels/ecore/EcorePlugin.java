package tudresden.ocl20.pivot.metamodels.ecore;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class EcorePlugin extends Plugin {

	// The plug-in ID
	public static final String ID = "tudresden.ocl20.pivot.metamodel.ecore"; //$NON-NLS-1$

	// The shared instance
	private static EcorePlugin plugin;
	
	/**
	 * The constructor
	 */
	public EcorePlugin() {
    plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
  public void start(BundleContext context) throws Exception {
		super.start(context);
    
    // configure custom logging properties
    LoggingPlugin.configureDefaultLogging(plugin);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
  public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static EcorePlugin getDefault() {
		return plugin;
	}

}
