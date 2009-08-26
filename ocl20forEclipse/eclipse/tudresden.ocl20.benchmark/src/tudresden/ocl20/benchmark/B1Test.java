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
 * The Class B1Test.
 */
public class B1Test extends BaseTest
{

	@BeforeClass
	public static void init()
	{
		initPerformer("b1", "CivilStatusWorld.ecore");
	}
	
	@Test
	/**
	 * Test invariants on first modelInstance
	 */
	public void testInvariants1()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b1/ModelFirstState.class");
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testdata/b1/expressions/invariants.ocl");
		
		perf.checkActiveInvariants();
	}
	
	/**
	 * Test invariants on the evolution model instance
	 */
	@Test
	public void testInvariants2()
	{
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b1/ModelEvolution.class");
		
		perf.safeLoadStatementFile("bin/tudresden/ocl20/benchmark/testdata/b1/expressions/invariants.ocl");
		
		perf.checkActiveInvariants();
	}
	
	@Test
	/**
	 * Test some queries
	 * <TODO> Fix Queries!
	 */
	public void testEvolutionQueries()
	{
		
		perf.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/b1/ModelEvolution.class");

		perf.safeLoadQueryFile("bin/tudresden/ocl20/benchmark/testdata/b1/expressions/evolutionQueries.ocl");
		
		perf.checkActiveInvariants();
		
	}
	
}
