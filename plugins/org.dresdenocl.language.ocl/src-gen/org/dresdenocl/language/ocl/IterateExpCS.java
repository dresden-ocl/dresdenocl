/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterate Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.IterateExpCS#getIteratorVariable <em>Iterator Variable</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IterateExpCS#getResultVariable <em>Result Variable</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IterateExpCS#getBodyExpression <em>Body Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getIterateExpCS()
 * @model
 * @generated
 */
public interface IterateExpCS extends LoopExpCS, ImplicitFeatureCallCS {
	/**
	 * Returns the value of the '<em><b>Iterator Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iterator Variable</em>' containment reference.
	 * @see #setIteratorVariable(IteratorExpVariableCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIterateExpCS_IteratorVariable()
	 * @model containment="true"
	 * @generated
	 */
	IteratorExpVariableCS getIteratorVariable();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IterateExpCS#getIteratorVariable <em>Iterator Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterator Variable</em>' containment reference.
	 * @see #getIteratorVariable()
	 * @generated
	 */
	void setIteratorVariable(IteratorExpVariableCS value);

	/**
	 * Returns the value of the '<em><b>Result Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Variable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Variable</em>' containment reference.
	 * @see #setResultVariable(VariableDeclarationWithInitCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIterateExpCS_ResultVariable()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableDeclarationWithInitCS getResultVariable();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IterateExpCS#getResultVariable <em>Result Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Variable</em>' containment reference.
	 * @see #getResultVariable()
	 * @generated
	 */
	void setResultVariable(VariableDeclarationWithInitCS value);

	/**
	 * Returns the value of the '<em><b>Body Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Expression</em>' containment reference.
	 * @see #setBodyExpression(OclExpressionCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIterateExpCS_BodyExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getBodyExpression();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IterateExpCS#getBodyExpression <em>Body Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Expression</em>' containment reference.
	 * @see #getBodyExpression()
	 * @generated
	 */
	void setBodyExpression(OclExpressionCS value);

} // IterateExpCS
