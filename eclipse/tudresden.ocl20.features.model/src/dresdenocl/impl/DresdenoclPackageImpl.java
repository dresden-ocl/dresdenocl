/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dresdenocl.impl;

import dresdenocl.Dresden_OCL2_for_Eclipse;
import dresdenocl.DresdenoclFactory;
import dresdenocl.DresdenoclPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DresdenoclPackageImpl extends EPackageImpl implements DresdenoclPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dresden_OCL2_for_EclipseEClass = null;

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
	 * @see dresdenocl.DresdenoclPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DresdenoclPackageImpl() {
		super(eNS_URI, DresdenoclFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DresdenoclPackage init() {
		if (isInited) return (DresdenoclPackage)EPackage.Registry.INSTANCE.getEPackage(DresdenoclPackage.eNS_URI);

		// Obtain or create and register package
		DresdenoclPackageImpl theDresdenoclPackage = (DresdenoclPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof DresdenoclPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new DresdenoclPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theDresdenoclPackage.createPackageContents();

		// Initialize created meta-data
		theDresdenoclPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDresdenoclPackage.freeze();

		return theDresdenoclPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDresden_OCL2_for_Eclipse() {
		return dresden_OCL2_for_EclipseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDresden_OCL2_for_Eclipse_SupportsDifferentMetamodels() {
		return (EAttribute)dresden_OCL2_for_EclipseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDresden_OCL2_for_Eclipse_FullOCLStandard() {
		return (EAttribute)dresden_OCL2_for_EclipseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDresden_OCL2_for_Eclipse_InterpretationAndCodeGeneration() {
		return (EAttribute)dresden_OCL2_for_EclipseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDresden_OCL2_for_Eclipse_SupportsDifferentModelInstanceTypes() {
		return (EAttribute)dresden_OCL2_for_EclipseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DresdenoclFactory getDresdenoclFactory() {
		return (DresdenoclFactory)getEFactoryInstance();
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
		dresden_OCL2_for_EclipseEClass = createEClass(DRESDEN_OCL2_FOR_ECLIPSE);
		createEAttribute(dresden_OCL2_for_EclipseEClass, DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS);
		createEAttribute(dresden_OCL2_for_EclipseEClass, DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD);
		createEAttribute(dresden_OCL2_for_EclipseEClass, DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION);
		createEAttribute(dresden_OCL2_for_EclipseEClass, DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(dresden_OCL2_for_EclipseEClass, Dresden_OCL2_for_Eclipse.class, "Dresden_OCL2_for_Eclipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDresden_OCL2_for_Eclipse_SupportsDifferentMetamodels(), ecorePackage.getEBoolean(), "supportsDifferentMetamodels", null, 1, 1, Dresden_OCL2_for_Eclipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDresden_OCL2_for_Eclipse_FullOCLStandard(), ecorePackage.getEBoolean(), "fullOCLStandard", null, 1, 1, Dresden_OCL2_for_Eclipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDresden_OCL2_for_Eclipse_InterpretationAndCodeGeneration(), ecorePackage.getEBoolean(), "interpretationAndCodeGeneration", null, 1, 1, Dresden_OCL2_for_Eclipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDresden_OCL2_for_Eclipse_SupportsDifferentModelInstanceTypes(), ecorePackage.getEBoolean(), "supportsDifferentModelInstanceTypes", null, 1, 1, Dresden_OCL2_for_Eclipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //DresdenoclPackageImpl
