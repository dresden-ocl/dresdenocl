package org.dresdenocl.debugger.test.unit;

import org.dresdenocl.debugger.test.AbstractDebuggerTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DebuggerUnitTest extends AbstractDebuggerTest {
	
	private String[] modelObjects = {"Class1"};

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractDebuggerTest.tearDown();
	}
	
	@Test
	public void testStartUp01() {
		
	}
}
