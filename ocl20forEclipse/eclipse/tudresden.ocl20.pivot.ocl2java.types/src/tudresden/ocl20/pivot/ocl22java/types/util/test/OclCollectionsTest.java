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
package tudresden.ocl20.pivot.ocl22java.types.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tudresden.ocl20.pivot.ocl22java.types.util.InvalidException;
import tudresden.ocl20.pivot.ocl22java.types.util.OclCollections;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclCollections}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclCollectionsTest {

	@Test
	public void testAsBag01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asBag(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.asBag(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));

		col1.add(elem1);
		assertEquals(2, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));
		assertEquals(elem1, OclCollections.asBag(col1).get(1));

		col1.add(elem2);
		assertEquals(3, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));
		assertEquals(elem1, OclCollections.asBag(col1).get(1));
		assertEquals(elem2, OclCollections.asBag(col1).get(2));
	}

	@Test
	public void testAsBag02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asBag(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclCollections.asBag(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));
		assertEquals(elem1, OclCollections.asBag(col1).get(1));

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclCollections.asBag(col1).size());
		assertEquals(elem1, OclCollections.asBag(col1).get(0));
		assertEquals(elem1, OclCollections.asBag(col1).get(1));
		assertEquals(elem2, OclCollections.asBag(col1).get(2));
	}

	@Test
	public void testAsOrderedSet01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asOrderedSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asOrderedSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclCollections.asOrderedSet(col1).size());
		assertEquals(elem1, OclCollections.asOrderedSet(col1).get(0));
		assertEquals(elem2, OclCollections.asOrderedSet(col1).get(1));
	}

	@Test
	public void testAsOrderedSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asOrderedSet(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclCollections.asOrderedSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclCollections.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclCollections.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclCollections.asOrderedSet(col1).size());
		assertEquals(elem1, OclCollections.asOrderedSet(col1).get(0));
		assertEquals(elem2, OclCollections.asOrderedSet(col1).get(1));
	}

	@Test
	public void testAsSequence01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asSequence(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));

		col1.add(elem1);
		assertEquals(2, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));
		assertEquals(elem1, OclCollections.asSequence(col1).get(1));

		col1.add(elem2);
		assertEquals(3, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));
		assertEquals(elem1, OclCollections.asSequence(col1).get(1));
		assertEquals(elem2, OclCollections.asSequence(col1).get(2));
	}

	@Test
	public void testAsSequence02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asSequence(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclCollections.asSequence(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));
		assertEquals(elem1, OclCollections.asSequence(col1).get(1));

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclCollections.asSequence(col1).size());
		assertEquals(elem1, OclCollections.asSequence(col1).get(0));
		assertEquals(elem1, OclCollections.asSequence(col1).get(1));
		assertEquals(elem2, OclCollections.asSequence(col1).get(2));
	}

	@Test
	public void testAsSet01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.asSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclCollections.asSet(col1).size());
	}

	@Test
	public void testAsSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclCollections.asSet(col1).size());

		col1 = new String[0];
		assertEquals(0, OclCollections.asSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclCollections.asSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclCollections.asSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclCollections.asSet(col1).size());
	}

	@Test
	public void testCount01() {

		Collection<String> col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		col1 = new ArrayList<String>();
		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		elem1 = "1";
		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		col1.add(elem1);
		assertEquals(new Integer(1), OclCollections.count(col1, elem1));
		assertEquals(new Integer(0), OclCollections.count(col1, elem2));
	}

	@Test
	public void testCount02() {

		String[] col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		col1 = new String[0];
		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		elem1 = "1";
		assertEquals(new Integer(0), OclCollections.count(col1, elem1));

		col1 = new String[] { elem1 };
		assertEquals(new Integer(1), OclCollections.count(col1, elem1));
		assertEquals(new Integer(0), OclCollections.count(col1, elem2));
	}

	@Test
	public void testEquals01() {

		Collection<String> col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclCollections.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclCollections.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclCollections.equals(col1, col2));
	}

	@Test
	public void testEquals02() {

		String[] col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclCollections.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] { elem2 };
		assertFalse(OclCollections.equals(col1, col2));
	}

	@Test
	public void testEquals03() {

		Collection<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1 = null;
		col2 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclCollections.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclCollections.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclCollections.equals(col1, col2));
	}

	@Test
	public void testEquals04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = null;
		col1 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclCollections.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclCollections.equals(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclCollections.equals(col1, col2));
	}

	@Test
	public void testExcludes01() {

		Collection<String> col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertTrue(OclCollections.excludes(col1, elem1));

		col1 = new ArrayList<String>();
		elem1 = "1";
		assertTrue(OclCollections.excludes(col1, elem1));

		col1.add(elem1);
		assertFalse(OclCollections.excludes(col1, elem1));
		assertTrue(OclCollections.excludes(col1, elem2));
	}

	@Test
	public void testExcludes02() {

		String[] col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertTrue(OclCollections.excludes(col1, elem1));

		col1 = new String[0];
		elem1 = "1";
		assertTrue(OclCollections.excludes(col1, elem1));

		col1 = new String[] { elem1 };
		assertFalse(OclCollections.excludes(col1, elem1));
		assertTrue(OclCollections.excludes(col1, elem2));
	}

	@Test(expected = InvalidException.class)
	public void testExcludes03() {

		Collection<String> col1 = null;
		String elem1 = null;

		col1 = new ArrayList<String>();
		OclCollections.excludes(col1, elem1);
	}

	@Test(expected = InvalidException.class)
	public void testExcludes04() {

		String[] col1 = null;
		String elem1 = null;

		col1 = new String[0];
		OclCollections.excludes(col1, elem1);
	}

	@Test
	public void testExcludesAll01() {

		Collection<String> col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.excludesAll(col1, col2));

		col2.add(elem2);
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1.add(elem2);
		assertFalse(OclCollections.excludesAll(col1, col2));
	}

	@Test
	public void testExcludesAll02() {

		Collection<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = null;
		col2 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.excludesAll(col1, col2));

		col2 = new String[] { elem2 };
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1.add(elem2);
		assertFalse(OclCollections.excludesAll(col1, col2));
	}

	@Test
	public void testExcludesAll03() {

		String[] col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.excludesAll(col1, col2));

		col2.add(elem2);
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclCollections.excludesAll(col1, col2));
	}

	@Test
	public void testExcludesAll04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = null;
		col2 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.excludesAll(col1, col2));

		col2 = new String[] { elem2 };
		assertTrue(OclCollections.excludesAll(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclCollections.excludesAll(col1, col2));
	}

	@Test
	public void testFlatten01() {

		Collection<Collection<String>> col1 = null;
		Collection<String> elem1 = null;
		Collection<String> elem2 = null;

		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new ArrayList<Collection<String>>();
		assertEquals(0, OclCollections.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.flatten(col1).size());

		col1.clear();
		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1.add(elem2);
		assertEquals(2, OclCollections.flatten(col1).size());
	}

	@Test
	public void testFlatten02() {

		Collection<String[]> col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new ArrayList<String[]>();
		assertEquals(0, OclCollections.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclCollections.flatten(col1).size());

		col1.clear();
		elem2 = new String[] { "1", "2" };
		col1.add(elem2);
		assertEquals(2, OclCollections.flatten(col1).size());
	}

	@Test
	public void testFlatten03() {

		Collection<?>[] col1 = null;
		Collection<String> elem1 = null;
		Collection<String> elem2 = null;

		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new Collection[0];
		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new Collection[] { elem1 };
		assertEquals(1, OclCollections.flatten(col1).size());

		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1 = new Collection[] { elem2 };
		assertEquals(2, OclCollections.flatten(col1).size());
	}

	@Test
	public void testFlatten04() {

		Object[] col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new Object[0];
		assertEquals(0, OclCollections.flatten(col1).size());

		col1 = new Object[] { elem1 };
		assertEquals(1, OclCollections.flatten(col1).size());

		elem2 = new String[] { "1", "2" };
		col1 = new Object[] { elem2 };
		assertEquals(2, OclCollections.flatten(col1).size());
	}

	@Test
	public void testIncludes01() {

		Collection<String> col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertFalse(OclCollections.includes(col1, elem1));

		col1 = new ArrayList<String>();
		elem1 = "1";
		assertFalse(OclCollections.includes(col1, elem1));

		col1.add(elem1);
		assertTrue(OclCollections.includes(col1, elem1));
		assertFalse(OclCollections.includes(col1, elem2));
	}

	@Test
	public void testIncludes02() {

		String[] col1 = null;
		String elem1 = null;
		String elem2 = "2";

		assertFalse(OclCollections.includes(col1, elem1));

		col1 = new String[0];
		elem1 = "1";
		assertFalse(OclCollections.includes(col1, elem1));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.includes(col1, elem1));
		assertFalse(OclCollections.includes(col1, elem2));
	}

	@Test(expected = InvalidException.class)
	public void testIncludes03() {

		Collection<String> col1 = null;
		String elem1 = null;

		col1 = new ArrayList<String>();
		OclCollections.includes(col1, elem1);
	}

	@Test(expected = InvalidException.class)
	public void testIncludes04() {

		String[] col1 = null;
		String elem1 = null;

		col1 = new String[0];
		OclCollections.includes(col1, elem1);
	}

	@Test
	public void testIncludesAll01() {

		Collection<String> col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.includesAll(col1, col2));

		col2.add(elem2);
		assertFalse(OclCollections.includesAll(col1, col2));

		col1.add(elem2);
		assertTrue(OclCollections.includesAll(col1, col2));
	}

	@Test
	public void testIncludesAll02() {

		Collection<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = null;
		col2 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.includesAll(col1, col2));

		col2 = new String[] { elem2 };
		assertFalse(OclCollections.includesAll(col1, col2));

		col1.add(elem2);
		assertTrue(OclCollections.includesAll(col1, col2));
	}

	@Test
	public void testIncludesAll03() {

		String[] col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.includesAll(col1, col2));

		col2.add(elem2);
		assertFalse(OclCollections.includesAll(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertTrue(OclCollections.includesAll(col1, col2));
	}

	@Test
	public void testIncludesAll04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = null;
		col2 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[0];
		assertTrue(OclCollections.includesAll(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.includesAll(col1, col2));

		col2 = new String[] { elem2 };
		assertFalse(OclCollections.includesAll(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertTrue(OclCollections.includesAll(col1, col2));
	}

	@Test
	public void testIsEmpty01() {

		Collection<String> col1 = null;
		String elem1 = "1";

		assertTrue(OclCollections.isEmpty(col1));

		col1 = new ArrayList<String>();
		assertTrue(OclCollections.isEmpty(col1));

		col1.add(elem1);
		assertFalse(OclCollections.isEmpty(col1));
	}

	@Test
	public void testIsEmpty02() {

		String[] col1 = null;
		String elem1 = "1";

		assertTrue(OclCollections.isEmpty(col1));

		col1 = new String[0];
		assertTrue(OclCollections.isEmpty(col1));

		col1 = new String[] { elem1 };
		assertFalse(OclCollections.isEmpty(col1));
	}

	@Test
	public void testMax01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertNull(OclCollections.max(col1));

		col1 = new ArrayList<String>();
		assertNull(OclCollections.max(col1));

		col1.add(elem1);
		assertEquals(elem1, OclCollections.max(col1));

		col1.add(elem2);
		assertEquals(elem2, OclCollections.max(col1));
	}

	@Test
	public void testMax02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertNull(OclCollections.max(col1));

		col1 = new String[0];
		assertNull(OclCollections.max(col1));

		col1 = new String[] { elem1 };
		assertEquals(elem1, OclCollections.max(col1));

		col1 = new String[] { elem1, elem2 };
		assertEquals(elem2, OclCollections.max(col1));
	}

	@Test
	public void testMin01() {

		Collection<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertNull(OclCollections.min(col1));

		col1 = new ArrayList<String>();
		assertNull(OclCollections.min(col1));

		col1.add(elem1);
		assertEquals(elem1, OclCollections.min(col1));

		col1.add(elem2);
		assertEquals(elem1, OclCollections.min(col1));
	}

	@Test
	public void testMin02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertNull(OclCollections.min(col1));

		col1 = new String[0];
		assertNull(OclCollections.min(col1));

		col1 = new String[] { elem1 };
		assertEquals(elem1, OclCollections.min(col1));

		col1 = new String[] { elem1, elem2 };
		assertEquals(elem1, OclCollections.min(col1));
	}

	@Test
	public void testNotEmpty01() {

		Collection<String> col1 = null;
		String elem1 = "1";

		assertFalse(OclCollections.notEmpty(col1));

		col1 = new ArrayList<String>();
		assertFalse(OclCollections.notEmpty(col1));

		col1.add(elem1);
		assertTrue(OclCollections.notEmpty(col1));
	}

	@Test
	public void testNotEmpty02() {

		String[] col1 = null;
		String elem1 = "1";

		assertFalse(OclCollections.notEmpty(col1));

		col1 = new String[0];
		assertFalse(OclCollections.notEmpty(col1));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.notEmpty(col1));
	}

	@Test
	public void testNotEquals01() {

		Collection<String> col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.notEquals(col1, col2));

		col2.add(elem1);
		assertFalse(OclCollections.notEquals(col1, col2));

		col1.add(elem2);
		assertTrue(OclCollections.notEquals(col1, col2));
	}

	@Test
	public void testNotEquals02() {

		String[] col1 = null;
		Collection<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.notEquals(col1, col2));

		col2.add(elem1);
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] { elem2 };
		assertTrue(OclCollections.notEquals(col1, col2));
	}

	@Test
	public void testNotEquals03() {

		Collection<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = null;
		col2 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new ArrayList<String>();
		assertFalse(OclCollections.notEquals(col1, col2));

		col1.add(elem1);
		assertTrue(OclCollections.notEquals(col1, col2));

		col2 = new String[] { elem1 };
		assertFalse(OclCollections.notEquals(col1, col2));

		col1.add(elem2);
		assertTrue(OclCollections.notEquals(col1, col2));
	}

	@Test
	public void testNotEquals04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = null;
		col1 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] {};
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] { elem1 };
		assertTrue(OclCollections.notEquals(col1, col2));

		col2 = new String[] { elem1 };
		assertFalse(OclCollections.notEquals(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertTrue(OclCollections.notEquals(col1, col2));
	}

	@Test
	public void testProduct01() {

		Collection<String> col1 = null;
		Collection<String> col2 = null;

		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1.add("A1");
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		col2.add("B1");
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1.add("A1");
		assertEquals(1, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple1;
		tuple1 = new HashMap<String, Object>();
		tuple1.put("first", "A1");
		tuple1.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));

		col1.add("A2");
		assertEquals(2, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple2;
		tuple2 = new HashMap<String, Object>();
		tuple2.put("first", "A2");
		tuple2.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));
		assertTrue(OclCollections.product(col1, col2).contains(tuple2));
	}

	@Test
	public void testProduct02() {

		String[] col1 = null;
		Collection<String> col2 = null;

		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[] { "A1" };
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[] {};
		col2.add("B1");
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[] { "A1" };
		assertEquals(1, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple1;
		tuple1 = new HashMap<String, Object>();
		tuple1.put("first", "A1");
		tuple1.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));

		col1 = new String[] { "A1", "A2" };
		assertEquals(2, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple2;
		tuple2 = new HashMap<String, Object>();
		tuple2.put("first", "A2");
		tuple2.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));
		assertTrue(OclCollections.product(col1, col2).contains(tuple2));
	}

	@Test
	public void testProduct03() {

		Collection<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = null;
		col2 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1.add("A1");
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new ArrayList<String>();
		col2 = new String[] { "B1" };
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1.add("A1");
		assertEquals(1, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple1;
		tuple1 = new HashMap<String, Object>();
		tuple1.put("first", "A1");
		tuple1.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));

		col1.add("A2");
		assertEquals(2, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple2;
		tuple2 = new HashMap<String, Object>();
		tuple2.put("first", "A2");
		tuple2.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));
		assertTrue(OclCollections.product(col1, col2).contains(tuple2));
	}

	@Test
	public void testProduct04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclCollections.product(col1, col2).size());

		col2 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = null;
		col2 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[0];
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[] { "A1" };
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[0];
		col2 = new String[] { "B1" };
		assertEquals(0, OclCollections.product(col1, col2).size());

		col1 = new String[] { "A1" };
		assertEquals(1, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple1;
		tuple1 = new HashMap<String, Object>();
		tuple1.put("first", "A1");
		tuple1.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));

		col1 = new String[] { "A1", "A2" };
		assertEquals(2, OclCollections.product(col1, col2).size());

		Map<String, Object> tuple2;
		tuple2 = new HashMap<String, Object>();
		tuple2.put("first", "A2");
		tuple2.put("second", "B1");

		assertTrue(OclCollections.product(col1, col2).contains(tuple1));
		assertTrue(OclCollections.product(col1, col2).contains(tuple2));
	}

	@Test
	public void testSize01() {

		Collection<String> col1 = null;
		String elem1 = "1";

		assertEquals(new Integer(0), OclCollections.size(col1));

		col1 = new ArrayList<String>();
		assertEquals(new Integer(0), OclCollections.size(col1));

		col1.add(elem1);
		assertEquals(new Integer(1), OclCollections.size(col1));

		col1.add(elem1);
		assertEquals(new Integer(2), OclCollections.size(col1));
	}

	@Test
	public void testSize02() {

		String[] col1 = null;
		String elem1 = "1";

		assertEquals(new Integer(0), OclCollections.size(col1));

		col1 = new String[0];
		assertEquals(new Integer(0), OclCollections.size(col1));

		col1 = new String[] { elem1 };
		assertEquals(new Integer(1), OclCollections.size(col1));

		col1 = new String[] { elem1, elem1 };
		assertEquals(new Integer(2), OclCollections.size(col1));
	}

	@Test
	public void testSum01() {

		Collection<Float> col1 = null;
		Float elem1 = new Float(1.0);
		Float elem2 = new Float(0.5);

		assertEquals(new Float(0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1 = new ArrayList<Float>();
		assertEquals(new Float(0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1.add(elem1);
		assertEquals(new Float(1.0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1.add(elem2);
		assertEquals(new Float(1.5), new Float(OclCollections.sum(col1)
				.doubleValue()));
	}

	@Test
	public void testSum02() {

		Collection<Integer> col1 = null;
		Integer elem1 = new Integer(1);
		Integer elem2 = new Integer(2);

		assertEquals(new Integer(0), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1 = new ArrayList<Integer>();
		assertEquals(new Integer(0), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1.add(elem1);
		assertEquals(new Integer(1), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1.add(elem2);
		assertEquals(new Integer(3), new Integer(OclCollections.sum(col1)
				.intValue()));
	}

	@Test
	public void testSum03() {

		Float[] col1 = null;
		Float elem1 = new Float(1.0);
		Float elem2 = new Float(0.5);

		assertEquals(new Float(0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1 = new Float[] {};
		assertEquals(new Float(0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1 = new Float[] { elem1 };
		assertEquals(new Float(1.0), new Float(OclCollections.sum(col1)
				.doubleValue()));

		col1 = new Float[] { elem1, elem2 };
		assertEquals(new Float(1.5), new Float(OclCollections.sum(col1)
				.doubleValue()));
	}

	@Test
	public void testSum04() {

		Integer[] col1 = null;
		Integer elem1 = new Integer(1);
		Integer elem2 = new Integer(2);

		assertEquals(new Integer(0), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1 = new Integer[] {};
		assertEquals(new Integer(0), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1 = new Integer[] { elem1 };
		assertEquals(new Integer(1), new Integer(OclCollections.sum(col1)
				.intValue()));

		col1 = new Integer[] { elem1, elem2 };
		assertEquals(new Integer(3), new Integer(OclCollections.sum(col1)
				.intValue()));
	}
}