/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.Transformation;
import orgomg.cwm.analysis.transformation.TransformationPackage;
import orgomg.cwm.analysis.transformation.TransformationStep;
import orgomg.cwm.analysis.transformation.TransformationTask;

import orgomg.cwm.foundation.softwaredeployment.impl.ComponentImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl#getStep <em>Step</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl#getOriginalTask <em>Original Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl#getInverseTask <em>Inverse Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl#getTransformation <em>Transformation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationTaskImpl extends ComponentImpl implements TransformationTask {
	/**
	 * The cached value of the '{@link #getStep() <em>Step</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStep()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationStep> step;

	/**
	 * The cached value of the '{@link #getOriginalTask() <em>Original Task</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalTask()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationTask> originalTask;

	/**
	 * The cached value of the '{@link #getInverseTask() <em>Inverse Task</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverseTask()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationTask> inverseTask;

	/**
	 * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformation()
	 * @generated
	 * @ordered
	 */
	protected EList<Transformation> transformation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationTaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.TRANSFORMATION_TASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationStep> getStep() {
		if (step == null) {
			step = new EObjectWithInverseResolvingEList<TransformationStep>(TransformationStep.class, this, TransformationPackage.TRANSFORMATION_TASK__STEP, TransformationPackage.TRANSFORMATION_STEP__TASK);
		}
		return step;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationTask> getOriginalTask() {
		if (originalTask == null) {
			originalTask = new EObjectWithInverseResolvingEList.ManyInverse<TransformationTask>(TransformationTask.class, this, TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK, TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK);
		}
		return originalTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationTask> getInverseTask() {
		if (inverseTask == null) {
			inverseTask = new EObjectWithInverseResolvingEList.ManyInverse<TransformationTask>(TransformationTask.class, this, TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK, TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK);
		}
		return inverseTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transformation> getTransformation() {
		if (transformation == null) {
			transformation = new EObjectWithInverseResolvingEList.ManyInverse<Transformation>(Transformation.class, this, TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION, TransformationPackage.TRANSFORMATION__TASK);
		}
		return transformation;
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStep()).basicAdd(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOriginalTask()).basicAdd(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInverseTask()).basicAdd(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTransformation()).basicAdd(otherEnd, msgs);
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				return ((InternalEList<?>)getStep()).basicRemove(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				return ((InternalEList<?>)getOriginalTask()).basicRemove(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				return ((InternalEList<?>)getInverseTask()).basicRemove(otherEnd, msgs);
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				return ((InternalEList<?>)getTransformation()).basicRemove(otherEnd, msgs);
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				return getStep();
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				return getOriginalTask();
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				return getInverseTask();
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				return getTransformation();
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				getStep().clear();
				getStep().addAll((Collection<? extends TransformationStep>)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				getOriginalTask().clear();
				getOriginalTask().addAll((Collection<? extends TransformationTask>)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				getInverseTask().clear();
				getInverseTask().addAll((Collection<? extends TransformationTask>)newValue);
				return;
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				getTransformation().clear();
				getTransformation().addAll((Collection<? extends Transformation>)newValue);
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				getStep().clear();
				return;
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				getOriginalTask().clear();
				return;
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				getInverseTask().clear();
				return;
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				getTransformation().clear();
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
			case TransformationPackage.TRANSFORMATION_TASK__STEP:
				return step != null && !step.isEmpty();
			case TransformationPackage.TRANSFORMATION_TASK__ORIGINAL_TASK:
				return originalTask != null && !originalTask.isEmpty();
			case TransformationPackage.TRANSFORMATION_TASK__INVERSE_TASK:
				return inverseTask != null && !inverseTask.isEmpty();
			case TransformationPackage.TRANSFORMATION_TASK__TRANSFORMATION:
				return transformation != null && !transformation.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransformationTaskImpl
