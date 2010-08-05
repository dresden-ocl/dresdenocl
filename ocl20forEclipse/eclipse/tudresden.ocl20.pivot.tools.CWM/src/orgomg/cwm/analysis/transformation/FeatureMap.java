/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Feature;
import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a mapping of source Features to target Features.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap <em>Classifier Map</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.FeatureMap#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getFeatureMap()
 * @model
 * @generated
 */
public interface FeatureMap extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Any code or script for the FeatureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function</em>' containment reference.
	 * @see #setFunction(ProcedureExpression)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getFeatureMap_Function()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getFunction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunction <em>Function</em>}' containment reference.
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
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getFeatureMap_FunctionDescription()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFunctionDescription();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunctionDescription <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Description</em>' attribute.
	 * @see #getFunctionDescription()
	 * @generated
	 */
	void setFunctionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Classifier Map</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFeatureMap <em>Feature Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owning ClassifierMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier Map</em>' container reference.
	 * @see #setClassifierMap(ClassifierMap)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getFeatureMap_ClassifierMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getFeatureMap
	 * @model opposite="featureMap"
	 * @generated
	 */
	ClassifierMap getClassifierMap();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap <em>Classifier Map</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier Map</em>' container reference.
	 * @see #getClassifierMap()
	 * @generated
	 */
	void setClassifierMap(ClassifierMap value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Feature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Feature#getFeatureMap <em>Feature Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the target Features of the FeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getFeatureMap_Target()
	 * @see orgomg.cwm.objectmodel.core.Feature#getFeatureMap
	 * @model opposite="featureMap" required="true"
	 * @generated
	 */
	EList<Feature> getTarget();

} // FeatureMap
