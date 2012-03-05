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
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tuple Type CS</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.TupleTypeCSImpl#getVariableDeclarationList <em>Variable Declaration List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleTypeCSImpl extends TypeCSImpl implements TupleTypeCS {
	/**
	 * The cached value of the '{@link #getVariableDeclarationList()
	 * <em>Variable Declaration List</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVariableDeclarationList()
	 * @generated
	 * @ordered
	 */
	protected VariableDeclarationWithoutInitListCS variableDeclarationList;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleTypeCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.TUPLE_TYPE_CS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationWithoutInitListCS getVariableDeclarationList() {
		return variableDeclarationList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariableDeclarationList(
			VariableDeclarationWithoutInitListCS newVariableDeclarationList,
			NotificationChain msgs) {
		VariableDeclarationWithoutInitListCS oldVariableDeclarationList = variableDeclarationList;
		variableDeclarationList = newVariableDeclarationList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST, oldVariableDeclarationList, newVariableDeclarationList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariableDeclarationList(
			VariableDeclarationWithoutInitListCS newVariableDeclarationList) {
		if (newVariableDeclarationList != variableDeclarationList) {
			NotificationChain msgs = null;
			if (variableDeclarationList != null)
				msgs = ((InternalEObject)variableDeclarationList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST, null, msgs);
			if (newVariableDeclarationList != null)
				msgs = ((InternalEObject)newVariableDeclarationList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST, null, msgs);
			msgs = basicSetVariableDeclarationList(newVariableDeclarationList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST, newVariableDeclarationList, newVariableDeclarationList));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST:
				return basicSetVariableDeclarationList(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST:
				return getVariableDeclarationList();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST:
				setVariableDeclarationList((VariableDeclarationWithoutInitListCS)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST:
				setVariableDeclarationList((VariableDeclarationWithoutInitListCS)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST:
				return variableDeclarationList != null;
		}
		return super.eIsSet(featureID);
	}

} // TupleTypeCSImpl
