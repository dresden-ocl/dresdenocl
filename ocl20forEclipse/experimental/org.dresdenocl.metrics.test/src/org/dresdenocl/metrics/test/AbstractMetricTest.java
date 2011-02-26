package org.dresdenocl.metrics.test;

import java.io.File;
import java.util.List;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.metamodels.ecore.internal.provider.EcoreModelProvider;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelProvider;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Abstract test class for metric tests.
 * 
 * @author Claas Wilke
 * 
 */
public class AbstractMetricTest {

	/** The {@link IModel} under test. */
	protected static IModel modelUnderTest;

	/** The {@link Constraint}s under test. */
	protected static List<Constraint> constraintsUnderTest;

	/** Setup method that should be called by concrete test classes. */
	protected static void setUp() throws Exception {

		/* Probably register the metamodel when running as standalone test. */
		if (ModelBusPlugin.getMetamodelRegistry().getMetamodel(
				EcoreMetamodelPlugin.ID) == null) {
			IMetamodel ecoreMM = new IMetamodel() {

				private EcoreModelProvider provider = new EcoreModelProvider();

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
			ModelBusPlugin.getMetamodelRegistry().addMetamodel(ecoreMM);
		}

		File modelFile = new File(
				"./../org.dresdenocl.examples.uml/model/UML.ecore");
		org.junit.Assert.assertTrue(modelFile.exists());

		modelUnderTest = Ocl2ForEclipseFacade.getModel(modelFile,
				EcoreMetamodelPlugin.ID);
		org.junit.Assert.assertNotNull(modelUnderTest);
	}

	/** TeardDown method that should be called by concrete test classes. */
	protected static void tearDown() {
		modelUnderTest = null;
		constraintsUnderTest = null;
	}
}
