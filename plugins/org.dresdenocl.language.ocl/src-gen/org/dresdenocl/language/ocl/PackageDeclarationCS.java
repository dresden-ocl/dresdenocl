/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emftext.commons.layout.LayoutInformation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PackageDeclarationCS#getContextDeclarations <em>Context Declarations</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PackageDeclarationCS#getLayoutInformation <em>Layout Information</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPackageDeclarationCS()
 * @model abstract="true"
 * @generated
 */
public interface PackageDeclarationCS extends EObject {
	/**
   * Returns the value of the '<em><b>Context Declarations</b></em>' containment reference list.
   * The list contents are of type {@link org.dresdenocl.language.ocl.ContextDeclarationCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Context Declarations</em>' containment reference list.
   * @see org.dresdenocl.language.ocl.OclPackage#getPackageDeclarationCS_ContextDeclarations()
   * @model containment="true"
   * @generated
   */
	EList<ContextDeclarationCS> getContextDeclarations();

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
   * @see org.dresdenocl.language.ocl.OclPackage#getPackageDeclarationCS_LayoutInformation()
   * @model
   * @generated
   */
	EList<LayoutInformation> getLayoutInformation();

} // PackageDeclarationCS
