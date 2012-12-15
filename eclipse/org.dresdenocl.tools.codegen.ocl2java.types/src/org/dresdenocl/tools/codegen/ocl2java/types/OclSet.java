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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSets;

/**
 * <p>
 * The {@link OclSet} is the mathematical set. It contains elements without
 * duplicates.
 * </p>
 * 
 * @param <T>
 *            The type of the elements contained in this {@link OclSet}.
 * 
 * @author Claas Wilke
 * @deprecated Use {@link OclSets} instead.
 */
public class OclSet<T> extends OclCollection<T> implements Set<T> {

	/**
	 * <p>
	 * Creates a new {@link OclSet}.
	 * </p>
	 */
	public OclSet() {
		this.myCollection = new HashSet<T>();
	}

	/**
	 * <p>
	 * Creates a new {@link OclSet} from a given {@link Set}.
	 * </p>
	 */
	public OclSet(Set<T> aSet) {
		this.myCollection = new HashSet<T>(aSet);
	}

	/**
	 * @return The {@link OclBag} that contains all the elements from this
	 *         {@link OclSet}.
	 */
	public OclBag<T> asBag() {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclOrderedSet} that contains all the elements from this
	 *         {@link OclSet}, in undefined order.
	 */
	public OclOrderedSet<T> asOrderedSet() {
		OclOrderedSet<T> result;

		result = new OclOrderedSet<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return A {@link OclSequence} that contains all the elements from this
	 *         {@link OclSet}, in undefined order.
	 */
	public OclSequence<T> asSequence() {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return A {@link OclSet} identical to this {@link OclSet}. This operation
	 *         exists for convenience reasons.
	 */
	public OclSet<T> asSet() {
		OclSet<T> result;

		result = new OclSet<T>();

		result.addAll(this.myCollection);

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
			OclSet<T> aSet = (OclSet<T>) anObject;
			result = this.equals(aSet);
		}

		catch (ClassCastException e) {
			result = false;
		}

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclSet} this {@link OclSet} shall be compared with.
	 * @return True if this {@link OclSet} and the given {@link OclSet} contain
	 *         the same elements, the same number of times.
	 */
	public boolean equals(OclSet<T> aSet) {
		boolean result;

		result = (this.size() == aSet.size());

		if (result) {
			for (T anElement : aSet) {
				result &= this.contains(anElement);

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
	 * @return The {@link OclSet} containing all elements of this {@link OclSet}
	 *         except the given Object.
	 */
	public OclSet<T> excluding(T anObject) {

		OclSet<T> result;

		result = new OclSet<T>();

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
	 * If the element type of this {@link OclSet} is not a {@link Collection}
	 * type, this results in the same {@link OclSet}. If the element type is a
	 * {@link Collection} type, the result is the {@link OclSet} containing all
	 * the elements of all the elements of the contained {@link Collection}.
	 * </p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OclSet<?> flatten() {
		OclSet result;

		result = new OclSet();

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
	 *            The object which shall be included to this {@link OclSet}.
	 * @return The {@link OclSet} containing all elements of this {@link OclSet}
	 *         and the given Object.
	 */
	public OclSet<T> including(T anObject) {
		OclSet<T> result;

		result = new OclSet<T>();

		result.addAll(this.myCollection);
		result.add(anObject);

		return result;
	}

	/**
	 * @param collection
	 *            The {@link OclBag} this {@link OclSet} shall be intersected
	 *            with.
	 * @return The intersection of this {@link OclSet} and a given
	 *         {@link OclBag}.
	 */
	public OclSet<T> intersection(OclBag<T> aBag) {
		OclSet<T> result;

		result = new OclSet<T>();

		for (T anElement : this.myCollection) {

			/* Check if anElement occurs also to the given OclBag. */
			if (aBag.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param collection
	 *            The {@link OclSet} this {@link OclSet} shall be intersected
	 *            with.
	 * @return The intersection of this {@link OclSet} and a given
	 *         {@link OclSet}.
	 */
	public OclSet<T> intersection(OclSet<T> aSet) {
		OclSet<T> result;

		result = new OclSet<T>();

		for (T anElement : aSet) {

			/* Check if anElement occurs also to this OclSet. */
			if (this.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclSet} this {@link OclSet} shall be intersected
	 *            with.
	 * @return The elements of this {@link OclSet}, which are not in aSet.
	 */
	public OclSet<T> minus(OclSet<T> aSet) {
		OclSet<T> result;

		result = new OclSet<T>();

		for (T anElement : this.myCollection) {

			/* Check if anElement occurs also in aSet. */
			if (!aSet.contains(anElement)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclSet} used to compute the symmetric difference.
	 * @return The {@link OclSet} containing all the elements that are in this
	 *         {@link OclSet} or aSet, but not in both.
	 */
	public OclSet<T> symmetricDifference(OclSet<T> aSet) {
		OclSet<T> result;

		result = new OclSet<T>();

		/* Add all elements of this set which are not in aSet. */
		for (T anElement : this.myCollection) {
			if (aSet.excludes(anElement)) {
				result.add(anElement);
			}
		}

		/* Add all elements of aSet which are not in this set. */
		for (T anElement : aSet) {
			if (this.excludes(anElement)) {
				result.add(anElement);
			}
		}

		return result;
	}

	/**
	 * @param aBag
	 *            The {@link OclBag} this {@link OclSet} shall be united with.
	 * @return The union of this {@link OclSet} and the given {@link OclBag}.
	 */
	public OclSet<T> union(OclBag<T> aBag) {
		OclSet<T> result;

		result = new OclSet<T>();

		result.addAll(aBag);

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclSet} this {@link OclSet} shall be united with.
	 * @return The union of this {@link OclSet} and the given {@link OclSet}.
	 */
	public OclSet<T> union(OclSet<T> aSet) {
		OclSet<T> result;

		result = new OclSet<T>();

		result.addAll(aSet);

		return result;
	}

}
