/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS#getTupleType <em>Tuple Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTupleTypeLiteralExpCS()
 * @model
 * @generated
 */
public interface TupleTypeLiteralExpCS extends LiteralExpCS {
	/**
   * Returns the value of the '<em><b>Tuple Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuple Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Tuple Type</em>' containment reference.
   * @see #setTupleType(TupleTypeCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTupleTypeLiteralExpCS_TupleType()
   * @model containment="true" required="true"
   * @generated
   */
	TupleTypeCS getTupleType();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS#getTupleType <em>Tuple Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuple Type</em>' containment reference.
   * @see #getTupleType()
   * @generated
   */
	void setTupleType(TupleTypeCS value);

} // TupleTypeLiteralExpCS
