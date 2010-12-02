/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.uml2.test.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;

/**
 * <p>
 * Contains all test cases of the {@link IMetamodel} test suite.
 * </p>
 * 
 * <p>
 * This test suite can be parameterized by extending it and calling the method
 * {@link MetaModelTestPlugin#prepareTest(String, String, String)}.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestUML2MetaModel.class, TestModelImport.class,
		TestUML2Packages.class })
public class AllUml2MetaModelTests {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}