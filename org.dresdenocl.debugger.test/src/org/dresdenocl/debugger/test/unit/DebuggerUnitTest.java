package org.dresdenocl.debugger.test.unit;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debugger.test.AbstractDebuggerTest;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.Constraint;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DebuggerUnitTest extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {

		AbstractDebuggerTest.tearDown();
	}

	@Test
	public void testStartUp01() throws Exception {

		final OclDebugger debugger;
		final String[] modelObjects = { "Class1" };
		final List<Constraint> constraints;
		final Set<IModelInstanceObject> imio;

		imio = getModelInstanceObjects(MODEL_INSTANCE_PATH, modelObjects);

		debugger = new OclDebugger(modelInstanceUnderTest);
		debugger.setDebugMode(true);
		debugger.setEventPort(findFreePort());

		constraints = getConstraints(MODEL_PATH, RESOURCE01_PATH);

		new Thread(new Runnable() {

			@Override
			public void run() {

				debugger.interpretConstraint(constraints.get(0), imio.iterator().next());
			}

		}).start();
		
		this.wait(1000);
		
		assertTrue(debugger.isDebugMode());
		assertTrue(debugger.isSuspended());
	}
}
