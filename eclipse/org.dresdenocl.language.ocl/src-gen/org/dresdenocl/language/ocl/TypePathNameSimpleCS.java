/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Path Name Simple CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.TypePathNameSimpleCS#getTypeName <em>Type Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getTypePathNameSimpleCS()
 * @model
 * @generated
 */
public interface TypePathNameSimpleCS extends TypePathNameCS {
	/**
   * Returns the value of the '<em><b>Type Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' reference.
   * @see #setTypeName(Type)
   * @see org.dresdenocl.language.ocl.OclPackage#getTypePathNameSimpleCS_TypeName()
   * @model required="true"
   * @generated
   */
	Type getTypeName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.TypePathNameSimpleCS#getTypeName <em>Type Name</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Name</em>' reference.
   * @see #getTypeName()
   * @generated
   */
	void setTypeName(Type value);

} // TypePathNameSimpleCS
