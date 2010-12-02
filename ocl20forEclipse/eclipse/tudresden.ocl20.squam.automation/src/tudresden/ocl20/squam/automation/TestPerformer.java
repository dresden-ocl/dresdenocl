package tudresden.ocl20.squam.automation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource.Diagnostic;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclPostParser;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemanticsException;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.IModelRegistry;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.provider.JavaModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import uml.Model;

public class TestPerformer {

	private final static String META_MODEL = UML2MetamodelPlugin.ID;

	private static String MODEL_FILE = "model/umlmodel.uml";

	private final static String DIR_TESTS = "/tests/";

	private static String bundleRoot;

	private IMetamodel metaModel;

	private IModelInstance modelInstance;

	private IModel model;

	private IOclInterpreter interpreter;

	static {

		Bundle thisBundle = Platform.getBundle(Activator.PLUGIN_ID);

		// TODO: better way to find model/oclexpr location
		bundleRoot = thisBundle.getLocation().substring(15);

	}

	public TestPerformer() {
		this.init();
	}

	public void init() {
		// try to load model and meta model
		this.metaModel = ModelBusPlugin.getMetamodelRegistry().getMetamodel(
				META_MODEL);

		if (this.metaModel == null) {
			throw new RuntimeException("Unable to load meta model during test.");
		}

		this.loadModel();

		this.createModelInstance();

		this.createInterpreter();

	}

	private void createInterpreter() {
		this.interpreter = OclInterpreterPlugin
				.createInterpreter(this.modelInstance);
	}

	private void loadModel() {

		File modelFile = new File(bundleRoot + MODEL_FILE);

		if (!modelFile.exists()) {
			throw new RuntimeException("Model file "
					+ modelFile.getAbsolutePath() + " doesn't exist");
		}

		IModelProvider modelProvider = this.metaModel.getModelProvider();
		try {
			this.model = modelProvider.getModel(modelFile);

			IModelRegistry modelRegistry = ModelBusPlugin.getModelRegistry();
			modelRegistry.addModel(this.model);
			modelRegistry.setActiveModel(this.model);

		} catch (Throwable e) {
			throw new RuntimeException("couldn't load model file");
		}

	}

	private void createModelInstance() {
		if (this.model == null) {
			throw new RuntimeException(
					"no model found, can't load model instance");
		}

		IModelInstanceProvider miProvider = new JavaModelInstanceProvider();

		// create empty instance and insert an object as test object
		this.modelInstance = miProvider.createEmptyModelInstance(this.model);
		try {
			this.modelInstance.addModelInstanceElement(new Model());
		} catch (TypeNotFoundInModelException e) {
			throw new RuntimeException(
					"wrong model, couldn't create instance object");
		}
	}

	public List<Constraint> doParse(String test) throws ParseException {

		// create resource
		URI oclFile = URI.createPlatformPluginURI(Activator.PLUGIN_ID
				+ DIR_TESTS + test, true);

		ResourceSet rs = new ResourceSetImpl();
		OclResource resource = new OclResource(oclFile);
		rs.getResources().add(resource);
		resource.setModel(this.model);

		try {
			resource.load(Collections.EMPTY_MAP);
			return OclPostParser.concreteSyntaxToEssentialOcl(resource);

		} catch (Throwable e) {
			EList<Diagnostic> resourceErrors = resource.getErrors();

			StringBuilder builder = new StringBuilder();
			builder.append("Errors that occoured during parsing\n");

			if (!resourceErrors.isEmpty()) {
				for (Diagnostic error : resourceErrors) {
					builder.append("\t");
					builder.append(error.getMessage());
					builder.append("\n");
				}
			}else{
				builder.append("\t-- unknown.");
			}

			throw new ParseException(builder.toString());
		}

	}

	public IInterpretationResult doInterpret(Constraint constraint) {
		IModelInstanceObject obj = this.modelInstance
				.getAllModelInstanceObjects().get(0);

		return this.interpreter.interpretConstraint(constraint, obj);
	}
}
