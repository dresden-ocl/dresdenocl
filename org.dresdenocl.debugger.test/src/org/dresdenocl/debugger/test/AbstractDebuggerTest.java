package org.dresdenocl.debugger.test;

import org.dresdenocl.debug.IOclDebuggable;
import org.dresdenocl.model.IModel;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;


public abstract class AbstractDebuggerTest extends AbstractDresdenOclTest {
	
	protected IOclDebuggable debuggerUnderTest;
	protected IModel modelUnderTest;
	protected IModelInstance modelInstanceUnderTest;
	
	public static void setUp() throws Exception {
		AbstractDresdenOclTest.setUp();
		
		
	}
	
	public static void tearDown() {
		
	}
}
