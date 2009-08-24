/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the EMF Ecore Model Instance Type of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance;

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

public class EcoreModelInstanceFactory extends AbstractModelInstanceFactory
		implements IModelInstanceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclBag(T[])
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclRoot> OclBag<T> createOclBag(T[] parts) {

		OclBag<T> result;
		List<T> partList;

		partList = Arrays.asList(parts);
		result = (OclBag<T>) Platform.getAdapterManager().getAdapter(partList,
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

		OclOrderedSet<T> result;
		List<T> partList;

		partList = new ArrayList<T>();

		for (int i = 0; i < parts.length; i++) {
			if (!partList.contains(parts[i])) {
				partList.add(parts[i]);
			}
		}

		result = (OclOrderedSet<T>) Platform.getAdapterManager().getAdapter(
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

		OclSequence<T> result;
		List<T> partList;

		partList = Arrays.asList(parts);

		result = (OclSequence<T>) Platform.getAdapterManager().getAdapter(
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

		OclSet<T> result;
		Set<T> partSet;

		partSet = new HashSet<T>(Arrays.asList(parts));

		result = (OclSet<T>) Platform.getAdapterManager().getAdapter(partSet,
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
		Map<String, OclRoot> parts;

		parts = new HashMap<String, OclRoot>();

		for (int i = 0; i < partNames.length; i++) {
			parts.put(partNames[i], partValues[i]);
		}

		result = (OclTuple) Platform.getAdapterManager().getAdapter(parts,
				OclTuple.class);

		return result;
	}
}