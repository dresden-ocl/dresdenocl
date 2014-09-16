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
 * A representation of the model object '<em><b>Query Column Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The result set of a query.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.QueryColumnSet#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getQueryColumnSet()
 * @model
 * @generated
 */
public interface QueryColumnSet extends ColumnSet {
	/**
	 * Returns the value of the '<em><b>Query</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The query expression generating this result. The language attribute of the expression should generally begin with "SQL"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Query</em>' containment reference.
	 * @see #setQuery(QueryExpression)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getQueryColumnSet_Query()
	 * @model containment="true"
	 * @generated
	 */
	QueryExpression getQuery();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.QueryColumnSet#getQuery <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' containment reference.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(QueryExpression value);

} // QueryColumnSet
