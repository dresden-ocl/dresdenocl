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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.SemanticException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that check that OperationCallExpressions are parsed
 * appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOperationCallExpressions {

	/**
	 * <p>
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive01.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive02.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive03.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive04.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive05.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive07.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive08.ocl";
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
	 * A test case to check that a OperationCallExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testOperationCallExpressionPositive09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationPositive09.ocl";
		modelFileName = "testmodel04.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		List<Constraint> constraints = testPerformer.parseFile(oclFileName);

		assertEquals(1, constraints.size());
		Constraint constraint = constraints.get(0);

		OclExpression exp = ((ExpressionInOcl) constraint.getSpecification())
				.getBodyExpression();
		assertTrue(exp instanceof PropertyCallExp);

		PropertyCallExp propExp = (PropertyCallExp) exp;
		Property referredProperty = propExp.getReferredProperty();

		Type childClassType = testPerformer.getCurrentModel().findType(
				Arrays.asList(new String[] { "root", "test", "ChildClass" }));
		assertNotNull(childClassType);

		Property expectedProperty = null;
		for (Property property : childClassType.getOwnedProperty()) {
			if (property.getName().equals("sameName")) {
				expectedProperty = property;
				break;
			}
			// no else.
		}
		// end for.

		assertNotNull(expectedProperty);
		assertEquals(expectedProperty, referredProperty);
	}

	/**
	 * <p>
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative01.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative02.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative03.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative04.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative05.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative06.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative07.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative08.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative12() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative12.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testOperationCallExpressionNegative13() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative13.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative14() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative14.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative15() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative15.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative16() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative16.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative17() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative17.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative18() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative18.ocl";
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
	 * A test case to check that a OperationCallExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testOperationCallExpressionNegative19() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/calls/operationNegative19.ocl";
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
}