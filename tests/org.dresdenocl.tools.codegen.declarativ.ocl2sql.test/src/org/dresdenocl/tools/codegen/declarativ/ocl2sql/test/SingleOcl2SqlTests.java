/*
Copyright (C) 2008-2011 by Bjoern Freitag (Bjoern.Freitag@mail.zih.tu-dresden.de)

This file is part of the OCL 2 SQL Code Generator of Dresden OCL for Eclipse.

Dresden OCL for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */

package org.dresdenocl.tools.codegen.declarativ.ocl2sql.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.Ocl2SqlPlugin;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.CarOcl2SqlTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.CarOcl2Sql_optimizeTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.SingleOcl2Sql_notoptimizeTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.SingleOcl2Sql_optimizeTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.fast.Ocl2SqlEcoreTest;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.test.tests.fast.Ocl2SqlUMLTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>
 * Provides a jUnit Test Suite containing all plug-in tests of the OCL 2Sql Code
 * transformer ({@link Ocl2SqlPlugin}).
 * </p>
 * 
 * @author Bjoern Freitag
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ SingleOcl2Sql_notoptimizeTest.class,
		SingleOcl2Sql_optimizeTest.class, Ocl2SqlUMLTest.class,Ocl2SqlEcoreTest.class,
		CarOcl2SqlTest.class, CarOcl2Sql_optimizeTest.class })
public class SingleOcl2SqlTests extends AbstractDresdenOclTest {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}
