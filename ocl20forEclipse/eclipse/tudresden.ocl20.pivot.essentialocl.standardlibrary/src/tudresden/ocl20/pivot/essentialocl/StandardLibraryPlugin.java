package tudresden.ocl20.pivot.essentialocl;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.provider.IOclLibraryProvider;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.provider.OclLibraryProvider;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.provider.StandaloneOclLibraryProvider;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class StandardLibraryPlugin extends Plugin {

	// The plug-in ID
	public static final String ID =
			"tudresden.ocl20.pivot.essentialocl.standardlibrary"; //$NON-NLS-1$

	// The shared instance
	private static StandardLibraryPlugin plugin;

	/** The standard OCL library provider */
	private IOclLibraryProvider oclLibraryProvider;

	/**
	 * The constructor
	 */
	public StandardLibraryPlugin() {

		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
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

	/**
	 * <p>
	 * Returns the {@link IOclLibraryProvider} that is used to get an instance of
	 * the {@link OclLibrary}.
	 * </p>
	 * <p>
	 * If no {@link IOclLibraryProvider} has been given to the
	 * {@link ModelBusPlugin}, the standard {@link OclLibraryProvider} will be
	 * used. This is important when using DresdenOCL stand-alone, as the
	 * {@link StandaloneOclLibraryProvider} should be set first, using
	 * {@link #setOclLibraryProvider(IOclLibraryProvider)}.
	 * </p>
	 * 
	 * @see #setOclLibraryProvider(IOclLibraryProvider)
	 * 
	 * @return the {@link IOclLibraryProvider} that is used to get an instance of
	 *         the {@link OclLibrary}
	 */
	public synchronized static IOclLibraryProvider getOclLibraryProvider() {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Model Bus plugin has not been activated."); //$NON-NLS-1$
		}

		/* Lazyily create the provider */
		if (plugin.oclLibraryProvider == null)
			plugin.oclLibraryProvider = new OclLibraryProvider();

		return plugin.oclLibraryProvider;
	}

	/**
	 * Sets the {@link IOclLibraryProvider} of the {@link ModelBusPlugin}. This
	 * method has to be called when using DresdenOCL stand-alone. The standard
	 * argument should be {@link StandaloneOclLibraryProvider}.
	 * 
	 * @param oclLibraryProvider
	 *          the {@link IOclLibraryProvider} to set
	 */
	public synchronized static void setOclLibraryProvider(
			IOclLibraryProvider oclLibraryProvider) {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Model Bus plugin has not been activated."); //$NON-NLS-1$
		}

		plugin.oclLibraryProvider = oclLibraryProvider;
	}
	
	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>tudresden.ocl20.logging</code> plug-in.
	 * </p>
	 * 
	 * @param clazz
	 *          The {@link Class} to return the {@link Logger} for.
	 * 
	 * @return A log4j {@link Logger}> instance.
	 * 
	 * @generated NOT
	 */
	public static Logger getLogger(Class<?> clazz) {

		return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
	}


}
