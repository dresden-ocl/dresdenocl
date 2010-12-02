/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import tudresden.ocl20.pivot.pivotmodel.Type;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Type Identifier CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getGenericType <em>Generic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionTypeIdentifierCS()
 * @model
 * @generated
 */
public interface CollectionTypeIdentifierCS extends TypeCS {
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
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionTypeIdentifierCS_TypeName()
	 * @model required="true"
	 * @generated
	 */
	Type getTypeName();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getTypeName <em>Type Name</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' reference.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(Type value);

	/**
	 * Returns the value of the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Type</em>' containment reference.
	 * @see #setGenericType(TypeCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionTypeIdentifierCS_GenericType()
	 * @model containment="true"
	 * @generated
	 */
	TypeCS getGenericType();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS#getGenericType <em>Generic Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Type</em>' containment reference.
	 * @see #getGenericType()
	 * @generated
	 */
	void setGenericType(TypeCS value);

} // CollectionTypeIdentifierCS
