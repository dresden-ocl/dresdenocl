package tudresden.ocl20.pivot.metamodels.uml2;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class UML2MetamodelPlugin extends Plugin {

	// The plug-in ID
	public static final String ID = "tudresden.ocl20.pivot.metamodels.uml2"; //$NON-NLS-1$

	// The shared instance
	private static UML2MetamodelPlugin plugin;

	/**
	 * The constructor
	 */
	public UML2MetamodelPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
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
	public static UML2MetamodelPlugin getDefault() {
		return plugin;
	}
}