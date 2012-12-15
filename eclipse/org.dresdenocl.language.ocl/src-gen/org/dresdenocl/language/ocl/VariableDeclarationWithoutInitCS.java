/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Without Init CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getVariableDeclarationWithoutInitCS()
 * @model
 * @generated
 */
public interface VariableDeclarationWithoutInitCS extends VariableDeclarationCS {
	/**
   * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' containment reference.
   * @see #setTypeName(TypeCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getVariableDeclarationWithoutInitCS_TypeName()
   * @model containment="true" required="true"
   * @generated
   */
	TypeCS getTypeName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS#getTypeName <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Name</em>' containment reference.
   * @see #getTypeName()
   * @generated
   */
	void setTypeName(TypeCS value);

} // VariableDeclarationWithoutInitCS
