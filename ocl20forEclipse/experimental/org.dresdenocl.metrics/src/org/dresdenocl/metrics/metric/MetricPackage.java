/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.dresdenocl.metrics.metric.MetricFactory
 * @model kind="package"
 * @generated
 */
public interface MetricPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "metric";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.dresden-ocl.org/metrics/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metric";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetricPackage eINSTANCE = org.dresdenocl.metrics.metric.impl.MetricPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.dresdenocl.metrics.metric.impl.MetricImpl <em>Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.metrics.metric.impl.MetricImpl
	 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getMetric()
	 * @generated
	 */
	int METRIC = 0;

	/**
	 * The number of structural features of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl <em>Constraint Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl
	 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintMetric()
	 * @generated
	 */
	int CONSTRAINT_METRIC = 3;

	/**
	 * The feature id for the '<em><b>Expression Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__EXPRESSION_COUNT = METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__EXPRESSION_DEPTH = METRIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Called Operations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__CALLED_OPERATIONS = METRIC_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Referred Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__REFERRED_CONSTRAINT = METRIC_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Constraint Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.dresdenocl.metrics.metric.impl.ModelMetricImpl <em>Model Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.metrics.metric.impl.ModelMetricImpl
	 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getModelMetric()
	 * @generated
	 */
	int MODEL_METRIC = 1;

	/**
	 * The feature id for the '<em><b>Expression Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__EXPRESSION_COUNT = CONSTRAINT_METRIC__EXPRESSION_COUNT;

	/**
	 * The feature id for the '<em><b>Expression Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__EXPRESSION_DEPTH = CONSTRAINT_METRIC__EXPRESSION_DEPTH;

	/**
	 * The feature id for the '<em><b>Called Operations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__CALLED_OPERATIONS = CONSTRAINT_METRIC__CALLED_OPERATIONS;

	/**
	 * The feature id for the '<em><b>Referred Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__REFERRED_CONSTRAINT = CONSTRAINT_METRIC__REFERRED_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Referred Model Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__REFERRED_MODEL_ID = CONSTRAINT_METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraint Count Per Kind</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND = CONSTRAINT_METRIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constraint Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC__CONSTRAINT_METRICS = CONSTRAINT_METRIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_METRIC_FEATURE_COUNT = CONSTRAINT_METRIC_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintCountImpl <em>Constraint Count</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintCountImpl
	 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintCount()
	 * @generated
	 */
	int CONSTRAINT_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_COUNT__KIND = 0;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_COUNT__COUNT = 1;

	/**
	 * The number of structural features of the '<em>Constraint Count</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_COUNT_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.dresdenocl.metrics.metric.Metric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metric</em>'.
	 * @see org.dresdenocl.metrics.metric.Metric
	 * @generated
	 */
	EClass getMetric();

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.metrics.metric.ModelMetric <em>Model Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Metric</em>'.
	 * @see org.dresdenocl.metrics.metric.ModelMetric
	 * @generated
	 */
	EClass getModelMetric();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ModelMetric#getReferredModelId <em>Referred Model Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Model Id</em>'.
	 * @see org.dresdenocl.metrics.metric.ModelMetric#getReferredModelId()
	 * @see #getModelMetric()
	 * @generated
	 */
	EAttribute getModelMetric_ReferredModelId();

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.metrics.metric.ModelMetric#getConstraintCountPerKind <em>Constraint Count Per Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint Count Per Kind</em>'.
	 * @see org.dresdenocl.metrics.metric.ModelMetric#getConstraintCountPerKind()
	 * @see #getModelMetric()
	 * @generated
	 */
	EReference getModelMetric_ConstraintCountPerKind();

	/**
	 * Returns the meta object for the containment reference list '{@link org.dresdenocl.metrics.metric.ModelMetric#getConstraintMetrics <em>Constraint Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint Metrics</em>'.
	 * @see org.dresdenocl.metrics.metric.ModelMetric#getConstraintMetrics()
	 * @see #getModelMetric()
	 * @generated
	 */
	EReference getModelMetric_ConstraintMetrics();

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.metrics.metric.ConstraintCount <em>Constraint Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Count</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintCount
	 * @generated
	 */
	EClass getConstraintCount();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintCount#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintCount#getKind()
	 * @see #getConstraintCount()
	 * @generated
	 */
	EAttribute getConstraintCount_Kind();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintCount#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintCount#getCount()
	 * @see #getConstraintCount()
	 * @generated
	 */
	EAttribute getConstraintCount_Count();

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.metrics.metric.ConstraintMetric <em>Constraint Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Metric</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric
	 * @generated
	 */
	EClass getConstraintMetric();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionCount <em>Expression Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression Count</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionCount()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_ExpressionCount();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionDepth <em>Expression Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression Depth</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getExpressionDepth()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_ExpressionDepth();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledOperations <em>Called Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Operations</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getCalledOperations()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_CalledOperations();

	/**
	 * Returns the meta object for the reference '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getReferredConstraint <em>Referred Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Constraint</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getReferredConstraint()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EReference getConstraintMetric_ReferredConstraint();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MetricFactory getMetricFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.dresdenocl.metrics.metric.impl.MetricImpl <em>Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.metrics.metric.impl.MetricImpl
		 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getMetric()
		 * @generated
		 */
		EClass METRIC = eINSTANCE.getMetric();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.metrics.metric.impl.ModelMetricImpl <em>Model Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.metrics.metric.impl.ModelMetricImpl
		 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getModelMetric()
		 * @generated
		 */
		EClass MODEL_METRIC = eINSTANCE.getModelMetric();

		/**
		 * The meta object literal for the '<em><b>Referred Model Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_METRIC__REFERRED_MODEL_ID = eINSTANCE.getModelMetric_ReferredModelId();

		/**
		 * The meta object literal for the '<em><b>Constraint Count Per Kind</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND = eINSTANCE.getModelMetric_ConstraintCountPerKind();

		/**
		 * The meta object literal for the '<em><b>Constraint Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_METRIC__CONSTRAINT_METRICS = eINSTANCE.getModelMetric_ConstraintMetrics();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintCountImpl <em>Constraint Count</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.metrics.metric.impl.ConstraintCountImpl
		 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintCount()
		 * @generated
		 */
		EClass CONSTRAINT_COUNT = eINSTANCE.getConstraintCount();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_COUNT__KIND = eINSTANCE.getConstraintCount_Kind();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_COUNT__COUNT = eINSTANCE.getConstraintCount_Count();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl <em>Constraint Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricImpl
		 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintMetric()
		 * @generated
		 */
		EClass CONSTRAINT_METRIC = eINSTANCE.getConstraintMetric();

		/**
		 * The meta object literal for the '<em><b>Expression Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__EXPRESSION_COUNT = eINSTANCE.getConstraintMetric_ExpressionCount();

		/**
		 * The meta object literal for the '<em><b>Expression Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__EXPRESSION_DEPTH = eINSTANCE.getConstraintMetric_ExpressionDepth();

		/**
		 * The meta object literal for the '<em><b>Called Operations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__CALLED_OPERATIONS = eINSTANCE.getConstraintMetric_CalledOperations();

		/**
		 * The meta object literal for the '<em><b>Referred Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_METRIC__REFERRED_CONSTRAINT = eINSTANCE.getConstraintMetric_ReferredConstraint();

	}

} //MetricPackage
