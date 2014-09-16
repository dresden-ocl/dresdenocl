/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.foundation.datatypes.TypeAlias;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Distinct Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A datatype defined as a Distinct Type, per [SQL] standard.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.SQLDistinctType#getLength <em>Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLDistinctType#getPrecision <em>Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLDistinctType#getScale <em>Scale</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLDistinctType#getSqlSimpleType <em>Sql Simple Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDistinctType()
 * @model
 * @generated
 */
public interface SQLDistinctType extends SQLDataType, TypeAlias {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The length of fixed length character or byte strings. Maximum length if length is
	 * variable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDistinctType_Length()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getLength();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLDistinctType#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(long value);

	/**
	 * Returns the value of the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total number of digits in the field
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precision</em>' attribute.
	 * @see #setPrecision(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDistinctType_Precision()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getPrecision();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLDistinctType#getPrecision <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision</em>' attribute.
	 * @see #getPrecision()
	 * @generated
	 */
	void setPrecision(long value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of digits on the right of the decimal separator.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDistinctType_Scale()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getScale();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLDistinctType#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(long value);

	/**
	 * Returns the value of the '<em><b>Sql Simple Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.SQLSimpleType#getSqlDistinctType <em>Sql Distinct Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Simple type used to define the distinct class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sql Simple Type</em>' reference.
	 * @see #setSqlSimpleType(SQLSimpleType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLDistinctType_SqlSimpleType()
	 * @see orgomg.cwm.resource.relational.SQLSimpleType#getSqlDistinctType
	 * @model opposite="sqlDistinctType" required="true"
	 * @generated
	 */
	SQLSimpleType getSqlSimpleType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLDistinctType#getSqlSimpleType <em>Sql Simple Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Simple Type</em>' reference.
	 * @see #getSqlSimpleType()
	 * @generated
	 */
	void setSqlSimpleType(SQLSimpleType value);

} // SQLDistinctType
