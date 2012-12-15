/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metrics;

import java.util.Collection;
import java.util.HashMap;

import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.ConstraintMetrics;
import org.dresdenocl.metrics.metric.Metric;
import org.dresdenocl.metrics.metric.MetricFactory;

import org.dresdenocl.essentialocl.expressions.BooleanLiteralExp;
import org.dresdenocl.essentialocl.expressions.CallExp;
import org.dresdenocl.essentialocl.expressions.CollectionItem;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralExp;
import org.dresdenocl.essentialocl.expressions.CollectionLiteralPart;
import org.dresdenocl.essentialocl.expressions.CollectionRange;
import org.dresdenocl.essentialocl.expressions.EnumLiteralExp;
import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.FeatureCallExp;
import org.dresdenocl.essentialocl.expressions.IfExp;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.InvalidLiteralExp;
import org.dresdenocl.essentialocl.expressions.IterateExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.LetExp;
import org.dresdenocl.essentialocl.expressions.LiteralExp;
import org.dresdenocl.essentialocl.expressions.LoopExp;
import org.dresdenocl.essentialocl.expressions.NumericLiteralExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PrimitiveLiteralExp;
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
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * Helper Class to compute {@link Metric}s for {@link Constraint}s or
 * {@link IModel}s.
 * 
 * @author Claas Wilke
 */
public class OclMetrics {

	/**
	 * Computes a {@link Metric} for a given {@link Constraint}.
	 * 
	 * @param constraint
	 *            The {@link Constraint}.
	 * @return The computed {@link Metric}.
	 */
	public static ConstraintMetric computeMetric(Constraint constraint) {

		ConstraintMetric result = MetricFactory.eINSTANCE
				.createConstraintMetric();
		result.setReferredConstraint(constraint);

		visitOclExpression(
				((ExpressionInOcl) constraint.getSpecification())
						.getBodyExpression(),
				result);

		return result;
	}

	/**
	 * Computes a {@link Metric} for a given {@link Collection} of
	 * {@link Constraint}s.
	 * 
	 * @param constraints
	 *            The {@link Constraint}s.
	 * @return The computed {@link Metric}.
	 */
	public static ConstraintMetrics computeMetric(
			Collection<Constraint> constraints) throws ModelAccessException {

		ConstraintMetrics result = MetricFactory.eINSTANCE
				.createConstraintMetrics();
		result.getConstraints().addAll(constraints);

		for (Constraint constraint : constraints) {
			ConstraintMetric constraintMetric = computeMetric(constraint);
			result.getConstraintMetrics().add(constraintMetric);
		}
		// end for.

		return result;
	}

	/**
	 * Visits an {@link OclExpression} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link OclExpression} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitOclExpression(OclExpression exp,
			ConstraintMetric metric) {

		if (exp instanceof CallExp) {
			visitCallExp((CallExp) exp, metric);
		}

		else if (exp instanceof IfExp) {
			visitIfExp((IfExp) exp, metric);
		}

		else if (exp instanceof LetExp) {
			visitLetExp((LetExp) exp, metric);
		}

		else if (exp instanceof LiteralExp) {
			visitLiteralExp((LiteralExp) exp, metric);
		}

		else if (exp instanceof VariableExp) {
			visitVariableExp((VariableExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits a {@link BooleanLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link BooleanLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitBooleanLiteralExp(BooleanLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("Boolean", metric);
	}

	/**
	 * Visits an {@link CallExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link CallExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitCallExp(CallExp exp, ConstraintMetric metric) {

		if (exp instanceof FeatureCallExp) {
			visitFeatureCallExp((FeatureCallExp) exp, metric);
		}

		else if (exp instanceof LoopExp) {
			visitLoopExp((LoopExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits a {@link CollectionItem} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param var
	 *            The {@link CollectionItem} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitCollectionItem(CollectionItem item,
			ConstraintMetric metric) {

		visitOclExpression(item.getItem(), metric);
	}

	/**
	 * Visits a {@link CollectionRange} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param range
	 *            The {@link Variable} to be visited.
	 * @param metric
	 *            The {@link CollectionRange} that is currently computed.
	 */
	protected static void visitCollectionRange(CollectionRange range,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitOclExpression(range.getFirst(), metric);
		int firstExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(range.getLast(), metric);
		int lastExpDepth = metric.getExpressionDepth();

		metric.setExpressionDepth(Math.max(firstExpDepth, lastExpDepth));
	}

