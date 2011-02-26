/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Count</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintCount#getKind <em>Kind</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintCount#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintCount()
 * @model
 * @generated
 */
public interface ConstraintCount extends EObject {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link tudresden.ocl20.pivot.pivotmodel.ConstraintKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see tudresden.ocl20.pivot.pivotmodel.ConstraintKind
	 * @see #setKind(ConstraintKind)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintCount_Kind()
	 * @model
	 * @generated
	 */
	ConstraintKind getKind();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintCount#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see tudresden.ocl20.pivot.pivotmodel.ConstraintKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ConstraintKind value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintCount_Count()
	 * @model
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintCount#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

} // ConstraintCount
