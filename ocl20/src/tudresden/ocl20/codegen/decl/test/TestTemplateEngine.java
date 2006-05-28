/*
 * TestTemplateEngine.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
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

package tudresden.ocl20.codegen.decl.test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tudresden.ocl20.codegen.decl.template.DeclarativeTemplate;
import tudresden.ocl20.codegen.decl.template.DeclarativeTemplateEngine;


public class TestTemplateEngine extends TestCase {

	public TestTemplateEngine(String name) {
		super(name);
	}
	
	public void testRun1() {
		try {
			LinkedList<String> groups = new LinkedList<String>();
			groups.add("decl/test/templates/testGeneral.stg");
			DeclarativeTemplateEngine general = new DeclarativeTemplateEngine(groups);
			
			DeclarativeTemplate generalTemplate2 = general.getTemplate("general");
			assertEquals(generalTemplate2.toString(), "generalTemplate");
			
			DeclarativeTemplate specificTemplate2 = general.getTemplate("specific");
			assertEquals(specificTemplate2.toString(), "generalTemplate");
			
			
			groups.add("decl/test/templates/testSpecific.stg");
			DeclarativeTemplateEngine specific = new DeclarativeTemplateEngine(groups);			
		
			DeclarativeTemplate generalTemplate = specific.getTemplate("general");
			assertEquals(generalTemplate.toString(), "generalTemplate");
			
			DeclarativeTemplate specificTemplate = specific.getTemplate("specific");
			assertEquals(specificTemplate.toString(), "specificTemplate");
			
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}			
	}
	
	public static Test suite() {
		TestSuite testSuite = new TestSuite();

		testSuite.addTest(new TestTemplateEngine("testRun1"));
		
   		return testSuite;
	}
}
