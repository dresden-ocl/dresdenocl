/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Or Derive Value CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getInitOrDeriveValueCS()
 * @model abstract="true"
 * @generated
 */
public interface InitOrDeriveValueCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Ocl Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Expression</em>' containment reference.
	 * @see #setOclExpression(OclExpressionCS)
	 * @see tudresden.ocl20.pivot.language.ocl.OclPackage#getInitOrDeriveValueCS_OclExpression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	OclExpressionCS getOclExpression();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS#getOclExpression <em>Ocl Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Expression</em>' containment reference.
	 * @see #getOclExpression()
	 * @generated
	 */
	void setOclExpression(OclExpressionCS value);

} // InitOrDeriveValueCS
