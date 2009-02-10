/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.ocl2java.types.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import tudresden.ocl20.pivot.ocl2java.types.OclBag;
import tudresden.ocl20.pivot.ocl2java.types.OclOrderedSet;
import tudresden.ocl20.pivot.ocl2java.types.OclSequence;
import tudresden.ocl20.pivot.ocl2java.types.OclSet;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclOrderedSet}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclSequenceTest {

	@Test
	public void testAppend() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
	
		sequence2.add(object1);
		sequence2.add(object2);
		sequence2.add(object3);
		sequence2.add(object4);
	
		sequence1 = sequence1.append(object4);
	
		assertEquals(sequence1, sequence2);
	}

	@Test
	public void testAsBag() {
	
		OclSequence<String> sequence1;
		OclBag<String> bag1;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
		sequence1.add(object4);
	
		bag1 = sequence1.asBag();
	
		assertEquals(4, bag1.size());
		assertTrue(bag1.contains(object1));
		assertTrue(bag1.contains(object2));
		assertTrue(bag1.contains(object3));
		assertTrue(bag1.contains(object4));
	}

	@Test
	public void testAsOrderedSet() {
	
		OclSequence<String> sequence1;
		OclOrderedSet<String> set1;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
		sequence1.add(object4);
	
		set1 = sequence1.asOrderedSet();
	
		assertEquals(3, set1.size());
		assertEquals(object1, set1.get(1));
		assertEquals(object2, set1.get(2));
		assertEquals(object3, set1.get(3));
		assertEquals(object4, set1.get(2));
	}

	@Test
	public void testAsSequence() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
		sequence1.add(object4);
	
		sequence2 = sequence1.asSequence();
	
		assertEquals(sequence2, sequence1);
	}

	@Test
	public void testAsSet() {
	
		OclSequence<String> sequence1;
		OclSet<String> set1;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
		sequence1.add(object4);
	
		set1 = sequence1.asSet();
	
		assertEquals(3, set1.size());
		assertTrue(set1.contains(object1));
		assertTrue(set1.contains(object2));
		assertTrue(set1.contains(object3));
		assertTrue(set1.contains(object4));
	}

	@Test
	public void testExcluding() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
	
		sequence2.add(object2);
		
		sequence1 = sequence1.excluding(object1);
	
		assertEquals(1, sequence1.size());
		assertTrue(sequence1.equals(sequence2));
	
		sequence1.add(object1);
		sequence1.add(object1);
	
		sequence1 = sequence1.excluding(object1);
	
		assertTrue(sequence1.equals(sequence2));
	}

	@Test
	public void testFirst() {
	
		OclSequence<String> sequence1;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		assertNull(sequence1.first());
	
		sequence1.add(object1);
		sequence1.add(object2);
	
		assertEquals(sequence1.first(), object1);
	}

	@Test
	public void testFlatten1() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
		OclSequence<?> sequence3;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
	
		sequence2.add(object1);
		sequence2.add(object2);
		
		sequence3 = sequence1.flatten();
		
		assertTrue(sequence3.contains(object1));
		assertTrue(sequence3.contains(object2));
		assertEquals(2, sequence3.size());
	
		assertTrue(sequence3.equals(sequence2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFlatten2() {
	
		OclSequence<OclSequence<String>> sequence1;
		OclSequence<String> sequence2;
		OclSequence<String> sequence3;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<OclSequence<String>>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		
		sequence2.add(object1);
		sequence2.add(object2);
		
		sequence1.add(sequence2);
		sequence1.add(sequence2);
		
		sequence3 = (OclSequence<String>) sequence1.flatten();
		
		assertTrue(sequence3.contains(object1));
		assertTrue(sequence3.contains(object2));
		
		assertEquals(2, sequence3.count(object1));
		assertEquals(2, sequence3.count(object2));
	}

	@Test
	public void testGet() {
	
		OclSequence<String> sequence1;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		try {
			sequence1.get(0);
			fail("An expected Exception was not thrown.");
		}
	
		catch (IndexOutOfBoundsException e) {
			// Expected
		}
	
		try {
			sequence1.get(1);
			fail("An expected Exception was not thrown.");
		}
	
		catch (IndexOutOfBoundsException e) {
			// Expected
		}
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object1);
	
		assertEquals(sequence1.get(1), object1);
		assertEquals(sequence1.get(2), object2);
		assertEquals(sequence1.get(3), object1);
	}

	@Test
	public void testIncluding() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		sequence1.add(object1);
		sequence1.add(object2);
	
		sequence2.add(object1);
		sequence2.add(object2);
		sequence2.add(object1);
		
		sequence1 = sequence1.including(object1);
	
		assertEquals(3, sequence1.size());
		assertTrue(sequence1.equals(sequence2));
	}

	@Test
	public void testIndexOf() {
	
		OclSequence<String> sequence1;
	
		String object1;
		String object2;
		String object3;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "1";
		
		assertEquals(0, sequence1.indexOf(object1));
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
	
		assertEquals(1, sequence1.indexOf(object1));
		assertEquals(2, sequence1.indexOf(object2));
		assertEquals(1, sequence1.indexOf(object3));
	}

	@Test
	public void testInsertAt() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
		String object3;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		
		sequence1.add(object1);
		sequence1.add(object2);
	
		sequence2.add(object1);
		sequence2.add(object3);
		sequence2.add(object2);
		
		sequence1 = sequence1.insertAt(2, object3);
	
		assertEquals(sequence2, sequence1);
	}

	@Test
	public void testLast() {
	
		OclSequence<String> sequence1;
	
		String object1;
		String object2;
	
		sequence1 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
	
		assertNull(sequence1.last());
	
		sequence1.add(object1);
		sequence1.add(object2);
	
		assertEquals(sequence1.last(), object2);
	}

	@Test
	public void testPrepend() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
	
		sequence2.add(object4);
		sequence2.add(object1);
		sequence2.add(object2);
		sequence2.add(object3);
	
		sequence1 = sequence1.prepend(object4);
	
		assertEquals(sequence1, sequence2);
	}

	@Test
	public void testSubSequence() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "4";
		
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object3);
		sequence1.add(object4);
	
		sequence2.add(object2);
		sequence2.add(object3);
		
		assertEquals(sequence1, sequence1.subSequence(1, 4));
		assertEquals(sequence2, sequence1.subSequence(2, 3));
	}

	@Test
	public void testUnion() {
	
		OclSequence<String> sequence1;
		OclSequence<String> sequence2;
		OclSequence<String> sequence3;
		OclSequence<String> sequence4;
	
		String object1;
		String object2;
		String object3;
	
		sequence1 = new OclSequence<String>();
		sequence2 = new OclSequence<String>();
		sequence3 = new OclSequence<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
	
		sequence1.add(object1);
		sequence1.add(object2);
		sequence1.add(object2);
	
		sequence2.add(object1);
		sequence2.add(object1);
		sequence2.add(object2);
		sequence2.add(object3);
	
		sequence3.add(object1);
		sequence3.add(object2);
		sequence3.add(object2);
		sequence3.add(object1);
		sequence3.add(object1);
		sequence3.add(object2);
		sequence3.add(object3);
	
		sequence4 = sequence1.union(sequence2);
	
		assertTrue(sequence3.equals(sequence4));
	
	}

}
