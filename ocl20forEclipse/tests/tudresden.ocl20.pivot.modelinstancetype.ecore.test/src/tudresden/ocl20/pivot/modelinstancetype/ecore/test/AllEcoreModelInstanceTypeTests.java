/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the EMF Ecore Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.ecore.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.modelinstancetype.ecore.test.tests.TestEcoreModelInstanceType;
import tudresden.ocl20.pivot.modelinstancetype.test.testmodel.TestmodelPackage;

/**
 * <p>
 * This class contains all tests of the {@link EcoreModelInstanceTypeTestPlugin}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestEcoreModelInstanceType.class })
public class AllEcoreModelInstanceTypeTests extends AbstractDresdenOclTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();

		if (!Platform.isRunning()) {
			/* Probably register the Testmodel resource for EMF. */
			if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
					.get(TestmodelPackage.eNS_PREFIX) == null) {

				Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
						.put(TestmodelPackage.eNS_PREFIX,
								new XMIResourceFactoryImpl() {
									public Resource createResource(URI uri) {
										XMIResource xmiResource = new XMIResourceImpl(
												uri);
										return xmiResource;
									}
								});
			}
			// no else.

			if (EPackage.Registry.INSTANCE
					.getEPackage(TestmodelPackage.eNS_URI) == null) {
				EPackage.Registry.INSTANCE.put(TestmodelPackage.eNS_PREFIX,
						TestmodelPackage.eINSTANCE);
			}
			// no else.
		}
		// no else.
	}
}