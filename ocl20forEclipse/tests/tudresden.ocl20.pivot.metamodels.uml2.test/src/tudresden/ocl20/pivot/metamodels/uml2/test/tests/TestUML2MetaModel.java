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

import org.junit.BeforeClass;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestPlugin;
import tudresden.ocl20.pivot.metamodels.test.MetaModelTestSuite;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.uml2.test.UML2MetaModelTestPlugin;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;

/**
 * <p>
 * This class tests the {@link UML2MetamodelPlugin} using the
 * {@link MetaModelTestSuite}.
 * </p>
 * 
 * @author Claas Wilke
 */
@Suite.SuiteClasses(value = { MetaModelTestSuite.class,
		TestUmlTypePrimitiveType.class })
public class TestUML2MetaModel extends MetaModelTestSuite {

	/** The id of the {@link IMetamodel} which shall be tested. */
	private static final String META_MODEL_ID = UML2MetamodelPlugin.ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODEL_PATH = "model/testModel.uml";

	/**
	 * <p>
	 * Prepares the {@link MetaModelTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {
		MetaModelTestPlugin.prepareTest(UML2MetaModelTestPlugin.PLUGIN_ID,
				TEST_MODEL_PATH, META_MODEL_ID);
	}
}