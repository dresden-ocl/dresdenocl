/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.dresdenocl.metrics.metric.MetricPackage
 * @generated
 */
public interface MetricFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetricFactory eINSTANCE = org.dresdenocl.metrics.metric.impl.MetricFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Metric</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Metric</em>'.
	 * @generated
	 */
	Metric createMetric();

	/**
	 * Returns a new object of class '<em>Model Metric</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Metric</em>'.
	 * @generated
	 */
	ModelMetric createModelMetric();

	/**
	 * Returns a new object of class '<em>Constraint Count</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Count</em>'.
	 * @generated
	 */
	ConstraintCount createConstraintCount();

	/**
	 * Returns a new object of class '<em>Constraint Metric</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Metric</em>'.
	 * @generated
	 */
	ConstraintMetric createConstraintMetric();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MetricPackage getMetricPackage();

} //MetricFactory
