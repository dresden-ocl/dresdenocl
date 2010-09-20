package tudresden.ocl20.testautomation.execution;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclPostParser;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.IModelRegistry;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.provider.JavaModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.testautomation.TestautomationPlugin;
import tudresden.ocl20.testautomation.exceptions.TestExecutionException;
import tudresden.ocl20.testautomation.exceptions.TestInitializationException;

public class TestPerformer {

	private final static String META_MODEL = UML2MetamodelPlugin.ID;

	private static Logger log = Logger.getLogger(TestPerformer.class);

	private IMetamodel metaModel;

	/**
	 * absolute URI pointing to the working directory of the *parent* eclipse
	 * instance --> not the temporary one started to execute the tests
	 */
	private static URI instanceRoot;

	private IModelProvider modelProvider;

	// model used for all tests
	private IModel testModel;

	// for the moment we only support java model instances
	IModelInstanceProvider modelInstanceProvider;

	// model instance used for all tests;
	private IModelInstance testModelInstance;

	private IOclInterpreter interpreter;

	private static IModelInstanceRegistry mir;

	private static TestEnvironment testEnv;

	private static IModelRegistry modelRegistry;

	/**
	 * to create a resource you need a file (even if the file doesnt exist).
	 * Otherwise you'll get a NPE
	 * 
	 * @TODO this is clearly a bug!!
	 */
	private static URI dummyFile;

	static {
		testEnv = new TestEnvironment();

		// TODO: configure logging system properly (LoggingPlugin)
		PropertyConfigurator.configure("log4j.properties");

		mir = ModelBusPlugin.getModelInstanceRegistry();

		modelRegistry = ModelBusPlugin.getModelRegistry();

		dummyFile = URI.createURI("dummy.ocl");

		String bundleRoot =
				Platform.getBundle(TestautomationPlugin.PLUGIN_ID).getLocation()
						.substring(10);
		URI bundleRootUri = URI.createURI(bundleRoot);
		// temporary uri to resolve a path to go "up"
		URI goUpUri = URI.createURI("..");
		instanceRoot = goUpUri.resolve(bundleRootUri);

		// instance root must not be a file and not be relative
		assert (!instanceRoot.isFile());
		assert (instanceRoot.isHierarchical() && !instanceRoot.isRelative());
	}

	public TestPerformer(String modelPath, String modelInstancePath)
			throws TestInitializationException {

		this();

		if (modelPath == null || modelPath.isEmpty()) {
			throw new TestInitializationException("Model must not be null");
		}

		this.loadModel(URI.createURI(modelPath));

		// model instance is not mandatory (e.g. for parse-only-tests)
		if (modelInstancePath != null && !modelInstancePath.isEmpty()) {
			this.initInstanceAndInterpreter(URI.createURI(modelInstancePath));
		}

	}

	private void initInstanceAndInterpreter(URI modelInstancePath)
			throws TestInitializationException {

		this.loadModelInstance(modelInstancePath);

		this.createInterpreter();
	}

	private TestPerformer() {

		this.metaModel =
				ModelBusPlugin.getMetamodelRegistry().getMetamodel(META_MODEL);

		if (this.metaModel == null) {
			throw new RuntimeException("Unable to load meta model during test.");
		}
		// initialize provider
		this.modelProvider = this.metaModel.getModelProvider();
		this.modelInstanceProvider = new JavaModelInstanceProvider();
	}

	private void createInterpreter() throws TestInitializationException {

		try {
			this.interpreter =
					OclInterpreterPlugin.createInterpreter(this.testModelInstance);
		} catch (Throwable e) {
			throw new TestInitializationException(e);
		}
	}

