/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import orgomg.cwm.foundation.expressions.ExpressionNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tree</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a specialized Transformation which can be modeled as an expression tree. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTree#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTree#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTree()
 * @model
 * @generated
 */
public interface TransformationTree extends Transformation {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.analysis.transformation.TreeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the type of TransformationTree, which can be unary or binary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.analysis.transformation.TreeType
	 * @see #setType(TreeType)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTree_Type()
	 * @model
	 * @generated
	 */
	TreeType getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.TransformationTree#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.analysis.transformation.TreeType
	 * @see #getType()
	 * @generated
	 */
	void setType(TreeType value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the expression tree that embodies the TransformationTree.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(ExpressionNode)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTree_Body()
	 * @model containment="true"
	 * @generated
	 */
	ExpressionNode getBody();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.TransformationTree#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(ExpressionNode value);

} // TransformationTree
