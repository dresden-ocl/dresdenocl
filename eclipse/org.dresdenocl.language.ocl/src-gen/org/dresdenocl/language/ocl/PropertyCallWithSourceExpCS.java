/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call With Source Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PropertyCallWithSourceExpCS#getSource <em>Source</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PropertyCallWithSourceExpCS#getPropertyCall <em>Property Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallWithSourceExpCS()
 * @model
 * @generated
 */
public interface PropertyCallWithSourceExpCS extends PropertyCallExpCS {
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
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallWithSourceExpCS_Source()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getSource();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallWithSourceExpCS#getSource <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' containment reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(OclExpressionCS value);

	/**
	 * Returns the value of the '<em><b>Property Call</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Call</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Call</em>' containment reference.
	 * @see #setPropertyCall(ImplicitPropertyCallCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallWithSourceExpCS_PropertyCall()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ImplicitPropertyCallCS getPropertyCall();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallWithSourceExpCS#getPropertyCall <em>Property Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Call</em>' containment reference.
	 * @see #getPropertyCall()
	 * @generated
	 */
	void setPropertyCall(ImplicitPropertyCallCS value);

} // PropertyCallWithSourceExpCS
