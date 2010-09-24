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
public class TestIteratorExp extends AbstractDiffTest {

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
	public void testAny01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "any01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCollect01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "collect01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCollectNested01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "collectNested01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExists01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "exists01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExists02() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "exists02");
	}
	
	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testForAll01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "forAll01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testForAll02() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "forAll02");
	}
	
	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsUnique01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "isUnique01");
	}
	
	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOne01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "one01");
	}
	
	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReject01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "reject01");
	}
	
	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSelect01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "select01");
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSortedBy01() throws Exception {

		this.compareFragmentCodeGeneration("expressions/iterator", "sortedBy01");
	}
}