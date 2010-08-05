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

import orgomg.cwm.analysis.transformation.TransformationPackage;
import orgomg.cwm.analysis.transformation.TransformationStep;
import orgomg.cwm.analysis.transformation.TransformationTask;

import orgomg.cwm.management.warehouseoperation.StepExecution;
import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.management.warehouseprocess.WarehouseStep;
import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationStepImpl#getTask <em>Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationStepImpl#getWarehouseStep <em>Warehouse Step</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationStepImpl#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationStepImpl extends ModelElementImpl implements TransformationStep {
	/**
	 * The cached value of the '{@link #getTask() <em>Task</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTask()
	 * @generated
	 * @ordered
	 */
	protected TransformationTask task;

	/**
	 * The cached value of the '{@link #getWarehouseStep() <em>Warehouse Step</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWarehouseStep()
	 * @generated
	 * @ordered
	 */
	protected EList<WarehouseStep> warehouseStep;

	/**
	 * The cached value of the '{@link #getExecution() <em>Execution</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecution()
	 * @generated
	 * @ordered
	 */
	protected EList<StepExecution> execution;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.TRANSFORMATION_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationTask getTask() {
		if (task != null && task.eIsProxy()) {
			InternalEObject oldTask = (InternalEObject)task;
			task = (TransformationTask)eResolveProxy(oldTask);
			if (task != oldTask) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransformationPackage.TRANSFORMATION_STEP__TASK, oldTask, task));
			}
		}
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationTask basicGetTask() {
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTask(TransformationTask newTask, NotificationChain msgs) {
		TransformationTask oldTask = task;
		task = newTask;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TransformationPackage.TRANSFORMATION_STEP__TASK, oldTask, newTask);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTask(TransformationTask newTask) {
		if (newTask != task) {
			NotificationChain msgs = null;
			if (task != null)
				msgs = ((InternalEObject)task).eInverseRemove(this, TransformationPackage.TRANSFORMATION_TASK__STEP, TransformationTask.class, msgs);
			if (newTask != null)
				msgs = ((InternalEObject)newTask).eInverseAdd(this, TransformationPackage.TRANSFORMATION_TASK__STEP, TransformationTask.class, msgs);
			msgs = basicSetTask(newTask, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.TRANSFORMATION_STEP__TASK, newTask, newTask));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WarehouseStep> getWarehouseStep() {
		if (warehouseStep == null) {
			warehouseStep = new EObjectWithInverseResolvingEList<WarehouseStep>(WarehouseStep.class, this, TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP, WarehouseprocessPackage.WAREHOUSE_STEP__TRANSFORMATION_STEP);
		}
		return warehouseStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StepExecution> getExecution() {
		if (execution == null) {
			execution = new EObjectWithInverseResolvingEList<StepExecution>(StepExecution.class, this, TransformationPackage.TRANSFORMATION_STEP__EXECUTION, WarehouseoperationPackage.STEP_EXECUTION__TRANSFORMATION_STEP);
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				if (task != null)
					msgs = ((InternalEObject)task).eInverseRemove(this, TransformationPackage.TRANSFORMATION_TASK__STEP, TransformationTask.class, msgs);
				return basicSetTask((TransformationTask)otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWarehouseStep()).basicAdd(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				return basicSetTask(null, msgs);
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				return ((InternalEList<?>)getWarehouseStep()).basicRemove(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				if (resolve) return getTask();
				return basicGetTask();
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				return getWarehouseStep();
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				setTask((TransformationTask)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				getWarehouseStep().clear();
				getWarehouseStep().addAll((Collection<? extends WarehouseStep>)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
				getExecution().clear();
				getExecution().addAll((Collection<? extends StepExecution>)newValue);
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				setTask((TransformationTask)null);
				return;
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				getWarehouseStep().clear();
				return;
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
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
			case TransformationPackage.TRANSFORMATION_STEP__TASK:
				return task != null;
			case TransformationPackage.TRANSFORMATION_STEP__WAREHOUSE_STEP:
				return warehouseStep != null && !warehouseStep.isEmpty();
			case TransformationPackage.TRANSFORMATION_STEP__EXECUTION:
				return execution != null && !execution.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransformationStepImpl
