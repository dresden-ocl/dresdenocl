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
import java.util.Collection;
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
public class TestSequence {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.append()</code> .
	 * </p>
	 */
	@Test
	public void testAppend01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(3, class1.testSequenceAppend(sequence01, 1).size());
		assertEquals(1, class1.testSequenceAppend(sequence01, 1).get(2));
		assertEquals(3, class1.testSequenceAppend(sequence01, 3).size());
		assertEquals(3, class1.testSequenceAppend(sequence01, 3).get(2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.asBag()</code> .
	 * </p>
	 */
	@Test
	public void testAsBag01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(3, class1.testSequenceAsBag(sequence01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.asOrderedSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsOrderedSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(2, class1.testSequenceAsOrderedSet(sequence01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.asSequence()</code> .
	 * </p>
	 */
	@Test
	public void testAsSequence01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(3, class1.testSequenceAsSequence(sequence01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.asSet()</code> .
	 * </p>
	 */
	@Test
	public void testAsSet01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(2, class1.testSequenceAsSet(sequence01).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.at()</code> .
	 * </p>
	 */
	@Test
	public void testAt01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		assertEquals(new Integer(1), class1.testSequenceAt(sequence01, 1));
		assertEquals(new Integer(2), class1.testSequenceAt(sequence01, 2));
		assertEquals(new Integer(3), class1.testSequenceAt(sequence01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.count()</code> .
	 * </p>
	 */
	@Test
	public void testCount01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(new Integer(2), class1.testSequenceCount01(sequence01, 1));
		assertEquals(new Integer(1), class1.testSequenceCount01(sequence01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.=(Sequence)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testEquals01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		List<Object> sequence02;
		sequence02 = new ArrayList<Object>();
		sequence02.add(1);
		sequence02.add(2);
		sequence02.add(2);

		assertTrue(class1.testSequenceEquals01(sequence01, sequence01));
		assertFalse(class1.testSequenceEquals01(sequence01, sequence02));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcluding01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		List<Object> sequence02;
		sequence02 = new ArrayList<Object>();
		sequence02.add(1);
		sequence02.add(1);

		List<Object> sequence03;
		sequence03 = new ArrayList<Object>();
		sequence03.add(2);

		assertEquals(sequence01, class1.testSequenceExcluding01(sequence01, 0));
		assertEquals(sequence03, class1.testSequenceExcluding01(sequence01, 1));
		assertEquals(sequence02, class1.testSequenceExcluding01(sequence01, 2));
		assertEquals(sequence01, class1.testSequenceExcluding01(sequence01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.first()</code> .
	 * </p>
	 */
	@Test
	public void testFirst01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		assertEquals(new Integer(1), class1.testSequenceFirst(sequence01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.flatten()</code> .
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

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(containedSet01);
		sequence01.add(containedSet02);

		Collection<?> flattenedCollection;
		flattenedCollection = class1.testSequenceFlatten(sequence01);

		int countOfOne = 0;
		for (Object elem : flattenedCollection) {
			if (elem.equals(1)) countOfOne++;
		}
		// end for.
		
		assertEquals(2, countOfOne);
		assertTrue(flattenedCollection.contains(2));
		assertTrue(flattenedCollection.contains(3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncluding01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(1);
		sequence01.add(2);

		List<Object> sequence02;
		sequence02 = new ArrayList<Object>();
		sequence02.add(1);
		sequence02.add(1);
		sequence02.add(2);
		sequence02.add(2);

		List<Object> sequence03;
		sequence03 = new ArrayList<Object>();
		sequence03.add(1);
		sequence03.add(1);
		sequence03.add(2);
		sequence03.add(1);

		assertEquals(sequence03, class1.testSequenceIncluding01(sequence01, 1));
		assertEquals(sequence02, class1.testSequenceIncluding01(sequence01, 2));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.indexOf(T)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testIndexOf01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		assertEquals(new Integer(1), class1
				.testSequenceIndexOf01(sequence01, 1));
		assertEquals(new Integer(2), class1
				.testSequenceIndexOf01(sequence01, 2));
		assertEquals(new Integer(3), class1
				.testSequenceIndexOf01(sequence01, 3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.insertAt(Integer, T)</code> .
	 * </p>
	 */
	@Test
	public void testInsertAt01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		assertEquals(4, class1.testSequenceInsertAt(sequence01, 1, 1).size());
		assertEquals(4, class1.testSequenceInsertAt(sequence01, 1, 0).size());
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.last()</code> .
	 * </p>
	 */
	@Test
	public void testLast01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		assertEquals(new Integer(3), class1.testSequenceLast(sequence01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.prepend()</code> .
	 * </p>
	 */
	@Test
	public void testPrepend01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);

		assertEquals(3, class1.testSequencePrepend(sequence01, 1).size());
		assertEquals(1, class1.testSequencePrepend(sequence01, 1).get(0));
		assertEquals(3, class1.testSequencePrepend(sequence01, 3).size());
		assertEquals(3, class1.testSequencePrepend(sequence01, 3).get(0));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Sequence.reverse()</code> .
	 * </p>
	 */
	@Test
	public void testReverse01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> oclSequence01;
		oclSequence01 = new ArrayList<Object>();
		oclSequence01.add("1");
		oclSequence01.add("2");
		oclSequence01.add("3");

		List<Object> oclSequence02;
		oclSequence02 = new ArrayList<Object>();
		oclSequence02.add("3");
		oclSequence02.add("2");
		oclSequence02.add("1");

		assertEquals(oclSequence02, class1.testSequenceReverse(oclSequence01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.subSequence(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testSubSequence01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);
		sequence01.add(3);

		List<Object> sequence02;
		sequence02 = new ArrayList<Object>();
		sequence02.add(1);
		sequence02.add(2);

		List<Object> sequence03;
		sequence03 = new ArrayList<Object>();
		sequence03.add(2);
		sequence03.add(3);

		assertEquals(sequence02, class1.testSequenceSubSequence(sequence01, 1,
				2));
		assertEquals(sequence03, class1.testSequenceSubSequence(sequence01, 2,
				3));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Sequence.union(Set(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnion01() {

		Class1 class1;
		class1 = new Class1();

		List<Object> sequence01;
		sequence01 = new ArrayList<Object>();
		sequence01.add(1);
		sequence01.add(2);

		List<Object> sequence02;
		sequence02 = new ArrayList<Object>();
		sequence02.add(1);
		sequence02.add(3);

		List<Object> sequence03;
		sequence03 = new ArrayList<Object>();
		sequence03.add(1);
		sequence03.add(2);
		sequence03.add(1);
		sequence03.add(3);

		assertEquals(sequence03, class1.testSequenceUnion01(sequence01,
				sequence02));
	}
}