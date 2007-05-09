package tudresden.ocl20.pivot.metamodels.uml;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class UmlMetamodelPlugin extends Plugin {

	// The plug-in ID
	public static final String ID = "tudresden.ocl20.pivot.metamodels.uml";

	// The shared instance
	private static UmlMetamodelPlugin plugin;
	
	/**
	 * The constructor
	 */
	public UmlMetamodelPlugin() {
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
	public static UmlMetamodelPlugin getDefault() {
		return plugin;
	}

}
