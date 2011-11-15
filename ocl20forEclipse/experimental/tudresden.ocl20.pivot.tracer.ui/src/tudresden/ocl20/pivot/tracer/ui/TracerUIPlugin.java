package tudresden.ocl20.pivot.tracer.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TracerUIPlugin extends AbstractUIPlugin implements BundleActivator {

    private static BundleContext context;

    /** the plug-in ID */
    public static final String PLUGIN_ID = "tudresden.ocl20.pivot.tracer.ui";

    /** the ID of the {@link TracerView}. */
    public static final String VIEW_ID = "tudresden.ocl20.pivot.tracer.ui.internal.views.TracerView";

    /** the shared instance of this plug-in */
    private static TracerUIPlugin plugin;

    static BundleContext getContext() {
	return context;
    }

    public TracerUIPlugin() {
	plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    public void start(BundleContext bundleContext) throws Exception {
	TracerUIPlugin.context = bundleContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext bundleContext) throws Exception {
	TracerUIPlugin.context = null;
    }

    /**
     * <p>
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     * </p>
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
	return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

}
