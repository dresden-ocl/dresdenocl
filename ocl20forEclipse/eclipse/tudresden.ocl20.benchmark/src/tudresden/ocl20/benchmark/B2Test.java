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

// TODO: Auto-generated Javadoc
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
	 * Test pre post from B2
	 */
	@Test
	public void testPrePost()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b2/ModelInstance.class");
		
		perf.safeLoadPrePostFile("bin/tudresden/ocl20/benchmark/testData/b2/expressions/prepost.ocl");
		
		perf.checkActiveInvariants();
		
	}
	
	
}
