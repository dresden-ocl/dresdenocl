/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.BooleanExpression;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

import orgomg.cwm.resource.relational.enumerations.ActionOrientationType;
import orgomg.cwm.resource.relational.enumerations.ConditionTimingType;
import orgomg.cwm.resource.relational.enumerations.EventManipulationType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An action run by the DBMS when specified events occur on the table owning the Trigger
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getEventManipulation <em>Event Manipulation</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getActionCondition <em>Action Condition</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getActionStatement <em>Action Statement</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getActionOrientation <em>Action Orientation</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getConditionTiming <em>Condition Timing</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getConditionReferenceNewTable <em>Condition Reference New Table</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getConditionReferenceOldTable <em>Condition Reference Old Table</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getUsedColumnSet <em>Used Column Set</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Trigger#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger()
 * @model
 * @generated
 */
public interface Trigger extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Event Manipulation</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.EventManipulationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates what types of events are using the current Trigger.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Manipulation</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.EventManipulationType
	 * @see #setEventManipulation(EventManipulationType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_EventManipulation()
	 * @model
	 * @generated
	 */
	EventManipulationType getEventManipulation();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getEventManipulation <em>Event Manipulation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Manipulation</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.EventManipulationType
	 * @see #getEventManipulation()
	 * @generated
	 */
	void setEventManipulation(EventManipulationType value);

	/**
	 * Returns the value of the '<em><b>Action Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A boolean expression which defines when the trigger has to be executed
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action Condition</em>' containment reference.
	 * @see #setActionCondition(BooleanExpression)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ActionCondition()
	 * @model containment="true"
	 * @generated
	 */
	BooleanExpression getActionCondition();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getActionCondition <em>Action Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Condition</em>' containment reference.
	 * @see #getActionCondition()
	 * @generated
	 */
	void setActionCondition(BooleanExpression value);

	/**
	 * Returns the value of the '<em><b>Action Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Trigger action itself
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action Statement</em>' containment reference.
	 * @see #setActionStatement(ProcedureExpression)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ActionStatement()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getActionStatement();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getActionStatement <em>Action Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Statement</em>' containment reference.
	 * @see #getActionStatement()
	 * @generated
	 */
	void setActionStatement(ProcedureExpression value);

	/**
	 * Returns the value of the '<em><b>Action Orientation</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.ActionOrientationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * It indicates if the trigger is called once per statement execution or before or after each row of the table is modified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action Orientation</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ActionOrientationType
	 * @see #setActionOrientation(ActionOrientationType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ActionOrientation()
	 * @model
	 * @generated
	 */
	ActionOrientationType getActionOrientation();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getActionOrientation <em>Action Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Orientation</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ActionOrientationType
	 * @see #getActionOrientation()
	 * @generated
	 */
	void setActionOrientation(ActionOrientationType value);

	/**
	 * Returns the value of the '<em><b>Condition Timing</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.ConditionTimingType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * It indicates if the trigger activity is run before or after the statement or row is modified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition Timing</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ConditionTimingType
	 * @see #setConditionTiming(ConditionTimingType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ConditionTiming()
	 * @model
	 * @generated
	 */
	ConditionTimingType getConditionTiming();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getConditionTiming <em>Condition Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Timing</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ConditionTimingType
	 * @see #getConditionTiming()
	 * @generated
	 */
	void setConditionTiming(ConditionTimingType value);

	/**
	 * Returns the value of the '<em><b>Condition Reference New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The alias for the owning table name, used in the actionStatement, to represent the state of the table after the insert/delete/update
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition Reference New Table</em>' attribute.
	 * @see #setConditionReferenceNewTable(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ConditionReferenceNewTable()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getConditionReferenceNewTable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getConditionReferenceNewTable <em>Condition Reference New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Reference New Table</em>' attribute.
	 * @see #getConditionReferenceNewTable()
	 * @generated
	 */
	void setConditionReferenceNewTable(String value);

	/**
	 * Returns the value of the '<em><b>Condition Reference Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The alias for the name of the owning table, used in the actionStatement, to represent the state of the table before the update/delete/insert.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition Reference Old Table</em>' attribute.
	 * @see #setConditionReferenceOldTable(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_ConditionReferenceOldTable()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getConditionReferenceOldTable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getConditionReferenceOldTable <em>Condition Reference Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition Reference Old Table</em>' attribute.
	 * @see #getConditionReferenceOldTable()
	 * @generated
	 */
	void setConditionReferenceOldTable(String value);

	/**
	 * Returns the value of the '<em><b>Used Column Set</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.NamedColumnSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.NamedColumnSet#getUsingTrigger <em>Using Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * NamedColumnSets referenced by the actionStatement or the actionCondition.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Used Column Set</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_UsedColumnSet()
	 * @see orgomg.cwm.resource.relational.NamedColumnSet#getUsingTrigger
	 * @model opposite="usingTrigger"
	 * @generated
	 */
	EList<NamedColumnSet> getUsedColumnSet();

	/**
	 * Returns the value of the '<em><b>Table</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.Table#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The table which owns the Trigger
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Table</em>' reference.
	 * @see #setTable(Table)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTrigger_Table()
	 * @see orgomg.cwm.resource.relational.Table#getTrigger
	 * @model opposite="trigger" required="true"
	 * @generated
	 */
	Table getTable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Trigger#getTable <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(Table value);

} // Trigger
