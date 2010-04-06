/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
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
package tudresden.ocl20.pivot.metamodels.ecore.test.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.ecore.test.EcoreMetaModelTestPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that test the adaptation of Classes contained in a
 * reference Jar archive.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelImport {

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @return The found {@link File} object.
	 */
	private static File getFile(String path) {

		String filePath;
		filePath = EcoreMetaModelTestPlugin.getDefault().getBundle()
				.getLocation();
		/* Remove 'reference:file:/' */
		filePath = filePath.substring(16);

		filePath += EcoreMetaModelTestPlugin.getDefault().getBundle()
				.getResource(path).getPath().substring(1);

		File constraintFile;
		constraintFile = new File(filePath);

		assertTrue(constraintFile.exists());

		return constraintFile;
	}

	/**
	 * <p>
	 * Tests the adaptation of a provider class referencing a Jar archive.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testReferencedModel01() throws IllegalArgumentException,
			ModelAccessException {

		String msg;
		msg = "The adaptation of referenced Ecore models seems to be wrong. ";

		File modelFile;
		modelFile = TestModelImport.getFile("model/test01.ecore");

		IModel model;
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL);

		assertNotNull(msg, model);

		/*
		 * Try to find a type that is referenced but not defined by the model
		 * itself.
		 */
		Type testTypeClass;
		testTypeClass = model.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "package1", "package2",
				"TestTypeClass1" }));
		assertNotNull(msg, testTypeClass);
	}
}