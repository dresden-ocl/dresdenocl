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
 * Contains test cases that check that all operations defined on the type
 * Sequence are parsed appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestSequence {

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->append(T)</code>.
	 * </p>
	 */
	@Test
	public void testAppendPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/appendPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->append(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testAppendNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/appendNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->at(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAtPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/atPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->at(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAtPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/atPositive02.ocl";
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
	 * A test case testing the method <code>Sequence->excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcludingPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/excludingPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testExcludingNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/excludingNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->first()</code>.
	 * </p>
	 */
	@Test
	public void testFirstPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/firstPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncludingPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/includingPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIncludingNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/includingNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->indexOf(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testIndexOfPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/indexOfPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->indexOf(Integer)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testIndexOfNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/indexOfNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test
	public void testInsertAtPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test
	public void testInsertAtPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtPositive02.ocl";
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
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testInsertAtNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->last()</code>.
	 * </p>
	 */
	@Test
	public void testLastPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/lastPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->prepend(T)</code>.
	 * </p>
	 */
	@Test
	public void testPrependPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/prependPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->reverse()</code>.
	 * </p>
	 */
	@Test
	public void testReversePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/reversePositive01.ocl";
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
	 * A test case testing the method <code>Sequence->prepend(T)</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPrependNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/prependNegative01.ocl";
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
	 * <code>Sequence->subSequence(Integer, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testSubSequencePositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/subSequencePositive01.ocl";
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
	 * <code>Sequence->subSequence(Integer, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testSubSequencePositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/subSequencePositive02.ocl";
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
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionPositive01.ocl";
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
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative01.ocl";
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
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative02.ocl";
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
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative03.ocl";
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
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testUnionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative04.ocl";
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