package tudresden.ocl20.pivot.tracer.test.constraintkinds;

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

public class ConstraintKindTracerTest extends AbstractTracerTest {

	private static final String CONSTRAINT_DIRECTORY =
			"resources/constraints/constraintkind/";

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
		String constraintFilePath = CONSTRAINT_DIRECTORY + "body/body01.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/body01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);
		/*
		File f = new File("resources/constraintkind/body01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	@Test
	public void testDef01() throws Exception {

		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "def/def01.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/def01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
		
		temporaryDocument = serializeTracerRoot(tracedRoot);
		/*
		File f = new File("resources/constraintkind/def01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	@Test
	public void testDerive01() throws Exception {

		/* local variables to be passed */
		String constraintFilePath =
				CONSTRAINT_DIRECTORY + "derive/derive01.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/derive01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);
		
		/*
		File f = new File("resources/constraintkind/derive01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		 */

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	@Test
	public void testDerive02() throws Exception {

		/* local variables to be passed */
		String constraintFilePath =
				CONSTRAINT_DIRECTORY + "derive/derive02.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/derive02_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);
		
		/*
		File f = new File("resources/constraintkind/derive02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		 */

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	@Test
	public void testInit01() throws Exception {

		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "initial/init01.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/init01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);
		/*
		File f = new File("resources/constraintkind/init01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		 */

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	@Test
	public void testInit02_01() throws Exception {

		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "initial/init02.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE4_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/init02_01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);
		/*
		File f = new File("resources/constraintkind/init02_01_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		 */

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}

	//@Test
	public void testInit02_02() throws Exception {

		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "initial/init02.ocl";
		String[] objectTypeNames = { "Class2" };

		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL2_PATH, INSTANCE5_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/constraintkind/init02_02_regression.xml",
						TracerTestPlugin.PLUGIN_ID);

		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/constraintkind/init02_02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/

		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
}