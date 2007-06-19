package tudresden.ocl20.logging;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Logger;

/**
 * This is the main interface provided for the Eclipse log4j integration.
 * 
 * Client plugins can configure the log4j logger returned by their associated
 * <code>ILogManager</code> through a file with name {@link #DEFAULT_CONFIG_FILE_NAME}. The file
 * should either be located in the root path of the plugin or be accessible as a resource through
 * the plugin's classloader. If no custom config file is found, a default configuration provided by
 * the {@link LoggingPlugin} is used.
 * 
 * @author Matthias Braeuer
 * @version 1.0 13.03.2007
 */
public interface ILogManager {

  /**
   * The default file name for the log4j configuration file.
   * 
   * <p>
   * Constant field value: <code>"logger.properties"</code>
   * </p>
   */
  String DEFAULT_CONFIG_FILE_NAME = "logger.properties"; //$NON-NLS-1$

  /**
   * Returns the log4j logger with the given name.
   * 
   * @param name the name of the logger that should be returned
   * 
   * @return a <code>Logger</code> instance
   */
  Logger getLogger(String name);

  /**
   * Returns the log4j logger for the given class. The class's
   * {@link Class#getCanonicalName() canonical name} is used to lookup the logger.
   * 
   * @param clazz the class a logger should be returned for
   * 
   * @return a <code>Logger</code> instance
   */
  Logger getLogger(Class<?> clazz);

  /**
   * Returns the log4j root logger.
   * 
   * @return a <code>Logger</code> instance
   */
  Logger getRootLogger();

  /**
   * Diposes this log manager. This includes, e.g., shutting down the associated log4j
   * {@link Hierarchy} and all registered appenders.
   */
  void dispose();

}
