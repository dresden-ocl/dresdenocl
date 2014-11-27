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
 * A representation of the model object '<em><b>Collection Type Identifier CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.CollectionTypeIdentifierCS#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.CollectionTypeIdentifierCS#getGenericType <em>Generic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionTypeIdentifierCS()
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
	 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionTypeIdentifierCS_TypeName()
	 * @model required="true"
	 * @generated
	 */
	Type getTypeName();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.CollectionTypeIdentifierCS#getTypeName <em>Type Name</em>}' reference.
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
	 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionTypeIdentifierCS_GenericType()
	 * @model containment="true"
	 * @generated
	 */
	TypeCS getGenericType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.CollectionTypeIdentifierCS#getGenericType <em>Generic Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Type</em>' containment reference.
	 * @see #getGenericType()
	 * @generated
	 */
	void setGenericType(TypeCS value);

} // CollectionTypeIdentifierCS
