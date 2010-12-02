/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.objectmodel.core.Constraint;

import orgomg.cwm.resource.relational.enumerations.DeferrabilityType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A rule that specifies the values allowed in one or more columns of every row of a table.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.CheckConstraint#getDeferrability <em>Deferrability</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getCheckConstraint()
 * @model
 * @generated
 */
public interface CheckConstraint extends Constraint {
	/**
	 * Returns the value of the '<em><b>Deferrability</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates the timing of the constraint enforcement during multiple-user updates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #setDeferrability(DeferrabilityType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getCheckConstraint_Deferrability()
	 * @model
	 * @generated
	 */
	DeferrabilityType getDeferrability();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.CheckConstraint#getDeferrability <em>Deferrability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferrability</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see #getDeferrability()
	 * @generated
	 */
	void setDeferrability(DeferrabilityType value);

} // CheckConstraint
