package org.dresdenocl.ocl2parser.test.keywords;

import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.ocl2parser.test.standardlibrary.AllStandardLibraryTests;
import org.dresdenocl.parser.ParseException;
import org.junit.Test;

/**
 * @author Lars Schuetze
 * 
 */
public class TestKeywords {

	private final static String MODEL_FILE_NAME = "testmodel.uml";
	private TestPerformer testPerformer;
	private String oclFileName;

	/**
	 * Checks whether misused keyword causes the parsing to fail
	 * 
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testKeywordNegative01() throws Exception {
		oclFileName = "keywords/keywordNegative01.ocl";

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
	 * Tests whether the parser parses special operation names that are no
	 * keywords
	 * 
	 * @throws Exception
	 */
	@Test
	public void testKeywordPostive01() throws Exception {
		oclFileName = "keywords/keywordPositive01.ocl";

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
	 * Tests whether the parser parses special operation names that are no
	 * keywords
	 * 
	 * @throws Exception
	 */
	@Test
	public void testKeywordPostive02() throws Exception {
		oclFileName = "keywords/keywordPositive02.ocl";

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
	 * Tests whether the parser parses escaped keywords
	 * 
	 * @throws Exception
	 */
	@Test
	public void testKeywordPostive03() throws Exception {
		oclFileName = "keywords/keywordPositive03.ocl";

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
