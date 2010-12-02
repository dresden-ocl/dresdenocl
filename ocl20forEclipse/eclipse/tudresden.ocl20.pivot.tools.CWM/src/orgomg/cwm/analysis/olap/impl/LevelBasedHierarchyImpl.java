/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.olap.HierarchyLevelAssociation;
import orgomg.cwm.analysis.olap.LevelBasedHierarchy;
import orgomg.cwm.analysis.olap.OlapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Level Based Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.LevelBasedHierarchyImpl#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LevelBasedHierarchyImpl extends HierarchyImpl implements LevelBasedHierarchy {
	/**
	 * The cached value of the '{@link #getHierarchyLevelAssociation() <em>Hierarchy Level Association</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHierarchyLevelAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<HierarchyLevelAssociation> hierarchyLevelAssociation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LevelBasedHierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.LEVEL_BASED_HIERARCHY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HierarchyLevelAssociation> getHierarchyLevelAssociation() {
		if (hierarchyLevelAssociation == null) {
			hierarchyLevelAssociation = new EObjectContainmentWithInverseEList<HierarchyLevelAssociation>(HierarchyLevelAssociation.class, this, OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY);
		}
		return hierarchyLevelAssociation;
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getHierarchyLevelAssociation()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				return ((InternalEList<?>)getHierarchyLevelAssociation()).basicRemove(otherEnd, msgs);
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				return getHierarchyLevelAssociation();
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				getHierarchyLevelAssociation().clear();
				getHierarchyLevelAssociation().addAll((Collection<? extends HierarchyLevelAssociation>)newValue);
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				getHierarchyLevelAssociation().clear();
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
			case OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION:
				return hierarchyLevelAssociation != null && !hierarchyLevelAssociation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LevelBasedHierarchyImpl
