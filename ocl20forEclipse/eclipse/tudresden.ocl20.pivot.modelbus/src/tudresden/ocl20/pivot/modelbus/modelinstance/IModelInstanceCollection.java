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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import java.util.Collection;

import tudresden.ocl20.pivot.modelbus.IModelObject;

/**
 * <p>
 * Represents an {@link IModelObject} that contains other {@link IModelObject}s.
 * This interface can be used to adapt array or collection like instance
 * {@link Object}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceCollection extends IModelObject {

	/**
	 * <p>
	 * Checks whether or not this {@link IModelObject} is contains sorted
	 * {@link Object}s.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelObject} is ordered.
	 */
	boolean isSorted();

	/**
	 * <p>
	 * Checks whether or not this {@link IModelObject} contains unique
	 * {@link Object}s.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelObject} is unique.
	 */
	boolean isUnique();

	/**
	 * <p>
	 * Returns all {@link IModelObject} contained in this
	 * {@link IModelInstanceCollection}.
	 * </p>
	 * 
	 * @return All {@link IModelObject} contained in this
	 *         {@link IModelInstanceCollection}.
	 */
	Collection<IModelObject> getContainedElements();
}