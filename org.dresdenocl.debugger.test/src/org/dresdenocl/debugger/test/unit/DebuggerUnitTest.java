package org.dresdenocl.debugger.test.unit;

import org.dresdenocl.debugger.test.AbstractDebuggerTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class DebuggerUnitTest extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractDebuggerTest.tearDown();
	}
}
