/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL2 Interpreter Test Suite of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.interpreter.test.standardlibrary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.test.OclInterpreterTestPlugin;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
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
public abstract class AbstractInterpreterTest {

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE1_NAME = "package1/Instance1";

	/** The name of a test {@link IModel} for this test suite. */
	protected static final String MODEL1_NAME = "package1/Model1";

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
		filePath = OclInterpreterTestPlugin.getDefault().getBundle()
				.getLocation();
		/* Remove 'reference:file:/' */
		filePath = filePath.substring(16);

		filePath += OclInterpreterTestPlugin.getDefault().getBundle()
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
	 */
	protected void assertIsFalse(IInterpretationResult result) {
		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertFalse(result.getResult().oclIsUndefined().isTrue());
		assertTrue(result.getResult() instanceof OclBoolean);
		assertFalse(((OclBoolean) result.getResult()).isTrue());
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * <code>invalid</code>.
	 * </p>
	 * 
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	protected void assertIsInvalid(IInterpretationResult result) {
		assertTrue(result.getResult().oclIsInvalid().isTrue());
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * <code>true</code>.
	 * </p>
	 * 
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	protected void assertIsTrue(IInterpretationResult result) {
		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertFalse(result.getResult().oclIsUndefined().isTrue());
		assertTrue(result.getResult() instanceof OclBoolean);
		assertTrue(((OclBoolean) result.getResult()).isTrue());
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * <code>undefined</code>.
	 * </p>
	 * 
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	protected void assertIsUndefined(IInterpretationResult result) {
		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertTrue(result.getResult().oclIsUndefined().isTrue());
	}

	/**
	 * <p>
	 * Interprets all {@link Constraint}s contained in a given constraint file
	 * for all {@link IModelInstanceObject}s of a given {@link Type}'s name of a
	 * given {@link IModelInstance}.
	 * 
	 * @param modelName
	 *            The name of the Java {@link IModel} file (by its name relative
	 *            to the <code>bin</code> directory).
	 * @param constraintName
	 *            The name of the constraint file (by its name relative to the
	 *            <code>resources/constraints</code> directory).
	 * @param instanceName
	 *            The name of the Java {@link IModelInstance} provider file
	 *            (relative to the <code>bin</code> directory)
	 * @param typeName
	 *            The name of the {@link Type} whose
	 *            {@link IModelInstanceObject}s shall be interpreted.
	 * @return
	 * @throws IllegalArgumentException
	 * @throws ModelAccessException
	 * @throws ParseException
	 */
	protected List<IInterpretationResult> interpretConstraintsForInstance(
			String modelName, String constraintName, String instanceName,
			List<String> typeName) throws IllegalArgumentException,
			ModelAccessException, ParseException {

		assertNotNull(modelName);
		assertNotNull(constraintName);
		assertNotNull(instanceName);
		assertNotNull(typeName);
		assertTrue(typeName.size() >= 1);

		List<IInterpretationResult> result;

		/* Load the model. */
		File modelFile;
		modelFile = AbstractInterpreterTest.getFile("bin/" + modelName
				+ ".class");

		IModel model;
		model = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.JAVA_META_MODEL);

		assertNotNull(model);

		/* Find the type for the IMIObjects to test. */
		Type objectType;
		objectType = model.findType(typeName);

		assertNotNull(objectType);

		/* Parse the constraint. */
		File constraintFile;
		constraintFile = AbstractInterpreterTest
				.getFile("resources/constraints/" + constraintName + ".ocl");

		List<Constraint> parsedConstraints;
		parsedConstraints = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, model, true);

		assertNotNull(parsedConstraints);
		assertTrue(parsedConstraints.size() >= 1);

		/* Load or get the instance. */
		File instanceFile;
		instanceFile = AbstractInterpreterTest.getFile("bin/" + instanceName
				+ ".class");

		IModelInstance modelInstance;
		modelInstance = Ocl2ForEclipseFacade.getModelInstance(instanceFile,
				model, Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

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

		/* Remove the constraints from the model. */
		model.removeConstraints(parsedConstraints);

		return result;
	}
}