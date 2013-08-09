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
public class TestDebugItators extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testAnyIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/any01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (1) */

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testAnyIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/any01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testAnyIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/any01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testAnyIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/any01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testAnyIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/any01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testExistIteratorStepInto01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/exists01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over second element (2) */
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
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
	public void testExistIteratorStepOver01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/exists01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
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
	public void testExistIteratorStepOver01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/exists01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
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
	public void testExistIteratorStepReturn01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/exists01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
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
	public void testExistIteratorStepReturn01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/exists01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'exists'. */
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
	public void testOneIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/one01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (1) */

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testOneIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/one01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testOneIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/one01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testOneIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/one01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testOneIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/one01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'one'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (one)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'any'. */
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
	public void testSelectIteratorStepInto01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/select01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over second element (2) */
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over third element (3). */
		
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
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
	public void testSelectIteratorStepOver01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/select01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
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
	public void testSelectIteratorStepOver01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/select01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
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
	public void testSelectIteratorStepReturn01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/select01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
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
	public void testSelectIteratorStepReturn01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/select01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at iterator expression 'select'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (select)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'select'. */
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
	public void testImplicitIteratorVariable01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/implicitItVar01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at collection literal 'set'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.COLLECTION_LITERAL + " (Set)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over first element (1) */
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Iteration over second element (2) */
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at integer literal '2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (2)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'any'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (any)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'any'. */
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
}
