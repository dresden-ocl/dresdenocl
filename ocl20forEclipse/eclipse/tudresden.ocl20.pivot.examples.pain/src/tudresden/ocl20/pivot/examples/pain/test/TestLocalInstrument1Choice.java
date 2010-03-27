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

import tudresden.ocl20.pivot.modelbus.ModelAccessException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>LocalInstrument1Choice</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestLocalInstrument1Choice extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	@SuppressWarnings("unused")
	private static final String CONSTRAINT_DIRECTORY = "localInstrument1Choice";

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
	 * Tests the invariant <code>epcLclInstrmCd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcLclInstrmCd01() throws Throwable {

		/* Do not test anything. No instance of LocalInstrument1Choice exists. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcLclInstrmCd", MODELINSTANCE_NAME_01, Arrays
		// .asList(new String[] { "LocalInstrument1Choice" }));
		//
		// assertNotNull(results);
		// assertEquals(2, results.size());
		//
		// this.assertIsFalse(results.get(0));
		// this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcLclInstrmCd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcLclInstrmCd02() throws Throwable {

		/* Do not test anything. No instance of LocalInstrument1Choice exists. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcLclInstrmCd", MODELINSTANCE_NAME_02, Arrays
		// .asList(new String[] { "LocalInstrument1Choice" }));
		//
		// assertNotNull(results);
		// assertEquals(1, results.size());
		//
		// this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcLclInstrmCd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcLclInstrmCd03() throws Throwable {

		/* Do not test anything. No instance of LocalInstrument1Choice exists. */
		// List<IInterpretationResult> results;
		// results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
		// + "/epcLclInstrmCd", MODELINSTANCE_NAME_03, Arrays
		// .asList(new String[] { "LocalInstrument1Choice" }));
		//
		// assertNotNull(results);
		// assertEquals(2, results.size());
		//
		// this.assertIsFalse(results.get(0));
		// this.assertIsFalse(results.get(1));
	}
}