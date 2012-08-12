/*
Copyright (C) 2011 by Lars Schuetze (lschuetze@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.tracer;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;

/**
 * <p>This plug-in enables tracing of the interpretation of OCL from the interpreter plug-in.</p>
 * 
 * @author Lars Schuetze
 */
public class TracerPlugin extends Plugin {

	/** the plug-in ID */
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.tracer";

	/** the shared instance of the plug-in */
	private static TracerPlugin plugin;

	/**
	 * The constructor
	 */
	public TracerPlugin() {
		//remains empty
		//
	}


	/**
	 * <p>Remove the {@link TracerPlugin} from the listeners of the {@link OclInterpreterPlugin}.</p>
	 */
	private static void disposeInterpreterTraceListener() {
//TODO: refactor into ui
/*
		if (plugin.listener != null) {
			// Remove this plug-in from the listeners of the interpreter.
			//
			OclInterpreterPlugin.getInterpreterRegistry()
					.removeInterpreterTraceListener(plugin.listener);
		}
*/
	}

	public static TracerPlugin getDefault() {

		if(plugin == null) {
			plugin = new TracerPlugin();
		}
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext bundleContext) throws Exception {

		super.start(bundleContext);
		
		plugin = this;

		// configure custom logging properties.
		//
		LoggingPlugin.configureDefaultLogging(plugin);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext bundleContext) throws Exception {
//TODO: refactor disposing		
		try {
			//disposeInterpreterTraceListener();
			plugin = null;
		}
		finally {
			super.stop(bundleContext);
		}
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

}
