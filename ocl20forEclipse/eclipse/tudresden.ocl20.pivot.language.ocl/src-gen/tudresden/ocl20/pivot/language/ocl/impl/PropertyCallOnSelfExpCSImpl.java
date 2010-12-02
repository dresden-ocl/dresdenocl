/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS;
import tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS;

import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Call On Self Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.PropertyCallOnSelfExpCSImpl#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyCallOnSelfExpCSImpl extends PropertyCallExpCSImpl implements PropertyCallOnSelfExpCS {
	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * The default value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkedPre()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MARKED_PRE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkedPre()
	 * @generated
	 * @ordered
	 */
	protected boolean isMarkedPre = IS_MARKED_PRE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyCallOnSelfExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.PROPERTY_CALL_ON_SELF_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMarkedPre() {
		return isMarkedPre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMarkedPre(boolean newIsMarkedPre) {
		boolean oldIsMarkedPre = isMarkedPre;
		isMarkedPre = newIsMarkedPre;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE, oldIsMarkedPre, isMarkedPre));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE:
				return isIsMarkedPre();
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
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY:
				setProperty((Property)newValue);
				return;
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE:
				setIsMarkedPre((Boolean)newValue);
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
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY:
				setProperty((Property)null);
				return;
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE:
				setIsMarkedPre(IS_MARKED_PRE_EDEFAULT);
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
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY:
				return property != null;
			case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE:
				return isMarkedPre != IS_MARKED_PRE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyCallBaseExpCS.class) {
			switch (derivedFeatureID) {
				case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY: return OclPackage.PROPERTY_CALL_BASE_EXP_CS__PROPERTY;
				case OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE: return OclPackage.PROPERTY_CALL_BASE_EXP_CS__IS_MARKED_PRE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyCallBaseExpCS.class) {
			switch (baseFeatureID) {
				case OclPackage.PROPERTY_CALL_BASE_EXP_CS__PROPERTY: return OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY;
				case OclPackage.PROPERTY_CALL_BASE_EXP_CS__IS_MARKED_PRE: return OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (isMarkedPre: ");
		result.append(isMarkedPre);
		result.append(')');
		return result.toString();
	}

} //PropertyCallOnSelfExpCSImpl
