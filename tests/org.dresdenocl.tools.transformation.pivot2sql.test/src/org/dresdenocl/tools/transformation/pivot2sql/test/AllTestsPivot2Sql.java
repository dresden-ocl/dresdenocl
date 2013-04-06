/*
 * AllTestsCodegen.java
 * 
 * Copyright (c) 2007 Claas Wilke
 * Contact: <claaswilke@gmx.net>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package org.dresdenocl.tools.transformation.pivot2sql.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.CWM2DdlTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2CWMtwoSchemaTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2CWMtypedTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2CWMverticalTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2DdlAndMappedModelEcoreTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2DdlAndMappedModelUMLTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2DdlTest;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.Pivot2MappedModelTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This TestSuite runs all Tests testing the package
 * <code>org.dresdenocl.tools.transformation.pivot2sql</code> and its
 * subpackages of the Dresden OCL2 Toolkit
 * 
 * @author Bjoern Freitag
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ Pivot2MappedModelTest.class, Pivot2CWMtypedTest.class,
	Pivot2CWMtwoSchemaTest.class, Pivot2CWMverticalTest.class, CWM2DdlTest.class, Pivot2DdlTest.class,
		Pivot2DdlAndMappedModelUMLTest.class ,Pivot2DdlAndMappedModelEcoreTest.class })
public class AllTestsPivot2Sql extends AbstractDresdenOclTest {
	// this class remains completely empty,
	// being used only as a holder for the above annotations
}
