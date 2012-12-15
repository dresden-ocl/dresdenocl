/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interval Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An IntervalEvent controls a continuous run of a WarehouseProcess. The Warehouse Process will run, then wait for the duration specified in the event, then run again.
 * 
 * An IntervalEvent is not affected by the result of the WarehouseProcess.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.IntervalEvent#getDuration <em>Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getIntervalEvent()
 * @model
 * @generated
 */
public interface IntervalEvent extends ScheduleEvent {
	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates the length of time (in seconds) to wait after a run of the WarehouseProcess before triggering the next one.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(String)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getIntervalEvent_Duration()
	 * @model dataType="orgomg.cwm.objectmodel.core.Float"
	 * @generated
	 */
	String getDuration();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.IntervalEvent#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(String value);

} // IntervalEvent
