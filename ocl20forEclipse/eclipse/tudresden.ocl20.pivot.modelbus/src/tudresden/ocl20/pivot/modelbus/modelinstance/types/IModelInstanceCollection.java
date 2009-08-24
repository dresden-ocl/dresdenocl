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
import java.util.Iterator;

/**
 * <p>
 * Represents an {@link IModelInstanceElement} that contains other
 * {@link IModelInstanceElement}s. This interface can be used to adapt array or
 * collection like instances.
 * </p>
 * 
 * @param <T>
 *          The type of the elements of the adapted collection.
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceCollection<T> extends IModelInstanceElement {

	/**
	 * Returns the underlying {@link Collection}.
	 * 
	 * @return the underlying {@link Collection}
	 */
	Collection<T> getCollection();

	/**
	 * <p>
	 * Returns the adapter for the given object that is contained is the adapted
	 * list.
	 * </p>
	 * <p>
	 * This allows a <i>lazy adaptation</i>, since for some of the standard
	 * library collection operations it is not necessary to adapt the whole
	 * collection content.
	 * </p>
	 * 
	 * @param object
	 *          an object contained in the adapted collection
	 * @return the adapter for the given object
	 */
	IModelInstanceElement getAdapter(T object);

	/**
	 * Returns an {@link Iterator} for the underlying collection.
	 * 
	 * @return an {@link Iterator} for the underlying collection.
	 */
	Iterator<T> getIterator();

	/**
	 * <p>
	 * Checks whether or not this {@link IModelObject} is contains ordered
	 * elements.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelObject} is ordered.
	 */
	boolean isOrdered();

	/**
	 * <p>
	 * Checks whether or not this {@link IModelObject} contains unique elements.
	 * </p>
	 * 
	 * <p>
	 * Default value is <code>true</code>.
	 * </p>
	 * 
	 * @return Whether or not this {@link IModelObject} is unique.
	 */
	boolean isUnique();
}