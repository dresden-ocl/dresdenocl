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

import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * Represents an {@link IModelInstanceElement} that contains other
 * {@link IModelInstanceElement}s. This interface can be used to adapt arrays or
 * {@link Collections}.
 * </p>
 * 
 * @param <T>
 *          The type of the elements of the adapted collection.
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceCollection<T extends IModelInstanceElement>
		extends IModelInstanceElement {

	/**
	 * <p>
	 * Returns the underlying {@link Collection} of {@link IModelInstanceElement}
	 * s.
	 * <p>
	 * 
	 * @return The underlying {@link Collection} of {@link IModelInstanceElement}
	 *         s.
	 */
	Collection<T> getCollection();

	/**
	 * <p>
	 * Checks whether or not this {@link IModelInstanceCollection} is contains
	 * ordered elements.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelInstanceCollection} is ordered.
	 */
	boolean isOrdered();

	/**
	 * <p>
	 * Checks whether or not this {@link IModelInstanceCollection} contains unique
	 * elements.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelInstanceCollection} is unique.
	 */
	boolean isUnique();
}