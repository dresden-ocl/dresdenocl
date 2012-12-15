package org.dresdenocl.util.deft;

import java.util.Comparator;

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
import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Expression;

/**
 * {@link Comparator} implementation for {@link Constraint}s that check if two
 * {@link Constraint}
 * 
 * <ol>
 * <li>Have the same abstract syntax structure (after transformation to
 * {@link Expression}s),</li>
 * <li>Have the same references to the model (e.g., call the same operations,
 * properties, etc.</li>
 * </ol>
 * 
 * @author Claas Wilke
 * 
 */
public class ConstraintComparator implements Comparator<Constraint> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Constraint o1, Constraint o2) {

		int result = 0;

		result = o1.getKind().compareTo(o2.getKind());

		if (result == 0)
			result = o1.getQualifiedName().compareTo(o2.getQualifiedName());
		// no else.

		if (result == 0)
			result = o1.getOwner().getQualifiedName()
					.compareTo(o2.getOwner().getQualifiedName());
		// no else.

		if (result == 0)
			result = compareOclExpression(
					((ExpressionInOcl) o1.getSpecification())
							.getBodyExpression(),
					((ExpressionInOcl) o2.getSpecification())
							.getBodyExpression());
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link OclExpression}s.
	 * 
	 * @param exp1
	 *            The first {@link OclExpression}.
	 * @param exp2
	 *            The second {@link OclExpression}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareOclExpression(OclExpression exp1, OclExpression exp2) {

		if (exp1 instanceof CallExp && exp2 instanceof CallExp) {
			return compareCallExp((CallExp) exp1, (CallExp) exp2);
		}

		else if (exp1 instanceof IfExp && exp2 instanceof IfExp) {
			return compareIfExp((IfExp) exp1, (IfExp) exp2);
		}

		else if (exp1 instanceof LetExp && exp2 instanceof LetExp) {
			return compareLetExp((LetExp) exp1, (LetExp) exp2);
		}

		else if (exp1 instanceof LiteralExp && exp2 instanceof LiteralExp) {
			return compareLiteralExp((LiteralExp) exp1, (LiteralExp) exp2);
		}

		else if (exp1 instanceof VariableExp && exp2 instanceof VariableExp) {
			return compareVariableExp((VariableExp) exp1, (VariableExp) exp2);
		}

		else
			return exp1.getClass().getCanonicalName()
					.compareTo(exp2.getClass().getCanonicalName());
	}

	/**
	 * Compares two given {@link BooleanLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link BooleanLiteralExp}.
	 * @param exp2
	 *            The second {@link BooleanLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareBooleanLiteralExp(BooleanLiteralExp exp1,
			BooleanLiteralExp exp2) {

		return new Boolean(exp1.isBooleanSymbol()).compareTo(new Boolean(exp2
				.isBooleanSymbol()));
	}

	/**
	 * Compares two given {@link CallExp}s.
	 * 
	 * @param exp1
	 *            The first {@link CallExp}.
	 * @param exp2
	 *            The second {@link CallExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareCallExp(CallExp exp1, CallExp exp2) {

		if (exp1 instanceof FeatureCallExp && exp2 instanceof FeatureCallExp) {
			return compareFeatureCallExp((FeatureCallExp) exp1,
					(FeatureCallExp) exp2);
		}

		else if (exp1 instanceof LoopExp && exp1 instanceof LoopExp) {
			return compareLoopExp((LoopExp) exp1, (LoopExp) exp2);
		}

		else
			return exp1.getClass().getCanonicalName()
					.compareTo(exp2.getClass().getCanonicalName());
	}

	/**
	 * Compares two given {@link CollectionItem}s.
	 * 
	 * @param item1
	 *            The first {@link CollectionItem}.
	 * @param item2
	 *            The second {@link CollectionItem}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareCollectionItem(CollectionItem item1,
			CollectionItem item2) {

		return compareOclExpression(item1.getItem(), item2.getItem());
	}

	/**
	 * Compares two given {@link CollectionRange}s.
	 * 
	 * @param range1
	 *            The first {@link CollectionRange}.
	 * @param range2
	 *            The second {@link CollectionRange}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareCollectionRange(CollectionRange range1,
			CollectionRange range2) {

		int result = 0;

		result = compareOclExpression(range1.getFirst(), range2.getFirst());

		if (result == 0)
			result = compareOclExpression(range1.getLast(), range2.getLast());
		// no else

		return result;
	}

	/**
	 * Compares two given {@link CollectionLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link CollectionLiteralExp}.
	 * @param exp2
	 *            The second {@link CollectionLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareCollectionLiteralExp(CollectionLiteralExp exp1,
			CollectionLiteralExp exp2) {

		int result = 0;

		result = exp1.getType().getQualifiedName()
				.compareTo(exp2.getType().getQualifiedName());

		if (result == 0)
			result = new Integer(exp1.getPart().size()).compareTo(new Integer(
					exp2.getPart().size()));
		// no else.

		if (result == 0) {

			for (int index = 0; index < exp1.getPart().size(); index++) {
				result = compareCollectionLiteralPart(
						exp1.getPart().get(index), exp2.getPart().get(index));

				if (result != 0)
					break;
				// no else.
			}
			// end for.
		}

		return result;
	}

	/**
	 * Compares two given {@link CollectionLiteralPart}s.
	 * 
	 * @param part1
	 *            The first {@link CollectionLiteralPart}.
	 * @param part2
	 *            The second {@link CollectionLiteralPart}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareCollectionLiteralPart(CollectionLiteralPart part1,
			CollectionLiteralPart part2) {

		if (part1 instanceof CollectionItem && part2 instanceof CollectionItem)
			return compareCollectionItem((CollectionItem) part1,
					(CollectionItem) part2);

		else if (part1 instanceof CollectionRange
				&& part2 instanceof CollectionRange)
			return compareCollectionRange((CollectionRange) part1,
					(CollectionRange) part2);

		else
			return part1.getClass().getCanonicalName()
					.compareTo(part2.getClass().getCanonicalName());
	}

	/**
	 * Compares two given {@link EnumLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link EnumLiteralExp}.
	 * @param exp2
	 *            The second {@link EnumLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareEnumLiteralExp(EnumLiteralExp exp1, EnumLiteralExp exp2) {

		return exp1.getReferredEnumLiteral().getQualifiedName()
				.compareTo(exp2.getReferredEnumLiteral().getQualifiedName());
	}

	/**
	 * Compares two given {@link FeatureCallExp}s.
	 * 
	 * @param exp1
	 *            The first {@link FeatureCallExp}.
	 * @param exp2
	 *            The second {@link FeatureCallExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareFeatureCallExp(FeatureCallExp exp1, FeatureCallExp exp2) {

		if (exp1 instanceof OperationCallExp
				&& exp2 instanceof OperationCallExp) {
			return compareOperationCallExp((OperationCallExp) exp1,
					(OperationCallExp) exp2);
		}

		else if (exp1 instanceof PropertyCallExp
				&& exp2 instanceof PropertyCallExp) {
			return comparePropertyCallExp((PropertyCallExp) exp1,
					(PropertyCallExp) exp2);
		}

		else
			return exp1.getClass().getCanonicalName()
					.compareTo(exp2.getClass().getCanonicalName());
	}

	/**
	 * Compares two given {@link IfExp}s.
	 * 
	 * @param exp1
	 *            The first {@link IfExp}.
	 * @param exp2
	 *            The second {@link IfExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareIfExp(IfExp exp1, IfExp exp2) {

		int result = 0;

		result = compareOclExpression(exp1.getCondition(), exp2.getCondition());

		if (result == 0)
			result = compareOclExpression(exp1.getThenExpression(),
					exp2.getThenExpression());
		// no else.

		if (result == 0)
			result = compareOclExpression(exp1.getElseExpression(),
					exp2.getElseExpression());
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link IntegerLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link IntegerLiteralExp}.
	 * @param exp2
	 *            The second {@link IntegerLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareIntegerLiteralExp(IntegerLiteralExp exp1,
			IntegerLiteralExp exp2) {

		return new Integer(exp1.getIntegerSymbol()).compareTo(new Integer(exp2
				.getIntegerSymbol()));
	}

	/**
	 * Compares two given {@link InvalidLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link InvalidLiteralExp}.
	 * @param exp2
	 *            The second {@link InvalidLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareInvalidLiteralExp(InvalidLiteralExp exp1,
			InvalidLiteralExp exp2) {

		return 0;
	}

	/**
	 * Compares two given {@link IterateExp}s.
	 * 
	 * @param exp1
	 *            The first {@link IterateExp}.
	 * @param exp2
	 *            The second {@link IterateExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareIterateExp(IterateExp exp1, IterateExp exp2) {

		int result = 0;

		result = new Integer(exp1.getIterator().size()).compareTo(new Integer(
				exp2.getIterator().size()));

		if (result == 0) {
			compareOclExpression(exp1.getSource(), exp2.getSource());
		}
		// no else.

		if (result == 0) {
			for (int index = 0; index < exp1.getIterator().size(); index++) {
				result = compareVariable(exp1.getIterator().get(index), exp2
						.getIterator().get(index));
			}
		}
		// no else.

		if (result == 0)
			result = compareOclExpression(exp1.getBody(), exp2.getBody());
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link IteratorExp}s.
	 * 
	 * @param exp1
	 *            The first {@link IteratorExp}.
	 * @param exp2
	 *            The second {@link IteratorExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareIteratorExp(IteratorExp exp1, IteratorExp exp2) {

		int result = 0;

		result = exp1.getName().compareTo(exp2.getName());

		if (result == 0) {
			result = new Integer(exp1.getIterator().size())
					.compareTo(new Integer(exp2.getIterator().size()));
		}

		if (result == 0) {
			return compareOclExpression(exp1.getSource(), exp2.getSource());
		}
		// no else.

		if (result == 0) {
			for (int index = 0; index < exp1.getIterator().size(); index++) {
				result = compareVariable(exp1.getIterator().get(index), exp2
						.getIterator().get(index));
			}
		}
		// no else.

		if (result == 0)
			result = compareOclExpression(exp1.getBody(), exp2.getBody());
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link LetExp}s.
	 * 
	 * @param exp1
	 *            The first {@link LetExp}.
	 * @param exp2
	 *            The second {@link LetExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareLetExp(LetExp exp1, LetExp exp2) {

		return compareVariable(exp1.getVariable(), exp2.getVariable());
	}

	/**
	 * Compares two given {@link LiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link LiteralExp}.
	 * @param exp2
	 *            The second {@link LiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareLiteralExp(LiteralExp exp1, LiteralExp exp2) {

		if (exp1 instanceof CollectionLiteralExp) {
			return compareCollectionLiteralExp((CollectionLiteralExp) exp1,
					(CollectionLiteralExp) exp1);
		}

		else if (exp1 instanceof EnumLiteralExp
				&& exp2 instanceof EnumLiteralExp) {
			return compareEnumLiteralExp((EnumLiteralExp) exp1,
					(EnumLiteralExp) exp2);
		}

		else if (exp1 instanceof InvalidLiteralExp
				&& exp2 instanceof InvalidLiteralExp) {
			return compareInvalidLiteralExp((InvalidLiteralExp) exp1,
					(InvalidLiteralExp) exp2);
		}

		else if (exp1 instanceof PrimitiveLiteralExp
				&& exp2 instanceof PrimitiveLiteralExp) {
			return comparePrimitiveLiteralExp((PrimitiveLiteralExp) exp1,
					(PrimitiveLiteralExp) exp2);
		}

		else if (exp1 instanceof TupleLiteralExp
				&& exp2 instanceof TupleLiteralExp) {
			return compareTupleLiteralExp((TupleLiteralExp) exp1,
					(TupleLiteralExp) exp2);
		}

		else if (exp1 instanceof TypeLiteralExp
				&& exp2 instanceof TypeLiteralExp) {
			return compareTypeLiteralExp((TypeLiteralExp) exp1,
					(TypeLiteralExp) exp2);
		}

		else if (exp1 instanceof UndefinedLiteralExp
				&& exp2 instanceof UndefinedLiteralExp) {
			return compareUndefinedLiteralExp((UndefinedLiteralExp) exp1,
					(UndefinedLiteralExp) exp2);
		}

		else
			return -1;
	}

	/**
	 * Compares two given {@link LoopExp}s.
	 * 
	 * @param exp1
	 *            The first {@link LoopExp}.
	 * @param exp2
	 *            The second {@link LoopExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareLoopExp(LoopExp exp1, LoopExp exp2) {

		if (exp1 instanceof IterateExp && exp2 instanceof IterateExp) {
			return compareIterateExp((IterateExp) exp1, (IterateExp) exp2);
		}

		else if (exp1 instanceof IteratorExp && exp2 instanceof IteratorExp) {
			return compareIteratorExp((IteratorExp) exp1, (IteratorExp) exp2);
		}

		else
			return -1;
	}

	/**
	 * Compares two given {@link NumericLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link NumericLiteralExp}.
	 * @param exp2
	 *            The second {@link NumericLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareNumericLiteralExp(NumericLiteralExp exp1,
			NumericLiteralExp exp2) {

		if (exp1 instanceof IntegerLiteralExp
				&& exp2 instanceof IntegerLiteralExp) {
			return compareIntegerLiteralExp((IntegerLiteralExp) exp1,
					(IntegerLiteralExp) exp1);
		}

		else if (exp1 instanceof RealLiteralExp
				&& exp2 instanceof RealLiteralExp) {
			return compareRealLiteralExp((RealLiteralExp) exp1,
					(RealLiteralExp) exp1);
		}

		else if (exp1 instanceof UnlimitedNaturalExp
				&& exp2 instanceof UnlimitedNaturalExp) {
			return compareUnlimitedNaturalExp((UnlimitedNaturalExp) exp1,
					(UnlimitedNaturalExp) exp1);
		}

		else
			return -1;
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
	protected int compareOperationCallExp(OperationCallExp exp1,
			OperationCallExp exp2) {

		int result = 0;

		result = exp1.getReferredOperation().getQualifiedName()
				.compareTo(exp2.getReferredOperation().getQualifiedName());

		if (result == 0)
			exp1.getReferredOperation()
					.getType()
					.getQualifiedName()
					.compareTo(
							exp2.getReferredOperation().getType()
									.getQualifiedName());
		// no else.

		if (result == 0)
			new Integer(exp1.getReferredOperation().getInputParameter().size())
					.compareTo(new Integer(exp2.getReferredOperation()
							.getInputParameter().size()));
		// no else.

		if (result == 0) {
			for (int index = 0; index < exp1.getReferredOperation()
					.getInputParameter().size(); index++) {
				result = exp1
						.getReferredOperation()
						.getInputParameter()
						.get(index)
						.getName()
						.compareTo(
								exp2.getReferredOperation().getInputParameter()
										.get(index).getName());

				result = exp1
						.getReferredOperation()
						.getInputParameter()
						.get(index)
						.getName()
						.compareTo(
								exp2.getReferredOperation().getInputParameter()
										.get(index).getName());

				if (result == 0)
					result = exp1
							.getReferredOperation()
							.getInputParameter()
							.get(index)
							.getType()
							.getQualifiedName()
							.compareTo(
									exp2.getReferredOperation()
											.getInputParameter().get(index)
											.getType().getQualifiedName());
				// no else.

				if (result != 0)
					break;
				// no else.
			}
		}
		// no else.

		if (result == 0) {
			compareOclExpression(exp1.getSource(), exp2.getSource());
		}
		// no else.

		if (result == 0)
			result = new Integer(exp1.getArgument().size())
					.compareTo(new Integer(exp2.getArgument().size()));
		// no else.

		if (result == 0) {
			for (int index = 0; index < exp1.getArgument().size(); index++) {
				result = compareOclExpression(exp1.getArgument().get(index),
						exp2.getArgument().get(index));
				if (result != 0)
					break;
				// no else.
			}
		}
		// no else.
		return result;
	}

	/**
	 * Compares two given {@link PrimitiveLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link PrimitiveLiteralExp}.
	 * @param exp2
	 *            The second {@link PrimitiveLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int comparePrimitiveLiteralExp(PrimitiveLiteralExp exp1,
			PrimitiveLiteralExp exp2) {

		if (exp1 instanceof BooleanLiteralExp
				&& exp1 instanceof BooleanLiteralExp) {
			return compareBooleanLiteralExp((BooleanLiteralExp) exp1,
					(BooleanLiteralExp) exp2);
		}

		else if (exp1 instanceof NumericLiteralExp
				&& exp2 instanceof NumericLiteralExp) {
			return compareNumericLiteralExp((NumericLiteralExp) exp1,
					(NumericLiteralExp) exp1);
		}

		else if (exp1 instanceof StringLiteralExp
				&& exp2 instanceof StringLiteralExp) {
			return compareStringLiteralExp((StringLiteralExp) exp1,
					(StringLiteralExp) exp1);
		}

		else
			return -1;
	}

	/**
	 * Compares two given {@link PropertyCallExp}s.
	 * 
	 * @param exp1
	 *            The first {@link PropertyCallExp}.
	 * @param exp2
	 *            The second {@link PropertyCallExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int comparePropertyCallExp(PropertyCallExp exp1,
			PropertyCallExp exp2) {

		int result = 0;

		result = exp1.getReferredProperty().getQualifiedName()
				.compareTo(exp2.getReferredProperty().getQualifiedName());

		if (result == 0)
			exp1.getReferredProperty()
					.getType()
					.getQualifiedName()
					.compareTo(
							exp2.getReferredProperty().getType()
									.getQualifiedName());
		// no else.

		if (result == 0) {
			result = compareOclExpression(exp1.getSource(), exp2.getSource());
		}
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link RealLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link RealLiteralExp}.
	 * @param exp2
	 *            The second {@link RealLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareRealLiteralExp(RealLiteralExp exp1, RealLiteralExp exp2) {

		return new Float(exp1.getRealSymbol()).compareTo(new Float(exp2
				.getRealSymbol()));
	}

	/**
	 * Compares two given {@link StringLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link StringLiteralExp}.
	 * @param exp2
	 *            The second {@link StringLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareStringLiteralExp(StringLiteralExp exp1,
			StringLiteralExp exp2) {

		return exp1.getStringSymbol().compareTo(exp2.getStringSymbol());
	}

	/**
	 * Compares two given {@link TupleLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link TupleLiteralExp}.
	 * @param exp2
	 *            The second {@link TupleLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareTupleLiteralExp(TupleLiteralExp exp1,
			TupleLiteralExp exp2) {
		int result = 0;
		exp1.getType().getQualifiedName()
				.compareTo(exp2.getType().getQualifiedName());

		if (result == 0)
			result = new Integer(exp1.getPart().size()).compareTo(new Integer(
					exp2.getPart().size()));
		// no else.

		if (result == 0) {
			for (int index = 0; index < exp1.getPart().size(); index++) {
				result = exp1.getPart().get(index).getName()
						.compareTo(exp2.getPart().get(index).getName());

				result = exp1
						.getPart()
						.get(index)
						.getType()
						.getQualifiedName()
						.compareTo(
								exp2.getPart().get(index).getType()
										.getQualifiedName());

				if (result == 0)
					result = compareOclExpression(exp1.getPart().get(index)
							.getValue(), exp2.getPart().get(index).getValue());
				// no else.

				if (result != 0)
					break;
				// no else.
			}
		}
		// no else.

		return result;
	}

	/**
	 * Compares two given {@link TypeLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link TypeLiteralExp}.
	 * @param exp2
	 *            The second {@link TypeLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareTypeLiteralExp(TypeLiteralExp exp1, TypeLiteralExp exp2) {

		return exp1.getReferredType().getQualifiedName()
				.compareTo(exp2.getReferredType().getQualifiedName());
	}

	/**
	 * Compares two given {@link UndefinedLiteralExp}s.
	 * 
	 * @param exp1
	 *            The first {@link UndefinedLiteralExp}.
	 * @param exp2
	 *            The second {@link UndefinedLiteralExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareUndefinedLiteralExp(UndefinedLiteralExp exp1,
			UndefinedLiteralExp exp2) {

		return 0;
	}

	/**
	 * Compares two given {@link UnlimitedNaturalExp}s.
	 * 
	 * @param exp1
	 *            The first {@link UnlimitedNaturalExp}.
	 * @param exp2
	 *            The second {@link UnlimitedNaturalExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareUnlimitedNaturalExp(UnlimitedNaturalExp exp1,
			UnlimitedNaturalExp exp2) {

		return 0;
	}

	/**
	 * Compares two given {@link Variable}s.
	 * 
	 * @param var1
	 *            The first {@link Variable}.
	 * @param var2
	 *            The second {@link Variable}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareVariable(Variable var1, Variable var2) {

		int result = 0;

		result = var1.getName().compareTo(var2.getName());

		if (result == 0)
			result = var1.getType().getQualifiedName()
					.compareTo(var1.getType().getQualifiedName());
		// no else.

		if (var1.getInitExpression() != null
				&& var2.getInitExpression() != null) {
			if (result == 0) {
				compareOclExpression(var1.getInitExpression(),
						var2.getInitExpression());
			}
			// no else.
		}

		/* exp1 is not null but exp2 is. */
		else if (var1.getInitExpression() != null)
			return 1;
		/* both are null */
		else if (var2.getInitExpression() == null)
			return 0;
		/* exp2 is not null but exp1 is. */
		else
			return -1;

		return result;
	}

	/**
	 * Compares two given {@link VariableExp}s.
	 * 
	 * @param exp1
	 *            The first {@link VariableExp}.
	 * @param exp2
	 *            The second {@link VariableExp}.
	 * @return -1,0, or 1 according to comparison.
	 */
	protected int compareVariableExp(VariableExp exp1, VariableExp exp2) {

		return compareVariable(exp1.getReferredVariable(),
				exp2.getReferredVariable());
	}
}
