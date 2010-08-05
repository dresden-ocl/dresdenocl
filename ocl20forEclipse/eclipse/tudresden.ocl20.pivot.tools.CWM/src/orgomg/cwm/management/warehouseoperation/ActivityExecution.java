/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.analysis.transformation.TransformationActivity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An ActivityExecution is used to record details of a specific execution of a TransformationActivity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ActivityExecution#getTransformationActivity <em>Transformation Activity</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ActivityExecution#getStepExecution <em>Step Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getActivityExecution()
 * @model
 * @generated
 */
public interface ActivityExecution extends TransformationExecution {
	/**
	 * Returns the value of the '<em><b>Transformation Activity</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationActivity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformation Activity</em>' reference.
	 * @see #setTransformationActivity(TransformationActivity)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getActivityExecution_TransformationActivity()
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity#getExecution
	 * @model opposite="execution" required="true"
	 * @generated
	 */
	TransformationActivity getTransformationActivity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ActivityExecution#getTransformationActivity <em>Transformation Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Activity</em>' reference.
	 * @see #getTransformationActivity()
	 * @generated
	 */
	void setTransformationActivity(TransformationActivity value);

	/**
	 * Returns the value of the '<em><b>Step Execution</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.StepExecution}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.StepExecution#getActivityExecution <em>Activity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StepExecutions recording the results of executing the individual
	 * TransformationSteps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Step Execution</em>' containment reference list.
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getActivityExecution_StepExecution()
	 * @see orgomg.cwm.management.warehouseoperation.StepExecution#getActivityExecution
	 * @model opposite="activityExecution" containment="true"
	 * @generated
	 */
	EList<StepExecution> getStepExecution();

} // ActivityExecution
