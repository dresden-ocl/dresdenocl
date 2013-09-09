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
public class TestDebugConstraintKinds extends AbstractDebuggerTest {

	@BeforeClass
	public static void setUp() throws Exception {

		AbstractDebuggerTest.setUp();
	}

	@Test
	public void testDefinitionStepInto01() throws Exception {

		String oclResource = "resources/constraintkind/def/def01.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);

		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepOver01() throws Exception {

		String oclResource = "resources/constraintkind/def/def01.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);

		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepReturn01() throws Exception {

		String oclResource = "resources/constraintkind/def/def01.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);

		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);

		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepInto02() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def02.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepOver02() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def02.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepReturn02() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def02.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepInto03() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def03.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepOver03() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def03.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepReturn03() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def03.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepInto04() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def04.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_INTO, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepOver04() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def04.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_OVER, DebugEvent.SUSPENDED, debugger);
	
		/* Debugger after integer exp '42'. */
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
	public void testDefinitionStepReturn04() throws Exception {
	
		String oclResource = "resources/constraintkind/def/def04.ocl";
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
		assertStackName(CallStackConstants.INTEGER_LITERAL + " (42)", debugger);
		assertVariableNumber(1, debugger);
		assertVariableExist(OclDebugger.SELF_VARIABLE_NAME, debugger);
	
		debugStepAndWaitFor(DebugStep.STEP_RETURN, DebugEvent.SUSPENDED,
				debugger);
	
		/* Debugger after integer exp '42'. */
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
	
	// TODO Test calls of definitions from other constraints.
}
