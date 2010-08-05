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
 * A representation of the model object '<em><b>Level Based Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A LevelBasedHierarchy is a hierarchy that describes relationships between specific levels of a Dimension. LevelBasedHierarchy can be used to model to "pure level" hierarchies (e.g., dimension-level tables) and "mixed" hierarchies (i.e., levels + linked nodes).
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.LevelBasedHierarchy#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getLevelBasedHierarchy()
 * @model
 * @generated
 */
public interface LevelBasedHierarchy extends Hierarchy {
	/**
	 * Returns the value of the '<em><b>Hierarchy Level Association</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy <em>Level Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * HierarchyLevelAssocs owned by the LevelBasedHierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hierarchy Level Association</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getLevelBasedHierarchy_HierarchyLevelAssociation()
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy
	 * @model opposite="levelBasedHierarchy" containment="true"
	 * @generated
	 */
	EList<HierarchyLevelAssociation> getHierarchyLevelAssociation();

} // LevelBasedHierarchy
