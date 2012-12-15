/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.analysis.transformation.TransformationPackage;
import orgomg.cwm.analysis.transformation.TransformationStep;

import orgomg.cwm.management.warehouseoperation.ActivityExecution;
import orgomg.cwm.management.warehouseoperation.StepExecution;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;
import orgomg.cwm.objectmodel.behavioral.CallAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.StepExecutionImpl#getTransformationStep <em>Transformation Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.StepExecutionImpl#getActivityExecution <em>Activity Execution</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.impl.StepExecutionImpl#getCallAction <em>Call Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StepExecutionImpl extends TransformationExecutionImpl implements StepExecution {
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
	 * The cached value of the '{@link #getCallAction() <em>Call Action</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallAction()
	 * @generated
	 * @ordered
	 */
	protected CallAction callAction;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StepExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WarehouseoperationPackage.Literals.STEP_EXECUTION;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP, oldTransformationStep, transformationStep));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP, oldTransformationStep, newTransformationStep);
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
				msgs = ((InternalEObject)transformationStep).eInverseRemove(this, TransformationPackage.TRANSFORMATION_STEP__EXECUTION, TransformationStep.class, msgs);
			if (newTransformationStep != null)
				msgs = ((InternalEObject)newTransformationStep).eInverseAdd(this, TransformationPackage.TRANSFORMATION_STEP__EXECUTION, TransformationStep.class, msgs);
			msgs = basicSetTransformationStep(newTransformationStep, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP, newTransformationStep, newTransformationStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution getActivityExecution() {
		if (eContainerFeatureID() != WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION) return null;
		return (ActivityExecution)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivityExecution(ActivityExecution newActivityExecution, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newActivityExecution, WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivityExecution(ActivityExecution newActivityExecution) {
		if (newActivityExecution != eInternalContainer() || (eContainerFeatureID() != WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION && newActivityExecution != null)) {
			if (EcoreUtil.isAncestor(this, newActivityExecution))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newActivityExecution != null)
				msgs = ((InternalEObject)newActivityExecution).eInverseAdd(this, WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION, ActivityExecution.class, msgs);
			msgs = basicSetActivityExecution(newActivityExecution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION, newActivityExecution, newActivityExecution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallAction getCallAction() {
		if (callAction != null && callAction.eIsProxy()) {
			InternalEObject oldCallAction = (InternalEObject)callAction;
			callAction = (CallAction)eResolveProxy(oldCallAction);
			if (callAction != oldCallAction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION, oldCallAction, callAction));
			}
		}
		return callAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallAction basicGetCallAction() {
		return callAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallAction(CallAction newCallAction, NotificationChain msgs) {
		CallAction oldCallAction = callAction;
		callAction = newCallAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION, oldCallAction, newCallAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallAction(CallAction newCallAction) {
		if (newCallAction != callAction) {
			NotificationChain msgs = null;
			if (callAction != null)
				msgs = ((InternalEObject)callAction).eInverseRemove(this, BehavioralPackage.CALL_ACTION__STEP_EXECUTION, CallAction.class, msgs);
			if (newCallAction != null)
				msgs = ((InternalEObject)newCallAction).eInverseAdd(this, BehavioralPackage.CALL_ACTION__STEP_EXECUTION, CallAction.class, msgs);
			msgs = basicSetCallAction(newCallAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION, newCallAction, newCallAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				if (transformationStep != null)
					msgs = ((InternalEObject)transformationStep).eInverseRemove(this, TransformationPackage.TRANSFORMATION_STEP__EXECUTION, TransformationStep.class, msgs);
				return basicSetTransformationStep((TransformationStep)otherEnd, msgs);
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActivityExecution((ActivityExecution)otherEnd, msgs);
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				if (callAction != null)
					msgs = ((InternalEObject)callAction).eInverseRemove(this, BehavioralPackage.CALL_ACTION__STEP_EXECUTION, CallAction.class, msgs);
				return basicSetCallAction((CallAction)otherEnd, msgs);
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
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				return basicSetTransformationStep(null, msgs);
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				return basicSetActivityExecution(null, msgs);
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				return basicSetCallAction(null, msgs);
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
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				return eInternalContainer().eInverseRemove(this, WarehouseoperationPackage.ACTIVITY_EXECUTION__STEP_EXECUTION, ActivityExecution.class, msgs);
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
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				if (resolve) return getTransformationStep();
				return basicGetTransformationStep();
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution();
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				if (resolve) return getCallAction();
				return basicGetCallAction();
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
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				setTransformationStep((TransformationStep)newValue);
				return;
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)newValue);
				return;
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				setCallAction((CallAction)newValue);
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
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				setTransformationStep((TransformationStep)null);
				return;
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				setActivityExecution((ActivityExecution)null);
				return;
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				setCallAction((CallAction)null);
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
			case WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP:
				return transformationStep != null;
			case WarehouseoperationPackage.STEP_EXECUTION__ACTIVITY_EXECUTION:
				return getActivityExecution() != null;
			case WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION:
				return callAction != null;
		}
		return super.eIsSet(featureID);
	}

} //StepExecutionImpl
