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
 * A representation of the model object '<em><b>Classifier Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getInvariantsAndDefinitions <em>Invariants And Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getClassifierContextDeclarationCS()
 * @model
 * @generated
 */
public interface ClassifierContextDeclarationCS extends ContextDeclarationCS {
	/**
   * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' containment reference.
   * @see #setTypeName(TypeCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getClassifierContextDeclarationCS_TypeName()
   * @model containment="true" required="true"
   * @generated
   */
	TypeCS getTypeName();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS#getTypeName <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Name</em>' containment reference.
   * @see #getTypeName()
   * @generated
   */
	void setTypeName(TypeCS value);

	/**
   * Returns the value of the '<em><b>Invariants And Definitions</b></em>' containment reference list.
   * The list contents are of type {@link tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invariants And Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Invariants And Definitions</em>' containment reference list.
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getClassifierContextDeclarationCS_InvariantsAndDefinitions()
   * @model containment="true" required="true"
   * @generated
   */
	EList<InvariantOrDefinitionCS> getInvariantsAndDefinitions();

} // ClassifierContextDeclarationCS
