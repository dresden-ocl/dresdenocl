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

package org.dresdenocl.ocl2parser.test.standardlibrary;

import org.junit.Ignore;
import org.junit.Test;

import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.parser.SemanticException;

/**
 * <p>
 * Contains test cases that check that all operations defined on the type Set
 * are parsed appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestSet {

	/**
	 * <p>
	 * A test case testing the method <code>Collection->excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcludingPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/excludingPositive01.ocl";
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
	 * A test case testing the method <code>Collection->including(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testExcludingNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/excludingNegative01.ocl";
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
	 * A test case testing the method <code>Collection->including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncludingPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/includingPositive01.ocl";
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
	 * A test case testing the method <code>Collection->including(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIncludingNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/includingNegative01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->intersection(Collection(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersectionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/intersectionPositive01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->intersection(Collection(T))</code>.
	 * </p>
	 */
	@Test
	public void testIntersectionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/intersectionPositive02.ocl";
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
	 * A test case testing the method
	 * <code>Collection->intersection(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIntersectionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/intersectionNegative01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->intersection(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIntersectionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/intersectionNegative02.ocl";
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
	 * A test case testing the method
	 * <code>Collection->intersection(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIntersectionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/intersectionNegative03.ocl";
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
	 * A test case testing the method <code>Collection->-(Collection(T))</code>.
	 * </p>
	 */
	// TODO: This tests whether "-" for set difference can be applied with "->", 
	// but the only way it is used in the standard is as an infix operation. 
	// Therefore ignore for now, but activate again in the future when the notation is
	// clearified.
	@Ignore
	@Test
	public void testMinusPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/minusPositive01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->minus(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testMinusNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/minusNegative01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->symmetricDifference(Collection(T))</code>.
	 * </p>
	 */
	@Test
	public void testSymmetricDifferencePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/symmetricDifferencePositive01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->symmetricDifference(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSymmetricDifferenceNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/symmetricDifferenceNegative01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->symmetricDifference(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSymmetricDifferenceNegative02() throws Exception {
	
		TestPerformer testPerformer;
	
		String modelFileName;
		String oclFileName;
	
		oclFileName = "standardlibrary/set/symmetricDifferenceNegative02.ocl";
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
	 * A test case testing the method
	 * <code>Collection->union(Collection(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/unionPositive01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->union(Collection(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/unionPositive02.ocl";
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
	 * A test case testing the method
	 * <code>Collection->union(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/unionNegative01.ocl";
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
	 * A test case testing the method
	 * <code>Collection->union(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/unionNegative02.ocl";
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
	 * A test case testing the method
	 * <code>Collection->union(Collection(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/set/unionNegative03.ocl";
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