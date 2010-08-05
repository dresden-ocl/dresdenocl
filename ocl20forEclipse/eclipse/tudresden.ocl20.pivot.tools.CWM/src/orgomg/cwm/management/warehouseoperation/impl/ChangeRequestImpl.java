/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.management.warehouseoperation.ChangeRequest;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ModelElement;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getChangeDescription <em>Change Description</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getChangeReason <em>Change Reason</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#isCompleted <em>Completed</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getRequestDate <em>Request Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getCompletionDate <em>Completion Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ChangeRequestImpl#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChangeRequestImpl extends ModelElementImpl implements ChangeRequest {
	/**
	 * The default value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANGE_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChangeDescription() <em>Change Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeDescription()
	 * @generated
	 * @ordered
	 */
	protected String changeDescription = CHANGE_DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeReason() <em>Change Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeReason()
	 * @generated
	 * @ordered
	 */
	protected static final String CHANGE_REASON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChangeReason() <em>Change Reason</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeReason()
	 * @generated
	 * @ordered
	 */
	protected String changeReason = CHANGE_REASON_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final String STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected String status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #isCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompleted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COMPLETED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCompleted() <em>Completed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCompleted()
	 * @generated
	 * @ordered
	 */
	protected boolean completed = COMPLETED_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequestDate() <em>Request Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestDate()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUEST_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestDate() <em>Request Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestDate()
	 * @generated
	 * @ordered
	 */
	protected String requestDate = REQUEST_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompletionDate() <em>Completion Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionDate()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPLETION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompletionDate() <em>Completion Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionDate()
	 * @generated
	 * @ordered
	 */
	protected String completionDate = COMPLETION_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModelElement() <em>Model Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElement()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElement> modelElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChangeRequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseoperationPackage.Literals.CHANGE_REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChangeDescription() {
		return changeDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeDescription(String newChangeDescription) {
		String oldChangeDescription = changeDescription;
		changeDescription = newChangeDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_DESCRIPTION, oldChangeDescription, changeDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getChangeReason() {
		return changeReason;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeReason(String newChangeReason) {
		String oldChangeReason = changeReason;
		changeReason = newChangeReason;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_REASON, oldChangeReason, changeReason));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(String newStatus) {
		String oldStatus = status;
		status = newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompleted(boolean newCompleted) {
		boolean oldCompleted = completed;
		completed = newCompleted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__COMPLETED, oldCompleted, completed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestDate(String newRequestDate) {
		String oldRequestDate = requestDate;
		requestDate = newRequestDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__REQUEST_DATE, oldRequestDate, requestDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompletionDate() {
		return completionDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletionDate(String newCompletionDate) {
		String oldCompletionDate = completionDate;
		completionDate = newCompletionDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.CHANGE_REQUEST__COMPLETION_DATE, oldCompletionDate, completionDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelElement> getModelElement() {
		if (modelElement == null) {
			modelElement = new EObjectWithInverseResolvingEList.ManyInverse<ModelElement>(ModelElement.class, this, WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT, CorePackage.MODEL_ELEMENT__CHANGE_REQUEST);
		}
		return modelElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModelElement()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				return ((InternalEList<?>)getModelElement()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_DESCRIPTION:
				return getChangeDescription();
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_REASON:
				return getChangeReason();
			case WarehouseoperationPackage.CHANGE_REQUEST__STATUS:
				return getStatus();
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETED:
				return isCompleted();
			case WarehouseoperationPackage.CHANGE_REQUEST__REQUEST_DATE:
				return getRequestDate();
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETION_DATE:
				return getCompletionDate();
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				return getModelElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_DESCRIPTION:
				setChangeDescription((String)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_REASON:
				setChangeReason((String)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__STATUS:
				setStatus((String)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETED:
				setCompleted((Boolean)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__REQUEST_DATE:
				setRequestDate((String)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETION_DATE:
				setCompletionDate((String)newValue);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				getModelElement().clear();
				getModelElement().addAll((Collection<? extends ModelElement>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_DESCRIPTION:
				setChangeDescription(CHANGE_DESCRIPTION_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_REASON:
				setChangeReason(CHANGE_REASON_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETED:
				setCompleted(COMPLETED_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__REQUEST_DATE:
				setRequestDate(REQUEST_DATE_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETION_DATE:
				setCompletionDate(COMPLETION_DATE_EDEFAULT);
				return;
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				getModelElement().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_DESCRIPTION:
				return CHANGE_DESCRIPTION_EDEFAULT == null ? changeDescription != null : !CHANGE_DESCRIPTION_EDEFAULT.equals(changeDescription);
			case WarehouseoperationPackage.CHANGE_REQUEST__CHANGE_REASON:
				return CHANGE_REASON_EDEFAULT == null ? changeReason != null : !CHANGE_REASON_EDEFAULT.equals(changeReason);
			case WarehouseoperationPackage.CHANGE_REQUEST__STATUS:
				return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETED:
				return completed != COMPLETED_EDEFAULT;
			case WarehouseoperationPackage.CHANGE_REQUEST__REQUEST_DATE:
				return REQUEST_DATE_EDEFAULT == null ? requestDate != null : !REQUEST_DATE_EDEFAULT.equals(requestDate);
			case WarehouseoperationPackage.CHANGE_REQUEST__COMPLETION_DATE:
				return COMPLETION_DATE_EDEFAULT == null ? completionDate != null : !COMPLETION_DATE_EDEFAULT.equals(completionDate);
			case WarehouseoperationPackage.CHANGE_REQUEST__MODEL_ELEMENT:
				return modelElement != null && !modelElement.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (changeDescription: ");
		result.append(changeDescription);
		result.append(", changeReason: ");
		result.append(changeReason);
		result.append(", status: ");
		result.append(status);
		result.append(", completed: ");
		result.append(completed);
		result.append(", requestDate: ");
		result.append(requestDate);
		result.append(", completionDate: ");
		result.append(completionDate);
		result.append(')');
		return result.toString();
	}

} //ChangeRequestImpl
