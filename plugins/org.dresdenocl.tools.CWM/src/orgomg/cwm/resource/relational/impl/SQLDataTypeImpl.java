/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.objectmodel.core.impl.ClassifierImpl;

import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDataTypeImpl#getTypeNumber <em>Type Number</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class SQLDataTypeImpl extends ClassifierImpl implements SQLDataType {
	/**
	 * The default value of the '{@link #getTypeNumber() <em>Type Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNumber()
	 * @generated
	 * @ordered
	 */
	protected static final long TYPE_NUMBER_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTypeNumber() <em>Type Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeNumber()
	 * @generated
	 * @ordered
	 */
	protected long typeNumber = TYPE_NUMBER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.SQL_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTypeNumber() {
		return typeNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeNumber(long newTypeNumber) {
		long oldTypeNumber = typeNumber;
		typeNumber = newTypeNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER, oldTypeNumber, typeNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER:
				return getTypeNumber();
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
			case RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER:
				setTypeNumber((Long)newValue);
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
			case RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER:
				setTypeNumber(TYPE_NUMBER_EDEFAULT);
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
			case RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER:
				return typeNumber != TYPE_NUMBER_EDEFAULT;
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
		result.append(" (typeNumber: ");
		result.append(typeNumber);
		result.append(')');
		return result.toString();
	}

} //SQLDataTypeImpl
