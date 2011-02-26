/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.metrics.metric.ModelMetric#getReferredModelId <em>Referred Model Id</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ModelMetric#getConstraintCountPerKind <em>Constraint Count Per Kind</em>}</li>
 *   <li>{@link org.dresdenocl.metrics.metric.ModelMetric#getConstraintMetrics <em>Constraint Metrics</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.metrics.metric.MetricPackage#getModelMetric()
 * @model
 * @generated
 */
public interface ModelMetric extends ConstraintMetric {
	/**
	 * Returns the value of the '<em><b>Referred Model Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Model Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Model Id</em>' attribute.
	 * @see #setReferredModelId(String)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getModelMetric_ReferredModelId()
	 * @model
	 * @generated
	 */
	String getReferredModelId();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ModelMetric#getReferredModelId <em>Referred Model Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Model Id</em>' attribute.
	 * @see #getReferredModelId()
	 * @generated
	 */
	void setReferredModelId(String value);

	/**
	 * Returns the value of the '<em><b>Constraint Count Per Kind</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Count Per Kind</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Count Per Kind</em>' containment reference.
	 * @see #setConstraintCountPerKind(ConstraintCount)
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getModelMetric_ConstraintCountPerKind()
	 * @model containment="true"
	 * @generated
	 */
	ConstraintCount getConstraintCountPerKind();

	/**
	 * Sets the value of the '{@link org.dresdenocl.metrics.metric.ModelMetric#getConstraintCountPerKind <em>Constraint Count Per Kind</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Count Per Kind</em>' containment reference.
	 * @see #getConstraintCountPerKind()
	 * @generated
	 */
	void setConstraintCountPerKind(ConstraintCount value);

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
	 * @see org.dresdenocl.metrics.metric.MetricPackage#getModelMetric_ConstraintMetrics()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConstraintMetric> getConstraintMetrics();

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

} // ModelMetric
