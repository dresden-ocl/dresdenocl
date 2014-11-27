/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.CollectionLiteralExpCS#getCollectionType <em>Collection Type</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.CollectionLiteralExpCS#getCollectionLiteralParts <em>Collection Literal Parts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionLiteralExpCS()
 * @model
 * @generated
 */
public interface CollectionLiteralExpCS extends LiteralExpCS {
	/**
	 * Returns the value of the '<em><b>Collection Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection Type</em>' containment reference.
	 * @see #setCollectionType(CollectionTypeIdentifierCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionLiteralExpCS_CollectionType()
	 * @model containment="true" required="true"
	 * @generated
	 */
	CollectionTypeIdentifierCS getCollectionType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.CollectionLiteralExpCS#getCollectionType <em>Collection Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection Type</em>' containment reference.
	 * @see #getCollectionType()
	 * @generated
	 */
	void setCollectionType(CollectionTypeIdentifierCS value);

	/**
	 * Returns the value of the '<em><b>Collection Literal Parts</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.language.ocl.CollectionLiteralPartsCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Literal Parts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection Literal Parts</em>' containment reference list.
	 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionLiteralExpCS_CollectionLiteralParts()
	 * @model containment="true"
	 * @generated
	 */
	EList<CollectionLiteralPartsCS> getCollectionLiteralParts();

} // CollectionLiteralExpCS
