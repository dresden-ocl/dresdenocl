package org.dresdenocl.metrics.test;

import java.io.File;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * Abstract test class for metric tests.
 * 
 * @author Claas Wilke
 * 
 */
public class AbstractMetricTest extends AbstractDresdenOclTest {

	/** The {@link IModel} under test. */
	protected static IModel modelUnderTest;

	/** The {@link Constraint}s under test. */
	protected static List<Constraint> constraintsUnderTest;

	/** Setup method that should be called by concrete test classes. */
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();

		File modelFile = AbstractDresdenOclTest.getFile("resources/testmodel.ecore",
				"org.dresdenocl.metrics.test");
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
