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

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Primitive Type Provider Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl#isBooleanProperty1 <em>Boolean Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl#getIntegerProperty1 <em>Integer Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl#getRealProperty1 <em>Real Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.PrimitiveTypeProviderClassImpl#getStringProperty1 <em>String Property1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrimitiveTypeProviderClassImpl extends EObjectImpl implements PrimitiveTypeProviderClass {
	/**
	 * The default value of the '{@link #isBooleanProperty1() <em>Boolean Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanProperty1()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_PROPERTY1_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBooleanProperty1() <em>Boolean Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanProperty1()
	 * @generated
	 * @ordered
	 */
	protected boolean booleanProperty1 = BOOLEAN_PROPERTY1_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntegerProperty1() <em>Integer Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerProperty1()
	 * @generated
	 * @ordered
	 */
	protected static final int INTEGER_PROPERTY1_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIntegerProperty1() <em>Integer Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegerProperty1()
	 * @generated
	 * @ordered
	 */
	protected int integerProperty1 = INTEGER_PROPERTY1_EDEFAULT;

	/**
	 * The default value of the '{@link #getRealProperty1() <em>Real Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealProperty1()
	 * @generated
	 * @ordered
	 */
	protected static final float REAL_PROPERTY1_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getRealProperty1() <em>Real Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealProperty1()
	 * @generated
	 * @ordered
	 */
	protected float realProperty1 = REAL_PROPERTY1_EDEFAULT;

	/**
	 * The default value of the '{@link #getStringProperty1() <em>String Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringProperty1()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_PROPERTY1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringProperty1() <em>String Property1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringProperty1()
	 * @generated
	 * @ordered
	 */
	protected String stringProperty1 = STRING_PROPERTY1_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrimitiveTypeProviderClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelPackage.Literals.PRIMITIVE_TYPE_PROVIDER_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBooleanProperty1() {
		return booleanProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanProperty1(boolean newBooleanProperty1) {
		boolean oldBooleanProperty1 = booleanProperty1;
		booleanProperty1 = newBooleanProperty1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1, oldBooleanProperty1, booleanProperty1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntegerProperty1() {
		return integerProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegerProperty1(int newIntegerProperty1) {
		int oldIntegerProperty1 = integerProperty1;
		integerProperty1 = newIntegerProperty1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1, oldIntegerProperty1, integerProperty1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getRealProperty1() {
		return realProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealProperty1(float newRealProperty1) {
		float oldRealProperty1 = realProperty1;
		realProperty1 = newRealProperty1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1, oldRealProperty1, realProperty1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringProperty1() {
		return stringProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringProperty1(String newStringProperty1) {
		String oldStringProperty1 = stringProperty1;
		stringProperty1 = newStringProperty1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1, oldStringProperty1, stringProperty1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1:
				return isBooleanProperty1();
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1:
				return getIntegerProperty1();
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1:
				return getRealProperty1();
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1:
				return getStringProperty1();
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
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1:
				setBooleanProperty1((Boolean)newValue);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1:
				setIntegerProperty1((Integer)newValue);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1:
				setRealProperty1((Float)newValue);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1:
				setStringProperty1((String)newValue);
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
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1:
				setBooleanProperty1(BOOLEAN_PROPERTY1_EDEFAULT);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1:
				setIntegerProperty1(INTEGER_PROPERTY1_EDEFAULT);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1:
				setRealProperty1(REAL_PROPERTY1_EDEFAULT);
				return;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1:
				setStringProperty1(STRING_PROPERTY1_EDEFAULT);
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
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__BOOLEAN_PROPERTY1:
				return booleanProperty1 != BOOLEAN_PROPERTY1_EDEFAULT;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__INTEGER_PROPERTY1:
				return integerProperty1 != INTEGER_PROPERTY1_EDEFAULT;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__REAL_PROPERTY1:
				return realProperty1 != REAL_PROPERTY1_EDEFAULT;
			case TestmodelPackage.PRIMITIVE_TYPE_PROVIDER_CLASS__STRING_PROPERTY1:
				return STRING_PROPERTY1_EDEFAULT == null ? stringProperty1 != null : !STRING_PROPERTY1_EDEFAULT.equals(stringProperty1);
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
		result.append(" (booleanProperty1: ");
		result.append(booleanProperty1);
		result.append(", integerProperty1: ");
		result.append(integerProperty1);
		result.append(", realProperty1: ");
		result.append(realProperty1);
		result.append(", stringProperty1: ");
		result.append(stringProperty1);
		result.append(')');
		return result.toString();
	}

} //PrimitiveTypeProviderClassImpl
