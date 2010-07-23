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
public class TestString extends AbstractDiffTest {

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
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAt01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "at01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCharacters01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "characters01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConcat01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "concat01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEqualsIgnoreCase01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string",
				"equalsIgnoreCase01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIndexOf01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "indexOf01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPlus01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "plus01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSize01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "size01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSubstring01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "substring01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToBoolean01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "toBoolean01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToInteger01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "toInteger01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToLowerCase01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "toLowerCase01");
	}

	/**
	 * <p>
	 * Tests the code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToReal01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "toReal01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToUpperCase01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/string", "toUpperCase01");
	}
}