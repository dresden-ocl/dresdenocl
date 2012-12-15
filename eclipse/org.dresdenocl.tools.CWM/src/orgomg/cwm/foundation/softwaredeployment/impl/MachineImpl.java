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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.DeployedComponent;
import orgomg.cwm.foundation.softwaredeployment.Machine;
import orgomg.cwm.foundation.softwaredeployment.Site;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.impl.NamespaceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Machine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl#getIpAddress <em>Ip Address</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl#getHostName <em>Host Name</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl#getMachineID <em>Machine ID</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl#getDeployedComponent <em>Deployed Component</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl#getSite <em>Site</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MachineImpl extends NamespaceImpl implements Machine {
	/**
	 * The default value of the '{@link #getIpAddress() <em>Ip Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIpAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String IP_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIpAddress() <em>Ip Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIpAddress()
	 * @generated
	 * @ordered
	 */
	protected String ipAddress = IP_ADDRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getHostName() <em>Host Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostName()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHostName() <em>Host Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostName()
	 * @generated
	 * @ordered
	 */
	protected String hostName = HOST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMachineID() <em>Machine ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMachineID()
	 * @generated
	 * @ordered
	 */
	protected static final String MACHINE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMachineID() <em>Machine ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMachineID()
	 * @generated
	 * @ordered
	 */
	protected String machineID = MACHINE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeployedComponent() <em>Deployed Component</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployedComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployedComponent> deployedComponent;

	/**
	 * The cached value of the '{@link #getSite() <em>Site</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSite()
	 * @generated
	 * @ordered
	 */
	protected Site site;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MachineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.MACHINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIpAddress(String newIpAddress) {
		String oldIpAddress = ipAddress;
		ipAddress = newIpAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.MACHINE__IP_ADDRESS, oldIpAddress, ipAddress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostName(String newHostName) {
		String oldHostName = hostName;
		hostName = newHostName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.MACHINE__HOST_NAME, oldHostName, hostName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMachineID() {
		return machineID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMachineID(String newMachineID) {
		String oldMachineID = machineID;
		machineID = newMachineID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.MACHINE__MACHINE_ID, oldMachineID, machineID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployedComponent> getDeployedComponent() {
		if (deployedComponent == null) {
			deployedComponent = new EObjectContainmentWithInverseEList<DeployedComponent>(DeployedComponent.class, this, SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE);
		}
		return deployedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site getSite() {
		if (site != null && site.eIsProxy()) {
			InternalEObject oldSite = (InternalEObject)site;
			site = (Site)eResolveProxy(oldSite);
			if (site != oldSite) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoftwaredeploymentPackage.MACHINE__SITE, oldSite, site));
			}
		}
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site basicGetSite() {
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSite(Site newSite, NotificationChain msgs) {
		Site oldSite = site;
		site = newSite;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.MACHINE__SITE, oldSite, newSite);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSite(Site newSite) {
		if (newSite != site) {
			NotificationChain msgs = null;
			if (site != null)
				msgs = ((InternalEObject)site).eInverseRemove(this, SoftwaredeploymentPackage.SITE__MACHINE, Site.class, msgs);
			if (newSite != null)
				msgs = ((InternalEObject)newSite).eInverseAdd(this, SoftwaredeploymentPackage.SITE__MACHINE, Site.class, msgs);
			msgs = basicSetSite(newSite, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.MACHINE__SITE, newSite, newSite));
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
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeployedComponent()).basicAdd(otherEnd, msgs);
			case SoftwaredeploymentPackage.MACHINE__SITE:
				if (site != null)
					msgs = ((InternalEObject)site).eInverseRemove(this, SoftwaredeploymentPackage.SITE__MACHINE, Site.class, msgs);
				return basicSetSite((Site)otherEnd, msgs);
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
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				return ((InternalEList<?>)getDeployedComponent()).basicRemove(otherEnd, msgs);
			case SoftwaredeploymentPackage.MACHINE__SITE:
				return basicSetSite(null, msgs);
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
			case SoftwaredeploymentPackage.MACHINE__IP_ADDRESS:
				return getIpAddress();
			case SoftwaredeploymentPackage.MACHINE__HOST_NAME:
				return getHostName();
			case SoftwaredeploymentPackage.MACHINE__MACHINE_ID:
				return getMachineID();
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				return getDeployedComponent();
			case SoftwaredeploymentPackage.MACHINE__SITE:
				if (resolve) return getSite();
				return basicGetSite();
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
			case SoftwaredeploymentPackage.MACHINE__IP_ADDRESS:
				setIpAddress((String)newValue);
				return;
			case SoftwaredeploymentPackage.MACHINE__HOST_NAME:
				setHostName((String)newValue);
				return;
			case SoftwaredeploymentPackage.MACHINE__MACHINE_ID:
				setMachineID((String)newValue);
				return;
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				getDeployedComponent().clear();
				getDeployedComponent().addAll((Collection<? extends DeployedComponent>)newValue);
				return;
			case SoftwaredeploymentPackage.MACHINE__SITE:
				setSite((Site)newValue);
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
			case SoftwaredeploymentPackage.MACHINE__IP_ADDRESS:
				setIpAddress(IP_ADDRESS_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.MACHINE__HOST_NAME:
				setHostName(HOST_NAME_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.MACHINE__MACHINE_ID:
				setMachineID(MACHINE_ID_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				getDeployedComponent().clear();
				return;
			case SoftwaredeploymentPackage.MACHINE__SITE:
				setSite((Site)null);
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
			case SoftwaredeploymentPackage.MACHINE__IP_ADDRESS:
				return IP_ADDRESS_EDEFAULT == null ? ipAddress != null : !IP_ADDRESS_EDEFAULT.equals(ipAddress);
			case SoftwaredeploymentPackage.MACHINE__HOST_NAME:
				return HOST_NAME_EDEFAULT == null ? hostName != null : !HOST_NAME_EDEFAULT.equals(hostName);
			case SoftwaredeploymentPackage.MACHINE__MACHINE_ID:
				return MACHINE_ID_EDEFAULT == null ? machineID != null : !MACHINE_ID_EDEFAULT.equals(machineID);
			case SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT:
				return deployedComponent != null && !deployedComponent.isEmpty();
			case SoftwaredeploymentPackage.MACHINE__SITE:
				return site != null;
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
		result.append(" (ipAddress: ");
		result.append(ipAddress);
		result.append(", hostName: ");
		result.append(hostName);
		result.append(", machineID: ");
		result.append(machineID);
		result.append(')');
		return result.toString();
	}

} //MachineImpl
