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
package org.dresdenocl.tools.codegen.ocl2java.types.test.util.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.dresdenocl.tools.codegen.ocl2java.types.OclInvalidException;
import org.dresdenocl.tools.codegen.ocl2java.types.util.OclOrderedSets;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclOrderedSets}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclOrderedSetsTest {

	@Test
	public void testAppend01() {

		List<String> col1 = null;

		assertEquals(1, OclOrderedSets.append(col1, null).size());
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclOrderedSets.append(col1, null).size());
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		col1.add("1");
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		assertEquals(2, OclOrderedSets.append(col1, "2").size());
		assertEquals("1", OclOrderedSets.append(col1, "2").get(0));
		assertEquals("2", OclOrderedSets.append(col1, "2").get(1));
	}

	@Test
	public void testAppend02() {

		String[] col1 = null;

		assertEquals(1, OclOrderedSets.append(col1, null).size());
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclOrderedSets.append(col1, null).size());
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(1, OclOrderedSets.append(col1, "1").size());

		assertEquals(2, OclOrderedSets.append(col1, "2").size());
		assertEquals("1", OclOrderedSets.append(col1, "2").get(0));
		assertEquals("2", OclOrderedSets.append(col1, "2").get(1));
	}

	@Test
	public void testAsBag01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asBag(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclOrderedSets.asBag(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));

		col1.add(elem2);
		assertEquals(2, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asBag(col1).get(1));
	}

	@Test
	public void testAsBag02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asBag(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclOrderedSets.asBag(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclOrderedSets.asBag(col1).size());
		assertEquals(elem1, OclOrderedSets.asBag(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asBag(col1).get(1));
	}

	@Test
	public void testAsOrderedSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asOrderedSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclOrderedSets.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asOrderedSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asOrderedSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclOrderedSets.asOrderedSet(col1).size());
		assertEquals(elem1, OclOrderedSets.asOrderedSet(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asOrderedSet(col1).get(1));
	}

	@Test
	public void testAsOrderedSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asOrderedSet(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclOrderedSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclOrderedSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclOrderedSets.asOrderedSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclOrderedSets.asOrderedSet(col1).size());
		assertEquals(elem1, OclOrderedSets.asOrderedSet(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asOrderedSet(col1).get(1));
	}

	@Test
	public void testAsSequence01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asSequence(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclOrderedSets.asSequence(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));

		col1.add(elem2);
		assertEquals(2, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asSequence(col1).get(1));
	}

	@Test
	public void testAsSequence02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asSequence(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclOrderedSets.asSequence(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclOrderedSets.asSequence(col1).size());
		assertEquals(elem1, OclOrderedSets.asSequence(col1).get(0));
		assertEquals(elem2, OclOrderedSets.asSequence(col1).get(1));
	}

	@Test
	public void testAsSet01() {

		List<String> col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asSet(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclOrderedSets.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asSet(col1).size());

		col1.add(elem1);
		assertEquals(1, OclOrderedSets.asSet(col1).size());

		col1.add(elem2);
		assertEquals(2, OclOrderedSets.asSet(col1).size());
	}

	@Test
	public void testAsSet02() {

		String[] col1 = null;
		String elem1 = "1";
		String elem2 = "2";

		assertEquals(0, OclOrderedSets.asSet(col1).size());

		col1 = new String[0];
		assertEquals(0, OclOrderedSets.asSet(col1).size());

		col1 = new String[] { elem1 };
		assertEquals(1, OclOrderedSets.asSet(col1).size());

		col1 = new String[] { elem1, elem1 };
		assertEquals(1, OclOrderedSets.asSet(col1).size());

		col1 = new String[] { elem1, elem1, elem2 };
		assertEquals(2, OclOrderedSets.asSet(col1).size());
	}

	@Test
	public void testAt01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");

		assertEquals("1", OclOrderedSets.at(col1, 1));

		col1.add("2");
		assertEquals("2", OclOrderedSets.at(col1, 2));
	}

	@Test
	public void testAt02() {

		String[] col1 = new String[] { "1" };

		assertEquals("1", OclOrderedSets.at(col1, 1));

		col1 = new String[] { "1", "2" };
		assertEquals("2", OclOrderedSets.at(col1, 2));
	}

	@Test(expected = OclInvalidException.class)
	public void testAt03() {

		List<String> col1 = new ArrayList<String>();
		OclOrderedSets.at(col1, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testAt04() {

		String[] col1 = new String[] {};
		OclOrderedSets.at(col1, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testAt05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclOrderedSets.at(col1, 0);
	}

	@Test(expected = OclInvalidException.class)
	public void testAt06() {

		String[] col1 = new String[] { "1" };
		OclOrderedSets.at(col1, 0);
	}

	@Test(expected = OclInvalidException.class)
	public void testAt07() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclOrderedSets.at(col1, 2);
	}

	@Test(expected = OclInvalidException.class)
	public void testAt08() {

		String[] col1 = new String[] { "1" };
		OclOrderedSets.at(col1, 2);
	}

	@Test
	public void testFirst01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");

		assertEquals("1", OclOrderedSets.first(col1));
	}

	@Test
	public void testFirst02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals("1", OclOrderedSets.first(col1));
	}

	@Test(expected = OclInvalidException.class)
	public void testFirst03() {

		List<String> col1 = new ArrayList<String>();

		OclOrderedSets.first(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testFirst04() {

		String[] col1 = new String[] {};

		OclOrderedSets.first(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testFirst05() {

		String[] col1 = null;

		OclOrderedSets.first(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testFirst06() {

		List<String> col1 = null;

		OclOrderedSets.first(col1);
	}

	@Test
	public void testIndexOf01() {
	
		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");
	
		assertEquals(new Integer(1), OclOrderedSets.indexOf(col1, "1"));
		assertEquals(new Integer(2), OclOrderedSets.indexOf(col1, "2"));
	}

	@Test
	public void testIndexOf02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals(new Integer(1), OclOrderedSets.indexOf(col1, "1"));
		assertEquals(new Integer(2), OclOrderedSets.indexOf(col1, "2"));
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf03() {

		List<String> col1 = new ArrayList<String>();

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf04() {

		String[] col1 = new String[] {};

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("2");

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf06() {

		String[] col1 = new String[] { "2" };

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf07() {

		List<String> col1 = null;

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testIndexOf08() {

		String[] col1 = null;

		OclOrderedSets.indexOf(col1, "1");
	}

	@Test
	public void testInsertAt01() {

		List<String> col1 = null;

		assertEquals(1, OclOrderedSets.insertAt(col1, 1, null).size());
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, null).size());
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		col1.add("1");
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		assertEquals(2, OclOrderedSets.insertAt(col1, 1, "2").size());
		assertEquals("1", OclOrderedSets.insertAt(col1, 1, "2").get(1));
		assertEquals("2", OclOrderedSets.insertAt(col1, 1, "2").get(0));
	}

	@Test
	public void testInsertAt02() {

		String[] col1 = null;

		assertEquals(1, OclOrderedSets.insertAt(col1, 1, null).size());
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, null).size());
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(1, OclOrderedSets.insertAt(col1, 1, "1").size());

		assertEquals(2, OclOrderedSets.insertAt(col1, 1, "2").size());
		assertEquals("1", OclOrderedSets.insertAt(col1, 1, "2").get(1));
		assertEquals("2", OclOrderedSets.insertAt(col1, 1, "2").get(0));
	}

	@Test(expected = OclInvalidException.class)
	public void testInsertAt03() {

		List<String> col1 = new ArrayList<String>();

		OclOrderedSets.insertAt(col1, 0, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testInsertAt04() {

		String[] col1 = new String[] {};

		OclOrderedSets.insertAt(col1, 0, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testInsertAt05() {

		List<String> col1 = new ArrayList<String>();

		OclOrderedSets.insertAt(col1, 2, "1");
	}

	@Test(expected = OclInvalidException.class)
	public void testInsertAt06() {

		String[] col1 = new String[] {};

		OclOrderedSets.insertAt(col1, 2, "1");
	}

	@Test
	public void testLast01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");

		assertEquals("2", OclOrderedSets.last(col1));
	}

	@Test
	public void testLast02() {

		String[] col1 = new String[] { "1", "2" };

		assertEquals("2", OclOrderedSets.last(col1));
	}

	@Test(expected = OclInvalidException.class)
	public void testLast03() {

		List<String> col1 = new ArrayList<String>();

		OclOrderedSets.last(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testLast04() {

		String[] col1 = new String[] {};

		OclOrderedSets.last(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testLast05() {

		String[] col1 = null;

		OclOrderedSets.last(col1);
	}

	@Test(expected = OclInvalidException.class)
	public void testLast06() {

		List<String> col1 = null;

		OclOrderedSets.last(col1);
	}

	@Test
	public void testPrepend01() {

		List<String> col1 = null;

		assertEquals(1, OclOrderedSets.prepend(col1, null).size());
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		col1 = new ArrayList<String>();
		assertEquals(1, OclOrderedSets.prepend(col1, null).size());
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		col1.add("1");
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		assertEquals(2, OclOrderedSets.prepend(col1, "2").size());
		assertEquals("1", OclOrderedSets.prepend(col1, "2").get(1));
		assertEquals("2", OclOrderedSets.prepend(col1, "2").get(0));
	}

	@Test
	public void testPrepend02() {

		String[] col1 = null;

		assertEquals(1, OclOrderedSets.prepend(col1, null).size());
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		col1 = new String[] {};
		assertEquals(1, OclOrderedSets.prepend(col1, null).size());
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		col1 = new String[] { "1" };
		assertEquals(1, OclOrderedSets.prepend(col1, "1").size());

		assertEquals(2, OclOrderedSets.prepend(col1, "2").size());
		assertEquals("1", OclOrderedSets.prepend(col1, "2").get(1));
		assertEquals("2", OclOrderedSets.prepend(col1, "2").get(0));
	}

	@Test
	public void testReverse01() {

		List<String> col1 = null;

		assertEquals(0, OclOrderedSets.reverse(col1).size());

		col1 = new ArrayList<String>();
		assertEquals(0, OclOrderedSets.reverse(col1).size());

		col1.add("1");
		assertEquals(1, OclOrderedSets.reverse(col1).size());
		assertEquals("1", OclOrderedSets.reverse(col1).get(0));

		col1.add("2");
		assertEquals(2, OclOrderedSets.reverse(col1).size());
		assertEquals("2", OclOrderedSets.reverse(col1).get(0));
		assertEquals("1", OclOrderedSets.reverse(col1).get(1));
	}

	@Test
	public void testReverse02() {

		String[] col1 = null;

		assertEquals(0, OclOrderedSets.reverse(col1).size());

		col1 = new String[] {};
		assertEquals(0, OclOrderedSets.reverse(col1).size());

		col1 = new String[] { "1" };
		assertEquals(1, OclOrderedSets.reverse(col1).size());
		assertEquals("1", OclOrderedSets.reverse(col1).get(0));

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclOrderedSets.reverse(col1).size());
		assertEquals("2", OclOrderedSets.reverse(col1).get(0));
		assertEquals("1", OclOrderedSets.reverse(col1).get(1));
	}

	@Test
	public void testSubOrderedSet01() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 1, 1).size());

		col1.add("2");
		assertEquals(2, OclOrderedSets.subOrderedSet(col1, 1, 2).size());
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 1, 1).size());
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 2, 2).size());
	}

	@Test
	public void testSubOrderedSet02() {

		String[] col1 = new String[] { "1" };
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 1, 1).size());

		col1 = new String[] { "1", "2" };
		assertEquals(2, OclOrderedSets.subOrderedSet(col1, 1, 2).size());
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 1, 1).size());
		assertEquals(1, OclOrderedSets.subOrderedSet(col1, 2, 2).size());
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet03() {

		List<String> col1 = new ArrayList<String>();
		OclOrderedSets.subOrderedSet(col1, 1, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet04() {

		String[] col1 = new String[] {};
		OclOrderedSets.subOrderedSet(col1, 1, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet05() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclOrderedSets.subOrderedSet(col1, 0, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet06() {

		String[] col1 = new String[] { "1" };
		OclOrderedSets.subOrderedSet(col1, 0, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet07() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		OclOrderedSets.subOrderedSet(col1, 1, 2);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet08() {

		String[] col1 = new String[] { "1" };
		OclOrderedSets.subOrderedSet(col1, 1, 2);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet09() {

		List<String> col1 = new ArrayList<String>();
		col1.add("1");
		col1.add("2");
		OclOrderedSets.subOrderedSet(col1, 2, 1);
	}

	@Test(expected = OclInvalidException.class)
	public void testSubOrderedSet10() {

		String[] col1 = new String[] { "1", "2" };
		OclOrderedSets.subOrderedSet(col1, 2, 1);
	}
}