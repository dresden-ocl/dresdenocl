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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.transformation.ClassifierFeatureMap;
import orgomg.cwm.analysis.transformation.ClassifierMap;
import orgomg.cwm.analysis.transformation.FeatureMap;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

import orgomg.cwm.objectmodel.core.impl.NamespaceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl#getFeatureMap <em>Feature Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl#getCfMap <em>Cf Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassifierMapImpl extends NamespaceImpl implements ClassifierMap {
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
	 * The cached value of the '{@link #getFeatureMap() <em>Feature Map</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureMap()
	 * @generated
	 * @ordered
	 */
	protected EList<FeatureMap> featureMap;

	/**
	 * The cached value of the '{@link #getCfMap() <em>Cf Map</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCfMap()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassifierFeatureMap> cfMap;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected EList<Classifier> source;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TransformationPackage.Literals.CLASSIFIER_MAP;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TransformationPackage.CLASSIFIER_MAP__FUNCTION, oldFunction, newFunction);
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
				msgs = ((InternalEObject)function).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TransformationPackage.CLASSIFIER_MAP__FUNCTION, null, msgs);
			if (newFunction != null)
				msgs = ((InternalEObject)newFunction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TransformationPackage.CLASSIFIER_MAP__FUNCTION, null, msgs);
			msgs = basicSetFunction(newFunction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.CLASSIFIER_MAP__FUNCTION, newFunction, newFunction));
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
			eNotify(new ENotificationImpl(this, Notification.SET, TransformationPackage.CLASSIFIER_MAP__FUNCTION_DESCRIPTION, oldFunctionDescription, functionDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FeatureMap> getFeatureMap() {
		if (featureMap == null) {
			featureMap = new EObjectContainmentWithInverseEList<FeatureMap>(FeatureMap.class, this, TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP, TransformationPackage.FEATURE_MAP__CLASSIFIER_MAP);
		}
		return featureMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassifierFeatureMap> getCfMap() {
		if (cfMap == null) {
			cfMap = new EObjectContainmentWithInverseEList<ClassifierFeatureMap>(ClassifierFeatureMap.class, this, TransformationPackage.CLASSIFIER_MAP__CF_MAP, TransformationPackage.CLASSIFIER_FEATURE_MAP__CLASSIFIER_MAP);
		}
		return cfMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getSource() {
		if (source == null) {
			source = new EObjectWithInverseResolvingEList.ManyInverse<Classifier>(Classifier.class, this, TransformationPackage.CLASSIFIER_MAP__SOURCE, CorePackage.CLASSIFIER__CLASSIFIER_MAP);
		}
		return source;
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
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatureMap()).basicAdd(otherEnd, msgs);
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCfMap()).basicAdd(otherEnd, msgs);
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSource()).basicAdd(otherEnd, msgs);
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
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION:
				return basicSetFunction(null, msgs);
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				return ((InternalEList<?>)getFeatureMap()).basicRemove(otherEnd, msgs);
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				return ((InternalEList<?>)getCfMap()).basicRemove(otherEnd, msgs);
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				return ((InternalEList<?>)getSource()).basicRemove(otherEnd, msgs);
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
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION:
				return getFunction();
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION_DESCRIPTION:
				return getFunctionDescription();
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				return getFeatureMap();
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				return getCfMap();
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				return getSource();
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
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION:
				setFunction((ProcedureExpression)newValue);
				return;
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION_DESCRIPTION:
				setFunctionDescription((String)newValue);
				return;
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				getFeatureMap().clear();
				getFeatureMap().addAll((Collection<? extends FeatureMap>)newValue);
				return;
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				getCfMap().clear();
				getCfMap().addAll((Collection<? extends ClassifierFeatureMap>)newValue);
				return;
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				getSource().clear();
				getSource().addAll((Collection<? extends Classifier>)newValue);
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
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION:
				setFunction((ProcedureExpression)null);
				return;
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION_DESCRIPTION:
				setFunctionDescription(FUNCTION_DESCRIPTION_EDEFAULT);
				return;
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				getFeatureMap().clear();
				return;
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				getCfMap().clear();
				return;
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				getSource().clear();
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
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION:
				return function != null;
			case TransformationPackage.CLASSIFIER_MAP__FUNCTION_DESCRIPTION:
				return FUNCTION_DESCRIPTION_EDEFAULT == null ? functionDescription != null : !FUNCTION_DESCRIPTION_EDEFAULT.equals(functionDescription);
			case TransformationPackage.CLASSIFIER_MAP__FEATURE_MAP:
				return featureMap != null && !featureMap.isEmpty();
			case TransformationPackage.CLASSIFIER_MAP__CF_MAP:
				return cfMap != null && !cfMap.isEmpty();
			case TransformationPackage.CLASSIFIER_MAP__SOURCE:
				return source != null && !source.isEmpty();
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

} //ClassifierMapImpl
