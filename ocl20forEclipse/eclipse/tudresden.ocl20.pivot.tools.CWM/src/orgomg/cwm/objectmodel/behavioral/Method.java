/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import orgomg.cwm.objectmodel.core.ProcedureExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Method is the implementation of an Operation. It specifies the algorithm or procedure that effects the results of an Operation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Method#getBody <em>Body</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Method#getSpecification <em>Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends BehavioralFeature {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A specification of the Method in some appropriate form (such as a programming language). The exact form of a Method’s specification and knowledge of the language in which it is described is outside the scope of the CWM.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(ProcedureExpression)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getMethod_Body()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getBody();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Method#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(ProcedureExpression value);

	/**
	 * Returns the value of the '<em><b>Specification</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Operation#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Operation that a Method implements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specification</em>' reference.
	 * @see #setSpecification(Operation)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getMethod_Specification()
	 * @see orgomg.cwm.objectmodel.behavioral.Operation#getMethod
	 * @model opposite="method" required="true"
	 * @generated
	 */
	Operation getSpecification();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Method#getSpecification <em>Specification</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specification</em>' reference.
	 * @see #getSpecification()
	 * @generated
	 */
	void setSpecification(Operation value);

} // Method
