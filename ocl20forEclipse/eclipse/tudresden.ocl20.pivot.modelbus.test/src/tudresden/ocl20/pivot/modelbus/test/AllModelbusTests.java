/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Test Suite of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.modelbus.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.modelbus.test.metamodel.AllMetaModelTests;
import tudresden.ocl20.pivot.modelbus.test.model.AllModelTests;
import tudresden.ocl20.pivot.modelbus.test.modelinstance.AllModelInstanceTests;

/**
 * <p>
 * Provides a jUnit Test Suite containing all plug-in tests of the Model Bus.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AllMetaModelTests.class, AllModelTests.class,
		AllModelInstanceTests.class })
public class AllModelbusTests {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}