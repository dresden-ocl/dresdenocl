package org.dresdenocl.metrics.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.ConstraintMetrics;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.Ocl22Parser;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;

/**
 * Contains test cases for {@link OclMetrics}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestConstraintMetrics extends AbstractMetricTest {

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractMetricTest.setUp();
	}

	@AfterClass
	public static void tearDown() {
		AbstractMetricTest.tearDown();
	}

	@Test
	public void testGetCalledOperations01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: not false\n");
		file.append("context Type1 inv: true or false\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		Map<String, Integer> calledOperations = metric.getCalledOperations();
		assertEquals(2, calledOperations.size());
		assertTrue(calledOperations.containsKey("Boolean.not(..)"));
		assertEquals(new Integer(1), calledOperations.get("Boolean.not(..)"));
		assertTrue(calledOperations.containsKey("Boolean.or(..)"));
		assertEquals(new Integer(1), calledOperations.get("Boolean.or(..)"));
	}

	@Test
	public void testGetCalledProperties01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: not prop1\n");
		file.append("context Type1 inv: prop1\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		Map<String, Integer> calledProperties = metric.getCalledProperties();
		assertEquals(1, calledProperties.size());
		assertTrue(calledProperties.containsKey("root::pack1::Type1::prop1"));
		assertEquals(new Integer(2),
				calledProperties.get("root::pack1::Type1::prop1"));
	}

	@Test
	public void testGetConstraintCount01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: false\n");
		file.append("context Type1 inv: true\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(2, metric.getConstraintCount());
	}

	@Test
	public void testGetExpressionCount01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: false\n");
		file.append("context Type1 inv: true\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(2, metric.getExpressionCount());
		assertEquals(1, metric.getMinExpressionCount());
		assertEquals(1, metric.getMaxExpressionCount());
		assertEquals(1, metric.getMeanExpressionCount());
		assertEquals(1d, metric.getAvgExpressionCount(), 0d);
	}

	@Test
	public void testGetExpressionCount02() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: false\n");
		file.append("context Type1 inv: not true\n");
		file.append("context Type1 inv: true and true\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(6, metric.getExpressionCount());
		assertEquals(1, metric.getMinExpressionCount());
		assertEquals(3, metric.getMaxExpressionCount());
		assertEquals(2, metric.getMeanExpressionCount());
		assertEquals(2d, metric.getAvgExpressionCount(), 0d);
	}

	@Test
	public void testGetExpressionDepth01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: false\n");
		file.append("context Type1 inv: true\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(1, metric.getMinExpressionDepth());
		assertEquals(1, metric.getMaxExpressionDepth());
		assertEquals(1, metric.getMeanExpressionDepth());
		assertEquals(1d, metric.getAvgExpressionDepth(), 0d);
	}

	@Test
	public void testGetExpressionDepth02() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: false\n");
		file.append("context Type1 inv: not true\n");
		file.append("context Type1 inv: true and true\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(1, metric.getMinExpressionDepth());
		assertEquals(2, metric.getMaxExpressionDepth());
		assertEquals(2, metric.getMeanExpressionDepth());
		assertEquals(1.66d, metric.getAvgExpressionDepth(), 0.01d);
	}

	@Test
	public void testGetNumberOfConstraintsByKind01() throws Exception {
	
		StringBuffer file = new StringBuffer();
		file.append("context Type1::op1() : Boolean body: true\n");
		file.append("context Type1 def: prop2 : Boolean = true\n");
		file.append("context Type1::prop1 derive: prop1\n");
		file.append("context Type1::prop1 init: true\n");
		file.append("context Type1 inv: true\n");
		file.append("context Type1::op1() : Boolean post: true\n");
		file.append("context Type1::op1() : Boolean pre: true\n");
	
		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);
	
		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);
	
		Map<ConstraintKind, Integer> constraintsByKind = metric.getNumberOfConstraintsByKind();
		
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.BODY));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.DEFINITION));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.DERIVED));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.INITIAL));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.INVARIANT));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.POSTCONDITION));
		assertEquals(new Integer(1), constraintsByKind.get(ConstraintKind.PRECONDITION));
	}

	@Test
	public void testGetNumberOfIfExpressions01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: if true then false else true endif\n");
		file.append("context Type1 inv: if false then true else false endif\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(2, metric.getNumberOfIfExpressions());
	}

	@Test
	public void testGetNumberOfLetExpressions01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: let a = true in a\n");
		file.append("context Type1 inv: let b = false in b\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		assertEquals(2, metric.getNumberOfLetExpressions());
	}

	@Test
	public void testGetUsedIterators01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: Set { true } -> any(true)\n");
		file.append("context Type1 inv: Set { false } -> any(false)\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		Map<String, Integer> usedIterators = metric.getUsedIterators();
		assertEquals(1, usedIterators.size());
		assertTrue(usedIterators.containsKey("any"));
		assertEquals(new Integer(2), usedIterators.get("any"));
	}

	@Test
	public void testGetUsedLiterals01() throws Exception {

		StringBuffer file = new StringBuffer();
		file.append("context Type1 inv: true\n");
		file.append("context Type1 inv: false\n");

		constraintsUnderTest = Ocl22Parser.INSTANCE.parseOclString(
				file.toString(), modelUnderTest);

		ConstraintMetrics metric = OclMetrics
				.computeMetric(constraintsUnderTest);

		Map<String, Integer> usedLiterals = metric.getUsedLiterals();
		assertEquals(1, usedLiterals.size());
		assertTrue(usedLiterals.containsKey("Boolean"));
		assertEquals(new Integer(2), usedLiterals.get("Boolean"));
	}
}