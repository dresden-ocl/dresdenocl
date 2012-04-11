/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.common.util.EList;

import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getProperty <em>Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getType <em>Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getInitOrDeriveValue <em>Init Or Derive Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getAttributeContextDeclarationCS()
 * @model
 * @generated
 */
public interface AttributeContextDeclarationCS extends ContextDeclarationCS {
	/**
   * Returns the value of the '<em><b>Type Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type Name</em>' containment reference.
   * @see #setTypeName(TypePathNameCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getAttributeContextDeclarationCS_TypeName()
   * @model containment="true" required="true"
   * @generated
   */
	TypePathNameCS getTypeName();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getTypeName <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Name</em>' containment reference.
   * @see #getTypeName()
   * @generated
   */
	void setTypeName(TypePathNameCS value);

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
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getAttributeContextDeclarationCS_Property()
   * @model required="true"
   * @generated
   */
	Property getProperty();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getProperty <em>Property</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property</em>' reference.
   * @see #getProperty()
   * @generated
   */
	void setProperty(Property value);

	/**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(TypeCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getAttributeContextDeclarationCS_Type()
   * @model containment="true"
   * @generated
   */
	TypeCS getType();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
	void setType(TypeCS value);

	/**
   * Returns the value of the '<em><b>Init Or Derive Value</b></em>' containment reference list.
   * The list contents are of type {@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Or Derive Value</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Init Or Derive Value</em>' containment reference list.
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getAttributeContextDeclarationCS_InitOrDeriveValue()
   * @model containment="true" required="true" upper="2"
   * @generated
   */
	EList<InitOrDeriveValueCS> getInitOrDeriveValue();

} // AttributeContextDeclarationCS
