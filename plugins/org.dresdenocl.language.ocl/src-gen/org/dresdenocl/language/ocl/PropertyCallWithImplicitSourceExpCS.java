/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call With Implicit Source Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallWithImplicitSourceExpCS#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallWithImplicitSourceExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallWithImplicitSourceExpCS()
 * @model
 * @generated
 */
public interface PropertyCallWithImplicitSourceExpCS extends PropertyCallExpCS {
	/**
	 * Returns the value of the '<em><b>Property Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Name</em>' containment reference.
	 * @see #setPropertyName(SimpleNameCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallWithImplicitSourceExpCS_PropertyName()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SimpleNameCS getPropertyName();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallWithImplicitSourceExpCS#getPropertyName <em>Property Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Name</em>' containment reference.
	 * @see #getPropertyName()
	 * @generated
	 */
	void setPropertyName(SimpleNameCS value);

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
	 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallWithImplicitSourceExpCS_IsMarkedPre()
	 * @model
	 * @generated
	 */
	boolean isIsMarkedPre();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallWithImplicitSourceExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
	 * @see #isIsMarkedPre()
	 * @generated
	 */
	void setIsMarkedPre(boolean value);

} // PropertyCallWithImplicitSourceExpCS
