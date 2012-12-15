/*
Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests.expressions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests.AbstractDiffTest;

/**
 * <p>
 * Contains some test cases to test the code generation {@link Constraint}s
 * containing Literal expressions.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInvalidLiteralExp extends AbstractDiffTest {

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
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid02() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid02");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid03() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid03");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid04() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid04");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid05() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid05");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid06() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid06");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid07() throws Exception {

		/*
		 * TODO This failure is associated to the bug
		 * tudresden.ocl20.pivot.ocl2parser
		 * .test.expressions.TestInvalidLiterals.testInvalidPositive02()
		 */
		this.compareFragmentCodeGeneration("expressions/literals", "invalid07");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid08() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid08");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInvalid09() throws Exception {

		this.compareFragmentCodeGeneration("expressions/literals", "invalid09");
	}
}