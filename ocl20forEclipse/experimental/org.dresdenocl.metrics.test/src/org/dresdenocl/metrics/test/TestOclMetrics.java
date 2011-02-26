package org.dresdenocl.metrics.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.ModelMetric;
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
	public void testModelMetric01() throws Exception {

		File constraintFile = new File(
				"./../org.dresdenocl.examples.uml/constraints/uml_wfrs.ocl");
		org.junit.Assert.assertTrue(constraintFile.exists());

		modelUnderTest.removeAllConstraints();
		constraintsUnderTest = Ocl2ForEclipseFacade.parseConstraints(
				constraintFile, modelUnderTest, true);
		org.junit.Assert.assertNotNull(constraintsUnderTest);

		ModelMetric metric = OclMetrics.computeMetric(modelUnderTest);

		assertNotNull(metric);
		assertNull(metric.getReferredConstraint());

		System.out.println(metric.getReport());

		// TODO
		assertEquals(0, metric.getExpressionDepth());
		// TODO
		metric.getConstraintCountPerKind();
		// TODO
		metric.getReferredModelId();
	}
}
