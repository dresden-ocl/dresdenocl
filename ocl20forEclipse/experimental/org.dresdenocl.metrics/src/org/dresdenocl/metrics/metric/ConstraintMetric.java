/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import java.util.Map;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Operation;

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
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetric#getReferredConstraint <em>Referred Constraint</em>}</li>
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

} // ConstraintMetric
