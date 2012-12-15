/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.language.ocl.CollectionTypeIdentifierCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.TypeCS;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type Identifier CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.CollectionTypeIdentifierCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.CollectionTypeIdentifierCSImpl#getGenericType <em>Generic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionTypeIdentifierCSImpl extends TypeCSImpl implements CollectionTypeIdentifierCS {
	/**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
	protected Type typeName;
	/**
   * The cached value of the '{@link #getGenericType() <em>Generic Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getGenericType()
   * @generated
   * @ordered
   */
	protected TypeCS genericType;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CollectionTypeIdentifierCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.COLLECTION_TYPE_IDENTIFIER_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Type getTypeName() {
    if (typeName != null && typeName.eIsProxy())
    {
      InternalEObject oldTypeName = (InternalEObject)typeName;
      typeName = (Type)eResolveProxy(oldTypeName);
      if (typeName != oldTypeName)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME, oldTypeName, typeName));
      }
    }
    return typeName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Type basicGetTypeName() {
    return typeName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTypeName(Type newTypeName) {
    Type oldTypeName = typeName;
    typeName = newTypeName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME, oldTypeName, typeName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TypeCS getGenericType() {
    return genericType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetGenericType(TypeCS newGenericType, NotificationChain msgs) {
    TypeCS oldGenericType = genericType;
    genericType = newGenericType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE, oldGenericType, newGenericType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setGenericType(TypeCS newGenericType) {
    if (newGenericType != genericType)
    {
      NotificationChain msgs = null;
      if (genericType != null)
        msgs = ((InternalEObject)genericType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE, null, msgs);
      if (newGenericType != null)
        msgs = ((InternalEObject)newGenericType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE, null, msgs);
      msgs = basicSetGenericType(newGenericType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE, newGenericType, newGenericType));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID)
    {
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE:
        return basicSetGenericType(null, msgs);
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
    switch (featureID)
    {
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME:
        if (resolve) return getTypeName();
        return basicGetTypeName();
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE:
        return getGenericType();
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
    switch (featureID)
    {
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME:
        setTypeName((Type)newValue);
        return;
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE:
        setGenericType((TypeCS)newValue);
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
    switch (featureID)
    {
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME:
        setTypeName((Type)null);
        return;
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE:
        setGenericType((TypeCS)null);
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
    switch (featureID)
    {
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME:
        return typeName != null;
      case OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE:
        return genericType != null;
    }
    return super.eIsSet(featureID);
  }

} //CollectionTypeIdentifierCSImpl
