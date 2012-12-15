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

package org.dresdenocl.tools.codegen.ocl2java.test.aspectj.standardlibrary;

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
public class TestSet {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.asBag()</code> .
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

		assertEquals(3, class1.testSetAsBag(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.asOrderedSet()</code> .
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

		assertEquals(3, class1.testSetAsOrderedSet(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.asSequence()</code> .
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

		assertEquals(3, class1.testSetAsSequence(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.asSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsSet01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		assertEquals(3, class1.testSetAsSet(set01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.count()</code> .
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

		assertEquals(new Integer(1), class1.testSetCount01(set01, 1));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.=(Set)</code> .
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
		set02.add(4);

		assertTrue(class1.testSetEquals01(set01, set01));
		assertFalse(class1.testSetEquals01(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcluding01() {

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

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(1);
		set03.add(3);

		assertEquals(set01, class1.testSetExcluding01(set01, 0));
		assertEquals(set02, class1.testSetExcluding01(set01, 3));
		assertEquals(set03, class1.testSetExcluding01(set01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.flatten()</code> .
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

		Set<Object> expectedSet;
		expectedSet = new HashSet<Object>();
		expectedSet.add(1);
		expectedSet.add(2);
		expectedSet.add(3);

		assertEquals(expectedSet, class1.testSetFlatten(oclSet));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncluding01() {

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

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(1);
		set03.add(3);

		assertEquals(set01, class1.testSetIncluding01(set02, 3));
		assertEquals(set01, class1.testSetIncluding01(set03, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Set.intersection(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersection01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(2);

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(1);

		assertEquals(set03, class1.testSetIntersection01(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Set.intersection(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersection02() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(3);

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(2);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);

		assertEquals(set02, class1.testSetIntersection02(set01, bag01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.-(Set)</code> .
	 * </p>
	 */
	@Test
	public void testMinus01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(2);
		set02.add(3);

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(1);

		assertEquals(set03, class1.testSetMinus(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Set.symmetricDifference(Set)</code> .
	 * </p>
	 */
	@Test
	public void testSymmetricDifference01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(3);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(2);

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(2);
		set03.add(3);

		assertEquals(set03, class1.testSetSymmetricDifference01(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.union(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnion01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);

		Set<Object> set02;
		set02 = new HashSet<Object>();
		set02.add(1);
		set02.add(3);

		Set<Object> set03;
		set03 = new HashSet<Object>();
		set03.add(1);
		set03.add(2);
		set03.add(3);

		assertEquals(set03, class1.testSetUnion01(set01, set02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Set.union(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnion02() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> set01;
		set01 = new HashSet<Object>();
		set01.add(1);
		set01.add(2);

		List<Object> bag01;
		bag01 = new ArrayList<Object>();
		bag01.add(1);
		bag01.add(3);
		
		List<?> unitedCollection;
		unitedCollection = class1.testSetUnion02(set01, bag01);

		int countOfOne = 0;
		for (Object elem : unitedCollection) {
			if (elem.equals(1)) countOfOne++;
		}
		// end for.
		
		assertEquals(2, countOfOne);
		assertTrue(unitedCollection.contains(2));
		assertTrue(unitedCollection.contains(3));
	}
}