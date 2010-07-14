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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.aspectj.standardlibrary;

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
public class TestBag {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.asBag()</code> .
	 * </p>
	 */
	@Test
	public void testAsBag01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		assertEquals(3, class1.testBagAsBag(bag01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.asOrderedSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsOrderedSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		assertEquals(2, class1.testBagAsOrderedSet(bag01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.asSequence()</code> .
	 * </p>
	 */
	@Test
	public void testAsSequence01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		assertEquals(3, class1.testBagAsSequence(bag01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.asSet()</code> .
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

		assertEquals(2, class1.testBagAsSet(bag01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.count()</code> .
	 * </p>
	 */
	@Test
	public void testCount01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		assertEquals(new Integer(2), class1.testBagCount01(bag01, 1));
		assertEquals(new Integer(1), class1.testBagCount01(bag01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.=(Bag)</code> .
	 * </p>
	 */
	@Test
	public void testEquals01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(2);
		bag02.add(2);

		assertTrue(class1.testBagEquals01(bag01, bag01));
		assertFalse(class1.testBagEquals01(bag01, bag02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcluding01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(1);

		List<Object> bag03;
		bag03 = new ArrayList<Object>();
		bag03.add(2);

		assertEquals(bag01, class1.testBagExcluding01(bag01, 0));
		assertEquals(bag03, class1.testBagExcluding01(bag01, 1));
		assertEquals(bag02, class1.testBagExcluding01(bag01, 2));
		assertEquals(bag01, class1.testBagExcluding01(bag01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.flatten()</code> .
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

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(containedSet01);
		bag01.add(containedSet02);

		List<Object> expectedBag;
		expectedBag = new ArrayList<Object>();
		expectedBag.add(1);
		expectedBag.add(2);
		expectedBag.add(1);
		expectedBag.add(3);

		assertEquals(expectedBag, class1.testBagFlatten(bag01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncluding01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(1);
		bag02.add(2);
		bag02.add(2);

		List<Object> bag03;
		bag03 = new ArrayList<Object>();
		bag03.add(1);
		bag03.add(1);
		bag03.add(2);
		bag03.add(1);

		assertEquals(bag03, class1.testBagIncluding01(bag01, 1));
		assertEquals(bag02, class1.testBagIncluding01(bag01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Bag.intersection(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersection01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(1);
		bag01.add(2);
		bag01.add(2);

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(2);

		assertEquals(bag02, class1.testBagIntersection01(bag01, set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Bag.intersection(Bag(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersection02() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(1);
		bag02.add(2);
		bag02.add(2);

		assertEquals(bag01, class1.testBagIntersection02(bag01, bag01));
		assertEquals(bag01, class1.testBagIntersection02(bag01, bag02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.union(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnion01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(2);

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(3);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(2);
		bag02.add(1);
		bag02.add(3);

		assertEquals(bag02, class1.testBagUnion01(bag01, set01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Bag.union(Bag(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnion02() {

		Class1 class1;
		class1 = new Class1();

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(2);

		List<Object> bag02;
		bag02 = new ArrayList<Object>();
		bag02.add(1);
		bag02.add(3);

		List<Object> bag03;
		bag03 = new ArrayList<Object>();
		bag03.add(1);
		bag03.add(2);
		bag03.add(1);
		bag03.add(3);

		assertEquals(bag03, class1.testBagUnion02(bag01, bag02));
	}
}