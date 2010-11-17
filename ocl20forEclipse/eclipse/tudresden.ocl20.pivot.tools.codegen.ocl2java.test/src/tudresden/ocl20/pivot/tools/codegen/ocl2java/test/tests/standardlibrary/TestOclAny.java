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
public class TestOclAny extends AbstractDiffTest {

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
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAllInstances01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "allInstances01");
		this.compareInstrumentationCodeGeneration("sltest/oclany", "allInstances01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAsSet01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "asSet01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAsSet02() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "asSet02");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAtPre01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "atPre01");
		this.compareInstrumentationCodeGeneration("sltest/oclany", "atPre01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEquals01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "equals01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNotEquals01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "notEquals01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclAsType01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclAsType01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsInvalid01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsInvalid01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsInvalid02() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsInvalid02");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsNew01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsNew01");
		this.compareInstrumentationCodeGeneration("sltest/oclany", "oclIsNew01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsKindOf01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsKindOf01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsTypeOf01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsTypeOf01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsUndefined01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsUndefined01");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclIsUndefined02() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclIsUndefined02");
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOclType01() throws Exception {

		this.compareFragmentCodeGeneration("sltest/oclany", "oclType01");
	}
}