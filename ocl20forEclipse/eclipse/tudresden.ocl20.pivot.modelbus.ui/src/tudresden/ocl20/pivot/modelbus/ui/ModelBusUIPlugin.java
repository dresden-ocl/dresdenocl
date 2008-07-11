package tudresden.ocl20.pivot.modelbus.ui;

import org.apache.log4j.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class ModelBusUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String ID = "tudresden.ocl20.pivot.modelbus.ui"; //$NON-NLS-1$

	// The shared instance
	private static ModelBusUIPlugin plugin;
	
	/**
	 * The constructor
	 */
	public ModelBusUIPlugin() {
    // no implementation necessary
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
  public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
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
	public static ModelBusUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(ID, path);
	}
  
  /**
   * Facade method for the classes in this plugin that hides the dependency from the
   * <code>tudresden.ocl20.logging</code> plugin.
   * 
   * @param clazz the class to return the logger for
   * 
   * @return a log4j <code>Logger</code> instance
   */
  public static Logger getLogger(Class<?> clazz) {
    return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
  }

}
