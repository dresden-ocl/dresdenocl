package org.dresdenocl.debug.test.unit;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.debug.test.unit.DebuggerUnitTest;

/**
 * Test suite of all Debugger unit tests.
 * 
 * @author Claas Wilke
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DebuggerUnitTest.class, TestDebugIfExpressions.class,
		TestDebugItators.class, TestDebugLetExpressions.class,
		TestDebugLiterals.class, TestDebugOperationCallExpressions.class,
		TestDebugPropertyCallExpressions.class })
public class AllUnitTests extends AbstractDresdenOclTest {
	/* remains emtpy. */
}
