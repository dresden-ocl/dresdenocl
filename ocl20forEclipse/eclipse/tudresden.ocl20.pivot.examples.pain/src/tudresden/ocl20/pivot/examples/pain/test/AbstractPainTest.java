/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the PAIN Case Study of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.examples.pain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.examples.pain.PainExamplePlugin;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Abstract implementation for test class to test code generation of standard
 * library operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractPainTest {

	/** The location of the {@link IModel} used for testing. */
	private static final String MODEL_NAME = "model/pain.008.001.01corrected.xsd";

	/** The location of an {@link IModelInstance} used for testing. */
	protected static final String MODELINSTANCE_NAME_01 = "BusEx1";

	/** The location of an {@link IModelInstance} used for testing. */
	protected static final String MODELINSTANCE_NAME_02 = "BusEx2";

	/** The location of an {@link IModelInstance} used for testing. */
	protected static final String MODELINSTANCE_NAME_03 = "Pain8Invalid";

	/** The {@link IModel} used for testing. */
	private static IModel testModel;

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	protected static void setUp() throws IllegalArgumentException,
			ModelAccessException {

		File modelFile;
		modelFile = AbstractPainTest.getFile(MODEL_NAME);

		testModel = Ocl2ForEclipseFacade.getModel(modelFile,
				XSDMetamodelPlugin.ID);
	}

	/**
	 * <p>
	 * Tears down the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	protected static void tearDown() throws IllegalArgumentException,
			ModelAccessException {

		Ocl2ForEclipseFacade.removeModel(testModel);
	}

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @return The found {@link File} object.
	 */
	private static File getFile(String path) {

		File result;

		String filePath;
		filePath = PainExamplePlugin.getDefault().getBundle().getLocation();
		/* Remove 'reference:file:/' */
		filePath = filePath.substring(16);

		filePath += PainExamplePlugin.getDefault().getBundle()
				.getResource(path).getPath().substring(1);

		result = new File(filePath);

		assertTrue(result.exists());

		return result;
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * <code>false</code>.
	 * </p>
	 * 
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 * @throws Throwable
	 */
	protected void assertIsFalse(IInterpretationResult result) throws Throwable {

		if (result.getResult().oclIsInvalid().isTrue()) {
			fail(result.getResult().getInvalidReason().getMessage());
		}
		// no else.

		assertFalse(result.getResult().oclIsUndefined().isTrue());
		assertTrue(result.getResult() instanceof OclBoolean);
		assertFalse(((OclBoolean) result.getResult()).isTrue());
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * <code>true</code>.
	 * </p>
	 * 
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 * @throws Throwable
	 */
	protected void assertIsTrue(IInterpretationResult result) throws Throwable {

		if (result.getResult().oclIsInvalid().isTrue()) {
			fail(result.getResult().getInvalidReason().getMessage());
		}
		// no else.

		assertFalse(result.getResult().oclIsUndefined().isTrue());
		assertTrue(result.getResult() instanceof OclBoolean);
		assertTrue(((OclBoolean) result.getResult()).isTrue());
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link Collection} of
	 * {@link IInterpretationResult}s contains a given number of
	 * <code>true</code> and <code>false</code> results.
	 * </p>
	 * 
	 * @param results
	 *            The {@link Collection} of {@link IInterpretationResult}s to be
	 *            checked.
	 * @param expectedTrues
	 *            The number of expected <code>true</code> values.
	 * @param expectedFalses
	 *            The number of expected <code>false</code> values.
	 * @throws Throwable
	 */
	protected void assertTruesAndFalses(
			Collection<IInterpretationResult> results, int expectedTrues,
			int expectedFalses) throws Throwable {

		assertNotNull(results);
		assertEquals(expectedFalses + expectedTrues, results.size());

		int trues = 0;
		int falses = 0;

		for (IInterpretationResult result : results) {

			assertFalse(result.getResult().oclIsInvalid().isTrue());
			assertFalse(result.getResult().oclIsUndefined().isTrue());

			assertTrue(result.getResult() instanceof OclBoolean);

			if (((OclBoolean) result.getResult()).isTrue()) {
				trues++;
			}

			else {
				falses++;
			}
		}
		// end for.

		assertEquals(expectedFalses, falses);
		assertEquals(expectedTrues, trues);
	}

	/**
	 * <p>
	 * Interprets all {@link Constraint}s contained in a given constraint file
	 * (by its name relative to the <code>constraint</code> directory) for all
	 * {@link IModelInstanceObject}s of a given {@link Type}'s name of a given
	 * {@link IModelInstance} (by the file name relative to the
	 * <code>modelinstances</code> directory).
	 * 
	 * @param constraintName
	 *            The name of the constraint file (by its name relative to the
	 *            <code>constraint</code> directory).
	 * @param instanceName
	 *            The name of the {@link IModelInstance} file (relative to the
	 *            <code>modelinstances</code> directory)
	 * @param typeName
	 *            The name of the {@link Type} whose
	 *            {@link IModelInstanceObject}s shall be interpreted.
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	protected List<IInterpretationResult> interpretConstraintsForInstance(
			String constraintName, String instanceName, List<String> typeName)
			throws IllegalArgumentException, ModelAccessException,
			ParseException {

		assertNotNull(constraintName);
		assertNotNull(instanceName);
		assertNotNull(typeName);
		assertTrue(typeName.size() >= 1);

		List<IInterpretationResult> result;

		/* Find the type for the IMIObjects to test. */
		Type objectType;
		objectType = AbstractPainTest.testModel.findType(typeName);

		assertNotNull(objectType);

		/* Parse the constraint. */
		File constraintFile;
		constraintFile = AbstractPainTest.getFile("constraints/"
				+ constraintName + ".ocl");

		List<Constraint> parsedConstraints;
		parsedConstraints = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, testModel, true);

		assertNotNull(parsedConstraints);
		assertTrue(parsedConstraints.size() >= 1);

		/* Load or get the instance. */
		File instanceFile;
		instanceFile = AbstractPainTest.getFile("modelinstances/"
				+ instanceName + ".xml");

		IModelInstance modelInstance;
		modelInstance = Ocl2ForEclipseFacade.getModelInstance(instanceFile,
				AbstractPainTest.testModel,
				XmlModelInstanceTypePlugin.PLUGIN_ID);

		/* Find the IMIObject(s) to test. */
		Set<IModelInstanceObject> imiObjects;
		imiObjects = modelInstance.getAllInstances(objectType);

		assertNotNull(imiObjects);
		assertTrue(imiObjects.size() >= 1);

		assertNotNull(modelInstance);

		/* Interpret the constraints. */
		result = new ArrayList<IInterpretationResult>();

		for (IModelInstanceObject imiObject : imiObjects) {
			result.addAll(Ocl2ForEclipseFacade.interpretConstraints(
					parsedConstraints, modelInstance, imiObject));
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Interprets all {@link Constraint}s contained in a given constraint file
	 * (by its name relative to the <code>constraint</code> directory) for all
	 * {@link IModelInstanceObject}s of a given {@link IModelInstance} (by the
	 * file name relative to the <code>modelinstances</code> directory).
	 * 
	 * @param constraintName
	 *            The name of the constraint file (by its name relative to the
	 *            <code>constraint</code> directory).
	 * @param instanceName
	 *            The name of the {@link IModelInstance} file (relative to the
	 *            <code>modelinstances</code> directory)
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	protected void reportConstraintInterpreationForInstance(
			String constraintName, String instanceName)
			throws IllegalArgumentException, ModelAccessException,
			ParseException {

		assertNotNull(constraintName);
		assertNotNull(instanceName);

		List<IInterpretationResult> results;

		/* Parse the constraint. */
		File constraintFile;
		constraintFile = AbstractPainTest.getFile("constraints/"
				+ constraintName + ".ocl");

		List<Constraint> parsedConstraints;
		parsedConstraints = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, testModel, true);

		assertNotNull(parsedConstraints);
		assertTrue(parsedConstraints.size() >= 1);

		/* Load or get the instance. */
		File instanceFile;
		instanceFile = AbstractPainTest.getFile("modelinstances/"
				+ instanceName + ".xml");

		IModelInstance modelInstance;
		modelInstance = Ocl2ForEclipseFacade.getModelInstance(instanceFile,
				AbstractPainTest.testModel,
				XmlModelInstanceTypePlugin.PLUGIN_ID);

		/* Find the IMIObject(s) to test. */
		Collection<IModelInstanceObject> imiObjects;
		imiObjects = modelInstance.getAllModelInstanceObjects();

		assertNotNull(imiObjects);
		assertTrue(imiObjects.size() >= 1);

		assertNotNull(modelInstance);

		/* Interpret the constraints. */
		results = new ArrayList<IInterpretationResult>();

		for (IModelInstanceObject imiObject : imiObjects) {
			results.addAll(Ocl2ForEclipseFacade.interpretConstraints(
					parsedConstraints, modelInstance, imiObject));
		}
		// end for.

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append("Interpreation results of instance: ");
		resultBuffer.append(instanceName + "\n");
		resultBuffer.append("Object:,Constraint:,Result:\n");

		for (IInterpretationResult aResult : results) {
			resultBuffer.append(aResult.getModelObject().getName());

			resultBuffer.append("," + aResult.getConstraint().getName());

			if (aResult.getResult().oclIsInvalid().isTrue()) {
				resultBuffer.append(",invalid: "
						+ aResult.getResult().getInvalidReason().getMessage()
						+ "\n");
			}

			else if (aResult.getResult().oclIsUndefined().isTrue()) {
				resultBuffer.append(",undefined\n");
			}

			else if (aResult.getResult() instanceof OclBoolean) {

				OclBoolean booleanResult;
				booleanResult = (OclBoolean) aResult.getResult();

				if (booleanResult.isTrue()) {
					resultBuffer.append(",true\n");
				}

				else {
					resultBuffer.append(",false\n");
				}
			}

			else {
				resultBuffer.append(",Result: "
						+ aResult.getResult().toString() + "\n");
			}
		}
		// end for.

		System.out.print(resultBuffer.toString());
	}
}