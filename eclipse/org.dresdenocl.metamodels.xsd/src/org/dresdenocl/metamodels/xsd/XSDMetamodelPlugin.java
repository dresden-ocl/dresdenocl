package tudresden.ocl20.pivot.metamodels.xsd;

import org.apache.log4j.Logger;

import org.eclipse.core.runtime.Plugin;

import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class XSDMetamodelPlugin extends Plugin {
  
  // The plug-in ID
  public static final String ID = "tudresden.ocl20.pivot.metamodels.xsd"; //$NON-NLS-1$

  // The shared instance
  private static XSDMetamodelPlugin plugin;
  
  /**
   * The constructor
   */
  public XSDMetamodelPlugin() {
    	plugin = this;
  }

  /*
   * (non-Javadoc)
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
  public static XSDMetamodelPlugin getDefault() {
    return plugin;
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
   * @generated
   */
  public static Logger getLogger(Class<?> clazz) {

    return LoggingPlugin.getLogManager(plugin).getLogger(clazz);
  }
}