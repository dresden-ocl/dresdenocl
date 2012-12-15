package org.dresdenocl.metrics.test.constraintmetric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.test.AbstractMetricTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.language.ocl.resource.ocl.Ocl22Parser;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * Contains test cases for {@link OclMetrics}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestIteratorExpMetric extends AbstractMetricTest {

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
				"context Type1 def: iteratorVal = Set { true } -> any(true)",
				modelUnderTest);
		Constraint constraint = constraintsUnderTest.get(0);

		ConstraintMetric metric = OclMetrics.computeMetric(constraint);

		assertNotNull(metric);
		assertEquals(constraint, metric.getReferredConstraint());
		assertEquals(4, metric.getExpressionCount());
		assertEquals(3, metric.getExpressionDepth());
		assertEquals(0, metric.getNumberOfIfExpressions());
		assertEquals(0, metric.getNumberOfLetExpressions());

		assertNull(metric.getCalledOperations());
		assertNull(metric.getCalledProperties());
		assertNotNull(metric.getUsedLiterals());

		Map<String, Integer> usedIterators = metric.getUsedIterators();
		assertEquals(1, usedIterators.size());
		assertTrue(usedIterators.containsKey("any"));
		assertEquals(new Integer(1), usedIterators.get("any"));
	}
}
