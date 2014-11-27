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
 * A representation of the model object '<em><b>Operation Definition CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.OperationDefinitionCS#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.OperationDefinitionCS#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.OperationDefinitionCS#getReturnType <em>Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getOperationDefinitionCS()
 * @model abstract="true"
 * @generated
 */
public interface OperationDefinitionCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see org.dresdenocl.language.ocl.OclPackage#getOperationDefinitionCS_Operation()
	 * @model required="true"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationDefinitionCS#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.language.ocl.ParameterCS}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.dresdenocl.language.ocl.OclPackage#getOperationDefinitionCS_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterCS> getParameters();

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' containment reference.
	 * @see #setReturnType(TypeCS)
	 * @see org.dresdenocl.language.ocl.OclPackage#getOperationDefinitionCS_ReturnType()
	 * @model containment="true"
	 * @generated
	 */
	TypeCS getReturnType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.language.ocl.OperationDefinitionCS#getReturnType <em>Return Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' containment reference.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(TypeCS value);

} // OperationDefinitionCS
