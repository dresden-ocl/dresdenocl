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

package org.dresdenocl.tools.codegen.ocl2java.test.tests.standardlibrary;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.AbstractDiffTest;

/**
 * <p>
 * Contains some test cases to test the code generation for standard library
 * operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInteger extends AbstractDiffTest {

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDiffTest.setUp();
	}

	/**
	 * <p>
	 * Tears down the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@AfterClass
	public static void tearDown() throws IllegalArgumentException,
			ModelAccessException {

		AbstractDiffTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDiv01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "div01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDivide01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "divide01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMax01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "max01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMin01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "min01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMod01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "mod01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMinus01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "minus01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMultiply01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "multiply01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNegation01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "negation01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPlus01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "plus01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToString01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/integer", "toString01");
	}
}