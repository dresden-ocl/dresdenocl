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
import java.util.List;

import org.junit.Test;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.InvalidException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util.OclSequences;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclSequences}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclSequencesTest {

	@Test
	public void testAppend01() {

		List<String> col1 = null;

		assertEquals(1, OclSequences.append(col1, null).size());
		assertEquals(1, OclSequences.append(col1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclSequences.append(col1, null).size());
		assertEquals(1, OclSequences.append(col1, "1").size());

		col1.add("1");
		assertEquals(2, OclSequences.append(col1, "1").size());

		assertEquals(2, OclSequences.append(col1, "2").size());
		assertEquals("1", OclSequences.append(col1, "2").get(0));
		assertEquals("2", OclSequences.append(col1, "2").get(1));
	}

	@Test
	public void testAppend02() {

		String[] col1 = null;

		assertEquals(1, OclSequences.append(col1, null).size());
		assertEquals(1, OclSequences.append(col1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclSequences.append(col1, null).size());
		assertEquals(1, OclSequences.append(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(2, OclSequences.append(col1, "1").size());

		assertEquals(2, OclSequences.append(col1, "2").size());
		assertEquals("1", OclSequences.append(col1, "2").get(0));
		assertEquals("2", OclSequences.append(col1, "2").get(1));
	}

	@Test
	public void testAsBag01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asBag(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.asBag(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asBag(col1).size());
		assertEquals(elem1, OclSequences.asBag(col1).get(0));

		col1.add(elem1);
		assertEquals(2, OclSequences.asBag(col1).size());

		col1.add(elem2);
		assertEquals(3, OclSequences.asBag(col1).size());
	}

	@Test
	public void testAsBag02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asBag(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.asBag(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSequences.asBag(col1).size());
		assertEquals(elem1, OclSequences.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclSequences.asBag(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclSequences.asBag(col1).size());
	}

	@Test
	public void testAsOrderedSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asOrderedSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asOrderedSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSequences.asOrderedSet(col1).size());
	}

	@Test
	public void testAsOrderedSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asOrderedSet(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.asOrderedSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSequences.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSequences.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSequences.asOrderedSet(col1).size());
	}

	@Test
	public void testAsSequence01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asSequence(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(2, OclSequences.asSequence(col1).size());

		col1.add(elem2);
		assertEquals(3, OclSequences.asSequence(col1).size());
	}

	@Test
	public void testAsSequence02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asSequence(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.asSequence(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSequences.asSequence(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(2, OclSequences.asSequence(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(3, OclSequences.asSequence(col1).size());
	}

	@Test
	public void testAsSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.asSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclSequences.asSet(col1).size());
	}

	@Test
	public void testAsSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclSequences.asSet(col1).size());

		col1 = new String[0];
		assertEquals(0, OclSequences.asSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclSequences.asSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclSequences.asSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclSequences.asSet(col1).size());
	}

	@Test
	public void testAt01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");

		assertEquals("1", OclSequences.at(col1, 1));

		col1.add("2");
		assertEquals("2", OclSequences.at(col1, 2));
	}

	@Test
	public void testAt02() {

		String[] col1 = new String[] { "1" };

		assertEquals("1", OclSequences.at(col1, 1));

		col1 = new String[] { "1", "2" };
		assertEquals("2", OclSequences.at(col1, 2));
	}

	@Test(expected = InvalidException.class)
	public void testAt03() {

		List<String> col1 = new ArrayList<String>();
		OclSequences.at(col1, 1);
	}

	@Test(expected = InvalidException.class)
	public void testAt04() {

		String[] col1 = new String[] {};
		OclSequences.at(col1, 1);
	}

	@Test(expected = InvalidException.class)
	public void testAt05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclSequences.at(col1, 0);
	}

	@Test(expected = InvalidException.class)
	public void testAt06() {

		String[] col1 = new String[] { "1" };
		OclSequences.at(col1, 0);
	}

	@Test(expected = InvalidException.class)
	public void testAt07() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclSequences.at(col1, 2);
	}

	@Test(expected = InvalidException.class)
	public void testAt08() {

		String[] col1 = new String[] { "1" };
		OclSequences.at(col1, 2);
	}

	@Test
	public void testCount01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(new Integer(0), OclSequences.count(col1, "1"));

		col1.add("1");
		assertEquals(new Integer(1), OclSequences.count(col1, "1"));
		assertEquals(new Integer(0), OclSequences.count(col1, "2"));
	}

	@Test
	public void testCount02() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclSequences.count(col1, "1"));

		col1 = new String[] { "1" };
		assertEquals(new Integer(1), OclSequences.count(col1, "1"));
		assertEquals(new Integer(0), OclSequences.count(col1, "2"));
	}

	@Test
	public void testCount03() {

		List<String> col1 = null;

		assertEquals(new Integer(0), OclSequences.count(col1, "1"));
	}

	@Test
	public void testCount04() {

		String[] col1 = null;

		assertEquals(new Integer(0), OclSequences.count(col1, "1"));
	}

	@Test
	public void testCount05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(new Integer(0), OclSequences.count(col1, null));
	}

	@Test
	public void testCount06() {

		String[] col1 = new String[] {};

		assertEquals(new Integer(0), OclSequences.count(col1, null));
	}

	@Test
	public void testEquals01() {

		List<String> col1 = null;
		List<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSequences.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclSequences.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclSequences.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclSequences.equals(col1, col2));
	}

	@Test
	public void testEquals02() {

		String[] col1 = null;
		List<String> col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = null;
		col2 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclSequences.equals(col1, col2));

		col2.add(elem1);
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] { elem2 };
		assertFalse(OclSequences.equals(col1, col2));
	}

	@Test
	public void testEquals03() {

		List<String> col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSequences.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1 = null;
		col2 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new ArrayList<String>();
		assertTrue(OclSequences.equals(col1, col2));

		col1.add(elem1);
		assertFalse(OclSequences.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclSequences.equals(col1, col2));

		col1.add(elem2);
		assertFalse(OclSequences.equals(col1, col2));
	}

	@Test
	public void testEquals04() {

		String[] col1 = null;
		String[] col2 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = null;
		col1 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] {};
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] { elem1 };
		assertFalse(OclSequences.equals(col1, col2));

		col2 = new String[] { elem1 };
		assertTrue(OclSequences.equals(col1, col2));

		col1 = new String[] { elem1, elem2 };
		assertFalse(OclSequences.equals(col1, col2));
	}

	@Test
	public void testExcluding01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(0, OclSequences.excluding(col1, "1").size());

		col1.add("1");
		col1.add("1");
		assertEquals(0, OclSequences.excluding(col1, "1").size());
		assertEquals(2, OclSequences.excluding(col1, "2").size());
	}

	@Test
	public void testExcluding02() {

		String[] col1 = new String[] {};

		assertEquals(0, OclSequences.excluding(col1, "1").size());

		col1 = new String[] { "1", "1" };
		assertEquals(0, OclSequences.excluding(col1, "1").size());
		assertEquals(2, OclSequences.excluding(col1, "2").size());
	}

	@Test(expected = InvalidException.class)
	public void testExcluding03() {

		List<String> col1 = null;

		OclSequences.excluding(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testExcluding04() {

		String[] col1 = null;

		OclSequences.excluding(col1, "1");
	}

	@Test
	public void testExcluding05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(0, OclSequences.excluding(col1, null).size());
	}

	@Test
	public void testExcluding06() {

		String[] col1 = new String[] {};

		assertEquals(0, OclSequences.excluding(col1, null).size());
	}

	@Test
	public void testFirst01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");

		assertEquals("1", OclSequences.first(col1));
	}

	@Test
	public void testFirst02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals("1", OclSequences.first(col1));
	}

	@Test(expected = InvalidException.class)
	public void testFirst03() {

		List<String> col1 = new ArrayList<String>();

		OclSequences.first(col1);
	}

	@Test(expected = InvalidException.class)
	public void testFirst04() {

		String[] col1 = new String[] {};

		OclSequences.first(col1);
	}

	@Test(expected = InvalidException.class)
	public void testFirst05() {

		String[] col1 = null;

		OclSequences.first(col1);
	}

	@Test(expected = InvalidException.class)
	public void testFirst06() {

		List<String> col1 = null;

		OclSequences.first(col1);
	}

	@Test
	public void testFlatten01() {

		List<List<String>> col1 = null;
		List<String> elem1 = null;
		List<String> elem2 = null;

		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new ArrayList<List<String>>();
		assertEquals(0, OclSequences.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.flatten(col1).size());

		col1.clear();
		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1.add(elem2);
		assertEquals(2, OclSequences.flatten(col1).size());
	}

	@Test
	public void testFlatten02() {

		List<String[]> col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new ArrayList<String[]>();
		assertEquals(0, OclSequences.flatten(col1).size());

		col1.add(elem1);
		assertEquals(1, OclSequences.flatten(col1).size());

		col1.clear();
		elem2 = new String[] { "1", "2" };
		col1.add(elem2);
		assertEquals(2, OclSequences.flatten(col1).size());
	}

	@Test
	public void testFlatten03() {

		List<?>[] col1 = null;
		List<String> elem1 = null;
		List<String> elem2 = null;

		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new List[0];
		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new List[] { elem1 };
		assertEquals(1, OclSequences.flatten(col1).size());

		elem2 = new ArrayList<String>();
		elem2.add("1");
		elem2.add("2");
		col1 = new List[] { elem2 };
		assertEquals(2, OclSequences.flatten(col1).size());
	}

	@Test
	public void testFlatten04() {

		Object[] col1 = null;
		String[] elem1 = null;
		String[] elem2 = null;

		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new Object[0];
		assertEquals(0, OclSequences.flatten(col1).size());

		col1 = new Object[] { elem1 };
		assertEquals(1, OclSequences.flatten(col1).size());

		elem2 = new String[] { "1", "2" };
		col1 = new Object[] { elem2 };
		assertEquals(2, OclSequences.flatten(col1).size());
	}

	@Test
	public void testIncluding01() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(1, OclSequences.including(col1, "1").size());

		col1.add("1");
		assertEquals(2, OclSequences.including(col1, "1").size());
		assertEquals(2, OclSequences.including(col1, "2").size());
	}

	@Test
	public void testIncluding02() {

		String[] col1 = new String[] {};

		assertEquals(1, OclSequences.including(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(2, OclSequences.including(col1, "1").size());
		assertEquals(2, OclSequences.including(col1, "2").size());
	}

	@Test(expected = InvalidException.class)
	public void testIncluding03() {

		List<String> col1 = null;

		OclSequences.including(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIncluding04() {

		String[] col1 = null;

		OclSequences.including(col1, "1");
	}

	@Test
	public void testIncluding05() {

		List<String> col1 = new ArrayList<String>();

		assertEquals(1, OclSequences.including(col1, null).size());
	}

	@Test
	public void testIncluding06() {

		String[] col1 = new String[] {};

		assertEquals(1, OclSequences.including(col1, null).size());
	}

	@Test
	public void testIndexOf01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");

		assertEquals(new Integer(1), OclSequences.indexOf(col1, "1"));
		assertEquals(new Integer(2), OclSequences.indexOf(col1, "2"));
	}

	@Test
	public void testIndexOf02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals(new Integer(1), OclSequences.indexOf(col1, "1"));
		assertEquals(new Integer(2), OclSequences.indexOf(col1, "2"));
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf03() {

		List<String> col1 = new ArrayList<String>();

		OclSequences.indexOf(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf04() {

		String[] col1 = new String[] {};

		OclSequences.indexOf(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("2");

		OclSequences.indexOf(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf06() {

		String[] col1 = new String[] { "2" };

		OclSequences.indexOf(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf07() {

		List<String> col1 = null;

		OclSequences.indexOf(col1, "1");
	}

	@Test(expected = InvalidException.class)
	public void testIndexOf08() {

		String[] col1 = null;

		OclSequences.indexOf(col1, "1");
	}

	@Test
	public void testInsertAt01() {

		List<String> col1 = null;

		assertEquals(1, OclSequences.insertAt(col1, 1, null).size());
		assertEquals(1, OclSequences.insertAt(col1, 1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclSequences.insertAt(col1, 1, null).size());
		assertEquals(1, OclSequences.insertAt(col1, 1, "1").size());

		col1.add("1");
		assertEquals(2, OclSequences.insertAt(col1, 1, "1").size());

		assertEquals(2, OclSequences.insertAt(col1, 1, "2").size());
		assertEquals("1", OclSequences.insertAt(col1, 1, "2").get(1));
		assertEquals("2", OclSequences.insertAt(col1, 1, "2").get(0));
	}

	@Test
	public void testInsertAt02() {

		String[] col1 = null;

		assertEquals(1, OclSequences.insertAt(col1, 1, null).size());
		assertEquals(1, OclSequences.insertAt(col1, 1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclSequences.insertAt(col1, 1, null).size());
		assertEquals(1, OclSequences.insertAt(col1, 1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(2, OclSequences.insertAt(col1, 1, "1").size());

		assertEquals(2, OclSequences.insertAt(col1, 1, "2").size());
		assertEquals("1", OclSequences.insertAt(col1, 1, "2").get(1));
		assertEquals("2", OclSequences.insertAt(col1, 1, "2").get(0));
	}

	@Test(expected = InvalidException.class)
	public void testInsertAt03() {

		List<String> col1 = new ArrayList<String>();

		OclSequences.insertAt(col1, 0, "1");
	}

	@Test(expected = InvalidException.class)
	public void testInsertAt04() {

		String[] col1 = new String[] {};

		OclSequences.insertAt(col1, 0, "1");
	}

	@Test(expected = InvalidException.class)
	public void testInsertAt05() {

		List<String> col1 = new ArrayList<String>();

		OclSequences.insertAt(col1, 2, "1");
	}

	@Test(expected = InvalidException.class)
	public void testInsertAt06() {

		String[] col1 = new String[] {};

		OclSequences.insertAt(col1, 2, "1");
	}

	@Test
	public void testLast01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");

		assertEquals("2", OclSequences.last(col1));
	}

	@Test
	public void testLast02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals("2", OclSequences.last(col1));
	}

	@Test(expected = InvalidException.class)
	public void testLast03() {

		List<String> col1 = new ArrayList<String>();

		OclSequences.last(col1);
	}

	@Test(expected = InvalidException.class)
	public void testLast04() {

		String[] col1 = new String[] {};

		OclSequences.last(col1);
	}

	@Test(expected = InvalidException.class)
	public void testLast05() {

		String[] col1 = null;

		OclSequences.last(col1);
	}

	@Test(expected = InvalidException.class)
	public void testLast06() {

		List<String> col1 = null;

		OclSequences.last(col1);
	}

	@Test
	public void testPrepend01() {

		List<String> col1 = null;

		assertEquals(1, OclSequences.prepend(col1, null).size());
		assertEquals(1, OclSequences.prepend(col1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclSequences.prepend(col1, null).size());
		assertEquals(1, OclSequences.prepend(col1, "1").size());

		col1.add("1");
		assertEquals(2, OclSequences.prepend(col1, "1").size());

		assertEquals(2, OclSequences.prepend(col1, "2").size());
		assertEquals("1", OclSequences.prepend(col1, "2").get(1));
		assertEquals("2", OclSequences.prepend(col1, "2").get(0));
	}

	@Test
	public void testPrepend02() {

		String[] col1 = null;

		assertEquals(1, OclSequences.prepend(col1, null).size());
		assertEquals(1, OclSequences.prepend(col1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclSequences.prepend(col1, null).size());
		assertEquals(1, OclSequences.prepend(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(2, OclSequences.prepend(col1, "1").size());

		assertEquals(2, OclSequences.prepend(col1, "2").size());
		assertEquals("1", OclSequences.prepend(col1, "2").get(1));
		assertEquals("2", OclSequences.prepend(col1, "2").get(0));
	}

	@Test
	public void testReverse01() {

		List<String> col1 = null;

		assertEquals(0, OclSequences.reverse(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.reverse(col1).size());

		col1.add("1");
		assertEquals(1, OclSequences.reverse(col1).size());
		assertEquals("1", OclSequences.reverse(col1).get(0));

		col1.add("2");
		assertEquals(2, OclSequences.reverse(col1).size());
		assertEquals("2", OclSequences.reverse(col1).get(0));
		assertEquals("1", OclSequences.reverse(col1).get(1));
	}

	@Test
	public void testReverse02() {

		String[] col1 = null;

		assertEquals(0, OclSequences.reverse(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.reverse(col1).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSequences.reverse(col1).size());
		assertEquals("1", OclSequences.reverse(col1).get(0));

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclSequences.reverse(col1).size());
		assertEquals("2", OclSequences.reverse(col1).get(0));
		assertEquals("1", OclSequences.reverse(col1).get(1));
	}

	@Test
	public void testSubSequence01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		assertEquals(1, OclSequences.subSequence(col1, 1, 1).size());

		col1.add("2");
		assertEquals(2, OclSequences.subSequence(col1, 1, 2).size());
		assertEquals(1, OclSequences.subSequence(col1, 1, 1).size());
		assertEquals(1, OclSequences.subSequence(col1, 2, 2).size());
	}

	@Test
	public void testSubSequence02() {

		String[] col1 = new String[] { "1" };
		assertEquals(1, OclSequences.subSequence(col1, 1, 1).size());

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclSequences.subSequence(col1, 1, 2).size());
		assertEquals(1, OclSequences.subSequence(col1, 1, 1).size());
		assertEquals(1, OclSequences.subSequence(col1, 2, 2).size());
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence03() {

		List<String> col1 = new ArrayList<String>();
		OclSequences.subSequence(col1, 1, 1);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence04() {

		String[] col1 = new String[] {};
		OclSequences.subSequence(col1, 1, 1);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclSequences.subSequence(col1, 0, 1);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence06() {

		String[] col1 = new String[] { "1" };
		OclSequences.subSequence(col1, 0, 1);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence07() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclSequences.subSequence(col1, 1, 2);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence08() {

		String[] col1 = new String[] { "1" };
		OclSequences.subSequence(col1, 1, 2);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence09() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");
		OclSequences.subSequence(col1, 2, 1);
	}

	@Test(expected = InvalidException.class)
	public void testSubSequence10() {

		String[] col1 = new String[] { "1", "2" };
		OclSequences.subSequence(col1, 2, 1);
	}

	@Test
	public void testUnion01() {

		List<String> col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSequences.union(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclSequences.union(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclSequences.union(col1, col2).size());
	}

	@Test
	public void testUnion02() {

		String[] col1 = null;
		List<String> col2 = null;

		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = null;
		col2 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSequences.union(col1, col2).size());

		col2.add("1");
		assertEquals(2, OclSequences.union(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclSequences.union(col1, col2).size());
	}

	@Test
	public void testUnion03() {

		List<String> col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1.add("1");
		assertEquals(1, OclSequences.union(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclSequences.union(col1, col2).size());

		col1.add("2");
		assertEquals(3, OclSequences.union(col1, col2).size());
	}

	@Test
	public void testUnion04() {

		String[] col1 = null;
		String[] col2 = null;

		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = null;
		col2 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] {};
		assertEquals(0, OclSequences.union(col1, col2).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclSequences.union(col1, col2).size());

		col2 = new String[] { "1" };
		assertEquals(2, OclSequences.union(col1, col2).size());

		col1 = new String[] { "1", "2" };
		assertEquals(3, OclSequences.union(col1, col2).size());
	}
}