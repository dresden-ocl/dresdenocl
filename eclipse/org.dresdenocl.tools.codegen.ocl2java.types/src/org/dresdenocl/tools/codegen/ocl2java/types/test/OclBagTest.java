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
package org.dresdenocl.tools.codegen.ocl2java.types.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.dresdenocl.tools.codegen.ocl2java.types.OclBag;
import org.dresdenocl.tools.codegen.ocl2java.types.OclOrderedSet;
import org.dresdenocl.tools.codegen.ocl2java.types.OclSequence;
import org.dresdenocl.tools.codegen.ocl2java.types.OclSet;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclBag}.
 * </p>
 * 
 * @author Claas Wilke
 */
@SuppressWarnings("deprecation")
public class OclBagTest {

	@Test
	public void testAsBag() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		String object1;
		String object2;

		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag1.add(object1);
		bag1.add(object2);

		bag2 = bag1.asBag();

		assertTrue(bag1.equals(bag2));
	}

	@Test
	public void testAsOrderedSet() {

		OclBag<String> bag1;
		OclOrderedSet<String> set1;
		String object1;
		String object2;

		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "1";

		bag1.add(object1);
		bag1.add(object2);

		set1 = bag1.asOrderedSet();

		assertTrue(set1.contains(object1));
		assertEquals(1, set1.count(object1));
		assertEquals(1, set1.size());
	}

	@Test
	public void testAsSequence() {

		OclBag<String> bag1;
		OclSequence<String> sequence1;
		String object1;
		String object2;

		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "1";

		bag1.add(object1);
		bag1.add(object2);

		sequence1 = bag1.asSequence();

		assertEquals(2, sequence1.size());
		assertEquals(2, sequence1.count(object1));
		assertEquals(2, sequence1.count(object2));
	}

	@Test
	public void testAsSet() {

		OclBag<String> bag1;
		OclSet<String> set1;
		String object1;
		String object2;

		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "1";

		bag1.add(object1);
		bag1.add(object2);

		set1 = bag1.asSet();

		assertTrue(set1.contains(object1));
		assertEquals(1, set1.count(object1));
		assertEquals(1, set1.size());
	}

	@Test
	public void testEquals() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		String object1;
		String object2;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();

		object1 = "1";
		object2 = "1";

		bag1.add(object1);
		bag1.add(object2);

		assertFalse(bag1.equals(bag2));

		bag2.add(object1);
		bag2.add(object1);

		assertTrue(bag1.equals(bag2));

		bag2.add(object1);
		bag2.add(object1);

		assertFalse(bag1.equals(bag2));
	}

	@Test
	public void testExcluding() {

		OclBag<String> bag1;
		OclBag<String> bag2;

		String object1;
		String object2;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag1.add(object1);
		bag1.add(object2);

		bag2.add(object2);

		bag1 = bag1.excluding(object1);

		assertEquals(1, bag1.size());
		assertTrue(bag1.equals(bag2));

		bag1.add(object1);
		bag1.add(object1);

		bag1 = bag1.excluding(object1);

		assertTrue(bag1.equals(bag2));
	}

	@Test
	public void testFlatten1() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		OclBag<?> bag3;

		String object1;
		String object2;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag1.add(object1);
		bag1.add(object2);

		bag2.add(object1);
		bag2.add(object2);

		bag3 = bag1.flatten();

		assertTrue(bag3.contains(object1));
		assertTrue(bag3.contains(object2));
		assertEquals(2, bag3.size());

		assertTrue(bag3.equals(bag2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFlatten2() {

		OclBag<OclBag<String>> bag1;
		OclBag<String> bag2;
		OclBag<String> bag3;

		String object1;
		String object2;

		bag1 = new OclBag<OclBag<String>>();
		bag2 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		bag2.add(object1);
		bag2.add(object2);

		bag1.add(bag2);
		bag1.add(bag2);

		bag3 = (OclBag<String>) bag1.flatten();

		assertTrue(bag3.contains(object1));
		assertTrue(bag3.contains(object2));

		assertEquals(2, bag3.count(object1));
		assertEquals(2, bag3.count(object2));
	}

	@Test
	public void testIncluding() {

		OclBag<String> bag1;
		OclBag<String> bag2;

		String object1;
		String object2;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();

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

		OclBag<String> bag1;
		OclBag<String> bag2;
		OclBag<String> bag3;
		OclBag<String> bag4;

		String object1;
		String object2;
		String object3;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();
		bag3 = new OclBag<String>();

		object1 = "1";
		object2 = "2";
		object3 = "3";

		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object2);

		bag2.add(object1);
		bag2.add(object1);
		bag2.add(object2);
		bag2.add(object3);

		bag3.add(object1);
		bag3.add(object2);

		bag4 = bag1.intersection(bag2);

		assertTrue(bag3.equals(bag4));

	}

	@Test
	public void testIntersectionWithSet() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		OclBag<String> bag3;
		OclSet<String> set1;

		String object1;
		String object2;
		String object3;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();
		set1 = new OclSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "3";

		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object2);

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);

		bag2.add(object1);
		bag2.add(object2);

		bag3 = bag1.intersection(set1);

		assertTrue(bag3.equals(bag2));

	}

	@Test
	public void testUnionWithBag() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		OclBag<String> bag3;
		OclBag<String> bag4;

		String object1;
		String object2;
		String object3;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();
		bag3 = new OclBag<String>();

		object1 = "1";
		object2 = "2";
		object3 = "3";

		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object2);

		bag2.add(object1);
		bag2.add(object1);
		bag2.add(object2);
		bag2.add(object3);

		bag3.add(object1);
		bag3.add(object1);
		bag3.add(object1);
		bag3.add(object2);
		bag3.add(object2);
		bag3.add(object2);
		bag3.add(object3);

		bag4 = bag1.union(bag2);

		assertTrue(bag3.equals(bag4));

	}

	@Test
	public void testUnionWithSet() {

		OclBag<String> bag1;
		OclBag<String> bag2;
		OclBag<String> bag3;
		OclSet<String> set1;

		String object1;
		String object2;
		String object3;

		bag1 = new OclBag<String>();
		bag2 = new OclBag<String>();
		set1 = new OclSet<String>();

		object1 = "1";
		object2 = "2";
		object3 = "3";

		bag1.add(object1);
		bag1.add(object2);
		bag1.add(object2);

		set1.add(object1);
		set1.add(object2);
		set1.add(object3);

		bag2.add(object1);
		bag2.add(object1);
		bag2.add(object2);
		bag2.add(object2);
		bag2.add(object2);
		bag2.add(object3);

		bag3 = bag1.union(set1);

		assertTrue(bag3.equals(bag2));
	}

}
