/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

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
package tudresden.ocl20.benchmark.common;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeRegistry;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.JavaStandardlibraryPlugin;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * This class loads a given model file, a given model instance and given OCL
 * files. Then it can be used to perform some interpreter tests.
 * </p>
 * 
 * @author Franz Eichhorn
 */
public class TestPerformer {

	/**
	 * used to specify the content of an ocl-file so that the performer knows how
	 * to pre-parse the files correctly. However QUERY is not used yet and the
	 * other two have exactly the same behavior.
	 */
	public static enum constraintFileType {
		STATEMENT, // complete statement containing context
		QUERY, // only query
		PREPOST
		// only pre/post, context will be attached
	};

	/**
	 * The global {@link IEnvironment} used during interpretation and preparation.
	 */
	protected IInterpretationEnvironment myGlobalEnvironment;

	// @ The {@link IOclInterpreter} used by this {@link TestPerformer}.
	protected IOclInterpreter myInterpreter = null;

	// @ Model which is worked on
	protected IModel myModel = null;

	// @ Metamodel of the Model
	protected IMetamodel metaModel = null;

	// @ Environment that holds common variables which
	// @ are used by both the TestPerformer and the Logger.
	protected TestEnvironment testEnv;

	// @ name of model instance type (mostly java)
	protected String modelInstanceType;

	protected List<IModelInstanceObject> activeMIObjects;

	/**
	 * 
	 * 
	 * @param testEnv 
	 */
	public TestPerformer(TestEnvironment testEnv) {

		super();

		this.testEnv = testEnv;
	}

