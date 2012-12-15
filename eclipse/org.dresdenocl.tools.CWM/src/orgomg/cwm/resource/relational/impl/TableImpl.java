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
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.Trigger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TableImpl#isIsTemporary <em>Is Temporary</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TableImpl#getTemporaryScope <em>Temporary Scope</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TableImpl#isIsSystem <em>Is System</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.TableImpl#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableImpl extends NamedColumnSetImpl implements Table {
	/**
	 * The default value of the '{@link #isIsTemporary() <em>Is Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTemporary()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TEMPORARY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsTemporary() <em>Is Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTemporary()
	 * @generated
	 * @ordered
	 */
	protected boolean isTemporary = IS_TEMPORARY_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemporaryScope() <em>Temporary Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporaryScope()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPORARY_SCOPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemporaryScope() <em>Temporary Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporaryScope()
	 * @generated
	 * @ordered
	 */
	protected String temporaryScope = TEMPORARY_SCOPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsSystem() <em>Is System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSystem()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SYSTEM_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSystem() <em>Is System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSystem()
	 * @generated
	 * @ordered
	 */
	protected boolean isSystem = IS_SYSTEM_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected EList<Trigger> trigger;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsTemporary() {
		return isTemporary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsTemporary(boolean newIsTemporary) {
		boolean oldIsTemporary = isTemporary;
		isTemporary = newIsTemporary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TABLE__IS_TEMPORARY, oldIsTemporary, isTemporary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemporaryScope() {
		return temporaryScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemporaryScope(String newTemporaryScope) {
		String oldTemporaryScope = temporaryScope;
		temporaryScope = newTemporaryScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TABLE__TEMPORARY_SCOPE, oldTemporaryScope, temporaryScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSystem() {
		return isSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSystem(boolean newIsSystem) {
		boolean oldIsSystem = isSystem;
		isSystem = newIsSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.TABLE__IS_SYSTEM, oldIsSystem, isSystem));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Trigger> getTrigger() {
		if (trigger == null) {
			trigger = new EObjectWithInverseResolvingEList<Trigger>(Trigger.class, this, RelationalPackage.TABLE__TRIGGER, RelationalPackage.TRIGGER__TABLE);
		}
		return trigger;
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
			case RelationalPackage.TABLE__TRIGGER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTrigger()).basicAdd(otherEnd, msgs);
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
			case RelationalPackage.TABLE__TRIGGER:
				return ((InternalEList<?>)getTrigger()).basicRemove(otherEnd, msgs);
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
			case RelationalPackage.TABLE__IS_TEMPORARY:
				return isIsTemporary();
			case RelationalPackage.TABLE__TEMPORARY_SCOPE:
				return getTemporaryScope();
			case RelationalPackage.TABLE__IS_SYSTEM:
				return isIsSystem();
			case RelationalPackage.TABLE__TRIGGER:
				return getTrigger();
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
			case RelationalPackage.TABLE__IS_TEMPORARY:
				setIsTemporary((Boolean)newValue);
				return;
			case RelationalPackage.TABLE__TEMPORARY_SCOPE:
				setTemporaryScope((String)newValue);
				return;
			case RelationalPackage.TABLE__IS_SYSTEM:
				setIsSystem((Boolean)newValue);
				return;
			case RelationalPackage.TABLE__TRIGGER:
				getTrigger().clear();
				getTrigger().addAll((Collection<? extends Trigger>)newValue);
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
			case RelationalPackage.TABLE__IS_TEMPORARY:
				setIsTemporary(IS_TEMPORARY_EDEFAULT);
				return;
			case RelationalPackage.TABLE__TEMPORARY_SCOPE:
				setTemporaryScope(TEMPORARY_SCOPE_EDEFAULT);
				return;
			case RelationalPackage.TABLE__IS_SYSTEM:
				setIsSystem(IS_SYSTEM_EDEFAULT);
				return;
			case RelationalPackage.TABLE__TRIGGER:
				getTrigger().clear();
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
			case RelationalPackage.TABLE__IS_TEMPORARY:
				return isTemporary != IS_TEMPORARY_EDEFAULT;
			case RelationalPackage.TABLE__TEMPORARY_SCOPE:
				return TEMPORARY_SCOPE_EDEFAULT == null ? temporaryScope != null : !TEMPORARY_SCOPE_EDEFAULT.equals(temporaryScope);
			case RelationalPackage.TABLE__IS_SYSTEM:
				return isSystem != IS_SYSTEM_EDEFAULT;
			case RelationalPackage.TABLE__TRIGGER:
				return trigger != null && !trigger.isEmpty();
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
		result.append(" (isTemporary: ");
		result.append(isTemporary);
		result.append(", temporaryScope: ");
		result.append(temporaryScope);
		result.append(", isSystem: ");
		result.append(isSystem);
		result.append(')');
		return result.toString();
	}

} //TableImpl
