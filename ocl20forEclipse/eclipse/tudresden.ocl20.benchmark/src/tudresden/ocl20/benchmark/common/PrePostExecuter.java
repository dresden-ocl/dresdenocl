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
import tudresden.ocl20.benchmark.common.StatementDefinition;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Class to dynamically create a testcase to deal with pre post constraints.
 * This class is abstract as it requires individual data in each context. See
 * the Pre/Post tests in B1-B7 for an example. Normally it's necessary to create
 * just an anonymous class and filling the abstract methods. If needed one can
 * overwrite protected hook methods to have additional behavior.
 * 
 * 
 * @TODO: at the moment there are pre and post tests for each statement (which
 *        doesnt make sense at all). First execute all pre's, then execute the
 *        function and execute all posts. Fix this!!!! Use inner type
 *        recognition of the StatementDefinition class (which is to be
 *        implemented as well) This is very important as the idea of pre and
 *        post doesn't make sense otherwise.
 */
abstract public class PrePostExecuter extends TestExecuter {

	/**
	 * Instantiates a new pre post executer.
	 * 
	 * @param model
	 * @param statementFile
	 */
	public PrePostExecuter(String model, String statementFile) {

		super(Helper.getFullFileNameFromPath(model) + "#" + //
				Helper.getFullFileNameFromPath(statementFile), model, statementFile);

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.benchmark.common.TestExecuter#createInterpretTests(junit
	 * .framework.TestSuite,
	 * tudresden.ocl20.benchmark.common.StatementParsingHelper
	 * .StatementDefinition)
	 */
	protected void createInterpretTests(TestSuite parent,
			final StatementDefinition stmt) {

		parent.addTest(new TestCase("pre") {

			public void runTest() throws Throwable {

				Constraint con = perf.getConstraintByStatement(stmt);
				if (con == null) {
					fail("Constraint was not found --> parsing must have been failed");
				}
				else {
					StringBuilder results = new StringBuilder();
					if (!perf
							.checkPreCondition(createModelInstanceObject(), con, results)) {
						fail(results.toString());
					}
				}
			}

			public void setUp() {

				// load a new empty model instance to allow creating the model instance
				// object
				perf.loadEmptyModelInstance();

				// run any method before executing the pre task that can be specified by
				// the client.
				preparePre();
			}
		});

		parent.addTest(new TestCase("post") {

			public void runTest() throws Throwable {

				Constraint con = perf.getConstraintByStatement(stmt);
				if (con == null) {
					fail("Constraint was not found --> parsing must have been failed");
				}
				else {
					StringBuilder results = new StringBuilder();
					if (!perf.checkPostCondition(createModelInstanceObject(), con,
							results, getMethod(), getParams())) {
						fail(results.toString());
					}
				}
			}

			public void setUp() {

				// load a new empty model instance to allow creating the model instance
				// object
				perf.loadEmptyModelInstance();

				preparePost();

			}

		});

	}

	/**
	 * Hook method to create a model instance object on which the pre and post
	 * constraints are examined and on which the method is invoked.
	 */
	abstract protected IModelInstanceObject createModelInstanceObject();

	/**
	 * Hook method to get the name of the method to be executed. The method
	 * expects the values of those variables in the interpretationEnvironment
	 * which are retrieved by the names of <code>getParams</code>
	 */
	abstract protected String getMethod();

	/**
	 * Hook method to get a list of names of parameters which are stored in the
	 * InterpretationEnvironment that are passed to the invoked method
	 * 
	 * @see getMethod
	 */
	abstract protected String[] getParams();

	/**
	 * Empty Hook method which can be overloaded by clients. This one is executed
	 * in the setUp procedure of each pre test.
	 * 
	 * @see preparePost
	 */
	protected void preparePre() {

	}

	/**
	 * Empty Hook method which can be overloaded by clients. This one is executed
	 * in the setUp procedure of each post test.
	 * 
	 * @see preparePre
	 */
	protected void preparePost() {

	}

}