	/**
	 * Visits a {@link CollectionLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link CollectionLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitCollectionLiteralExp(CollectionLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		int maxDepth = expDepth;

		for (CollectionLiteralPart part : exp.getPart()) {
			metric.setExpressionDepth(expDepth);

			visitCollectionLiteralPart(part, metric);
			maxDepth = Math.max(maxDepth, metric.getExpressionDepth());
		}
		// end for.

		metric.setExpressionDepth(maxDepth);

		increaseLiteralCount(exp.getType().getName(), metric);
	}

	/**
	 * Visits a {@link CollectionLiteralPart} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param part
	 *            The {@link CollectionLiteralPart} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitCollectionLiteralPart(
			CollectionLiteralPart part, ConstraintMetric metric) {

		if (part instanceof CollectionItem)
			visitCollectionItem((CollectionItem) part, metric);

		else if (part instanceof CollectionRange)
			visitCollectionRange((CollectionRange) part, metric);

		else
			throw new IllegalStateException(
					"Visit of unknown CollectionLiteralPart: "
							+ part.getClass().getCanonicalName());

	}

	/**
	 * Visits a {@link EnumLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link EnumLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitEnumLiteralExp(EnumLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("EnumerationLiteral", metric);
		increaseLiteralCount("EnumerationLiteral: "
				+ exp.getReferredEnumLiteral().getQualifiedName(), metric);
	}

	/**
	 * Visits an {@link FeatureCallExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link FeatureCallExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitFeatureCallExp(FeatureCallExp exp,
			ConstraintMetric metric) {

		if (exp instanceof OperationCallExp) {
			visitOperationCallExp((OperationCallExp) exp, metric);
		}

		else if (exp instanceof PropertyCallExp) {
			visitPropertyCallExp((PropertyCallExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits a {@link IfExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link LetExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitIfExp(IfExp exp, ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getCondition(), metric);
		int conditionExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getThenExpression(), metric);
		int thenExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getElseExpression(), metric);
		int elseExpDepth = metric.getExpressionDepth();

		metric.setExpressionDepth(Math.max(conditionExpDepth,
				Math.max(thenExpDepth, elseExpDepth)));

		metric.setNumberOfIfExpressions(metric.getNumberOfIfExpressions() + 1);
	}

	/**
	 * Visits a {@link IntegerLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link IntegerLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitIntegerLiteralExp(IntegerLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("Integer", metric);
	}

	/**
	 * Visits a {@link InvalidLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link InvalidLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitInvalidLiteralExp(InvalidLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("invalid", metric);
	}

	/**
	 * Visits a {@link IterateExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link IterateExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitIterateExp(IterateExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitVariable(exp.getResult(), metric);
		int resultExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getSource(), metric);
		int sourceExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getBody(), metric);
		int bodyExpDepth = metric.getExpressionDepth();

		metric.setExpressionDepth(Math.max(sourceExpDepth,
				Math.max(bodyExpDepth, resultExpDepth)));

		increaseIteratorCount("iterate", metric);
	}

	/**
	 * Visits a {@link IteratorExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link IteratorExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitIteratorExp(IteratorExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getSource(), metric);
		int sourceExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getBody(), metric);
		int bodyExpDepth = metric.getExpressionDepth();

		metric.setExpressionDepth(Math.max(sourceExpDepth, bodyExpDepth));

		increaseIteratorCount(exp.getName(), metric);
	}

	/**
	 * Visits a {@link LetExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link LetExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitLetExp(LetExp exp, ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getIn(), metric);
		int inExpDepth = metric.getExpressionDepth();
		metric.setExpressionDepth(expDepth);

		visitVariable(exp.getVariable(), metric);
		int varExpDepth = metric.getExpressionDepth();

		metric.setExpressionDepth(Math.max(inExpDepth, varExpDepth));

		metric.setNumberOfLetExpressions(metric.getNumberOfLetExpressions() + 1);
	}

	/**
	 * Visits an {@link LiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link LiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitLiteralExp(LiteralExp exp,
			ConstraintMetric metric) {

		if (exp instanceof CollectionLiteralExp) {
			visitCollectionLiteralExp((CollectionLiteralExp) exp, metric);
		}

		else if (exp instanceof EnumLiteralExp) {
			visitEnumLiteralExp((EnumLiteralExp) exp, metric);
		}

		else if (exp instanceof InvalidLiteralExp) {
			visitInvalidLiteralExp((InvalidLiteralExp) exp, metric);
		}

		else if (exp instanceof PrimitiveLiteralExp) {
			visitPrimitiveLiteralExp((PrimitiveLiteralExp) exp, metric);
		}

		else if (exp instanceof TupleLiteralExp) {
			visitTupleLiteralExp((TupleLiteralExp) exp, metric);
		}

		else if (exp instanceof TypeLiteralExp) {
			visitTypeLiteralExp((TypeLiteralExp) exp, metric);
		}

		else if (exp instanceof UndefinedLiteralExp) {
			visitUndefinedLiteralExp((UndefinedLiteralExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits an {@link LoopExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link LoopExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitLoopExp(LoopExp exp, ConstraintMetric metric) {

		if (exp instanceof IterateExp) {
			visitIterateExp((IterateExp) exp, metric);
		}

		else if (exp instanceof IteratorExp) {
			visitIteratorExp((IteratorExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits an {@link NumericLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link NumericLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitNumericLiteralExp(NumericLiteralExp exp,
			ConstraintMetric metric) {

		if (exp instanceof IntegerLiteralExp) {
			visitIntegerLiteralExp((IntegerLiteralExp) exp, metric);
		}

		else if (exp instanceof RealLiteralExp) {
			visitRealLiteralExp((RealLiteralExp) exp, metric);
		}

		else if (exp instanceof UnlimitedNaturalExp) {
			visitUnlimitedNaturalExp((UnlimitedNaturalExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits a {@link OperationCallExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link OperationCallExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitOperationCallExp(OperationCallExp exp,
			ConstraintMetric metric) {

		if (metric.getCalledOperations() == null)
			metric.setCalledOperations(new HashMap<String, Integer>());
		// no else.

		String opName = "";

		if (exp.getReferredOperation() == null && exp.getName().equals("atPre"))
			opName = "OclAny@pre()";
		else {
			if (exp.getReferredOperation().getOwningType() != null)
				opName = exp.getReferredOperation().getOwningType()
						.getQualifiedName();
			// no else.

			/* Probably remove generics. */
			if (opName.contains("("))
				opName = opName.substring(0, opName.indexOf("("));
			// no else.

