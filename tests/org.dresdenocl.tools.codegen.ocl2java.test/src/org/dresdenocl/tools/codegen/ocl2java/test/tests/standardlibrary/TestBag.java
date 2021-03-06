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
public class TestBag extends AbstractDiffTest {

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void bagUp() throws Exception {

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

		this.compareFragmentCodeGeneration("sltest/bag", "asBag01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "asOrderedSet01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "asSequence01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "asSet01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "count01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "equals01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "excluding01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "flatten01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "including01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "intersection01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "intersection02");
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

		this.compareFragmentCodeGeneration("sltest/bag", "union01");
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

		this.compareFragmentCodeGeneration("sltest/bag", "union02");
	}
}