package org.dresdenocl.debug.test;

import org.dresdenocl.pivotmodel.Expression;

/**
 * Constants representing names of specific {@link Expression}s on the debuggers
 * call stack.
 * 
 * @author Claas Wilke
 */
public interface CallStackConstants {

	public static final String BOOLEAN_LITERAL = "caseBooleanLiteralExp ( BooleanLiteralExpImpl )";
	public static final String INTEGER_LITERAL = "caseIntegerLiteralExp ( IntegerLiteralExpImpl )";
	public static final String NON_STATIC_OPERATION = "evaluateNonStaticOperation > ( OperationCallExpImpl )";
	public static final String STRING_LITERAL = "caseStringLiteralExp ( StringLiteralExpImpl )";

}
