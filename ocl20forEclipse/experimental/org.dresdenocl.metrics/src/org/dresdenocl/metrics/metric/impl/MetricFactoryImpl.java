/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric.impl;

import org.dresdenocl.metrics.metric.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricFactoryImpl extends EFactoryImpl implements MetricFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MetricFactory init() {
		try {
			MetricFactory theMetricFactory = (MetricFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.dresden-ocl.org/metrics/"); 
			if (theMetricFactory != null) {
				return theMetricFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MetricFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MetricPackage.METRIC: return createMetric();
			case MetricPackage.MODEL_METRIC: return createModelMetric();
			case MetricPackage.CONSTRAINT_COUNT: return createConstraintCount();
			case MetricPackage.CONSTRAINT_METRIC: return createConstraintMetric();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Metric createMetric() {
		MetricImpl metric = new MetricImpl();
		return metric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelMetric createModelMetric() {
		ModelMetricImpl modelMetric = new ModelMetricImpl();
		return modelMetric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintCount createConstraintCount() {
		ConstraintCountImpl constraintCount = new ConstraintCountImpl();
		return constraintCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintMetric createConstraintMetric() {
		ConstraintMetricImpl constraintMetric = new ConstraintMetricImpl();
		return constraintMetric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricPackage getMetricPackage() {
		return (MetricPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MetricPackage getPackage() {
		return MetricPackage.eINSTANCE;
	}

} //MetricFactoryImpl
