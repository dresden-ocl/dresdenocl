/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.analysis.transformation.ClassifierFeatureMap;
import orgomg.cwm.analysis.transformation.ClassifierMap;

import orgomg.cwm.foundation.datatypes.TypeAlias;

import orgomg.cwm.foundation.expressions.ExpressionNode;

import orgomg.cwm.foundation.typemapping.TypeMapping;

import orgomg.cwm.objectmodel.behavioral.Parameter;

import orgomg.cwm.objectmodel.instance.Instance;

import orgomg.cwm.objectmodel.relationships.Generalization;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A classifier is an element that describes structural and behavioral features; it comes in several specific forms, including class, data type, interface, component, and others that are defined in other metamodel packages.
 * 
 * Classifier is often used as a type.
 * 
 * In the metamodel, a Classifier may declare a collection of Features, such as Attributes, Operations and Methods. It has a name, which is unique in the Namespace enclosing the Classifier. Classifier is an abstract metaclass.
 * 
 * Classifier is a child of Namespace. As a Namespace, a Classifier may declare other Classifiers nested in its scope. Nested Classifiers may be accessed by other Classifiers only if the nested Classifiers have adequate visibility. There are no data value or state consequences of nested Classifiers, i.e., it is not an aggregation or composition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getStructuralFeature <em>Structural Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getParameter <em>Parameter</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getGeneralization <em>Generalization</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getSpecialization <em>Specialization</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getInstance <em>Instance</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getAlias <em>Alias</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getExpressionNode <em>Expression Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getMappingFrom <em>Mapping From</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getMappingTo <em>Mapping To</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getClassifierMap <em>Classifier Map</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Classifier#getCfMap <em>Cf Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends Namespace {
	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An abstract Classifier is not instantiable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_IsAbstract()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Classifier#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Feature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Feature#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Features owned by a Classifier instance and provides their ordering.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Feature()
	 * @see orgomg.cwm.objectmodel.core.Feature#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<Feature> getFeature();

	/**
	 * Returns the value of the '<em><b>Structural Feature</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.StructuralFeature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of StructuralFeatures for which the Classifier defines the type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Structural Feature</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_StructuralFeature()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<StructuralFeature> getStructuralFeature();

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.Parameter}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Parameter instances for which a particular Classifier acts as a type definition.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Parameter()
	 * @see orgomg.cwm.objectmodel.behavioral.Parameter#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<Parameter> getParameter();

	/**
	 * Returns the value of the '<em><b>Generalization</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.relationships.Generalization}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.relationships.Generalization#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Generalization instances in which the Classifier acts as a child
	 * in the inheritance hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Generalization</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Generalization()
	 * @see orgomg.cwm.objectmodel.relationships.Generalization#getChild
	 * @model opposite="child"
	 * @generated
	 */
	EList<Generalization> getGeneralization();

	/**
	 * Returns the value of the '<em><b>Specialization</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.relationships.Generalization}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.relationships.Generalization#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Generalization instances in which the Classifier acts a parent in the inheritance hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Specialization</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Specialization()
	 * @see orgomg.cwm.objectmodel.relationships.Generalization#getParent
	 * @model opposite="parent"
	 * @generated
	 */
	EList<Generalization> getSpecialization();

	/**
	 * Returns the value of the '<em><b>Instance</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.instance.Instance}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Instance#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Instances described by the Classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instance</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Instance()
	 * @see orgomg.cwm.objectmodel.instance.Instance#getClassifier
	 * @model opposite="classifier"
	 * @generated
	 */
	EList<Instance> getInstance();

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.datatypes.TypeAlias}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.datatypes.TypeAlias#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TypeAliases that have be defined for a particular Classifier instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alias</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_Alias()
	 * @see orgomg.cwm.foundation.datatypes.TypeAlias#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<TypeAlias> getAlias();

	/**
	 * Returns the value of the '<em><b>Expression Node</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.expressions.ExpressionNode}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.expressions.ExpressionNode#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ExpressionNode instances for which this Classifier acts as the type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression Node</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_ExpressionNode()
	 * @see orgomg.cwm.foundation.expressions.ExpressionNode#getType
	 * @model opposite="type"
	 * @generated
	 */
	EList<ExpressionNode> getExpressionNode();

	/**
	 * Returns the value of the '<em><b>Mapping From</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.typemapping.TypeMapping}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.typemapping.TypeMapping#getSourceType <em>Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TypeMapping instances in which a particular Classifier participates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapping From</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_MappingFrom()
	 * @see orgomg.cwm.foundation.typemapping.TypeMapping#getSourceType
	 * @model opposite="sourceType"
	 * @generated
	 */
	EList<TypeMapping> getMappingFrom();

	/**
	 * Returns the value of the '<em><b>Mapping To</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.typemapping.TypeMapping}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.typemapping.TypeMapping#getTargetType <em>Target Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TypeMapping instance of a particular Classifier instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mapping To</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_MappingTo()
	 * @see orgomg.cwm.foundation.typemapping.TypeMapping#getTargetType
	 * @model opposite="targetType"
	 * @generated
	 */
	EList<TypeMapping> getMappingTo();

	/**
	 * Returns the value of the '<em><b>Classifier Map</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.ClassifierMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ClassifierMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier Map</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_ClassifierMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<ClassifierMap> getClassifierMap();

	/**
	 * Returns the value of the '<em><b>Cf Map</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ClassifierFeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cf Map</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getClassifier_CfMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifier
	 * @model opposite="classifier"
	 * @generated
	 */
	EList<ClassifierFeatureMap> getCfMap();

} // Classifier
