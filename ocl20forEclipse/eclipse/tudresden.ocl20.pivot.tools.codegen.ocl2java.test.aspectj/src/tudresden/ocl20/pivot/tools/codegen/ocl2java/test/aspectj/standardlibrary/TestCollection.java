/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

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

package tudresden.ocl20.pivot.ocl22java.test.aspectj.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestCollection {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.asBag()</code> .
	 * </p>
	 */
	@Test
	public void testAsBag01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(3, class1.testCollectionAsBag(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.asOrderedSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsOrderedSet01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(3, class1.testCollectionAsOrderedSet(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.asSequence()</code> .
	 * </p>
	 */
	@Test
	public void testAsSequence01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(3, class1.testCollectionAsSequence(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.asSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);
		bag01.add(3);

		assertEquals(3, class1.testCollectionAsSet(bag01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.count()</code> .
	 * </p>
	 */
	@Test
	public void testCount01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(new Integer(1), class1.testCollectionCount(set01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.=(Collection)</code> .
	 * </p>
	 */
	@Test
	public void testEquals01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(2);
		set02.add(2);

		assertTrue(class1.testCollectionEquals(set01, set01));
		assertFalse(class1.testCollectionEquals(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.excludes(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcludes01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertTrue(class1.testCollectionExcludes(set01, 4));
		assertFalse(class1.testCollectionExcludes(set01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.excludesAll(Collection)</code>.
	 * </p>
	 */
	@Test
	public void testExcludesAll01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(4);
		set02.add(5);
		set02.add(6);

		assertTrue(class1.testCollectionExcludesAll(set01, set02));
		assertFalse(class1.testCollectionExcludesAll(set01, set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.flatten()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testFlatten01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> containedSet01;
		containedSet01 = new HashSet<Object>();
		containedSet01.add(1);
		containedSet01.add(2);

		Set<Object> containedSet02;
		containedSet02 = new HashSet<Object>();
		containedSet02.add(1);
		containedSet02.add(3);

		Set<Object> oclSet;
		oclSet = new HashSet<Object>();
		oclSet.add(containedSet01);
		oclSet.add(containedSet02);

		List<Object> expectedCollection;
		expectedCollection = new ArrayList<Object>();
		expectedCollection.add(1);
		expectedCollection.add(2);
		expectedCollection.add(1);
		expectedCollection.add(3);

		assertEquals(expectedCollection, class1.testCollectionFlatten(oclSet));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.includes(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncludes01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertTrue(class1.testCollectionIncludes(set01, 2));
		assertFalse(class1.testCollectionIncludes(set01, 4));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.includesAll(Collection)</code>.
	 * </p>
	 */
	@Test
	public void testIncludesAll01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(4);
		set02.add(5);
		set02.add(6);

		assertTrue(class1.testCollectionIncludesAll(set01, set01));
		assertFalse(class1.testCollectionIncludesAll(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.isEmpty()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testIsEmpty01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertFalse(class1.testCollectionIsEmpty(set01));

		set01.clear();
		assertTrue(class1.testCollectionIsEmpty(set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.max()</code> .
	 * </p>
	 */
	@Test
	public void testMax01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(3, class1.testCollectionMax(set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.min()</code> .
	 * </p>
	 */
	@Test
	public void testMin01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(1, class1.testCollectionMin(set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.<>(Collection)</code> .
	 * </p>
	 */
	@Test
	public void testNotEquals01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(2);
		set02.add(2);

		assertFalse(class1.testCollectionNotEquals(set01, set01));
		assertTrue(class1.testCollectionNotEquals(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.notEmpty()</code> .
	 * </p>
	 */
	@Test
	public void testNotEmpty01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertTrue(class1.testCollectionNotEmpty(set01));

		set01.clear();
		assertFalse(class1.testCollectionNotEmpty(set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Collection.product(Collection)</code> .
	 * </p>
	 */
	@Test
	public void testProduct01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(2);

		assertEquals(new Integer(4), class1.testCollectionProduct(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.size()</code> .
	 * </p>
	 */
	@Test
	public void testSize01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);

		assertEquals(new Integer(2), class1.testCollectionSize(set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.sum()</code> .
	 * </p>
	 */
	@Test
	public void testSum01() {

		Class1 class1;
		class1 = new Class1();

		Set<Integer> set01;
		set01 = new HashSet<Integer>();
		set01.add(1);
		set01.add(2);

		assertEquals(new Integer(3), class1.testCollectionSum(set01));
	}
}