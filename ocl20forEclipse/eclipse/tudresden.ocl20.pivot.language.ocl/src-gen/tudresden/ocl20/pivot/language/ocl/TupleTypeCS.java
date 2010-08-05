/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.TupleTypeCS#getVariableDeclarationList <em>Variable Declaration List</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTupleTypeCS()
 * @model
 * @generated
 */
public interface TupleTypeCS extends TypeCS {
	/**
	 * Returns the value of the '<em><b>Variable Declaration List</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declaration List</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declaration List</em>' containment reference.
	 * @see #setVariableDeclarationList(VariableDeclarationWithoutInitListCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTupleTypeCS_VariableDeclarationList()
	 * @model containment="true"
	 * @generated
	 */
	VariableDeclarationWithoutInitListCS getVariableDeclarationList();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeCS#getVariableDeclarationList <em>Variable Declaration List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declaration List</em>' containment reference.
	 * @see #getVariableDeclarationList()
	 * @generated
	 */
	void setVariableDeclarationList(VariableDeclarationWithoutInitListCS value);

} // TupleTypeCS
