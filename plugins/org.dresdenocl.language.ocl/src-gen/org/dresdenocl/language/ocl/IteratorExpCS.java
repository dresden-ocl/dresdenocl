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
 * A representation of the model object '<em><b>Iterator Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.IteratorExpCS#getIteratorName <em>Iterator Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IteratorExpCS#getIteratorVariables <em>Iterator Variables</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IteratorExpCS#getBodyExpression <em>Body Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getIteratorExpCS()
 * @model
 * @generated
 */
public interface IteratorExpCS extends LoopExpCS, ImplicitFeatureCallCS {
	/**
   * Returns the value of the '<em><b>Iterator Name</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Iterator Name</em>' attribute.
   * @see #setIteratorName(String)
   * @see org.dresdenocl.language.ocl.OclPackage#getIteratorExpCS_IteratorName()
   * @model required="true"
   * @generated
   */
	String getIteratorName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.IteratorExpCS#getIteratorName <em>Iterator Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Iterator Name</em>' attribute.
   * @see #getIteratorName()
   * @generated
   */
	void setIteratorName(String value);

	/**
   * Returns the value of the '<em><b>Iterator Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.dresdenocl.language.ocl.IteratorExpVariableCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iterator Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Iterator Variables</em>' containment reference list.
   * @see org.dresdenocl.language.ocl.OclPackage#getIteratorExpCS_IteratorVariables()
   * @model containment="true" upper="2"
   * @generated
   */
	EList<IteratorExpVariableCS> getIteratorVariables();

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
   * @see org.dresdenocl.language.ocl.OclPackage#getIteratorExpCS_BodyExpression()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getBodyExpression();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.IteratorExpCS#getBodyExpression <em>Body Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body Expression</em>' containment reference.
   * @see #getBodyExpression()
   * @generated
   */
	void setBodyExpression(OclExpressionCS value);

} // IteratorExpCS
