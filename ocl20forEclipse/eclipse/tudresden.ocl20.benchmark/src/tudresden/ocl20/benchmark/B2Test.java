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
package tudresden.ocl20.benchmark;

import junit.framework.Test;
import junit.framework.TestSuite;
import tudresden.ocl20.benchmark.common.InvariantExecuter;
import tudresden.ocl20.benchmark.common.PrePostExecuter;
import tudresden.ocl20.benchmark.testdata.b2.Gender;
import tudresden.ocl20.benchmark.testdata.b2.Person;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;

// TODO: Auto-generated Javadoc
/**
 * The Class B2Test.
 */
public class B2Test extends TestSuite {

	/**
	 * Instantiates a new b2 test.
	 */
	public B2Test() {

		super("b2");

		// test invariants
		this
				.addTest(new InvariantExecuter("b2/CivilStatusWorld.ecore", //
						"bin/tudresden/ocl20/benchmark/testdata/b2/ModelInstance.class",//
						"bin/tudresden/ocl20/benchmark/testData/b2/expressions/invariants.ocl"));

		// test the pre and post conditions of the Class' functions
		this.addTest(this.testPrePostBirth());

		this.addTest(this.testPrePostMarry());

		this.addTest(this.testPrePostDivorce());

		this.addTest(this.testPrePostDeath());

	}

	/**
	 * Test pre post conditions for the birth-method parameters for birth:
	 * aName:String, Gender:aGender.
	 * 
	 * @return 
	 */

	public TestSuite testPrePostBirth() {

		return new PrePostExecuter("b2/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostBirth.ocl") {

			@Override
			protected IModelInstanceObject createModelInstanceObject() {

				// create new Person to test the Constraints on
				IModelInstanceObject testPerson =
						perf.createModelInstanceAdapter(new Person());

				// create and set Parameters
				perf.setEnvironmentVariable("aName", "Ada");
				perf.setEnvironmentVariable("aGender", Gender.female);

				return testPerson;
			}

			@Override
			protected String getMethod() {

				return "birth";
			}

			@Override
			protected String[] getParams() {

				return new String[] { "aName", "aGender" };
			}
		};
	}

	/**
	 * Test pre post marry.
	 * 
	 * @return the test suite
	 */
	public TestSuite testPrePostMarry() {

		return new PrePostExecuter("b2/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostMarry.ocl") {

			@Override
			protected String[] getParams() {

				return new String[] { "aSpouse" };
			}

			@Override
			protected String getMethod() {

				return "marry";
			}

			@Override
			protected IModelInstanceObject createModelInstanceObject() {

				Person ada = new Person();
				ada.birth("Ada", Gender.female);

				IModelInstanceObject person = perf.createModelInstanceAdapter(ada);

				return person;
			}

			@Override
			protected void preparePost() {

				Person bob = new Person();
				bob.birth("Bob", Gender.male);

				perf.setEnvironmentVariable("aSpouse", bob);
			}
		};
	}

	/**
	 * Test pre post divorce.
	 * 
	 * @return the test suite
	 */
	public TestSuite testPrePostDivorce() {

		return new PrePostExecuter("b2/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostDivorce.ocl") {

			@Override
			protected String[] getParams() {

				return new String[] {};
			}

			@Override
			protected String getMethod() {

				return "divorce";
			}

			@Override
			protected IModelInstanceObject createModelInstanceObject() {

				Person ada = new Person();
				ada.birth("Ada", Gender.female);
				Person bob = new Person();
				bob.birth("Bob", Gender.male);
				ada.marry(bob);

				IModelInstanceObject person = perf.createModelInstanceAdapter(ada);

				return person;
			}
		};
	}

	/**
	 * Test pre post death.
	 * 
	 * @return the test suite
	 */
	public TestSuite testPrePostDeath() {

		return new PrePostExecuter("b2/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostDeath.ocl") {

			@Override
			protected String[] getParams() {

				return new String[] {};
			}

			@Override
			protected String getMethod() {

				return "death";
			}

			@Override
			protected IModelInstanceObject createModelInstanceObject() {

				// create person and it's spouse and marry them
				Person ada = new Person();
				ada.birth("Ada", Gender.female);

				IModelInstanceObject person = perf.createModelInstanceAdapter(ada);

				return person;
			}
		};
	}
	
	/**
	 * returns the test suite for single execution
	 */
	public static Test suite()
	{
		return new B2Test();
	}
}
