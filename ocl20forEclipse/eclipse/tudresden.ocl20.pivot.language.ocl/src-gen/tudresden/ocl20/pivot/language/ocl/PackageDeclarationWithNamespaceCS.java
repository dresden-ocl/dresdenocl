/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Declaration With Namespace CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationWithNamespaceCS()
 * @model
 * @generated
 */
public interface PackageDeclarationWithNamespaceCS extends PackageDeclarationCS {
	/**
   * Returns the value of the '<em><b>Nested Namespace</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Namespace</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Nested Namespace</em>' containment reference.
   * @see #setNestedNamespace(PackageDeclarationNestedNamespaceCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationWithNamespaceCS_NestedNamespace()
   * @model containment="true" required="true"
   * @generated
   */
	PackageDeclarationNestedNamespaceCS getNestedNamespace();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nested Namespace</em>' containment reference.
   * @see #getNestedNamespace()
   * @generated
   */
	void setNestedNamespace(PackageDeclarationNestedNamespaceCS value);

} // PackageDeclarationWithNamespaceCS
