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
	int CONSTRAINT_METRIC = 2;

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
	 * The feature id for the '<em><b>Called Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__CALLED_PROPERTIES = METRIC_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referred Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__REFERRED_CONSTRAINT = METRIC_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Number Of If Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS = METRIC_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Number Of Let Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS = METRIC_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Used Iterators</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__USED_ITERATORS = METRIC_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Used Literals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC__USED_LITERALS = METRIC_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Constraint Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRIC_FEATURE_COUNT = METRIC_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl <em>Constraint Metrics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl
	 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintMetrics()
	 * @generated
	 */
	int CONSTRAINT_METRICS = 1;

	/**
	 * The feature id for the '<em><b>Expression Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__EXPRESSION_COUNT = CONSTRAINT_METRIC__EXPRESSION_COUNT;

	/**
	 * The feature id for the '<em><b>Expression Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__EXPRESSION_DEPTH = CONSTRAINT_METRIC__EXPRESSION_DEPTH;

	/**
	 * The feature id for the '<em><b>Called Operations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__CALLED_OPERATIONS = CONSTRAINT_METRIC__CALLED_OPERATIONS;

	/**
	 * The feature id for the '<em><b>Called Properties</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__CALLED_PROPERTIES = CONSTRAINT_METRIC__CALLED_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Referred Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__REFERRED_CONSTRAINT = CONSTRAINT_METRIC__REFERRED_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Number Of If Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__NUMBER_OF_IF_EXPRESSIONS = CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS;

	/**
	 * The feature id for the '<em><b>Number Of Let Expressions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__NUMBER_OF_LET_EXPRESSIONS = CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS;

	/**
	 * The feature id for the '<em><b>Used Iterators</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__USED_ITERATORS = CONSTRAINT_METRIC__USED_ITERATORS;

	/**
	 * The feature id for the '<em><b>Used Literals</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__USED_LITERALS = CONSTRAINT_METRIC__USED_LITERALS;

	/**
	 * The feature id for the '<em><b>Constraint Metrics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__CONSTRAINT_METRICS = CONSTRAINT_METRIC_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__CONSTRAINTS = CONSTRAINT_METRIC_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Number Of Constraints By Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND = CONSTRAINT_METRIC_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Constraint Metrics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_METRICS_FEATURE_COUNT = CONSTRAINT_METRIC_FEATURE_COUNT + 3;

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
	 * Returns the meta object for class '{@link org.dresdenocl.metrics.metric.ConstraintMetrics <em>Constraint Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Metrics</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetrics
	 * @generated
	 */
	EClass getConstraintMetrics();

	/**
	 * Returns the meta object for the containment reference list '{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraintMetrics <em>Constraint Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint Metrics</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraintMetrics()
	 * @see #getConstraintMetrics()
	 * @generated
	 */
	EReference getConstraintMetrics_ConstraintMetrics();

	/**
	 * Returns the meta object for the reference list '{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Constraints</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetrics#getConstraints()
	 * @see #getConstraintMetrics()
	 * @generated
	 */
	EReference getConstraintMetrics_Constraints();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetrics#getNumberOfConstraintsByKind <em>Number Of Constraints By Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Constraints By Kind</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetrics#getNumberOfConstraintsByKind()
	 * @see #getConstraintMetrics()
	 * @generated
	 */
	EAttribute getConstraintMetrics_NumberOfConstraintsByKind();

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
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getCalledProperties <em>Called Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Called Properties</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getCalledProperties()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_CalledProperties();

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
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfIfExpressions <em>Number Of If Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of If Expressions</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfIfExpressions()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_NumberOfIfExpressions();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfLetExpressions <em>Number Of Let Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Let Expressions</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getNumberOfLetExpressions()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_NumberOfLetExpressions();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedIterators <em>Used Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Iterators</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getUsedIterators()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_UsedIterators();

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.metrics.metric.ConstraintMetric#getUsedLiterals <em>Used Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Used Literals</em>'.
	 * @see org.dresdenocl.metrics.metric.ConstraintMetric#getUsedLiterals()
	 * @see #getConstraintMetric()
	 * @generated
	 */
	EAttribute getConstraintMetric_UsedLiterals();

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
		 * The meta object literal for the '{@link org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl <em>Constraint Metrics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.metrics.metric.impl.ConstraintMetricsImpl
		 * @see org.dresdenocl.metrics.metric.impl.MetricPackageImpl#getConstraintMetrics()
		 * @generated
		 */
		EClass CONSTRAINT_METRICS = eINSTANCE.getConstraintMetrics();

		/**
		 * The meta object literal for the '<em><b>Constraint Metrics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_METRICS__CONSTRAINT_METRICS = eINSTANCE.getConstraintMetrics_ConstraintMetrics();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_METRICS__CONSTRAINTS = eINSTANCE.getConstraintMetrics_Constraints();

		/**
		 * The meta object literal for the '<em><b>Number Of Constraints By Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRICS__NUMBER_OF_CONSTRAINTS_BY_KIND = eINSTANCE.getConstraintMetrics_NumberOfConstraintsByKind();

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
		 * The meta object literal for the '<em><b>Called Properties</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__CALLED_PROPERTIES = eINSTANCE.getConstraintMetric_CalledProperties();

		/**
		 * The meta object literal for the '<em><b>Referred Constraint</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_METRIC__REFERRED_CONSTRAINT = eINSTANCE.getConstraintMetric_ReferredConstraint();

		/**
		 * The meta object literal for the '<em><b>Number Of If Expressions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__NUMBER_OF_IF_EXPRESSIONS = eINSTANCE.getConstraintMetric_NumberOfIfExpressions();

		/**
		 * The meta object literal for the '<em><b>Number Of Let Expressions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__NUMBER_OF_LET_EXPRESSIONS = eINSTANCE.getConstraintMetric_NumberOfLetExpressions();

		/**
		 * The meta object literal for the '<em><b>Used Iterators</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__USED_ITERATORS = eINSTANCE.getConstraintMetric_UsedIterators();

		/**
		 * The meta object literal for the '<em><b>Used Literals</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_METRIC__USED_LITERALS = eINSTANCE.getConstraintMetric_UsedLiterals();

	}

} //MetricPackage
