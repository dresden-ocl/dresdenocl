package org.dresdenocl.essentialocl;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.dresdenocl.logging.LoggingPlugin;
import org.dresdenocl.essentialocl.standardlibrary.provider.IOclLibraryProvider;
import org.dresdenocl.essentialocl.standardlibrary.provider.OclLibraryProvider;
import org.dresdenocl.essentialocl.standardlibrary.provider.StandaloneOclLibraryProvider;
import org.dresdenocl.essentialocl.types.OclLibrary;

/**
 * The only purpose of this plugin is to initialize the {@link LoggingPlugin
 * logging support}. This enables fine-tuning the log behaviour by providing a
 * custom <code>log4j.properties</code> file. If the Essential OCL
 * implementation is used outside of Eclipse, this plugin is not needed. The
 * classes in this plugin only refer to the log4j {@link Logger} class, never to
 * the custom Eclipse logging support provided by the <code>LoggingPlugin</code>
 * . Thus, logging behaviour can be retained even outside of Eclipse if the
 * log4j libraries are added to the classpath.
 * 
 * @author Matthias Braeuer
 * @version 1.0 20.03.2007
 */
public class EssentialOclPlugin extends Plugin {

	/**
	 * The id of this plugin
	 */
	public static final String ID = "org.dresdenocl.essentialocl"; //$NON-NLS-1$

	// The shared instance
	private static EssentialOclPlugin plugin;

	/** The standard OCL library provider */
	private IOclLibraryProvider oclLibraryProvider;

	/**
	 * Creates a new <code>PivotModelPlugin</code>. This is done by the
	 * workbench.
	 */
	public EssentialOclPlugin() {

		super();
		plugin = this;
	}

	/**
	 * <p>
	 * Returns the {@link IOclLibraryProvider} that is used to get an instance
	 * of the {@link OclLibrary}.
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
	 * @return the {@link IOclLibraryProvider} that is used to get an instance
	 *         of the {@link OclLibrary}
	 */
	public synchronized static IOclLibraryProvider getOclLibraryProvider() {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			// throw new IllegalStateException(
			//					"The Essential OCL plugin has not been activated."); //$NON-NLS-1$
			plugin = new EssentialOclPlugin();
			System.out
					.println("The Essential OCL plugin has not been activated. Intialized it manually.");
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
	 *            the {@link IOclLibraryProvider} to set
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
	 * Overridden to additionally configure the logging support for this plugin.
	 * 
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
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
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

		super.stop(context);
	}

	/**
	 * <p>
	 * Facade method for the classes in this plug-in that hides the dependency
	 * from the <code>org.dresdenocl.logging</code> plug-in.
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
