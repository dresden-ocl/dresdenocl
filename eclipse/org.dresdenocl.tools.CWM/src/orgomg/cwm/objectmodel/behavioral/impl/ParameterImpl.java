/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.objectmodel.behavioral.BehavioralFeature;
import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;
import orgomg.cwm.objectmodel.behavioral.Event;
import orgomg.cwm.objectmodel.behavioral.Parameter;
import orgomg.cwm.objectmodel.behavioral.ParameterDirectionKind;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.Expression;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.ParameterImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.ParameterImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.ParameterImpl#getBehavioralFeature <em>Behavioral Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.ParameterImpl#getEvent <em>Event</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.impl.ParameterImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends ModelElementImpl implements Parameter {
	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected Expression defaultValue;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterDirectionKind KIND_EDEFAULT = ParameterDirectionKind.PDK_IN;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected ParameterDirectionKind kind = KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Classifier type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BehavioralPackage.Literals.PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefaultValue(Expression newDefaultValue, NotificationChain msgs) {
		Expression oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__DEFAULT_VALUE, oldDefaultValue, newDefaultValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(Expression newDefaultValue) {
		if (newDefaultValue != defaultValue) {
			NotificationChain msgs = null;
			if (defaultValue != null)
				msgs = ((InternalEObject)defaultValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - BehavioralPackage.PARAMETER__DEFAULT_VALUE, null, msgs);
			if (newDefaultValue != null)
				msgs = ((InternalEObject)newDefaultValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - BehavioralPackage.PARAMETER__DEFAULT_VALUE, null, msgs);
			msgs = basicSetDefaultValue(newDefaultValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__DEFAULT_VALUE, newDefaultValue, newDefaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDirectionKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(ParameterDirectionKind newKind) {
		ParameterDirectionKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BehavioralFeature getBehavioralFeature() {
		if (eContainerFeatureID() != BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE) return null;
		return (BehavioralFeature)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBehavioralFeature(BehavioralFeature newBehavioralFeature, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBehavioralFeature, BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBehavioralFeature(BehavioralFeature newBehavioralFeature) {
		if (newBehavioralFeature != eInternalContainer() || (eContainerFeatureID() != BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE && newBehavioralFeature != null)) {
			if (EcoreUtil.isAncestor(this, newBehavioralFeature))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBehavioralFeature != null)
				msgs = ((InternalEObject)newBehavioralFeature).eInverseAdd(this, BehavioralPackage.BEHAVIORAL_FEATURE__PARAMETER, BehavioralFeature.class, msgs);
			msgs = basicSetBehavioralFeature(newBehavioralFeature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE, newBehavioralFeature, newBehavioralFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getEvent() {
		if (eContainerFeatureID() != BehavioralPackage.PARAMETER__EVENT) return null;
		return (Event)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent(Event newEvent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEvent, BehavioralPackage.PARAMETER__EVENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(Event newEvent) {
		if (newEvent != eInternalContainer() || (eContainerFeatureID() != BehavioralPackage.PARAMETER__EVENT && newEvent != null)) {
			if (EcoreUtil.isAncestor(this, newEvent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEvent != null)
				msgs = ((InternalEObject)newEvent).eInverseAdd(this, BehavioralPackage.EVENT__PARAMETER, Event.class, msgs);
			msgs = basicSetEvent(newEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__EVENT, newEvent, newEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Classifier)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, BehavioralPackage.PARAMETER__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(Classifier newType, NotificationChain msgs) {
		Classifier oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Classifier newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__PARAMETER, Classifier.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CorePackage.CLASSIFIER__PARAMETER, Classifier.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BehavioralPackage.PARAMETER__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBehavioralFeature((BehavioralFeature)otherEnd, msgs);
			case BehavioralPackage.PARAMETER__EVENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEvent((Event)otherEnd, msgs);
			case BehavioralPackage.PARAMETER__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__PARAMETER, Classifier.class, msgs);
				return basicSetType((Classifier)otherEnd, msgs);
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
			case BehavioralPackage.PARAMETER__DEFAULT_VALUE:
				return basicSetDefaultValue(null, msgs);
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				return basicSetBehavioralFeature(null, msgs);
			case BehavioralPackage.PARAMETER__EVENT:
				return basicSetEvent(null, msgs);
			case BehavioralPackage.PARAMETER__TYPE:
				return basicSetType(null, msgs);
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
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				return eInternalContainer().eInverseRemove(this, BehavioralPackage.BEHAVIORAL_FEATURE__PARAMETER, BehavioralFeature.class, msgs);
			case BehavioralPackage.PARAMETER__EVENT:
				return eInternalContainer().eInverseRemove(this, BehavioralPackage.EVENT__PARAMETER, Event.class, msgs);
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
			case BehavioralPackage.PARAMETER__DEFAULT_VALUE:
				return getDefaultValue();
			case BehavioralPackage.PARAMETER__KIND:
				return getKind();
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				return getBehavioralFeature();
			case BehavioralPackage.PARAMETER__EVENT:
				return getEvent();
			case BehavioralPackage.PARAMETER__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case BehavioralPackage.PARAMETER__DEFAULT_VALUE:
				setDefaultValue((Expression)newValue);
				return;
			case BehavioralPackage.PARAMETER__KIND:
				setKind((ParameterDirectionKind)newValue);
				return;
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				setBehavioralFeature((BehavioralFeature)newValue);
				return;
			case BehavioralPackage.PARAMETER__EVENT:
				setEvent((Event)newValue);
				return;
			case BehavioralPackage.PARAMETER__TYPE:
				setType((Classifier)newValue);
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
			case BehavioralPackage.PARAMETER__DEFAULT_VALUE:
				setDefaultValue((Expression)null);
				return;
			case BehavioralPackage.PARAMETER__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				setBehavioralFeature((BehavioralFeature)null);
				return;
			case BehavioralPackage.PARAMETER__EVENT:
				setEvent((Event)null);
				return;
			case BehavioralPackage.PARAMETER__TYPE:
				setType((Classifier)null);
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
			case BehavioralPackage.PARAMETER__DEFAULT_VALUE:
				return defaultValue != null;
			case BehavioralPackage.PARAMETER__KIND:
				return kind != KIND_EDEFAULT;
			case BehavioralPackage.PARAMETER__BEHAVIORAL_FEATURE:
				return getBehavioralFeature() != null;
			case BehavioralPackage.PARAMETER__EVENT:
				return getEvent() != null;
			case BehavioralPackage.PARAMETER__TYPE:
				return type != null;
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(')');
		return result.toString();
	}

} //ParameterImpl
