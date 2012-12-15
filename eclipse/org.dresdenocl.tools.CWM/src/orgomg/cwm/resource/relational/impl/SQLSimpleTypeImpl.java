/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLDistinctType;
import orgomg.cwm.resource.relational.SQLSimpleType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Simple Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getCharacterMaximumLength <em>Character Maximum Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getCharacterOctetLength <em>Character Octet Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getNumericPrecision <em>Numeric Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getNumericPrecisionRadix <em>Numeric Precision Radix</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getNumericScale <em>Numeric Scale</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getDateTimePrecision <em>Date Time Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLSimpleTypeImpl#getSqlDistinctType <em>Sql Distinct Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLSimpleTypeImpl extends SQLDataTypeImpl implements SQLSimpleType {
	/**
	 * The default value of the '{@link #getCharacterMaximumLength() <em>Character Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterMaximumLength()
	 * @generated
	 * @ordered
	 */
	protected static final long CHARACTER_MAXIMUM_LENGTH_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCharacterMaximumLength() <em>Character Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterMaximumLength()
	 * @generated
	 * @ordered
	 */
	protected long characterMaximumLength = CHARACTER_MAXIMUM_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getCharacterOctetLength() <em>Character Octet Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterOctetLength()
	 * @generated
	 * @ordered
	 */
	protected static final long CHARACTER_OCTET_LENGTH_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCharacterOctetLength() <em>Character Octet Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterOctetLength()
	 * @generated
	 * @ordered
	 */
	protected long characterOctetLength = CHARACTER_OCTET_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumericPrecision() <em>Numeric Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final long NUMERIC_PRECISION_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNumericPrecision() <em>Numeric Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericPrecision()
	 * @generated
	 * @ordered
	 */
	protected long numericPrecision = NUMERIC_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumericPrecisionRadix() <em>Numeric Precision Radix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericPrecisionRadix()
	 * @generated
	 * @ordered
	 */
	protected static final long NUMERIC_PRECISION_RADIX_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNumericPrecisionRadix() <em>Numeric Precision Radix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericPrecisionRadix()
	 * @generated
	 * @ordered
	 */
	protected long numericPrecisionRadix = NUMERIC_PRECISION_RADIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumericScale() <em>Numeric Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericScale()
	 * @generated
	 * @ordered
	 */
	protected static final long NUMERIC_SCALE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNumericScale() <em>Numeric Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumericScale()
	 * @generated
	 * @ordered
	 */
	protected long numericScale = NUMERIC_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDateTimePrecision() <em>Date Time Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateTimePrecision()
	 * @generated
	 * @ordered
	 */
	protected static final long DATE_TIME_PRECISION_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getDateTimePrecision() <em>Date Time Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDateTimePrecision()
	 * @generated
	 * @ordered
	 */
	protected long dateTimePrecision = DATE_TIME_PRECISION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSqlDistinctType() <em>Sql Distinct Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlDistinctType()
	 * @generated
	 * @ordered
	 */
	protected EList<SQLDistinctType> sqlDistinctType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLSimpleTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.SQL_SIMPLE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCharacterMaximumLength() {
		return characterMaximumLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterMaximumLength(long newCharacterMaximumLength) {
		long oldCharacterMaximumLength = characterMaximumLength;
		characterMaximumLength = newCharacterMaximumLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH, oldCharacterMaximumLength, characterMaximumLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCharacterOctetLength() {
		return characterOctetLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterOctetLength(long newCharacterOctetLength) {
		long oldCharacterOctetLength = characterOctetLength;
		characterOctetLength = newCharacterOctetLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH, oldCharacterOctetLength, characterOctetLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getNumericPrecision() {
		return numericPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumericPrecision(long newNumericPrecision) {
		long oldNumericPrecision = numericPrecision;
		numericPrecision = newNumericPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION, oldNumericPrecision, numericPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getNumericPrecisionRadix() {
		return numericPrecisionRadix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumericPrecisionRadix(long newNumericPrecisionRadix) {
		long oldNumericPrecisionRadix = numericPrecisionRadix;
		numericPrecisionRadix = newNumericPrecisionRadix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX, oldNumericPrecisionRadix, numericPrecisionRadix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getNumericScale() {
		return numericScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumericScale(long newNumericScale) {
		long oldNumericScale = numericScale;
		numericScale = newNumericScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_SCALE, oldNumericScale, numericScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getDateTimePrecision() {
		return dateTimePrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDateTimePrecision(long newDateTimePrecision) {
		long oldDateTimePrecision = dateTimePrecision;
		dateTimePrecision = newDateTimePrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_SIMPLE_TYPE__DATE_TIME_PRECISION, oldDateTimePrecision, dateTimePrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SQLDistinctType> getSqlDistinctType() {
		if (sqlDistinctType == null) {
			sqlDistinctType = new EObjectWithInverseResolvingEList<SQLDistinctType>(SQLDistinctType.class, this, RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE, RelationalPackage.SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE);
		}
		return sqlDistinctType;
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
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSqlDistinctType()).basicAdd(otherEnd, msgs);
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
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				return ((InternalEList<?>)getSqlDistinctType()).basicRemove(otherEnd, msgs);
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
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH:
				return getCharacterMaximumLength();
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH:
				return getCharacterOctetLength();
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION:
				return getNumericPrecision();
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX:
				return getNumericPrecisionRadix();
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_SCALE:
				return getNumericScale();
			case RelationalPackage.SQL_SIMPLE_TYPE__DATE_TIME_PRECISION:
				return getDateTimePrecision();
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				return getSqlDistinctType();
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
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH:
				setCharacterMaximumLength((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH:
				setCharacterOctetLength((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION:
				setNumericPrecision((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX:
				setNumericPrecisionRadix((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_SCALE:
				setNumericScale((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__DATE_TIME_PRECISION:
				setDateTimePrecision((Long)newValue);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				getSqlDistinctType().clear();
				getSqlDistinctType().addAll((Collection<? extends SQLDistinctType>)newValue);
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
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH:
				setCharacterMaximumLength(CHARACTER_MAXIMUM_LENGTH_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH:
				setCharacterOctetLength(CHARACTER_OCTET_LENGTH_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION:
				setNumericPrecision(NUMERIC_PRECISION_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX:
				setNumericPrecisionRadix(NUMERIC_PRECISION_RADIX_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_SCALE:
				setNumericScale(NUMERIC_SCALE_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__DATE_TIME_PRECISION:
				setDateTimePrecision(DATE_TIME_PRECISION_EDEFAULT);
				return;
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				getSqlDistinctType().clear();
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
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH:
				return characterMaximumLength != CHARACTER_MAXIMUM_LENGTH_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH:
				return characterOctetLength != CHARACTER_OCTET_LENGTH_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION:
				return numericPrecision != NUMERIC_PRECISION_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX:
				return numericPrecisionRadix != NUMERIC_PRECISION_RADIX_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__NUMERIC_SCALE:
				return numericScale != NUMERIC_SCALE_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__DATE_TIME_PRECISION:
				return dateTimePrecision != DATE_TIME_PRECISION_EDEFAULT;
			case RelationalPackage.SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE:
				return sqlDistinctType != null && !sqlDistinctType.isEmpty();
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
		result.append(" (characterMaximumLength: ");
		result.append(characterMaximumLength);
		result.append(", characterOctetLength: ");
		result.append(characterOctetLength);
		result.append(", numericPrecision: ");
		result.append(numericPrecision);
		result.append(", numericPrecisionRadix: ");
		result.append(numericPrecisionRadix);
		result.append(", numericScale: ");
		result.append(numericScale);
		result.append(", dateTimePrecision: ");
		result.append(dateTimePrecision);
		result.append(')');
		return result.toString();
	}

} //SQLSimpleTypeImpl
