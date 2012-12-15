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
package org.dresdenocl.metrics.metric.impl;

import java.util.Map;

import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.MetricPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.pivotmodel.Constraint;

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
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getCalledProperties <em>Called Properties</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getReferredConstraint <em>Referred Constraint</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getNumberOfIfExpressions <em>Number Of If Expressions</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getNumberOfLetExpressions <em>Number Of Let Expressions</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getUsedIterators <em>Used Iterators</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl#getUsedLiterals <em>Used Literals</em>}</li>
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
	 * The cached value of the '{@link #getCalledProperties() <em>Called Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledProperties()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Integer> calledProperties;

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
	 * The default value of the '{@link #getNumberOfIfExpressions() <em>Number Of If Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfIfExpressions()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_IF_EXPRESSIONS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfIfExpressions() <em>Number Of If Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfIfExpressions()
	 * @generated
	 * @ordered
	 */
	protected int numberOfIfExpressions = NUMBER_OF_IF_EXPRESSIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberOfLetExpressions() <em>Number Of Let Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfLetExpressions()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_LET_EXPRESSIONS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfLetExpressions() <em>Number Of Let Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfLetExpressions()
	 * @generated
	 * @ordered
	 */
	protected int numberOfLetExpressions = NUMBER_OF_LET_EXPRESSIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUsedIterators() <em>Used Iterators</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedIterators()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Integer> usedIterators;

	/**
	 * The cached value of the '{@link #getUsedLiterals() <em>Used Literals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedLiterals()
	 * @generated
	 * @ordered
	 */
	protected Map<String, Integer> usedLiterals;

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
	public Map<String, Integer> getCalledProperties() {
		return calledProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledProperties(Map<String, Integer> newCalledProperties) {
		Map<String, Integer> oldCalledProperties = calledProperties;
		calledProperties = newCalledProperties;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__CALLED_PROPERTIES, oldCalledProperties, calledProperties));
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
	public int getNumberOfIfExpressions() {
		return numberOfIfExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfIfExpressions(int newNumberOfIfExpressions) {
		int oldNumberOfIfExpressions = numberOfIfExpressions;
		numberOfIfExpressions = newNumberOfIfExpressions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS, oldNumberOfIfExpressions, numberOfIfExpressions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfLetExpressions() {
		return numberOfLetExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfLetExpressions(int newNumberOfLetExpressions) {
		int oldNumberOfLetExpressions = numberOfLetExpressions;
		numberOfLetExpressions = newNumberOfLetExpressions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS, oldNumberOfLetExpressions, numberOfLetExpressions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Integer> getUsedIterators() {
		return usedIterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedIterators(Map<String, Integer> newUsedIterators) {
		Map<String, Integer> oldUsedIterators = usedIterators;
		usedIterators = newUsedIterators;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__USED_ITERATORS, oldUsedIterators, usedIterators));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Integer> getUsedLiterals() {
		return usedLiterals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsedLiterals(Map<String, Integer> newUsedLiterals) {
		Map<String, Integer> oldUsedLiterals = usedLiterals;
		usedLiterals = newUsedLiterals;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.CONSTRAINT_METRIC__USED_LITERALS, oldUsedLiterals, usedLiterals));
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
			case MetricPackage.CONSTRAINT_METRIC__CALLED_PROPERTIES:
				return getCalledProperties();
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				if (resolve) return getReferredConstraint();
				return basicGetReferredConstraint();
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS:
				return getNumberOfIfExpressions();
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS:
				return getNumberOfLetExpressions();
			case MetricPackage.CONSTRAINT_METRIC__USED_ITERATORS:
				return getUsedIterators();
			case MetricPackage.CONSTRAINT_METRIC__USED_LITERALS:
				return getUsedLiterals();
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
			case MetricPackage.CONSTRAINT_METRIC__CALLED_PROPERTIES:
				setCalledProperties((Map<String, Integer>)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				setReferredConstraint((Constraint)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS:
				setNumberOfIfExpressions((Integer)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS:
				setNumberOfLetExpressions((Integer)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__USED_ITERATORS:
				setUsedIterators((Map<String, Integer>)newValue);
				return;
			case MetricPackage.CONSTRAINT_METRIC__USED_LITERALS:
				setUsedLiterals((Map<String, Integer>)newValue);
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
			case MetricPackage.CONSTRAINT_METRIC__CALLED_PROPERTIES:
				setCalledProperties((Map<String, Integer>)null);
				return;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				setReferredConstraint((Constraint)null);
				return;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS:
				setNumberOfIfExpressions(NUMBER_OF_IF_EXPRESSIONS_EDEFAULT);
				return;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS:
				setNumberOfLetExpressions(NUMBER_OF_LET_EXPRESSIONS_EDEFAULT);
				return;
			case MetricPackage.CONSTRAINT_METRIC__USED_ITERATORS:
				setUsedIterators((Map<String, Integer>)null);
				return;
			case MetricPackage.CONSTRAINT_METRIC__USED_LITERALS:
				setUsedLiterals((Map<String, Integer>)null);
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
			case MetricPackage.CONSTRAINT_METRIC__CALLED_PROPERTIES:
				return calledProperties != null;
			case MetricPackage.CONSTRAINT_METRIC__REFERRED_CONSTRAINT:
				return referredConstraint != null;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS:
				return numberOfIfExpressions != NUMBER_OF_IF_EXPRESSIONS_EDEFAULT;
			case MetricPackage.CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS:
				return numberOfLetExpressions != NUMBER_OF_LET_EXPRESSIONS_EDEFAULT;
			case MetricPackage.CONSTRAINT_METRIC__USED_ITERATORS:
				return usedIterators != null;
			case MetricPackage.CONSTRAINT_METRIC__USED_LITERALS:
				return usedLiterals != null;
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
		result.append(", calledProperties: ");
		result.append(calledProperties);
		result.append(", numberOfIfExpressions: ");
		result.append(numberOfIfExpressions);
		result.append(", numberOfLetExpressions: ");
		result.append(numberOfLetExpressions);
		result.append(", usedIterators: ");
		result.append(usedIterators);
		result.append(", usedLiterals: ");
		result.append(usedLiterals);
		result.append(')');
		return result.toString();
	}

} //ConstraintMetricImpl
