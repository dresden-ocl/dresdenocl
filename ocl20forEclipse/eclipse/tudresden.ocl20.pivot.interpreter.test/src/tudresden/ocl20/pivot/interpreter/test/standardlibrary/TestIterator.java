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

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
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
				CONSTRAINT_DIRECTORY + "/any01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any05", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/any06", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any07", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/any08", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsFalse(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/collect01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/collect02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/collect03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/collect04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
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
				CONSTRAINT_DIRECTORY + "/collect05", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testExists01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/exists01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists05", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists06", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists07", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists08", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists09", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists10", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists11", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists12", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists13", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists14", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists15", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists16", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/exists17", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/forAll01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/forAll02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/forAll03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/forAll04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/forAll05", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll06", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll07", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll08", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll09", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll10", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll11", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll12", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll13", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll14", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll15", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll16", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll17", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll18", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll19", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll20", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll21", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll22", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll23", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll24", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/forAll25", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));
	
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
				CONSTRAINT_DIRECTORY + "/isUnique01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testIsUnique02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testIsUnique03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/isUnique03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/isUnique04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testOne01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testOne02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one02", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
	public void testOne03() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/one03", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

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
				CONSTRAINT_DIRECTORY + "/one04", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsInvalid(results.get(0));
	}
}