/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestmodelFactoryImpl extends EFactoryImpl implements TestmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TestmodelFactory init() {
		try {
			TestmodelFactory theTestmodelFactory = (TestmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.tudresden.de/ocl20/pivot/modelinstancetype/test/testmodel"); 
			if (theTestmodelFactory != null) {
				return theTestmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TestmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelFactoryImpl() {
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
			case TestmodelPackage.CONTAINER_CLASS: return createContainerClass();
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS: return createPrimitiveTypeProviderClass();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS: return createCollectionTypeProviderClass();
			case TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS: return createEnumerationLiteralProviderClass();
			case TestmodelPackage.CLASS1: return createClass1();
			case TestmodelPackage.CLASS2: return createClass2();
			case TestmodelPackage.COPYABLE_CLASS: return createCopyableClass();
			case TestmodelPackage.C1_IMPLEMENTATION: return createC1Implementation();
			case TestmodelPackage.I1I2_IMPLEMENTATION: return createI1I2Implementation();
			case TestmodelPackage.I1_IMPLEMENTATION: return createI1Implementation();
			case TestmodelPackage.I2C1_IMPLEMENTATION: return createI2C1Implementation();
			case TestmodelPackage.I3C2_IMPLEMENTATION: return createI3C2Implementation();
			case TestmodelPackage.I3_IMPLEMENTATION: return createI3Implementation();
			case TestmodelPackage.CLONABLE_COPYABLE_CLASS: return createClonableCopyableClass();
			case TestmodelPackage.INITIALIZABLE_COPYABLE_CLASS: return createInitializableCopyableClass();
			case TestmodelPackage.NON_COPYABLE_CLASS: return createNonCopyableClass();
			case TestmodelPackage.STATIC_PROPERTY_AND_OPERATION_CLASS: return createStaticPropertyAndOperationClass();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TestmodelPackage.ENUMERATION1:
				return createEnumeration1FromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TestmodelPackage.ENUMERATION1:
				return convertEnumeration1ToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerClass createContainerClass() {
		ContainerClassImpl containerClass = new ContainerClassImpl();
		return containerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeProviderClass createPrimitiveTypeProviderClass() {
		PrimitiveTypeProviderClassImpl primitiveTypeProviderClass = new PrimitiveTypeProviderClassImpl();
		return primitiveTypeProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionTypeProviderClass createCollectionTypeProviderClass() {
		CollectionTypeProviderClassImpl collectionTypeProviderClass = new CollectionTypeProviderClassImpl();
		return collectionTypeProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteralProviderClass createEnumerationLiteralProviderClass() {
		EnumerationLiteralProviderClassImpl enumerationLiteralProviderClass = new EnumerationLiteralProviderClassImpl();
		return enumerationLiteralProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Class1 createClass1() {
		Class1Impl class1 = new Class1Impl();
		return class1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Class2 createClass2() {
		Class2Impl class2 = new Class2Impl();
		return class2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CopyableClass createCopyableClass() {
		CopyableClassImpl copyableClass = new CopyableClassImpl();
		return copyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public C1Implementation createC1Implementation() {
		C1ImplementationImpl c1Implementation = new C1ImplementationImpl();
		return c1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public I1I2Implementation createI1I2Implementation() {
		I1I2ImplementationImpl i1I2Implementation = new I1I2ImplementationImpl();
		return i1I2Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public I1Implementation createI1Implementation() {
		I1ImplementationImpl i1Implementation = new I1ImplementationImpl();
		return i1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public I2C1Implementation createI2C1Implementation() {
		I2C1ImplementationImpl i2C1Implementation = new I2C1ImplementationImpl();
		return i2C1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public I3C2Implementation createI3C2Implementation() {
		I3C2ImplementationImpl i3C2Implementation = new I3C2ImplementationImpl();
		return i3C2Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public I3Implementation createI3Implementation() {
		I3ImplementationImpl i3Implementation = new I3ImplementationImpl();
		return i3Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClonableCopyableClass createClonableCopyableClass() {
		ClonableCopyableClassImpl clonableCopyableClass = new ClonableCopyableClassImpl();
		return clonableCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitializableCopyableClass createInitializableCopyableClass() {
		InitializableCopyableClassImpl initializableCopyableClass = new InitializableCopyableClassImpl();
		return initializableCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NonCopyableClass createNonCopyableClass() {
		NonCopyableClassImpl nonCopyableClass = new NonCopyableClassImpl();
		return nonCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyAndOperationClass createStaticPropertyAndOperationClass() {
		StaticPropertyAndOperationClassImpl staticPropertyAndOperationClass = new StaticPropertyAndOperationClassImpl();
		return staticPropertyAndOperationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration1 createEnumeration1FromString(EDataType eDataType, String initialValue) {
		Enumeration1 result = Enumeration1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnumeration1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelPackage getTestmodelPackage() {
		return (TestmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TestmodelPackage getPackage() {
		return TestmodelPackage.eINSTANCE;
	}

} //TestmodelFactoryImpl
