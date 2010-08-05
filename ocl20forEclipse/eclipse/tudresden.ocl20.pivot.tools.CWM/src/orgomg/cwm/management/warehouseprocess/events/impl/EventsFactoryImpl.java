/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.management.warehouseprocess.events.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EventsFactoryImpl extends EFactoryImpl implements EventsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventsFactory init() {
		try {
			EventsFactory theEventsFactory = (EventsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/management/warehouseprocess/events.ecore"); 
			if (theEventsFactory != null) {
				return theEventsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EventsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EventsPackage.POINT_IN_TIME_EVENT: return createPointInTimeEvent();
			case EventsPackage.CUSTOM_CALENDAR_EVENT: return createCustomCalendarEvent();
			case EventsPackage.CUSTOM_CALENDAR: return createCustomCalendar();
			case EventsPackage.CALENDAR_DATE: return createCalendarDate();
			case EventsPackage.INTERVAL_EVENT: return createIntervalEvent();
			case EventsPackage.EXTERNAL_EVENT: return createExternalEvent();
			case EventsPackage.INTERNAL_EVENT: return createInternalEvent();
			case EventsPackage.CASCADE_EVENT: return createCascadeEvent();
			case EventsPackage.RETRY_EVENT: return createRetryEvent();
			case EventsPackage.RECURRING_POINT_IN_TIME_EVENT: return createRecurringPointInTimeEvent();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PointInTimeEvent createPointInTimeEvent() {
		PointInTimeEventImpl pointInTimeEvent = new PointInTimeEventImpl();
		return pointInTimeEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCalendarEvent createCustomCalendarEvent() {
		CustomCalendarEventImpl customCalendarEvent = new CustomCalendarEventImpl();
		return customCalendarEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomCalendar createCustomCalendar() {
		CustomCalendarImpl customCalendar = new CustomCalendarImpl();
		return customCalendar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CalendarDate createCalendarDate() {
		CalendarDateImpl calendarDate = new CalendarDateImpl();
		return calendarDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalEvent createIntervalEvent() {
		IntervalEventImpl intervalEvent = new IntervalEventImpl();
		return intervalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalEvent createExternalEvent() {
		ExternalEventImpl externalEvent = new ExternalEventImpl();
		return externalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalEvent createInternalEvent() {
		InternalEventImpl internalEvent = new InternalEventImpl();
		return internalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CascadeEvent createCascadeEvent() {
		CascadeEventImpl cascadeEvent = new CascadeEventImpl();
		return cascadeEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RetryEvent createRetryEvent() {
		RetryEventImpl retryEvent = new RetryEventImpl();
		return retryEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecurringPointInTimeEvent createRecurringPointInTimeEvent() {
		RecurringPointInTimeEventImpl recurringPointInTimeEvent = new RecurringPointInTimeEventImpl();
		return recurringPointInTimeEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventsPackage getEventsPackage() {
		return (EventsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EventsPackage getPackage() {
		return EventsPackage.eINSTANCE;
	}

} //EventsFactoryImpl
