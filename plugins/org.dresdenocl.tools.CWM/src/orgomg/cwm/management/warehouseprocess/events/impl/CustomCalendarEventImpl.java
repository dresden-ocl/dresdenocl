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

import orgomg.cwm.management.warehouseprocess.events.CustomCalendar;
import orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent;
import orgomg.cwm.management.warehouseprocess.events.EventsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Calendar Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarEventImpl#getCustomCalendar <em>Custom Calendar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomCalendarEventImpl extends PointInTimeEventImpl implements CustomCalendarEvent {
	/**
	 * The cached value of the '{@link #getCustomCalendar() <em>Custom Calendar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomCalendar()
	 * @generated
	 * @ordered
	 */
	protected CustomCalendar customCalendar;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomCalendarEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.CUSTOM_CALENDAR_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCalendar getCustomCalendar() {
		if (customCalendar != null && customCalendar.eIsProxy()) {
			InternalEObject oldCustomCalendar = (InternalEObject)customCalendar;
			customCalendar = (CustomCalendar)eResolveProxy(oldCustomCalendar);
			if (customCalendar != oldCustomCalendar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR, oldCustomCalendar, customCalendar));
			}
		}
		return customCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCalendar basicGetCustomCalendar() {
		return customCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCustomCalendar(CustomCalendar newCustomCalendar, NotificationChain msgs) {
		CustomCalendar oldCustomCalendar = customCalendar;
		customCalendar = newCustomCalendar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR, oldCustomCalendar, newCustomCalendar);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustomCalendar(CustomCalendar newCustomCalendar) {
		if (newCustomCalendar != customCalendar) {
			NotificationChain msgs = null;
			if (customCalendar != null)
				msgs = ((InternalEObject)customCalendar).eInverseRemove(this, EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT, CustomCalendar.class, msgs);
			if (newCustomCalendar != null)
				msgs = ((InternalEObject)newCustomCalendar).eInverseAdd(this, EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT, CustomCalendar.class, msgs);
			msgs = basicSetCustomCalendar(newCustomCalendar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR, newCustomCalendar, newCustomCalendar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				if (customCalendar != null)
					msgs = ((InternalEObject)customCalendar).eInverseRemove(this, EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT, CustomCalendar.class, msgs);
				return basicSetCustomCalendar((CustomCalendar)otherEnd, msgs);
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
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				return basicSetCustomCalendar(null, msgs);
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
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				if (resolve) return getCustomCalendar();
				return basicGetCustomCalendar();
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
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				setCustomCalendar((CustomCalendar)newValue);
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
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				setCustomCalendar((CustomCalendar)null);
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
			case EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR:
				return customCalendar != null;
		}
		return super.eIsSet(featureID);
	}

} //CustomCalendarEventImpl
