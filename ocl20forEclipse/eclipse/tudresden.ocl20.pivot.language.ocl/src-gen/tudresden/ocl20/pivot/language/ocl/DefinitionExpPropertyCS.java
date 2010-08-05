/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Definition Exp Property CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS#getVariableDeclaration <em>Variable Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpPropertyCS()
 * @model
 * @generated
 */
public interface DefinitionExpPropertyCS extends DefinitionExpPartCS {
	/**
	 * Returns the value of the '<em><b>Variable Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declaration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declaration</em>' containment reference.
	 * @see #setVariableDeclaration(VariableDeclarationWithInitCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpPropertyCS_VariableDeclaration()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableDeclarationWithInitCS getVariableDeclaration();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS#getVariableDeclaration <em>Variable Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declaration</em>' containment reference.
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	void setVariableDeclaration(VariableDeclarationWithInitCS value);

} // DefinitionExpPropertyCS
