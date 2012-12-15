/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition Exp Operation CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOperation <em>Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getEqual <em>Equal</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpOperationCS()
 * @model
 * @generated
 */
public interface DefinitionExpOperationCS extends DefinitionExpPartCS {
	/**
   * Returns the value of the '<em><b>Operation</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Operation</em>' containment reference.
   * @see #setOperation(OperationDefinitionInDefCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpOperationCS_Operation()
   * @model containment="true" required="true"
   * @generated
   */
	OperationDefinitionInDefCS getOperation();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOperation <em>Operation</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operation</em>' containment reference.
   * @see #getOperation()
   * @generated
   */
	void setOperation(OperationDefinitionInDefCS value);

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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpOperationCS_Equal()
   * @model required="true"
   * @generated
   */
	String getEqual();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getEqual <em>Equal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Equal</em>' attribute.
   * @see #getEqual()
   * @generated
   */
	void setEqual(String value);

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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpOperationCS_OclExpression()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getOclExpression();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS#getOclExpression <em>Ocl Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ocl Expression</em>' containment reference.
   * @see #getOclExpression()
   * @generated
   */
	void setOclExpression(OclExpressionCS value);

} // DefinitionExpOperationCS
