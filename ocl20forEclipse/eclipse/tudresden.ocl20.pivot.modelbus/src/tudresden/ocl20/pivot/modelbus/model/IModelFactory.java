/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.modelbus.model;

import java.util.List;

import javax.naming.TimeLimitExceededException;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IterateExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.expressions.WellformednessException;
import tudresden.ocl20.pivot.modelbus.model.exception.FactoryException;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class contains many utility methods to create {@link OclExpression}s and
 * their parts. The signatures of the methods reflect the needs of a primitive
 * string-based script engine, but may not necessarily be suited for a real OCL
 * parser. Thus, this interface may be refactored once an OCL parser for the
 * Pivot Model-based architecture is being built.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModelFactory {

	/**
	 * <p>
	 * Creates a {@link BooleanLiteralExp}.
	 * </p>
	 * 
	 * @param booleanSymbol
	 *          The <code>boolean</code> value of the {@link BooleanLiteralExp}.
	 * @return A {@link BooleanLiteralExp} instance.
	 */
	BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol);

	/**
	 * <p>
	 * Creates a {@link CollectionItem}.
	 * </p>
	 * 
	 * @param item
	 *          The {@link OclExpression} of the item.
	 * @return A {@link CollectionItem} instance.
	 */
	CollectionItem createCollectionItem(OclExpression item);

	/**
	 * <p>
	 * Creates a {@link CollectionLiteralExp}.
	 * </p>
	 * 
	 * @param kind
	 *          The {@link CollectionKind} of the {@link CollectionLiteralExp}.
	 * @param parts
	 *          The {@link CollectionLiteralPart}s of the
	 *          {@link CollectionLiteralExp}.
	 * @return A {@link CollectionLiteralExp} instance.
	 */
	CollectionLiteralExp createCollectionLiteralExp(CollectionKind kind,
			CollectionLiteralPart... parts);

	/**
	 * <p>
	 * Creates a {@link CollectionRange}.
	 * </p>
	 * 
	 * @param first
	 *          The {@link OclExpression} for the first element.
	 * @param last
	 *          The {@link OclExpression} for the last element.
	 * @return A {@link CollectionRange} instance.
	 */
	CollectionRange createCollectionRange(OclExpression first, OclExpression last);

	/**
	 * <p>
	 * Creates a new {@link Constraint}. The name is optional. but the other
	 * parameters need to have valid values.
	 * </p>
	 * 
	 * @param name
	 *          An optional name for the {@link Constraint}.
	 * @param kind
	 *          One of the constants defined in {@link ConstraintKind}.
	 * @param specification
	 *          The {@link Expression} that specifies the {@link Constraint}.
	 * @param constrainedElement
	 *          At least one element that is the target of the {@link Constraint}.
	 * 
	 * @return A {@link Constraint} instance.
	 */
	Constraint createConstraint(String name, ConstraintKind kind,
			Expression specification, ConstrainableElement... constrainedElement);

	/**
	 * <p>
	 * Creates a {@link EnumLiteralExp}.
	 * </p>
	 * 
	 * @param referredEnumLiteralPathName
	 *          The fully qualified name of the referred
	 *          {@link EnumerationLiteral} as a {@link String}.
	 * @return A {@link EnumLiteralExp} instance.
	 * @throws FactoryException
	 *           Thrown, if the creation fails.
	 */
	EnumLiteralExp createEnumLiteralExp(List<String> referredEnumLiteralPathName)
			throws FactoryException;

	/**
	 * <p>
	 * Creates a new {@link ExpressionInOcl}. The body expression and the context
	 * variable must not be <code>null</code>. The result and parameter variables
	 * are optional since they are only required for constraints whose context is
	 * an operation.
	 * </p>
	 * 
	 * @param body
	 *          The body expression as a {@link String} in OCL concrete syntax.
	 * @param bodyExpression
	 *          The {@link OclExpression} that is the body of the
	 *          {@link ExpressionInOcl}.
	 * @param context
	 *          The {@link Variable} representing the contextual classifier.
	 * @param result
	 *          The result {@link Variable} of an operation {@link Constraint}.
	 * @param parameter
	 *          The parameters of an operation {@link Constraint}.
	 * 
	 * @return An {@link ExpressionInOcl} instance.
	 */
	ExpressionInOcl createExpressionInOcl(String body,
			OclExpression bodyExpression, Variable context, Variable result,
			Variable... parameter);

	/**
	 * <p>
	 * Creates an {@link IfExp}.
	 * </p>
	 * 
	 * @param condition
	 *          The condition {@link OclExpression}.
	 * @param thenExpression
	 *          The then {@link OclExpression}.
	 * @param elseExpression
	 *          The else {@link OclExpression}.
	 * @return An {@link IfExp} instance.
	 */
	IfExp createIfExp(OclExpression condition, OclExpression thenExpression,
			OclExpression elseExpression);

	/**
	 * <p>
	 * Creates an {@link IntegerLiteralExp}.
	 * </p>
	 * 
	 * @param integerSymbol
	 *          The <code>int</code> value of the {@link IntegerLiteralExp}.
	 * @return An {@link IntegerLiteralExp} instance.
	 */
	IntegerLiteralExp createIntegerLiteralExp(int integerSymbol);

	/**
	 * <p>
	 * Creates an {@link InvalidLiteralExp}.
	 * </p>
	 * 
	 * @return The {@link InvalidLiteralExp} instance.
	 */
	InvalidLiteralExp createInvalidLiteralExp();

	/**
	 * <p>
	 * Creates a new {@link IterateExp}.
	 * </p>
	 * 
	 * @param source
	 *          The source {@link OclExpression}.
	 * @param name
	 *          The name of the {@link IterateExp}.
	 * @param body
	 *          The body {@link OclExpression} of the {@link IterateExp}.
	 * @param result
	 *          The result {@link Variable}.
	 * @param iterator
	 *          The optional iterator {@link Variable}s as an array.
	 * @return The {@link IterateExp} instance.
	 */
	IterateExp createIterateExp(OclExpression source, OclExpression body,
			Variable result, Variable... iterator);

	/**
	 * <p>
	 * Creates a new {@link IteratorExp}.
	 * </p>
	 * 
	 * @param source
	 *          The source {@link OclExpression}.
	 * @param name
	 *          The name of the {@link IteratorExp}.
	 * @param body
	 *          The body {@link OclExpression}.
	 * @param iterator
	 *          The iterator {@link Variable}s as an array.
	 * 
	 * @return A {@link IteratorExp} instance.
	 */
	IteratorExp createIteratorExp(OclExpression source, String name,
			OclExpression body, Variable... iterator);

	/**
	 * <p>
	 * Creates a {@link LetExp} instance.
	 * </p>
	 * 
	 * @param variable
	 *          The {@link Variable} of the {@link LetExp}.
	 * @param in
	 *          The {@link OclExpression} of the {@link LetExp}.
	 * @return A {@link LetExp} instance.
	 */
	LetExp createLetExp(Variable variable, OclExpression in);

	/**
	 * <p>
	 * Creates a new {@link OperationCallExp}.
	 * </p>
	 * 
	 * @param source
	 *          The source {@link OclExpression} of the {@link OperationCallExp}.
	 * @param referredOperationName
	 *          The fully qualified name of the operation (i.e., including the
	 *          fully qualified name of its owning {@link Type}).
	 * @param argument
	 *          An optional list of arguments as {@link OclExpression}s.
	 * 
	 * @return The created {@link OperationCallExp}.
	 */
	OperationCallExp createOperationCallExp(OclExpression source,
			String referredOperationName, OclExpression... argument);

	/**
	 * <p>
	 * Creates a new {@link OperationCallExp} for a static operation. The
	 * <code>referredOperationPathName</code> must not be <code>null</code>, the
	 * arguments are optional. The owning type must exist in the associated
	 * {@link IModel model} and the specified operation must be static.
	 * </p>
	 * 
	 * @param referredOperationPathName
	 *          The fully qualified name of the operation (i.e., including the
	 *          fully qualified name of its owning {@link Type}).
	 * @param argument
	 *          An optional list of arguments as {@link OclExpression}s.
	 * 
	 * @return An {@link OperationCallExp} instance.
	 * 
	 * @throws FactoryException
	 *           If the expression cannot be created.
	 */
	OperationCallExp createOperationCallExp(
			List<String> referredOperationPathName, OclExpression... argument)
			throws FactoryException;

	/**
	 * <p>
	 * Creates a {@link PropertyCallExp}.
	 * </p>
	 * 
	 * @param source
	 *          The source {@link OclExpression} of the {@link PropertyCallExp}.
	 * @param referredPropertyName
	 *          The referred property's name as a {@link String}.
	 * @param qualifier
	 *          qualifier {@link OclExpression} as an Array.
	 * 
	 * @return A {@link PropertyCallExp} instance.
	 */
	PropertyCallExp createPropertyCallExp(OclExpression source,
			String referredPropertyName, OclExpression... qualifier);

	/**
	 * <p>
	 * Creates a {@link PropertyCallExp}.
	 * </p>
	 * 
	 * @param referredPropertyPathName
	 *          The referred property's name as a {@link String}.
	 * @param qualifier
	 *          qualifier {@link OclExpression} as an Array.
	 * @return A {@link PropertyCallExp} instance.
	 * @throws FactoryException
	 *           Thrown, if the creation fails.
	 */
	PropertyCallExp createPropertyCallExp(List<String> referredPropertyPathName,
			OclExpression... qualifier) throws FactoryException;

	/**
	 * <p>
	 * Creates a {@link RealLiteralExp}.
	 * </p>
	 * 
	 * @param realSymbol
	 *          The <code>float</code> value of the {@link RealLiteralExp}.</p>
	 * @return The {@link RealLiteralExp}.
	 */
	RealLiteralExp createRealLiteralExp(float realSymbol);

	/**
	 * <p>
	 * Creates a {@link StringLiteralExp}.
	 * </p>
	 * 
	 * @param stringSymbol
	 *          The {@link String} value of the {@link StringLiteralExp}.
	 * @return A {@link StringLiteralExp} instance.
	 */
	StringLiteralExp createStringLiteralExp(String stringSymbol);

	/**
	 * <p>
	 * Creates a {@link TupleLiteralExp}.
	 * </p>
	 * 
	 * @param parts
	 *          The {@link TupleLiteralPart}s of the {@link TupleLiteralExp}.
	 * @return A {@link TupleLiteralExp} instance.
	 */
	TupleLiteralExp createTupleLiteralExp(TupleLiteralPart... parts);

	/**
	 * <p>
	 * Creates a new {@link TupleLiteralPart} from a {@link Variable variable
	 * declaration}.
	 * </p>
	 * 
	 * @param variableDeclaration
	 *          The variable declaration for which the tuple literal part should
	 *          be created
	 * 
	 * @return A {@link TupleLiteralPart} instance.
	 */
	TupleLiteralPart createTupleLiteralPart(Variable variableDeclaration)
			throws FactoryException;

	/**
	 * <p>
	 * Creates a {@link TypeLiteralExp}.
	 * </p>
	 * 
	 * @param referredTypePathName
	 *          The fully qualified name of the {@link Type} this
	 *          {@link TimeLimitExceededException} refers to.
	 * 
	 * @return A {@link TypeLiteralExp} instance.
	 * @throws FactoryException
	 *           Thrown, if the creation fails.
	 */
	TypeLiteralExp createTypeLiteralExp(List<String> referredTypePathName)
			throws FactoryException;

	/**
	 * <p>
	 * Creates an {@link UndefinedLiteralExp}.
	 * </p>
	 * 
	 * @return An {@link UndefinedLiteralExp} instance.
	 */
	UndefinedLiteralExp createUndefinedLiteralExp();

	/**
	 * <p>
	 * Creates an {@link UnlimitedNaturalExp}.
	 * </p>
	 * 
	 * @param symbol
	 *          The <code>long</code> value of the {@link UnlimitedNaturalExp}.
	 * @return A {@link UnlimitedNaturalExp} instance.
	 */
	UnlimitedNaturalExp createUnlimitedNaturalExp(long symbol);

	/**
	 * <p>
	 * Creates a new {@link Variable}. The name must not be <code>null</code>.
	 * Type and init expression are optional (OCL Specification, Section 9.3). If
	 * none is given, however, it is likely that a {@link WellformednessException}
	 * will be thrown at a later time when the type is requested. If both a type
	 * and an init expression are given, this method will not check whether the
	 * type of the init expression conforms to the given type. Instead, this will
	 * be checked by the {@link Variable} implementation once the type is accessed
	 * for the first time.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable}.
	 * @param type
	 *          The {@link Type} of the {@link Variable}.
	 * @param initExpression
	 *          An (optional) initialization {@link OclExpression}.
	 * 
	 * @return A {@link Variable} instance.
	 */
	Variable createVariable(String name, Type type, OclExpression initExpression);

	/**
	 * <p>
	 * Creates a new {@link Variable} that represents a {@link Parameter} in an
	 * expression that constrains an {@link Operation}. The name and type of the
	 * {@link Variable} will be determined automatically.
	 * </p>
	 * 
	 * @param representedParameter
	 *          The {@link Parameter} represented by the {@link Variable}.
	 * 
	 * @return A {@link Variable} instance.
	 */
	Variable createVariable(Parameter representedParameter);

	/**
	 * <p>
	 * Creates a {@link VariableExp}.
	 * </p>
	 * 
	 * @param referredVariable
	 *          The referred {@link Variable}.
	 * @return A {@link VariableExp} instance.
	 */
	VariableExp createVariableExp(Variable referredVariable);
}