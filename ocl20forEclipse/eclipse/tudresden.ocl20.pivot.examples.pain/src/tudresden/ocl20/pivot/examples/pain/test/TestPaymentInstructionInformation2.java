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
import static org.junit.Assert.fail;

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
public class TestPaymentInstructionInformation2 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "paymentInstructionInformation2";

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
	 * Tests the invariant <code>chargeBearerRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargeBearerRule01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargeBearerRule", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargeBearerRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargeBearerRule02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargeBearerRule", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargeBearerRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargeBearerRule03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargeBearerRule", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountAgentRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountAgentRule01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountAgentRule", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountAgentRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountAgentRule02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountAgentRule", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountAgentRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountAgentRule03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountAgentRule", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountRule01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountRule", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountRule02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountRule", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>chargesAccountRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testChargesAccountRule03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/chargesAccountRule", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAcct01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAcct", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAcct02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAcct", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAcct03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAcct", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAgtAcct01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAgtAcct", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAgtAcct02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAgtAcct", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosCdtrAgtAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrAgtAcct03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrAgtAcct", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcct01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcct", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcct02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcct", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcct</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcct03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcct", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcctAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcctAgt01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcctAgt", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcctAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcctAgt02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcctAgt", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosChrgsAcctAgt</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosChrgsAcctAgt03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosChrgsAcctAgt", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosUltmtCdtr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosUltmtCdtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosUltmtCdtr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosUltmtCdtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosUltmtCdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosUltmtCdtr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosUltmtCdtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAcctId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAcctId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAcctId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAcctId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAcctId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAcctId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAcctId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAdrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAdrLine01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAdrLine", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAdrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAdrLine02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAdrLine", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAdrLine</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAdrLine03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAdrLine", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAgtFinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAgtFinInstnId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAgtFinInstnId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAgtFinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAgtFinInstnId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAgtFinInstnId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrAgtFinInstnId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrAgtFinInstnId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrAgtFinInstnId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrNm01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrNm", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrNm02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrNm", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrNm</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrNm03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrNm", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdUnusedFull</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdUnusedFull01() throws Throwable {

		fail("The parser does not like this constraint and crashes for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdUnusedFull", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdUnusedFull</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdUnusedFull02() throws Throwable {

		fail("The parser does not like this constraint and crashes for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdUnusedFull", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdUnusedFull</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdUnusedFull03() throws Throwable {

		fail("The parser does not like this constraint and crashes for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdUnusedFull", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfchrgBr01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfchrgBr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfchrgBr02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfchrgBr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfchrgBr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfchrgBr03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfchrgBr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdorgId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdorgId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdorgId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdorgId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdorgId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdorgId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdorgId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdprvtId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdprvtId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdprvtId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdprvtId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtInfultmtCtdrIdprvtId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtInfultmtCtdrIdprvtId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtInfultmtCtdrIdprvtId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInf01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInf", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInf02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInf", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInf</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInf03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInf", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsFalse(results.get(0));
		this.assertIsFalse(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInfSeqTp</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInfSeqTp01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInfSeqTp", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInfSeqTp</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInfSeqTp02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInfSeqTp", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPmtTpInfSeqTp</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPmtTpInfSeqTp03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPmtTpInfSeqTp", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcUltimateccdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcUltimateccdtr01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcUltimateccdtr", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcUltimateccdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcUltimateccdtr02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcUltimateccdtr", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcUltimateccdtr</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcUltimateccdtr03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcUltimateccdtr", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>paymentTypeInformationRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testPaymentTypeInformationRule01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/paymentTypeInformationRule", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>paymentTypeInformationRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testPaymentTypeInformationRule02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/paymentTypeInformationRule", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>paymentTypeInformationRule</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testPaymentTypeInformationRule03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/paymentTypeInformationRule", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorGuideline01() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorGuideline", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorGuideline02() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorGuideline", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorGuideline03() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorGuideline", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorIndrctDbtTxInfGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorIndrctDbtTxInfGuideline01() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorIndrctDbtTxInfGuideline", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorIndrctDbtTxInfGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorIndrctDbtTxInfGuideline02() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorIndrctDbtTxInfGuideline", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(1, results.size());
	
		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the invariant <code>ultimateCreditorIndrctDbtTxInfGuideline</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUltimateCreditorIndrctDbtTxInfGuideline03() throws Throwable {
	
		fail("The parsing of this constraint fails for some reason.");

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/ultimateCreditorIndrctDbtTxInfGuideline", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "PaymentInstructionInformation2" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}
}