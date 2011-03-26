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
package tudresden.ocl20.pivot.metamodels.xsd.test;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.xsd.XSDPackage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestSuite;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.xsd.internal.provider.XSDModelProvider;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * This class tests the {@link UML2MetamodelPlugin} using the
 * {@link MetaModelTestSuite}.
 * </p>
 * 
 * @author Claas Wilke
 */
@Suite.SuiteClasses(value = { MetaModelTestSuite.class })
public class TestXmlSchemaMetaModel extends MetaModelTestSuite {

	/** The id of the {@link IMetamodel} which shall be tested. */
	private static final String META_MODEL_ID = XSDMetamodelPlugin.ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODEL_PATH = "model/package1.xsd";

	/**
	 * <p>
	 * Prepares the {@link MetaModelTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		/* Problably register the XSD metamodel. */
		IMetamodel xsdMetamodel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(META_MODEL_ID);
		if (xsdMetamodel == null) {
			xsdMetamodel = new IMetamodel() {

				IModelProvider provider = new XSDModelProvider();

				public String getName() {
					return META_MODEL_ID;
				}

				public IModelProvider getModelProvider() {
					return provider;
				}

				public String getId() {
					return META_MODEL_ID;
				}
			};
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(xsdMetamodel);
		}

		/* Probably register the XSD resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				"xsd") == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"xsd", new XMIResourceFactoryImpl() {
						public Resource createResource(URI uri) {
							XMIResource xmiResource = new XMIResourceImpl(uri);
							return xmiResource;
						}
					});
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(XSDPackage.eNS_PREFIX,
					XSDPackage.eINSTANCE);
		}
		// no else.

		MetaModelTestServices.capitalizePackageNames = true;

		MetaModelTestPlugin.prepareTest(XmlSchemaMetamodelTestPlugin.ID,
				TEST_MODEL_PATH, META_MODEL_ID);
	}

	@AfterClass
	public static void tearDown() {
		MetaModelTestServices.capitalizePackageNames = false;
	}
}