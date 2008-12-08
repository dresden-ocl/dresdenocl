package tudresden.ocl20.pivot.models.uml2;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Class Wilke
 */
public class Uml2ModelPlugin extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.models.uml";

	// The shared instance
	private static Uml2ModelPlugin plugin;

	/**
	 * <p>
	 * The constructor of the {@link Uml2ModelPlugin}.
	 * </p>
	 */
	public Uml2ModelPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * @return The shared instance.
	 */
	public static Uml2ModelPlugin getDefault() {
		return plugin;
	}

}
