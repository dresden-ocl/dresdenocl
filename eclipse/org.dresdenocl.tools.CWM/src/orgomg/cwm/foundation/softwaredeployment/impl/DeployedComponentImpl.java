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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.Component;
import orgomg.cwm.foundation.softwaredeployment.DeployedComponent;
import orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem;
import orgomg.cwm.foundation.softwaredeployment.Machine;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployed Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl#getPathname <em>Pathname</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl#getMachine <em>Machine</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl#getDeployedSoftwareSystem <em>Deployed Software System</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeployedComponentImpl extends PackageImpl implements DeployedComponent {
	/**
	 * The default value of the '{@link #getPathname() <em>Pathname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathname()
	 * @generated
	 * @ordered
	 */
	protected static final String PATHNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPathname() <em>Pathname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathname()
	 * @generated
	 * @ordered
	 */
	protected String pathname = PATHNAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeployedSoftwareSystem() <em>Deployed Software System</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployedSoftwareSystem()
	 * @generated
	 * @ordered
	 */
	protected EList<DeployedSoftwareSystem> deployedSoftwareSystem;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected Component component;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeployedComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.DEPLOYED_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPathname() {
		return pathname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathname(String newPathname) {
		String oldPathname = pathname;
		pathname = newPathname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__PATHNAME, oldPathname, pathname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine getMachine() {
		if (eContainerFeatureID() != SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE) return null;
		return (Machine)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMachine(Machine newMachine, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMachine, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMachine(Machine newMachine) {
		if (newMachine != eInternalContainer() || (eContainerFeatureID() != SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE && newMachine != null)) {
			if (EcoreUtil.isAncestor(this, newMachine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMachine != null)
				msgs = ((InternalEObject)newMachine).eInverseAdd(this, SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT, Machine.class, msgs);
			msgs = basicSetMachine(newMachine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE, newMachine, newMachine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeployedSoftwareSystem> getDeployedSoftwareSystem() {
		if (deployedSoftwareSystem == null) {
			deployedSoftwareSystem = new EObjectWithInverseResolvingEList.ManyInverse<DeployedSoftwareSystem>(DeployedSoftwareSystem.class, this, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM, SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT);
		}
		return deployedSoftwareSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getComponent() {
		if (component != null && component.eIsProxy()) {
			InternalEObject oldComponent = (InternalEObject)component;
			component = (Component)eResolveProxy(oldComponent);
			if (component != oldComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT, oldComponent, component));
			}
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponent(Component newComponent, NotificationChain msgs) {
		Component oldComponent = component;
		component = newComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT, oldComponent, newComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(Component newComponent) {
		if (newComponent != component) {
			NotificationChain msgs = null;
			if (component != null)
				msgs = ((InternalEObject)component).eInverseRemove(this, SoftwaredeploymentPackage.COMPONENT__DEPLOYMENT, Component.class, msgs);
			if (newComponent != null)
				msgs = ((InternalEObject)newComponent).eInverseAdd(this, SoftwaredeploymentPackage.COMPONENT__DEPLOYMENT, Component.class, msgs);
			msgs = basicSetComponent(newComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT, newComponent, newComponent));
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMachine((Machine)otherEnd, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeployedSoftwareSystem()).basicAdd(otherEnd, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				if (component != null)
					msgs = ((InternalEObject)component).eInverseRemove(this, SoftwaredeploymentPackage.COMPONENT__DEPLOYMENT, Component.class, msgs);
				return basicSetComponent((Component)otherEnd, msgs);
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				return basicSetMachine(null, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				return ((InternalEList<?>)getDeployedSoftwareSystem()).basicRemove(otherEnd, msgs);
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				return basicSetComponent(null, msgs);
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				return eInternalContainer().eInverseRemove(this, SoftwaredeploymentPackage.MACHINE__DEPLOYED_COMPONENT, Machine.class, msgs);
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__PATHNAME:
				return getPathname();
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				return getMachine();
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				return getDeployedSoftwareSystem();
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__PATHNAME:
				setPathname((String)newValue);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				setMachine((Machine)newValue);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				getDeployedSoftwareSystem().clear();
				getDeployedSoftwareSystem().addAll((Collection<? extends DeployedSoftwareSystem>)newValue);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				setComponent((Component)newValue);
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__PATHNAME:
				setPathname(PATHNAME_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				setMachine((Machine)null);
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				getDeployedSoftwareSystem().clear();
				return;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				setComponent((Component)null);
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
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__PATHNAME:
				return PATHNAME_EDEFAULT == null ? pathname != null : !PATHNAME_EDEFAULT.equals(pathname);
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__MACHINE:
				return getMachine() != null;
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM:
				return deployedSoftwareSystem != null && !deployedSoftwareSystem.isEmpty();
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT__COMPONENT:
				return component != null;
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
		result.append(" (pathname: ");
		result.append(pathname);
		result.append(')');
		return result.toString();
	}

} //DeployedComponentImpl
