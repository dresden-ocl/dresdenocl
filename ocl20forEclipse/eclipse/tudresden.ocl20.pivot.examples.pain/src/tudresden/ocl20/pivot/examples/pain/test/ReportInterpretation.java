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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains some test cases to test constraints defined on the context
 * <code>MandateRelatedInformation1</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ReportInterpretation extends AbstractPainTest {

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
	 * Reports the interpretation of all constraints for
	 * {@link AbstractPainTest#MODELINSTANCE_NAME_01}.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReportInterpretationForInstance01()
			throws IllegalArgumentException, ModelAccessException,
			ParseException {

		super.reportConstraintInterpreationForInstance("wfrs",
				MODELINSTANCE_NAME_01);
	}

	/**
	 * <p>
	 * Reports the interpretation of all constraints for
	 * {@link AbstractPainTest#MODELINSTANCE_NAME_01}.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReportInterpretationForInstance02()
			throws IllegalArgumentException, ModelAccessException,
			ParseException {
	
		super.reportConstraintInterpreationForInstance("wfrs",
				MODELINSTANCE_NAME_02);
	}

	/**
	 * <p>
	 * Reports the interpretation of all constraints for
	 * {@link AbstractPainTest#MODELINSTANCE_NAME_01}.
	 * </p>
	 * 
	 * @throws ParseException
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReportInterpretationForInstance03()
			throws IllegalArgumentException, ModelAccessException,
			ParseException {
	
		super.reportConstraintInterpreationForInstance("wfrs",
				MODELINSTANCE_NAME_03);
	}
}