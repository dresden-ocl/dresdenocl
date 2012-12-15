/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Type Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS#getCollectionType <em>Collection Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getCollectionTypeLiteralExpCS()
 * @model
 * @generated
 */
public interface CollectionTypeLiteralExpCS extends LiteralExpCS {
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
   * @see org.dresdenocl.language.ocl.OclPackage#getCollectionTypeLiteralExpCS_CollectionType()
   * @model containment="true" required="true"
   * @generated
   */
	CollectionTypeIdentifierCS getCollectionType();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS#getCollectionType <em>Collection Type</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Collection Type</em>' containment reference.
   * @see #getCollectionType()
   * @generated
   */
	void setCollectionType(CollectionTypeIdentifierCS value);

} // CollectionTypeLiteralExpCS
