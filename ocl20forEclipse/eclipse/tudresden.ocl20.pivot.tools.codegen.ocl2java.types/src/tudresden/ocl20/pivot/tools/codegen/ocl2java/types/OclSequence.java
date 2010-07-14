/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * An {@link OclSequence} is a collection where the elements are ordered. An
 * element may be part of an {@link OclSequence} more than once.
 * </p>
 * 
 * @param <T>
 *            The type of the elements contained in this {@link OclSequence}.
 * 
 * @author Claas Wilke
 */
public class OclSequence<T> extends OclCollection<T> {

	/**
	 * <p>
	 * Creates a new {@link OclSet}.
	 * </p>
	 */
	public OclSequence() {
		this.myCollection = new ArrayList<T>();
	}

	/**
	 * @param anObject
	 *            The Object which shall be appended.
	 * @return The {@link OclSequence} of elements, consisting of all elements
	 *         of this {@link OclSequence}, followed by the given object.
	 */
	public OclSequence<T> append(T anObject) {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);
		result.add(anObject);

		return result;
	}

	/**
	 * @return An {@link OclBag} containing all the elements from this
	 *         {@link OclSequence}, in undefined order.
	 */
	public OclBag<T> asBag() {
		OclBag<T> result;

		result = new OclBag<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return The {@link OclOrderedSet} containing all the elements from this
	 *         {@link OclSequence}, in the same order, with duplicates removed.
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
	 * @return A {@link OclSequence} identical to this {@link OclSequence}. This
	 *         operation exists for convenience reasons.
	 */
	public OclSequence<T> asSequence() {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return The {@link OclSet} containing all the elements from this
	 *         {@link OclSequence}, with duplicates removed.
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
			OclSequence<T> aSequence = (OclSequence<T>) anObject;
			result = this.equals(aSequence);
		}

		catch (ClassCastException e) {
			result = false;
		}

		return result;
	}

	/**
	 * @param aSequence
	 *            The {@link OclSequence} this {@link OclSequence} shall be
	 *            compared with.
	 * @return True if this {@link OclSequence} and the given
	 *         {@link OclSequence} contain the same elements, the same number of
	 *         times.
	 */
	public boolean equals(OclSequence<T> aSequence) {
		boolean result;
		int index;

		result = (this.size() == aSequence.size());

		if (result) {

			index = 1;

			for (T anElement : aSequence) {
				result &= this.get(index).equals(anElement);

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
	 * @param anObject
	 *            The object which shall not be part of the result.
	 * @return The {@link OclSequence} containing all elements of this
	 *         {@link OclSequence} except all occurrences of the given Object.
	 */
	public OclSequence<T> excluding(T anObject) {

		OclSequence<T> result;

		result = new OclSequence<T>();

		for (T anElement : this.myCollection) {

			if (!anElement.equals(anObject)) {
				result.add(anElement);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @return The first element of this {@link OclSequence}.
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
	 * If the element type of this {@link OclSequence} is not a
	 * {@link Collection} type, this results in the same {@link OclSequence}. If
	 * the element type is a {@link Collection} type, the result is the
	 * {@link OclSequence} containing all the elements of all the elements of
	 * the contained {@link Collection}.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public OclSequence<?> flatten() {
		OclSequence result;

		result = new OclSequence();

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
	 * @return The i-th element of this {@link OclSequence}.
	 */
	public T get(int index) {
		T result;

		result = ((List<T>) this.myCollection).get(index - 1);

		return result;
	}

	/**
	 * @param anObject
	 *            The object which shall be included to this {@link OclBag}.
	 * @return The {@link OclSequence} containing all elements of this
	 *         {@link OclSequence} plus anObject added as the last element.
	 */
	public OclSequence<T> including(T anObject) {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);
		result.add(anObject);

		return result;
	}

	/**
	 * @param anObject
	 *            The {@link Object} which index shall be returned.
	 * @return The index of object anObject in this {@link OclSequence}.
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
	 * @return The {@link OclSequence} consisting of this {@link OclSequence}
	 *         with anObject inserted at position index.
	 */
	public OclSequence<T> insertAt(int index, T anObject) {
		OclSequence<T> result;
		List<T> resultSequence;

		result = new OclSequence<T>();
		resultSequence = new ArrayList<T>();

		resultSequence.addAll(this.myCollection);

		/* Set does not contain duplicates. */
		if (!resultSequence.contains(anObject)) {
			resultSequence.add(index - 1, anObject);
		}
		// no else.

		result.addAll(resultSequence);

		return result;
	}

	/**
	 * @return The last element of this {@link OclSequence}.
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
	 * @return The {@link OclSequence} consisting of anObject, followed by all
	 *         elements of this {@link OclSequence}.
	 */
	public OclSequence<T> prepend(T anObject) {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.add(anObject);
		result.addAll(this.myCollection);

		return result;
	}

	/**
	 * @return An {@link OclSequence} containing the same elements but in
	 *         reverse order.
	 */
	public OclSequence<T> reverse() {

		OclSequence<T> result;
		result = new OclSequence<T>();

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
	 * @return The sub-set of this {@link OclSequence} starting at number lower,
	 *         up to and including element number upper.
	 */
	public OclSequence<T> subSequence(int lower, int upper) {

		OclSequence<T> result;

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

		result = new OclSequence<T>();

		/* Indexes in OCL starts with 1. */
		for (int index = lower - 1; index < upper; index++) {
			result.add(((List<T>) this.myCollection).get(index));
		}

		return result;
	}

	/**
	 * @param aSequence
	 *            The {@link OclSequence} this {@link OclSequence} shall be
	 *            united with.
	 * @return The {@link OclSequence} consisting of all elements of this
	 *         {@link OclSequence}, followed by all elements in aSequence.
	 */
	public OclSequence<T> union(OclSequence<T> aSequence) {
		OclSequence<T> result;

		result = new OclSequence<T>();

		result.addAll(this.myCollection);
		result.addAll(aSequence);

		return result;
	}

}
