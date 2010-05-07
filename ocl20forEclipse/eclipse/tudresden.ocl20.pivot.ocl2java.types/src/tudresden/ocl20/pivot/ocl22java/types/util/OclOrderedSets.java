/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.ocl22java.types.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Represents utility class to provide operations of OCL Ordered Sets in Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclOrderedSets {

	/**
	 * <p>
	 * Returns the set of elements, consisting of all elements of self, followed
	 * by object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be appended.
	 * @return The set of elements, consisting of all elements of self, followed
	 *         by object.
	 */
	public static <T extends Object> List<T> append(List<T> self, T object) {

		List<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = new ArrayList<T>();
		}

		else {
			result = new ArrayList<T>(self);
		}
		// end else.

		if (!result.contains(object)) {
			result.add(object);
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the set of elements, consisting of all elements of self, followed
	 * by object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be appended.
	 * @return The set of elements, consisting of all elements of self, followed
	 *         by object.
	 */
	public static <T extends Object> List<T> append(T[] self, T object) {

		List<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = new ArrayList<T>();
		}

		else {
			result = new ArrayList<T>(Arrays.asList(self));
		}
		// end else.

		if (!result.contains(object)) {
			result.add(object);
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the Bag that contains all the elements from self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The Bag that contains all the elements from self.
	 */
	public static <T extends Object> List<T> asBag(List<T> self) {

		/* First ensure that List is an ordered set. */
		return OclCollections.asBag(OclCollections.asOrderedSet(self));
	}

	/**
	 * <p>
	 * Returns the Bag that contains all the elements from self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The Bag that contains all the elements from self.
	 */
	public static <T extends Object> List<T> asBag(T[] self) {

		/* First ensure that List is an ordered set. */
		return OclCollections.asBag(OclCollections.asOrderedSet(self));
	}

	/**
	 * <p>
	 * Returns an OrderedSet that contains all the elements from self, with
	 * duplicates removed, in an order dependent on the particular concrete
	 * collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return An OrderedSet that contains all the elements from self, with
	 *         duplicates removed, in an order dependent on the particular
	 *         concrete collection type.
	 */
	public static <T extends Object> List<T> asOrderedSet(List<T> self) {

		return OclCollections.asOrderedSet(self);
	}

	/**
	 * <p>
	 * Returns an OrderedSet that contains all the elements from self, with
	 * duplicates removed, in an order dependent on the particular concrete
	 * collection type.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return An OrderedSet that contains all the elements from self, with
	 *         duplicates removed, in an order dependent on the particular
	 *         concrete collection type.
	 */
	public static <T extends Object> List<T> asOrderedSet(T[] self) {

		return OclCollections.asOrderedSet(self);
	}

	/**
	 * <p>
	 * Returns the Sequence that contains all the elements from self, in an
	 * order dependent on the particular concrete collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The Sequence that contains all the elements from self, in an
	 *         order dependent on the particular concrete collection type.
	 */
	public static <T extends Object> List<T> asSequence(List<T> self) {

		/* First ensure that List is an ordered set. */
		return OclCollections.asSequence(OclCollections.asOrderedSet(self));
	}

	/**
	 * <p>
	 * Returns the Sequence that contains all the elements from self, in an
	 * order dependent on the particular concrete collection type.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The Sequence that contains all the elements from self, in an
	 *         order dependent on the particular concrete collection type.
	 */
	public static <T extends Object> List<T> asSequence(T[] self) {

		/* First ensure that List is an ordered set. */
		return OclCollections.asSequence(OclCollections.asOrderedSet(self));
	}

	/**
	 * <p>
	 * Returns the Set containing all the elements from self, with duplicates
	 * removed.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The Set containing all the elements from self, with duplicates
	 *         removed.
	 */
	public static <T extends Object> Set<T> asSet(List<T> self) {

		return OclCollections.asSet(self);
	}

	/**
	 * <p>
	 * Returns the Set containing all the elements from self, with duplicates
	 * removed.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The Set containing all the elements from self, with duplicates
	 *         removed.
	 */
	public static <T extends Object> Set<T> asSet(T[] self) {

		return OclCollections.asSet(self);
	}

	/**
	 * <p>
	 * Returns the i-th element of self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param i
	 *            The index of the object to return (<strong>Indexes in OCL
	 *            start with 1!</strong>).
	 * @return The i-th element of self.
	 */
	public static <T extends Object> T at(List<T> self, Integer i) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			throw new InvalidException(
					"Cannot invoke at() on empty OrderedSets.");
		}
		// no else.

		if (i < 1 || i > self.size()) {
			throw new InvalidException("Index must lay between 1 and "
					+ self.size() + " but was " + i + ".");
		}
		// no else.

		return self.get(i - 1);
	}

	/**
	 * <p>
	 * Returns the i-th element of self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param i
	 *            The index of the object to return (<strong>Indexes in OCL
	 *            start with 1!</strong>).
	 * @return The i-th element of self.
	 */
	public static <T extends Object> T at(T[] self, Integer i) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return at(new ArrayList<T>(), i);
		}

		else {
			return at(new ArrayList<T>(Arrays.asList(self)), i);
		}
		// end else.
	}

	/**
	 * <p>
	 * Returns the first element in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The first element in self.
	 */
	public static <T extends Object> T first(List<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			throw new InvalidException(
					"Cannot invoke operation first() on an empty OrderedSet.");
		}
		// no else.

		/* First ensure that self is an ordered set. */
		return OclCollections.asOrderedSet(self).get(0);
	}

	/**
	 * <p>
	 * Returns the first element in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The first element in self.
	 */
	public static <T extends Object> T first(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.length == 0) {
			throw new InvalidException(
					"Cannot invoke operation first() on an empty OrderedSet.");
		}
		// no else.

		/* First ensure that self is an ordered set. */
		return OclCollections.asOrderedSet(self).get(0);
	}

	/**
	 * <p>
	 * Returns the index of object in the OrderedSet (<strong>Indexes in OCL
	 * start with 1!</strong>).
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be indexed.
	 * @return The index of object in the OrderedSet.
	 */
	public static <T extends Object> Integer indexOf(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0 || !self.contains(object)) {
			throw new InvalidException(
					"The given object is not contained in self.");
		}
		// no else.

		/* First ensure that self is an ordered set. */
		return OclCollections.asOrderedSet(self).indexOf(object) + 1;
	}

	/**
	 * <p>
	 * Returns the index of object in the OrderedSet (<strong>Indexes in OCL
	 * start with 1!</strong>).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be indexed.
	 * @return The index of object in the OrderedSet.
	 */
	public static <T extends Object> Integer indexOf(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return indexOf(new ArrayList<T>(), object);
		}

		else {
			return indexOf(Arrays.asList(self), object);
		}
		// end else.
	}

	/**
	 * <p>
	 * Returns the set consisting of self with object inserted at position
	 * index.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param index
	 *            The index where to add the object (<strong>Indexes in OCL
	 *            start with 1!</strong>).
	 * @param object
	 *            The object to be inserted.
	 * @return The set consisting of self with object inserted at position
	 *         index.
	 */
	public static <T extends Object> List<T> insertAt(List<T> self,
			Integer index, T object) {

		List<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = new ArrayList<T>();
		}

		else {
			result = new ArrayList<T>(self);
		}
		// end else.

		if (index < 1 || index > result.size() + 1) {
			throw new InvalidException("Index must lay between 1 and "
					+ (result.size() + 1) + " but was " + index + ".");
		}
		// no else.

		/* First ensure that self is an ordered set. */
		result = OclCollections.asOrderedSet(result);

		if (!result.contains(object)) {
			result.add(index - 1, object);
		}

		return result;
	}

	/**
	 * <p>
	 * Returns the set consisting of self with object inserted at position
	 * index.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param index
	 *            The index where to add the object (<strong>Indexes in OCL
	 *            start with 1!</strong>).
	 * @param object
	 *            The object to be inserted.
	 * @return The set consisting of self with object inserted at position
	 *         index.
	 */
	public static <T extends Object> List<T> insertAt(T[] self, Integer index,
			T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return insertAt(new ArrayList<T>(), index, object);
		}

		else {
			return insertAt(new ArrayList<T>(Arrays.asList(self)), index,
					object);
		}
		// end else.
	}

	/**
	 * <p>
	 * Returns the last element in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The last element in self.
	 */
	public static <T extends Object> T last(List<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			throw new InvalidException(
					"Cannot invoke operation first() on an empty OrderedSet.");
		}
		// no else.

		/* First ensure that self is an ordered set. */
		List<T> resultList = OclCollections.asOrderedSet(self);

		return resultList.get(resultList.size() - 1);
	}

	/**
	 * <p>
	 * Returns the last element in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The last element in self.
	 */
	public static <T extends Object> T last(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.length == 0) {
			throw new InvalidException(
					"Cannot invoke operation last() on an empty OrderedSet.");
		}
		// no else.

		return self[self.length - 1];
	}

	/**
	 * <p>
	 * Returns the ordered set consisting of object, followed by all elements in
	 * self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be prepended.
	 * @return The ordered set consisting of object, followed by all elements in
	 *         self.
	 */
	public static <T extends Object> List<T> prepend(List<T> self, T object) {

		List<T> result;
		result = new ArrayList<T>();
		result.add(object);

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}

		else {
			for (T element : self) {
				if (!result.contains(element)) {
					result.add(element);
				}
				// no else.
			}
			return result;
		}
	}

	/**
	 * <p>
	 * Returns the ordered set consisting of object, followed by all elements in
	 * self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be prepended.
	 * @return The ordered set consisting of object, followed by all elements in
	 *         self.
	 */
	public static <T extends Object> List<T> prepend(T[] self, T object) {

		List<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = prepend(new ArrayList<T>(), object);
		}

		else {
			result = prepend(new ArrayList<T>(Arrays.asList(self)), object);
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Returns the ordered set of elements with same elements but with the
	 * opposite order.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The ordered set of elements with same elements but with the
	 *         opposite order.
	 */
	public static <T extends Object> List<T> reverse(List<T> self) {

		List<T> result;
		result = new ArrayList<T>();

		/* OCL Collections cannot be null. */
		if (self != null) {

			for (T element : self) {

				if (!result.contains(element)) {
					result.add(0, element);
				}
				// no else.
			}
			// end for.
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the ordered set of elements with same elements but with the
	 * opposite order.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The ordered set of elements with same elements but with the
	 *         opposite order.
	 */
	public static <T extends Object> List<T> reverse(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}

		else {
			return reverse(Arrays.asList(self));
		}
	}

	/**
	 * <p>
	 * Returns the sub-set of self starting at number lower, up to and including
	 * element number upper.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param lower
	 *            The lower index (<strong>Indexes in OCL start with
	 *            1!</strong>).
	 * @param lower
	 *            The upper index (<strong>Indexes in OCL start with
	 *            1!</strong>).
	 * @return The sub-set of self starting at number lower, up to and including
	 *         element number upper.
	 */
	public static <T extends Object> List<T> subOrderedSet(List<T> self,
			Integer lower, Integer upper) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			throw new InvalidException(
					"Cannot invoke subOrderedSet() on empty OrderedSets.");
		}

		else {
			self = new ArrayList<T>(self);
		}
		// end else.

		/* First ensure that self is an ordered set. */
		List<T> result;
		result = OclCollections.asOrderedSet(self);

		if (lower < 1 || lower > result.size()) {
			throw new InvalidException("Index lower must lay between 1 and "
					+ result.size() + " but was " + lower + ".");
		}
		// no else.

		if (upper < 1 || upper > result.size()) {
			throw new InvalidException("Index upper must lay between 1 and "
					+ result.size() + " but was " + upper + ".");
		}
		// no else.

		if (lower > upper) {
			throw new InvalidException(
					"Index lower must be equal or less than upper.");
		}
		// no else.

		result = result.subList(lower - 1, upper);

		return result;
	}

	/**
	 * <p>
	 * Returns the sub-set of self starting at number lower, up to and including
	 * element number upper.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param lower
	 *            The lower index (<strong>Indexes in OCL start with
	 *            1!</strong>).
	 * @param lower
	 *            The upper index (<strong>Indexes in OCL start with
	 *            1!</strong>).
	 * @return The sub-set of self starting at number lower, up to and including
	 *         element number upper.
	 */
	public static <T extends Object> List<T> subOrderedSet(T[] self,
			Integer lower, Integer upper) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return subOrderedSet(new ArrayList<T>(), lower, upper);
		}

		else {
			return subOrderedSet(new ArrayList<T>(Arrays.asList(self)), lower,
					upper);
		}
		// end else.
	}
}