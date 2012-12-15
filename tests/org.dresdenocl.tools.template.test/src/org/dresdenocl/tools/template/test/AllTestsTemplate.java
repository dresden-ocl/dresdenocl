/*
 * AllTestsTemplate.java
 * 
 * Copyright (c) 2010 Bjoern Freitag
 * Contact: <bjoernfreitag@googlemail.com>
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

package org.dresdenocl.tools.template.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.tools.template.test.tests.TestStringTemplateEngine;
import org.dresdenocl.tools.template.test.tests.TestTemplateGroup;
import org.dresdenocl.tools.template.test.tests.TestTemplateEngineRegistry;

/**
 * This TestSuite runs all Tests testing the packages
 * <code>org.dresdenocl.tools.template</code> of the Dresden OCL2 Toolkit
 * 
 * @author Bjoern Freitag
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestTemplateEngineRegistry.class,
		TestStringTemplateEngine.class, TestTemplateGroup.class })
public class AllTestsTemplate extends AbstractDresdenOclTest {
	// this class remains completely empty,
	// being used only as a holder for the above annotations
}