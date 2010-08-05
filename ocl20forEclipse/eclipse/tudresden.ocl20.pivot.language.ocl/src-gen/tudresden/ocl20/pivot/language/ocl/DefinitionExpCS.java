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
 * A representation of the model object '<em><b>Definition Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#isStatic <em>Static</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#getDefinitionExpPart <em>Definition Exp Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpCS()
 * @model
 * @generated
 */
public interface DefinitionExpCS extends InvariantOrDefinitionCS {
	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpCS_Static()
	 * @model
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Definition Exp Part</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition Exp Part</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition Exp Part</em>' containment reference.
	 * @see #setDefinitionExpPart(DefinitionExpPartCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getDefinitionExpCS_DefinitionExpPart()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DefinitionExpPartCS getDefinitionExpPart();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.DefinitionExpCS#getDefinitionExpPart <em>Definition Exp Part</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition Exp Part</em>' containment reference.
	 * @see #getDefinitionExpPart()
	 * @generated
	 */
	void setDefinitionExpPart(DefinitionExpPartCS value);

} // DefinitionExpCS
