/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.InvalidException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclBags;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclBags}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclBagsTest {

	@Test
	public void testAsBag01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asBag(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.asBag(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asBag(col1).size());
		assertEquals(elem1, OclBags.asBag(col1).get(0));

		col1.add(elem1);
		assertEquals(2, OclBags.asBag(col1).size());

		col1.add(elem2);
		assertEquals(3, OclBags.asBag(col1).size());
	}

	@Test
	public void testAsBag02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asBag(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.asBag(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclBags.asBag(col1).size());
		assertEquals(elem1, OclBags.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclBags.asBag(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclBags.asBag(col1).size());
	}

	@Test
	public void testAsOrderedSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asOrderedSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asOrderedSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclBags.asOrderedSet(col1).size());
	}

	@Test
	public void testAsOrderedSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asOrderedSet(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.asOrderedSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclBags.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclBags.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclBags.asOrderedSet(col1).size());
	}

	@Test
	public void testAsSequence01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asSequence(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(2, OclBags.asSequence(col1).size());

		col1.add(elem2);
		assertEquals(3, OclBags.asSequence(col1).size());
	}

	@Test
	public void testAsSequence02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asSequence(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.asSequence(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclBags.asSequence(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclBags.asSequence(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclBags.asSequence(col1).size());
	}

	@Test
	public void testAsSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.asSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclBags.asSet(col1).size());
	}

	@Test
	public void testAsSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclBags.asSet(col1).size());

		col1 = new String[0];
		assertEquals(0, OclBags.asSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclBags.asSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclBags.asSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclBags.asSet(col1).size());
	}

	@Test
	public void testCount01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(new Integer(0), OclBags.count(col1, "1"));

		col1.add("1");
		assertEquals(new Integer(1), OclBags.count(col1, "1"));
		assertEquals(new Integer(0), OclBags.count(col1, "2"));
	}

	@Test
	public void testCount02() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclBags.count(col1, "1"));

		col1 = new String[] { "1" };
		assertEquals(new Integer(1), OclBags.count(col1, "1"));
		assertEquals(new Integer(0), OclBags.count(col1, "2"));
	}

	@Test
	public void testCount03() {

		List<String> col1 = null;

		assertEquals(new Integer(0), OclBags.count(col1, "1"));
	}

	@Test
	public void testCount04() {

		String[] col1 = null;

		assertEquals(new Integer(0), OclBags.count(col1, "1"));
	}

	@Test
	public void testCount05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(new Integer(0), OclBags.count(col1, null));
	}

	@Test
	public void testCount06() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclBags.count(col1, null));
	}

	@Test
	public void testEquals01() {

		List<String> col1 = null;
		List<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclBags.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclBags.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclBags.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclBags.equals(col1, col2));
	}

	@Test
	public void testEquals02() {

		String[] col1 = null;
		List<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclBags.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] { elem2 };
		assertFalse(OclBags.equals(col1, col2));
	}

	@Test
	public void testEquals03() {

		List<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclBags.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1 = null;
		col2 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclBags.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclBags.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclBags.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclBags.equals(col1, col2));
	}

	@Test
	public void testEquals04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = null;
		col1 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclBags.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclBags.equals(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclBags.equals(col1, col2));
	}

	@Test
	public void testExcluding01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(0, OclBags.excluding(col1, "1").size());

		col1.add("1");
		col1.add("1");
		assertEquals(0, OclBags.excluding(col1, "1").size());
		assertEquals(2, OclBags.excluding(col1, "2").size());
	}

	@Test
	public void testExcluding02() {

		String[] col1 = new String[] {};

		assertEquals(0, OclBags.excluding(col1, "1").size());

		col1 = new String[] { "1", "1" };
		assertEquals(0, OclBags.excluding(col1, "1").size());
		assertEquals(2, OclBags.excluding(col1, "2").size());
	}

	@Test(expected = InvalidException.class)
	public void testExcluding03() {

		List<String> col1 = null;

		OclBags.excluding(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testExcluding04() {

		String[] col1 = null;

		OclBags.excluding(col1, "1");
	}

	@Test
	public void testExcluding05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(0, OclBags.excluding(col1, null).size());
	}

	@Test
	public void testExcluding06() {

		String[] col1 = new String[] {};

		assertEquals(0, OclBags.excluding(col1, null).size());
	}

	@Test
	public void testFlatten01() {

		List<List<String>> col1 = null;
		List<String> elem1 = null;
		List<String> elem2 = null;

		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new ArrayList<List<String>>();
		assertEquals(0, OclBags.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.flatten(col1).size());

		col1.clear();
		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1.add(elem2);
		assertEquals(2, OclBags.flatten(col1).size());
	}

	@Test
	public void testFlatten02() {

		List<String[]> col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new ArrayList<String[]>();
		assertEquals(0, OclBags.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclBags.flatten(col1).size());

		col1.clear();
		elem2 = new String[] { "1", "2" };
		col1.add(elem2);
		assertEquals(2, OclBags.flatten(col1).size());
	}

	@Test
	public void testFlatten03() {

		List<?>[] col1 = null;
		List<String> elem1 = null;
		List<String> elem2 = null;

		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new List[0];
		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new List[] { elem1 };
		assertEquals(1, OclBags.flatten(col1).size());

		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1 = new List[] { elem2 };
		assertEquals(2, OclBags.flatten(col1).size());
	}

	@Test
	public void testFlatten04() {

		Object[] col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new Object[0];
		assertEquals(0, OclBags.flatten(col1).size());

		col1 = new Object[] { elem1 };
		assertEquals(1, OclBags.flatten(col1).size());

		elem2 = new String[] { "1", "2" };
		col1 = new Object[] { elem2 };
		assertEquals(2, OclBags.flatten(col1).size());
	}

	@Test
	public void testIncluding01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(1, OclBags.including(col1, "1").size());

		col1.add("1");
		assertEquals(2, OclBags.including(col1, "1").size());
		assertEquals(2, OclBags.including(col1, "2").size());
	}

	@Test
	public void testIncluding02() {

		String[] col1 = new String[] {};

		assertEquals(1, OclBags.including(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(2, OclBags.including(col1, "1").size());
		assertEquals(2, OclBags.including(col1, "2").size());
	}

	@Test(expected = InvalidException.class)
	public void testIncluding03() {

		List<String> col1 = null;

		OclBags.including(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIncluding04() {

		String[] col1 = null;

		OclBags.including(col1, "1");
	}

	@Test
	public void testIncluding05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(1, OclBags.including(col1, null).size());
	}

	@Test
	public void testIncluding06() {

		String[] col1 = new String[] {};

		assertEquals(1, OclBags.including(col1, null).size());
	}

	@Test
	public void testIntersection01() {

		List<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1.add("1");
		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection02() {

		String[] col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1" };
		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1", "2" };
		assertEquals(1, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection03() {

		List<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1.add("1");
		col2 = new String[] { "1", "1" };
		assertEquals(2, OclBags.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(2, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1" };
		col2 = new String[] { "1", "1" };
		assertEquals(2, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1", "2" };
		assertEquals(2, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection05() {

		List<String> col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1.add("1");
		col2.add("1");
		assertEquals(2, OclBags.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(2, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection06() {

		String[] col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclBags.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1" };
		col2.add("1");
		assertEquals(2, OclBags.intersection(col1, col2).size());

		col1 = new String[] { "1", "1", "2" };
		assertEquals(2, OclBags.intersection(col1, col2).size());
	}

	@Test
	public void testUnionWithBag01() {

		List<String> col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclBags.union(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclBags.union(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclBags.union(col1, col2).size());
	}

	@Test
	public void testUnionWithBag02() {

		String[] col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclBags.union(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclBags.union(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclBags.union(col1, col2).size());
	}

	@Test
	public void testUnionWithBag03() {

		List<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclBags.union(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclBags.union(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclBags.union(col1, col2).size());
	}

	@Test
	public void testUnionWithBag04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclBags.union(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclBags.union(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclBags.union(col1, col2).size());
	}

	@Test
	public void testUnionWithSet01() {

		List<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclBags.union(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclBags.union(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclBags.union(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclBags.union(col1, col2).size());
	}

}
