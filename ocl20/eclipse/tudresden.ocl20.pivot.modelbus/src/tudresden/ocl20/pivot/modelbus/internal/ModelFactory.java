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
package tudresden.ocl20.pivot.modelbus.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionsFactory;
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
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelFactory;
import tudresden.ocl20.pivot.modelbus.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * Standard implementation of the {@link IModelFactory} interface which relies on a given
 * {@link IModel} instance to find types in the model.
 * 
 * @author Matthias Braeuer
 * @version 1.0 10.04.2007
 */
public class ModelFactory implements IModelFactory {

  // logger for this class
  private static final Logger logger = ModelBusPlugin.getLogger(ModelFactory.class);

  // the model which is the basis for OCL expressions created by this factory
  private IModel model;

  // the OCL Library instance; will be retrieved from the IOclLibraryProvider of the model
  private OclLibrary oclLibrary;

  /**
   * @param model
   */
  public ModelFactory(IModel model) {
    if (logger.isDebugEnabled()) {
      logger.debug("ModelFactory(model=" + model + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // usually this would not happen unless clients create model factories themselves
    if (model == null) {
      throw new IllegalArgumentException("No valid model provided to the Model Factory."); //$NON-NLS-1$
    }

    // initialize
    this.model = model;

    if (logger.isDebugEnabled()) {
      logger.debug("ModelFactory() - exit"); //$NON-NLS-1$
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createBooleanLiteralExp(boolean)
   */
  public BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol) {
    if (logger.isDebugEnabled()) {
      logger.debug("createBooleanLiteralExp(booleanSymbol=" + booleanSymbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    BooleanLiteralExp booleanLiteralExp = ExpressionsFactory.INSTANCE.createBooleanLiteralExp();
    booleanLiteralExp.setBooleanSymbol(booleanSymbol);

    if (logger.isDebugEnabled()) {
      logger.debug("createBooleanLiteralExp() - exit - return value=" + booleanLiteralExp); //$NON-NLS-1$
    }

    return booleanLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createCollectionItem(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public CollectionItem createCollectionItem(OclExpression item) {
    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionItem(item=" + item + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (item == null) {
      throw new IllegalArgumentException("The parameter 'item' must not be null."); //$NON-NLS-1$
    }

    CollectionItem collectionItem = ExpressionsFactory.INSTANCE.createCollectionItem();
    collectionItem.setItem(item);

    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionItem() - exit - return value=" + collectionItem); //$NON-NLS-1$
    }

    return collectionItem;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createCollectionLiteralExp(java.lang.String,
   *      java.util.List)
   */
  public CollectionLiteralExp createCollectionLiteralExp(CollectionKind kind,
      CollectionLiteralPart... parts) {
    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionLiteralExp(kind=" + kind + ", parts=" //$NON-NLS-1$ //$NON-NLS-2$
          + ArrayUtils.toString(parts) + ") - enter"); //$NON-NLS-1$
    }

    if (kind == null || parts == null) {
      throw new IllegalArgumentException(
          "Parameters must not be null: kind=" + kind + ", parts=" + parts + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    CollectionLiteralExp collectionLiteralExp = ExpressionsFactory.INSTANCE
        .createCollectionLiteralExp();
    collectionLiteralExp.setKind(kind);
    collectionLiteralExp.getPart().addAll(Arrays.asList(parts));

    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionLiteralExp() - exit - return value=" + collectionLiteralExp); //$NON-NLS-1$
    }

    return collectionLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createCollectionRange(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public CollectionRange createCollectionRange(OclExpression first, OclExpression last) {
    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionRange(first=" + first + ", last=" + last + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    if (first == null || last == null) {
      throw new IllegalArgumentException(
          "Parameters must not be null: first=" + first + ", last=" + last + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    CollectionRange collectionRange = ExpressionsFactory.INSTANCE.createCollectionRange();
    collectionRange.setFirst(first);
    collectionRange.setLast(last);

    if (logger.isDebugEnabled()) {
      logger.debug("createCollectionRange() - exit - return value=" + collectionRange); //$NON-NLS-1$
    }

    return collectionRange;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createConstraint(java.lang.String,
   *      tudresden.ocl20.pivot.pivotmodel.ConstraintKind,
   *      tudresden.ocl20.pivot.pivotmodel.Expression,
   *      tudresden.ocl20.pivot.pivotmodel.ConstrainableElement[])
   */
  public Constraint createConstraint(String name, ConstraintKind kind, Expression specification,
      ConstrainableElement... constrainedElement) {

    if (logger.isDebugEnabled()) {
      logger.debug("createConstraint(name=" + name + ", kind=" + kind + ", specification=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + specification + ", constrainedElement=" + ArrayUtils.toString(constrainedElement) //$NON-NLS-1$
          + ") - enter"); //$NON-NLS-1$
    }

    if (kind == null || specification == null || constrainedElement == null) {
      throw new IllegalArgumentException("Parameters must not be null: kind=" + kind //$NON-NLS-1$
          + ", specification=" + specification //$NON-NLS-1$
          + ", constrainedElement=" + ArrayUtils.toString(constrainedElement) + "."); //$NON-NLS-1$//$NON-NLS-2$
    }

    Constraint constraint = PivotModelFactory.INSTANCE.createConstraint();

    constraint.setName(name);
    constraint.setKind(kind);
    constraint.setSpecification(specification);
    constraint.getConstrainedElement().addAll(Arrays.asList(constrainedElement));

    if (logger.isDebugEnabled()) {
      logger.debug("createConstraint() - exit - return value=" + constraint); //$NON-NLS-1$
    }

    return constraint;

  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createEnumLiteralExp(java.util.List)
   */
  public EnumLiteralExp createEnumLiteralExp(List<String> pathName) {
    if (logger.isDebugEnabled()) {
      logger.debug("createEnumLiteralExp(pathName=" + pathName + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (pathName == null || pathName.size() < 2) {
      throw new IllegalArgumentException("The path name '" + pathName //$NON-NLS-1$
          + "' is either null or does not have the minimal number of two segments."); //$NON-NLS-1$
    }

    // separate the enumeration name and the literal name
    String enumLiteralName = pathName.get(pathName.size() - 1);
    pathName = pathName.subList(0,pathName.size() - 1);

    // find the enumeration
    Type enumeration = findType(pathName);

    // check that we found an enumeration
    if (!(enumeration instanceof Enumeration)) {
      throw new IllegalArgumentException("The path name " + pathName //$NON-NLS-1$
          + " does not denote an enumeration."); //$NON-NLS-1$
    }

    EnumerationLiteral enumLiteral = ((Enumeration) enumeration).lookupLiteral(enumLiteralName);

    if (enumLiteral == null) {
      throw new IllegalArgumentException("There is no literal with name '" + enumLiteralName //$NON-NLS-1$
          + "' in enumeration '" + enumeration.getQualifiedName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    EnumLiteralExp enumLiteralExp = ExpressionsFactory.INSTANCE.createEnumLiteralExp();
    enumLiteralExp.setReferredEnumLiteral(enumLiteral);

    if (logger.isDebugEnabled()) {
      logger.debug("createEnumLiteralExp() - exit - return value=" + enumLiteralExp); //$NON-NLS-1$
    }

    return enumLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createExpressionInOcl(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
   */
  public ExpressionInOcl createExpressionInOcl(String body, OclExpression bodyExpression,
      Variable context, Variable result, Variable... parameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("createExpressionInOcl(bodyExpression=" + bodyExpression + ", context=" //$NON-NLS-1$ //$NON-NLS-2$
          + context + ", result=" + result + ", parameter=" + ArrayUtils.toString(parameter) //$NON-NLS-1$ //$NON-NLS-2$
          + ") - enter"); //$NON-NLS-1$
    }

    if (bodyExpression == null || context == null) {
      throw new IllegalArgumentException("Parameters must not be null: bodyExpression=" //$NON-NLS-1$
          + bodyExpression + ", context=" + context + "."); //$NON-NLS-1$//$NON-NLS-2$
    }

    ExpressionInOcl expressionInOcl = ExpressionsFactory.INSTANCE.createExpressionInOcl();

    if (StringUtils.isNotEmpty(body)) {
      expressionInOcl.setBody(body);
    }

    expressionInOcl.setBodyExpression(bodyExpression);
    expressionInOcl.setContext(context);

    if (result != null) {
      expressionInOcl.setResult(result);
    }

    if (parameter != null) {
      expressionInOcl.getParameter().addAll(Arrays.asList(parameter));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createExpressionInOcl() - exit - return value=" + expressionInOcl); //$NON-NLS-1$
    }

    return expressionInOcl;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createIfExp(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public IfExp createIfExp(OclExpression condition, OclExpression thenExpression,
      OclExpression elseExpression) {
    if (logger.isDebugEnabled()) {
      logger.debug("createIfExp(condition=" + condition + ", thenExpression=" + thenExpression //$NON-NLS-1$ //$NON-NLS-2$
          + ", elseExpression=" + elseExpression + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (condition == null || thenExpression == null || elseExpression == null) {
      throw new IllegalArgumentException("Parameters must not be null: condition=" + condition //$NON-NLS-1$
          + "thenExpression = " + thenExpression + ", elseExpression=" + elseExpression + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    IfExp ifExp = ExpressionsFactory.INSTANCE.createIfExp();

    ifExp.setCondition(condition);
    ifExp.setThenExpression(thenExpression);
    ifExp.setElseExpression(elseExpression);

    if (logger.isDebugEnabled()) {
      logger.debug("createIfExp() - exit - return value=" + ifExp); //$NON-NLS-1$
    }
    return ifExp;

  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createIntegerLiteralExp(int)
   */
  public IntegerLiteralExp createIntegerLiteralExp(int integerSymbol) {
    if (logger.isDebugEnabled()) {
      logger.debug("createIntegerLiteralExp(integerSymbol=" + integerSymbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    IntegerLiteralExp integerLiteralExp = ExpressionsFactory.INSTANCE.createIntegerLiteralExp();
    integerLiteralExp.setIntegerSymbol(integerSymbol);

    if (logger.isDebugEnabled()) {
      logger.debug("createIntegerLiteralExp() - exit - return value=" + integerLiteralExp); //$NON-NLS-1$
    }

    return integerLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createInvalidLiteralExp()
   */
  public InvalidLiteralExp createInvalidLiteralExp() {
    if (logger.isDebugEnabled()) {
      logger.debug("createInvalidLiteralExp() - enter"); //$NON-NLS-1$
    }

    InvalidLiteralExp invalidLiteralExp = ExpressionsFactory.INSTANCE.createInvalidLiteralExp();

    if (logger.isDebugEnabled()) {
      logger.debug("createInvalidLiteralExp() - exit - return value=" + invalidLiteralExp); //$NON-NLS-1$
    }

    return invalidLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createIterateExp(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      java.lang.String, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
   */
  public IterateExp createIterateExp(OclExpression source, OclExpression body, Variable result,
      Variable... iterator) {
    if (logger.isDebugEnabled()) {
      logger.debug("createIterateExp(source=" + source + ", body=" + body + ", result=" + result //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ", iterator=" + ArrayUtils.toString(iterator) + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (source == null || body == null || result == null) {
      throw new IllegalArgumentException("Parameters must not be null: source=" + source //$NON-NLS-1$
          + ", body=" + body + ", result=" + result + "."); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    }

    IterateExp iterateExp = ExpressionsFactory.INSTANCE.createIterateExp();

    iterateExp.setSource(source);
    iterateExp.setBody(body);
    iterateExp.setResult(result);

    if (iterator != null) {
      iterateExp.getIterator().addAll(Arrays.asList(iterator));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createIterateExp() - exit - return value=" + iterateExp); //$NON-NLS-1$
    }
    return iterateExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createIteratorExp(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      java.lang.String, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
   */
  public IteratorExp createIteratorExp(OclExpression source, String name, OclExpression body,
      Variable... iterator) {

    if (logger.isDebugEnabled()) {
      logger.debug("createIteratorExp(source=" + source + ", name=" + name + ", body=" + body //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ", iterator=" + ArrayUtils.toString(iterator) + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (source == null || StringUtils.isEmpty(name) || body == null) {
      throw new IllegalArgumentException("Parameters must not be null or empty: source=" + source //$NON-NLS-1$
          + ", name=" + name + ", body=" + body + "."); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    }

    IteratorExp iteratorExp = ExpressionsFactory.INSTANCE.createIteratorExp();

    iteratorExp.setSource(source);
    iteratorExp.setName(name);
    iteratorExp.setBody(body);

    if (iterator != null) {
      iteratorExp.getIterator().addAll(Arrays.asList(iterator));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createIteratorExp() - exit - return value=" + iteratorExp); //$NON-NLS-1$
    }

    return iteratorExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createLetExp(tudresden.ocl20.pivot.essentialocl.expressions.Variable,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public LetExp createLetExp(Variable variable, OclExpression in) {
    if (logger.isDebugEnabled()) {
      logger.debug("createLetExp(variable=" + variable + ", in=" + in + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    if (variable == null || in == null) {
      throw new IllegalArgumentException("Parameters must not be null: variable=" + variable //$NON-NLS-1$
          + ", in=" + in + "."); //$NON-NLS-1$//$NON-NLS-2$
    }

    LetExp letExp = ExpressionsFactory.INSTANCE.createLetExp();

    letExp.setVariable(variable);
    letExp.setIn(in);

    if (logger.isDebugEnabled()) {
      logger.debug("createLetExp() - exit - return value=" + letExp); //$NON-NLS-1$
    }

    return letExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createOperationCallExp(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      java.lang.String, java.util.List, java.util.List)
   */
  public OperationCallExp createOperationCallExp(OclExpression source,
      String referredOperationName, OclExpression... argument) {

    if (logger.isDebugEnabled()) {
      logger.debug("createOperationCallExp(source=" + source + ", referredOperationName=" //$NON-NLS-1$ //$NON-NLS-2$
          + referredOperationName + ", argument=" + ArrayUtils.toString(argument) + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (source == null || StringUtils.isEmpty(referredOperationName)) {
      throw new IllegalArgumentException("Parameters must not be null or empty: source=" + source //$NON-NLS-1$
          + ", referredOperationName=" + referredOperationName + ","); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // collect the parameter types
    List<Type> paramTypes = new ArrayList<Type>();

    if (argument != null) {
      for (int i = 0; i < argument.length; i++) {
        paramTypes.add(argument[i].getType());
      }
    }

    // lookup the operation
    Operation operation = source.getType().lookupOperation(referredOperationName,paramTypes);

    if (operation == null) {
      throw new IllegalArgumentException("Unable to find operation '" + referredOperationName //$NON-NLS-1$
          + "' with argument types " + paramTypes + " in type '" //$NON-NLS-1$ //$NON-NLS-2$
          + source.getType().getQualifiedName() + "'."); //$NON-NLS-1$
    }

    OperationCallExp operationCallExp = ExpressionsFactory.INSTANCE.createOperationCallExp();
    operationCallExp.setSource(source);
    operationCallExp.setReferredOperation(operation);

    if (argument != null) {
      operationCallExp.getArgument().addAll(Arrays.asList(argument));
    }
    
    // a property call expression needs access to the OCL library for determining its type
    operationCallExp.setOclLibrary(getOclLibrary());

    if (logger.isDebugEnabled()) {
      logger.debug("createOperationCallExp() - exit - return value=" + operationCallExp); //$NON-NLS-1$
    }

    return operationCallExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createOperationCallExp(java.util.List,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression[])
   */
  public OperationCallExp createOperationCallExp(List<String> pathName, OclExpression... argument) {
    if (logger.isDebugEnabled()) {
      logger.debug("createOperationCallExp(pathName=" + pathName //$NON-NLS-1$
          + ", argument=" + ArrayUtils.toString(argument) + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (pathName == null || pathName.size() < 2) {
      throw new IllegalArgumentException("The static operation path name '" + pathName //$NON-NLS-1$
          + "' is either null or does not have the required number of at least two segments."); //$NON-NLS-1$
    }

    // split the pathname into the type and operation part
    String referredOperation = pathName.get(pathName.size() - 1);
    pathName = pathName.subList(0,pathName.size() - 1);

    // lookup the type
    Type owningType = findType(pathName);

    // collect the parameter types
    List<Type> paramTypes = new ArrayList<Type>();

    if (argument != null) {
      for (int i = 0; i < argument.length; i++) {
        paramTypes.add(argument[i].getType());
      }
    }

    // lookup the operation
    Operation operation = owningType.lookupOperation(referredOperation,paramTypes);

    if (operation == null || !operation.isStatic()) {
      throw new IllegalArgumentException("Unable to find a static operation '" + referredOperation //$NON-NLS-1$
          + "' with argument types " + paramTypes + "' in type " //$NON-NLS-1$ //$NON-NLS-2$
          + owningType.getQualifiedName() + "."); //$NON-NLS-1$
    }

    // create the expression
    OperationCallExp operationCallExp = ExpressionsFactory.INSTANCE.createOperationCallExp();
    operationCallExp.setReferredOperation(operation);
    
    if (argument != null) {
      operationCallExp.getArgument().addAll(Arrays.asList(argument));
    }

    // a property call expression needs access to the OCL library for determining its type
    operationCallExp.setOclLibrary(getOclLibrary());

    if (logger.isDebugEnabled()) {
      logger.debug("createOperationCallExp() - exit - return value=" + operationCallExp); //$NON-NLS-1$
    }

    return operationCallExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createPropertyCallExp(tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
   *      java.lang.String)
   */
  public PropertyCallExp createPropertyCallExp(OclExpression source, String referredPropertyName,
      OclExpression... qualifier) {
    if (logger.isDebugEnabled()) {
      logger.debug("createPropertyCallExp(source=" + source + ", referredPropertyName=" //$NON-NLS-1$ //$NON-NLS-2$
          + referredPropertyName + ", qualifier=" + ArrayUtils.toString(qualifier) + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
    }

    if (source == null || StringUtils.isEmpty(referredPropertyName)) {
      throw new IllegalArgumentException("Parameters must not be null or empty: source=" + source //$NON-NLS-1$
          + ", referredPropertyName=" + referredPropertyName + "."); //$NON-NLS-1$//$NON-NLS-2$
    }

    // lookup the property
    Property property = source.getType().lookupProperty(referredPropertyName);

    if (property == null) {
      throw new IllegalArgumentException("Unable to find property '" + referredPropertyName //$NON-NLS-1$
          + "' in type '" + source.getType().getQualifiedName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    PropertyCallExp propertyCallExp = ExpressionsFactory.INSTANCE.createPropertyCallExp();

    propertyCallExp.setSource(source);
    propertyCallExp.setReferredProperty(property);

    // a property call expression needs access to the OCL library for determining its type
    propertyCallExp.setOclLibrary(getOclLibrary());

    if (qualifier != null) {
      propertyCallExp.getQualifier().addAll(Arrays.asList(qualifier));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createPropertyCallExp() - exit - return value=" + propertyCallExp); //$NON-NLS-1$
    }

    return propertyCallExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createPropertyCallExp(java.util.List,
   *      tudresden.ocl20.pivot.essentialocl.expressions.OclExpression[])
   */
  public PropertyCallExp createPropertyCallExp(List<String> pathName, OclExpression... qualifier) {
    if (logger.isDebugEnabled()) {
      logger.debug("createPropertyCallExp(pathName=" + pathName //$NON-NLS-1$
          + ", qualifier=" + ArrayUtils.toString(qualifier) + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (pathName == null || pathName.size() < 2) {
      throw new IllegalArgumentException("The static property path name '" + pathName //$NON-NLS-1$
          + "' is either null or does not have the required number of at least two segments."); //$NON-NLS-1$
    }

    // split the path name into the type name and the property name
    String referredProperty = pathName.get(pathName.size() - 1);
    pathName = pathName.subList(0,pathName.size() - 1);

    // lookup the type
    Type owningType = findType(pathName);

    // lookup the property
    Property property = owningType.lookupProperty(referredProperty);

    if (property == null || !property.isStatic()) {
      throw new IllegalArgumentException("Unable to find a static property '" + referredProperty //$NON-NLS-1$
          + "' in type " + owningType.getQualifiedName() + "."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // create the expression
    PropertyCallExp propertyCallExp = ExpressionsFactory.INSTANCE.createPropertyCallExp();
    propertyCallExp.setReferredProperty(property);
    
    // a property call expression needs access to the OCL library for determining its type
    propertyCallExp.setOclLibrary(getOclLibrary());

    if (qualifier != null) {
      propertyCallExp.getQualifier().addAll(Arrays.asList(qualifier));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createPropertyCallExp() - exit - return value=" + propertyCallExp); //$NON-NLS-1$
    }

    return propertyCallExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createRealLiteralExp(float)
   */
  public RealLiteralExp createRealLiteralExp(float realSymbol) {
    if (logger.isDebugEnabled()) {
      logger.debug("createRealLiteralExp(realSymbol=" + realSymbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    RealLiteralExp realLiteralExp = ExpressionsFactory.INSTANCE.createRealLiteralExp();
    realLiteralExp.setRealSymbol(realSymbol);

    if (logger.isDebugEnabled()) {
      logger.debug("createRealLiteralExp() - exit - return value=" + realLiteralExp); //$NON-NLS-1$
    }

    return realLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createStringLiteralExp(java.lang.String)
   */
  public StringLiteralExp createStringLiteralExp(String stringSymbol) {
    if (logger.isDebugEnabled()) {
      logger.debug("createStringLiteralExp(stringSymbol=" + stringSymbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (stringSymbol == null) {
      throw new IllegalArgumentException("The argument 'stringSymbol' must not be null."); //$NON-NLS-1$
    }

    StringLiteralExp stringLiteralExp = ExpressionsFactory.INSTANCE.createStringLiteralExp();
    stringLiteralExp.setStringSymbol(stringSymbol);

    if (logger.isDebugEnabled()) {
      logger.debug("createStringLiteralExp() - exit - return value=" + stringLiteralExp); //$NON-NLS-1$
    }

    return stringLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createTupleLiteralExp(java.util.List)
   */
  public TupleLiteralExp createTupleLiteralExp(TupleLiteralPart... parts) {
    if (logger.isDebugEnabled()) {
      logger.debug("createTupleLiteralExp(parts=" + ArrayUtils.toString(parts) + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    TupleLiteralExp tupleLiteralExp = ExpressionsFactory.INSTANCE.createTupleLiteralExp();

    if (parts != null) {
      tupleLiteralExp.getPart().addAll(Arrays.asList(parts));
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createTupleLiteralExp() - exit - return value=" + tupleLiteralExp); //$NON-NLS-1$
    }

    return tupleLiteralExp;

  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createTupleLiteralPart(java.lang.String,
   *      java.lang.String, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public TupleLiteralPart createTupleLiteralPart(String name, List<String> typeName,
      OclExpression value) {
    if (logger.isDebugEnabled()) {
      logger.debug("createTupleLiteralPart(name=" + name + ", typeName=" + typeName + ", value=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + value + ") - enter"); //$NON-NLS-1$
    }

    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("The argument 'name' must not be null or empty."); //$NON-NLS-1$
    }

    Type type = null;

    // if a type name has been defined try to find the corresponding type
    if (typeName != null && typeName.size() > 0) {
      type = findType(typeName);
    }

    // if no type is given, we need a value to infer the type (in TupleLiteralPart.getType)
    else if (value == null) {
      throw new IllegalArgumentException(
          "The value of the TupleLiteralPart must not be null if no type name is provided."); //$NON-NLS-1$
    }

    TupleLiteralPart part = ExpressionsFactory.INSTANCE.createTupleLiteralPart();
    part.setName(name);

    if (type != null) {
      part.setType(type);
    }

    if (value != null) {
      part.setValue(value);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createTupleLiteralPart() - exit - return value=" + part); //$NON-NLS-1$
    }

    return part;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createTypeLiteralExp(java.util.List)
   */
  public TypeLiteralExp createTypeLiteralExp(List<String> referredTypeName) {
    if (logger.isDebugEnabled()) {
      logger.debug("createTypeLiteralExp(referredTypeName=" + referredTypeName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (referredTypeName == null || referredTypeName.size() == 0) {
      throw new IllegalArgumentException(
          "The argument 'referredTypeName' must not be null or empty."); //$NON-NLS-1$
    }

    Type type = findType(referredTypeName);

    TypeLiteralExp typeLiteralExp = ExpressionsFactory.INSTANCE.createTypeLiteralExp();
    typeLiteralExp.setReferredType(type);

    if (logger.isDebugEnabled()) {
      logger.debug("createTypeLiteralExp() - exit - return value=" + typeLiteralExp); //$NON-NLS-1$
    }

    return typeLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createUndefinedLiteralExp()
   */
  public UndefinedLiteralExp createUndefinedLiteralExp() {
    if (logger.isDebugEnabled()) {
      logger.debug("createUndefinedLiteralExp() - enter"); //$NON-NLS-1$
    }

    UndefinedLiteralExp undefinedLiteralExp = ExpressionsFactory.INSTANCE
        .createUndefinedLiteralExp();

    if (logger.isDebugEnabled()) {
      logger.debug("createUndefinedLiteralExp() - exit - return value=" + undefinedLiteralExp); //$NON-NLS-1$
    }

    return undefinedLiteralExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createUnlimitedNaturalExp(java.lang.String)
   */
  public UnlimitedNaturalExp createUnlimitedNaturalExp(String symbol) {
    if (logger.isDebugEnabled()) {
      logger.debug("createUnlimitedNaturalExp(symbol=" + symbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (StringUtils.isEmpty(symbol)) {
      throw new IllegalArgumentException("The argument 'symbol' must not be null or empty."); //$NON-NLS-1$
    }

    UnlimitedNaturalExp unlimitedNaturalExp = ExpressionsFactory.INSTANCE
        .createUnlimitedNaturalExp();
    unlimitedNaturalExp.setSymbol(symbol);

    if (logger.isDebugEnabled()) {
      logger.debug("createUnlimitedNaturalExp() - exit - return value=" + unlimitedNaturalExp); //$NON-NLS-1$
    }

    return unlimitedNaturalExp;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createVariable(java.lang.String,
   *      java.util.List, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
   */
  public Variable createVariable(String name, List<String> typeName, OclExpression initExpression) {
    if (logger.isDebugEnabled()) {
      logger.debug("createVariable(name=" + name + ", typeName=" + typeName + ", initExpression=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + initExpression + ") - enter"); //$NON-NLS-1$
    }

    if (StringUtils.isEmpty(name)) {
      throw new IllegalArgumentException("The 'name' argument must not be null or empty."); //$NON-NLS-1$
    }

    Type type = null;

    // if the type name is given lookup the type
    if (typeName != null && typeName.size() > 0) {
      type = findType(typeName);
    }

    // else check whether an init expression exists to infer the type (in Variable.getType)
    else if (initExpression == null) {
      throw new IllegalArgumentException(
          "The init expression must not be null if no type name is given."); //$NON-NLS-1$
    }

    Variable variable = ExpressionsFactory.INSTANCE.createVariable();
    variable.setName(name);

    if (type != null) {
      variable.setType(type);
    }

    if (initExpression != null) {
      variable.setInitExpression(initExpression);
    }

    if (logger.isDebugEnabled()) {
      logger.debug("createVariable() - exit - return value=" + variable); //$NON-NLS-1$
    }

    return variable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createVariable(tudresden.ocl20.pivot.pivotmodel.Parameter)
   */
  public Variable createVariable(Parameter representedParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("createVariable(representedParameter=" + representedParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (representedParameter == null) {
      throw new IllegalArgumentException("The represented parameter must not be null."); //$NON-NLS-1$
    }

    Variable variable = ExpressionsFactory.INSTANCE.createVariable();
    variable.setRepresentedParameter(representedParameter);

    if (logger.isDebugEnabled()) {
      logger.debug("createVariable() - exit - return value=" + variable); //$NON-NLS-1$
    }

    return variable;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createVariableExp(tudresden.ocl20.pivot.essentialocl.expressions.Variable)
   */
  public VariableExp createVariableExp(Variable referredVariable) {
    if (logger.isDebugEnabled()) {
      logger.debug("createVariableExp(referredVariable=" + referredVariable + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    if (referredVariable == null) {
      throw new IllegalArgumentException("The referred variable must not be null."); //$NON-NLS-1$
    }

    VariableExp variableExp = ExpressionsFactory.INSTANCE.createVariableExp();
    variableExp.setReferredVariable(referredVariable);
    
    // a variable expression needs access to the OCL library for determining its type
    variableExp.setOclLibrary(getOclLibrary());

    if (logger.isDebugEnabled()) {
      logger.debug("createVariableExp() - exit - return value=" + variableExp); //$NON-NLS-1$
    }

    return variableExp;

  }

  /**
   * Helper method that looks up a {@link Type} in the acciated model.
   * 
   * @param pathName the path name of the type to look for
   * 
   * @return a <code>Type</code> instance
   * 
   * @throws IllegalStateException if the model cannot be accessed
   * @throws IllegalArgumentException if no type with that path name can be found
   */
  protected Type findType(List<String> pathName) {
    Type type;

    try {
      type = model.findType(pathName);
    }
    catch (ModelAccessException e) {
      throw new IllegalStateException("An error occured when accessing model '" //$NON-NLS-1$
          + model.getDisplayName() + "'.",e); //$NON-NLS-1$
    }

    if (type == null) {
      throw new IllegalArgumentException("Unable to find type '" + pathName + "'."); //$NON-NLS-1$//$NON-NLS-2$
    }

    return type;
  }

  /**
   * Helper method to lazily get the OCL Library Provider
   */
  protected OclLibrary getOclLibrary() {
    
    if (oclLibrary == null) {
      IOclLibraryProvider provider = model.getOclLibraryProvider();

      if (provider == null) {
        throw new IllegalStateException("Failed to retrieve an OCL Library Provider from model '" //$NON-NLS-1$
            + model.getDisplayName() + "'."); //$NON-NLS-1$
      }
      
      oclLibrary = provider.getOclLibrary();
    }
    
    return oclLibrary;
  }
}
