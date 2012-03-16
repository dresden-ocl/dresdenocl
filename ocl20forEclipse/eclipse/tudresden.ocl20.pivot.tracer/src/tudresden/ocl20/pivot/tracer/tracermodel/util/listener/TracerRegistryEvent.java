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
package tudresden.ocl20.pivot.tracer.tracermodel.util.listener;

import java.util.EventObject;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

/**
 * <p>An event send by the {@link tudresden.ocl20.pivot.tracer.TracerPlugin TracerPlugin}.</p>
 * @author Lars Schuetze
 *
 */
public class TracerRegistryEvent extends EventObject {

	private static final long serialVersionUID = -3687475668757628934L;
	
	/** The root element of the trace. */
	private TracerRoot trace;

	/**
	 * <p>The constructor to the event object.</p>
	 * @param source The {@link TracerRegistry} sending the event.
	 * @param trace The {@link TracerRoot} to be send to all listeners.
	 */
	public TracerRegistryEvent(TracerRegistry source, TracerRoot trace) {

		super(source);
		this.trace = trace;
	}

	@Override
	public TracerRegistry getSource() {

		return (TracerRegistry) source;
	}

	/**
	 * @return The {@link TracerRoot} that has been traced.
	 */
	public TracerRoot getTrace() {

		return trace;
	}
}
