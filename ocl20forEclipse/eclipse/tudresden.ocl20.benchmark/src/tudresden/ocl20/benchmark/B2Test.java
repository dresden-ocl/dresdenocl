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



import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.benchmark.testdata.b2.Gender;
import tudresden.ocl20.benchmark.testdata.b2.Person;

import tudresden.ocl20.pivot.modelbus.IModelObject;


/**
 * The Class B2Test.
 */
public class B2Test extends BaseTest
{

	/**
	 * Inits the.
	 */
	@BeforeClass
	public static void init()
	{
		initPerformer("b2", "CivilStatusWorld.ecore");
	}
	
	@Test
	/**
	 * Test invariants from B2
	 */
	public void testInvariants()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b2/ModelInstance.class");
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/invariants.ocl");
			
		perf.checkActiveInvariants();
		
	}
	
	/**
	 * Test pre post conditions for the birth-method
	 * parameters for birth: aName:String, Gender:aGender
	 */
	@Test
	public void testPrePostBirth()
	{
		perf.safeLoadPrePostFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostBirth.ocl");
		
		// create new Person to test the Constraints on
		IModelObject testPerson= perf.createModelInstanceAdapter(new Person());
		
		// create and set Parameters
		perf.setEnvironmentVariable("aName", "Ada");
		perf.setEnvironmentVariable("aGender", Gender.female);
		
		perf.checkPreAndPostConditions(testPerson, "birth", "aName", "aGender");
		
		
	}
	
	/**
	 * Test pre post conditions for he marry-method
	 * Person::marry(aSpouse:Person)
	 */
	@Test
	public void testPrePostMarry()
	{
		perf.safeLoadPrePostFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostMarry.ocl");
		Person ada = new Person();
		ada.birth("Ada", Gender.female);
		Person bob = new Person();
		bob.birth("Bob", Gender.male);
		
		// create new Person to test the Constraints on
		IModelObject testPerson= perf.createModelInstanceAdapter(ada);
		IModelObject testSpouse= perf.createModelInstanceAdapter(bob);
		
		// create and set Parameters
		perf.setEnvironmentVariable("aSpouse", testSpouse);
			
		perf.checkPreAndPostConditions(testPerson, "marry", "aSpouse");
		
	}
	
	/**
	 * Test pre post conditions for he divorce-method
	 * Person::marry(aSpouse:Person)
	 */
	@Test
	public void testPrePostDivorce()
	{
		
		perf.safeLoadPrePostFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostDivorce.ocl");
		
		// create person and it's spouse and marry them
		Person ada = new Person();
		ada.birth("Ada", Gender.female);
		Person bob = new Person();
		bob.birth("Bob", Gender.male);
		ada.marry(bob);
		
		// create new Person to test the Constraints on
		IModelObject testPerson= perf.createModelInstanceAdapter(ada);

		// first pre is checked.
		// after that divorce is executed and all posts are checked.
		perf.checkPreAndPostConditions(testPerson, "divorce");
		
	}
	
	
	/**
	 * Test pre post conditions for he death-method
	 * Person::death()
	 */
	@Test
	public void testPrePostDeath()
	{
		
		perf.safeLoadPrePostFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepostDeath.ocl");
		
		// create person and it's spouse and marry them
		Person ada = new Person();
		ada.birth("Ada", Gender.female);
		
		// create new Person to test the Constraints on
		IModelObject testPerson = perf.createModelInstanceAdapter(ada);

		// first pre is checked.
		// after that divorce is executed and all posts are checked.
		perf.checkPreAndPostConditions(testPerson, "death");
		
	}
	
	
	
}
