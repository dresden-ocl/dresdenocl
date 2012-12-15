/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.BooleanLiteralExpCS#isBooleanLiteral <em>Boolean Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getBooleanLiteralExpCS()
 * @model
 * @generated
 */
public interface BooleanLiteralExpCS extends PrimitiveLiteralExpCS {
	/**
   * Returns the value of the '<em><b>Boolean Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boolean Literal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Boolean Literal</em>' attribute.
   * @see #setBooleanLiteral(boolean)
   * @see org.dresdenocl.language.ocl.OclPackage#getBooleanLiteralExpCS_BooleanLiteral()
   * @model required="true"
   * @generated
   */
	boolean isBooleanLiteral();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.BooleanLiteralExpCS#isBooleanLiteral <em>Boolean Literal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Boolean Literal</em>' attribute.
   * @see #isBooleanLiteral()
   * @generated
   */
	void setBooleanLiteral(boolean value);

} // BooleanLiteralExpCS
