/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Calendar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named list of dates and times. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendar#getCustomCalendarEvent <em>Custom Calendar Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCustomCalendar()
 * @model
 * @generated
 */
public interface CustomCalendar extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Custom Calendar Event</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar <em>Custom Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates which event uses this custom calendar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Custom Calendar Event</em>' reference list.
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCustomCalendar_CustomCalendarEvent()
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar
	 * @model opposite="customCalendar"
	 * @generated
	 */
	EList<CustomCalendarEvent> getCustomCalendarEvent();

} // CustomCalendar
