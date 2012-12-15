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
package org.dresdenocl.metrics.metric;

import java.util.Map;

import org.dresdenocl.pivotmodel.Constraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionCount <em>Expression Count</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionDepth <em>Expression Depth</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledOperations <em>Called Operations</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledProperties <em>Called Properties</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getReferredConstraint <em>Referred Constraint</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfIfExpressions <em>Number Of If Expressions</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfLetExpressions <em>Number Of Let Expressions</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedIterators <em>Used Iterators</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedLiterals <em>Used Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric()
 * @model
 * @generated
 */
public interface ConstraintMetric extends Metric {
	/**
	 * Returns the value of the '<em><b>Expression Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Count</em>' attribute.
	 * @see #setExpressionCount(int)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_ExpressionCount()
	 * @model
	 * @generated
	 */
	int getExpressionCount();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionCount <em>Expression Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression Count</em>' attribute.
	 * @see #getExpressionCount()
	 * @generated
	 */
	void setExpressionCount(int value);

	/**
	 * Returns the value of the '<em><b>Expression Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Depth</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Depth</em>' attribute.
	 * @see #setExpressionDepth(int)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_ExpressionDepth()
	 * @model
	 * @generated
	 */
	int getExpressionDepth();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionDepth <em>Expression Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression Depth</em>' attribute.
	 * @see #getExpressionDepth()
	 * @generated
	 */
	void setExpressionDepth(int value);

	/**
	 * Returns the value of the '<em><b>Called Operations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Operations</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Operations</em>' attribute.
	 * @see #setCalledOperations(Map)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_CalledOperations()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Integer> getCalledOperations();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledOperations <em>Called Operations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Operations</em>' attribute.
	 * @see #getCalledOperations()
	 * @generated
	 */
	void setCalledOperations(Map<String, Integer> value);

	/**
	 * Returns the value of the '<em><b>Called Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Properties</em>' attribute.
	 * @see #setCalledProperties(Map)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_CalledProperties()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Integer> getCalledProperties();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledProperties <em>Called Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Properties</em>' attribute.
	 * @see #getCalledProperties()
	 * @generated
	 */
	void setCalledProperties(Map<String, Integer> value);

	/**
	 * Returns the value of the '<em><b>Referred Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Constraint</em>' reference.
	 * @see #setReferredConstraint(Constraint)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_ReferredConstraint()
	 * @model
	 * @generated
	 */
	Constraint getReferredConstraint();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getReferredConstraint <em>Referred Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Constraint</em>' reference.
	 * @see #getReferredConstraint()
	 * @generated
	 */
	void setReferredConstraint(Constraint value);

	/**
	 * Returns the value of the '<em><b>Number Of If Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of If Expressions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of If Expressions</em>' attribute.
	 * @see #setNumberOfIfExpressions(int)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_NumberOfIfExpressions()
	 * @model
	 * @generated
	 */
	int getNumberOfIfExpressions();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfIfExpressions <em>Number Of If Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of If Expressions</em>' attribute.
	 * @see #getNumberOfIfExpressions()
	 * @generated
	 */
	void setNumberOfIfExpressions(int value);

	/**
	 * Returns the value of the '<em><b>Number Of Let Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Let Expressions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Let Expressions</em>' attribute.
	 * @see #setNumberOfLetExpressions(int)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_NumberOfLetExpressions()
	 * @model
	 * @generated
	 */
	int getNumberOfLetExpressions();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfLetExpressions <em>Number Of Let Expressions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Let Expressions</em>' attribute.
	 * @see #getNumberOfLetExpressions()
	 * @generated
	 */
	void setNumberOfLetExpressions(int value);

	/**
	 * Returns the value of the '<em><b>Used Iterators</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Iterators</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Iterators</em>' attribute.
	 * @see #setUsedIterators(Map)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_UsedIterators()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Integer> getUsedIterators();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedIterators <em>Used Iterators</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Iterators</em>' attribute.
	 * @see #getUsedIterators()
	 * @generated
	 */
	void setUsedIterators(Map<String, Integer> value);

	/**
	 * Returns the value of the '<em><b>Used Literals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Literals</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Literals</em>' attribute.
	 * @see #setUsedLiterals(Map)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetric_UsedLiterals()
	 * @model transient="true"
	 * @generated
	 */
	Map<String, Integer> getUsedLiterals();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedLiterals <em>Used Literals</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Used Literals</em>' attribute.
	 * @see #getUsedLiterals()
	 * @generated
	 */
	void setUsedLiterals(Map<String, Integer> value);

} // ConstraintMetric
