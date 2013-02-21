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
package org.dresdenocl.essentialocl.expressions.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.naming.TimeLimitExceededException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.expressions.BooleanLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionItem;
import org.dresdenocl.essentialocl.expressions.CollectionKind;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralPart;
import org.dresdenocl.essentialocl.expressions.CollectionRange;
import org.dresdenocl.essentialocl.expressions.EnumLiteralExp;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.ExpressionsFactory;
import org.dresdenocl.essentialocl.expressions.IfExp;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.InvalidLiteralExp;
import org.dresdenocl.essentialocl.expressions.IterateExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.LetExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.essentialocl.expressions.RealLiteralExp;
import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralExp;
import org.dresdenocl.essentialocl.expressions.TupleLiteralPart;
import org.dresdenocl.essentialocl.expressions.TypeLiteralExp;
import org.dresdenocl.essentialocl.expressions.UndefinedLiteralExp;
import org.dresdenocl.essentialocl.expressions.UnlimitedNaturalExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.VariableExp;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.essentialocl.types.util.TypeResolver;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.TypeNotFoundException;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.Expression;
import org.dresdenocl.pivotmodel.Feature;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * Standard implementation of the {@link EssentialOclFactory} interface which
 * relies on a given {@link IModel} instance to find types in the model.
 * 
 * @author Matthias Braeuer
 * @version 1.0 10.04.2007
 */
