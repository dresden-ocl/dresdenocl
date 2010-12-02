/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Point In Time Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A PointInTime event is triggered at a fixed time, independently of any external context.
 * 
 * The triggering time can be either defined functionally (as in the RecurringPointInTimeEvent extension of this class), or by an explicit list of times (CustomCalendarEvent).
 * <!-- end-model-doc -->
 *
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getPointInTimeEvent()
 * @model
 * @generated
 */
public interface PointInTimeEvent extends ScheduleEvent {
} // PointInTimeEvent
