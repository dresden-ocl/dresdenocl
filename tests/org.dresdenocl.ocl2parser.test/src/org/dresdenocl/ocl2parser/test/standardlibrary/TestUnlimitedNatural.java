package org.dresdenocl.ocl2parser.test.standardlibrary;

import java.io.FileNotFoundException;

import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.ocl2parser.test.exception.MetaModelNotFoundException;
import org.dresdenocl.parser.ParseException;
import org.junit.Test;

/**
 * 
 * @author Lars Schuetze
 * 
 */
public class TestUnlimitedNatural {

	private final static String MODEL_FILE_NAME = "testmodel.uml";
	private TestPerformer testPerformer;
	private String oclFileName;

	/**
	 * Checks whether the interpreter interprets the unlimitedNatural
	 */
	@Test
	public void testInfinity01() throws Exception {

		oclFileName = "standardlibrary/unlimitednatural/infinity01.ocl";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(MODEL_FILE_NAME);
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * Checks whether multiplication with infinity results in infinity
	 */
	@Test
	public void testMultiplyInfinity01() throws Exception {

		oclFileName = "standardlibrary/unlimitednatural/infinity02.ocl";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(MODEL_FILE_NAME);
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * Adding a value to infinity will still be infinity
	 */
	@Test
	public void testAddInfinity01() throws Exception {

		oclFileName = "standardlibrary/unlimitednatural/infinity03.ocl";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(MODEL_FILE_NAME);
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * The value range of an unlimited natural is (0, oo]
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws MetaModelNotFoundException
	 * @throws FileNotFoundException
	 */
	@Test(expected = ParseException.class)
	public void testNonNegative01() throws IllegalArgumentException,
			ModelAccessException, ParseException, MetaModelNotFoundException,
			FileNotFoundException {

		oclFileName = "standardlibrary/unlimitednatural/nonnegative01.ocl";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(MODEL_FILE_NAME);
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * The value range of an unlimited natural is (0, oo]
	 * 
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws MetaModelNotFoundException
	 * @throws FileNotFoundException
	 */
	@Test(expected = ParseException.class)
	public void testNonNegative02() throws IllegalArgumentException,
			ModelAccessException, ParseException, MetaModelNotFoundException,
			FileNotFoundException {

		oclFileName = "standardlibrary/unlimitednatural/nonnegative02.ocl";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(MODEL_FILE_NAME);
		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}
