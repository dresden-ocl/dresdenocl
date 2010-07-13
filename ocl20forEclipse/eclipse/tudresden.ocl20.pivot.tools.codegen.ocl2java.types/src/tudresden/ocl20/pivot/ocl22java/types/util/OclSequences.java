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
 * Represents utility class to provide operations of OCL Sequences in Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclSequences {

	/**
	 * <p>
	 * Returns the sequence of elements, consisting of all elements of self,
	 * followed by object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be appended.
	 * @return The sequence of elements, consisting of all elements of self,
	 *         followed by object.
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

		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the sequence of elements, consisting of all elements of self,
	 * followed by object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be appended.
	 * @return The sequence of elements, consisting of all elements of self,
	 *         followed by object.
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

		result.add(object);

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

		return OclCollections.asBag(self);
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

		if (self == null) {
			return OclCollections.asBag(new ArrayList<T>());
		}
		// no else.

		return OclCollections.asBag(Arrays.asList(self));
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

		if (self == null) {
			return OclCollections.asOrderedSet(new ArrayList<T>());
		}
		// no else.

		return OclCollections.asOrderedSet(Arrays.asList(self));
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

		return OclCollections.asSequence(self);
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

		if (self == null) {
			return OclCollections.asSequence(new ArrayList<T>());
		}
		// no else.

		return OclCollections.asSequence(Arrays.asList(self));
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

		if (self == null) {
			return OclCollections.asSet(new ArrayList<T>());
		}
		// no else.

		return OclCollections.asSet(Arrays.asList(self));
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
			throw new InvalidException("Cannot invoke at() on empty Sequences.");
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
	 * Returns the number of occurrences of object in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Lost} representing self.
	 * @param object
	 *            The object to be counted.
	 * @return The number of occurrences of object in self.
	 */
	public static <T extends Object> Integer count(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new Integer(0);
		}
		// no else.

		Integer result = 0;

		for (T element : self) {
			if (element.equals(object)) {
				result++;
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the number of occurrences of object in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be counted.
	 * @return The number of occurrences of object in self.
	 */
	public static <T extends Object> Integer count(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new Integer(0);
		}
		// no else.

		return count(Arrays.asList(self), object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} to be checked.
	 * @param c2
	 *            The {@link List} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			List<T2> self, List<T1> c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T1>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T2>();
		}
		// no else.

		return self.equals(c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The {@link List} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			T2[] self, List<T1> c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T1>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return equals(new ArrayList<T2>(), c2);
		}
		// no else.

		return equals(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			List<T2> self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return equals(self, new ArrayList<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T2>();
		}
		// no else.

		return equals(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			T2[] self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return equals(self, new ArrayList<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return equals(new ArrayList<T1>(), c2);
		}
		// no else.

		return equals(Arrays.asList(self), Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the sequence containing all elements of self apart from all
	 * occurrences of object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The sequence containing all elements of self apart from all
	 *         occurrences of object.
	 */
	public static <T extends Object> List<T> excluding(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new InvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>(self);

		while (result.contains(object)) {
			result.remove(object);
		}
		// end while.

		return result;
	}

	/**
	 * <p>
	 * Returns the sequence containing all elements of self apart from all
	 * occurrences of object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The sequence containing all elements of self apart from all
	 *         occurrences of object.
	 */
	public static <T extends Object> List<T> excluding(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new InvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		return excluding(new ArrayList<T>(Arrays.asList(self)), object);
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
					"Cannot invoke operation first() on an empty Sequences.");
		}
		// no else.

		return self.get(0);
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
					"Cannot invoke operation first() on an empty Sequences.");
		}
		// no else.

		return self[0];
	}

	/**
	 * <p>
	 * If the element type is not a collection type, this results in the same
	 * collection as self. If the element type is a collection type, the result
	 * is a collection containing all the elements of all the recursively
	 * flattened elements of self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The flattened {@link List}.
	 */
	public static <T extends Object> List<?> flatten(List<T> self) {

		return new ArrayList<Object>(OclCollections.flatten(self));
	}

	/**
	 * <p>
	 * If the element type is not a collection type, this results in the same
	 * collection as self. If the element type is a collection type, the result
	 * is a collection containing all the elements of all the recursively
	 * flattened elements of self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The flattened {@link List}.
	 */
	public static <T extends Object> List<?> flatten(T[] self) {

		if (self == null) {
			return new ArrayList<Object>();
		}
		// no else.

		return new ArrayList<Object>(OclCollections.flatten(new ArrayList<T>(
				Arrays.asList(self))));
	}

	/**
	 * <p>
	 * Returns the sequence containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The sequence containing all elements of self plus object.
	 */
	public static <T extends Object> List<T> including(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new InvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>(self);
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the sequence containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The sequence containing all elements of self plus object.
	 */
	public static <T extends Object> List<T> including(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new InvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>(Arrays.asList(self));
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the index of object in the Sequence (<strong>Indexes in OCL start
	 * with 1!</strong>).
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be indexed.
	 * @return The index of object in the Sequence.
	 */
	public static <T extends Object> Integer indexOf(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0 || !self.contains(object)) {
			throw new InvalidException(
					"The given object is not contained in self.");
		}
		// no else.

		return self.indexOf(object) + 1;
	}

	/**
	 * <p>
	 * Returns the index of object in the Sequence (<strong>Indexes in OCL start
	 * with 1!</strong>).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be indexed.
	 * @return The index of object in the Sequence.
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
	 * Returns the sequence consisting of self with object inserted at position
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
	 * @return The sequence consisting of self with object inserted at position
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

		result.add(index - 1, object);

		return result;
	}

	/**
	 * <p>
	 * Returns the sequence consisting of self with object inserted at position
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
	 * @return The sequence consisting of self with object inserted at position
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
					"Cannot invoke operation first() on an empty Sequences.");
		}
		// no else.

		return self.get(self.size() - 1);
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
					"Cannot invoke operation last() on an empty Sequences.");
		}
		// no else.

		return self[self.length - 1];
	}

	/**
	 * <p>
	 * Returns the sequence consisting of object, followed by all elements in
	 * self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be prepended.
	 * @return The sequence consisting of object, followed by all elements in
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
				result.add(element);
			}
			return result;
		}
	}

	/**
	 * <p>
	 * Returns the sequence consisting of object, followed by all elements in
	 * self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be prepended.
	 * @return The sequence consisting of object, followed by all elements in
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
	 * Returns the sequence of elements with same elements but with the opposite
	 * order.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @return The sequence of elements with same elements but with the opposite
	 *         order.
	 */
	public static <T extends Object> List<T> reverse(List<T> self) {

		List<T> result;
		result = new ArrayList<T>();

		/* OCL Collections cannot be null. */
		if (self != null) {

			for (T element : self) {

				result.add(0, element);
			}
			// end for.
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the sequence of elements with same elements but with the opposite
	 * order.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The sequence of elements with same elements but with the opposite
	 *         order.
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
	 * Returns the sub-sequence of self starting at number lower, up to and
	 * including element number upper.
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
	 * @return The sub-sequence of self starting at number lower, up to and
	 *         including element number upper.
	 */
	public static <T extends Object> List<T> subSequence(List<T> self,
			Integer lower, Integer upper) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			throw new InvalidException(
					"Cannot invoke subSequence() on empty Sequences.");
		}

		else {
			self = new ArrayList<T>(self);
		}
		// end else.

		if (lower < 1 || lower > self.size()) {
			throw new InvalidException("Index lower must lay between 1 and "
					+ self.size() + " but was " + lower + ".");
		}
		// no else.

		if (upper < 1 || upper > self.size()) {
			throw new InvalidException("Index upper must lay between 1 and "
					+ self.size() + " but was " + upper + ".");
		}
		// no else.

		if (lower > upper) {
			throw new InvalidException(
					"Index lower must be equal or less than upper.");
		}
		// no else.

		List<T> result;
		result = self.subList(lower - 1, upper);

		return result;
	}

	/**
	 * <p>
	 * Returns the sub-sequence of self starting at number lower, up to and
	 * including element number upper.
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
	 * @return The sub-sequence of self starting at number lower, up to and
	 *         including element number upper.
	 */
	public static <T extends Object> List<T> subSequence(T[] self,
			Integer lower, Integer upper) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return subSequence(new ArrayList<T>(), lower, upper);
		}

		else {
			return subSequence(new ArrayList<T>(Arrays.asList(self)), lower,
					upper);
		}
		// end else.
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param c2
	 *            The other {@link List}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(List<T> self, List<T> c2) {

		List<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = new ArrayList<T>();
		}

		else {
			result = new ArrayList<T>(self);
		}

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T>();
		}
		// no else.

		result.addAll(c2);

		return result;
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other {@link List}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(T[] self, List<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return union(new ArrayList<T>(), c2);
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T>();
		}
		// no else.

		return union(new ArrayList<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param c2
	 *            The other array.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(List<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return union(new ArrayList<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return union(self, new ArrayList<T>());
		}
		// no else.

		return union(self, new ArrayList<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other array.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return union(new ArrayList<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return union(self, new ArrayList<T>());
		}
		// no else.

		return union(new ArrayList<T>(Arrays.asList(self)), new ArrayList<T>(
				Arrays.asList(c2)));
	}
}