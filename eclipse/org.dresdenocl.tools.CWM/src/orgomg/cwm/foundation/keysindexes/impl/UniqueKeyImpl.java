/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.keysindexes.KeyRelationship;
import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;
import orgomg.cwm.foundation.keysindexes.UniqueKey;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.StructuralFeature;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unique Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.UniqueKeyImpl#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.UniqueKeyImpl#getKeyRelationship <em>Key Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniqueKeyImpl extends ModelElementImpl implements UniqueKey {
	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<StructuralFeature> feature;

	/**
	 * The cached value of the '{@link #getKeyRelationship() <em>Key Relationship</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeyRelationship()
	 * @generated
	 * @ordered
	 */
	protected EList<KeyRelationship> keyRelationship;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UniqueKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KeysindexesPackage.Literals.UNIQUE_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructuralFeature> getFeature() {
		if (feature == null) {
			feature = new EObjectWithInverseResolvingEList.ManyInverse<StructuralFeature>(StructuralFeature.class, this, KeysindexesPackage.UNIQUE_KEY__FEATURE, CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY);
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KeyRelationship> getKeyRelationship() {
		if (keyRelationship == null) {
			keyRelationship = new EObjectWithInverseResolvingEList<KeyRelationship>(KeyRelationship.class, this, KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP, KeysindexesPackage.KEY_RELATIONSHIP__UNIQUE_KEY);
		}
		return keyRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeature()).basicAdd(otherEnd, msgs);
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getKeyRelationship()).basicAdd(otherEnd, msgs);
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
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				return ((InternalEList<?>)getFeature()).basicRemove(otherEnd, msgs);
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				return ((InternalEList<?>)getKeyRelationship()).basicRemove(otherEnd, msgs);
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
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				return getFeature();
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				return getKeyRelationship();
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
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				getFeature().clear();
				getFeature().addAll((Collection<? extends StructuralFeature>)newValue);
				return;
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				getKeyRelationship().clear();
				getKeyRelationship().addAll((Collection<? extends KeyRelationship>)newValue);
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
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				getFeature().clear();
				return;
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				getKeyRelationship().clear();
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
			case KeysindexesPackage.UNIQUE_KEY__FEATURE:
				return feature != null && !feature.isEmpty();
			case KeysindexesPackage.UNIQUE_KEY__KEY_RELATIONSHIP:
				return keyRelationship != null && !keyRelationship.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UniqueKeyImpl
