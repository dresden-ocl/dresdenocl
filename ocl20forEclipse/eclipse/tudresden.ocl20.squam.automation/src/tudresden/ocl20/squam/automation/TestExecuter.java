package tudresden.ocl20.squam.automation;

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestExecuter extends TestSuite {

	protected TestPerformer perf;

	public TestExecuter(String file) {

		super(file);

		this.perf = new TestPerformer();

		this.createTests(file);

	}

	/**
	 * Creates the tests.
	 * 
	 * @throws ParseException
	 */
	protected void createTests(String file) {

		// parse everything
		List<Constraint> constraints = null;
		try {
			constraints = this.perf.doParse(file);
		} catch (ParseException e) {
			this.addTest(TestSuite.warning(e.getMessage()));
			return;
		}

		for (final Constraint constraint : constraints) {

			this.addTest(new TestCase(constraint.getQualifiedName()) {

				public void runTest() throws Throwable {
					IInterpretationResult res = perf.doInterpret(constraint);

					OclAny oclResult = res.getResult();
					assertFalse(oclResult.oclIsInvalid().isTrue());

					if (oclResult instanceof OclBoolean) {
						OclBoolean bool = (OclBoolean) oclResult;

						assertTrue(bool.isTrue());
					} else {
						fail("result was not true");
					}

				}
			});
		}
	}

}
