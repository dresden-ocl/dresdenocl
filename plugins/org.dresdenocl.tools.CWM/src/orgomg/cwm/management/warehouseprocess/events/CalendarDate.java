/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Calendar Date</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An entry in a CustomCalendar representing a specific date and time.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.CalendarDate#getSpecificDate <em>Specific Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCalendarDate()
 * @model
 * @generated
 */
public interface CalendarDate extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Specific Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of the date. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specific Date</em>' attribute.
	 * @see #setSpecificDate(String)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCalendarDate_SpecificDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getSpecificDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.CalendarDate#getSpecificDate <em>Specific Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specific Date</em>' attribute.
	 * @see #getSpecificDate()
	 * @generated
	 */
	void setSpecificDate(String value);

} // CalendarDate