public class EssentialOclFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EssentialOclPlugin
			.getLogger(EssentialOclFactory.class);

	/** The {@link OclLibrary} instance. */
	private OclLibrary oclLibrary;

	/** The {@link ITypeResolver} used for looking up types. */
	private TypeResolver typeResolver;

	/** The {@link IModel} the created OCL expressions belong to. */
	private IModel model;

	/**
	 * <p>
	 * Creates a new {@link EssentialOclFactory}.
	 * </p>
	 * 
	 * @param oclLibrary
	 *          The {@link OclLibrary} this {@link EssentialOclFactory} belongs
	 *          to.
	 * @param model
	 *          the {@link IModel} the created OCL expressions belong to.
	 */
	public EssentialOclFactory(OclLibrary oclLibrary, IModel model) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("EssentialOclFactory(oclLibrary=" + oclLibrary + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (oclLibrary == null) {
			throw new IllegalArgumentException(
					"Paramter 'oclLibrary' must not be null."); //$NON-NLS-1$
		}
		// no else.

		if (model == null) {
			throw new IllegalArgumentException("Paramter 'model' must not be null."); //$NON-NLS-1$
		}
		// no else.

		/* initialize the reference to the model. */
		this.model = model;
		this.oclLibrary = oclLibrary;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("OclLibrary() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a {@link BooleanLiteralExp}.
	 * </p>
	 * 
	 * @param booleanSymbol
	 *          The <code>boolean</code> value of the {@link BooleanLiteralExp}.
	 * @return A {@link BooleanLiteralExp} instance.
	 */
	public BooleanLiteralExp createBooleanLiteralExp(boolean booleanSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createBooleanLiteralExp(booleanSymbol=" + booleanSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		BooleanLiteralExp booleanLiteralExp;

		booleanLiteralExp = ExpressionsFactory.INSTANCE.createBooleanLiteralExp();
		booleanLiteralExp.setBooleanSymbol(booleanSymbol);
		booleanLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createBooleanLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ booleanLiteralExp);
		}

		return booleanLiteralExp;
	}

	/**
	 * <p>
	 * Creates a {@link CollectionItem}.
	 * </p>
	 * 
	 * @param item
	 *          The {@link OclExpression} of the item.
	 * @return A {@link CollectionItem} instance.
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
	 * <p>
	 * Creates a {@link CollectionLiteralExp}.
	 * </p>
	 * 
	 * @param kind
	 *          The {@link CollectionKind} of the {@link CollectionLiteralExp} .
	 * @param parts
	 *          The {@link CollectionLiteralPart}s of the
	 *          {@link CollectionLiteralExp}.
	 * @return A {@link CollectionLiteralExp} instance.
	 */
	public CollectionLiteralExp createCollectionLiteralExp(CollectionKind kind,
			Type elementType, CollectionLiteralPart... parts) {

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
		collectionLiteralExp.setElementType(elementType);
		collectionLiteralExp.getPart().addAll(Arrays.asList(parts));

		// set the reference to the OCL Library
		collectionLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createCollectionLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ collectionLiteralExp);
		}

		return collectionLiteralExp;
	}

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
	public Constraint createConstraint(String name, ConstraintKind kind,
			Expression specification, Feature definedFeature,
			ConstrainableElement... constrainedElement) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createConstraint(name=" + name + ", kind=" + kind //$NON-NLS-1$ //$NON-NLS-2$
					+ ", specification=" + specification + ", constrainedElement=" //$NON-NLS-1$//$NON-NLS-2$
					+ ArrayUtils.toString(constrainedElement) + ") - enter"); //$NON-NLS-1$
		}

		if (kind == null || specification == null || constrainedElement == null) {
			throw new NullArgumentException(
					"kind or specification or constrainedElement"); //$NON-NLS-1$
		}

		Constraint constraint = PivotModelFactory.eINSTANCE.createConstraint();

		constraint.setName(name);
		constraint.setKind(kind);
		constraint.setSpecification(specification);
		constraint.setDefinedFeature(definedFeature);
		constraint.getConstrainedElement()
				.addAll(Arrays.asList(constrainedElement));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createConstraint() - exit - return value=" + constraint); //$NON-NLS-1$
		}

		return constraint;

	}

	/**
	 * <p>
	 * Creates a {@link EnumLiteralExp}.
	 * </p>
	 * 
	 * @param referredEnumLiteralPathName
	 *          The fully qualified name of the referred
	 *          {@link EnumerationLiteral} as a {@link String}.
	 * @return A {@link EnumLiteralExp} instance.
	 * @throws EssentialOclFactoryException
	 *           Thrown, if the creation fails.
	 */
	public EnumLiteralExp createEnumLiteralExp(List<String> pathName)
			throws EssentialOclFactoryException {

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
	 *          The result {@link Variable} of an operation {@link Constraint} .
	 * @param parameter
	 *          The parameters of an operation {@link Constraint}.
	 * 
	 * @return An {@link ExpressionInOcl} instance.
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
			expressionInOcl.setBody(body.replaceAll("\r\n|\r|\n", " "));
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

		ifExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIfExp() - exit - return value=" + ifExp); //$NON-NLS-1$
		}
		return ifExp;

	}

	/**
	 * <p>
	 * Creates an {@link IntegerLiteralExp}.
	 * </p>
	 * 
	 * @param integerSymbol
	 *          The <code>int</code> value of the {@link IntegerLiteralExp}.
	 * @return An {@link IntegerLiteralExp} instance.
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
		integerLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIntegerLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ integerLiteralExp);
		}

		return integerLiteralExp;
	}

	/**
	 * <p>
	 * Creates an {@link InvalidLiteralExp}.
	 * </p>
	 * 
	 * @return The {@link InvalidLiteralExp} instance.
	 */
	public InvalidLiteralExp createInvalidLiteralExp() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createInvalidLiteralExp() - enter"); //$NON-NLS-1$
		}

		InvalidLiteralExp invalidLiteralExp =
				ExpressionsFactory.INSTANCE.createInvalidLiteralExp();

		invalidLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createInvalidLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ invalidLiteralExp);
		}

		return invalidLiteralExp;
	}

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
		iteratorExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createIteratorExp() - exit - return value=" + iteratorExp); //$NON-NLS-1$
		}

		return iteratorExp;
	}

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
			StringBuilder paramTypeNames = new StringBuilder();
			if (paramTypes.size() == 0)
				paramTypeNames.append(" with no argument");
			if (paramTypes.size() == 1)
				paramTypeNames.append(" with argument type ");
			if (paramTypes.size() > 1)
				paramTypeNames.append(" with argument types ");
			for (int i = 0; i < paramTypes.size(); i++) {
				Type paramType = paramTypes.get(i);
				if (i > 0)
					paramTypeNames.append(", ");
				paramTypeNames.append("'");
				paramTypeNames.append(paramType.getName());
				paramTypeNames.append("'");
			}
			throw new IllegalArgumentException(
					"Unable to find operation '" + referredOperationName //$NON-NLS-1$
							+ "'" + paramTypeNames + " in type '" //$NON-NLS-1$ //$NON-NLS-2$
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
		operationCallExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("createOperationCallExp() - exit - return value=" + operationCallExp); //$NON-NLS-1$
		}

		return operationCallExp;
	}

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
	 * @throws EssentialOclFactoryException
	 *           If the expression cannot be created.
	 */
	public OperationCallExp createOperationCallExp(List<String> pathName,
			OclExpression... argument) throws EssentialOclFactoryException {

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

		// collect the parameter types
		List<Type> paramTypes = new ArrayList<Type>();

		if (argument != null) {
			for (int i = 0; i < argument.length; i++) {
				paramTypes.add(argument[i].getType());
			}
		}

		// lookup the type
		Type owningType = findType(pathName);

		// FIXME Michael: This is wrong, but the parser treats this as a static
		// operation. A new parser should avoid this, so this code can move to
		// another place (non-static operation)

		// lookup the operation on OclType first (allInstances, probably more)
		Operation operation =
				oclLibrary.getOclType().lookupOperation(referredOperation, paramTypes);

		if (operation == null) {

			operation = owningType.lookupOperation(referredOperation, paramTypes);

			if (operation == null || !operation.isStatic()) {
				throw new IllegalArgumentException(
						"Unable to find a static operation '" + referredOperation //$NON-NLS-1$
								+ "' with argument types " + paramTypes + "' in type " //$NON-NLS-1$ //$NON-NLS-2$
								+ owningType.getQualifiedName() + "."); //$NON-NLS-1$
			}
		}
		// no else.

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
		operationCallExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createOperationCallExp() - exit - return value=" //$NON-NLS-1$
					+ operationCallExp);
		}

		return operationCallExp;
	}

	/**
	 * @author Lars Schuetze
	 * @param source
	 *          The source {@link OclExpression} of the {@link PropertyCallExp}.
	 * @param referredProperty
	 *          The referred {@link Property property}
	 * @param qualifier
	 *          qualifier {@link OclExpression} as an Array.
	 * @return A {@link PropertyCallExp} instance.
	 * @throws EssentialOclFactoryException
	 */
	public PropertyCallExp createPropertyCallExp(OclExpression source,
			Property referredProperty, OclExpression... qualifier)
			throws EssentialOclFactoryException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp(source=" + source
					+ ", refferedProperty=" + referredProperty + ", qualifier="
					+ ArrayUtils.toString(qualifier));
		}

		if (source == null || referredProperty == null) {
			throw new IllegalArgumentException(
					"Parameters must not be null: source=" + source //$NON-NLS-1$
							+ ", referredProperty=" + referredProperty + "."); //$NON-NLS-1$//$NON-NLS-2$
		}

		// create the expression
		PropertyCallExp propertyCallExp =
				ExpressionsFactory.INSTANCE.createPropertyCallExp();

		propertyCallExp.setSource(source);
		propertyCallExp.setSourceType(referredProperty.getOwningType());
		propertyCallExp.setReferredProperty(referredProperty);

		// a property call expression needs access to the OCL library
		propertyCallExp.setOclLibrary(oclLibrary);

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
	 * @throws EssentialOclFactoryException
	 */
	public PropertyCallExp createPropertyCallExp(OclExpression source,
			String referredPropertyName, OclExpression... qualifier)
			throws EssentialOclFactoryException {

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

		// invalid and undefined conform to all types, so we ignore if we
		// haven't found a property
		if (property == null) {
			throw new EssentialOclFactoryException(
					"Unable to find property '" + referredPropertyName //$NON-NLS-1$
							+ "' in type '" + source.getType().getQualifiedName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// create the expression
		PropertyCallExp propertyCallExp =
				ExpressionsFactory.INSTANCE.createPropertyCallExp();

		propertyCallExp.setSource(source);
		propertyCallExp.setSourceType(sourceType);
		propertyCallExp.setReferredProperty(property);

		// a property call expression needs access to the OCL library
		propertyCallExp.setOclLibrary(oclLibrary);

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
	 * @throws EssentialOclFactoryException
	 *           Thrown, if the creation fails.
	 */
	public PropertyCallExp createPropertyCallExp(List<String> pathName,
			OclExpression... qualifier) throws EssentialOclFactoryException {

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
		propertyCallExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createPropertyCallExp() - exit - return value=" //$NON-NLS-1$
					+ propertyCallExp);
		}

		return propertyCallExp;
	}

	/**
	 * <p>
	 * Creates a {@link RealLiteralExp}.
	 * </p>
	 * 
	 * @param realSymbol
	 *          The <code>float</code> value of the {@link RealLiteralExp} .</p>
	 * @return The {@link RealLiteralExp}.
	 */
	public RealLiteralExp createRealLiteralExp(float realSymbol) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createRealLiteralExp(realSymbol=" + realSymbol //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		RealLiteralExp realLiteralExp =
				ExpressionsFactory.INSTANCE.createRealLiteralExp();
		realLiteralExp.setRealSymbol(realSymbol);

		realLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createRealLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ realLiteralExp);
		}

		return realLiteralExp;
	}

	/**
	 * <p>
	 * Creates a {@link StringLiteralExp}.
	 * </p>
	 * 
	 * @param stringSymbol
	 *          The {@link String} value of the {@link StringLiteralExp}.
	 * @return A {@link StringLiteralExp} instance.
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
		stringLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createStringLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ stringLiteralExp);
		}

		return stringLiteralExp;
	}

	/**
	 * <p>
	 * Creates a {@link TupleLiteralExp}.
	 * </p>
	 * 
	 * @param parts
	 *          The {@link TupleLiteralPart}s of the {@link TupleLiteralExp}.
	 * @return A {@link TupleLiteralExp} instance.
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
		tupleLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createTupleLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ tupleLiteralExp);
		}

		return tupleLiteralExp;

	}

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
	 * @throws EssentialOclFactoryException
	 *           Thrown, if the creation fails.
	 */
	public TypeLiteralExp createTypeLiteralExp(List<String> referredTypeName)
			throws EssentialOclFactoryException {

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

	/**
	 * <p>
	 * Creates an {@link UndefinedLiteralExp}.
	 * </p>
	 * 
	 * @return An {@link UndefinedLiteralExp} instance.
	 */
	public UndefinedLiteralExp createUndefinedLiteralExp() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUndefinedLiteralExp() - enter"); //$NON-NLS-1$
		}

		UndefinedLiteralExp undefinedLiteralExp =
				ExpressionsFactory.INSTANCE.createUndefinedLiteralExp();

		undefinedLiteralExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createUndefinedLiteralExp() - exit - return value=" //$NON-NLS-1$
					+ undefinedLiteralExp);
		}

		return undefinedLiteralExp;
	}

	/**
	 * <p>
	 * Creates an {@link UnlimitedNaturalExp}.
	 * </p>
	 * 
	 * @param symbol
	 *          The <code>long</code> value of the {@link UnlimitedNaturalExp} .
	 * @return A {@link UnlimitedNaturalExp} instance.
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

	/**
	 * <p>
	 * Creates a {@link VariableExp}.
	 * </p>
	 * 
	 * @param referredVariable
	 *          The referred {@link Variable}.
	 * @return A {@link VariableExp} instance.
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
		variableExp.setOclLibrary(oclLibrary);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("createVariableExp() - exit - return value=" + variableExp); //$NON-NLS-1$
		}

		return variableExp;

	}

	/**
	 * <p>
	 * Helper method to look up a type using the associated type resolver. Handles
	 * all possible checked exceptions by converting them into a factory
	 * exception.
	 * </p>
	 */
	public Type findType(List<String> pathName)
			throws EssentialOclFactoryException {

		Type type;

		if (typeResolver == null) {
			typeResolver = new TypeResolver(oclLibrary);
		}

		try {
			type = typeResolver.findType(pathName, model);
		}

		catch (TypeNotFoundException e) {
			LOGGER.error("findType(pathName=" + pathName + ")", e); //$NON-NLS-1$//$NON-NLS-2$
			throw new EssentialOclFactoryException(
					"Failed to lookup type " + pathName //$NON-NLS-1$
							+ ", both in the OCL Standard Library and the associated model '" //$NON-NLS-1$
							+ model.getDisplayName() + "'.", e); //$NON-NLS-1$

		}

		catch (ModelAccessException e) {
			LOGGER.error("findType(pathName=" + pathName + ")", e); //$NON-NLS-1$ //$NON-NLS-2$
			throw new EssentialOclFactoryException(
					"An error occured when accessing model '" + model.getDisplayName() //$NON-NLS-1$
							+ "'.", e); //$NON-NLS-1$
		}

		return type;
	}
}