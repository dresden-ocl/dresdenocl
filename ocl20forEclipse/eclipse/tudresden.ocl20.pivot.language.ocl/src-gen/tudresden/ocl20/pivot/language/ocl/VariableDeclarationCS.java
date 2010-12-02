/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS#getVariableName <em>Variable Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationCS()
 * @model abstract="true"
 * @generated
 */
public interface VariableDeclarationCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Name</em>' containment reference.
	 * @see #setVariableName(SimpleNameCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getVariableDeclarationCS_VariableName()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SimpleNameCS getVariableName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS#getVariableName <em>Variable Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Name</em>' containment reference.
	 * @see #getVariableName()
	 * @generated
	 */
	void setVariableName(SimpleNameCS value);

} // VariableDeclarationCS
