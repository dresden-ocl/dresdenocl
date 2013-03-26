package org.dresdenocl.interpreter.test.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Lars Schuetze
 */
public class TestUnlimitedNatural extends AbstractInterpreterTest {

	private static final String CONSTRAINT_DIRECTORY = "standardlibrary/unlimitednatural";

	/**
	 * Checks whether the interpreter interprets the unlimitedNatural
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	@Test
	public void testInfinity01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/infinity01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());
	}

	/**
	 * Checks whether multiplication with infinity results in infinity
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	@Test
	public void testMultiplyInfinity01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/infinity02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());
	}

	/**
	 * Adding a value to infinity will still be infinity
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	@Test
	public void testAddInfinity01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/infinity03", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));

		assertNotNull(results);
		assertEquals(1, results.size());
	}

	/**
	 * The value range of an unlimited natural is (0, oo]
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testNonNegative01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/nonnegative01", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));
	}

	/**
	 * The value range of an unlimited natural is (0, oo]
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testNonNegative02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL1_NAME,
				CONSTRAINT_DIRECTORY + "/nonnegative02", INSTANCE1_NAME,
				Arrays.asList(new String[] { "Class1" }));
	}
}
