/*
 * Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de) This file is part of
 * Dresden OCL. Dresden OCL is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.testsuite._abstract;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.xsd.XSDPackage;
import org.junit.BeforeClass;

import tudresden.ocl20.logging.LoggingPlugin;
import tudresden.ocl20.pivot.essentialocl.types.impl.TypesPackageImpl;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.resource.ocl.OclReferenceResolveHelperProvider;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor.OclReferenceResolveHelper;
import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.ecore.internal.provider.EcoreModelProvider;
import tudresden.ocl20.pivot.metamodels.java.JavaMetaModelPlugin;
import tudresden.ocl20.pivot.metamodels.java.internal.provider.JavaModelProvider;
import tudresden.ocl20.pivot.metamodels.uml2.UML2MetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.uml2.internal.provider.UML2ModelProvider;
import tudresden.ocl20.pivot.metamodels.xsd.XSDMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.xsd.internal.provider.XSDModelProvider;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceTypeRegistry;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.provider.JavaModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.provider.XmlModelInstanceProvider;
import tudresden.ocl20.pivot.tools.template.ITemplateEngineRegistry;
import tudresden.ocl20.pivot.tools.template.ITemplateGroupRegistry;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tudresden.ocl20.pivot.tools.template.impl.StandaloneTemplateEngineRegistry;
import tudresden.ocl20.pivot.tools.template.impl.StandaloneTemplateGroupRegistry;
import tudresden.ocl20.pivot.tools.template.internal.TemplateGroup;
import tudresden.ocl20.pivot.tools.template.sql.SQLTemplate;
import tudresden.ocl20.pivot.tools.template.stringtemplate.StringTemplateEngine;
import tudresden.ocl20.pivot.tools.transformation.ITransformationRegistry;
import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl.Cwm2DdlImpl;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl.Pivot2CwmImpl;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl.Pivot2Ddl;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl.Pivot2DdlAndMappedModel;
import tudresden.ocl20.pivot.tools.transformation.pivot2sql.impl.Pivot2MappedModelImpl;

/**
 * Abstract class for Dresden OCL test cases. Contains static setUp and tearDown
 * methods that initialize Dresden OCL when the Eclipse platform is not running
 * (headless).
 * 
 * @author Claas Wilke
 */
public class AbstractDresdenOclTest {

	/** Whether or not logging has been enabled yet. */
	private static boolean loggingEnabled = false;

	/** Whether or not was initialized yet. */
	private static boolean isInitialized = false;

	@BeforeClass
	public static void setUp() throws IOException {

		/* Initializes Dresden OCL when tests were started headless. */
		if (!Platform.isRunning() && !isInitialized) {
			initializeLogging();
			registerEmfResourceFactories();
			registerMetamodels();
			registerModelInstanceTypes();

			OclReferenceResolveHelperProvider
					.setOclReferenceResolveHelper(new OclReferenceResolveHelper());

			try {
				initializeTemplateEngine();
			} catch (TemplateException e) {
				new RuntimeException(e.getMessage(), e);
			}

			isInitialized = true;
		}
		// no else.
	}

	/** Initializes logging. */
	protected static void initializeLogging() throws IOException {
		if (!loggingEnabled) {
			File loggerPropertiesUrl = getFile("log4j.properties",
					"org.dresdenocl.testsuite.abstract");
			new LoggingPlugin(loggerPropertiesUrl.toURI().toURL());
			loggingEnabled = true;
		}
		// no else.
	}

	/**
	 * Intitializes the template engine required for code generation.
	 * 
	 * @throws TemplateException
	 */
	protected static void initializeTemplateEngine() throws TemplateException {

		TemplatePlugin.getDefault();

		final StringTemplateEngine stringTemplateEngine = new StringTemplateEngine();

		ITemplateEngineRegistry templateEngineRegistry = new StandaloneTemplateEngineRegistry();
		templateEngineRegistry.addTemplateEngine(stringTemplateEngine);

		ITemplateGroupRegistry templateGroupRegistry = new StandaloneTemplateGroupRegistry();
		templateGroupRegistry.addTemplateGroup(new TemplateGroup(
				stringTemplateEngine.getDisplayName(), null,
				stringTemplateEngine));

		TemplatePlugin.setTempateEngineRegistry(templateEngineRegistry);
		TemplatePlugin.setTempateGroupRegistry(templateGroupRegistry);

		// SQLTemplate.loadSQLTemplates();

		// ITransformationRegistry transformationRegistry = TransformationPlugin
		// .getTransformationRegistry();
		// transformationRegistry.addTransformation(new Pivot2MappedModelImpl(
		// null, null));
		// transformationRegistry.addTransformation(new Pivot2CwmImpl(null,
		// null));
		// transformationRegistry.addTransformation(new Pivot2Ddl(null, null));
		// transformationRegistry.addTransformation(new Cwm2DdlImpl(null,
		// null));
		// transformationRegistry.addTransformation(new Pivot2DdlAndMappedModel(
		// null, null));
	}

