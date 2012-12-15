/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.businessnomenclature;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concept</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a business idea or notion. 
 * 
 * Concepts are represented by Terms. Users use Terms that are familiar to them in their business environment to refer to Concepts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Concept#getTerm <em>Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Concept#getRelatedConcept <em>Related Concept</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Concept#getConcept <em>Concept</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getConcept()
 * @model
 * @generated
 */
public interface Concept extends VocabularyElement {
	/**
	 * Returns the value of the '<em><b>Term</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Term.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Term</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getConcept_Term()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getConcept
	 * @model opposite="concept"
	 * @generated
	 */
	EList<Term> getTerm();

	/**
	 * Returns the value of the '<em><b>Related Concept</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Concept}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Concept#getConcept <em>Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the related Concepts.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Concept</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getConcept_RelatedConcept()
	 * @see orgomg.cwm.analysis.businessnomenclature.Concept#getConcept
	 * @model opposite="concept"
	 * @generated
	 */
	EList<Concept> getRelatedConcept();

	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Concept}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Concept#getRelatedConcept <em>Related Concept</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Concept.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Concept</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getConcept_Concept()
	 * @see orgomg.cwm.analysis.businessnomenclature.Concept#getRelatedConcept
	 * @model opposite="relatedConcept"
	 * @generated
	 */
	EList<Concept> getConcept();

} // Concept
