/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.expressions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.objectmodel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The Expressions package depends on the following packages:
 * 
 *     org.omg::CWM::ObjectModel::Core
 * 
 * The CWM Expressions metamodel provides basic support for the definition of expression trees within the CWM. The intent of the Expressions metamodel is to provide a place for other CWM packages (such as Transformation) and CWM compliant tools to record shared expressions in a common form that can be used for interchange and lineage tracking.
 * 
 * The expression concept in the CWM Foundation takes a functional view of expression trees, resulting in the ability of relatively few expression types to represent a broad range of expressions. Every function or traditional mathematical operator that appears in an expression hierarchy is represented as a FeatureNode. For example, the arithmetic plus operation “a + b” can be thought of as the function “sum(a, b).” The semantics of a particular function or operation are left to specific tool implementations and are not captured by the CWM. 
 * 
 * The hierarchical nature of the CWM’s representation of expressions is achieved by the recursive nature of the OperationArgument association. This association allows the sub-hierarchies within an expression to be treated as actual parameters of their parent nodes.
 * 
 * OCL Representation of Expressions Constraints
 * 
 * [C-5-1] A FeatureNode that has parameters other than the"this" parameter represents a Feature that is also an Operation.
 * context FeatureNode inv:
 * if self.feature.ownerScope = #instance
 * then self.argument->size > 1 implies
 *    self.feature.oclIsKindOf(Operation)
 * else self.argument->size > 0 implies
 *    self.feature.oclIsKindOf(Operation)
 * endif
 * 
 * [C-5-2] If the FeatureNode represents an instance-scope feature, the first argument is a "this" or "self" argument; that is, the object invoking the feature. The convention is enforced by checking that the type of the first argument is the same as the type of the feature.
 * context FeatureNode inv:
 * self.feature.ownerScope = #instance implies
 * self.argument->first.type.allFeatures->includes(self.feature)
 * 
 * [C-5-3] If the FeatureNode represents a BehavioralFeature, the number of arguments must be equal to the number of the BehavioralFeature’s parameters, plus one for the “this” parameter if the BehavioralFeature is of instance scope.
 * context FeatureNode inv:
 * self.feature.oclIsKindOf(BehavioralFeature) implies
 * (if self.feature.ownerScope = #instance
 *    then self.argument->size =
 *       self.feature.oclAsType(BehavioralFeature).parameters->size + 1
 *    else self.argument->size =
 *       self.feature.oclAsType(BehavioralFeature).parameters->size
 * endif)
 * <!-- end-model-doc -->
 * @see orgomg.cwm.foundation.expressions.ExpressionsFactory
 * @model kind="package"
 * @generated
 */
