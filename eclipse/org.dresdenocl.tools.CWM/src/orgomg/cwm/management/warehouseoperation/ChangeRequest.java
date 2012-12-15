/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a request for change affecting one or more ModelElements. The change request may represent a proposed change or one that has been implemented or rejected.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getChangeReason <em>Change Reason</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getStatus <em>Status</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#isCompleted <em>Completed</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getRequestDate <em>Request Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getCompletionDate <em>Completion Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest()
 * @model
 * @generated
 */
public interface ChangeRequest extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Change Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A description of the change.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Description</em>' attribute.
	 * @see #setChangeDescription(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_ChangeDescription()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getChangeDescription();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getChangeDescription <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Description</em>' attribute.
	 * @see #getChangeDescription()
	 * @generated
	 */
	void setChangeDescription(String value);

	/**
	 * Returns the value of the '<em><b>Change Reason</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The reason or justification for the ChangeRequest.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Change Reason</em>' attribute.
	 * @see #setChangeReason(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_ChangeReason()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getChangeReason();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getChangeReason <em>Change Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change Reason</em>' attribute.
	 * @see #getChangeReason()
	 * @generated
	 */
	void setChangeReason(String value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The status of the ChangeRequest. This would normally contain a string such as proposed, agreed, implemented or rejected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_Status()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getStatus();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(String value);

	/**
	 * Returns the value of the '<em><b>Completed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that no further action is required for this change request, i.e. it has either
	 * been implemented or been rejected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Completed</em>' attribute.
	 * @see #setCompleted(boolean)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_Completed()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isCompleted();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#isCompleted <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completed</em>' attribute.
	 * @see #isCompleted()
	 * @generated
	 */
	void setCompleted(boolean value);

	/**
	 * Returns the value of the '<em><b>Request Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the change request was raised.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Request Date</em>' attribute.
	 * @see #setRequestDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_RequestDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getRequestDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getRequestDate <em>Request Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request Date</em>' attribute.
	 * @see #getRequestDate()
	 * @generated
	 */
	void setRequestDate(String value);

	/**
	 * Returns the value of the '<em><b>Completion Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date when all action on the change request was completed (i.e. when implementation was completed or it was rejected).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Completion Date</em>' attribute.
	 * @see #setCompletionDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_CompletionDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getCompletionDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.ChangeRequest#getCompletionDate <em>Completion Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Completion Date</em>' attribute.
	 * @see #getCompletionDate()
	 * @generated
	 */
	void setCompletionDate(String value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getChangeRequest <em>Change Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a ModelElement affected by a ChangeRequest.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference list.
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getChangeRequest_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getChangeRequest
	 * @model opposite="changeRequest" required="true"
	 * @generated
	 */
	EList<ModelElement> getModelElement();

} // ChangeRequest
