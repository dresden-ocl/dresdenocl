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

import tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS;
import tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.CollectionTypeLiteralExpCSImpl#getCollectionType <em>Collection Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionTypeLiteralExpCSImpl extends LiteralExpCSImpl implements CollectionTypeLiteralExpCS {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionTypeLiteralExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.COLLECTION_TYPE_LITERAL_EXP_CS;
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
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE, oldCollectionType, newCollectionType);
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
		if (newCollectionType != collectionType) {
			NotificationChain msgs = null;
			if (collectionType != null)
				msgs = ((InternalEObject)collectionType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE, null, msgs);
			if (newCollectionType != null)
				msgs = ((InternalEObject)newCollectionType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE, null, msgs);
			msgs = basicSetCollectionType(newCollectionType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE, newCollectionType, newCollectionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE:
				return basicSetCollectionType(null, msgs);
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
			case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE:
				return getCollectionType();
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
			case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE:
				setCollectionType((CollectionTypeIdentifierCS)newValue);
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
			case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE:
				setCollectionType((CollectionTypeIdentifierCS)null);
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
			case OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE:
				return collectionType != null;
		}
		return super.eIsSet(featureID);
	}

} //CollectionTypeLiteralExpCSImpl
