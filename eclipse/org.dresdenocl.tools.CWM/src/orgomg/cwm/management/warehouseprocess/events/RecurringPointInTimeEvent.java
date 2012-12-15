/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek;
import orgomg.cwm.management.warehouseprocess.datatype.RecurringType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Recurring Point In Time Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This event triggers a WarehouseProcess on a regular basis such as a specific date or time (for example, the Wednesday of every other week, at 2:30 pm).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getRecurringType <em>Recurring Type</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getFrequencyFactor <em>Frequency Factor</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMonth <em>Month</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfMonth <em>Day Of Month</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfWeek <em>Day Of Week</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getHour <em>Hour</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMinute <em>Minute</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getSecond <em>Second</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent()
 * @model
 * @generated
 */
public interface RecurringPointInTimeEvent extends PointInTimeEvent {
	/**
	 * Returns the value of the '<em><b>Recurring Type</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.management.warehouseprocess.datatype.RecurringType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates how often the event should be triggered (weekly, daily, etc.).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Recurring Type</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.RecurringType
	 * @see #setRecurringType(RecurringType)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_RecurringType()
	 * @model
	 * @generated
	 */
	RecurringType getRecurringType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getRecurringType <em>Recurring Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recurring Type</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.RecurringType
	 * @see #getRecurringType()
	 * @generated
	 */
	void setRecurringType(RecurringType value);

	/**
	 * Returns the value of the '<em><b>Frequency Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates the repetition of the event. For example, for a weekly recurringType, a value of 1 will mean that it is to be triggered every week, a value of 2 will mean that it is to be triggered every other week, etc.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Frequency Factor</em>' attribute.
	 * @see #setFrequencyFactor(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_FrequencyFactor()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getFrequencyFactor();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getFrequencyFactor <em>Frequency Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency Factor</em>' attribute.
	 * @see #getFrequencyFactor()
	 * @generated
	 */
	void setFrequencyFactor(long value);

	/**
	 * Returns the value of the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates which month of the year (from 1 to 12) an annual event is to be triggered.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Month</em>' attribute.
	 * @see #setMonth(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_Month()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getMonth();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMonth <em>Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Month</em>' attribute.
	 * @see #getMonth()
	 * @generated
	 */
	void setMonth(long value);

	/**
	 * Returns the value of the '<em><b>Day Of Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates which day of the month (from 1 to 31) a monthly or annual event is to be triggered. For a monthly event, if the day of the month is greater than the number of days in the month, it is assumed that the scheduler will run the WarehouseProcess on the last day of the month.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Day Of Month</em>' attribute.
	 * @see #setDayOfMonth(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_DayOfMonth()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getDayOfMonth();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfMonth <em>Day Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Of Month</em>' attribute.
	 * @see #getDayOfMonth()
	 * @generated
	 */
	void setDayOfMonth(long value);

	/**
	 * Returns the value of the '<em><b>Day Of Week</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates which day of the week a weekly schedule is running.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Day Of Week</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek
	 * @see #setDayOfWeek(DayOfWeek)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_DayOfWeek()
	 * @model
	 * @generated
	 */
	DayOfWeek getDayOfWeek();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfWeek <em>Day Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Day Of Week</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek
	 * @see #getDayOfWeek()
	 * @generated
	 */
	void setDayOfWeek(DayOfWeek value);

	/**
	 * Returns the value of the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates at what hour (from 0 to 23) an annual, monthly, weekly, or daily event is being triggered.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hour</em>' attribute.
	 * @see #setHour(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_Hour()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getHour();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getHour <em>Hour</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hour</em>' attribute.
	 * @see #getHour()
	 * @generated
	 */
	void setHour(long value);

	/**
	 * Returns the value of the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates at what minute (from 0 to 59) an event is triggered. Applies to all events except the "everyMinute" ones.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Minute</em>' attribute.
	 * @see #setMinute(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_Minute()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getMinute();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMinute <em>Minute</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minute</em>' attribute.
	 * @see #getMinute()
	 * @generated
	 */
	void setMinute(long value);

	/**
	 * Returns the value of the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates at what second (from 0 to 59) an event must be run. Applies to all events.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Second</em>' attribute.
	 * @see #setSecond(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRecurringPointInTimeEvent_Second()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getSecond();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getSecond <em>Second</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Second</em>' attribute.
	 * @see #getSecond()
	 * @generated
	 */
	void setSecond(long value);

} // RecurringPointInTimeEvent
