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
public class TestDebugPropertyCallExpressions extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testPropertyCallExpressionStepInto01() throws Exception {

		String oclResource = "resources/expressions/calls/property01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
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

		/* Debugger after property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepInto02() throws Exception {

		String oclResource = "resources/expressions/calls/property02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'staticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (staticInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger at type literal call 'TestClass'. */
		assertCurrentLine(5, debugger);
		assertStackSize(3, debugger);
		assertStackName(CallStackConstants.TYPE_LITERAL
				+ " (resource::package01::TestClass)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'staticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (staticInteger)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepInto03() throws Exception {

		String oclResource = "resources/expressions/calls/property03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
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

		/* Debugger after property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepOver01() throws Exception {

		String oclResource = "resources/expressions/calls/property01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepOver02() throws Exception {

		String oclResource = "resources/expressions/calls/property02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'staticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (staticInteger)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'staticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (staticInteger)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepOver03() throws Exception {

		String oclResource = "resources/expressions/calls/property03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		/* The source of the property call should be on the stack. */
		/* The result of the property call should be on the stack. */
		assertVariableNumber(3, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testPropertyCallExpressionStepReturn01() throws Exception {

		String oclResource = "resources/expressions/calls/property01.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		assertVariableNumber(1, debugger);
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
	public void testPropertyCallExpressionStepReturn02() throws Exception {

		String oclResource = "resources/expressions/calls/property02.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'staticInteger'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (staticInteger)",
				debugger);
		assertVariableNumber(1, debugger);
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
	public void testPropertyCallExpressionStepReturn03() throws Exception {

		String oclResource = "resources/expressions/calls/property03.ocl";
		OclDebugger debugger = generateDebugger(oclResource);
		waitForEvent(DebugEvent.STARTED);
		waitForEvent(DebugEvent.SUSPENDED);

		/* Start debugging. */
		File resourceFile = getFile(oclResource, DebugTestPlugin.PLUGIN_ID);
		debugger.addLineBreakPoint(resourceFile.getAbsolutePath(), 5);
		debugStepAndWaitFor(DebugStep.RESUME, DebugEvent.SUSPENDED, debugger);

		/* Debugger at property call 'integer'. */
		assertCurrentLine(5, debugger);
		assertStackSize(2, debugger);
		assertStackName(CallStackConstants.PROPERTY_CALL + " (integer)",
				debugger);
		assertVariableNumber(1, debugger);
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
	public void testRecursivePropertyCallExpressionStepInto01()
			throws Exception {

		String oclResource = "resources/expressions/calls/recProperty01.ocl";
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testRecursivePropertyCallExpressionStepOver01_01()
			throws Exception {
	
		String oclResource = "resources/expressions/calls/recProperty01.ocl";
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT,
				debugger);
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
	public void testRecursivePropertyCallExpressionStepOver01_02()
			throws Exception {
	
		String oclResource = "resources/expressions/calls/recProperty01.ocl";
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
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
	public void testRecursivePropertyCallExpressionStepReturn01_01()
			throws Exception {
	
		String oclResource = "resources/expressions/calls/recProperty01.ocl";
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(2, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
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
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
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
	public void testRecursivePropertyCallExpressionStepReturn01_02()
			throws Exception {
	
		String oclResource = "resources/expressions/calls/recProperty01.ocl";
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
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
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
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after property call 'numberOfParents'. */
		assertCurrentLine(7, debugger);
		assertStackSize(4, debugger);
		assertStackName(
				CallStackConstants.PROPERTY_CALL + " (numberOfParents)",
				debugger);
		assertVariableNumber(4, debugger);
		assertVariableExist(OclDebugger.OCL_CALL_SOURCE_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.OCL_PROPERTY_CALL_RESULT, debugger);
		assertVariableExist(OclDebugger.OCL_IF_CONDITION_RESULT_VATRIABLE_NAME,
				debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
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
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED, debugger);
	
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
