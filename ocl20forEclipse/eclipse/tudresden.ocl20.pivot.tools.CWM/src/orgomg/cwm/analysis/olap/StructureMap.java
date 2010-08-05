/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import orgomg.cwm.analysis.transformation.TransformationMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structure Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * StructureMap is a subclass of TransformationMap that maps Dimension attributes to their physical data sources.
 * 
 * (Note: Multiple StructureMaps may be grouped together into single, cohesive unit via TransformationTask.)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV <em>Dimension Deployment LV</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP <em>Dimension Deployment IP</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getStructureMap()
 * @model
 * @generated
 */
public interface StructureMap extends TransformationMap {
	/**
	 * Returns the value of the '<em><b>Dimension Deployment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getStructureMap <em>Structure Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDeployment owning a StructureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment</em>' container reference.
	 * @see #setDimensionDeployment(DimensionDeployment)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getStructureMap_DimensionDeployment()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getStructureMap
	 * @model opposite="structureMap" required="true"
	 * @generated
	 */
	DimensionDeployment getDimensionDeployment();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment <em>Dimension Deployment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension Deployment</em>' container reference.
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	void setDimensionDeployment(DimensionDeployment value);

	/**
	 * Returns the value of the '<em><b>Dimension Deployment LV</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues <em>List Of Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDeployment referencing a "list of values" StructureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment LV</em>' reference.
	 * @see #setDimensionDeploymentLV(DimensionDeployment)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getStructureMap_DimensionDeploymentLV()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues
	 * @model opposite="listOfValues"
	 * @generated
	 */
	DimensionDeployment getDimensionDeploymentLV();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV <em>Dimension Deployment LV</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension Deployment LV</em>' reference.
	 * @see #getDimensionDeploymentLV()
	 * @generated
	 */
	void setDimensionDeploymentLV(DimensionDeployment value);

	/**
	 * Returns the value of the '<em><b>Dimension Deployment IP</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent <em>Immediate Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The DimensionDeployment referencing an "immedate parent" StructureMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension Deployment IP</em>' reference.
	 * @see #setDimensionDeploymentIP(DimensionDeployment)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getStructureMap_DimensionDeploymentIP()
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent
	 * @model opposite="immediateParent"
	 * @generated
	 */
	DimensionDeployment getDimensionDeploymentIP();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP <em>Dimension Deployment IP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension Deployment IP</em>' reference.
	 * @see #getDimensionDeploymentIP()
	 * @generated
	 */
	void setDimensionDeploymentIP(DimensionDeployment value);

} // StructureMap
