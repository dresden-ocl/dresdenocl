/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestmodelPackageImpl extends EPackageImpl implements TestmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeProviderClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionTypeProviderClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationLiteralProviderClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass class1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass class2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interface1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interface2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interface3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass copyableClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass c1ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass i1I2ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass i1ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass i2C1ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass i3C2ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass i3ImplementationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clonableCopyableClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initializableCopyableClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nonCopyableClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticPropertyAndOperationClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum enumeration1EEnum = null;

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
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TestmodelPackageImpl() {
		super(eNS_URI, TestmodelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TestmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TestmodelPackage init() {
		if (isInited) return (TestmodelPackage)EPackage.Registry.INSTANCE.getEPackage(TestmodelPackage.eNS_URI);

		// Obtain or create and register package
		TestmodelPackageImpl theTestmodelPackage = (TestmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TestmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TestmodelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTestmodelPackage.createPackageContents();

		// Initialize created meta-data
		theTestmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTestmodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TestmodelPackage.eNS_URI, theTestmodelPackage);
		return theTestmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainerClass() {
		return containerClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_PrimitiveTypeProviderClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_CollectionTypeProviderClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_EnumerationLiteralProviderClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_Class1Instances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_Class2Instances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_Interface1Instances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_Interface2Instances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_Interface3Instances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_CopyableClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_NonCopyableClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainerClass_StaticPropertyAndOperationClassInstances() {
		return (EReference)containerClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeProviderClass() {
		return primitiveTypeProviderClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveTypeProviderClass_BooleanProperty1() {
		return (EAttribute)primitiveTypeProviderClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveTypeProviderClass_IntegerProperty1() {
		return (EAttribute)primitiveTypeProviderClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveTypeProviderClass_RealProperty1() {
		return (EAttribute)primitiveTypeProviderClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrimitiveTypeProviderClass_StringProperty1() {
		return (EAttribute)primitiveTypeProviderClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollectionTypeProviderClass() {
		return collectionTypeProviderClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCollectionTypeProviderClass_BagProperty1() {
		return (EAttribute)collectionTypeProviderClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCollectionTypeProviderClass_OrderedSetProperty1() {
		return (EAttribute)collectionTypeProviderClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCollectionTypeProviderClass_SequenceProperty1() {
		return (EAttribute)collectionTypeProviderClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCollectionTypeProviderClass_SetProperty1() {
		return (EAttribute)collectionTypeProviderClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumerationLiteralProviderClass() {
		return enumerationLiteralProviderClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumerationLiteralProviderClass_EnumerationLiteralProperty1() {
		return (EAttribute)enumerationLiteralProviderClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClass1() {
		return class1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_NonMultipleProperty() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_MultipleUniqueOrderedProperty() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_MultipleUniqueUnorderedProperty() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_MultipleNonuniqueOrderedProperty() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_MultipleNonuniqueUnorderedProperty() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyBooleanNonMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyStringNonMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyBooleanMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyStringMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass1_ArgumentPropertyObjectNonMultiple() {
		return (EReference)class1EClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass1_ArgumentPropertyObjectMultiple() {
		return (EReference)class1EClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyEnumerationLiteralNonMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass1_ArgumentPropertyEnumerationLiteralMultiple() {
		return (EAttribute)class1EClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClass2() {
		return class2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterface1() {
		return interface1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterface2() {
		return interface2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterface3() {
		return interface3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCopyableClass() {
		return copyableClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getC1Implementation() {
		return c1ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getI1I2Implementation() {
		return i1I2ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getI1Implementation() {
		return i1ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getI2C1Implementation() {
		return i2C1ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getI3C2Implementation() {
		return i3C2ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getI3Implementation() {
		return i3ImplementationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClonableCopyableClass() {
		return clonableCopyableClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitializableCopyableClass() {
		return initializableCopyableClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonCopyableClass() {
		return nonCopyableClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticPropertyAndOperationClass() {
		return staticPropertyAndOperationClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEnumeration1() {
		return enumeration1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelFactory getTestmodelFactory() {
		return (TestmodelFactory)getEFactoryInstance();
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
		containerClassEClass = createEClass(CONTAINER_CLASS);
		createEReference(containerClassEClass, CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__CLASS1_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__CLASS2_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__INTERFACE1_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__INTERFACE2_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__INTERFACE3_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES);
		createEReference(containerClassEClass, CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES);

		primitiveTypeProviderClassEClass = createEClass(PRIMITIVE_TYPE_PROVIDER_CLASS);
		createEAttribute(primitiveTypeProviderClassEClass, PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1);
		createEAttribute(primitiveTypeProviderClassEClass, PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1);
		createEAttribute(primitiveTypeProviderClassEClass, PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1);
		createEAttribute(primitiveTypeProviderClassEClass, PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1);

		collectionTypeProviderClassEClass = createEClass(COLLECTION_TYPE_PROVIDER_CLASS);
		createEAttribute(collectionTypeProviderClassEClass, COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1);
		createEAttribute(collectionTypeProviderClassEClass, COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1);
		createEAttribute(collectionTypeProviderClassEClass, COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1);
		createEAttribute(collectionTypeProviderClassEClass, COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1);

		enumerationLiteralProviderClassEClass = createEClass(ENUMERATION_LITERAL_PROVIDER_CLASS);
		createEAttribute(enumerationLiteralProviderClassEClass, ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1);

		class1EClass = createEClass(CLASS1);
		createEAttribute(class1EClass, CLASS1__NON_MULTIPLE_PROPERTY);
		createEAttribute(class1EClass, CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY);
		createEAttribute(class1EClass, CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY);
		createEAttribute(class1EClass, CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY);
		createEAttribute(class1EClass, CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE);
		createEReference(class1EClass, CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE);
		createEReference(class1EClass, CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE);
		createEAttribute(class1EClass, CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE);

		class2EClass = createEClass(CLASS2);

		interface1EClass = createEClass(INTERFACE1);

		interface2EClass = createEClass(INTERFACE2);

		interface3EClass = createEClass(INTERFACE3);

		copyableClassEClass = createEClass(COPYABLE_CLASS);

		c1ImplementationEClass = createEClass(C1_IMPLEMENTATION);

		i1I2ImplementationEClass = createEClass(I1I2_IMPLEMENTATION);

		i1ImplementationEClass = createEClass(I1_IMPLEMENTATION);

		i2C1ImplementationEClass = createEClass(I2C1_IMPLEMENTATION);

		i3C2ImplementationEClass = createEClass(I3C2_IMPLEMENTATION);

		i3ImplementationEClass = createEClass(I3_IMPLEMENTATION);

		clonableCopyableClassEClass = createEClass(CLONABLE_COPYABLE_CLASS);

		initializableCopyableClassEClass = createEClass(INITIALIZABLE_COPYABLE_CLASS);

		nonCopyableClassEClass = createEClass(NON_COPYABLE_CLASS);

		staticPropertyAndOperationClassEClass = createEClass(STATIC_PROPERTY_AND_OPERATION_CLASS);

		// Create enums
		enumeration1EEnum = createEEnum(ENUMERATION1);
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
		class2EClass.getESuperTypes().add(this.getClass1());
		interface3EClass.getESuperTypes().add(this.getInterface2());
		c1ImplementationEClass.getESuperTypes().add(this.getClass1());
		i1I2ImplementationEClass.getESuperTypes().add(this.getInterface1());
		i1I2ImplementationEClass.getESuperTypes().add(this.getInterface2());
		i1ImplementationEClass.getESuperTypes().add(this.getInterface1());
		i2C1ImplementationEClass.getESuperTypes().add(this.getInterface2());
		i2C1ImplementationEClass.getESuperTypes().add(this.getClass1());
		i3C2ImplementationEClass.getESuperTypes().add(this.getClass2());
		i3C2ImplementationEClass.getESuperTypes().add(this.getInterface3());
		i3ImplementationEClass.getESuperTypes().add(this.getInterface3());
		clonableCopyableClassEClass.getESuperTypes().add(this.getCopyableClass());
		initializableCopyableClassEClass.getESuperTypes().add(this.getCopyableClass());

		// Initialize classes and features; add operations and parameters
		initEClass(containerClassEClass, ContainerClass.class, "ContainerClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainerClass_PrimitiveTypeProviderClassInstances(), this.getPrimitiveTypeProviderClass(), null, "primitiveTypeProviderClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_CollectionTypeProviderClassInstances(), this.getCollectionTypeProviderClass(), null, "collectionTypeProviderClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_EnumerationLiteralProviderClassInstances(), this.getEnumerationLiteralProviderClass(), null, "enumerationLiteralProviderClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_Class1Instances(), this.getClass1(), null, "class1Instances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_Class2Instances(), this.getClass2(), null, "class2Instances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_Interface1Instances(), this.getInterface1(), null, "interface1Instances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_Interface2Instances(), this.getInterface2(), null, "interface2Instances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_Interface3Instances(), this.getInterface3(), null, "interface3Instances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_CopyableClassInstances(), this.getCopyableClass(), null, "copyableClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_NonCopyableClassInstances(), this.getNonCopyableClass(), null, "nonCopyableClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainerClass_StaticPropertyAndOperationClassInstances(), this.getStaticPropertyAndOperationClass(), null, "staticPropertyAndOperationClassInstances", null, 0, -1, ContainerClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(primitiveTypeProviderClassEClass, PrimitiveTypeProviderClass.class, "PrimitiveTypeProviderClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrimitiveTypeProviderClass_BooleanProperty1(), ecorePackage.getEBoolean(), "booleanProperty1", null, 0, 1, PrimitiveTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrimitiveTypeProviderClass_IntegerProperty1(), ecorePackage.getEInt(), "integerProperty1", null, 0, 1, PrimitiveTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrimitiveTypeProviderClass_RealProperty1(), ecorePackage.getEFloat(), "realProperty1", null, 0, 1, PrimitiveTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrimitiveTypeProviderClass_StringProperty1(), ecorePackage.getEString(), "stringProperty1", null, 0, 1, PrimitiveTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(collectionTypeProviderClassEClass, CollectionTypeProviderClass.class, "CollectionTypeProviderClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCollectionTypeProviderClass_BagProperty1(), ecorePackage.getEString(), "bagProperty1", null, 0, -1, CollectionTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCollectionTypeProviderClass_OrderedSetProperty1(), ecorePackage.getEInt(), "orderedSetProperty1", null, 0, -1, CollectionTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollectionTypeProviderClass_SequenceProperty1(), ecorePackage.getEString(), "sequenceProperty1", null, 0, -1, CollectionTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCollectionTypeProviderClass_SetProperty1(), ecorePackage.getEString(), "setProperty1", null, 0, -1, CollectionTypeProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(enumerationLiteralProviderClassEClass, EnumerationLiteralProviderClass.class, "EnumerationLiteralProviderClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumerationLiteralProviderClass_EnumerationLiteralProperty1(), this.getEnumeration1(), "enumerationLiteralProperty1", null, 0, 1, EnumerationLiteralProviderClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(class1EClass, Class1.class, "Class1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClass1_NonMultipleProperty(), ecorePackage.getEString(), "nonMultipleProperty", null, 0, 1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_MultipleUniqueOrderedProperty(), ecorePackage.getEString(), "multipleUniqueOrderedProperty", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_MultipleUniqueUnorderedProperty(), ecorePackage.getEString(), "multipleUniqueUnorderedProperty", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getClass1_MultipleNonuniqueOrderedProperty(), ecorePackage.getEString(), "multipleNonuniqueOrderedProperty", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_MultipleNonuniqueUnorderedProperty(), ecorePackage.getEString(), "multipleNonuniqueUnorderedProperty", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyBooleanNonMultiple(), ecorePackage.getEBoolean(), "argumentPropertyBooleanNonMultiple", null, 0, 1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyStringNonMultiple(), ecorePackage.getEString(), "argumentPropertyStringNonMultiple", null, 0, 1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyBooleanMultiple(), ecorePackage.getEBoolean(), "argumentPropertyBooleanMultiple", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyStringMultiple(), ecorePackage.getEString(), "argumentPropertyStringMultiple", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClass1_ArgumentPropertyObjectNonMultiple(), this.getClass1(), null, "argumentPropertyObjectNonMultiple", null, 0, 1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClass1_ArgumentPropertyObjectMultiple(), this.getClass1(), null, "argumentPropertyObjectMultiple", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyEnumerationLiteralNonMultiple(), this.getEnumeration1(), "argumentPropertyEnumerationLiteralNonMultiple", null, 0, 1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass1_ArgumentPropertyEnumerationLiteralMultiple(), this.getEnumeration1(), "argumentPropertyEnumerationLiteralMultiple", null, 0, -1, Class1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(class1EClass, null, "voidOperation", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(class1EClass, ecorePackage.getEBoolean(), "nonMultipleOperation", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(class1EClass, ecorePackage.getEString(), "multipleUniqueOrderedOperation", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(class1EClass, ecorePackage.getEString(), "multipleUniqueUnorderedOperation", 0, -1, IS_UNIQUE, !IS_ORDERED);

		addEOperation(class1EClass, ecorePackage.getEString(), "multipleNonuniqueOrderedOperation", 0, -1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(class1EClass, ecorePackage.getEString(), "multipleNonuniqueUnorderedOperation", 0, -1, !IS_UNIQUE, !IS_ORDERED);

		EOperation op = addEOperation(class1EClass, null, "voidOperationWithBooleanArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "arg1", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithBooleanMultipleArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "arg1", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithStringArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "arg1", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithStringMultipleArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "arg1", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithObjectArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getClass1(), "arg1", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithObjectMultipleArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getClass1(), "arg1", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithEnumerationLiteralArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEnumeration1(), "arg1", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(class1EClass, null, "voidOperationWithEnumerationLiteralMultipleArgument", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEnumeration1(), "arg1", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(class2EClass, Class2.class, "Class2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(interface1EClass, Interface1.class, "Interface1", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(interface2EClass, Interface2.class, "Interface2", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(interface3EClass, Interface3.class, "Interface3", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(copyableClassEClass, CopyableClass.class, "CopyableClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(c1ImplementationEClass, C1Implementation.class, "C1Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(i1I2ImplementationEClass, I1I2Implementation.class, "I1I2Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(i1ImplementationEClass, I1Implementation.class, "I1Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(i2C1ImplementationEClass, I2C1Implementation.class, "I2C1Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(i3C2ImplementationEClass, I3C2Implementation.class, "I3C2Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(i3ImplementationEClass, I3Implementation.class, "I3Implementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clonableCopyableClassEClass, ClonableCopyableClass.class, "ClonableCopyableClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(initializableCopyableClassEClass, InitializableCopyableClass.class, "InitializableCopyableClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nonCopyableClassEClass, NonCopyableClass.class, "NonCopyableClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(staticPropertyAndOperationClassEClass, StaticPropertyAndOperationClass.class, "StaticPropertyAndOperationClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(enumeration1EEnum, Enumeration1.class, "Enumeration1");
		addEEnumLiteral(enumeration1EEnum, Enumeration1.LITERAL1);
		addEEnumLiteral(enumeration1EEnum, Enumeration1.LITERAL2);

		// Create resource
		createResource(eNS_URI);
	}

} //TestmodelPackageImpl
