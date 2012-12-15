/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pre Post Or Body Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS#getName <em>Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPrePostOrBodyDeclarationCS()
 * @model abstract="true"
 * @generated
 */
public interface PrePostOrBodyDeclarationCS extends EObject {
	/**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(SimpleNameCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getPrePostOrBodyDeclarationCS_Name()
   * @model containment="true"
   * @generated
   */
	SimpleNameCS getName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
	void setName(SimpleNameCS value);

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
   * @see org.dresdenocl.language.ocl.OclPackage#getPrePostOrBodyDeclarationCS_OclExpression()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getOclExpression();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS#getOclExpression <em>Ocl Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ocl Expression</em>' containment reference.
   * @see #getOclExpression()
   * @generated
   */
	void setOclExpression(OclExpressionCS value);

} // PrePostOrBodyDeclarationCS
