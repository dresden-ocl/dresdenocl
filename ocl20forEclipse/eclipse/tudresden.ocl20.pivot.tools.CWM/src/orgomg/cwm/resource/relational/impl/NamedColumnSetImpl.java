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

import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.SQLStructuredType;
import orgomg.cwm.resource.relational.Trigger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Column Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.NamedColumnSetImpl#getUsingTrigger <em>Using Trigger</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.NamedColumnSetImpl#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.NamedColumnSetImpl#getOptionScopeColumn <em>Option Scope Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NamedColumnSetImpl extends ColumnSetImpl implements NamedColumnSet {
	/**
	 * The cached value of the '{@link #getUsingTrigger() <em>Using Trigger</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsingTrigger()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> usingTrigger;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected SQLStructuredType type;

	/**
	 * The cached value of the '{@link #getOptionScopeColumn() <em>Option Scope Column</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptionScopeColumn()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> optionScopeColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamedColumnSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.NAMED_COLUMN_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Trigger> getUsingTrigger() {
		if (usingTrigger == null) {
			usingTrigger = new EObjectWithInverseResolvingEList.ManyInverse<Trigger>(Trigger.class, this, RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER, RelationalPackage.TRIGGER__USED_COLUMN_SET);
		}
		return usingTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStructuredType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (SQLStructuredType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RelationalPackage.NAMED_COLUMN_SET__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStructuredType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(SQLStructuredType newType, NotificationChain msgs) {
		SQLStructuredType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelationalPackage.NAMED_COLUMN_SET__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(SQLStructuredType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET, SQLStructuredType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET, SQLStructuredType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.NAMED_COLUMN_SET__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getOptionScopeColumn() {
		if (optionScopeColumn == null) {
			optionScopeColumn = new EObjectWithInverseResolvingEList<Column>(Column.class, this, RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN, RelationalPackage.COLUMN__OPTION_SCOPE_COLUMN_SET);
		}
		return optionScopeColumn;
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsingTrigger()).basicAdd(otherEnd, msgs);
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, RelationalPackage.SQL_STRUCTURED_TYPE__COLUMN_SET, SQLStructuredType.class, msgs);
				return basicSetType((SQLStructuredType)otherEnd, msgs);
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOptionScopeColumn()).basicAdd(otherEnd, msgs);
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				return ((InternalEList<?>)getUsingTrigger()).basicRemove(otherEnd, msgs);
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				return basicSetType(null, msgs);
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				return ((InternalEList<?>)getOptionScopeColumn()).basicRemove(otherEnd, msgs);
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				return getUsingTrigger();
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				return getOptionScopeColumn();
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				getUsingTrigger().clear();
				getUsingTrigger().addAll((Collection<? extends Trigger>)newValue);
				return;
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				setType((SQLStructuredType)newValue);
				return;
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				getOptionScopeColumn().clear();
				getOptionScopeColumn().addAll((Collection<? extends Column>)newValue);
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				getUsingTrigger().clear();
				return;
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				setType((SQLStructuredType)null);
				return;
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				getOptionScopeColumn().clear();
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
			case RelationalPackage.NAMED_COLUMN_SET__USING_TRIGGER:
				return usingTrigger != null && !usingTrigger.isEmpty();
			case RelationalPackage.NAMED_COLUMN_SET__TYPE:
				return type != null;
			case RelationalPackage.NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN:
				return optionScopeColumn != null && !optionScopeColumn.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NamedColumnSetImpl
