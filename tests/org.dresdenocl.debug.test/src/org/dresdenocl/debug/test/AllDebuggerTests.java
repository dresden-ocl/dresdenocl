package org.dresdenocl.debug.test;

import org.dresdenocl.debug.test.unit.AllUnitTests;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AllUnitTests.class })
public class AllDebuggerTests extends AbstractDresdenOclTest {
	// remains emtpy
}
