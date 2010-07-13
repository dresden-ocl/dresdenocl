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

package tudresden.ocl20.pivot.ocl2java.test.tests.standardlibrary;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.ocl2java.Ocl22JavaPlugin;

/**
 * <p>
 * Provides a jUnit Test Suite containing all standard library tests of the OCL
 * 2 Java Code transformer ({@link Ocl22JavaPlugin}).
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { TestBag.class, TestBoolean.class, TestCollection.class,
		TestInteger.class, TestOclAny.class, TestOrderedSet.class,
		TestReal.class, TestSequence.class, TestSet.class, TestString.class })
public class AllStandardLibraryTests {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}
