/*{dynbegin:package}*/
package tudresden.ocl20.testautomation.builder.templates;

/*{dynend:package}*/

/*{header}*/

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.testautomation.exceptions.TestExecutionException;
import tudresden.ocl20.testautomation.exceptions.TestInitializationException;

/*{classdescription}*/

public class //
/* {dynbegin:classname} */
SimpleInvarianceTemplate //
		/* {dynend:classname} */
		extends tudresden.ocl20.testautomation.builder.templates.TestCaseTemplate {

	public// 
	/* {dynbegin:constructor} */
	SimpleInvarianceTemplate
	/* {dynend:constructor} */
	() throws TestInitializationException {

		super("/*{testcaseName}*/", "/*{modelFile}*/", "/*{modelInstanceFile}*/");
	}

	/* {dynbegin:invTest} */
	public void testInvariant_/* {testName} */() throws TestExecutionException {

		String test = "/*{constraint}*/";

		Constraint constraint = parseConstraint(test);

		this.interpretInvariant(constraint);

	}

	/* {dynend:invTest} */

	private void interpretInvariant(Constraint constraint) {

		IInterpretationResult res = this.performer.doInterpret(constraint);

		OclAny oclResult = res.getResult();
		assertFalse(oclResult.oclIsInvalid().isTrue());

		if (oclResult instanceof OclBoolean) {
			OclBoolean bool = (OclBoolean) oclResult;
			assertTrue(bool.isTrue());
		}
		else {
			fail("result was not true");
		}
	}
}
