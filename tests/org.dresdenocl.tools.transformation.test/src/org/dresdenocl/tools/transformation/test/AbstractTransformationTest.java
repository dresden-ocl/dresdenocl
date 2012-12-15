package tudresden.ocl20.pivot.tools.transformation.test;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import tudresden.ocl20.pivot.tools.transformation.TransformationPlugin;
import tudresden.ocl20.pivot.tools.transformation.test.util.TestFalseTrans;
import tudresden.ocl20.pivot.tools.transformation.test.util.TestParallelTrans;
import tudresden.ocl20.pivot.tools.transformation.test.util.TestTrans;

/**
 * Abstract test class for transformation tests.
 * 
 * @author Claas Wilke
 */
public abstract class AbstractTransformationTest extends AbstractDresdenOclTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDresdenOclTest.setUp();
		TransformationPlugin.getTransformationRegistry().addTransformation(
				new TestTrans("", ""));
		TransformationPlugin.getTransformationRegistry().addTransformation(
				new TestParallelTrans("", ""));

		TransformationPlugin.getTransformationRegistry().addTransformation(
					new TestFalseTrans("", ""));

	}

	@AfterClass
	public static void tearDown() {

		TransformationPlugin.getTransformationRegistry().removeTransformation(
				"TestParallelTrans");
		TransformationPlugin.getTransformationRegistry().removeTransformation(
				"TestTrans");
		TransformationPlugin.getTransformationRegistry().removeTransformation(
				"TestFalseTrans");

	}
}
