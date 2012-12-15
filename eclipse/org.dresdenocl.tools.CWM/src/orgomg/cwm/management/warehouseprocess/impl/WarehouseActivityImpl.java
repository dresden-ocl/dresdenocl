/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.impl;

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

import orgomg.cwm.management.warehouseprocess.WarehouseActivity;
import orgomg.cwm.management.warehouseprocess.WarehouseStep;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Warehouse Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl#getWarehouseStep <em>Warehouse Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl#getTransformationActivity <em>Transformation Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WarehouseActivityImpl extends WarehouseProcessImpl implements WarehouseActivity {
	/**
	 * The cached value of the '{@link #getWarehouseStep() <em>Warehouse Step</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarehouseStep()
	 * @generated
	 * @ordered
	 */
	protected EList<WarehouseStep> warehouseStep;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WarehouseActivityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseprocessPackage.Literals.WAREHOUSE_ACTIVITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WarehouseStep> getWarehouseStep() {
		if (warehouseStep == null) {
			warehouseStep = new EObjectContainmentWithInverseEList<WarehouseStep>(WarehouseStep.class, this, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP, WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY);
		}
		return warehouseStep;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY, oldTransformationActivity, transformationActivity));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY, oldTransformationActivity, newTransformationActivity);
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
				msgs = ((InternalEObject)transformationActivity).eInverseRemove(this, TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY, TransformationActivity.class, msgs);
			if (newTransformationActivity != null)
				msgs = ((InternalEObject)newTransformationActivity).eInverseAdd(this, TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY, TransformationActivity.class, msgs);
			msgs = basicSetTransformationActivity(newTransformationActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY, newTransformationActivity, newTransformationActivity));
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWarehouseStep()).basicAdd(otherEnd, msgs);
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				if (transformationActivity != null)
					msgs = ((InternalEObject)transformationActivity).eInverseRemove(this, TransformationPackage.TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY, TransformationActivity.class, msgs);
				return basicSetTransformationActivity((TransformationActivity)otherEnd, msgs);
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				return ((InternalEList<?>)getWarehouseStep()).basicRemove(otherEnd, msgs);
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				return basicSetTransformationActivity(null, msgs);
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				return getWarehouseStep();
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				if (resolve) return getTransformationActivity();
				return basicGetTransformationActivity();
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				getWarehouseStep().clear();
				getWarehouseStep().addAll((Collection<? extends WarehouseStep>)newValue);
				return;
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				setTransformationActivity((TransformationActivity)newValue);
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				getWarehouseStep().clear();
				return;
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				setTransformationActivity((TransformationActivity)null);
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
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP:
				return warehouseStep != null && !warehouseStep.isEmpty();
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY:
				return transformationActivity != null;
		}
		return super.eIsSet(featureID);
	}

} //WarehouseActivityImpl
