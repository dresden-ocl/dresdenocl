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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>MandateRelatedInformation1</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestMandateRelatedInformation1 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "mandateRelatedInformation1";

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
	 * Tests the invariant <code>amendmentIndicatorRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testAmendmentIndicatorRule01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/amendmentIndicatorRule", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>amendmentIndicatorRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testAmendmentIndicatorRule02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/amendmentIndicatorRule", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>amendmentIndicatorRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testAmendmentIndicatorRule03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/amendmentIndicatorRule", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosMndtRltInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosMndtRltInf01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosMndtRltInf", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosMndtRltInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosMndtRltInf02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosMndtRltInf", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
		this.assertIsFalse(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosMndtRltInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosMndtRltInf03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosMndtRltInf", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltInfAmdmntInd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltInfAmdmntInd01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltInfAmdmntInd", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltInfAmdmntInd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltInfAmdmntInd02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltInfAmdmntInd", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltInfAmdmntInd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltInfAmdmntInd03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltInfAmdmntInd", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcOrgnlDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcOrgnlDbtrAcctId01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcOrgnlDbtrAcctId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcOrgnlDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcOrgnlDbtrAcctId02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcOrgnlDbtrAcctId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcOrgnlDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcOrgnlDbtrAcctId03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcOrgnlDbtrAcctId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "MandateRelatedInformation1" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}
}