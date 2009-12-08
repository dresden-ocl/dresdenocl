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

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.ocl2parser.test.exception.MetaModelNotFoundException;

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
	public void testAppendPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/appendPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->append(T)</code>.
	 * </p>
	 */
	@Test
	public void testAppendNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/appendNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected Exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->at(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAtPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/atPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->at(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAtPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/atPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->excluding(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcludingPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/excludingPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test
	public void testExcludingNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/excludingNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->first()</code>.
	 * </p>
	 */
	@Test
	public void testFirstPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/firstPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncludingPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/includingPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->including(T)</code>.
	 * </p>
	 */
	@Test
	public void testIncludingNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/includingNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->indexOf(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testIndexOfPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/indexOfPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->indexOf(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testIndexOfNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/indexOfNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected Exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test
	public void testInsertAtPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test
	public void testInsertAtPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->insertAt(Integer, T)</code>.
	 * </p>
	 */
	@Test
	public void testInsertAtNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/insertAtNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected Exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->last()</code>.
	 * </p>
	 */
	@Test
	public void testLastPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/lastPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->prepend(T)</code>.
	 * </p>
	 */
	@Test
	public void testPrependPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/prependPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->reverse()</code>.
	 * </p>
	 */
	@Test
	public void testReversePositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/reversePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->prepend(T)</code>.
	 * </p>
	 */
	@Test
	public void testPrependNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/prependNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected Exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method
	 * <code>Sequence->subSequence(Integer, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testSubSequencePositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/subSequencePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method
	 * <code>Sequence->subSequence(Integer, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testSubSequencePositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/subSequencePositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionNegative02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionNegative03() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case testing the method <code>Sequence->union(Sequence(T))</code>.
	 * </p>
	 */
	@Test
	public void testUnionNegative04() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/sequence/unionNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllStandardLibraryTests.META_MODEL_ID,
							AllStandardLibraryTests.MODEL_BUNDLE,
							AllStandardLibraryTests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);

				fail("Expected SemanticException was not thrown.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}
}