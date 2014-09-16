/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage
 * @generated
 */
public interface EventsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EventsFactory eINSTANCE = orgomg.cwm.management.warehouseprocess.events.impl.EventsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Point In Time Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Point In Time Event</em>'.
	 * @generated
	 */
	PointInTimeEvent createPointInTimeEvent();

	/**
	 * Returns a new object of class '<em>Custom Calendar Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Calendar Event</em>'.
	 * @generated
	 */
	CustomCalendarEvent createCustomCalendarEvent();

	/**
	 * Returns a new object of class '<em>Custom Calendar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Calendar</em>'.
	 * @generated
	 */
	CustomCalendar createCustomCalendar();

	/**
	 * Returns a new object of class '<em>Calendar Date</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Calendar Date</em>'.
	 * @generated
	 */
	CalendarDate createCalendarDate();

	/**
	 * Returns a new object of class '<em>Interval Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interval Event</em>'.
	 * @generated
	 */
	IntervalEvent createIntervalEvent();

	/**
	 * Returns a new object of class '<em>External Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Event</em>'.
	 * @generated
	 */
	ExternalEvent createExternalEvent();

	/**
	 * Returns a new object of class '<em>Internal Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Internal Event</em>'.
	 * @generated
	 */
	InternalEvent createInternalEvent();

	/**
	 * Returns a new object of class '<em>Cascade Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cascade Event</em>'.
	 * @generated
	 */
	CascadeEvent createCascadeEvent();

	/**
	 * Returns a new object of class '<em>Retry Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Retry Event</em>'.
	 * @generated
	 */
	RetryEvent createRetryEvent();

	/**
	 * Returns a new object of class '<em>Recurring Point In Time Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Recurring Point In Time Event</em>'.
	 * @generated
	 */
	RecurringPointInTimeEvent createRecurringPointInTimeEvent();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EventsPackage getEventsPackage();

} //EventsFactory
