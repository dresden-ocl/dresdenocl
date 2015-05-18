package org.dresdenocl.modelinstancetype.xml.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.model.IModel;
import org.dresdenocl.modelinstance.IModelInstance;
import org.junit.Test;

/**
 * <p>
 * Contains test cases for Bug #66 - XML Namespaces are not evaluated on the
 * node name.
 * </p>
 * 
 * @author Lars Schütze
 *
 */
public class TestBugFix04 extends AbstractXmlModelInstanceTest {

	/**
	 * <p>
	 * Tests to load the model instance.
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadModel() throws Exception {

		File modelFile;
		modelFile = TestBugFix04.getFile("model/bugfix04.xsd");

		IModel testModel = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.XSD_META_MODEL);
		assertNotNull(testModel);

		File modelInstanceFile;
		modelInstanceFile = TestBugFix04.getFile("modelinstance/bugfix04.xml");

		IModelInstance testModelInstance = Ocl2ForEclipseFacade
				.getModelInstance(modelInstanceFile, testModel,
						Ocl2ForEclipseFacade.XML_MODEL_INSTANCE_TYPE);

		assertNotNull(testModelInstance);
	}
}
