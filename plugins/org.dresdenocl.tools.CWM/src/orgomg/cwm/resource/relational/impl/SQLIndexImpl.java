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

import orgomg.cwm.foundation.keysindexes.impl.IndexImpl;

import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLIndex;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLIndexImpl#getFilterCondition <em>Filter Condition</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLIndexImpl#isIsNullable <em>Is Nullable</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLIndexImpl#isAutoUpdate <em>Auto Update</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLIndexImpl extends IndexImpl implements SQLIndex {
	/**
	 * The default value of the '{@link #getFilterCondition() <em>Filter Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilterCondition() <em>Filter Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterCondition()
	 * @generated
	 * @ordered
	 */
	protected String filterCondition = FILTER_CONDITION_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNullable() <em>Is Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NULLABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNullable() <em>Is Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean isNullable = IS_NULLABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutoUpdate() <em>Auto Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_UPDATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoUpdate() <em>Auto Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoUpdate()
	 * @generated
	 * @ordered
	 */
	protected boolean autoUpdate = AUTO_UPDATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLIndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.SQL_INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilterCondition() {
		return filterCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilterCondition(String newFilterCondition) {
		String oldFilterCondition = filterCondition;
		filterCondition = newFilterCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_INDEX__FILTER_CONDITION, oldFilterCondition, filterCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNullable() {
		return isNullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNullable(boolean newIsNullable) {
		boolean oldIsNullable = isNullable;
		isNullable = newIsNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_INDEX__IS_NULLABLE, oldIsNullable, isNullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoUpdate(boolean newAutoUpdate) {
		boolean oldAutoUpdate = autoUpdate;
		autoUpdate = newAutoUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_INDEX__AUTO_UPDATE, oldAutoUpdate, autoUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationalPackage.SQL_INDEX__FILTER_CONDITION:
				return getFilterCondition();
			case RelationalPackage.SQL_INDEX__IS_NULLABLE:
				return isIsNullable();
			case RelationalPackage.SQL_INDEX__AUTO_UPDATE:
				return isAutoUpdate();
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
			case RelationalPackage.SQL_INDEX__FILTER_CONDITION:
				setFilterCondition((String)newValue);
				return;
			case RelationalPackage.SQL_INDEX__IS_NULLABLE:
				setIsNullable((Boolean)newValue);
				return;
			case RelationalPackage.SQL_INDEX__AUTO_UPDATE:
				setAutoUpdate((Boolean)newValue);
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
			case RelationalPackage.SQL_INDEX__FILTER_CONDITION:
				setFilterCondition(FILTER_CONDITION_EDEFAULT);
				return;
			case RelationalPackage.SQL_INDEX__IS_NULLABLE:
				setIsNullable(IS_NULLABLE_EDEFAULT);
				return;
			case RelationalPackage.SQL_INDEX__AUTO_UPDATE:
				setAutoUpdate(AUTO_UPDATE_EDEFAULT);
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
			case RelationalPackage.SQL_INDEX__FILTER_CONDITION:
				return FILTER_CONDITION_EDEFAULT == null ? filterCondition != null : !FILTER_CONDITION_EDEFAULT.equals(filterCondition);
			case RelationalPackage.SQL_INDEX__IS_NULLABLE:
				return isNullable != IS_NULLABLE_EDEFAULT;
			case RelationalPackage.SQL_INDEX__AUTO_UPDATE:
				return autoUpdate != AUTO_UPDATE_EDEFAULT;
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
		result.append(" (filterCondition: ");
		result.append(filterCondition);
		result.append(", isNullable: ");
		result.append(isNullable);
		result.append(", autoUpdate: ");
		result.append(autoUpdate);
		result.append(')');
		return result.toString();
	}

} //SQLIndexImpl
