package tudresden.ocl20.benchmark.common;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
////
//import tudresden.ocl20.pivot.modelbus.IMetamodel;
//import tudresden.ocl20.pivot.modelbus.IModelInstance;
//import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
//import tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry;
//import tudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry;
//import tudresden.ocl20.pivot.modelbus.IModel;
//import tudresden.ocl20.pivot.modelbus.IModelProvider;
//import tudresden.ocl20.pivot.modelbus.IModelRegistry;
//import tudresden.ocl20.pivot.modelbus.ModelAccessException;
//import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
//import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
//import tudresden.ocl20.interpreter.test.tests.TestPerformer;

//import java.lang.reflect.*;


/**
 * Adapter to TestPerformerClass used by Benchmark
 * @author Franz Eichhorn
 */
public class BenchmarkPerformer extends TestPerformer
{
	public BenchmarkPerformer(String name)
	{
		super(name);
	}
		
//	public void setModelInstanceType(String modelInstance)
//	{
//		super.setModelInstanceType(modelInstance);
//		
//		// register new model instancde type in modelbus
//		IModelInstanceTypeRegistry miTypeReg = ModelBusPlugin.getModelInstanceTypeRegistry();
//		//miTypeReg.
//	}
}	
//}
//public class BenchmarkPerformer
//{
//	protected TestPerformer perf;
//	
//	//! meta model supplied by model bus
//	protected IMetamodel metaModel;
//	
//	public BenchmarkPerformer()
//	{
//		this.perf = new TestPerformer();
//	}
//	
//	public void setMetaModel(String model)
//	{
//		this.perf.setMetaModel(model);
//	}
//	
//	public void setModelFile(String modelFile)
//	{
//		this.perf.setModelFile(modelFile);
//	}
//	
//	public void setModelInstanceFile(String modelInstance)
//	{
//		this.perf.setModelInstanceFile(modelInstance);
//	}
//	
//	/**
//	 * initializes the test performer
//	 */
//	public void init() throws Exception
//	{
//		// try to load metamodel and model
//		try{
//			//Class refTestPerf = Class.forName("TestPerformer");
//			Field fields[] = this.perf.getClass().getFields();
//			for(int i=0; i<fields.length; ++i){
//				System.out.println(fields[i].toString());
//			}
//			this.metaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel((String)this.perf.getClass().getField("modelFile").get(this.perf));
//		}catch(Exception e){
//			throw e;
//		}
//	}
//	
//}
/**
 *
 */
