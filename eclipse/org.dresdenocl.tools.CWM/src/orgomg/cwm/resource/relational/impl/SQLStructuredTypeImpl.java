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

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLDataType;
import orgomg.cwm.resource.relational.SQLStructuredType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Structured Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLStructuredTypeImpl#getTypeNumber <em>Type Number</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLStructuredTypeImpl#getColumnSet <em>Column Set</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.SQLStructuredTypeImpl#getReferencingColumn <em>Referencing Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLStructuredTypeImpl extends ClassImpl implements SQLStructuredType {
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
	 * The cached value of the '{@link #getColumnSet() <em>Column Set</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnSet()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedColumnSet> columnSet;

	/**
	 * The cached value of the '{@link #getReferencingColumn() <em>Referencing Column</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencingColumn()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> referencingColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SQLStructuredTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.SQL_STRUCTURED_TYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER, oldTypeNumber, typeNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedColumnSet> getColumnSet() {
		if (columnSet == null) {
			columnSet = new EObjectWithInverseResolvingEList<NamedColumnSet>(NamedColumnSet.class, this, RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET, RelationalPackage.NAMED_COLUMN_SET__TYPE);
		}
		return columnSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getReferencingColumn() {
		if (referencingColumn == null) {
			referencingColumn = new EObjectWithInverseResolvingEList<Column>(Column.class, this, RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN, RelationalPackage.COLUMN__REFERENCED_TABLE_TYPE);
		}
		return referencingColumn;
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getColumnSet()).basicAdd(otherEnd, msgs);
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReferencingColumn()).basicAdd(otherEnd, msgs);
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				return ((InternalEList<?>)getColumnSet()).basicRemove(otherEnd, msgs);
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				return ((InternalEList<?>)getReferencingColumn()).basicRemove(otherEnd, msgs);
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER:
				return getTypeNumber();
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				return getColumnSet();
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				return getReferencingColumn();
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER:
				setTypeNumber((Long)newValue);
				return;
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				getColumnSet().clear();
				getColumnSet().addAll((Collection<? extends NamedColumnSet>)newValue);
				return;
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				getReferencingColumn().clear();
				getReferencingColumn().addAll((Collection<? extends Column>)newValue);
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER:
				setTypeNumber(TYPE_NUMBER_EDEFAULT);
				return;
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				getColumnSet().clear();
				return;
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				getReferencingColumn().clear();
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
			case RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER:
				return typeNumber != TYPE_NUMBER_EDEFAULT;
			case RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET:
				return columnSet != null && !columnSet.isEmpty();
			case RelationalPackage.SQL_STRUCTURED_TYPE__REFERENCING_COLUMN:
				return referencingColumn != null && !referencingColumn.isEmpty();
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
		if (baseClass == SQLDataType.class) {
			switch (derivedFeatureID) {
				case RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER: return RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER;
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
		if (baseClass == SQLDataType.class) {
			switch (baseFeatureID) {
				case RelationalPackage.SQL_DATA_TYPE__TYPE_NUMBER: return RelationalPackage.SQL_STRUCTURED_TYPE__TYPE_NUMBER;
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
		result.append(" (typeNumber: ");
		result.append(typeNumber);
		result.append(')');
		return result.toString();
	}

} //SQLStructuredTypeImpl
