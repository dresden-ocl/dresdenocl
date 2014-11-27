/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.TupleLiteralExpCS#getVariableDeclarations <em>Variable Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getTupleLiteralExpCS()
 * @model
 * @generated
 */
public interface TupleLiteralExpCS extends LiteralExpCS {
	/**
	 * Returns the value of the '<em><b>Variable Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declarations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declarations</em>' containment reference.
	 * @see #setVariableDeclarations(VariableDeclarationWithInitListCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getTupleLiteralExpCS_VariableDeclarations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableDeclarationWithInitListCS getVariableDeclarations();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.TupleLiteralExpCS#getVariableDeclarations <em>Variable Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declarations</em>' containment reference.
	 * @see #getVariableDeclarations()
	 * @generated
	 */
	void setVariableDeclarations(VariableDeclarationWithInitListCS value);

} // TupleLiteralExpCS
