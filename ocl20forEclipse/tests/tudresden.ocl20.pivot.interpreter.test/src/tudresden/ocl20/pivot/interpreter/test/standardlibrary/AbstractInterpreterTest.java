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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.test.OclInterpreterTestPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Abstract implementation for test class to test code generation of standard
 * library operations.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractInterpreterTest extends AbstractDresdenOclTest{

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE1_NAME = "package1/Instance1";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE2_NAME = "package1/Instance2";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE3_NAME = "package1/Instance3";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE4_NAME = "package1/Instance4";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE5_NAME = "package1/Instance5";

	/** The name of a test {@link IModel} for this test suite. */
	protected static final String MODEL1_NAME = "package1/Model1";

	/** The name of a test {@link IModel} for this test suite. */
	protected static final String MODEL2_NAME = "package1/Model2";

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @return The found {@link File} object.
	 * @throws IOException
	 */
	private static File getFile(String path) throws IOException {

		return AbstractDresdenOclTest.getFile(path,
				OclInterpreterTestPlugin.PLUGIN_ID);
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is an
	 * {@link OclCollection} of a given size.
	 * </p>
	 * 
	 * @param expectedSize
	 *            The expectedSize of the collection.
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	@SuppressWarnings("unchecked")
	protected void assertIsCollectionOfSize(Integer expectedSize,
			IInterpretationResult result) {
		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertFalse(result.getResult().oclIsUndefined().isTrue());

		if (result.getResult() instanceof OclCollection<?>) {
			OclCollection<OclAny> oclCollection;
			oclCollection = (OclCollection<OclAny>) result.getResult();

			OclInteger size;
			size = oclCollection.size();

			assertEquals(new Long(expectedSize), size.getModelInstanceInteger()
					.getLong());
		}

		else {
			fail("Result was not a collection.");
		}
	}

	/**
	 * <p>
	 * Helper method to assert that a given {@link IInterpretationResult} is
	 * equal to a given {@link Object}.
	 * </p>
	 * 
	 * @param expected
	 *            The expected {@link Object}.
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	protected void assertIsEqual(Object expected, IInterpretationResult result) {
		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertFalse(result.getResult().oclIsUndefined().isTrue());

		if (result.getResult() instanceof OclInteger) {
			OclInteger oclInteger;
			oclInteger = (OclInteger) result.getResult();

			assertTrue(expected instanceof Number);
			assertEquals(new Long(((Number) expected).longValue()), oclInteger
					.getModelInstanceInteger().getLong());
		}

		else {
			fail("Comparison not implemented yet.");
		}
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
	 * Helper method to assert that a given {@link IInterpretationResult} is of
	 * a given Type.
	 * </p>
	 * 
	 * @param expectedType
	 *            The expected {@link Type} of the {@link IInterpretationResult}
	 *            .
	 * @param result
	 *            The {@link IInterpretationResult} to be checked.
	 */
	protected void assertIsOfType(Type expectedType,
			IInterpretationResult result) {

		assertFalse(result.getResult().oclIsInvalid().isTrue());
		assertFalse(result.getResult().oclIsUndefined().isTrue());

		OclType<?> oclType = result.getResult().oclType();

		if (oclType.oclIsInvalid().isTrue()
				|| oclType.oclIsUndefined().isTrue()) {
			fail("OclType was invalid or undefined.");
		}

		else if (!expectedType.conformsTo(oclType.getType())) {
			fail("ExpectedType was '" + expectedType.getName() + "' but was '"
					+ oclType.getType().getName() + "'.");
		}
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
		try {
			modelFile = AbstractInterpreterTest.getFile("bin/" + modelName
					+ ".class");
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

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
		try {
			constraintFile = AbstractInterpreterTest
					.getFile("resources/constraints/" + constraintName + ".ocl");
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		List<Constraint> parsedConstraints;
		parsedConstraints = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, model, true);

		assertNotNull(parsedConstraints);
		assertTrue(parsedConstraints.size() >= 1);

		/* Load or get the instance. */
		File instanceFile;
		try {
			instanceFile = AbstractInterpreterTest.getFile("bin/"
					+ instanceName + ".class");
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		IModelInstance modelInstance;
		modelInstance = Ocl2ForEclipseFacade.getModelInstance(instanceFile,
				model, Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Find the IMIObject(s) to test. */
		Set<IModelInstanceObject> imiObjects;
		imiObjects = modelInstance.getAllInstances(objectType);

		assertNotNull(imiObjects);

		assertNotNull(modelInstance);

		/* Interpret the constraints. */
		result = new ArrayList<IInterpretationResult>();

		List<Constraint> staticConstraints = new ArrayList<Constraint>();

		for (Constraint constraint : parsedConstraints) {
			switch (constraint.getKind()) {
			case DEFINITION:
				if (constraint.getDefinedFeature().isStatic()) {
					staticConstraints.add(constraint);
				}
				break;
			case DERIVED:
			case INITIAL:
			case BODY:
				if (((Feature) constraint.getConstrainedElement().iterator()
						.next()).isStatic()) {
					staticConstraints.add(constraint);
				}
				break;
			// no default;
			}
		}
		// end for.

		parsedConstraints.removeAll(staticConstraints);

		result.addAll(Ocl2ForEclipseFacade.interpretConstraints(
				staticConstraints, modelInstance, null));

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