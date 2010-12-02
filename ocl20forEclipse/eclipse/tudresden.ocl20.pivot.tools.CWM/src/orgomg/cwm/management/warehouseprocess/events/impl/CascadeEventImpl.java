/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType;

import orgomg.cwm.management.warehouseprocess.events.CascadeEvent;
import orgomg.cwm.management.warehouseprocess.events.EventsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cascade Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.events.impl.CascadeEventImpl#getWaitRule <em>Wait Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CascadeEventImpl extends InternalEventImpl implements CascadeEvent {
	/**
	 * The default value of the '{@link #getWaitRule() <em>Wait Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitRule()
	 * @generated
	 * @ordered
	 */
	protected static final WaitRuleType WAIT_RULE_EDEFAULT = WaitRuleType.WAIT_FOR_ALL;

	/**
	 * The cached value of the '{@link #getWaitRule() <em>Wait Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaitRule()
	 * @generated
	 * @ordered
	 */
	protected WaitRuleType waitRule = WAIT_RULE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CascadeEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EventsPackage.Literals.CASCADE_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WaitRuleType getWaitRule() {
		return waitRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitRule(WaitRuleType newWaitRule) {
		WaitRuleType oldWaitRule = waitRule;
		waitRule = newWaitRule == null ? WAIT_RULE_EDEFAULT : newWaitRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EventsPackage.CASCADE_EVENT__WAIT_RULE, oldWaitRule, waitRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EventsPackage.CASCADE_EVENT__WAIT_RULE:
				return getWaitRule();
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
			case EventsPackage.CASCADE_EVENT__WAIT_RULE:
				setWaitRule((WaitRuleType)newValue);
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
			case EventsPackage.CASCADE_EVENT__WAIT_RULE:
				setWaitRule(WAIT_RULE_EDEFAULT);
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
			case EventsPackage.CASCADE_EVENT__WAIT_RULE:
				return waitRule != WAIT_RULE_EDEFAULT;
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
		result.append(" (waitRule: ");
		result.append(waitRule);
		result.append(')');
		return result.toString();
	}

} //CascadeEventImpl
