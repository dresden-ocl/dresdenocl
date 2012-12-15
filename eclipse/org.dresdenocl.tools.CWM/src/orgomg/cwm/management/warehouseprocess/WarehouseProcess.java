/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.management.warehouseprocess.events.InternalEvent;
import orgomg.cwm.management.warehouseprocess.events.WarehouseEvent;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Warehouse Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A WarehouseProcess represents the processing of a transformation. It is instantiated as one of its subtypes WarehouseActivity or WarehouseStep, depending on whether it represents the processing of a TransformationActivity or a Transformation Step.
 * A WarehouseProcess may be associated with one or more WarehouseEvents, each identifying events that cause the processing to be initiated. It may also be associated with one or more internal events that will be triggered when processing terminates.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isStaticDefinition <em>Static Definition</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isIsSequential <em>Is Sequential</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getWarehouseEvent <em>Warehouse Event</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getInternalEvent <em>Internal Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseProcess()
 * @model abstract="true"
 * @generated
 */
public interface WarehouseProcess extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Static Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When a WarehouseProcess is a constant mapping (such as a Relational View of legacy data or a continuous data propagation process), this flag indicates that the mapping does not require to be run for the target to be up-to-date and in sync with
	 * the source.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Static Definition</em>' attribute.
	 * @see #setStaticDefinition(boolean)
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseProcess_StaticDefinition()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isStaticDefinition();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isStaticDefinition <em>Static Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static Definition</em>' attribute.
	 * @see #isStaticDefinition()
	 * @generated
	 */
	void setStaticDefinition(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This flag indicates if more than one instance of this WarehouseProcess may run at a time. If this flag is true, the scheduler should fail any attempt to trigger this WarehouseProcess while an instance is already in progress.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Sequential</em>' attribute.
	 * @see #setIsSequential(boolean)
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseProcess_IsSequential()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsSequential();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isIsSequential <em>Is Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Sequential</em>' attribute.
	 * @see #isIsSequential()
	 * @generated
	 */
	void setIsSequential(boolean value);

	/**
	 * Returns the value of the '<em><b>Warehouse Event</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess <em>Warehouse Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a set of events of various types, which will be used to trigger the execution of the warehouse process and its associated transformations.	
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Event</em>' containment reference list.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseProcess_WarehouseEvent()
	 * @see orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess
	 * @model opposite="warehouseProcess" containment="true"
	 * @generated
	 */
	EList<WarehouseEvent> getWarehouseEvent();

	/**
	 * Returns the value of the '<em><b>Internal Event</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.events.InternalEvent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getTriggeringWP <em>Triggering WP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies an internal event triggered by the termination of the WarehouseProcess. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Internal Event</em>' reference list.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseProcess_InternalEvent()
	 * @see orgomg.cwm.management.warehouseprocess.events.InternalEvent#getTriggeringWP
	 * @model opposite="triggeringWP"
	 * @generated
	 */
	EList<InternalEvent> getInternalEvent();

} // WarehouseProcess
