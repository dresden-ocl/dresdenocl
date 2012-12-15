/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.metamodels.ecore.test.tests;

import org.junit.BeforeClass;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.ecore.test.EcoreMetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestSuite;

/**
 * <p>
 * This class tests the {@link EcoreMetamodelPlugin} using the
 * {@link MetaModelTestSuite}.
 * 
 * @author Claas Wilke
 */
@Suite.SuiteClasses(value = { MetaModelTestSuite.class, TestEcoreDataType.class })
public class TestEcoreMetaModel extends MetaModelTestSuite {

	/** The ID of the {@link IMetamodel} which shall be tested. */
	private static final String META_MODEL_ID = EcoreMetamodelPlugin.ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODEL_PATH = "model/testModel.ecore";

	/**
	 * <p>
	 * Prepares the {@link MetaModelTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		MetaModelTestPlugin.prepareTest(EcoreMetaModelTestPlugin.PLUGIN_ID,
				TEST_MODEL_PATH, META_MODEL_ID);
	}

}