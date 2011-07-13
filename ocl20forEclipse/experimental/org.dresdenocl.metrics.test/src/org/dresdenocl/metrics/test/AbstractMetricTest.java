package org.dresdenocl.metrics.test;

import java.io.File;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
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

		AbstractDresdenOclTest.setUp();
		
		File modelFile = AbstractDresdenOclTest.getFile("model/UML.ecore",
				"org.dresdenocl.examples.uml");
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
