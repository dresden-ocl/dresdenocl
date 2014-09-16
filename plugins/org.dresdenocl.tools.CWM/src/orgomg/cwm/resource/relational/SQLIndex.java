/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.foundation.keysindexes.Index;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An Index on a table.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.SQLIndex#getFilterCondition <em>Filter Condition</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLIndex#isIsNullable <em>Is Nullable</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLIndex#isAutoUpdate <em>Auto Update</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLIndex()
 * @model
 * @generated
 */
public interface SQLIndex extends Index {
	/**
	 * Returns the value of the '<em><b>Filter Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Which subset of the table is indexed
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Filter Condition</em>' attribute.
	 * @see #setFilterCondition(String)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLIndex_FilterCondition()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFilterCondition();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLIndex#getFilterCondition <em>Filter Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filter Condition</em>' attribute.
	 * @see #getFilterCondition()
	 * @generated
	 */
	void setFilterCondition(String value);

	/**
	 * Returns the value of the '<em><b>Is Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Entries in this index can be null
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Nullable</em>' attribute.
	 * @see #setIsNullable(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLIndex_IsNullable()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsNullable();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLIndex#isIsNullable <em>Is Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Nullable</em>' attribute.
	 * @see #isIsNullable()
	 * @generated
	 */
	void setIsNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Auto Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The index is updated automatically
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Auto Update</em>' attribute.
	 * @see #setAutoUpdate(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLIndex_AutoUpdate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isAutoUpdate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.SQLIndex#isAutoUpdate <em>Auto Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Update</em>' attribute.
	 * @see #isAutoUpdate()
	 * @generated
	 */
	void setAutoUpdate(boolean value);

} // SQLIndex
