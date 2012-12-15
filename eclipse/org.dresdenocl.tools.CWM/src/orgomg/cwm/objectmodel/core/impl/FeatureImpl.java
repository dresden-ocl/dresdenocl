/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core.impl;

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

import orgomg.cwm.analysis.transformation.ClassifierFeatureMap;
import orgomg.cwm.analysis.transformation.FeatureMap;
import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.foundation.expressions.ExpressionsPackage;
import orgomg.cwm.foundation.expressions.FeatureNode;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ScopeKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.FeatureImpl#getOwnerScope <em>Owner Scope</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.FeatureImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.FeatureImpl#getFeatureNode <em>Feature Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.FeatureImpl#getFeatureMap <em>Feature Map</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.impl.FeatureImpl#getCfMap <em>Cf Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FeatureImpl extends ModelElementImpl implements Feature {
	/**
	 * The default value of the '{@link #getOwnerScope() <em>Owner Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnerScope()
	 * @generated
	 * @ordered
	 */
	protected static final ScopeKind OWNER_SCOPE_EDEFAULT = ScopeKind.SK_INSTANCE;

	/**
	 * The cached value of the '{@link #getOwnerScope() <em>Owner Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnerScope()
	 * @generated
	 * @ordered
	 */
	protected ScopeKind ownerScope = OWNER_SCOPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFeatureNode() <em>Feature Node</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureNode()
	 * @generated
	 * @ordered
	 */
	protected EList<FeatureNode> featureNode;

	/**
	 * The cached value of the '{@link #getFeatureMap() <em>Feature Map</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatureMap()
	 * @generated
	 * @ordered
	 */
	protected EList<FeatureMap> featureMap;

	/**
	 * The cached value of the '{@link #getCfMap() <em>Cf Map</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCfMap()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassifierFeatureMap> cfMap;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeKind getOwnerScope() {
		return ownerScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwnerScope(ScopeKind newOwnerScope) {
		ScopeKind oldOwnerScope = ownerScope;
		ownerScope = newOwnerScope == null ? OWNER_SCOPE_EDEFAULT : newOwnerScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.FEATURE__OWNER_SCOPE, oldOwnerScope, ownerScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getOwner() {
		if (eContainerFeatureID() != CorePackage.FEATURE__OWNER) return null;
		return (Classifier)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(Classifier newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, CorePackage.FEATURE__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(Classifier newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != CorePackage.FEATURE__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, CorePackage.CLASSIFIER__FEATURE, Classifier.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.FEATURE__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FeatureNode> getFeatureNode() {
		if (featureNode == null) {
			featureNode = new EObjectWithInverseResolvingEList<FeatureNode>(FeatureNode.class, this, CorePackage.FEATURE__FEATURE_NODE, ExpressionsPackage.FEATURE_NODE__FEATURE);
		}
		return featureNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FeatureMap> getFeatureMap() {
		if (featureMap == null) {
			featureMap = new EObjectWithInverseResolvingEList.ManyInverse<FeatureMap>(FeatureMap.class, this, CorePackage.FEATURE__FEATURE_MAP, TransformationPackage.FEATURE_MAP__TARGET);
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
			cfMap = new EObjectWithInverseResolvingEList.ManyInverse<ClassifierFeatureMap>(ClassifierFeatureMap.class, this, CorePackage.FEATURE__CF_MAP, TransformationPackage.CLASSIFIER_FEATURE_MAP__FEATURE);
		}
		return cfMap;
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
			case CorePackage.FEATURE__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((Classifier)otherEnd, msgs);
			case CorePackage.FEATURE__FEATURE_NODE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatureNode()).basicAdd(otherEnd, msgs);
			case CorePackage.FEATURE__FEATURE_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeatureMap()).basicAdd(otherEnd, msgs);
			case CorePackage.FEATURE__CF_MAP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCfMap()).basicAdd(otherEnd, msgs);
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
			case CorePackage.FEATURE__OWNER:
				return basicSetOwner(null, msgs);
			case CorePackage.FEATURE__FEATURE_NODE:
				return ((InternalEList<?>)getFeatureNode()).basicRemove(otherEnd, msgs);
			case CorePackage.FEATURE__FEATURE_MAP:
				return ((InternalEList<?>)getFeatureMap()).basicRemove(otherEnd, msgs);
			case CorePackage.FEATURE__CF_MAP:
				return ((InternalEList<?>)getCfMap()).basicRemove(otherEnd, msgs);
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
			case CorePackage.FEATURE__OWNER:
				return eInternalContainer().eInverseRemove(this, CorePackage.CLASSIFIER__FEATURE, Classifier.class, msgs);
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
			case CorePackage.FEATURE__OWNER_SCOPE:
				return getOwnerScope();
			case CorePackage.FEATURE__OWNER:
				return getOwner();
			case CorePackage.FEATURE__FEATURE_NODE:
				return getFeatureNode();
			case CorePackage.FEATURE__FEATURE_MAP:
				return getFeatureMap();
			case CorePackage.FEATURE__CF_MAP:
				return getCfMap();
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
			case CorePackage.FEATURE__OWNER_SCOPE:
				setOwnerScope((ScopeKind)newValue);
				return;
			case CorePackage.FEATURE__OWNER:
				setOwner((Classifier)newValue);
				return;
			case CorePackage.FEATURE__FEATURE_NODE:
				getFeatureNode().clear();
				getFeatureNode().addAll((Collection<? extends FeatureNode>)newValue);
				return;
			case CorePackage.FEATURE__FEATURE_MAP:
				getFeatureMap().clear();
				getFeatureMap().addAll((Collection<? extends FeatureMap>)newValue);
				return;
			case CorePackage.FEATURE__CF_MAP:
				getCfMap().clear();
				getCfMap().addAll((Collection<? extends ClassifierFeatureMap>)newValue);
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
			case CorePackage.FEATURE__OWNER_SCOPE:
				setOwnerScope(OWNER_SCOPE_EDEFAULT);
				return;
			case CorePackage.FEATURE__OWNER:
				setOwner((Classifier)null);
				return;
			case CorePackage.FEATURE__FEATURE_NODE:
				getFeatureNode().clear();
				return;
			case CorePackage.FEATURE__FEATURE_MAP:
				getFeatureMap().clear();
				return;
			case CorePackage.FEATURE__CF_MAP:
				getCfMap().clear();
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
			case CorePackage.FEATURE__OWNER_SCOPE:
				return ownerScope != OWNER_SCOPE_EDEFAULT;
			case CorePackage.FEATURE__OWNER:
				return getOwner() != null;
			case CorePackage.FEATURE__FEATURE_NODE:
				return featureNode != null && !featureNode.isEmpty();
			case CorePackage.FEATURE__FEATURE_MAP:
				return featureMap != null && !featureMap.isEmpty();
			case CorePackage.FEATURE__CF_MAP:
				return cfMap != null && !cfMap.isEmpty();
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
		result.append(" (ownerScope: ");
		result.append(ownerScope);
		result.append(')');
		return result.toString();
	}

} //FeatureImpl
