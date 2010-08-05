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
 * A representation of the model object '<em><b>SQL Structured Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Datatype defined as Structured Type, per [SQL] standard.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.SQLStructuredType#getColumnSet <em>Column Set</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.SQLStructuredType#getReferencingColumn <em>Referencing Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLStructuredType()
 * @model
 * @generated
 */
public interface SQLStructuredType extends orgomg.cwm.objectmodel.core.Class, SQLDataType {
	/**
	 * Returns the value of the '<em><b>Column Set</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.NamedColumnSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.NamedColumnSet#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A NamedColumnSet created as of this type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Column Set</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLStructuredType_ColumnSet()
	 * @see orgomg.cwm.resource.relational.NamedColumnSet#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<NamedColumnSet> getColumnSet();

	/**
	 * Returns the value of the '<em><b>Referencing Column</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.relational.Column}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.relational.Column#getReferencedTableType <em>Referenced Table Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to a column of an SQLStructuredType (otherType) which is created with a REF clause referencing this SQLStructuredType (thisType). Note that in general, otherType and thisType are two different instances of SQLStructuredType.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Referencing Column</em>' reference list.
	 * @see orgomg.cwm.resource.relational.RelationalPackage#getSQLStructuredType_ReferencingColumn()
	 * @see orgomg.cwm.resource.relational.Column#getReferencedTableType
	 * @model opposite="referencedTableType"
	 * @generated
	 */
	EList<Column> getReferencingColumn();

} // SQLStructuredType
