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
 * A representation of the model object '<em><b>Level</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Level is a subclass of MemberSelection that assigns each member of a Dimension to a specific hierarchical level within the Dimension.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.Level#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getLevel()
 * @model
 * @generated
 */
public interface Level extends MemberSelection {
	/**
	 * Returns the value of the '<em><b>Hierarchy Level Association</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel <em>Current Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The HierarchyLevelAssocs designating this Level as their current level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hierarchy Level Association</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getLevel_HierarchyLevelAssociation()
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel
	 * @model opposite="currentLevel"
	 * @generated
	 */
	EList<HierarchyLevelAssociation> getHierarchyLevelAssociation();

} // Level