			opName += "." + exp.getReferredOperation().getName() + "(..)";
		}

		Integer oldCount = metric.getCalledOperations().get(opName);

		if (oldCount == null)
			oldCount = 0;
		// no else.

		oldCount++;
		metric.getCalledOperations().put(opName, oldCount);

		/* Count the expression. */
		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		visitOclExpression(exp.getSource(), metric);
		int maxDepth = metric.getExpressionDepth();

		for (OclExpression arg : exp.getArgument()) {
			metric.setExpressionDepth(expDepth);
			visitOclExpression(arg, metric);
			maxDepth = Math.max(maxDepth, metric.getExpressionDepth());
		}
		// end for.

		metric.setExpressionDepth(maxDepth);
	}

	/**
	 * Visits an {@link PrimitiveLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link PrimitiveLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitPrimitiveLiteralExp(PrimitiveLiteralExp exp,
			ConstraintMetric metric) {

		if (exp instanceof BooleanLiteralExp) {
			visitBooleanLiteralExp((BooleanLiteralExp) exp, metric);
		}

		else if (exp instanceof NumericLiteralExp) {
			visitNumericLiteralExp((NumericLiteralExp) exp, metric);
		}

		else if (exp instanceof StringLiteralExp) {
			visitStringLiteralExp((StringLiteralExp) exp, metric);
		}

		else
			throw new IllegalStateException("Visit of unknown expression: "
					+ exp.getClass().getCanonicalName());
	}

	/**
	 * Visits a {@link PropertyCallExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link PropertyCallExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitPropertyCallExp(PropertyCallExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		visitOclExpression(exp.getSource(), metric);

		/* Increase or set the property count. */
		if (metric.getCalledProperties() == null)
			metric.setCalledProperties(new HashMap<String, Integer>());
		// no else.

		String name = exp.getReferredProperty().getQualifiedName();
		Integer propertyCount = metric.getCalledProperties().get(name);
		if (propertyCount == null)
			propertyCount = 1;
		else
			propertyCount++;
		// end else.
		metric.getCalledProperties().put(name, propertyCount);
	}

	/**
	 * Visits a {@link RealLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link StringLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitRealLiteralExp(RealLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("Real", metric);
	}

	/**
	 * Visits a {@link StringLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link StringLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitStringLiteralExp(StringLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("String", metric);
	}

	/**
	 * Visits a {@link TupleLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link TupleLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitTupleLiteralExp(TupleLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		int expDepth = metric.getExpressionDepth() + 1;
		metric.setExpressionDepth(expDepth);

		int maxDepth = expDepth;

		for (TupleLiteralPart part : exp.getPart()) {
			metric.setExpressionDepth(expDepth);

			visitOclExpression(part.getValue(), metric);
			maxDepth = Math.max(maxDepth, metric.getExpressionDepth());
		}
		// end for.

		metric.setExpressionDepth(maxDepth);

		increaseLiteralCount("Tuple", metric);
		increaseLiteralCount("Tuple: " + exp.getType().getName(), metric);
	}

	/**
	 * Visits a {@link TypeLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link TypeLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitTypeLiteralExp(TypeLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("Type", metric);
		increaseLiteralCount("Type: "
				+ exp.getReferredType().getQualifiedName(), metric);
	}

	/**
	 * Visits a {@link UndefinedLiteralExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link UndefinedLiteralExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitUndefinedLiteralExp(UndefinedLiteralExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("null", metric);
	}

	/**
	 * Visits a {@link UnlimitedNaturalExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link UnlimitedNaturalExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitUnlimitedNaturalExp(UnlimitedNaturalExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		increaseLiteralCount("UnlimitedNatural", metric);
	}

	/**
	 * Visits a {@link Variable} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param var
	 *            The {@link Variable} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitVariable(Variable var, ConstraintMetric metric) {

		if (var.getInitExpression() != null)
			visitOclExpression(var.getInitExpression(), metric);
		// no else.
	}

	/**
	 * Visits a {@link VariableExp} during the computation of a
	 * {@link ConstraintMetric}.
	 * 
	 * @param exp
	 *            The {@link VariableExp} to be visited.
	 * @param metric
	 *            The {@link ConstraintMetric} that is currently computed.
	 */
	protected static void visitVariableExp(VariableExp exp,
			ConstraintMetric metric) {

		metric.setExpressionCount(metric.getExpressionCount() + 1);
		metric.setExpressionDepth(metric.getExpressionDepth() + 1);

		/* Variables must only be visited when used in let expressions. */
		// visitVariable(exp.getReferredVariable(), metric);
	}

	/**
	 * Helper method to increase the count for a given iterator's name.
	 * 
	 * @param name
	 *            The name of the iterator.
	 * @param metric
	 *            The {@link ConstraintMetric}.
	 */
	private static void increaseIteratorCount(String name,
			ConstraintMetric metric) {
		/* Increase or set the literal count. */
		if (metric.getUsedIterators() == null)
			metric.setUsedIterators(new HashMap<String, Integer>());
		// no else.

		Integer literalCount = metric.getUsedIterators().get(name);
		if (literalCount == null)
			literalCount = 1;
		else
			literalCount++;
		// end else.
		metric.getUsedIterators().put(name, literalCount);
	}

	/**
	 * Helper method to increase the count for a given literal's name.
	 * 
	 * @param name
	 *            The name of the literal.
	 * @param metric
	 *            The {@link ConstraintMetric}.
	 */
	private static void increaseLiteralCount(String name,
			ConstraintMetric metric) {
		/* Increase or set the literal count. */
		if (metric.getUsedLiterals() == null)
			metric.setUsedLiterals(new HashMap<String, Integer>());
		// no else.

		Integer literalCount = metric.getUsedLiterals().get(name);
		if (literalCount == null)
			literalCount = 1;
		else
			literalCount++;
		// end else.
		metric.getUsedLiterals().put(name, literalCount);
	}
}
