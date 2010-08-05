/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Feature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The FeatureNode class represents ExpressionNode instances that are features (i.e., attributes or operations) of some Classifier instance within the CWM.
 * 
 * A FeatureNode with a null OperationArgument association represents either a parameter-less operation or an attribute value obtained from some StructuralFeature instance.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.expressions.FeatureNode#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.expressions.FeatureNode#getArgument <em>Argument</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getFeatureNode()
 * @model
 * @generated
 */
public interface FeatureNode extends ExpressionNode {
	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Feature#getFeatureNode <em>Feature Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Feature (attribute or operation) which this FeatureNode instance represents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see #setFeature(Feature)
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getFeatureNode_Feature()
	 * @see orgomg.cwm.objectmodel.core.Feature#getFeatureNode
	 * @model opposite="featureNode" required="true"
	 * @generated
	 */
	Feature getFeature();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.expressions.FeatureNode#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(Feature value);

	/**
	 * Returns the value of the '<em><b>Argument</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.expressions.ExpressionNode}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode <em>Feature Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ExpressionNode instances that represent the actual arguments for this FeatureNode. If the argument reference is null, the FeatureNode is an attribute or parameter-less function or operation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Argument</em>' containment reference list.
	 * @see orgomg.cwm.foundation.expressions.ExpressionsPackage#getFeatureNode_Argument()
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode
	 * @model opposite="featureNode" containment="true"
	 * @generated
	 */
	EList<ExpressionNode> getArgument();

} // FeatureNode
