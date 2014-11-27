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

import org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1;
import org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;
import org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory;
import org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;
import org.dresdenocl.modelinstancetype.test.testmodel.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TestmodelFactoryImpl extends EFactoryImpl implements org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory init() {
		try {
			org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory theTestmodelFactory = (org.dresdenocl.modelinstancetype.test.testmodel.TestmodelFactory)EPackage.Registry.INSTANCE.getEFactory(TestmodelPackage.eNS_URI);
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
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.CONTAINER_CLASS: return createContainerClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS: return createPrimitiveTypeProviderClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS: return createCollectionTypeProviderClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS: return createEnumerationLiteralProviderClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.CLASS1: return createClass1();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.CLASS2: return createClass2();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.COPYABLE_CLASS: return createCopyableClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.C1_IMPLEMENTATION: return createC1Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.I1I2_IMPLEMENTATION: return createI1I2Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.I1_IMPLEMENTATION: return createI1Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.I2C1_IMPLEMENTATION: return createI2C1Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.I3C2_IMPLEMENTATION: return createI3C2Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.I3_IMPLEMENTATION: return createI3Implementation();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.CLONABLE_COPYABLE_CLASS: return createClonableCopyableClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.INITIALIZABLE_COPYABLE_CLASS: return createInitializableCopyableClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.NON_COPYABLE_CLASS: return createNonCopyableClass();
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.STATIC_PROPERTY_AND_OPERATION_CLASS: return createStaticPropertyAndOperationClass();
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
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION1:
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
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION1:
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
	public org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass createContainerClass() {
		ContainerClassImpl containerClass = new ContainerClassImpl();
		return containerClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass createPrimitiveTypeProviderClass() {
		PrimitiveTypeProviderClassImpl primitiveTypeProviderClass = new PrimitiveTypeProviderClassImpl();
		return primitiveTypeProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass createCollectionTypeProviderClass() {
		CollectionTypeProviderClassImpl collectionTypeProviderClass = new CollectionTypeProviderClassImpl();
		return collectionTypeProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass createEnumerationLiteralProviderClass() {
		EnumerationLiteralProviderClassImpl enumerationLiteralProviderClass = new EnumerationLiteralProviderClassImpl();
		return enumerationLiteralProviderClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.Class1 createClass1() {
		Class1Impl class1 = new Class1Impl();
		return class1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.Class2 createClass2() {
		Class2Impl class2 = new Class2Impl();
		return class2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass createCopyableClass() {
		CopyableClassImpl copyableClass = new CopyableClassImpl();
		return copyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation createC1Implementation() {
		C1ImplementationImpl c1Implementation = new C1ImplementationImpl();
		return c1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation createI1I2Implementation() {
		I1I2ImplementationImpl i1I2Implementation = new I1I2ImplementationImpl();
		return i1I2Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation createI1Implementation() {
		I1ImplementationImpl i1Implementation = new I1ImplementationImpl();
		return i1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation createI2C1Implementation() {
		I2C1ImplementationImpl i2C1Implementation = new I2C1ImplementationImpl();
		return i2C1Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation createI3C2Implementation() {
		I3C2ImplementationImpl i3C2Implementation = new I3C2ImplementationImpl();
		return i3C2Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation createI3Implementation() {
		I3ImplementationImpl i3Implementation = new I3ImplementationImpl();
		return i3Implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass createClonableCopyableClass() {
		ClonableCopyableClassImpl clonableCopyableClass = new ClonableCopyableClassImpl();
		return clonableCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass createInitializableCopyableClass() {
		InitializableCopyableClassImpl initializableCopyableClass = new InitializableCopyableClassImpl();
		return initializableCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass createNonCopyableClass() {
		NonCopyableClassImpl nonCopyableClass = new NonCopyableClassImpl();
		return nonCopyableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass createStaticPropertyAndOperationClass() {
		StaticPropertyAndOperationClassImpl staticPropertyAndOperationClass = new StaticPropertyAndOperationClassImpl();
		return staticPropertyAndOperationClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 createEnumeration1FromString(EDataType eDataType, String initialValue) {
		org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 result = org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1.get(initialValue);
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
	public org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage getTestmodelPackage() {
		return (org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage getPackage() {
		return org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.eINSTANCE;
	}

} //TestmodelFactoryImpl
