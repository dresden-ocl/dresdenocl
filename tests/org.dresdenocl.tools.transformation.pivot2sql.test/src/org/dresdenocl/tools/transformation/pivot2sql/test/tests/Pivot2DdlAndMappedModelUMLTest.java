package org.dresdenocl.tools.transformation.pivot2sql.test.tests;

import static org.junit.Assert.fail;

import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TestPerformer;
import org.dresdenocl.tools.transformation.pivot2sql.test.tests.util.TransformationTest;
import org.junit.Before;

public class Pivot2DdlAndMappedModelUMLTest extends Pivot2DdlAndMappedModelTest {

	@Before
	public void setUp() {

		try {
			model = TestPerformer.addUMLModel(TransformationTest.TEST_COMPLEX);
		} catch (Exception e) {
			fail("Can't initialize model");
		}
	}

}
