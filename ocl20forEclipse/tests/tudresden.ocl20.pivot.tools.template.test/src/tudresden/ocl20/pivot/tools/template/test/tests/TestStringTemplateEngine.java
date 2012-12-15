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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tudresden.ocl20.pivot.tools.template.test.TemplateTestPlugin;

/**
 * This test will test the class StringTemplateEngine.java of the package
 * tudresden.ocl20.pivot.tools.template.stringtemplate.
 * 
 * @see tudresden.ocl20.pivot.tools.template.ITemplateEngine
 */
public class TestStringTemplateEngine extends AbstractDresdenOclTest {

	/**
	 * <p>
	 * Implemented test run 1.<br />
	 * <br/>
	 * Tests the StringTemplateEngine with two templates.
	 * </p>
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 * 
	 */
	@Test
	public void run1() throws MalformedURLException, IOException {

		// Create a List with templates and add the first template
		LinkedList<String> groups = new LinkedList<String>();

		groups.add(AbstractDresdenOclTest
				.getFile("/resources/templates/testGeneral.stg",
						TemplateTestPlugin.ID).getAbsolutePath());

		// Create DeclarativeTemplateEngine with the List containing one
		// template
		ITemplateGroup general = null;
		try {
			general = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("Test1", null);
			general.addFiles(groups);
			// Check the operations getTemplate with the parameters 'general'
			// and
			// 'specific'
			// The called operation toString of the template should return the
			// value
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
		groups.add(AbstractDresdenOclTest
				.getFile("/resources/templates/testSpecific.stg",
						TemplateTestPlugin.ID).getAbsolutePath());
		ITemplateGroup specific = null;
		try {
			specific = TemplatePlugin.getTemplateGroupRegistry()
					.addDefaultTemplateGroup("Test2", null);
			specific.addFiles(groups);
			ITemplate generalTemplate = specific.getTemplate("general");
			assertEquals(generalTemplate.toString(), "generalTemplate");

			ITemplate specificTemplate = specific.getTemplate("specific");
			assertEquals(specificTemplate.toString(), "specificTemplate");

		} catch (TemplateException e) {
			e.printStackTrace();
			fail("Can't initialize STE specific");
		}
		if (specific != null)
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(
					specific);
		if (general != null)
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(
					general);

	}

}
