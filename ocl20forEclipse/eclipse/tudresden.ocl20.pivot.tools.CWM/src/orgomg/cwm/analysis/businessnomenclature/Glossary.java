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
 * A representation of the model object '<em><b>Glossary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a collection of Terms.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Glossary#getLanguage <em>Language</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Glossary#getTaxonomy <em>Taxonomy</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getGlossary()
 * @model
 * @generated
 */
public interface Glossary extends Nomenclature {
	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the language that the Glossary is represented in.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getGlossary_Language()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.Glossary#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Taxonomy</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Taxonomy}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Taxonomy#getGlossary <em>Glossary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Taxonomies from which the Glossary is derived.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Taxonomy</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getGlossary_Taxonomy()
	 * @see orgomg.cwm.analysis.businessnomenclature.Taxonomy#getGlossary
	 * @model opposite="glossary"
	 * @generated
	 */
	EList<Taxonomy> getTaxonomy();

} // Glossary
