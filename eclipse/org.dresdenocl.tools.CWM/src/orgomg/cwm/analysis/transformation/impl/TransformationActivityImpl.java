/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.TransformationActivity;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.management.warehouseoperation.ActivityExecution;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.management.warehouseprocess.WarehouseActivity;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.objectmodel.core.impl.SubsystemImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl#getWarehouseActivity <em>Warehouse Activity</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationActivityImpl extends SubsystemImpl implements TransformationActivity {
	/**
	 * The default value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationDate() <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationDate()
	 * @generated
	 * @ordered
	 */
	protected String creationDate = CREATION_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWarehouseActivity() <em>Warehouse Activity</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarehouseActivity()
	 * @generated
	 * @ordered
	 */
	protected EList<WarehouseActivity> warehouseActivity;

	/**
	 * The cached value of the '{@link #getExecution() <em>Execution</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivityExecution> execution;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.TRANSFORMATION_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationDate(String newCreationDate) {
		String oldCreationDate = creationDate;
		creationDate = newCreationDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.TRANSFORMATION_ACTIVITY__CREATION_DATE, oldCreationDate, creationDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WarehouseActivity> getWarehouseActivity() {
		if (warehouseActivity == null) {
			warehouseActivity = new EObjectWithInverseResolvingEList<WarehouseActivity>(WarehouseActivity.class, this, TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY);
		}
		return warehouseActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivityExecution> getExecution() {
		if (execution == null) {
			execution = new EObjectWithInverseResolvingEList<ActivityExecution>(ActivityExecution.class, this, TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION, WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY);
		}
		return execution;
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWarehouseActivity()).basicAdd(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecution()).basicAdd(otherEnd, msgs);
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				return ((InternalEList<?>)getWarehouseActivity()).basicRemove(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				return ((InternalEList<?>)getExecution()).basicRemove(otherEnd, msgs);
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__CREATION_DATE:
				return getCreationDate();
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				return getWarehouseActivity();
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				return getExecution();
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__CREATION_DATE:
				setCreationDate((String)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				getWarehouseActivity().clear();
				getWarehouseActivity().addAll((Collection<? extends WarehouseActivity>)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				getExecution().clear();
				getExecution().addAll((Collection<? extends ActivityExecution>)newValue);
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__CREATION_DATE:
				setCreationDate(CREATION_DATE_EDEFAULT);
				return;
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				getWarehouseActivity().clear();
				return;
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				getExecution().clear();
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
			case TransformationPackage.TRANSFORMATION_ACTIVITY__CREATION_DATE:
				return CREATION_DATE_EDEFAULT == null ? creationDate != null : !CREATION_DATE_EDEFAULT.equals(creationDate);
			case TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY:
				return warehouseActivity != null && !warehouseActivity.isEmpty();
			case TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION:
				return execution != null && !execution.isEmpty();
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
		result.append(" (creationDate: ");
		result.append(creationDate);
		result.append(')');
		return result.toString();
	}

} //TransformationActivityImpl
