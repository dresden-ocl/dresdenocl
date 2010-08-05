/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vocabulary Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a common superclass for Concepts and Terms.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getDefinition <em>Definition</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getExample <em>Example</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getUsage <em>Usage</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getRelatedElement <em>Related Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getElement <em>Element</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement()
 * @model
 * @generated
 */
public interface VocabularyElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides the definition of the VocabularyElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see #setDefinition(String)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_Definition()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getDefinition();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getDefinition <em>Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(String value);

	/**
	 * Returns the value of the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Provides examples of the VocabularyElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Example</em>' attribute.
	 * @see #setExample(String)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_Example()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getExample();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getExample <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Example</em>' attribute.
	 * @see #getExample()
	 * @generated
	 */
	void setExample(String value);

	/**
	 * Returns the value of the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies typical usage of the VocabularyElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Usage</em>' attribute.
	 * @see #setUsage(String)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_Usage()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getUsage();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getUsage <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage</em>' attribute.
	 * @see #getUsage()
	 * @generated
	 */
	void setUsage(String value);

	/**
	 * Returns the value of the '<em><b>Related Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the related VocabularyElements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Element</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_RelatedElement()
	 * @see orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getElement
	 * @model opposite="element"
	 * @generated
	 */
	EList<VocabularyElement> getRelatedElement();

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getRelatedElement <em>Related Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a VocabularyElement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_Element()
	 * @see orgomg.cwm.analysis.businessnomenclature.VocabularyElement#getRelatedElement
	 * @model opposite="relatedElement"
	 * @generated
	 */
	EList<VocabularyElement> getElement();

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getVocabularyElement <em>Vocabulary Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElements for which the VocabularyElement provides business meaning.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getVocabularyElement_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getVocabularyElement
	 * @model opposite="vocabularyElement"
	 * @generated
	 */
	EList<ModelElement> getModelElement();

} // VocabularyElement
