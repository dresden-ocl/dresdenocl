/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.datatypes.Union;

import orgomg.cwm.foundation.keysindexes.IndexedFeature;
import orgomg.cwm.foundation.keysindexes.KeyRelationship;
import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;
import orgomg.cwm.foundation.keysindexes.UniqueKey;

import orgomg.cwm.objectmodel.core.ChangeableKind;
import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Multiplicity;
import orgomg.cwm.objectmodel.core.OrderingKind;
import orgomg.cwm.objectmodel.core.ScopeKind;
import orgomg.cwm.objectmodel.core.StructuralFeature;

import orgomg.cwm.objectmodel.instance.InstancePackage;
import orgomg.cwm.objectmodel.instance.Slot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getChangeability <em>Changeability</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getTargetScope <em>Target Scope</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getSlot <em>Slot</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getDiscriminatedUnion <em>Discriminated Union</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getIndexedFeature <em>Indexed Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getKeyRelationship <em>Key Relationship</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.StructuralFeatureImpl#getUniqueKey <em>Unique Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StructuralFeatureImpl extends FeatureImpl implements StructuralFeature {
	/**
	 * The default value of the '{@link #getChangeability() <em>Changeability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeability()
	 * @generated
	 * @ordered
	 */
	protected static final ChangeableKind CHANGEABILITY_EDEFAULT = ChangeableKind.CK_CHANGEABLE;

	/**
	 * The cached value of the '{@link #getChangeability() <em>Changeability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeability()
	 * @generated
	 * @ordered
	 */
	protected ChangeableKind changeability = CHANGEABILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected Multiplicity multiplicity;

	/**
	 * The default value of the '{@link #getOrdering() <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected static final OrderingKind ORDERING_EDEFAULT = OrderingKind.OK_UNORDERED;

	/**
	 * The cached value of the '{@link #getOrdering() <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected OrderingKind ordering = ORDERING_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetScope()
	 * @generated
	 * @ordered
	 */
	protected static final ScopeKind TARGET_SCOPE_EDEFAULT = ScopeKind.SK_INSTANCE;

	/**
	 * The cached value of the '{@link #getTargetScope() <em>Target Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetScope()
	 * @generated
	 * @ordered
	 */
	protected ScopeKind targetScope = TARGET_SCOPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Classifier type;

	/**
	 * The cached value of the '{@link #getSlot() <em>Slot</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlot()
	 * @generated
	 * @ordered
	 */
	protected EList<Slot> slot;

	/**
	 * The cached value of the '{@link #getDiscriminatedUnion() <em>Discriminated Union</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscriminatedUnion()
	 * @generated
	 * @ordered
	 */
	protected EList<Union> discriminatedUnion;

	/**
	 * The cached value of the '{@link #getIndexedFeature() <em>Indexed Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<IndexedFeature> indexedFeature;

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
	 * The cached value of the '{@link #getUniqueKey() <em>Unique Key</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueKey()
	 * @generated
	 * @ordered
	 */
	protected EList<UniqueKey> uniqueKey;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.STRUCTURAL_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeableKind getChangeability() {
		return changeability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeability(ChangeableKind newChangeability) {
		ChangeableKind oldChangeability = changeability;
		changeability = newChangeability == null ? CHANGEABILITY_EDEFAULT : newChangeability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__CHANGEABILITY, oldChangeability, changeability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMultiplicity(Multiplicity newMultiplicity, NotificationChain msgs) {
		Multiplicity oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY, oldMultiplicity, newMultiplicity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiplicity(Multiplicity newMultiplicity) {
		if (newMultiplicity != multiplicity) {
			NotificationChain msgs = null;
			if (multiplicity != null)
				msgs = ((InternalEObject)multiplicity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY, null, msgs);
			if (newMultiplicity != null)
				msgs = ((InternalEObject)newMultiplicity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY, null, msgs);
			msgs = basicSetMultiplicity(newMultiplicity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY, newMultiplicity, newMultiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingKind getOrdering() {
		return ordering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdering(OrderingKind newOrdering) {
		OrderingKind oldOrdering = ordering;
		ordering = newOrdering == null ? ORDERING_EDEFAULT : newOrdering;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__ORDERING, oldOrdering, ordering));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeKind getTargetScope() {
		return targetScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetScope(ScopeKind newTargetScope) {
		ScopeKind oldTargetScope = targetScope;
		targetScope = newTargetScope == null ? TARGET_SCOPE_EDEFAULT : newTargetScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__TARGET_SCOPE, oldTargetScope, targetScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Classifier)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.STRUCTURAL_FEATURE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Classifier newType, NotificationChain msgs) {
		Classifier oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Classifier newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__STRUCTURAL_FEATURE, Classifier.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CorePackage.CLASSIFIER__STRUCTURAL_FEATURE, Classifier.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.STRUCTURAL_FEATURE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Slot> getSlot() {
		if (slot == null) {
			slot = new EObjectWithInverseResolvingEList<Slot>(Slot.class, this, CorePackage.STRUCTURAL_FEATURE__SLOT, InstancePackage.SLOT__FEATURE);
		}
		return slot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Union> getDiscriminatedUnion() {
		if (discriminatedUnion == null) {
			discriminatedUnion = new EObjectWithInverseResolvingEList<Union>(Union.class, this, CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION, DatatypesPackage.UNION__DISCRIMINATOR);
		}
		return discriminatedUnion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IndexedFeature> getIndexedFeature() {
		if (indexedFeature == null) {
			indexedFeature = new EObjectWithInverseResolvingEList<IndexedFeature>(IndexedFeature.class, this, CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE, KeysindexesPackage.INDEXED_FEATURE__FEATURE);
		}
		return indexedFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KeyRelationship> getKeyRelationship() {
		if (keyRelationship == null) {
			keyRelationship = new EObjectWithInverseResolvingEList.ManyInverse<KeyRelationship>(KeyRelationship.class, this, CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP, KeysindexesPackage.KEY_RELATIONSHIP__FEATURE);
		}
		return keyRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UniqueKey> getUniqueKey() {
		if (uniqueKey == null) {
			uniqueKey = new EObjectWithInverseResolvingEList.ManyInverse<UniqueKey>(UniqueKey.class, this, CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY, KeysindexesPackage.UNIQUE_KEY__FEATURE);
		}
		return uniqueKey;
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
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__STRUCTURAL_FEATURE, Classifier.class, msgs);
				return basicSetType((Classifier)otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSlot()).basicAdd(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDiscriminatedUnion()).basicAdd(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIndexedFeature()).basicAdd(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getKeyRelationship()).basicAdd(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUniqueKey()).basicAdd(otherEnd, msgs);
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
			case CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY:
				return basicSetMultiplicity(null, msgs);
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				return basicSetType(null, msgs);
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				return ((InternalEList<?>)getSlot()).basicRemove(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				return ((InternalEList<?>)getDiscriminatedUnion()).basicRemove(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				return ((InternalEList<?>)getIndexedFeature()).basicRemove(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				return ((InternalEList<?>)getKeyRelationship()).basicRemove(otherEnd, msgs);
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				return ((InternalEList<?>)getUniqueKey()).basicRemove(otherEnd, msgs);
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
			case CorePackage.STRUCTURAL_FEATURE__CHANGEABILITY:
				return getChangeability();
			case CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY:
				return getMultiplicity();
			case CorePackage.STRUCTURAL_FEATURE__ORDERING:
				return getOrdering();
			case CorePackage.STRUCTURAL_FEATURE__TARGET_SCOPE:
				return getTargetScope();
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				return getSlot();
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				return getDiscriminatedUnion();
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				return getIndexedFeature();
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				return getKeyRelationship();
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				return getUniqueKey();
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
			case CorePackage.STRUCTURAL_FEATURE__CHANGEABILITY:
				setChangeability((ChangeableKind)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY:
				setMultiplicity((Multiplicity)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__ORDERING:
				setOrdering((OrderingKind)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__TARGET_SCOPE:
				setTargetScope((ScopeKind)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				setType((Classifier)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				getSlot().clear();
				getSlot().addAll((Collection<? extends Slot>)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				getDiscriminatedUnion().clear();
				getDiscriminatedUnion().addAll((Collection<? extends Union>)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				getIndexedFeature().clear();
				getIndexedFeature().addAll((Collection<? extends IndexedFeature>)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				getKeyRelationship().clear();
				getKeyRelationship().addAll((Collection<? extends KeyRelationship>)newValue);
				return;
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				getUniqueKey().clear();
				getUniqueKey().addAll((Collection<? extends UniqueKey>)newValue);
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
			case CorePackage.STRUCTURAL_FEATURE__CHANGEABILITY:
				setChangeability(CHANGEABILITY_EDEFAULT);
				return;
			case CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY:
				setMultiplicity((Multiplicity)null);
				return;
			case CorePackage.STRUCTURAL_FEATURE__ORDERING:
				setOrdering(ORDERING_EDEFAULT);
				return;
			case CorePackage.STRUCTURAL_FEATURE__TARGET_SCOPE:
				setTargetScope(TARGET_SCOPE_EDEFAULT);
				return;
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				setType((Classifier)null);
				return;
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				getSlot().clear();
				return;
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				getDiscriminatedUnion().clear();
				return;
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				getIndexedFeature().clear();
				return;
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				getKeyRelationship().clear();
				return;
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				getUniqueKey().clear();
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
			case CorePackage.STRUCTURAL_FEATURE__CHANGEABILITY:
				return changeability != CHANGEABILITY_EDEFAULT;
			case CorePackage.STRUCTURAL_FEATURE__MULTIPLICITY:
				return multiplicity != null;
			case CorePackage.STRUCTURAL_FEATURE__ORDERING:
				return ordering != ORDERING_EDEFAULT;
			case CorePackage.STRUCTURAL_FEATURE__TARGET_SCOPE:
				return targetScope != TARGET_SCOPE_EDEFAULT;
			case CorePackage.STRUCTURAL_FEATURE__TYPE:
				return type != null;
			case CorePackage.STRUCTURAL_FEATURE__SLOT:
				return slot != null && !slot.isEmpty();
			case CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION:
				return discriminatedUnion != null && !discriminatedUnion.isEmpty();
			case CorePackage.STRUCTURAL_FEATURE__INDEXED_FEATURE:
				return indexedFeature != null && !indexedFeature.isEmpty();
			case CorePackage.STRUCTURAL_FEATURE__KEY_RELATIONSHIP:
				return keyRelationship != null && !keyRelationship.isEmpty();
			case CorePackage.STRUCTURAL_FEATURE__UNIQUE_KEY:
				return uniqueKey != null && !uniqueKey.isEmpty();
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
		result.append(" (changeability: ");
		result.append(changeability);
		result.append(", ordering: ");
		result.append(ordering);
		result.append(", targetScope: ");
		result.append(targetScope);
		result.append(')');
		return result.toString();
	}

} //StructuralFeatureImpl
