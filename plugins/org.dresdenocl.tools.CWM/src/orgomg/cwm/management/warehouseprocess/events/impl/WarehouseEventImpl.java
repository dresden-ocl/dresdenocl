/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.management.warehouseprocess.WarehouseProcess;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.management.warehouseprocess.events.EventsPackage;
import orgomg.cwm.management.warehouseprocess.events.WarehouseEvent;

import orgomg.cwm.objectmodel.behavioral.impl.EventImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Warehouse Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.WarehouseEventImpl#getWarehouseProcess <em>Warehouse Process</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class WarehouseEventImpl extends EventImpl implements WarehouseEvent {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WarehouseEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.WAREHOUSE_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseProcess getWarehouseProcess() {
		if (eContainerFeatureID() != EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS) return null;
		return (WarehouseProcess)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWarehouseProcess(WarehouseProcess newWarehouseProcess, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWarehouseProcess, EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWarehouseProcess(WarehouseProcess newWarehouseProcess) {
		if (newWarehouseProcess != eInternalContainer() || (eContainerFeatureID() != EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS && newWarehouseProcess != null)) {
			if (EcoreUtil.isAncestor(this, newWarehouseProcess))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWarehouseProcess != null)
				msgs = ((InternalEObject)newWarehouseProcess).eInverseAdd(this, WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT, WarehouseProcess.class, msgs);
			msgs = basicSetWarehouseProcess(newWarehouseProcess, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS, newWarehouseProcess, newWarehouseProcess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWarehouseProcess((WarehouseProcess)otherEnd, msgs);
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
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				return basicSetWarehouseProcess(null, msgs);
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
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				return eInternalContainer().eInverseRemove(this, WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT, WarehouseProcess.class, msgs);
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
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				return getWarehouseProcess();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				setWarehouseProcess((WarehouseProcess)newValue);
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
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				setWarehouseProcess((WarehouseProcess)null);
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
			case EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS:
				return getWarehouseProcess() != null;
		}
		return super.eIsSet(featureID);
	}

} //WarehouseEventImpl
