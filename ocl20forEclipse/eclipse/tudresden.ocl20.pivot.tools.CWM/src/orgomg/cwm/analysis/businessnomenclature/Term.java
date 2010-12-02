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
 * A representation of the model object '<em><b>Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents words or phrases used by business users to refer to Concepts.
 * 
 * A Term has a definition in a specific context. The context is provided by the referenced Concept that describes the underlying semantics.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getConcept <em>Concept</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getRelatedTerm <em>Related Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getTerm <em>Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getPreferredTerm <em>Preferred Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getSynonym <em>Synonym</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getWiderTerm <em>Wider Term</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Term#getNarrowerTerm <em>Narrower Term</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm()
 * @model
 * @generated
 */
public interface Term extends VocabularyElement {
	/**
	 * Returns the value of the '<em><b>Concept</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Concept}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Concept#getTerm <em>Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Concepts from which the Term is derived.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Concept</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_Concept()
	 * @see orgomg.cwm.analysis.businessnomenclature.Concept#getTerm
	 * @model opposite="term"
	 * @generated
	 */
	EList<Concept> getConcept();

	/**
	 * Returns the value of the '<em><b>Related Term</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getTerm <em>Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the related Terms.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Related Term</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_RelatedTerm()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getTerm
	 * @model opposite="term"
	 * @generated
	 */
	EList<Term> getRelatedTerm();

	/**
	 * Returns the value of the '<em><b>Term</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getRelatedTerm <em>Related Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Term.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Term</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_Term()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getRelatedTerm
	 * @model opposite="relatedTerm"
	 * @generated
	 */
	EList<Term> getTerm();

	/**
	 * Returns the value of the '<em><b>Preferred Term</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getSynonym <em>Synonym</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the preferred term for the synonym.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Preferred Term</em>' reference.
	 * @see #setPreferredTerm(Term)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_PreferredTerm()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getSynonym
	 * @model opposite="synonym"
	 * @generated
	 */
	Term getPreferredTerm();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.Term#getPreferredTerm <em>Preferred Term</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred Term</em>' reference.
	 * @see #getPreferredTerm()
	 * @generated
	 */
	void setPreferredTerm(Term value);

	/**
	 * Returns the value of the '<em><b>Synonym</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getPreferredTerm <em>Preferred Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Term.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Synonym</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_Synonym()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getPreferredTerm
	 * @model opposite="preferredTerm"
	 * @generated
	 */
	EList<Term> getSynonym();

	/**
	 * Returns the value of the '<em><b>Wider Term</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getNarrowerTerm <em>Narrower Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Term.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wider Term</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_WiderTerm()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getNarrowerTerm
	 * @model opposite="narrowerTerm"
	 * @generated
	 */
	EList<Term> getWiderTerm();

	/**
	 * Returns the value of the '<em><b>Narrower Term</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Term}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Term#getWiderTerm <em>Wider Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the narrower terms for the wider term.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Narrower Term</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTerm_NarrowerTerm()
	 * @see orgomg.cwm.analysis.businessnomenclature.Term#getWiderTerm
	 * @model opposite="widerTerm"
	 * @generated
	 */
	EList<Term> getNarrowerTerm();

} // Term
