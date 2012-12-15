/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the UML2 Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.uml2.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.metamodels.uml2.test.UML2MetaModelTestPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.pivotmodel.Namespace;

/**
 * <p>
 * Contains test cases that ensure that the root package of a UML2 model is
 * adapted correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestUML2Packages {

	/**
	 * <p>
	 * Tests to load a model whose root package is a model. This package should
	 * not be adapted but its children packages should be adapted correctly.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRootPackage01() throws ModelAccessException {

		IModel testModel;
		testModel = this.getUML2Model("/model/packageTest/model01.uml");

		Namespace root;
		root = testModel.getRootNamespace();

		assertNotNull(root);
		assertEquals(ModelConstants.ROOT_PACKAGE_NAME, root.getName());

		assertNull(root.getNestingNamespace());
		assertEquals(1, root.getNestedNamespace().size());

		Namespace package1;
		package1 = root.getNestedNamespace().get(0);

		assertNotNull(package1);
		assertEquals("package1", package1.getName());

		assertNotNull(package1.getNestingNamespace());
		assertEquals(root, package1.getNestingNamespace());
		assertEquals(1, package1.getNestedNamespace().size());

		Namespace package2;
		package2 = package1.getNestedNamespace().get(0);

		assertNotNull(package2);
		assertEquals("package2", package2.getName());

		assertNotNull(package2.getNestingNamespace());
		assertEquals(package1, package2.getNestingNamespace());
		assertEquals(0, package2.getNestedNamespace().size());
	}

	/**
	 * <p>
	 * Tests to load a model whose root package is a package. This package
	 * should be adapted correctly.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRootPackage02() throws ModelAccessException {

		IModel testModel;
		testModel = this.getUML2Model("/model/packageTest/model02.uml");

		Namespace root;
		root = testModel.getRootNamespace();

		assertNotNull(root);
		assertEquals(ModelConstants.ROOT_PACKAGE_NAME, root.getName());

		assertNull(root.getNestingNamespace());
		assertEquals(1, root.getNestedNamespace().size());

		Namespace package1;
		package1 = root.getNestedNamespace().get(0);

		assertNotNull(package1);
		assertEquals("package1", package1.getName());

		assertNotNull(package1.getNestingNamespace());
		assertEquals(root, package1.getNestingNamespace());
		assertEquals(1, package1.getNestedNamespace().size());

		Namespace package2;
		package2 = package1.getNestedNamespace().get(0);

		assertNotNull(package2);
		assertEquals("package2", package2.getName());

		assertNotNull(package2.getNestingNamespace());
		assertEquals(package1, package2.getNestingNamespace());
		assertEquals(0, package2.getNestedNamespace().size());
	}

	/**
	 * <p>
	 * Tests to load a model whose root package is a profile package. This
	 * package should not be adapted but its children packages should be adapted
	 * correctly.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testRootPackage03() throws ModelAccessException {

		IModel testModel;
		testModel = this.getUML2Model("/model/packageTest/model03.uml");

		Namespace root;
		root = testModel.getRootNamespace();

		assertNotNull(root);
		assertEquals(ModelConstants.ROOT_PACKAGE_NAME, root.getName());

		assertNull(root.getNestingNamespace());
		assertEquals(1, root.getNestedNamespace().size());

		Namespace package1;
		package1 = root.getNestedNamespace().get(0);

		assertNotNull(package1);
		assertEquals("package1", package1.getName());

		assertNotNull(package1.getNestingNamespace());
		assertEquals(root, package1.getNestingNamespace());
		assertEquals(1, package1.getNestedNamespace().size());

		Namespace package2;
		package2 = package1.getNestedNamespace().get(0);

		assertNotNull(package2);
		assertEquals("package2", package2.getName());

		assertNotNull(package2.getNestingNamespace());
		assertEquals(package1, package2.getNestingNamespace());
		assertEquals(0, package2.getNestedNamespace().size());
	}

	/**
	 * <p>
	 * Loads an IModel for a given path.
	 * </p>
	 * 
	 * @param path
	 *            The relative path in this plug-in to the model that shall be
	 *            loaded.
	 * @return The current {@link IModel} or <code>null</code>.
	 * @throws ModelAccessException
	 */
	private IModel getUML2Model(String path) throws ModelAccessException {

		IModel result;
		File modelFile;
		try {
			modelFile = AbstractDresdenOclTest.getFile(path,
					UML2MetaModelTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		result = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);

		return result;
	}
}