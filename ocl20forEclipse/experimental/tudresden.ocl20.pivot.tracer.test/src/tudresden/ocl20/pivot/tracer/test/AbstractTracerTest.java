package tudresden.ocl20.pivot.tracer.test;

import java.io.File;
import java.util.List;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.metamodels.ecore.EcoreMetamodelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Abstract test class for Dresden OCL Tracer tests.
 * 
 * @author Lars Schütze
 * 
 */
public class AbstractTracerTest extends AbstractDresdenOclTest {

	/** The {@link IModel} under test. */
	protected static IModel modelUnderTest;

	/** The {@link Constraint}s under test. */
	protected static List<Constraint> constraintsUnderTest;

	/** Setup method that should be called by concrete test classes. */
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
		/*
		 * Load the model under test
		 */
		/*
		 * File modelFile =
		 * AbstractDresdenOclTest.getFile("/resources/testmodel.ecore",
		 * "tudresden.ocl20.pivot.tracer.test");
		 * org.junit.Assert.assertTrue(modelFile.exists()); modelUnderTest =
		 * Ocl2ForEclipseFacade.getModel(modelFile, EcoreMetamodelPlugin.ID);
		 * org.junit.Assert.assertNotNull(modelUnderTest);
		 */
		/*
		 * Activate the Tracer
		 */
		org.junit.Assert.assertNotNull(tudresden.ocl20.pivot.tracer.TracerPlugin
				.getInterpreterTraceListener());
	}

	/** TeardDown method that should be called by concrete test classes. */
	public static void tearDown() {

		modelUnderTest = null;
		constraintsUnderTest = null;
	}
}
