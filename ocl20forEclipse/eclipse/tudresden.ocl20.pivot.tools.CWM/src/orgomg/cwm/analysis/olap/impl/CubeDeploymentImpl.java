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

import orgomg.cwm.analysis.olap.ContentMap;
import orgomg.cwm.analysis.olap.CubeDeployment;
import orgomg.cwm.analysis.olap.CubeRegion;
import orgomg.cwm.analysis.olap.DeploymentGroup;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cube Deployment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl#getCubeRegion <em>Cube Region</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl#getDeploymentGroup <em>Deployment Group</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl#getContentMap <em>Content Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CubeDeploymentImpl extends ClassImpl implements CubeDeployment {
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
	 * The cached value of the '{@link #getContentMap() <em>Content Map</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContentMap()
	 * @generated
	 * @ordered
	 */
	protected EList<ContentMap> contentMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CubeDeploymentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.CUBE_DEPLOYMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeRegion getCubeRegion() {
		if (eContainerFeatureID() != OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION) return null;
		return (CubeRegion)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCubeRegion(CubeRegion newCubeRegion, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCubeRegion, OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCubeRegion(CubeRegion newCubeRegion) {
		if (newCubeRegion != eInternalContainer() || (eContainerFeatureID() != OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION && newCubeRegion != null)) {
			if (EcoreUtil.isAncestor(this, newCubeRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCubeRegion != null)
				msgs = ((InternalEObject)newCubeRegion).eInverseAdd(this, OlapPackage.CUBE_REGION__CUBE_DEPLOYMENT, CubeRegion.class, msgs);
			msgs = basicSetCubeRegion(newCubeRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION, newCubeRegion, newCubeRegion));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP, oldDeploymentGroup, deploymentGroup));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP, oldDeploymentGroup, newDeploymentGroup);
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
				msgs = ((InternalEObject)deploymentGroup).eInverseRemove(this, OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT, DeploymentGroup.class, msgs);
			if (newDeploymentGroup != null)
				msgs = ((InternalEObject)newDeploymentGroup).eInverseAdd(this, OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT, DeploymentGroup.class, msgs);
			msgs = basicSetDeploymentGroup(newDeploymentGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP, newDeploymentGroup, newDeploymentGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ContentMap> getContentMap() {
		if (contentMap == null) {
			contentMap = new EObjectContainmentWithInverseEList<ContentMap>(ContentMap.class, this, OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP, OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT);
		}
		return contentMap;
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCubeRegion((CubeRegion)otherEnd, msgs);
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				if (deploymentGroup != null)
					msgs = ((InternalEObject)deploymentGroup).eInverseRemove(this, OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT, DeploymentGroup.class, msgs);
				return basicSetDeploymentGroup((DeploymentGroup)otherEnd, msgs);
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContentMap()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				return basicSetCubeRegion(null, msgs);
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				return basicSetDeploymentGroup(null, msgs);
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				return ((InternalEList<?>)getContentMap()).basicRemove(otherEnd, msgs);
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				return eInternalContainer().eInverseRemove(this, OlapPackage.CUBE_REGION__CUBE_DEPLOYMENT, CubeRegion.class, msgs);
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				return getCubeRegion();
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				if (resolve) return getDeploymentGroup();
				return basicGetDeploymentGroup();
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				return getContentMap();
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				setCubeRegion((CubeRegion)newValue);
				return;
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				setDeploymentGroup((DeploymentGroup)newValue);
				return;
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				getContentMap().clear();
				getContentMap().addAll((Collection<? extends ContentMap>)newValue);
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				setCubeRegion((CubeRegion)null);
				return;
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				setDeploymentGroup((DeploymentGroup)null);
				return;
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				getContentMap().clear();
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
			case OlapPackage.CUBE_DEPLOYMENT__CUBE_REGION:
				return getCubeRegion() != null;
			case OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP:
				return deploymentGroup != null;
			case OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP:
				return contentMap != null && !contentMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CubeDeploymentImpl
