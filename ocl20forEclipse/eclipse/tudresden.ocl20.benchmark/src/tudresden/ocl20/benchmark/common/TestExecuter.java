/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

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
package tudresden.ocl20.benchmark.common;

import junit.framework.TestCase;
import junit.framework.TestSuite;

// TODO: Auto-generated Javadoc
abstract public class TestExecuter extends TestSuite {

	protected String statementFile;

	protected TestPerformer perf;

	protected static String META_MODEL = "tudresden.ocl20.pivot.metamodels.ecore";

	protected StatementParsingHelper stmLoader;

	protected TestEnvironment testEnv;

	/**
	 * Instantiates a new test executer.
	 * 
	 * @param name 
	 * @param model 
	 * @param statements 
	 * 
	 * @throws RuntimeException 
	 */
	public TestExecuter(String name, String model, String statements)
			throws RuntimeException {

		super(name);
		
		this.testEnv = new TestEnvironment();
			
		this.perf = new TestPerformer(this.testEnv);
		
		this.initModel(model);
		this.initStatements(statements);
		this.createTests();
		
	}
	
	/**
	 * Inits the statements.
	 * 
	 * @param statementFile 
	 */
	protected void initStatements(String statementFile)
	{

		this.statementFile = statementFile;

		this.stmLoader = new StatementParsingHelper(this.testEnv);
		
		this.stmLoader.safeLoadStatementFile(this.statementFile);
	}

	/**
	 * Inits the model.
	 * 
	 * @param model 
	 */
	protected void initModel(String model){
		perf.setModelInstanceType("tudresden.ocl20.pivot.modelinstancetype.java");

		this.perf.init("tudresden.ocl20.pivot.metamodels.ecore",
				"src/tudresden/ocl20/benchmark/testdata/" + model);
	}

	/**
	 * Creates the tests.
	 */
	protected void createTests() {

		for (StatementDefinition statementDef : this.testEnv.loadedStatements) {

			TestSuite tmp = new TestSuite(statementDef.getName());

			this.createParseTest(tmp, statementDef);

			this.createInterpretTests(tmp, statementDef);

			this.addTest(tmp);

		}
	}

	/**
	 * Creates the interpret tests.
	 * 
	 * @param parent 
	 * @param stmt 
	 */
	abstract protected void createInterpretTests(TestSuite parent,
			final StatementDefinition stmt);

	/**
	 * Creates the parse test.
	 * 
	 * @param parent 
	 * @param stmt 
	 */
	protected void createParseTest(TestSuite parent,
			final StatementDefinition stmt) {

		parent.addTest(new TestCase("parse") {

			public void runTest() throws Throwable {

				perf.parseOclStatement(stmt.getFullStatement());
			}

		});
	}

}
