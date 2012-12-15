/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Path Name Nested CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getTypePathName <em>Type Path Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTypePathNameNestedCS()
 * @model
 * @generated
 */
public interface TypePathNameNestedCS extends TypePathNameCS {
	/**
   * Returns the value of the '<em><b>Namespace</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Namespace</em>' reference.
   * @see #setNamespace(Namespace)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTypePathNameNestedCS_Namespace()
   * @model required="true"
   * @generated
   */
	Namespace getNamespace();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getNamespace <em>Namespace</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Namespace</em>' reference.
   * @see #getNamespace()
   * @generated
   */
	void setNamespace(Namespace value);

	/**
   * Returns the value of the '<em><b>Type Path Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Path Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type Path Name</em>' containment reference.
   * @see #setTypePathName(TypePathNameCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getTypePathNameNestedCS_TypePathName()
   * @model containment="true" required="true"
   * @generated
   */
	TypePathNameCS getTypePathName();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS#getTypePathName <em>Type Path Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Path Name</em>' containment reference.
   * @see #getTypePathName()
   * @generated
   */
	void setTypePathName(TypePathNameCS value);

} // TypePathNameNestedCS
