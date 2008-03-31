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

package tudresden.ocl20.tests.codegen;

import tudresden.ocl20.tests.codegen.decl.AllTestsDecl;
import tudresden.ocl20.tests.codegen.sql.AllTestsSql;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This TestSuite runs all Tests testing the package
 * <code>tudresden.ocl20.codegen</code> and its subpackages
 * of the Dresden OCL2 Toolkit
 * 
 * @author Claas Wilke
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AllTestsDecl.class,
	AllTestsSql.class
})
public class AllTestsCodegen {
    // this class remains completely empty, 
    // being used only as a holder for the above annotations
}
