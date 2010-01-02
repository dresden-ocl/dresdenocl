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

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;
import tudresden.ocl20.benchmark.common.InvariantExecuter;


public class B5Test extends TestSuite {

	/**
	 * Instantiates a new b5 test.
	 */
	public B5Test() {

		super("b5");

		String modelInstance =
				"bin/tudresden/ocl20/benchmark/testdata/common/ModelInstance.class";
		String model = "common/DummyWorld.ecore";

		
		// get directory where all expressions are located
		File expressionsDir =
				new File("src/tudresden/ocl20/benchmark/testdata/b5/expressions/");

		
		// get all files
		File[] testFiles = expressionsDir.listFiles();

		
		// create an own invariant executer for all 
		for (File testFile : testFiles) {
			if (testFile.getName().indexOf(".ocl") == -1) {
				continue;
			}
			this.addTest(new InvariantExecuter(model, //
					modelInstance, //
					"src/tudresden/ocl20/benchmark/testdata/b5/expressions/"
							+ testFile.getName()));
		}
	}
	
	/**
	 * returns the test suite for single execution
	 */
	public static Test suite()
	{
		return new B5Test();
	}
}
