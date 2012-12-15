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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.ClassifierMap;
import orgomg.cwm.analysis.transformation.FeatureMap;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl#getClassifierMap <em>Classifier Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureMapImpl extends ModelElementImpl implements FeatureMap {
	/**
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
	protected ProcedureExpression function;

	/**
	 * The default value of the '{@link #getFunctionDescription() <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTION_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunctionDescription() <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionDescription()
	 * @generated
	 * @ordered
	 */
	protected String functionDescription = FUNCTION_DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.FEATURE_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcedureExpression getFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunction(ProcedureExpression newFunction, NotificationChain msgs) {
		ProcedureExpression oldFunction = function;
		function = newFunction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TransformationPackage.FEATURE_MAP__FUNCTION, oldFunction, newFunction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunction(ProcedureExpression newFunction) {
		if (newFunction != function) {
			NotificationChain msgs = null;
			if (function != null)
				msgs = ((InternalEObject)function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TransformationPackage.FEATURE_MAP__FUNCTION, null, msgs);
			if (newFunction != null)
				msgs = ((InternalEObject)newFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TransformationPackage.FEATURE_MAP__FUNCTION, null, msgs);
			msgs = basicSetFunction(newFunction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.FEATURE_MAP__FUNCTION, newFunction, newFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFunctionDescription() {
		return functionDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionDescription(String newFunctionDescription) {
		String oldFunctionDescription = functionDescription;
		functionDescription = newFunctionDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.FEATURE_MAP__FUNCTION_DESCRIPTION, oldFunctionDescription, functionDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassifierMap getClassifierMap() {
		if (eContainerFeatureID() != TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP) return null;
		return (ClassifierMap)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClassifierMap(ClassifierMap newClassifierMap, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClassifierMap, TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassifierMap(ClassifierMap newClassifierMap) {
		if (newClassifierMap != eInternalContainer() || (eContainerFeatureID() != TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP && newClassifierMap != null)) {
			if (EcoreUtil.isAncestor(this, newClassifierMap))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClassifierMap != null)
				msgs = ((InternalEObject)newClassifierMap).eInverseAdd(this, TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP, ClassifierMap.class, msgs);
			msgs = basicSetClassifierMap(newClassifierMap, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP, newClassifierMap, newClassifierMap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getTarget() {
		if (target == null) {
			target = new EObjectWithInverseResolvingEList.ManyInverse<Feature>(Feature.class, this, TransformationPackage.FEATURE_MAP__TARGET, CorePackage.FEATURE__FEATURE_MAP);
		}
		return target;
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
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClassifierMap((ClassifierMap)otherEnd, msgs);
			case TransformationPackage.FEATURE_MAP__TARGET:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTarget()).basicAdd(otherEnd, msgs);
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
			case TransformationPackage.FEATURE_MAP__FUNCTION:
				return basicSetFunction(null, msgs);
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				return basicSetClassifierMap(null, msgs);
			case TransformationPackage.FEATURE_MAP__TARGET:
				return ((InternalEList<?>)getTarget()).basicRemove(otherEnd, msgs);
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
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				return eInternalContainer().eInverseRemove(this, TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP, ClassifierMap.class, msgs);
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
			case TransformationPackage.FEATURE_MAP__FUNCTION:
				return getFunction();
			case TransformationPackage.FEATURE_MAP__FUNCTION_DESCRIPTION:
				return getFunctionDescription();
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				return getClassifierMap();
			case TransformationPackage.FEATURE_MAP__TARGET:
				return getTarget();
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
			case TransformationPackage.FEATURE_MAP__FUNCTION:
				setFunction((ProcedureExpression)newValue);
				return;
			case TransformationPackage.FEATURE_MAP__FUNCTION_DESCRIPTION:
				setFunctionDescription((String)newValue);
				return;
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				setClassifierMap((ClassifierMap)newValue);
				return;
			case TransformationPackage.FEATURE_MAP__TARGET:
				getTarget().clear();
				getTarget().addAll((Collection<? extends Feature>)newValue);
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
			case TransformationPackage.FEATURE_MAP__FUNCTION:
				setFunction((ProcedureExpression)null);
				return;
			case TransformationPackage.FEATURE_MAP__FUNCTION_DESCRIPTION:
				setFunctionDescription(FUNCTION_DESCRIPTION_EDEFAULT);
				return;
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				setClassifierMap((ClassifierMap)null);
				return;
			case TransformationPackage.FEATURE_MAP__TARGET:
				getTarget().clear();
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
			case TransformationPackage.FEATURE_MAP__FUNCTION:
				return function != null;
			case TransformationPackage.FEATURE_MAP__FUNCTION_DESCRIPTION:
				return FUNCTION_DESCRIPTION_EDEFAULT == null ? functionDescription != null : !FUNCTION_DESCRIPTION_EDEFAULT.equals(functionDescription);
			case TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP:
				return getClassifierMap() != null;
			case TransformationPackage.FEATURE_MAP__TARGET:
				return target != null && !target.isEmpty();
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
		result.append(" (functionDescription: ");
		result.append(functionDescription);
		result.append(')');
		return result.toString();
	}

} //FeatureMapImpl
