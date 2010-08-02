/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2Java;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.test.Ocl2CodeTestPlugin;

/**
 * <p>
 * This loads the meta model repository with the UML meta model. It also loads a
 * given model file and a given OCL file.
 * </p>
 * 
 * @author Claas Wilke
 */
public final class CodegenTestPerformer {

	/** The name of the bundle of the model file. */
	private static final String MODEL_BUNDLE =
			"tudresden.ocl20.pivot.examples.royalandloyal";

	/** The path of the UML model file. */
	private static final String MODEL_FILE_NAME = "model/royalsandloyals.uml";

	/** The name of the bundle for the transformed code. */
	private static final String OUTPUT_BUNDLE =
			"tudresden.ocl20.pivot.examples.royalandloyal.ocl22javacode";

	/** The only instance of {@link CodegenTestPerformer}. */
	private static CodegenTestPerformer myInstance;

	/** The file directory of the model. */
	private String fileDirectory = "";

	/** The {@link IOcl2javaSettings} used for testing. */
	private IOcl2JavaSettings myCodeGeneratorSettings;

	/** The {@link IModel} used for testing. */
	private IModel myModel = null;

	/** The {@link File} resource of the {@link IModel}. */
	private File myModelFile;

	/** Contains the transformed code of the last test case. */
	private String transformedCode;

	/**
	 * <p>
	 * Creates a new {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Thrown if any error occurred while loading the model or the meta
	 *           model.
	 */
	private CodegenTestPerformer() throws Ocl2CodeException {

		super();

		init();
	}

	/**
	 * <p>
	 * Returns the only instance of {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @return The only instance of {@link CodegenTestPerformer}.
	 * @throws Ocl2CodeException
	 *           Thrown if {@link Ocl2Parser} of {@link IModel} initialization
	 *           required for testing fails.
	 */
	public static CodegenTestPerformer getInstance() throws Ocl2CodeException {

		/* Eventually create the instance. */
		if (myInstance == null) {
			myInstance = new CodegenTestPerformer();
		}
		// no else

		return myInstance;
	}

	/**
	 * <p>
	 * Parses the {@link File} <code>oclFileName</code> against the loaded UML
	 * model file. If an error occurred an {@link Ocl2CodeException} will be
	 * thrown.
	 * </p>
	 * 
	 * @param oclFileName
	 *          The OCL file to be parsed.
	 * @throws Ocl2CodeException
	 *           Is thrown if any error occurs
	 * @return The added {@link Constraint}s as a {@link List}.
	 */
	public List<Constraint> addOclConstraintsToModel(String oclFileName)
			throws Ocl2CodeException {

		List<Constraint> result;
		File oclFile;
		oclFile = new File(fileDirectory + oclFileName);

		if (!oclFile.exists()) {
			String msg;

			msg = "The given OCL file does not exist. File name: ";
			msg += oclFileName + ".";

			throw new Ocl2CodeException(msg);
		}

		try {
			result =
					Ocl2ForEclipseFacade.parseConstraints(oclFile, this.myModel, true);
		}

		catch (Exception e) {
			String msg;

			msg = "Exception during parsing of given OCL constraints. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new Ocl2CodeException(msg, e);
		}

		return result;
	}

	/**
	 * <p>
	 * Disables inheritance for a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which inheritance shall be disabled.
	 */
	public void disableInheritance(Constraint aConstraint) {

		this.myCodeGeneratorSettings.setInheritanceDisabled(aConstraint, true);
	}

