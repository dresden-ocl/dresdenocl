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

package tudresden.ocl20.pivot.ocl2java.test.tests.standardlibrary;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;
import tudresden.ocl20.pivot.ocl2java.test.tests.AbstractDiffTest;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test the code generation for standard library
 * operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestCollection extends AbstractDiffTest {

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@BeforeClass
	public static void setUp() throws IllegalArgumentException,
			ModelAccessException {

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
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testAsBag01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "asBag01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testAsOrderedSet01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection",
				"asOrderedSet01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testAsSequence01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "asSequence01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testAsSet01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "asSet01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testCount01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "count01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testEquals01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "equals01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testExcludes01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "excludes01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testExcludesAll01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this
				.compareFragmentCodeGeneration("sltest/collection",
						"excludesAll01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testFlatten01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {
	
		this.compareFragmentCodeGeneration("sltest/collection", "flatten01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testIncludesAll01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this
				.compareFragmentCodeGeneration("sltest/collection",
						"includesAll01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testIncludes01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "includes01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testIsEmpty01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "isEmpty01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testMax01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "max01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testMin01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "min01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testNotEmpty01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "notEmpty01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testNotEquals01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "notEquals01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testProduct01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "product01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testSize01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "size01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testSum01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/collection", "sum01");
	}
}