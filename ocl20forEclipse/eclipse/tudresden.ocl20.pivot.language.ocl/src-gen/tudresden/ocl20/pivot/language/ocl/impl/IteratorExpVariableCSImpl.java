/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.SimpleNameCS;
import tudresden.ocl20.pivot.language.ocl.TypeCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterator Exp Variable CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl#getVariableName <em>Variable Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.IteratorExpVariableCSImpl#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IteratorExpVariableCSImpl extends EObjectImpl implements IteratorExpVariableCS {
	/**
	 * The cached value of the '{@link #getVariableName() <em>Variable Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableName()
	 * @generated
	 * @ordered
	 */
	protected SimpleNameCS variableName;

	/**
	 * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeName()
	 * @generated
	 * @ordered
	 */
	protected TypeCS typeName;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratorExpVariableCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.ITERATOR_EXP_VARIABLE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleNameCS getVariableName() {
		return variableName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariableName(SimpleNameCS newVariableName, NotificationChain msgs) {
		SimpleNameCS oldVariableName = variableName;
		variableName = newVariableName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME, oldVariableName, newVariableName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariableName(SimpleNameCS newVariableName) {
		if (newVariableName != variableName) {
			NotificationChain msgs = null;
			if (variableName != null)
				msgs = ((InternalEObject)variableName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME, null, msgs);
			if (newVariableName != null)
				msgs = ((InternalEObject)newVariableName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME, null, msgs);
			msgs = basicSetVariableName(newVariableName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME, newVariableName, newVariableName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeCS getTypeName() {
		return typeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeName(TypeCS newTypeName, NotificationChain msgs) {
		TypeCS oldTypeName = typeName;
		typeName = newTypeName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME, oldTypeName, newTypeName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeName(TypeCS newTypeName) {
		if (newTypeName != typeName) {
			NotificationChain msgs = null;
			if (typeName != null)
				msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME, null, msgs);
			if (newTypeName != null)
				msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME, null, msgs);
			msgs = basicSetTypeName(newTypeName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME, newTypeName, newTypeName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME:
				return basicSetVariableName(null, msgs);
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME:
				return basicSetTypeName(null, msgs);
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
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME:
				return getVariableName();
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME:
				return getTypeName();
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
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME:
				setVariableName((SimpleNameCS)newValue);
				return;
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME:
				setTypeName((TypeCS)newValue);
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
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME:
				setVariableName((SimpleNameCS)null);
				return;
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME:
				setTypeName((TypeCS)null);
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
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME:
				return variableName != null;
			case OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME:
				return typeName != null;
		}
		return super.eIsSet(featureID);
	}

} //IteratorExpVariableCSImpl
