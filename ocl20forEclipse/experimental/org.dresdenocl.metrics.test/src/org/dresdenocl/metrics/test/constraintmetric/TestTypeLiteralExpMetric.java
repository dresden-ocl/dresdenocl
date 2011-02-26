package org.dresdenocl.metrics.test.constraintmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.test.AbstractMetricTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Contains test cases for {@link OclMetrics}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestTypeLiteralExpMetric extends AbstractMetricTest {

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractMetricTest.setUp();
	}

	@AfterClass
	public static void tearDown() {
		AbstractMetricTest.tearDown();
	}

	@Test
	public void testConstraintMetric01() throws Exception {

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				"context Class def: typeVal = Class", modelUnderTest);
		Constraint constraint = constraintsUnderTest.get(0);

		ConstraintMetric metric = OclMetrics.computeMetric(constraint);

		assertNotNull(metric);
		assertEquals(constraint, metric.getReferredConstraint());
		assertEquals(1, metric.getExpressionCount());
		assertEquals(1, metric.getExpressionDepth());
	}
}
