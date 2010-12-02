/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Without Init List CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS#getVariableDeclarations <em>Variable Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithoutInitListCS()
 * @model
 * @generated
 */
public interface VariableDeclarationWithoutInitListCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declarations</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithoutInitListCS_VariableDeclarations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<VariableDeclarationWithoutInitCS> getVariableDeclarations();

} // VariableDeclarationWithoutInitListCS
