/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Element;
import orgomg.cwm.objectmodel.core.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * All node types within an expression are derived from the ExpressionNode type.
 * 
 * An expression is stored as a collection of instances of the subtypes of ExpressionNode arranged in a hierarchical fashion. The ExpressionNode instance at the top (or “root”) of the hierarchy represents the value of the expression and serves as a starting point for expression evaluation. Refer to the descriptions of the subtypes of ExpressionNode (ElementNode, ConstantNode, and FeatureNode) for additional information about the representation of expressions.
 * 
 * One important purpose for providing storage of expressions as a general feature of the CWM is to promote sharing them between tools and to provide a means for recording lineage relationships between components within expressions. Specific details of the implementation of expression operators are left to the implementing tools.
 * 
 * When ExpressionNode is used as the type of an Attribute, an instance of the Attribute can contain either an expression tree as described here or a textual representation of the expression in body and language values of in an attribute of type Expression (defined ObjectModel). The expression attribute is provided for the latter usage. To obtain CWM’s sharing and lineage tracking features for elements within an expression, the expression must be represented as an expression hierarcy.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.expressions.ExpressionNode#getExpression <em>Expression</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.expressions.ExpressionNode#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode <em>Feature Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getExpressionNode()
 * @model
 * @generated
 */
public interface ExpressionNode extends Element {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual representation of the expression relevant for this ExpressionNode instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getExpressionNode_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getExpressionNode <em>Expression Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that represents the type of the expression at this level in the expression hierarchy. Although, formally, each node within an expression tree is capable of having a value and therefore, a data type, this reference is optional because modeling the data type of intermediate nodes in an expression tree is not always interesting, thereby reducing the effort required to create expression trees.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Classifier)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getExpressionNode_Type()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getExpressionNode
	 * @model opposite="expressionNode"
	 * @generated
	 */
	Classifier getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Classifier value);

	/**
	 * Returns the value of the '<em><b>Feature Node</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.expressions.FeatureNode#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the FeatureNode for which this ExpressionNode instance represents the value of an argument. Because arguments can themselves represent entire expression sub-trees, this reference is used to create hierarchies of expression nodes, permitting representation of entire expression trees within the CWM.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature Node</em>' container reference.
	 * @see #setFeatureNode(FeatureNode)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getExpressionNode_FeatureNode()
	 * @see orgomg.cwm.foundation.expressions.FeatureNode#getArgument
	 * @model opposite="argument"
	 * @generated
	 */
	FeatureNode getFeatureNode();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode <em>Feature Node</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Node</em>' container reference.
	 * @see #getFeatureNode()
	 * @generated
	 */
	void setFeatureNode(FeatureNode value);

} // ExpressionNode
