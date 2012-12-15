/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call With Source And Arguments CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceAndArgumentsCS#getOperationCall <em>Operation Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationCallWithSourceAndArgumentsCS()
 * @model
 * @generated
 */
public interface OperationCallWithSourceAndArgumentsCS extends OperationCallWithSourceExpCS {
	/**
	 * Returns the value of the '<em><b>Operation Call</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Call</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Call</em>' containment reference.
	 * @see #setOperationCall(ImplicitOperationCallCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationCallWithSourceAndArgumentsCS_OperationCall()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ImplicitOperationCallCS getOperationCall();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceAndArgumentsCS#getOperationCall <em>Operation Call</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Call</em>' containment reference.
	 * @see #getOperationCall()
	 * @generated
	 */
	void setOperationCall(ImplicitOperationCallCS value);

} // OperationCallWithSourceAndArgumentsCS
