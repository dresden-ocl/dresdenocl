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
import org.junit.Test;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.parser.SemanticException;

/**
 * <p>
 * Contains test cases that check that PropertyCallExpressions are parsed
 * appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPropertyCallExpressions extends AbstractDresdenOclTest {

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testPropertyCallExpressionPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyPositive06.ocl";
		modelFileName = "testmodel03.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPropertyCallExpressionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPropertyCallExpressionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPropertyCallExpressionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to check that a PropertyCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testPropertyCallExpressionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/propertyNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		// testPerformer.parseFile(oclFileName);

		StringBuffer code = new StringBuffer();
		code.append("package package1::package2\n");
		code.append("\n");
		code.append("-- Should fail. The second def contains a type error?\n");
		code.append("context Type1\n");
		code.append("def: mydefine : Integer = 100\n");
		code.append("\n");
		code.append("def: mystring : Integ = mydefine + 50\n");
		code.append("\n");
		code.append("endpackage\n");
		Ocl22Parser.INSTANCE.parseOclString(code.toString(),
				testPerformer.getCurrentModel(), false);
	}
}