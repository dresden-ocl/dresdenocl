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

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * Contains test cases for {@link OclMetrics}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestOperationCallExpMetric extends AbstractMetricTest {

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
				"context Type1 inv: not true", modelUnderTest);
		Constraint constraint = constraintsUnderTest.get(0);

		ConstraintMetric metric = OclMetrics.computeMetric(constraint);

		assertNotNull(metric);
		assertEquals(constraint, metric.getReferredConstraint());
		assertEquals(2, metric.getExpressionCount());
		assertEquals(2, metric.getExpressionDepth());
		assertEquals(0, metric.getNumberOfIfExpressions());
		assertEquals(0, metric.getNumberOfLetExpressions());

		assertNull(metric.getCalledProperties());
		assertNotNull(metric.getUsedLiterals());
		assertNull(metric.getUsedIterators());

		Map<String, Integer> calledOperations = metric.getCalledOperations();
		assertEquals(1, calledOperations.size());
		assertTrue(calledOperations.containsKey("Boolean.not(..)"));
		assertEquals(new Integer(1), calledOperations.get("Boolean.not(..)"));
	}

	@Test
	public void testConstraintMetric02() throws Exception {

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				"context Type1 inv: true or false", modelUnderTest);
		Constraint constraint = constraintsUnderTest.get(0);

		ConstraintMetric metric = OclMetrics.computeMetric(constraint);

		assertNotNull(metric);
		assertEquals(constraint, metric.getReferredConstraint());
		assertEquals(3, metric.getExpressionCount());
		assertEquals(2, metric.getExpressionDepth());
		assertEquals(0, metric.getNumberOfIfExpressions());
		assertEquals(0, metric.getNumberOfLetExpressions());

		assertNull(metric.getCalledProperties());
		assertNotNull(metric.getUsedLiterals());
		assertNull(metric.getUsedIterators());

		Map<String, Integer> calledOperations = metric.getCalledOperations();
		assertEquals(1, calledOperations.size());
		assertTrue(calledOperations.containsKey("Boolean.or(..)"));
		assertEquals(new Integer(1), calledOperations.get("Boolean.or(..)"));
	}

	@Test
	public void testConstraintMetric03() throws Exception {
	
		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				"context Type1::op1() post: prop1 = prop1@pre", modelUnderTest);
		Constraint constraint = constraintsUnderTest.get(0);
	
		ConstraintMetric metric = OclMetrics.computeMetric(constraint);
	
		assertNotNull(metric);
		assertEquals(constraint, metric.getReferredConstraint());
		assertEquals(6, metric.getExpressionCount());
		assertEquals(4, metric.getExpressionDepth());
		assertEquals(0, metric.getNumberOfIfExpressions());
		assertEquals(0, metric.getNumberOfLetExpressions());
	
		assertNotNull(metric.getCalledProperties());
		assertNull(metric.getUsedLiterals());
		assertNull(metric.getUsedIterators());
	
		Map<String, Integer> calledOperations = metric.getCalledOperations();
		assertEquals(2, calledOperations.size());
		assertTrue(calledOperations.containsKey("OclAny@pre()"));
		assertEquals(new Integer(1), calledOperations.get("OclAny@pre()"));
		assertTrue(calledOperations.containsKey("OclAny.=(..)"));
		assertEquals(new Integer(1), calledOperations.get("OclAny.=(..)"));
	}
}
