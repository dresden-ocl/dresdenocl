package org.dresdenocl.debug.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.facade.Ocl2ForEclipseFacade;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractDebuggerTest extends AbstractDresdenOclTest {

	/** Declares possible events to be happened during debugging. */
	protected enum DebugEvent {
		CONSTRAINT_INTERPRETED, STARTED, SUSPENDED, RESUMED, TERMINATED
	}

	/** Declared possible debug steps to be executed by the debugger. */
	protected enum DebugStep {
		RESUME, TERMINATE, STEP_INTO, STEP_OVER, STEP_RETURN
	}

	protected static final String MODEL_PATH =
			"target/classes/resource/package01/TestModel.class";
	protected static final String MODEL_INSTANCE_PATH =
			"target/classes/resource/package01/TestModelInstance.class";
	protected static final String RESOURCE01_PATH = "resources/resource01.ocl";

	/** The last lines received as an event from the {@link OclDebugger}. */
	protected static volatile List<String> lastReceivedLines =
			new LinkedList<String>();

	protected static IModel modelUnderTest;
	protected static IModelInstance modelInstanceUnderTest;

	/** Socket the {@link OclDebugger} is sending events to. */
	protected static Socket socket;
	/**
	 * Thread used to listen for events on the {@link OclDebugger}'s
	 * {@link Socket}.
	 */
	protected static SocketListener socketListener;

	protected static OclDebugger debugger;

	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
	}

	@Before
	public void before() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		assertEquals(null, ModelBusPlugin.getModelRegistry().getActiveModel());
	}

	@After
	public void after() {

		debugStepAndWaitFor(DebugStep.TERMINATE, DebugEvent.TERMINATED, debugger);
		try {
			debugger.shutdown();
		} catch (Exception e) {
			// drop it
		}

		assertTrue(ModelBusPlugin.getModelInstanceRegistry().removeModelInstance(
				modelInstanceUnderTest));
		assertTrue(ModelBusPlugin.getModelRegistry().removeModel(modelUnderTest));

		modelUnderTest = null;
		modelInstanceUnderTest = null;

		socketListener.terminate = true;

		debugger = null;

		lastReceivedLines = new LinkedList<String>();

		if (null != socket) {
			try {
				socket.close();
			} catch (IOException e) {
				/* Not important. */
			}
		}
		// no else.

	}

	protected static IModel getModel(String modelPath)
			throws ModelAccessException {

		IModel result;
		File modelFile;
		try {
			modelFile =
					AbstractDebuggerTest.getFile(modelPath, DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		assertNotNull(modelFile);

		result =
				Ocl2ForEclipseFacade.getModel(modelFile,
						Ocl2ForEclipseFacade.JAVA_META_MODEL);

		return result;
	}

	protected static IModelInstance getModelInstance(String modelInstancePath)
			throws ModelAccessException {

		IModelInstance result;
		File modelInstanceFile;
		try {
			modelInstanceFile =
					AbstractDebuggerTest.getFile(modelInstancePath,
							DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}
		assertNotNull(modelInstanceFile);

		result =
				Ocl2ForEclipseFacade.getModelInstance(modelInstanceFile,
						modelUnderTest, Ocl2ForEclipseFacade.JAVA_MODEL_INSTANCE_TYPE);

		return result;
	}

	protected static Set<IModelInstanceObject> getModelInstanceObjects(
			String modelInstancePath, String... modelObjects)
			throws ModelAccessException {

		modelInstanceUnderTest = getModelInstance(modelInstancePath);
		assertNotNull(modelInstanceUnderTest);

		ModelBusPlugin.getModelInstanceRegistry().addModelInstance(
				modelInstanceUnderTest);
		ModelBusPlugin.getModelInstanceRegistry().setActiveModelInstance(
				modelUnderTest, modelInstanceUnderTest);

		// check the parameter
		assertTrue(modelObjects != null && modelObjects.length >= 1);

		// get the types
		Type objectType = modelUnderTest.findType(Arrays.asList(modelObjects));
		assertNotNull("cannot find type", objectType);

		// find the objects
		Set<IModelInstanceObject> result =
				modelInstanceUnderTest.getAllInstances(objectType);
		assertNotNull("could not get all instances", result);

		return result;
	}

	protected static List<Constraint> getConstraints(String modelPath,
			String resourcePath) throws ModelAccessException, ParseException {

		modelUnderTest = getModel(modelPath);
		assertNotNull("could not get model", modelUnderTest);

		ModelBusPlugin.getModelRegistry().addModel(modelUnderTest);
		ModelBusPlugin.getModelRegistry().setActiveModel(modelUnderTest);

		List<Constraint> result;
		File resourceFile;
		try {
			resourceFile =
					AbstractDebuggerTest.getFile(resourcePath, DebugTestPlugin.PLUGIN_ID);
		} catch (IOException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		assertNotNull("resource file is null", resourceFile);
		assertTrue("Cannot read the resource file", resourceFile.canRead());

		result =
				Ocl2ForEclipseFacade.parseConstraints(resourceFile, modelUnderTest,
						true);

		assertNotNull("parse result is null", result);
		assertTrue("parse result is empty", result.size() >= 1);

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

	/**
	 * Helper method asserting that the {@link OclDebugger} is at the right line.
	 * 
	 * @param currentLine
	 *          The line to be asserted.
	 * @param debugger
	 *          The {@link OclDebugger}.
	 */
	protected void assertCurrentLine(int currentLine, OclDebugger debugger) {

		assertEquals("The OclDebugger should bet at line " + currentLine + ".",
				currentLine, debugger.getCurrentLine());
	}

	/**
	 * Helper method asserting that the last entry of the call stack has the right
	 * name.
	 * 
	 * @param name
	 *          The name to be asserted.
	 * @param debugger
	 *          The {@link OclDebugger}.
	 */
	protected void assertStackName(String name, OclDebugger debugger) {

		assertNotNull("The call stack should not be empty.", debugger.getStack());
		assertFalse("The call stack should not be empty.",
				debugger.getStack().length == 0);

		String[] debuggerStack = debugger.getStack();
		String callStackName = debuggerStack[debuggerStack.length - 1];
		assertEquals(
				"The name of the last entry on the call stack should start with '"
						+ name + "'", name,
				callStackName.substring(0, callStackName.indexOf(",")));
	}

	/**
	 * Helper method asserting the size of the call stack.
	 * 
	 * @param stackSize
	 *          The size of the stack.
	 * @param debugger
	 *          The {@link OclDebugger}.
	 */
	protected void assertStackSize(int stackSize, OclDebugger debugger) {

		assertEquals("The Stack should have the size " + stackSize + ".",
				stackSize, debugger.getStack().length);
	}

	/**
	 * Helper method asserting that a variable with a given name is visible in the
	 * current stack frame.
	 * 
	 * @param name
	 *          The name of the variable expected to be visible.
	 * @param debugger
	 *          The {@link OclDebugger} to assert.
	 */
	protected void assertVariableExist(String name, OclDebugger debugger) {

		assertTrue("The variable '" + name
				+ "' should be visible for the current stack.",
				getVariablesFromStackFrame(debugger).keySet().contains(name));
	}

	/**
	 * Helper method asserting the count of variables visible in the current stack
	 * frame.
	 * 
	 * @param count
	 *          The expected count of variables.
	 * @param debugger
	 *          The {@link OclDebugger} to assert.
	 */
	protected void assertVariableNumber(int count, OclDebugger debugger) {

		assertEquals(
				"The expected number of variables for the current stack frame was wrong.",
				count, getVariablesFromStackFrame(debugger).size());
	}

	/**
	 * Helper method executing a given {@link DebugStep} and waiting for a given
	 * {@link DebugEvent} afterwards. Fails, if the {@link DebugEvent} will not
	 * happen within 10 seconds.
	 * 
	 * @param step
	 *          The {@link DebugStep} to be executed.
	 * @param event
	 *          The {@link DebugEvent} to be wait for.
	 * @param debugger
	 *          The {@link OclDebugger}.
	 */
	protected void debugStepAndWaitFor(DebugStep step, DebugEvent event,
			OclDebugger debugger) {

		debugStepAndWaitFor(step, event, debugger, 10000);
	}

	/**
	 * Helper method executing a given {@link DebugStep} and waiting for a given
	 * {@link DebugEvent} afterwards. Fails, if the {@link DebugEvent} will not
	 * happen within the specified timeout.
	 * 
	 * @param step
	 *          The {@link DebugStep} to be executed.
	 * @param event
	 *          The {@link DebugEvent} to be wait for.
	 * @param debugger
	 *          The {@link OclDebugger}.
	 * @param timeout
	 *          The timeout.
	 */
	protected void debugStepAndWaitFor(DebugStep step, DebugEvent event,
			OclDebugger debugger, long timeout) {

		lastReceivedLines.clear();

		switch (step) {
		case RESUME:
			debugger.resume();
			break;
		case TERMINATE:
			debugger.terminate();
			break;
		case STEP_INTO:
			debugger.stepInto();
			break;
		case STEP_OVER:
			debugger.stepOver();
			break;
		case STEP_RETURN:
			debugger.stepReturn();
			break;
		default:
			Assert.fail("Unknown debugstep: " + step.name());
		}

		/*
		 * try { Thread.sleep(100); } catch (InterruptedException e) { // Not
		 * important. }
		 */

		waitForEvent(event, timeout);
	}

	/**
	 * Helper method creating an {@link OclDebugger} for a given OCL resource.
	 * 
	 * Besides, a {@link SocketListener} will be created and started that puts the
	 * last received event into the lastReceivedLine field.
	 * 
	 * @param oclResource
	 *          Path leading to the OCL file used for this {@link OclDebugger}
	 *          relative to the root directory of this test plugin.
	 * @return The created {@link OclDebugger}
	 * @throws Exception
	 */
	protected OclDebugger generateDebugger(String oclResource) throws Exception {
		
		synchronized (System.out) {
			System.out.println("== generateDebugger ==");
			System.out.println(oclResource);
		}

		final String[] modelObjects = { "TestClass" };
		final List<Constraint> constraints;
		final Set<IModelInstanceObject> imio;
		final int eventPort = findFreePort();

		constraints = getConstraints(MODEL_PATH, oclResource);
		imio = getModelInstanceObjects(MODEL_INSTANCE_PATH, modelObjects);

		assertNotNull("There is no active model set.", ModelBusPlugin
				.getModelRegistry().getActiveModel());
		assertNotNull(
				"There is no active model instance for the model under test.",
				ModelBusPlugin.getModelInstanceRegistry().getActiveModelInstance(
						modelUnderTest));

		debugger = new OclDebugger(modelInstanceUnderTest);
		debugger.setDebugMode(true);

		new Thread(new Runnable() {

			@Override
			public void run() {

				debugger.setEventPort(eventPort);
				debugger.interpretConstraint(constraints.get(0), imio.iterator().next());
			}
		}).start();

		// Thread.sleep(1000);
		socket = new Socket("localhost", eventPort);
		socketListener = new SocketListener();
		socketListener.start();

		return debugger;
	}

	/**
	 * Helper method returning all variables for the last stack frame as a Map.
	 * 
	 * @param debugger
	 *          The {@link OclDebugger} for which the variables shall be returned.
	 * @return The variables as a {@link Map} (keys are names as {@link String}
	 *         s).
	 */
	protected Map<String, Object> getVariablesFromStackFrame(OclDebugger debugger) {

		int frameId = debugger.getStack().length;

		if (frameId > 0) {
			frameId = frameId - 1;
			return debugger
					.getFrameVariables(debugger.getStack()[frameId].split(",")[1]);
		}

		else
			return Collections.emptyMap();
	}

	/**
	 * Helper method that waits till a given event will be sent by the
	 * {@link OclDebugger} with a timeout of 10 seconds.
	 * 
	 * @param event
	 *          The {@link DebugEvent} to be waited for.
	 */
	protected void waitForEvent(DebugEvent event) {

		waitForEvent(event, 10000);
	}

	/**
	 * Helper method that waits till a given event will be sent by the
	 * {@link OclDebugger}.
	 * 
	 * @param event
	 *          The {@link DebugEvent} to be waited for.
	 * @param timeout
	 *          The maximum amount of time to wait for the {@link DebugEvent} .
	 *          Fails otherwise.
	 */
	protected void waitForEvent(DebugEvent event, long timeout) {

		long currentMillis = System.currentTimeMillis();
		while (!lastReceivedLines.contains(event.name() + ":")) {
			try {
				Thread.sleep(100);
				if (currentMillis + timeout < System.currentTimeMillis())
					Assert.fail("Expected DebugEvent " + event.name()
							+ " did not happen within specified timeout.");
				// no else.
			} catch (InterruptedException e) {
				/* Not that important. */
			}
		}
	}

	/**
	 * Helper {@link Thread} listening to the {@link Socket} of the
	 * {@link OclDebugger} for events.
	 * 
	 * @author Claas Wilke
	 */
	private class SocketListener extends Thread {

		/**
		 * If this {@link SocketListener} shall terminate, set this to
		 * <code>true</code>.
		 */
		public boolean terminate = false;

		/**
		 * Creates a new {@link SocketListener}.
		 */
		public SocketListener() {

			lastReceivedLines.clear();
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String inputLine;

				while (!terminate && (inputLine = in.readLine()) != null) {
					lastReceivedLines.add(inputLine);
				}
			} catch (IOException e) {
				if (!terminate)
					Assert.fail("Could not read from socket of debugger: "
							+ e.getMessage());
				// no else.
			}
		}
	}
}
