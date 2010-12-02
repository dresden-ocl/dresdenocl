package tudresden.ocl20.squam.automation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class ParserTest extends TestSuite {

	public ParserTest() {
		// this.addTest(this.testWrapper());

		addFileToTest("oclexpressions/OCLTypes/PrimitiveTypes/Reals.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/PrimitiveTypes/Booleans.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/PrimitiveTypes/Strings.oclx.ocl");
		
		addFileToTest("oclexpressions/OCLTypes/OCLSpecialTypes/OCLAny.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/OCLSpecialTypes/OCLInvalids.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/OCLSpecialTypes/OCLVoids.oclx.ocl");
		
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/Bags.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/Collections.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/OrderedSets.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/Sets.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/SetsBags.oclx.ocl");
		addFileToTest("oclexpressions/OCLTypes/CompositeTypes/Tuples.oclx.ocl");
		
		addFileToTest("oclexpressions/Definitions/RecursiveCall_sqrt.oclx.ocl");
		addFileToTest("oclexpressions/Definitions/StringExtensions.oclx.ocl");
		

	}

	private void addFileToTest(String filename) {
		TestExecuter tmp = new TestExecuter(filename);

		this.addTest(tmp);
	}

	/**
	 * returns the test suite for single execution
	 */
	public static Test suite() {
		return new ParserTest();
	}

}
