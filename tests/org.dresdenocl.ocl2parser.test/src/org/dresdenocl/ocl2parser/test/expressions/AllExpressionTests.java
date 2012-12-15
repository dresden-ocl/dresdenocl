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

package tudresden.ocl20.pivot.ocl2parser.test.expressions;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.IModel;

/**
 * <p>
 * Provides all tests of this package as a test suite.
 * </p>
 * 
 * @author Claas Wilke
 */
/* Specify a runner class: Suite.class. */
@RunWith(Suite.class)
/* Specify an array of test classes. */
@Suite.SuiteClasses({ TestBooleanLiterals.class, TestCollectionLiterals.class,
		TestEnumerationLiterals.class, TestIfExpressions.class,
		TestIntegerLiterals.class, TestInvalidLiterals.class,
		TestIterateExpressions.class, TestLetExpressions.class,
		TestNullLiterals.class, TestOperationCallExpressions.class,
		TestPropertyCallExpressions.class, TestRealLiterals.class,
		TestStringLiterals.class, TestTupleLiterals.class,
		TestTypeExpressions.class, TestVariableExpressions.class })
public class AllExpressionTests extends AbstractDresdenOclTest {

	/**
	 * The name of the {@link Bundle} that provides the model used during
	 * testing.
	 */
	public static final String MODEL_BUNDLE = "tudresden.ocl20.pivot.ocl2parser.test";

	/** The path of the directory of the {@link IModel} used during testing. */
	public static final String MODEL_DIRECTORY = "resources/model/";

	/** The ID of the {@link IMetamodel} used during testing. */
	public static final String META_MODEL_ID = "tudresden.ocl20.pivot.metamodels.uml2";
}