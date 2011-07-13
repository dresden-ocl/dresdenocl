package org.dresdenocl.metrics.test.constraintmetric;

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
@Suite.SuiteClasses({ TestBooleanLiteralExpMetric.class,
		TestCollectionLiteralExpMetric.class,
		TestEnumerationLiteralExpMetric.class, TestIfExpMetric.class,
		TestIntegerLiteralExpMetric.class, TestInvalidLiteralExpMetric.class,
		TestIterateExpMetric.class, TestIteratorExpMetric.class,
		TestLetExpMetric.class, TestOperationCallExpMetric.class,
		TestPropertyCallExpMetric.class, TestStringLiteralExpMetric.class,
		TestTupleLiteralExpMetric.class, TestTypeLiteralExpMetric.class,
		TestRealLiteralExpMetric.class, TestUndefinedLiteralExpMetric.class,
		TestVariableExpMetric.class })
public class AllConstraintMetricsTests extends AbstractDresdenOclTest {
	/* Remains empty. */
}
