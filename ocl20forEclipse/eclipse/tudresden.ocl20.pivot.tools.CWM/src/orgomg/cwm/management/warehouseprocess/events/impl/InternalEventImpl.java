/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.management.warehouseprocess.WarehouseProcess;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.management.warehouseprocess.events.EventsPackage;
import orgomg.cwm.management.warehouseprocess.events.InternalEvent;

import orgomg.cwm.objectmodel.core.BooleanExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Internal Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl#getTriggeringWP <em>Triggering WP</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InternalEventImpl extends WarehouseEventImpl implements InternalEvent {
	/**
	 * The cached value of the '{@link #getTriggeringWP() <em>Triggering WP</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggeringWP()
	 * @generated
	 * @ordered
	 */
	protected EList<WarehouseProcess> triggeringWP;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected BooleanExpression condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InternalEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.INTERNAL_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WarehouseProcess> getTriggeringWP() {
		if (triggeringWP == null) {
			triggeringWP = new EObjectWithInverseResolvingEList.ManyInverse<WarehouseProcess>(WarehouseProcess.class, this, EventsPackage.INTERNAL_EVENT__TRIGGERING_WP, WarehouseprocessPackage.WAREHOUSE_PROCESS__INTERNAL_EVENT);
		}
		return triggeringWP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanExpression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(BooleanExpression newCondition, NotificationChain msgs) {
		BooleanExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EventsPackage.INTERNAL_EVENT__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(BooleanExpression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EventsPackage.INTERNAL_EVENT__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EventsPackage.INTERNAL_EVENT__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EventsPackage.INTERNAL_EVENT__CONDITION, newCondition, newCondition));
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggeringWP()).basicAdd(otherEnd, msgs);
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				return ((InternalEList<?>)getTriggeringWP()).basicRemove(otherEnd, msgs);
			case EventsPackage.INTERNAL_EVENT__CONDITION:
				return basicSetCondition(null, msgs);
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				return getTriggeringWP();
			case EventsPackage.INTERNAL_EVENT__CONDITION:
				return getCondition();
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				getTriggeringWP().clear();
				getTriggeringWP().addAll((Collection<? extends WarehouseProcess>)newValue);
				return;
			case EventsPackage.INTERNAL_EVENT__CONDITION:
				setCondition((BooleanExpression)newValue);
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				getTriggeringWP().clear();
				return;
			case EventsPackage.INTERNAL_EVENT__CONDITION:
				setCondition((BooleanExpression)null);
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
			case EventsPackage.INTERNAL_EVENT__TRIGGERING_WP:
				return triggeringWP != null && !triggeringWP.isEmpty();
			case EventsPackage.INTERNAL_EVENT__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

} //InternalEventImpl
