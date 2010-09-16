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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclBag;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclOrderedSet;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclSequence;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclSet;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclSet}.
 * </p>
 * 
 * @author Claas Wilke
 */
@SuppressWarnings("deprecation")
public class OclSetTest {
	
	@Test
	public void testAsBag() {

		OclSet<String> set1;
		OclBag<String> bag1;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclSet<String>();

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

		OclSet<String> set1;
		OclOrderedSet<String> set2;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		set2 = set1.asOrderedSet();

		assertEquals(4, set2.size());
		assertTrue(set2.contains(object1));
		assertTrue(set2.contains(object2));
		assertTrue(set2.contains(object3));
		assertTrue(set2.contains(object4));
	}

	@Test
	public void testAsSequence() {

		OclSet<String> set1;
		OclSequence<String> sequence1;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclSet<String>();

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
			assertTrue(set1.contains(sequence1.get(1)));
		}
	}

	@Test
	public void testAsSet() {

		OclSet<String> set1;
		OclSet<String> set2;

		String object1;
		String object2;
		String object3;
		String object4;

		set1 = new OclSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "4";
		object4 = "3";

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
		set1.add(object4);

		set2 = set1.asSet();

		assertEquals(set2, set1);
	}

	@Test
	public void testExcluding() {
	
		OclSet<String> set1;
		OclSet<String> set2;

		String object1;
		String object2;
	
		set1 = new OclSet<String>();
		set2 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
	
		set1.add(object1);
		set1.add(object2);
	
		set2.add(object2);
		
		set1 = set1.excluding(object1);
	
		assertEquals(1, set1.size());
		assertTrue(set1.equals(set2));
	
		set1.add(object1);
		set1.add(object1);
	
		set1 = set1.excluding(object1);

		assertTrue(set1.equals(set2));
	}

	@Test
	public void testFlatten1() {

		OclSet<String> set1;
		OclSet<String> set2;
		OclSet<?> set3;

		String object1;
		String object2;

		set1 = new OclSet<String>();
		set2 = new OclSet<String>();

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

		OclSet<OclBag<String>> set1;
		OclBag<String> bag1;
		OclSet<String> set2;

		String object1;
		String object2;

		set1 = new OclSet<OclBag<String>>();
		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag1.add(object1);
		bag1.add(object2);

		set1.add(bag1);

		set2 = (OclSet<String>) set1.flatten();

		assertTrue(set2.contains(object1));
		assertTrue(set2.contains(object2));

		assertEquals(1, set2.count(object1));
		assertEquals(1, set2.count(object2));
	}

	@Test
	public void testIncluding() {
	
		OclSet<String> bag1;
		OclSet<String> bag2;
	
		String object1;
		String object2;
	
		bag1 = new OclSet<String>();
		bag2 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
	
		bag1.add(object2);
	
		bag2.add(object1);
		bag2.add(object2);
		
		bag1 = bag1.including(object1);
	
		assertEquals(2, bag1.size());
		assertTrue(bag1.equals(bag2));
	
	}

	@Test
	public void testIntersectionWithBag() {
	
		OclSet<String> set1;
		OclBag<String> bag1;
		OclSet<String> set2;
		OclSet<String> set3;
	
		String object1;
		String object2;
		String object3;
	
		set1 = new OclSet<String>();
		bag1 = new OclBag<String>();
		set2 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
	
		set1.add(object1);
		set1.add(object2);
	
		bag1.add(object1);
		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object3);

		set2.add(object1);
		set2.add(object2);

		set3 = set1.intersection(bag1);
	
		assertTrue(set2.equals(set3));
	
	}

	@Test
	public void testIntersectionWithSet() {
	
		OclSet<String> set1;
		OclSet<String> set2;
		OclSet<String> set3;
		OclSet<String> set4;
	
		String object1;
		String object2;
		String object3;
	
		set1 = new OclSet<String>();
		set2 = new OclSet<String>();
		set4 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
	
		set1.add(object1);
		set1.add(object2);
	
		set4.add(object1);
		set4.add(object2);
		set4.add(object3);
	
		set2.add(object1);
		set2.add(object2);
	
		set3 = set1.intersection(set4);
	
		assertTrue(set3.equals(set2));
	
	}

	@Test
	public void testMinus() {
	
		OclSet<String> set1;
		OclSet<String> set2;
		OclSet<String> set3;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclSet<String>();
		set2 = new OclSet<String>();
		set3 = new OclSet<String>();
	
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
	
		set3.add(object1);
		set3.add(object4);
		
		assertEquals(set3, set1.minus(set2));
	
	}

	@Test
	public void testSymmetricDifference() {
	
		OclSet<String> set1;
		OclSet<String> set2;
		OclSet<String> set3;
	
		String object1;
		String object2;
		String object3;
		String object4;
	
		set1 = new OclSet<String>();
		set2 = new OclSet<String>();
		set3 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
		object4 = "4";
	
		set1.add(object1);
		set1.add(object2);
		set1.add(object3);
	
		set2.add(object2);
		set2.add(object3);
		set2.add(object4);
	
		set3.add(object1);
		set3.add(object4);
		
		assertEquals(set3, set1.symmetricDifference(set2));	
	}

	@Test
	public void testUnionWithBag() {
	
		OclSet<String> set1;
		OclBag<String> bag1;
		OclSet<String> set2;
		OclSet<String> set3;
	
		String object1;
		String object2;
		String object3;
	
		set1 = new OclSet<String>();
		bag1 = new OclBag<String>();
		set2 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
	
		set1.add(object1);
		set1.add(object2);
	
		bag1.add(object1);
		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object3);
	
		set2.add(object1);
		set2.add(object2);
		set2.add(object3);
	
		set3 = set1.union(bag1);
	
		assertEquals(set2, set3);
	
	}

	@Test
	public void testUnionWithSet() {
	
		OclSet<String> set1;
		OclSet<String> set2;
		OclSet<String> set3;
		OclSet<String> set4;
	
		String object1;
		String object2;
		String object3;
	
		set1 = new OclSet<String>();
		set2 = new OclSet<String>();
		set4 = new OclSet<String>();
	
		object1 = "1";
		object2 = "2";
		object3 = "3";
	
		set1.add(object1);
		set1.add(object2);
		set1.add(object2);
	
		set4.add(object1);
		set4.add(object2);
		set4.add(object3);
	
		set2.add(object1);
		set2.add(object2);
		set2.add(object3);

		set3 = set1.union(set4);
	
		assertTrue(set3.equals(set2));
	}

}
