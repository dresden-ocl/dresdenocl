/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.management.warehouseprocess.events.CustomCalendar;
import orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent;
import orgomg.cwm.management.warehouseprocess.events.EventsPackage;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Custom Calendar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarImpl#getCustomCalendarEvent <em>Custom Calendar Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CustomCalendarImpl extends PackageImpl implements CustomCalendar {
	/**
	 * The cached value of the '{@link #getCustomCalendarEvent() <em>Custom Calendar Event</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomCalendarEvent()
	 * @generated
	 * @ordered
	 */
	protected EList<CustomCalendarEvent> customCalendarEvent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomCalendarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.CUSTOM_CALENDAR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CustomCalendarEvent> getCustomCalendarEvent() {
		if (customCalendarEvent == null) {
			customCalendarEvent = new EObjectWithInverseResolvingEList<CustomCalendarEvent>(CustomCalendarEvent.class, this, EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT, EventsPackage.CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR);
		}
		return customCalendarEvent;
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCustomCalendarEvent()).basicAdd(otherEnd, msgs);
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				return ((InternalEList<?>)getCustomCalendarEvent()).basicRemove(otherEnd, msgs);
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				return getCustomCalendarEvent();
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				getCustomCalendarEvent().clear();
				getCustomCalendarEvent().addAll((Collection<? extends CustomCalendarEvent>)newValue);
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				getCustomCalendarEvent().clear();
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
			case EventsPackage.CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT:
				return customCalendarEvent != null && !customCalendarEvent.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CustomCalendarImpl
