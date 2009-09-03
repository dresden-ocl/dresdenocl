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
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * A factory for {@link IModelInstanceElement}s.
 * </p>
 * 
 * @author Michael Thiele
 */
public interface IModelInstanceFactory {

	/**
	 * <p>
	 * Creates a new adapter for the given object. Uses a {@link WeakHashMap} to
	 * cache adaptions to {@link IModelInstanceObject}s, but not to
	 * {@link IModelInstancePrimitiveType}s and {@link IModelInstanceCollection}s
	 * (since copies of those elements can be created with the same contents).
	 * </p>
	 * 
	 * @param adapted
	 *          the object to adapt
	 * @return the adapter for the given object
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to any
	 *           {@link Type} of the current {@link IModel}.
	 */
	IModelInstanceElement createModelInstanceElement(Object adapted)
			throws TypeNotFoundInModelException;

	/**
	 * <p>
	 * Creates an {@link IModelInstanceCollection} for a given {@link Collection}
	 * and a given {@link OclCollectionTypeKind}, the created
	 * {@link IModelInstanceCollection} shall belong to.
	 * </p>
	 * 
	 * @param collection
	 *          The {@link Collection} for that a {@link IModelInstanceCollection}
	 *          shall be created.
	 * @param kind
	 *          The {@link OclCollectionTypeKind}, the created
	 *          {@link IModelInstanceCollection} shall belong to.
	 * 
	 * @return The created {@link IModelInstanceCollection}.
	 */
	<T> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> collection, OclCollectionTypeKind kind);
}