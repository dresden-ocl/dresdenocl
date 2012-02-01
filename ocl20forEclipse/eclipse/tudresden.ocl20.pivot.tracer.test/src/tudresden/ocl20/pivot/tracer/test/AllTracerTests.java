package tudresden.ocl20.pivot.tracer.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tudresden.ocl20.pivot.tracer.test.constraintkinds.ConstraintKindTracerTest;
import tudresden.ocl20.pivot.tracer.test.standardlibrary.AllStandardLibraryTracerTests;

/**
 * Test suite containing all Dresden OCL Tracer Tests
 * 
 * @author Lars Schütze
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ConstraintKindTracerTest.class,
	AllStandardLibraryTracerTests.class})
public class AllTracerTests extends AbstractDresdenOclTest {
	/* Remains empty */
}
