/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getOperation <em>Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getPrePostOrBodyDeclarations <em>Pre Post Or Body Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationContextDeclarationCS()
 * @model
 * @generated
 */
public interface OperationContextDeclarationCS extends ContextDeclarationCS {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' containment reference.
	 * @see #setOperation(OperationDefinitionInContextCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationContextDeclarationCS_Operation()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OperationDefinitionInContextCS getOperation();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS#getOperation <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' containment reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(OperationDefinitionInContextCS value);

	/**
	 * Returns the value of the '<em><b>Pre Post Or Body Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Post Or Body Declarations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Post Or Body Declarations</em>' containment reference list.
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getOperationContextDeclarationCS_PrePostOrBodyDeclarations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<PrePostOrBodyDeclarationCS> getPrePostOrBodyDeclarations();

} // OperationContextDeclarationCS
