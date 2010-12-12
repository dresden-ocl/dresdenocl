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
package tudresden.ocl20.pivot.interpreter.test.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test Standard Library iterators on
 * <code>Collection</code>s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestIterator extends AbstractInterpreterTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "standardlibrary/iterator";

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

		AbstractInterpreterTest.setUp();
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

		AbstractInterpreterTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsUndefined(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->any(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testAny17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/any17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure01_1() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure01", INSTANCE2_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		if (((OclCollection<?>) results.get(0).getResult()).size()
				.getModelInstanceInteger().getLong() == 0) {
			this.assertIsCollectionOfSize(0, results.get(0));
			this.assertIsCollectionOfSize(1, results.get(1));
		}

		else {
			this.assertIsCollectionOfSize(1, results.get(0));
			this.assertIsCollectionOfSize(0, results.get(1));
		}
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure01_2() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure01", INSTANCE3_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsCollectionOfSize(2, results.get(0));
		this.assertIsCollectionOfSize(2, results.get(1));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure02", INSTANCE2_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		if (((OclCollection<?>) results.get(0).getResult()).size()
				.getModelInstanceInteger().getLong() == 0) {
			this.assertIsCollectionOfSize(0, results.get(0));
			this.assertIsCollectionOfSize(1, results.get(1));
		}

		else {
			this.assertIsCollectionOfSize(1, results.get(0));
			this.assertIsCollectionOfSize(0, results.get(1));
		}
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure03", INSTANCE2_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		if (((OclCollection<?>) results.get(0).getResult()).size()
				.getModelInstanceInteger().getLong() == 0) {
			this.assertIsCollectionOfSize(0, results.get(0));
			this.assertIsCollectionOfSize(1, results.get(1));
		}

		else {
			this.assertIsCollectionOfSize(1, results.get(0));
			this.assertIsCollectionOfSize(0, results.get(1));
		}
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure04", INSTANCE2_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		if (((OclCollection<?>) results.get(0).getResult()).size()
				.getModelInstanceInteger().getLong() == 0) {
			this.assertIsCollectionOfSize(0, results.get(0));
			this.assertIsCollectionOfSize(1, results.get(1));
		}

		else {
			this.assertIsCollectionOfSize(1, results.get(0));
			this.assertIsCollectionOfSize(0, results.get(1));
		}
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(0, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testClosure08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/closure08", INSTANCE3_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsCollectionOfSize(2, results.get(0));
		this.assertIsCollectionOfSize(2, results.get(1));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollect09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collect09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->collectNested(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testCollectNested09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/collectNested09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testExists17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll18() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll18", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll19() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll19", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll20() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll20", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll21() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll21", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll22() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll22", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll23() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll23", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll24() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll24", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testForAll25() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/forAll25", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIsUnique17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->iterate(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIterate01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/iterate01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->iterate(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIterate02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/iterate02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->iterate(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testIterate03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/iterate03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne18() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one18", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne19() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one19", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne20() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one20", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne21() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one21", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne22() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one22", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne23() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one23", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne24() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one24", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->one(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testOne25() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one25", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(2, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(2, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(2, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReject17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/reject17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(0, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect10() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select10", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect11() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select11", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect12() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select12", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect13() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select13", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect14() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select14", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(1, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect15() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select15", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect16() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select16", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->select(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSelect17() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/select17", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy04() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy04", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy05() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy05", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy06() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy06", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsCollectionOfSize(3, results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy07() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy07", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy08() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy08", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}

	/**
	 * <p>
	 * Tests the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testSortedBy09() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/sortedBy09", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}
}