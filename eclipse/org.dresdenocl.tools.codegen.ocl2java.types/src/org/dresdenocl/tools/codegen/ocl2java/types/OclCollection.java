/*
Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dresdenocl.tools.codegen.ocl2java.types.util.OclCollections;

/**
 * <p>
 * Represents OCL Collections in Java.
 * </p>
 * 
 * @param <T>
 *            The type of the elements contained in this {@link OclBag}.
 * 
 * @author Claas Wilke
 * @deprecated Use {@link OclCollections} instead.
 */
public abstract class OclCollection<T> implements Collection<T> {

	protected Collection<T> myCollection;

	/**
	 * <p>
	 * Returns how often a given {@link Object} occurs in this
	 * {@link IOclCollection}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} which shall be counted.
	 * @return How often a given {@link Object} occurs in this
	 *         {@link IOclCollection}
	 */
	public int count(T anObject) {
		int result;

		result = 0;

		for (T anElem : this.myCollection) {
			if (anElem.equals(anObject)) {
				result++;
			}
			// no else.
		}

		return result;
	}

	/**
	 * <p>
	 * Returns true, if a given {@link Object} does not occur in this
	 * {@link IOclCollection}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} which shall be searched for.
	 * @return True, if a given {@link Object} does not occur in this
	 *         {@link Collection}.
	 */
	public boolean excludes(Object anObject) {
		return !this.contains(anObject);
	}

	/**
	 * <p>
	 * Returns true, if all elements of a given {@link Collection} do not occur
	 * in this {@link Collection}.
	 * </p>
	 * 
	 * @param aCollection
	 *            The {@link IOclCollection} which shall be searched for.
	 * @return True, if all elements of a given {@link IOclCollection} do not
	 *         occur in this {@link Collection}.
	 */
	public boolean excludesAll(Collection<?> aCollection) {

		boolean result;

		result = true;

		if (!this.isEmpty()) {
			for (Object anElement : aCollection) {
				result &= !this.contains(anElement);
			}
		}
		// no else.

		return result;
	}

	/**
	 * @return True, if the {@link Collection} is not empty.
	 */
	public boolean notEmpty() {
		return !this.isEmpty();
	}

	/**
	 * @param aCollection
	 *            The {@link IOclCollection} to compute the cartesian product.
	 * @return The cartesian product of this and a given {@link IOclCollection}.
	 */
	public OclSet<Map<String, Object>> product(Collection<?> aCollection) {

		OclSet<Map<String, Object>> result;

		result = new OclSet<Map<String, Object>>();

		for (T element1 : this.myCollection) {

			for (Object element2 : aCollection) {
				Map<String, Object> aTuple;

				aTuple = new HashMap<String, Object>();

				aTuple.put("first", element1);
				aTuple.put("second", element2);

				result.add(aTuple);
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Object anObject) {
		return this.myCollection.contains(anObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> aCollection) {
		return this.myCollection.containsAll(aCollection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.ocl2java.types.IOclCollection#size()
	 */
	public int size() {
		return this.myCollection.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T anObject) {
		return this.myCollection.add(anObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends T> aCollection) {
		return this.myCollection.addAll(aCollection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear() {
		this.myCollection.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty() {
		return this.myCollection.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	public Iterator<T> iterator() {
		return this.myCollection.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Object arg0) {
		return this.myCollection.remove(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> arg0) {
		return this.myCollection.removeAll(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> arg0) {
		return this.myCollection.retainAll(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray() {
		return this.myCollection.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(T[])
	 */
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return this.myCollection.toArray(arg0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.myCollection.toString();
	}
}