	/**
	 * <p>
	 * Creates the instrumentation code for all loaded {@link Constraint}s.
	 * </p>
	 */
	public void doCodeGeneration() {

		try {
			String sourceDirectory;
			List<Constraint> allConstraints;

			/* Get the bundle location for the model files. */
			sourceDirectory = Platform.getBundle(OUTPUT_BUNDLE).getLocation();

			/* Remove the 'reference:file:' from the beginning. */
			sourceDirectory = sourceDirectory.substring(15);
			sourceDirectory += "src/";

			this.myCodeGeneratorSettings.setSourceDirectory(sourceDirectory);

			allConstraints = this.getAllConstraints();

			this.myCodeGeneratorSettings.setSaveCode(true);
			Ocl2ForEclipseFacade.generateAspectJCode(allConstraints,
					this.myCodeGeneratorSettings);
			this.myCodeGeneratorSettings.setSaveCode(false);
		}

		catch (Ocl2CodeException e) {
			String msg;

			msg = "An error occured during preparing tests. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new RuntimeException(msg);
		}
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *          The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *          The file which contains the expected code.
	 * @throws Ocl2CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName)
			throws Ocl2CodeException {

		this.doDiffTest(constraintFileName, expectedFileName, false);
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *          The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *          The file which contains the expected code.
	 * @param disableInheritance
	 *          Set this value true if the inheritance of the {@link Constraint}
	 *          shall be disabled.
	 * @throws Ocl2CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName,
			boolean disableInheritance) throws Ocl2CodeException {

		this
				.doDiffTest(
						constraintFileName,
						expectedFileName,
						disableInheritance,
						IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *          The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *          The file which contains the expected code.
	 * @param disableInheritance
	 *          Set this value true if the inheritance of the {@link Constraint}
	 *          shall be disabled.
	 * @param checkMode
	 *          The mode with which invariants shall be checked after
	 *          instrumentation.
	 * @throws Ocl2CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName,
			boolean disableInheritance, int checkMode) throws Ocl2CodeException {

		List<Constraint> constraints;
		Constraint constraint;

		/* Load the OCL file. */
		constraints = this.addOclConstraintsToModel(constraintFileName);
		constraint = constraints.get(constraints.size() - 1);

		/* Probably disable the inheritance. */
		if (disableInheritance) {
			this.disableInheritance(constraint);
		}
		// no else.

		this.myCodeGeneratorSettings.setInvariantCheckMode(constraint, checkMode);

		/* Transform the instrumentation code. */
		this.transformedCode =
				Ocl2ForEclipseFacade.generateAspectJCode(constraint,
						this.myCodeGeneratorSettings);

		/* To the real difference test. */
		this.compareStringAndFile(expectedFileName, transformedCode);
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *          The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *          The file which contains the expected code.
	 */
	public void doFragmentDiffTest(String constraintFileName,
			String expectedFileName) {

		try {
			List<Constraint> constraints;
			Constraint constraint;

			/* Load the OCL file. */
			constraints = this.addOclConstraintsToModel(constraintFileName);
			constraint = constraints.get(constraints.size() - 1);

			/* Transform the fragment code. */
			this.transformedCode =
					Ocl2ForEclipseFacade.generateJavaFragmentCode(constraint,
							this.myCodeGeneratorSettings);

			/* Do the real difference test. */
			this.compareStringAndFile(expectedFileName, transformedCode);
		}

		catch (Ocl2CodeException e) {
			String msg;

			msg = "An error occured during preparing tests. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new RuntimeException(msg);
		}
	}

	/**
	 * <p>
	 * Resets and re-initializes the {@link CodegenTestPerformer} and the
	 * ModelRegistry.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Thrown if {@link Ocl2Parser} of {@link IModel} initialization
	 *           required for testing fails.
	 */
	public void reset() throws Ocl2CodeException {

		ModelBusPlugin.getModelRegistry().dispose();

		this.init();
	}

	/**
	 * <p>
	 * Resets and re-initializes the {@link IOcl2Java} code generator of this
	 * {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Thrown if {@link Ocl2Parser} of {@link IModel} initialization
	 *           required for testing fails.
	 */
	public void resetCodeGenerator() throws Ocl2CodeException {

		this.myCodeGeneratorSettings =
				Ocl2ForEclipseFacade.getJavaCodeGeneratorSettings();
	}

	/**
	 * <p>
	 * Sets the strength how often invariants shall be checked for a given
	 * {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which inheritance shall be disabled.
	 * @param mode
	 *          A value between 1 and 3.
	 */
	public void setInvariantCheckMode(Constraint aConstraint, int mode) {

		this.myCodeGeneratorSettings.setInvariantCheckMode(aConstraint, mode);
	}

	/**
	 * <p>
	 * Compares a given <code>String</code> with a given expected
	 * <code>String</code> (as a path to a file) and lets the test fail if both
	 * codes don't match. <strong>Leading and tailing white spaces in each line of
	 * the <code>String</code>s will be ignored!</strong>.
	 * </p>
	 * 
	 * @param expectedFilePath
	 *          The path of the String file used for the difference test. The path
	 *          should be relative to the resource folder to avoid errors.
	 * @param givenString
	 *          The String which shall be checked.
	 * @throws Ocl2CodeException
	 *           Thrown, if a difference test fails.
	 */
	public void compareStringAndFile(String expectedFilePath, String givenString)
			throws Ocl2CodeException {

		File expectedCodeFile;
		String expectedCodeBundleDirectory;

		/* Get the bundle location for the difference file. */
		expectedCodeBundleDirectory =
				Platform.getBundle(Ocl2CodeTestPlugin.PLUGIN_ID).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		expectedCodeBundleDirectory = expectedCodeBundleDirectory.substring(15);

		/* Open the file. */
		expectedCodeFile = new File(expectedCodeBundleDirectory + expectedFilePath);

		/* Compare String lines with expected lines from text file. */
		try {
			BufferedReader diffReader;
			String expectedString;

			/* Open expected String file. */
			diffReader = new BufferedReader(new FileReader(expectedCodeFile));

			expectedString = "";

			/* Read the expected code from file. */
			while (diffReader.ready()) {

				expectedString += diffReader.readLine();

				if (diffReader.ready()) {
					expectedString += "\n";
				}
				// no else.
			}
			// end while.

			String[] expectedLines;
			String[] givenLines;

			/* Split both strings into lines. */
			expectedLines = expectedString.split("\n");
			givenLines = givenString.split("\n");

			/* Both arrays must have the same length. */
			if (expectedLines.length != givenLines.length) {
				/*
				 * If two lines are not equal compare the whole file again to show the
				 * right error message with comparison failure.
				 */
				assertEquals(expectedString, givenString);
			}

			/* Else compare them line by line by trimming the lines.. */
			else {

				for (int index = 0; index < expectedLines.length; index++) {

					if (!expectedLines[index].trim().equals(givenLines[index].trim())) {
						/*
						 * If two lines are not equal compare the whole file again to show
						 * the right error message with comparison failure.
						 */
						assertEquals(expectedString, givenString);
					}
					// no else.
				}
				// end for.
			}
			// end else.
		}
		// end try.

		catch (FileNotFoundException e) {
			throw new Ocl2CodeException(
					"An already found file was not found. Test failed.");
		}

		catch (IOException e) {
			throw new Ocl2CodeException(
					"The difference file could not be read. Test failed");
		}
	}

	/**
	 * <p>
	 * Initializes the {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Thrown if any error occurred while loading the model or the meta
	 *           model.
	 */
	private void init() throws Ocl2CodeException {

		this.transformedCode = null;

		/* Get the bundle location for the model files. */
		this.fileDirectory = Platform.getBundle(MODEL_BUNDLE).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		this.fileDirectory = fileDirectory.substring(15);

		this.loadModel();

		this.resetCodeGenerator();

		this.myCodeGeneratorSettings.setGettersForDefinedAttributesEnabled(true);
	}

	/**
	 * @return A {@link List} containing all Constraints of the current set UML2
	 *         model.
	 */
	private List<Constraint> getAllConstraints() {

		List<Constraint> result;
		Namespace rootNamespace;

		try {
			rootNamespace = this.myModel.getRootNamespace();
		} catch (ModelAccessException e) {

			String msg;

			msg = "No RootNamespace found. Reason: " + e.getMessage();

			throw new RuntimeException(msg);
		}

		result = rootNamespace.getOwnedAndNestedRules();

		return result;
	}

	/**
	 * <p>
	 * Loads the {@link IModel} for testing.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *           Is thrown if the model cannot be initialized or the model file is
	 *           not found.
	 */
	private void loadModel() throws Ocl2CodeException {

		/* Check if the model has already been set. */
		if (this.myModel != null
				&& this.myModel.getDisplayName().equals(MODEL_FILE_NAME)) {
			return;
		}
		// no else.

		this.myModelFile = new File(this.fileDirectory + MODEL_FILE_NAME);

		if (!myModelFile.exists()) {
			String msg;

			msg = "The model file ";
			msg += this.fileDirectory + MODEL_FILE_NAME;
			msg += " doesn't exist.";

			throw new Ocl2CodeException(msg);
		}
		// no else;

		try {
			this.myModel =
					Ocl2ForEclipseFacade.getModel(this.myModelFile,
							Ocl2ForEclipseFacade.UML2_MetaModel);
		}

		catch (ModelAccessException e) {
			throw new Ocl2CodeException("The model could not be loaded. "
					+ e.getMessage());
		}
	}
}