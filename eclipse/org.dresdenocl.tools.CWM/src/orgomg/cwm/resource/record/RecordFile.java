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
 * A representation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A RecordFile is the definition of a file. It may have one or more RecordDefs, defining the structure of the records in the file. Each of these RecordDefs defines a valid structure for records in the file. Subclasses of RecordFile in extensions to support specific languages and systems may be used to represent specific types of files such as COBOL CopyLib files and C-language header files.
 * 
 * Physical deployments of a RecordFile can be found via the DataManagerDataPackage association in the SoftwareDeployment package .
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.record.RecordFile#isIsSelfDescribing <em>Is Self Describing</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordFile#getRecordDelimiter <em>Record Delimiter</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordFile#getSkipRecords <em>Skip Records</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.RecordFile#getRecord <em>Record</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.record.RecordPackage#getRecordFile()
 * @model
 * @generated
 */
public interface RecordFile extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Is Self Describing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if the contents of fields in the first record of the file contain field names applicable to subsequent records.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Self Describing</em>' attribute.
	 * @see #setIsSelfDescribing(boolean)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordFile_IsSelfDescribing()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsSelfDescribing();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordFile#isIsSelfDescribing <em>Is Self Describing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Self Describing</em>' attribute.
	 * @see #isIsSelfDescribing()
	 * @generated
	 */
	void setIsSelfDescribing(boolean value);

	/**
	 * Returns the value of the '<em><b>Record Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the value that serves as a logical end-of-record indication in a stream-oriented file. A common examples include the usage of carriage-return characters and carriage-return/line-feed character pairs as new-line characters in ASCII text files.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Record Delimiter</em>' attribute.
	 * @see #setRecordDelimiter(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordFile_RecordDelimiter()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getRecordDelimiter();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordFile#getRecordDelimiter <em>Record Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Record Delimiter</em>' attribute.
	 * @see #getRecordDelimiter()
	 * @generated
	 */
	void setRecordDelimiter(long value);

	/**
	 * Returns the value of the '<em><b>Skip Records</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of records to ignore at the beginning of a file. The specific semantics of records that are skipped may be beyond the scope of CWM.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Skip Records</em>' attribute.
	 * @see #setSkipRecords(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordFile_SkipRecords()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getSkipRecords();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.RecordFile#getSkipRecords <em>Skip Records</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Skip Records</em>' attribute.
	 * @see #getSkipRecords()
	 * @generated
	 */
	void setSkipRecords(long value);

	/**
	 * Returns the value of the '<em><b>Record</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.record.RecordDef}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.record.RecordDef#getFile <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of records stored in the file. The ordering may indicate the physical ordering of records with different layouts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Record</em>' reference list.
	 * @see orgomg.cwm.resource.record.RecordPackage#getRecordFile_Record()
	 * @see orgomg.cwm.resource.record.RecordDef#getFile
	 * @model opposite="file"
	 * @generated
	 */
	EList<RecordDef> getRecord();

} // RecordFile
