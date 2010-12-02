/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import orgomg.cwm.analysis.transformation.TransformationStep;

import orgomg.cwm.objectmodel.behavioral.CallAction;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A StepExecution is used to record details of a specific execution of a TransformationStep.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.StepExecution#getTransformationStep <em>Transformation Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.StepExecution#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.StepExecution#getCallAction <em>Call Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getStepExecution()
 * @model
 * @generated
 */
public interface StepExecution extends TransformationExecution {
	/**
	 * Returns the value of the '<em><b>Transformation Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationStep#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationStep.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformation Step</em>' reference.
	 * @see #setTransformationStep(TransformationStep)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getStepExecution_TransformationStep()
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getExecution
	 * @model opposite="execution" required="true"
	 * @generated
	 */
	TransformationStep getTransformationStep();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getTransformationStep <em>Transformation Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Step</em>' reference.
	 * @see #getTransformationStep()
	 * @generated
	 */
	void setTransformationStep(TransformationStep value);

	/**
	 * Returns the value of the '<em><b>Activity Execution</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.ActivityExecution#getStepExecution <em>Step Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ActivityExecution of which the StepExecution is a part.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Execution</em>' container reference.
	 * @see #setActivityExecution(ActivityExecution)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getStepExecution_ActivityExecution()
	 * @see orgomg.cwm.management.warehouseoperation.ActivityExecution#getStepExecution
	 * @model opposite="stepExecution"
	 * @generated
	 */
	ActivityExecution getActivityExecution();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getActivityExecution <em>Activity Execution</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Execution</em>' container reference.
	 * @see #getActivityExecution()
	 * @generated
	 */
	void setActivityExecution(ActivityExecution value);

	/**
	 * Returns the value of the '<em><b>Call Action</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.CallAction#getStepExecution <em>Step Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the CallAction for a StepExecution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Call Action</em>' reference.
	 * @see #setCallAction(CallAction)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getStepExecution_CallAction()
	 * @see orgomg.cwm.objectmodel.behavioral.CallAction#getStepExecution
	 * @model opposite="stepExecution"
	 * @generated
	 */
	CallAction getCallAction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getCallAction <em>Call Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Action</em>' reference.
	 * @see #getCallAction()
	 * @generated
	 */
	void setCallAction(CallAction value);

} // StepExecution
