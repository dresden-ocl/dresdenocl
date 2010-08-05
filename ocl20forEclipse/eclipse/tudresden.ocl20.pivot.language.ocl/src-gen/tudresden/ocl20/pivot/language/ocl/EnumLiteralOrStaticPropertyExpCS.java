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
 * A representation of the model object '<em><b>Enum Literal Or Static Property Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getEnumLiteralOrStaticProperty <em>Enum Literal Or Static Property</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getEnumLiteralOrStaticPropertyExpCS()
 * @model
 * @generated
 */
public interface EnumLiteralOrStaticPropertyExpCS extends LiteralExpCS {
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
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getEnumLiteralOrStaticPropertyExpCS_TypeName()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TypePathNameCS getTypeName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getTypeName <em>Type Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' containment reference.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(TypePathNameCS value);

	/**
	 * Returns the value of the '<em><b>Enum Literal Or Static Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enum Literal Or Static Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enum Literal Or Static Property</em>' reference.
	 * @see #setEnumLiteralOrStaticProperty(NamedElement)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getEnumLiteralOrStaticPropertyExpCS_EnumLiteralOrStaticProperty()
	 * @model required="true"
	 * @generated
	 */
	NamedElement getEnumLiteralOrStaticProperty();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS#getEnumLiteralOrStaticProperty <em>Enum Literal Or Static Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enum Literal Or Static Property</em>' reference.
	 * @see #getEnumLiteralOrStaticProperty()
	 * @generated
	 */
	void setEnumLiteralOrStaticProperty(NamedElement value);

} // EnumLiteralOrStaticPropertyExpCS
