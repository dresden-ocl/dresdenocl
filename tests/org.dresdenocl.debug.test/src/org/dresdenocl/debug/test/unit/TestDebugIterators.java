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
public class TestDebugIterators extends AbstractDebuggerTest {

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
	public void testClosureIteratorStepInto01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/closure01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterator expression 'closure'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (closure)",
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
	
		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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
	
		/* Debugger after iterator exp 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/collect01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
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

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger after iterator exp 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/collect01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/collect01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
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

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/collect01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/collect01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
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

		/* Debugger at iterator expression 'collect'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (collect)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'collect'. */
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
	public void testCollectNestedIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/collectNested01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
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

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger at operation call '<>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (<>)", debugger);
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

		/* Debugger after iterator exp 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collectNested'. */
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
	public void testCollectNestedIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/collectNested01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collectNested'. */
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
	public void testCollectNestedIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/collectNested01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
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

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'collectNested'. */
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
	public void testCollectNestedIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/collectNested01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'collectNested'. */
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
	public void testCollectNestedIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/collectNested01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
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

		/* Debugger at iterator expression 'collectNested'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION
				+ " (collectNested)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'collectNested'. */
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
	public void testExistIteratorStepInto02() throws Exception {

		String oclResource = "resources/expressions/iterator/exists02.ocl";
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

		/* Debugger at (outer) iterator expression 'exists'. */
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

		/* Debugger at (inner) iterator expression 'exists'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (exists)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist("i1", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertStackSize(3, debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i1)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i2)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
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
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertStackSize(3, debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i1)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i2)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertStackSize(4, debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
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
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
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
	public void testExistIteratorStepOver02_01() throws Exception {

		String oclResource = "resources/expressions/iterator/exists02.ocl";
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
	public void testExistIteratorStepOver02_02() throws Exception {

		String oclResource = "resources/expressions/iterator/exists02.ocl";
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

		/* Debugger at (outer) iterator expression 'exists'. */
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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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
	public void testExistIteratorStepReturn02_01() throws Exception {

		String oclResource = "resources/expressions/iterator/exists02.ocl";
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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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
	public void testExistIteratorStepReturn02_02() throws Exception {

		String oclResource = "resources/expressions/iterator/exists02.ocl";
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

		/* Debugger at (outer) iterator expression 'exists'. */
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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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
	public void testForAllIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Iteration over third element (3) */

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger at operation call '>'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (>)", debugger);
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

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepInto02() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll' (outer). */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll' (inner). */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
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
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i1'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i1)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i2'. */
		assertCurrentLine(5, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i2)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '3'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (3)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
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
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '='. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (=)", debugger);
		assertVariableNumber(7, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i1", debugger);
		assertVariableExist("i2", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepOver02_01() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepOver02_02() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll' (outer). */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepReturn02_01() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testForAllIteratorStepReturn02_02() throws Exception {

		String oclResource = "resources/expressions/iterator/forAll02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'forAll'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
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

		/* Debugger at iterator expression 'forAll' (outer). */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (forAll)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'forAll'. */
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
	public void testIterateStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/iterate01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
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

		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Initialization of iterate variable. */

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (1) */

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("sum", debugger);
		assertVariableExist("i", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

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
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("sum", debugger);
		assertVariableExist("i", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

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
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over third element (3) */

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("sum", debugger);
		assertVariableExist("i", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'sum'. */
		assertCurrentLine(5, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (sum)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

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
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist("i", debugger);
		assertVariableExist("sum", debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterate exp. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATE_EXPRESSION_RESULT, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterate exp. */
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
	public void testIterateStepOver01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/iterate01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATE_EXPRESSION_RESULT, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
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
	public void testIterateStepOver01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/iterate01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
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
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_ITERATE_EXPRESSION_RESULT, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
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
	public void testIterateStepReturn01_01() throws Exception {
	
		String oclResource = "resources/expressions/iterator/iterate01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
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
	public void testIterateStepReturn01_02() throws Exception {
	
		String oclResource = "resources/expressions/iterator/iterate01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);
	
		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
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
	
		/* Debugger at iterate expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATE_EXPRESSION, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after iterate exp. */
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
	public void testIsUniqueIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/isUnique01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
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

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over third element (3) */

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
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
	public void testIsUniqueIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/isUnique01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
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
	public void testIsUniqueIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/isUnique01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
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

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'isUnique'. */
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
	public void testIsUniqueIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/isUnique01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'isUnique'. */
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
	public void testIsUniqueIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/isUnique01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
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

		/* Debugger at iterator expression 'isUnique'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (isUnique)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'isUnique'. */
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

		/* Iteration over third element (3) */

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
	public void testRejectIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/reject01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
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

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
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

		/* Debugger after iterator exp 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'reject'. */
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
	public void testRejectIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/reject01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'reject'. */
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
	public void testRejectIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/reject01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
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

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'reject'. */
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
	public void testRejectIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/reject01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'reject'. */
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
	public void testRejectIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/reject01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
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

		/* Debugger at iterator expression 'reject'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (reject)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'reject'. */
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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

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
	public void testSortedByIteratorStepInto01() throws Exception {

		String oclResource = "resources/expressions/iterator/sortedBy01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (3) */

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over second element (2) */

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over third element (3). */

		/* Debugger at variable call 'i'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (i)", debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
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
	public void testSortedByIteratorStepOver01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/sortedBy01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
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
	public void testSortedByIteratorStepOver01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/sortedBy01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (3) */

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(6, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist("i", debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_ELEMENTS, debugger);
		assertVariableExist(OclDebugger.OCL_SORTED_BY_VALUES, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after iterator exp 'sortedBy'. */
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
	public void testSortedByIteratorStepReturn01_01() throws Exception {

		String oclResource = "resources/expressions/iterator/sortedBy01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'sortedBy'. */
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
	public void testSortedByIteratorStepReturn01_02() throws Exception {

		String oclResource = "resources/expressions/iterator/sortedBy01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
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

		/* Debugger at collection literal 'OrderedSet'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(
				CallStackConstants.COLLECTION_LITERAL + " (OrderedSet)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_COLLECTION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Iteration over first element (3) */

		/* Debugger at iterator expression 'sortedBy'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.ITERATOR_EXPRESSION + " (sortedBy)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_ITERATOR_EXPRESSION_RESULT,
				debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after iterator exp 'sortedBy'. */
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
