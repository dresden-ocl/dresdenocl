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
package org.dresdenocl.modelinstancetype.xml.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.model.IModel;
import org.dresdenocl.modelinstance.IModelInstance;

/**
 * <p>
 * Contains test cases for bug fix 01 (issued by Miriam Ebel).
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBugFix01 extends AbstractXmlModelInstanceTest {

	/**
	 * <p>
	 * Tests to load the model instance.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadModel() throws Exception {

		File modelFile;
		modelFile = TestBugFix01.getFile("model/bugfix01.xsd");

		IModel testModel = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.XSD_META_MODEL);
		assertNotNull(testModel);

		File modelInstanceFile;
		modelInstanceFile = TestBugFix01.getFile("modelinstance/bugfix01.xml");

		IModelInstance testModelInstance = Ocl2ForEclipseFacade
				.getModelInstance(modelInstanceFile, testModel,
						Ocl2ForEclipseFacade.XML_MODEL_INSTANCE_TYPE);

		assertNotNull(testModelInstance);
	}
}