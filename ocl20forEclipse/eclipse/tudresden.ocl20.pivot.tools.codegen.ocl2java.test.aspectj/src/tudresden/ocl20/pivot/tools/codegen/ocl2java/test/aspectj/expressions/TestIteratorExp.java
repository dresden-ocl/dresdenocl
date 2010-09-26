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
package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.aspectj.expressions;

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
