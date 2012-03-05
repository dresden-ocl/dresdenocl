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

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.TupleTypeCS;
import tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple Type Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeLiteralExpCSImpl#getTupleType <em>Tuple Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleTypeLiteralExpCSImpl extends LiteralExpCSImpl implements TupleTypeLiteralExpCS {
	/**
	 * The cached value of the '{@link #getTupleType() <em>Tuple Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTupleType()
	 * @generated
	 * @ordered
	 */
	protected TupleTypeCS tupleType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleTypeLiteralExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.TUPLE_TYPE_LITERAL_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleTypeCS getTupleType() {
		return tupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTupleType(TupleTypeCS newTupleType, NotificationChain msgs) {
		TupleTypeCS oldTupleType = tupleType;
		tupleType = newTupleType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE, oldTupleType, newTupleType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTupleType(TupleTypeCS newTupleType) {
		if (newTupleType != tupleType) {
			NotificationChain msgs = null;
			if (tupleType != null)
				msgs = ((InternalEObject)tupleType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE, null, msgs);
			if (newTupleType != null)
				msgs = ((InternalEObject)newTupleType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE, null, msgs);
			msgs = basicSetTupleType(newTupleType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE, newTupleType, newTupleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE:
				return basicSetTupleType(null, msgs);
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
			case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE:
				return getTupleType();
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
			case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE:
				setTupleType((TupleTypeCS)newValue);
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
			case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE:
				setTupleType((TupleTypeCS)null);
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
			case OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE:
				return tupleType != null;
		}
		return super.eIsSet(featureID);
	}

} //TupleTypeLiteralExpCSImpl
