/*
Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@mailbox.tu-dresden.de)

This file is part of the CWMrelational Meta Model of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.metamodels.cwmrelational.test.tests;

import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.metamodels.cwmrelational.test.CWMrelationalMetaModelTestPlugin;
import org.dresdenocl.metamodels.test.MetaModelTestPlugin;
import org.dresdenocl.metamodels.test.MetaModelTestServices;
import org.dresdenocl.metamodels.test.MetaModelTestSuite;
import org.dresdenocl.model.metamodel.IMetamodel;
import org.junit.BeforeClass;
import org.junit.runners.Suite;

/**
 * <p>
 * This class tests the {@link CWMMetamodelPlugin} using the
 * {@link MetaModelTestSuite}.
 * 
 * @author Bjoern Freitag
 */
@Suite.SuiteClasses(value = { MetaModelTestSuite.class })
public class TestCWMMetaModel extends MetaModelTestSuite {

	/** The ID of the {@link IMetamodel} which shall be tested. */
	private static final String META_MODEL_ID = CWMMetamodelPlugin.ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODEL_PATH = "model/testModel.relational";

	/**
	 * <p>
	 * Prepares the {@link MetaModelTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		MetaModelTestPlugin.prepareTest(CWMrelationalMetaModelTestPlugin.PLUGIN_ID,
				TEST_MODEL_PATH, META_MODEL_ID);
		MetaModelTestServices.supportsNoOperations = true;
		MetaModelTestServices.supportsNoInheritances = true;
	}

}