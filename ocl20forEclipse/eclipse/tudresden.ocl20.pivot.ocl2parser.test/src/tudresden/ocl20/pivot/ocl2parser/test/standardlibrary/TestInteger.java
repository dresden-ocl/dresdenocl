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

package tudresden.ocl20.pivot.ocl2parser.test.standardlibrary;

import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;

/**
 * <p>
 * Contains test cases that check that all operations defined on the type
 * Integer are parsed appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInteger {

	/**
	 * <p>
	 * A test case testing the method <code>Integer.abs()</code>.
	 * </p>
	 */
	@Test
	public void testAbsPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/absPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.+(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAddPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/addPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.+(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAddPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/addPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.div(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer./(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivisionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divisionPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer./(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivisionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divisionPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.max(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMaxPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/maxPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.min(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.-(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinusPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minusPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.-(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinusPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minusPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.mod(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testModPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/modPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.*(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMultiplyPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/multiplyPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.*(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMultiplyPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/multiplyPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegationPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/negationPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegationPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/negationPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the method <code>Integer.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToStringPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/toStringPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer =
				TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
						AllStandardLibraryTests.MODEL_BUNDLE,
						AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}