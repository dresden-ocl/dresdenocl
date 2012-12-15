/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.tools.codegen.ocl2java.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.tools.codegen.ocl2java.Ocl2JavaPlugin;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.FragmentTest;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.InstrumentationTest;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.constraintkinds.AllConstraintsKindTests;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.context.AllContextTests;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.expressions.AllExpressionsTests;
import org.dresdenocl.tools.codegen.ocl2java.test.tests.standardlibrary.AllStandardLibraryTests;

/**
 * <p>
 * Provides a jUnit Test Suite containing all plug-in tests of the OCL 2 Java
 * Code transformer ({@link Ocl2JavaPlugin}).
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AllConstraintsKindTests.class, AllContextTests.class,
		AllStandardLibraryTests.class, AllExpressionsTests.class,
		InstrumentationTest.class, FragmentTest.class })
public class AllOcl2JavaTests extends AbstractDresdenOclTest {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}
