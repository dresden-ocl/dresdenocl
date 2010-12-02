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


public class B6Test extends TestSuite {

	/**
	 * Instantiates a new b6 test.
	 */
	public B6Test() {

		super("b6");

		this.addTest(new InvariantExecuter("common/DummyWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testdata/common/ModelInstance.class", //
		"src/tudresden/ocl20/benchmark/testdata/b6/expressions/determinateness.ocl"));
		
		this.addTest(new InvariantExecuter("common/DummyWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testdata/common/ModelInstance.class", //
		"src/tudresden/ocl20/benchmark/testdata/b6/expressions/conversion2sequence.ocl"));
	}
	
	/**
	 * returns the test suite for single execution
	 */
	public static Test suite()
	{
		return new B6Test();
	}
}