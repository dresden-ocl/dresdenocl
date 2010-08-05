/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import tudresden.ocl20.pivot.pivotmodel.Type;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration With Init CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getInitialization <em>Initialization</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getEqual <em>Equal</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithInitCS()
 * @model
 * @generated
 */
public interface VariableDeclarationWithInitCS extends VariableDeclarationCS {
	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' containment reference.
	 * @see #setTypeName(TypeCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithInitCS_TypeName()
	 * @model containment="true"
	 * @generated
	 */
	TypeCS getTypeName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getTypeName <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' containment reference.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(TypeCS value);

	/**
	 * Returns the value of the '<em><b>Initialization</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initialization</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initialization</em>' containment reference.
	 * @see #setInitialization(OclExpressionCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithInitCS_Initialization()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getInitialization();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getInitialization <em>Initialization</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initialization</em>' containment reference.
	 * @see #getInitialization()
	 * @generated
	 */
	void setInitialization(OclExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Equal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Equal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Equal</em>' attribute.
	 * @see #setEqual(String)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationWithInitCS_Equal()
	 * @model required="true"
	 * @generated
	 */
	String getEqual();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS#getEqual <em>Equal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Equal</em>' attribute.
	 * @see #getEqual()
	 * @generated
	 */
	void setEqual(String value);

} // VariableDeclarationWithInitCS
