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
 * A representation of the model object '<em><b>Content Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ContentMap is a subclass of TransformationMap that maps CubeRegion attributes to their physical data sources.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment <em>Cube Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getContentMap()
 * @model
 * @generated
 */
public interface ContentMap extends TransformationMap {
	/**
	 * Returns the value of the '<em><b>Cube Deployment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDeployment#getContentMap <em>Content Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CubeDeployment owning a ContentMap.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Deployment</em>' container reference.
	 * @see #setCubeDeployment(CubeDeployment)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getContentMap_CubeDeployment()
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getContentMap
	 * @model opposite="contentMap" required="true"
	 * @generated
	 */
	CubeDeployment getCubeDeployment();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment <em>Cube Deployment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube Deployment</em>' container reference.
	 * @see #getCubeDeployment()
	 * @generated
	 */
	void setCubeDeployment(CubeDeployment value);

} // ContentMap
