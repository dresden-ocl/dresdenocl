/*
 * TestStringTemplateEngine.java
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

package tudresden.ocl20.pivot.tools.template.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.LinkedList;

import org.junit.Test;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * This test will test the class StringTemplateEngine.java of the package
 * tudresden.ocl20.pivot.tools.template.stringtemplate.
 * 
 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine
 */
public class TestStringTemplateEngine {

	/**
	 * <p>
	 * Implemented test run 1.<br />
	 * <br/>
	 * Tests the StringTemplateEngine with two templates.
	 * </p>
	 * 
	 */
	@Test
	public void run1() {

		// Create a List with templates and add the first template
		LinkedList<URL> groups = new LinkedList<URL>();
		groups.add(TestStringTemplateEngine.class
				.getResource("/resources/templates/testGeneral.stg"));

		// Create DeclarativeTemplateEngine with the List containing one template
		ITemplateGroup general = null;
		try {
			general =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Test1", "StringTemplate", null);
			general.addFiles(groups);
			// Check the operations getTemplate with the parameters 'general' and
			// 'specific'
			// The called operation toString of the template should return the value
			// 'generalTemplate' in both cases (as specified in the file
			// testGeneral.stg).
			ITemplate generalTemplate2 = general.getTemplate("general");
			assertEquals(generalTemplate2.toString(), "generalTemplate");

			ITemplate specificTemplate2 = general.getTemplate("specific");
			assertEquals(specificTemplate2.toString(), "generalTemplate");
		} catch (TemplateException e) {
			e.printStackTrace();
			fail("Can't initialize STE general");
		}

		// Add the second template to the List of templates and create a new
		groups.add(TestStringTemplateEngine.class
				.getResource("/resources/templates/testSpecific.stg"));
		ITemplateGroup specific = null;
		try {
			specific =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Test2", "StringTemplate", null);
			specific.addFiles(groups);
			ITemplate generalTemplate = specific.getTemplate("general");
			assertEquals(generalTemplate.toString(), "generalTemplate");

			ITemplate specificTemplate = specific.getTemplate("specific");
			assertEquals(specificTemplate.toString(), "specificTemplate");

		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Can't initialize STE specific");
		}
		if (specific != null)
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(specific);
		if (general != null)
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(general);

	}

}
