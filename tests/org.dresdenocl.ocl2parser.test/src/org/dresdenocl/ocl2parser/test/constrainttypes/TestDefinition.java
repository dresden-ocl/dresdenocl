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

package org.dresdenocl.ocl2parser.test.constrainttypes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;
import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.parser.SemanticException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that check that the context of definitions is parsed
 * correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestDefinition extends AbstractDresdenOclTest {

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* Check that the defined property is a static one. */
		Type type1 = testPerformer.getCurrentModel()
				.findType(
						Arrays.asList(new String[] { "package1", "package2",
								"Type1" }));
		assertNotNull("Constrained Type not found.", type1);

		Property theProperty = null;
		for (Property aProperty : type1.getOwnedProperty()) {
			if (null != aProperty.getName()
					&& aProperty.getName().equals("globalInteger")) {
				theProperty = aProperty;
				break;
			}
			// no else.
		}

		assertNotNull("Defined Property not found", theProperty);
		assertTrue("The defined Property should be static.",
				theProperty.isStatic());
	}

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* Check that the defined operation is a static one. */
		Type type1 = testPerformer.getCurrentModel()
				.findType(
						Arrays.asList(new String[] { "package1", "package2",
								"Type1" }));
		assertNotNull("Constrained Type not found.", type1);

		Operation theOperation = null;
		for (Operation aOperation : type1.getOwnedOperation()) {
			if (null != aOperation.getName()
					&& aOperation.getName().equals("getGlobalInteger")) {
				theOperation = aOperation;
				break;
			}
			// no else.
		}

		assertNotNull("Defined Operation not found", theOperation);
		assertTrue("The defined Operation should be static.",
				theOperation.isStatic());
	}

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a definition that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testDefinitionPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defPositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		List<Constraint> constraints = testPerformer.parseFile(oclFileName,
				true);

		assertNotNull(constraints);
		assertEquals(1, constraints.size());

		Constraint constraint = constraints.get(0);
		assertTrue(constraint.getSpecification() instanceof ExpressionInOcl);
		OclExpression exp = ((ExpressionInOcl) constraint.getSpecification())
				.getBodyExpression();

		assertTrue(exp instanceof PropertyCallExp);
		PropertyCallExp propExp = (PropertyCallExp) exp;

		assertNotNull(propExp.getReferredProperty());
		Property prop = propExp.getReferredProperty();

		assertEquals("testDefPositive06", prop.getName());
		assertEquals(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
				.getOclInteger(), prop.getType());

		assertNotNull(prop.getOwner());
	}

	/**
	 * <p>
	 * A test case to parse an definition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testDefinitionNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an definition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = ParseException.class)
	public void testDefinitionNegative05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defNegative05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse an definition that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testDefinitionNegative06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "constrainttypes/defNegative06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}