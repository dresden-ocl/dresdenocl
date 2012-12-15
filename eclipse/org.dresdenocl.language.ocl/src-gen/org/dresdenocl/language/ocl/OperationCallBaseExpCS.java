/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.pivotmodel.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Call Base Exp CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.OperationCallBaseExpCS#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.OperationCallBaseExpCS#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.OperationCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallBaseExpCS()
 * @model
 * @generated
 */
public interface OperationCallBaseExpCS extends EObject {
	/**
   * Returns the value of the '<em><b>Operation Name</b></em>' reference.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Name</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Operation Name</em>' reference.
   * @see #setOperationName(Operation)
   * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallBaseExpCS_OperationName()
   * @model required="true"
   * @generated
   */
	Operation getOperationName();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationCallBaseExpCS#getOperationName <em>Operation Name</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operation Name</em>' reference.
   * @see #getOperationName()
   * @generated
   */
	void setOperationName(Operation value);

	/**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.dresdenocl.language.ocl.OclExpressionCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference list.
   * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallBaseExpCS_Arguments()
   * @model containment="true"
   * @generated
   */
	EList<OclExpressionCS> getArguments();

	/**
   * Returns the value of the '<em><b>Is Marked Pre</b></em>' attribute.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Marked Pre</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Is Marked Pre</em>' attribute.
   * @see #setIsMarkedPre(boolean)
   * @see org.dresdenocl.language.ocl.OclPackage#getOperationCallBaseExpCS_IsMarkedPre()
   * @model
   * @generated
   */
	boolean isIsMarkedPre();

	/**
   * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationCallBaseExpCS#isIsMarkedPre <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Marked Pre</em>' attribute.
   * @see #isIsMarkedPre()
   * @generated
   */
	void setIsMarkedPre(boolean value);

} // OperationCallBaseExpCS
