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

import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.SemanticException;

/**
 * <p>
 * Contains test cases that check that the context of preconditions is parsed
 * correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPrecondition {

	/**
	 * <p>
	 * A test case to parse a precondition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testPreconditionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionPositive01.ocl";
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
	 * A test case to parse a precondition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testPreconditionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionPositive02.ocl";
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
	 * A test case to parse a precondition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testPreconditionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionNegative01.ocl";
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
	 * A test case to parse a precondition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testPreconditionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionNegative02.ocl";
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
	 * A test case to parse a precondition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPreconditionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionNegative03.ocl";
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
	 * A test case to parse a postcondition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPreconditionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/preconditionNegative04.ocl";
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