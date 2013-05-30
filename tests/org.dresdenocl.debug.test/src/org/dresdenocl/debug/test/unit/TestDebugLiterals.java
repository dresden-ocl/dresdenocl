package org.dresdenocl.debug.test.unit;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.CallStackConstants;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Contains test cases testing the debugging of operations defined on the String
 * type.
 * 
 * @author Claas Wilke
 */
public class TestDebugLiterals extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {
		AbstractDebuggerTest.tearDown();
	}

	@Test
	public void testBooleanLiteralStepInto01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/boolean01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DEBUG_EVENT_SUSPENDED);

		debugger.resume();
		lastReceivedLine = null;
		waitForEvent(DEBUG_EVENT_SUSPENDED);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugger.stepInto();
		waitForEvent(DEBUG_EVENT_SUSPENDED);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* TODO 'result' should be on the stack?. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugger.stepInto();
		waitForEvent(DEBUG_EVENT_SUSPENDED);

		/* Debugging terminated. */
		/* TODO How to determine whether or not the debugger terminated?. */
		// waitForEvent(DEBUG_EVENT_CONSTRAINT_INTERPRETED);
	}
}
