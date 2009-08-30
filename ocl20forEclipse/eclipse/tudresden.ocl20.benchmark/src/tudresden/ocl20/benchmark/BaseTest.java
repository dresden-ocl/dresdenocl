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

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.Before;

import java.io.IOException;

import tudresden.ocl20.benchmark.common.TestPerformer;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTest.
 */
public class BaseTest {
	
	/** The perf. */
	protected static TestPerformer perf;
	
	/**
	 * initializes the test performer which is used by any test case.
	 * 
	 * @param part Specifies the part (b1 to b7) of the benchmark being executed
	 * @param model the model
	 */
	protected static void initPerformer(String part, String model, String modelPathPrefix)
	{
		try {
			perf = new TestPerformer(part.toUpperCase());

			perf.setModelInstanceType("tudresden.ocl20.pivot.modelinstancetype.java");

			perf.setModelInstanceType("tudresden.ocl20.pivot.modelinstancetype.java");
			
			perf.init("tudresden.ocl20.pivot.metamodels.ecore",
					"tudresden.ocl20.benchmark",
					"src/tudresden/ocl20/benchmark/testdata/" + modelPathPrefix
							+ "/" + model);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	protected static void initPerformer(String part, String model)
	{
		initPerformer(part, model, part.toLowerCase());
	}
	
	
	@AfterClass
	public static void tearDownPerformer()
	{
		try{
			perf.deInit();
		}catch(IOException e){
			System.out.println(e);
			fail();
		}
	}
	
	@Before
	public void setUp()
	{
		perf.clean();
	}


}
