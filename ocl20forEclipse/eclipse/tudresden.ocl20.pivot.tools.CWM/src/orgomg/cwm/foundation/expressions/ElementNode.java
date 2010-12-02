/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An ElementNode is a node in an expression that references some ModelElement instance. This subclass of ExpressionNode allows an expression to reference any CWM model element that is not a Feature and cannot, therefore, be represented as a FeatureNode.
 * 
 * Typically, use of this subclass of ExpressionNode implies that a tool attempting to evaluate the expression will be able to determine if the referenced ModelElement instance is also an instance of some interesting subclass of ModelElement that contains a value of interest in the expression.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.expressions.ElementNode#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getElementNode()
 * @model
 * @generated
 */
public interface ElementNode extends ExpressionNode {
	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getElementNode <em>Element Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElement instance which this ElementNode references.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference.
	 * @see #setModelElement(ModelElement)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getElementNode_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getElementNode
	 * @model opposite="elementNode" required="true"
	 * @generated
	 */
	ModelElement getModelElement();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.ElementNode#getModelElement <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ModelElement value);

} // ElementNode
