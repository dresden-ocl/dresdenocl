/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the XML Model Instance Plug-in Test Suite of Dresden OCL2
for Eclipse.

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

package org.dresdenocl.modelinstancetype.xml.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>
 * This class contains all tests of the {@link XmlModelInstanceTypeTestPlugin}.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestBugFix01.class, TestBugFix02.class,
		TestBugFix03.class, TestBugFix04.class, TestValueProperty.class,
		TestXmlModelInstanceType.class })
public class AllXmlInstanceTests extends AbstractDresdenOclTest {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}