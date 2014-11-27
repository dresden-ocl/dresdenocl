/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1;
import org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Literal Provider Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.EnumerationLiteralProviderClassImpl#getEnumerationLiteralProperty1 <em>Enumeration Literal Property1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationLiteralProviderClassImpl extends EObjectImpl implements org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass {
	/**
	 * The default value of the '{@link #getEnumerationLiteralProperty1() <em>Enumeration Literal Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationLiteralProperty1()
	 * @generated
	 * @ordered
	 */
	protected static final org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 ENUMERATION_LITERAL_PROPERTY1_EDEFAULT = org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1.LITERAL1;

	/**
	 * The cached value of the '{@link #getEnumerationLiteralProperty1() <em>Enumeration Literal Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationLiteralProperty1()
	 * @generated
	 * @ordered
	 */
	protected org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 enumerationLiteralProperty1 = ENUMERATION_LITERAL_PROPERTY1_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationLiteralProviderClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.Literals.ENUMERATION_LITERAL_PROVIDER_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 getEnumerationLiteralProperty1() {
		return enumerationLiteralProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumerationLiteralProperty1(org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 newEnumerationLiteralProperty1) {
		org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1 oldEnumerationLiteralProperty1 = enumerationLiteralProperty1;
		enumerationLiteralProperty1 = newEnumerationLiteralProperty1 == null ? ENUMERATION_LITERAL_PROPERTY1_EDEFAULT : newEnumerationLiteralProperty1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1, oldEnumerationLiteralProperty1, enumerationLiteralProperty1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1:
				return getEnumerationLiteralProperty1();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1:
				setEnumerationLiteralProperty1((org.dresdenocl.modelinstancetype.test.testmodel.Enumeration1)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1:
				setEnumerationLiteralProperty1(ENUMERATION_LITERAL_PROPERTY1_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.ENUMERATION_LITERAL_PROVIDER_CLASS__ENUMERATION_LITERAL_PROPERTY1:
				return enumerationLiteralProperty1 != ENUMERATION_LITERAL_PROPERTY1_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (enumerationLiteralProperty1: ");
		result.append(enumerationLiteralProperty1);
		result.append(')');
		return result.toString();
	}

} //EnumerationLiteralProviderClassImpl
