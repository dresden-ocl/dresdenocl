/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.objectmodel.core.Classifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A SQLDataType is used to reference any datatype associated with a column
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.SQLDataType#getTypeNumber <em>Type Number</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDataType()
 * @model abstract="true"
 * @generated
 */
public interface SQLDataType extends Classifier {
	/**
	 * Returns the value of the '<em><b>Type Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number assigned to the datatype by the owning RDBMS
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Number</em>' attribute.
	 * @see #setTypeNumber(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDataType_TypeNumber()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getTypeNumber();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLDataType#getTypeNumber <em>Type Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Number</em>' attribute.
	 * @see #getTypeNumber()
	 * @generated
	 */
	void setTypeNumber(long value);

} // SQLDataType
