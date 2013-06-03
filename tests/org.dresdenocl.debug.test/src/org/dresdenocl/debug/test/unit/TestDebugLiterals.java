package org.dresdenocl.debug.test.unit;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.CallStackConstants;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Contains test cases testing the debugging of operations defined on the String
 * type.
 * 
 * @author Claas Wilke
 */
public class TestDebugLiterals extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {
		AbstractDebuggerTest.setUp();
	}

	@AfterClass
	public static void tearDown() {
		AbstractDebuggerTest.tearDown();
	}

	@Test
	public void testBooleanLiteralStepInto01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/boolean01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testBooleanLiteralStepOver01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/boolean01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testBooleanLiteralStepReturn01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/boolean01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.BOOLEAN_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testIntegerLiteralStepInto01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/integer01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testIntegerLiteralStepOver01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/integer01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testIntegerLiteralStepReturn01() throws Exception {

		OclDebugger debugger = generateDebugger("resources/expressions/literals/integer01.ocl");

		File resourceFile = getFile(MODEL_INSTANCE_PATH,
				DebugTestPlugin.PLUGIN_ID);

		/* Start debugging. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		waitForEvent(DebugEvent.SUSPENDED);

		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist("self", debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after boolean literal 'true'. */
		assertCurrentLine(5, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("self", debugger);
		assertVariableExist("result", debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}
}
