package org.dresdenocl.debug.test.unit;

import static org.junit.Assert.assertNull;

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
public class TestDebugString extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testGreaterThanStepInto01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/standardlibrary/string/greaterThan01.ocl");
		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		Thread.sleep(2000);
		debugger.resume();
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(1000);

		/* Debugger at '>'. */
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.NON_STATIC_OPERATION, debugger);
		assertNull("The Stack variables should be empty.",
				debugger.getFrameVariables(debugger.getStack()[0]));
		assertCurrentLine(6, debugger);

		debugger.stepInto();
		/* TODO Shouldn't we wait for the debugger in a more intelligent way? */
		Thread.sleep(1000);

		/* Debugger at 'b'. */
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.STRING_LITERAL, debugger);
		assertNull("The Stack variables should be empty.",
				debugger.getFrameVariables(debugger.getStack()[0]));
		assertCurrentLine(5, debugger);

		/* TODO Continue test case. */
	}
}
