package tudresden.ocl20.benchmark.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
import tudresden.ocl20.pivot.modelbus.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry;
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
 * @author Franz Eichhorn
 */
public class TestPerformer {

	public static enum constraintFileType {
		STATEMENT, // complete statement containing context
		QUERY, 	// only query
		PREPOST	// only pre/post, context will be attached
	};

	// @ The package of the meta model.
	private String metaModelName;

	// @ The name of the bundle of the model file.
	private String modelInstanceType;

	// @ The name of the bundle of the model file.
	private String modelBundle;

	// @ The path of the UML model file.
	private String modelFile;

	// @ directory where all files are stored
	protected String fileDirectory = "";

	protected List<String> loadedOclFiles;

	private static int testCounter = 0;

	/**
	 * Used to select uninterpreted {@link Constraint}s for the actual test case.
	 */
	protected Set<Constraint> interpretedConstraints;

	/**
	 * The global {@link IEnvironment} used during interpretation and preparation.
	 */
	protected IEnvironment myGlobalEnvironment;

	/** The {@link IOclInterpreter} used by this {@link TestPerformer}. */
	protected IOclInterpreter myInterpreter = null;

	protected IModel myModel = null;

	// @ currently loaded model instance. Since the models can evulate
	// @ there can be different model instances during lifetime
	protected IModelInstance myModelInstance = null;

	protected IMetamodel metaModel = null;


	protected BenchmarkLogger bLogger;

	// @ Name of the currently executed test
	protected String testName;

	/**
	 * <p>
	 * This constructor loads the UML2 meta model, the UML2 model and the model
	 * instance.
	 * </p>
	 */
	public TestPerformer(String testName) {

		super();

		this.testName = testName;

		this.bLogger = new BenchmarkLogger(this.testName);

		this.loadedOclFiles = new ArrayList<String>();
		// initLogger();
	}



	/**
	 * <p>
	 * Converts the canonical name of a given {@link Class} into a {@link List}
	 * containing the name of the packages of the path.
	 * </p>
	 * 
	 * @param aClass
	 *          The class which path shall be returned.
	 * @return A {@link List} containing the name of the packages of the path of
	 *         the given {@link Class}.
	 */
	public static List<String> getClassNameAsPathList(Class<?> aClass) {

		List<String> result;

		String aCanonicalName;
		String[] pathArray;

		aCanonicalName = aClass.getCanonicalName();

		pathArray = aCanonicalName.split("\\.");

		result = new ArrayList<String>(pathArray.length);

		for (int index = 0; index < pathArray.length; index++) {
			result.add(pathArray[index]);
		}

		return result;
	}

	/**
	 * @return The global {@link IEnvironment} used by this {@link TestPerformer}
	 *         for preparation and interpretation of {@link Constraint}s.
	 */
	public IEnvironment getGlobalEnvironment() {

		return this.myGlobalEnvironment;
	}

	/**
	 * @param pathList
	 *          The {@link Type} which instances shall be returned.
	 * @return All {@link IModelObject}s of the active {@link IModelInstance}
	 *         which are of the {@link Type} described by the given pathList.
	 */
	public List<IModelObject> getObjectsOfKind(List<String> pathList) {

		List<IModelObject> result;

		result =
				ModelBusPlugin.getModelInstanceRegistry().getActiveModelInstance(
						this.myModel).getObjectsOfKind(pathList);

		return result;

	}

	public void addInterpretedConstraint(Constraint c) {

		this.interpretedConstraints.add(c);
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

			this.metaModel =
					ModelBusPlugin.getMetamodelRegistry().getMetamodel(metaModelName);

			if (metaModel == null) {
				throw new RuntimeException(
						"Unable to load UML2 meta model during test.");
			}
			// no else.

			/* Get the bundle location for the model files. */
			fileDirectory = Platform.getBundle(modelBundle).getLocation();

			/* Remove the 'reference:file:' from the beginning. */
			fileDirectory = fileDirectory.substring(15);

			/* Load the model. */
			this.loadModel(modelFile);

			/* Initialize the set containing already interpreted constraints. */
			this.interpretedConstraints = new HashSet<Constraint>();

			/* Initialize the global environment. */
			this.myGlobalEnvironment = Environment.getGlobalEnvironment();

			/* Initialize the interpreter. */
			this.myInterpreter = new OclInterpreter(this.myGlobalEnvironment);
			this.myInterpreter.setUseCache(false);
		}

