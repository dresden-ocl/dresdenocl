/**
 */
package ecore.org.dresdenocl.modelinstancetype.test.testmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory
 * @model kind="package"
 * @generated
 */
public interface TestmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "testmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.tudresden.de/ocl20/pivot/modelinstancetype/test/testmodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "testmodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TestmodelPackage eINSTANCE = org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl <em>Container Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getContainerClass()
	 * @generated
	 */
	int CONTAINER_CLASS = 0;

	/**
	 * The feature id for the '<em><b>Primitive Type Provider Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES = 0;

	/**
	 * The feature id for the '<em><b>Collection Type Provider Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES = 1;

	/**
	 * The feature id for the '<em><b>Enumeration Literal Provider Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES = 2;

	/**
	 * The feature id for the '<em><b>Class1 Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__CLASS1_INSTANCES = 3;

	/**
	 * The feature id for the '<em><b>Class2 Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__CLASS2_INSTANCES = 4;

	/**
	 * The feature id for the '<em><b>Interface1 Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__INTERFACE1_INSTANCES = 5;

	/**
	 * The feature id for the '<em><b>Interface2 Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__INTERFACE2_INSTANCES = 6;

	/**
	 * The feature id for the '<em><b>Interface3 Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__INTERFACE3_INSTANCES = 7;

	/**
	 * The feature id for the '<em><b>Copyable Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES = 8;

	/**
	 * The feature id for the '<em><b>Non Copyable Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES = 9;

	/**
	 * The feature id for the '<em><b>Static Property And Operation Class Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES = 10;

	/**
	 * The number of structural features of the '<em>Container Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_CLASS_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl <em>Primitive Type Provider Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getPrimitiveTypeProviderClass()
	 * @generated
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS = 1;

	/**
	 * The feature id for the '<em><b>Boolean Property1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1 = 0;

	/**
	 * The feature id for the '<em><b>Integer Property1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1 = 1;

	/**
	 * The feature id for the '<em><b>Real Property1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1 = 2;

	/**
	 * The feature id for the '<em><b>String Property1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1 = 3;

	/**
	 * The number of structural features of the '<em>Primitive Type Provider Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_PROVIDER_CLASS_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl <em>Collection Type Provider Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getCollectionTypeProviderClass()
	 * @generated
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS = 2;

	/**
	 * The feature id for the '<em><b>Bag Property1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1 = 0;

	/**
	 * The feature id for the '<em><b>Ordered Set Property1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1 = 1;

	/**
	 * The feature id for the '<em><b>Sequence Property1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1 = 2;

	/**
	 * The feature id for the '<em><b>Set Property1</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1 = 3;

	/**
	 * The number of structural features of the '<em>Collection Type Provider Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_TYPE_PROVIDER_CLASS_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.EnumerationLiteralProviderClassImpl <em>Enumeration Literal Provider Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.EnumerationLiteralProviderClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getEnumerationLiteralProviderClass()
	 * @generated
	 */
	int ENUMERATION_LITERAL_PROVIDER_CLASS = 3;

	/**
	 * The feature id for the '<em><b>Enumeration Literal Property1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1 = 0;

	/**
	 * The number of structural features of the '<em>Enumeration Literal Provider Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_LITERAL_PROVIDER_CLASS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl <em>Class1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClass1()
	 * @generated
	 */
	int CLASS1 = 4;

	/**
	 * The feature id for the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__NON_MULTIPLE_PROPERTY = 0;

	/**
	 * The feature id for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = 2;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = 4;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = 5;

	/**
	 * The feature id for the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = 6;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = 7;

	/**
	 * The feature id for the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE = 8;

	/**
	 * The feature id for the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = 9;

	/**
	 * The feature id for the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = 10;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = 11;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = 12;

	/**
	 * The number of structural features of the '<em>Class1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS1_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class2Impl <em>Class2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.Class2Impl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClass2()
	 * @generated
	 */
	int CLASS2 = 5;

	/**
	 * The feature id for the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__NON_MULTIPLE_PROPERTY = CLASS1__NON_MULTIPLE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__MULTIPLE_UNIQUE_ORDERED_PROPERTY = CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_STRING_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE;

	/**
	 * The number of structural features of the '<em>Class2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS2_FEATURE_COUNT = CLASS1_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1 <em>Interface1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface1()
	 * @generated
	 */
	int INTERFACE1 = 6;

	/**
	 * The number of structural features of the '<em>Interface1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE1_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2 <em>Interface2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface2()
	 * @generated
	 */
	int INTERFACE2 = 7;

	/**
	 * The number of structural features of the '<em>Interface2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE2_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3 <em>Interface3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface3()
	 * @generated
	 */
	int INTERFACE3 = 8;

	/**
	 * The number of structural features of the '<em>Interface3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE3_FEATURE_COUNT = INTERFACE2_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CopyableClassImpl <em>Copyable Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.CopyableClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getCopyableClass()
	 * @generated
	 */
	int COPYABLE_CLASS = 9;

	/**
	 * The number of structural features of the '<em>Copyable Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COPYABLE_CLASS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.C1ImplementationImpl <em>C1 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.C1ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getC1Implementation()
	 * @generated
	 */
	int C1_IMPLEMENTATION = 10;

	/**
	 * The feature id for the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__NON_MULTIPLE_PROPERTY = CLASS1__NON_MULTIPLE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__MULTIPLE_UNIQUE_ORDERED_PROPERTY = CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE;

	/**
	 * The number of structural features of the '<em>C1 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int C1_IMPLEMENTATION_FEATURE_COUNT = CLASS1_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I1I2ImplementationImpl <em>I1I2 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I1I2ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI1I2Implementation()
	 * @generated
	 */
	int I1I2_IMPLEMENTATION = 11;

	/**
	 * The number of structural features of the '<em>I1I2 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I1I2_IMPLEMENTATION_FEATURE_COUNT = INTERFACE1_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I1ImplementationImpl <em>I1 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I1ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI1Implementation()
	 * @generated
	 */
	int I1_IMPLEMENTATION = 12;

	/**
	 * The number of structural features of the '<em>I1 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I1_IMPLEMENTATION_FEATURE_COUNT = INTERFACE1_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I2C1ImplementationImpl <em>I2C1 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I2C1ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI2C1Implementation()
	 * @generated
	 */
	int I2C1_IMPLEMENTATION = 13;

	/**
	 * The feature id for the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__NON_MULTIPLE_PROPERTY = INTERFACE2_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__MULTIPLE_UNIQUE_ORDERED_PROPERTY = INTERFACE2_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = INTERFACE2_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = INTERFACE2_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = INTERFACE2_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = INTERFACE2_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = INTERFACE2_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = INTERFACE2_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_MULTIPLE = INTERFACE2_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = INTERFACE2_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = INTERFACE2_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = INTERFACE2_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = INTERFACE2_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>I2C1 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I2C1_IMPLEMENTATION_FEATURE_COUNT = INTERFACE2_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I3C2ImplementationImpl <em>I3C2 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I3C2ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI3C2Implementation()
	 * @generated
	 */
	int I3C2_IMPLEMENTATION = 14;

	/**
	 * The feature id for the '<em><b>Non Multiple Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__NON_MULTIPLE_PROPERTY = CLASS2__NON_MULTIPLE_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__MULTIPLE_UNIQUE_ORDERED_PROPERTY = CLASS2__MULTIPLE_UNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = CLASS2__MULTIPLE_UNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = CLASS2__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = CLASS2__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property String Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_STRING_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_STRING_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Non Multiple</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Object Multiple</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_OBJECT_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE;

	/**
	 * The feature id for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = CLASS2__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE;

	/**
	 * The number of structural features of the '<em>I3C2 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3C2_IMPLEMENTATION_FEATURE_COUNT = CLASS2_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I3ImplementationImpl <em>I3 Implementation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I3ImplementationImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI3Implementation()
	 * @generated
	 */
	int I3_IMPLEMENTATION = 15;

	/**
	 * The number of structural features of the '<em>I3 Implementation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int I3_IMPLEMENTATION_FEATURE_COUNT = INTERFACE3_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ClonableCopyableClassImpl <em>Clonable Copyable Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.ClonableCopyableClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClonableCopyableClass()
	 * @generated
	 */
	int CLONABLE_COPYABLE_CLASS = 16;

	/**
	 * The number of structural features of the '<em>Clonable Copyable Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLONABLE_COPYABLE_CLASS_FEATURE_COUNT = COPYABLE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.InitializableCopyableClassImpl <em>Initializable Copyable Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.InitializableCopyableClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInitializableCopyableClass()
	 * @generated
	 */
	int INITIALIZABLE_COPYABLE_CLASS = 17;

	/**
	 * The number of structural features of the '<em>Initializable Copyable Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZABLE_COPYABLE_CLASS_FEATURE_COUNT = COPYABLE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.NonCopyableClassImpl <em>Non Copyable Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.NonCopyableClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getNonCopyableClass()
	 * @generated
	 */
	int NON_COPYABLE_CLASS = 18;

	/**
	 * The number of structural features of the '<em>Non Copyable Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_COPYABLE_CLASS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.StaticPropertyAndOperationClassImpl <em>Static Property And Operation Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.StaticPropertyAndOperationClassImpl
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getStaticPropertyAndOperationClass()
	 * @generated
	 */
	int STATIC_PROPERTY_AND_OPERATION_CLASS = 19;

	/**
	 * The number of structural features of the '<em>Static Property And Operation Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATIC_PROPERTY_AND_OPERATION_CLASS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 <em>Enumeration1</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getEnumeration1()
	 * @generated
	 */
	int ENUMERATION1 = 20;


	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass <em>Container Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass
	 * @generated
	 */
	EClass getContainerClass();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getPrimitiveTypeProviderClassInstances <em>Primitive Type Provider Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Primitive Type Provider Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getPrimitiveTypeProviderClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_PrimitiveTypeProviderClassInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getCollectionTypeProviderClassInstances <em>Collection Type Provider Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Collection Type Provider Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getCollectionTypeProviderClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_CollectionTypeProviderClassInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getEnumerationLiteralProviderClassInstances <em>Enumeration Literal Provider Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enumeration Literal Provider Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getEnumerationLiteralProviderClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_EnumerationLiteralProviderClassInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getClass1Instances <em>Class1 Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class1 Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getClass1Instances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_Class1Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getClass2Instances <em>Class2 Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class2 Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getClass2Instances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_Class2Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface1Instances <em>Interface1 Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface1 Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface1Instances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_Interface1Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface2Instances <em>Interface2 Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface2 Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface2Instances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_Interface2Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface3Instances <em>Interface3 Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interface3 Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getInterface3Instances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_Interface3Instances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getCopyableClassInstances <em>Copyable Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Copyable Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getCopyableClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_CopyableClassInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getNonCopyableClassInstances <em>Non Copyable Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Non Copyable Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getNonCopyableClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_NonCopyableClassInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getStaticPropertyAndOperationClassInstances <em>Static Property And Operation Class Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Static Property And Operation Class Instances</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass#getStaticPropertyAndOperationClassInstances()
	 * @see #getContainerClass()
	 * @generated
	 */
	EReference getContainerClass_StaticPropertyAndOperationClassInstances();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass <em>Primitive Type Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type Provider Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass
	 * @generated
	 */
	EClass getPrimitiveTypeProviderClass();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#isBooleanProperty1 <em>Boolean Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#isBooleanProperty1()
	 * @see #getPrimitiveTypeProviderClass()
	 * @generated
	 */
	EAttribute getPrimitiveTypeProviderClass_BooleanProperty1();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getIntegerProperty1 <em>Integer Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getIntegerProperty1()
	 * @see #getPrimitiveTypeProviderClass()
	 * @generated
	 */
	EAttribute getPrimitiveTypeProviderClass_IntegerProperty1();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getRealProperty1 <em>Real Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getRealProperty1()
	 * @see #getPrimitiveTypeProviderClass()
	 * @generated
	 */
	EAttribute getPrimitiveTypeProviderClass_RealProperty1();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getStringProperty1 <em>String Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass#getStringProperty1()
	 * @see #getPrimitiveTypeProviderClass()
	 * @generated
	 */
	EAttribute getPrimitiveTypeProviderClass_StringProperty1();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass <em>Collection Type Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Type Provider Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass
	 * @generated
	 */
	EClass getCollectionTypeProviderClass();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getBagProperty1 <em>Bag Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Bag Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getBagProperty1()
	 * @see #getCollectionTypeProviderClass()
	 * @generated
	 */
	EAttribute getCollectionTypeProviderClass_BagProperty1();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getOrderedSetProperty1 <em>Ordered Set Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Ordered Set Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getOrderedSetProperty1()
	 * @see #getCollectionTypeProviderClass()
	 * @generated
	 */
	EAttribute getCollectionTypeProviderClass_OrderedSetProperty1();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSequenceProperty1 <em>Sequence Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sequence Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSequenceProperty1()
	 * @see #getCollectionTypeProviderClass()
	 * @generated
	 */
	EAttribute getCollectionTypeProviderClass_SequenceProperty1();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSetProperty1 <em>Set Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Set Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSetProperty1()
	 * @see #getCollectionTypeProviderClass()
	 * @generated
	 */
	EAttribute getCollectionTypeProviderClass_SetProperty1();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass <em>Enumeration Literal Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Literal Provider Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass
	 * @generated
	 */
	EClass getEnumerationLiteralProviderClass();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass#getEnumerationLiteralProperty1 <em>Enumeration Literal Property1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enumeration Literal Property1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass#getEnumerationLiteralProperty1()
	 * @see #getEnumerationLiteralProviderClass()
	 * @generated
	 */
	EAttribute getEnumerationLiteralProviderClass_EnumerationLiteralProperty1();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1 <em>Class1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1
	 * @generated
	 */
	EClass getClass1();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getNonMultipleProperty <em>Non Multiple Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Non Multiple Property</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getNonMultipleProperty()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_NonMultipleProperty();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueOrderedProperty <em>Multiple Unique Ordered Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multiple Unique Ordered Property</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueOrderedProperty()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_MultipleUniqueOrderedProperty();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueUnorderedProperty <em>Multiple Unique Unordered Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multiple Unique Unordered Property</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleUniqueUnorderedProperty()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_MultipleUniqueUnorderedProperty();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueOrderedProperty <em>Multiple Nonunique Ordered Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multiple Nonunique Ordered Property</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueOrderedProperty()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_MultipleNonuniqueOrderedProperty();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueUnorderedProperty <em>Multiple Nonunique Unordered Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Multiple Nonunique Unordered Property</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getMultipleNonuniqueUnorderedProperty()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_MultipleNonuniqueUnorderedProperty();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#isArgumentPropertyBooleanNonMultiple <em>Argument Property Boolean Non Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Argument Property Boolean Non Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#isArgumentPropertyBooleanNonMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyBooleanNonMultiple();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringNonMultiple <em>Argument Property String Non Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Argument Property String Non Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringNonMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyStringNonMultiple();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyBooleanMultiple <em>Argument Property Boolean Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Argument Property Boolean Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyBooleanMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyBooleanMultiple();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringMultiple <em>Argument Property String Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Argument Property String Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyStringMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyStringMultiple();

	/**
	 * Returns the meta object for the reference '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectNonMultiple <em>Argument Property Object Non Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Argument Property Object Non Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectNonMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EReference getClass1_ArgumentPropertyObjectNonMultiple();

	/**
	 * Returns the meta object for the reference list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectMultiple <em>Argument Property Object Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Argument Property Object Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyObjectMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EReference getClass1_ArgumentPropertyObjectMultiple();

	/**
	 * Returns the meta object for the attribute '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralNonMultiple <em>Argument Property Enumeration Literal Non Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Argument Property Enumeration Literal Non Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralNonMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyEnumerationLiteralNonMultiple();

	/**
	 * Returns the meta object for the attribute list '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralMultiple <em>Argument Property Enumeration Literal Multiple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Argument Property Enumeration Literal Multiple</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1#getArgumentPropertyEnumerationLiteralMultiple()
	 * @see #getClass1()
	 * @generated
	 */
	EAttribute getClass1_ArgumentPropertyEnumerationLiteralMultiple();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class2 <em>Class2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class2</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class2
	 * @generated
	 */
	EClass getClass2();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1 <em>Interface1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1
	 * @generated
	 */
	EClass getInterface1();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2 <em>Interface2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface2</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2
	 * @generated
	 */
	EClass getInterface2();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3 <em>Interface3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface3</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3
	 * @generated
	 */
	EClass getInterface3();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass <em>Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Copyable Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass
	 * @generated
	 */
	EClass getCopyableClass();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation <em>C1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>C1 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation
	 * @generated
	 */
	EClass getC1Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation <em>I1I2 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>I1I2 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation
	 * @generated
	 */
	EClass getI1I2Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation <em>I1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>I1 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation
	 * @generated
	 */
	EClass getI1Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation <em>I2C1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>I2C1 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation
	 * @generated
	 */
	EClass getI2C1Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation <em>I3C2 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>I3C2 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation
	 * @generated
	 */
	EClass getI3C2Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation <em>I3 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>I3 Implementation</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation
	 * @generated
	 */
	EClass getI3Implementation();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass <em>Clonable Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clonable Copyable Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass
	 * @generated
	 */
	EClass getClonableCopyableClass();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass <em>Initializable Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initializable Copyable Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass
	 * @generated
	 */
	EClass getInitializableCopyableClass();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass <em>Non Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Copyable Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass
	 * @generated
	 */
	EClass getNonCopyableClass();

	/**
	 * Returns the meta object for class '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass <em>Static Property And Operation Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Property And Operation Class</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass
	 * @generated
	 */
	EClass getStaticPropertyAndOperationClass();

	/**
	 * Returns the meta object for enum '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 <em>Enumeration1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enumeration1</em>'.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
	 * @generated
	 */
	EEnum getEnumeration1();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TestmodelFactory getTestmodelFactory();

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
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl <em>Container Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getContainerClass()
		 * @generated
		 */
		EClass CONTAINER_CLASS = eINSTANCE.getContainerClass();

		/**
		 * The meta object literal for the '<em><b>Primitive Type Provider Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES = eINSTANCE.getContainerClass_PrimitiveTypeProviderClassInstances();

		/**
		 * The meta object literal for the '<em><b>Collection Type Provider Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES = eINSTANCE.getContainerClass_CollectionTypeProviderClassInstances();

		/**
		 * The meta object literal for the '<em><b>Enumeration Literal Provider Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES = eINSTANCE.getContainerClass_EnumerationLiteralProviderClassInstances();

		/**
		 * The meta object literal for the '<em><b>Class1 Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__CLASS1_INSTANCES = eINSTANCE.getContainerClass_Class1Instances();

		/**
		 * The meta object literal for the '<em><b>Class2 Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__CLASS2_INSTANCES = eINSTANCE.getContainerClass_Class2Instances();

		/**
		 * The meta object literal for the '<em><b>Interface1 Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__INTERFACE1_INSTANCES = eINSTANCE.getContainerClass_Interface1Instances();

		/**
		 * The meta object literal for the '<em><b>Interface2 Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__INTERFACE2_INSTANCES = eINSTANCE.getContainerClass_Interface2Instances();

		/**
		 * The meta object literal for the '<em><b>Interface3 Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__INTERFACE3_INSTANCES = eINSTANCE.getContainerClass_Interface3Instances();

		/**
		 * The meta object literal for the '<em><b>Copyable Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES = eINSTANCE.getContainerClass_CopyableClassInstances();

		/**
		 * The meta object literal for the '<em><b>Non Copyable Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES = eINSTANCE.getContainerClass_NonCopyableClassInstances();

		/**
		 * The meta object literal for the '<em><b>Static Property And Operation Class Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES = eINSTANCE.getContainerClass_StaticPropertyAndOperationClassInstances();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl <em>Primitive Type Provider Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getPrimitiveTypeProviderClass()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE_PROVIDER_CLASS = eINSTANCE.getPrimitiveTypeProviderClass();

		/**
		 * The meta object literal for the '<em><b>Boolean Property1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1 = eINSTANCE.getPrimitiveTypeProviderClass_BooleanProperty1();

		/**
		 * The meta object literal for the '<em><b>Integer Property1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1 = eINSTANCE.getPrimitiveTypeProviderClass_IntegerProperty1();

		/**
		 * The meta object literal for the '<em><b>Real Property1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1 = eINSTANCE.getPrimitiveTypeProviderClass_RealProperty1();

		/**
		 * The meta object literal for the '<em><b>String Property1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1 = eINSTANCE.getPrimitiveTypeProviderClass_StringProperty1();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl <em>Collection Type Provider Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getCollectionTypeProviderClass()
		 * @generated
		 */
		EClass COLLECTION_TYPE_PROVIDER_CLASS = eINSTANCE.getCollectionTypeProviderClass();

		/**
		 * The meta object literal for the '<em><b>Bag Property1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1 = eINSTANCE.getCollectionTypeProviderClass_BagProperty1();

		/**
		 * The meta object literal for the '<em><b>Ordered Set Property1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1 = eINSTANCE.getCollectionTypeProviderClass_OrderedSetProperty1();

		/**
		 * The meta object literal for the '<em><b>Sequence Property1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1 = eINSTANCE.getCollectionTypeProviderClass_SequenceProperty1();

		/**
		 * The meta object literal for the '<em><b>Set Property1</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1 = eINSTANCE.getCollectionTypeProviderClass_SetProperty1();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.EnumerationLiteralProviderClassImpl <em>Enumeration Literal Provider Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.EnumerationLiteralProviderClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getEnumerationLiteralProviderClass()
		 * @generated
		 */
		EClass ENUMERATION_LITERAL_PROVIDER_CLASS = eINSTANCE.getEnumerationLiteralProviderClass();

		/**
		 * The meta object literal for the '<em><b>Enumeration Literal Property1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1 = eINSTANCE.getEnumerationLiteralProviderClass_EnumerationLiteralProperty1();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl <em>Class1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.Class1Impl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClass1()
		 * @generated
		 */
		EClass CLASS1 = eINSTANCE.getClass1();

		/**
		 * The meta object literal for the '<em><b>Non Multiple Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__NON_MULTIPLE_PROPERTY = eINSTANCE.getClass1_NonMultipleProperty();

		/**
		 * The meta object literal for the '<em><b>Multiple Unique Ordered Property</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__MULTIPLE_UNIQUE_ORDERED_PROPERTY = eINSTANCE.getClass1_MultipleUniqueOrderedProperty();

		/**
		 * The meta object literal for the '<em><b>Multiple Unique Unordered Property</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__MULTIPLE_UNIQUE_UNORDERED_PROPERTY = eINSTANCE.getClass1_MultipleUniqueUnorderedProperty();

		/**
		 * The meta object literal for the '<em><b>Multiple Nonunique Ordered Property</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__MULTIPLE_NONUNIQUE_ORDERED_PROPERTY = eINSTANCE.getClass1_MultipleNonuniqueOrderedProperty();

		/**
		 * The meta object literal for the '<em><b>Multiple Nonunique Unordered Property</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__MULTIPLE_NONUNIQUE_UNORDERED_PROPERTY = eINSTANCE.getClass1_MultipleNonuniqueUnorderedProperty();

		/**
		 * The meta object literal for the '<em><b>Argument Property Boolean Non Multiple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_BOOLEAN_NON_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyBooleanNonMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property String Non Multiple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_STRING_NON_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyStringNonMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property Boolean Multiple</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_BOOLEAN_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyBooleanMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property String Multiple</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_STRING_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyStringMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property Object Non Multiple</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS1__ARGUMENT_PROPERTY_OBJECT_NON_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyObjectNonMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property Object Multiple</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS1__ARGUMENT_PROPERTY_OBJECT_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyObjectMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property Enumeration Literal Non Multiple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_NON_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyEnumerationLiteralNonMultiple();

		/**
		 * The meta object literal for the '<em><b>Argument Property Enumeration Literal Multiple</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS1__ARGUMENT_PROPERTY_ENUMERATION_LITERAL_MULTIPLE = eINSTANCE.getClass1_ArgumentPropertyEnumerationLiteralMultiple();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.Class2Impl <em>Class2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.Class2Impl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClass2()
		 * @generated
		 */
		EClass CLASS2 = eINSTANCE.getClass2();

		/**
		 * The meta object literal for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1 <em>Interface1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface1()
		 * @generated
		 */
		EClass INTERFACE1 = eINSTANCE.getInterface1();

		/**
		 * The meta object literal for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2 <em>Interface2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface2()
		 * @generated
		 */
		EClass INTERFACE2 = eINSTANCE.getInterface2();

		/**
		 * The meta object literal for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3 <em>Interface3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInterface3()
		 * @generated
		 */
		EClass INTERFACE3 = eINSTANCE.getInterface3();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CopyableClassImpl <em>Copyable Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.CopyableClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getCopyableClass()
		 * @generated
		 */
		EClass COPYABLE_CLASS = eINSTANCE.getCopyableClass();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.C1ImplementationImpl <em>C1 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.C1ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getC1Implementation()
		 * @generated
		 */
		EClass C1_IMPLEMENTATION = eINSTANCE.getC1Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I1I2ImplementationImpl <em>I1I2 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I1I2ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI1I2Implementation()
		 * @generated
		 */
		EClass I1I2_IMPLEMENTATION = eINSTANCE.getI1I2Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I1ImplementationImpl <em>I1 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I1ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI1Implementation()
		 * @generated
		 */
		EClass I1_IMPLEMENTATION = eINSTANCE.getI1Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I2C1ImplementationImpl <em>I2C1 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I2C1ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI2C1Implementation()
		 * @generated
		 */
		EClass I2C1_IMPLEMENTATION = eINSTANCE.getI2C1Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I3C2ImplementationImpl <em>I3C2 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I3C2ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI3C2Implementation()
		 * @generated
		 */
		EClass I3C2_IMPLEMENTATION = eINSTANCE.getI3C2Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.I3ImplementationImpl <em>I3 Implementation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.I3ImplementationImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getI3Implementation()
		 * @generated
		 */
		EClass I3_IMPLEMENTATION = eINSTANCE.getI3Implementation();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ClonableCopyableClassImpl <em>Clonable Copyable Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.ClonableCopyableClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getClonableCopyableClass()
		 * @generated
		 */
		EClass CLONABLE_COPYABLE_CLASS = eINSTANCE.getClonableCopyableClass();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.InitializableCopyableClassImpl <em>Initializable Copyable Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.InitializableCopyableClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getInitializableCopyableClass()
		 * @generated
		 */
		EClass INITIALIZABLE_COPYABLE_CLASS = eINSTANCE.getInitializableCopyableClass();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.NonCopyableClassImpl <em>Non Copyable Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.NonCopyableClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getNonCopyableClass()
		 * @generated
		 */
		EClass NON_COPYABLE_CLASS = eINSTANCE.getNonCopyableClass();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.StaticPropertyAndOperationClassImpl <em>Static Property And Operation Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.StaticPropertyAndOperationClassImpl
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getStaticPropertyAndOperationClass()
		 * @generated
		 */
		EClass STATIC_PROPERTY_AND_OPERATION_CLASS = eINSTANCE.getStaticPropertyAndOperationClass();

		/**
		 * The meta object literal for the '{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 <em>Enumeration1</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1
		 * @see org.dresdenocl.modelinstancetype.test.testmodel.impl.TestmodelPackageImpl#getEnumeration1()
		 * @generated
		 */
		EEnum ENUMERATION1 = eINSTANCE.getEnumeration1();

	}

} //TestmodelPackage
