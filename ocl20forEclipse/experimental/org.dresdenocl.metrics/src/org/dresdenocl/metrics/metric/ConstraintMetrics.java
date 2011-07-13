/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import java.util.Map;

import org.eclipse.emf.common.util.EList;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint Metrics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraintMetrics <em>Constraint Metrics</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getNumberOfConstraintsByKind <em>Number Of Constraints By Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetrics()
 * @model
 * @generated
 */
public interface ConstraintMetrics extends ConstraintMetric {
	/**
	 * Returns the value of the '<em><b>Constraint Metrics</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.metrics.metric.ConstraintMetric}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Metrics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Metrics</em>' containment reference list.
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetrics_ConstraintMetrics()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstraintMetric> getConstraintMetrics();

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.pivotmodel.Constraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' reference list.
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetrics_Constraints()
	 * @model
	 * @generated
	 */
	EList<Constraint> getConstraints();

	/**
	 * Returns the value of the '<em><b>Number Of Constraints By Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Constraints By Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Constraints By Kind</em>' attribute.
	 * @see #setNumberOfConstraintsByKind(Map)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getConstraintMetrics_NumberOfConstraintsByKind()
	 * @model transient="true"
	 * @generated
	 */
	Map<ConstraintKind, Integer> getNumberOfConstraintsByKind();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getNumberOfConstraintsByKind <em>Number Of Constraints By Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Constraints By Kind</em>' attribute.
	 * @see #getNumberOfConstraintsByKind()
	 * @generated
	 */
	void setNumberOfConstraintsByKind(Map<ConstraintKind, Integer> value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t\r\n\t\treturn this.getConstraintMetrics().size();\r\n'"
	 * @generated
	 */
	int getConstraintCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t\r\n\t\tint result = 0;\r\n\r\n\t\t\r\n\t\tfor ( org.dresdenocl.metrics.metric.ConstraintMetric metric : this.getConstraintMetrics())\r\n\t\t\tresult += metric.getExpressionCount();\r\n\r\n\t\t// end for.\r\n\t\t\r\n\t\treturn result;\r\n'"
	 * @generated
	 */
	int getExpressionCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t\r\n\t\treturn this.getExpressionCount() / this.getConstraintCount();\r\n'"
	 * @generated
	 */
	float getAvgExpressionCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @model kind="operation" required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='\r\n\t\t\r\n\t\tint totalDepth = 0;\r\n\r\n\t\t\r\n\t\tfor ( org.dresdenocl.metrics.metric.ConstraintMetric metric : this.getConstraintMetrics())\r\n\t\t\ttotalDepth += metric.getExpressionDepth();\r\n\r\n\t\t// end for.\r\n\r\n\t\treturn totalDepth / this.getConstraintCount();\r\n'"
	 * @generated
	 */
	float getAvgExpressionDepth();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMinExpressionCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMinExpressionDepth();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMaxExpressionCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMaxExpressionDepth();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMeanExpressionCount();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	int getMeanExpressionDepth();

} // ConstraintMetrics
