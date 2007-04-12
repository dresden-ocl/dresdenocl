package tudresden.ocl20.pivot.modelbus;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.modelbus.internal.MetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.internal.ModelRegistry;

/**
 * The activator class controls the plug-in life cycle
 */
public class ModelBusPlugin extends Plugin {

  // The plug-in ID
  public static final String ID = "tudresden.ocl20.pivot.modelbus"; //$NON-NLS-1$

  // The shared instance
  private static ModelBusPlugin plugin;

  // the registry of registered metamodels
  private IMetamodelRegistry metamodelRegistry;

  // the registry for models
  private IModelRegistry modelRegistry;

  /**
   * The constructor
   */
  public ModelBusPlugin() {
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

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {

    // dispose the metamodel registry
    if (metamodelRegistry != null) {
      metamodelRegistry.dispose();
      metamodelRegistry = null;
    }
    
    // dispose the model registry
    if (modelRegistry != null) {
      modelRegistry.dispose();
      modelRegistry = null;
    }

    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * 
   * @return the shared instance
   */
  public static ModelBusPlugin getDefault() {
    return plugin;
  }

  /**
   * Returns the {@link IMetamodelRegistry} managed by the <code>ModelBusPlugin</code>.
   * 
   * @return an <code>IMetamodelRegistry</code> instance
   */
  public static IMetamodelRegistry getMetamodelRegistry() {
    // check that the plugin has been activated
    if (plugin == null) {
      throw new IllegalStateException("The Model Bus plugin has not been activated."); //$NON-NLS-1$
    }

    // lazily create the registry
    if (plugin.metamodelRegistry == null) {
      plugin.metamodelRegistry = new MetamodelRegistry();
    }

    return plugin.metamodelRegistry;
  }

  /**
   * Returns the {@link IModelRegistry} managed by the <code>ModelBusPlugin</code>.
   * 
   * @return an <code>IModelRegistry</code> instance
   */
  public static IModelRegistry getModelRegistry() {
    // check that the plugin has been activated
    if (plugin == null) {
      throw new IllegalStateException("The Model Bus plugin has not been activated."); //$NON-NLS-1$
    }

    // lazily create the registry
    if (plugin.modelRegistry == null) {
      plugin.modelRegistry = new ModelRegistry();
    }

    return plugin.modelRegistry;
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
