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

import java.util.List;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.parser.SemanticException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Operation;

/**
 * <p>
 * Contains test cases that check that LetExpressions are parsed appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestLetExpressions {

	/**
	 * <p>
	 * A test case to check that a LetExpression is parsed appropriately.
	 * </p>
	 */
	@Test
	public void testLetPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive01.ocl";
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
	 * A test case to check that a LetExpression is parsed appropriately.
	 * </p>
	 */
	@Test
	public void testLetPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive02.ocl";
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
	 * A test case to check that a LetExpression is parsed appropriately.
	 * </p>
	 */
	@Test
	public void testLetPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive03.ocl";
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
	 * A test case to check that a LetExpression is parsed appropriately.
	 * </p>
	 */
	@Test
	public void testLetPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive04.ocl";
		modelFileName = "testmodel02.uml";

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
	 * A test case to check that a LetExpression is parsed appropriately.
	 * </p>
	 */
	@Test
	public void testLetPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive05.ocl";
		modelFileName = "testmodel02.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		List<Constraint> constraints = testPerformer.parseFile(oclFileName);

		/* Outer variable should be defined around inner variable. */
		assertNotNull(constraints);
		assertEquals(1, constraints.size());

		Constraint constraint = constraints.get(0);
		assertTrue(constraint.getSpecification() instanceof ExpressionInOcl);

		OclExpression exp = ((ExpressionInOcl) constraint.getSpecification())
				.getBodyExpression();
		assertTrue(exp instanceof LetExp);

		LetExp letExp = (LetExp) exp;
		Variable var = letExp.getVariable();
		assertEquals("outer", var.getName());

		exp = letExp.getIn();
		assertTrue(exp instanceof LetExp);

		letExp = (LetExp) exp;
		var = letExp.getVariable();
		assertEquals("inner", var.getName());

	}
	
	/**
	 * <p>
	 * A test case to check that a LetExpression is parsed appropriately with correct associativity (see Issue #14582).
	 * </p>
	 */
	@Test
	public void testLetPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letPositive06.ocl";
		modelFileName = "testmodel02.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		List<Constraint> constraints = testPerformer.parseFile(oclFileName);

		assertNotNull(constraints);
		assertEquals(1, constraints.size());

		Constraint constraint = constraints.get(0);
		assertTrue(constraint.getSpecification() instanceof ExpressionInOcl);

		OclExpression exp = ((ExpressionInOcl) constraint.getSpecification())
				.getBodyExpression();
		assertTrue(exp instanceof OperationCallExp);

		OperationCallExp operationCallExp = (OperationCallExp) exp;
		Operation operation = operationCallExp.getReferredOperation();
		assertEquals("+", operation.getName());

		exp = operationCallExp.getSource();
		assertTrue(exp instanceof PropertyCallExp);

		exp = operationCallExp.getArgument().get(0);
		LetExp letExp = (LetExp) exp;
		assertEquals("b", letExp.getVariable().getName());
		
		operationCallExp = (OperationCallExp) letExp.getIn();
		operation = operationCallExp.getReferredOperation();
		assertEquals("+", operation.getName());

		exp = operationCallExp.getSource();
		assertTrue(exp instanceof PropertyCallExp);
		
		exp = operationCallExp.getArgument().get(0);
		letExp = (LetExp) exp;
		assertEquals("c", letExp.getVariable().getName());
		
		operationCallExp = (OperationCallExp) letExp.getIn();
		operation = operationCallExp.getReferredOperation();
		assertEquals("+", operation.getName());
		
		exp = operationCallExp.getSource();
		assertTrue(exp instanceof PropertyCallExp);
		
		exp = operationCallExp.getArgument().get(0);
		assertTrue(exp instanceof PropertyCallExp);
	}

	/**
	 * <p>
	 * A test case to check that a LetExpression is not parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testLetNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/letNegative02.ocl";
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