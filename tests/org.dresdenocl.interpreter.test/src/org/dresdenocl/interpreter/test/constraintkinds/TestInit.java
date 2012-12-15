/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the PAIN Case Study of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.interpreter.test.constraintkinds;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.test.standardlibrary.AbstractInterpreterTest;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test init constraints.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInit extends AbstractInterpreterTest {

	/** The name of the constraint directory for this test suite. */
	private static final String CONSTRAINT_DIRECTORY = "constraintkind/initial";

	/**
	 * A non-static init should be interpreted for every model element.
	 */
	@Test
	public void testInit01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL2_NAME,
				CONSTRAINT_DIRECTORY + "/init01", INSTANCE4_NAME,
				Arrays.asList(new String[] { "Class2" }));

		assertNotNull(results);
		assertEquals(2, results.size());

		this.assertIsEqual(new Integer(42), results.get(0));
		this.assertIsEqual(new Integer(42), results.get(0));
	}

	/**
	 * A static init should be interpreted exactly once.
	 */
	@Test
	public void testInit02_01() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL2_NAME,
				CONSTRAINT_DIRECTORY + "/init02", INSTANCE4_NAME,
				Arrays.asList(new String[] { "Class2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(new Integer(42), results.get(0));
	}

	/**
	 * A static body should be interpreted exactly once (even if not
	 * ModelInstanceElement for this type exists).
	 */
	@Test
	public void testInit02_02() throws IllegalArgumentException,
			ModelAccessException, ParseException {

		List<IInterpretationResult> results;
		results = super.interpretConstraintsForInstance(MODEL2_NAME,
				CONSTRAINT_DIRECTORY + "/init02", INSTANCE5_NAME,
				Arrays.asList(new String[] { "Class2" }));

		assertNotNull(results);
		assertEquals(1, results.size());

		this.assertIsEqual(new Integer(42), results.get(0));
	}
}