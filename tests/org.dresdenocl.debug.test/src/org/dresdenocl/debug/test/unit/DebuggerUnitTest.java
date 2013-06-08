package org.dresdenocl.debug.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.junit.BeforeClass;
import org.junit.Test;

public class DebuggerUnitTest extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testStartUp01() throws Exception {
		OclDebugger debugger = generateDebugger(RESOURCE01_PATH);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
		
		assertTrue("Debugger is not in debug mode", debugger.isDebugMode());
		assertTrue("Debugger is not suspended", debugger.isSuspended());
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.RESUMED, debugger);
		assertFalse("Debugger is suspended but should be not",
				debugger.isSuspended());
	}

	@Test
	public void testBreakpointSet01() throws Exception {

		OclDebugger debugger = generateDebugger(RESOURCE01_PATH);
		File resourceFile = getFile(MODEL_INSTANCE_PATH, DebugTestPlugin.PLUGIN_ID);

		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 3);
		Thread.sleep(2000);
		debugger.resume();
		Thread.sleep(1000);
		assertTrue("Debugger is not in the right line",
				debugger.getCurrentLine() == 3);
	}
}
