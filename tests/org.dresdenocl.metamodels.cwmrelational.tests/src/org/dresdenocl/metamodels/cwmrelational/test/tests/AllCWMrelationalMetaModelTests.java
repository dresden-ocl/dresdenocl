/*
Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@mailbox.tu-dresden.de)

This file is part of the CWMrelational Meta Model of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.metamodels.cwmrelational.test.tests;

import org.dresdenocl.metamodels.test.MetaModelTestPlugin;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
 * @author Bjoern Freitag
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestCWMMetaModel.class })
public class AllCWMrelationalMetaModelTests {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}