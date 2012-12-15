/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.datatypes.TypeAlias;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.DataType;

import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLDistinctType;
import orgomg.cwm.resource.relational.SQLSimpleType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Distinct Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDistinctTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDistinctTypeImpl#getLength <em>Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDistinctTypeImpl#getPrecision <em>Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDistinctTypeImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLDistinctTypeImpl#getSqlSimpleType <em>Sql Simple Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLDistinctTypeImpl extends SQLDataTypeImpl implements SQLDistinctType {
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
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final long LENGTH_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected long length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final long PRECISION_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected long precision = PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected static final long SCALE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected long scale = SCALE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSqlSimpleType() <em>Sql Simple Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlSimpleType()
	 * @generated
	 * @ordered
	 */
	protected SQLSimpleType sqlSimpleType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLDistinctTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.SQL_DISTINCT_TYPE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelationalPackage.SQL_DISTINCT_TYPE__TYPE, oldType, type));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__TYPE, oldType, newType);
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
				msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__ALIAS, Classifier.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, CorePackage.CLASSIFIER__ALIAS, Classifier.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(long newLength) {
		long oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getPrecision() {
		return precision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecision(long newPrecision) {
		long oldPrecision = precision;
		precision = newPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__PRECISION, oldPrecision, precision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScale(long newScale) {
		long oldScale = scale;
		scale = newScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__SCALE, oldScale, scale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLSimpleType getSqlSimpleType() {
		if (sqlSimpleType != null && sqlSimpleType.eIsProxy()) {
			InternalEObject oldSqlSimpleType = (InternalEObject)sqlSimpleType;
			sqlSimpleType = (SQLSimpleType)eResolveProxy(oldSqlSimpleType);
			if (sqlSimpleType != oldSqlSimpleType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE, oldSqlSimpleType, sqlSimpleType));
			}
		}
		return sqlSimpleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLSimpleType basicGetSqlSimpleType() {
		return sqlSimpleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSqlSimpleType(SQLSimpleType newSqlSimpleType, NotificationChain msgs) {
		SQLSimpleType oldSqlSimpleType = sqlSimpleType;
		sqlSimpleType = newSqlSimpleType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE, oldSqlSimpleType, newSqlSimpleType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlSimpleType(SQLSimpleType newSqlSimpleType) {
		if (newSqlSimpleType != sqlSimpleType) {
			NotificationChain msgs = null;
			if (sqlSimpleType != null)
				msgs = ((InternalEObject)sqlSimpleType).eInverseRemove(this, RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE, SQLSimpleType.class, msgs);
			if (newSqlSimpleType != null)
				msgs = ((InternalEObject)newSqlSimpleType).eInverseAdd(this, RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE, SQLSimpleType.class, msgs);
			msgs = basicSetSqlSimpleType(newSqlSimpleType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE, newSqlSimpleType, newSqlSimpleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, CorePackage.CLASSIFIER__ALIAS, Classifier.class, msgs);
				return basicSetType((Classifier)otherEnd, msgs);
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				if (sqlSimpleType != null)
					msgs = ((InternalEObject)sqlSimpleType).eInverseRemove(this, RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE, SQLSimpleType.class, msgs);
				return basicSetSqlSimpleType((SQLSimpleType)otherEnd, msgs);
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
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				return basicSetType(null, msgs);
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				return basicSetSqlSimpleType(null, msgs);
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
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case RelationalPackage.SQL_DISTINCT_TYPE__LENGTH:
				return getLength();
			case RelationalPackage.SQL_DISTINCT_TYPE__PRECISION:
				return getPrecision();
			case RelationalPackage.SQL_DISTINCT_TYPE__SCALE:
				return getScale();
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				if (resolve) return getSqlSimpleType();
				return basicGetSqlSimpleType();
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
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				setType((Classifier)newValue);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__LENGTH:
				setLength((Long)newValue);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__PRECISION:
				setPrecision((Long)newValue);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__SCALE:
				setScale((Long)newValue);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				setSqlSimpleType((SQLSimpleType)newValue);
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
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				setType((Classifier)null);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__PRECISION:
				setPrecision(PRECISION_EDEFAULT);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__SCALE:
				setScale(SCALE_EDEFAULT);
				return;
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				setSqlSimpleType((SQLSimpleType)null);
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
			case RelationalPackage.SQL_DISTINCT_TYPE__TYPE:
				return type != null;
			case RelationalPackage.SQL_DISTINCT_TYPE__LENGTH:
				return length != LENGTH_EDEFAULT;
			case RelationalPackage.SQL_DISTINCT_TYPE__PRECISION:
				return precision != PRECISION_EDEFAULT;
			case RelationalPackage.SQL_DISTINCT_TYPE__SCALE:
				return scale != SCALE_EDEFAULT;
			case RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE:
				return sqlSimpleType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DataType.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == TypeAlias.class) {
			switch (derivedFeatureID) {
				case RelationalPackage.SQL_DISTINCT_TYPE__TYPE: return DatatypesPackage.TYPE_ALIAS__TYPE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DataType.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == TypeAlias.class) {
			switch (baseFeatureID) {
				case DatatypesPackage.TYPE_ALIAS__TYPE: return RelationalPackage.SQL_DISTINCT_TYPE__TYPE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (length: ");
		result.append(length);
		result.append(", precision: ");
		result.append(precision);
		result.append(", scale: ");
		result.append(scale);
		result.append(')');
		return result.toString();
	}

} //SQLDistinctTypeImpl
