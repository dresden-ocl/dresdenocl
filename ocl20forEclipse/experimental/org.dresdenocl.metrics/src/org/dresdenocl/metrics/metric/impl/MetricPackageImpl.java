/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.metrics.metric.impl;

import org.dresdenocl.metrics.metric.ConstraintCount;
import org.dresdenocl.metrics.metric.ConstraintMetric;
import org.dresdenocl.metrics.metric.Metric;
import org.dresdenocl.metrics.metric.MetricFactory;
import org.dresdenocl.metrics.metric.MetricPackage;
import org.dresdenocl.metrics.metric.ModelMetric;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.datatypes.DatatypesPackage;

import tudresden.ocl20.pivot.pivotmodel.PivotModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricPackageImpl extends EPackageImpl implements MetricPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metricEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelMetricEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintCountEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintMetricEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.dresdenocl.metrics.metric.MetricPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MetricPackageImpl() {
		super(eNS_URI, MetricFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MetricPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MetricPackage init() {
		if (isInited) return (MetricPackage)EPackage.Registry.INSTANCE.getEPackage(MetricPackage.eNS_URI);

		// Obtain or create and register package
		MetricPackageImpl theMetricPackage = (MetricPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MetricPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MetricPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PivotModelPackage.eINSTANCE.eClass();
		DatatypesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMetricPackage.createPackageContents();

		// Initialize created meta-data
		theMetricPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMetricPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MetricPackage.eNS_URI, theMetricPackage);
		return theMetricPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetric() {
		return metricEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelMetric() {
		return modelMetricEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelMetric_ReferredModelId() {
		return (EAttribute)modelMetricEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelMetric_ConstraintCountPerKind() {
		return (EReference)modelMetricEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelMetric_ConstraintMetrics() {
		return (EReference)modelMetricEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintCount() {
		return constraintCountEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintCount_Kind() {
		return (EAttribute)constraintCountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintCount_Count() {
		return (EAttribute)constraintCountEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintMetric() {
		return constraintMetricEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintMetric_ExpressionCount() {
		return (EAttribute)constraintMetricEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintMetric_ExpressionDepth() {
		return (EAttribute)constraintMetricEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintMetric_CalledOperations() {
		return (EAttribute)constraintMetricEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstraintMetric_ReferredConstraint() {
		return (EReference)constraintMetricEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricFactory getMetricFactory() {
		return (MetricFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		metricEClass = createEClass(METRIC);

		modelMetricEClass = createEClass(MODEL_METRIC);
		createEAttribute(modelMetricEClass, MODEL_METRIC__REFERRED_MODEL_ID);
		createEReference(modelMetricEClass, MODEL_METRIC__CONSTRAINT_COUNT_PER_KIND);
		createEReference(modelMetricEClass, MODEL_METRIC__CONSTRAINT_METRICS);

		constraintCountEClass = createEClass(CONSTRAINT_COUNT);
		createEAttribute(constraintCountEClass, CONSTRAINT_COUNT__KIND);
		createEAttribute(constraintCountEClass, CONSTRAINT_COUNT__COUNT);

		constraintMetricEClass = createEClass(CONSTRAINT_METRIC);
		createEAttribute(constraintMetricEClass, CONSTRAINT_METRIC__EXPRESSION_COUNT);
		createEAttribute(constraintMetricEClass, CONSTRAINT_METRIC__EXPRESSION_DEPTH);
		createEAttribute(constraintMetricEClass, CONSTRAINT_METRIC__CALLED_OPERATIONS);
		createEReference(constraintMetricEClass, CONSTRAINT_METRIC__REFERRED_CONSTRAINT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PivotModelPackage thePivotModelPackage = (PivotModelPackage)EPackage.Registry.INSTANCE.getEPackage(PivotModelPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		modelMetricEClass.getESuperTypes().add(this.getConstraintMetric());
		constraintMetricEClass.getESuperTypes().add(this.getMetric());

		// Initialize classes and features; add operations and parameters
		initEClass(metricEClass, Metric.class, "Metric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(metricEClass, ecorePackage.getEString(), "getReport", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(modelMetricEClass, ModelMetric.class, "ModelMetric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelMetric_ReferredModelId(), ecorePackage.getEString(), "referredModelId", null, 0, 1, ModelMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelMetric_ConstraintCountPerKind(), this.getConstraintCount(), null, "constraintCountPerKind", null, 0, 1, ModelMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelMetric_ConstraintMetrics(), this.getConstraintMetric(), null, "constraintMetrics", null, 0, -1, ModelMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getConstraintCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getExpressionCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEFloat(), "getAvgExpressionCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEFloat(), "getAvgExpressionDepth", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMinExpressionCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMinExpressionDepth", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMaxExpressionCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMaxExpressionDepth", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMeanExpressionCount", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelMetricEClass, ecorePackage.getEInt(), "getMeanExpressionDepth", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(constraintCountEClass, ConstraintCount.class, "ConstraintCount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintCount_Kind(), thePivotModelPackage.getConstraintKind(), "kind", null, 0, 1, ConstraintCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintCount_Count(), ecorePackage.getEInt(), "count", null, 0, 1, ConstraintCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(constraintMetricEClass, ConstraintMetric.class, "ConstraintMetric", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstraintMetric_ExpressionCount(), ecorePackage.getEInt(), "expressionCount", null, 0, 1, ConstraintMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConstraintMetric_ExpressionDepth(), ecorePackage.getEInt(), "expressionDepth", null, 0, 1, ConstraintMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEIntegerObject());
		g1.getETypeArguments().add(g2);
		initEAttribute(getConstraintMetric_CalledOperations(), g1, "calledOperations", null, 0, 1, ConstraintMetric.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConstraintMetric_ReferredConstraint(), thePivotModelPackage.getConstraint(), null, "referredConstraint", null, 0, 1, ConstraintMetric.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MetricPackageImpl
