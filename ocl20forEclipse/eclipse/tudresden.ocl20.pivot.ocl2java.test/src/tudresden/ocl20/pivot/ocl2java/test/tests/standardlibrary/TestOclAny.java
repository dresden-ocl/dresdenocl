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

import tudresden.ocl20.pivot.facade.OCL2ParsingException;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;

/**
 * <p>
 * Contains some test cases to test the code generation for standard library
 * operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOclAny extends AbstractSLTest {

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

		AbstractSLTest.setUp();
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

		AbstractSLTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the instrumentation of a constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void testAllInstancesInstrumented01() throws IllegalArgumentException,
			OCL2ParsingException, ModelAccessException, Ocl22CodeException {

		this.compareFragmentCodeGeneration("oclany", "allInstances01");
		this.compareInstrumentationCodeGeneration("oclany", "allInstances01");
	}
}