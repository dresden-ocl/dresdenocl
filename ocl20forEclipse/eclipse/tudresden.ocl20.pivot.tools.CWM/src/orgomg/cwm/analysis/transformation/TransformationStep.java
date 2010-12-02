/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.management.warehouseoperation.StepExecution;

import orgomg.cwm.management.warehouseprocess.WarehouseStep;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents the usage of a TransformationTask in a TransformationActivity. A TransformationStep relates to one TransformationTask. 
 * 
 * TransformationSteps are used to coordinate the flow of control between their TransformationTasks. Ordering of the TransformationSteps are defined using the PrecedenceConstrainedBy dependency.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationStep#getTask <em>Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationStep#getWarehouseStep <em>Warehouse Step</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationStep#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationStep()
 * @model
 * @generated
 */
public interface TransformationStep extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Task</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationTask#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationTask
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Task</em>' reference.
	 * @see #setTask(TransformationTask)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationStep_Task()
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getStep
	 * @model opposite="step" required="true"
	 * @generated
	 */
	TransformationTask getTask();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.TransformationStep#getTask <em>Task</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(TransformationTask value);

	/**
	 * Returns the value of the '<em><b>Warehouse Step</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.WarehouseStep}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep <em>Transformation Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the WarehouseSteps that perform a TranformationStep.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Step</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationStep_WarehouseStep()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep
	 * @model opposite="transformationStep"
	 * @generated
	 */
	EList<WarehouseStep> getWarehouseStep();

	/**
	 * Returns the value of the '<em><b>Execution</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.StepExecution}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getTransformationStep <em>Transformation Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a StepExecution recording details of a specific execution of a
	 * TransformationStep.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Execution</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationStep_Execution()
	 * @see orgomg.cwm.management.warehouseoperation.StepExecution#getTransformationStep
	 * @model opposite="transformationStep"
	 * @generated
	 */
	EList<StepExecution> getExecution();

} // TransformationStep
