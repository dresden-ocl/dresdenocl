/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a mapping of source Classifiers to target Classifiers. A ClassifierMap may consists of a group of ClassifierFeatureMaps and/or FeatureMaps.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFeatureMap <em>Feature Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierMap#getCfMap <em>Cf Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.ClassifierMap#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap()
 * @model
 * @generated
 */
public interface ClassifierMap extends Namespace {
	/**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Any code or script for the ClassifierMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function</em>' containment reference.
	 * @see #setFunction(ProcedureExpression)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap_Function()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getFunction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunction <em>Function</em>}' containment reference.
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
	 * A short description for any code or script performed by the ClassifierMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Description</em>' attribute.
	 * @see #setFunctionDescription(String)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap_FunctionDescription()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFunctionDescription();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunctionDescription <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Description</em>' attribute.
	 * @see #getFunctionDescription()
	 * @generated
	 */
	void setFunctionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Feature Map</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.FeatureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owned FeatureMaps
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature Map</em>' containment reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap_FeatureMap()
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap
	 * @model opposite="classifierMap" containment="true"
	 * @generated
	 */
	EList<FeatureMap> getFeatureMap();

	/**
	 * Returns the value of the '<em><b>Cf Map</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owned ClassifierFeatureMaps
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cf Map</em>' containment reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap_CfMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap
	 * @model opposite="classifierMap" containment="true"
	 * @generated
	 */
	EList<ClassifierFeatureMap> getCfMap();

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Classifier}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the source Classifiers of the ClassifierMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getClassifierMap_Source()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getClassifierMap
	 * @model opposite="classifierMap" required="true"
	 * @generated
	 */
	EList<Classifier> getSource();

} // ClassifierMap
