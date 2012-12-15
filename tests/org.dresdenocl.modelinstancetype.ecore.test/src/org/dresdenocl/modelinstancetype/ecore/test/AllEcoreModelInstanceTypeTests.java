/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the EMF Ecore Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.ecore.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.modelinstancetype.ecore.test.tests.TestEcoreModelInstanceType;

/**
 * <p>
 * This class contains all tests of the {@link EcoreModelInstanceTypeTestPlugin}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestEcoreModelInstanceType.class })
public class AllEcoreModelInstanceTypeTests extends AbstractDresdenOclTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
	}
}