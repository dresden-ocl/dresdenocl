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
package tudresden.ocl20.pivot.metamodels.uml2.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.uml2.test.UML2MetaModelTestPlugin;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

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
		assertEquals(IModelBusConstants.ROOT_PACKAGE_NAME, root.getName());

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
	 * Tests to load a model whose root package is a package. This package should
	 * be adapted correctly.
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
		assertEquals(IModelBusConstants.ROOT_PACKAGE_NAME, root.getName());

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
	 * Tests to load a model whose root package is a profile package. This package
	 * should not be adapted but its children packages should be adapted
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
		assertEquals(IModelBusConstants.ROOT_PACKAGE_NAME, root.getName());
	
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
	 *          The relative path in this plug-in to the model that shall be
	 *          loaded.
	 * @return The current {@link IModel} or <code>null</code>.
	 * @throws ModelAccessException
	 */
	private IModel getUML2Model(String path) throws ModelAccessException {

		IModel result;

		String bundleDirectory;
		File modelFile;

		/* Get the bundle location for the model files. */
		bundleDirectory =
				UML2MetaModelTestPlugin.getDefault().getBundle().getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		bundleDirectory = bundleDirectory.substring(15);

		modelFile = new File(bundleDirectory + path);

		/* Check if the given file does not exist. */
		if (!modelFile.exists()) {
			throw new RuntimeException("The model for the path " + bundleDirectory
					+ path + " cannot be found.");
		}

		/* Else try to load the model. */
		else {

			IMetamodel metaModel;

			/* Get the metaModel. */
			metaModel =
					ModelBusPlugin.getMetamodelRegistry().getMetamodel(
							UML2MetamodelPlugin.ID);

			if (metaModel != null) {
				result = metaModel.getModelProvider().getModel(modelFile);
			}

			else {
				throw new RuntimeException("The meta-model " + UML2MetamodelPlugin.ID
						+ " has not been found.");
			}
		}
		// end else.

		return result;
	}
}