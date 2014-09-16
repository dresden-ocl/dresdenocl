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
 * A representation of the model object '<em><b>Hierarchy Level Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HierarchyLevelAssociation is a class that orders Levels within a LevelBasedHierarchy, and provides a means of mapping Level/Hierarchy-oriented  Dimension attributes to one or more physical deployments.
 * 
 * The relative ordering of DimensionDeployment classes optionally implies a desired order of selection of DimensionDeployments, based on implementation-specific considerations (e.g., optimized access of aggregate data).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy <em>Level Based Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel <em>Current Level</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchyLevelAssociation()
 * @model
 * @generated
 */
public interface HierarchyLevelAssociation extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Level Based Hierarchy</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.LevelBasedHierarchy#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The LevelBasedHierarchy owning HierarchyLevelAssocs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Level Based Hierarchy</em>' container reference.
	 * @see #setLevelBasedHierarchy(LevelBasedHierarchy)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchyLevelAssociation_LevelBasedHierarchy()
	 * @see orgomg.cwm.analysis.olap.LevelBasedHierarchy#getHierarchyLevelAssociation
	 * @model opposite="hierarchyLevelAssociation" required="true"
	 * @generated
	 */
	LevelBasedHierarchy getLevelBasedHierarchy();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy <em>Level Based Hierarchy</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level Based Hierarchy</em>' container reference.
	 * @see #getLevelBasedHierarchy()
	 * @generated
	 */
	void setLevelBasedHierarchy(LevelBasedHierarchy value);

	/**
	 * Returns the value of the '<em><b>Current Level</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Level#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Level designated by a HierarchyLevelAssoc as its current level.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Current Level</em>' reference.
	 * @see #setCurrentLevel(Level)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchyLevelAssociation_CurrentLevel()
	 * @see orgomg.cwm.analysis.olap.Level#getHierarchyLevelAssociation
	 * @model opposite="hierarchyLevelAssociation" required="true"
	 * @generated
	 */
	Level getCurrentLevel();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel <em>Current Level</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Level</em>' reference.
	 * @see #getCurrentLevel()
	 * @generated
	 */
	void setCurrentLevel(Level value);

	/**
	 * Returns the value of the '<em><b>Dimension Deployment</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.DimensionDeployment}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDepolyments owned by a HierarchyLevelAssociation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getHierarchyLevelAssociation_DimensionDeployment()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation
	 * @model opposite="hierarchyLevelAssociation" containment="true"
	 * @generated
	 */
	EList<DimensionDeployment> getDimensionDeployment();

} // HierarchyLevelAssociation
