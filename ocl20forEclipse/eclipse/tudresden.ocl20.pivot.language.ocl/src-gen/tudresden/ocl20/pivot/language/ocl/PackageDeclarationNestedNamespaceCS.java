/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.emftext.commons.layout.LayoutInformation;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Declaration Nested Namespace CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getLayoutInformation <em>Layout Information</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationNestedNamespaceCS()
 * @model
 * @generated
 */
public interface PackageDeclarationNestedNamespaceCS extends EObject {
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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationNestedNamespaceCS_Namespace()
   * @model required="true"
   * @generated
   */
	Namespace getNamespace();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNamespace <em>Namespace</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Namespace</em>' reference.
   * @see #getNamespace()
   * @generated
   */
	void setNamespace(Namespace value);

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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationNestedNamespaceCS_NestedNamespace()
   * @model containment="true"
   * @generated
   */
	PackageDeclarationNestedNamespaceCS getNestedNamespace();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS#getNestedNamespace <em>Nested Namespace</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nested Namespace</em>' containment reference.
   * @see #getNestedNamespace()
   * @generated
   */
	void setNestedNamespace(PackageDeclarationNestedNamespaceCS value);

	/**
   * Returns the value of the '<em><b>Layout Information</b></em>' reference list.
   * The list contents are of type {@link org.emftext.commons.layout.LayoutInformation}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layout Information</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Layout Information</em>' reference list.
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPackageDeclarationNestedNamespaceCS_LayoutInformation()
   * @model
   * @generated
   */
	EList<LayoutInformation> getLayoutInformation();

} // PackageDeclarationNestedNamespaceCS
