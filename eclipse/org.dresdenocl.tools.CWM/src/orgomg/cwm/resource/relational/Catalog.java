/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Catalog is the unit of logon and identification. It also identifies the scope of SQL statements: the tables contained in a catalog can be used in a single SQL statement.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.Catalog#getDefaultCharacterSetName <em>Default Character Set Name</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Catalog#getDefaultCollationName <em>Default Collation Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getCatalog()
 * @model
 * @generated
 */
public interface Catalog extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Default Character Set Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the default character set used for the values in the column.
	 * This field applies only to columns whose datatype is a character string.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Character Set Name</em>' attribute.
	 * @see #setDefaultCharacterSetName(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getCatalog_DefaultCharacterSetName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getDefaultCharacterSetName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Catalog#getDefaultCharacterSetName <em>Default Character Set Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Character Set Name</em>' attribute.
	 * @see #getDefaultCharacterSetName()
	 * @generated
	 */
	void setDefaultCharacterSetName(String value);

	/**
	 * Returns the value of the '<em><b>Default Collation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the default collation sequence used to sort the data values in the column.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Collation Name</em>' attribute.
	 * @see #setDefaultCollationName(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getCatalog_DefaultCollationName()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getDefaultCollationName();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Catalog#getDefaultCollationName <em>Default Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Collation Name</em>' attribute.
	 * @see #getDefaultCollationName()
	 * @generated
	 */
	void setDefaultCollationName(String value);

} // Catalog
