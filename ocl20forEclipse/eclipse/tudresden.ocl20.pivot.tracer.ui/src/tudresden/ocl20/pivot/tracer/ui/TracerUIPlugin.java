package tudresden.ocl20.pivot.tracer.ui;

import org.apache.log4j.Logger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.tracer.ui.internal.views.TracerView;

public class TracerUIPlugin extends AbstractUIPlugin {

	/** the plug-in ID */
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.tracer.ui";

	/** the ID of the {@link TracerView}. */
	public static final String TRACER_VIEW_ID =
			"tudresden.ocl20.pivot.tracer.ui.internal.views.TracerView";

	/** the shared instance of this plug-in */
	private static TracerUIPlugin plugin;

	public TracerUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext )
	 */
	public void start(BundleContext bundleContext) throws Exception {

		super.start(bundleContext);
		
		plugin = this;

		/* configure custom logging properties. */
		LoggingPlugin.configureDefaultLogging(plugin);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {

		plugin = null;
		super.stop(bundleContext);
	}

	/**
	 * <p>
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * </p>
	 * 
	 * @param path
	 *          the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {

		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *            The {@link Class} to return the {@link Logger} for.
	 * 
	 * @return A log4j {@link Logger}> instance.
	 * 
	 * @generated NOT
	 */
	public static Logger getLogger(Class<?> clazz) {

		return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
	}

}
