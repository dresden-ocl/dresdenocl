package tudresden.ocl20.pivot.tracer.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.custommonkey.xmlunit.Diff;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tracer.TracerPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

/**
 * Abstract test class for Dresden OCL Tracer tests.
 * 
 * @author Lars Schuetze
 * 
 */
public class AbstractTracerTest extends AbstractDresdenOclTest {

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE1_PATH = "bin/package1/Instance1.class";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE2_PATH = "bin/package1/Instance2.class";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE3_PATH = "bin/package1/Instance3.class";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE4_PATH = "bin/package1/Instance4.class";

	/** The name of a test {@link IModelInstance} for this test suite. */
	protected static final String INSTANCE5_PATH = "bin/package1/Instance5.class";

	/** The name of a test {@link IModel} for this test suite. */
	protected static final String MODEL1_PATH = "bin/package1/Model1.class";

	/** The name of a test {@link IModel} for this test suite. */
	protected static final String MODEL2_PATH = "bin/package1/Model2.class";

	/** The {@link IModel} under test. */
	private static IModel modelUnderTest;

	/** This cache maps the path of a model instance file to a model instance. */
	private static Map<String, IModelInstance> modelInstanceCache;

	/** This cache maps the path of a model file to a model. */
	private static Map<String, IModel> modelCache;

	/** This cache maps the path of a constraint file to a list of constraints. */
	private static Map<String, List<Constraint>> constraintCache;

	/** This is the {@link DocumentBuilder} for the XMLs processed. */
	private static DocumentBuilder db;
	
	/** The {@link File} where the regression XMLs are loaded into. */
	protected static File regressionFile;

	/** The {@link Document} where the temporary XMLs are stored in. */
	protected static Document temporaryDocument;

	/** Setup method that should be called by concrete test classes. */
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
		/* Activate the Tracer */
		org.junit.Assert.assertNotNull(tudresden.ocl20.pivot.tracer.TracerPlugin
				.getInterpreterTraceListener());

