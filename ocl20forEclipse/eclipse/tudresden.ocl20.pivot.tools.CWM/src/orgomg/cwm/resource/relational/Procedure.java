/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.objectmodel.behavioral.Method;

import orgomg.cwm.resource.relational.enumerations.ProcedureType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class describes Relational DBMS Stored procedures and functions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.Procedure#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getProcedure()
 * @model
 * @generated
 */
public interface Procedure extends Method {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.ProcedureType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Procedure can be either a Function or a true Procedure. This indicates whether this object returns a value or not.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ProcedureType
	 * @see #setType(ProcedureType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getProcedure_Type()
	 * @model
	 * @generated
	 */
	ProcedureType getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Procedure#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.ProcedureType
	 * @see #getType()
	 * @generated
	 */
	void setType(ProcedureType value);

} // Procedure
