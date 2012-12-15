/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL2 Parser Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.ocl2parser.test.standardlibrary;

import org.junit.Test;

import org.dresdenocl.ocl2parser.test.TestPerformer;

/**
 * <p>
 * Contains test cases that checks some operations defined on the type OclAny
 * for enumeration literals.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestEnumeration {

	/**
	 * <p>
	 * A test case testing the method <code>OclAny.=()</code> on enumeration
	 * literals.
	 * </p>
	 */
	@Test
	public void testEqualsPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "standardlibrary/enumeration/equalsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllStandardLibraryTests.META_MODEL_ID,
				AllStandardLibraryTests.MODEL_BUNDLE,
				AllStandardLibraryTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}