		catch (Exception e) {
			throw new RuntimeException("Unable to initialize the test. Reason: "
					+ e.getMessage());
		}
	}

	/**
	 * loads an Ocl file by loading each statement separately to avoid abort due
	 * to parser exceptions Statements that could not be loaded, will be stored as
	 * well as the error messages
	 * 
	 * @param oclFile
	 *          Filename of Ocl File
	 */
	public void safeLoadStatementFile(String oclFileName) throws RuntimeException {

		this.safeLoadConstraintFile(oclFileName, constraintFileType.STATEMENT);
	}

	protected boolean parseOclStatement(String oclStatement,
			String packageInformation) {

		OCL2Parser anOCLparser;
		String completeStatement = packageInformation + oclStatement + "endpackage";
		StringReader reader = new StringReader(completeStatement);
		anOCLparser = new OCL2Parser(myModel, reader);

		// try to parse the ocl statement
		try {
			anOCLparser.parse();
		} catch (Exception e) {
			this.bLogger.oclParseError(oclStatement, e);
			return false;
		}

		return true;
	}

	/**
	 * Returns the package (is expected to be the first line) of a BufferedReader
	 * 
	 * @param reader
	 *          Input reader that contains package statement
	 */
	protected String getPackageFromReader(BufferedReader reader)
			throws RuntimeException {

		String line = "";
		try {
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				// empty line or comment at the beginning
				if (line.equals("") || line.startsWith("--")) {
					continue;
				}

				if (line.startsWith("package")) {
					return line;
				}

				throw new RuntimeException(
						"Cannot find the package-statement in the reader. This has to be the first statement!");
			}

			throw new RuntimeException(
					"Unexpected End of Reader, did not find a package");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void safeLoadQueryFile(String queryFile) {

		this.safeLoadConstraintFile(queryFile, constraintFileType.QUERY);
	}

	/**
	 * Loads an ocl file either as query or normal invariant file...
	 */
	protected void safeLoadConstraintFile(String fileName, constraintFileType type) {

		// check if the file has been loaded already
		if (this.loadedOclFiles.contains(fileName)) {
			return;
		}
		FileReader oclFileReader = this.safeOpenFileReader(fileName);
		BufferedReader bufReader = new BufferedReader(oclFileReader);

		// counter for success/failure
		int[] statistics = {0,0};
		
		// package information to be copied
		// at the beginning of each statement
		String packageInformation = this.getPackageFromReader(bufReader) + "\n";

		switch(type){
		case STATEMENT:
			this.handleStatements(bufReader, packageInformation, statistics);
			break;
			
		case QUERY:
			this.handleQueries(bufReader, packageInformation, statistics);
			break;
			
		case PREPOST:
			this.handlePrePost(bufReader, packageInformation, statistics);
			break;
		}
		
		this.printParseStatistics(fileName, statistics);

		// register for being loaded
		this.loadedOclFiles.add(fileName);
	}
	
	protected void handleStatements(BufferedReader source,
			String packageInfo, int[] statistics) {
		
		String strLine;
		StringBuilder oclStatementString = new StringBuilder();
			
		while ((strLine = this.nextLine(source)) != null || oclStatementString.length() > 0) {
			
			if ((strLine == null || strLine.startsWith("context"))
					&& oclStatementString.length() > 0) {
				
				// try to parse ocl statement
				if (this.parseOclStatement(oclStatementString.toString(), packageInfo)) {
					++statistics[0];
				}
				else {
					++statistics[1];
				}
				// empty ocl string
				oclStatementString.setLength(0);
			}
			
			if(strLine != null){
				oclStatementString.append(strLine);		
				oclStatementString.append("\n");
			}
		}
	}
	
	protected void handlePrePost(BufferedReader source,
			String packageInfo, int[] statistics){
		
		String strLine;
		while ((strLine = this.nextLine(source)) != null) {
			
		}
	}
	
	protected void handleQueries(BufferedReader source,
			String packageInfo, int[] statistics){
		
		
		
//		String query = "";
//		String methodName = "query_" + (testCounter++) + "()";
//		// query
//		if (strLine.startsWith("?")) {
//			query = strLine.substring(1);
//			query =
//					"context Person\ndef: " + methodName + ":Boolean\n=" + query;
//		}
//		//					
//		// def: getServicesByLevel(levelName: String): Set(Service)
//		// = levels->select(name = levelName).availableServices->asSet()
//
//		// parse query
//		if (this.parseOclStatement(query, packageInformation)) {
//			++success;
//		}
//		else {
//			++failure;
//		}
//
//		// try to interpret the constraint immediately
//		query = "context Person\ninv: " + "self." + methodName;
//		if (this.parseOclStatement(query, packageInformation)) {
//			++success;
//		}
//		else {
//			++failure;
//		}
//		
//		
//		
//		String strLine;
//		while ((strLine = this.nextLine(source)) != null) {
//			
//		}
	}
	
	/**
	 * returns the next line of the reader by 
	 * omitting comments, empty lines etc.
	 * It's throwing RuntimeException when the
	 * file seems corrupt.
	 * 
	 * @return next line or null in case of end
	 */
	protected String nextLine(BufferedReader source)
	{
		String line;
		try{
			// as long as there are lines to read
			while((line = source.readLine()) != null){
				line = line.trim();
				if(line.length() == 0 || line.startsWith("--")){
					continue;
				}
				
				if(line.startsWith("package")) {
					throw new RuntimeException(
							"Package information should be only once in an Ocl File");
				}
				
				// end of file reached
				if(line.startsWith("endpackage")){
					return null;
				}
				
				return line;
				
			}

		}catch(IOException e){
			throw new RuntimeException("Error Reading Source File.", e);
		}
		
		// file empty
		return null;
	}

	protected void printParseStatistics(String fileName, int[] statistics) {

		this.bLogger.outHead3("Statistics for " + fileName);
		this.bLogger.printf("successful statements: %d ", statistics[0]);
		this.bLogger.printf("failed     statements: %d ", statistics[1]);
	}

	// public InterpretationPerformer
	// prepareRemainingConstraintsForSingleInterpretation(List<IModelObject>
	// modelObjects){
	//		
	// /* Collect all not yet interpreted constraints. */
	// List<Constraint> uninterpretedConstraints =
	// this.collectNotYetInterpretedConstraints();
	//		
	// // prepare interpretation Performer
	// InterpretationPerformer intPerf = new
	// InterpretationPerformer(this.myInterpreter, this);
	//		
	//		
	// intPerf.addConstraints(uninterpretedConstraints);
	// intPerf.addModelObjects(modelObjects);
	//		
	// return intPerf;
	//		
	// }

	public void checkActiveModelInstance() {

		bLogger.outHead1("Interpretation using current model instance");
		List<IModelObject> testObjects = this.myModelInstance.getObjects();
		List<Constraint> constraints = this.getAllActiveConstraints();

		bLogger.printf("constraints : %d", constraints.size());
		bLogger.printf("test objects: %d", testObjects.size());
		bLogger.printf("->max tests : %d", constraints.size() * testObjects.size());

		bLogger.outHead2("Results");

		NamedElement conElement = null;
		NamedElement conParent = null;
		OclRoot result = null;

		// counter
		int success = 0, failure = 0;

		for (IModelObject obj : testObjects) {
			for (Constraint con : constraints) {

				conElement = (NamedElement) con.getConstrainedElement().get(0);
				if (conElement instanceof Property || conElement instanceof Operation) {
					conElement = conElement.getOwner();
				}

				// if they equal
				if (obj.getQualifiedNameString().equals(conElement.getQualifiedName())) {
					// try to interpret

					try {
						result = this.myInterpreter.interpret(con, obj);
					} catch (Exception e) {
						bLogger.interpretationException(con, obj, e);
						++failure;
						continue;
					}

					if (((JavaOclBoolean) result).isTrue()) {
						System.out.println(con.getSpecification().getBody());
						++success;
					}
					else {
						++failure;
						bLogger.interpretationError(con, obj, result);
					}
				}
			}
		}

		bLogger.outHead3("Results");
		bLogger.printf("successful: %d", success);
		bLogger.printf("failed    : %d", failure);
		bLogger.printf("total     : %d", success + failure);

	}

	protected List<Constraint> getAllActiveConstraints() {

		Namespace root = null;
		List<Constraint> constraints = new LinkedList<Constraint>();
		try {
			root = this.myModel.getRootNamespace();
		} catch (ModelAccessException e) {
			throw new RuntimeException(e);
		}

		return this.getAllActiveConstraints(root, constraints);
	}

	protected List<Constraint> getAllActiveConstraints(Namespace ns,
			List<Constraint> constraints) {

		for (Namespace nested : ns.getNestedNamespace()) {
			this.getAllActiveConstraints(nested, constraints);
		}

		constraints.addAll(ns.getOwnedRule());

		return constraints;
	}

	/**
	 * <p>
	 * Removes a given variable from the {@link IEnvironment} used for preparation
	 * and interpretation.
	 * </p>
	 * 
	 * @param path
	 *          The path and name of the variable which shall be set.
	 */
	public void resetVar(String path) {

		/* Add the variable to the environment. */
		this.myGlobalEnvironment.addVar(path, null);
	}

	/**
	 * <p>
	 * Sets the meta model of this {@link TestPerformer}.
	 * </p>
	 * 
	 * @param metaModel
	 *          The meta model for which the test shall be performed.
	 */
	public void setMetaModel(String metaModel) {

		this.metaModelName = metaModel;
	}

	/**
	 * <p>
	 * Sets the model bundle of this {@link TestPerformer}.
	 * </p>
	 * 
	 * @param modelBundle
	 *          The model bundle for which the test shall be performed.
	 */
	public void setModelBundle(String modelBundle) {

		this.modelBundle = modelBundle;
	}

	/**
	 * <p>
	 * Sets the model file of this {@link TestPerformer}.
	 * </p>
	 * 
	 * @param modelFile
	 *          The model file for which the test shall be performed.
	 */
	public void setModelFile(String modelFile) {

		this.modelFile = modelFile;
	}

	/**
	 * <p>
	 * Sets the model instance of this {@link TestPerformer}.
	 * </p>
	 * 
	 * @param modelInstance
	 *          The model instance for which the test shall be performed.
	 */
	public void setModelInstanceType(String modelInstance) {

		this.modelInstanceType = modelInstance;
	}

	/**
	 * <p>
	 * Add a given {@link Object} as a variable to the {@link IEnvironment} used
	 * for preparation and interpretation.
	 * </p>
	 * 
	 * @param path
	 *          The path and name of the variable which shall be set.
	 * @param value
	 *          The value of the set variable as an Object.
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
	 * <p>
	 * Loads a given fileName as an {@link IModelInstance} of the actual selected
	 * {@link IModel}.
	 * </p>
	 * 
	 * @param modelInstanceFileName
	 *          The file of the provider class of the {@link IModelInstance}.
	 * @throws RuntimeException
	 *           Thrown, if the given file can not be found.
	 */
	public void loadModelInstance(String modelInstanceFileName)
			throws RuntimeException {

		/* Check if a model has been set yet. */
		if (this.myModel == null) {
			throw new RuntimeException("No model found to load a model instance.");

		}

		File modelInstanceFile =
				this.safeOpenFile(fileDirectory + modelInstanceFileName);
		;

		IModelInstanceTypeRegistry tMTypeReg =
				ModelBusPlugin.getModelInstanceTypeRegistry();
		IModelInstanceType tMInstanceType =
				tMTypeReg.getModelInstanceType(this.modelInstanceType);
		IModelInstanceProvider modelInstanceProvider =
				tMInstanceType.getModelInstanceProvider();

		this.myModelInstance = null;

		/* Load the model instance. */
		try {
			this.myModelInstance =
					modelInstanceProvider.getModelInstance(modelInstanceFile,
							this.myModel);
		} catch (ModelAccessException e) {
			throw new RuntimeException(e);
		}

		/*
		 * Add the successfully loaded model instance to the model instance
		 * registry.
		 */
		IModelInstanceRegistry modelInstanceRegistry;
		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

		modelInstanceRegistry.addModelInstance(myModel, this.myModelInstance);

		modelInstanceRegistry.setActiveModelInstance(myModel, this.myModelInstance);

		this.myGlobalEnvironment.setModelInstance(this.myModelInstance);
	}

	/**
	 * <p>
	 * Loads an {@link IModel} which uses the corresponding meta model.
	 * </p>
	 * 
	 * @param modelName
	 *          The filename of the UML2 model.
	 * @throws Ocl22JavaException
	 *           Is thrown if the {@link IModel} cannot be initialized or the
	 *           model file is not found.
	 */
	protected void loadModel(String modelName) throws RuntimeException {

		/* Check if the model has not already been loaded yet. */
		if (!(this.myModel != null && this.myModel.getDisplayName().equals(
				modelName))) {

			File modelFile;

			modelFile = this.safeOpenFile(this.fileDirectory + modelName);

			/* Try to load the model. */
			try {
				IModelRegistry modelRegistry;

				this.myModel = this.metaModel.getModelProvider().getModel(modelFile);

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

	// ************************************
	// * Some Helper Functions
	// ************************************

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

	protected FileReader safeOpenFileReader(String fileName)
			throws RuntimeException {

		File tmpFile = this.safeOpenFile(fileName);

		try {
			FileReader tmpReader = new FileReader(tmpFile);
			return tmpReader;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error opening file " + fileName
					+ " (it exists, though)");
		}
	}
}