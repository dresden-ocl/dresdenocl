/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the PAIN Case Study of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.examples.pain.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>StructuredRemittanceInformation6</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestStructuredRemittanceInformation6 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	@SuppressWarnings("unused")
	private static final String CONSTRAINT_DIRECTORY = "structuredRemittanceInformation6";

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {

		AbstractPainTest.setUp();
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

		AbstractPainTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfstrccdtrRefInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfstrccdtrRefInf01() throws Throwable {

		/* Do not test anything. No instance elements available. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcRmtInfstrccdtrRefInf", MODELINSTANCE_NAME_01,
		// Arrays.asList(new String[] { "StructuredRemittanceInformation6" }));
		//
		// assertNotNull(results);
		// assertEquals(0, results.size());
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfstrccdtrRefInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfstrccdtrRefInf02() throws Throwable {

		/* Do not test anything. No instance elements available. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcRmtInfstrccdtrRefInf", MODELINSTANCE_NAME_02,
		// Arrays.asList(new String[] { "StructuredRemittanceInformation6" }));
		//
		// assertNotNull(results);
		// assertEquals(0, results.size());
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfstrccdtrRefInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfstrccdtrRefInf03() throws Throwable {

		/* Do not test anything. No instance elements available. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcRmtInfstrccdtrRefInf", MODELINSTANCE_NAME_03,
		// Arrays.asList(new String[] { "StructuredRemittanceInformation6" }));
		//
		// assertNotNull(results);
		// assertEquals(0, results.size());
	}
}