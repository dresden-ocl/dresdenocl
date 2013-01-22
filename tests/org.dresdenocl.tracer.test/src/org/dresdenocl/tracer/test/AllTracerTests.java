package org.dresdenocl.tracer.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.dresdenocl.tracer.test.constraintkinds.ConstraintKindTracerTest;
import org.dresdenocl.tracer.test.standardlibrary.AllStandardLibraryTracerTests;

/**
 * Test suite containing all Dresden OCL Tracer Tests
 * 
 * @author Lars Schuetze
 * 
 */
@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses({ ConstraintKindTracerTest.class,
	AllStandardLibraryTracerTests.class})
public class AllTracerTests extends AbstractDresdenOclTest {
	/* Remains empty */
}
