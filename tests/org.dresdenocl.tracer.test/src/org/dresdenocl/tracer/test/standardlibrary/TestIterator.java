package org.dresdenocl.tracer.test.standardlibrary;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.interpreter.test.OclInterpreterTestPlugin;
import org.dresdenocl.tracer.test.AbstractTracerTest;
import org.dresdenocl.tracer.test.TracerTestPlugin;
import org.dresdenocl.tracer.tracermodel.TracerRoot;


public class TestIterator extends AbstractTracerTest {
	
	private static final String CONSTRAINT_DIRECTORY = "resources/constraints/standardlibrary/";  
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		AbstractTracerTest.setUp();
	}
	
	@AfterClass
	public static void tearDown() {
		
		AbstractTracerTest.tearDown();
	}
	
	@Test
	public void testAny01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/any01.ocl";
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
						"resources/standardlibrary/any01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	//@Test
	public void testClosure01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/closure01.ocl";
		String[] objectTypeNames = { "Class1" };
	
		TracerRoot tracedRoot;
		tracedRoot =
				getTraceFromInterpretation(MODEL1_PATH, INSTANCE2_PATH,
						constraintFilePath, OclInterpreterTestPlugin.PLUGIN_ID,
						objectTypeNames, Ocl2ForEclipseFacade.JAVA_META_MODEL,
						Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);
	
		/* Write the object to a file */
		regressionFile =
				AbstractTracerTest.getFile(
						"resources/standardlibrary/closure01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testCollect01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/collect01.ocl";
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
						"resources/standardlibrary/collect01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testCollect02() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/collect02.ocl";
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
						"resources/standardlibrary/collect02_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testCollectNested01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/collectNested01.ocl";
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
						"resources/standardlibrary/collectNested01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testExists01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/exists01.ocl";
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
						"resources/standardlibrary/exists01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testForAll01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/forAll01.ocl";
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
						"resources/standardlibrary/forAll01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testIsUnique01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/isUnique01.ocl";
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
						"resources/standardlibrary/isUnique01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testIterate01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/iterate01.ocl";
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
						"resources/standardlibrary/iterate01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testOne02() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/one02.ocl";
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
						"resources/standardlibrary/one02_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testReject01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/reject01.ocl";
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
						"resources/standardlibrary/reject01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testSelect01() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/select01.ocl";
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
						"resources/standardlibrary/select01_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
	
	@Test
	public void testSortedBy02() throws Exception {
		
		/* local variables to be passed */
		String constraintFilePath = CONSTRAINT_DIRECTORY + "iterator/sortedBy02.ocl";
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
						"resources/standardlibrary/sortedBy02_regression.xml",
						TracerTestPlugin.PLUGIN_ID);
	
		temporaryDocument = serializeTracerRoot(tracedRoot);

		/*
		File f = new File("resources/standardlibrary/sortedBy02_regression.xml");
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		DOMSource source = new DOMSource(temporaryDocument);
		StreamResult result =	new StreamResult(f);
		tf.transform(source, result);
		*/
	
		assertTrue("XML documents are not equal",
				compareXmlFiles(regressionFile, temporaryDocument));
	}
}
