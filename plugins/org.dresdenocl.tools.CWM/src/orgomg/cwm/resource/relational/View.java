/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational;

import orgomg.cwm.foundation.datatypes.QueryExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A view is a non-materialized set of rows, defined by the associated query.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.View#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.View#isCheckOption <em>Check Option</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.View#getQueryExpression <em>Query Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getView()
 * @model
 * @generated
 */
public interface View extends NamedColumnSet {
	/**
	 * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether the underlying tables can be updated through an update to this View.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Read Only</em>' attribute.
	 * @see #setIsReadOnly(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getView_IsReadOnly()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsReadOnly();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.View#isIsReadOnly <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Read Only</em>' attribute.
	 * @see #isIsReadOnly()
	 * @generated
	 */
	void setIsReadOnly(boolean value);

	/**
	 * Returns the value of the '<em><b>Check Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This field is meaningful only if the view is not ReadOnly. CheckOption indicates that the RDBMS will validate that changes made to the data verify the view filtering condition and belong to the view result set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Check Option</em>' attribute.
	 * @see #setCheckOption(boolean)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getView_CheckOption()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isCheckOption();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.View#isCheckOption <em>Check Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Option</em>' attribute.
	 * @see #isCheckOption()
	 * @generated
	 */
	void setCheckOption(boolean value);

	/**
	 * Returns the value of the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query associated with the View.
	 * 
	 * The query result must match the set of Columns associated with the View (in parent class ColumnSet)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Query Expression</em>' containment reference.
	 * @see #setQueryExpression(QueryExpression)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getView_QueryExpression()
	 * @model containment="true"
	 * @generated
	 */
	QueryExpression getQueryExpression();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.View#getQueryExpression <em>Query Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Expression</em>' containment reference.
	 * @see #getQueryExpression()
	 * @generated
	 */
	void setQueryExpression(QueryExpression value);

} // View
