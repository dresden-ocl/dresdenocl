package tudresden.ocl20.pivot.essentialocl;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class StandardLibraryPlugin extends Plugin {

	// The plug-in ID
	public static final String ID = "tudresden.ocl20.pivot.essentialocl.standardlibrary"; //$NON-NLS-1$

	// The shared instance
	private static StandardLibraryPlugin plugin;
	
	/**
	 * The constructor
	 */
	public StandardLibraryPlugin() {
    plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
  public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
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
	public static StandardLibraryPlugin getDefault() {
		return plugin;
	}

}
