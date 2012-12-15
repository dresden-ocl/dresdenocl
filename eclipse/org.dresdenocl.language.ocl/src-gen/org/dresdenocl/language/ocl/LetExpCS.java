/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Let Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.LetExpCS#getVariableDeclarations <em>Variable Declarations</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.LetExpCS#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getLetExpCS()
 * @model
 * @generated
 */
public interface LetExpCS extends OclExpressionCS {
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
   * @see org.dresdenocl.language.ocl.OclPackage#getLetExpCS_VariableDeclarations()
   * @model containment="true" required="true"
   * @generated
   */
	EList<VariableDeclarationWithInitCS> getVariableDeclarations();

	/**
   * Returns the value of the '<em><b>Ocl Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Ocl Expression</em>' containment reference.
   * @see #setOclExpression(OclExpressionCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getLetExpCS_OclExpression()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getOclExpression();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.LetExpCS#getOclExpression <em>Ocl Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ocl Expression</em>' containment reference.
   * @see #getOclExpression()
   * @generated
   */
	void setOclExpression(OclExpressionCS value);

} // LetExpCS
