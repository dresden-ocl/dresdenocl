/*
Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)

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
package org.dresdenocl.interpreter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.test.standardlibrary.AbstractInterpreterTest;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test Standard Library operations on
 * <code>Bag</code>s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestRecursion extends AbstractInterpreterTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "recursion";

	/**
	 * <p>
	 * Tests the operation <code>Collection.product(Collection)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testFibonacci01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/fibonacci01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(7, results.size());

		this.assertIsUndefined(results.get(0));
		this.assertIsTrue(results.get(1));
		this.assertIsTrue(results.get(2));
		this.assertIsTrue(results.get(3));
		this.assertIsTrue(results.get(4));
		this.assertIsTrue(results.get(5));
		this.assertIsTrue(results.get(6));
	}

	/**
	 * <p>
	 * Tests the operation <code>Collection.product(Collection)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRecursiveProperty01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/recProperty01", INSTANCE2_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		assertIsNotInvalid(results.get(0));
		assertFalse(results.get(0).getResult().oclIsUndefined().isTrue());
		assertTrue(results.get(0).getResult() instanceof OclInteger);

		assertIsNotInvalid(results.get(1));
		assertFalse(results.get(1).getResult().oclIsUndefined().isTrue());
		assertTrue(results.get(1).getResult() instanceof OclInteger);

		OclInteger oclInteger01;
		oclInteger01 = (OclInteger) results.get(0).getResult();
		OclInteger oclInteger02;
		oclInteger02 = (OclInteger) results.get(1).getResult();

		/* One result must be 1 and one 0. */
		assertTrue((oclInteger01.getModelInstanceInteger().getLong() == 0 && oclInteger02
				.getModelInstanceInteger().getLong() == 1)
				|| (oclInteger01.getModelInstanceInteger().getLong() == 1 && oclInteger02
						.getModelInstanceInteger().getLong() == 0));
	}
}