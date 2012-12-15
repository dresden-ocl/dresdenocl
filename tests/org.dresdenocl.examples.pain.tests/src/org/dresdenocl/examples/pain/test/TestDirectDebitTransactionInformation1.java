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
package org.dresdenocl.examples.pain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.model.ModelAccessException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>DirectDebitTransaction1</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestDirectDebitTransactionInformation1 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "directDebitTransactionInformation1";

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
	 * Tests the invariant <code>epcAosDbtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDbtrAgtAcct01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDbtrAgtAcct", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDbtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDbtrAgtAcct02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDbtrAgtAcct", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDbtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDbtrAgtAcct03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDbtrAgtAcct", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInf01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInf", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInf02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInf", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInf03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInf", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfDbtr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfDbtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfDbtr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfDbtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
		this.assertIsFalse(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfDbtr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfDbtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPmtTpInf01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPmtTpInf", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPmtTpInf02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPmtTpInf", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPmtTpInf03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPmtTpInf", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPurpPrtry</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPurpPrtry01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPurpPrtry", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPurpPrtry</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPurpPrtry02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPurpPrtry", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfPurpPrtry</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfPurpPrtry03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfPurpPrtry", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtCdtr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtCdtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtCdtr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtCdtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtCdtr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtCdtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtDbtr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtDbtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtDbtr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtDbtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTxInfUltmtDbtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTxInfUltmtDbtr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTxInfUltmtDbtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosInstrForCdtrAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosInstrForCdtrAgt01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosInstrForCdtrAgt", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosInstrForCdtrAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosInstrForCdtrAgt02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosInstrForCdtrAgt", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosInstrForCdtrAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosInstrForCdtrAgt03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosInstrForCdtrAgt", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcChargeBearer</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcChargeBearer01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcChargeBearer", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcChargeBearer</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcChargeBearer02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcChargeBearer", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcChargeBearer</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcChargeBearer03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcChargeBearer", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAcctId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAcctId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAcctId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAcctId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
		this.assertIsFalse(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAcctId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAcctId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtradrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtradrLine01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtradrLine", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtradrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtradrLine02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtradrLine", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtradrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtradrLine03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtradrLine", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAgtfinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAgtfinInstnId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAgtfinInstnId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAgtfinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAgtfinInstnId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAgtfinInstnId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrAgtfinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrAgtfinInstnId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrAgtfinInstnId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrNm01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrNm", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrNm02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrNm", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDbtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDbtrNm03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDbtrNm", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdOrgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdOrgId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdOrgId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdOrgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdOrgId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdOrgId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdOrgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdOrgId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdOrgId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdprvtId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdprvtId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdprvtId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdprvtId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfdbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfdbtrIdprvtId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbtTxInfdbtrIdprvtId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfMandatoryFields</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfMandatoryFields01() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfMandatoryFields",
						MODELINSTANCE_NAME_01,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		this.assertTruesAndFalses(results, 1, 1);
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfMandatoryFields</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfMandatoryFields02() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfMandatoryFields",
						MODELINSTANCE_NAME_02,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfMandatoryFields</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfMandatoryFields03() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfMandatoryFields",
						MODELINSTANCE_NAME_03,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		this.assertTruesAndFalses(results, 1, 1);
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfUltmdbrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfUltmdbrIdorgId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY + "/epcDrctDbtTxInfUltmdbrIdorgId",
						MODELINSTANCE_NAME_01,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfUltmdbrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfUltmdbrIdorgId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY + "/epcDrctDbtTxInfUltmdbrIdorgId",
						MODELINSTANCE_NAME_02,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfUltmdbrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfUltmdbrIdorgId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY + "/epcDrctDbtTxInfUltmdbrIdorgId",
						MODELINSTANCE_NAME_03,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtcdtrIdOrdId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtcdtrIdOrdId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtcdtrIdOrdId",
						MODELINSTANCE_NAME_01,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtcdtrIdOrdId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtcdtrIdOrdId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtcdtrIdOrdId",
						MODELINSTANCE_NAME_02,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtcdtrIdOrdId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtcdtrIdOrdId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtcdtrIdOrdId",
						MODELINSTANCE_NAME_03,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtCdtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtCdtrIdprvtId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtCdtrIdprvtId",
						MODELINSTANCE_NAME_01,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtCdtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtCdtrIdprvtId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtCdtrIdprvtId",
						MODELINSTANCE_NAME_02,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtCdtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtCdtrIdprvtId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtCdtrIdprvtId",
						MODELINSTANCE_NAME_03,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtDbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtDbtrIdprvtId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtDbtrIdprvtId",
						MODELINSTANCE_NAME_01,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtDbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtDbtrIdprvtId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtDbtrIdprvtId",
						MODELINSTANCE_NAME_02,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbtTxInfultmtDbtrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbtTxInfultmtDbtrIdprvtId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super
				.interpretConstraintsForInstance(
						CONSTRAINT_DIRECTORY
								+ "/epcDrctDbtTxInfultmtDbtrIdprvtId",
						MODELINSTANCE_NAME_03,
						Arrays
								.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbTxInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbTxInfchrgBr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbTxInfchrgBr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbTxInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbTxInfchrgBr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbTxInfchrgBr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
		this.assertIsFalse(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcDrctDbTxInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcDrctDbTxInfchrgBr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcDrctDbTxInfchrgBr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstdAmtEURO</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstdAmtEURO01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstdAmtEURO", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstdAmtEURO</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstdAmtEURO02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstdAmtEURO", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstdAmtEURO</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstdAmtEURO03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstdAmtEURO", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstrdAmtValueRange</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstrdAmtValueRange01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstrdAmtValueRange", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstrdAmtValueRange</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstrdAmtValueRange02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstrdAmtValueRange", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcInstrdAmtValueRange</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcInstrdAmtValueRange03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcInstrdAmtValueRange", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateDebtorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateDebtorGuideline01() throws Throwable {
		
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateDebtorGuideline", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateDebtorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateDebtorGuideline02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateDebtorGuideline", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateDebtorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateDebtorGuideline03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateDebtorGuideline", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransactionInformation1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}
}