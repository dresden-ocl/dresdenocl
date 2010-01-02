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

import tudresden.ocl20.benchmark.common.InvariantExecuter;
import junit.framework.Test;
import junit.framework.TestSuite;

// TODO: Auto-generated Javadoc
/**
 * The Class B1Test.
 */
public class B1Test extends TestSuite {

	/**
	 * Instantiates a new b1 test.
	 */
	public B1Test() {

		super("b1");

		this.addTest(this.testInvariants1());
		
		
		this.addTest(this.testInvariants2());

	}

	/**
	 * Test invariants1.
	 * 
	 * @return the test suite
	 */
	protected TestSuite testInvariants1() {

		return new InvariantExecuter("b1/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testdata/b1/ModelFirstState.class", //
				"bin/tudresden/ocl20/benchmark/testdata/b1/expressions/invariants.ocl");
	}
	
	/**
	 * Test invariants2.
	 * 
	 * @return the test suite
	 */
	protected TestSuite testInvariants2() {
		return new InvariantExecuter("b1/CivilStatusWorld.ecore", //
				"bin/tudresden/ocl20/benchmark/testdata/b1/ModelEvolution.class", //
				"bin/tudresden/ocl20/benchmark/testdata/b1/expressions/invariants.ocl");

	}
	
	/**
	 * returns the test suite for single execution
	 */
	public static Test suite()
	{
		return new B1Test();
	}

}