public interface ExpressionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "expressions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/foundation/expressions.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.foundation.expressions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionsPackage eINSTANCE = orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl <em>Expression Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl
	 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getExpressionNode()
	 * @generated
	 */
	int EXPRESSION_NODE = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_NODE__EXPRESSION = CorePackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_NODE__TYPE = CorePackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_NODE__FEATURE_NODE = CorePackage.ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Expression Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_NODE_FEATURE_COUNT = CorePackage.ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.expressions.impl.ConstantNodeImpl <em>Constant Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.expressions.impl.ConstantNodeImpl
	 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getConstantNode()
	 * @generated
	 */
	int CONSTANT_NODE = 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_NODE__EXPRESSION = EXPRESSION_NODE__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_NODE__TYPE = EXPRESSION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_NODE__FEATURE_NODE = EXPRESSION_NODE__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_NODE__VALUE = EXPRESSION_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constant Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_NODE_FEATURE_COUNT = EXPRESSION_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.expressions.impl.ElementNodeImpl <em>Element Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.expressions.impl.ElementNodeImpl
	 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getElementNode()
	 * @generated
	 */
	int ELEMENT_NODE = 2;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_NODE__EXPRESSION = EXPRESSION_NODE__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_NODE__TYPE = EXPRESSION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_NODE__FEATURE_NODE = EXPRESSION_NODE__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_NODE__MODEL_ELEMENT = EXPRESSION_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_NODE_FEATURE_COUNT = EXPRESSION_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.expressions.impl.FeatureNodeImpl <em>Feature Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.expressions.impl.FeatureNodeImpl
	 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getFeatureNode()
	 * @generated
	 */
	int FEATURE_NODE = 3;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE__EXPRESSION = EXPRESSION_NODE__EXPRESSION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE__TYPE = EXPRESSION_NODE__TYPE;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE__FEATURE_NODE = EXPRESSION_NODE__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE__FEATURE = EXPRESSION_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE__ARGUMENT = EXPRESSION_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Feature Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_NODE_FEATURE_COUNT = EXPRESSION_NODE_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.expressions.ExpressionNode <em>Expression Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Node</em>'.
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode
	 * @generated
	 */
	EClass getExpressionNode();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode#getExpression()
	 * @see #getExpressionNode()
	 * @generated
	 */
	EReference getExpressionNode_Expression();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode#getType()
	 * @see #getExpressionNode()
	 * @generated
	 */
	EReference getExpressionNode_Type();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode <em>Feature Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Feature Node</em>'.
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode#getFeatureNode()
	 * @see #getExpressionNode()
	 * @generated
	 */
	EReference getExpressionNode_FeatureNode();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.expressions.ConstantNode <em>Constant Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant Node</em>'.
	 * @see orgomg.cwm.foundation.expressions.ConstantNode
	 * @generated
	 */
	EClass getConstantNode();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.expressions.ConstantNode#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see orgomg.cwm.foundation.expressions.ConstantNode#getValue()
	 * @see #getConstantNode()
	 * @generated
	 */
	EAttribute getConstantNode_Value();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.expressions.ElementNode <em>Element Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Node</em>'.
	 * @see orgomg.cwm.foundation.expressions.ElementNode
	 * @generated
	 */
	EClass getElementNode();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.expressions.ElementNode#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model Element</em>'.
	 * @see orgomg.cwm.foundation.expressions.ElementNode#getModelElement()
	 * @see #getElementNode()
	 * @generated
	 */
	EReference getElementNode_ModelElement();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.expressions.FeatureNode <em>Feature Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Node</em>'.
	 * @see orgomg.cwm.foundation.expressions.FeatureNode
	 * @generated
	 */
	EClass getFeatureNode();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.expressions.FeatureNode#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see orgomg.cwm.foundation.expressions.FeatureNode#getFeature()
	 * @see #getFeatureNode()
	 * @generated
	 */
	EReference getFeatureNode_Feature();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.foundation.expressions.FeatureNode#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Argument</em>'.
	 * @see orgomg.cwm.foundation.expressions.FeatureNode#getArgument()
	 * @see #getFeatureNode()
	 * @generated
	 */
	EReference getFeatureNode_Argument();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExpressionsFactory getExpressionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl <em>Expression Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.expressions.impl.ExpressionNodeImpl
		 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getExpressionNode()
		 * @generated
		 */
		EClass EXPRESSION_NODE = eINSTANCE.getExpressionNode();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_NODE__EXPRESSION = eINSTANCE.getExpressionNode_Expression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_NODE__TYPE = eINSTANCE.getExpressionNode_Type();

		/**
		 * The meta object literal for the '<em><b>Feature Node</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_NODE__FEATURE_NODE = eINSTANCE.getExpressionNode_FeatureNode();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.expressions.impl.ConstantNodeImpl <em>Constant Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.expressions.impl.ConstantNodeImpl
		 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getConstantNode()
		 * @generated
		 */
		EClass CONSTANT_NODE = eINSTANCE.getConstantNode();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_NODE__VALUE = eINSTANCE.getConstantNode_Value();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.expressions.impl.ElementNodeImpl <em>Element Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.expressions.impl.ElementNodeImpl
		 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getElementNode()
		 * @generated
		 */
		EClass ELEMENT_NODE = eINSTANCE.getElementNode();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_NODE__MODEL_ELEMENT = eINSTANCE.getElementNode_ModelElement();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.expressions.impl.FeatureNodeImpl <em>Feature Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.expressions.impl.FeatureNodeImpl
		 * @see orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl#getFeatureNode()
		 * @generated
		 */
		EClass FEATURE_NODE = eINSTANCE.getFeatureNode();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_NODE__FEATURE = eINSTANCE.getFeatureNode_Feature();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_NODE__ARGUMENT = eINSTANCE.getFeatureNode_Argument();

	}

} //ExpressionsPackage
