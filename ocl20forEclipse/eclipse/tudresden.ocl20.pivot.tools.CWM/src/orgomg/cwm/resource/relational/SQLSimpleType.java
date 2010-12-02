/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Simple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A simple datatype used with an SQL column. Examples are Integer, Varchar, LOB, CLOB, etc...
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getCharacterMaximumLength <em>Character Maximum Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getCharacterOctetLength <em>Character Octet Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericPrecision <em>Numeric Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericPrecisionRadix <em>Numeric Precision Radix</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericScale <em>Numeric Scale</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getDateTimePrecision <em>Date Time Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLSimpleType#getSqlDistinctType <em>Sql Distinct Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType()
 * @model
 * @generated
 */
public interface SQLSimpleType extends SQLDataType, DataType {
	/**
	 * Returns the value of the '<em><b>Character Maximum Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Character Maximum Length</em>' attribute.
	 * @see #setCharacterMaximumLength(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_CharacterMaximumLength()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getCharacterMaximumLength();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getCharacterMaximumLength <em>Character Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Maximum Length</em>' attribute.
	 * @see #getCharacterMaximumLength()
	 * @generated
	 */
	void setCharacterMaximumLength(long value);

	/**
	 * Returns the value of the '<em><b>Character Octet Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Character Octet Length</em>' attribute.
	 * @see #setCharacterOctetLength(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_CharacterOctetLength()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getCharacterOctetLength();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getCharacterOctetLength <em>Character Octet Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Octet Length</em>' attribute.
	 * @see #getCharacterOctetLength()
	 * @generated
	 */
	void setCharacterOctetLength(long value);

	/**
	 * Returns the value of the '<em><b>Numeric Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Numeric Precision</em>' attribute.
	 * @see #setNumericPrecision(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_NumericPrecision()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getNumericPrecision();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericPrecision <em>Numeric Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numeric Precision</em>' attribute.
	 * @see #getNumericPrecision()
	 * @generated
	 */
	void setNumericPrecision(long value);

	/**
	 * Returns the value of the '<em><b>Numeric Precision Radix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Numeric Precision Radix</em>' attribute.
	 * @see #setNumericPrecisionRadix(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_NumericPrecisionRadix()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getNumericPrecisionRadix();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericPrecisionRadix <em>Numeric Precision Radix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numeric Precision Radix</em>' attribute.
	 * @see #getNumericPrecisionRadix()
	 * @generated
	 */
	void setNumericPrecisionRadix(long value);

	/**
	 * Returns the value of the '<em><b>Numeric Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Numeric Scale</em>' attribute.
	 * @see #setNumericScale(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_NumericScale()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getNumericScale();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getNumericScale <em>Numeric Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numeric Scale</em>' attribute.
	 * @see #getNumericScale()
	 * @generated
	 */
	void setNumericScale(long value);

	/**
	 * Returns the value of the '<em><b>Date Time Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * See [SQL], corresponding field in DATA_TYPE_DESCRIPTOR
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date Time Precision</em>' attribute.
	 * @see #setDateTimePrecision(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_DateTimePrecision()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getDateTimePrecision();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLSimpleType#getDateTimePrecision <em>Date Time Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date Time Precision</em>' attribute.
	 * @see #getDateTimePrecision()
	 * @generated
	 */
	void setDateTimePrecision(long value);

	/**
	 * Returns the value of the '<em><b>Sql Distinct Type</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.SQLDistinctType}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.SQLDistinctType#getSqlSimpleType <em>Sql Simple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Distinct types that use this simple type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sql Distinct Type</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLSimpleType_SqlDistinctType()
	 * @see orgomg.cwm.resource.relational.SQLDistinctType#getSqlSimpleType
	 * @model opposite="sqlSimpleType"
	 * @generated
	 */
	EList<SQLDistinctType> getSqlDistinctType();

} // SQLSimpleType
