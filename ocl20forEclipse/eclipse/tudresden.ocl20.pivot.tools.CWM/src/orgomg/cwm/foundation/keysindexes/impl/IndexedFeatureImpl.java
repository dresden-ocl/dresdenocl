/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.foundation.keysindexes.Index;
import orgomg.cwm.foundation.keysindexes.IndexedFeature;
import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.StructuralFeature;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indexed Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexedFeatureImpl#isIsAscending <em>Is Ascending</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexedFeatureImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexedFeatureImpl#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexedFeatureImpl extends ModelElementImpl implements IndexedFeature {
	/**
	 * The default value of the '{@link #isIsAscending() <em>Is Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAscending()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ASCENDING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAscending() <em>Is Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsAscending()
	 * @generated
	 * @ordered
	 */
	protected boolean isAscending = IS_ASCENDING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected StructuralFeature feature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexedFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KeysindexesPackage.Literals.INDEXED_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsAscending() {
		return isAscending;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsAscending(boolean newIsAscending) {
		boolean oldIsAscending = isAscending;
		isAscending = newIsAscending;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEXED_FEATURE__IS_ASCENDING, oldIsAscending, isAscending));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature getFeature() {
		if (feature != null && feature.eIsProxy()) {
			InternalEObject oldFeature = (InternalEObject)feature;
			feature = (StructuralFeature)eResolveProxy(oldFeature);
			if (feature != oldFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KeysindexesPackage.INDEXED_FEATURE__FEATURE, oldFeature, feature));
			}
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature basicGetFeature() {
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFeature(StructuralFeature newFeature, NotificationChain msgs) {
		StructuralFeature oldFeature = feature;
		feature = newFeature;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEXED_FEATURE__FEATURE, oldFeature, newFeature);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeature(StructuralFeature newFeature) {
		if (newFeature != feature) {
			NotificationChain msgs = null;
			if (feature != null)
				msgs = ((InternalEObject)feature).eInverseRemove(this, CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE, StructuralFeature.class, msgs);
			if (newFeature != null)
				msgs = ((InternalEObject)newFeature).eInverseAdd(this, CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE, StructuralFeature.class, msgs);
			msgs = basicSetFeature(newFeature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEXED_FEATURE__FEATURE, newFeature, newFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index getIndex() {
		if (eContainerFeatureID() != KeysindexesPackage.INDEXED_FEATURE__INDEX) return null;
		return (Index)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndex(Index newIndex, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newIndex, KeysindexesPackage.INDEXED_FEATURE__INDEX, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndex(Index newIndex) {
		if (newIndex != eInternalContainer() || (eContainerFeatureID() != KeysindexesPackage.INDEXED_FEATURE__INDEX && newIndex != null)) {
			if (EcoreUtil.isAncestor(this, newIndex))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newIndex != null)
				msgs = ((InternalEObject)newIndex).eInverseAdd(this, KeysindexesPackage.INDEX__INDEXED_FEATURE, Index.class, msgs);
			msgs = basicSetIndex(newIndex, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEXED_FEATURE__INDEX, newIndex, newIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				if (feature != null)
					msgs = ((InternalEObject)feature).eInverseRemove(this, CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE, StructuralFeature.class, msgs);
				return basicSetFeature((StructuralFeature)otherEnd, msgs);
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetIndex((Index)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				return basicSetFeature(null, msgs);
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				return basicSetIndex(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				return eInternalContainer().eInverseRemove(this, KeysindexesPackage.INDEX__INDEXED_FEATURE, Index.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KeysindexesPackage.INDEXED_FEATURE__IS_ASCENDING:
				return isIsAscending();
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				if (resolve) return getFeature();
				return basicGetFeature();
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				return getIndex();
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
			case KeysindexesPackage.INDEXED_FEATURE__IS_ASCENDING:
				setIsAscending((Boolean)newValue);
				return;
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				setFeature((StructuralFeature)newValue);
				return;
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				setIndex((Index)newValue);
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
			case KeysindexesPackage.INDEXED_FEATURE__IS_ASCENDING:
				setIsAscending(IS_ASCENDING_EDEFAULT);
				return;
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				setFeature((StructuralFeature)null);
				return;
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				setIndex((Index)null);
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
			case KeysindexesPackage.INDEXED_FEATURE__IS_ASCENDING:
				return isAscending != IS_ASCENDING_EDEFAULT;
			case KeysindexesPackage.INDEXED_FEATURE__FEATURE:
				return feature != null;
			case KeysindexesPackage.INDEXED_FEATURE__INDEX:
				return getIndex() != null;
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
		result.append(" (isAscending: ");
		result.append(isAscending);
		result.append(')');
		return result.toString();
	}

} //IndexedFeatureImpl
