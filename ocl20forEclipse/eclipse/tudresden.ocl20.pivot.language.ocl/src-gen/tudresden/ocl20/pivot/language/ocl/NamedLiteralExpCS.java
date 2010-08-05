/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS#getNamedElement <em>Named Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNamedLiteralExpCS()
 * @model
 * @generated
 */
public interface NamedLiteralExpCS extends OclExpressionCS {
	/**
	 * Returns the value of the '<em><b>Named Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Named Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Named Element</em>' reference.
	 * @see #setNamedElement(NamedElement)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getNamedLiteralExpCS_NamedElement()
	 * @model required="true"
	 * @generated
	 */
	NamedElement getNamedElement();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS#getNamedElement <em>Named Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Named Element</em>' reference.
	 * @see #getNamedElement()
	 * @generated
	 */
	void setNamedElement(NamedElement value);

} // NamedLiteralExpCS
