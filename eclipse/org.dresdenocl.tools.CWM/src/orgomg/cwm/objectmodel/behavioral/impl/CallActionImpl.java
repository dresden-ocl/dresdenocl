/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.management.warehouseoperation.StepExecution;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.objectmodel.behavioral.Argument;
import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;
import orgomg.cwm.objectmodel.behavioral.CallAction;
import orgomg.cwm.objectmodel.behavioral.Operation;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Call Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.CallActionImpl#getActualArgument <em>Actual Argument</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.CallActionImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.CallActionImpl#getStepExecution <em>Step Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallActionImpl extends ModelElementImpl implements CallAction {
	/**
	 * The cached value of the '{@link #getActualArgument() <em>Actual Argument</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualArgument()
	 * @generated
	 * @ordered
	 */
	protected EList<Argument> actualArgument;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * The cached value of the '{@link #getStepExecution() <em>Step Execution</em>}' reference list.
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
	protected CallActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehavioralPackage.Literals.CALL_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Argument> getActualArgument() {
		if (actualArgument == null) {
			actualArgument = new EObjectContainmentWithInverseEList<Argument>(Argument.class, this, BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT, BehavioralPackage.ARGUMENT__CALL_ACTION);
		}
		return actualArgument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {
		if (operation != null && operation.eIsProxy()) {
			InternalEObject oldOperation = (InternalEObject)operation;
			operation = (Operation)eResolveProxy(oldOperation);
			if (operation != oldOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralPackage.CALL_ACTION__OPERATION, oldOperation, operation));
			}
		}
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(Operation newOperation, NotificationChain msgs) {
		Operation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehavioralPackage.CALL_ACTION__OPERATION, oldOperation, newOperation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {
		if (newOperation != operation) {
			NotificationChain msgs = null;
			if (operation != null)
				msgs = ((InternalEObject)operation).eInverseRemove(this, BehavioralPackage.OPERATION__CALL_ACTION, Operation.class, msgs);
			if (newOperation != null)
				msgs = ((InternalEObject)newOperation).eInverseAdd(this, BehavioralPackage.OPERATION__CALL_ACTION, Operation.class, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.CALL_ACTION__OPERATION, newOperation, newOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StepExecution> getStepExecution() {
		if (stepExecution == null) {
			stepExecution = new EObjectWithInverseResolvingEList<StepExecution>(StepExecution.class, this, BehavioralPackage.CALL_ACTION__STEP_EXECUTION, WarehouseoperationPackage.STEP_EXECUTION__CALL_ACTION);
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getActualArgument()).basicAdd(otherEnd, msgs);
			case BehavioralPackage.CALL_ACTION__OPERATION:
				if (operation != null)
					msgs = ((InternalEObject)operation).eInverseRemove(this, BehavioralPackage.OPERATION__CALL_ACTION, Operation.class, msgs);
				return basicSetOperation((Operation)otherEnd, msgs);
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				return ((InternalEList<?>)getActualArgument()).basicRemove(otherEnd, msgs);
			case BehavioralPackage.CALL_ACTION__OPERATION:
				return basicSetOperation(null, msgs);
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				return getActualArgument();
			case BehavioralPackage.CALL_ACTION__OPERATION:
				if (resolve) return getOperation();
				return basicGetOperation();
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				getActualArgument().clear();
				getActualArgument().addAll((Collection<? extends Argument>)newValue);
				return;
			case BehavioralPackage.CALL_ACTION__OPERATION:
				setOperation((Operation)newValue);
				return;
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				getActualArgument().clear();
				return;
			case BehavioralPackage.CALL_ACTION__OPERATION:
				setOperation((Operation)null);
				return;
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
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
			case BehavioralPackage.CALL_ACTION__ACTUAL_ARGUMENT:
				return actualArgument != null && !actualArgument.isEmpty();
			case BehavioralPackage.CALL_ACTION__OPERATION:
				return operation != null;
			case BehavioralPackage.CALL_ACTION__STEP_EXECUTION:
				return stepExecution != null && !stepExecution.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CallActionImpl
