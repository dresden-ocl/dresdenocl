/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.foundation.keysindexes.KeyRelationship;

import orgomg.cwm.resource.relational.enumerations.DeferrabilityType;
import orgomg.cwm.resource.relational.enumerations.ReferentialRuleType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Foreign Key associates columns from one table with columns of another table.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.ForeignKey#getDeleteRule <em>Delete Rule</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.ForeignKey#getUpdateRule <em>Update Rule</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.ForeignKey#getDeferrability <em>Deferrability</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getForeignKey()
 * @model
 * @generated
 */
public interface ForeignKey extends KeyRelationship {
	/**
	 * Returns the value of the '<em><b>Delete Rule</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.ReferentialRuleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An enumerated type. Indicates the disposition of the data records containing the foreign key value when the record of the matching primary key is deleted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Delete Rule</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @see #setDeleteRule(ReferentialRuleType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getForeignKey_DeleteRule()
	 * @model
	 * @generated
	 */
	ReferentialRuleType getDeleteRule();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.ForeignKey#getDeleteRule <em>Delete Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete Rule</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @see #getDeleteRule()
	 * @generated
	 */
	void setDeleteRule(ReferentialRuleType value);

	/**
	 * Returns the value of the '<em><b>Update Rule</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.ReferentialRuleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Same as deleteRule for updates of the primary key data record
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Update Rule</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @see #setUpdateRule(ReferentialRuleType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getForeignKey_UpdateRule()
	 * @model
	 * @generated
	 */
	ReferentialRuleType getUpdateRule();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.ForeignKey#getUpdateRule <em>Update Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Rule</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @see #getUpdateRule()
	 * @generated
	 */
	void setUpdateRule(ReferentialRuleType value);

	/**
	 * Returns the value of the '<em><b>Deferrability</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if the validity of the ForeignKey is to be tested at each statement or at the end of a transaction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #setDeferrability(DeferrabilityType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getForeignKey_Deferrability()
	 * @model
	 * @generated
	 */
	DeferrabilityType getDeferrability();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.ForeignKey#getDeferrability <em>Deferrability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #getDeferrability()
	 * @generated
	 */
	void setDeferrability(DeferrabilityType value);

} // ForeignKey
