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

package tudresden.ocl20.pivot.ocl2java.test.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class CodegenTestPerformer extends AbstractCompareTest {

	/** The name of the bundle of the model file. */
	private static final String MODEL_BUNDLE = "tudresden.ocl20.pivot.examples.royalandloyal";

	/** The name of the folder for the transformed code. */
	private static final String OUTPUT_FOLDER = "F:/TU Dresden/GB/Workspace/tudresden.ocl20.pivot.examples.royalandloyal.constraints/src/";

	/** The path of the UML model file. */
	private String MODEL_FILE_NAME = "model/royalsandloyals.uml";

	/** A List containing already tested constraints. */
	private List<Constraint> alreadyTestedConstraints;

	/** The code generator used for testing. */
	private IOcl2Code codeGenerator;

	/** The file directory of the model. */
	private String fileDirectory = "";

	/** The model used for testing. */
	private IModel model = null;

	/* The file of the model. */
	private File modelFile;

	/** The model provider used for testing. */
	private IModelProvider modelProvider = null;

	/** The parser used to load Constraints. */
	private OCL2Parser myOCLparser;

	/** Contains the transformed code of the last test case. */
	private String transformedCode;

	/** The meta model used for testing. */
	private IMetamodel uml2MetaModel = null;

	/**
	 * <p>
	 * Creates a new {@link CodegenTestPerformer}.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 *             Is thrown if any error occurred while loading the model or
	 *             the meta model.
	 */
	public CodegenTestPerformer() throws Ocl2CodeException {
		super();

		try {
			this.uml2MetaModel = ModelBusPlugin.getMetamodelRegistry()
					.getMetamodel("tudresden.ocl20.pivot.metamodels.uml2");

			if (uml2MetaModel == null) {
				throw new Ocl2CodeException(
						"Unable to load uml meta model during test.");
			}
			// no else.

		} catch (Exception e) {
			throw new Ocl2CodeException(
					"Unable to load uml meta model during test: "
							+ e.getMessage());
		}

		this.alreadyTestedConstraints = new ArrayList<Constraint>();

		this.transformedCode = null;

		/* Get the bundle location for the model files. */
		this.fileDirectory = Platform.getBundle(MODEL_BUNDLE).getLocation();

		/* Remove the 'reference:file:' from the beginning. */
		this.fileDirectory = fileDirectory.substring(15);

		this.setUML2Model(this.MODEL_FILE_NAME);

		this.codeGenerator = Ocl2CodeFactory.getInstance()
				.createJavaCodeGenerator();

		this.codeGenerator.getSettings().setGettersForDefinedAttributesEnabled(
				true);
	}

	/**
	 * <p>
	 * Parses the file <i>oclFileName</i> against the loaded UML model file. If
	 * an error occurred an CodeGenerationException will be thrown.
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
	
		myOCLparser = new OCL2Parser(model, oclFileReader);
	
		try {
			myOCLparser.parse();
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
		this.codeGenerator.getSettings().setInheritanceDisabled(aConstraint,
				true);
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
		this.codeGenerator.getSettings().setInvariantCheckMode(aConstraint,
				mode);
	}

	/**
	 * <p>
	 * With this method a model (in UML2) can be set. This method must be
	 * invoked before the <i>setOCLConstraint</i> method.
	 * </p>
	 * 
	 * @param umlModelName
	 *            The filename of the UML model.
	 * @throws Ocl2CodeException
	 *             Is thrown if the model cannot be initialized or the model
	 *             file is not found.
	 */
	public void setUML2Model(String umlModelName) throws Ocl2CodeException {
	
		if (this.model != null) {
			if (this.model.getDisplayName().equals(umlModelName))
				return;
		}
	
		this.modelProvider = uml2MetaModel.getModelProvider();
		this.modelFile = new File(this.fileDirectory + umlModelName);
	
		if (!modelFile.exists()) {
			String msg;
	
			msg = "The model file ";
			msg += this.fileDirectory + umlModelName;
			msg += " doesn't exists.";
	
			throw new Ocl2CodeException(msg);
		}
		// no else;
	
		try {
			this.model = modelProvider.getModel(modelFile);
		}
	
		catch (ModelAccessException e) {
			throw new Ocl2CodeException("The model could not be loaded. "
					+ e.getMessage());
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
	
			this.addOCLFile(constraintFileName);
	
			lastConstraint = this.getLastLoadedConstraint();
	
			if (disableInheritance) {
				this.disableInheritance(lastConstraint);
			}
			// no else.
	
			if (checkMode != IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE) {
				this.codeGenerator.getSettings().setInvariantCheckMode(
						lastConstraint, checkMode);
			}
	
			testedConstraints = this.getNotYetTestedConstraints();
	
			codeResults = this.codeGenerator
					.transformInstrumentationCode(testedConstraints);
	
			this.transformedCode = codeResults.get(codeResults.size() - 1);
	
			this.alreadyTestedConstraints.addAll(testedConstraints);
	
			this.diffStringWithFile(expectedFileName, transformedCode);
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
	
			this.addOCLFile(constraintFileName);
	
			testedConstraints = this.getNotYetTestedConstraints();
	
			codeResults = this.codeGenerator
					.transformFragmentCode(testedConstraints);
	
			this.transformedCode = codeResults.get(codeResults.size() - 1);
	
			this.alreadyTestedConstraints.addAll(testedConstraints);
	
			this.diffStringWithFile(expectedFileName, transformedCode);
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
	 * Creates the instrumentation code for all loaded {@link Constraint}s.
	 * </p>
	 */
	public void doCodeGeneration() {
	
		try {
			List<Constraint> allConstraints;
	
			allConstraints = this.getAllConstraints();
	
			this.codeGenerator.getSettings().setSourceDirectory(OUTPUT_FOLDER);
			this.codeGenerator.getSettings().setSaveCode(true);
	
			this.codeGenerator.transformInstrumentationCode(allConstraints);
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
	 * @param aNamespace
	 *            The {@link Namespace} which {@link Constraint}s shall be
	 *            returned.
	 * @return A {@link List} containing all Constraints of a given
	 *         {@link Namespace} and its nested {@link Namespace}s
	 */
	private List<Constraint> getAllConstraints(Namespace aNamespace) {
	
		List<Constraint> result;
	
		List<Namespace> nestedNamespaces;
	
		result = new ArrayList<Constraint>();
	
		nestedNamespaces = aNamespace.getNestedNamespace();
	
		/* Iterate through all nested name spaces. */
		for (Namespace aNestedNamespace : nestedNamespaces) {
			result.addAll(this.getAllConstraints(aNestedNamespace));
		}
	
		/* Add all constraints of this name space. */
		result.addAll(aNamespace.getOwnedRule());
	
		return result;
	}

	/**
	 * @return A {@link List} containing all Constraints of the current set UML2
	 *         model.
	 */
	private List<Constraint> getAllConstraints() {
	
		List<Constraint> result;
		Namespace rootNamespace;
	
		try {
			rootNamespace = this.model.getRootNamespace();
		} catch (ModelAccessException e) {
	
			String msg;
	
			msg = "No RootNamespace found. Reason: " + e.getMessage();
	
			throw new RuntimeException(msg);
		}
	
		result = this.getAllConstraints(rootNamespace);
	
		return result;
	}

	/**
	 * @return A {@link List} containing all {@link Constraint}s which have not
	 *         been tested yet.
	 */
	private List<Constraint> getNotYetTestedConstraints() {
	
		List<Constraint> result;
	
		result = this.getAllConstraints();
		result.removeAll(this.alreadyTestedConstraints);
	
		return result;
	}
}
