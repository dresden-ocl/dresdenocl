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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOrderedSet {

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.append()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testAppend01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);

		assertEquals(2, class1.testOrderedSetAppend(orderedSet01, 1).size());
		assertEquals(2, class1.testOrderedSetAppend(orderedSet01, 1).get(1));
		assertEquals(3, class1.testOrderedSetAppend(orderedSet01, 3).size());
		assertEquals(3, class1.testOrderedSetAppend(orderedSet01, 3).get(2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.asBag()</code> .
	 * </p>
	 */
	@Test
	public void testAsBag01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(3, class1.testOrderedSetAsBag(orderedSet01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OrderedSet.asOrderedSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsOrderedSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(3, class1.testOrderedSetAsOrderedSet(orderedSet01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OrderedSet.asSequence()</code> .
	 * </p>
	 */
	@Test
	public void testAsSequence01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(3, class1.testOrderedSetAsSequence(orderedSet01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.asSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(3, class1.testOrderedSetAsSet(orderedSet01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.at()</code> .
	 * </p>
	 */
	@Test
	public void testAt01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(new Integer(1), class1.testOrderedSetAt(orderedSet01, 1));
		assertEquals(new Integer(2), class1.testOrderedSetAt(orderedSet01, 2));
		assertEquals(new Integer(3), class1.testOrderedSetAt(orderedSet01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.first()</code> .
	 * </p>
	 */
	@Test
	public void testFirst01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(new Integer(1), class1.testOrderedSetFirst(orderedSet01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OrderedSet.indexOf(T)</code> .
	 * </p>
	 */
	@Test
	public void testIndexOf01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(new Integer(1), class1.testOrderedSetIndexOf01(
				orderedSet01, 1));
		assertEquals(new Integer(2), class1.testOrderedSetIndexOf01(
				orderedSet01, 2));
		assertEquals(new Integer(3), class1.testOrderedSetIndexOf01(
				orderedSet01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OrderedSet.insertAt(Integer, T)</code> .
	 * </p>
	 */
	@Test
	public void testInsertAt01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(3, class1.testOrderedSetInsertAt(orderedSet01, 1, 1)
				.size());
		assertEquals(4, class1.testOrderedSetInsertAt(orderedSet01, 1, 0)
				.size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.last()</code> .
	 * </p>
	 */
	@Test
	public void testLast01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		assertEquals(new Integer(3), class1.testOrderedSetLast(orderedSet01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.prepend()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testPrepend01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);

		assertEquals(2, class1.testOrderedSetPrepend(orderedSet01, 1).size());
		assertEquals(1, class1.testOrderedSetPrepend(orderedSet01, 1).get(0));
		assertEquals(3, class1.testOrderedSetPrepend(orderedSet01, 3).size());
		assertEquals(3, class1.testOrderedSetPrepend(orderedSet01, 3).get(0));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OrderedSet.reverse()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testReverse01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> oclOrderedSet01;
		oclOrderedSet01 = new ArrayList<Object>();
		oclOrderedSet01.add("1");
		oclOrderedSet01.add("2");
		oclOrderedSet01.add("3");

		List<Object> oclOrderedSet02;
		oclOrderedSet02 = new ArrayList<Object>();
		oclOrderedSet02.add("3");
		oclOrderedSet02.add("2");
		oclOrderedSet02.add("1");

		assertEquals(oclOrderedSet02, class1
				.testOrderedSetReverse(oclOrderedSet01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OrderedSet.subOrderedSet(OrderedSet(T))</code>.
	 * </p>
	 */
	@Test
	public void testSubOrderedSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> orderedSet01;
		orderedSet01 = new ArrayList<Object>();
		orderedSet01.add(1);
		orderedSet01.add(2);
		orderedSet01.add(3);

		List<Object> orderedSet02;
		orderedSet02 = new ArrayList<Object>();
		orderedSet02.add(1);
		orderedSet02.add(2);

		List<Object> orderedSet03;
		orderedSet03 = new ArrayList<Object>();
		orderedSet03.add(2);
		orderedSet03.add(3);

		assertEquals(orderedSet02, class1.testOrderedSetSubOrderedSet(
				orderedSet01, 1, 2));
		assertEquals(orderedSet03, class1.testOrderedSetSubOrderedSet(
				orderedSet01, 2, 3));
	}
}