//public class TestPerformer {
//	
//	protected static IModelProvider modelProvider = null;
//	
//	// active model
//	protected IModel model = null;
//
//	//! current model instance
//	protected IModelInstance modelInstance = null;
//	
//	protected static TestPerformer instance = null;
//	
//
//	/**
//	 * Metamodel (received by model bus)
//	 */
//	protected static IMetamodel metaModel = null;
//	
//	/**
//	 * Base Dir for Test Data
//	 */
//	protected static String TEST_DATA_DIR = "./src/testData/";
//	
//	
//	
//	/**
//	 * This constructor loads the uml metamodel.
//	 * @throws Exception is thrown if any error occurred while loading the model or the metamodel
//	 */
//	//public  TestPerformer() throws Exception {
//	public BenchmarkPerformer() throws Exception
//	{
//		super();
//		
//		try {
//			BenchmarkPerformer.metaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel("tudresden.ocl20.pivot.metamodels.ecore");
//			
//			BenchmarkPerformer.modelProvider = BenchmarkPerformer.metaModel.getModelProvider();
//			if (BenchmarkPerformer.metaModel == null) {
//				throw new Exception("Unable to load uml metamodel.   ");
//			}
//		} catch(Exception ex) {
//			
//			throw new Exception("Unable to load uml metamodel.   " + ex.getMessage());
//		}
//
//	}
//	
//	/**
//	 * Loads passed model file (under current meta model)
//	 */
//	public void loadModel(String modelName) 
//			throws RuntimeException 
//	{
//		
//		assert(this.model != null);
//		assert(BenchmarkPerformer.modelProvider != null);
//		assert(BenchmarkPerformer.metaModel != null);
//
//		// skip if model has been loaded already
//		if (!(this.model != null && this.model.getDisplayName().equals(
//				modelName))) {
//
//			File modelFile = this.safeOpenFile(modelName);
//
//			// load model and register to Model Bus
//			try {
//				IModelRegistry modelRegistry;
//
//				this.model = BenchmarkPerformer.modelProvider.getModel(modelFile);
//
//				modelRegistry = ModelBusPlugin.getModelRegistry();
//
//				modelRegistry.addModel(this.model);
//				modelRegistry.setActiveModel(this.model);
//			}
//
//			catch (ModelAccessException e) {
//				throw new RuntimeException("The model could not be loaded. "
//						+ e.getMessage());
//			}
//		}
//		// no else.
//	}
//	
//	
//	/**
//	 * <p>
//	 * Parses the file <i>oclFileName</i> against the loaded UML model file. If
//	 * an error occurred an CodeGenerationException will be thrown.
//	 * </p>
//	 * 
//	 * @param oclFileName
//	 *            The OCL file to be parsed.
//	 * @throws Ocl22JavaException
//	 *             Is thrown if any error occurs
//	 */
//	public void loadOCLFile(String oclFileName) 
//	throws RuntimeException 
//	{
//		assert(this.model != null);
//		
//		FileReader oclFileReader = this.safeOpenFileReader(oclFileName);
//
//		OCL2Parser anOCLparser = new OCL2Parser(this.model, oclFileReader);
//		
//		/* Try to parse the OCL file. */
//		try {
//			anOCLparser.parse();
//		}
//
//		catch (Exception e) {
//			String msg;
//
//			msg = "Exception during parsing of given OCL constraints. ";
//			msg += e.getMessage();
//			
//			throw new RuntimeException(msg);
//		}
//	}
//			
//	/**
//	 * Get the model instance of the loaded model.
//	 * @return the model instance of the loaded model
//	 */
//	public IModel getCurrentModel() {
//		return this.model;
//	}
//	
//	
//	
//	/**
//	 * <p>
//	 * Loads a given fileName as an {@link IModelInstance} of the actual
//	 * selected {@link IModel}.
//	 * </p>
//	 * 
//	 * @param modelInstanceProviderFileName
//	 *            The file of the provider class of the {@link IModelInstance}.
//	 * @throws RuntimeException
//	 *             Thrown, if the given file can not be found.
//	 */
//	protected void loadModelInstance(String miProviderFilename)
//			throws RuntimeException {
//
//		/* Check if a model has been set yet. */
//		assert(this.model != null);
//		
//		IModelInstanceProvider modelInstanceProvider;
//
////		modelInstanceProvider = ModelBusPlugin
////				.getModelInstanceTypeRegistry().getModelInstanceType(
////						modelInstanceType).getModelInstanceProvider();
//		modelInstanceProvider = new ModelInstanceProvider();
//		
//		this.modelInstance = null;
//		
//		File miProviderFile = this.safeOpenFile(miProviderFilename);
//		
//		/* Load the model instance. */
//		try {
//
//			this.modelInstance = modelInstanceProvider.getModelInstance(
//					miProviderFile, this.myModel);
//		}
//
//		catch (ModelAccessException e) {
//
//			String msg;
//
//			msg = "An error occured when loading model '" + myModel + "'";
//
//			e.printStackTrace();
//
//			throw new RuntimeException(msg, e);
//		}
//
//		/*
//		 * Add the successfully loaded model instance to the model instance
//		 * registry.
//		 */
//		IModelInstanceRegistry modelInstanceRegistry;
//		modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();
//
//		modelInstanceRegistry.addModelInstance(this.model,
//				this.modelInstance);
//		modelInstanceRegistry.setActiveModelInstance(this.model,
//				this.modelInstance);
//		
//
//	}
//
//
//	
//	// ************************************
//	// * Some Helper Functions
//	// ************************************
//	
//	protected File safeOpenFile(String fileName)
//			throws RuntimeException
//	{
//		File tmpFile = new File(BenchmarkPerformer.TEST_DATA_DIR + fileName);
//		
//		// error reporting if it doesnt exists
//		if (!tmpFile.exists()) {
//			String msg;
//
//			msg = "The requested file ";
//			msg += BenchmarkPerformer.TEST_DATA_DIR + fileName;
//			msg += " doesn't exists.";
//
//			throw new RuntimeException(msg);
//		}
//		
//		return tmpFile;
//	}
//	
//	
//	protected FileReader safeOpenFileReader(String fileName)
//	throws RuntimeException
//	{
//		File tmpFile = this.safeOpenFile(fileName);
//		
//		try{
//			FileReader tmpReader = new FileReader(tmpFile);
//			return tmpReader;
//		}catch(FileNotFoundException e) {
//			throw new RuntimeException("Error opening file " + fileName + " (it exists, though)");
//		}
//	}
//			
//		
//
//		
//}
