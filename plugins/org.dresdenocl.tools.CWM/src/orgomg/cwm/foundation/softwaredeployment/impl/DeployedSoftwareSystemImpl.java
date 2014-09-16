/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.DeployedComponent;
import orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem;
import orgomg.cwm.foundation.softwaredeployment.SoftwareSystem;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployed Software System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl#getSoftwareSystem <em>Software System</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl#getDeployedComponent <em>Deployed Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeployedSoftwareSystemImpl extends PackageImpl implements DeployedSoftwareSystem {
	/**
	 * The cached value of the '{@link #getSoftwareSystem() <em>Software System</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftwareSystem()
	 * @generated
	 * @ordered
	 */
	protected SoftwareSystem softwareSystem;

	/**
	 * The cached value of the '{@link #getDeployedComponent() <em>Deployed Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployedComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployedComponent> deployedComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployedSoftwareSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.DEPLOYED_SOFTWARE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareSystem getSoftwareSystem() {
		if (softwareSystem != null && softwareSystem.eIsProxy()) {
			InternalEObject oldSoftwareSystem = (InternalEObject)softwareSystem;
			softwareSystem = (SoftwareSystem)eResolveProxy(oldSoftwareSystem);
			if (softwareSystem != oldSoftwareSystem) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM, oldSoftwareSystem, softwareSystem));
			}
		}
		return softwareSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareSystem basicGetSoftwareSystem() {
		return softwareSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSoftwareSystem(SoftwareSystem newSoftwareSystem, NotificationChain msgs) {
		SoftwareSystem oldSoftwareSystem = softwareSystem;
		softwareSystem = newSoftwareSystem;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM, oldSoftwareSystem, newSoftwareSystem);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftwareSystem(SoftwareSystem newSoftwareSystem) {
		if (newSoftwareSystem != softwareSystem) {
			NotificationChain msgs = null;
			if (softwareSystem != null)
				msgs = ((InternalEObject)softwareSystem).eInverseRemove(this, SoftwaredeploymentPackage.SOFTWARE_SYSTEM__DEPLOYMENT, SoftwareSystem.class, msgs);
			if (newSoftwareSystem != null)
				msgs = ((InternalEObject)newSoftwareSystem).eInverseAdd(this, SoftwaredeploymentPackage.SOFTWARE_SYSTEM__DEPLOYMENT, SoftwareSystem.class, msgs);
			msgs = basicSetSoftwareSystem(newSoftwareSystem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM, newSoftwareSystem, newSoftwareSystem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployedComponent> getDeployedComponent() {
		if (deployedComponent == null) {
			deployedComponent = new EObjectWithInverseResolvingEList.ManyInverse<DeployedComponent>(DeployedComponent.class, this, SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM);
		}
		return deployedComponent;
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				if (softwareSystem != null)
					msgs = ((InternalEObject)softwareSystem).eInverseRemove(this, SoftwaredeploymentPackage.SOFTWARE_SYSTEM__DEPLOYMENT, SoftwareSystem.class, msgs);
				return basicSetSoftwareSystem((SoftwareSystem)otherEnd, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeployedComponent()).basicAdd(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				return basicSetSoftwareSystem(null, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				return ((InternalEList<?>)getDeployedComponent()).basicRemove(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				if (resolve) return getSoftwareSystem();
				return basicGetSoftwareSystem();
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				return getDeployedComponent();
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				setSoftwareSystem((SoftwareSystem)newValue);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				getDeployedComponent().clear();
				getDeployedComponent().addAll((Collection<? extends DeployedComponent>)newValue);
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				setSoftwareSystem((SoftwareSystem)null);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				getDeployedComponent().clear();
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
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM:
				return softwareSystem != null;
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT:
				return deployedComponent != null && !deployedComponent.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DeployedSoftwareSystemImpl