		/* Set up all caches */
		modelInstanceCache = new WeakHashMap<String, IModelInstance>();
		modelCache = new WeakHashMap<String, IModel>();
		constraintCache = new WeakHashMap<String, List<Constraint>>();
		db = getDocumentBuilder();
	}

	protected static DocumentBuilder getDocumentBuilder()
			throws ParserConfigurationException {

		if (db == null) {
			/* Set up the builder for the XML documents */
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			dbf.setCoalescing(true);
			dbf.setIgnoringComments(true);
			dbf.setIgnoringElementContentWhitespace(true);

			db = dbf.newDocumentBuilder();
		}
		return db;
	}

	/** TeardDown method that should be called by concrete test classes. */
	public static void tearDown() {

		/* nullify all class variables */
		modelUnderTest = null;
		db = null;
		regressionFile = null;
		temporaryDocument = null;

		/* nullify all caches */
		modelInstanceCache = null;
		modelCache = null;
		constraintCache = null;
	}

	/**
	 * <p>
	 * Saves the given {@link TracerRoot} into a file with the given
	 * filename
	 * </p>
	 * 
	 * @param root
	 *          the {@link TracerRoot} to be serialized
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws IOException
	 */
	protected static Document serializeTracerRoot(TracerRoot root)
			throws ParserConfigurationException, TransformerException {

		Document document =
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		/* Process the root into XML */
		Element rootElement = document.createElement("TracerItems");
		document.appendChild(rootElement);

		for (TracerItem rootItem : root.getRootItems()) {
			processAllChilds(rootElement, rootItem, document);
		}

		return document;
	}

	/**
	 * <p>
	 * Process all children of a given TracerItem to be serialized into a XML
	 * file.
	 * </p>
	 * 
	 * @param rootElement
	 *          the root Element of the XML document
	 * @param item
	 *          the {@link TracerItem} to be processed.
	 * @param document
	 *          the {@link Document} to be written.
	 */
	private static void processAllChilds(Element rootElement, TracerItem item,
			Document document) {

		Element e = document.createElement("TracerItem");
		rootElement.appendChild(e);

		e.setAttribute("expression", item.getExpression().toString());
		e.setAttribute("result", item.getResult().toString());

		/* Call this function for all children recursively */
		for (TracerItem child : item.getChildren()) {
			processAllChilds(e, child, document);
		}
	}

	/**
	 * <p>
	 * Compares two given XML files
	 * </p>
	 * 
	 * @param regressionFile
	 *          the regression XML file
	 * @param testDocument
	 *          the {@link Document} to be compared
	 * @return whether the regression file and the test document are the same
	 * @throws SAXException
	 * @throws IOException
	 */
	protected static boolean compareXmlFiles(File regressionFile,
			Document testDocument) throws SAXException, IOException {

		org.junit.Assert.assertTrue(regressionFile != null);
		org.junit.Assert.assertTrue(testDocument != null);
		org.junit.Assert.assertTrue(regressionFile.exists());

		Document regressionDocument;
		regressionDocument = db.parse(regressionFile);

		Diff diff = new Diff(regressionDocument, testDocument);
		
		//System.out.println(diff.toString());
		
		return diff.similar();
	}

	/**
	 * <p>
	 * Load and interpret the constraints on the model instances of the model and
	 * returns the trace.
	 * </p>
	 * 
	 * @param modelFilePath
	 *          the path to the model file.
	 * @param modelInstanceFilePath
	 *          the path to the model instance file.
	 * @param constraintFilePath
	 *          the path to the constraint file.
	 * @param objectTypeNames
	 *          the names of the object types in the model instance.
	 * @param modelType
	 *          the type of the model
	 * @param modelInstanceType
	 *          the type of the model instance
	 * @return the resulting trace
	 * @throws Exception
	 */
	protected static TracerRoot getTraceFromInterpretation(String modelFilePath,
			String modelInstanceFilePath, String constraintFilePath, String bundleId,
			String[] objectTypeNames, String modelType, String modelInstanceType)
			throws Exception {

		assertNotNull(modelFilePath);
		assertNotNull(modelInstanceFilePath);
		assertNotNull(constraintFilePath);
		assertNotNull(bundleId);
		assertTrue(objectTypeNames.length >= 1);
		assertNotNull(modelType);
		assertNotNull(modelInstanceType);
		
		/* Clear the tracer */
		TracerPlugin.getInterpreterTraceListener().interpretationCleared();

		modelUnderTest = getModel(modelFilePath, modelType, bundleId);
		assertNotNull(modelUnderTest);

		/* Find the type for the IMIObjects to test. */
		Type objectType;
		objectType = modelUnderTest.findType(Arrays.asList(objectTypeNames));
		assertNotNull(objectType);

		/* Get all constraints */
		List<Constraint> constraints;
		constraints = getConstraints(constraintFilePath, bundleId);

		/* Load or get the instance. */
		IModelInstance modelInstance;
		modelInstance =
				getModelInstance(modelInstanceFilePath, modelInstanceType, bundleId);

		/* Find the IMIObject(s) to test. */
		Set<IModelInstanceObject> imiObjects;
		imiObjects = modelInstance.getAllInstances(objectType);
		assertNotNull(imiObjects);

		/* Send the constraints to the interpreter */
		for (IModelInstanceObject imiObject : imiObjects) {
			Ocl2ForEclipseFacade.interpretConstraints(constraints, modelInstance,
					imiObject);
		}
		// end for.

		modelUnderTest.removeConstraints(constraints);
		
		TracerRoot tracedRoot;
		tracedRoot = TracerPlugin.getInterpreterTraceListener().getCurrentRoot();

		assertNotNull(tracedRoot);
		assertTrue(tracedRoot.getRootItems().size() >= 1);

		return tracedRoot;
	}

	private static IModel getModel(String modelFilePath, String modelType,
			String bundleId) throws ModelAccessException {

		IModel model = modelCache.get(modelFilePath);

		/* check if the model has been cached before */
		if (model != null) {
			return model;
		}

		File modelFile;
		/* Load the model */
		try {
			modelFile = AbstractTracerTest.getFile(modelFilePath, bundleId);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		model = Ocl2ForEclipseFacade.getModel(modelFile, modelType);
		/* cache the model */
		modelCache.put(modelFilePath, model);
		return model;
	}

	private static IModelInstance getModelInstance(String modelInstanceFilePath,
			String modelInstanceType, String bundleId)
			throws IllegalArgumentException, ModelAccessException {

		IModelInstance modelInstance =
				modelInstanceCache.get(modelInstanceFilePath);

		/* check if the model has been cached before */
		if (modelInstance != null) {
			return modelInstance;
		}

		File modelInstanceFile;
		try {
			modelInstanceFile =
					AbstractTracerTest.getFile(modelInstanceFilePath, bundleId);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		modelInstance =
				Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile,
						modelUnderTest, modelInstanceType);

		assertNotNull(modelInstance);

		/* Cache the model instance */
		modelInstanceCache.put(modelInstanceFilePath, modelInstance);
		return modelInstance;
	}

	private static List<Constraint> getConstraints(String constraintFilePath,
			String bundleId) throws ModelAccessException, IllegalArgumentException,
			ParseException {

		List<Constraint> constraints = constraintCache.get(constraintFilePath);

		/* check if the constraints has been cached before */
		if (constraints != null) {
			return constraints;
		}

		File constraintFile;
		try {
			constraintFile = AbstractTracerTest.getFile(constraintFilePath, bundleId);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		assertNotNull(constraintFile);
		assertTrue(constraintFile.canRead());

		constraints =
				Ocl2ForEclipseFacade.parseConstraints(constraintFile, modelUnderTest,
						true);

		assertNotNull(constraints);
		assertTrue(constraints.size() >= 1);

		/* cache the constraints */
		constraintCache.put(constraintFilePath, constraints);
		return constraints;
	}
}
