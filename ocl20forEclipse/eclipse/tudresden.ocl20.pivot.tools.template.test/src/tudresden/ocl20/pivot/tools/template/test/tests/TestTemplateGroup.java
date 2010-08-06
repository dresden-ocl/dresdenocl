/*
 * TestTemplatGroup.java
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.tools.template.ITemplate;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

/**
 * This test will test the class ITemplateGroupRegistry.java and ITemplateGroup
 * of the package tudresden.ocl20.pivot.tools.template.
 * 
 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroup
 * @see tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry
 */
public class TestTemplateGroup {

	private static String templateEngineName = "StringTemplate";

	private static ITemplateGroup general = null;

	private static List<ITemplateGroup> tempGroup;

	@BeforeClass
	public static void class_setup() {

		LinkedList<URL> groups = new LinkedList<URL>();
		groups.add(TestStringTemplateEngine.class
				.getResource("/resources/templates/testGeneral.stg"));
		tempGroup = TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups();
		for (ITemplateGroup tg : tempGroup) {
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(tg);
		}
		try {
			general =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"Test1", templateEngineName, null);
			general.addFiles(groups);
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(general);
		} catch (TemplateException e) {
			fail("Can't set a template group!");
		}
	}

	@Before
	public void setup() {

		TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(general);
		int size =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(1, size);
	}

	@After
	public void tear_down() {

		Iterator<ITemplateGroup> iterTempEng =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups()
						.iterator();
		while (iterTempEng.hasNext()) {
			TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(
					iterTempEng.next());
		}
		int size =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(0, size);
	}

	@AfterClass
	public static void class_tear_down() {

		for (ITemplateGroup tg : tempGroup) {
			TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(tg);
		}

	}

	/**
	 * <p>
	 * Implemented test checkAddGroup.<br />
	 * <br/>
	 * Tests the ITemplateGroupRegistry for adding new TemplateGroups.
	 * </p>
	 * 
	 */
	@Test
	public void checkAddGroup() {

		ITemplateGroup test =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup("Test1");
		assertNotNull(test);

		assertEquals(test.getDisplayName(), "Test1");
		assertSame(test, general);

		test = TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup("Test2");
		assertNull(test);

	}

	/**
	 * <p>
	 * Implemented test checkRemoveGroup.<br />
	 * <br/>
	 * Tests the ITemplateGroupRegistry for removing TemplateGroups.
	 * </p>
	 * 
	 */
	@Test
	public void checkRemoveGroup() {

		TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup("Test2");
		int size =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(1, size);
		ITemplateGroup temp =
				TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup("Test1");

		TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup(temp);
		size = TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(0, size);

		TemplatePlugin.getTemplateGroupRegistry().addTemplateGroup(temp);
		size = TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(1, size);

		TemplatePlugin.getTemplateGroupRegistry().removeTemplateGroup("Test1");
		size = TemplatePlugin.getTemplateGroupRegistry().getTemplateGroups().size();
		assertEquals(0, size);
	}

	/**
	 * <p>
	 * Implemented test checkGetTemplates.<br />
	 * <br/>
	 * Tests the ITemplateGroup for get a Template.
	 * </p>
	 * 
	 */
	@Test
	public void checkGetTemplates() {

		ITemplate temp = general.getTemplate("specific");
		assertEquals("generalTemplate", temp.toString());

		URL secondTemp =
				TestStringTemplateEngine.class
						.getResource("/resources/templates/testSpecific.stg");
		ITemplateGroup testSuper1 = null;
		try {
			testSuper1 =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"TestSuper1", templateEngineName, general);
			testSuper1.addFile(secondTemp);
		} catch (TemplateException e) {
			fail("Can't set TemplateGroup testSuper1");
		}

		temp = testSuper1.getTemplate("specific");
		assertEquals("specificTemplate", temp.toString());

		temp = general.getTemplate("specific2");
		assertNull(temp);

		ITemplateGroup testSuper2 = null;
		try {
			testSuper2 =
					TemplatePlugin.getTemplateGroupRegistry().addDefaultTemplateGroup(
							"TestSuper2", templateEngineName, testSuper1);
			testSuper2.addFile(TestStringTemplateEngine.class
					.getResource("/resources/templates/testGeneral.stg"));
		} catch (TemplateException e) {
			fail("Can't set TemplateGroup testSuper2");
		}

		temp = testSuper2.getTemplate("specific2");
		assertNotNull(temp);
		assertEquals("specificTemplate", temp.toString());

	}

}
