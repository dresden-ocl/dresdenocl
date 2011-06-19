/*
 * Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de) This file is part of
 * the UML2 Meta Model of Dresden OCL. Dresden OCL is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.xsd.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.Arrays;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * This class tests the correct adaptation of an XSD shipped by Fraunhofer ISOB.
 * 
 * @author Claas Wilke
 */
public class TestXmlSchemaIOSB extends AbstractDresdenOclTest {

	protected static IModel modelUnderTest;

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractDresdenOclTest.setUp();
		File modelFile = AbstractDresdenOclTest.getFile("model/iosb.xsd",
				XmlSchemaMetamodelTestPlugin.ID);
		modelUnderTest = Ocl2ForEclipseFacade.getModel(modelFile,
				XSDMetamodelPlugin.ID);
	}

	@AfterClass
	public static void tearDown() {
		modelUnderTest = null;
	}

	@Test
	public void testNamespaceIosb01() throws Exception {
		Namespace namespace = modelUnderTest
				.findNamespace(Arrays.asList(new String[] {
						ModelConstants.ROOT_PACKAGE_NAME, "Iosb" }));

		assertNotNull(namespace);
	}

	@Test
	@Ignore
	public void testTypeCAEXFile01() throws Exception {
		Type type = modelUnderTest.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "Iosb", "CAEXFileType" }));

		assertNotNull(type);

		assertNotNull(type.lookupProperty("externalReference"));
		assertNotNull(type.lookupProperty("instanceHierarchy"));
		assertNotNull(type.lookupProperty("interfaceClassLib"));
		assertNotNull(type.lookupProperty("roleClassLib"));
		assertNotNull(type.lookupProperty("systemUnitClassLib"));
		assertNotNull(type.lookupProperty("fileName"));
		assertNotNull(type.lookupProperty("schemaVersion"));
		assertNotNull(type.lookupProperty("changeMode"));
		/*
		 * FIXME Claas: Obviously EMFs XSD adapter does not support recursive
		 * structures within XSD files. This seems to be the reason of this
		 * failure here.
		 */
		assertNotNull(type.lookupProperty("additionalInformation"));
	}

	@Test
	@Ignore
	public void testTypeCAEXBasicObject01() throws Exception {
		Type type = modelUnderTest.findType(Arrays.asList(new String[] {
				ModelConstants.ROOT_PACKAGE_NAME, "Iosb", "CAEXBasicObject" }));

		assertNotNull(type);

		/*
		 * FIXME Claas: Obviously EMFs XSD adapter does not support recursive
		 * structures within XSD files. This seems to be the reason of this
		 * failure here.
		 */
		assertNotNull(type.lookupProperty("additionalInformation"));
	}
}