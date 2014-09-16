/**
 */
package org.dresdenocl.language.ocl;

import org.dresdenocl.pivotmodel.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Name Simple CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PathNameSimpleCS#getNamedElement <em>Named Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPathNameSimpleCS()
 * @model
 * @generated
 */
public interface PathNameSimpleCS extends PathNameCS {
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
   * @see org.dresdenocl.language.ocl.OclPackage#getPathNameSimpleCS_NamedElement()
   * @model required="true"
   * @generated
   */
	NamedElement getNamedElement();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PathNameSimpleCS#getNamedElement <em>Named Element</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Named Element</em>' reference.
   * @see #getNamedElement()
   * @generated
   */
	void setNamedElement(NamedElement value);

} // PathNameSimpleCS
