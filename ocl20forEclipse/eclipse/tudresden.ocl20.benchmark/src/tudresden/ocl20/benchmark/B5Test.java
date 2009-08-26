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

import org.junit.BeforeClass;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class B4Test.
 */
public class B5Test extends BaseTest {

	/**
	 * Test init.
	 */
	@BeforeClass
	public static void testInit() {
		initPerformer("b5", "DummyWorld.ecore", "common");

	}

	/**
	 * Load all invariant files within the testdata directory an check them
	 * afterwards on the dummy model instance
	 */
	@Test
	public void testAllInvariants() {

		perf
				.loadModelInstance("bin/tudresden/ocl20/benchmark/testdata/common/ModelInstance.class");
		File expressionsDir = new File(
				"src/tudresden/ocl20/benchmark/testdata/b5/expressions/");

		File[] testFiles = expressionsDir.listFiles();

		for (File testFile : testFiles) {
			perf
					.safeLoadStatementFile("src/tudresden/ocl20/benchmark/testdata/b5/expressions/"
							+ testFile.getName());
		}

		perf.checkActiveInvariants();

	}

}
