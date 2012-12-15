/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Range CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getFrom <em>From</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionRangeCS()
 * @model
 * @generated
 */
public interface CollectionRangeCS extends CollectionLiteralPartsCS {
	/**
   * Returns the value of the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>From</em>' containment reference.
   * @see #setFrom(OclExpressionCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionRangeCS_From()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getFrom();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getFrom <em>From</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>From</em>' containment reference.
   * @see #getFrom()
   * @generated
   */
	void setFrom(OclExpressionCS value);

	/**
   * Returns the value of the '<em><b>To</b></em>' containment reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>To</em>' containment reference.
   * @see #setTo(OclExpressionCS)
   * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getCollectionRangeCS_To()
   * @model containment="true" required="true"
   * @generated
   */
	OclExpressionCS getTo();

	/**
   * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.CollectionRangeCS#getTo <em>To</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>To</em>' containment reference.
   * @see #getTo()
   * @generated
   */
	void setTo(OclExpressionCS value);

} // CollectionRangeCS
