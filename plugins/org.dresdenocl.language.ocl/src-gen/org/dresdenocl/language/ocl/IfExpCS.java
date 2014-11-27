/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.IfExpCS#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IfExpCS#getThenBranch <em>Then Branch</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.IfExpCS#getElseBranch <em>Else Branch</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getIfExpCS()
 * @model
 * @generated
 */
public interface IfExpCS extends OclExpressionCS {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(OclExpressionCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIfExpCS_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getCondition();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IfExpCS#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(OclExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Then Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Branch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Branch</em>' containment reference.
	 * @see #setThenBranch(OclExpressionCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIfExpCS_ThenBranch()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getThenBranch();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IfExpCS#getThenBranch <em>Then Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Branch</em>' containment reference.
	 * @see #getThenBranch()
	 * @generated
	 */
	void setThenBranch(OclExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Else Branch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Branch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Branch</em>' containment reference.
	 * @see #setElseBranch(OclExpressionCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getIfExpCS_ElseBranch()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getElseBranch();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.IfExpCS#getElseBranch <em>Else Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Branch</em>' containment reference.
	 * @see #getElseBranch()
	 * @generated
	 */
	void setElseBranch(OclExpressionCS value);

} // IfExpCS
