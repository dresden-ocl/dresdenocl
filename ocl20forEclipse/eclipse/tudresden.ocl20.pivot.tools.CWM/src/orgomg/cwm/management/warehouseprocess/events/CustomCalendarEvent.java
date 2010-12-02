/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Calendar Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This event is controlled by a list of dates and times. To make the list easily shareable between multiple WarehouseProcesses the calendar itself is in a separate class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar <em>Custom Calendar</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCustomCalendarEvent()
 * @model
 * @generated
 */
public interface CustomCalendarEvent extends PointInTimeEvent {
	/**
	 * Returns the value of the '<em><b>Custom Calendar</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendar#getCustomCalendarEvent <em>Custom Calendar Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates which custom calendar is used for this event.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Custom Calendar</em>' reference.
	 * @see #setCustomCalendar(CustomCalendar)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCustomCalendarEvent_CustomCalendar()
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendar#getCustomCalendarEvent
	 * @model opposite="customCalendarEvent" required="true"
	 * @generated
	 */
	CustomCalendar getCustomCalendar();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar <em>Custom Calendar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom Calendar</em>' reference.
	 * @see #getCustomCalendar()
	 * @generated
	 */
	void setCustomCalendar(CustomCalendar value);

} // CustomCalendarEvent
