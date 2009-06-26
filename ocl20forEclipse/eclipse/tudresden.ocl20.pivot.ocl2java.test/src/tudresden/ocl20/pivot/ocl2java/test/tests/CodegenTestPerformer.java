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

package tudresden.ocl20.pivot.ocl2java.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.ocl2java.IOcl2Code;
import tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings;
import tudresden.ocl20.pivot.ocl2java.Ocl2CodeFactory;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.ocl2java.test.Ocl2CodeTestPlugin;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <p>
 * This loads the meta model repository with the UML meta model. It also loads a
 * given model file and a given OCL file.
 * </p>
 * 
 * @author Claas Wilke
 */
public final class CodegenTestPerformer {

	/** The ID of the {@link IMetamodel} of the {@link IModel} used for testing. */
	private static final String META_MODEL_ID = "tudresden.ocl20.pivot.metamodels.uml2";

	/** The name of the bundle of the model file. */
	private static final String MODEL_BUNDLE = "tudresden.ocl20.pivot.examples.royalandloyal";

	/** The path of the UML model file. */
	private static final String MODEL_FILE_NAME = "model/royalsandloyals.uml";

	/**
	 * FIXME Claas: Denote a relative path to the workspace.
	 * 
	 * The name of the folder for the transformed code.
	 */
	private static final String OUTPUT_FOLDER = "F:/TU Dresden/GB/Workspace/tudresden.ocl20.pivot.examples.royalandloyal.constraints/src/";

	/** The only instance of {@link CodegenTestPerformer}. */
	private static CodegenTestPerformer myInstance;

	/** A List containing already tested constraints. */
	private List<Constraint> alreadyTestedConstraints;

	/** The file directory of the model. */
	private String fileDirectory = "";

	/** The code generator used for testing. */
	private IOcl2Code myCodeGenerator;

	/** The {@link IModel} used for testing. */
	private IModel myModel = null;

	/** The {@link File} resource of the {@link IModel}. */
	private File myModelFile;

	/** The {@link IModelProvider} used for testing. */
	private IModelProvider myModelProvider = null;

	/** The {@link OCL2Parser} used to load {@link Constraint}s. */
	private OCL2Parser myOCLparser;

	/** Contains the transformed code of the last test case. */
	private String transformedCode;

	/**
	 * <p>
	 * Creates a new {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Thrown if any error occurred while loading the model or the
	 *             meta model.
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
	 *             Thrown if {@link OCL2Parser} of {@link IModel} initialization
	 *             required for testing fails.
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
	 * Parses the file <code>oclFileName</code> against the loaded UML model
	 * file. If an error occurred an CodeGenerationException will be thrown.
	 * </p>
	 * 
	 * @param oclFileName
	 *            The OCL file to be parsed.
	 * @throws Ocl2CodeException
	 *             Is thrown if any error occurs
	 */
	public void addOCLFile(String oclFileName) throws Ocl2CodeException {

		File oclFile;
		FileReader oclFileReader;

		oclFile = new File(fileDirectory + oclFileName);

		if (!oclFile.exists()) {
			String msg;

			msg = "The given OCL file does not exist. File name: ";
			msg += oclFileName + ".";

			throw new Ocl2CodeException(msg);
		}

		try {
			oclFileReader = new FileReader(oclFile);
		}

		catch (FileNotFoundException e) {
			String msg;

			msg = "The given OCL file does not exist. File name: ";
			msg += oclFileName + ".";

			throw new Ocl2CodeException(msg);
		}

		this.myOCLparser = new OCL2Parser(this.myModel, oclFileReader);

		try {
			this.myOCLparser.parse();
		} catch (Exception e) {
			String msg;

			msg = "Exception during parsing of given OCL constraints. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new Ocl2CodeException(msg);
		}
	}

	/**
	 * <p>
	 * Disables inheritance for a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which inheritance shall be
	 *            disabled.
	 */
	public void disableInheritance(Constraint aConstraint) {
		this.myCodeGenerator.getSettings().setInheritanceDisabled(aConstraint,
				true);
	}

