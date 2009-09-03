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

import org.eclipse.emf.common.util.UniqueEList;

/**
 * <p>
 * A wrapper for an ordered set.
 * </p>
 * 
 * @author Michael Thiele
 * 
 */
public interface IModelInstanceOrderedSet<T> extends IModelInstanceCollection<T> {

	/**
	 * <p>
	 * Returns the underlying {@link UniqueEList Ecore UniqueEList}.
	 * </p>
	 * 
	 * @return the underlying {@link UniqueEList Ecore UniqueEList}.
	 */
	UniqueEList<Object> getUniqueEList();
}