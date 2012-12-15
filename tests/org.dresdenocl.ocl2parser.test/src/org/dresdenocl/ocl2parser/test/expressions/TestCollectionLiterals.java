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

package org.dresdenocl.ocl2parser.test.expressions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.parser.SemanticException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;
import org.dresdenocl.pivotmodel.Feature;

/**
 * <p>
 * Contains test cases that check that CollectionLiteralExpressions are parsed
 * appropriately.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestCollectionLiterals {

	/**
	 * <p>
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive01.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive02.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive03.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive04.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive05.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive06.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive07.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive08.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive09.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		Collection<Constraint> constraints = testPerformer
				.parseFile(oclFileName);
		assertNotNull(constraints);
		assertEquals(1, constraints.size());

		Constraint constraint = constraints.iterator().next();
		assertEquals(ConstraintKind.DEFINITION, constraint.getKind());

		Feature definedFeature = constraint.getDefinedFeature();
		assertNotNull(definedFeature);
		assertEquals(
				EssentialOclPlugin
						.getOclLibraryProvider()
						.getOclLibrary()
						.getSetType(
								EssentialOclPlugin.getOclLibraryProvider()
										.getOclLibrary().getOclInteger()),
				definedFeature.getType());
	}

	/**
	 * <p>
	 * A test case to check that a CollectionLiteralExpression is parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testCollectionPositive10() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionPositive10.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllExpressionTests.META_MODEL_ID,
				AllExpressionTests.MODEL_BUNDLE,
				AllExpressionTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		Collection<Constraint> constraints = testPerformer
				.parseFile(oclFileName);
		assertNotNull(constraints);
		assertEquals(1, constraints.size());

		Constraint constraint = constraints.iterator().next();
		assertEquals(ConstraintKind.DEFINITION, constraint.getKind());

		Feature definedFeature = constraint.getDefinedFeature();
		assertNotNull(definedFeature);
		assertEquals(
				EssentialOclPlugin
						.getOclLibraryProvider()
						.getOclLibrary()
						.getSetType(
								EssentialOclPlugin.getOclLibraryProvider()
										.getOclLibrary().getOclVoid()),
				definedFeature.getType());
	}
	
	/**
	 * <p>
	 * A test case to check that a CollectionLiteralExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectionNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionNegative01.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectionNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionNegative02.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectionNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionNegative03.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionNegative04.ocl";
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
	 * A test case to check that a CollectionLiteralExpression is not parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testCollectionNegative05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/literals/collectionNegative05.ocl";
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