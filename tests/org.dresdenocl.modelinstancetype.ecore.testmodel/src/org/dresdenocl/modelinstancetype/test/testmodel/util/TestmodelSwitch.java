/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
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
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage
 * @generated
 */
public class TestmodelSwitch<T> extends 
Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TestmodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelSwitch() {
		if (modelPackage == null) {
			modelPackage = TestmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TestmodelPackage.CONTAINER_CLASS: {
				ContainerClass containerClass = (ContainerClass)theEObject;
				T result = caseContainerClass(containerClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS: {
				PrimitiveTypeProviderClass primitiveTypeProviderClass = (PrimitiveTypeProviderClass)theEObject;
				T result = casePrimitiveTypeProviderClass(primitiveTypeProviderClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS: {
				CollectionTypeProviderClass collectionTypeProviderClass = (CollectionTypeProviderClass)theEObject;
				T result = caseCollectionTypeProviderClass(collectionTypeProviderClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS: {
				EnumerationLiteralProviderClass enumerationLiteralProviderClass = (EnumerationLiteralProviderClass)theEObject;
				T result = caseEnumerationLiteralProviderClass(enumerationLiteralProviderClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.CLASS1: {
				Class1 class1 = (Class1)theEObject;
				T result = caseClass1(class1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.CLASS2: {
				Class2 class2 = (Class2)theEObject;
				T result = caseClass2(class2);
				if (result == null) result = caseClass1(class2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.INTERFACE1: {
				Interface1 interface1 = (Interface1)theEObject;
				T result = caseInterface1(interface1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.INTERFACE2: {
				Interface2 interface2 = (Interface2)theEObject;
				T result = caseInterface2(interface2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.INTERFACE3: {
				Interface3 interface3 = (Interface3)theEObject;
				T result = caseInterface3(interface3);
				if (result == null) result = caseInterface2(interface3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.COPYABLE_CLASS: {
				CopyableClass copyableClass = (CopyableClass)theEObject;
				T result = caseCopyableClass(copyableClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.C1_IMPLEMENTATION: {
				C1Implementation c1Implementation = (C1Implementation)theEObject;
				T result = caseC1Implementation(c1Implementation);
				if (result == null) result = caseClass1(c1Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.I1I2_IMPLEMENTATION: {
				I1I2Implementation i1I2Implementation = (I1I2Implementation)theEObject;
				T result = caseI1I2Implementation(i1I2Implementation);
				if (result == null) result = caseInterface1(i1I2Implementation);
				if (result == null) result = caseInterface2(i1I2Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.I1_IMPLEMENTATION: {
				I1Implementation i1Implementation = (I1Implementation)theEObject;
				T result = caseI1Implementation(i1Implementation);
				if (result == null) result = caseInterface1(i1Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.I2C1_IMPLEMENTATION: {
				I2C1Implementation i2C1Implementation = (I2C1Implementation)theEObject;
				T result = caseI2C1Implementation(i2C1Implementation);
				if (result == null) result = caseInterface2(i2C1Implementation);
				if (result == null) result = caseClass1(i2C1Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.I3C2_IMPLEMENTATION: {
				I3C2Implementation i3C2Implementation = (I3C2Implementation)theEObject;
				T result = caseI3C2Implementation(i3C2Implementation);
				if (result == null) result = caseClass2(i3C2Implementation);
				if (result == null) result = caseInterface3(i3C2Implementation);
				if (result == null) result = caseClass1(i3C2Implementation);
				if (result == null) result = caseInterface2(i3C2Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.I3_IMPLEMENTATION: {
				I3Implementation i3Implementation = (I3Implementation)theEObject;
				T result = caseI3Implementation(i3Implementation);
				if (result == null) result = caseInterface3(i3Implementation);
				if (result == null) result = caseInterface2(i3Implementation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.CLONABLE_COPYABLE_CLASS: {
				ClonableCopyableClass clonableCopyableClass = (ClonableCopyableClass)theEObject;
				T result = caseClonableCopyableClass(clonableCopyableClass);
				if (result == null) result = caseCopyableClass(clonableCopyableClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.INITIALIZABLE_COPYABLE_CLASS: {
				InitializableCopyableClass initializableCopyableClass = (InitializableCopyableClass)theEObject;
				T result = caseInitializableCopyableClass(initializableCopyableClass);
				if (result == null) result = caseCopyableClass(initializableCopyableClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.NON_COPYABLE_CLASS: {
				NonCopyableClass nonCopyableClass = (NonCopyableClass)theEObject;
				T result = caseNonCopyableClass(nonCopyableClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TestmodelPackage.STATIC_PROPERTY_AND_OPERATION_CLASS: {
				StaticPropertyAndOperationClass staticPropertyAndOperationClass = (StaticPropertyAndOperationClass)theEObject;
				T result = caseStaticPropertyAndOperationClass(staticPropertyAndOperationClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerClass(ContainerClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type Provider Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type Provider Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveTypeProviderClass(PrimitiveTypeProviderClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Type Provider Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Type Provider Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionTypeProviderClass(CollectionTypeProviderClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal Provider Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Literal Provider Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationLiteralProviderClass(EnumerationLiteralProviderClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClass1(Class1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClass2(Class2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterface1(Interface1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterface2(Interface2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterface3(Interface3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Copyable Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Copyable Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCopyableClass(CopyableClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>C1 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>C1 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseC1Implementation(C1Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>I1I2 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>I1I2 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseI1I2Implementation(I1I2Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>I1 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>I1 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseI1Implementation(I1Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>I2C1 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>I2C1 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseI2C1Implementation(I2C1Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>I3C2 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>I3C2 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseI3C2Implementation(I3C2Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>I3 Implementation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>I3 Implementation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseI3Implementation(I3Implementation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Clonable Copyable Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Clonable Copyable Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClonableCopyableClass(ClonableCopyableClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initializable Copyable Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initializable Copyable Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInitializableCopyableClass(InitializableCopyableClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Copyable Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Copyable Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNonCopyableClass(NonCopyableClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Static Property And Operation Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Static Property And Operation Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStaticPropertyAndOperationClass(StaticPropertyAndOperationClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TestmodelSwitch
