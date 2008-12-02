package tudresden.ocl20.pivot.ocl2parser.test;

import tudresden.ocl20.pivot.ocl2parser.test.parsertests.AllInstanceTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.BodySetTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.CollectionTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.DefinitionTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.IncludesAllTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.IncludesTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.IsUniqueTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.LetExpTest;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.OclFilesTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for tudresden.ocl20.pivot.ocl2parser.test.parsertests");
		//$JUnit-BEGIN$
		suite.addTestSuite(IncludesAllTest.class);
		suite.addTestSuite(IsUniqueTest.class);
		suite.addTestSuite(IncludesTest.class);
		suite.addTestSuite(AllInstanceTest.class);
		suite.addTestSuite(CollectionTest.class);
		suite.addTestSuite(OclFilesTest.class);
		suite.addTestSuite(DefinitionTest.class);
		suite.addTestSuite(LetExpTest.class);
		suite.addTestSuite(BodySetTest.class);
		//$JUnit-END$
		return suite;
	}

}
