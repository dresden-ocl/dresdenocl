package tudresden.ocl20.pivot.tracer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;

public class TracerPlugin implements BundleActivator {

	private static BundleContext context;
	
	/** the plug-in ID */
	public static final String PLUGIN_ID = "tudresden.ocl20.pivot.tracer";
	
	/** the shared instance of the plug-in */
	private static TracerPlugin plugin;
	
	/** the listener who is holding the tree structure */
	private InterpreterRegistryListenerImpl listener;
	
	public TracerPlugin() {
		plugin = this;
	}
	
	public static InterpreterRegistryListenerImpl getInterpreterTraceListener() {
		if(plugin == null) {
			//System.out.println("The Tracer has not been activated. Initialized it manually.");
			plugin = new TracerPlugin();
		}
		//no else
		
		if(plugin.listener == null) {
			plugin.listener = new InterpreterRegistryListenerImpl();
			
			/* add the listener object to the registry */
			OclInterpreterPlugin.getInterpreterRegistry()
				.addInterpreterTraceListener(plugin.listener);		
		}
		//no else
		
		return plugin.listener;
	}
	
	public static void disposeInterpreterTraceListener() {
		if(plugin.listener != null) {
			OclInterpreterPlugin.getInterpreterRegistry()
				.removeInterpreterTraceListener(plugin.listener);
		}
	}
	
	public static TracerPlugin getDefault() {
		return plugin;
	}

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		TracerPlugin.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		TracerPlugin.context = null;
	}

}
