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

package org.dresdenocl.ocl2parser.test.context;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.ocl2parser.test.TestPerformer;
import org.dresdenocl.parser.SemanticException;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * <p>
 * Contains test cases that check that the context of OCL constraints is parsed
 * correctly.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestContext {

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive07.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive08.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive09.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive10() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive10.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive11() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive11.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive12() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive12.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive13() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive13.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive14() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive14.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive15() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive15.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive16() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive16.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive17() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive17.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		List<Constraint> constraints;
		constraints = testPerformer.parseFile(oclFileName);

		assertEquals(1, constraints.size());

		Constraint constraint = constraints.get(0);

		/* Check the name of the parameter of the context. */
		List<Variable> parameters;
		parameters = ((ExpressionInOcl) constraint.getSpecification())
				.getParameter();

		assertEquals(1, parameters.size());
		assertEquals("testInput", parameters.get(0).getName());
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive18() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive18.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testContextPositive19() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextPositive19.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative01() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative02() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative03() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative04() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative04.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative05() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative05.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative06() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative06.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative07() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative07.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative08() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative08.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	// FIXME: the standard is not accurate regarding lookup (p. 93), the lookup
	// for elements says "search in surrounding namespace" which is currently
	// done in Dresden OCL. Unfortunately, the OCL code in the standard does not
	// do what the comment says. Will stick with the comment version though.
	
	// change: removed expected exception
	@Test //(expected = SemanticException.class)
	public void testContextNegative09() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative09.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}

	/**
	 * <p>
	 * A test case to parse a context that should not be parsed appropriately.
	 * </p>
	 */
	@Test(expected = SemanticException.class)
	public void testContextNegative11() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "context/contextNegative11.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllContextTests.META_MODEL_ID, AllContextTests.MODEL_BUNDLE,
				AllContextTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName);
	}
}