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
 * A representation of the model object '<em><b>Nomenclature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a common superclass for Taxonomy and Glossary.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Nomenclature#getParent <em>Parent</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.businessnomenclature.Nomenclature#getChild <em>Child</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getNomenclature()
 * @model
 * @generated
 */
public interface Nomenclature extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Nomenclature#getChild <em>Child</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the parent Nomenclature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Nomenclature)
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getNomenclature_Parent()
	 * @see orgomg.cwm.analysis.businessnomenclature.Nomenclature#getChild
	 * @model opposite="child"
	 * @generated
	 */
	Nomenclature getParent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.businessnomenclature.Nomenclature#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Nomenclature value);

	/**
	 * Returns the value of the '<em><b>Child</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.businessnomenclature.Nomenclature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.businessnomenclature.Nomenclature#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the child Nomenclatures.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Child</em>' containment reference list.
	 * @see orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage#getNomenclature_Child()
	 * @see orgomg.cwm.analysis.businessnomenclature.Nomenclature#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<Nomenclature> getChild();

} // Nomenclature
