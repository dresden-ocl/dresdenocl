/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Call Explicit Path Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getSource <em>Source</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getPropertyPath <em>Property Path</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallExplicitPathExpCS()
 * @model
 * @generated
 */
public interface PropertyCallExplicitPathExpCS extends PropertyCallExpCS {
	/**
   * Returns the value of the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' containment reference.
   * @see #setSource(OclExpressionCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallExplicitPathExpCS_Source()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getSource();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getSource <em>Source</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' containment reference.
   * @see #getSource()
   * @generated
   */
	void setSource(OclExpressionCS value);

	/**
   * Returns the value of the '<em><b>Property Path</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Path</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Property Path</em>' containment reference.
   * @see #setPropertyPath(PathNameCS)
   * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallExplicitPathExpCS_PropertyPath()
   * @model containment="true" required="true"
   * @generated
   */
	PathNameCS getPropertyPath();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getPropertyPath <em>Property Path</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Path</em>' containment reference.
   * @see #getPropertyPath()
   * @generated
   */
	void setPropertyPath(PathNameCS value);

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
   * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallExplicitPathExpCS_PropertyName()
   * @model containment="true" required="true"
   * @generated
   */
	SimpleNameCS getPropertyName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#getPropertyName <em>Property Name</em>}' containment reference.
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
   * @see org.dresdenocl.language.ocl.OclPackage#getPropertyCallExplicitPathExpCS_IsMarkedPre()
   * @model
   * @generated
   */
	boolean isIsMarkedPre();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
   * @see #isIsMarkedPre()
   * @generated
   */
	void setIsMarkedPre(boolean value);

} // PropertyCallExplicitPathExpCS
