/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import orgomg.cwm.management.warehouseprocess.WarehouseProcess;

import orgomg.cwm.objectmodel.behavioral.Event;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Warehouse Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A virtual class to refer to any Event.
 * 
 * A WarehouseEvent (or its derivations) represents what triggers the running of a WarehouseProcess. An event can be initiated by a clock, by an external trigger, or by an internal trigger (the conclusion of some WarehouseProcess).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess <em>Warehouse Process</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getWarehouseEvent()
 * @model abstract="true"
 * @generated
 */
public interface WarehouseEvent extends Event {
	/**
	 * Returns the value of the '<em><b>Warehouse Process</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getWarehouseEvent <em>Warehouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the warehouse process which will triggered by the event.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Process</em>' container reference.
	 * @see #setWarehouseProcess(WarehouseProcess)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getWarehouseEvent_WarehouseProcess()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#getWarehouseEvent
	 * @model opposite="warehouseEvent" required="true"
	 * @generated
	 */
	WarehouseProcess getWarehouseProcess();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess <em>Warehouse Process</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warehouse Process</em>' container reference.
	 * @see #getWarehouseProcess()
	 * @generated
	 */
	void setWarehouseProcess(WarehouseProcess value);

} // WarehouseEvent
