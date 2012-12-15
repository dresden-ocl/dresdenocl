package tudresden.ocl20.logging.internal;

import java.net.URL;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.spi.RootLogger;
import org.eclipse.core.runtime.Plugin;

import tudresden.ocl20.logging.ILogManager;
import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.logging.appender.ErrorLogAppender;
import tudresden.ocl20.logging.appender.PluginLogFileAppender;

/**
 * Default implementation of the <code>ILogManager</code> interface.
 * 
 * @author Matthias Braeuer
 * @version 1.0 13.03.2007
 */
public class DefaultLogManager implements ILogManager {

  // a logger for this class
  private static final Logger logger = Logger
      .getLogger(DefaultLogManager.class);

  // the plugin that belongs to this log manager
  private Plugin plugin;

  // the log4j hierarchy managed by this log manager
  private Hierarchy hierarchy;

  // flag indicating whether this log manager has been disposed
  private boolean disposed;

  /**
   * Creates a new <code>DefaultLogManager</code> instance for the given
   * {@link Plugin}. A log4j {@link Hierarchy} object will be created
   * specifically for this <code>LogManager</code>.
   * 
   * @param aPlugin the corresponding Eclipse plugin, must NOT be
   *          <code>null</code>
   */
  public DefaultLogManager(final Plugin aPlugin) {
    if (logger.isDebugEnabled()) {
      logger.debug("DefaultLogManager(plugin=" + aPlugin + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // precondition check
    if (aPlugin == null) {
      throw new IllegalArgumentException("The parameter 'plugin' was null!"); //$NON-NLS-1$
    }

    // set the plugin
    this.plugin = aPlugin;

    // instantiate a new log4j hierarchy with log level set to WARN
    hierarchy = new Hierarchy(new RootLogger(Level.WARN));

    // add a special hierarchy listener to configure the Eclipse appenders if
    // necessary
    hierarchy.addHierarchyEventListener(new PluginEventListener());

    // configure the hierarchy
    new PropertyConfigurator().doConfigure(getLoggerPropertiesUrl(), hierarchy);

    if (logger.isDebugEnabled()) {
      logger.debug("DefaultLogManager() - exit"); //$NON-NLS-1$
    }
  }

  /**
   * Helper method to find a custom version of the logger properties in the
   * associated plugin. If no custom properties are found, the URL of the
   * default properties in this plugin is returned.
   * 
   * @return a <code>URL</code> instance
   */
  private URL getLoggerPropertiesUrl() {
    if (logger.isDebugEnabled()) {
      logger.debug("getLoggerPropertiesUrl() - enter"); //$NON-NLS-1$
    }

    URL url;

    // try to load the config file from the main path of the plugin
    url = plugin.getBundle().getEntry("/" + DEFAULT_CONFIG_FILE_NAME); //$NON-NLS-1$

    // try to load a resource from the plugin
    if (url == null) {
      url = plugin.getBundle().getResource(DEFAULT_CONFIG_FILE_NAME);
    }

    // default to own logger properties
    if (url == null) {
      url = LoggingPlugin.getDefault().getBundle().getEntry(
          "/" + DEFAULT_CONFIG_FILE_NAME); //$NON-NLS-1$
    }

    if (logger.isDebugEnabled()) {
      logger.debug("getLoggerPropertiesUrl() - exit - return value=" + url); //$NON-NLS-1$
    }

    return url;
  }

  /**
   * Returns the logger with the given name managed by the log4j
   * {@link Hierarchy} encapsulated by this log manager.
   * 
   * @param name the name of the desired logger
   * 
   * @return a <code>Logger</code> instance
   */
  public Logger getLogger(final String name) {
    return hierarchy.getLogger(name);
  }

  /**
   * Delegates to the encapsulated {@link Hierarchy log4j hierarchy}'s
   * {@link Hierarchy#getLogger(String)} method by passing the
   * {@link Class#getCanonicalName() canonical name} of the given class.
   * 
   * @param clazz the class to return a logger for
   * 
   * @return a <code>Logger</code> instance
   */
  public Logger getLogger(final Class<?> clazz) {
    return hierarchy.getLogger(clazz.getCanonicalName());
  }

  /**
   * Returns the root logger of the encapsulated
   * {@link Hierarchy log4j hierarchy}.
   * 
   * @return a <code>Logger</code> instance
   */
  public Logger getRootLogger() {
    return hierarchy.getRootLogger();
  }


  /**
   * Shuts down the encapsulated {@link Hierarchy log4j hierarchy}.
   */
  public void dispose() {
    if (logger.isDebugEnabled()) {
      logger.debug("dispose() - enter"); //$NON-NLS-1$
    }

    // prevent expensive shutdown calls on all the hierarchy's appenders
    if (!disposed) {
      hierarchy.shutdown();
      disposed = true;
    }

    if (logger.isDebugEnabled()) {
      logger.debug("dispose() - exit"); //$NON-NLS-1$
    }
  }


  /**
   * Returns a string representation of this log manager using the Jakarta
   * Commons Lang library. 
   * 
   * @return a <code>String</code> instance
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(
        "plugin", plugin).append("disposed", disposed).toString(); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * Returns the plugin associated with this log manager.
   * 
   * @return a <code>Plugin</code> instance or <code>null</code> if no
   *         plugin has been set
   */
  protected Plugin getPlugin() {
    return plugin;
  }

  /**
   * A custom hierarchy event listener that will set the necessary properties
   * for the special plugin-based log4j appenders. Note that we don't need to
   * check whether the plugin belonging to this LogManager is null because in
   * this case the listener would not have been attached to the log4j hierarchy.
   */
  protected class PluginEventListener implements HierarchyEventListener {

    /**
     * Logger for this class.
     */
    private final Logger logger = Logger.getLogger(PluginEventListener.class);

    /**
     * Called when a new appender is added for a particular level. Internally it
     * checks if the appender is one of our custom ones and sets its custom
     * properties.
     * 
     * @param category level
     * @param appender appender added for this level
     */
    public void addAppenderEvent(final Category category,
        final Appender appender) {
      if (logger.isDebugEnabled()) {
        logger
            .debug("addAppenderEvent(cat=" + category + ", appender=" + appender + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }

      if (appender instanceof ErrorLogAppender) {
        ((ErrorLogAppender) appender).setLog(getPlugin().getLog());
      }

      else if (appender instanceof PluginLogFileAppender) {
        ((PluginLogFileAppender) appender).setStateLocation(getPlugin()
            .getStateLocation());
      }

      if (logger.isDebugEnabled()) {
        logger.debug("addAppenderEvent() - exit"); //$NON-NLS-1$
      }
    }

    /**
     * Called when an appender is removed from for a particular level. Does
     * nothing.
     * 
     * @param category level
     * @param appender appender added for this level
     */
    public void removeAppenderEvent(final Category category,
        final Appender appender) {
      // no implementation necessary
    }
  }

}
