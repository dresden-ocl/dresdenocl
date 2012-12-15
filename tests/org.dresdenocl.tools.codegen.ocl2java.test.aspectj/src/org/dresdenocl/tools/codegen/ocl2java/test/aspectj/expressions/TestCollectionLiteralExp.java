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
public class TestCollectionLiteralExp {

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectionLiteralExp01() {

		Class1 class1;
		class1 = new Class1();
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);

		assertEquals(expectedResult, class1.testCollectionLiteralExp01());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectionLiteralExp02() {

		Class1 class1;
		class1 = new Class1();
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);

		assertEquals(expectedResult, class1.testCollectionLiteralExp02());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectionLiteralExp03() {

		Class1 class1;
		class1 = new Class1();
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);

		assertEquals(expectedResult, class1.testCollectionLiteralExp03());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectionLiteralExp04() {

		Class1 class1;
		class1 = new Class1();
		
		List<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);

		assertEquals(expectedResult, class1.testCollectionLiteralExp04());
	}
	
	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testCollectionLiteralExp05() {

		Class1 class1;
		class1 = new Class1();
		
		Set<Integer> expectedResult = new HashSet<Integer>();
		expectedResult.add(0);
		expectedResult.add(1);
		expectedResult.add(2);

		assertEquals(expectedResult, class1.testCollectionLiteralExp05());
	}
}
