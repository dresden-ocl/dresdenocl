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
public class OclOrderedSetTest {

	@Test
	public void testAppend() {
	
		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";
	
		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
	
		set2.add(object1);
		set2.add(object2);
		set2.add(object3);
		set2.add(object4);
	
		set1 = set1.append(object4);
	
		assertEquals(set1, set2);
	}

	@Test
	public void testAsBag() {

		OclOrderedSet<String> set1;
		OclBag<String> bag1;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		bag1 = set1.asBag();

		assertEquals(4, bag1.size());
		assertTrue(bag1.contains(object1));
		assertTrue(bag1.contains(object2));
		assertTrue(bag1.contains(object3));
		assertTrue(bag1.contains(object4));
	}

	@Test
	public void testAsOrderedSet() {

		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		set2 = set1.asOrderedSet();

		assertTrue(set1.equals(set2));
	}

	@Test
	public void testAsSequence() {

		OclOrderedSet<String> set1;
		OclSequence<String> sequence1;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		sequence1 = set1.asSequence();

		for (int i = 1; i <= set1.size(); i++) {
			assertEquals(set1.get(1), sequence1.get(1));
		}
	}

	@Test
	public void testAsSet() {

		OclOrderedSet<String> set1;
		OclSet<String> set2;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		set2 = set1.asSet();

		assertEquals(4, set2.size());
		assertTrue(set2.contains(object1));
		assertTrue(set2.contains(object2));
		assertTrue(set2.contains(object3));
		assertTrue(set2.contains(object4));
	}

	@Test
	public void testFirst() {

		OclOrderedSet<String> set1;

		String object1;
		String object2;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";

		assertNull(set1.first());

		set1.add(object1);
		set1.add(object2);

		assertEquals(set1.first(), object1);
	}

	@Test
	public void testFlatten1() {

		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
		OclOrderedSet<?> set3;

		String object1;
		String object2;

		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";

		set1.add(object1);
		set1.add(object2);

		set2.add(object1);
		set2.add(object2);

		set3 = set1.flatten();

		assertTrue(set3.contains(object1));
		assertTrue(set3.contains(object2));
		assertEquals(2, set3.size());

		assertTrue(set3.equals(set2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFlatten2() {

		OclOrderedSet<OclBag<String>> set1;
		OclBag<String> bag1;
		OclOrderedSet<String> set2;

		String object1;
		String object2;

		set1 = new OclOrderedSet<OclBag<String>>();
		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag1.add(object1);
		bag1.add(object2);

		set1.add(bag1);

		set2 = (OclOrderedSet<String>) set1.flatten();

		assertTrue(set2.contains(object1));
		assertTrue(set2.contains(object2));

		assertEquals(1, set2.count(object1));
		assertEquals(1, set2.count(object2));
	}

	@Test
	public void testGet() {

		OclOrderedSet<String> set1;

		String object1;
		String object2;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";

		try {
			set1.get(0);
			fail("An expected Exception was not thrown.");
		}

		catch (IndexOutOfBoundsException e) {
			// Expected
		}

		try {
			set1.get(1);
			fail("An expected Exception was not thrown.");
		}

		catch (IndexOutOfBoundsException e) {
			// Expected
		}

		set1.add(object1);
		set1.add(object2);

		assertEquals(set1.get(1), object1);
		assertEquals(set1.get(2), object2);
	}

	@Test
	public void testIndexOf() {
	
		OclOrderedSet<String> set1;
	
		String object1;
		String object2;
	
		set1 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		
		assertEquals(0, set1.indexOf(object1));

		set1.add(object1);
		set1.add(object2);
	
		assertEquals(1, set1.indexOf(object1));
		assertEquals(2, set1.indexOf(object2));
	}

	@Test
	public void testInsertAt() {
	
		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
	
		String object1;
		String object2;
		String object3;
	
		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		
		set1.add(object1);
		set1.add(object2);

		set2.add(object1);
		set2.add(object3);
		set2.add(object2);
		
		set1 = set1.insertAt(2, object3);

		assertEquals(set2, set1);
	}

	@Test
	public void testLast() {

		OclOrderedSet<String> set1;

		String object1;
		String object2;

		set1 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";

		assertNull(set1.last());

		set1.add(object1);
		set1.add(object2);

		assertEquals(set1.last(), object2);
	}

	@Test
	public void testPrepend() {

		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);

		set2.add(object4);
		set2.add(object1);
		set2.add(object2);
		set2.add(object3);

		set1 = set1.prepend(object4);

		assertEquals(set1, set2);
	}

	@Test
	public void testSubOrderedSet() {
	
		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "4";
		
		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);
	
		set2.add(object2);
		set2.add(object3);
		
		assertEquals(set1, set1.subOrderedSet(1, 4));
		assertEquals(set2, set1.subOrderedSet(2, 3));
	}

	@Test
	public void testAdd() {
	
		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "2";
		
		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		set2.add(object1);
		set2.add(object2);
		set2.add(object3);

		assertEquals(set2, set1);
	}

	@Test
	public void testAddAll() {
	
		OclOrderedSet<String> set1;
		OclOrderedSet<String> set2;
		OclOrderedSet<String> set3;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclOrderedSet<String>();
		set2 = new OclOrderedSet<String>();
		set3 = new OclOrderedSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "4";
		
		set1.add(object1);
		set1.add(object2);
		set1.add(object4);
	
		set2.add(object1);
		set2.add(object2);
		set2.add(object3);

		set3.add(object1);
		set3.add(object2);
		set3.add(object4);
		set3.add(object3);
		
		set1.addAll(set2);

		assertEquals(set3, set1);
	}

}
