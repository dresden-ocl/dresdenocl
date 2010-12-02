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
import tudresden.ocl20.pivot.model.ModelAccessException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>RemittanceInformation1</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestRemittanceInformation1 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "remittanceInformation1";

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
	 * Tests the invariant <code>epcAosRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosRmtInfStrd01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosRmtInfStrd", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosRmtInfStrd02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosRmtInfStrd", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosRmtInfStrd03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosRmtInfStrd", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfStrd01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfStrd", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfStrd02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfStrd", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
		this.assertIsFalse(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfStrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfStrd03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfStrd", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfustrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfustrd01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfustrd", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfustrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfustrd02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfustrd", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcRmtInfustrd</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcRmtInfustrd03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcRmtInfustrd", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "RemittanceInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}
}