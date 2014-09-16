/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.examples.pml;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.examples.pml.Operation#getName <em>Name</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Operation#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Operation#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.dresdenocl.examples.pml.Operation#getMyType <em>My Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.examples.pml.PmlPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getOperation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Operation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' reference.
	 * @see #setReturnType(JavaType)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getOperation_ReturnType()
	 * @model required="true"
	 * @generated
	 */
	JavaType getReturnType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Operation#getReturnType <em>Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' reference.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(JavaType value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.examples.pml.Parameter}.
	 * It is bidirectional and its opposite is '{@link org.dresdenocl.examples.pml.Parameter#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.dresdenocl.examples.pml.PmlPackage#getOperation_Parameters()
	 * @see org.dresdenocl.examples.pml.Parameter#getOperation
	 * @model opposite="operation" containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>My Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>My Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>My Type</em>' reference.
	 * @see #setMyType(JavaType)
	 * @see org.dresdenocl.examples.pml.PmlPackage#getOperation_MyType()
	 * @model required="true"
	 * @generated
	 */
	JavaType getMyType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.examples.pml.Operation#getMyType <em>My Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>My Type</em>' reference.
	 * @see #getMyType()
	 * @generated
	 */
	void setMyType(JavaType value);

} // Operation
