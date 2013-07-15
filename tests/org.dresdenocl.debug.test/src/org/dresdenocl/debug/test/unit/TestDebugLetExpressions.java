package org.dresdenocl.debug.test.unit;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.CallStackConstants;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Contains test cases testing the debugging of operations defined on the String
 * type.
 * 
 * @author Claas Wilke
 */
public class TestDebugLetExpressions extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testLetExpressionStepInto01() throws Exception {

		String oclResource = "resources/expressions/let01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'senseOfLife'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (senseOfLife)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable expression 'senseOfLife'. */
		assertCurrentLine(6, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (senseOfLife)",
				debugger);
		/* 'senseOfLife' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("senseOfLife", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '42'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		/* 'senseOfLife' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("senseOfLife", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after variable expression 'senseOfLife'. */
		assertCurrentLine(6, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (senseOfLife)",
				debugger);
		/* 'senseOfLife' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("senseOfLife", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepInto02() throws Exception {

		String oclResource = "resources/expressions/let02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'col'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (col)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable expression 'col'. */
		assertCurrentLine(6, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (col)", debugger);
		/* 'col' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("col", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		/* 'col' should be on the stack. */
		/* 'oclCollection' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("col", debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at String literal 'first'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.STRING_LITERAL + " ('first')",
				debugger);
		/* 'col' should be on the stack. */
		/* 'oclCollection' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("col", debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after collection literal. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		/* 'col' should be on the stack. */
		/* 'oclCollection' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("col", debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after variable expression 'col'. */
		assertCurrentLine(6, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (col)", debugger);
		/* 'senseOfLife' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("col", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepInto03() throws Exception {

		String oclResource = "resources/expressions/let03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (outer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'inner'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (inner)",
				debugger);
		/* 'outer' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call expression '+'. */
		assertCurrentLine(6, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable expression 'outer'. */
		assertCurrentLine(6, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (outer)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at string literal 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.STRING_LITERAL + " ('outer')",
				debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after variable expression 'outer'. */
		assertCurrentLine(6, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (outer)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable expression 'inner'. */
		assertCurrentLine(6, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (inner)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		/* 'oclSource' should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at string literal 'inner'. */
		assertCurrentLine(5, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.STRING_LITERAL + " ('inner')",
				debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		/* 'oclSource' should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after variable expression 'inner'. */
		assertCurrentLine(6, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (inner)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		/* 'oclSource' should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call expression '+'. */
		assertCurrentLine(6, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		/* 'oclSource' should be on the stack. */
		/* 'param1' should be on the stack. */
		assertVariableNumber(5, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression '+'. */
		assertCurrentLine(6, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* 'outer' should be on the stack. */
		/* 'inner' should be on the stack. */
		/* 'oclOperationResult' should be on the stack. */
		assertVariableNumber(6, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist("inner", debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1, debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepOver01() throws Exception {

		String oclResource = "resources/expressions/let01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'senseOfLife'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (senseOfLife)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepOver02() throws Exception {

		String oclResource = "resources/expressions/let02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'col'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (col)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepOver03_01() throws Exception {

		String oclResource = "resources/expressions/let03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (outer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepOver03_02() throws Exception {

		String oclResource = "resources/expressions/let03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (outer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'inner'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (inner)",
				debugger);
		/* 'outer' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepReturn01() throws Exception {

		String oclResource = "resources/expressions/let01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'senseOfLife'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (senseOfLife)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepReturn02() throws Exception {

		String oclResource = "resources/expressions/let02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'col'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (col)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepReturn03_01() throws Exception {

		String oclResource = "resources/expressions/let03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (outer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}

	@Test
	public void testLetExpressionStepReturn03_02() throws Exception {

		String oclResource = "resources/expressions/let03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'outer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (outer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at let expression 'inner'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.LET_EXPRESSION + " (inner)",
				debugger);
		/* 'outer' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist("outer", debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after let expression. */
		assertCurrentLine(4, debugger);
		assertStackSize(1, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* 'result' should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN,
				DebugEvent.CONSTRAINT_INTERPRETED, debugger);
	}
}
