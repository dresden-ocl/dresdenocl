/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.pivotmodel.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call Base Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallBaseExpCS#getProperty <em>Property</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallBaseExpCS()
 * @model
 * @generated
 */
public interface PropertyCallBaseExpCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' reference.
	 * @see #setProperty(Property)
	 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallBaseExpCS_Property()
	 * @model required="true"
	 * @generated
	 */
	Property getProperty();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallBaseExpCS#getProperty <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(Property value);

	/**
	 * Returns the value of the '<em><b>Is Marked Pre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Marked Pre</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Marked Pre</em>' attribute.
	 * @see #setIsMarkedPre(boolean)
	 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallBaseExpCS_IsMarkedPre()
	 * @model
	 * @generated
	 */
	boolean isIsMarkedPre();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
	 * @see #isIsMarkedPre()
	 * @generated
	 */
	void setIsMarkedPre(boolean value);

} // PropertyCallBaseExpCS
