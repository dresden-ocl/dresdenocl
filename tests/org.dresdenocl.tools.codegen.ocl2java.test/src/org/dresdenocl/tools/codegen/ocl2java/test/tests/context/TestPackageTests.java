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

package org.dresdenocl.tools.codegen.ocl2java.test.tests.context;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.tools.codegen.ocl2java.IOcl2JavaSettings;
import org.dresdenocl.tools.codegen.ocl2java.Ocl2JavaFactory;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.AbstractDiffTest;

/**
 * <p>
 * Contains some test cases to test the code generation {@link Constraint}s
 * containing Literal expressions.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPackageTests extends AbstractDiffTest {

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
	public void testPackage01() throws Exception {

		IOcl2JavaSettings settings = Ocl2JavaFactory.getInstance()
				.createJavaCodeGeneratorSettings();
		settings.setBasisPackage("basisPackage");
		this.compareFragmentCodeGeneration("context", "package01", settings);
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPackage02() throws Exception {

		IOcl2JavaSettings settings = Ocl2JavaFactory.getInstance()
				.createJavaCodeGeneratorSettings();
		settings.setBasisPackage("basisPackage");
		this.compareFragmentCodeGeneration("context", "package02", settings);
	}

	/**
	 * <p>
	 * Tests the fragment code generation of the constraint.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPackage03() throws Exception {

		IOcl2JavaSettings settings = Ocl2JavaFactory.getInstance()
				.createJavaCodeGeneratorSettings();
		settings.setBasisPackage("basisPackage1.basisPackage2");
		this.compareFragmentCodeGeneration("context", "package03", settings);
	}
}