	/**
	 * <p>
	 * Initializes the {@link TestPerformer} after all required parameters are
	 * set.
	 * </p>
	 * 
	 * @param metaModelName 
	 * @param modelFilePath 
	 * 
	 * @throws RuntimeException Is thrown if any error occurred while loading the model or the
	 * meta model.
	 */
	public void init(String metaModelName, String modelFilePath)
			throws RuntimeException {

		/* Try to load model and meta model. */
		try {

			this.metaModel =
					ModelBusPlugin.getMetamodelRegistry().getMetamodel(metaModelName);

			if (metaModel == null) {
				throw new RuntimeException(
						"Unable to load UML2 meta model during test.");
			}

			this.testEnv.initFileDirectory(Platform
					.getBundle("tudresden.ocl20.benchmark"));

			// Load the model.
			this.loadModel(modelFilePath);

			// Initialize the interpreter.
			this.myInterpreter = OclInterpreterPlugin.createInterpreter(null);
			this.myInterpreter.setCachingEnabled(false);

			this.myGlobalEnvironment = this.myInterpreter.getEnvironment();
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Loads all objects and constraints of the currently active model instance.
	 */
	public void loadActiveMIObjects() {

		if (this.activeMIObjects == null) {
			this.activeMIObjects =
					this.getActiveModelInstance().getAllModelInstanceObjects();
		}
	}

	/**
	 * Check invariant with all active instance objects.
	 * 
	 * @param con 
	 * @param results 
	 * 
	 * @return true, if successful
	 * 
	 * @throws Throwable 
	 */
	public boolean checkInvariantWithAllActiveInstanceObjects(Constraint con,
			StringBuilder results) throws Throwable {

		NamedElement conElement = null;

		boolean success = true;

		for (IModelInstanceObject obj : this.activeMIObjects) {

			conElement = (NamedElement) con.getConstrainedElement().get(0);
			if (conElement instanceof Property || conElement instanceof Operation) {
				conElement = conElement.getOwner();
			}
			// interpret only when rule applies to object
			if (obj.getTypes().contains(conElement)) {
				String result;
				// Interpret constraint and current object
				result = this.interpretConstraint2(obj, con);

				if (result != null) {
					results.append(obj.getName() + " --> " + result + " ## ");
					success = false;
				}
				else {
					results.append(obj.getName() + " --> OK ## ");
				}

			}
		}

		return success;

	}


	/**
	 * Check post condition.
	 * 
	 * @param guineaPig 
	 * @param con 
	 * @param results 
	 * @param method 
	 * @param params 
	 * 
	 * @return true, if successful
	 * 
	 * @throws Throwable 
	 */
	public boolean checkPostCondition(IModelInstanceObject guineaPig,
			Constraint con, StringBuilder results, String method, String... params)
			throws Throwable {

		// collect method parameters
		OclAny[] oclParams = this.collectMethodParams(params);

		// create an ocl wrapper around the instance object (not sure why :))
		OclAny oclGuineaPig =
				this.myInterpreter.getStandardLibraryFactory().createOclAny(guineaPig);

		//
		List<Type> parameterTypes = new ArrayList<Type>();

		for (OclAny oclParam : oclParams) {
			parameterTypes.add(oclParam.getModelInstanceElement().getTypes()
					.iterator().next());
		}

		/* Find the operation. */
		Operation operation = null;

		for (Type type : guineaPig.getTypes()) {

			operation = type.lookupOperation(method, parameterTypes);
			if (operation != null) {
				break;
			}

		}

		// invoke the operation
		if (operation != null) {
			oclGuineaPig.invokeOperation(operation, oclParams);
		}
		else {
			results.append("Operation not found");
			return false;
		}

		String result;
		// Interpret constraint after the invocation
		result = this.interpretConstraint2(guineaPig, con);

		if (result != null) {
			results.append(guineaPig.getName() + " --> " + result);
			return false;
		}
		else {
			return true;
		}

	}

	/**
	 * Check pre condition.
	 * 
	 * @param guineaPig 
	 * @param con 
	 * @param results 
	 * 
	 * @return true, if successful
	 * 
	 * @throws Throwable 
	 */
	public boolean checkPreCondition(IModelInstanceObject guineaPig,
			Constraint con, StringBuilder results) throws Throwable {

		boolean success = true;
		String result;
		// Interpret constraint and current object
		result = this.interpretConstraint2(guineaPig, con);

		if (result != null) {
			results.append(guineaPig.getName() + " --> " + result + " ## ");
			success = false;
		}
		else {
			results.append(guineaPig.getName() + " --> OK ## ");
		}

		return success;
	}

	/**
	 * Collect an array of OclAny-Objects registered in the environment.
	 * 
	 * @param params String-Array identifying all Variables that are being
	 * fetched
	 * 
	 * @return 
	 */
	protected OclAny[] collectMethodParams(String... params) {

		OclAny[] oclParams = new OclAny[params.length];
		int counter = 0;
		for (String name : params) {
			oclParams[counter++] = this.myGlobalEnvironment.getVar(name);
		}

		return oclParams;

	}

	/**
	 * Interprets a model instance object with a constraint.
	 * 
	 * @param obj 
	 * @param con 
	 * 
	 * @return null if everything went ok, string in case of failure or exception
	 * when something weired happend.
	 * 
	 * @throws Throwable 
	 */
	protected String interpretConstraint2(IModelInstanceObject obj, Constraint con)
			throws Throwable {

		OclAny result = null;

		result = this.myInterpreter.interpretConstraint(con, obj).getResult();

		if (result == null) {
			return "Interpretation error: result was null";

		}

		if (result.oclIsUndefined().isTrue()) {
			return "Result is oclUndefined. OclBoolean was expected";
		}

		OclBoolean res = (OclBoolean) result;

		if (res != null && res.isTrue()) {
			return null;
		}
		else {
			return "Result was OclBoolean(false)";
		}

	}

	/**
	 * Interpret constraint.
	 * 
	 * @param obj 
	 * @param con 
	 */
	@SuppressWarnings("unused")
	protected void interpretConstraint(IModelInstanceObject obj, Constraint con) {

	}

	/**
	 * Gets the constraint by statement.
	 * 
	 * @param stmt 
	 * 
	 * @return the constraint by statement
	 * 
	 * @throws ModelAccessException 
	 */
	public Constraint getConstraintByStatement(
			StatementDefinition stmt)
			throws ModelAccessException {

		Namespace ns = this.myModel.findNamespace(stmt.getNamespaceList());

		for (Constraint con : ns.getOwnedRule()) {
			if (stmt.isSourceOf(con)) {
				return con;
			}
		}

		return null;
	}

	/**
	 * <p>
	 * Sets the model instance of this {@link TestPerformer}.
	 * </p>
	 * 
	 * @param modelInstance The model instance for which the test shall be performed.
	 */
	public void setModelInstanceType(String modelInstance) {

		this.modelInstanceType = modelInstance;
	}

	/**
	 * create an empty model instance on the currently loaded model.
	 */
	public void loadEmptyModelInstance() {

		// create an empty model instance
		IModelInstanceProvider modelInstanceProvider =
				this.getModelInstanceProvider();

		IModelInstance emptyModelInstance =
				modelInstanceProvider.createEmptyModelInstance(this.myModel);

		// set as current model instance
		this.setActiveModelInstance(emptyModelInstance);
	}

	/**
	 * <p>
	 * Loads a given fileName as an {@link IModelInstance} of the actual selected
	 * {@link IModel}.
	 * </p>
	 * 
	 * @param modelInstanceFileName The file of the provider class of the {@link IModelInstance}.
	 * 
	 * @throws RuntimeException Thrown, if the given file can not be found.
	 */
	public void loadModelInstance(String modelInstanceFileName)
			throws RuntimeException {

		assert (this.myModel != null);

		// if it was loaded,
		// reactivate it.
		if (this.testEnv.loadedInstances.containsKey(modelInstanceFileName)) {
			this.setActiveModelInstance(this.testEnv.loadedInstances
					.get(modelInstanceFileName));
			return;
		}

		File modelInstanceFile =
				this.safeOpenFile(this.testEnv.fileDirectory + modelInstanceFileName);

		IModelInstanceProvider modelInstanceProvider =
				this.getModelInstanceProvider();

		IModelInstance loadedInstance = null;

		// Load the model instance.
		try {
			loadedInstance =
					modelInstanceProvider.getModelInstance(modelInstanceFile,
							this.myModel);
		} catch (ModelAccessException e) {
			throw new RuntimeException(e);
		}

		assert (loadedInstance != null);

		this.testEnv.loadedInstances.put(modelInstanceFileName, loadedInstance);

		this.setActiveModelInstance(loadedInstance);
	}

	/**
	 * Parses the ocl statement.
	 * 
	 * @param completeStatement 
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception 
	 */
	public void parseOclStatement(String completeStatement) throws Exception {

		OCL2Parser anOCLparser;

		StringReader reader = new StringReader(completeStatement);
		anOCLparser = new OCL2Parser(myModel, reader);

		anOCLparser.parse();

	}

	/**
	 * Sets the active model instance.
	 * 
	 * @param instance 
	 */
	protected void setActiveModelInstance(IModelInstance instance) {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();
		IModelInstance[] instances =
				modelInstanceRegistry.getModelInstances(this.myModel);
		for (IModelInstance inst : instances) {
			// instance already loaded
			if (inst.equals(instance)) {
				// activate and return
				modelInstanceRegistry.setActiveModelInstance(this.myModel, instance);
				return;
			}
		}
		// else add and activate
		modelInstanceRegistry.addModelInstance(this.myModel, instance);
		modelInstanceRegistry.setActiveModelInstance(this.myModel, instance);

		this.myGlobalEnvironment.setModelInstance(instance);
	}

	/**
	 * Gets the active model instance.
	 * 
	 * @return the active model instance
	 */
	protected IModelInstance getActiveModelInstance() {

		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		return modelInstanceRegistry.getActiveModelInstance(this.myModel);
	}

	/**
	 * retrieves model instance provider for the current model instance type.
	 * (It's assumed that the modelInstanceType doesn't change during
	 * TestPerformer lifecycle.)
	 * 
	 * @return current model instanceprovider
	 */
	protected IModelInstanceProvider getModelInstanceProvider() {

		IModelInstanceTypeRegistry tMTypeReg =
				ModelBusPlugin.getModelInstanceTypeRegistry();
		IModelInstanceType tMInstanceType =
				tMTypeReg.getModelInstanceType(this.modelInstanceType);
		return tMInstanceType.getModelInstanceProvider();
	}

	/**
	 * <p>
	 * Loads an {@link IModel} which uses the corresponding meta model.
	 * </p>
	 * 
	 * @param modelName Filename of the model
	 * 
	 * @throws RuntimeException 
	 */
	protected void loadModel(String modelName) throws RuntimeException {

		/* Check if the model has not already been loaded yet. */
		if (!(this.myModel != null && this.myModel.getDisplayName().equals(
				modelName))) {

			File modelFile;

			modelFile = this.safeOpenFile(this.testEnv.fileDirectory + modelName);

			/* Try to load the model. */
			try {
				IModelRegistry modelRegistry;

				this.myModel = this.metaModel.getModelProvider().getModel(modelFile);

				modelRegistry = ModelBusPlugin.getModelRegistry();

				boolean loaded = false;
				for (IModel model : modelRegistry.getModels()) {
					// model already loaded
					if (myModel.equals(model)) {
						loaded = true;
						break;
					}
				}
				if (!loaded) {
					modelRegistry.addModel(this.myModel);
				}
				modelRegistry.setActiveModel(this.myModel);
			} catch (ModelAccessException e) {
				throw new RuntimeException(e);
			}
		}
		// no else.
	}

	// ************************************
	// * Some Helper Functions
	// ************************************

	/**
	 * Safe open file.
	 * 
	 * @param fileName 
	 * 
	 * @return the file
	 * 
	 * @throws RuntimeException 
	 */
	protected File safeOpenFile(String fileName) throws RuntimeException {

		File tmpFile = new File(fileName);

		// error reporting if it doesnt exists
		if (!tmpFile.exists()) {
			String msg;

			msg = "The requested file ";
			msg += fileName;
			msg += " doesn't exists.";

			throw new RuntimeException(msg);
		}

		return tmpFile;
	}

	/**
	 * creates an object adapting the passed model instance object.
	 * 
	 * @param object Object to be adapteds
	 * 
	 * @return 
	 */
	public IModelInstanceObject createModelInstanceAdapter(Object object){

		IModelInstanceObject result = null;
		try {
			
			result =
					(IModelInstanceObject) this.getActiveModelInstance()
							.addModelInstanceElement(object);
		}

		catch (TypeNotFoundInModelException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * <p>
	 * Add a given {@link Object} as a variable to the
	 * {@link IInterpretationEnvironment} used for preparation and interpretation.
	 * </p>
	 * 
	 * @param path The path and name of the variable which shall be set.
	 * @param value The {@link IModelInstanceObject} value of the set variable as an
	 * Object.
	 * 
	 * @return <code>true</code>, if the given value has been set as a value
	 * successfully.
	 */
	public boolean setEnvironmentVariable(String path, Object value) {

		boolean result;

		/* Convert the object into an OclAny. */
		OclAny adaptedObject;

		try {
			adaptedObject =
					JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
							this.myGlobalEnvironment.getModelInstance()
									.getModelInstanceFactory().createModelInstanceElement(value));

			/* Add the variable to the environment. */
			this.myInterpreter.setEnviromentVariable(path, adaptedObject);

			result = true;
		}

		catch (TypeNotFoundInModelException e) {
			result = false;
		}

		return result;
	}

	/**
	 * creates an ocl root adapter from a model instance adapter in order to being
	 * able to execute a method on the model level.
	 * 
	 * @param obj 
	 * 
	 * @return 
	 */
	public OclAny createOclRootAdapterByMIObject(IModelInstanceObject obj) {

		return JavaStandardlibraryPlugin.getStandardLibraryFactory().createOclAny(
				obj);
	}
}