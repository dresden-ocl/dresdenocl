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

package tudresden.ocl20.pivot.ocl2parser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.constrainttypes.AllConstraintTypeTests;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases that test the interface of the {@link Ocl2Parser}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInterface extends AbstractDresdenOclTest {

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive01_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* The model should contain one newly parsed constraints. */
		assertEquals(existingConstraints + 1, testPerformer.getCurrentModel()
				.getConstraints().size());

		/* Remove the constraints. */
		testPerformer.getCurrentModel().removeAllConstraints();
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive01_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, false);

		/* The model should contain no newly parsed constraints. */
		assertEquals(existingConstraints, testPerformer.getCurrentModel()
				.getConstraints().size());
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive02_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* The model should contain one newly parsed constraints. */
		assertEquals(existingConstraints + 3, testPerformer.getCurrentModel()
				.getConstraints().size());

		/* Remove the constraints. */
		testPerformer.getCurrentModel().removeAllConstraints();
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive02_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, false);

		/* The model should contain no newly parsed constraints. */
		assertEquals(existingConstraints, testPerformer.getCurrentModel()
				.getConstraints().size());
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive03_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* The model should contain one newly parsed constraints. */
		assertEquals(existingConstraints + 1, testPerformer.getCurrentModel()
				.getConstraints().size());

		Type type1;
		type1 = testPerformer.getCurrentModel().findType(
				Arrays.asList(new String[] { "Type1" }));

		assertNotNull(type1);

		Operation definedOperation;
		definedOperation = type1.lookupOperation("definedOperation01",
				new ArrayList<Type>());

		/* The defined operation should exist. */
		assertNotNull(definedOperation);

		/* Remove the constraints. */
		testPerformer.getCurrentModel().removeAllConstraints();
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive03_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, false);

		/* The model should contain no newly parsed constraints. */
		assertEquals(existingConstraints, testPerformer.getCurrentModel()
				.getConstraints().size());

		Type type1;
		type1 = testPerformer.getCurrentModel().findType(
				Arrays.asList(new String[] { "Type1" }));

		assertNotNull(type1);

		Operation definedOperation;
		definedOperation = type1.lookupOperation("definedOperation01",
				new ArrayList<Type>());

		/* The defined operation should not exist. */
		assertNull(definedOperation);
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive04_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		ModelListenerMock listener;
		listener = new ModelListenerMock();
		testPerformer.getCurrentModel().addListener(listener);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, true);

		/* The model should contain one newly parsed constraints. */
		assertEquals(existingConstraints + 1, testPerformer.getCurrentModel()
				.getConstraints().size());

		/* The listener should have been notified. */
		assertTrue(listener.wasNotified);

		/* Remove the listener. */
		testPerformer.getCurrentModel().removeListener(listener);

		/* Remove the constraints. */
		testPerformer.getCurrentModel().removeAllConstraints();
	}

	/**
	 * <p>
	 * A test case to parse an expression that should be parsed appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsPositive04_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsPositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		ModelListenerMock listener;
		listener = new ModelListenerMock();
		testPerformer.getCurrentModel().addListener(listener);

		/* Try to parse the constraint file. */
		testPerformer.parseFile(oclFileName, false);

		/* The model should contain no newly parsed constraints. */
		assertEquals(existingConstraints, testPerformer.getCurrentModel()
				.getConstraints().size());

		/* The listener should not have been notified. */
		assertFalse(listener.wasNotified);

		/* Remove the listener. */
		testPerformer.getCurrentModel().removeListener(listener);
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative01_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, true);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative01_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, false);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative02_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, true);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative02_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative02.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, false);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative03_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, true);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());

			Type type1;
			type1 = testPerformer.getCurrentModel().findType(
					Arrays.asList(new String[] { "Type1" }));

			assertNotNull(type1);

			Operation definedOperation;
			definedOperation = type1.lookupOperation("definedOperation01",
					new ArrayList<Type>());

			/* The defined operation should not exist. */
			assertNull(definedOperation);
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative03_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative03.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, false);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());

			Type type1;
			type1 = testPerformer.getCurrentModel().findType(
					Arrays.asList(new String[] { "Type1" }));

			assertNotNull(type1);

			Operation definedOperation;
			definedOperation = type1.lookupOperation("definedOperation01",
					new ArrayList<Type>());

			/* The defined operation should not exist. */
			assertNull(definedOperation);
		}
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative04_withAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		ModelListenerMock listener;
		listener = new ModelListenerMock();
		testPerformer.getCurrentModel().addListener(listener);

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, true);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());

			/* The listener should not have been notified. */
			assertFalse(listener.wasNotified);
		}

		/* Remove the listener. */
		testPerformer.getCurrentModel().removeListener(listener);
	}

	/**
	 * <p>
	 * A test case to parse an expression that should not be parsed
	 * appropriately.
	 * </p>
	 */
	@Test
	public void testParseConstraintsNegative04_withoutAdd() throws Exception {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "interface/constraintsNegative01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);

		int existingConstraints;
		existingConstraints = testPerformer.getCurrentModel().getConstraints()
				.size();

		ModelListenerMock listener;
		listener = new ModelListenerMock();
		testPerformer.getCurrentModel().addListener(listener);

		/* Try to parse the constraint file. */
		try {
			testPerformer.parseFile(oclFileName, false);
			fail("Expected exception was not thrown.");
		}

		catch (ParseException e) {
			/* The model should not contain any newly parsed constraints. */
			assertEquals(existingConstraints, testPerformer.getCurrentModel()
					.getConstraints().size());

			/* The listener should not have been notified. */
			assertFalse(listener.wasNotified);
		}

		/* Remove the listener. */
		testPerformer.getCurrentModel().removeListener(listener);
	}
}