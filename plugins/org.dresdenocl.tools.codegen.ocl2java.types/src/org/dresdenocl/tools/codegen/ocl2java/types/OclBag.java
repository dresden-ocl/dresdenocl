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

import java.util.ArrayList;
import java.util.Collection;

import org.dresdenocl.tools.codegen.ocl2java.types.util.OclBags;

/**
 * <p>
 * An {@link OclBag} is a collection with duplicates allowed. That is, one
 * object can be an element of a {@link OclBag} many times. There is no ordering
 * defined on the elements in an {@link OclBag}.
 * </p>
 * 
 * @param <T>
 *            The type of the elements contained in this {@link OclBag}.
 * 
 * @author Claas Wilke
 * @deprecated Use {@link OclBags} instead.
 */
public class OclBag<T> extends OclCollection<T> {

	/**
	 * <p>
	 * Creates a new {@link OclBag}.
	 * </p>
	 */
	public OclBag() {
		this.myCollection = new ArrayList<T>();
	}

	/**
	 * @return An {@link OclBag} identical to this {@link OclBag}. This
	 *         operation exists for convenience reasons.
	 */
	public OclBag<T> asBag() {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return The {@link OclOrderedSet} containing all the elements from this
	 *         {@link OclBag}, in undefined order, with duplicates removed.
	 */
	public OclOrderedSet<T> asOrderedSet() {
		OclOrderedSet<T> result;

		result = new OclOrderedSet<T>();

		for (T anElement : this.myCollection) {

			/* Check if the element has already been added. */
			if (!result.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @return A {@link OclSequence} that contains all the elements from this
	 *         {@link OclBag}, in undefined order.
	 */
	public OclSequence<T> asSequence() {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return The {@link OclSet} containing all the elements from this
	 *         {@link OclBag}, with duplicates removed.
	 */
	public OclSet<T> asSet() {
		OclSet<T> result;

		result = new OclSet<T>();

		for (T anElement : this.myCollection) {

			/* Check if the element has already been added. */
			if (!result.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object anObject) {
		boolean result;

		try {
			OclBag<T> aBag = (OclBag<T>) anObject;
			result = this.equals(aBag);
		}

		catch (ClassCastException e) {
			result = false;
		}

		return result;
	}

	/**
	 * @param aBag
	 *            The {@link OclBag} this {@link OclBag} shall be compared with.
	 * @return True if this {@link OclBag} and the given {@link OclBag} contain
	 *         the same elements, the same number of times.
	 */
	public boolean equals(OclBag<T> aBag) {
		boolean result;

		result = (this.size() == aBag.size());

		if (result) {
			for (T anElement : aBag) {
				result &= (this.count(anElement) == aBag.count(anElement));

				if (!result) {
					break;
				}
				// no else.
			}
		}
		// no else.

		return result;
	}

	/**
	 * @param anObject
	 *            The object which shall not be part of the result.
	 * @return The {@link OclBag} containing all elements of this {@link OclBag}
	 *         except the given Object.
	 */
	public OclBag<T> excluding(T anObject) {

		OclBag<T> result;

		result = new OclBag<T>();

		for (T anElement : this.myCollection) {

			if (!anElement.equals(anObject)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * <p>
	 * If the element type of this {@link OclBag} is not a {@link Collection}
	 * type, this results in the same {@link OclBag}. If the element type is a
	 * {@link Collection} type, the result is the {@link OclBag} containing all
	 * the elements of all the elements of the contained {@link Collection}.
	 * </p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OclBag<?> flatten() {
		OclBag result;

		result = new OclBag();

		for (T anElement : this.myCollection) {

			/* The element is a collection, add all its elements. */
			if (anElement instanceof Collection) {
				Collection aCollection;

				aCollection = (Collection) anElement;

				result.addAll(aCollection);
			}

			/* Else add the element itself. */
			else {
				result.add(anElement);
			}
		}

		return result;
	}

	/**
	 * @param anObject
	 *            The object which shall be included to this {@link OclBag}.
	 * @return The {@link OclBag} containing all elements of this {@link OclBag}
	 *         and the given Object.
	 */
	public OclBag<T> including(T anObject) {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);
		result.add(anObject);

		return result;
	}

	/**
	 * @param collection
	 *            The {@link OclBag} this {@link OclBag} shall be intersected
	 *            with.
	 * @return The intersection of this {@link OclBag} and a given
	 *         {@link OclBag}.
	 */
	public OclBag<T> intersection(OclBag<T> aBag) {
		OclBag<T> result;

		result = new OclBag<T>();

		for (T anElement : aBag) {

			/* Check if anElement was already added to the result. */
			if (result.excludes(anElement)) {
				int minOccurrence;

				/* Get the minimum occurrence of anElement in both collections. */
				minOccurrence = Math.min(aBag.count(anElement), this
						.count(anElement));

				/* Add anElement to the result minOccurence times. */
				for (int i = 0; i < minOccurrence; i++) {
					result.add(anElement);
				}
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param collection
	 *            The {@link OclBag} this {@link OclBag} shall be intersected
	 *            with.
	 * @return The intersection of this {@link OclBag} and a given
	 *         {@link OclBag}.
	 */
	public OclBag<T> intersection(OclSet<T> aSet) {
		OclBag<T> result;

		result = new OclBag<T>();

		for (T anElement : aSet) {

			/* Check if anElement occurs also to this OclBag. */
			if (this.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param aBag
	 *            The {@link OclBag} this {@link OclBag} shall be united with.
	 * @return The union of this {@link OclBag} and the given {@link OclBag}.
	 */
	public OclBag<T> union(OclBag<T> aBag) {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);
		result.addAll(aBag);

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclSet} this {@link OclBag} shall be united with.
	 * @return The union of this {@link OclBag} and the given {@link OclSet}.
	 */
	public OclBag<T> union(OclSet<T> aSet) {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);
		result.addAll(aSet);

		return result;
	}
}
