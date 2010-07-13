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
public class TestOrderedSet extends AbstractDiffTest {

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

		this.compareFragmentCodeGeneration("sltest/orderedset", "asBag01");
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

		this.compareFragmentCodeGeneration("sltest/orderedset",
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

		this.compareFragmentCodeGeneration("sltest/orderedset", "asSequence01");
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

		this.compareFragmentCodeGeneration("sltest/orderedset", "asSet01");
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
	public void testAppend01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "append01");
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
	public void testAt01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "at01");
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
	public void testFirst01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "first01");
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
	public void testInsertAt01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "insertAt01");
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
	public void testIndexOf01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "indexOf01");
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
	public void testLast01() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "last01");
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
	public void testPrepend01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "prepend01");
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
	public void testReverse01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset", "reverse01");
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
	public void testSubOrderedSet01() throws IllegalArgumentException,
			ParseException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("sltest/orderedset",
				"subOrderedSet01");
	}
}