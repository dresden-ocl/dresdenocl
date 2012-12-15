/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import orgomg.cwm.management.warehouseprocess.events.*;

import orgomg.cwm.objectmodel.behavioral.Event;

import orgomg.cwm.objectmodel.core.Element;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage
 * @generated
 */
public class EventsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EventsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventsSwitch() {
		if (modelPackage == null) {
			modelPackage = EventsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case EventsPackage.WAREHOUSE_EVENT: {
				WarehouseEvent warehouseEvent = (WarehouseEvent)theEObject;
				T result = caseWarehouseEvent(warehouseEvent);
				if (result == null) result = caseEvent(warehouseEvent);
				if (result == null) result = caseModelElement(warehouseEvent);
				if (result == null) result = caseElement(warehouseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.SCHEDULE_EVENT: {
				ScheduleEvent scheduleEvent = (ScheduleEvent)theEObject;
				T result = caseScheduleEvent(scheduleEvent);
				if (result == null) result = caseWarehouseEvent(scheduleEvent);
				if (result == null) result = caseEvent(scheduleEvent);
				if (result == null) result = caseModelElement(scheduleEvent);
				if (result == null) result = caseElement(scheduleEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.POINT_IN_TIME_EVENT: {
				PointInTimeEvent pointInTimeEvent = (PointInTimeEvent)theEObject;
				T result = casePointInTimeEvent(pointInTimeEvent);
				if (result == null) result = caseScheduleEvent(pointInTimeEvent);
				if (result == null) result = caseWarehouseEvent(pointInTimeEvent);
				if (result == null) result = caseEvent(pointInTimeEvent);
				if (result == null) result = caseModelElement(pointInTimeEvent);
				if (result == null) result = caseElement(pointInTimeEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.CUSTOM_CALENDAR_EVENT: {
				CustomCalendarEvent customCalendarEvent = (CustomCalendarEvent)theEObject;
				T result = caseCustomCalendarEvent(customCalendarEvent);
				if (result == null) result = casePointInTimeEvent(customCalendarEvent);
				if (result == null) result = caseScheduleEvent(customCalendarEvent);
				if (result == null) result = caseWarehouseEvent(customCalendarEvent);
				if (result == null) result = caseEvent(customCalendarEvent);
				if (result == null) result = caseModelElement(customCalendarEvent);
				if (result == null) result = caseElement(customCalendarEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.CUSTOM_CALENDAR: {
				CustomCalendar customCalendar = (CustomCalendar)theEObject;
				T result = caseCustomCalendar(customCalendar);
				if (result == null) result = casePackage(customCalendar);
				if (result == null) result = caseNamespace(customCalendar);
				if (result == null) result = caseModelElement(customCalendar);
				if (result == null) result = caseElement(customCalendar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.CALENDAR_DATE: {
				CalendarDate calendarDate = (CalendarDate)theEObject;
				T result = caseCalendarDate(calendarDate);
				if (result == null) result = caseModelElement(calendarDate);
				if (result == null) result = caseElement(calendarDate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.INTERVAL_EVENT: {
				IntervalEvent intervalEvent = (IntervalEvent)theEObject;
				T result = caseIntervalEvent(intervalEvent);
				if (result == null) result = caseScheduleEvent(intervalEvent);
				if (result == null) result = caseWarehouseEvent(intervalEvent);
				if (result == null) result = caseEvent(intervalEvent);
				if (result == null) result = caseModelElement(intervalEvent);
				if (result == null) result = caseElement(intervalEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.EXTERNAL_EVENT: {
				ExternalEvent externalEvent = (ExternalEvent)theEObject;
				T result = caseExternalEvent(externalEvent);
				if (result == null) result = caseWarehouseEvent(externalEvent);
				if (result == null) result = caseEvent(externalEvent);
				if (result == null) result = caseModelElement(externalEvent);
				if (result == null) result = caseElement(externalEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.INTERNAL_EVENT: {
				InternalEvent internalEvent = (InternalEvent)theEObject;
				T result = caseInternalEvent(internalEvent);
				if (result == null) result = caseWarehouseEvent(internalEvent);
				if (result == null) result = caseEvent(internalEvent);
				if (result == null) result = caseModelElement(internalEvent);
				if (result == null) result = caseElement(internalEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.CASCADE_EVENT: {
				CascadeEvent cascadeEvent = (CascadeEvent)theEObject;
				T result = caseCascadeEvent(cascadeEvent);
				if (result == null) result = caseInternalEvent(cascadeEvent);
				if (result == null) result = caseWarehouseEvent(cascadeEvent);
				if (result == null) result = caseEvent(cascadeEvent);
				if (result == null) result = caseModelElement(cascadeEvent);
				if (result == null) result = caseElement(cascadeEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.RETRY_EVENT: {
				RetryEvent retryEvent = (RetryEvent)theEObject;
				T result = caseRetryEvent(retryEvent);
				if (result == null) result = caseInternalEvent(retryEvent);
				if (result == null) result = caseWarehouseEvent(retryEvent);
				if (result == null) result = caseEvent(retryEvent);
				if (result == null) result = caseModelElement(retryEvent);
				if (result == null) result = caseElement(retryEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EventsPackage.RECURRING_POINT_IN_TIME_EVENT: {
				RecurringPointInTimeEvent recurringPointInTimeEvent = (RecurringPointInTimeEvent)theEObject;
				T result = caseRecurringPointInTimeEvent(recurringPointInTimeEvent);
				if (result == null) result = casePointInTimeEvent(recurringPointInTimeEvent);
				if (result == null) result = caseScheduleEvent(recurringPointInTimeEvent);
				if (result == null) result = caseWarehouseEvent(recurringPointInTimeEvent);
				if (result == null) result = caseEvent(recurringPointInTimeEvent);
				if (result == null) result = caseModelElement(recurringPointInTimeEvent);
				if (result == null) result = caseElement(recurringPointInTimeEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Warehouse Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Warehouse Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWarehouseEvent(WarehouseEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Schedule Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schedule Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScheduleEvent(ScheduleEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Point In Time Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Point In Time Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePointInTimeEvent(PointInTimeEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Calendar Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Calendar Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomCalendarEvent(CustomCalendarEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Custom Calendar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Custom Calendar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCustomCalendar(CustomCalendar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Calendar Date</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Calendar Date</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCalendarDate(CalendarDate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interval Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interval Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntervalEvent(IntervalEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalEvent(ExternalEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Internal Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Internal Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInternalEvent(InternalEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cascade Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cascade Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCascadeEvent(CascadeEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Retry Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Retry Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRetryEvent(RetryEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Recurring Point In Time Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Recurring Point In Time Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRecurringPointInTimeEvent(RecurringPointInTimeEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvent(Event object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(orgomg.cwm.objectmodel.core.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //EventsSwitch
