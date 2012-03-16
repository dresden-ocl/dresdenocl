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

import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

/**
 * <p>
 * This interface provides the utility to register as listener to the tracer
 * plug-in. The listeners will be informed when the trace changes that they can
 * update their UI.
 * </p>
 * 
 * @author Lars Schuetze
 * 
 */
public interface TracerRegistry {

	public void addTracerRegistryListener(TracerRegistryListener listener);

	public void removeTracerRegistryListener(TracerRegistryListener listener);

	/** Indicates that the trace has been updated. */
	public void fireUpdateTrace(TracerRoot trace);
}
