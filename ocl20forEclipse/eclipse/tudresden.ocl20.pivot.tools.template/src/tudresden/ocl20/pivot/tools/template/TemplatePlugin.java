package tudresden.ocl20.pivot.tools.template;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.tools.template.internal.TemplateEngineRegistry;
import tudresden.ocl20.pivot.tools.template.internal.TemplateGroupRegistry;

/**
 * <p>
 * The activator class controls the plug-in life cycle.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class TemplatePlugin extends Plugin {

	/** The plug-in ID. */
	public static final String ID = "tudresden.ocl20.pivot.tools.template"; //$NON-NLS-1$

	private ITemplateGroupRegistry templateGroupRegistry;

	private ITemplateEngineRegistry templateEngineRegistry;

	/** The shared instance. */
	private static TemplatePlugin plugin;

	/**
	 * <p>
	 * Creates a new {@link EcoreMetamodelPlugin}.
	 * </p>
	 */
	public TemplatePlugin() {

		plugin = this;
	}

	/**
	 * <p>
	 * Returns the shared instance.
	 * </p>
	 * 
	 * @return the shared instance
	 */
	public static TemplatePlugin getDefault() {

		if (plugin == null)
			plugin = new TemplatePlugin();
		// no else.

		return plugin;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		super.start(context);

		/* configure custom logging properties. */
		LoggingPlugin.configureDefaultLogging(plugin);

		/* Hack to automatically set the TemplateEngineRegistry in Eclipse. */
		plugin.templateEngineRegistry = new TemplateEngineRegistry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

		/* Dispose the meta-model registry. */
		if (plugin.templateGroupRegistry != null) {
			plugin.templateGroupRegistry.dispose();
			plugin.templateGroupRegistry = null;
		}

		plugin = null;

		super.stop(context);
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateGroupRegistry} managed by the
	 * {@link TemplatePlugin}.
	 * </p>
	 * <p>
	 * If no {@link ITemplateGroupRegistry} has been given to the
	 * {@link TemplatePlugin}, the standard {@link TemplateGroupRegistry} will
	 * be used. This is important when using DresdenOCL stand-alone, as the
	 * {@link StandaloneTemplateGroupRegistry} should be set first, using
	 * {@link #setMetamodelRegistry(ITemplateGroupRegistry)}.
	 * </p>
	 * 
	 * @see #setTemplateGroupRegistry(ITemplateGroupRegistry)
	 * 
	 * @return An {@link ITemplateGroupRegistry} instance.
	 */
	public synchronized static ITemplateGroupRegistry getTemplateGroupRegistry() {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		/* Lazily create the registry. */
		if (plugin.templateGroupRegistry == null) {
			plugin.templateGroupRegistry = new TemplateGroupRegistry();
		}

		return plugin.templateGroupRegistry;
	}

	/**
	 * Sets the {@link ITemplateGroupRegistry} of the {@link TemplatePlugin}.
	 * This method has to be called when using DresdenOCL stand-alone. The
	 * standard argument should be {@link StandaloneTemplateGroupRegistry}.
	 * 
	 * @param templateGroupRegistry
	 *            the {@link ITemplateGroupRegistry} to set
	 */
	public synchronized static void setTempateGroupRegistry(
			ITemplateGroupRegistry templateGroupRegistry) {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		plugin.templateGroupRegistry = templateGroupRegistry;
	}

	/**
	 * <p>
	 * Returns the {@link ITemplateEngineRegistry} managed by the
	 * {@link TemplatePlugin}.
	 * </p>
	 * <p>
	 * If no {@link ITemplateEngineRegistry} has been given to the
	 * {@link TemplatePlugin}, the standard {@link TemplateEngineRegistry} will
	 * be used. This is important when using DresdenOCL stand-alone, as the
	 * {@link StandaloneTemplateEngineRegistry} should be set first, using
	 * {@link #setMetamodelRegistry(ITemplateEngineRegistry)}.
	 * </p>
	 * 
	 * @see #setTemplateEngineRegistry(ITemplateEngineRegistry)
	 * 
	 * @return An {@link ITemplateEngineRegistry} instance.
	 */
	public synchronized static ITemplateEngineRegistry getTemplateEngineRegistry() {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		/* Lazily create the registry. */
		if (plugin.templateEngineRegistry == null) {
			throw new IllegalStateException(
					"The templateEngineRegistry must not be null.");
		}

		return plugin.templateEngineRegistry;
	}

	/**
	 * Sets the {@link ITemplateEngineRegistry} of the {@link TemplatePlugin}.
	 * This method has to be called when using DresdenOCL stand-alone. The
	 * standard argument should be {@link StandaloneTemplateEngineRegistry}.
	 * 
	 * @param templateEngineRegistry
	 *            the {@link ITemplateEngineRegistry} to set
	 */
	public synchronized static void setTempateEngineRegistry(
			ITemplateEngineRegistry templateEngineRegistry) {

		/* Check that the plugin has been activated. */
		if (plugin == null) {
			throw new IllegalStateException(
					"The Template plugin has not been activated."); //$NON-NLS-1$
		}

		plugin.templateEngineRegistry = templateEngineRegistry;
	}

}