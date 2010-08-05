/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constant Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the ConstantNode class are ExpressionNodes that represent constant values within expressions. Appropriate uses of the ConstantNode class place the values of constants in the value attribute, rather than in the expression::body attribute inherited from ExpressionNode. The latter attribute is intended for a different purpose; see the description of the ExpressionNode class for details.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.expressions.ConstantNode#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getConstantNode()
 * @model
 * @generated
 */
public interface ConstantNode extends ExpressionNode {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of a constant in an expression tree.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getConstantNode_Value()
	 * @model dataType="orgomg.cwm.objectmodel.core.Any"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.ConstantNode#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // ConstantNode
