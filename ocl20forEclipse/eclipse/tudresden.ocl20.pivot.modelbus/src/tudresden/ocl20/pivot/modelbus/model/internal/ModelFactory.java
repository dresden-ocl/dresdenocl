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
package tudresden.ocl20.pivot.modelbus.model.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NullArgumentException;
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
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelFactory;
import tudresden.ocl20.pivot.modelbus.model.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.model.ITypeResolver;
import tudresden.ocl20.pivot.modelbus.model.exception.FactoryException;
import tudresden.ocl20.pivot.modelbus.model.exception.TypeNotFoundException;
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
 * Standard implementation of the {@link IModelFactory} interface which relies
 * on a given {@link IModel} instance to find types in the model.
 * 
 * @author Matthias Braeuer
 * @version 1.0 10.04.2007
 */
public class ModelFactory implements IModelFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(ModelFactory.class);

	/**
	 * The {@link IModel} which is the basis for OCL expressions created by this
	 * factory.
	 */
	private IModel model;

	/** The {@link OclLibrary} instance. */
	private OclLibrary oclLibrary;

	/** The {@link ITypeResolver} used for looking up types. */
	private ITypeResolver typeResolver;

	/**
	 * <p>
	 * Creates a new {@link ModelFactory}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} this {@link ModelFactory} belongs to.
	 */
	public ModelFactory(IModel model) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("ModelFactory(model=" + model + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* should not happen unless clients create model factories themselves. */
		if (model == null) {
			throw new IllegalArgumentException(
					"No valid model provided to the Model Factory."); //$NON-NLS-1$
		}

		/* initialize the reference to the model. */
		this.model = model;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("ModelFactory() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createBooleanLiteralExp(boolean
	 * )
	 */
	public BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createBooleanLiteralExp(booleanSymbol=" + booleanSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		BooleanLiteralExp booleanLiteralExp;

		booleanLiteralExp = ExpressionsFactory.INSTANCE.createBooleanLiteralExp();
		booleanLiteralExp.setBooleanSymbol(booleanSymbol);
		booleanLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createBooleanLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ booleanLiteralExp);
		}

		return booleanLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createCollectionItem(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression)
	 */
	public CollectionItem createCollectionItem(OclExpression item) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionItem(item=" + item + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (item == null) {
			throw new IllegalArgumentException(
					"The parameter 'item' must not be null."); //$NON-NLS-1$
		}

		CollectionItem collectionItem;

		collectionItem = ExpressionsFactory.INSTANCE.createCollectionItem();
		collectionItem.setItem(item);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionItem() - exit - return value=" //$NON-NLS-1$
					+ collectionItem);
		}

		return collectionItem;
	}

	/**
	 * Creates a {@link CollectionLiteralExp}. Both parameters <code>kind</code>
	 * and <code>parts</code> must not be <code>null</code>. However, the
	 * <code>parts</code> varargs array may be empty, in this case an empty
	 * collection will be created.
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.model.IModelFactory#createCollectionLiteralExp(java.lang.String,
	 *      java.util.List)
	 */
	public CollectionLiteralExp createCollectionLiteralExp(CollectionKind kind,
			CollectionLiteralPart... parts) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionLiteralExp(kind=" + kind + ", parts=" //$NON-NLS-1$ //$NON-NLS-2$
					+ ArrayUtils.toString(parts) + ") - enter"); //$NON-NLS-1$
		}

		// check parameters
		if (kind == null || parts == null) {
			throw new IllegalArgumentException(
					"Parameters must not be null: kind=" + kind + ", parts=" + parts + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		CollectionLiteralExp collectionLiteralExp;

		collectionLiteralExp =
				ExpressionsFactory.INSTANCE.createCollectionLiteralExp();
		collectionLiteralExp.setKind(kind);
		collectionLiteralExp.getPart().addAll(Arrays.asList(parts));

		// set the reference to the OCL Library
		collectionLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ collectionLiteralExp);
		}

		return collectionLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createCollectionRange(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
	 */
	public CollectionRange createCollectionRange(OclExpression first,
			OclExpression last) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionRange(first=" + first + ", last=" + last //$NON-NLS-1$ //$NON-NLS-2$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (first == null || last == null) {
			throw new NullArgumentException("first or last"); //$NON-NLS-1$
		}

		CollectionRange collectionRange;

		collectionRange = ExpressionsFactory.INSTANCE.createCollectionRange();
		collectionRange.setFirst(first);
		collectionRange.setLast(last);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionRange() - exit - return value=" //$NON-NLS-1$
					+ collectionRange);
		}

		return collectionRange;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createConstraint(java.lang
	 * .String, tudresden.ocl20.pivot.pivotmodel.ConstraintKind,
	 * tudresden.ocl20.pivot.pivotmodel.Expression,
	 * tudresden.ocl20.pivot.pivotmodel.ConstrainableElement[])
	 */
	public Constraint createConstraint(String name, ConstraintKind kind,
			Expression specification, ConstrainableElement... constrainedElement) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createConstraint(name=" + name + ", kind=" + kind //$NON-NLS-1$ //$NON-NLS-2$
					+ ", specification=" + specification + ", constrainedElement=" //$NON-NLS-1$//$NON-NLS-2$
					+ ArrayUtils.toString(constrainedElement) + ") - enter"); //$NON-NLS-1$
		}

		if (kind == null || specification == null || constrainedElement == null) {
			throw new NullArgumentException(
					"kind or specification or constrainedElement"); //$NON-NLS-1$
		}

		Constraint constraint = PivotModelFactory.INSTANCE.createConstraint();

		constraint.setName(name);
		constraint.setKind(kind);
		constraint.setSpecification(specification);
		constraint.getConstrainedElement()
				.addAll(Arrays.asList(constrainedElement));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createConstraint() - exit - return value=" + constraint); //$NON-NLS-1$
		}

		return constraint;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createEnumLiteralExp(java.
	 * util.List)
	 */
	public EnumLiteralExp createEnumLiteralExp(List<String> pathName)
			throws FactoryException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createEnumLiteralExp(pathName=" + pathName + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
		}

		if (pathName == null || pathName.size() < 2) {
			throw new IllegalArgumentException(
					"The path name '" + pathName //$NON-NLS-1$
							+ "' is either null or does not have the minimal number of two segments."); //$NON-NLS-1$
		}

		// separate the enumeration name and the literal name
		String enumLiteralName = pathName.get(pathName.size() - 1);
		pathName = pathName.subList(0, pathName.size() - 1);

		// find the enumeration
		Type enumeration = findType(pathName);

		// check that we found an enumeration
		if (!(enumeration instanceof Enumeration)) {
			throw new IllegalArgumentException("The path name " + pathName //$NON-NLS-1$
					+ " does not denote an enumeration."); //$NON-NLS-1$
		}

		EnumerationLiteral enumLiteral =
				((Enumeration) enumeration).lookupLiteral(enumLiteralName);

		if (enumLiteral == null) {
			throw new IllegalArgumentException(
					"There is no literal with name '" + enumLiteralName //$NON-NLS-1$
							+ "' in enumeration '" + enumeration.getQualifiedName() + "'."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		EnumLiteralExp enumLiteralExp;

		enumLiteralExp = ExpressionsFactory.INSTANCE.createEnumLiteralExp();
		enumLiteralExp.setReferredEnumLiteral(enumLiteral);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createEnumLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ enumLiteralExp);
		}

		return enumLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createExpressionInOcl(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
	 */
	public ExpressionInOcl createExpressionInOcl(String body,
			OclExpression bodyExpression, Variable context, Variable result,
			Variable... parameter) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createExpressionInOcl(bodyExpression=" + bodyExpression //$NON-NLS-1$
					+ ", context=" + context + ", result=" + result + ", parameter=" //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
					+ ArrayUtils.toString(parameter) + ") - enter"); //$NON-NLS-1$
		}

		if (bodyExpression == null || context == null) {
			throw new NullArgumentException("bodyExpression or context"); //$NON-NLS-1$
		}

		ExpressionInOcl expressionInOcl;

		expressionInOcl = ExpressionsFactory.INSTANCE.createExpressionInOcl();
		expressionInOcl.setBodyExpression(bodyExpression);
		expressionInOcl.setContext(context);

		if (StringUtils.isNotEmpty(body)) {
			expressionInOcl.setBody(body);
		}

		if (result != null) {
			expressionInOcl.setResult(result);
		}

		if (parameter != null) {
			expressionInOcl.getParameter().addAll(Arrays.asList(parameter));
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createExpressionInOcl() - exit - return value=" //$NON-NLS-1$
					+ expressionInOcl);
		}

		return expressionInOcl;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createIfExp(tudresden.ocl20
	 * .pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
	 */
	public IfExp createIfExp(OclExpression condition,
			OclExpression thenExpression, OclExpression elseExpression) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIfExp(condition=" + condition + ", thenExpression=" //$NON-NLS-1$ //$NON-NLS-2$
					+ thenExpression + ", elseExpression=" + elseExpression //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (condition == null || thenExpression == null || elseExpression == null) {
			throw new NullArgumentException(
					"condition or thenExpression or elseExpression"); //$NON-NLS-1$
		}

		IfExp ifExp = ExpressionsFactory.INSTANCE.createIfExp();

		ifExp.setCondition(condition);
		ifExp.setThenExpression(thenExpression);
		ifExp.setElseExpression(elseExpression);

		ifExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIfExp() - exit - return value=" + ifExp); //$NON-NLS-1$
		}
		return ifExp;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createIntegerLiteralExp(int)
	 */
	public IntegerLiteralExp createIntegerLiteralExp(int integerSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIntegerLiteralExp(integerSymbol=" + integerSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		IntegerLiteralExp integerLiteralExp =
				ExpressionsFactory.INSTANCE.createIntegerLiteralExp();
		integerLiteralExp.setIntegerSymbol(integerSymbol);

		// initialize reference to the OCL Library
		integerLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIntegerLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ integerLiteralExp);
		}

		return integerLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelFactory#createInvalidLiteralExp()
	 */
	public InvalidLiteralExp createInvalidLiteralExp() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createInvalidLiteralExp() - enter"); //$NON-NLS-1$
		}

		InvalidLiteralExp invalidLiteralExp =
				ExpressionsFactory.INSTANCE.createInvalidLiteralExp();

		invalidLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createInvalidLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ invalidLiteralExp);
		}

		return invalidLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createIterateExp(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression, java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
	 */
	public IterateExp createIterateExp(OclExpression source, OclExpression body,
			Variable result, Variable... iterator) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIterateExp(source=" + source + ", body=" + body //$NON-NLS-1$ //$NON-NLS-2$
					+ ", result=" + result + ", iterator=" //$NON-NLS-1$//$NON-NLS-2$
					+ ArrayUtils.toString(iterator) + ") - enter"); //$NON-NLS-1$
		}

		if (source == null || body == null || result == null) {
			throw new NullArgumentException("source or body or result"); //$NON-NLS-1$
		}

		IterateExp iterateExp = ExpressionsFactory.INSTANCE.createIterateExp();

		iterateExp.setSource(source);
		iterateExp.setBody(body);
		iterateExp.setResult(result);

		if (iterator != null) {
			iterateExp.getIterator().addAll(Arrays.asList(iterator));
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIterateExp() - exit - return value=" + iterateExp); //$NON-NLS-1$
		}

		return iterateExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createIteratorExp(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression, java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression,
	 * tudresden.ocl20.pivot.essentialocl.expressions.Variable[])
	 */
	public IteratorExp createIteratorExp(OclExpression source, String name,
			OclExpression body, Variable... iterator) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIteratorExp(source=" + source + ", name=" + name //$NON-NLS-1$ //$NON-NLS-2$
					+ ", body=" + body + ", iterator=" + ArrayUtils.toString(iterator) //$NON-NLS-1$//$NON-NLS-2$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (source == null || StringUtils.isEmpty(name) || body == null) {
			throw new IllegalArgumentException(
					"Parameters must not be null or empty: source=" + source //$NON-NLS-1$
							+ ", name=" + name + ", body=" + body + "."); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		}

		IteratorExp iteratorExp = ExpressionsFactory.INSTANCE.createIteratorExp();

		iteratorExp.setSource(source);
		iteratorExp.setName(name);
		iteratorExp.setBody(body);

		if (iterator != null) {
			iteratorExp.getIterator().addAll(Arrays.asList(iterator));
		}

		// set the reference to the OCL library
		iteratorExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIteratorExp() - exit - return value=" + iteratorExp); //$NON-NLS-1$
		}

		return iteratorExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createLetExp(tudresden.ocl20
	 * .pivot.essentialocl.expressions.Variable,
	 * tudresden.ocl20.pivot.essentialocl.expressions.OclExpression)
	 */
	public LetExp createLetExp(Variable variable, OclExpression in) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createLetExp(variable=" + variable + ", in=" + in //$NON-NLS-1$ //$NON-NLS-2$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (variable == null || in == null) {
			throw new IllegalArgumentException(
					"Parameters must not be null: variable=" + variable //$NON-NLS-1$
							+ ", in=" + in + "."); //$NON-NLS-1$//$NON-NLS-2$
		}

		LetExp letExp = ExpressionsFactory.INSTANCE.createLetExp();

		letExp.setVariable(variable);
		letExp.setIn(in);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createLetExp() - exit - return value=" + letExp); //$NON-NLS-1$
		}

		return letExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createOperationCallExp(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression, java.lang.String,
	 * java.util.List, java.util.List)
	 */
	public OperationCallExp createOperationCallExp(OclExpression source,
			String referredOperationName, OclExpression... argument) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOperationCallExp(source=" + source //$NON-NLS-1$
					+ ", referredOperationName=" + referredOperationName + ", argument=" //$NON-NLS-1$ //$NON-NLS-2$
					+ ArrayUtils.toString(argument) + ") - enter"); //$NON-NLS-1$
		}

		if (source == null || StringUtils.isEmpty(referredOperationName)) {
			throw new IllegalArgumentException(
					"Parameters must not be null or empty: source=" + source //$NON-NLS-1$
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
		Type sourceType = source.getType();
		Operation operation =
				sourceType.lookupOperation(referredOperationName, paramTypes);

		if (operation == null) {
			throw new IllegalArgumentException(
					"Unable to find operation '" + referredOperationName //$NON-NLS-1$
							+ "' with argument types " + paramTypes + " in type '" //$NON-NLS-1$ //$NON-NLS-2$
							+ source.getType().getQualifiedName() + "'."); //$NON-NLS-1$
		}

		OperationCallExp operationCallExp =
				ExpressionsFactory.INSTANCE.createOperationCallExp();
		operationCallExp.setSource(source);
		operationCallExp.setReferredOperation(operation);

		if (argument != null) {
			operationCallExp.getArgument().addAll(Arrays.asList(argument));
		}

		// a property call expression needs access to the OCL library
		operationCallExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createOperationCallExp() - exit - return value=" + operationCallExp); //$NON-NLS-1$
		}

		return operationCallExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createOperationCallExp(java
	 * .util.List, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression[])
	 */
	public OperationCallExp createOperationCallExp(List<String> pathName,
			OclExpression... argument) throws FactoryException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOperationCallExp(pathName=" + pathName //$NON-NLS-1$
					+ ", argument=" + ArrayUtils.toString(argument) + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (pathName == null || pathName.size() < 2) {
			throw new IllegalArgumentException(
					"The static operation path name '" + pathName //$NON-NLS-1$
							+ "' is either null or does not have the required number of at least two segments."); //$NON-NLS-1$
		}

		// split the pathname into the type and operation part
		String referredOperation = pathName.get(pathName.size() - 1);
		pathName = pathName.subList(0, pathName.size() - 1);

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
		Operation operation =
				owningType.lookupOperation(referredOperation, paramTypes);

		if (operation == null || !operation.isStatic()) {
			throw new IllegalArgumentException(
					"Unable to find a static operation '" + referredOperation //$NON-NLS-1$
							+ "' with argument types " + paramTypes + "' in type " //$NON-NLS-1$ //$NON-NLS-2$
							+ owningType.getQualifiedName() + "."); //$NON-NLS-1$
		}

		// create the expression
		OperationCallExp operationCallExp =
				ExpressionsFactory.INSTANCE.createOperationCallExp();
		operationCallExp.setSourceType(owningType);
		operationCallExp.setReferredOperation(operation);

		if (argument != null) {
			operationCallExp.getArgument().addAll(Arrays.asList(argument));
		}

		// a property call expression needs access to the OCL library for
		// determining its type
		operationCallExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOperationCallExp() - exit - return value=" //$NON-NLS-1$
					+ operationCallExp);
		}

		return operationCallExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createPropertyCallExp(tudresden
	 * .ocl20.pivot.essentialocl.expressions.OclExpression, java.lang.String)
	 */
	public PropertyCallExp createPropertyCallExp(OclExpression source,
			String referredPropertyName, OclExpression... qualifier) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp(source=" + source //$NON-NLS-1$
					+ ", referredPropertyName=" + referredPropertyName + ", qualifier=" //$NON-NLS-1$ //$NON-NLS-2$
					+ ArrayUtils.toString(qualifier) + ") - enter"); //$NON-NLS-1$
		}

		if (source == null || StringUtils.isEmpty(referredPropertyName)) {
			throw new IllegalArgumentException(
					"Parameters must not be null or empty: source=" + source //$NON-NLS-1$
							+ ", referredPropertyName=" + referredPropertyName + "."); //$NON-NLS-1$//$NON-NLS-2$
		}

		// lookup the property
		Type sourceType = source.getType();
		Property property = sourceType.lookupProperty(referredPropertyName);

		// invalid and undefined conform to all types, so we ignore if we haven't
		// found a property
		if (property == null) {
			throw new IllegalArgumentException(
					"Unable to find property '" + referredPropertyName //$NON-NLS-1$
							+ "' in type '" + source.getType().getQualifiedName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		PropertyCallExp propertyCallExp =
				ExpressionsFactory.INSTANCE.createPropertyCallExp();

		propertyCallExp.setSource(source);
		propertyCallExp.setReferredProperty(property);

		// a property call expression needs access to the OCL library
		propertyCallExp.setOclLibrary(getOclLibrary());

		// set the qualifiers if existing
		if (qualifier != null) {
			propertyCallExp.getQualifier().addAll(Arrays.asList(qualifier));
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp() - exit - return value=" //$NON-NLS-1$
					+ propertyCallExp);
		}

		return propertyCallExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createPropertyCallExp(java
	 * .util.List, tudresden.ocl20.pivot.essentialocl.expressions.OclExpression[])
	 */
	public PropertyCallExp createPropertyCallExp(List<String> pathName,
			OclExpression... qualifier) throws FactoryException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp(pathName=" + pathName //$NON-NLS-1$
					+ ", qualifier=" + ArrayUtils.toString(qualifier) + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (pathName == null || pathName.size() < 2) {
			throw new IllegalArgumentException(
					"The static property path name '" + pathName //$NON-NLS-1$
							+ "' is either null or does not have the required number of at least two segments."); //$NON-NLS-1$
		}

		// split the path name into the type name and the property name
		String referredProperty = pathName.get(pathName.size() - 1);
		pathName = pathName.subList(0, pathName.size() - 1);

		// lookup the type
		Type owningType = findType(pathName);

		// lookup the property
		Property property = owningType.lookupProperty(referredProperty);

		if (property == null || !property.isStatic()) {
			throw new IllegalArgumentException(
					"Unable to find a static property '" + referredProperty //$NON-NLS-1$
							+ "' in type " + owningType.getQualifiedName() + "."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// create the expression
		PropertyCallExp propertyCallExp =
				ExpressionsFactory.INSTANCE.createPropertyCallExp();
		propertyCallExp.setSourceType(owningType);
		propertyCallExp.setReferredProperty(property);

		// add qualifiers
		if (qualifier != null) {
			propertyCallExp.getQualifier().addAll(Arrays.asList(qualifier));
		}

		// set reference to OCL library
		propertyCallExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp() - exit - return value=" //$NON-NLS-1$
					+ propertyCallExp);
		}

		return propertyCallExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createRealLiteralExp(float)
	 */
	public RealLiteralExp createRealLiteralExp(float realSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createRealLiteralExp(realSymbol=" + realSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		RealLiteralExp realLiteralExp =
				ExpressionsFactory.INSTANCE.createRealLiteralExp();
		realLiteralExp.setRealSymbol(realSymbol);

		realLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createRealLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ realLiteralExp);
		}

		return realLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createStringLiteralExp(java
	 * .lang.String)
	 */
	public StringLiteralExp createStringLiteralExp(String stringSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createStringLiteralExp(stringSymbol=" + stringSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (stringSymbol == null) {
			throw new IllegalArgumentException(
					"The argument 'stringSymbol' must not be null."); //$NON-NLS-1$
		}

		StringLiteralExp stringLiteralExp;

		// create and initialize a new StringLiteralExp
		stringLiteralExp = ExpressionsFactory.INSTANCE.createStringLiteralExp();
		stringLiteralExp.setStringSymbol(stringSymbol);
		stringLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createStringLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ stringLiteralExp);
		}

		return stringLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createTupleLiteralExp(java
	 * .util.List)
	 */
	public TupleLiteralExp createTupleLiteralExp(TupleLiteralPart... parts) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createTupleLiteralExp(parts=" + ArrayUtils.toString(parts) //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		TupleLiteralExp tupleLiteralExp;

		// create new expression and add parts
		tupleLiteralExp = ExpressionsFactory.INSTANCE.createTupleLiteralExp();

		if (parts != null) {
			tupleLiteralExp.getPart().addAll(Arrays.asList(parts));
		}

		// set reference to OCL library
		tupleLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createTupleLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ tupleLiteralExp);
		}

		return tupleLiteralExp;

	}

	/**
	 * Creates a new {@link TupleLiteralPart}.
	 */
	public TupleLiteralPart createTupleLiteralPart(Variable variableDeclaration) {

		if (variableDeclaration == null) {
			throw new NullArgumentException("variableDeclaration"); //$NON-NLS-1$
		}

		TupleLiteralPart part;

		// create a new part
		part = ExpressionsFactory.INSTANCE.createTupleLiteralPart();

		// set the property and the value of the part
		part.setProperty(variableDeclaration.asProperty());
		part.setValue(variableDeclaration.getInitExpression());

		return part;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createTypeLiteralExp(java.
	 * util.List)
	 */
	public TypeLiteralExp createTypeLiteralExp(List<String> referredTypeName)
			throws FactoryException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createTypeLiteralExp(referredTypeName=" + referredTypeName //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (referredTypeName == null || referredTypeName.size() == 0) {
			throw new IllegalArgumentException(
					"The argument 'referredTypeName' must not be null or empty."); //$NON-NLS-1$
		}

		Type type = findType(referredTypeName);

		TypeLiteralExp typeLiteralExp =
				ExpressionsFactory.INSTANCE.createTypeLiteralExp();
		typeLiteralExp.setReferredType(type);

		// initialize reference to the OCL Library
		typeLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createTypeLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ typeLiteralExp);
		}

		return typeLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createUndefinedLiteralExp()
	 */
	public UndefinedLiteralExp createUndefinedLiteralExp() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUndefinedLiteralExp() - enter"); //$NON-NLS-1$
		}

		UndefinedLiteralExp undefinedLiteralExp =
				ExpressionsFactory.INSTANCE.createUndefinedLiteralExp();

		undefinedLiteralExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUndefinedLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ undefinedLiteralExp);
		}

		return undefinedLiteralExp;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createUnlimitedNaturalExp(
	 * java.lang.String)
	 */
	public UnlimitedNaturalExp createUnlimitedNaturalExp(long symbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUnlimitedNaturalExp(symbol=" + symbol + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		UnlimitedNaturalExp unlimitedNaturalExp =
				ExpressionsFactory.INSTANCE.createUnlimitedNaturalExp();
		unlimitedNaturalExp.setSymbol(symbol);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUnlimitedNaturalExp() - exit - return value=" //$NON-NLS-1$
					+ unlimitedNaturalExp);
		}

		return unlimitedNaturalExp;
	}

	/**
	 * Creates a {@link Variable} using the {@link ExpressionsFactory}.
	 */
	public Variable createVariable(String name, Type type,
			OclExpression initExpression) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariable(name=" + name + ", type=" + type //$NON-NLS-1$ //$NON-NLS-2$
					+ ", initExpression=" + initExpression + ") - enter"); //$NON-NLS-1$//$NON-NLS-2$
		}

		// precondition check
		if (StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException(
					"The 'name' argument must not be null or empty."); //$NON-NLS-1$
		}

		// create the variable and initialize
		Variable variable = ExpressionsFactory.INSTANCE.createVariable();
		variable.setName(name);

		if (type != null) {
			variable.setType(type);
		}

		if (initExpression != null) {
			variable.setInitExpression(initExpression);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariable() - exit - return value=" + variable); //$NON-NLS-1$
		}

		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createVariable(tudresden.ocl20
	 * .pivot.pivotmodel.Parameter)
	 */
	public Variable createVariable(Parameter representedParameter) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariable(representedParameter=" //$NON-NLS-1$
					+ representedParameter + ") - enter"); //$NON-NLS-1$
		}

		if (representedParameter == null) {
			throw new IllegalArgumentException(
					"The represented parameter must not be null."); //$NON-NLS-1$
		}

		Variable variable = ExpressionsFactory.INSTANCE.createVariable();
		variable.setRepresentedParameter(representedParameter);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariable() - exit - return value=" + variable); //$NON-NLS-1$
		}

		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelFactory#createVariableExp(tudresden
	 * .ocl20.pivot.essentialocl.expressions.Variable)
	 */
	public VariableExp createVariableExp(Variable referredVariable) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariableExp(referredVariable=" + referredVariable //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		if (referredVariable == null) {
			throw new IllegalArgumentException(
					"The referred variable must not be null."); //$NON-NLS-1$
		}

		VariableExp variableExp = ExpressionsFactory.INSTANCE.createVariableExp();
		variableExp.setReferredVariable(referredVariable);

		// a variable expression needs access to the OCL library
		variableExp.setOclLibrary(getOclLibrary());

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariableExp() - exit - return value=" + variableExp); //$NON-NLS-1$
		}

		return variableExp;

	}

	/**
	 * <p>
	 * A helper method to lazily get the OCL Library.
	 * </p>
	 */
	protected OclLibrary getOclLibrary() {

		if (oclLibrary == null) {
			IOclLibraryProvider provider = model.getOclLibraryProvider();

			if (provider == null) {
				throw new ModelBusException("The model '" + model.getDisplayName() //$NON-NLS-1$
						+ "' did not return a valid provider for the OCL Standard Library."); //$NON-NLS-1$
			}

			oclLibrary = provider.getOclLibrary();
		}

		return oclLibrary;
	}

	/**
	 * <p>
	 * A helper method to lazily get the type resolver.
	 * </p>
	 */
	protected ITypeResolver getTypeResolver() {

		if (typeResolver == null) {
			typeResolver = model.getTypeResolver();

			if (typeResolver == null) {
				throw new ModelBusException("The model '" + model.getDisplayName() //$NON-NLS-1$
						+ "'did not provide a valid type resolver."); //$NON-NLS-1$
			}
		}

		return typeResolver;
	}

	/**
	 * <p>
	 * Helper method to look up a type using the associated type resolver. Handles
	 * all possible checked exceptions by converting them into a factory
	 * exception.
	 * </p>
	 */
	protected Type findType(List<String> pathName) throws FactoryException {

		Type type;

		try {
			type = getTypeResolver().findType(pathName);
		}

		catch (TypeNotFoundException e) {
			LOGGER.error("findType(pathName=" + pathName + ")", e); //$NON-NLS-1$//$NON-NLS-2$
			throw new FactoryException("Failed to lookup type " + pathName //$NON-NLS-1$
					+ ", both in the OCL Standard Library and the associated model '" //$NON-NLS-1$
					+ model.getDisplayName() + "'.", e); //$NON-NLS-1$

		}

		catch (ModelAccessException e) {
			LOGGER.error("findType(pathName=" + pathName + ")", e); //$NON-NLS-1$ //$NON-NLS-2$
			throw new FactoryException(
					"An error occured when accessing model '" + model.getDisplayName() //$NON-NLS-1$
							+ "'.", e); //$NON-NLS-1$
		}

		return type;
	}
}