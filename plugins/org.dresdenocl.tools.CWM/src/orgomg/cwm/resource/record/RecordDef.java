/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.record;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A RecordDef is an ordered collection of Fields representing the structure of a Record.
 * 
 * Examples of RecordDefs include definitions of
 * 
 *     language-specific data structures
 *     database records
 *     IMS segments
 * 
 * The internal structure of a RecordDef instance is constructed by adding Field instances as features (using the ElementOwnership association) and pointing each Field instance’s inherited type reference to the Classifier instance representing the Field’s data type. The referenced instance can be either a primitive data type (an instance of DataType, such as "integer") or a structured data type (such as a Group instance).
 * 
 * Refer to the example for more details of the relationships between RecordDefs, Fields, Records, and their values.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.record.RecordDef#getFieldDelimiter <em>Field Delimiter</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordDef#isIsFixedWidth <em>Is Fixed Width</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordDef#getTextDelimiter <em>Text Delimiter</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordDef#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.record.RecordPackage#getRecordDef()
 * @model
 * @generated
 */
public interface RecordDef extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Field Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of a fieldDelimiter used to separate field values in an input stream.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Field Delimiter</em>' attribute.
	 * @see #setFieldDelimiter(String)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordDef_FieldDelimiter()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFieldDelimiter();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordDef#getFieldDelimiter <em>Field Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Delimiter</em>' attribute.
	 * @see #getFieldDelimiter()
	 * @generated
	 */
	void setFieldDelimiter(String value);

	/**
	 * Returns the value of the '<em><b>Is Fixed Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if the record is fixed length. Otherwise, the record can be of variable length.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Fixed Width</em>' attribute.
	 * @see #setIsFixedWidth(boolean)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordDef_IsFixedWidth()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsFixedWidth();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordDef#isIsFixedWidth <em>Is Fixed Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Fixed Width</em>' attribute.
	 * @see #isIsFixedWidth()
	 * @generated
	 */
	void setIsFixedWidth(boolean value);

	/**
	 * Returns the value of the '<em><b>Text Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The delimiter of a text string in the record, such as a quote.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text Delimiter</em>' attribute.
	 * @see #setTextDelimiter(String)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordDef_TextDelimiter()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getTextDelimiter();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordDef#getTextDelimiter <em>Text Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text Delimiter</em>' attribute.
	 * @see #getTextDelimiter()
	 * @generated
	 */
	void setTextDelimiter(String value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.record.RecordFile}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.record.RecordFile#getRecord <em>Record</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of files in which a record is stored.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>File</em>' reference list.
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordDef_File()
	 * @see orgomg.cwm.resource.record.RecordFile#getRecord
	 * @model opposite="record"
	 * @generated
	 */
	EList<RecordFile> getFile();

} // RecordDef
