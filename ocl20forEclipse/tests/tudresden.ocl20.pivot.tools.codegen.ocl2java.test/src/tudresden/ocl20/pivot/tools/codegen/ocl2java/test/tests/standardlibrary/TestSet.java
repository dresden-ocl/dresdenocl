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
public class TestSet extends AbstractDiffTest {

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
	public void testAsBag01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "asBag01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAsOrderedSet01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "asOrderedSet01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAsSequence01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "asSequence01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAsSet01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "asSet01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCount01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "count01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEquals01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "equals01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExcluding01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "excluding01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFlatten01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "flatten01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIncluding01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "including01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIntersection01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "intersection01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIntersection02() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "intersection02");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMinus01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "minus01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSymmetricDifference01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "symmetricDifference01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnion01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "union01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnion02() throws Exception {

		this.compareFragmentCodeGeneration("sltest/set", "union02");
	}
}