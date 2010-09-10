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

package tudresden.ocl20.pivot.tools.transformation.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;

/**
 * This test will test the class ITransformationRegistry.java of the package
 * tudresden.ocl20.pivot.tools.transformation.
 * 
 * @see tudresden.ocl20.pivot.tools.transformation.ITransformationRegistry
 */
public class TestTransformationRegistry {
	
	private ITransformation<?,?,?> itrans;
	
	@Before
	public void setUp() {
		itrans = TransformationFactory.getInstance().getTransformation("TestTrans", "", "");
		TransformationPlugin.getTransformationRegistry().removeTransformation(itrans);
	}

	@After
	public void tear_down() {
		if (!TransformationPlugin.getTransformationRegistry().getTransformationList().contains("TestTrans")) {
			TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		}

	}

	@Test
	public void checkAdd() {
		int size = TransformationPlugin.getTransformationRegistry().getTransformationList().size();
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		List<String> trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation isn't add.", trans.size(),size+1);
		assertTrue("The transformation isn't add correctly", trans.contains(itrans.getClass().getSimpleName()));
		
		//Check is transformation added twice
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation add extra.", trans.size(),size+1);
		
		//Check work extension add.
		assertNotNull("The extension add isn't work.",TransformationPlugin.getTransformationRegistry().getTransformationClass("TestFalseTrans"));
		
	}
	
	@Test 
	public void checkRemove() {
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		int size = TransformationPlugin.getTransformationRegistry().getTransformationList().size();
		TransformationPlugin.getTransformationRegistry().removeTransformation(itrans);
		List<String> trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation isn't add.", trans.size(),size-1);
		assertFalse("The transformation registry removes a other transformation", trans.contains(itrans.getClass().getSimpleName()));
		
		//Check is transformation remove twice
		TransformationPlugin.getTransformationRegistry().removeTransformation(itrans);
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation add extra.", trans.size(),size-1);
		
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		size = TransformationPlugin.getTransformationRegistry().getTransformationList().size();
		TransformationPlugin.getTransformationRegistry().removeTransformation(itrans.getClass().getSimpleName());
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation isn't add.", trans.size(),size-1);
		assertFalse("The transformation registry removes a other transformation", trans.contains(itrans.getClass().getSimpleName()));
		
		//Check is transformation remove twice
		TransformationPlugin.getTransformationRegistry().removeTransformation(itrans.getClass().getSimpleName());
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertEquals("The transformation add extra.", trans.size(),size-1);
	}
	
	@Test
	public void checkGetTransformationList() {
		List<String> trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertFalse("A extra transformation is in the list",trans.contains(itrans.getClass().getSimpleName()));
		TransformationPlugin.getTransformationRegistry().addTransformation(itrans);
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList();
		assertTrue("The transformation isn't in the list",trans.contains(itrans.getClass().getSimpleName()));
		
		//Check get with Types
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList(EObject.class, String.class, IOcl2CodeSettings.class);
		assertTrue("The transformation TestTrans isn't in the list",trans.contains(itrans.getClass().getSimpleName()));
		assertTrue("The parallel transformation isn't in the list with first parameter",trans.contains("TestParallelTrans"));
		assertFalse("A extra transformation is in the list",trans.contains("TestFalseTrans"));
		
		trans = TransformationPlugin.getTransformationRegistry().getTransformationList(EObject.class, EObject.class, IOcl2CodeSettings.class);
		assertTrue("The transformation TestFalseTrans isn't in the list",trans.contains("TestFalseTrans"));
		assertTrue("The parallel transformation isn't in the list with second parameter",trans.contains("TestParallelTrans"));
		assertFalse("A extra transformation is in the list",trans.contains(itrans.getClass().getSimpleName()));
	}
	
}
