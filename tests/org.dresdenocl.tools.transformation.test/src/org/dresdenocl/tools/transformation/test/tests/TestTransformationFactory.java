package org.dresdenocl.tools.transformation.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.eclipse.emf.ecore.EObject;
import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.tools.codegen.IOcl2CodeSettings;
import org.dresdenocl.tools.transformation.ITransformation;
import org.dresdenocl.tools.transformation.TransformationFactory;
import org.dresdenocl.tools.transformation.impl.Tuple;
import org.dresdenocl.tools.transformation.test.AbstractTransformationTest;

/**
 * This test will test the class TestTransformationFactory.java of the package
 * org.dresdenocl.tools.transformation.
 * 
 * @see org.dresdenocl.tools.transformation.TestTransformationFactory
 */
public class TestTransformationFactory extends AbstractTransformationTest {

	private static ITransformation<?, ?, ?> itrans;

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractTransformationTest.setUp();
		itrans = TransformationFactory.getInstance().getTransformation(
				"TestTrans", "", "");
	}

	@Test
	public void checkGetTransformationSimple() {

		ITransformation<?, ?, ?> trans = TransformationFactory.getInstance()
				.getTransformation("TestTrans1X", "", "");
		assertNull("A not exists transformation is created", trans);
		trans = TransformationFactory.getInstance().getTransformation(
				"TestParallelTrans", "", "");
		assertNotNull(trans);
		assertNotSame("Is same transformation type.", trans.getClass()
				.getName(), itrans.getClass().getName());
		trans = TransformationFactory.getInstance().getTransformation(
				itrans.getClass().getSimpleName(), "", "");
		assertNotNull(trans);
		assertNotSame("Isn't generate a new instance", trans, itrans);
		assertEquals("Isn't same transformation type.", trans.getClass()
				.getName(), itrans.getClass().getName());
	}

	@Test
	public void checkGetTransformationParameter() {

		ITransformation<EObject, IOcl2CodeSettings, String> trans = TransformationFactory
				.getInstance().getTransformation("TestParallelTrans",
						EObject.class, String.class, IOcl2CodeSettings.class,
						"", "");
		assertNull("The transformation is created with false paramter", trans);
		trans = TransformationFactory.getInstance().getTransformation(
				itrans.getClass().getSimpleName(), EObject.class, String.class,
				IOcl2CodeSettings.class, "", "");
		assertNotNull(trans);
		assertEquals("Isn't of same transformation type.", trans.getClass()
				.getName(), itrans.getClass().getName());
	}

	@Test
	public void checkGetTransformationParameterParallel() {

		ITransformation<EObject, IOcl2CodeSettings, Tuple<String, EObject>> trans = TransformationFactory
				.getInstance().getParallelTransformation(
						itrans.getClass().getSimpleName(), EObject.class,
						String.class, EObject.class, IOcl2CodeSettings.class,
						"", "");
		assertNull("The transformation is created with false paramter", trans);
		trans = TransformationFactory.getInstance().getParallelTransformation(
				"TestParallelTrans", EObject.class, String.class,
				EObject.class, IOcl2CodeSettings.class, "", "");
		assertNotNull(trans);
		assertEquals("Isn't of same transformation type.", trans.getClass()
				.getSimpleName(), "TestParallelTrans");

	}
}
