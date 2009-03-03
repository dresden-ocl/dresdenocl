/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.models.uml2.internal.modelinstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceFactory;

/**
 * <p>
 * An ModelInstanceFactory to provide methods to create an
 * {@link Uml2ModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Uml2ModelInstanceFactory extends AbstractModelInstanceFactory
		implements IModelInstanceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclBag(T[])
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclRoot> OclBag<T> createOclBag(T[] parts) {
		List<T> partList;
		OclBag<T> result;

		partList = Arrays.asList(parts);

		result = (OclBag) Platform.getAdapterManager().getAdapter(partList,
				OclBag.class);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclOrderedSet
	 * (T[])
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclRoot> OclOrderedSet<T> createOclOrderedSet(T[] parts) {

		List<T> partList;
		OclOrderedSet<T> result;

		partList = new ArrayList<T>();

		for (int i = 0; i < parts.length; i++) {
			if (!partList.contains(parts[i])) {
				partList.add(parts[i]);
			}
			// no else.
		}

		result = (OclOrderedSet) Platform.getAdapterManager().getAdapter(
				partList, OclOrderedSet.class);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclSequence
	 * (T[])
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclRoot> OclSequence<T> createOclSequence(T[] parts) {

		List<T> partList;
		OclSequence<T> result;

		partList = Arrays.asList(parts);

		result = (OclSequence) Platform.getAdapterManager().getAdapter(
				partList, OclSequence.class);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclSet(T[])
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclRoot> OclSet<T> createOclSet(T[] parts) {

		Set<T> partList;
		OclSet<T> result;

		partList = new HashSet<T>(Arrays.asList(parts));

		result = (OclSet) Platform.getAdapterManager().getAdapter(partList,
				OclSet.class);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclTuple(java
	 * .lang.String[],
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	public OclTuple createOclTuple(String[] partNames, OclRoot[] partValues) {

		OclTuple result;

		Map<String, OclRoot> partMap;

		partMap = new HashMap<String, OclRoot>();

		/* Check if the names and the values have the same length. */
		if (partNames.length == partValues.length) {

			int index;

			index = 0;

			/* Add all parts to the map. */
			while (index < partNames.length) {

				partMap.put(partNames[index], partValues[index]);

				index++;
			}
		}
		// no else.

		result = (OclTuple) Platform.getAdapterManager().getAdapter(partMap,
				OclTuple.class);

		return result;
	}
}