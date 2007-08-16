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
package tudresden.ocl20.pivot.modelbus;

import java.util.List;

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
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;

/**
 * This class contains many utility methods to create {@link OclExpression}s
 * and their parts. The signatures of the methods reflect the needs of a
 * primitive string-based script engine, but may not necessarily be suited for a
 * real OCL parser. Thus, this interface may be refactored once an OCL parser
 * for the Pivot Model-based architecture is being built.
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface IModelFactory {

  /**
   * Creates a new {@link Constraint}. The name is optional. but the other
   * parameters need to have valid values.
   * 
   * @param name an optional name for the <code>Constraint</code>
   * @param kind one of the constants defined in {@link ConstraintKind}
   * @param specification the <code>Expression</code> that specifies the
   *          <code>Constraint</code>
   * @param constrainedElement at least one element that is the target of the
   *          <code>Constraint</code>
   * 
   * @return a <code>Constraint</code> instance
   */
  Constraint createConstraint(String name, ConstraintKind kind,
      Expression specification, ConstrainableElement... constrainedElement);

  /**
   * Creates a new {@link ExpressionInOcl}. The body expression and the context
   * variable must not be <code>null</code>. The result and parameter
   * variables are optional since they are only required for constraints whose
   * context is an operation.
   * 
   * @param body the body expression as a <code>String</code> in OCL concrete
   *          syntax
   * @param bodyExpression the <code>OclExpresson</code> that is the body of
   *          the <code>ExpressonInOcl</code>
   * @param context the <code>Variable</code> representing the contextual
   *          classifier
   * @param result the result variable of an operation constraint
   * @param parameter the parameters of an operation constraint
   * 
   * @return an <code>ExpressionInOcl</code> instance
   */
  ExpressionInOcl createExpressionInOcl(String body,
      OclExpression bodyExpression, Variable context, Variable result,
      Variable... parameter);

  /**
   * Creates a new {@link Variable}. The name must not be <code>null</code>.
   * Type and init expression are optional (OCL Specification, Section 9.3). If
   * none is given, however, it is likely that a {@link WellformednessException}
   * will be thrown at a later time when the type is requested. If both a type
   * and an init expression are given, this method will not check whether the
   * type of the init expression conforms to the given type. Instead, this will
   * be checked by the <code>Variable</code> implementation once the type is
   * accessed for the first time.
   * 
   * @param name the name of the variable
   * @param typePathName the type of the variable
   * @param initExpression an (optional) initialization expression
   * 
   * @return a <code>Variable</code> instance
   * 
   * @throws FactoryException if the variable cannot be created
   */
  Variable createVariable(String name, List<String> typePathName,
      OclExpression initExpression) throws FactoryException;

  /**
   * Creates a new {@link Variable} with the same semantics as
   * {@link #createVariable(String, List, OclExpression)}, but allows to
   * additionally specify a list of type arguments for the type of the variable.
   * This is required for generic types such as the collection types from the
   * OCL Standard Library.
   * 
   * @param name the name of the variable
   * @param typePathName the type of the variable
   * @param typeArguments a list of type names 8each represented as a list of
   *          path segments)
   * @param initExpression an (optional) initialization expression
   * 
   * @return a <code>Variable</code> instance
   * 
   * @throws FactoryException if the variable cannot be created
   */
  Variable createVariable(String name, List<String> typePathName,
      List<List<String>> typeArguments, OclExpression initExpression)
      throws FactoryException;

  /**
   * Creates a new {@link Variable} that represents a {@link Parameter} in an
   * expression that constrains an {@link Operation}. The name and type of the
   * <code>Variable</code> will be determined automatically.
   * 
   * @param representedParameter the <code>Parameter</code> represented by the
   *          <code>Variable</code>
   * 
   * @return a <code>Variable</code> instance
   */
  Variable createVariable(Parameter representedParameter);

  /**
   * @param source
   * @param referredPropertyName
   * @param qualifier
   * 
   * @return
   */
  PropertyCallExp createPropertyCallExp(OclExpression source,
      String referredPropertyName, OclExpression... qualifier);

  /**
   * @param referredPropertyPathName
   * @param qualifier
   * @return
   * @throws FactoryException
   */
  PropertyCallExp createPropertyCallExp(List<String> referredPropertyPathName,
      OclExpression... qualifier) throws FactoryException;

  /**
   * @param source
   * @param referredOperationName
   * @param argument
   * 
   * @return
   */
  OperationCallExp createOperationCallExp(OclExpression source,
      String referredOperationName, OclExpression... argument);

  /**
   * Creates a new {@link OperationCallExp} for a static operation. The
   * <code>referredOperationPathName</code> must not be <code>null</code>,
   * the arguments are optional. The owning type must exist in the associated
   * {@link IModel model} and the specified operation must be static.
   * 
   * @param referredOperationPathName the fully qualified name of the operation
   *          (i.e., including the fully qualified name of its owning
   *          <code>Type</code>
   * @param argument an optional list of arguments
   * 
   * @return an <code>OperationCallExp</code> instance
   * 
   * @throws FactoryException if the expression cannot be created
   */
  OperationCallExp createOperationCallExp(
      List<String> referredOperationPathName, OclExpression... argument)
      throws FactoryException;

  /**
   * @param source
   * @param name
   * @param body
   * @param result
   * @param iterator
   * @return
   */
  IterateExp createIterateExp(OclExpression source, OclExpression body,
      Variable result, Variable... iterator);

  /**
   * @param source
   * @param name
   * @param body
   * @param iterator
   * 
   * @return
   */
  IteratorExp createIteratorExp(OclExpression source, String name,
      OclExpression body, Variable... iterator);

  /**
   * @param condition
   * @param thenExpression
   * @param elseExpression
   * @return
   */
  IfExp createIfExp(OclExpression condition, OclExpression thenExpression,
      OclExpression elseExpression);

  /**
   * @param variable
   * @param in
   * @return
   */
  LetExp createLetExp(Variable variable, OclExpression in);

  /**
   * @param referredVariable
   * @return
   */
  VariableExp createVariableExp(Variable referredVariable);

  /**
   * @param first
   * @param last
   * @return
   */
  CollectionRange createCollectionRange(OclExpression first, OclExpression last);

  /**
   * @param item
   * @return
   */
  CollectionItem createCollectionItem(OclExpression item);

  /**
   * @param parts
   * @return
   */
  CollectionLiteralExp createCollectionLiteralExp(CollectionKind kind,
      CollectionLiteralPart... parts);

  /**
   * Creates a new {@link TupleLiteralPart} from a
   * {@link Variable variable declaration}.
   * 
   * @param variableDeclaration the variable declaration for which the tuple
   *          literal part should be created
   * 
   * @return a <code>TupleLiteralPart</code> instance
   */
  TupleLiteralPart createTupleLiteralPart(Variable variableDeclaration)
      throws FactoryException;

  /**
   * @param parts
   * @return
   */
  TupleLiteralExp createTupleLiteralExp(TupleLiteralPart... parts);

  /**
   * @param referredEnumLiteralPathName
   * @return
   * @throws FactoryException
   */
  EnumLiteralExp createEnumLiteralExp(List<String> referredEnumLiteralPathName)
      throws FactoryException;

  /**
   * @param realSymbol
   * @return
   */
  RealLiteralExp createRealLiteralExp(float realSymbol);

  /**
   * @param symbol
   * @return
   */
  UnlimitedNaturalExp createUnlimitedNaturalExp(long symbol);

  /**
   * @param integerSymbol
   * @return
   */
  IntegerLiteralExp createIntegerLiteralExp(int integerSymbol);

  /**
   * @param stringSymbol
   * @return
   */
  StringLiteralExp createStringLiteralExp(String stringSymbol);

  /**
   * @param booleanSymbol
   * @return
   */
  BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol);

  /**
   * @return
   */
  UndefinedLiteralExp createUndefinedLiteralExp();

  /**
   * @return
   */
  InvalidLiteralExp createInvalidLiteralExp();

  /**
   * @param referredTypePathName
   * 
   * @return
   * @throws FactoryException
   */
  TypeLiteralExp createTypeLiteralExp(List<String> referredTypePathName)
      throws FactoryException;

}
