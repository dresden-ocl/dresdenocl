package tudresden.ocl20.pivot.tracer.test.constrainttests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.tracer.test.AbstractTracerTest;
import tudresden.ocl20.pivot.tracer.test.TracerTestPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

public class TestBooleanLiteralExp extends AbstractTracerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractTracerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractTracerTest.tearDown();
	}

	@Test
	public void testConstraintTracer01() throws Exception {

		/* local variables to be passed */
		String modelFilePath = "bin/tudresden/ocl20/pivot/tracer/test/package01/Model01.class";
		String modelInstanceFilePath = "bin/tudresden/ocl20/pivot/tracer/test/package01/Instance01.class";
		String constraintFilePath = "resources/constraints.ocl";
		String bundleId = TracerTestPlugin.PLUGIN_ID;
		String modelType = Ocl2ForEclipseFacade.JAVA_META_MODEL;
		String modelInstanceType = Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE;
		String[] objectTypeNames = {"Class01"};

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(modelFilePath, modelInstanceFilePath,
						constraintFilePath, bundleId, objectTypeNames, modelType,
						modelInstanceType);

		assertNotNull(tracedRoot);
		assertTrue(tracedRoot.getRootItems().size() >= 1);

		/* Write the object to a file */
		File file1, file2;

		file1 = saveTracerTree(tracedRoot, "rootfile1");
		file2 = saveTracerTree(tracedRoot, "rootfile2");

		assertTrue(compareXmlFiles(file1, file2));
	}
}