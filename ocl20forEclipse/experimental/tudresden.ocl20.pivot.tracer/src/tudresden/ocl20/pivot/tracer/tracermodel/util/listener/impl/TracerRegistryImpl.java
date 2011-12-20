/**
 * 
 */
package tudresden.ocl20.pivot.tracer.tracermodel.util.listener.impl;

import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistryListener;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistry;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistryEvent;

/**
 * @author Lars Schütze
 * 
 */
public class TracerRegistryImpl implements TracerRegistry {

	/**
	 * 
	 */
	private ListenerList tracerListeners;

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistry#
	 * addTracerRegistryListener
	 * (tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerListener)
	 */
	public void addTracerRegistryListener(TracerRegistryListener listener) {

		getTracerListener().add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistry#
	 * removeTracerRegistryListener
	 * (tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerListener)
	 */
	public void removeTracerRegistryListener(TracerRegistryListener listener) {

		getTracerListener().remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistry#
	 * fireUpdateTrace
	 * (tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistryEvent
	 * )
	 */
	public void fireUpdateTrace(TracerRoot trace) {

		Object[] listeners;
		listeners = getTracerListener().getListeners();

		TracerRegistryEvent event = null;

		for (int i = 0; i < listeners.length; i++) {
			/* Lazily create the event. */
			if (event == null) {
				event = new TracerRegistryEvent(this, trace);
			}
			/* Inform all listeners to update their trace. */
			((TracerRegistryListener) listeners[i]).updateTrace(event);
		}
	}

	/**
	 * 
	 * @return The listener from the registry.
	 */
	private ListenerList getTracerListener() {

		if (tracerListeners == null) {
			tracerListeners = new ListenerList(ListenerList.IDENTITY);
		}
		return tracerListeners;
	}

}
