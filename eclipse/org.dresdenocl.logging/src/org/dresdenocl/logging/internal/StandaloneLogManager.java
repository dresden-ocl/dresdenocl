package org.dresdenocl.logging.internal;

import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.RootLogger;

import org.dresdenocl.logging.ILogManager;

public class StandaloneLogManager implements ILogManager {

	// the log4j hierarchy managed by this log manager
	private Hierarchy hierarchy;

	// flag indicating whether this log manager has been disposed
	private boolean disposed;

	/**
	 * Creates a new <code>StandaloneLogManager</code> instance for the given
	 * {@link URL}. A log4j {@link Hierarchy} object will be created specifically
	 * for this <code>LogManager</code>.
	 * 
	 * @param loggerPropertiesUrl
	 *          the location of the properties for, must NOT be <code>null</code>
	 */
	public StandaloneLogManager(URL loggerPropertiesUrl) {

		// instantiate a new log4j hierarchy with log level set to WARN
		hierarchy = new Hierarchy(new RootLogger(Level.WARN));

		if (loggerPropertiesUrl != null)
			new PropertyConfigurator().doConfigure(loggerPropertiesUrl, hierarchy);
		
		else {
			Properties properties = new Properties();
			properties.put("log4j.rootLogger", "error, stdout");
			properties.put("log4j.appender.stdout",
					"org.apache.log4j.ConsoleAppender");
			properties.put("log4j.appender.stdout.layout",
					"org.apache.log4j.PatternLayout");
			properties.put("log4j.appender.stdout.layout.ConversionPattern",
					"%c: %m%n");
			new PropertyConfigurator().doConfigure(properties, hierarchy);
		}

	}

	public void dispose() {

		// prevent expensive shutdown calls on all the hierarchy's appenders
		if (!disposed) {
			hierarchy.shutdown();
			disposed = true;
		}
	}

	public Logger getLogger(String name) {

		return hierarchy.getLogger(name);
	}

	public Logger getLogger(Class<?> clazz) {

		return hierarchy.getLogger(clazz.getCanonicalName());
	}

	public Logger getRootLogger() {

		return hierarchy.getRootLogger();
	}
}
