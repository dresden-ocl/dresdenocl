package tudresden.ocl20.testautomation.execution;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.testautomation.exceptions.TestExecutionException;
import tudresden.ocl20.testautomation.exceptions.TestInitializationException;

/**
 * This template contains several helping methods that are used by the generated
 * test classes
 * 
 * @author Franz Eichhorn
 * 
 */
abstract public class AbstractTestCase extends TestCase {

	private String modelFile;

	private String modelInstanceFile;

	protected TestPerformer performer;

	protected AbstractTestCase(String name, String modelFile,
			String modelInstanceFile) throws TestInitializationException {

		this.modelFile = modelFile;
		this.modelInstanceFile = modelInstanceFile;
		this.setName(name);
		this.performer = new TestPerformer(this.modelFile, this.modelInstanceFile);

	}

	protected void initializePerformer(String modelFile, String modelInstancefile)
			throws TestInitializationException {

		this.modelFile = modelFile;
		this.modelInstanceFile = modelInstancefile;

	}

	/**
	 * parses a constraint and fails the test if the parsing failed
	 * 
	 * @param statement
	 * @return
	 */
	protected Constraint parseConstraintOrFail(String statement) {

		try {
			return this.performer.parseConstraint(statement);
		} catch (TestExecutionException e) {

			fail("Error parsing the statement" + e.getMessage());

			// will never be reached since fail throws an exception
			return null;
		}
	}

	/**
	 * Parses a constraint but propagates the exception
	 * 
	 * @param statement
	 * @return
	 * @throws TestExecutionException
	 */
	protected Constraint parseConstraint(String statement)
			throws TestExecutionException {

		return this.performer.parseConstraint(statement);
	}

	protected void interpretInvariant(Constraint constraint) {

		this.interpretInvariant(constraint, true);
	}

	protected void interpretInvariant(Constraint constraint, boolean expected) {

		IInterpretationResult res = this.performer.doInterpret(constraint);

		OclAny oclResult = res.getResult();
		assertFalse("Result was oclInvalid", oclResult.oclIsInvalid().isTrue());
		assertFalse("Result was oclUndefined", oclResult.oclIsUndefined().isTrue());

		if (oclResult instanceof OclBoolean) {
			OclBoolean bool = (OclBoolean) oclResult;
			assertEquals(expected, bool.isTrue());
		}
		else {
			fail("result is not of type OclBoolean ("+oclResult.toString()+")");
		}
	}

	public void setUp() throws Exception {

		this.performer.refreshLoadedModel();
	}

	public void tearDown() {

		this.performer = null;
	}

}
