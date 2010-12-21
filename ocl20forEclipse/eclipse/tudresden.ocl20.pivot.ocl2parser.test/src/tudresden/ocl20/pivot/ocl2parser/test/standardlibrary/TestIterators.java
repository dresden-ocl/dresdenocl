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
import tudresden.ocl20.pivot.parser.SemanticException;

/**
 * <p>
 * Contains test cases that check that the predefined iterators are parsed
 * appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestIterators {

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test
	public void testAnyPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test
	public void testAnyPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test
	public void testAnyPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test
	public void testAnyPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test
	public void testAnyPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testAnyNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testAnyNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testAnyNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->any(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testAnyNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/anyNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test
	public void testCollectPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectPositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test
	public void testCollectNestedPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test
	public void testCollectNestedPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test
	public void testCollectNestedPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test
	public void testCollectNestedPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test
	public void testCollectNestedPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNestedNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNestedNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNestedNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator
	 * <code>Collection->collectNested(..)</code> .
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectNestedNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/collectNestedNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->closure(..)</code>.
	 * </p>
	 */
	@Test
	public void testClosurePositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closurePositive07.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testClosureNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closureNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->collect(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testClosureNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/closureNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive07.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive08.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->exists(..)</code>.
	 * </p>
	 */
	@Test
	public void testExistsPositive09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/existsPositive09.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive07.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive08.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->forAll(..)</code>.
	 * </p>
	 */
	@Test
	public void testForAllPositive09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/forAllPositive09.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test
	public void testIsUniquePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniquePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test
	public void testIsUniquePositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniquePositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test
	public void testIsUniquePositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniquePositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test
	public void testIsUniquePositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniquePositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test
	public void testIsUniquePositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniquePositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIsUniqueNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniqueNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIsUniqueNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniqueNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIsUniqueNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniqueNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->isUnique(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIsUniqueNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/isUniqueNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test
	public void testOnePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/onePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test
	public void testOnePositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/onePositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test
	public void testOnePositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/onePositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test
	public void testOnePositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/onePositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test
	public void testOnePositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/onePositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOneNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/oneNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOneNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/oneNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOneNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/oneNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->one(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOneNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/oneNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test
	public void testRejectPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test
	public void testRejectPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test
	public void testRejectPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test
	public void testRejectPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test
	public void testRejectPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testRejectNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testRejectNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testRejectNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->reject(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testRejectNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/rejectNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test
	public void testSelectPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test
	public void testSelectPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test
	public void testSelectPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test
	public void testSelectPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test
	public void testSelectPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSelectNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSelectNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSelectNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->select(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSelectNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/selectNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test
	public void testSortedByPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test
	public void testSortedByPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test
	public void testSortedByPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test
	public void testSortedByPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test
	public void testSortedByPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSortedByNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSortedByNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSortedByNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case testing the iterator <code>Collection->sortedBy(..)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testSortedByNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/iterators/sortedByNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		testPerformer.parseFile(oclFileName);
	}
}