	private void loadModel(URI modelPath) throws TestInitializationException {

		// check if exists in environment
		this.testModel = testEnv.getModelIfLoaded(modelPath);
		if (this.testModel == null) {
			String absoluteModelPath = this.getAbsolutePathForRelativeURI(modelPath);
			File modelFile = new File(absoluteModelPath);

			if (!modelFile.exists()) {
				throw new TestInitializationException("Model file "
						+ modelFile.getAbsolutePath() + " doesn't exist");
			}

			try {
				this.testModel = this.modelProvider.getModel(modelFile);
				testEnv.addModel(modelPath, this.testModel);
			} catch (ModelAccessException e) {
				throw new TestInitializationException(e);
			}
		}

		this.refreshLoadedModel();
	}

	/**
	 * TODO: doku
	 */
	public void refreshLoadedModel() {

		// remove model if it was present to avoid
		// redefining properties with "def" and getting
		// errors
		modelRegistry.removeModel(this.testModel);
		modelRegistry.addModel(this.testModel);
		modelRegistry.setActiveModel(this.testModel);
	}

	private void loadModelInstance(URI filePath)
			throws TestInitializationException {

		if (this.testModel == null) {
			throw new TestInitializationException(
					"no model found, can't load model instance");
		}

		this.testModelInstance = testEnv.getModelInstanceIfLoaded(filePath);
		if (this.testModelInstance != null) {
			return;
		}

		try {

			// TODO: remove URI and URL mix!!
			URI absolutePath = this.resolveAgainstInstanceURI(filePath);
			URL url = new URL(absolutePath.toString());
			this.testModelInstance =
					this.modelInstanceProvider.getModelInstance(url, this.testModel);
			testEnv.addModelInstance(filePath, this.testModelInstance);
			mir.addModelInstance(this.testModelInstance);
		} catch (ModelAccessException e) {
			throw new TestInitializationException(e);
		} catch (MalformedURLException e) {
			System.out.println("malformed url");
		}
	}

	/**
	 * assumes
	 * 
	 * @param test
	 * @return
	 * @throws TestExecutionException
	 * @throws ParseException
	 */
	public Constraint parseConstraint(String statement)
			throws TestExecutionException {

		ResourceSet rs = new ResourceSetImpl();
		OclResource resource = new OclResource(dummyFile);

		try {
			InputStream inputStream = this.createStatementInStream(statement);
			resource.reload(inputStream, null);

			rs.getResources().add(resource);
			resource.setModel(this.testModel);
			List<Constraint> parsedConstraints =
					OclPostParser.concreteSyntaxToEssentialOcl(resource);

			if (parsedConstraints.size() == 0) {
				throw new TestExecutionException("no successfully parsed constraint");
			}

			if (parsedConstraints.size() > 1) {
				throw new TestExecutionException(
						"there is more than one parsed constraint "
								+ "--> check how the constraint file is split");
			}

			return parsedConstraints.get(0);
		} catch (Throwable e) {
			String errors = this.getParseErrors(resource);
			throw new TestExecutionException(errors, e);
		}
	}

	private String getParseErrors(OclResource resource) {

		EList<Diagnostic> resourceErrors = resource.getErrors();
		StringBuilder builder = new StringBuilder();
		builder.append("Errors that occoured during parsing\n");

		if (!resourceErrors.isEmpty()) {
			for (Diagnostic error : resourceErrors) {
				builder.append("\t");
				builder.append(error.getMessage());
				builder.append("\n");
			}
		}
		else {
			builder.append("\t-- unknown.");
		}
		return builder.toString();
	}

	private InputStream createStatementInStream(String statement)
			throws UnsupportedEncodingException {

		log.debug(statement);

		return new ByteArrayInputStream(statement.getBytes("UTF-8"));

	}

	public IInterpretationResult doInterpret(Constraint constraint) {

		IModelInstanceObject obj =
				this.testModelInstance.getAllModelInstanceObjects().get(0);

		return this.interpreter.interpretConstraint(constraint, obj);
	}

	private URI resolveAgainstInstanceURI(URI path) {

		return path.resolve(instanceRoot);
	}

	private String getAbsolutePathForRelativeURI(URI path) {

		URI resolved = this.resolveAgainstInstanceURI(path);
		return resolved.path();
	}

}
