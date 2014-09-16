/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.olap.DimensionDeployment;
import orgomg.cwm.analysis.olap.HierarchyLevelAssociation;
import orgomg.cwm.analysis.olap.Level;
import orgomg.cwm.analysis.olap.LevelBasedHierarchy;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hierarchy Level Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl#getLevelBasedHierarchy <em>Level Based Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl#getCurrentLevel <em>Current Level</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HierarchyLevelAssociationImpl extends ClassImpl implements HierarchyLevelAssociation {
	/**
	 * The cached value of the '{@link #getCurrentLevel() <em>Current Level</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentLevel()
	 * @generated
	 * @ordered
	 */
	protected Level currentLevel;

	/**
	 * The cached value of the '{@link #getDimensionDeployment() <em>Dimension Deployment</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionDeployment()
	 * @generated
	 * @ordered
	 */
	protected EList<DimensionDeployment> dimensionDeployment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HierarchyLevelAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.HIERARCHY_LEVEL_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LevelBasedHierarchy getLevelBasedHierarchy() {
		if (eContainerFeatureID() != OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY) return null;
		return (LevelBasedHierarchy)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLevelBasedHierarchy(LevelBasedHierarchy newLevelBasedHierarchy, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLevelBasedHierarchy, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevelBasedHierarchy(LevelBasedHierarchy newLevelBasedHierarchy) {
		if (newLevelBasedHierarchy != eInternalContainer() || (eContainerFeatureID() != OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY && newLevelBasedHierarchy != null)) {
			if (EcoreUtil.isAncestor(this, newLevelBasedHierarchy))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLevelBasedHierarchy != null)
				msgs = ((InternalEObject)newLevelBasedHierarchy).eInverseAdd(this, OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION, LevelBasedHierarchy.class, msgs);
			msgs = basicSetLevelBasedHierarchy(newLevelBasedHierarchy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY, newLevelBasedHierarchy, newLevelBasedHierarchy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Level getCurrentLevel() {
		if (currentLevel != null && currentLevel.eIsProxy()) {
			InternalEObject oldCurrentLevel = (InternalEObject)currentLevel;
			currentLevel = (Level)eResolveProxy(oldCurrentLevel);
			if (currentLevel != oldCurrentLevel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL, oldCurrentLevel, currentLevel));
			}
		}
		return currentLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Level basicGetCurrentLevel() {
		return currentLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCurrentLevel(Level newCurrentLevel, NotificationChain msgs) {
		Level oldCurrentLevel = currentLevel;
		currentLevel = newCurrentLevel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL, oldCurrentLevel, newCurrentLevel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentLevel(Level newCurrentLevel) {
		if (newCurrentLevel != currentLevel) {
			NotificationChain msgs = null;
			if (currentLevel != null)
				msgs = ((InternalEObject)currentLevel).eInverseRemove(this, OlapPackage.LEVEL__HIERARCHY_LEVEL_ASSOCIATION, Level.class, msgs);
			if (newCurrentLevel != null)
				msgs = ((InternalEObject)newCurrentLevel).eInverseAdd(this, OlapPackage.LEVEL__HIERARCHY_LEVEL_ASSOCIATION, Level.class, msgs);
			msgs = basicSetCurrentLevel(newCurrentLevel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL, newCurrentLevel, newCurrentLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DimensionDeployment> getDimensionDeployment() {
		if (dimensionDeployment == null) {
			dimensionDeployment = new EObjectContainmentWithInverseEList<DimensionDeployment>(DimensionDeployment.class, this, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT, OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION);
		}
		return dimensionDeployment;
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLevelBasedHierarchy((LevelBasedHierarchy)otherEnd, msgs);
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				if (currentLevel != null)
					msgs = ((InternalEObject)currentLevel).eInverseRemove(this, OlapPackage.LEVEL__HIERARCHY_LEVEL_ASSOCIATION, Level.class, msgs);
				return basicSetCurrentLevel((Level)otherEnd, msgs);
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDimensionDeployment()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				return basicSetLevelBasedHierarchy(null, msgs);
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				return basicSetCurrentLevel(null, msgs);
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				return ((InternalEList<?>)getDimensionDeployment()).basicRemove(otherEnd, msgs);
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				return eInternalContainer().eInverseRemove(this, OlapPackage.LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION, LevelBasedHierarchy.class, msgs);
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				return getLevelBasedHierarchy();
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				if (resolve) return getCurrentLevel();
				return basicGetCurrentLevel();
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				return getDimensionDeployment();
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				setLevelBasedHierarchy((LevelBasedHierarchy)newValue);
				return;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				setCurrentLevel((Level)newValue);
				return;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				getDimensionDeployment().clear();
				getDimensionDeployment().addAll((Collection<? extends DimensionDeployment>)newValue);
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				setLevelBasedHierarchy((LevelBasedHierarchy)null);
				return;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				setCurrentLevel((Level)null);
				return;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				getDimensionDeployment().clear();
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
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY:
				return getLevelBasedHierarchy() != null;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL:
				return currentLevel != null;
			case OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT:
				return dimensionDeployment != null && !dimensionDeployment.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HierarchyLevelAssociationImpl
