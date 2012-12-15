/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.metamodels.test.tests.TestEnumeration;
import org.dresdenocl.metamodels.test.tests.TestEnumerationLiteral;
import org.dresdenocl.metamodels.test.tests.TestModel;
import org.dresdenocl.metamodels.test.tests.TestNamespace;
import org.dresdenocl.metamodels.test.tests.TestOperation;
import org.dresdenocl.metamodels.test.tests.TestParameter;
import org.dresdenocl.metamodels.test.tests.TestPrimitiveType;
import org.dresdenocl.metamodels.test.tests.TestProperty;
import org.dresdenocl.metamodels.test.tests.TestType;
import org.dresdenocl.model.metamodel.IMetamodel;

/**
 * <p>
 * Contains all test cases of the {@link IMetamodel} test suite.
 * </p>
 * 
 * <p>
 * This test suite can be parameterized by extending it and calling the method
 * {@link MetaModelTestPlugin#prepareTest(String, String, String)}.
 * </p>
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestEnumeration.class, TestEnumerationLiteral.class,
		TestModel.class, TestNamespace.class, TestType.class,
		TestOperation.class, TestParameter.class, TestPrimitiveType.class,
		TestProperty.class })
public class MetaModelTestSuite extends AbstractDresdenOclTest {
	/*
	 * This class remains completely empty, being used only as a holder for the
	 * above annotations.
	 */
}