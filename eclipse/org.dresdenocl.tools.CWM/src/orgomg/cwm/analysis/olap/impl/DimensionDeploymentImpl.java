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

import orgomg.cwm.analysis.olap.DeploymentGroup;
import orgomg.cwm.analysis.olap.DimensionDeployment;
import orgomg.cwm.analysis.olap.HierarchyLevelAssociation;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.StructureMap;
import orgomg.cwm.analysis.olap.ValueBasedHierarchy;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dimension Deployment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getValueBasedHierarchy <em>Value Based Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getStructureMap <em>Structure Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getListOfValues <em>List Of Values</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getImmediateParent <em>Immediate Parent</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl#getDeploymentGroup <em>Deployment Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DimensionDeploymentImpl extends ClassImpl implements DimensionDeployment {
	/**
	 * The cached value of the '{@link #getStructureMap() <em>Structure Map</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructureMap()
	 * @generated
	 * @ordered
	 */
	protected EList<StructureMap> structureMap;

	/**
	 * The cached value of the '{@link #getListOfValues() <em>List Of Values</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListOfValues()
	 * @generated
	 * @ordered
	 */
	protected StructureMap listOfValues;

	/**
	 * The cached value of the '{@link #getImmediateParent() <em>Immediate Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImmediateParent()
	 * @generated
	 * @ordered
	 */
	protected StructureMap immediateParent;

	/**
	 * The cached value of the '{@link #getDeploymentGroup() <em>Deployment Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentGroup()
	 * @generated
	 * @ordered
	 */
	protected DeploymentGroup deploymentGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionDeploymentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.DIMENSION_DEPLOYMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HierarchyLevelAssociation getHierarchyLevelAssociation() {
		if (eContainerFeatureID() != OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION) return null;
		return (HierarchyLevelAssociation)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHierarchyLevelAssociation(HierarchyLevelAssociation newHierarchyLevelAssociation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newHierarchyLevelAssociation, OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHierarchyLevelAssociation(HierarchyLevelAssociation newHierarchyLevelAssociation) {
		if (newHierarchyLevelAssociation != eInternalContainer() || (eContainerFeatureID() != OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION && newHierarchyLevelAssociation != null)) {
			if (EcoreUtil.isAncestor(this, newHierarchyLevelAssociation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newHierarchyLevelAssociation != null)
				msgs = ((InternalEObject)newHierarchyLevelAssociation).eInverseAdd(this, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT, HierarchyLevelAssociation.class, msgs);
			msgs = basicSetHierarchyLevelAssociation(newHierarchyLevelAssociation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION, newHierarchyLevelAssociation, newHierarchyLevelAssociation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueBasedHierarchy getValueBasedHierarchy() {
		if (eContainerFeatureID() != OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY) return null;
		return (ValueBasedHierarchy)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValueBasedHierarchy(ValueBasedHierarchy newValueBasedHierarchy, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newValueBasedHierarchy, OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueBasedHierarchy(ValueBasedHierarchy newValueBasedHierarchy) {
		if (newValueBasedHierarchy != eInternalContainer() || (eContainerFeatureID() != OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY && newValueBasedHierarchy != null)) {
			if (EcoreUtil.isAncestor(this, newValueBasedHierarchy))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueBasedHierarchy != null)
				msgs = ((InternalEObject)newValueBasedHierarchy).eInverseAdd(this, OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT, ValueBasedHierarchy.class, msgs);
			msgs = basicSetValueBasedHierarchy(newValueBasedHierarchy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY, newValueBasedHierarchy, newValueBasedHierarchy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StructureMap> getStructureMap() {
		if (structureMap == null) {
			structureMap = new EObjectContainmentWithInverseEList<StructureMap>(StructureMap.class, this, OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT);
		}
		return structureMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureMap getListOfValues() {
		if (listOfValues != null && listOfValues.eIsProxy()) {
			InternalEObject oldListOfValues = (InternalEObject)listOfValues;
			listOfValues = (StructureMap)eResolveProxy(oldListOfValues);
			if (listOfValues != oldListOfValues) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, oldListOfValues, listOfValues));
			}
		}
		return listOfValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureMap basicGetListOfValues() {
		return listOfValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetListOfValues(StructureMap newListOfValues, NotificationChain msgs) {
		StructureMap oldListOfValues = listOfValues;
		listOfValues = newListOfValues;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, oldListOfValues, newListOfValues);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setListOfValues(StructureMap newListOfValues) {
		if (newListOfValues != listOfValues) {
			NotificationChain msgs = null;
			if (listOfValues != null)
				msgs = ((InternalEObject)listOfValues).eInverseRemove(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, StructureMap.class, msgs);
			if (newListOfValues != null)
				msgs = ((InternalEObject)newListOfValues).eInverseAdd(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, StructureMap.class, msgs);
			msgs = basicSetListOfValues(newListOfValues, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, newListOfValues, newListOfValues));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureMap getImmediateParent() {
		if (immediateParent != null && immediateParent.eIsProxy()) {
			InternalEObject oldImmediateParent = (InternalEObject)immediateParent;
			immediateParent = (StructureMap)eResolveProxy(oldImmediateParent);
			if (immediateParent != oldImmediateParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, oldImmediateParent, immediateParent));
			}
		}
		return immediateParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructureMap basicGetImmediateParent() {
		return immediateParent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImmediateParent(StructureMap newImmediateParent, NotificationChain msgs) {
		StructureMap oldImmediateParent = immediateParent;
		immediateParent = newImmediateParent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, oldImmediateParent, newImmediateParent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImmediateParent(StructureMap newImmediateParent) {
		if (newImmediateParent != immediateParent) {
			NotificationChain msgs = null;
			if (immediateParent != null)
				msgs = ((InternalEObject)immediateParent).eInverseRemove(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, StructureMap.class, msgs);
			if (newImmediateParent != null)
				msgs = ((InternalEObject)newImmediateParent).eInverseAdd(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, StructureMap.class, msgs);
			msgs = basicSetImmediateParent(newImmediateParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, newImmediateParent, newImmediateParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentGroup getDeploymentGroup() {
		if (deploymentGroup != null && deploymentGroup.eIsProxy()) {
			InternalEObject oldDeploymentGroup = (InternalEObject)deploymentGroup;
			deploymentGroup = (DeploymentGroup)eResolveProxy(oldDeploymentGroup);
			if (deploymentGroup != oldDeploymentGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP, oldDeploymentGroup, deploymentGroup));
			}
		}
		return deploymentGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentGroup basicGetDeploymentGroup() {
		return deploymentGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeploymentGroup(DeploymentGroup newDeploymentGroup, NotificationChain msgs) {
		DeploymentGroup oldDeploymentGroup = deploymentGroup;
		deploymentGroup = newDeploymentGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP, oldDeploymentGroup, newDeploymentGroup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeploymentGroup(DeploymentGroup newDeploymentGroup) {
		if (newDeploymentGroup != deploymentGroup) {
			NotificationChain msgs = null;
			if (deploymentGroup != null)
				msgs = ((InternalEObject)deploymentGroup).eInverseRemove(this, OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT, DeploymentGroup.class, msgs);
			if (newDeploymentGroup != null)
				msgs = ((InternalEObject)newDeploymentGroup).eInverseAdd(this, OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT, DeploymentGroup.class, msgs);
			msgs = basicSetDeploymentGroup(newDeploymentGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP, newDeploymentGroup, newDeploymentGroup));
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetHierarchyLevelAssociation((HierarchyLevelAssociation)otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetValueBasedHierarchy((ValueBasedHierarchy)otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStructureMap()).basicAdd(otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				if (listOfValues != null)
					msgs = ((InternalEObject)listOfValues).eInverseRemove(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, StructureMap.class, msgs);
				return basicSetListOfValues((StructureMap)otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				if (immediateParent != null)
					msgs = ((InternalEObject)immediateParent).eInverseRemove(this, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, StructureMap.class, msgs);
				return basicSetImmediateParent((StructureMap)otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				if (deploymentGroup != null)
					msgs = ((InternalEObject)deploymentGroup).eInverseRemove(this, OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT, DeploymentGroup.class, msgs);
				return basicSetDeploymentGroup((DeploymentGroup)otherEnd, msgs);
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				return basicSetHierarchyLevelAssociation(null, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				return basicSetValueBasedHierarchy(null, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				return ((InternalEList<?>)getStructureMap()).basicRemove(otherEnd, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				return basicSetListOfValues(null, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				return basicSetImmediateParent(null, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				return basicSetDeploymentGroup(null, msgs);
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				return eInternalContainer().eInverseRemove(this, OlapPackage.HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT, HierarchyLevelAssociation.class, msgs);
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				return eInternalContainer().eInverseRemove(this, OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT, ValueBasedHierarchy.class, msgs);
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				return getHierarchyLevelAssociation();
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				return getValueBasedHierarchy();
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				return getStructureMap();
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				if (resolve) return getListOfValues();
				return basicGetListOfValues();
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				if (resolve) return getImmediateParent();
				return basicGetImmediateParent();
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				if (resolve) return getDeploymentGroup();
				return basicGetDeploymentGroup();
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				setHierarchyLevelAssociation((HierarchyLevelAssociation)newValue);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				setValueBasedHierarchy((ValueBasedHierarchy)newValue);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				getStructureMap().clear();
				getStructureMap().addAll((Collection<? extends StructureMap>)newValue);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				setListOfValues((StructureMap)newValue);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				setImmediateParent((StructureMap)newValue);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				setDeploymentGroup((DeploymentGroup)newValue);
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				setHierarchyLevelAssociation((HierarchyLevelAssociation)null);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				setValueBasedHierarchy((ValueBasedHierarchy)null);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				getStructureMap().clear();
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				setListOfValues((StructureMap)null);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				setImmediateParent((StructureMap)null);
				return;
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				setDeploymentGroup((DeploymentGroup)null);
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
			case OlapPackage.DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION:
				return getHierarchyLevelAssociation() != null;
			case OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY:
				return getValueBasedHierarchy() != null;
			case OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP:
				return structureMap != null && !structureMap.isEmpty();
			case OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES:
				return listOfValues != null;
			case OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT:
				return immediateParent != null;
			case OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP:
				return deploymentGroup != null;
		}
		return super.eIsSet(featureID);
	}

} //DimensionDeploymentImpl
