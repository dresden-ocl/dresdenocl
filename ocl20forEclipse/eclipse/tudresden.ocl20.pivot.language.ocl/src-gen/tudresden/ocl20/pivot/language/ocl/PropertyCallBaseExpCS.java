/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call Base Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#getProperty <em>Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallBaseExpCS()
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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallBaseExpCS_Property()
   * @model required="true"
   * @generated
   */
	Property getProperty();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#getProperty <em>Property</em>}' reference.
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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getPropertyCallBaseExpCS_IsMarkedPre()
   * @model
   * @generated
   */
	boolean isIsMarkedPre();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
   * @see #isIsMarkedPre()
   * @generated
   */
	void setIsMarkedPre(boolean value);

} // PropertyCallBaseExpCS
