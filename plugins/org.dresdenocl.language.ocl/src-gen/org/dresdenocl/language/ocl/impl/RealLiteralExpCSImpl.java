/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.RealLiteralExpCS;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Real Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.RealLiteralExpCSImpl#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.RealLiteralExpCSImpl#getRealValue <em>Real Value</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.RealLiteralExpCSImpl#getNavigationOperator <em>Navigation Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RealLiteralExpCSImpl extends PrimitiveLiteralExpCSImpl implements RealLiteralExpCS {
	/**
	 * The default value of the '{@link #getIntValue() <em>Int Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntValue()
	 * @generated
	 * @ordered
	 */
	protected static final int INT_VALUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIntValue() <em>Int Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntValue()
	 * @generated
	 * @ordered
	 */
	protected int intValue = INT_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRealValue() <em>Real Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealValue()
	 * @generated
	 * @ordered
	 */
	protected static final String REAL_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRealValue() <em>Real Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealValue()
	 * @generated
	 * @ordered
	 */
	protected String realValue = REAL_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNavigationOperator() <em>Navigation Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationOperator()
	 * @generated
	 * @ordered
	 */
	protected static final String NAVIGATION_OPERATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNavigationOperator() <em>Navigation Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationOperator()
	 * @generated
	 * @ordered
	 */
	protected String navigationOperator = NAVIGATION_OPERATOR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RealLiteralExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.REAL_LITERAL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntValue() {
		return intValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntValue(int newIntValue) {
		int oldIntValue = intValue;
		intValue = newIntValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE, oldIntValue, intValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealValue() {
		return realValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealValue(String newRealValue) {
		String oldRealValue = realValue;
		realValue = newRealValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE, oldRealValue, realValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNavigationOperator() {
		return navigationOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigationOperator(String newNavigationOperator) {
		String oldNavigationOperator = navigationOperator;
		navigationOperator = newNavigationOperator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR, oldNavigationOperator, navigationOperator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE:
				return getIntValue();
			case OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE:
				return getRealValue();
			case OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR:
				return getNavigationOperator();
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
			case OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE:
				setIntValue((Integer)newValue);
				return;
			case OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE:
				setRealValue((String)newValue);
				return;
			case OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR:
				setNavigationOperator((String)newValue);
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
			case OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE:
				setIntValue(INT_VALUE_EDEFAULT);
				return;
			case OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE:
				setRealValue(REAL_VALUE_EDEFAULT);
				return;
			case OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR:
				setNavigationOperator(NAVIGATION_OPERATOR_EDEFAULT);
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
			case OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE:
				return intValue != INT_VALUE_EDEFAULT;
			case OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE:
				return REAL_VALUE_EDEFAULT == null ? realValue != null : !REAL_VALUE_EDEFAULT.equals(realValue);
			case OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR:
				return NAVIGATION_OPERATOR_EDEFAULT == null ? navigationOperator != null : !NAVIGATION_OPERATOR_EDEFAULT.equals(navigationOperator);
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
		result.append(" (intValue: ");
		result.append(intValue);
		result.append(", realValue: ");
		result.append(realValue);
		result.append(", navigationOperator: ");
		result.append(navigationOperator);
		result.append(')');
		return result.toString();
	}

} //RealLiteralExpCSImpl
