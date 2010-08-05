/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A TransformationExecution is used to record details of a specific execution.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getEndDate <em>End Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#isInProgress <em>In Progress</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#isSuccessful <em>Successful</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getStatus <em>Status</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution()
 * @model
 * @generated
 */
public interface TransformationExecution extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date and time when the execution started.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution_StartDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getStartDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(String value);

	/**
	 * Returns the value of the '<em><b>End Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date and time when the execution ended.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>End Date</em>' attribute.
	 * @see #setEndDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution_EndDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getEndDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getEndDate <em>End Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Date</em>' attribute.
	 * @see #getEndDate()
	 * @generated
	 */
	void setEndDate(String value);

	/**
	 * Returns the value of the '<em><b>In Progress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A boolean indicating whether or not the execution is in progress.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>In Progress</em>' attribute.
	 * @see #setInProgress(boolean)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution_InProgress()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isInProgress();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#isInProgress <em>In Progress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Progress</em>' attribute.
	 * @see #isInProgress()
	 * @generated
	 */
	void setInProgress(boolean value);

	/**
	 * Returns the value of the '<em><b>Successful</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A boolean indicating whether or not the execution completed successfully.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Successful</em>' attribute.
	 * @see #setSuccessful(boolean)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution_Successful()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isSuccessful();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#isSuccessful <em>Successful</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Successful</em>' attribute.
	 * @see #isSuccessful()
	 * @generated
	 */
	void setSuccessful(boolean value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An expression that may be used to provide status details of the execution. For example it could provide comments for a successful execution, or details of errors for an unsuccessful execution.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' containment reference.
	 * @see #setStatus(Expression)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getTransformationExecution_Status()
	 * @model containment="true"
	 * @generated
	 */
	Expression getStatus();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.TransformationExecution#getStatus <em>Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' containment reference.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(Expression value);

} // TransformationExecution
