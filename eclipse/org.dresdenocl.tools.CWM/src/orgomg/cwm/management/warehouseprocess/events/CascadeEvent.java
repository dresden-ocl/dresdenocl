/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cascade Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CascadeEvent indicates that completion of one or more triggering WarehouseProcesses triggers another WarehouseProcess.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.CascadeEvent#getWaitRule <em>Wait Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCascadeEvent()
 * @model
 * @generated
 */
public interface CascadeEvent extends InternalEvent {
	/**
	 * Returns the value of the '<em><b>Wait Rule</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if the event should be triggered as soon as any of the triggering WarehouseProcesses has completed that satisfies the condition (inherited from InternalEvent) or only when all the triggering WarehouseProcesses have completed (provided the condition is satisfied).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wait Rule</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType
	 * @see #setWaitRule(WaitRuleType)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getCascadeEvent_WaitRule()
	 * @model
	 * @generated
	 */
	WaitRuleType getWaitRule();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.CascadeEvent#getWaitRule <em>Wait Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait Rule</em>' attribute.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType
	 * @see #getWaitRule()
	 * @generated
	 */
	void setWaitRule(WaitRuleType value);

} // CascadeEvent
