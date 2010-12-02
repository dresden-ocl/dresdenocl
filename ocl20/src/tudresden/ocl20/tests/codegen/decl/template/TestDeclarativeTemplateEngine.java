/*
 * TestDeclarativeTemplateEngine.java
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

package tudresden.ocl20.tests.codegen.decl.template;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import tudresden.ocl20.codegen.decl.template.DeclarativeTemplate;
import tudresden.ocl20.codegen.decl.template.DeclarativeTemplateEngine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This test will test the class DeclarativeTemplateEngine.java of the package
 * tudresden.ocl20.codegen.sql.codegen.template.
 * 
 *@see tudresden.ocl20.codegen.decl.codegen.template.DeclarativeTemplateEngine
 *@see tudresden.ocl20.codegen.decl.codegen.template.TemplateEngine
 */
/*
 * JavaDoc added and changed to jUnit4 by Claas Wilke in 2007
 */
public class TestDeclarativeTemplateEngine {

	/**
	 * <p>Implemented test run 1.<br /><br/>
	 * Tests the DeclarativeTemplateEngine with two templates.</p>
	 * 
	 * <p><strong>ATTENTION: This test case uses some templates locate
	 * in the <code>resources</code> folder of the Dresden OCL2 Toolkit!
	 * Make sure, that the <code>resources</code> folder is listed as a
	 * library in the classpath of your project!</strong></p>
	 */
	@Test public void run1() {
		try {
			// Create a List with templates and add the first template
			LinkedList<String> groups = new LinkedList<String>();
			groups.add("decl/test/templates/testGeneral.stg");
			
			// Create DeclarativeTemplateEngine with the List containing one template
			DeclarativeTemplateEngine general = new DeclarativeTemplateEngine(groups);
			
			// Check the operations getTemplate with the parameters 'general' and 'specific'
			// The called operation toString of the template should return the value
			// 'generalTemplate' in both cases (as specified in the file testGeneral.stg).
			DeclarativeTemplate generalTemplate2 = general.getTemplate("general");
			assertEquals(generalTemplate2.toString(), "generalTemplate");
			
			DeclarativeTemplate specificTemplate2 = general.getTemplate("specific");
			assertEquals(specificTemplate2.toString(), "generalTemplate");
			
			// Add the second template to the List of templates and create a new
			// DeclarativeTemplateEngine
			groups.add("decl/test/templates/testSpecific.stg");
			DeclarativeTemplateEngine specific = new DeclarativeTemplateEngine(groups);			
		
			// Check the operations getTemplate with the parameters 'general' and 'specific'
			// The called operation toString of the template should return the value
			// 'generalTemplate' in both cases (as specified in the files testGeneral.stg 
			// and testSpecific.stg).
			DeclarativeTemplate generalTemplate = specific.getTemplate("general");
			assertEquals(generalTemplate.toString(), "generalTemplate");
			
			DeclarativeTemplate specificTemplate = specific.getTemplate("specific");
			assertEquals(specificTemplate.toString(), "specificTemplate");			
		} 
		
		// Thrown if an empty list was used to create a DeclarativeTemplateEngine
		catch (FileNotFoundException e) {
			fail("Tried to create a DeclarativeTemplateEngine with an empty List of templates.");
		}	
		
		// If one of the templates 'testGeneral.stg' and 'testSpecific.stg' could not
		// be loaded, a NullPointerException is thrown by the DeclarativeTemplateEngine
		// catch this Exception and let the testCase fail.
		catch (NullPointerException e) {
			fail("At least one of the templates 'testGeneral.stg' and 'testSpecific.stg' " +
					"could not be found. Make sure, that the resource folder is listed in your " +
					"classpath.");	
		}
	}
	
}
