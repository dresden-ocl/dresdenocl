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

import org.junit.Ignore;
import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;

/**
 * <p>
 * Contains test cases that check that all operations defined on the type Real
 * are parsed appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestReal {

	/**
	 * <p>
	 * A test case testing the method <code>Real.abs()</code>.
	 * </p>
	 */
	@Test
	public void testAbsPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/absPositive01.ocl";
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
	 * A test case testing the method <code>Real.+(Real)</code>.
	 * </p>
	 */
	@Test
	public void testAddPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/addPositive01.ocl";
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
	 * A test case testing the method <code>Real.+(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testAddPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/addPositive02.ocl";
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
	 * A test case testing the method <code>Real./(Real)</code>.
	 * </p>
	 */
	@Test
	public void testDivisionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/divisionPositive01.ocl";
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
	 * A test case testing the method <code>Real./(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testDivisionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/divisionPositive02.ocl";
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
	 * A test case testing the method <code>Real.floor()</code>.
	 * </p>
	 */
	@Test
	public void testFloorPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/floorPositive01.ocl";
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
	 * A test case testing the method <code>Real.>=(Real)</code>.
	 * </p>
	 */
	@Test
	public void testGreaterThanEqualPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/greaterThanEqualPositive01.ocl";
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
	 * A test case testing the method <code>Real.>=(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testGreaterThanEqualPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/greaterThanEqualPositive02.ocl";
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
	 * A test case testing the method <code>Real.>(Real)</code>.
	 * </p>
	 */
	@Test
	public void testGreaterThanPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/greaterThanPositive01.ocl";
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
	 * A test case testing the method <code>Real.>(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testGreaterThanPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/greaterThanPositive02.ocl";
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
	 * A test case testing the method <code>Real.<=(Real)</code>.
	 * </p>
	 */
	@Test
	public void testLessThanEqualPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/lessThanEqualPositive01.ocl";
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
	 * A test case testing the method <code>Real.<=(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testLessThanEqualPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/lessThanEqualPositive02.ocl";
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
	 * A test case testing the method <code>Real.<(Real)</code>.
	 * </p>
	 */
	@Test
	public void testLessThanPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/lessThanPositive01.ocl";
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
	 * A test case testing the method <code>Real.<(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testLessThanPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/lessThanPositive02.ocl";
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
	 * A test case testing the method <code>Real.max(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMaxPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/maxPositive01.ocl";
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
	 * A test case testing the method <code>Real.min(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMinPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/minPositive01.ocl";
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
	 * A test case testing the method <code>Real.-(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMinusPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/minusPositive01.ocl";
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
	 * A test case testing the method <code>Real.-(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testMinusPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/minusPositive02.ocl";
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
	 * A test case testing the method <code>Real.*(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMultiplyPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/multiplyPositive01.ocl";
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
	 * A test case testing the method <code>Real.*(Real)</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testMultiplyPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/multiplyPositive02.ocl";
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
	 * A test case testing the method <code>Real.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegationPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/negationPositive01.ocl";
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
	 * A test case testing the method <code>Real.-()</code>.
	 * </p>
	 */
	@Test
	@Ignore("Postfix syntax for infix operators is currently not possible using EMFText.")
	public void testNegationPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/negationPositive02.ocl";
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
	 * A test case testing the method <code>Real.round()</code>.
	 * </p>
	 */
	@Test
	public void testRoundPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/roundPositive01.ocl";
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
	 * A test case testing the method <code>Real.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToStringPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/real/toStringPositive01.ocl";
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