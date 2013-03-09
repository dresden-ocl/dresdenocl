package org.dresdenocl.debug.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

public abstract class AbstractDebuggerTest extends AbstractDresdenOclTest {

	protected static final String MODEL_PATH = "bin/resource/package01/TestModel.class";
	protected static final String MODEL_INSTANCE_PATH = "bin/resource/package01/TestModelInstance.class";
	protected static final String RESOURCE01_PATH = "resources/resource01.ocl";

	private static Map<String, IModel> modelCache;
	private static Map<String, IModelInstance> modelInstanceCache;
	private static Map<String, List<Constraint>> constraintCache;
	protected static IModel modelUnderTest;
	protected static IModelInstance modelInstanceUnderTest;

	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
		modelCache = new HashMap<String, IModel>();
		modelInstanceCache = new HashMap<String, IModelInstance>();
		constraintCache = new HashMap<String, List<Constraint>>();
	}

	public static void tearDown() {

		modelCache.clear();
		modelCache = null;

		modelInstanceCache.clear();
		modelInstanceCache = null;

		constraintCache.clear();
		constraintCache = null;
	}

	protected static IModel getModel(String modelPath)
			throws ModelAccessException {

		IModel result = modelCache.get(modelPath);
		if (result != null) {
			return result;
		}
		// no else

		File modelFile;
		try {
			modelFile = AbstractDebuggerTest.getFile(modelPath,
					DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		assertNotNull(modelFile);

		result = Ocl2ForEclipseFacade.getModel(modelFile,
				Ocl2ForEclipseFacade.JAVA_META_MODEL);
		// cache the model
		modelCache.put(modelPath, result);

		return result;
	}

	protected static IModelInstance getModelInstance(String modelInstancePath)
			throws ModelAccessException {

		IModelInstance result = modelInstanceCache.get(modelInstancePath);
		if (result != null) {
			return result;
		}
		// no else

		File modelInstanceFile;
		try {
			modelInstanceFile = AbstractDebuggerTest.getFile(modelInstancePath,
					DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		assertNotNull(modelInstanceFile);

		result = Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile,
				modelUnderTest, Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);
		// Cache the model instance
		modelInstanceCache.put(modelInstancePath, result);

		return result;
	}

	protected static Set<IModelInstanceObject> getModelInstanceObjects(
			String modelInstancePath, String... modelObjects)
			throws ModelAccessException {

		modelInstanceUnderTest = getModelInstance(modelInstancePath);
		assertNotNull(modelInstanceUnderTest);

		// check the parameter
		assertTrue(modelObjects != null && modelObjects.length >= 1);

		// get the types
		Type objectType = modelUnderTest.findType(Arrays.asList(modelObjects));
		assertNotNull(objectType);

		// find the objects
		Set<IModelInstanceObject> result = modelInstanceUnderTest
				.getAllInstances(objectType);
		assertNotNull(result);

		return result;
	}

	protected static List<Constraint> getConstraints(String modelPath,
			String resourcePath) throws ModelAccessException, ParseException {

		modelUnderTest = getModel(modelPath);
		assertNotNull(modelUnderTest);

		List<Constraint> result = constraintCache.get(resourcePath);
		if (result != null) {
			return result;
		}
		// no else

		File resourceFile;
		try {
			resourceFile = AbstractDebuggerTest.getFile(resourcePath,
					DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		assertNotNull(resourceFile);
		assertTrue(resourceFile.canRead());

		result = Ocl2ForEclipseFacade.parseConstraints(resourceFile,
				modelUnderTest, true);

		assertNotNull(result);
		assertTrue(result.size() >= 1);

		// cache the constraints
		constraintCache.put(resourcePath, result);

		return result;
	}

	protected static int findFreePort() {

		ServerSocket socket = null;
		try {
			socket = new ServerSocket(0);
			return socket.getLocalPort();
		} catch (IOException e) {
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return -1;
	}

	protected static OclDebugger generateDebugger() throws Exception {

		final OclDebugger debugger;
		final String[] modelObjects = { "TestClass" };
		final List<Constraint> constraints;
		final Set<IModelInstanceObject> imio;

		constraints = getConstraints(MODEL_PATH, RESOURCE01_PATH);

		imio = getModelInstanceObjects(MODEL_INSTANCE_PATH, modelObjects);

		debugger = new OclDebugger(modelInstanceUnderTest);
		debugger.setDebugMode(true);

		final int eventPort = findFreePort();
		debugger.setEventPort(eventPort);

		new Thread(new Runnable() {

			@Override
			public void run() {

				debugger.interpretConstraint(constraints.get(0), imio
						.iterator().next());
			}

		}).start();
		
		AbstractDebuggerTest.class.wait(1000);

		Socket socket = new Socket("localhost", eventPort);
		socket.close();
		return debugger;
	}
}
