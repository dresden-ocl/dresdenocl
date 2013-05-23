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
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(2000);
		debugger.resume();
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(1000);

		/* Debugger at boolean literal 'true'. */
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* TODO How to check the stack frame variables accordingly? */
		/* Self should be on the stack. */
		// assertNull("The Stack variables should be empty.",
		// debugger.getFrameVariables(debugger.getStack()[0]));
		assertCurrentLine(5, debugger);

		debugger.stepInto();
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(1000);

		/* Debugger after boolean literal 'true'. */
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* TODO How to check the stack frame variables accordingly? */
		/* Self should be on the stack. */
		/* 'result' should be on the stack?. */
		// assertNull("The Stack variables should be empty.",
		// debugger.getFrameVariables(debugger.getStack()[0]));
		assertCurrentLine(5, debugger);

		/* TODO Continue test case. */
		debugger.stepInto();
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(1000);

		/* TODO How to determine whether or not the debugger terminated?. */
	}
}
