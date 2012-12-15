/*
 * Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL 2 Java Code Generator of Dresden OCL.
 *
 * Dresden OCL2 is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Dresden OCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java.test.aspectj.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests the generated code for OCL Expressions.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestIteratorExp {

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testAnyIteratorExp01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(2), class1.testAnyIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp01_01() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		Class1 c = new Class1();
		
		a.parent = b;
		b.parent = c;

		Set<Class1> expectedResult01 = new HashSet<Class1>();
		expectedResult01.add(b);
		expectedResult01.add(c);

		assertEquals(expectedResult01, a.testClosureIteratorExp01());

		Set<Class1> expectedResult02 = new HashSet<Class1>();
		expectedResult02.add(c);

		assertEquals(expectedResult02, b.testClosureIteratorExp01());

		Set<Class1> expectedResult03 = new HashSet<Class1>();

		assertEquals(expectedResult03, c.testClosureIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp01_02() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		
		a.parent = b;
		b.parent = a;

		Set<Class1> expectedResult01 = new HashSet<Class1>();
		expectedResult01.add(a);
		expectedResult01.add(b);

		assertEquals(expectedResult01, a.testClosureIteratorExp01());
		assertEquals(expectedResult01, b.testClosureIteratorExp01());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp02_01() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		Class1 c = new Class1();
		
		a.parent = b;
		b.parent = c;

		Set<Class1> expectedResult01 = new HashSet<Class1>();
		expectedResult01.add(b);
		expectedResult01.add(c);

		assertEquals(expectedResult01, a.testClosureIteratorExp02());

		Set<Class1> expectedResult02 = new HashSet<Class1>();
		expectedResult02.add(c);

		assertEquals(expectedResult02, b.testClosureIteratorExp02());

		Set<Class1> expectedResult03 = new HashSet<Class1>();

		assertEquals(expectedResult03, c.testClosureIteratorExp02());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp03_01() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		Class1 c = new Class1();
		
		a.parent = b;
		b.parent = c;

		List<Class1> expectedResult01 = new ArrayList<Class1>();
		expectedResult01.add(b);
		expectedResult01.add(c);

		assertEquals(expectedResult01, a.testClosureIteratorExp03());

		List<Class1> expectedResult02 = new ArrayList<Class1>();
		expectedResult02.add(c);

		assertEquals(expectedResult02, b.testClosureIteratorExp03());

		List<Class1> expectedResult03 = new ArrayList<Class1>();

		assertEquals(expectedResult03, c.testClosureIteratorExp03());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp04_01() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		Class1 c = new Class1();
		
		a.parent = b;
		b.parent = c;

		List<Class1> expectedResult01 = new ArrayList<Class1>();
		expectedResult01.add(b);
		expectedResult01.add(c);

		assertEquals(expectedResult01, a.testClosureIteratorExp04());

		List<Class1> expectedResult02 = new ArrayList<Class1>();
		expectedResult02.add(c);

		assertEquals(expectedResult02, b.testClosureIteratorExp04());

		List<Class1> expectedResult03 = new ArrayList<Class1>();

		assertEquals(expectedResult03, c.testClosureIteratorExp04());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp05_01() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		Class1 c = new Class1();
		Class1 d = new Class1();
		
		Set<Class1> children = new HashSet<Class1>();
		children.add(b);
		
		a.children = children;
		
		children = new HashSet<Class1>();
		children.add(c);
		children.add(d);
		b.children = children;

		Set<Class1> expectedResult01 = new HashSet<Class1>();
		expectedResult01.add(b);
		expectedResult01.add(c);
		expectedResult01.add(d);

		assertEquals(expectedResult01, a.testClosureIteratorExp05());

		Set<Class1> expectedResult02 = new HashSet<Class1>();
		expectedResult02.add(c);
		expectedResult02.add(d);

		assertEquals(expectedResult02, b.testClosureIteratorExp05());

		Set<Class1> expectedResult03 = new HashSet<Class1>();

		assertEquals(expectedResult03, c.testClosureIteratorExp05());
		assertEquals(expectedResult03, d.testClosureIteratorExp05());
	}	

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testClosureIteratorExp05_02() {

		Class1 a = new Class1();
		Class1 b = new Class1();
		
		Set<Class1> children = new HashSet<Class1>();
		children.add(b);
		
		a.children = children;
		
		children = new HashSet<Class1>();
		children.add(a);
		b.children = children;

		Set<Class1> expectedResult01 = new HashSet<Class1>();
		expectedResult01.add(a);
		expectedResult01.add(b);

		assertEquals(expectedResult01, a.testClosureIteratorExp05());
		assertEquals(expectedResult01, b.testClosureIteratorExp05());
	}	

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectIteratorExp01() {

		Class1 class1;
		class1 = new Class1();

		List<Boolean> expectedResult = new ArrayList<Boolean>();
		expectedResult.add(true);
		expectedResult.add(false);
		expectedResult.add(true);

		assertEquals(expectedResult, class1.testCollectIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectNestedIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		List<Boolean> expectedResult = new ArrayList<Boolean>();
		expectedResult.add(true);
		expectedResult.add(false);
		expectedResult.add(true);
	
		assertEquals(expectedResult, class1.testCollectNestedIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testExistsIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertTrue(class1.testExistsIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testExistsIteratorExp02() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertTrue(class1.testExistsIteratorExp02());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testForAllIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertTrue(class1.testForAllIteratorExp01());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testForAllIteratorExp02() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertFalse(class1.testForAllIteratorExp02());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testIsUniqueIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertTrue(class1.testIsUniqueIteratorExp01());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testIterateExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertEquals(new Integer(6), class1.testIterateExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testOneIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertTrue(class1.testOneIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testRejectIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
		
		Set<Integer> expectedResult = new HashSet<Integer>();
		expectedResult.add(1);
		expectedResult.add(3);
		
		assertEquals(expectedResult, class1.testRejectIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testSelectIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
		
		Set<Integer> expectedResult = new HashSet<Integer>();
		expectedResult.add(2);
		
		assertEquals(expectedResult, class1.testSelectIteratorExp01());
	}

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testSortedByIteratorExp01() {
	
		Class1 class1;
		class1 = new Class1();
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(3);
		expectedResult.add(2);
		expectedResult.add(1);
		
		assertEquals(expectedResult, class1.testSortedByIteratorExp01());
	}
}
