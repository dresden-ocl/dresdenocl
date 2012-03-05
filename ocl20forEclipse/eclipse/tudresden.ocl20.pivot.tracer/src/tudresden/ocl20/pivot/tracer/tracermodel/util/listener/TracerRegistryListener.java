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
package tudresden.ocl20.pivot.tracer.tracermodel.util.listener;

/**
 * <p>Interface for listeners of the tracer plug-in.</p>
 * @see tudresden.ocl20.pivot.tracer.TracerPlugin
 * @author Lars Schütze
 */
public interface TracerRegistryListener {

	/**
	 * @param event The {@link TracerRegistryEvent} with the new trace.
	 */
	public void updateTrace(TracerRegistryEvent event);
}
