package tudresden.ocl20.pivot.tracer.test.constrainttests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.interpreter.test.OclInterpreterTestPlugin;
import tudresden.ocl20.pivot.tracer.test.AbstractTracerTest;
import tudresden.ocl20.pivot.tracer.test.TracerTestPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

public class ConstraintTracerTest extends AbstractTracerTest {

	private static final String CONSTRAINT_DIRECTORY =
			"resources/constraints/constraintkind/body/";

	private static File regressionFile;

	private static Document temporaryDocument;

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractTracerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractTracerTest.tearDown();
	}

	@Test
	public void testBody01() throws Exception {

		/* local variables to be passed */
		String modelFilePath = MODEL2_PATH;
		String modelInstanceFilePath = INSTANCE4_PATH;
		String constraintFilePath = CONSTRAINT_DIRECTORY + "body01.ocl";
		String bundleId = OclInterpreterTestPlugin.PLUGIN_ID;
		String modelType = Ocl2ForEclipseFacade.JAVA_META_MODEL;
		String modelInstanceType = Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE;
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(modelFilePath, modelInstanceFilePath,
						constraintFilePath, bundleId, objectTypeNames, modelType,
						modelInstanceType);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile("resources/body01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);

		assertTrue(compareXmlFiles(regressionFile, temporaryDocument));
	}
}