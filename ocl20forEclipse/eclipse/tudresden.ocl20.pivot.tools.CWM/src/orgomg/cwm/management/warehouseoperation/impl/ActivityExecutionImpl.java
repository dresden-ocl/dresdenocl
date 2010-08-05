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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.TransformationActivity;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.management.warehouseoperation.ActivityExecution;
import orgomg.cwm.management.warehouseoperation.StepExecution;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ActivityExecutionImpl#getTransformationActivity <em>Transformation Activity</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.ActivityExecutionImpl#getStepExecution <em>Step Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityExecutionImpl extends TransformationExecutionImpl implements ActivityExecution {
	/**
	 * The cached value of the '{@link #getTransformationActivity() <em>Transformation Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationActivity()
	 * @generated
	 * @ordered
	 */
	protected TransformationActivity transformationActivity;

	/**
	 * The cached value of the '{@link #getStepExecution() <em>Step Execution</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepExecution()
	 * @generated
	 * @ordered
	 */
	protected EList<StepExecution> stepExecution;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseoperationPackage.Literals.ACTIVITY_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationActivity getTransformationActivity() {
		if (transformationActivity != null && transformationActivity.eIsProxy()) {
			InternalEObject oldTransformationActivity = (InternalEObject)transformationActivity;
			transformationActivity = (TransformationActivity)eResolveProxy(oldTransformationActivity);
			if (transformationActivity != oldTransformationActivity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY, oldTransformationActivity, transformationActivity));
			}
		}
		return transformationActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationActivity basicGetTransformationActivity() {
		return transformationActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransformationActivity(TransformationActivity newTransformationActivity, NotificationChain msgs) {
		TransformationActivity oldTransformationActivity = transformationActivity;
		transformationActivity = newTransformationActivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY, oldTransformationActivity, newTransformationActivity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformationActivity(TransformationActivity newTransformationActivity) {
		if (newTransformationActivity != transformationActivity) {
			NotificationChain msgs = null;
			if (transformationActivity != null)
				msgs = ((InternalEObject)transformationActivity).eInverseRemove(this, TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION, TransformationActivity.class, msgs);
			if (newTransformationActivity != null)
				msgs = ((InternalEObject)newTransformationActivity).eInverseAdd(this, TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION, TransformationActivity.class, msgs);
			msgs = basicSetTransformationActivity(newTransformationActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY, newTransformationActivity, newTransformationActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StepExecution> getStepExecution() {
		if (stepExecution == null) {
			stepExecution = new EObjectContainmentWithInverseEList<StepExecution>(StepExecution.class, this, WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION, WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION);
		}
		return stepExecution;
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				if (transformationActivity != null)
					msgs = ((InternalEObject)transformationActivity).eInverseRemove(this, TransformationPackage.TRANSFORMATION_ACTIVITY__EXECUTION, TransformationActivity.class, msgs);
				return basicSetTransformationActivity((TransformationActivity)otherEnd, msgs);
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStepExecution()).basicAdd(otherEnd, msgs);
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				return basicSetTransformationActivity(null, msgs);
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				return ((InternalEList<?>)getStepExecution()).basicRemove(otherEnd, msgs);
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				if (resolve) return getTransformationActivity();
				return basicGetTransformationActivity();
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				return getStepExecution();
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				setTransformationActivity((TransformationActivity)newValue);
				return;
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				getStepExecution().clear();
				getStepExecution().addAll((Collection<? extends StepExecution>)newValue);
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				setTransformationActivity((TransformationActivity)null);
				return;
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				getStepExecution().clear();
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
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__TRANSFORMATION_ACTIVITY:
				return transformationActivity != null;
			case WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION:
				return stepExecution != null && !stepExecution.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActivityExecutionImpl
