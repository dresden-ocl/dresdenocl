/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL2 Parser Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.ocl2parser.test.standardlibrary;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.osgi.framework.Bundle;

import org.dresdenocl.model.metamodel.IMetamodel;
import org.dresdenocl.model.IModel;

/**
 * <p>
 * Provides all tests of this package as a test suite.
 * </p>
 * 
 * @author Claas Wilke
 * @author Lars Schuetze
 */
/* Specify a runner class: Suite.class. */
@RunWith(Suite.class)
/* Specify an array of test classes. */
@Suite.SuiteClasses({ TestBag.class, TestBoolean.class, TestCollection.class,
		TestInteger.class, TestIterators.class, TestOclAny.class,
		TestOclType.class, TestOrderedSet.class, TestReal.class,
		TestSequence.class, TestSet.class, TestString.class,
		TestUnlimitedNatural.class })
public class AllStandardLibraryTests {

	/**
	 * The name of the {@link Bundle} that provides the model used during
	 * testing.
	 */
	public static final String MODEL_BUNDLE = "org.dresdenocl.ocl2parser.test";

	/** The path of the directory of the {@link IModel} used during testing. */
	public static final String MODEL_DIRECTORY = "resources/model/";

	/** The ID of the {@link IMetamodel} used during testing. */
	public static final String META_MODEL_ID = "org.dresdenocl.metamodels.uml2";
}