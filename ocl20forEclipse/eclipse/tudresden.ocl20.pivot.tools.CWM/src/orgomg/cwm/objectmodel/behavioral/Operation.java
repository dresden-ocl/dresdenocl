/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Operation is a service that can be requested from an object to effect behavior. An Operation has a signature, which describes the parameters that are possible (including possible return values).
 * 
 * In the metamodel, an Operation is a BehavioralFeature that can be applied to instances of the Classifier that contains the Operation.
 * 
 * Operation is the specification, while Method is the implementation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Operation#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Operation#getCallAction <em>Call Action</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Operation#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends BehavioralFeature {
	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then the Operation does not have an implementation, and one must be supplied by a descendant. If false, the Operation must have an implementation in the class or inherited from an ancestor.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getOperation_IsAbstract()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Operation#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Call Action</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.CallAction}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.CallAction#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the CallAction instance representing a particular invocation of an Operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Call Action</em>' reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getOperation_CallAction()
	 * @see orgomg.cwm.objectmodel.behavioral.CallAction#getOperation
	 * @model opposite="operation"
	 * @generated
	 */
	EList<CallAction> getCallAction();

	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.Method}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Method#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Methods defined for an Operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method</em>' reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getOperation_Method()
	 * @see orgomg.cwm.objectmodel.behavioral.Method#getSpecification
	 * @model opposite="specification"
	 * @generated
	 */
	EList<Method> getMethod();

} // Operation
