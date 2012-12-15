/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.keysindexes.Index;
import orgomg.cwm.foundation.keysindexes.IndexedFeature;
import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;

import orgomg.cwm.objectmodel.core.CorePackage;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexImpl#isIsPartitioning <em>Is Partitioning</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexImpl#isIsSorted <em>Is Sorted</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexImpl#isIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexImpl#getIndexedFeature <em>Indexed Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.impl.IndexImpl#getSpannedClass <em>Spanned Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexImpl extends ModelElementImpl implements Index {
	/**
	 * The default value of the '{@link #isIsPartitioning() <em>Is Partitioning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPartitioning()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_PARTITIONING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsPartitioning() <em>Is Partitioning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsPartitioning()
	 * @generated
	 * @ordered
	 */
	protected boolean isPartitioning = IS_PARTITIONING_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsSorted() <em>Is Sorted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSorted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSorted() <em>Is Sorted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSorted()
	 * @generated
	 * @ordered
	 */
	protected boolean isSorted = IS_SORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsUnique() <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean isUnique = IS_UNIQUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIndexedFeature() <em>Indexed Feature</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexedFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<IndexedFeature> indexedFeature;

	/**
	 * The cached value of the '{@link #getSpannedClass() <em>Spanned Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpannedClass()
	 * @generated
	 * @ordered
	 */
	protected orgomg.cwm.objectmodel.core.Class spannedClass;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KeysindexesPackage.Literals.INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsPartitioning() {
		return isPartitioning;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsPartitioning(boolean newIsPartitioning) {
		boolean oldIsPartitioning = isPartitioning;
		isPartitioning = newIsPartitioning;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEX__IS_PARTITIONING, oldIsPartitioning, isPartitioning));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSorted() {
		return isSorted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSorted(boolean newIsSorted) {
		boolean oldIsSorted = isSorted;
		isSorted = newIsSorted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEX__IS_SORTED, oldIsSorted, isSorted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsUnique() {
		return isUnique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsUnique(boolean newIsUnique) {
		boolean oldIsUnique = isUnique;
		isUnique = newIsUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEX__IS_UNIQUE, oldIsUnique, isUnique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IndexedFeature> getIndexedFeature() {
		if (indexedFeature == null) {
			indexedFeature = new EObjectContainmentWithInverseEList<IndexedFeature>(IndexedFeature.class, this, KeysindexesPackage.INDEX__INDEXED_FEATURE, KeysindexesPackage.INDEXED_FEATURE__INDEX);
		}
		return indexedFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public orgomg.cwm.objectmodel.core.Class getSpannedClass() {
		if (spannedClass != null && spannedClass.eIsProxy()) {
			InternalEObject oldSpannedClass = (InternalEObject)spannedClass;
			spannedClass = (orgomg.cwm.objectmodel.core.Class)eResolveProxy(oldSpannedClass);
			if (spannedClass != oldSpannedClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KeysindexesPackage.INDEX__SPANNED_CLASS, oldSpannedClass, spannedClass));
			}
		}
		return spannedClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public orgomg.cwm.objectmodel.core.Class basicGetSpannedClass() {
		return spannedClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpannedClass(orgomg.cwm.objectmodel.core.Class newSpannedClass, NotificationChain msgs) {
		orgomg.cwm.objectmodel.core.Class oldSpannedClass = spannedClass;
		spannedClass = newSpannedClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEX__SPANNED_CLASS, oldSpannedClass, newSpannedClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpannedClass(orgomg.cwm.objectmodel.core.Class newSpannedClass) {
		if (newSpannedClass != spannedClass) {
			NotificationChain msgs = null;
			if (spannedClass != null)
				msgs = ((InternalEObject)spannedClass).eInverseRemove(this, CorePackage.CLASS__INDEX, orgomg.cwm.objectmodel.core.Class.class, msgs);
			if (newSpannedClass != null)
				msgs = ((InternalEObject)newSpannedClass).eInverseAdd(this, CorePackage.CLASS__INDEX, orgomg.cwm.objectmodel.core.Class.class, msgs);
			msgs = basicSetSpannedClass(newSpannedClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KeysindexesPackage.INDEX__SPANNED_CLASS, newSpannedClass, newSpannedClass));
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
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIndexedFeature()).basicAdd(otherEnd, msgs);
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				if (spannedClass != null)
					msgs = ((InternalEObject)spannedClass).eInverseRemove(this, CorePackage.CLASS__INDEX, orgomg.cwm.objectmodel.core.Class.class, msgs);
				return basicSetSpannedClass((orgomg.cwm.objectmodel.core.Class)otherEnd, msgs);
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
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				return ((InternalEList<?>)getIndexedFeature()).basicRemove(otherEnd, msgs);
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				return basicSetSpannedClass(null, msgs);
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
			case KeysindexesPackage.INDEX__IS_PARTITIONING:
				return isIsPartitioning();
			case KeysindexesPackage.INDEX__IS_SORTED:
				return isIsSorted();
			case KeysindexesPackage.INDEX__IS_UNIQUE:
				return isIsUnique();
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				return getIndexedFeature();
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				if (resolve) return getSpannedClass();
				return basicGetSpannedClass();
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
			case KeysindexesPackage.INDEX__IS_PARTITIONING:
				setIsPartitioning((Boolean)newValue);
				return;
			case KeysindexesPackage.INDEX__IS_SORTED:
				setIsSorted((Boolean)newValue);
				return;
			case KeysindexesPackage.INDEX__IS_UNIQUE:
				setIsUnique((Boolean)newValue);
				return;
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				getIndexedFeature().clear();
				getIndexedFeature().addAll((Collection<? extends IndexedFeature>)newValue);
				return;
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				setSpannedClass((orgomg.cwm.objectmodel.core.Class)newValue);
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
			case KeysindexesPackage.INDEX__IS_PARTITIONING:
				setIsPartitioning(IS_PARTITIONING_EDEFAULT);
				return;
			case KeysindexesPackage.INDEX__IS_SORTED:
				setIsSorted(IS_SORTED_EDEFAULT);
				return;
			case KeysindexesPackage.INDEX__IS_UNIQUE:
				setIsUnique(IS_UNIQUE_EDEFAULT);
				return;
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				getIndexedFeature().clear();
				return;
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				setSpannedClass((orgomg.cwm.objectmodel.core.Class)null);
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
			case KeysindexesPackage.INDEX__IS_PARTITIONING:
				return isPartitioning != IS_PARTITIONING_EDEFAULT;
			case KeysindexesPackage.INDEX__IS_SORTED:
				return isSorted != IS_SORTED_EDEFAULT;
			case KeysindexesPackage.INDEX__IS_UNIQUE:
				return isUnique != IS_UNIQUE_EDEFAULT;
			case KeysindexesPackage.INDEX__INDEXED_FEATURE:
				return indexedFeature != null && !indexedFeature.isEmpty();
			case KeysindexesPackage.INDEX__SPANNED_CLASS:
				return spannedClass != null;
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
		result.append(" (isPartitioning: ");
		result.append(isPartitioning);
		result.append(", isSorted: ");
		result.append(isSorted);
		result.append(", isUnique: ");
		result.append(isUnique);
		result.append(')');
		return result.toString();
	}

} //IndexImpl
