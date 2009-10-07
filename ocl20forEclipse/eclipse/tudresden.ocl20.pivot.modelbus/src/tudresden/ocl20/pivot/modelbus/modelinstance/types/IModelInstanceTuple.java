/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;

/**
 * <p>
 * Represents an {@link IModelInstanceElement} that adapts a Tuple value from
 * the {@link IModelInstance}.
 * </p>
 * 
 * <p>
 * <strong>Tuple values cannot be part of the {@link IModelInstance} itself,
 * they can be only created by the standard library!</strong> Thus,
 * {@link IModelInstance}s should not implement this {@link Class}.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceTuple extends IModelInstanceElement {

	/**
	 * <p>
	 * Returns the value for a given key (as a {@link IModelInstanceString}).
	 * </p>
	 * 
	 * @param The
	 *          key, whose value shall be returned.
	 * @return The value of the given key.
	 * 
	 * @throws IllegalArgumentException
	 *           Thrown, if a given key is not contained in this tuple, or thew
	 *           given key is <code>null</code>.
	 */
	IModelInstanceElement get(IModelInstanceString key);
}