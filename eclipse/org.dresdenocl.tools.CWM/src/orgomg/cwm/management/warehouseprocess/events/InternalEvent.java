/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.management.warehouseprocess.WarehouseProcess;

import orgomg.cwm.objectmodel.core.BooleanExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Internal Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An event which may be triggered, depending on whether or not a condition is satisfied, by the conclusion of one or more WarehouseProcess runs.
 * 
 * There are two types of InternalEvents, depending whether the event triggers a series of different WarehouseProcesses, or whether the event triggers the same WarehouseProcess in an attempt to retry a failed run.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getTriggeringWP <em>Triggering WP</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getInternalEvent()
 * @model
 * @generated
 */
public interface InternalEvent extends WarehouseEvent {
	/**
	 * Returns the value of the '<em><b>Triggering WP</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.WarehouseProcess}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getInternalEvent <em>Internal Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the triggering warehouse process. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Triggering WP</em>' reference list.
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getInternalEvent_TriggeringWP()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#getInternalEvent
	 * @model opposite="internalEvent" required="true"
	 * @generated
	 */
	EList<WarehouseProcess> getTriggeringWP();

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates what condition the triggering WarehouseProcess run must meet to be considered (success, failure, warnings, etc.).
	 * 
	 * How the condition is expressed, and how the result of a Transform is generated is left to the implementation of the scheduler and the transformation, respectively.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(BooleanExpression)
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#getInternalEvent_Condition()
	 * @model containment="true"
	 * @generated
	 */
	BooleanExpression getCondition();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(BooleanExpression value);

} // InternalEvent
