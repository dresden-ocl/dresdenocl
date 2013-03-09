package org.dresdenocl.debug.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DebuggerUnitTest extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractDebuggerTest.tearDown();
	}

	@Test
	public void testStartUp01() throws Exception {

		OclDebugger debugger = generateDebugger();

		assertTrue(debugger.isDebugMode());
		assertTrue(debugger.isSuspended());
		debugger.resume();
		assertFalse(debugger.isSuspended());
		debugger.terminate();
	}

	@Test
	public void testBreakpointSet01() throws Exception {

		OclDebugger debugger = generateDebugger();
		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 3);
		debugger.resume();
		assertTrue(debugger.getCurrentLine() == 3);
	}
}
