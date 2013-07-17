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
	public static final String COLLECTION_ITEM = "CollectionItem";
	public static final String COLLECTION_LITERAL = "CollectionLiteralExpression";
	public static final String ENUMERATION_LITERAL = "EnumerationLiteralExpression";
	public static final String EXPRESSION_IN_OCL = "ExpressionInOcl";
	public static final String IF_EXPRESSION = "IfExpression";
	public static final String INTEGER_LITERAL = "IntegerLiteralExpression";
	public static final String INVALID_LITERAL = "InvalidLiteralExpression";
	public static final String ITERATOR_EXPRESSION = "IteratorExpression";
	public static final String LET_EXPRESSION = "LetExpression";
	public static final String OPERATION_CALL = "OperationCallExpression";
	public static final String PROPERTY_CALL = "PropertyCallExpression";
	public static final String REAL_LITERAL = "RealLiteralExpression";
	public static final String STRING_LITERAL = "StringLiteralExpression";
	public static final String TYPE_LITERAL = "TypeLiteralExpression";
	public static final String TUPLE_LITERAL = "TupleLiteralExpression";
	public static final String TUPLE_LITERAL_PART = "TupleLiteralPart";
	public static final String UNDEFINED_LITERAL = "UndefinedLiteralExpression";
	public static final String VARIABLE_CALL = "VariableExpression";

}
