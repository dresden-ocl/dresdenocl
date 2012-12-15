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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclBag;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclCollection;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.types.OclSet;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclCollection}.
 * </p>
 * 
 * @author Claas Wilke
 */
@SuppressWarnings("deprecation")
public class OclCollectionTest {

	@Test
	public void testContains() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		assertFalse(collection1.contains(object1));
		assertFalse(collection1.contains(object2));

		collection1.add(object1);

		assertTrue(collection1.contains(object1));
		assertFalse(collection1.contains(object2));
	}

	@Test
	public void testContainsAll() {

		OclCollection<String> collection1;
		OclCollection<String> collection2;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		collection2 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		assertTrue(collection1.containsAll(collection2));

		collection1.add(object1);
		collection2.add(object1);

		assertTrue(collection1.containsAll(collection2));

		collection2.add(object2);

		assertFalse(collection1.containsAll(collection2));
	}

	@Test
	public void testCount() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		object1 = "1";
		object2 = "1";

		assertEquals(0, collection1.count(object1));

		collection1.add(object1);

		assertEquals(1, collection1.count(object1));

		collection1.add(object1);

		assertEquals(2, collection1.count(object1));

		collection1.add(object2);

		assertEquals(3, collection1.count(object2));
	}

	@Test
	public void testExcludes() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		assertTrue(collection1.excludes(object1));
		assertTrue(collection1.excludes(object2));

		collection1.add(object1);

		assertFalse(collection1.excludes(object1));
		assertTrue(collection1.excludes(object2));
	}

	@Test
	public void testExcludesAll() {

		OclCollection<String> collection1;
		OclCollection<String> collection2;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		collection2 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		assertTrue(collection1.excludesAll(collection2));

		collection1.add(object2);
		collection2.add(object1);

		assertTrue(collection1.excludesAll(collection2));

		collection2.add(object2);

		assertFalse(collection1.excludesAll(collection2));
	}

	@Test
	public void testIsEmpty() {

		OclCollection<String> collection1;
		String object1;

		collection1 = new OclBag<String>();
		object1 = "1";

		assertTrue(collection1.isEmpty());

		collection1.add(object1);

		assertFalse(collection1.isEmpty());
	}

	@Test
	public void testNotEmpty() {

		OclCollection<String> collection1;
		String object1;

		collection1 = new OclBag<String>();
		object1 = "1";

		assertFalse(collection1.notEmpty());

		collection1.add(object1);

		assertTrue(collection1.notEmpty());
	}

	@Test
	public void testProduct() {
		OclCollection<String> set1;
		OclCollection<String> bag1;
		OclSet<Map<String, Object>> product;

		String object1;
		String object2;

		Map<String, Object> element1;
		Map<String, Object> element2;
		Map<String, Object> element3;
		Map<String, Object> element4;

		set1 = new OclSet<String>();
		bag1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		set1.add(object1);
		set1.add(object2);

		bag1.add(object2);
		bag1.add(object1);
		bag1.add(object2);

		element1 = new HashMap<String, Object>();
		element1.put("first", "1");
		element1.put("second", "1");

		element2 = new HashMap<String, Object>();
		element2.put("first", "1");
		element2.put("second", "2");

		element3 = new HashMap<String, Object>();
		element3.put("first", "2");
		element3.put("second", "1");

		element4 = new HashMap<String, Object>();
		element4.put("first", "2");
		element4.put("second", "2");

		product = set1.product(bag1);

		assertEquals(4, product.size());
		assertTrue(product.contains(element1));
		assertTrue(product.contains(element2));
		assertTrue(product.contains(element3));
		assertTrue(product.contains(element4));

		product = bag1.product(set1);

		assertEquals(4, product.size());
		assertTrue(product.contains(element1));
		assertTrue(product.contains(element2));
		assertTrue(product.contains(element3));
		assertTrue(product.contains(element4));
	}

	@Test
	public void testSize() {

		OclCollection<String> collection1;
		String object1;

		collection1 = new OclBag<String>();
		object1 = "1";

		assertEquals(0, collection1.size());

		collection1.add(object1);

		assertEquals(1, collection1.size());
	}

	@Test
	public void testAdd() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		assertEquals(0, collection1.size());

		collection1.add(object1);
		collection1.add(object2);

		assertEquals(2, collection1.size());
	}

	@Test
	public void testAddAll() {

		OclCollection<String> collection1;
		OclCollection<String> collection2;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		collection2 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		collection2.add(object1);
		collection2.add(object2);

		assertEquals(0, collection1.size());

		collection1.addAll(collection2);

		assertEquals(2, collection1.size());
	}

	@Test
	public void testClear() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		assertEquals(0, collection1.size());

		collection1.add(object1);
		collection1.add(object2);

		assertEquals(2, collection1.size());

		collection1.clear();

		assertEquals(0, collection1.size());
	}

	@Test
	public void testRemove() {

		OclCollection<String> collection1;
		String object1;
		String object2;

		collection1 = new OclBag<String>();

		object1 = "1";
		object2 = "2";

		collection1.add(object1);
		collection1.add(object2);

		assertTrue(collection1.remove(object1));

		assertEquals(1, collection1.size());

		assertFalse(collection1.remove(object1));

		assertEquals(1, collection1.size());
	}

	@Test
	public void testRemoveAll() {

		OclCollection<String> collection1;
		OclCollection<String> collection2;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		collection2 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		collection1.add(object1);
		collection1.add(object2);
		collection2.add(object1);
		collection2.add(object2);

		assertEquals(2, collection1.size());

		assertTrue(collection1.removeAll(collection2));

		assertEquals(0, collection1.size());

		assertFalse(collection1.removeAll(collection2));
	}

	@Test
	public void testRetainAll() {

		OclCollection<String> collection1;
		OclCollection<String> collection2;
		String object1;
		String object2;

		collection1 = new OclBag<String>();
		collection2 = new OclBag<String>();
		object1 = "1";
		object2 = "2";

		collection1.add(object1);
		collection1.add(object2);
		collection2.add(object1);

		assertTrue(collection1.retainAll(collection2));

		assertTrue(collection1.contains(object1));
		assertFalse(collection1.contains(object2));
	}

}
