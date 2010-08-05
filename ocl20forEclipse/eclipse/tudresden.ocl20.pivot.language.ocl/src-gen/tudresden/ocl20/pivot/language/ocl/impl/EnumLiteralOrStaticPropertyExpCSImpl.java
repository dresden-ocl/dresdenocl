/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameCS;

import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Literal Or Static Property Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.EnumLiteralOrStaticPropertyExpCSImpl#getEnumLiteralOrStaticProperty <em>Enum Literal Or Static Property</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumLiteralOrStaticPropertyExpCSImpl extends LiteralExpCSImpl implements EnumLiteralOrStaticPropertyExpCS {
	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected TypePathNameCS typeName;

	/**
	 * The cached value of the '{@link #getEnumLiteralOrStaticProperty() <em>Enum Literal Or Static Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumLiteralOrStaticProperty()
	 * @generated
	 * @ordered
	 */
	protected NamedElement enumLiteralOrStaticProperty;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumLiteralOrStaticPropertyExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypePathNameCS getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeName(TypePathNameCS newTypeName, NotificationChain msgs) {
		TypePathNameCS oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME, oldTypeName, newTypeName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(TypePathNameCS newTypeName) {
		if (newTypeName != typeName) {
			NotificationChain msgs = null;
			if (typeName != null)
				msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME, null, msgs);
			if (newTypeName != null)
				msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME, null, msgs);
			msgs = basicSetTypeName(newTypeName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME, newTypeName, newTypeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement getEnumLiteralOrStaticProperty() {
		if (enumLiteralOrStaticProperty != null && enumLiteralOrStaticProperty.eIsProxy()) {
			InternalEObject oldEnumLiteralOrStaticProperty = (InternalEObject)enumLiteralOrStaticProperty;
			enumLiteralOrStaticProperty = (NamedElement)eResolveProxy(oldEnumLiteralOrStaticProperty);
			if (enumLiteralOrStaticProperty != oldEnumLiteralOrStaticProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY, oldEnumLiteralOrStaticProperty, enumLiteralOrStaticProperty));
			}
		}
		return enumLiteralOrStaticProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedElement basicGetEnumLiteralOrStaticProperty() {
		return enumLiteralOrStaticProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumLiteralOrStaticProperty(NamedElement newEnumLiteralOrStaticProperty) {
		NamedElement oldEnumLiteralOrStaticProperty = enumLiteralOrStaticProperty;
		enumLiteralOrStaticProperty = newEnumLiteralOrStaticProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY, oldEnumLiteralOrStaticProperty, enumLiteralOrStaticProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME:
				return basicSetTypeName(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME:
				return getTypeName();
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY:
				if (resolve) return getEnumLiteralOrStaticProperty();
				return basicGetEnumLiteralOrStaticProperty();
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
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME:
				setTypeName((TypePathNameCS)newValue);
				return;
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY:
				setEnumLiteralOrStaticProperty((NamedElement)newValue);
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
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME:
				setTypeName((TypePathNameCS)null);
				return;
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY:
				setEnumLiteralOrStaticProperty((NamedElement)null);
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
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME:
				return typeName != null;
			case OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY:
				return enumLiteralOrStaticProperty != null;
		}
		return super.eIsSet(featureID);
	}

} //EnumLiteralOrStaticPropertyExpCSImpl
