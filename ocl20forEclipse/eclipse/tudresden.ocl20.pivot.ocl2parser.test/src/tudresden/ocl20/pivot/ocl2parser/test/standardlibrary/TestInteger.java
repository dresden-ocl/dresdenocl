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
	public void testAbsPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/absPositive01.ocl";
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
	 * A test case testing the method <code>Integer.+(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAddPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/addPositive01.ocl";
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
	 * A test case testing the method <code>Integer.+(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAddPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/addPositive02.ocl";
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
	 * A test case testing the method <code>Integer.div(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divPositive01.ocl";
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
	 * A test case testing the method <code>Integer./(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivisionPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divisionPositive01.ocl";
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
	 * A test case testing the method <code>Integer./(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testDivisionPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/divisionPositive02.ocl";
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
	 * A test case testing the method <code>Integer.max(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMaxPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/maxPositive01.ocl";
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
	 * A test case testing the method <code>Integer.min(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minPositive01.ocl";
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
	 * A test case testing the method <code>Integer.-(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinusPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minusPositive01.ocl";
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
	 * A test case testing the method <code>Integer.-(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMinusPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/minusPositive02.ocl";
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
	 * A test case testing the method <code>Integer.mod(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testModPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/modPositive01.ocl";
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
	 * A test case testing the method <code>Integer.*(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMultiplyPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/multiplyPositive01.ocl";
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
	 * A test case testing the method <code>Integer.*(Integer)</code>.
	 * </p>
	 */
	@Test
	public void testMultiplyPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/multiplyPositive02.ocl";
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
	 * A test case testing the method <code>Integer.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegationPositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/negationPositive01.ocl";
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
	 * A test case testing the method <code>Integer.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegationPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/negationPositive02.ocl";
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
	 * A test case testing the method <code>Integer.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToStringPositive02() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/integer/toStringPositive02.ocl";
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
}