/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration With Init List CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS#getVariableDeclarations <em>Variable Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getVariableDeclarationWithInitListCS()
 * @model
 * @generated
 */
public interface VariableDeclarationWithInitListCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.language.ocl.VariableDeclarationWithInitCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declarations</em>' containment reference list.
	 * @see org.dresdenocl.language.ocl.OclPackage#getVariableDeclarationWithInitListCS_VariableDeclarations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<VariableDeclarationWithInitCS> getVariableDeclarations();

} // VariableDeclarationWithInitListCS
