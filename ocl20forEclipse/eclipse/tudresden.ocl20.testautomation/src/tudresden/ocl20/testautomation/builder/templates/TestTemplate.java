/*{dynbegin:package}*/
package tudresden.ocl20.testautomation.builder.templates;

/*{dynbegin:__notused__}*/
/**
 * This is a Template file dynamically generated Unittests 
 * for Ocl-Constraints
 * 
 * All comments of this type `/`*{name}*`/` are variables 
 * that can be replaced with the appropriate values.
 * 
 * All comments of type 
 * `/`*{dynbegin:name}*`/` and 
 * `/`*{dynend:name}*`/`
 * are blocks that may be repeated arbitrary times.  
 * 
 * To be able to use variables inside of longer comments, there is a third
 * kind of syntax: <{name}>. 
 * Theoretically this type can be used everywhere, but it doesn't make sense outside of comments
 * as it would result in a compile error for the template class
 * 
 */
/*{dynend:__notused__}*/

/*{dynend:package}*/

/*{fileheader}*/

import junit.framework.AssertionFailedError;
import tudresden.ocl20.testautomation.exceptions.TestExecutionException;
import tudresden.ocl20.testautomation.exceptions.TestInitializationException;

/**
 * Sourcefile: <{sourcefile}>
 * 
 * @author
 * @generated
 */
public class //
/* {dynbegin:classname} */
TestTemplate
/* {dynend:classname} */
extends
		tudresden.ocl20.testautomation.execution.AbstractTestCase {

	public// 
	/* {dynbegin:constructor} */
	TestTemplate
	/* {dynend:constructor} */
	() throws TestInitializationException {

		super("/*{testcaseName}*/",//
				"/*{modelFile}*/",//
				"/*{modelInstanceFile}*/");

	}

	/* {dynbegin:parseNegativeTest} */
	/**
	 * Test that executes parsing only but assumes that parsing FAILS
	 * 
	 * @throws TestExecutionException
	 */
	public void testNegParse_/* {testName} */() {

		try {
			String test = "/*{constraint}*/";

			// is expected to fail
			this.parseConstraint(test);

			fail("Parsing didn't fail unexpectedly");
		} catch (TestExecutionException e) {

		}
	}

	/* {dynend:parseNegativeTest} */

	/* {dynbegin:parseTest} */
	/**
	 * Test that executes parsing only
	 * 
	 * @throws TestExecutionException
	 */
	public void testParse_/* {testName} */() throws TestExecutionException {

		String test = "/*{constraint}*/";

		tudresden.ocl20.pivot.pivotmodel.Constraint constraint =
				this.parseConstraintOrFail(test);

		assertNotNull(constraint);
	}

	/* {dynend:parseTest} */

	/* {dynbegin:invTest} */
	public void testInvariant_/* {testName} */() throws TestExecutionException {

		String test = "/*{constraint}*/";

		tudresden.ocl20.pivot.pivotmodel.Constraint constraint =
				parseConstraintOrFail(test);

		this.interpretInvariant(constraint);

	}

	/* {dynend:invTest} */

	/* {dynbegin:invNegativeTest} */
	public void testNegInvariant_/* {testName} */()
			throws TestExecutionException {

		String test = "/*{constraint}*/";

		tudresden.ocl20.pivot.pivotmodel.Constraint constraint =
				parseConstraintOrFail(test);

		// is expected to fail
		this.interpretInvariant(constraint, false);

	}
	/* {dynend:invNegativeTest} */

	/* {dynbegin:prepareParse} */
	/**
	 * This template method can be used to parse a constraint before invoking
	 * other operations. That is useful e.g. for def-Constraints, because the
	 * definitions must be done before any interpretations with them are invoked.
	 * 
	 * @throws TestExecutionException
	 */
	private void prepareParse_/* {testName} */() throws TestExecutionException {

		String test = "/*{constraint}*/";

		tudresden.ocl20.pivot.pivotmodel.Constraint constraint =
				this.parseConstraintOrFail(test);

		assertNotNull(constraint);
	}

	/* {dynend:prepareParse} */

	

	public void setUp() throws Exception {

		super.setUp();
		try{

		/* {dynbegin:setUpPrepareParse} */
		this.prepareParse_/* {value} */();
		/* {dynend:setUpPrepareParse } */
		} catch (AssertionFailedError e) {
			throw new TestExecutionException("Error while running SetUp", e);
		}
	}

}
