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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclOrderedSets;

/**
 * <p>
 * The {@link OclOrderedSet} is a Set, the elements of which are ordered. It
 * contains no duplicates.
 * </p>
 * 
 * @param <T>
 *            The type of the elements contained in this {@link OclOrderedSet}.
 * 
 * @author Claas Wilke
 * @deprecated Use {@link OclOrderedSets} instead.
 */
public class OclOrderedSet<T> extends OclCollection<T> implements Set<T> {

	/**
	 * <p>
	 * Creates a new {@link OclOrderedSet}.
	 * </p>
	 */
	public OclOrderedSet() {
		this.myCollection = new ArrayList<T>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add(T anObject) {

		boolean result;

		result = false;

		/* Set does not contain duplicates. */
		if (this.excludes(anObject)) {
			result = this.myCollection.add(anObject);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends T> aCollection) {

		boolean result;

		result = false;

		for (T anElement : aCollection) {
			/* Set does not contain duplicates. */
			if (this.excludes(anElement)) {
				/* true if this collection changed as a result of the call. */
				result |= this.myCollection.add(anElement);
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
			OclOrderedSet<T> aBag = (OclOrderedSet<T>) anObject;
			result = this.equals(aBag);
		}

		catch (ClassCastException e) {
			result = false;
		}

		return result;
	}

	/**
	 * @param anObject
	 *            The Object which shall be appended.
	 * @return The {@link OclOrderedSet} of elements, consisting of all elements
	 *         of this {@link OclOrderedSet}, followed by the given object.
	 */
	public OclOrderedSet<T> append(T anObject) {
		OclOrderedSet<T> result;

		result = new OclOrderedSet<T>();

		result.addAll(this.myCollection);
		result.add(anObject);

		return result;
	}

	/**
	 * @return The {@link OclBag} that contains all the elements from this
	 *         {@link OclOrderedSet}.
	 */
	public OclBag<T> asBag() {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclOrderedSet} identical to this {@link OclOrderedSet}.
	 *         This operation exists for convenience reasons.
	 */
	public OclOrderedSet<T> asOrderedSet() {
		OclOrderedSet<T> result;

		result = new OclOrderedSet<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclSequence} that contains all the elements from this
	 *         {@link OclOrderedSet} in the same order.
	 */
	public OclSequence<T> asSequence() {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclSet} that contains all the elements from this
	 *         {@link OclOrderedSet} in undefined order.
	 */
	public OclSet<T> asSet() {
		OclSet<T> result;

		result = new OclSet<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @param aSet
	 *            The {@link OclOrderedSet} this {@link OclOrderedSet} shall be
	 *            compared with.
	 * @return True if this {@link OclOrderedSet} and the given
	 *         {@link OclOrderedSet} contain the same elements, the same number
	 *         of times.
	 */
	public boolean equals(OclOrderedSet<T> aSet) {
		boolean result;
		int index;

		result = (this.size() == aSet.size());

		if (result) {
			index = 1;
			for (T anElement : aSet) {
				result &= (anElement.equals(this.get(index)));

				if (!result) {
					break;
				}
				// no else.

				index++;
			}
		}
		// no else.

		return result;
	}

	/**
	 * @return The first element of this {@link OclOrderedSet}.
	 */
	public T first() {
		T result;

		result = null;

		if (this.size() > 0) {
			result = this.get(1);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * If the element type of this {@link OclOrderedSet} is not a
	 * {@link Collection} type, this results in the same {@link OclOrderedSet}.
	 * If the element type is a {@link Collection} type, the result is the
	 * {@link OclOrderedSet} containing all the elements of all the elements of
	 * the contained {@link Collection}.
	 * </p>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OclOrderedSet<?> flatten() {
		OclOrderedSet result;

		result = new OclOrderedSet();

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
	 * @param index
	 *            The index of the element which shall be returned.
	 *            <strong>Please pay attention that indexes in OCL starts with
	 *            1.</strong>
	 * @return The i-th element of this {@link OclOrderedSet}.
	 */
	public T get(int index) {
		T result;

		result = ((List<T>) this.myCollection).get(index - 1);

		return result;
	}

	/**
	 * @param anObject
	 *            The {@link Object} which index shall be returned.
	 * @return The index of object anObject in this {@link OclOrderedSet}.
	 *         <strong>Please pay attention that indexes in OCL starts with
	 *         1.</strong>
	 */
	public int indexOf(T anObject) {
		int result;

		result = ((List<T>) this.myCollection).indexOf(anObject) + 1;

		return result;
	}

	/**
	 * @param index
	 *            The index where anObject shall be inserted. <strong>Please pay
	 *            attention that indexes in OCL starts with 1.</strong>
	 * @param anObject
	 *            The {@link Object} which shall be inserted.
	 * @return The {@link OclOrderedSet} consisting of this
	 *         {@link OclOrderedSet} with anObject inserted at position index.
	 */
	public OclOrderedSet<T> insertAt(int index, T anObject) {
		OclOrderedSet<T> result;
		List<T> resultSet;

		result = new OclOrderedSet<T>();
		resultSet = new ArrayList<T>();

		resultSet.addAll(this.myCollection);

		/* Set does not contain duplicates. */
		if (!resultSet.contains(anObject)) {
			resultSet.add(index - 1, anObject);
		}
		// no else.

		result.addAll(resultSet);

		return result;
	}

	/**
	 * @return The last element of this {@link OclOrderedSet}.
	 */
	public T last() {
		T result;

		result = null;

		if (this.size() > 0) {
			result = this.get(this.size());
		}
		// no else.

		return result;
	}

	/**
	 * @param anObject
	 *            The Object which shall be prepended.
	 * @return The {@link OclOrderedSet} consisting of anObject, followed by all
	 *         elements of this {@link OclOrderedSet}.
	 */
	public OclOrderedSet<T> prepend(T anObject) {
		OclOrderedSet<T> result;

		result = new OclOrderedSet<T>();

		result.add(anObject);
		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclOrderedSet} containing the same elements but in
	 *         reverse order.
	 */
	public OclOrderedSet<T> reverse() {

		OclOrderedSet<T> result;
		result = new OclOrderedSet<T>();

		for (T element : this.myCollection) {
			result = result.insertAt(1, element);
		}
		// end for.

		return result;
	}

	/**
	 * @param lower
	 *            The index of the first element included in the result.
	 *            <strong>Please pay attention that indexes in OCL starts with
	 *            1.</strong>
	 * @param upper
	 *            The index of the last element included in the result.
	 *            <strong>Please pay attention that indexes in OCL starts with
	 *            1.</strong>
	 * @return The sub-set of this {@link OclOrderedSet} starting at number
	 *         lower, up to and including element number upper.
	 */
	public OclOrderedSet<T> subOrderedSet(int lower, int upper) {

		OclOrderedSet<T> result;

		/* Check the indexes. */
		if (lower < 1 || lower > this.size() || upper < 1
				|| upper > this.size() || lower > upper) {
			String msg;

			msg = "Wrong indexes for subOrderedSet. Lower was " + lower;
			msg += " and upper was " + upper;
			msg += " but the size of this OclOrderedSet was " + this.size();
			msg += ".";

			throw new IndexOutOfBoundsException(msg);
		}
		// no else.

		result = new OclOrderedSet<T>();

		/* Indexes in OCL starts with 1. */
		for (int index = lower - 1; index < upper; index++) {
			result.add(((List<T>) this.myCollection).get(index));
		}

		return result;
	}

}
