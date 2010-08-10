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
package tudresden.ocl20.pivot.interpreter.test.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test Standard Library operations on
 * <code>Bag</code>s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBag extends AbstractInterpreterTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "standardlibrary/bag";

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
	 * Tests the operation <code>Collection.product(Collection)</code>.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testEquals01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/equals01", INSTANCE1_NAME, Arrays
						.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsTrue(results.get(0));
	}
}