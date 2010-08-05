/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.analysis.transformation.TransformationPackage;
import orgomg.cwm.analysis.transformation.TransformationStep;

import orgomg.cwm.management.warehouseprocess.WarehouseActivity;
import orgomg.cwm.management.warehouseprocess.WarehouseStep;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Warehouse Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl#getTransformationStep <em>Transformation Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl#getWarehouseActivity <em>Warehouse Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WarehouseStepImpl extends WarehouseProcessImpl implements WarehouseStep {
	/**
	 * The cached value of the '{@link #getTransformationStep() <em>Transformation Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationStep()
	 * @generated
	 * @ordered
	 */
	protected TransformationStep transformationStep;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WarehouseStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseprocessPackage.Literals.WAREHOUSE_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationStep getTransformationStep() {
		if (transformationStep != null && transformationStep.eIsProxy()) {
			InternalEObject oldTransformationStep = (InternalEObject)transformationStep;
			transformationStep = (TransformationStep)eResolveProxy(oldTransformationStep);
			if (transformationStep != oldTransformationStep) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP, oldTransformationStep, transformationStep));
			}
		}
		return transformationStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationStep basicGetTransformationStep() {
		return transformationStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransformationStep(TransformationStep newTransformationStep, NotificationChain msgs) {
		TransformationStep oldTransformationStep = transformationStep;
		transformationStep = newTransformationStep;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP, oldTransformationStep, newTransformationStep);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformationStep(TransformationStep newTransformationStep) {
		if (newTransformationStep != transformationStep) {
			NotificationChain msgs = null;
			if (transformationStep != null)
				msgs = ((InternalEObject)transformationStep).eInverseRemove(this, TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP, TransformationStep.class, msgs);
			if (newTransformationStep != null)
				msgs = ((InternalEObject)newTransformationStep).eInverseAdd(this, TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP, TransformationStep.class, msgs);
			msgs = basicSetTransformationStep(newTransformationStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP, newTransformationStep, newTransformationStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseActivity getWarehouseActivity() {
		if (eContainerFeatureID() != WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY) return null;
		return (WarehouseActivity)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWarehouseActivity(WarehouseActivity newWarehouseActivity, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWarehouseActivity, WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWarehouseActivity(WarehouseActivity newWarehouseActivity) {
		if (newWarehouseActivity != eInternalContainer() || (eContainerFeatureID() != WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY && newWarehouseActivity != null)) {
			if (EcoreUtil.isAncestor(this, newWarehouseActivity))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWarehouseActivity != null)
				msgs = ((InternalEObject)newWarehouseActivity).eInverseAdd(this, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP, WarehouseActivity.class, msgs);
			msgs = basicSetWarehouseActivity(newWarehouseActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY, newWarehouseActivity, newWarehouseActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				if (transformationStep != null)
					msgs = ((InternalEObject)transformationStep).eInverseRemove(this, TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP, TransformationStep.class, msgs);
				return basicSetTransformationStep((TransformationStep)otherEnd, msgs);
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWarehouseActivity((WarehouseActivity)otherEnd, msgs);
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
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				return basicSetTransformationStep(null, msgs);
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				return basicSetWarehouseActivity(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				return eInternalContainer().eInverseRemove(this, WarehouseprocessPackage.WAREHOUSE_ACTIVITY__WAREHOUSE_STEP, WarehouseActivity.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				if (resolve) return getTransformationStep();
				return basicGetTransformationStep();
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				return getWarehouseActivity();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				setTransformationStep((TransformationStep)newValue);
				return;
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				setWarehouseActivity((WarehouseActivity)newValue);
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
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				setTransformationStep((TransformationStep)null);
				return;
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				setWarehouseActivity((WarehouseActivity)null);
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
			case WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP:
				return transformationStep != null;
			case WarehouseprocessPackage.WAREHOUSE_STEP__WAREHOUSE_ACTIVITY:
				return getWarehouseActivity() != null;
		}
		return super.eIsSet(featureID);
	}

} //WarehouseStepImpl
