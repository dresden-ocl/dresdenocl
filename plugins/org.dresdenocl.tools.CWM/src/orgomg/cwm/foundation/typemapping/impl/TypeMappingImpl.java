/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.typemapping.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.foundation.typemapping.TypeMapping;
import orgomg.cwm.foundation.typemapping.TypemappingPackage;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.typemapping.impl.TypeMappingImpl#isIsBestMatch <em>Is Best Match</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.impl.TypeMappingImpl#isIsLossy <em>Is Lossy</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.impl.TypeMappingImpl#getSourceType <em>Source Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.typemapping.impl.TypeMappingImpl#getTargetType <em>Target Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeMappingImpl extends ModelElementImpl implements TypeMapping {
	/**
	 * The default value of the '{@link #isIsBestMatch() <em>Is Best Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsBestMatch()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_BEST_MATCH_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsBestMatch() <em>Is Best Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsBestMatch()
	 * @generated
	 * @ordered
	 */
	protected boolean isBestMatch = IS_BEST_MATCH_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsLossy() <em>Is Lossy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsLossy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_LOSSY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsLossy() <em>Is Lossy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsLossy()
	 * @generated
	 * @ordered
	 */
	protected boolean isLossy = IS_LOSSY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceType() <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceType()
	 * @generated
	 * @ordered
	 */
	protected Classifier sourceType;

	/**
	 * The cached value of the '{@link #getTargetType() <em>Target Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetType()
	 * @generated
	 * @ordered
	 */
	protected Classifier targetType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypemappingPackage.Literals.TYPE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsBestMatch() {
		return isBestMatch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsBestMatch(boolean newIsBestMatch) {
		boolean oldIsBestMatch = isBestMatch;
		isBestMatch = newIsBestMatch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__IS_BEST_MATCH, oldIsBestMatch, isBestMatch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsLossy() {
		return isLossy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsLossy(boolean newIsLossy) {
		boolean oldIsLossy = isLossy;
		isLossy = newIsLossy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__IS_LOSSY, oldIsLossy, isLossy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getSourceType() {
		if (sourceType != null && sourceType.eIsProxy()) {
			InternalEObject oldSourceType = (InternalEObject)sourceType;
			sourceType = (Classifier)eResolveProxy(oldSourceType);
			if (sourceType != oldSourceType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE, oldSourceType, sourceType));
			}
		}
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetSourceType() {
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourceType(Classifier newSourceType, NotificationChain msgs) {
		Classifier oldSourceType = sourceType;
		sourceType = newSourceType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE, oldSourceType, newSourceType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceType(Classifier newSourceType) {
		if (newSourceType != sourceType) {
			NotificationChain msgs = null;
			if (sourceType != null)
				msgs = ((InternalEObject)sourceType).eInverseRemove(this, CorePackage.CLASSIFIER__MAPPING_FROM, Classifier.class, msgs);
			if (newSourceType != null)
				msgs = ((InternalEObject)newSourceType).eInverseAdd(this, CorePackage.CLASSIFIER__MAPPING_FROM, Classifier.class, msgs);
			msgs = basicSetSourceType(newSourceType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE, newSourceType, newSourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier getTargetType() {
		if (targetType != null && targetType.eIsProxy()) {
			InternalEObject oldTargetType = (InternalEObject)targetType;
			targetType = (Classifier)eResolveProxy(oldTargetType);
			if (targetType != oldTargetType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypemappingPackage.TYPE_MAPPING__TARGET_TYPE, oldTargetType, targetType));
			}
		}
		return targetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Classifier basicGetTargetType() {
		return targetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetType(Classifier newTargetType, NotificationChain msgs) {
		Classifier oldTargetType = targetType;
		targetType = newTargetType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__TARGET_TYPE, oldTargetType, newTargetType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetType(Classifier newTargetType) {
		if (newTargetType != targetType) {
			NotificationChain msgs = null;
			if (targetType != null)
				msgs = ((InternalEObject)targetType).eInverseRemove(this, CorePackage.CLASSIFIER__MAPPING_TO, Classifier.class, msgs);
			if (newTargetType != null)
				msgs = ((InternalEObject)newTargetType).eInverseAdd(this, CorePackage.CLASSIFIER__MAPPING_TO, Classifier.class, msgs);
			msgs = basicSetTargetType(newTargetType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypemappingPackage.TYPE_MAPPING__TARGET_TYPE, newTargetType, newTargetType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				if (sourceType != null)
					msgs = ((InternalEObject)sourceType).eInverseRemove(this, CorePackage.CLASSIFIER__MAPPING_FROM, Classifier.class, msgs);
				return basicSetSourceType((Classifier)otherEnd, msgs);
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				if (targetType != null)
					msgs = ((InternalEObject)targetType).eInverseRemove(this, CorePackage.CLASSIFIER__MAPPING_TO, Classifier.class, msgs);
				return basicSetTargetType((Classifier)otherEnd, msgs);
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
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				return basicSetSourceType(null, msgs);
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				return basicSetTargetType(null, msgs);
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
			case TypemappingPackage.TYPE_MAPPING__IS_BEST_MATCH:
				return isIsBestMatch();
			case TypemappingPackage.TYPE_MAPPING__IS_LOSSY:
				return isIsLossy();
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				if (resolve) return getSourceType();
				return basicGetSourceType();
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				if (resolve) return getTargetType();
				return basicGetTargetType();
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
			case TypemappingPackage.TYPE_MAPPING__IS_BEST_MATCH:
				setIsBestMatch((Boolean)newValue);
				return;
			case TypemappingPackage.TYPE_MAPPING__IS_LOSSY:
				setIsLossy((Boolean)newValue);
				return;
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				setSourceType((Classifier)newValue);
				return;
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				setTargetType((Classifier)newValue);
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
			case TypemappingPackage.TYPE_MAPPING__IS_BEST_MATCH:
				setIsBestMatch(IS_BEST_MATCH_EDEFAULT);
				return;
			case TypemappingPackage.TYPE_MAPPING__IS_LOSSY:
				setIsLossy(IS_LOSSY_EDEFAULT);
				return;
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				setSourceType((Classifier)null);
				return;
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				setTargetType((Classifier)null);
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
			case TypemappingPackage.TYPE_MAPPING__IS_BEST_MATCH:
				return isBestMatch != IS_BEST_MATCH_EDEFAULT;
			case TypemappingPackage.TYPE_MAPPING__IS_LOSSY:
				return isLossy != IS_LOSSY_EDEFAULT;
			case TypemappingPackage.TYPE_MAPPING__SOURCE_TYPE:
				return sourceType != null;
			case TypemappingPackage.TYPE_MAPPING__TARGET_TYPE:
				return targetType != null;
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
		result.append(" (isBestMatch: ");
		result.append(isBestMatch);
		result.append(", isLossy: ");
		result.append(isLossy);
		result.append(')');
		return result.toString();
	}

} //TypeMappingImpl
