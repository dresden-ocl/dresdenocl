/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric.impl;

import java.util.Map;

import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.MetricPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getExpressionCount <em>Expression Count</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getExpressionDepth <em>Expression Depth</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getCalledOperations <em>Called Operations</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getReferredConstraint <em>Referred Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintMetricImpl extends MetricImpl implements ConstraintMetric {
	/**
	 * The default value of the '{@link #getExpressionCount() <em>Expression Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionCount()
	 * @generated
	 * @ordered
	 */
	protected static final int EXPRESSION_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExpressionCount() <em>Expression Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionCount()
	 * @generated
	 * @ordered
	 */
	protected int expressionCount = EXPRESSION_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getExpressionDepth() <em>Expression Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int EXPRESSION_DEPTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExpressionDepth() <em>Expression Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionDepth()
	 * @generated
	 * @ordered
	 */
	protected int expressionDepth = EXPRESSION_DEPTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCalledOperations() <em>Called Operations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledOperations()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Integer> calledOperations;

	/**
	 * The cached value of the '{@link #getReferredConstraint() <em>Referred Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredConstraint()
	 * @generated
	 * @ordered
	 */
	protected Constraint referredConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintMetricImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricPackage.Literals.CONSTRAINT_METRIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExpressionCount() {
		return expressionCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpressionCount(int newExpressionCount) {
		int oldExpressionCount = expressionCount;
		expressionCount = newExpressionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__EXPRESSION_COUNT, oldExpressionCount, expressionCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExpressionDepth() {
		return expressionDepth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpressionDepth(int newExpressionDepth) {
		int oldExpressionDepth = expressionDepth;
		expressionDepth = newExpressionDepth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__EXPRESSION_DEPTH, oldExpressionDepth, expressionDepth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Integer> getCalledOperations() {
		return calledOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledOperations(Map<String, Integer> newCalledOperations) {
		Map<String, Integer> oldCalledOperations = calledOperations;
		calledOperations = newCalledOperations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__CALLED_OPERATIONS, oldCalledOperations, calledOperations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint getReferredConstraint() {
		if (referredConstraint != null && referredConstraint.eIsProxy()) {
			InternalEObject oldReferredConstraint = (InternalEObject)referredConstraint;
			referredConstraint = (Constraint)eResolveProxy(oldReferredConstraint);
			if (referredConstraint != oldReferredConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT, oldReferredConstraint, referredConstraint));
			}
		}
		return referredConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint basicGetReferredConstraint() {
		return referredConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferredConstraint(Constraint newReferredConstraint) {
		Constraint oldReferredConstraint = referredConstraint;
		referredConstraint = newReferredConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT, oldReferredConstraint, referredConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_COUNT:
				return getExpressionCount();
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_DEPTH:
				return getExpressionDepth();
			case MetricPackage.CONSTRAINT_METRIC__CALLED_OPERATIONS:
				return getCalledOperations();
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				if (resolve) return getReferredConstraint();
				return basicGetReferredConstraint();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_COUNT:
				setExpressionCount((Integer)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_DEPTH:
				setExpressionDepth((Integer)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__CALLED_OPERATIONS:
				setCalledOperations((Map<String, Integer>)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				setReferredConstraint((Constraint)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_COUNT:
				setExpressionCount(EXPRESSION_COUNT_EDEFAULT);
				return;
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_DEPTH:
				setExpressionDepth(EXPRESSION_DEPTH_EDEFAULT);
				return;
			case MetricPackage.CONSTRAINT_METRIC__CALLED_OPERATIONS:
				setCalledOperations((Map<String, Integer>)null);
				return;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				setReferredConstraint((Constraint)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_COUNT:
				return expressionCount != EXPRESSION_COUNT_EDEFAULT;
			case MetricPackage.CONSTRAINT_METRIC__EXPRESSION_DEPTH:
				return expressionDepth != EXPRESSION_DEPTH_EDEFAULT;
			case MetricPackage.CONSTRAINT_METRIC__CALLED_OPERATIONS:
				return calledOperations != null;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				return referredConstraint != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (expressionCount: ");
		result.append(expressionCount);
		result.append(", expressionDepth: ");
		result.append(expressionDepth);
		result.append(", calledOperations: ");
		result.append(calledOperations);
		result.append(')');
		return result.toString();
	}

} //ConstraintMetricImpl
