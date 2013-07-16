package org.dresdenocl.debug.test.unit;

import java.io.File;

import org.dresdenocl.debug.OclDebugger;
import org.dresdenocl.debug.test.AbstractDebuggerTest;
import org.dresdenocl.debug.test.CallStackConstants;
import org.dresdenocl.debug.test.DebugTestPlugin;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Contains test cases testing the debugging of {@link OperationCallExp}s.
 * 
 * @author Claas Wilke
 */
public class TestDebugOperationCallExpressions extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testOperationCallExpressionStepInto01() throws Exception {

		String oclResource = "resources/expressions/calls/operation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (getInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'self'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (getInteger)",
				debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepInto02() throws Exception {

		String oclResource = "resources/expressions/calls/operation02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getStaticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (getStaticInteger)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at type literal 'TestClass'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.TYPE_LITERAL
				+ " (resource::package01::TestClass)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (getStaticInteger)", debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepInto03() throws Exception {

		String oclResource = "resources/expressions/calls/operation03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '42'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		/* oclSource should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* oclSource should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepInto04() throws Exception {

		String oclResource = "resources/expressions/calls/operation04.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at self variable. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '42'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		/* oclSource should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		/* oclSource should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		/* The result of the operation call should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepInto05() throws Exception {

		String oclResource = "resources/expressions/calls/operation05.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at self variable. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '0'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		/* oclSource should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '42'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		/* oclSource should be on the stack. */
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		/* oclSource should be on the stack. */
		/* oclParam1 should be on the stack. */
		/* oclParam2 should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "1",
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + "2",
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		/* The result of the operation call should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 2,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepOver01() throws Exception {

		String oclResource = "resources/expressions/calls/operation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (getInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (getInteger)",
				debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepOver02() throws Exception {

		String oclResource = "resources/expressions/calls/operation02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getStaticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (getStaticInteger)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (getStaticInteger)", debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepOver03() throws Exception {

		String oclResource = "resources/expressions/calls/operation03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		/* The result of the operation call should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepOver04() throws Exception {

		String oclResource = "resources/expressions/calls/operation04.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		/* The result of the operation call should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepOver05() throws Exception {

		String oclResource = "resources/expressions/calls/operation05.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		/* The result of the operation call should be on the stack. */
		/* oclParam1 should be on the stack. */
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 2,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepReturn01() throws Exception {

		String oclResource = "resources/expressions/calls/operation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (getInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepReturn02() throws Exception {

		String oclResource = "resources/expressions/calls/operation02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'getStaticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (getStaticInteger)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepReturn03() throws Exception {

		String oclResource = "resources/expressions/calls/operation03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepReturn04() throws Exception {

		String oclResource = "resources/expressions/calls/operation04.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (isInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call expression. */
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
	public void testOperationCallExpressionStepReturn05() throws Exception {

		String oclResource = "resources/expressions/calls/operation05.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'isIntegerBetween'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (isIntegerBetween)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call expression. */
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
	public void testRecursiveOperationCallExpressionStepInto01()
			throws Exception {

		String oclResource = "resources/expressions/calls/recOperation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);

		/* Breakpoints on then and else expressions. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 6);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 7);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'self'. */
		assertCurrentLine(7, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at expression in OCL. */
		assertCurrentLine(4, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* Only self should be on the stack. */
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at if expression. */
		assertCurrentLine(5, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.IF_EXPRESSION, debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call 'oclIsUndefined'. */
		assertCurrentLine(5, debugger);
		assertStackSize(7, debugger);
		assertStackName(
				CallStackConstants.OPERATION_CALL + " (oclIsUndefined)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'parent'. */
		assertCurrentLine(5, debugger);
		assertStackSize(8, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'self'. */
		assertCurrentLine(5, debugger);
		assertStackSize(9, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'parent'. */
		assertCurrentLine(5, debugger);
		assertStackSize(8, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call 'oclIsUndefined'. */
		assertCurrentLine(5, debugger);
		assertStackSize(7, debugger);
		assertStackName(
				CallStackConstants.OPERATION_CALL + " (oclIsUndefined)",
				debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '0'. */
		assertCurrentLine(6, debugger);
		assertStackSize(7, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (0)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after recursive property call 'numberOfParents'. */
		assertCurrentLine(4, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call expression. */
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
	public void testRecursiveOperationCallExpressionStepOver01_01()
			throws Exception {

		String oclResource = "resources/expressions/calls/recOperation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);

		/* Breakpoints on then and else expressions. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 6);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 7);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call expression. */
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
	public void testRecursiveOperationCallExpressionStepOver01_02()
			throws Exception {

		String oclResource = "resources/expressions/calls/recOperation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);

		/* Breakpoints on then and else expressions. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 6);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 7);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'self'. */
		assertCurrentLine(7, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at expression in OCL. */
		assertCurrentLine(4, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* Only self should be on the stack. */
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after recursive property call 'numberOfParents'. */
		assertCurrentLine(4, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_RESULT_VATRIABLE_NAME, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger at integer literal '1'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (1)", debugger);
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call expression. */
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
	public void testRecursiveOperationCallExpressionStepReturn01_01()
			throws Exception {

		String oclResource = "resources/expressions/calls/recOperation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);

		/* Breakpoints on then and else expressions. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 6);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 7);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after property call expression. */
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
	public void testRecursiveOperationCallExpressionStepReturn01_02()
			throws Exception {

		String oclResource = "resources/expressions/calls/recOperation01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);

		/* Breakpoints on then and else expressions. */
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 6);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 7);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at variable call 'self'. */
		assertCurrentLine(7, debugger);
		assertStackSize(6, debugger);
		assertStackName(CallStackConstants.VARIABLE_CALL + " (self)", debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'parent'. */
		assertCurrentLine(7, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (parent)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at expression in OCL. */
		assertCurrentLine(4, debugger);
		assertStackSize(5, debugger);
		assertStackName(CallStackConstants.EXPRESSION_IN_OCL, debugger);
		/* Only self should be on the stack. */
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL
				+ " (numberOfParents)", debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after operation call '+'. */
		assertCurrentLine(7, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.OPERATION_CALL + " (+)", debugger);
		assertVariableNumber(5, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PARAMETER_VALUE_VARIBALE + 1,
				debugger);
		assertVariableExist(OclDebugger.OCL_OPERATION_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after property call expression. */
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
