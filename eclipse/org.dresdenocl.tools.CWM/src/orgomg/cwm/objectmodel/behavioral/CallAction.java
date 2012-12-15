/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.management.warehouseoperation.StepExecution;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A call action is an action resulting in an invocation of an operation.
 * 
 * The purpose of a CallAction is to identify the actual Arguments used in a specific invocation of an Operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.CallAction#getActualArgument <em>Actual Argument</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.CallAction#getOperation <em>Operation</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.CallAction#getStepExecution <em>Step Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getCallAction()
 * @model
 * @generated
 */
public interface CallAction extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Actual Argument</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.Argument}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Argument#getCallAction <em>Call Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Argument instances representing the actual arguments passed during Operation invocation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Argument</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getCallAction_ActualArgument()
	 * @see orgomg.cwm.objectmodel.behavioral.Argument#getCallAction
	 * @model opposite="callAction" containment="true"
	 * @generated
	 */
	EList<Argument> getActualArgument();

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Operation#getCallAction <em>Call Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Operation instance for which the CallAction instance records an invocation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getCallAction_Operation()
	 * @see orgomg.cwm.objectmodel.behavioral.Operation#getCallAction
	 * @model opposite="callAction" required="true"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.CallAction#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Step Execution</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.StepExecution}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getCallAction <em>Call Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StepExecution to which the CallAction applies.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Step Execution</em>' reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getCallAction_StepExecution()
	 * @see orgomg.cwm.management.warehouseoperation.StepExecution#getCallAction
	 * @model opposite="callAction"
	 * @generated
	 */
	EList<StepExecution> getStepExecution();

} // CallAction
