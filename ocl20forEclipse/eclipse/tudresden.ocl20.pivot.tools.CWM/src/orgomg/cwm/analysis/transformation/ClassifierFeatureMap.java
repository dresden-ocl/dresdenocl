/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier Feature Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a mapping of Classifiers to Features.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#isClassifierToFeature <em>Classifier To Feature</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap <em>Classifier Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap()
 * @model
 * @generated
 */
public interface ClassifierFeatureMap extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Any code or script for the FeatureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function</em>' containment reference.
	 * @see #setFunction(ProcedureExpression)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_Function()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getFunction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(ProcedureExpression value);

	/**
	 * Returns the value of the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A short description for any code or script performed by the FeatureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Description</em>' attribute.
	 * @see #setFunctionDescription(String)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_FunctionDescription()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFunctionDescription();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunctionDescription <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Description</em>' attribute.
	 * @see #getFunctionDescription()
	 * @generated
	 */
	void setFunctionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Classifier To Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies if the mapping is from Classifiers (source) to Features (target). The default is true.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier To Feature</em>' attribute.
	 * @see #setClassifierToFeature(boolean)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_ClassifierToFeature()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isClassifierToFeature();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#isClassifierToFeature <em>Classifier To Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier To Feature</em>' attribute.
	 * @see #isClassifierToFeature()
	 * @generated
	 */
	void setClassifierToFeature(boolean value);

	/**
	 * Returns the value of the '<em><b>Classifier Map</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getCfMap <em>Cf Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owning ClassifierMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier Map</em>' container reference.
	 * @see #setClassifierMap(ClassifierMap)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_ClassifierMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getCfMap
	 * @model opposite="cfMap"
	 * @generated
	 */
	ClassifierMap getClassifierMap();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap <em>Classifier Map</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier Map</em>' container reference.
	 * @see #getClassifierMap()
	 * @generated
	 */
	void setClassifierMap(ClassifierMap value);

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Classifier}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getCfMap <em>Cf Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the source/target Classifiers of the ClassifierFeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_Classifier()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getCfMap
	 * @model opposite="cfMap" required="true"
	 * @generated
	 */
	EList<Classifier> getClassifier();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Feature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Feature#getCfMap <em>Cf Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the source/target Features of the ClassifierFeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierFeatureMap_Feature()
	 * @see orgomg.cwm.objectmodel.core.Feature#getCfMap
	 * @model opposite="cfMap" required="true"
	 * @generated
	 */
	EList<Feature> getFeature();

} // ClassifierFeatureMap
