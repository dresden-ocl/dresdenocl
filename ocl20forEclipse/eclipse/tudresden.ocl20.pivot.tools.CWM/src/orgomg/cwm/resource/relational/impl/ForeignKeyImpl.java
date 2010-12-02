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

import orgomg.cwm.foundation.keysindexes.impl.KeyRelationshipImpl;

import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.RelationalPackage;

import orgomg.cwm.resource.relational.enumerations.DeferrabilityType;
import orgomg.cwm.resource.relational.enumerations.ReferentialRuleType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.ForeignKeyImpl#getDeleteRule <em>Delete Rule</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.ForeignKeyImpl#getUpdateRule <em>Update Rule</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.ForeignKeyImpl#getDeferrability <em>Deferrability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForeignKeyImpl extends KeyRelationshipImpl implements ForeignKey {
	/**
	 * The default value of the '{@link #getDeleteRule() <em>Delete Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeleteRule()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialRuleType DELETE_RULE_EDEFAULT = ReferentialRuleType.IMPORTED_KEY_NO_ACTION;

	/**
	 * The cached value of the '{@link #getDeleteRule() <em>Delete Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeleteRule()
	 * @generated
	 * @ordered
	 */
	protected ReferentialRuleType deleteRule = DELETE_RULE_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpdateRule() <em>Update Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdateRule()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialRuleType UPDATE_RULE_EDEFAULT = ReferentialRuleType.IMPORTED_KEY_NO_ACTION;

	/**
	 * The cached value of the '{@link #getUpdateRule() <em>Update Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpdateRule()
	 * @generated
	 * @ordered
	 */
	protected ReferentialRuleType updateRule = UPDATE_RULE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeferrability() <em>Deferrability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeferrability()
	 * @generated
	 * @ordered
	 */
	protected static final DeferrabilityType DEFERRABILITY_EDEFAULT = DeferrabilityType.INITIALLY_DEFERRED;

	/**
	 * The cached value of the '{@link #getDeferrability() <em>Deferrability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeferrability()
	 * @generated
	 * @ordered
	 */
	protected DeferrabilityType deferrability = DEFERRABILITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForeignKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.FOREIGN_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialRuleType getDeleteRule() {
		return deleteRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeleteRule(ReferentialRuleType newDeleteRule) {
		ReferentialRuleType oldDeleteRule = deleteRule;
		deleteRule = newDeleteRule == null ? DELETE_RULE_EDEFAULT : newDeleteRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.FOREIGN_KEY__DELETE_RULE, oldDeleteRule, deleteRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialRuleType getUpdateRule() {
		return updateRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpdateRule(ReferentialRuleType newUpdateRule) {
		ReferentialRuleType oldUpdateRule = updateRule;
		updateRule = newUpdateRule == null ? UPDATE_RULE_EDEFAULT : newUpdateRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.FOREIGN_KEY__UPDATE_RULE, oldUpdateRule, updateRule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeferrabilityType getDeferrability() {
		return deferrability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeferrability(DeferrabilityType newDeferrability) {
		DeferrabilityType oldDeferrability = deferrability;
		deferrability = newDeferrability == null ? DEFERRABILITY_EDEFAULT : newDeferrability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.FOREIGN_KEY__DEFERRABILITY, oldDeferrability, deferrability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationalPackage.FOREIGN_KEY__DELETE_RULE:
				return getDeleteRule();
			case RelationalPackage.FOREIGN_KEY__UPDATE_RULE:
				return getUpdateRule();
			case RelationalPackage.FOREIGN_KEY__DEFERRABILITY:
				return getDeferrability();
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
			case RelationalPackage.FOREIGN_KEY__DELETE_RULE:
				setDeleteRule((ReferentialRuleType)newValue);
				return;
			case RelationalPackage.FOREIGN_KEY__UPDATE_RULE:
				setUpdateRule((ReferentialRuleType)newValue);
				return;
			case RelationalPackage.FOREIGN_KEY__DEFERRABILITY:
				setDeferrability((DeferrabilityType)newValue);
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
			case RelationalPackage.FOREIGN_KEY__DELETE_RULE:
				setDeleteRule(DELETE_RULE_EDEFAULT);
				return;
			case RelationalPackage.FOREIGN_KEY__UPDATE_RULE:
				setUpdateRule(UPDATE_RULE_EDEFAULT);
				return;
			case RelationalPackage.FOREIGN_KEY__DEFERRABILITY:
				setDeferrability(DEFERRABILITY_EDEFAULT);
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
			case RelationalPackage.FOREIGN_KEY__DELETE_RULE:
				return deleteRule != DELETE_RULE_EDEFAULT;
			case RelationalPackage.FOREIGN_KEY__UPDATE_RULE:
				return updateRule != UPDATE_RULE_EDEFAULT;
			case RelationalPackage.FOREIGN_KEY__DEFERRABILITY:
				return deferrability != DEFERRABILITY_EDEFAULT;
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
		result.append(" (deleteRule: ");
		result.append(deleteRule);
		result.append(", updateRule: ");
		result.append(updateRule);
		result.append(", deferrability: ");
		result.append(deferrability);
		result.append(')');
		return result.toString();
	}

} //ForeignKeyImpl