	/**
	 * <p>
	 * Creates the instrumentation code for all loaded {@link Constraint}s.
	 * </p>
	 */
	public void doCodeGeneration() {

		try {
			List<Constraint> allConstraints;

			allConstraints = this.getAllConstraints();

			this.myCodeGenerator.getSettings()
					.setSourceDirectory(OUTPUT_FOLDER);
			this.myCodeGenerator.getSettings().setSaveCode(true);

			this.myCodeGenerator.transformInstrumentationCode(allConstraints);
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
	 *            The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *            The file which contains the expected code.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName) {
		this.doDiffTest(constraintFileName, expectedFileName, false);
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *            The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *            The file which contains the expected code.
	 * @param disableInheritance
	 *            Set this value true if the inheritance of the
	 *            {@link Constraint} shall be disabled.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName,
			boolean disableInheritance) {

		this
				.doDiffTest(
						constraintFileName,
						expectedFileName,
						disableInheritance,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
	}

	/**
	 * <p>
	 * Performs a difference test with the set constraintFileName and
	 * expectedFileName and eventually prints the transformed code.
	 * </p>
	 * 
	 * @param constraintFileName
	 *            The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *            The file which contains the expected code.
	 * @param disableInheritance
	 *            Set this value true if the inheritance of the
	 *            {@link Constraint} shall be disabled.
	 * @param checkMode
	 *            The mode with which invariants shall be checked after
	 *            instrumentation.
	 */
	public void doDiffTest(String constraintFileName, String expectedFileName,
			boolean disableInheritance, int checkMode) {

		try {
			List<String> codeResults;
			List<Constraint> testedConstraints;
			Constraint lastConstraint;

			/* Load the OCL file. */
			this.addOCLFile(constraintFileName);

			/*
			 * Get the parsed constraint (files are only allowed to contain one
			 * expression).
			 */
			lastConstraint = this.getLastLoadedConstraint();

			/* Eventually disable the inheritance. */
			if (disableInheritance) {
				this.disableInheritance(lastConstraint);
			}
			// no else.

			/* Eventually set the invariant check mode. */
			if (checkMode != IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE) {
				this.myCodeGenerator.getSettings().setInvariantCheckMode(
						lastConstraint, checkMode);
			}

			/* Get the not yet tested constraints. */
			testedConstraints = this.getNotYetTestedConstraints();

			/* Transform the instrumentation code. */
			codeResults = this.myCodeGenerator
					.transformInstrumentationCode(testedConstraints);

			/* Get the transformed code of the last parsed constraint. */
			this.transformedCode = codeResults.get(codeResults.size() - 1);

			this.alreadyTestedConstraints.addAll(testedConstraints);

			/* To the real difference test. */
			this.testDifferenceBetweenStringAndFile(expectedFileName,
					transformedCode);
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
	 *            The {@link Constraint} file used for the diff test.
	 * @param expectedFileName
	 *            The file which contains the expected code.
	 */
	public void doFragmentDiffTest(String constraintFileName,
			String expectedFileName) {

		try {
			List<String> codeResults;
			List<Constraint> testedConstraints;

			/* Parse the constraint. */
			this.addOCLFile(constraintFileName);

			/* Get the not yet tested constraints. */
			testedConstraints = this.getNotYetTestedConstraints();

			/* Transform the fragment code. */
			codeResults = this.myCodeGenerator
					.transformFragmentCode(testedConstraints);

			/* Get the code of the last parsed constraint. */
			this.transformedCode = codeResults.get(codeResults.size() - 1);

			this.alreadyTestedConstraints.addAll(testedConstraints);

			/* Do the real difference test. */
			this.testDifferenceBetweenStringAndFile(expectedFileName,
					transformedCode);
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
	 * @return The last {@link Constraint} loaded for testing.
	 */
	public Constraint getLastLoadedConstraint() {

		Constraint result;
		List<Constraint> allConstraints;

		result = null;
		allConstraints = this.getAllConstraints();

		if (allConstraints.size() > 0) {
			result = allConstraints.get(allConstraints.size() - 1);
		}

		return result;
	}

	/**
	 * <p>
	 * Resets and re-initializes the {@link CodegenTestPerformer} and the
	 * ModelRegistry.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Thrown if {@link OCL2Parser} of {@link IModel} initialization
	 *             required for testing fails.
	 */
	public void reset() throws Ocl2CodeException {

		this.alreadyTestedConstraints = null;

		ModelBusPlugin.getModelRegistry().dispose();

		this.init();
	}

	/**
	 * <p>
	 * Sets the strength how often invariants shall be checked for a given
	 * {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which inheritance shall be
	 *            disabled.
	 * @param mode
	 *            A value between 1 and 3.
	 */
	public void setInvariantCheckMode(Constraint aConstraint, int mode) {
		this.myCodeGenerator.getSettings().setInvariantCheckMode(aConstraint,
				mode);
	}

	/**
	 *<p>
	 * Initializes the {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Thrown if any error occurred while loading the model or the
	 *             meta model.
	 */
	private void init() throws Ocl2CodeException {

		try {
			IMetamodel metamodel;

			metamodel = ModelBusPlugin.getMetamodelRegistry().getMetamodel(
					META_MODEL_ID);

			if (metamodel == null) {
				throw new Ocl2CodeException("Unable to load the IMetamodel "
						+ META_MODEL_ID);
			}
			// no else.

		} catch (Exception e) {
			throw new Ocl2CodeException("Unable to load the IMetamodel "
					+ META_MODEL_ID + ": " + e.getMessage());
		}

		this.alreadyTestedConstraints = new ArrayList<Constraint>();

		this.transformedCode = null;

		/* Get the bundle location for the model files. */
		this.fileDirectory = Platform.getBundle(MODEL_BUNDLE).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		this.fileDirectory = fileDirectory.substring(15);

		this.loadModel();

		this.myCodeGenerator = Ocl2CodeFactory.getInstance()
				.createJavaCodeGenerator();

		this.myCodeGenerator.getSettings()
				.setGettersForDefinedAttributesEnabled(true);
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
	 * @return A {@link List} containing all {@link Constraint}s which have not
	 *         been tested yet.
	 */
	private List<Constraint> getNotYetTestedConstraints() {

		List<Constraint> result;

		result = new ArrayList<Constraint>(this.getAllConstraints());
		result.removeAll(this.alreadyTestedConstraints);

		return result;
	}

	/**
	 * <p>
	 * Loads the {@link IModel} for testing.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Is thrown if the model cannot be initialized or the model
	 *             file is not found.
	 */
	private void loadModel() throws Ocl2CodeException {

		/* Check if the model has already been set. */
		if (this.myModel != null
				&& this.myModel.getDisplayName().equals(MODEL_FILE_NAME)) {
			return;
		}
		// no else.

		IMetamodel metaModel;

		metaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel(
				META_MODEL_ID);

		this.myModelProvider = metaModel.getModelProvider();
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
			this.myModel = myModelProvider.getModel(myModelFile);
		}

		catch (ModelAccessException e) {
			throw new Ocl2CodeException("The model could not be loaded. "
					+ e.getMessage());
		}
	}

	/**
	 * <p>
	 * Compares a given <code>String</code> with a given expected
	 * <code>String</code> (as a path to a file) and lets the test fail if both
	 * codes don't match. <strong>Leading and tailing white spaces in each line
	 * of the <code>String</code>s will be ignored!</strong>.
	 * </p>
	 * 
	 * @param expectedFilePath
	 *            The path of the String file used for the difference test. The
	 *            path should be relative to the resource folder to avoid
	 *            errors.
	 * @param aString
	 *            The String which shall be checked.
	 * @throws Ocl2CodeException
	 *             Thrown, if a difference test fails.
	 */
	private void testDifferenceBetweenStringAndFile(String expectedFilePath,
			String aString) throws Ocl2CodeException {

		File expectedCodeFile;
		String expectedCodeBundleDirectory;

		String aStringsLines[];
		BufferedReader diffReader;
		String diffLine;
		int index;

		/* Get the bundle location for the difference file. */
		expectedCodeBundleDirectory = Platform.getBundle(
				Ocl2CodeTestPlugin.PLUGIN_ID).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		expectedCodeBundleDirectory = expectedCodeBundleDirectory.substring(15);

		/* Open the file. */
		expectedCodeFile = new File(expectedCodeBundleDirectory
				+ expectedFilePath);

		/* Split the given String into lines */
		aStringsLines = aString.split("\n");

		/* Compare String lines with expected lines from text file. */
		try {
			/* Open expected String file. */
			diffReader = new BufferedReader(new FileReader(expectedCodeFile));

			/*
			 * Read the difference file per line and compare with the given
			 * String.
			 */
			diffLine = diffReader.readLine();

			/* Index for given String lines. */
			index = 0;

			while (diffLine != null) {

				/* Check array index. */
				if (index >= aStringsLines.length) {

					if (diffLine.trim().length() > 0) {
						fail("Given String was longer than the diff file. Test failed");
					}
					// no else.

				}

				/*
				 * Check if both lines match (without leading and tailing white
				 * spaces!).
				 */
				else {

					/*
					 * If a line does not equal, print out the compared file and
					 * fail.
					 */
					if (!diffLine.trim().equals(aStringsLines[index].trim())) {

						System.out.println(">>" + aString + "<<");

						assertEquals(diffLine.trim(), aStringsLines[index]
								.trim());
					}
				}

				/*
				 * Get next line from the difference file and increment array
				 * index.
				 */
				diffLine = diffReader.readLine();
				index++;
			}
			// end while.
		}
		// end try.

		catch (FileNotFoundException e) {
			throw new Ocl2CodeException(
					"An already found file was not found. Test failed.");
		}

		catch (IOException e) {
			throw new Ocl2CodeException(
					"A line from difference file could not be read. Test failed");
		}
	}
}