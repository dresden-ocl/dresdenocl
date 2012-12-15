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
package tudresden.ocl20.pivot.metamodels.uml2.test.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.Arrays;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.uml2.test.UML2MetaModelTestPlugin;
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
	 * @throws Exception
	 *             Thrown, if the opening fails.
	 */
	protected static File getFile(String path) throws Exception {

		return AbstractDresdenOclTest.getFile(path,
				UML2MetaModelTestPlugin.PLUGIN_ID);
	}

	/**
	 * <p>
	 * Tests the adaptation of a provider class referencing a Jar archive.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReferencedModel01() throws Exception, ModelAccessException {

		String msg;
		msg = "The adaptation of referenced UML models seems to be wrong. ";

		File modelFile;
		modelFile = TestModelImport.getFile("model/referenceTest/model02.uml");

		IModel model;
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);

		assertNotNull(msg, model);

		/*
		 * Try to find a type that is referenced but not defined by the model
		 * itself.
		 */
		Type referredType;
		referredType = model
				.findType(Arrays.asList(new String[] {
						ModelConstants.ROOT_PACKAGE_NAME, "package1",
						"ReferredType" }));
		assertNotNull(msg, referredType);

		modelFile = TestModelImport.getFile("model/referenceTest/model01.uml");

		/*
		 * Because the import is not bijective, the type resolving should not
		 * work vice versa.
		 */
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);

		assertNotNull(msg, model);

		/*
		 * Try to find a type that is referenced but not defined by the model
		 * itself.
		 */
		Type testReferredType;
		testReferredType = model.findType(Arrays
				.asList(new String[] { ModelConstants.ROOT_PACKAGE_NAME,
						"package2", "ReferencingType" }));
		assertNull(msg, testReferredType);
	}

	/**
	 * <p>
	 * Tests the adaptation of a provider class referencing a Jar archive.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReferencedModel02() throws Exception {

		String msg;
		msg = "The adaptation of referenced UML models seems to be wrong. ";

		File modelFile;
		modelFile = TestModelImport.getFile("model/referenceTest/model04.uml");

		IModel model;
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);

		assertNotNull(msg, model);

		/*
		 * Try to find a type that is referenced but not defined by the model
		 * itself.
		 */
		Type referredType;
		referredType = model.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "ReferredType" }));
		assertNotNull(msg, referredType);

		modelFile = TestModelImport.getFile("model/referenceTest/model03.uml");

		/*
		 * Because the import is not bijective, the type resolving should not
		 * work vice versa.
		 */
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.UML2_MetaModel);

		assertNotNull(msg, model);

		/*
		 * Try to find a type that is referenced but not defined by the model
		 * itself.
		 */
		Type testReferredType;
		testReferredType = model.findType(Arrays
				.asList(new String[] { ModelConstants.ROOT_PACKAGE_NAME,
						"package2", "ReferencingType" }));
		assertNull(msg, testReferredType);
	}
}