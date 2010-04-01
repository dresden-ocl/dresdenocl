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
 * <code>DirectDebitTransaction1</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestDirectDebitTransaction1 extends AbstractPainTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "directDebitTransaction1";

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
	 * Tests the invariant <code>EpcAosCdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrSchmeId01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrSchmeId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>EpcAosCdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrSchmeId02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrSchmeId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>EpcAosCdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosCdtrSchmeId03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosCdtrSchmeId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTx</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTx01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTx", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		this.assertTruesAndFalses(results, 1, 1);
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTx</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTx02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTx", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		this.assertTruesAndFalses(results, 1, 2);
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcAosDrctDbtTx</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcAosDrctDbtTx03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcAosDrctDbtTx", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		this.assertTruesAndFalses(results, 1, 1);
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdOnlyOne</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdOnlyOne01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdOnlyOne", MODELINSTANCE_NAME_01,
				Arrays.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdOnlyOne</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdOnlyOne02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdOnlyOne", MODELINSTANCE_NAME_02,
				Arrays.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdOnlyOne</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdOnlyOne03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdOnlyOne", MODELINSTANCE_NAME_03,
				Arrays.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdSepa</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdSepa01() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdSepa", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdSepa</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdSepa02() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdSepa", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(3, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdSepa</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcCdtrSchemeIdPrvtIdSepa03() throws Throwable {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcCdtrSchemeIdPrvtIdSepa", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcCdtrSchemeIdPrvtIdSepa</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdSEPA", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcMndtRltdInfOrgnlCdtrSchemIdPrvitIdOnlyOnce", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPACS3MappingDrctDbtTxcdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPACS3MappingDrctDbtTxcdtrSchmeId01() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPACS3MappingDrctDbtTxcdtrSchmeId", MODELINSTANCE_NAME_01, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPACS3MappingDrctDbtTxcdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPACS3MappingDrctDbtTxcdtrSchmeId02() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPACS3MappingDrctDbtTxcdtrSchmeId", MODELINSTANCE_NAME_02, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(3, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
	}

	/**
	 * <p>
	 * Tests the invariant <code>epcPACS3MappingDrctDbtTxcdtrSchmeId</code>.
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testEpcPACS3MappingDrctDbtTxcdtrSchmeId03() throws Throwable {
	
		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(CONSTRAINT_DIRECTORY
				+ "/epcPACS3MappingDrctDbtTxcdtrSchmeId", MODELINSTANCE_NAME_03, Arrays
				.asList(new String[] { "DirectDebitTransaction1" }));
	
		assertNotNull(results);
		assertEquals(2, results.size());
	
		this.assertIsTrue(results.get(0));
		this.assertIsTrue(results.get(1));
	}
}