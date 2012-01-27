package tudresden.ocl20.pivot.tracer.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;

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
	
	/** The {@link DocumentBuilder} for the XML files */
	private static DocumentBuilder db;

	/** Setup method that should be called by concrete test classes. */
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
		/*
		 * Activate the Tracer
		 */
		org.junit.Assert.assertNotNull(tudresden.ocl20.pivot.tracer.TracerPlugin
				.getInterpreterTraceListener());
		
		/* Set up the builder for the XML documents */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setCoalescing(true);
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		
		db = dbf.newDocumentBuilder();
	}

	/** TeardDown method that should be called by concrete test classes. */
	public static void tearDown() {

		modelUnderTest = null;
		constraintsUnderTest = null;
	}

	protected static File saveTracerTree(TracerRoot root, String filename)
		throws ParserConfigurationException, TransformerException {
		
		/* Create the file where the tree is stored in memory */
		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();
		
		/* Process the root into XML */
		Element rootElement = document.createElement("TracerItems");
		document.appendChild(rootElement);
		
		for(TracerItem rootItem : root.getRootItems()) {
			processAllChilds(rootElement, rootItem, document);
		}
		
		/* Transform the in-memory file to the file system */
		File file = new File("resources/" + filename + ".xml");
		TransformerFactory.newInstance().newTransformer().transform(
				new DOMSource(document), new StreamResult(file));
		
		return file;
	}
	
	private static void processAllChilds(Element rootElement, TracerItem item, Document document) {
		
		Element e = document.createElement("TracerItem");
		rootElement.appendChild(e);
		
		e.setAttribute("expression", item.getExpression().toString());
		e.setAttribute("result", item.getResult().toString());
		
		/* Call this function for all children recursively */
		for(TracerItem child : item.getChildren()) {
			processAllChilds(e, child, document);
		}
	}
	
	protected static boolean compareXmlFiles(File file1, File file2)
		throws IOException, SAXException {
		
		Document doc1 = db.parse(file1);
		doc1.normalizeDocument();
		
		Document doc2 = db.parse(file2);
		doc2.normalizeDocument();
		
		return doc1.isEqualNode(doc2);
	}
}
