package org.dresdenocl.logging;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import org.dresdenocl.logging.appender.ErrorLogAppender;
import org.dresdenocl.logging.internal.DefaultLogManager;
import org.dresdenocl.logging.internal.StandaloneLogManager;

/**
 * The LoggingPlugin integrates log4j and allows client plugins to retrieve an
 * {@link ILogManager} object via the {@link #getLogManager(Plugin)} method.
 * Having a separate log manager (and an associated log4j {@link Hierarchy}) for
 * each client plugin is necessary for a clean integration with the Eclipse
 * architecture where each plugin has its own classloader, resources and
 * {@link ILog} instance.
 * 
 * <p>
 * The code for this plugin is loosely based on the article
 * <em>"Plugging in a logging framework for
 * Eclipse plug-ins"</em> by Manoel Marques available at the IBM developerWorks
 * site. Large portions of the code and its behaviour have been changed, some
 * aspects removed and some features added.
 * </p>
 * 
 * <p>
 * To use the LoggingPlugin, simply retrieve an {@link ILogManager} for your
 * plugin like this:
 * 
 * <pre>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * ILogManager logManager = LoggingPlugin.getLogManager(MyPlugin.getDefault());
 * </pre>
 * 
 * You can then get your log4j loggers from the <code>ILogManager</code> using
 * the familiar <code>getLogger()</code> methods. You can also combine
 * everything into one call like this:
 * 
 * <pre>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Logger logger = LoggingPlugin.getLogManager(MyPlugin.getDefault()).getLogger(
 * 		MyClass.class);
 * </pre>
 * 
 * An alternative is to provide a facade method in your Plugin class in order
 * not to introduce dependencies to the LoggingPlugin class in all your classes.
 * Then you can simply call:
 * 
 * <pre>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Logger logger = MyPlugin.getLogger(MyClass.class);
 * </pre>
 * 
 * and the <code>getLogger()</code> method in <code>MyPlugin</code> will deal
 * with getting the <code>ILogManager</code>. See the Javadoc for
 * {@link ILogManager} for details on how to customize the log configuration
 * used by the log manager.
 * </p>
 * 
 * <p>
 * Alternatively, you can entirely do without the <code>ILogManager</code> API
 * and retrieve your loggers the traditional log4j way:
 * 
 * <pre>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Logger logger = Logger.getLogger(MyClass.class);
 * </pre>
 * 
 * This way, a default log4j configuration provided by the
 * <code>LoggingPlugin</code> will be used and all logs will appear under the
 * <code>org.dresdenocl.logging</code> plugin id. See
 * {@link #configureDefaultLogging(Plugin)} for more information on how to
 * customize logging in this case.
 * </p>
 * 
 * @author Matthias Braeuer
 * @version 1.0 14/03/2007
 */
public class LoggingPlugin extends Plugin {

	// an identifier for the default log manager (used if null is passed when
	// requesting a log mgr.)
	private static final String DEFAULT_LOG_MANAGER_NAME = "org.dresdenocl.logging"; //$NON-NLS-1$

	// the system property used to activate debug logging for the logging plugin
	private static final String DEBUG_SYSTEM_PROPERTY = "org.dresdenocl.logging.debug"; //$NON-NLS-1$

	// the default name of the log4j configuration file
	private static final String DEFAULT_LOG4J_CONFIGURATION_FILE = "log4j.properties"; //$NON-NLS-1$

	// The plug-in ID
	public static final String ID = "org.dresdenocl.logging"; //$NON-NLS-1$

	// The shared instance
	private static LoggingPlugin plugin;

	// maps plugins to their associated log managers
	private Map<String, ILogManager> logManagers;

	// used in standalone-mode to configure logging via a single properties file
	private URL loggerPropertiesUrl;

	/**
	 * Creates a new <code>LoggingPlugin</code> instance.
	 */
	public LoggingPlugin() {

		plugin = this;
	}

