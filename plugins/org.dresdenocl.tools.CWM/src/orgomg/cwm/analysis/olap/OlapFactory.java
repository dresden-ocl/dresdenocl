/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.analysis.olap.OlapPackage
 * @generated
 */
public interface OlapFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OlapFactory eINSTANCE = orgomg.cwm.analysis.olap.impl.OlapFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Content Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Map</em>'.
	 * @generated
	 */
	ContentMap createContentMap();

	/**
	 * Returns a new object of class '<em>Cube</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cube</em>'.
	 * @generated
	 */
	Cube createCube();

	/**
	 * Returns a new object of class '<em>Cube Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cube Deployment</em>'.
	 * @generated
	 */
	CubeDeployment createCubeDeployment();

	/**
	 * Returns a new object of class '<em>Cube Dimension Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cube Dimension Association</em>'.
	 * @generated
	 */
	CubeDimensionAssociation createCubeDimensionAssociation();

	/**
	 * Returns a new object of class '<em>Cube Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cube Region</em>'.
	 * @generated
	 */
	CubeRegion createCubeRegion();

	/**
	 * Returns a new object of class '<em>Deployment Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Group</em>'.
	 * @generated
	 */
	DeploymentGroup createDeploymentGroup();

	/**
	 * Returns a new object of class '<em>Dimension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension</em>'.
	 * @generated
	 */
	Dimension createDimension();

	/**
	 * Returns a new object of class '<em>Dimension Deployment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dimension Deployment</em>'.
	 * @generated
	 */
	DimensionDeployment createDimensionDeployment();

	/**
	 * Returns a new object of class '<em>Hierarchy Level Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hierarchy Level Association</em>'.
	 * @generated
	 */
	HierarchyLevelAssociation createHierarchyLevelAssociation();

	/**
	 * Returns a new object of class '<em>Level Based Hierarchy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Level Based Hierarchy</em>'.
	 * @generated
	 */
	LevelBasedHierarchy createLevelBasedHierarchy();

	/**
	 * Returns a new object of class '<em>Member Selection Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Member Selection Group</em>'.
	 * @generated
	 */
	MemberSelectionGroup createMemberSelectionGroup();

	/**
	 * Returns a new object of class '<em>Member Selection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Member Selection</em>'.
	 * @generated
	 */
	MemberSelection createMemberSelection();

	/**
	 * Returns a new object of class '<em>Schema</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Schema</em>'.
	 * @generated
	 */
	Schema createSchema();

	/**
	 * Returns a new object of class '<em>Value Based Hierarchy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Based Hierarchy</em>'.
	 * @generated
	 */
	ValueBasedHierarchy createValueBasedHierarchy();

	/**
	 * Returns a new object of class '<em>Level</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Level</em>'.
	 * @generated
	 */
	Level createLevel();

	/**
	 * Returns a new object of class '<em>Coded Level</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Coded Level</em>'.
	 * @generated
	 */
	CodedLevel createCodedLevel();

	/**
	 * Returns a new object of class '<em>Measure</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measure</em>'.
	 * @generated
	 */
	Measure createMeasure();

	/**
	 * Returns a new object of class '<em>Structure Map</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structure Map</em>'.
	 * @generated
	 */
	StructureMap createStructureMap();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OlapPackage getOlapPackage();

} //OlapFactory
