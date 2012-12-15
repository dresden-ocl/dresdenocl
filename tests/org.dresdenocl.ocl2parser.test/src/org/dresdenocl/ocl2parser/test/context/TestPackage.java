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

package org.dresdenocl.ocl2parser.test.context;

import org.junit.Test;

import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.parser.ParseException;

/**
 * <p>
 * Contains test cases that check that the package declaration is parsed
 * appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPackage {

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPackagePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packagePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPackagePositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packagePositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPackagePositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packagePositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testPackageNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packageNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately. Packages can be left out. (OCL 2.2 standard, p.173)
	 * </p>
	 */
	@Test
	public void testPackageNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packageNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that the package declaration is parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testPackageNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/packageNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}