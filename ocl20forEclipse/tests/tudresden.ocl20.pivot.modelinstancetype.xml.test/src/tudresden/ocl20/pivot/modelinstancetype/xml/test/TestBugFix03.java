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
package tudresden.ocl20.pivot.modelinstancetype.xml.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases for bug fix 01 (issued by Miriam Ebel).
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBugFix03 extends AbstractXmlModelInstanceTest {

	/**
	 * <p>
	 * Tests to load the model instance.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBugFix03() throws Exception {

		File modelFile;
		modelFile = TestBugFix03.getFile("model/bugfix03.xsd");

		IModel testModel = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.XSD_META_MODEL);
		assertNotNull(testModel);

		File modelInstanceFile;
		modelInstanceFile = TestBugFix03.getFile("modelinstance/bugfix03.xml");

		IModelInstance testModelInstance = Ocl2ForEclipseFacade
				.getModelInstance(modelInstanceFile, testModel,
						Ocl2ForEclipseFacade.XML_MODEL_INSTANCE_TYPE);

		assertNotNull(testModelInstance);

		/* Get an attribute type instance. */
		Type attributeType = testModel.findType(Arrays.asList(new String[] { "root",
				"Bugfix03", "AttributeType" }));
		assertNotNull(attributeType);

		Set<IModelInstanceObject> attributeInstances = testModelInstance
				.getAllInstances(attributeType);
		assertNotNull(attributeInstances);
		assertTrue(attributeInstances.size() > 0);

		IModelInstanceObject anAttributeInstance = attributeInstances
				.iterator().next();

		/* Get the value of the property 'value'. */
		Property property = null;

		for (Property aProperty : attributeType.getOwnedProperty()) {
			if (aProperty.getName().equals("value")) {
				property = aProperty;
				break;
			}
			// no else.
		}
		// end for.

		IModelInstanceElement value = anAttributeInstance.getProperty(property);
		assertNotNull(value);
		assertTrue(value instanceof IModelInstanceObject);
		Object objectValue = ((IModelInstanceObject) value).getObject();

		assertNotNull(objectValue);
		assertEquals(1, objectValue);
	}
}