/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS;
import tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.PathNameCS;
import tudresden.ocl20.pivot.language.ocl.SimpleNameCS;
import tudresden.ocl20.pivot.language.ocl.TypeCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameCS;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl#getType <em>Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.AttributeContextDeclarationCSImpl#getInitOrDeriveValue <em>Init Or Derive Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeContextDeclarationCSImpl extends ContextDeclarationCSImpl implements AttributeContextDeclarationCS {
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
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TypeCS type;

	/**
	 * The cached value of the '{@link #getInitOrDeriveValue() <em>Init Or Derive Value</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitOrDeriveValue()
	 * @generated
	 * @ordered
	 */
	protected EList<InitOrDeriveValueCS> initOrDeriveValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeContextDeclarationCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.ATTRIBUTE_CONTEXT_DECLARATION_CS;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME, oldTypeName, newTypeName);
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
				msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME, null, msgs);
			if (newTypeName != null)
				msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME, null, msgs);
			msgs = basicSetTypeName(newTypeName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME, newTypeName, newTypeName));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY, oldProperty, property));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeCS getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(TypeCS newType, NotificationChain msgs) {
		TypeCS oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeCS newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InitOrDeriveValueCS> getInitOrDeriveValue() {
		if (initOrDeriveValue == null) {
			initOrDeriveValue = new EObjectContainmentEList<InitOrDeriveValueCS>(InitOrDeriveValueCS.class, this, OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE);
		}
		return initOrDeriveValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME:
				return basicSetTypeName(null, msgs);
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE:
				return basicSetType(null, msgs);
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE:
				return ((InternalEList<?>)getInitOrDeriveValue()).basicRemove(otherEnd, msgs);
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
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME:
				return getTypeName();
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE:
				return getType();
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE:
				return getInitOrDeriveValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME:
				setTypeName((TypePathNameCS)newValue);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY:
				setProperty((Property)newValue);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE:
				setType((TypeCS)newValue);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE:
				getInitOrDeriveValue().clear();
				getInitOrDeriveValue().addAll((Collection<? extends InitOrDeriveValueCS>)newValue);
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
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME:
				setTypeName((TypePathNameCS)null);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY:
				setProperty((Property)null);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE:
				setType((TypeCS)null);
				return;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE:
				getInitOrDeriveValue().clear();
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
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME:
				return typeName != null;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY:
				return property != null;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE:
				return type != null;
			case OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE:
				return initOrDeriveValue != null && !initOrDeriveValue.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AttributeContextDeclarationCSImpl
