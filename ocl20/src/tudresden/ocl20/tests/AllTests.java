/*
 * TestOcl2.java
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

package tudresden.ocl20.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.tests.codegen.AllTestsCodegen;
import tudresden.ocl20.tests.core.AllTestsCore;
import tudresden.ocl20.tests.integration.AllTestsIntegration;
import tudresden.ocl20.tests.transformation.AllTestsTransformation;
import tudresden.ocl20.tests.util.AllTestsUtil;

/**
 * This TestSuite contains all Tests integrated into the source code of the
 * Dresden OCL2 Toolkit
 * 
 * <p>
 * <strong>ATTENTION: This test case uses some files located in the
 * <code>resources</code> folder of the Dresden OCL2 Toolkit! Make sure, that
 * the <code>resources</code> folder is listed as a library in the classpath
 * of your project!</strong>
 * </p>
 * 
 * <p>
 * <strong>ATTENTION: This test case uses the Java class <code>FileReader</code>
 * which seems to have some problems with paths containing white spaces. Make
 * sure, that your project folder and its absolute path does not contain any
 * white spaces!</strong>
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { AllTestsCodegen.class, AllTestsCore.class,
		AllTestsIntegration.class, AllTestsTransformation.class,
		AllTestsUtil.class
})
public class AllTests {
	// this class remains completely empty,
	// being used only as a holder for the above annotations
}
