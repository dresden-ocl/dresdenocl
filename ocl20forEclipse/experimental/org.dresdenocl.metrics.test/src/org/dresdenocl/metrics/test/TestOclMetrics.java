package org.dresdenocl.metrics.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.ConstraintMetrics;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;

/**
 * Contains test cases for {@link OclMetrics}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestOclMetrics extends AbstractMetricTest {

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractMetricTest.setUp();
	}

	@AfterClass
	public static void tearDown() {
		AbstractMetricTest.tearDown();
	}

	@Test
	public void testConstriantMetrics01() throws Exception {

		File constraintFile = AbstractDresdenOclTest.getFile(
				"constraints/uml_wfrs.ocl", "org.dresdenocl.examples.uml");
		org.junit.Assert.assertTrue(constraintFile.exists());

		modelUnderTest.removeAllConstraints();
		constraintsUnderTest = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, modelUnderTest, true);
		org.junit.Assert.assertNotNull(constraintsUnderTest);

		ConstraintMetrics metric = OclMetrics.computeMetric(modelUnderTest
				.getConstraints());

		assertNotNull(metric);
		assertNull(metric.getReferredConstraint());

		System.out.println(metric.getReport());

		StringBuffer complexity = new StringBuffer();
		complexity.append("\n\n");
		complexity.append("Complexity Stats:\n");
		complexity.append("# Expressions, Expression Tree Depth\n");

		for (ConstraintMetric constraintMetric : metric.getConstraintMetrics()) {
			complexity.append(constraintMetric.getExpressionCount());
			complexity.append(",");
			complexity.append(constraintMetric.getExpressionDepth());
			complexity.append("\n");
		}
		System.out.println(complexity);

		// TODO
		assertEquals(0, metric.getExpressionDepth());
		// TODO
		// metric.getConstraintCountPerKind();
		// TODO
	}
}
