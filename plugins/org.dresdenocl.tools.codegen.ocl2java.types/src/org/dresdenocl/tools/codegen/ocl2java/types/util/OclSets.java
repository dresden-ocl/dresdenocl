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
package org.dresdenocl.tools.codegen.ocl2java.types.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dresdenocl.tools.codegen.ocl2java.types.OclInvalidException;

/**
 * <p>
 * Represents utility class to provide operations of OCL Sets in Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclSets {

	/**
	 * <p>
	 * Returns the Bag that contains all the elements from self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @return The Bag that contains all the elements from self.
	 */
	public static <T extends Object> List<T> asBag(Set<T> self) {

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
			return OclCollections.asBag(new HashSet<T>());
		}
		// no else.

		return OclCollections.asBag(new HashSet<T>(Arrays.asList(self)));
	}

	/**
	 * <p>
	 * Returns an OrderedSet that contains all the elements from self, with
	 * duplicates removed, in an order dependent on the particular concrete
	 * collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @return An OrderedSet that contains all the elements from self, with
	 *         duplicates removed, in an order dependent on the particular
	 *         concrete collection type.
	 */
	public static <T extends Object> List<T> asOrderedSet(Set<T> self) {

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
			return OclCollections.asOrderedSet(new HashSet<T>());
		}
		// no else.

		return OclCollections.asOrderedSet(new HashSet<T>(Arrays.asList(self)));
	}

	/**
	 * <p>
	 * Returns the Sequence that contains all the elements from self, in an
	 * order dependent on the particular concrete collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @return The Sequence that contains all the elements from self, in an
	 *         order dependent on the particular concrete collection type.
	 */
	public static <T extends Object> List<T> asSequence(Set<T> self) {

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
			return OclCollections.asSequence(new HashSet<T>());
		}
		// no else.

		return OclCollections.asSequence(new HashSet<T>(Arrays.asList(self)));
	}

	/**
	 * <p>
	 * Returns the Set containing all the elements from self, with duplicates
	 * removed.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @return The Set containing all the elements from self, with duplicates
	 *         removed.
	 */
	public static <T extends Object> Set<T> asSet(Set<T> self) {

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
			return OclCollections.asSet(new HashSet<T>());
		}
		// no else.

		return OclCollections.asSet(new HashSet<T>(Arrays.asList(self)));
	}

	/**
	 * <p>
	 * Returns the number of occurrences of object in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param object
	 *            The object to be counted.
	 * @return The number of occurrences of object in self.
	 */
	public static <T extends Object> Integer count(Set<T> self, T object) {

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

		return count(new HashSet<T>(Arrays.asList(self)), object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} to be checked.
	 * @param c2
	 *            The {@link Set} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			Set<T2> self, Set<T1> c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new HashSet<T1>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new HashSet<T2>();
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
	 *            The {@link Set} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			T2[] self, Set<T1> c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new HashSet<T1>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return equals(new HashSet<T2>(), c2);
		}
		// no else.

		return equals(new HashSet<T2>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is a collection of the same kind as self
	 * and contains the same elements in the same quantities and in the same
	 * order, in the case of an ordered collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			Set<T2> self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return equals(self, new HashSet<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new HashSet<T2>();
		}
		// no else.

		return equals(self, new HashSet<T1>(Arrays.asList(c2)));
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
			return equals(self, new HashSet<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return equals(new HashSet<T1>(), c2);
		}
		// no else.

		return equals(new HashSet<T2>(Arrays.asList(self)), new HashSet<T1>(
				Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the set containing all elements of self without object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The set containing all elements of self without object.
	 */
	public static <T extends Object> Set<T> excluding(Set<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>(self);
		result.remove(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the set containing all elements of self without object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The set containing all elements of self without object.
	 */
	public static <T extends Object> Set<T> excluding(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>(Arrays.asList(self));
		result.remove(object);

		return result;
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
	 *            The {@link Set} representing self.
	 * @return The flattened {@link Set}.
	 */
	public static <T extends Object> Set<?> flatten(Set<T> self) {

		return new HashSet<Object>(OclCollections.flatten(self));
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
	 * @return The flattened {@link Set}.
	 */
	public static <T extends Object> Set<?> flatten(T[] self) {

		if (self == null) {
			return new HashSet<Object>();
		}
		// no else.

		return new HashSet<Object>(OclCollections.flatten(new HashSet<T>(Arrays
				.asList(self))));
	}

	/**
	 * <p>
	 * Returns the set containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The set containing all elements of self plus object.
	 */
	public static <T extends Object> Set<T> including(Set<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>(self);
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the set containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The set containing all elements of self plus object.
	 */
	public static <T extends Object> Set<T> including(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>(Arrays.asList(self));
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the set of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other {@link Collection}.
	 * @return The intersection of self and s (i.e., the set of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> Set<T> intersection(Set<T> self,
			Collection<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>();
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>();

		for (T element : self) {
			if (c2.contains(element)) {
				result.add(element);
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the set of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other {@link Collection}.
	 * @return The intersection of self and s (i.e., the set of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> Set<T> intersection(T[] self,
			Collection<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>();
		}
		// no else.

		return intersection(new HashSet<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the set of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other array.
	 * @return The intersection of self and s (i.e., the set of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> Set<T> intersection(Set<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>();
		}
		// no else.

		return intersection(self, new HashSet<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the set of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other array.
	 * @return The intersection of self and s (i.e., the set of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> Set<T> intersection(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>();
		}
		// no else.

		return intersection(new HashSet<T>(Arrays.asList(self)),
				new HashSet<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the elements of self, which are not in s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other {@link Set}.
	 * @return The elements of self, which are not in s.
	 */
	public static <T extends Object> Set<T> minus(Set<T> self, Set<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>(self);
		}
		// no else.

		Set<T> result;
		result = self;
		result.removeAll(c2);

		return result;
	}

	/**
	 * <p>
	 * Returns the elements of self, which are not in s.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other {@link Set}.
	 * @return The elements of self, which are not in s.
	 */
	public static <T extends Object> Set<T> minus(T[] self, Set<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			c2 = new HashSet<T>();
		}
		// no else.

		return minus(new HashSet<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the elements of self, which are not in s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other array.
	 * @return The elements of self, which are not in s.
	 */
	public static <T extends Object> Set<T> minus(Set<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return new HashSet<T>(self);
		}
		// no else.

		return minus(self, new HashSet<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the elements of self, which are not in s.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other array.
	 * @return The elements of self, which are not in s.
	 */
	public static <T extends Object> Set<T> minus(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}

		else if (c2 == null) {
			return minus(new HashSet<T>(Arrays.asList(self)), new HashSet<T>());
		}
		// no else.

		return minus(new HashSet<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the set containing all the elements that are in self or s, but
	 * not in both.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other {@link Set}.
	 * @return The set containing all the elements that are in self or s, but
	 *         not in both.
	 */
	public static <T extends Object> Set<T> symmetricDifference(Set<T> self,
			Set<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new HashSet<T>();
		}
		// no else.

		if (c2 == null) {
			c2 = new HashSet<T>();
		}
		// no else.

		Set<T> result;
		result = new HashSet<T>();

		for (T element : self) {
			if (!c2.contains(element)) {
				result.add(element);
			}
			// no else.
		}
		// end for.

		for (T element : c2) {
			if (!self.contains(element)) {
				result.add(element);
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the set containing all the elements that are in self or s, but
	 * not in both.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other {@link Set}.
	 * @return The set containing all the elements that are in self or s, but
	 *         not in both.
	 */
	public static <T extends Object> Set<T> symmetricDifference(T[] self,
			Set<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return symmetricDifference(new HashSet<T>(), c2);
		}

		else if (c2 == null) {
			c2 = new HashSet<T>();
		}
		// no else.

		return symmetricDifference(new HashSet<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the set containing all the elements that are in self or s, but
	 * not in both.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other array.
	 * @return The set containing all the elements that are in self or s, but
	 *         not in both.
	 */
	public static <T extends Object> Set<T> symmetricDifference(Set<T> self,
			T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new HashSet<T>();
		}
		// no else.

		if (c2 == null) {
			return symmetricDifference(self, new HashSet<T>());
		}
		// no else.

		return symmetricDifference(self, new HashSet<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the set containing all the elements that are in self or s, but
	 * not in both.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other array.
	 * @return The set containing all the elements that are in self or s, but
	 *         not in both.
	 */
	public static <T extends Object> Set<T> symmetricDifference(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return symmetricDifference(new HashSet<T>(), c2);
		}

		else if (c2 == null) {
			return symmetricDifference(self, new HashSet<T>());
		}
		// no else.

		return symmetricDifference(new HashSet<T>(Arrays.asList(self)),
				new HashSet<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other {@link List}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> unionWithBag(Set<T> self,
			List<T> c2) {

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
	public static <T extends Object> List<T> unionWithBag(T[] self, List<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithBag(new HashSet<T>(), c2);
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T>();
		}
		// no else.

		return unionWithBag(new HashSet<T>(Arrays.asList(self)), c2);
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
	public static <T extends Object> List<T> unionWithBag(Set<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithBag(new HashSet<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return unionWithBag(self, new ArrayList<T>());
		}
		// no else.

		return unionWithBag(self, new ArrayList<T>(Arrays.asList(c2)));
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
	public static <T extends Object> List<T> unionWithBag(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithBag(new HashSet<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return unionWithBag(self, new ArrayList<T>());
		}
		// no else.

		return unionWithBag(new HashSet<T>(Arrays.asList(self)),
				new ArrayList<T>(Arrays.asList(c2)));
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other {@link Set}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> Set<T> unionWithSet(Set<T> self, Set<T> c2) {

		Set<T> result;

		/* OCL Collections cannot be null. */
		if (self == null) {
			result = new HashSet<T>();
		}

		else {
			result = new HashSet<T>(self);
		}

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new HashSet<T>();
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
	 *            The other {@link Set}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> Set<T> unionWithSet(T[] self, Set<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithSet(new HashSet<T>(), c2);
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new HashSet<T>();
		}
		// no else.

		return unionWithSet(new HashSet<T>(Arrays.asList(self)), c2);
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Set} representing self.
	 * @param c2
	 *            The other array.
	 * @return The union of self and s.
	 */
	public static <T extends Object> Set<T> unionWithSet(Set<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithSet(new HashSet<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return unionWithSet(self, new HashSet<T>());
		}
		// no else.

		return unionWithSet(self, new HashSet<T>(Arrays.asList(c2)));
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
	public static <T extends Object> Set<T> unionWithSet(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return unionWithSet(new HashSet<T>(), c2);
		}
		// no else.

		if (c2 == null) {
			return unionWithSet(self, new HashSet<T>());
		}
		// no else.

		return unionWithSet(new HashSet<T>(Arrays.asList(self)),
				new HashSet<T>(Arrays.asList(c2)));
	}
}