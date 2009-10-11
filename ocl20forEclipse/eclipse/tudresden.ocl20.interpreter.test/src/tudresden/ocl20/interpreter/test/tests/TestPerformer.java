/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

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
package tudresden.ocl20.interpreter.test.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.examples.royalsandloyals.Customer;
import tudresden.ocl20.pivot.examples.royalsandloyals.CustomerCard;
import tudresden.ocl20.pivot.examples.royalsandloyals.Date;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyAccount;
import tudresden.ocl20.pivot.examples.royalsandloyals.LoyaltyProgram;
import tudresden.ocl20.pivot.examples.royalsandloyals.ProgramPartner;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.provider.JavaModelInstanceProvider;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;

/**
 * <p>
 * This class loads a given model file, a given model instance and given OCL
 * files. Then it can be used to perform some interpreter tests.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPerformer {

	/**
	 * The qualified Name of the package that contains the {@link Class}es used
	 * for testing.
	 */

	public static String QUALIFIED_NAME_MODEL_PACKAGE =
			IModelBusConstants.ROOT_PACKAGE_NAME
					+ "::tudresden::ocl20::pivot::examples::royalsandloyals";

	/** The qualified Name of {@link Customer}. */
	public static String QUALIFIED_NAME_CUSTOMER =
			QUALIFIED_NAME_MODEL_PACKAGE + "::Customer";

	/** The qualified Name of {@link CustomerCard}. */
	public static String QUALIFIED_NAME_CUSTOMER_CARD =
			QUALIFIED_NAME_MODEL_PACKAGE + "::CustomerCard";

	/** The qualified Name of {@link Date}. */
	public static String QUALIFIED_NAME_DATE =
			QUALIFIED_NAME_MODEL_PACKAGE + "::Date";

	/** The qualified Name of {@link LoyaltyAccount}. */
	public static String QUALIFIED_NAME_LOYALTY_ACCOUNT =
			QUALIFIED_NAME_MODEL_PACKAGE + "::LoyaltyAccount";

	/** The qualified Name of {@link LoyaltyProgram}. */
	public static String QUALIFIED_NAME_LOYALTY_PROGRAM =
			QUALIFIED_NAME_MODEL_PACKAGE + "::LoyaltyProgram";

	/** The qualified Name of {@link LoyaltyProgram}. */
	public static String QUALIFIED_NAME_MEMBERSHIP =
			QUALIFIED_NAME_MODEL_PACKAGE + "::Membership";

	/** The qualified Name of {@link ProgramPartner}. */
	public static String QUALIFIED_NAME_PROGRAM_PARTNER =
			QUALIFIED_NAME_MODEL_PACKAGE + "::ProgramPartner";

	/** The package of the UML2 meta model. */
	private final static String META_MODEL = UML2MetamodelPlugin.ID;

	/** The name of the bundle of the model file. */
	private final static String MODEL_BUNDLE =
			"tudresden.ocl20.pivot.examples.royalandloyal";

	/** The path of the UML model file. */
	private final static String MODEL_FILE = "model/royalsandloyals.uml";

	/**
	 * Contains the directory where the OCL files are stored which shall be parsed
	 * and imported.
	 */
	protected String fileDirectory = "";

	/**
	 * Used to select uninterpreted {@link Constraint}s for the actual test case.
	 */
	protected Set<Constraint> interpretedConstraints;

	/**
	 * The global {@link IInterpretationEnvironment} used during interpretation
	 * and preparation.
	 */
	protected IInterpretationEnvironment myGlobalEnvironment;

	/** The {@link IOclInterpreter} used by this {@link TestPerformer}. */
	protected IOclInterpreter myInterpreter = null;

	/** Contains the UML2 meta model. */
	protected IMetamodel myMetaModel = null;

	/** Contains the loaded UML2 model. */
	protected IModel myModel = null;

	/** Contains the loaded UML2 model instance. */
	protected IModelInstance myModelInstance = null;

	/**
	 * <p>
	 * Creates a new TestPerformer.
	 * </p>
	 */
	public TestPerformer() {

		super();
		this.init();
	}

	/**
	 * <p>
	 * Adapts a given {@link Object} as {@link IModelInstanceElement} and adds it
	 * to the {@link IModelInstance} under test.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted and added.
	 * @return The adapted {@link IModelInstanceElement}.
	 */
	public IModelInstanceElement addModelObject(Object object) {

		IModelInstanceElement result;

		try {
			result = myModelInstance.addModelInstanceElement(object);
		}

		// FIXME: Shouldn't there be a better way of handling this exception?
		catch (TypeNotFoundInModelException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Initializes the {@link TestPerformer} after all required parameters are
	 * set.
	 * </p>
	 * 
	 * @throws RuntimeException
	 *           Is thrown if any error occurred while loading the model or the
	 *           meta model.
	 */
	public void init() throws RuntimeException {

		/* Try to load model and meta model. */
		try {

			this.myMetaModel =
					ModelBusPlugin.getMetamodelRegistry().getMetamodel(META_MODEL);

			if (myMetaModel == null) {
				throw new RuntimeException("Unable to load meta model during test.");
			}
			// no else.

			/* Get the bundle location for the model files. */
			fileDirectory = Platform.getBundle(MODEL_BUNDLE).getLocation();

			/* Remove the 'reference:file:' from the beginning. */
			fileDirectory = fileDirectory.substring(15);

			/* Load the model. */
			this.loadModel();

			/* Load the model instance. */
			this.loadModelInstance();

			/* Initialize the set containing already interpreted constraints. */
			this.interpretedConstraints = new HashSet<Constraint>();

			/* Initialize the interpreter. */
			this.myInterpreter =
					OclInterpreterPlugin.createInterpreter(this.myModelInstance);
			this.myInterpreter.setCachingEnabled(false);

			this.myGlobalEnvironment = this.myInterpreter.getEnvironment();
		}

		catch (Exception e) {
			throw new RuntimeException("Unable to initialize the test. Reason: "
					+ e.getMessage());
		}
	}

	/**
	 * <p>
	 * Interprets all not yet interpreted {@link Constraint}s for a given
	 * {@link IModelInstanceElement}.
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} that shall be interpreted.
	 * @result A {@link List} containing {@link OclAny} results for all
	 *         interpreted combinations.
	 */
	public List<OclAny> interpretRemainingConstraints(
			IModelInstanceElement modelObject) {

		List<OclAny> result;

		List<Constraint> uninterpretedConstraints;

		result = new ArrayList<OclAny>();

		/* Collect all not yet interpreted constraints. */
		uninterpretedConstraints = this.collectNotYetInterpretedConstraints();

		/*
		 * Iterate through the given model objects and not yet interpreted
		 * constraints.
		 */
		for (Constraint aConstraint : uninterpretedConstraints) {

			NamedElement aConstrainedElement;

			/* Get the constrained element. */
			aConstrainedElement =
					(NamedElement) aConstraint.getConstrainedElement().get(0);

			/* Check if the constrained element is an operation. */
			if (aConstrainedElement instanceof Operation) {

				Operation anOperation;

				anOperation = (Operation) aConstrainedElement;

				/* Use the constrained type instead. */
				aConstrainedElement = anOperation.getOwningType();
			}

			/*
			 * Check if the the constrained element matches to the current model
			 * object and eventually do the interpretation.
			 */
			if (modelObject.getTypes().contains(aConstrainedElement)) {
				result.add(this.myInterpreter.interpretConstraint(aConstraint,
						modelObject).getResult());
			}
			// no else.

			/* Add the constraint to the already interpreted constraints. */
			this.interpretedConstraints.add(aConstraint);
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Interprets all not yet interpreted {@link Constraint}s for a given
	 * {@link List} of {@link IModelInstanceElement}s.
	 * 
	 * @param modelObjects
	 *          The {@link IModelInstanceElement}s that shall be interpreted.
	 * @result A {@link List} containing {@link OclAny} results for all
	 *         interpreted combinations.
	 */
	public List<OclAny> interpretRemainingConstraints(
			List<IModelInstanceElement> modelObjects) {

		List<OclAny> result;

		List<Constraint> uninterpretedConstraints;

		result = new ArrayList<OclAny>();

		/* Collect all not yet interpreted constraints. */
		uninterpretedConstraints = this.collectNotYetInterpretedConstraints();

		/*
		 * Iterate through the given model objects and not yet interpreted
		 * constraints.
		 */
		for (Constraint aConstraint : uninterpretedConstraints) {

			for (IModelInstanceElement anModelObject : modelObjects) {

				NamedElement aConstrainedElement;

				/* Get the constrained element. */
				aConstrainedElement =
						(NamedElement) aConstraint.getConstrainedElement().get(0);

				/* Check if the constrained element is an operation. */
				if (aConstrainedElement instanceof Operation) {

					Operation anOperation;

					anOperation = (Operation) aConstrainedElement;

					/* Use the constrained type instead. */
					aConstrainedElement = anOperation.getOwningType();
				}

				/*
				 * Check if the the constrained element matches to the current model
				 * object and eventually do the interpretation.
				 */
				if (anModelObject.getTypes().contains(aConstrainedElement)) {
					result.add(this.myInterpreter.interpretConstraint(aConstraint,
							anModelObject).getResult());
				}
				// no else.
			}
			// end for.

			/* Add the constraint to the already interpreted constraints. */
			this.interpretedConstraints.add(aConstraint);
		}
		// end for.

		return result;
	}

	// TODO JavaDoc
	public Operation findOperation(IModelInstanceElement imiElement, String name) throws OperationNotFoundException {

		for (Type type : imiElement.getTypes()) {
			
			for (Operation ownedOperation : type.getOwnedOperation()) {

				if (ownedOperation.getName().equals(name)) {
					return ownedOperation;
				}
			}
		}
		
		throw new OperationNotFoundException("Cannot find operation " + name + " on " + imiElement);
	}

	/**
	 * <p>
	 * Parses the file <i>oclFileName</i> against the loaded UML model file. If an
	 * error occurred an CodeGenerationException will be thrown.
	 * </p>
	 * 
	 * @param oclFileName
	 *          The OCL file to be parsed.
	 * @throws Ocl22JavaException
	 *           Is thrown if any error occurs
	 */
	public void loadOCLFile(String oclFileName) throws RuntimeException {

		File oclFile;
		FileReader oclFileReader;

		OCL2Parser anOCLparser;

		oclFile = new File(fileDirectory + oclFileName);

		/* Check if the given file exists. */
		if (!oclFile.exists()) {
			String msg;

			msg = "The given OCL file does not exist. File name: ";
			msg += oclFileName + ".";

			throw new RuntimeException(msg);
		}
		// no else.

		/* Try to open the file. */
		try {
			oclFileReader = new FileReader(oclFile);
		}

		catch (FileNotFoundException e) {
			String msg;

			msg = "The given OCL file does not exist. File name: ";
			msg += oclFileName + ".";

			throw new RuntimeException(msg);
		}

		anOCLparser = new OCL2Parser(myModel, oclFileReader);

		/* Try to parse the OCL file. */
		try {
			anOCLparser.parse();
		}

		catch (Exception e) {
			String msg;

			msg = "Exception during parsing of given OCL constraints. ";
			msg += e.getMessage();

			e.printStackTrace();

			throw new RuntimeException(msg, e);
		}
	}

	/**
	 * <p>
	 * Prepares all not yet interpreted {@link Constraint}s for a given
	 * {@link IModelInstanceElement}.
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} for that the not yet interpreted
	 *          {@link Constraint}s shall be prepared.
	 * @return The {@link IInterpretationEnvironment} of the
	 *         {@link IOclInterpreter} containing the done preparation.
	 */
	public IInterpretationEnvironment prepareRemainingConstraints(
			IModelInstanceElement modelObject) {

		return this.prepareRemainingConstraints(modelObject, true);
	}

	/**
	 * <p>
	 * Prepares all not yet interpreted {@link Constraint}s for a given
	 * {@link IModelInstanceElement}.
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} for that the not yet interpreted
	 *          {@link Constraint}s shall be prepared.
	 * @param setInterpreted
	 *          If true, the prepared {@link Constraint}s will be added to the set
	 *          of interpreted {@link Constraint}s and thus will not be prepared
	 *          or interpreted again.
	 * @return The {@link IInterpretationEnvironment} of the
	 *         {@link IOclInterpreter} containing the done preparation.
	 */
	public IInterpretationEnvironment prepareRemainingConstraints(
			IModelInstanceElement modelObject, boolean setInterpreted) {

		List<Constraint> unInterpretedConstraints;

		/* Collect not interpreted constraints. */
		unInterpretedConstraints = this.collectNotYetInterpretedConstraints();

		/*
		 * Iterate through the given model objects and all not yet interpreted
		 * constraints.
		 */
		for (Constraint aConstraint : unInterpretedConstraints) {

			NamedElement aConstrainedElement;

			/* Get the constrained element. */
			aConstrainedElement =
					(NamedElement) aConstraint.getConstrainedElement().get(0);

			/*
			 * If the constrained element is a property, get the constrained type
			 * instead.
			 */
			if (aConstrainedElement instanceof Property) {

				Property aProperty;

				aProperty = (Property) aConstrainedElement;
				aConstrainedElement = aProperty.getOwningType();
			}

			/*
			 * Else if the constrained element is an operation, get the constrained
			 * type instead.
			 */
			else if (aConstrainedElement instanceof Operation) {

				Operation operation;

				operation = (Operation) aConstrainedElement;
				aConstrainedElement = operation.getOwningType();
			}
			// no else.

			/*
			 * Check if the current model object matches to the constrained element
			 * and eventually prepare the constrained element.
			 */
			if (modelObject.getTypes().contains(aConstrainedElement)) {
				this.myInterpreter.prepareConstraint(aConstraint, modelObject);
			}
			// no else.

			/*
			 * Eventually add the constraint to the already interpreted constraints.
			 */
			if (setInterpreted) {
				this.interpretedConstraints.add(aConstraint);
			}
		}
		// end for.

		return this.myGlobalEnvironment;
	}

	/**
	 * <p>
	 * Resets the {@link IModel} used by this {@link TestPerformer} and
	 * reinitializes it. Used to remove new defined or initialized
	 * {@link IModelInstanceElement}s from the {@link IModel}.
	 * </p>
	 */
	public void resetModel() {

		/* Remove all loaded models. */
		ModelBusPlugin.getModelRegistry().dispose();

		/* Reload the model. */
		this.loadModel();
		this.myModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		/* Try to reset the prepared constraints of the global environment. */
		this.myGlobalEnvironment.clearPreparedConstraints();
	}

	/**
	 * <p>
	 * Removes a variable to the {@link IInterpretationEnvironment} used for
	 * preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *          The path and name of the variable which shall be reset.
	 */
	public void resetEnvironmentVariable(String path) {

		/* Add the variable to the environment. */
		this.myInterpreter.resetEnviromentVariable(path);
	}

	/**
	 * <p>
	 * Add a given {@link Object} as a variable to the
	 * {@link IInterpretationEnvironment} used for preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *          The path and name of the variable which shall be set.
	 * @param value
	 *          The {@link IModelInstanceElement} value of the set variable as an
	 *          Object.
	 * @throws TypeNotFoundInModelException
	 */
	public void setEnvironmentVariable(String path, Object value)
			throws TypeNotFoundInModelException {

		/* Convert the object into an OclAny. */
		OclAny adaptedObject;

		IModelInstanceElement imiElement =
				myModelInstance.addModelInstanceElement(value);

		adaptedObject =
				JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
						imiElement);
		/* Add the variable to the environment. */
		this.myInterpreter.setEnviromentVariable(path, adaptedObject);
	}

	/**
	 * <p>
	 * Add a given {@link OclAny} as a variable to the
	 * {@link IInterpretationEnvironment} used for preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *          The path and name of the variable which shall be set.
	 * @param value
	 *          The {@link OclAny} value of the set variable as an Object.
	 */
	public void setEnvironmentVariable(String path, OclAny value) {

		/* Add the variable to the environment. */
		this.myInterpreter.setEnviromentVariable(path, value);
	}

	/**
	 * @return A {@link List} containing all loaded {@link Constraint}s which were
	 *         not interpreted yet.
	 */
	protected List<Constraint> collectNotYetInterpretedConstraints() {

		List<Constraint> result;

		Namespace rootNamespace;

		try {
			rootNamespace = this.myModel.getRootNamespace();

			result =
					this.collectNotYetInterpretedConstraintsOfNamespace(rootNamespace);
		}

		catch (ModelAccessException e) {
			e.printStackTrace();
			result = null;

			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

	/**
	 * <p>
	 * Loads the {@link IModel} used for testing.
	 * </p>
	 * 
	 * @throws Ocl22JavaException
	 *           Is thrown if the {@link IModel} cannot be initialized or the
	 *           model file is not found.
	 */
	protected void loadModel() throws RuntimeException {

		/* Check if the model has not already been loaded yet. */
		if (!(this.myModel != null && this.myModel.getDisplayName().equals(
				MODEL_FILE))) {

			File modelFile;

			modelFile = new File(this.fileDirectory + MODEL_FILE);

			/* Check if the given file exists. */
			if (!modelFile.exists()) {
				String msg;

				msg = "The model file ";
				msg += this.fileDirectory + MODEL_FILE;
				msg += " doesn't exists.";

				throw new RuntimeException(msg);
			}
			// no else;

			/* Try to load the model. */
			try {
				IModelRegistry modelRegistry;

				this.myModel = this.myMetaModel.getModelProvider().getModel(modelFile);

				modelRegistry = ModelBusPlugin.getModelRegistry();

				modelRegistry.addModel(myModel);
				modelRegistry.setActiveModel(myModel);
			}

			catch (ModelAccessException e) {
				throw new RuntimeException("The model could not be loaded. "
						+ e.getMessage());
			}
		}
		// no else.
	}

	/**
	 * <p>
	 * Loads a new empty {@link IModelInstance} of the actual selected
	 * {@link IModel}.
	 * </p>
	 * 
	 * @throws RuntimeException
	 *           Thrown, if an error during {@link IModelInstance} initialization
	 *           occurs.
	 */
	protected void loadModelInstance() throws RuntimeException {

		/* Check if a model has been set yet. */
		if (this.myModel != null) {

			this.myModelInstance = null;

			/* Load the model instance. */
			IModelInstanceProvider modelInstanceProvider =
					new JavaModelInstanceProvider();
			this.myModelInstance =
					modelInstanceProvider.createEmptyModelInstance(this.myModel);

			/*
			 * Add the successfully loaded model instance to the model instance
			 * registry.
			 */
			IModelInstanceRegistry modelInstanceRegistry;
			modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

			modelInstanceRegistry.addModelInstance(myModel, this.myModelInstance);
			modelInstanceRegistry.setActiveModelInstance(myModel,
					this.myModelInstance);
		}

		else {
			throw new RuntimeException("No model found to load a model instance.");
		}
	}

	/**
	 * @param aNamespace
	 *          The {@link Namespace} which {@link Constraint}s shall be
	 *          collected.
	 * @return All {@link Constraint}s of a given {@link Namespace} and its nested
	 *         {@link Namespace}s which were not interpreted yet.
	 */
	private List<Constraint> collectNotYetInterpretedConstraintsOfNamespace(
			Namespace aNamespace) {

		List<Constraint> result;

		List<Namespace> nestedNamespaces;
		List<Constraint> ownedRules;

		result = new ArrayList<Constraint>();

		/*
		 * Recursively iterate through all nested name spaces and add their
		 * constraints.
		 */
		nestedNamespaces = aNamespace.getNestedNamespace();

		for (Namespace aNestedNamespace : nestedNamespaces) {
			result.addAll(this
					.collectNotYetInterpretedConstraintsOfNamespace(aNestedNamespace));
		}

		/* Add all not yet interpreted constraints of this name space. */
		ownedRules = aNamespace.getOwnedRule();

		for (Constraint aConstraint : ownedRules) {
			if (!this.interpretedConstraints.contains(aConstraint)) {
				result.add(aConstraint);
			}
			// no else.
		}

		return result;
	}
}