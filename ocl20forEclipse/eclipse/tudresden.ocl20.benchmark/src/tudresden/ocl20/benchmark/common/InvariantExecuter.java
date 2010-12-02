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
import tudresden.ocl20.pivot.pivotmodel.Constraint;

// TODO: Auto-generated Javadoc
public class InvariantExecuter extends TestExecuter {

	protected String miName;
	
	
	/**
	 * Instantiates a new invariant executer.
	 * 
	 * @param model 
	 * @param modelInstance 
	 * @param statementFile 
	 */
	public InvariantExecuter(String model, String modelInstance, String statementFile)
	{
		super(Helper.getFullFileNameFromPath(model) + "#" + //
		Helper.getFullFileNameFromPath(modelInstance) + "#" + //
		Helper.getFullFileNameFromPath(statementFile), model, statementFile);
		
		this.miName = modelInstance;

	}
	
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.benchmark.common.TestExecuter#createInterpretTests(junit.framework.TestSuite, tudresden.ocl20.benchmark.common.StatementParsingHelper.StatementDefinition)
	 */
	@Override
	protected void createInterpretTests(TestSuite parent, final StatementDefinition stmt) {

		parent.addTest( new TestCase("interpret") {

			protected StatementDefinition statement = stmt;

			public void runTest() throws Throwable {

				Constraint con = perf.getConstraintByStatement(this.statement);
				if(con == null){
					fail("Constraint was not found --> parsing must have been failed");
				}else{
					StringBuilder results = new StringBuilder();
					if(!perf.checkInvariantWithAllActiveInstanceObjects(con, results)){
						fail(results.toString());
					}
				}
			}

			public void setUp() {

				perf.loadModelInstance(miName);

				perf.loadActiveMIObjects();

			}

		});

	}
}
