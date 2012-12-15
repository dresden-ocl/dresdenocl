/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the XML Model Instance Plug-in Test Suite of Dresden OCL2
for Eclipse.

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

package org.dresdenocl.modelinstancetype.xml.test;

import org.junit.BeforeClass;
import org.junit.runners.Suite;

import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestServices;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestSuite;
import org.dresdenocl.modelinstancetype.xml.XmlModelInstanceTypePlugin;

/**
 * <p>
 * This class tests the {@link JavaModelInstanceTypePlugin} using the
 * {@link ModelInstanceTypeTestPlugin}.
 * 
 * @author Claas Wilke
 */
@Suite.SuiteClasses(value = { ModelInstanceTypeTestSuite.class })
public class TestXmlModelInstanceType extends ModelInstanceTypeTestSuite {

	/** The id of the {@link IModelInstanceTypeObject} which shall be tested. */
	private static final String MODEL_INSTANCE_ID =
			XmlModelInstanceTypePlugin.PLUGIN_ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODELINSTANCE_PATH =
			"modelinstance/test.xml";

	/**
	 * <p>
	 * Prepares the {@link ModelInstanceTypeTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		ModelInstanceTypeTestPlugin.prepareTest(
				XmlModelInstanceTypeTestPlugin.PLUGIN_ID, TEST_MODELINSTANCE_PATH,
				MODEL_INSTANCE_ID);

		ModelInstanceTypeTestServices.getInstance().setBooleanPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setIntegerPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setRealPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setStringPropertyCounter(1);
		ModelInstanceTypeTestServices.getInstance().setSequencePropertyCounter(1);
	}
}