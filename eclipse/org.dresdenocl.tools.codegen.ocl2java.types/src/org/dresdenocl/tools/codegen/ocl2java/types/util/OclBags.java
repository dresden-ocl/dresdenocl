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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclInvalidException;

/**
 * <p>
 * Represents utility class to provide operations of OCL Bags in Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclBags {

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
	 * Returns the bag containing all elements of self apart from all
	 * occurrences of object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The bag containing all elements of self apart from all
	 *         occurrences of object.
	 */
	public static <T extends Object> List<T> excluding(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
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
	 * Returns the bag containing all elements of self apart from all
	 * occurrences of object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The bag containing all elements of self apart from all
	 *         occurrences of object.
	 */
	public static <T extends Object> List<T> excluding(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		return excluding(new ArrayList<T>(Arrays.asList(self)), object);
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
	 * Returns the bag containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param object
	 *            The object to be included.
	 * @return The bag containing all elements of self plus object.
	 */
	public static <T extends Object> List<T> including(List<T> self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>(self);
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the bag containing all elements of self plus object.
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param object
	 *            The object to be included.
	 * @return The bag containing all elements of self plus object.
	 */
	public static <T extends Object> List<T> including(T[] self, T object) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			throw new OclInvalidException("Parameter 'self' must not be null.");
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>(Arrays.asList(self));
		result.add(object);

		return result;
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the bag of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param c2
	 *            The other {@link Collection}.
	 * @return The intersection of self and s (i.e., the bag of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> List<T> intersection(List<T> self,
			Collection<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}

		else if (c2 == null) {
			return new ArrayList<T>();
		}
		// no else.

		List<T> result;
		result = new ArrayList<T>();

		for (T element : self) {
			if (c2.contains(element) && !result.contains(element)) {
				for (int count = 0; count < Math.min(count(self, element),
						count(new ArrayList<T>(c2), element)); count++) {
					result.add(element);
				}
				// end for.
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the bag of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other {@link Collection}.
	 * @return The intersection of self and s (i.e., the bag of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> List<T> intersection(T[] self,
			Collection<T> c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}

		else if (c2 == null) {
			return new ArrayList<T>();
		}
		// no else.

		return intersection(Arrays.asList(self), c2);
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the bag of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param c2
	 *            The other array.
	 * @return The intersection of self and s (i.e., the bag of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> List<T> intersection(List<T> self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}

		else if (c2 == null) {
			return new ArrayList<T>();
		}
		// no else.

		return intersection(self, Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the intersection of self and s (i.e., the bag of all elements
	 * that are in both self and s).
	 * </p>
	 * 
	 * @param self
	 *            The array representing self.
	 * @param c2
	 *            The other array.
	 * @return The intersection of self and s (i.e., the bag of all elements
	 *         that are in both self and s).
	 */
	public static <T extends Object> List<T> intersection(T[] self, T[] c2) {

		/* OCL Collections cannot be null. */
		if (self == null) {
			return new ArrayList<T>();
		}

		else if (c2 == null) {
			return new ArrayList<T>();
		}
		// no else.

		return intersection(Arrays.asList(self), Arrays.asList(c2));
	}

	/**
	 * <p>
	 * Returns the union of self and s.
	 * </p>
	 * 
	 * @param self
	 *            The {@link List} representing self.
	 * @param c2
	 *            The other {@link Collection}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(List<T> self,
			Collection<T> c2) {

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
	 *            The other {@link Collection}.
	 * @return The union of self and s.
	 */
	public static <T extends Object> List<T> union(T[] self, Collection<T> c2) {

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