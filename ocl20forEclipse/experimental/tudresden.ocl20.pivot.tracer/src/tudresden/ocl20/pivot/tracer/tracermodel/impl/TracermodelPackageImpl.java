/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class TracermodelPackageImpl extends EPackageImpl implements
	TracermodelPackage {
    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private EClass tracerItemEClass = null;

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private EClass tracerRootEClass = null;

    /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private EClass uuidToTracerItemMapEClass = null;

/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private EDataType oclAnyEDataType = null;

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private EDataType uuidEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TracermodelPackageImpl() {
                super(eNS_URI, TracermodelFactory.eINSTANCE);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link TracermodelPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TracermodelPackage init() {
                if (isInited) return (TracermodelPackage)EPackage.Registry.INSTANCE.getEPackage(TracermodelPackage.eNS_URI);

                // Obtain or create and register package
                TracermodelPackageImpl theTracermodelPackage = (TracermodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TracermodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TracermodelPackageImpl());

                isInited = true;

                // Create package meta-data objects
                theTracermodelPackage.createPackageContents();

                // Initialize created meta-data
                theTracermodelPackage.initializePackageContents();

                // Mark meta-data to indicate it can't be changed
                theTracermodelPackage.freeze();

  
                // Update the registry and return the package
                EPackage.Registry.INSTANCE.put(TracermodelPackage.eNS_URI, theTracermodelPackage);
                return theTracermodelPackage;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EClass getTracerItem() {
                return tracerItemEClass;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EReference getTracerItem_Expression() {
                return (EReference)tracerItemEClass.getEStructuralFeatures().get(0);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EAttribute getTracerItem_Result() {
                return (EAttribute)tracerItemEClass.getEStructuralFeatures().get(1);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EReference getTracerItem_Parent() {
                return (EReference)tracerItemEClass.getEStructuralFeatures().get(2);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EReference getTracerItem_Children() {
                return (EReference)tracerItemEClass.getEStructuralFeatures().get(3);
        }

    /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getTracerItem_UUID() {
                return (EAttribute)tracerItemEClass.getEStructuralFeatures().get(4);
        }

/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EClass getTracerRoot() {
                return tracerRootEClass;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EReference getTracerRoot_RootItems() {
                return (EReference)tracerRootEClass.getEStructuralFeatures().get(0);
        }

    /**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EClass getUUIDToTracerItemMap() {
                return uuidToTracerItemMapEClass;
        }

/**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EAttribute getUUIDToTracerItemMap_Key() {
                return (EAttribute)uuidToTracerItemMapEClass.getEStructuralFeatures().get(0);
        }

/**
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public EReference getUUIDToTracerItemMap_Value() {
                return (EReference)uuidToTracerItemMapEClass.getEStructuralFeatures().get(1);
        }

/**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EDataType getOclAny() {
                return oclAnyEDataType;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public EDataType getUUID() {
                return uuidEDataType;
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    public TracermodelFactory getTracermodelFactory() {
                return (TracermodelFactory)getEFactoryInstance();
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
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
                tracerItemEClass = createEClass(TRACER_ITEM);
                createEReference(tracerItemEClass, TRACER_ITEM__EXPRESSION);
                createEAttribute(tracerItemEClass, TRACER_ITEM__RESULT);
                createEReference(tracerItemEClass, TRACER_ITEM__PARENT);
                createEReference(tracerItemEClass, TRACER_ITEM__CHILDREN);
                createEAttribute(tracerItemEClass, TRACER_ITEM__UUID);

                tracerRootEClass = createEClass(TRACER_ROOT);
                createEReference(tracerRootEClass, TRACER_ROOT__ROOT_ITEMS);

                uuidToTracerItemMapEClass = createEClass(UUID_TO_TRACER_ITEM_MAP);
                createEAttribute(uuidToTracerItemMapEClass, UUID_TO_TRACER_ITEM_MAP__KEY);
                createEReference(uuidToTracerItemMapEClass, UUID_TO_TRACER_ITEM_MAP__VALUE);

                // Create data types
                oclAnyEDataType = createEDataType(OCL_ANY);
                uuidEDataType = createEDataType(UUID);
        }

    /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
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
                initEClass(tracerItemEClass, TracerItem.class, "TracerItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getTracerItem_Expression(), ecorePackage.getEObject(), null, "expression", null, 0, 1, TracerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEAttribute(getTracerItem_Result(), this.getOclAny(), "result", null, 0, 1, TracerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getTracerItem_Parent(), this.getTracerItem(), null, "parent", null, 0, 1, TracerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getTracerItem_Children(), this.getTracerItem(), null, "children", null, 0, -1, TracerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
                initEAttribute(getTracerItem_UUID(), this.getUUID(), "UUID", null, 0, 1, TracerItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                initEClass(tracerRootEClass, TracerRoot.class, "TracerRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
                initEReference(getTracerRoot_RootItems(), this.getTracerItem(), null, "rootItems", null, 0, -1, TracerRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                initEClass(uuidToTracerItemMapEClass, Map.Entry.class, "UUIDToTracerItemMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
                initEAttribute(getUUIDToTracerItemMap_Key(), this.getUUID(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
                initEReference(getUUIDToTracerItemMap_Value(), this.getTracerItem(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

                // Initialize data types
                initEDataType(oclAnyEDataType, OclAny.class, "OclAny", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
                initEDataType(uuidEDataType, java.util.UUID.class, "UUID", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

                // Create resource
                createResource(eNS_URI);
        }

} // TracermodelPackageImpl
