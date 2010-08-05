/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Argument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Argument is an expression describing how to determine an actual value passed in a CallAction.
 * 
 * In the metamodel an Argument is a composite part of a CallAction and contains a meta-attribute, value, of type Expression. It states how the actual argument is determined when the owning CallAction is executed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Argument#getValue <em>Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Argument#getCallAction <em>Call Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getArgument()
 * @model
 * @generated
 */
public interface Argument extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An expression determining the actual Argument instance when executed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Expression)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getArgument_Value()
	 * @model containment="true"
	 * @generated
	 */
	Expression getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Argument#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);

	/**
	 * Returns the value of the '<em><b>Call Action</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.CallAction#getActualArgument <em>Actual Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the CallAction instance representing a particular invocation of an Operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Call Action</em>' container reference.
	 * @see #setCallAction(CallAction)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getArgument_CallAction()
	 * @see orgomg.cwm.objectmodel.behavioral.CallAction#getActualArgument
	 * @model opposite="actualArgument"
	 * @generated
	 */
	CallAction getCallAction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Argument#getCallAction <em>Call Action</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Action</em>' container reference.
	 * @see #getCallAction()
	 * @generated
	 */
	void setCallAction(CallAction value);

} // Argument
