/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.objectmodel.core.Attribute;

import orgomg.cwm.resource.relational.enumerations.NullableType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A column in a result set, a view, a table, or an SQLStructuredType.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getPrecision <em>Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getScale <em>Scale</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getIsNullable <em>Is Nullable</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getLength <em>Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getCollationName <em>Collation Name</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getCharacterSetName <em>Character Set Name</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getReferencedTableType <em>Referenced Table Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Column#getOptionScopeColumnSet <em>Option Scope Column Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends Attribute {
	/**
	 * Returns the value of the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total number of digits in the field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precision</em>' attribute.
	 * @see #setPrecision(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_Precision()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getPrecision();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getPrecision <em>Precision</em>}' attribute.
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
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_Scale()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getScale();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(long value);

	/**
	 * Returns the value of the '<em><b>Is Nullable</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.relational.enumerations.NullableType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if null values are valid in this column.
	 * 
	 * Note: Default values for Column data are provided in initialValue in the UML Attribute class (the class from which the Column class inherits).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Nullable</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.NullableType
	 * @see #setIsNullable(NullableType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_IsNullable()
	 * @model
	 * @generated
	 */
	NullableType getIsNullable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getIsNullable <em>Is Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Nullable</em>' attribute.
	 * @see orgomg.cwm.resource.relational.enumerations.NullableType
	 * @see #getIsNullable()
	 * @generated
	 */
	void setIsNullable(NullableType value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The length of fixed length character or byte strings. Maximum length if length is variable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(long)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_Length()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getLength();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(long value);

	/**
	 * Returns the value of the '<em><b>Collation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the collation sequence used to sort the data values in the column.
	 * This applies only to columns whose datatype is a form of character string.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Collation Name</em>' attribute.
	 * @see #setCollationName(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_CollationName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getCollationName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getCollationName <em>Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collation Name</em>' attribute.
	 * @see #getCollationName()
	 * @generated
	 */
	void setCollationName(String value);

	/**
	 * Returns the value of the '<em><b>Character Set Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the character set used for the values in the column.
	 * This field applies only to columns whose datatype is a character string.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Character Set Name</em>' attribute.
	 * @see #setCharacterSetName(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_CharacterSetName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getCharacterSetName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getCharacterSetName <em>Character Set Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Set Name</em>' attribute.
	 * @see #getCharacterSetName()
	 * @generated
	 */
	void setCharacterSetName(String value);

	/**
	 * Returns the value of the '<em><b>Referenced Table Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.SQLStructuredType#getReferencingColumn <em>Referencing Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The column, used in an SQLStructuredType is a REF to a type. This references the REF’ed SQLStructuredType. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referenced Table Type</em>' reference.
	 * @see #setReferencedTableType(SQLStructuredType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_ReferencedTableType()
	 * @see orgomg.cwm.resource.relational.SQLStructuredType#getReferencingColumn
	 * @model opposite="referencingColumn"
	 * @generated
	 */
	SQLStructuredType getReferencedTableType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getReferencedTableType <em>Referenced Table Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Table Type</em>' reference.
	 * @see #getReferencedTableType()
	 * @generated
	 */
	void setReferencedTableType(SQLStructuredType value);

	/**
	 * Returns the value of the '<em><b>Option Scope Column Set</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.NamedColumnSet#getOptionScopeColumn <em>Option Scope Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the NamedColumnSet indicated in the SCOPE clause of the Column definition.
	 * 
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Option Scope Column Set</em>' reference.
	 * @see #setOptionScopeColumnSet(NamedColumnSet)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getColumn_OptionScopeColumnSet()
	 * @see orgomg.cwm.resource.relational.NamedColumnSet#getOptionScopeColumn
	 * @model opposite="optionScopeColumn"
	 * @generated
	 */
	NamedColumnSet getOptionScopeColumnSet();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Column#getOptionScopeColumnSet <em>Option Scope Column Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Option Scope Column Set</em>' reference.
	 * @see #getOptionScopeColumnSet()
	 * @generated
	 */
	void setOptionScopeColumnSet(NamedColumnSet value);

} // Column
