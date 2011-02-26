package org.dresdenocl.metrics;

import java.util.HashMap;

import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.Metric;
import org.dresdenocl.metrics.metric.MetricFactory;
import org.dresdenocl.metrics.metric.ModelMetric;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IterateExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LoopExp;
import tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp;
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
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

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
	 * Computes a {@link Metric} for a given {@link IModel}.
	 * 
	 * @param model
	 *            The {@link IModel}.
	 * @return The computed {@link Metric}.
	 */
	public static ModelMetric computeMetric(IModel model)
			throws ModelAccessException {

		ModelMetric result = MetricFactory.eINSTANCE.createModelMetric();
		result.setReferredModelId(model.getDisplayName());

		for (Constraint constraint : model.getConstraints()) {
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

		if (exp.getReferredOperation().getOwningType() != null)
			opName = exp.getReferredOperation().getOwningType()
					.getQualifiedName();
		// no else.

		/* Probably remove generics. */
		if (opName.contains("("))
			opName = opName.substring(0, opName.indexOf("("));
		// no else.

		opName += "." + exp.getReferredOperation().getName() + "(..)";

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

		visitVariable(exp.getReferredVariable(), metric);
	}
}
