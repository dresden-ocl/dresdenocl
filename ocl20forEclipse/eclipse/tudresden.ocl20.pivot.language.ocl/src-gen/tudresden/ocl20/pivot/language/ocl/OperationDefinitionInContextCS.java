/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Definition In Context CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationDefinitionInContextCS()
 * @model
 * @generated
 */
public interface OperationDefinitionInContextCS extends OperationDefinitionCS {

	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' containment reference.
	 * @see #setTypeName(TypePathNameCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationDefinitionInContextCS_TypeName()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypePathNameCS getTypeName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS#getTypeName <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' containment reference.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(TypePathNameCS value);

} // OperationDefinitionInContextCS
