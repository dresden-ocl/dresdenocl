/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.foundation.keysindexes.UniqueKey;

import orgomg.cwm.resource.relational.enumerations.DeferrabilityType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A condition to define uniqueness of rows in a table. An example of UniqueConstraint is a primary key
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.UniqueConstraint#getDeferrability <em>Deferrability</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getUniqueConstraint()
 * @model
 * @generated
 */
public interface UniqueConstraint extends UniqueKey {
	/**
	 * Returns the value of the '<em><b>Deferrability</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if the validity of the UniqueConstraint is to be tested at each statement or at the end of a transaction.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #setDeferrability(DeferrabilityType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getUniqueConstraint_Deferrability()
	 * @model
	 * @generated
	 */
	DeferrabilityType getDeferrability();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.UniqueConstraint#getDeferrability <em>Deferrability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #getDeferrability()
	 * @generated
	 */
	void setDeferrability(DeferrabilityType value);

} // UniqueConstraint
