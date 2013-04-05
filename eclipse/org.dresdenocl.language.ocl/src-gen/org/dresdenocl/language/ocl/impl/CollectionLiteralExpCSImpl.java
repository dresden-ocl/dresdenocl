/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.dresdenocl.language.ocl.CollectionLiteralExpCS;
import org.dresdenocl.language.ocl.CollectionLiteralPartsCS;
import org.dresdenocl.language.ocl.CollectionTypeIdentifierCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.CollectionLiteralExpCSImpl#getCollectionType <em>Collection Type</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.CollectionLiteralExpCSImpl#getCollectionLiteralParts <em>Collection Literal Parts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionLiteralExpCSImpl extends LiteralExpCSImpl implements CollectionLiteralExpCS {
	/**
   * The cached value of the '{@link #getCollectionType() <em>Collection Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCollectionType()
   * @generated
   * @ordered
   */
	protected CollectionTypeIdentifierCS collectionType;

	/**
   * The cached value of the '{@link #getCollectionLiteralParts() <em>Collection Literal Parts</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getCollectionLiteralParts()
   * @generated
   * @ordered
   */
	protected EList<CollectionLiteralPartsCS> collectionLiteralParts;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected CollectionLiteralExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.COLLECTION_LITERAL_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public CollectionTypeIdentifierCS getCollectionType() {
    return collectionType;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetCollectionType(CollectionTypeIdentifierCS newCollectionType, NotificationChain msgs) {
    CollectionTypeIdentifierCS oldCollectionType = collectionType;
    collectionType = newCollectionType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE, oldCollectionType, newCollectionType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setCollectionType(CollectionTypeIdentifierCS newCollectionType) {
    if (newCollectionType != collectionType)
    {
      NotificationChain msgs = null;
      if (collectionType != null)
        msgs = ((InternalEObject)collectionType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE, null, msgs);
      if (newCollectionType != null)
        msgs = ((InternalEObject)newCollectionType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE, null, msgs);
      msgs = basicSetCollectionType(newCollectionType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE, newCollectionType, newCollectionType));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<CollectionLiteralPartsCS> getCollectionLiteralParts() {
    if (collectionLiteralParts == null)
    {
      collectionLiteralParts = new EObjectContainmentEList<CollectionLiteralPartsCS>(CollectionLiteralPartsCS.class, this, OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS);
    }
    return collectionLiteralParts;
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
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE:
        return basicSetCollectionType(null, msgs);
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS:
        return ((InternalEList<?>)getCollectionLiteralParts()).basicRemove(otherEnd, msgs);
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
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE:
        return getCollectionType();
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS:
        return getCollectionLiteralParts();
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
    switch (featureID)
    {
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE:
        setCollectionType((CollectionTypeIdentifierCS)newValue);
        return;
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS:
        getCollectionLiteralParts().clear();
        getCollectionLiteralParts().addAll((Collection<? extends CollectionLiteralPartsCS>)newValue);
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
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE:
        setCollectionType((CollectionTypeIdentifierCS)null);
        return;
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS:
        getCollectionLiteralParts().clear();
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
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE:
        return collectionType != null;
      case OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS:
        return collectionLiteralParts != null && !collectionLiteralParts.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //CollectionLiteralExpCSImpl
