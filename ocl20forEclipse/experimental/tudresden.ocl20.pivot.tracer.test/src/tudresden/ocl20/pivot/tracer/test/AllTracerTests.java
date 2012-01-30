package tudresden.ocl20.pivot.tracer.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.tracer.test.constrainttests.AllContraintTracerTests;

/**
 * Test suite containing all Dresden OCL Tracer Tests
 * 
 * @author Lars Schütze
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AllContraintTracerTests.class })
public class AllTracerTests extends AbstractDresdenOclTest {
	/* Remains empty */
}
