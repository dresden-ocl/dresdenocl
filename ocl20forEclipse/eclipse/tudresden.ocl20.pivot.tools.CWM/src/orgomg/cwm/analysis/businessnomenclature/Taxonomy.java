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
 * A representation of the model object '<em><b>Taxonomy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a collection of Concepts that form an ontology.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Taxonomy#getGlossary <em>Glossary</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTaxonomy()
 * @model
 * @generated
 */
public interface Taxonomy extends Nomenclature {
	/**
	 * Returns the value of the '<em><b>Glossary</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Glossary}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Glossary#getTaxonomy <em>Taxonomy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Glossary.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Glossary</em>' reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getTaxonomy_Glossary()
	 * @see orgomg.cwm.analysis.businessnomenclature.Glossary#getTaxonomy
	 * @model opposite="taxonomy"
	 * @generated
	 */
	EList<Glossary> getGlossary();

} // Taxonomy
