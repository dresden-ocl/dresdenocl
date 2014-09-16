/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.analysis.transformation.ClassifierFeatureMap;
import orgomg.cwm.analysis.transformation.FeatureMap;

import orgomg.cwm.foundation.expressions.FeatureNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A feature is a property, like attribute or operation, which is encapsulated within a Classifier.
 * 
 * In the metamodel, a Feature declares a structural or behavioral characteristic of an instance of a Classifier or of the Classifier itself. Feature is an abstract metaclass.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Feature#getOwnerScope <em>Owner Scope</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Feature#getOwner <em>Owner</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Feature#getFeatureNode <em>Feature Node</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Feature#getFeatureMap <em>Feature Map</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Feature#getCfMap <em>Cf Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature()
 * @model abstract="true"
 * @generated
 */
public interface Feature extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Owner Scope</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.core.ScopeKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the Feature appears in every instance of the Classifier or whether it appears only once for the entire Classifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner Scope</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ScopeKind
	 * @see #setOwnerScope(ScopeKind)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature_OwnerScope()
	 * @model
	 * @generated
	 */
	ScopeKind getOwnerScope();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Feature#getOwnerScope <em>Owner Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner Scope</em>' attribute.
	 * @see orgomg.cwm.objectmodel.core.ScopeKind
	 * @see #getOwnerScope()
	 * @generated
	 */
	void setOwnerScope(ScopeKind value);

	/**
	 * Returns the value of the '<em><b>Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that owns the Feature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner</em>' container reference.
	 * @see #setOwner(Classifier)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature_Owner()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	Classifier getOwner();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Feature#getOwner <em>Owner</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner</em>' container reference.
	 * @see #getOwner()
	 * @generated
	 */
	void setOwner(Classifier value);

	/**
	 * Returns the value of the '<em><b>Feature Node</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.expressions.FeatureNode}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.expressions.FeatureNode#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the FeatureNode instances that use a particular Feature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature Node</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature_FeatureNode()
	 * @see orgomg.cwm.foundation.expressions.FeatureNode#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<FeatureNode> getFeatureNode();

	/**
	 * Returns the value of the '<em><b>Feature Map</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.FeatureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.FeatureMap#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the FeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature Map</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature_FeatureMap()
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<FeatureMap> getFeatureMap();

	/**
	 * Returns the value of the '<em><b>Cf Map</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ClassifierFeatureMap
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cf Map</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getFeature_CfMap()
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<ClassifierFeatureMap> getCfMap();

} // Feature
