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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests.standardlibrary;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests.AbstractDiffTest;

/**
 * <p>
 * Contains some test cases to test the code generation for standard library
 * operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBoolean extends AbstractDiffTest {

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
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAnd01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "and01");
	}

	/**
	 * <p>
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testImplies01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "implies01");
	}

	/**
	 * <p>
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNot01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "not01");
	}

	/**
	 * <p>
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOr01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "or01");
	}

	/**
	 * <p>
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToString01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "toString01");
	}

	/**
	 * <p>
	 * Tests the code generation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testXor01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/boolean", "xor01");
	}
}