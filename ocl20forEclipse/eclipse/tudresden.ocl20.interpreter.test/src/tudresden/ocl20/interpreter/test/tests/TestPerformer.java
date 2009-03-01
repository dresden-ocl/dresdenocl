package tudresden.ocl20.interpreter.test.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclBoolean;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclInteger;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclString;

/**
 * <p>
 * This class loads a given model file, a given model instance and given OCL
 * files. Then it can be used to perform some interpreter tests.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPerformer {

	/** The package of the UML2 meta model. */
	protected final static String META_MODEL_BUNDLE = "tudresden.ocl20.pivot.metamodels.uml2";

	/** The name of the bundle of the model file. */
	protected final static String MODEL_BUNDLE = "tudresden.ocl20.pivot.examples.royalandloyal";

	/** The path of the UML model file. */
	protected final static String MODEL_FILE_NAME = "model/royalsandloyals.uml";

	/** The path of the UML model instance file. */
	protected final static String MODEL_INSTANCE_FILE_NAME = "bin/tudresden/ocl20/pivot/examples/royalsandloyals/ModelProviderClass.class";

	/**
	 * Contains the directory where the OCL files are stored which shall be
	 * parsed and imported.
	 */
	protected String fileDirectory = "";

	/**
	 * Used to select uninterpreted {@link Constraint}s for the actual test
	 * case.
	 */
	protected Set<Constraint> interpretedConstraints;

	/**
	 * The global {@link IEnvironment} used during interpretation and
	 * preparation.
	 */
	protected IEnvironment myGlobalEnvironment;

	/** The {@link IOclInterpreter} used by this {@link TestPerformer}. */
	protected IOclInterpreter myInterpreter = null;

	/** Contains the loaded UML2 model. */
	protected IModel myModel = null;

	/** Contains the loaded UML2 model instance. */
	protected IModelInstance myModelInstance = null;

	/** Contains the UML2 meta model. */
	protected IMetamodel myUML2MetaModel = null;

	/**
	 * <p>
	 * This constructor loads the UML2 meta model, the UML2 model and the model
	 * instance.
	 * </p>
	 * 
	 * @throws RuntimeException
	 *             Is thrown if any error occurred while loading the model or
	 *             the meta model.
	 */
	public TestPerformer() throws RuntimeException {

		super();

		/* Try to load model and meta model. */
		try {

			this.myUML2MetaModel = ModelBusPlugin.getMetamodelRegistry()
					.getMetamodel(META_MODEL_BUNDLE);

			if (myUML2MetaModel == null) {
				throw new RuntimeException(
						"Unable to load UML2 meta model during test.");
			}
			// no else.

			/* Get the bundle location for the model files. */
			fileDirectory = Platform.getBundle(MODEL_BUNDLE).getLocation();

			/* Remove the 'reference:file:' from the beginning. */
			fileDirectory = fileDirectory.substring(15);

			/* Load the model. */
			this.loadUML2Model(MODEL_FILE_NAME);

			/* Load the model instance. */
			this.loadModelInstance(MODEL_INSTANCE_FILE_NAME);

			/* Initialize the set containing already interpreted constraints. */
			this.interpretedConstraints = new HashSet<Constraint>();

			/* Initialize the global environment. */
			this.myGlobalEnvironment = Environment.getGlobalEnvironment();
			this.myGlobalEnvironment.setModelInstance(this.myModelInstance);

			/* Initialize the interpreter. */
			this.myInterpreter = new OclInterpreter(this.myGlobalEnvironment);
			this.myInterpreter.setUseCache(false);
		}

		catch (Exception e) {
			throw new RuntimeException(
					"Unable to initialize the test. Reason: " + e.getMessage());
		}
	}

	/**
	 * <p>
	 * Converts the canonical name of a given {@link Class} into a {@link List}
	 * containing the name of the packages of the path.
	 * </p>
	 * 
	 * @param aClass
	 *            The class which path shall be returned.
	 * @return A {@link List} containing the name of the packages of the path of
	 *         the given {@link Class}.
	 */
	public static List<String> getClassNameAsPathList(Class<?> aClass) {

		List<String> result;

		String aCanonicalName;
		String[] pathArray;

		aCanonicalName = aClass.getCanonicalName();

		pathArray = aCanonicalName.split("\\.");

		result = new ArrayList<String>();

		for (int index = 0; index < pathArray.length; index++) {
			result.add(pathArray[index]);
		}

		return result;
	}

	/**
	 * @return The global {@link IEnvironment} used by this
	 *         {@link TestPerformer} for preparation and interpretation of
	 *         {@link Constraint}s.
	 */
	public IEnvironment getGlobalEnvironment() {

		return this.myGlobalEnvironment;
	}

	/**
	 * @param pathList
	 *            The {@link Type} which instances shall be returned.
	 * @return All {@link IModelObject}s of the active {@link IModelInstance}
	 *         which are of the {@link Type} described by the given pathList.
	 */
	public List<IModelObject> getObjectsOfKind(List<String> pathList) {

		List<IModelObject> result;

		result = ModelBusPlugin.getModelInstanceRegistry()
				.getActiveModelInstance(this.myModel)
				.getObjectsOfKind(pathList);

		return result;

	}

	/**
	 * <p>
	 * Interprets all not yet interpreted {@link Constraint}s for a given
	 * {@link List} of {@link IModelObject}s.
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s that shall be interpreted.
	 * @result A {@link List} containing {@link OclRoot} results for all
	 *         interpreted combinations.
	 */
	public List<OclRoot> interpretRemainingConstraints(
			List<IModelObject> modelObjects) {

		List<OclRoot> result;

		List<Constraint> uninterpretedConstraints;

		result = new ArrayList<OclRoot>();

		/* Collect all not yet interpreted constraints. */
		uninterpretedConstraints = this.collectNotYetInterpretedConstraints();

		/*
		 * Iterate through the given model objects and not yet interpreted
		 * constraints.
		 */
		for (Constraint aConstraint : uninterpretedConstraints) {

			for (IModelObject anModelObject : modelObjects) {

				NamedElement aConstrainedElement;

				String aModelObjectsName;
				String aConstrainedElemsName;

				/* Get the names of the current model object. */
				aModelObjectsName = anModelObject.getQualifiedNameString();

				/* Get the constrained element. */
				aConstrainedElement = (NamedElement) aConstraint
						.getConstrainedElement().get(0);

				/* Check if the constrained element is an operation. */
				if (aConstrainedElement instanceof Operation) {

					Operation anOperation;

					anOperation = (Operation) aConstrainedElement;

					/* Use the constrained type instead. */
					aConstrainedElement = anOperation.getOwningType();
				}

				/* Get the constrained element's name. */
				aConstrainedElemsName = aConstrainedElement.getQualifiedName();

				/*
				 * Check if the the constrained element matches to the current
				 * model object and eventually do the interpretation.
				 */
				if (aConstrainedElemsName.equals(aModelObjectsName)) {
					result.add(this.myInterpreter.interpret(aConstraint,
							anModelObject));
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

	/**
	 * <p>
	 * Parses the file <i>oclFileName</i> against the loaded UML model file. If
	 * an error occurred an CodeGenerationException will be thrown.
	 * </p>
	 * 
	 * @param oclFileName
	 *            The OCL file to be parsed.
	 * @throws Ocl22JavaException
	 *             Is thrown if any error occurs
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

			throw new RuntimeException(msg);
		}
	}

	/**
	 * <p>
	 * Prepares all not yet interpreted {@link Constraint}s for a given
	 * {@link List} of {@link IModelObject}s.
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s for which the not yet interpreted
	 *            {@link Constraint}s shall be prepared.
	 * @return The {@link IEnvironment} of the {@link OclInterpreter} containing
	 *         the done preparation.
	 */
	public IEnvironment prepareRemainingConstraints(
			List<IModelObject> modelObjects) {

		return this.prepareRemainingConstraints(modelObjects, true);
	}

	/**
	 * <p>
	 * Prepares all not yet interpreted {@link Constraint}s for a given
	 * {@link List} of {@link IModelObject}s.
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s for which the not yet interpreted
	 *            {@link Constraint}s shall be prepared.
	 * @param setInterpreted
	 *            If true, the prepared {@link Constraint}s will be added to the
	 *            set of interpreted {@link Constraint}s and thus will not be
	 *            prepared or interpreted again.
	 * @return The {@link IEnvironment} of the {@link OclInterpreter} containing
	 *         the done preparation.
	 */
	public IEnvironment prepareRemainingConstraints(
			List<IModelObject> modelObjects, boolean setInterpreted) {

		List<Constraint> unInterpretedConstraints;

		/* Collect not interpreted constraints. */
		unInterpretedConstraints = this.collectNotYetInterpretedConstraints();

		/*
		 * Iterate through the given model objects and all not yet interpreted
		 * constraints.
		 */
		for (Constraint aConstraint : unInterpretedConstraints) {

			for (IModelObject aModelObject : modelObjects) {

				NamedElement aConstrainedElement;

				String aModelObjectsName;
				String aConstrainedElemsName;

				/* Get the model objects name. */
				aModelObjectsName = aModelObject.getQualifiedNameString();

				/* Get the constrained element. */
				aConstrainedElement = (NamedElement) aConstraint
						.getConstrainedElement().get(0);

				/*
				 * If the constrained element is a property, get the constrained
				 * type instead.
				 */
				if (aConstrainedElement instanceof Property) {

					Property aProperty;

					aProperty = (Property) aConstrainedElement;
					aConstrainedElement = aProperty.getOwningType();
				}

				/*
				 * Else if the constrained element is an operation, get the
				 * constrained type instead.
				 */
				else if (aConstrainedElement instanceof Operation) {

					Operation operation;

					operation = (Operation) aConstrainedElement;
					aConstrainedElement = operation.getOwningType();
				}
				// no else.

				/* Get the constrained element's name. */
				aConstrainedElemsName = aConstrainedElement.getQualifiedName();

				/*
				 * Check if the current model object matches to the constrained
				 * element and eventually prepare the constrained element.
				 */
				if (aConstrainedElemsName.equals(aModelObjectsName)) {
					this.myInterpreter.prepare(aConstraint, aModelObject);
				}
				// no else.

			}
			// end for.

			/*
			 * Eventually add the constraint to the already interpreted
			 * constraints.
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
	 * {@link IModelObject}s from the {@link IModel}.
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public void resetUML2Model() {

		/* Remove all loaded models. */
		ModelBusPlugin.getModelRegistry().dispose();

		/* Reload the model. */
		this.loadUML2Model(MODEL_FILE_NAME);
		this.myModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		/* Try to reset the prepared constraints of the global environment. */
		try {
			Field savedConstraintsField;
			Object savedConstraintsObject;
			Map<String, Constraint> savedConstraints;

			/* Get the field by reflection which contains the constraints. */
			savedConstraintsField = Environment.class
					.getDeclaredField("savedConstraints");
			savedConstraintsField.setAccessible(true);

			/* Get the field's object and cast it to a Map. */
			savedConstraintsObject = savedConstraintsField
					.get(this.myGlobalEnvironment);
			savedConstraints = (Map<String, Constraint>) savedConstraintsObject;

			/* Clear the Map. */
			if (savedConstraints != null) {
				savedConstraints.clear();
			}
			// no else.
		}

		catch (SecurityException e) {
			/* Ignore this exception. */
		}

		catch (NoSuchFieldException e) {
			/* Ignore this exception. */
		}

		catch (IllegalArgumentException e) {
			/* Ignore this exception. */
		}

		catch (IllegalAccessException e) {
			/* Ignore this exception. */
		}
	}

	/**
	 * <p>
	 * Removes a given variable from the {@link IEnvironment} used for
	 * preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *            The path and name of the variable which shall be set.
	 */
	public void resetVar(String path) {

		/* Add the variable to the environment. */
		this.myGlobalEnvironment.addVar(path, null);
	}

	/**
	 * <p>
	 * Add a given {@link Object} as a variable to the {@link IEnvironment} used
	 * for preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *            The path and name of the variable which shall be set.
	 * @param value
	 *            The value of the set variable as an Object.
	 */
	public void setVar(String path, Object value) {

		OclRoot anOclRoot;

		/* Convert the given variable into an OclRoot depending on its type. */
		if (value instanceof Integer) {
			anOclRoot = new JavaOclInteger((Integer) value);
		}

		else if (value instanceof Float) {
			anOclRoot = new JavaOclReal((Float) value);
		}

		else if (value instanceof Boolean) {
			anOclRoot = JavaOclBoolean.getInstance((Boolean) value);
		}

		else if (value instanceof String) {
			anOclRoot = new JavaOclString((String) value);
		}

		else {
			anOclRoot = new JavaOclObject(value);
		}

		/* Add the variable to the environment. */
		this.myGlobalEnvironment.addVar(path, anOclRoot);
	}

	/**
	 * @return A {@link List} containing all loaded {@link Constraint}s which
	 *         were not interpreted yet.
	 */
	protected List<Constraint> collectNotYetInterpretedConstraints() {

		List<Constraint> result;

		Namespace rootNamespace;

		try {
			rootNamespace = this.myModel.getRootNamespace();

			result = this
					.collectNotYetInterpretedConstraintsOfNamespace(rootNamespace);
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
	 * Loads a given fileName as an {@link IModelInstance} of the actual
	 * selected {@link IModel}.
	 * </p>
	 * 
	 * @param modelInstanceProviderFileName
	 *            The file of the provider class of the {@link IModelInstance}.
	 * @throws RuntimeException
	 *             Thrown, if the given file can not be found.
	 */
	protected void loadModelInstance(String modelInstanceProviderFileName)
			throws RuntimeException {

		/* Check if a model has been set yet. */
		if (this.myModel != null) {

			IModelInstanceProvider modelInstanceProvider;
			File modelInstanceFile;

			modelInstanceProvider = myModel.getModelInstanceProvider();
			this.myModelInstance = null;

			// load the model instance
			try {
				modelInstanceFile = new File(fileDirectory
						+ modelInstanceProviderFileName);

				if (!modelInstanceFile.exists()) {
					String msg;

					msg = "The given model instance provider class file ";
					msg += "does not exist. File name: ";
					msg += modelInstanceFile + ".";

					throw new RuntimeException(msg);
				}

				this.myModelInstance = modelInstanceProvider
						.getModelInstance(modelInstanceFile);
			}

			catch (ModelAccessException e) {

				String msg;

				msg = "An error occured when loading model '" + myModel + "'";

				e.printStackTrace();

				throw new RuntimeException(msg, e);
			}

			/*
			 * Add the successfully loaded model instance to the model instance
			 * registry.
			 */
			IModelInstanceRegistry modelInstanceRegistry;
			modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

			modelInstanceRegistry.addModelInstance(myModel,
					this.myModelInstance);
			modelInstanceRegistry.setActiveModelInstance(myModel,
					this.myModelInstance);
		}

		else {
			throw new RuntimeException(
					"No model found to load a model instance.");
		}
	}

	/**
	 * <p>
	 * Loads an {@link IModel} which uses the UML2 meta model.
	 * </p>
	 * 
	 * @param uml2ModelName
	 *            The filename of the UML2 model.
	 * @throws Ocl22JavaException
	 *             Is thrown if the {@link IModel} cannot be initialized or the
	 *             model file is not found.
	 */
	protected void loadUML2Model(String uml2ModelName) throws RuntimeException {

		/* Check if the model has not already been loaded yet. */
		if (!(this.myModel != null && this.myModel.getDisplayName().equals(
				uml2ModelName))) {

			File modelFile;

			modelFile = new File(this.fileDirectory + uml2ModelName);

			/* Check if the given file exists. */
			if (!modelFile.exists()) {
				String msg;

				msg = "The model file ";
				msg += this.fileDirectory + uml2ModelName;
				msg += " doesn't exists.";

				throw new RuntimeException(msg);
			}
			// no else;

			/* Try to load the model. */
			try {
				IModelRegistry modelRegistry;

				this.myModel = this.myUML2MetaModel.getModelProvider()
						.getModel(modelFile);

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
	 * @param aNamespace
	 *            The {@link Namespace} which {@link Constraint}s shall be
	 *            collected.
	 * @return All {@link Constraint}s of a given {@link Namespace} and its
	 *         nested {@link Namespace}s which were not interpreted yet.
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
			result
					.addAll(this
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
