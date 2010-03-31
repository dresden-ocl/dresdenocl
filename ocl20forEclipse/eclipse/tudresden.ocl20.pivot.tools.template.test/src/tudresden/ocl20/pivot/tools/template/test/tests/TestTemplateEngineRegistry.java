/*
 * TestTemplateEngineRegistry.java
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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Iterator;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.tools.template.ITemplateEngine;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;

/**
 * This test will test the class ITemplateEngineRegistry.java of the package
 * tudresden.ocl20.pivot.tools.template.
 * 
 *@see tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry
 */
public class TestTemplateEngineRegistry {
	
	private static ITemplateEngine saveTemplateEngine = null;
	
	@BeforeClass
	public static void setUp() {
		if (saveTemplateEngine == null) {
			saveTemplateEngine = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().get(0);
		}
	}
	
	@After
	public void setStandardPlugin() {
		
		Iterator<ITemplateEngine> iterTempEng = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().iterator();
		while (iterTempEng.hasNext()) {
			TemplatePlugin.getTemplateEngineRegistry().removeTemplateEngine(iterTempEng.next());
		}
		TemplatePlugin.getTemplateEngineRegistry().addTemplateEngine(saveTemplateEngine);
	}

	/**
	 * <p>Implemented test checkInitializeEngineRegistry.<br /><br/>
	 * Tests the ITemplateEngineRegistry check are SmallTemplateEngine and StringTemplate on the registry.</p>
	 * 
	 */
	@Test 
	public void checkInitializeEngineRegistry() {
		/* check on Startup that one TemplateEngine is inside */
		int size = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().size();
		assertEquals(1, size);
			
		ITemplateEngine templateEngine1 = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("StringTemplate");
		assertNotNull(templateEngine1);
		
		assertEquals("StringTemplate",templateEngine1.getDisplayName());
		
		ITemplateEngine templateEngine2 = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("otherTemplateEngine");
		assertNull(templateEngine2);
		
		templateEngine2 = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().get(0);
		assertEquals(templateEngine1.getDisplayName(),templateEngine2.getDisplayName());
	
	}
	
	/**
	 * <p>Implemented test checkAddRemoveEningeRegistry.<br /><br/>
	 * Tests the ITemplateEngineRegistry check are SmallTemplateEngine and StringTemplate on the registry.</p>
	 * 
	 */
	@Test 
	public void checkAddRemoveEngineRegistry() {

		int size = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().size();
		assertEquals(size, 1);		
		TemplatePlugin.getTemplateEngineRegistry().addTemplateEngine(new SmallTemplateEngine());
		size = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().size();
		assertEquals(size, 2);
			
		ITemplateEngine templateEngine = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("otherTemplateEngine");
		assertNotNull(templateEngine);
		assertEquals(templateEngine.getDisplayName(),new SmallTemplateEngine().getDisplayName());
		
		TemplatePlugin.getTemplateEngineRegistry().removeTemplateEngine(templateEngine);
		size = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().size();
		assertEquals(size, 1);
		
		templateEngine = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("otherTemplateEngine");
		assertNull(templateEngine);
		
		templateEngine = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("StringTemplate");
		assertNotNull(templateEngine);
	
	}
	
	
	/**
	 * <p>Implemented test checkNewInstances.<br /><br/>
	 * Tests the ITemplateEngineRegistry check are SmallTemplateEngine and StringTemplate on the registry.</p>
	 * 
	 */
	@Test 
	public void checkNewInstances() {

		ITemplateEngine templateEngine1 = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("StringTemplate");
		ITemplateEngine templateEngine2 = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("StringTemplate");
		ITemplateEngine templateEngine3 = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().get(0);
		ITemplateEngine templateEngine4 = TemplatePlugin.getTemplateEngineRegistry().getTemplateEngines().get(0);
		
		assertNotSame(templateEngine1, templateEngine2);
		assertNotSame(templateEngine1, templateEngine3);
		assertNotSame(templateEngine1, templateEngine4);
		assertSame(templateEngine3,templateEngine4);
		
		ITemplateEngine templateEngine5 = new SmallTemplateEngine();
		TemplatePlugin.getTemplateEngineRegistry().addTemplateEngine(templateEngine5);
		ITemplateEngine templateEngine6 = TemplatePlugin.getTemplateEngineRegistry().getNewTemplateEngine("otherTemplateEngine");
		assertNotSame(templateEngine5, templateEngine6);
			
	}
	
	
	
}
