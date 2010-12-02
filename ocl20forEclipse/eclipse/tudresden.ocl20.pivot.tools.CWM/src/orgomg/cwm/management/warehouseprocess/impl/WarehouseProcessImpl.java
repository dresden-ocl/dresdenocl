/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.management.warehouseprocess.WarehouseProcess;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.management.warehouseprocess.events.EventsPackage;
import orgomg.cwm.management.warehouseprocess.events.InternalEvent;
import orgomg.cwm.management.warehouseprocess.events.WarehouseEvent;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Warehouse Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl#isStaticDefinition <em>Static Definition</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl#isIsSequential <em>Is Sequential</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl#getWarehouseEvent <em>Warehouse Event</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl#getInternalEvent <em>Internal Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class WarehouseProcessImpl extends ModelElementImpl implements WarehouseProcess {
	/**
	 * The default value of the '{@link #isStaticDefinition() <em>Static Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStaticDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_DEFINITION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStaticDefinition() <em>Static Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStaticDefinition()
	 * @generated
	 * @ordered
	 */
	protected boolean staticDefinition = STATIC_DEFINITION_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsSequential() <em>Is Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSequential()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SEQUENTIAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSequential() <em>Is Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSequential()
	 * @generated
	 * @ordered
	 */
	protected boolean isSequential = IS_SEQUENTIAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWarehouseEvent() <em>Warehouse Event</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarehouseEvent()
	 * @generated
	 * @ordered
	 */
	protected EList<WarehouseEvent> warehouseEvent;

	/**
	 * The cached value of the '{@link #getInternalEvent() <em>Internal Event</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalEvent()
	 * @generated
	 * @ordered
	 */
	protected EList<InternalEvent> internalEvent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WarehouseProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseprocessPackage.Literals.WAREHOUSE_PROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStaticDefinition() {
		return staticDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticDefinition(boolean newStaticDefinition) {
		boolean oldStaticDefinition = staticDefinition;
		staticDefinition = newStaticDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_PROCESS__STATIC_DEFINITION, oldStaticDefinition, staticDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSequential() {
		return isSequential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSequential(boolean newIsSequential) {
		boolean oldIsSequential = isSequential;
		isSequential = newIsSequential;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_PROCESS__IS_SEQUENTIAL, oldIsSequential, isSequential));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WarehouseEvent> getWarehouseEvent() {
		if (warehouseEvent == null) {
			warehouseEvent = new EObjectContainmentWithInverseEList<WarehouseEvent>(WarehouseEvent.class, this, WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT, EventsPackage.WAREHOUSE_EVENT__WAREHOUSE_PROCESS);
		}
		return warehouseEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InternalEvent> getInternalEvent() {
		if (internalEvent == null) {
			internalEvent = new EObjectWithInverseResolvingEList.ManyInverse<InternalEvent>(InternalEvent.class, this, WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT, EventsPackage.INTERNAL_EVENT__TRIGGERING_WP);
		}
		return internalEvent;
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWarehouseEvent()).basicAdd(otherEnd, msgs);
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInternalEvent()).basicAdd(otherEnd, msgs);
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				return ((InternalEList<?>)getWarehouseEvent()).basicRemove(otherEnd, msgs);
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				return ((InternalEList<?>)getInternalEvent()).basicRemove(otherEnd, msgs);
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__STATIC_DEFINITION:
				return isStaticDefinition();
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__IS_SEQUENTIAL:
				return isIsSequential();
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				return getWarehouseEvent();
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				return getInternalEvent();
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__STATIC_DEFINITION:
				setStaticDefinition((Boolean)newValue);
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__IS_SEQUENTIAL:
				setIsSequential((Boolean)newValue);
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				getWarehouseEvent().clear();
				getWarehouseEvent().addAll((Collection<? extends WarehouseEvent>)newValue);
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				getInternalEvent().clear();
				getInternalEvent().addAll((Collection<? extends InternalEvent>)newValue);
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__STATIC_DEFINITION:
				setStaticDefinition(STATIC_DEFINITION_EDEFAULT);
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__IS_SEQUENTIAL:
				setIsSequential(IS_SEQUENTIAL_EDEFAULT);
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				getWarehouseEvent().clear();
				return;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				getInternalEvent().clear();
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
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__STATIC_DEFINITION:
				return staticDefinition != STATIC_DEFINITION_EDEFAULT;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__IS_SEQUENTIAL:
				return isSequential != IS_SEQUENTIAL_EDEFAULT;
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__WAREHOUSE_EVENT:
				return warehouseEvent != null && !warehouseEvent.isEmpty();
			case WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT:
				return internalEvent != null && !internalEvent.isEmpty();
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
		result.append(" (staticDefinition: ");
		result.append(staticDefinition);
		result.append(", isSequential: ");
		result.append(isSequential);
		result.append(')');
		return result.toString();
	}

} //WarehouseProcessImpl
