/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Facade of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.facade;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.Metric;
import org.eclipse.emf.common.util.URI;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.java.JavaMetaModelPlugin;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.Ocl2DeclCodeFactory;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.IOcl2Sql;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.Ocl2SQLFactory;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2Java;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.Ocl2JavaFactory;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tudresden.ocl20.pivot.tools.transformation.ITransformation;
import tudresden.ocl20.pivot.tools.transformation.TransformationFactory;
import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;
import tudresden.ocl20.pivot.tools.transformation.impl.Tuple;

/**
 * <p>
 * This is a <i>Facade</i> that provides access to all main services of Dresden
 * OCL2 for Eclipse. The use of this Facade is easy but it depends on all
 * bundles of Dresden OCL2 for Eclipse. If you want to use only some services
 * you probably should implement your own Facade.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl2ForEclipseFacade {

	/** The ID of the EMF Ecore {@link IMetamodel}. */
	public final static String ECORE_META_MODEL = EcoreMetamodelPlugin.ID;

	/** The ID of the EMF Ecore {@link IModelInstanceType}. */
	public final static String ECORE_MODEL_INSTANCE_TYPE = EcoreModelInstanceTypePlugin.PLUGIN_ID;

	/** The ID of the reflective Java {@link IMetamodel}. */
	public final static String JAVA_META_MODEL = JavaMetaModelPlugin.ID;

	/** The ID of the Java {@link IModelInstanceType}. */
	public final static String JAVA_MODEL_INSTANCE_TYPE = JavaModelInstanceTypePlugin.PLUGIN_ID;

	/** The ID of the Eclipse MDT UML2 {@link IMetamodel}. */
	public final static String UML2_MetaModel = UML2MetamodelPlugin.ID;

	/** The ID of the XML Schema (XSD) {@link IMetamodel}. */
	public final static String XSD_META_MODEL = XSDMetamodelPlugin.ID;

	/** The ID of the XML {@link IModelInstanceType}. */
	public final static String XML_MODEL_INSTANCE_TYPE = XmlModelInstanceTypePlugin.PLUGIN_ID;

	/**
	 * Cached interpreters for interpretation. They are stored in a
	 * {@link WeakHashMap}. If an {@link IModelInstance} becomes garbage, its
	 * {@link IOclInterpreter} becomes garbage as well.
	 */
	private static Map<IModelInstance, IOclInterpreter> cachedInterpreters = new WeakHashMap<IModelInstance, IOclInterpreter>();

	/** The {@link IOcl2Java} representing the Java/AspectJ code generator. */
	private static IOcl2Java javaCodeGenerator = null;

	/** The {@link IOcl2Sql} representing the Java/AspectJ code generator. */
	private static IOcl2Sql sqlCodeGenerator = null;

	/**
	 * <p>
	 * Generates the AspectJ code for a given {@link List} of {@link Constraint}
	 * s and a given {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s used for code generation.
	 * @param settings
	 *            The {@link IOcl22CodeSettings} used for code generation (can
	 *            be <code>null</code> if default settings shall be used).
	 * @return The generated AspectJ code as a set of {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl22CodeException
	 */
	public static List<String> generateAspectJCode(
			List<Constraint> constraints, IOcl2JavaSettings settings)
			throws IllegalArgumentException, Ocl2CodeException {

		if (constraints == null || constraints.size() == 0) {
			throw new IllegalArgumentException(
					"The list of constraints must not be emtpy.");
		}
		// no else.

		if (javaCodeGenerator == null) {
			javaCodeGenerator = Ocl2JavaFactory.getInstance()
					.createJavaCodeGenerator();
		}
		// no else.

		javaCodeGenerator.resetEnvironment();

		if (settings != null) {
			javaCodeGenerator.setSettings(settings);
		}

		else {
			/* Necessary to replace possibly altered settings. */
			javaCodeGenerator.setSettings(Ocl2JavaFactory.getInstance()
					.createJavaCodeGeneratorSettings());
		}

		return javaCodeGenerator.transformInstrumentationCode(constraints);
	}

	/**
	 * <p>
	 * Generates the AspectJ code for a given {@link Constraint} and a given
	 * {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} used for code generation.
	 * @param settings
	 *            The {@link IOcl22CodeSettings} used for code generation (can
	 *            be <code>null</code> if default settings shall be used).
	 * @return The generated AspectJ code as a {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl22CodeException
	 */
	public static String generateAspectJCode(Constraint constraint,
			IOcl2JavaSettings settings) throws IllegalArgumentException,
			Ocl2CodeException {

		if (constraint == null) {
			throw new IllegalArgumentException(
					"The constraint must not be null.");
		}
		// no else.

		List<Constraint> constraints;
		constraints = new ArrayList<Constraint>();
		constraints.add(constraint);

		return generateAspectJCode(constraints, settings).get(0);
	}

	/**
	 * <p>
	 * Generates the Java fragment code for a given {@link List} of
	 * {@link Constraint}s and a given {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s used for code generation.
	 * @param settings
	 *            The {@link IOcl22CodeSettings} used for code generation (can
	 *            be <code>null</code> if default settings shall be used).
	 * @return The generated fragments as a set of {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl22CodeException
	 */
	public static List<String> generateJavaFragmentCode(
			List<Constraint> constraints, IOcl2JavaSettings settings)
			throws IllegalArgumentException, Ocl2CodeException {

		if (constraints == null || constraints.size() == 0) {
			throw new IllegalArgumentException(
					"The list of constraints must not be emtpy.");
		}
		// no else.

		if (javaCodeGenerator == null) {
			javaCodeGenerator = Ocl2JavaFactory.getInstance()
					.createJavaCodeGenerator();
		}
		// no else.

		javaCodeGenerator.resetEnvironment();

		if (settings != null) {
			javaCodeGenerator.setSettings(settings);
		}

		else {
			/* Necessary to replace possibly altered settings. */
			javaCodeGenerator.setSettings(Ocl2JavaFactory.getInstance()
					.createJavaCodeGeneratorSettings());
		}

		return javaCodeGenerator.transformFragmentCode(constraints);
	}

	/**
	 * <p>
	 * Generates the Java fragment code for a given {@link Constraint} and a
	 * given {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} used for code generation.
	 * @param settings
	 *            The {@link IOcl22CodeSettings} used for code generation (can
	 *            be <code>null</code> if default settings shall be used).
	 * @return The generated fragment as a {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl22CodeException
	 */
	public static String generateJavaFragmentCode(Constraint constraint,
			IOcl2JavaSettings settings) throws IllegalArgumentException,
			Ocl2CodeException {

		if (constraint == null) {
			throw new IllegalArgumentException(
					"The constraint must not be null.");
		}
		// no else.

		List<Constraint> constraints;
		constraints = new ArrayList<Constraint>();
		constraints.add(constraint);

		return generateJavaFragmentCode(constraints, settings).get(0);
	}

	/**
	 * <p>
	 * Returns an {@link IOcl22CodeSettings} instance to configure Java or
	 * AspectJ code generation.
	 * </p>
	 * 
	 * @return An {@link IOcl22CodeSettings} instance to configure Java or
	 *         AspectJ code generation.
	 */
	public static IOcl2JavaSettings getJavaCodeGeneratorSettings() {

		return Ocl2JavaFactory.getInstance().createJavaCodeGeneratorSettings();
	}

	/**
	 * <p>
	 * Generates the SQL code for a given {@link List} of {@link Constraint} s
	 * and a given {@link IOcl2DeclSettings}.
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s used for code generation.
	 * @param settings
	 *            The {@link IOcl2DeclSettings} used for code generation (can be
	 *            <code>null</code> if default settings shall be used).
	 * @param model
	 *            The {@link IModel} for code generation
	 * @return The generated SQL code as a set of {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl2CodeException
	 */
	public static List<String> generateSQLCode(List<Constraint> constraints,
			IOcl2DeclSettings settings, IModel model)
			throws IllegalArgumentException, Ocl2CodeException {

		if (constraints == null || constraints.size() == 0) {
			throw new IllegalArgumentException(
					"The list of constraints must not be emtpy.");
		}
		// no else.

		if (sqlCodeGenerator == null) {
			sqlCodeGenerator = Ocl2SQLFactory.getInstance()
					.createSQLCodeGenerator();
		}
		// no else.

		sqlCodeGenerator.resetEnvironment();

		if (settings != null) {
			sqlCodeGenerator.setSettings(settings);
		}

		else {
			/* Necessary to replace possibly altered settings. */
			sqlCodeGenerator.setSettings(getDeclCodeGeneratorSettings());
		}
		sqlCodeGenerator.setInputModel(model);
		return sqlCodeGenerator.transformFragmentCode(constraints);
	}

	/**
	 * <p>
	 * Generates the SQL code for a given {@link Constraint} and a given
	 * {@link IOcl2DeclSettings}.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} used for code generation.
	 * @param settings
	 *            The {@link IOcl2DeclSettings} used for code generation (can be
	 *            <code>null</code> if default settings shall be used).
	 * @param model
	 *            The {@link IModel} for code generation
	 * @return The generated SQL code as a {@link String}s.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@link List} of {@link Constraint}s is empty or
	 *             <code>null</code>.
	 * @throws Ocl2CodeException
	 */
	public static String generateSQLCode(Constraint constraint,
			IOcl2DeclSettings settings, IModel model)
			throws IllegalArgumentException, Ocl2CodeException {

		if (constraint == null) {
			throw new IllegalArgumentException(
					"The constraint must not be null.");
		}
		// no else.

		List<Constraint> constraints;
		constraints = new ArrayList<Constraint>();
		constraints.add(constraint);

		return generateSQLCode(constraints, settings, model).get(0);
	}

	/**
	 * <p>
	 * Returns an {@link IOcl2DeclSettings} instance to configure SQL code
	 * generation.
	 * </p>
	 * 
	 * @return An {@link IOcl2DeclSettings} instance to configure SQL code
	 *         generation.
	 */
	public static IOcl2DeclSettings getDeclCodeGeneratorSettings() {

		return Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
	}

	/**
	 * <p>
	 * Return an {@link ITemplateGroup} instance with template for code
	 * generation.
	 * </p>
	 * 
	 * @param templateGroupName
	 *            The name of template group
	 * @return a instance of the template group or <i>null</i> if there no
	 *         template group with the name
	 * @throws TemplateException
	 */
	public static ITemplateGroup getTemplateGroup(String templateGroupName)
			throws TemplateException {

		return TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup(
				templateGroupName);
	}

	/**
	 * <p>
	 * Return a list with the names of transformation.
	 * </P>
	 * 
	 * @return the list with names.
	 */
	public static List<String> getTransformationList() {

		return TransformationPlugin.getTransformationRegistry()
				.getTransformationList();
	}

	/**
	 * <p>
	 * Returns a name list of transformation which transform a in_type to a
	 * out_type. The settings of transformation must implements the
	 * IOcl2DeclSettings.
	 * </p>
	 * 
	 * @param in_type
	 *            the type of transformation input
	 * @param out_type
	 *            the type of transformation output
	 * @return the list with names
	 */
	public static List<String> getTransformation(Class<?> in_type,
			Class<?> out_type) {

		return TransformationPlugin.getTransformationRegistry()
				.getTransformationList(in_type, out_type,
						IOcl2DeclSettings.class);
	}

	/**
	 * <p>
	 * Returns an {@link ITransformation} instance with the parameters.
	 * </p>
	 * 
	 * @param transId
	 *            the name of the transformation
	 * @param modelIn
	 *            the type of the transformation input
	 * @param modelOut
	 *            the type of the transformation output
	 * @param settings
	 *            the settings of the transformation
	 * @param modelInName
	 *            the input name of the transformation
	 * @param modelOutName
	 *            the output name of the transformation
	 * @return a new instance of {@link ITransformation} or <i>null</i> if there
	 *         no transformation with the parameters.
	 */
	public static <I, S, O> ITransformation<I, S, O> getTransformation(
			String transId, Class<I> modelIn, Class<O> modelOut,
			Class<S> settings, String modelInName, String modelOutName) {

		return TransformationFactory.getInstance().getTransformation(transId,
				modelIn, modelOut, settings, modelInName, modelOutName);
	}

	/**
	 * <p>
	 * Returns an {@link ITransformation} instance with the parameters.
	 * </p>
	 * 
	 * @param transId
	 *            the name of the transformation
	 * @param modelIn
	 *            the type of the transformation input
	 * @param modelOut1
	 *            the type of the first element of the output tuple
	 * @param modelOut2
	 *            the type of the second element of the output tuple
	 * @param settings
	 *            the settings of the transformation
	 * @param modelInName
	 *            the input name of the transformation
	 * @param modelOutName
	 *            the output name of the transformation
	 * @return a new instance of {@link ITransformation} or <i>null</i> if there
	 *         no transformation with the parameters.
	 */
	public static <I, S, O1, O2> ITransformation<I, S, Tuple<O1, O2>> getParallelTransformation(
			String transId, Class<I> modelIn, Class<O1> modelOut1,
			Class<O2> modelOut2, Class<S> settings, String modelInName,
			String modelOutName) {

		return TransformationFactory.getInstance().getParallelTransformation(
				transId, modelIn, modelOut1, modelOut2, settings, modelInName,
				modelOutName);
	}

	/**
	 * <p>
	 * Returns the {@link IMetamodel} for a given ID or throws an
	 * {@link IllegalArgumentException} if the given ID does not exists.
	 * </p>
	 * 
	 * @param id
	 *            The ID of the {@link IMetamodel} that shall be returned. Use
	 *            the provided constants, if the id of the {@link IMetamodel} is
	 *            unknown.
	 * @return The {@link IMetamodel} of the given id.
	 * @throws IllegalArgumentException
	 *             Thrown if the given ID does not exists.
	 */
	public static IMetamodel getMetaModel(String id)
			throws IllegalArgumentException {

		if (id == null) {
			throw new IllegalArgumentException(
					"Parameter 'id' must not be null.");
		}
		// no else.

		IMetamodel result;
		result = ModelBusPlugin.getMetamodelRegistry().getMetamodel(id);

		if (result == null) {
			throw new IllegalArgumentException("A MetaModel for the given id '"
					+ id + "' does not exist.");
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns a {@link Set} containing all {@link IMetamodel}s of Dresden OCL2
	 * for Eclipse.
	 * </p>
	 */
	public static Set<IMetamodel> getMetaModels() {

		return new HashSet<IMetamodel>(Arrays.asList(ModelBusPlugin
				.getMetamodelRegistry().getMetamodels()));
	}

	/**
	 * Computes a {@link Metric} object containing metrics for a given
	 * {@link Constraint}.
	 * 
	 * @param constraint
	 *            The {@link Constraint} for which the {@link Metric} object
	 *            shall be computed.
	 * @return A {@link Metric} object containing metrics for the given
	 *         {@link Constraint}.
	 */
	public static Metric getMetrics(Constraint constraint) {
		return OclMetrics.computeMetric(constraint);
	}

	/**
	 * Computes a {@link Metric} object containing metrics for a given
	 * {@link Constraint}.
	 * 
	 * @param constraint
	 *            The {@link Constraint} for which the {@link Metric} object
	 *            shall be computed.
	 * @return A {@link Metric} object containing metrics for the given
	 *         {@link Constraint}.
	 */
	public static Metric getMetrics(IModel model) {
		try {
			return OclMetrics.computeMetric(model.getConstraints());
		} catch (ModelAccessException e) {
			throw new IllegalStateException(
					"Exception during computation of metric for the given model.",
					e);
		}
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModel} for a given {@link File} and a given
	 * {@link IMetamodel}.
	 * </p>
	 * 
	 * @param file
	 *            The {@link File} that shall be loaded.
	 * @param metaModel
	 *            The {@link IMetamodel} of the {@link IModel}.
	 * @return The loaded {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModel getModel(File file, IMetamodel metaModel)
			throws ModelAccessException, IllegalArgumentException {

		if (file == null) {
			throw new IllegalArgumentException(
					"Parameter 'file' must not be null.");
		}

		else if (metaModel == null) {
			throw new IllegalArgumentException(
					"Parameter 'metaModel' must not be null.");
		}
		// no else.

		/* Probably add the MetaModel. */
		if (!getMetaModels().contains(metaModel)) {
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(metaModel);
		}
		// no else.

		IModel result;

		result = metaModel.getModelProvider().getModel(file);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModel} for a given {@link File} and a given
	 * {@link IMetamodel}'s.
	 * </p>
	 * 
	 * @param file
	 *            The {@link File} of the file that shall be loaded.
	 * @param metaModelID
	 *            The ID of {@link IMetamodel} of the {@link IModel}.
	 * @return The loaded {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid or the given ID does not
	 *             identify an {@link IMetamodel}.
	 */
	public static IModel getModel(File file, String metaModelID)
			throws ModelAccessException, IllegalArgumentException {

		if (file == null) {
			throw new IllegalArgumentException(
					"Parameter 'file' must not be null.");
		}

		else if (metaModelID == null) {
			throw new IllegalArgumentException(
					"Parameter 'metaModelID' must not be null.");
		}
		// no else.

		IModel result;

		result = getMetaModel(metaModelID).getModelProvider().getModel(file);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModel} for a given location and a given
	 * {@link IMetamodel}.
	 * </p>
	 * 
	 * @param location
	 *            The location of the file that shall be loaded.
	 * @param metaModel
	 *            The {@link IMetamodel} of the {@link IModel}.
	 * @return The loaded {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModel getModel(URL location, IMetamodel metaModel)
			throws ModelAccessException, IllegalArgumentException {

		if (location == null) {
			throw new IllegalArgumentException(
					"Parameter 'location' must not be null.");
		}

		else if (metaModel == null) {
			throw new IllegalArgumentException(
					"Parameter 'metaModel' must not be null.");
		}
		// no else.

		/* Probably add the MetaModel. */
		if (!getMetaModels().contains(metaModel)) {
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(metaModel);
		}
		// no else.

		IModel result;

		result = metaModel.getModelProvider().getModel(location);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModel} for a given location and a given
	 * {@link IMetamodel}'s.
	 * </p>
	 * 
	 * @param location
	 *            The location of the file that shall be loaded.
	 * @param metaModelID
	 *            The ID of {@link IMetamodel} of the {@link IModel}.
	 * @return The loaded {@link IModel}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid or the given ID does not
	 *             identify an {@link IMetamodel}.
	 */
	public static IModel getModel(URL location, String metaModelID)
			throws ModelAccessException, IllegalArgumentException {

		if (location == null) {
			throw new IllegalArgumentException(
					"Parameter 'location' must not be null.");
		}

		else if (metaModelID == null) {
			throw new IllegalArgumentException(
					"Parameter 'metaModelID' must not be null.");
		}
		// no else.

		IModel result;

		result = getMetaModel(metaModelID).getModelProvider()
				.getModel(location);

		return result;
	}

	/**
	 * <p>
	 * Creates and returns an empty {@link IModelInstance} for a given
	 * {@link IModel} and a given {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceType
	 *            The {@link IModelInstanceType} of the {@link IModelInstance}.
	 * @return The created empty {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getEmptyModelInstance(IModel model,
			IModelInstanceType modelInstanceType) throws ModelAccessException,
			IllegalArgumentException {

		if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}

		else if (modelInstanceType == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceType' must not be null.");
		}
		// no else.

		/* Probably add the ModelInstanceType. */
		if (!getModelInstanceTypes().contains(modelInstanceType)) {
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					modelInstanceType);
		}
		// no else.

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider()
				.createEmptyModelInstance(model);

		return result;
	}

	/**
	 * <p>
	 * Creates and returns an empty {@link IModelInstance} for a given
	 * {@link IModel} and a given {@link IModelInstanceType}'s ID.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceTypeID
	 *            The id of the {@link IModelInstanceType} of the
	 *            {@link IModelInstance}.
	 * @return The created empty {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getEmptyModelInstance(IModel model,
			String modelInstanceTypeID) throws ModelAccessException,
			IllegalArgumentException {

		if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}
		// no else.

		IModelInstanceType modelInstanceType;
		modelInstanceType = getModelInstanceType(modelInstanceTypeID);

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider()
				.createEmptyModelInstance(model);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModelInstance} for a given {@link File}, a
	 * given {@link IModel} and a given {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @param file
	 *            The {@link File} that shall be loaded.
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceType
	 *            The {@link IModelInstanceType} of the {@link IModelInstance}.
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getModelInstance(File file, IModel model,
			IModelInstanceType modelInstanceType) throws ModelAccessException,
			IllegalArgumentException {

		if (file == null) {
			throw new IllegalArgumentException(
					"Parameter 'file' must not be null.");
		}

		else if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}

		else if (modelInstanceType == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceType' must not be null.");
		}
		// no else.

		/* Probably add the ModelInstanceType. */
		if (!getModelInstanceTypes().contains(modelInstanceType)) {
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					modelInstanceType);
		}
		// no else.

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider().getModelInstance(
				file, model);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModelInstance} for a given {@link File}, a
	 * given {@link IModel} and a given ID of a {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @param file
	 *            The {@link File} that shall be loaded.
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceTypeID
	 *            The id of the {@link IModelInstanceType} of the
	 *            {@link IModelInstance}.
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getModelInstance(File file, IModel model,
			String modelInstanceTypeID) throws ModelAccessException,
			IllegalArgumentException {

		if (file == null) {
			throw new IllegalArgumentException(
					"Parameter 'file' must not be null.");
		}

		else if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}
		// no else.

		IModelInstanceType modelInstanceType;
		modelInstanceType = getModelInstanceType(modelInstanceTypeID);

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider().getModelInstance(
				file, model);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModelInstance} for a given location, a given
	 * {@link IModel} and a given {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @param location
	 *            The location of the {@link File} that shall be loaded.
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceType
	 *            The {@link IModelInstanceType} of the {@link IModelInstance}.
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getModelInstance(URL location, IModel model,
			IModelInstanceType modelInstanceType) throws ModelAccessException,
			IllegalArgumentException {

		if (location == null) {
			throw new IllegalArgumentException(
					"Parameter 'location' must not be null.");
		}

		else if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}

		else if (modelInstanceType == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceType' must not be null.");
		}
		// no else.

		/* Probably add the ModelInstanceType. */
		if (!getModelInstanceTypes().contains(modelInstanceType)) {
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					modelInstanceType);
		}
		// no else.

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider().getModelInstance(
				location, model);

		return result;
	}

	/**
	 * <p>
	 * Loads and returns an {@link IModelInstance} for a given location, a given
	 * {@link IModel} and a given ID of a {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @param location
	 *            The location of the {@link File} that shall be loaded.
	 * @param model
	 *            The {@link IModel} of the {@link IModelInstance}.
	 * @param modelInstanceTypeID
	 *            The id of the {@link IModelInstanceType} of the
	 *            {@link IModelInstance}.
	 * @return The loaded {@link IModelInstance}.
	 * @throws ModelAccessException
	 *             Thrown, if the given location can not be loaded.
	 * @throws IllegalArgumentException
	 *             Thrown if the parameters are invalid.
	 */
	public static IModelInstance getModelInstance(URL location, IModel model,
			String modelInstanceTypeID) throws ModelAccessException,
			IllegalArgumentException {

		if (location == null) {
			throw new IllegalArgumentException(
					"Parameter 'location' must not be null.");
		}

		else if (model == null) {
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		}

		IModelInstanceType modelInstanceType;
		modelInstanceType = getModelInstanceType(modelInstanceTypeID);

		IModelInstance result;

		result = modelInstanceType.getModelInstanceProvider().getModelInstance(
				location, model);

		return result;
	}

	/**
	 * <p>
	 * Returns a {@link Set} containing all {@link IModelInstanceType}s of
	 * Dresden OCL2 for Eclipse.
	 * </p>
	 */
	public static Set<IModelInstanceType> getModelInstanceTypes() {

		return new HashSet<IModelInstanceType>(Arrays.asList(ModelBusPlugin
				.getModelInstanceTypeRegistry().getModelInstanceTypes()));
	}

	/**
	 * <p>
	 * Returns the {@link IModelInstanceType} for a given ID or throws an
	 * {@link IllegalArgumentException} if the given ID does not exists.
	 * </p>
	 * 
	 * @param id
	 *            The ID of the {@link IModelInstanceType} that shall be
	 *            returned. Use the provided constants, if the id of the
	 *            {@link IModelInstanceType} is unknown.
	 * @return The {@link IModelInstanceType} of the given id.
	 * @throws IllegalArgumentException
	 *             Thrown if the given ID does not exists.
	 */
	public static IModelInstanceType getModelInstanceType(String id)
			throws IllegalArgumentException {

		if (id == null) {
			throw new IllegalArgumentException(
					"Parameter 'id' must not be null.");
		}
		// no else.

		IModelInstanceType result;
		result = ModelBusPlugin.getModelInstanceTypeRegistry()
				.getModelInstanceType(id);

		if (result == null) {
			throw new IllegalArgumentException(
					"A ModelInstanceType for the given id '" + id
							+ "' does not exist.");
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Interpret the given {@link Constraint} for the given
	 * {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @param constraint
	 *            The {@link Constraint} to be interpreted.
	 * @param modelInstance
	 *            The {@link IModelInstance} the {@link IModelInstanceElement}
	 *            belongs to.
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} representing the current
	 *            object. Can be <code>null</code> if none of the given
	 *            {@link Constraint}s requires an {@link IModelInstanceObject}
	 *            as context, i.e. all of the are defined in a static context
	 *            (static def, or body/derive/init on static feature).
	 * 
	 * @return The {@link IInterpretationResult} of the interpretation or
	 *         <code>null</code> if the given {@link Constraint} cannot be
	 *         checked for the given {@link IModelInstanceElement} (wrong
	 *         {@link Type} of {@link IModelInstanceElement}).
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link IModelInstance} is in an invalid
	 *             state.
	 */
	public static IInterpretationResult interpretConstraint(
			Constraint constraint, IModelInstance modelInstance,
			IModelInstanceElement modelInstanceElement)
			throws IllegalArgumentException, ModelAccessException {

		if (constraint == null) {
			throw new IllegalArgumentException(
					"Parameter 'constraint' must not be null.");
		}

		else if (modelInstance == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstance' must not be null.");
		}
		// no else.

		IInterpretationResult result;
		IOclInterpreter interpreter;

		/* Create or use a cached interpreter. */
		if (cachedInterpreters.containsKey(modelInstance)) {
			interpreter = cachedInterpreters.get(modelInstance);
		}

		else {
			interpreter = OclInterpreterPlugin.createInterpreter(modelInstance);
			cachedInterpreters.put(modelInstance, interpreter);
		}

		result = interpreter.interpretConstraint(constraint,
				modelInstanceElement);

		return result;
	}

	/**
	 * <p>
	 * Interprets a given {@link Collection} of {@link Constraint} for the given
	 * {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s to be interpreted.
	 * @param modelInstance
	 *            The {@link IModelInstance} the {@link IModelInstanceElement}
	 *            belongs to.
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} representing the current
	 *            object. Can be <code>null</code> if none of the given
	 *            {@link Constraint}s requires an {@link IModelInstanceObject}
	 *            as context, i.e. all of the are defined in a static context
	 *            (static def, or body/derive/init on static feature).
	 * 
	 * @return A {@link List} containing the {@link IInterpretationResult} of
	 *         the interpretation as {@link OclRoot}s.
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 * @throws ModelAccessException
	 *             Thrown, if the given {@link IModelInstance} is in an invalid
	 *             state.
	 */
	public static List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints, IModelInstance modelInstance,
			IModelInstanceElement modelInstanceElement)
			throws IllegalArgumentException, ModelAccessException {

		if (constraints == null) {
			throw new IllegalArgumentException(
					"Parameter 'constraints' must not be null.");
		}
		// no else.

		List<IInterpretationResult> result;
		result = new ArrayList<IInterpretationResult>();

		for (Constraint constraint : constraints) {
			IInterpretationResult aResult;
			aResult = interpretConstraint(constraint, modelInstance,
					modelInstanceElement);

			if (aResult != null) {
				result.add(aResult);
			}
			// no else.
		}
		// end for.

		return result;
	}

	/**
	 * <p>
	 * Interprets its given postconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please be aware, that some postconditions must be prepared before
	 * their constrained {@link Operation} is invoked to save some values such
	 * as <code>@pre</code> values!</strong> Use the method
	 * {@link Ocl2ForEclipseFacade#preparePostConditions(IModelInstance, IModelInstanceElement, Operation, IModelInstanceElement[], Collection)}
	 * to prepare postconditions.
	 * </p>
	 * 
	 * @param modelInstance
	 *            The {@link IModelInstance} the {@link IModelInstanceElement}
	 *            belongs to.
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameters
	 *            The values of the {@link Operation}'s {@link Parameter}s as a
	 *            {@link List} of {@link IModelInstanceElement} values.
	 * @param resultValue
	 *            The result of the {@link Operation}'s invocation or
	 *            <code>null</code> if no result has been returned (e.g., a void
	 *            {@link Operation}).
	 * @param postConditions
	 *            The postconditions that shall be interpreted.
	 *            <b>Attention:</b> if this {@link Collection} contains
	 *            {@link Constraint}s of the {@link ConstraintKind} that is
	 *            different than {@link ConstraintKind#POSTCONDITION} they will
	 *            not be interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 */
	public static List<IInterpretationResult> interpretPostConditions(
			IModelInstance modelInstance,
			IModelInstanceElement modelInstanceElement, Operation operation,
			List<IModelInstanceElement> parameters,
			IModelInstanceElement resultValue,
			Collection<Constraint> postConditions)
			throws IllegalArgumentException {

		if (modelInstance == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstance' must not be null.");
		}

		else if (modelInstanceElement == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceElement' must not be null.");
		}

		else if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}

		else if (parameters == null) {
			throw new IllegalArgumentException(
					"Parameter 'parameters' must not be null.");
		}

		else if (postConditions == null) {
			throw new IllegalArgumentException(
					"Parameter 'postConditions' must not be null.");
		}
		// no else.

		List<IInterpretationResult> result;
		IOclInterpreter interpreter;

		/* Create or use a cached interpreter. */
		if (cachedInterpreters.containsKey(modelInstance)) {
			interpreter = cachedInterpreters.get(modelInstance);
		}

		else {
			interpreter = OclInterpreterPlugin.createInterpreter(modelInstance);
			cachedInterpreters.put(modelInstance, interpreter);
		}

		result = interpreter.interpretPostConditions(modelInstanceElement,
				operation, parameters.toArray(new IModelInstanceElement[0]),
				resultValue, postConditions);

		return result;
	}

	/**
	 * <p>
	 * Interprets its given preconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelInstance
	 *            The {@link IModelInstance} the {@link IModelInstanceElement}
	 *            belongs to.
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameters
	 *            The values of the {@link Operation}'s {@link Parameter}s as a
	 *            {@link List} of {@link IModelInstanceElement} values.
	 * @param preConditions
	 *            The preconditions that shall be interpreted. <b>Attention:</b>
	 *            if this {@link Collection} contains {@link Constraint}s of the
	 *            {@link ConstraintKind} that is different than
	 *            {@link ConstraintKind#PRECONDITION} they will not be
	 *            interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 */
	public static List<IInterpretationResult> interpretPreConditions(
			IModelInstance modelInstance,
			IModelInstanceElement modelInstanceElement, Operation operation,
			List<IModelInstanceElement> parameters,
			Collection<Constraint> preConditions)
			throws IllegalArgumentException {

		if (modelInstance == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstance' must not be null.");
		}

		else if (modelInstanceElement == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceElement' must not be null.");
		}

		else if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}

		else if (parameters == null) {
			throw new IllegalArgumentException(
					"Parameter 'parameters' must not be null.");
		}

		else if (preConditions == null) {
			throw new IllegalArgumentException(
					"Parameter 'preConditions' must not be null.");
		}
		// no else.

		List<IInterpretationResult> result;
		IOclInterpreter interpreter;

		/* Create or use a cached interpreter. */
		if (cachedInterpreters.containsKey(modelInstance)) {
			interpreter = cachedInterpreters.get(modelInstance);
		}

		else {
			interpreter = OclInterpreterPlugin.createInterpreter(modelInstance);
			cachedInterpreters.put(modelInstance, interpreter);
		}

		result = interpreter.interpretPreConditions(modelInstanceElement,
				operation, parameters.toArray(new IModelInstanceElement[0]),
				preConditions);

		return result;
	}

	/**
	 * <p>
	 * Removes a {@link IModel model}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} that shall be removed.
	 * 
	 * @return <code>true</code> if the given {@link IModel} has been removed.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given parameter is invalid.
	 */
	public static boolean removeModel(IModel model)
			throws IllegalArgumentException {

		return ModelBusPlugin.getModelRegistry().removeModel(model);
	}

	/**
	 * <p>
	 * Removes a {@link IModelInstance}.
	 * </p>
	 * 
	 * @param modelInstance
	 *            The {@link IModelInstance} that shall be removed.
	 * 
	 * @return <code>true</code> if the given {@link IModelInstance} has been
	 *         removed.
	 * @throws IllegalArgumentException
	 *             Thrown, if the given parameter is invalid.
	 */
	public static boolean removeModelInstance(IModelInstance modelInstance)
			throws IllegalArgumentException {

		return ModelBusPlugin.getModelInstanceRegistry().removeModelInstance(
				modelInstance);
	}

	/**
	 * <p>
	 * Parses a {@link List} of OCL {@link Constraint}s that are provided by a
	 * given {@link File}.
	 * </p>
	 * 
	 * @param file
	 *            The {@link File} providing the {@link File} or Stream to be
	 *            parsed.
	 * @param model
	 *            The {@link IModel} for which the {@link Constraint}s shall be
	 *            parsed.
	 * @param addToModel
	 *            Indicates whether or not the parsed {@link Constraint}s, its
	 *            defined fields and functions to the given {@link IModel}.
	 * @return The parsed {@link Constraint}s as a {@link List}.
	 * @throws ParseException
	 *             Thrown, if the parsing fails.
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 */
	public static List<Constraint> parseConstraints(File file, IModel model,
			boolean addToModel) throws ParseException, IllegalArgumentException {

		if (file == null) {
			throw new IllegalAccessError("Parameter 'file' must not be null.");
		}
		// no else.

		URI uri = URI.createFileURI(file.getAbsolutePath());

		return parseConstraints(uri, model, addToModel);
	}

	/**
	 * <p>
	 * Parses a {@link List} of OCL {@link Constraint}s that are provided by a
	 * given {@link URL}.
	 * </p>
	 * 
	 * @param location
	 *            The {@link URL} providing the {@link File} or Stream to be
	 *            parsed.
	 * @param model
	 *            The {@link IModel} for which the {@link Constraint}s shall be
	 *            parsed.
	 * @param addToModel
	 *            Indicates whether or not the parsed {@link Constraint}s, its
	 *            defined fields and functions to the given {@link IModel}.
	 * @return The parsed {@link Constraint}s as a {@link List}.
	 * @throws ParseException
	 *             Thrown, if the parsing fails.
	 * @throws IllegalArgumentException
	 *             Thrown, if at least one parameter is invalid.
	 */
	public static List<Constraint> parseConstraints(URL location, IModel model,
			boolean addToModel) throws ParseException, IllegalArgumentException {

		if (location == null) {
			throw new IllegalArgumentException(
					"Parameter 'location' must not be null.");
		}
		// no else.

		return parseConstraints(URI.createFileURI(location.getFile()), model,
				addToModel);
	}

	public static List<Constraint> parseConstraints(URI uri, IModel model,
			boolean addToModel) throws ParseException, IllegalArgumentException {

		if (uri == null)
			throw new IllegalArgumentException(
					"Parameter 'uri' must not be null.");
		// no else.

		if (model == null)
			throw new IllegalArgumentException(
					"Parameter 'model' must not be null.");
		// no else.

		return Ocl22Parser.INSTANCE.doParse(model, uri, addToModel);

	}

	/**
	 * <p>
	 * Prepares a given {@link Collection} of postconditions for a given
	 * {@link Operation}.
	 * </p>
	 * 
	 * @param modelInstance
	 *            The {@link IModelInstance} the {@link IModelInstanceElement}
	 *            belongs to.
	 * @param modelInstanceElement
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameters
	 *            The values of the {@link Operation}'s {@link Parameter}s as a
	 *            {@link List} of {@link IModelInstanceElement} values.
	 * @param postConditions
	 *            The postconditions that shall be prepared. <b>Attention:</b>
	 *            if this {@link Collection} contains {@link Constraint}s of the
	 *            {@link ConstraintKind} that is different than
	 *            {@link ConstraintKind#POSTCONDITION} they will not be
	 *            prepared.
	 */
	public static void preparePostConditions(IModelInstance modelInstance,
			IModelInstanceElement modelInstanceElement, Operation operation,
			List<IModelInstanceElement> parameters,
			Collection<Constraint> postConditions) {

		if (modelInstance == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstance' must not be null.");
		}

		else if (modelInstanceElement == null) {
			throw new IllegalArgumentException(
					"Parameter 'modelInstanceElement' must not be null.");
		}

		else if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}

		else if (parameters == null) {
			throw new IllegalArgumentException(
					"Parameter 'parameters' must not be null.");
		}

		else if (postConditions == null) {
			throw new IllegalArgumentException(
					"Parameter 'postConditions' must not be null.");
		}
		// no else.

		IOclInterpreter interpreter;

		/* Create or use a cached interpreter. */
		if (cachedInterpreters.containsKey(modelInstance)) {
			interpreter = cachedInterpreters.get(modelInstance);
		}

		else {
			interpreter = OclInterpreterPlugin.createInterpreter(modelInstance);
			cachedInterpreters.put(modelInstance, interpreter);
		}

		interpreter.preparePostConditions(modelInstanceElement, operation,
				parameters.toArray(new IModelInstanceElement[0]),
				postConditions);
	}
}