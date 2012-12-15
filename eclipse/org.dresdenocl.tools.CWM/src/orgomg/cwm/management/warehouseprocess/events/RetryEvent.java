/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Retry Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Indicates that a WarehouseProcess should be retried upon failure. This type of event is used for example when a WarehouseProcess relies on sources with uncertain availability (connection or uptime).
 * 
 * In general, the triggering WarehouseProcess and the triggered WarehouseProcess are the same, and only one WarehouseProcess is involved. But this is not an imposed limitation. It is left to the schedulers to decide on the implementation behavior for complex cases.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getWaitDuration <em>Wait Duration</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getMaxCount <em>Max Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRetryEvent()
 * @model
 * @generated
 */
public interface RetryEvent extends InternalEvent {
	/**
	 * Returns the value of the '<em><b>Wait Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates the length of time (in seconds) to wait before retrying the triggered WarehouseProcess.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wait Duration</em>' attribute.
	 * @see #setWaitDuration(String)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRetryEvent_WaitDuration()
	 * @model dataType="orgomg.cwm.objectmodel.core.Float"
	 * @generated
	 */
	String getWaitDuration();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getWaitDuration <em>Wait Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait Duration</em>' attribute.
	 * @see #getWaitDuration()
	 * @generated
	 */
	void setWaitDuration(String value);

	/**
	 * Returns the value of the '<em><b>Max Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates how many times the triggered WarehouseProcess should be retried before being declared failed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Count</em>' attribute.
	 * @see #setMaxCount(long)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getRetryEvent_MaxCount()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getMaxCount();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getMaxCount <em>Max Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Count</em>' attribute.
	 * @see #getMaxCount()
	 * @generated
	 */
	void setMaxCount(long value);

} // RetryEvent
