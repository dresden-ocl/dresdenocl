/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A materialized NamedColumnSet.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.Table#isIsTemporary <em>Is Temporary</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Table#getTemporaryScope <em>Temporary Scope</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Table#isIsSystem <em>Is System</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.Table#getTrigger <em>Trigger</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getTable()
 * @model
 * @generated
 */
public interface Table extends NamedColumnSet {
	/**
	 * Returns the value of the '<em><b>Is Temporary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the table content is temporary. SQL92 standards provide two types of temporary tables (local Temporary and Global Temporary). However, RDBMS products have implemented variations on this theme. It is recommended that the product manufacturers provide specific temporary information (besides the temporaryScope attribute) in their extensions. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Temporary</em>' attribute.
	 * @see #setIsTemporary(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTable_IsTemporary()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsTemporary();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Table#isIsTemporary <em>Is Temporary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Temporary</em>' attribute.
	 * @see #isIsTemporary()
	 * @generated
	 */
	void setIsTemporary(boolean value);

	/**
	 * Returns the value of the '<em><b>Temporary Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This attribute is meaningful only when the isTemporary flag is True [C-1]. The scope indicates when the data of this table are available. "SESSION", "APPLICATION" are examples of possible values. Look at the Scope attribute for Global Temporary tables in the SQL standards for more details.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Temporary Scope</em>' attribute.
	 * @see #setTemporaryScope(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTable_TemporaryScope()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getTemporaryScope();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Table#getTemporaryScope <em>Temporary Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporary Scope</em>' attribute.
	 * @see #getTemporaryScope()
	 * @generated
	 */
	void setTemporaryScope(String value);

	/**
	 * Returns the value of the '<em><b>Is System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the Table is a System Table (generally part of or view on the system catalog).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is System</em>' attribute.
	 * @see #setIsSystem(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTable_IsSystem()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsSystem();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.Table#isIsSystem <em>Is System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is System</em>' attribute.
	 * @see #isIsSystem()
	 * @generated
	 */
	void setIsSystem(boolean value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.Trigger}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.Trigger#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Associates triggers executed during changes to the table.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getTable_Trigger()
	 * @see orgomg.cwm.resource.relational.Trigger#getTable
	 * @model opposite="table"
	 * @generated
	 */
	EList<Trigger> getTrigger();

} // Table
