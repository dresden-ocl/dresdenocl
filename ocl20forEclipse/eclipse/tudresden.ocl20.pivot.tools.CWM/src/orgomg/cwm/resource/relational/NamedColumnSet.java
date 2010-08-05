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
 * A representation of the model object '<em><b>Named Column Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A catalogued set of columns, which may be Table or View.
 * 
 * Note for typed tables: It is assumed that the typed table will own a set of columns conforming to the type they are OF. This set of columns allows the manipulation of the table by products which ignore this [SQL] extension. It also allows the columns of type REF, to be copied to a column with a SCOPE reference.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.NamedColumnSet#getUsingTrigger <em>Using Trigger</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.NamedColumnSet#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.NamedColumnSet#getOptionScopeColumn <em>Option Scope Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getNamedColumnSet()
 * @model
 * @generated
 */
public interface NamedColumnSet extends ColumnSet {
	/**
	 * Returns the value of the '<em><b>Using Trigger</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.Trigger}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.Trigger#getUsedColumnSet <em>Used Column Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Trigger which references this table in its expression
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Using Trigger</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getNamedColumnSet_UsingTrigger()
	 * @see orgomg.cwm.resource.relational.Trigger#getUsedColumnSet
	 * @model opposite="usedColumnSet"
	 * @generated
	 */
	EList<Trigger> getUsingTrigger();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.SQLStructuredType#getColumnSet <em>Column Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For typed Tables and Views, reference to the base SQLStructuredType.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(SQLStructuredType)
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getNamedColumnSet_Type()
	 * @see orgomg.cwm.resource.relational.SQLStructuredType#getColumnSet
	 * @model opposite="columnSet"
	 * @generated
	 */
	SQLStructuredType getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.relational.NamedColumnSet#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(SQLStructuredType value);

	/**
	 * Returns the value of the '<em><b>Option Scope Column</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.Column}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.Column#getOptionScopeColumnSet <em>Option Scope Column Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the Column which contains theSCOPE clause.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Option Scope Column</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getNamedColumnSet_OptionScopeColumn()
	 * @see orgomg.cwm.resource.relational.Column#getOptionScopeColumnSet
	 * @model opposite="optionScopeColumnSet"
	 * @generated
	 */
	EList<Column> getOptionScopeColumn();

} // NamedColumnSet
