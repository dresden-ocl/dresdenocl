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
package org.dresdenocl.tools.codegen.ocl2java.types.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import org.dresdenocl.tools.codegen.ocl2java.types.OclInvalidException;
import org.dresdenocl.tools.codegen.ocl2java.types.util.OclSets;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclSets}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclSetsTest {

	@Test
	public void testAsBag01() {

		Set<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asBag(col1).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.asBag(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asBag(col1).size());
		assertEquals(elem1, OclSets.asBag(col1).get(0));

		col1.add(elem1);
		assertEquals(1, OclSets.asBag(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSets.asBag(col1).size());
	}

	@Test
	public void testAsBag02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asBag(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.asBag(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSets.asBag(col1).size());
		assertEquals(elem1, OclSets.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSets.asBag(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSets.asBag(col1).size());
	}

	@Test
	public void testAsOrderedSet01() {

		Set<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asOrderedSet(col1).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asOrderedSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSets.asOrderedSet(col1).size());
	}

	@Test
	public void testAsOrderedSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asOrderedSet(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSets.asOrderedSet(col1).size());
	}

	@Test
	public void testAsSequence01() {

		Set<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asSequence(col1).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asSequence(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSets.asSequence(col1).size());
	}

	@Test
	public void testAsSequence02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asSequence(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.asSequence(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSets.asSequence(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSets.asSequence(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSets.asSequence(col1).size());
	}

	@Test
	public void testAsSet01() {

		Set<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asSet(col1).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.asSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSets.asSet(col1).size());
	}

	@Test
	public void testAsSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSets.asSet(col1).size());

		col1 = new String[0];
		assertEquals(0, OclSets.asSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSets.asSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSets.asSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSets.asSet(col1).size());
	}

	@Test
	public void testCount01() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(new Integer(0), OclSets.count(col1, "1"));

		col1.add("1");
		assertEquals(new Integer(1), OclSets.count(col1, "1"));
		assertEquals(new Integer(0), OclSets.count(col1, "2"));
	}

	@Test
	public void testCount02() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclSets.count(col1, "1"));

		col1 = new String[] { "1" };
		assertEquals(new Integer(1), OclSets.count(col1, "1"));
		assertEquals(new Integer(0), OclSets.count(col1, "2"));
	}

	@Test
	public void testCount03() {

		Set<String> col1 = null;

		assertEquals(new Integer(0), OclSets.count(col1, "1"));
	}

	@Test
	public void testCount04() {

		String[] col1 = null;

		assertEquals(new Integer(0), OclSets.count(col1, "1"));
	}

	@Test
	public void testCount05() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(new Integer(0), OclSets.count(col1, null));
	}

	@Test
	public void testCount06() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclSets.count(col1, null));
	}

	@Test
	public void testEquals01() {

		Set<String> col1 = null;
		Set<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSets.equals(col1, col2));

		col1 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1 = null;
		col2 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclSets.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclSets.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclSets.equals(col1, col2));
	}

	@Test
	public void testEquals02() {

		String[] col1 = null;
		Set<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = null;
		col2 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclSets.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] { elem2 };
		assertFalse(OclSets.equals(col1, col2));
	}

	@Test
	public void testEquals03() {

		Set<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSets.equals(col1, col2));

		col1 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1 = null;
		col2 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = new HashSet<String>();
		assertTrue(OclSets.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclSets.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclSets.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclSets.equals(col1, col2));
	}

	@Test
	public void testEquals04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = null;
		col1 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclSets.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclSets.equals(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclSets.equals(col1, col2));
	}

	@Test
	public void testExcluding01() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(0, OclSets.excluding(col1, "1").size());

		col1.add("1");
		assertEquals(0, OclSets.excluding(col1, "1").size());
		assertEquals(1, OclSets.excluding(col1, "2").size());
	}

	@Test
	public void testExcluding02() {

		String[] col1 = new String[] {};

		assertEquals(0, OclSets.excluding(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(0, OclSets.excluding(col1, "1").size());
		assertEquals(1, OclSets.excluding(col1, "2").size());
	}

	@Test(expected = OclInvalidException.class)
	public void testExcluding03() {

		Set<String> col1 = null;

		OclSets.excluding(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testExcluding04() {

		String[] col1 = null;

		OclSets.excluding(col1, "1");
	}

	@Test
	public void testExcluding05() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(0, OclSets.excluding(col1, null).size());
	}

	@Test
	public void testExcluding06() {

		String[] col1 = new String[] {};

		assertEquals(0, OclSets.excluding(col1, null).size());
	}

	@Test
	public void testFlatten01() {

		Set<Collection<String>> col1 = null;
		Collection<String> elem1 = null;
		Collection<String> elem2 = null;

		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new HashSet<Collection<String>>();
		assertEquals(0, OclSets.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.flatten(col1).size());

		col1.clear();
		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1.add(elem2);
		assertEquals(2, OclSets.flatten(col1).size());
	}

	@Test
	public void testFlatten02() {

		Set<String[]> col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new HashSet<String[]>();
		assertEquals(0, OclSets.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSets.flatten(col1).size());

		col1.clear();
		elem2 = new String[] { "1", "2" };
		col1.add(elem2);
		assertEquals(2, OclSets.flatten(col1).size());
	}

	@Test
	public void testFlatten03() {

		Set<?>[] col1 = null;
		Set<String> elem1 = null;
		Set<String> elem2 = null;

		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new Set[0];
		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new Set[] { elem1 };
		assertEquals(1, OclSets.flatten(col1).size());

		elem2 = new HashSet<String>();
		elem2.add("1");
		elem2.add("2");
		col1 = new Set[] { elem2 };
		assertEquals(2, OclSets.flatten(col1).size());
	}

	@Test
	public void testFlatten04() {

		Object[] col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new Object[0];
		assertEquals(0, OclSets.flatten(col1).size());

		col1 = new Object[] { elem1 };
		assertEquals(1, OclSets.flatten(col1).size());

		elem2 = new String[] { "1", "2" };
		col1 = new Object[] { elem2 };
		assertEquals(2, OclSets.flatten(col1).size());
	}

	@Test
	public void testIncluding01() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(1, OclSets.including(col1, "1").size());

		col1.add("1");
		assertEquals(1, OclSets.including(col1, "1").size());
		assertEquals(2, OclSets.including(col1, "2").size());
	}

	@Test
	public void testIncluding02() {

		String[] col1 = new String[] {};

		assertEquals(1, OclSets.including(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.including(col1, "1").size());
		assertEquals(2, OclSets.including(col1, "2").size());
	}

	@Test(expected = OclInvalidException.class)
	public void testIncluding03() {

		Set<String> col1 = null;

		OclSets.including(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIncluding04() {

		String[] col1 = null;

		OclSets.including(col1, "1");
	}

	@Test
	public void testIncluding05() {

		Set<String> col1 = new HashSet<String>();

		assertEquals(1, OclSets.including(col1, null).size());
	}

	@Test
	public void testIncluding06() {

		String[] col1 = new String[] {};

		assertEquals(1, OclSets.including(col1, null).size());
	}

	@Test
	public void testIntersection01() {

		Set<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection02() {

		String[] col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection03() {

		Set<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection05() {

		Set<String> col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1.add("1");
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testIntersection06() {

		String[] col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(0, OclSets.intersection(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.intersection(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.intersection(col1, col2).size());
	}

	@Test
	public void testMinus01() {

		Set<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.minus(col1, col2).size());

		col2.add("1");
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.minus(col1, col2).size());
	}

	@Test
	public void testMinus02() {

		String[] col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.minus(col1, col2).size());

		col2.add("1");
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.minus(col1, col2).size());
	}

	@Test
	public void testMinus03() {

		Set<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.minus(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.minus(col1, col2).size());
	}

	@Test
	public void testMinus04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.minus(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(0, OclSets.minus(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.minus(col1, col2).size());
	}

	@Test
	public void testSymmetricDifference01() {

		Set<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("1"));

		col2.add("1");
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));

		col2.add("3");
		assertEquals(2, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("3"));
	}

	@Test
	public void testSymmetricDifference02() {

		String[] col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("1"));

		col2.add("1");
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));

		col2.add("3");
		assertEquals(2, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("3"));
	}

	@Test
	public void testSymmetricDifference03() {

		Set<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("1"));

		col2 = new String[] { "1" };
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1.add("2");
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));

		col2 = new String[] { "1", "3" };
		assertEquals(2, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("3"));
	}

	@Test
	public void testSymmetricDifference04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("1"));

		col2 = new String[] { "1" };
		assertEquals(0, OclSets.symmetricDifference(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(1, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));

		col2 = new String[] { "1", "3" };
		assertEquals(2, OclSets.symmetricDifference(col1, col2).size());
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("2"));
		assertTrue(OclSets.symmetricDifference(col1, col2).contains("3"));
	}

	@Test
	public void testUnionWithBag01() {

		Set<String> col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.unionWithBag(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclSets.unionWithBag(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclSets.unionWithBag(col1, col2).size());
	}

	@Test
	public void testUnionWithBag02() {

		String[] col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithBag(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclSets.unionWithBag(col1, col2).size());
	}

	@Test
	public void testUnionWithBag03() {

		Set<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.unionWithBag(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclSets.unionWithBag(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclSets.unionWithBag(col1, col2).size());
	}

	@Test
	public void testUnionWithBag04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithBag(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclSets.unionWithBag(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclSets.unionWithBag(col1, col2).size());
	}

	@Test
	public void testUnionWithSet01() {

		Set<String> col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col1.add("2");
		assertEquals(2, OclSets.unionWithSet(col1, col2).size());
	}

	@Test
	public void testUnionWithSet02() {

		String[] col1 = null;
		Set<String> col2 = null;

		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = null;
		col2 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col2.add("1");
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclSets.unionWithSet(col1, col2).size());
	}

	@Test
	public void testUnionWithSet03() {

		Set<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new HashSet<String>();
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col1.add("2");
		assertEquals(2, OclSets.unionWithSet(col1, col2).size());
	}

	@Test
	public void testUnionWithSet04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(1, OclSets.unionWithSet(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclSets.unionWithSet(col1, col2).size());
	}
}