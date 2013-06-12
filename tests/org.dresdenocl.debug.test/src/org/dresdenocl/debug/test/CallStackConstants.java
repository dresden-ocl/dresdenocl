package org.dresdenocl.debug.test;

import org.dresdenocl.pivotmodel.Expression;

/**
 * Constants representing names of specific {@link Expression}s on the debuggers
 * call stack.
 * 
 * @author Claas Wilke
 */
public interface CallStackConstants {

	public static final String BOOLEAN_LITERAL = "BooleanLiteralExpression";
	public static final String ENUMERATION_LITERAL = "EnumerationLiteralExpression";
	public static final String EXPRESSION_IN_OCL = "ExpressionInOcl";
	public static final String INTEGER_LITERAL = "IntegerLiteralExpression";
	public static final String INVALID_LITERAL = "InvalidLiteralExpression";
	public static final String NON_STATIC_OPERATION = "evaluateNonStaticOperation > ( OperationCallExpImpl )";
	public static final String REAL_LITERAL = "RealLiteralExpression";
	public static final String STRING_LITERAL = "StringLiteralExpression";
	public static final String TYPE_LITERAL = "TypeLiteralExpression";
	public static final String TUPLE_LITERAL = "TupleLiteralExpression";
	public static final String UNDEFINED_LITERAL = "UndefinedLiteralExpression";

}
