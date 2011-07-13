package org.dresdenocl.metrics.test;

import org.dresdenocl.metrics.test.constraintmetric.AllConstraintMetricsTests;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test Suite containing all Dresden OCL Metrics tests.
 * 
 * @author Claas Wilke
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AllConstraintMetricsTests.class, TestOclMetrics.class })
public class AllMetricsTests extends AbstractDresdenOclTest {
	/* Remains empty. */
}
