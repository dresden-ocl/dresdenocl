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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Represents utility class to provide operations of OCL Collections in Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclCollections {

	/**
	 * <p>
	 * Returns the Bag that contains all the elements from self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The Bag that contains all the elements from self.
	 */
	public static <T extends Object> List<T> asBag(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		return new ArrayList<T>(self);
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

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		return Arrays.asList(self);
	}

	/**
	 * <p>
	 * Returns an OrderedSet that contains all the elements from self, with
	 * duplicates removed, in an order dependent on the particular concrete
	 * collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return An OrderedSet that contains all the elements from self, with
	 *         duplicates removed, in an order dependent on the particular
	 *         concrete collection type.
	 */
	public static <T extends Object> List<T> asOrderedSet(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		List<T> result = new ArrayList<T>();

		for (T element : self) {
			if (!result.contains(element)) {
				result.add(element);
			}
			// no else.
		}
		// end for.

		return result;
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

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		return asOrderedSet(Arrays.asList(self));
	}

	/**
	 * <p>
	 * Returns the Sequence that contains all the elements from self, in an
	 * order dependent on the particular concrete collection type.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The Sequence that contains all the elements from self, in an
	 *         order dependent on the particular concrete collection type.
	 */
	public static <T extends Object> List<T> asSequence(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		return new ArrayList<T>(self);
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

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}
		// no else.

		return Arrays.asList(self);
	}

	/**
	 * <p>
	 * Returns the Set containing all the elements from self, with duplicates
	 * removed.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The Set containing all the elements from self, with duplicates
	 *         removed.
	 */
	public static <T extends Object> Set<T> asSet(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}
		// no else.

		return new HashSet<T>(self);
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

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new HashSet<T>();
		}
		// no else.

		return new HashSet<T>(Arrays.asList(self));
	}

	/**
	 * <p>
	 * Returns the number of times that object occurs in the collection self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @param object
	 *            The {@link Object} to count.
	 * @return The number of times that object occurs in the collection self.
	 */
	public static <T extends Object> Integer count(Collection<T> self, T object) {
		assert (object != null);

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T>();
		}
		// no else.

		int result;
		result = 0;

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
	 * Returns the number of times that object occurs in the collection self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The {@link Object} to count.
	 * @return The number of times that object occurs in the collection self.
	 */
	public static <T extends Object> Integer count(T[] self, T object) {
		assert (object != null);

		/* OCL Collections cannot be null. */
		if (self == null) {
			return 0;
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
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			Collection<T2> self, Collection<T1> c2) {

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
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			T2[] self, Collection<T1> c2) {

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
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is a collection of the same kind as self
	 *         and contains the same elements in the same quantities and in the
	 *         same order, in the case of an ordered collection type.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean equals(
			Collection<T2> self, T1[] c2) {

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
	 * Returns <code>true</code> if object is not an element of self, false
	 * otherwise.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @param object
	 *            The {@link Object} which shall be searched for.
	 * @return <code>true</code> if object is not an element of self, false
	 *         otherwise.
	 */
	public static <T extends Object> Boolean excludes(Collection<T> self,
			T object) {
		return !includes(self, object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if object is not an element of self, false
	 * otherwise.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The {@link Object} which shall be searched for.
	 * @return <code>true</code> if object is not an element of self, false
	 *         otherwise.
	 */
	public static <T extends Object> Boolean excludes(T[] self, T object) {
		return !includes(self, object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains none of the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if self contains none of the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean excludesAll(
			Collection<T2> self, Collection<T1> c2) {

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

		boolean result;
		result = true;

		if (!self.isEmpty()) {
			for (Object element : c2) {
				result &= !self.contains(element);
			}
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains none of the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if self contains none of the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean excludesAll(
			T2[] self, Collection<T1> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return excludesAll(new ArrayList<T2>(), c2);
		}
		// no else.

		return excludesAll(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains none of the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if self contains none of the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean excludesAll(
			Collection<T2> self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return excludesAll(self, new ArrayList<T2>());
		}
		// no else.

		return excludesAll(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains none of the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if self contains none of the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean excludesAll(
			T2[] self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return true;
		}

		else if (c2 == null) {
			/* Self cannot be null. */
			return excludesAll(Arrays.asList(self), new ArrayList<T1>());
		}
		// no else.

		return excludesAll(Arrays.asList(self), Arrays.asList(c2));
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
	 *            The {@link Collection} representing self.
	 * @return The flattened {@link Collection}.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> Collection<?> flatten(Collection<T> self) {

		Collection<Object> result;
		result = new ArrayList<Object>();

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}
		// no else.

		for (T element : self) {

			if (element == null) {
				result.add(element);
			}

			else if (element instanceof Collection<?>) {
				result.addAll((Collection<?>) element);
			}

			else if (element.getClass().isArray()) {
				result.addAll(Arrays.asList((T[]) element));
			}

			else {
				result.add(element);
			}
		}
		// end for.

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
	 *            The array representing self.
	 * @return The flattened {@link Collection}.
	 */
	public static <T extends Object> Collection<?> flatten(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<Object>();
		}
		// no else.

		return flatten(Arrays.asList(self));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if object is an element of self, false
	 * otherwise.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param object
	 *            The {@link Object} which shall be searched for.
	 * @return <code>true</code> if object is an element of self, false
	 *         otherwise.
	 */
	public static <T extends Object> Boolean includes(Collection<T> self,
			T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return false;
		}

		else if (object == null) {
			throw new InvalidException("Parameter 'object' must not be null.");
		}
		// no else.

		return self.contains(object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if object is an element of self, false
	 * otherwise.
	 * </p>
	 * 
	 * @param object
	 *            The {@link Object} which shall be searched for.
	 * @param self
	 *            The array to be checked.
	 * @return <code>true</code> if object is an element of self, false
	 *         otherwise.
	 */
	public static <T extends Object> Boolean includes(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return false;
		}

		else if (object == null) {
			throw new InvalidException("Parameter 'object' must not be null.");
		}
		// no else.

		return includes(Arrays.asList(self), object);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains all the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if self contains all the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean includesAll(
			Collection<T2> self, Collection<T1> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T2>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T1>();
		}
		// no else.

		return self.containsAll(c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains all the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if self contains all the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean includesAll(
			T2[] self, Collection<?> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return includesAll(new ArrayList<T2>(), c2);
		}
		// no else.

		return includesAll(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains all the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if self contains all the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean includesAll(
			Collection<T2> self, T1[] c2) {
		assert (c2 != null);

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return includesAll(self, new ArrayList<T2>());
		}
		// no else.

		return includesAll(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self contains all the elements of c2.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if self contains all the elements of c2.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean includesAll(
			T2[] self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return true;
		}

		else if (self == null) {
			/* c2 cannot be null. */
			return includesAll(new ArrayList<T2>(), Arrays.asList(c2));
		}
		// no else.

		return includesAll(Arrays.asList(self), Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self is empty.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return <code>true</code> if self is empty.
	 */
	public static <T extends Object> Boolean isEmpty(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return true;
		}
		// no else.

		return self.isEmpty();
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self is empty.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return <code>true</code> if self is empty.
	 */
	public static <T extends Object> Boolean isEmpty(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return true;
		}
		// no else.

		return self.length == 0;
	}

	/**
	 * <p>
	 * Returns the element with the maximum value of all elements in self.
	 * Elements must be of a type implementing {@link Comparable}.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The element with the maximum value of all elements in self.
	 *         Elements must be of a type implementing {@link Comparable}.
	 */
	public static <T extends Object> T max(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			return null;
		}
		// no else.

		T result;

		result = Collections.max(self, new Comparator<T>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 * java.lang.Object)
			 */
			@SuppressWarnings("unchecked")
			public int compare(T first, T second) {

				if (first instanceof Comparable<?>
						&& second instanceof Comparable<?>) {
					Comparable<Object> firstComparable;
					Comparable<Object> secondComparable;

					firstComparable = (Comparable<Object>) first;
					secondComparable = (Comparable<Object>) second;

					return firstComparable.compareTo(secondComparable);
				}
				// no else.

				else {
					throw new InvalidException(
							"Cannot compare elements of collection.");
				}
			}
		});

		return result;
	}

	/**
	 * <p>
	 * Returns the element with the maximum value of all elements in self.
	 * Elements must be of a type implementing {@link Comparable}.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The element with the maximum value of all elements in self.
	 *         Elements must be of a type implementing {@link Comparable}.
	 */
	public static <T extends Comparable<T>> T max(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.length == 0) {
			return null;
		}
		// no else.

		return max(Arrays.asList(self));
	}

	/**
	 * <p>
	 * Returns the element with the minimum value of all elements in self.
	 * Elements must be of a type implementing {@link Comparable}.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The element with the minimum value of all elements in self.
	 *         Elements must be of a type implementing {@link Comparable}.
	 */
	public static <T extends Object> T min(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.size() == 0) {
			return null;
		}
		// no else.

		T result;

		result = Collections.min(self, new Comparator<T>() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Comparator#compare(java.lang.Object,
			 * java.lang.Object)
			 */
			@SuppressWarnings("unchecked")
			public int compare(T first, T second) {

				if (first instanceof Comparable<?>
						&& second instanceof Comparable<?>) {
					Comparable<Object> firstComparable;
					Comparable<Object> secondComparable;

					firstComparable = (Comparable<Object>) first;
					secondComparable = (Comparable<Object>) second;

					return firstComparable.compareTo(secondComparable);
				}
				// no else.

				else {
					throw new InvalidException(
							"Cannot compare elements of collection.");
				}
			}
		});

		return result;
	}

	/**
	 * <p>
	 * Returns the element with the minimum value of all elements in self.
	 * Elements must be of a type implementing {@link Comparable}.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The element with the minimum value of all elements in self.
	 *         Elements must be of a type implementing {@link Comparable}.
	 */
	public static <T extends Comparable<T>> T min(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null || self.length == 0) {
			return null;
		}
		// no else.

		return min(Arrays.asList(self));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self is not empty.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return <code>true</code> if self is not empty.
	 */
	public static <T extends Object> Boolean notEmpty(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return false;
		}
		// no else.

		return !self.isEmpty();
	}

	/**
	 * <p>
	 * Returns <code>true</code> if self is not empty.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return <code>true</code> if self is not empty.
	 */
	public static <T extends Object> Boolean notEmpty(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return false;
		}
		// no else.

		return self.length > 0;
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is not equal to self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if c is not equal to self.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean notEquals(
			Collection<T2> self, Collection<T1> c2) {

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

		return !self.equals(c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is not equal to self.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The {@link Collection} which elements shall be checked.
	 * @return <code>true</code> if c is not equal to self.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean notEquals(
			T2[] self, Collection<T1> c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			c2 = new ArrayList<T1>();
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return notEquals(new ArrayList<T2>(), c2);
		}
		// no else.

		return notEquals(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is not equal to self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is not equal to self.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean notEquals(
			Collection<T2> self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return notEquals(self, new ArrayList<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T2>();
		}
		// no else.

		return notEquals(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns <code>true</code> if c is not equal to self.
	 * </p>
	 * 
	 * @param self
	 *            The array to be checked.
	 * @param c2
	 *            The array which elements shall be checked.
	 * @return <code>true</code> if c is not equal to self.
	 */
	public static <T1 extends Object, T2 extends Object> Boolean notEquals(
			T2[] self, T1[] c2) {

		/* OCL Collections cannot be null. */
		if (c2 == null) {
			return notEquals(self, new ArrayList<T1>());
		}
		// no else.

		/* OCL Collections cannot be null. */
		if (self == null) {
			return notEquals(new ArrayList<T1>(), c2);
		}
		// no else.

		return notEquals(Arrays.asList(self), Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the cartesian product of self and c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @param c2
	 *            The second {@link Collection}.
	 * @return The cartesian product of self and c2.
	 */
	public static <T1 extends Object, T2 extends Object> Set<Map<String, Object>> product(
			Collection<T1> self, Collection<T2> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T1>();
		}
		// no else.

		if (c2 == null) {
			c2 = new ArrayList<T2>();
		}
		// no else.

		Set<Map<String, Object>> result;
		result = new HashSet<Map<String, Object>>();

		for (T1 element1 : self) {

			for (Object element2 : c2) {

				Map<String, Object> tuple;
				tuple = new HashMap<String, Object>();
				tuple.put("first", element1);
				tuple.put("second", element2);

				result.add(tuple);
			}
			// end for.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the cartesian product of self and c2.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The second {@link Collection}.
	 * @return The cartesian product of self and c2.
	 */
	public static <T1 extends Object, T2 extends Object> Set<Map<String, Object>> product(
			T1[] self, Collection<T2> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return product(new ArrayList<T1>(), c2);
		}

		else if (c2 == null) {
			c2 = new ArrayList<T2>();
		}
		// no else.

		return product(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns the cartesian product of self and c2.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @param c2
	 *            The second array.
	 * @return The cartesian product of self and c2.
	 */
	public static <T1 extends Object, T2 extends Object> Set<Map<String, Object>> product(
			Collection<T1> self, T2[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			self = new ArrayList<T1>();
		}

		if (c2 == null) {
			return product(self, new ArrayList<T2>());
		}
		// no else.

		return product(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the cartesian product of self and c2.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The second array.
	 * @return The cartesian product of self and c2.
	 */
	public static <T1 extends Object, T2 extends Object> Set<Map<String, Object>> product(
			T1[] self, T2[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return product(new ArrayList<T1>(), c2);
		}
		// no else.

		else if (c2 == null) {
			return product(self, new ArrayList<T2>());
		}
		// no else.

		return product(Arrays.asList(self), Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the number of elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The number of elements in self.
	 */
	public static <T extends Object> Integer size(Collection<T> self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return 0;
		}
		// no else.

		return self.size();
	}

	/**
	 * <p>
	 * Returns the number of elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The number of elements in self.
	 */
	public static <T extends Object> Integer size(T[] self) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return 0;
		}
		// no else.

		return self.length;
	}

	/**
	 * <p>
	 * Returns the addition of all elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The addition of all elements in self.
	 */
	public static Float sum(Collection<Float> self) {

		Float result;
		result = new Float(0);

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}
		// no else.

		for (Float element : self) {
			result += element;
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the addition of all elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The {@link Collection} representing self.
	 * @return The addition of all elements in self.
	 */
	public static Integer sum(Collection<Integer> self) {

		Integer result;
		result = 0;

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}
		// no else.

		for (Integer element : self) {
			result += element;
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the addition of all elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The addition of all elements in self.
	 */
	public static Integer sum(Integer[] self) {

		Integer result;
		result = 0;

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}
		// no else.

		for (Integer element : self) {
			result += element;
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the addition of all elements in self.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @return The addition of all elements in self.
	 */
	public static Float sum(Float[] self) {

		Float result;
		result = new Float(0);

		/* OCL Collections cannot be null. */
		if (self == null) {
			return result;
		}
		// no else.

		for (Float element : self) {
			result += element;
		}
		// end for.

		return result;
	}
}