	/**
	 * Loads resources required for the UML meta model.
	 * 
	 * @throws IOException
	 */
	protected static void loadUmlResources() throws IOException {

		File umlResources = getFile(
				"lib/org.eclipse.uml2.uml.resources_3.1.1.v201008191505.jar",
				"org.dresdenocl.testsuite.abstract");
		if (umlResources == null)
			throw new IllegalArgumentException(
					"Cannot laod an UML model with umlResources == null; umlResources has to point to the jar file of the plugin org.eclipse.uml2.uml.resources.");

		URI pluginURI = URI.createURI("jar:file:"
				+ umlResources.getAbsolutePath() + "!/");
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP),
				pluginURI.appendSegment("libraries").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.METAMODELS_PATHMAP),
				pluginURI.appendSegment("metamodels").appendSegment(""));
		URIConverter.URI_MAP.put(URI.createURI(UMLResource.PROFILES_PATHMAP),
				pluginURI.appendSegment("profiles").appendSegment(""));

	}

	/**
	 * Registers EMF resource factories at EMF.
	 * 
	 * @throws IOException
	 */
	protected static void registerEmfResourceFactories() throws IOException {

		/* Probably register the Ecore resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				EcorePackage.eNS_PREFIX) == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					EcorePackage.eNS_PREFIX, new EcoreResourceFactoryImpl() {
						public Resource createResource(URI uri) {
							XMIResource xmiResource = new XMIResourceImpl(uri);
							return xmiResource;
						}
					});
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(EcorePackage.eNS_PREFIX,
					EcorePackage.eINSTANCE);
		}
		// no else.

		/* Probably register the UML resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				UMLPackage.eNS_PREFIX) == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					UMLPackage.eNS_PREFIX, UMLResource.Factory.INSTANCE);
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(UMLPackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(UMLPackage.eNS_PREFIX,
					UMLPackage.eINSTANCE);
			loadUmlResources();
		}
		// no else.

		/* Probably register the XSD resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				XSDPackage.eNS_PREFIX) == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					XSDPackage.eNS_PREFIX, new XMIResourceFactoryImpl() {
						public Resource createResource(URI uri) {
							XMIResource xmiResource = new XMIResourceImpl(uri);
							return xmiResource;
						}
					});
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(XSDPackage.eNS_PREFIX,
					XSDPackage.eINSTANCE);
			loadUmlResources();
		}
		// no else.

		/* Probably register the types resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				TypesPackageImpl.eNS_PREFIX) == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					TypesPackageImpl.eNS_PREFIX, new XMIResourceFactoryImpl() {
						public Resource createResource(URI uri) {
							XMIResource xmiResource = new XMIResourceImpl(uri);
							return xmiResource;
						}
					});
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(TypesPackageImpl.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(TypesPackageImpl.eNS_PREFIX,
					TypesPackageImpl.eINSTANCE);
		}
		// no else.

		/* Probably register the OCL resource for EMF. */
		if (Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(
				OclPackage.eNS_PREFIX) == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					OclPackage.eNS_PREFIX, new XMIResourceFactoryImpl() {
						public Resource createResource(URI uri) {
							XMIResource xmiResource = new XMIResourceImpl(uri);
							return xmiResource;
						}
					});
		}
		// no else.

		if (EPackage.Registry.INSTANCE.getEPackage(OclPackage.eNS_URI) == null) {
			EPackage.Registry.INSTANCE.put(OclPackage.eNS_PREFIX,
					OclPackage.eINSTANCE);
		}
		// no else.
	}

	/** Registers {@link IMetamodel}s at the {@link IMetamodelRegistry}. */
	protected static void registerMetamodels() {

		/* Probably register the Ecore metamodel. */
		IMetamodel ecoreMetamodel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(EcoreMetamodelPlugin.ID);
		if (ecoreMetamodel == null) {
			ecoreMetamodel = new IMetamodel() {

				IModelProvider provider = new EcoreModelProvider();

				public String getName() {
					return EcoreMetamodelPlugin.ID;
				}

				public IModelProvider getModelProvider() {
					return provider;
				}

				public String getId() {
					return EcoreMetamodelPlugin.ID;
				}
			};
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(ecoreMetamodel);
		}

		/* Probably register the Java metamodel. */
		IMetamodel javaMetamodel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(JavaMetaModelPlugin.ID);
		if (javaMetamodel == null) {
			javaMetamodel = new IMetamodel() {

				IModelProvider provider = new JavaModelProvider();

				public String getName() {
					return JavaMetaModelPlugin.ID;
				}

				public IModelProvider getModelProvider() {
					return provider;
				}

				public String getId() {
					return JavaMetaModelPlugin.ID;
				}
			};
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(javaMetamodel);
		}

		/* Probably register the UML metamodel. */
		IMetamodel umlMetamodel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(UML2MetamodelPlugin.ID);
		if (umlMetamodel == null) {
			umlMetamodel = new IMetamodel() {

				IModelProvider provider = new UML2ModelProvider();

				public String getName() {
					return UML2MetamodelPlugin.ID;
				}

				public IModelProvider getModelProvider() {
					return provider;
				}

				public String getId() {
					return UML2MetamodelPlugin.ID;
				}
			};
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(umlMetamodel);
		}

		/* Probably register the XSD metamodel. */
		IMetamodel xsdMetamodel = ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(XSDMetamodelPlugin.ID);
		if (xsdMetamodel == null) {
			xsdMetamodel = new IMetamodel() {

				IModelProvider provider = new XSDModelProvider();

				public String getName() {
					return XSDMetamodelPlugin.ID;
				}

				public IModelProvider getModelProvider() {
					return provider;
				}

				public String getId() {
					return XSDMetamodelPlugin.ID;
				}
			};
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(xsdMetamodel);
		}
	}

	/**
	 * Registers {@link IModelInstanceType}s at the
	 * {@link IModelInstanceTypeRegistry}.
	 */
	protected static void registerModelInstanceTypes() {

		/* Probably register the Ecore model instance. */
		IModelInstanceType ecoreModelInstanceType = ModelBusPlugin
				.getModelInstanceTypeRegistry().getModelInstanceType(
						EcoreModelInstanceTypePlugin.PLUGIN_ID);
		if (ecoreModelInstanceType == null) {
			ecoreModelInstanceType = new IModelInstanceType() {

				private IModelInstanceProvider provider = new EcoreModelInstanceProvider();

				public String getName() {
					return EcoreModelInstanceTypePlugin.PLUGIN_ID;
				}

				public IModelInstanceProvider getModelInstanceProvider() {
					return provider;
				}

				public String getId() {
					return EcoreModelInstanceTypePlugin.PLUGIN_ID;
				}
			};
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					ecoreModelInstanceType);
		}

		/* Probably register the Java model instance. */
		IModelInstanceType javaModelInstanceType = ModelBusPlugin
				.getModelInstanceTypeRegistry().getModelInstanceType(
						JavaModelInstanceTypePlugin.PLUGIN_ID);
		if (javaModelInstanceType == null) {
			javaModelInstanceType = new IModelInstanceType() {

				private IModelInstanceProvider provider = new JavaModelInstanceProvider();

				public String getName() {
					return JavaModelInstanceTypePlugin.PLUGIN_ID;
				}

				public IModelInstanceProvider getModelInstanceProvider() {
					return provider;
				}

				public String getId() {
					return JavaModelInstanceTypePlugin.PLUGIN_ID;
				}
			};
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					javaModelInstanceType);
		}

		/* Probably register the XML model instance. */
		IModelInstanceType xmlModelInstanceType = ModelBusPlugin
				.getModelInstanceTypeRegistry().getModelInstanceType(
						XmlModelInstanceTypePlugin.PLUGIN_ID);
		if (xmlModelInstanceType == null) {
			xmlModelInstanceType = new IModelInstanceType() {

				private IModelInstanceProvider provider = new XmlModelInstanceProvider();

				public String getName() {
					return XmlModelInstanceTypePlugin.PLUGIN_ID;
				}

				public IModelInstanceProvider getModelInstanceProvider() {
					return provider;
				}

				public String getId() {
					return XmlModelInstanceTypePlugin.PLUGIN_ID;
				}
			};
			ModelBusPlugin.getModelInstanceTypeRegistry().addModelInstanceType(
					xmlModelInstanceType);
		}
	}

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @param bundleId
	 *            The ID of the Bundle containing the resource.
	 * @return The found {@link File} object.
	 * @throws IOException
	 * @throws Exception
	 *             Thrown, if the opening fails.
	 */
	public static File getFile(String path, String bundleId) throws IOException {

		URL fileLocation;
		File file;

		if (Platform.isRunning()) {
			fileLocation = Platform.getBundle(bundleId).getResource(path);
			fileLocation = FileLocator.resolve(fileLocation);
			file = new File(fileLocation.getFile());
		}

		else {
			File currentLocation = new File("./");
			File bundleFile = null;

			while (currentLocation != null && currentLocation.exists()
					&& currentLocation.isDirectory()) {
				bundleFile = new File(currentLocation.getAbsolutePath()
						+ File.separator + bundleId);

				if (bundleFile.exists() && bundleFile.isDirectory())
					break;
				else {
					bundleFile = null;
					currentLocation = new File(
							currentLocation.getAbsolutePath() + File.separator
									+ ".." + File.separator);
				}
			}

			if (bundleFile != null)
				file = new File(bundleFile + File.separator + path);

			else
				throw new RuntimeException("Bundle or directory '" + bundleId
						+ "' was not found.");
		}

		assertTrue(file.exists());

		return file;
	}
}
