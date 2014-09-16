/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call With Source Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.OperationCallWithSourceExpCS#getSource <em>Source</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.OperationCallWithSourceExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallWithSourceExpCS()
 * @model
 * @generated
 */
public interface OperationCallWithSourceExpCS extends OperationCallExpCS {
	/**
   * Returns the value of the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' containment reference.
   * @see #setSource(OclExpressionCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallWithSourceExpCS_Source()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getSource();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationCallWithSourceExpCS#getSource <em>Source</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' containment reference.
   * @see #getSource()
   * @generated
   */
	void setSource(OclExpressionCS value);

	/**
   * Returns the value of the '<em><b>Is Marked Pre</b></em>' attribute.
   * The default value is <code>"false"</code>.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Marked Pre</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Marked Pre</em>' attribute.
   * @see #setIsMarkedPre(boolean)
   * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallWithSourceExpCS_IsMarkedPre()
   * @model default="false"
   * @generated
   */
	boolean isIsMarkedPre();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationCallWithSourceExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
   * @see #isIsMarkedPre()
   * @generated
   */
	void setIsMarkedPre(boolean value);

} // OperationCallWithSourceExpCS
