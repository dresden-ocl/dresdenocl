/*
Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)

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
package tudresden.ocl20.pivot.tracer.tracermodel.util.listener.impl;

import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistry;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistryEvent;
import tudresden.ocl20.pivot.tracer.tracermodel.util.listener.TracerRegistryListener;

/**
 * <p>Implementation of the registry of the tracer.</p>
 * 
 * @author Lars Schütze
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
