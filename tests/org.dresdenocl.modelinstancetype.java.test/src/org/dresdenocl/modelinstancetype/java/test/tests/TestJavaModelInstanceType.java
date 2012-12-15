/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
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
package org.dresdenocl.modelinstancetype.java.test.tests;

import org.junit.BeforeClass;
import org.junit.runners.Suite;

import org.dresdenocl.modelinstancetype.java.JavaModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.java.test.JavaModelInstanceTypeTestPlugin;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestServices;
import org.dresdenocl.modelinstancetype.test.ModelInstanceTypeTestSuite;

/**
 * <p>
 * This class tests the {@link JavaModelInstanceTypePlugin} using the
 * {@link ModelInstanceTypeTestPlugin}.
 * 
 * @author Claas Wilke
 */
@Suite.SuiteClasses(value = { ModelInstanceTypeTestSuite.class })
public class TestJavaModelInstanceType extends ModelInstanceTypeTestSuite {

	/** The id of the {@link IModelInstanceTypeObject} which shall be tested. */
	private static final String MODEL_INSTANCE_ID =
			JavaModelInstanceTypePlugin.PLUGIN_ID;

	/** The path of the model which shall be tested. */
	private static final String TEST_MODELINSTANCE_PATH =
			"bin/org/dresdenocl/modelinstancetype/java/test/modelinstance/ProviderClass.class";

	/**
	 * <p>
	 * Prepares the {@link ModelInstanceTypeTestSuite}.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		ModelInstanceTypeTestPlugin.prepareTest(
				JavaModelInstanceTypeTestPlugin.PLUGIN_ID, TEST_MODELINSTANCE_PATH,
				MODEL_INSTANCE_ID);

		ModelInstanceTypeTestServices.getInstance().setBooleanPropertyCounter(2);
		ModelInstanceTypeTestServices.getInstance().setIntegerPropertyCounter(10);
		ModelInstanceTypeTestServices.getInstance().setRealPropertyCounter(4);
		ModelInstanceTypeTestServices.getInstance().setStringPropertyCounter(4);
		ModelInstanceTypeTestServices.getInstance().setSequencePropertyCounter(2);
	}
}