package org.dresdenocl.debugger.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.debugger.test.unit.DebuggerUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DebuggerUnitTest.class })
public class AllDebuggerTests extends AbstractDresdenOclTest {
	// remains emtpy
}
