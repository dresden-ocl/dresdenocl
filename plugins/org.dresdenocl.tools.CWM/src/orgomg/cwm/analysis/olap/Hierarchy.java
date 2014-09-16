/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Hierarchy is an organizational structure that imposes a parent/child ordering on the members of the Dimension, usually to define either a navigational or consolidation/computational paths through the Dimension (i.e., a value associated with a child member is aggregated into one or more parents). 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.Hierarchy#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension <em>Defaulted Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Hierarchy#getCubeDimensionAssociation <em>Cube Dimension Association</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchy()
 * @model abstract="true"
 * @generated
 */
public interface Hierarchy extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Dimension#getHierarchy <em>Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Dimension owning Hierarchies.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' container reference.
	 * @see #setDimension(Dimension)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchy_Dimension()
	 * @see orgomg.cwm.analysis.olap.Dimension#getHierarchy
	 * @model opposite="hierarchy" required="true"
	 * @generated
	 */
	Dimension getDimension();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Hierarchy#getDimension <em>Dimension</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension</em>' container reference.
	 * @see #getDimension()
	 * @generated
	 */
	void setDimension(Dimension value);

	/**
	 * Returns the value of the '<em><b>Defaulted Dimension</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Dimension#getDisplayDefault <em>Display Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Dimension designating the Hierarchy as its default Hierarchy for display purposes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Defaulted Dimension</em>' reference.
	 * @see #setDefaultedDimension(Dimension)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchy_DefaultedDimension()
	 * @see orgomg.cwm.analysis.olap.Dimension#getDisplayDefault
	 * @model opposite="displayDefault"
	 * @generated
	 */
	Dimension getDefaultedDimension();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension <em>Defaulted Dimension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defaulted Dimension</em>' reference.
	 * @see #getDefaultedDimension()
	 * @generated
	 */
	void setDefaultedDimension(Dimension value);

	/**
	 * Returns the value of the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeDimensionAssociation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy <em>Calc Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CubeDimAssocs designating the default Hierarchy to be used in consolidation calculations performed on the Cube.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Dimension Association</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchy_CubeDimensionAssociation()
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy
	 * @model opposite="calcHierarchy"
	 * @generated
	 */
	EList<CubeDimensionAssociation> getCubeDimensionAssociation();

} // Hierarchy
