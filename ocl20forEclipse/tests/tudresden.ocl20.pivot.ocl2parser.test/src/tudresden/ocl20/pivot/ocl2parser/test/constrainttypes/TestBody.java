/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL2 Parser Test Suite of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.ocl2parser.test.constrainttypes;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains test cases that check that the context of body expressions is parsed
 * correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBody extends AbstractDresdenOclTest {

	/**
	 * <p>
	 * A test case to parse a body expression that should be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testBodyPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/bodyPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a body expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testBodyNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/bodyNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a body expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testBodyNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/bodyNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a body expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testBodyNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/bodyNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a body expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testBodyNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/bodyNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}