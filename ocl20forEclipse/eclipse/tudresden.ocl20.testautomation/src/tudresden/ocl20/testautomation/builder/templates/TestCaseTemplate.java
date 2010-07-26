package tudresden.ocl20.testautomation.builder.templates;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.testautomation.exceptions.TestExecutionException;
import tudresden.ocl20.testautomation.exceptions.TestInitializationException;
import tudresden.ocl20.testautomation.execution.TestPerformer;

abstract public class TestCaseTemplate extends TestCase {

	private String modelFile;

	private String modelInstanceFile;

	protected TestPerformer performer;

	protected TestCaseTemplate(String name, String modelFile, String modelInstanceFile) throws TestInitializationException {

		this.modelFile = modelFile;
		this.modelInstanceFile = modelInstanceFile;
	this.setName(name);
		this.performer = new TestPerformer(this.modelFile, this.modelInstanceFile);

		
	}

	protected void initializePerformer(String modelFile, String modelInstancefile) throws TestInitializationException{
		this.modelFile = modelFile;
		this.modelInstanceFile = modelInstancefile;
		
	}

	protected Constraint parseConstraint(String statement) {

		try {
			return this.performer.parseConstraint(statement);
		} catch (TestExecutionException e) {

			fail("Error parsing the statement" + e.getMessage());

			// will never be reached since fail throws an exception
			return null;
		}
	}

}
