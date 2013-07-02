package org.dresdenocl.debug.test.unit;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.debug.test.unit.DebuggerUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DebuggerUnitTest.class, TestDebugLetExpressions.class,
		TestDebugLiterals.class, TestDebugString.class })
public class AllUnitTests extends AbstractDresdenOclTest {
	// remains emtpy
}
