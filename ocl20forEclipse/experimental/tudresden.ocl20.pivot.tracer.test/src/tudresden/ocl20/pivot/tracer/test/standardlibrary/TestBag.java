package tudresden.ocl20.pivot.tracer.test.standardlibrary;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.interpreter.test.OclInterpreterTestPlugin;
import tudresden.ocl20.pivot.tracer.test.AbstractTracerTest;
import tudresden.ocl20.pivot.tracer.test.TracerTestPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;


public class TestBag extends AbstractTracerTest {
	
	private static final String CONSTRAINT_DIRECTORY =
			"resources/constraints/standardlibrary/";
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		AbstractTracerTest.setUp();
	}
	
	@AfterClass
	public static void tearDown() {
		
		AbstractTracerTest.tearDown();
	}
	
	@Test
	public void testBag01() throws Exception {

		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "bag/equals01.ocl";
		String[] objectTypeNames = { "Class1" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL1_PATH, INSTANCE1_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/standardlibrary/bag01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/bag01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
}
