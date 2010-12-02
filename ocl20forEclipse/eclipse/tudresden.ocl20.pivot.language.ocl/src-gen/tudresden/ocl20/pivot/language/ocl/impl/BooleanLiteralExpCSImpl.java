/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.BooleanLiteralExpCSImpl#isBooleanLiteral <em>Boolean Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanLiteralExpCSImpl extends PrimitiveLiteralExpCSImpl implements BooleanLiteralExpCS {
	/**
	 * The default value of the '{@link #isBooleanLiteral() <em>Boolean Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanLiteral()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOOLEAN_LITERAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBooleanLiteral() <em>Boolean Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBooleanLiteral()
	 * @generated
	 * @ordered
	 */
	protected boolean booleanLiteral = BOOLEAN_LITERAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanLiteralExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.BOOLEAN_LITERAL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBooleanLiteral() {
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBooleanLiteral(boolean newBooleanLiteral) {
		boolean oldBooleanLiteral = booleanLiteral;
		booleanLiteral = newBooleanLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL, oldBooleanLiteral, booleanLiteral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL:
				return isBooleanLiteral();
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
			case OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL:
				setBooleanLiteral((Boolean)newValue);
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
			case OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL:
				setBooleanLiteral(BOOLEAN_LITERAL_EDEFAULT);
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
			case OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL:
				return booleanLiteral != BOOLEAN_LITERAL_EDEFAULT;
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
		result.append(" (booleanLiteral: ");
		result.append(booleanLiteral);
		result.append(')');
		return result.toString();
	}

} //BooleanLiteralExpCSImpl