	/**
	 * Creates a new {@link LoggingPlugin} instance. Use this constructor when
	 * developing stand-alone applications.
	 * 
	 * @param loggerPropertiesUrl
	 *            points to a Log4j properties file
	 */
	public LoggingPlugin(URL loggerPropertiesUrl) {

		this();
		this.loggerPropertiesUrl = loggerPropertiesUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {

		super.start(context);

		checkIfDebugEnabled();
		initializeLogManagers();
	}

	/**
	 * Checks a system property whether debugging should be activated for the
	 * internal log4j logger.
	 */
	private void checkIfDebugEnabled() {

		String debug = System.getProperty(DEBUG_SYSTEM_PROPERTY);

		if (debug != null && debug.equalsIgnoreCase("true")) { //$NON-NLS-1$
			Logger.getRootLogger().setLevel(Level.DEBUG);
		}
	}

	/**
	 * Create the map of log managers and initialize the default log manager for
	 * the logging plugin
	 */
	private void initializeLogManagers() {

		logManagers = new HashMap<String, ILogManager>();
		logManagers
				.put(DEFAULT_LOG_MANAGER_NAME, new DefaultLogManager(plugin));
	}

	/**
	 * Stops the plugin. All log managers registered with the
	 * <code>LoggingPlugin</code> will be disposed.
	 * 
	 * @param context
	 *            the context for this plugin
	 * 
	 * @throws Exception
	 *             if an error occurs
	 * 
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 * @see ILogManager#dispose()
	 * 
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {

		disposeLogManagers();

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the Singleton plugin or <code>null</code> if the plugin has not
	 *         been activated
	 */
	public static LoggingPlugin getDefault() {

		return plugin;
	}

	/**
	 * Returns the {@link ILogManager} for a particular plugin. If the given
	 * <code>plugin</code> is <code>null</code>, the default log manager
	 * belonging to the <code>LoggingPlugin</code> will be returned.
	 * 
	 * @param aPlugin
	 *            the plugin, may be <code>null</code>
	 * 
	 * @return the <code>ILogManager</code> for the given plugin or the default
	 *         log manager
	 */
	public static ILogManager getLogManager(final Plugin aPlugin) {

		// inside Eclipse
		if (Platform.isRunning()) {
			ILogManager logManager;
			String name;

			// invariant check
			if (LoggingPlugin.plugin == null) {
				throw new IllegalStateException(
						"The LoggingPlugin has not been activated by the Eclipse platform."); //$NON-NLS-1$
			}

			// determine the name for the log manager
			name = (aPlugin != null) ? aPlugin.getBundle().getSymbolicName()
					: DEFAULT_LOG_MANAGER_NAME;

			// try to get a log manager for this plugin
			logManager = LoggingPlugin.plugin.logManagers.get(name);

			// no log manager found, so create a new log manager for the given
			// plugin
			if (logManager == null) {
				logManager = new DefaultLogManager(aPlugin);

				synchronized (LoggingPlugin.plugin.logManagers) {
					LoggingPlugin.plugin.logManagers.put(name, logManager);
				}
			}

			return logManager;
		}

		// stand-alone application
		else {
			// invariant check
			if (LoggingPlugin.plugin == null) {
				System.out
						.println("The LoggingPlugin has not been activated. Intialized it manually."); //$NON-NLS-1$
				plugin = new LoggingPlugin();
			}

			final String key = "standaloneLogger";

			if (plugin.logManagers == null)
				plugin.logManagers = new HashMap<String, ILogManager>(1);
			if (plugin.logManagers.containsKey(key))
				return plugin.logManagers.get(key);

			else {
				ILogManager standaloneLogManager = new StandaloneLogManager(
						plugin.loggerPropertiesUrl);
				plugin.logManagers.put(key, standaloneLogManager);
				return standaloneLogManager;
			}
		}
	}

	/**
	 * This method allows to configure the default log4j logger hierarchy that
	 * is automatically created when using one of the <code>getLogger()</code>
	 * methods in the log4j {@link Logger} class. Because Eclipse carefully
	 * separates the classloaders for each plugin, this is the only way a client
	 * plugin can configure the default log4j loggers (loaded by the
	 * <code>org.dresdenocl.logging</code> bundle). This might be necessary
	 * when a client plugin does not want to use the {@link #getLogManager()}
	 * API provided by the <code>LoggingPlugin</code>.
	 * 
	 * <p>
	 * The <code>LoggingPlugin</code> provides a default
	 * <em>log4j.properties</em> configuration file which will be used in this
	 * case. This file sets the log level to {@link Level#WARN WARN}. It also
	 * defines an {@link ErrorLogAppender} named <code>"errorLog"</code>, but
	 * does not assign it to the root logger. This is to prevent the Error Log
	 * from being flooded with messages if clients set the log level for their
	 * loggers to {@link Level#DEBUG DEBUG} during development.
	 * </p>
	 * 
	 * <p>
	 * Client plugins can contribute their own log properties through a
	 * <em>log4j.properties</em> file that is either located in their root path
	 * or accessible via the plugin's classloader. In this file, they may also
	 * use the Error Log appender defined by the <code>LoggingPlugin</code>. If
	 * a custom <code>log4j.properties</code> file is provided, this method will
	 * load it and configure the default log4j logger hierarchy.
	 * </p>
	 * 
	 * @param aPlugin
	 *            the plugin that provides additional log4j properties
	 */
	public static void configureDefaultLogging(final Plugin aPlugin) {

		if (Platform.isRunning()) {
			URL url;

			// precondition check
			if (aPlugin == null) {
				throw new IllegalArgumentException(
						"The parameter 'plugin' was null!"); //$NON-NLS-1$
			}

			// try to load a file from the given plugin's root
			url = aPlugin.getBundle().getEntry(
					"/" + DEFAULT_LOG4J_CONFIGURATION_FILE); //$NON-NLS-1$

			// try to load a resource from the plugin
			if (url == null) {
				url = aPlugin.getBundle().getResource(
						DEFAULT_LOG4J_CONFIGURATION_FILE);
			}

			// configure additional properties
			if (url != null) {
				PropertyConfigurator.configure(url);
			}
		}
		// no else.
	}

	// disposes all currently registered log managers
	private void disposeLogManagers() {

		synchronized (logManagers) {
			for (ILogManager logManager : logManagers.values()) {
				logManager.dispose();
			}
		}
	}

}
